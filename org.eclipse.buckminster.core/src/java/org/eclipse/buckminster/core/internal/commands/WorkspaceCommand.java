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
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;

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
		monitor.beginTask(null, 1000);
		try
		{
			return this.internalRun(MonitorUtils.subMonitor(monitor, 900));
		}
		finally
		{
			saveWorkspace(MonitorUtils.subMonitor(monitor, 100));
			monitor.done();
		}
	}

	protected abstract int internalRun(IProgressMonitor monitor) throws Exception;

	private static void saveWorkspace(IProgressMonitor monitor)
	{
		try
		{
			ResourcesPlugin.getWorkspace().save(true, monitor);
		}
		catch(Throwable e)
		{
			Buckminster.getLogger().error("Error while saving workspace: " + e.getMessage(), e);
		}
	}
}
