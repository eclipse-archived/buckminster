/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.commands;

import java.io.PrintStream;

import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;

@SuppressWarnings("restriction")
public class ListTargetDefinitions extends WorkspaceCommand
{
	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		PrintStream out = System.out;
		out.println(NLS.bind(Messages.Using_workspace_at_0, Platform.getInstanceLocation().getURL().toString()));
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		int maxNameLen = -1;
		ITargetHandle[] handles = service.getTargets(monitor);
		int top = handles.length;
		if(top == 0)
		{
			out.println(Messages.No_target_definitions_found_in_workspace);
			return 0;
		}

		ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
		ITargetDefinition activeTarget = null;
		ITargetDefinition[] targets = new ITargetDefinition[top];
		for(int idx = 0; idx < top; ++idx)
		{
			ITargetHandle targetHandle = handles[idx];
			ITargetDefinition target = targetHandle.getTargetDefinition();
			targets[idx] = target;
			if(targetHandle.equals(activeHandle))
				activeTarget = target;

			int nameLen = target.getName().length();
			if(nameLen > maxNameLen)
				maxNameLen = nameLen;
		}

		ITargetPlatform tp = TargetPlatform.getInstance();
		for(int idx = 0; idx < top; ++idx)
		{
			ITargetDefinition target = targets[idx];
			String name = target.getName();
			int nameLen = name.length();
			if(target == activeTarget)
				out.print('*');
			else
				out.print(' ');
			out.print(' ');
			out.print(name);
			while(nameLen++ < maxNameLen)
				out.print(' ');
			out.print(" : "); //$NON-NLS-1$

			String os = target.getOS();
			String ws = target.getWS();
			String arch = target.getArch();
			String nl = target.getNL();
			if(os == null)
				os = tp.getOS();
			if(ws == null)
				ws = tp.getWS();
			if(arch == null)
				arch = tp.getArch();
			if(nl == null)
				nl = tp.getNL();
			out.print(os);
			out.print(',');
			out.print(ws);
			out.print(',');
			out.print(arch);
			if(nl != null)
			{
				out.print('/');
				out.print(nl);
			}
			out.println();
		}
		return 0;
	}
}
