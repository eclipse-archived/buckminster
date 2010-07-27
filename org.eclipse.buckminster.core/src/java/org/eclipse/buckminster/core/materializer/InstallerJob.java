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
import org.eclipse.buckminster.core.metadata.MetadataSynchronizer;
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
public class InstallerJob extends WorkspaceJob {
	private final MaterializationContext context;

	private final boolean propagateStatus;

	public InstallerJob(MaterializationContext ctx, boolean propagateStatus) throws CoreException {
		super(Messages.InstallerJob_Installing);
		this.context = ctx;
		this.propagateStatus = propagateStatus;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(BUILD);
		this.setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 1000);
		BillOfMaterials bom = context.getBillOfMaterials();
		try {
			AbstractMaterializer.performInstallActions(bom, context, MonitorUtils.subMonitor(monitor, 100));
			MetadataSynchronizer.workspaceCatchUp(monitor);
		} catch (CoreException e) {
			context.addRequestStatus(bom.getRequest(), e.getStatus());
			if (propagateStatus)
				CorePlugin.getLogger().error(e, e.getMessage());
		} finally {
			monitor.done();
			if (propagateStatus)
				context.emitWarningAndErrorTags();
		}
		return Status.OK_STATUS;
	}
}
