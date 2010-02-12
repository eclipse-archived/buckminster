/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.materializer.WorkspaceBindingInstallJob;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.internal.resources.DelayedSnapshotJob;
import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.utils.StringPoolJob;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;

/**
 * The workspace command ensures that the workspace is in good shape when the
 * command terminates.
 * 
 * @author Thomas Hallgren
 * 
 */
@SuppressWarnings("restriction")
public abstract class WorkspaceCommand extends AbstractCommand {
	private static final Pattern DEFINE_PATTERN = Pattern.compile("^([^=]+)(?:=(.+))?$"); //$NON-NLS-1$

	static private final OptionDescriptor DEFINE_DESCRIPTOR = new OptionDescriptor('D', "define", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	static private final OptionDescriptor PROPERTIES_DESCRIPTOR = new OptionDescriptor('P', "properties", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static void saveWorkspace(IProgressMonitor monitor) {
		monitor.beginTask(null, 300);
		try {
			IStatus saveStatus = ((Workspace) ResourcesPlugin.getWorkspace()).save(true, true, MonitorUtils.subMonitor(monitor, 100));
			if (!(saveStatus == null || saveStatus.isOK()))
				throw new ResourceException(saveStatus);
		} catch (Throwable e) {
			Buckminster.getLogger().error(e, NLS.bind(Messages.Error_while_saving_workspace_0, e.getMessage()));
		}
		monitor.done();
	}

	private Map<String, String> props;

	private boolean inWorkspace = false;

	public void addProperties(Map<String, String> properties) {
		if (props == null)
			props = new HashMap<String, String>(properties);
		else
			props.putAll(properties);
	}

	public void addProperty(String key, String value) {
		if (props == null)
			props = new HashMap<String, String>();
		props.put(key, value);
	}

	public boolean isInWorkspace() {
		return inWorkspace;
	}

	public void setInWorkspace(boolean inWorkspace) {
		this.inWorkspace = inWorkspace;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(DEFINE_DESCRIPTOR);
		appendHere.add(PROPERTIES_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(DEFINE_DESCRIPTOR)) {
			String v = option.getValue();
			Matcher m = DEFINE_PATTERN.matcher(v);
			if (!m.matches())
				throw new IllegalArgumentException(NLS.bind(Messages.Not_a_key_value_string_0, v));
			String key = m.group(1);
			String value = m.group(2) == null ? "" //$NON-NLS-1$
					: m.group(2);
			addProperty(key, value);
		}
		if (option.is(PROPERTIES_DESCRIPTOR)) {
			String v = option.getValue();
			InputStream input = null;
			try {
				URL propsURL = URLUtils.normalizeToURL(v);
				input = DownloadManager.read(propsURL, null);
				addProperties(new BMProperties(input));
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(NLS.bind(Messages.Invalid_URL_or_Path_0, v));
			} finally {
				IOUtils.close(input);
			}
		}
	}

	protected void initWorkspace(IProgressMonitor monitor) throws Exception {
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription wsDesc = ws.getDescription();
		wsDesc.setAutoBuilding(false);
		wsDesc.setSnapshotInterval(Long.MAX_VALUE);
		ws.setDescription(wsDesc);
		saveWorkspace(monitor);
	}

	protected abstract int internalRun(IProgressMonitor monitor) throws Exception;

	@Override
	protected final int run(IProgressMonitor monitor) throws Exception {
		monitor.beginTask(null, 1000);

		// We block some jobs that are targeted for IDE development but
		// considered bad during a headless build.
		//
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addClassBlock("org.eclipse.core.internal.events.AutoBuildJob"); //$NON-NLS-1$
		jobBlocker.addClassBlock("org.eclipse.jdt.internal.core.search.processing.JobManager$1$ProgressJob"); //$NON-NLS-1$

		Properties sysProps = System.getProperties();
		try {
			if (!isInWorkspace())
				initWorkspace(MonitorUtils.subMonitor(monitor, 50));

			if (props != null) {
				Properties newProps = new Properties(sysProps);
				newProps.putAll(props);
				System.setProperties(newProps);
			}
			jobBlocker.addClassBlock(DelayedSnapshotJob.class);
			return internalRun(MonitorUtils.subMonitor(monitor, 900));
		} finally {
			try {
				if (isInWorkspace())
					jobBlocker.release();
				else {
					final Logger logger = CorePlugin.getLogger();
					logger.debug("Doing full workspace refresh"); //$NON-NLS-1$
					try {
						ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));
					} catch (Throwable e) {
						Buckminster.getLogger().error(NLS.bind(Messages.Error_while_refreshing_workspace_0, e.getMessage()), e);
					}

					// Suspend the job manager temporarily and wait for all jobs
					// to drain
					//
					final IJobManager jobManager = Job.getJobManager();

					// Cancel jobs that are known to run indefinitely
					//
					WorkspaceBindingInstallJob.stop();
					for (Job job : jobManager.find(null)) {
						if (job instanceof StringPoolJob || job instanceof DelayedSnapshotJob)
							job.cancel();
					}

					// We wait for current jobs to end but we use a timeout
					// since there might be jobs
					// that run forever.
					//
					Thread joinWait = new Thread() {
						@Override
						public void run() {
							try {
								jobManager.join(null, new NullProgressMonitor());
							} catch (InterruptedException e) {
							}
						}
					};
					logger.debug("Waiting for jobs to end"); //$NON-NLS-1$

					// Wait at max 60 seconds for all jobs to complete. The
					// normal case is that
					// the join returns very quickly.
					//
					joinWait.start();
					joinWait.join(60000);
					joinWait.interrupt();

					// Cancel remaining jobs
					//
					for (Job job : jobManager.find(null)) {
						int state = job.getState();
						if (state != Job.NONE) {
							logger.debug("  JOB: %s is still active", job.toString()); //$NON-NLS-1$
							job.cancel();
						}
					}

					// and resume the job manager. The workspace save will start
					// new
					// jobs.
					//
					jobBlocker.removeClassBlock(DelayedSnapshotJob.class);
					saveWorkspace(MonitorUtils.subMonitor(monitor, 50));
					monitor.done();
				}
			} finally {
				if (props != null)
					System.setProperties(sysProps);
			}
		}
	}
}
