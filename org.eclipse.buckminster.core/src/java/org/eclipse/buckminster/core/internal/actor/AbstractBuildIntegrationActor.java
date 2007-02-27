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
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
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
	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		int kind = WellknownActions.ECLIPSE.name2Kind(this.getNameForKind(ctx));

		// We build the workspace here. There's no way we can build individual
		// projects. We prevent repeated invocations of this workspace build using
		// the GlobalContext.hasExecutedKind(kind) method
		//
		GlobalContext globCtx = ((PerformContext)ctx).getGlobalContext();
		if(globCtx.hasExecutedKind(kind))
		{
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		monitor.beginTask(null, 200);
		try
		{
			IWorkspace ws = ResourcesPlugin.getWorkspace();
			ws.getRoot().refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 100));
			ws.build(kind, MonitorUtils.subMonitor(monitor, 100));
			for(IMarker problem : ResourcesPlugin.getWorkspace().getRoot().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE))
			{
				switch(problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO))
				{
				case IMarker.SEVERITY_ERROR:
					throw new CoreException(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, problem.getAttribute(IMarker.MESSAGE, ""), null));
				case IMarker.SEVERITY_WARNING:
					globCtx.kindWasExecuted(kind);
					return new Status(IStatus.WARNING, CorePlugin.getID(), IStatus.OK, problem.getAttribute(IMarker.MESSAGE, ""), null);
				}
			}
			globCtx.kindWasExecuted(kind);
			return Status.OK_STATUS;
		}
		finally
		{
			monitor.done();
		}
	}

	protected String getNameForKind(IActionContext ctx)
	{
		return ctx.getAction().getName();
	}
}
