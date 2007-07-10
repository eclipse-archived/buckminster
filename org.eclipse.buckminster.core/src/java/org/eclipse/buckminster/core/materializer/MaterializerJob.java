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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * A job that will materialize according to specifications.
 * @author Thomas Hallgren
 */
public class MaterializerJob extends Job
{
	private final MaterializationContext m_context;

	private final boolean m_waitForInstall;

	public static void run(MaterializationContext context, boolean waitForInstall)
	throws CoreException
	{
		try
		{
			MaterializerJob mbJob = new MaterializerJob(context, waitForInstall);
			mbJob.schedule();
			mbJob.join(); // longrunning
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
		MaterializerJob mbJob = new MaterializerJob(context, false);
		mbJob.internalRun(monitor);
	}

	private MaterializerJob(MaterializationContext ctx, boolean waitForInstall)
	{
		super("Materializing");
		m_context = ctx;
		m_waitForInstall = waitForInstall;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(LONG);
	}

	private void internalRun(IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1000);
		try
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
	
			if (resPerMat.size() > 0)
			{
				int ticksPerM = 800 / resPerMat.size();
				for(Map.Entry<String, List<Resolution>> entry : resPerMat.entrySet())
				{
					IMaterializer materializer = corePlugin.getMaterializer(entry.getKey());
					materializer.materialize(entry.getValue(), m_context, MonitorUtils.subMonitor(monitor, ticksPerM));
				}
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
		finally
		{
			monitor.done();
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
