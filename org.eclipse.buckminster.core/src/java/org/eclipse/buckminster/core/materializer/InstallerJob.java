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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * A job that will materialize and bind according to specifications.
 * 
 * @author Thomas Hallgren
 */
public class InstallerJob extends WorkspaceJob
{
	private final MaterializationContext m_context;

	private final boolean m_propagateStatus;

	public InstallerJob(MaterializationContext ctx, boolean propagateStatus) throws CoreException
	{
		super(Messages.InstallerJob_Installing);
		m_context = ctx;
		m_propagateStatus = propagateStatus;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(BUILD);
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
			m_context.addRequestStatus(bom.getRequest(), e.getStatus());
			if(m_propagateStatus)
				CorePlugin.getLogger().error(e, e.getMessage());
		}
		finally
		{
			monitor.done();
			if(m_propagateStatus)
				m_context.emitWarningAndErrorTags();
		}
		return Status.OK_STATUS;
	}
}
