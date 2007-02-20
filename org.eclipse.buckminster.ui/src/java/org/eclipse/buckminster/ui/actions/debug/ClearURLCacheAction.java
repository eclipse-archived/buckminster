/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions.debug;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.resolver.ResourceMapCache;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @author Thomas Hallgren
 *
 */
public class ClearURLCacheAction implements IWorkbenchWindowActionDelegate
{
	public void dispose()
	{
	}

	public void init(IWorkbenchWindow window)
	{
	}

	public void run(IAction action)
	{
		CorePlugin plugin = CorePlugin.getDefault();
		plugin.clearRemoteFileCache();
		plugin.clearURLCache();
		ResourceMapCache.clearCache();
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}
}
