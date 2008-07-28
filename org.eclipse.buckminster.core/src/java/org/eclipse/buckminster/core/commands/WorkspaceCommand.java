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
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.materializer.WorkspaceBindingInstallJob;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.internal.resources.DelayedSnapshotJob;
import org.eclipse.core.internal.resources.ResourceException;
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

/**
 * The workspace command ensures that the workspace is in good shape
 * when the command terminates.
 *
 * @author Thomas Hallgren
 *
 */
@SuppressWarnings("restriction")
public abstract class WorkspaceCommand extends AbstractCommand
{
	@Override
	protected final int run(IProgressMonitor monitor) throws Exception
	{
		monitor.beginTask(null, 1000);

		// We block some jobs that are targeted for IDE development but
		// considered bad during a headless build.
		//
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addClassBlock("org.eclipse.core.internal.events.AutoBuildJob");
		jobBlocker.addClassBlock("org.eclipse.jdt.internal.core.search.processing.JobManager$1$ProgressJob");

		try
		{
			initWorkspace(MonitorUtils.subMonitor(monitor, 50));
			jobBlocker.addClassBlock(DelayedSnapshotJob.class);
			return internalRun(MonitorUtils.subMonitor(monitor, 900));
		}
		finally
		{
			final Logger logger = CorePlugin.getLogger();
			logger.debug("Doing full workspace refresh");
			try
			{
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));
			}
			catch(Throwable e)
			{
				Buckminster.getLogger().error("Error while refreshing workspace: " + e.getMessage(), e);
			}

			// Suspend the job manager temporarily and wait for all jobs to drain
			//
			final IJobManager jobManager = Job.getJobManager();
			jobManager.suspend();

			// Cancel jobs that are known to run indefinitely
			//
			WorkspaceBindingInstallJob.stop();
			for(Job job : jobManager.find(null))
			{
				if(job instanceof StringPoolJob)
					job.cancel();
			}

			// We wait for current jobs to end but we use a timeout since there might be jobs
			// that run forever.
			//
			Thread joinWait = new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						jobManager.join(null, new NullProgressMonitor());
					}
					catch(InterruptedException e)
					{
						for(Job job : jobManager.find(null))
						{
							int state = job.getState();
							if(state == Job.RUNNING)
								logger.debug("  JOB: %s is still running", job.toString());
						}
					}
				}
			};
			logger.debug("Waiting for jobs to end");
			
			// Wait at max 30 seconds for all jobs to complete. The normal case is that
			// the join returns very quickly.
			//
			joinWait.start();
			joinWait.join(30000);
			joinWait.interrupt();

			// Cancel remaining jobs
			//
			for(Job job : jobManager.find(null))
				job.cancel();

			// and resume the job manager. The workspace save will start new
			// jobs.
			//
			jobBlocker.removeClassBlock(DelayedSnapshotJob.class);
			jobManager.resume();
			saveWorkspace(MonitorUtils.subMonitor(monitor, 50));
			monitor.done();
		}
	}

	protected void initWorkspace(IProgressMonitor monitor) throws Exception
	{
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription wsDesc = ws.getDescription();
		wsDesc.setAutoBuilding(false);
		wsDesc.setSnapshotInterval(Long.MAX_VALUE);
		ws.setDescription(wsDesc);
		saveWorkspace(monitor);
	}

	protected abstract int internalRun(IProgressMonitor monitor) throws Exception;

	private static void saveWorkspace(IProgressMonitor monitor)
	{
		monitor.beginTask(null, 300);
		try
		{
			IStatus saveStatus = ResourcesPlugin.getWorkspace().save(true, MonitorUtils.subMonitor(monitor, 100));
			if(!(saveStatus == null || saveStatus.isOK()))
				throw new ResourceException(saveStatus);
		}
		catch(Throwable e)
		{
			Buckminster.getLogger().error(e, "Error while saving workspace: %s", e.getMessage());
		}
		monitor.done();
	}
}
