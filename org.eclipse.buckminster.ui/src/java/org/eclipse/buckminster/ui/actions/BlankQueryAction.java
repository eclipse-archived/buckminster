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

import java.io.File;

import org.eclipse.buckminster.ui.ExternalFileEditorInput;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

public class BlankQueryAction implements IObjectActionDelegate
{
	public static final String TEMP_FILE_PREFIX = "bmqtmp-"; //$NON-NLS-1$

	private IWorkbenchPart m_targetPart;

	public void run(IAction action)
	{
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPartSite site = m_targetPart.getSite();
		IEditorDescriptor ed = workbench.getEditorRegistry().getDefaultEditor("buckminster.cquery"); //$NON-NLS-1$
		try
		{
			File tempFile = File.createTempFile(TEMP_FILE_PREFIX, ".cquery"); //$NON-NLS-1$
			tempFile.deleteOnExit();
			site.getPage().openEditor(new ExternalFileEditorInput(tempFile), ed.getId());
		}
		catch(Exception e)
		{
			UiUtils.openError(site.getShell(), Messages.unable_to_open_editor, e);
		}
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_targetPart = targetPart;
	}
}
