/*****************************************************************************
 * Copyright (c) 2008-2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.io.PrintStream;

import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetHandle;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;

public class ListTargetDefinitions extends WorkspaceCommand {
	public static String getTargetName(ITargetDefinition target) throws CoreException {
		String name = target.getName();
		if (name != null) {
			name = name.trim();
			if (name.length() > 0)
				return name;
			name = null;
		}
		ITargetLocation[] bundleContainers = target.getTargetLocations();
		if (bundleContainers != null && bundleContainers.length == 1) {
			return bundleContainers[0].getLocation(false);
		}
		return "<no name>"; //$NON-NLS-1$
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		PrintStream out = System.out;
		out.println(NLS.bind(Messages.Using_workspace_at_0, Platform.getInstanceLocation().getURL().toString()));
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		int maxNameLen = -1;
		ITargetHandle[] handles = service.getTargets(monitor);
		int top = handles.length;
		if (top == 0) {
			out.println(Messages.No_target_definitions_found_in_workspace);
			return 0;
		}

		ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
		ITargetDefinition activeTarget = null;
		ITargetDefinition[] targets = new ITargetDefinition[top];
		for (int idx = 0; idx < top; ++idx) {
			ITargetHandle targetHandle = handles[idx];
			ITargetDefinition target = targetHandle.getTargetDefinition();
			targets[idx] = target;
			if (targetHandle.equals(activeHandle))
				activeTarget = target;

			String name = getTargetName(target);
			int nameLen = name.length();
			if (nameLen > maxNameLen)
				maxNameLen = nameLen;
		}

		ITargetPlatform tp = TargetPlatform.getInstance();
		for (int idx = 0; idx < top; ++idx) {
			ITargetDefinition target = targets[idx];
			String name = getTargetName(target);
			int nameLen = name.length();
			if (target == activeTarget)
				out.print('*');
			else
				out.print(' ');
			out.print(' ');
			out.print(name);
			while (nameLen++ < maxNameLen)
				out.print(' ');
			out.print(" : "); //$NON-NLS-1$

			String os = target.getOS();
			String ws = target.getWS();
			String arch = target.getArch();
			String nl = target.getNL();
			if (os == null)
				os = tp.getOS();
			if (ws == null)
				ws = tp.getWS();
			if (arch == null)
				arch = tp.getArch();
			if (nl == null)
				nl = tp.getNL();
			out.print(os);
			out.print(',');
			out.print(ws);
			out.print(',');
			out.print(arch);
			if (nl != null) {
				out.print('/');
				out.print(nl);
			}
			out.println();
		}
		return 0;
	}
}
