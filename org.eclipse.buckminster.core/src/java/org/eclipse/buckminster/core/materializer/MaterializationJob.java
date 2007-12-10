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
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
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
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.service.prefs.BackingStoreException;

/**
 * A job that will materialize according to specifications.
 * @author Thomas Hallgren
 */
public class MaterializationJob extends Job
{
	public static final String MAX_PARALLEL_JOBS = "maxParallelMaterializationJobs";

	public static final int MAX_PARALLEL_JOBS_DEFAULT = 4;

	public static void setUp()
	{
		IEclipsePreferences defaultNode = BuckminsterPreferences.getDefaultNode();
		defaultNode.putInt(MAX_PARALLEL_JOBS, MAX_PARALLEL_JOBS_DEFAULT);
		try
		{
			defaultNode.flush();
		}
		catch(BackingStoreException e)
		{
			Buckminster.getLogger().error(e.toString(), e);
		}		
	}

	public static int getMaxParallelJobs()
	{
		return BuckminsterPreferences.getNode().getInt(MAX_PARALLEL_JOBS, MAX_PARALLEL_JOBS_DEFAULT);
	}

	private final MaterializationContext m_context;

	private final boolean m_waitForInstall;

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
		catch(OperationCanceledException e)
		{
			throw e;
		}
		catch(Throwable t)
		{
			throw BuckminsterException.wrap(t);
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

	protected MaterializationJob(MaterializationContext ctx, boolean waitForInstall)
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

	protected void internalRun(final IProgressMonitor monitor) throws CoreException
	{
		BillOfMaterials bom = m_context.getBillOfMaterials();

		Queue<MaterializerJob> allJobs = prepareJobs(monitor, bom);
		
		if(allJobs != null)
		{
			triggerJobs(monitor, allJobs);
			waitForJobs(monitor, allJobs, bom);
		}

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

	protected Queue<MaterializerJob> prepareJobs(IProgressMonitor monitor, BillOfMaterials bom) throws CoreException
	{
		CorePlugin corePlugin = CorePlugin.getDefault();
		Map<String,List<Resolution>> resPerMat = new LinkedHashMap<String, List<Resolution>>();
		MaterializationSpec mspec = m_context.getMaterializationSpec();
		for(Resolution cr : bom.findMaterializationCandidates(m_context, mspec))
		{
			String materializer = mspec.getMaterializerID(cr);
			List<Resolution> crs = resPerMat.get(materializer);
			if(crs == null)
			{
				crs = new ArrayList<Resolution>();
				resPerMat.put(materializer, crs);
			}
			crs.add(cr);
		}

		if(resPerMat.size() == 0)
			return null;

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
		
		return allJobs;
	}
	
	protected void triggerJobs(final IProgressMonitor monitor, final Queue<MaterializerJob> allJobs)
	{
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
				if(monitor.isCanceled() || (!m_context.isContinueOnError() && m_context.getStatus().getSeverity() == IStatus.ERROR))
					cancel();
			}

			@Override
			public void done(IJobChangeEvent event)
			{
				if(!monitor.isCanceled())
				{
					MaterializerJob mjob = allJobs.poll();
					if(mjob != null)
					{
						mjob.addJobChangeListener(this);
						mjob.schedule();
					}
				}	
			}
		};

		int maxJobs = m_context.getMaxParallelJobs();
		for(int idx = 0; idx < maxJobs; ++idx)
		{
			MaterializerJob job = allJobs.poll();
			if(job == null)
				break;

			job.addJobChangeListener(listener);
			job.schedule();
		}
	}
	
	protected void waitForJobs(IProgressMonitor monitor, Queue<MaterializerJob> allJobs, BillOfMaterials bom) throws CoreException
	{
		// Wait until all jobs have completed
		//
		IJobManager jobManager = Job.getJobManager();
		try
		{
			jobManager.join(m_context, monitor);
		}
		catch(OperationCanceledException e)
		{
			jobManager.cancel(m_context);
			allJobs.clear();
			throw e;
		}
		catch(InterruptedException e)
		{
			jobManager.cancel(m_context);
			allJobs.clear();
			throw new OperationCanceledException();
		}

		IStatus status = m_context.getStatus();
		if(!m_context.isContinueOnError() && status.getSeverity() == IStatus.ERROR)
		{
			m_context.clearStatus();
			throw new CoreException(status);
		}
	}

	protected MaterializationContext getMaterializationContext()
	{
		return m_context;
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
