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
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;

/**
 * A job that will materialize and bind according to specifications.
 * @author Thomas Hallgren
 */
public class MaterializerJob extends WorkspaceJob
{
	private final MaterializationContext m_context;

	public static void run(MaterializationContext context)
	throws CoreException
	{
		JobBlocker blocker = blockJobs();
		try
		{
			MaterializerJob mbJob = new MaterializerJob(context);
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
		finally
		{
			blocker.release();
		}
	}

	public static void runDelegated(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		MaterializerJob mbJob = new MaterializerJob(context);
		IStatus status = mbJob.runInWorkspace(monitor);
		if(status.getSeverity() == IStatus.CANCEL)
			throw new OperationCanceledException();
		if(!status.isOK())
			throw new CoreException(status);
	}

	private MaterializerJob(MaterializationContext ctx) throws CoreException
	{
		super("Materializing and binding");
		m_context = ctx;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(LONG);
		this.setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	public static JobBlocker blockJobs()
	{
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addNameBlock("Building workspace");
		jobBlocker.addNameBlock("Periodic workspace save.");
		return jobBlocker;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException
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
			AbstractMaterializer.performInstallActions(bom, m_context, MonitorUtils.subMonitor(monitor, 100));
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			return e.getStatus();
		}
		finally
		{
			monitor.done();
		}
		return Status.OK_STATUS;
	}
}
