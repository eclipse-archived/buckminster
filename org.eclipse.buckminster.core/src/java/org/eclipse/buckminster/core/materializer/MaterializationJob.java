/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;

/**
 * A job that will materialize according to specifications.
 * @author Thomas Hallgren
 */
public class MaterializationJob extends Job
{
	private final MaterializationContext m_context;

	private final boolean m_waitForInstall;

	private final int m_maxParallelJobs = 4;

	public static void run(MaterializationContext context, boolean waitForInstall)
	throws CoreException
	{
		try
		{
			MaterializationJob mbJob = new MaterializationJob(context, waitForInstall);
			mbJob.schedule();
			mbJob.join(); // long running
			IStatus status = mbJob.getResult();

			if(status.getSeverity() == IStatus.CANCEL)
				throw new OperationCanceledException();
			if(!status.isOK())
				throw new CoreException(status);
			
			// We wait to give the event manager a chance to deliver all
			// events while the JobBlocker still active. This gives us
			// a chance to add dynamic dependencies to projects
			//
			Thread.sleep(3000);
		}
		catch(InterruptedException e)
		{
			throw new OperationCanceledException();
		}
		catch(Throwable t)
		{
			throw new RuntimeException("Unexpected error", t);
		}
	}

	/**
	 * Runs this job immediately without scheduling. This method is intended to be called
	 * when the materialization is done from the GUI and uses a <code>IRunnableWithProgress</code>
	 *
	 * @param context
	 * @param monitor
	 * @throws CoreException
	 */
	public static void runDelegated(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		MaterializationJob mbJob = new MaterializationJob(context, false);
		mbJob.internalRun(monitor);
	}

	private MaterializationJob(MaterializationContext ctx, boolean waitForInstall)
	{
		super("Materializing");
		m_context = ctx;
		m_waitForInstall = waitForInstall;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(false);
		this.setPriority(LONG);
	}

	private void internalRun(IProgressMonitor monitor) throws CoreException
	{
		CorePlugin corePlugin = CorePlugin.getDefault();
		Map<String,List<Resolution>> resPerMat = new LinkedHashMap<String, List<Resolution>>();
		MaterializationSpec mspec = m_context.getMaterializationSpec();
		BillOfMaterials bom = m_context.getBillOfMaterials();
		for(Resolution cr : bom.findMaterializationCandidates(mspec))
		{
			String materializer = mspec.getMaterializerID(cr.getComponentIdentifier());
			List<Resolution> crs = resPerMat.get(materializer);
			if(crs == null)
			{
				crs = new ArrayList<Resolution>();
				resPerMat.put(materializer, crs);
			}
			crs.add(cr);
		}

		if(resPerMat.size() == 0)
		{
			bom.store();
			return;
		}

		final Queue<MaterializerJob> allJobs = new LinkedList<MaterializerJob>();
		for(Map.Entry<String, List<Resolution>> entry : resPerMat.entrySet())
		{
			IMaterializer materializer = corePlugin.getMaterializer(entry.getKey());
			List<Resolution> resolutions = entry.getValue();
			if(materializer.canWorkInParallel())
			{
				// Start one job for each resolution
				//
				for(Resolution res : resolutions)
					allJobs.offer(new MaterializerJob(entry.getKey(), materializer, Collections.singletonList(res), m_context));
			}
			else
				allJobs.offer(new MaterializerJob(entry.getKey(), materializer, resolutions, m_context));
		}

		// -- Schedule at most m_maxParallelJobs. After that, let the termination of
		// a job schedule a new one until the queue is empty.
		//
		// This is the listener that schedule a new job on termination of another
		//
		IJobChangeListener listener = new JobChangeAdapter()
		{
			@Override
			public void done(IJobChangeEvent event)
			{
				MaterializerJob mjob = allJobs.poll();
				if(mjob != null)
				{
					mjob.addJobChangeListener(this);
					mjob.schedule();
				}
			}
		};

		for(int idx = 0; idx < m_maxParallelJobs; ++idx)
		{
			MaterializerJob job = allJobs.poll();
			if(job == null)
				break;

			job.addJobChangeListener(listener);
			job.schedule();
		}

		// Wait until all jobs have completed
		//
		IJobManager jobManager = Job.getJobManager();
		try
		{
			jobManager.join(m_context, monitor);
		}
		catch(OperationCanceledException e)
		{
			return;
		}
		catch(InterruptedException e)
		{
			return;
		}

		bom.store();
		InstallerJob installerJob = new InstallerJob(m_context);
		installerJob.schedule();
		if(m_waitForInstall)
		{
			try
			{
				installerJob.join();
			}
			catch(InterruptedException e)
			{
				throw new OperationCanceledException();
			}
		}
	}

	@Override
	public IStatus run(IProgressMonitor monitor)
	{
		try
		{
			internalRun(monitor);
			return Status.OK_STATUS;
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			return e.getStatus();
		}
		catch(OperationCanceledException e)
		{
			return Status.CANCEL_STATUS;
		}
	}
}
