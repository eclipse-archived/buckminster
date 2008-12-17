/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.commands;

import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;

public class Clean extends WorkspaceCommand
{
	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length > 0)
			throw new UsageException(Messages.Too_many_arguments);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IProject[] projs = ws.getRoot().getProjects();

		try
		{
			monitor.beginTask(null, projs.length);
			ws.build(IncrementalProjectBuilder.CLEAN_BUILD, MonitorUtils.subMonitor(monitor, projs.length));
		}
		finally
		{
			monitor.done();
		}

		return 0;
	}

}
