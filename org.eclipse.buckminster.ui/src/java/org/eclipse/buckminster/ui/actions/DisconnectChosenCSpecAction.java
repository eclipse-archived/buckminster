/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class DisconnectChosenCSpecAction extends AbstractChosenCSpecAction
{
	@Override
	protected void run(CSpec cspec, IWorkbenchWindow wbWin)
	{
		IWorkbenchPage page = wbWin.getActivePage();
		if(page == null)
			return;

		try
		{
			ComponentIdentifier wanted = cspec.getComponentIdentifier();
			for(Resolution res : WorkspaceInfo.getAllResolutions())
			{
				ComponentIdentifier cid = res.getCSpec().getComponentIdentifier();
				if(wanted.matches(cid))
				{
					res.remove(StorageManager.getDefault());
				}
			}
		}
		catch(CoreException e)
		{
			UiUtils.openError(page.getWorkbenchWindow().getShell(), Messages.unable_to_open_editor, e);
		}
	}
}
