/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.commands;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * The workspace command ensures that the workspace is in good shape
 * when the command terminates.
 *
 * @author Thomas Hallgren
 *
 */
public abstract class WorkspaceCommand extends AbstractCommand
{
	@SuppressWarnings("restriction")
	@Override
	protected final int run(IProgressMonitor monitor) throws Exception
	{
		try
		{
			return this.internalRun(monitor);
		}
		finally
		{
			closeWorkspace();
		}
	}

	protected abstract int internalRun(IProgressMonitor monitor) throws Exception;

	@SuppressWarnings("restriction")
	private static void closeWorkspace()
	{
		try
		{
			((org.eclipse.core.internal.resources.Workspace)ResourcesPlugin.getWorkspace()).close(new NullProgressMonitor());
		}
		catch(Throwable e)
		{
			Buckminster.getLogger().error("Error while closing workspace: " + e.getMessage(), e);
		}
	}
}
