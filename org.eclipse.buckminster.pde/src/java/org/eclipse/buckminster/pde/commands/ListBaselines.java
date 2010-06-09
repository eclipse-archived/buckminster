/*****************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.io.PrintStream;

import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.api.tools.internal.provisional.ApiPlugin;
import org.eclipse.pde.api.tools.internal.provisional.IApiBaselineManager;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiBaseline;

@SuppressWarnings("restriction")
public class ListBaselines extends WorkspaceCommand {
	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		PrintStream out = System.out;
		out.println(NLS.bind(org.eclipse.buckminster.core.Messages.Using_workspace_at_0, Platform.getInstanceLocation().getURL().toString()));
		IApiBaselineManager baselineManager = ApiPlugin.getDefault().getApiBaselineManager();
		IApiBaseline[] baselines = baselineManager.getApiBaselines();
		if (baselines.length == 0) {
			out.println(Messages.No_baselines_found_in_workspace);
			return 0;
		}

		IApiBaseline active = baselineManager.getDefaultApiBaseline();
		for (IApiBaseline baseline : baselines) {
			if (baseline == active)
				out.print('*');
			else
				out.print(' ');
			out.println(baseline.getName());
		}
		return 0;
	}
}
