/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.materializer.WorkspaceBindingInstallJob;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
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

	private boolean inWorkspace = false;

	public boolean isInWorkspace() {
		return inWorkspace;
	}

	public void setInWorkspace(boolean inWorkspace) {
		this.inWorkspace = inWorkspace;
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

		try {
			if (!isInWorkspace())
				initWorkspace(MonitorUtils.subMonitor(monitor, 50));

			jobBlocker.addClassBlock(DelayedSnapshotJob.class);
			return internalRun(MonitorUtils.subMonitor(monitor, 900));
		} finally {
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
							// Cancel remaining jobs
							//
							for (Job job : jobManager.find(null)) {
								int state = job.getState();
								if (state != Job.NONE) {
									logger.debug("  JOB: %s is still active", job.toString()); //$NON-NLS-1$
									job.cancel();
								}
							}
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

				// and resume the job manager. The workspace save will start
				// new
				// jobs.
				//
				jobBlocker.removeClassBlock(DelayedSnapshotJob.class);
				saveWorkspace(MonitorUtils.subMonitor(monitor, 50));
				monitor.done();
			}
		}
	}
}
