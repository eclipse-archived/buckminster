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

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.runtime.BuckminsterException;
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
 * 
 * @author Thomas Hallgren
 */
public class InstallerJob extends WorkspaceJob
{
	public static void run(MaterializationContext context) throws CoreException
	{
		try
		{
			InstallerJob mbJob = new InstallerJob(context);
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
		catch(OperationCanceledException e)
		{
			throw e;
		}
		catch(Throwable t)
		{
			throw BuckminsterException.wrap(t);
		}
	}

	public static void runDelegated(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		InstallerJob mbJob = new InstallerJob(context);
		IStatus status = mbJob.runInWorkspace(monitor);
		if(status.getSeverity() == IStatus.CANCEL)
			throw new OperationCanceledException();
		if(!status.isOK())
			throw new CoreException(status);
	}

	private final MaterializationContext m_context;

	public InstallerJob(MaterializationContext ctx) throws CoreException
	{
		super("Installing");
		m_context = ctx;

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
		monitor.beginTask(null, 1000);
		BillOfMaterials bom = m_context.getBillOfMaterials();
		try
		{
			AbstractMaterializer.performInstallActions(bom, m_context, MonitorUtils.subMonitor(monitor, 100));
		}
		catch(CoreException e)
		{
			if(!m_context.isContinueOnError())
				throw e;
			m_context.addRequestStatus(bom.getRequest(), e.getStatus());
		}
		finally
		{
			monitor.done();
		}
		return Status.OK_STATUS;
	}
}
