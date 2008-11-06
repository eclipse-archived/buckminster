package org.eclipse.buckminster.jnlp.p2;

import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

public class JNLPMaterializationJob extends MaterializationJob
{
	protected JNLPMaterializationJob(MaterializationContext ctx, boolean waitForInstall)
	{
		super(ctx, waitForInstall);
	}

	public static void runDelegated(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		JNLPMaterializationJob mbJob = new JNLPMaterializationJob(context, true);
		mbJob.internalRun(monitor);
	}

	@Override
	protected void triggerJobs(final IProgressMonitor monitor, final Queue<MaterializerJob> allJobs)
	{
		final Queue<MaterializerJob> jobsToWakeUp = new LinkedList<MaterializerJob>();

		// -- Schedule at most m_maxParallelJobs. After that, let the termination of
		// a job schedule a new one until the queue is empty.
		//
		// This is the listener that schedule a new job on termination of another
		//
		IJobChangeListener listener = new JobChangeAdapter()
		{
			@Override
			public void aboutToRun(IJobChangeEvent event)
			{
				if(monitor.isCanceled() ||
						(!getMaterializationContext().isContinueOnError() && getMaterializationContext().getStatus().getSeverity() == IStatus.ERROR))
					cancel();
			}

			@Override
			public void done(IJobChangeEvent event)
			{
				if(!monitor.isCanceled())
				{
					MaterializerJob mjob = jobsToWakeUp.poll();
					if(mjob != null)
					{
						mjob.wakeUp();
					}
				}	
			}
		};

		int maxJobs = getMaterializationContext().getMaxParallelJobs();
		int jobs = allJobs.size();
		for(int idx = 0; idx < jobs; ++idx)
		{
			MaterializerJob job = allJobs.poll();
			if(job == null)
				break;

			job.addJobChangeListener(listener);
			if(idx < maxJobs)
			{
				job.schedule();
			} else
			{
				job.schedule(10); // really don't want to start it now
				job.sleep();
				jobsToWakeUp.offer(job);
			}
		}
	}
}
