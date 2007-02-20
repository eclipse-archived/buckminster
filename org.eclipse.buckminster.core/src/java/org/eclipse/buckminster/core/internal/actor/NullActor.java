/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author kolwing
 * 
 */
public class NullActor extends AbstractActor
{
	public static final String ID = "null";

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return Status.OK_STATUS;
	}
}
