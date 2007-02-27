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

import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IncrementalProjectBuilder;
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
	private final RMContext m_context;

	private final BillOfMaterials m_billOfMaterials;

	private final Set<Resolution> m_skipThese;

	private final IMaterializer m_materializer;

	public static void run(BillOfMaterials bom, RMContext ctx, Set<Resolution> skipThese, IProgressMonitor monitor)
	throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);

		// set up for reporting at least some progress
		//
		try
		{
			monitor.beginTask(null, IProgressMonitor.UNKNOWN);
			MaterializerJob mbJob = new MaterializerJob(bom, ctx, skipThese);
			mbJob.schedule();
			mbJob.join(); // longrunning
			IStatus status = mbJob.getResult();

			if(status.getSeverity() == IStatus.CANCEL)
				throw new OperationCanceledException();
			if(!status.isOK())
				throw new CoreException(status);
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
			monitor.done();
		}
	}

	public static void runDelegated(BillOfMaterials bom, RMContext context, Set<Resolution> skipThese,
		final IProgressMonitor monitor) throws CoreException
	{
		MaterializerJob mbJob = new MaterializerJob(bom, context, skipThese);
		IStatus status = mbJob.runInWorkspace(monitor);
		if(status.getSeverity() == IStatus.CANCEL)
			throw new OperationCanceledException();
		if(!status.isOK())
			throw new CoreException(status);
	}

	private MaterializerJob(BillOfMaterials bom, RMContext ctx, Set<Resolution> skipThese) throws CoreException
	{
		super("Materializing and binding");
		m_billOfMaterials = bom;
		m_context = ctx;
		m_skipThese = skipThese;
		m_materializer = CorePlugin.getDefault().getMaterializer(ctx.get(IMaterializer.MATERIALIZER_PROPERTY));

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(LONG);
		this.setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException
	{
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addNameBlock("Building workspace");
		jobBlocker.addNameBlock("Periodic workspace save.");
		monitor.beginTask(null, 1000);
		try
		{
			m_materializer.materialize(m_billOfMaterials, m_skipThese, m_context, MonitorUtils.subMonitor(monitor, 800));
			m_materializer.performInstallActions(m_billOfMaterials, m_skipThese, m_context, MonitorUtils.subMonitor(monitor, 100));
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
			return e.getStatus();
		}
		finally
		{
			monitor.done();
			jobBlocker.release();
		}

		// We blocked all workspace builds during this process so
		// initiating a new one here is necessary.
		//
		ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, MonitorUtils.subMonitor(monitor, 100));
		return Status.OK_STATUS;
	}
}
