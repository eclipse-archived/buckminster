/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.CSpecEditorInput;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ViewCSpecAction extends AbstractCSpecAction
{
	@Override
	protected void run(CSpec cspec, Shell shell)
	{
		IWorkbench workbench = PlatformUI.getWorkbench();

		IWorkbenchWindow wbWin = workbench.getActiveWorkbenchWindow();
		if(wbWin == null)
			return;

		IWorkbenchPage page = wbWin.getActivePage();
		if(page == null)
			return;

		IEditorRegistry editorRegistry = workbench.getEditorRegistry();
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
