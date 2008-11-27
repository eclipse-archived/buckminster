/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.CSpecEditorInput;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ViewChosenCSpecAction extends AbstractChosenCSpecAction
{
	@Override
	protected void run(CSpec cspec, IWorkbenchWindow wbWin)
	{
		IWorkbenchPage page = wbWin.getActivePage();
		if(page == null)
			return;

		IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
		CSpecEditorInput input = new CSpecEditorInput(cspec);
		IEditorDescriptor ed = editorRegistry.getDefaultEditor(input.getName(), input.getContentDescription()
				.getContentType());
		try
		{
			page.openEditor(input, ed.getId());
		}
		catch(PartInitException e)
		{
			UiUtils.openError(page.getWorkbenchWindow().getShell(), Messages.unable_to_open_editor, e);
		}
	}
}
