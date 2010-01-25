/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author kolwing
 * 
 */
public abstract class AbstractBuildIntegrationActor extends AbstractActor
{
	protected String getNameForKind(IActionContext ctx)
	{
		return ctx.getAction().getName();
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 300);
		try
		{
			IProject project = WorkspaceInfo.getProject(ctx.getCSpec().getComponentIdentifier());
			if(project == null)
				return Status.OK_STATUS;

			project.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 100));
			project.build(WellknownActions.ECLIPSE.name2Kind(getNameForKind(ctx)),
					MonitorUtils.subMonitor(monitor, 200));
			for(IMarker problem : project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE))
			{
				switch(problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO))
				{
				case IMarker.SEVERITY_ERROR:
					throw new CoreException(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK,
							problem.getAttribute(IMarker.MESSAGE, ""), null)); //$NON-NLS-1$
				case IMarker.SEVERITY_WARNING:
					return new Status(IStatus.WARNING, CorePlugin.getID(), IStatus.OK, problem.getAttribute(
							IMarker.MESSAGE, ""), null); //$NON-NLS-1$
				}
			}
			return Status.OK_STATUS;
		}
		finally
		{
			monitor.done();
		}
	}
}
