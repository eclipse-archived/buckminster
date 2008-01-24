/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.ui.wizards.QueryWizard;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ImportAction implements IObjectActionDelegate
{
	private IWorkbenchPart m_workbenchPart;
	private IFile m_fileToImport;

	public void dispose()
	{
	}

	public void run(IAction action)
	{
		if(m_fileToImport != null)
			QueryWizard.openWizard(m_workbenchPart, new StructuredSelection(m_fileToImport));
	}

	public void selectionChanged(IAction action, ISelection sel)
	{
		m_fileToImport = null;
		if(!(sel instanceof IStructuredSelection))
			return;

		IStructuredSelection selection = (IStructuredSelection)sel;
		if(selection.size() == 1)
		{
			Object selected = selection.getFirstElement();
			if(selected instanceof IFile)
				m_fileToImport = (IFile)selected;
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_workbenchPart = targetPart;
	}
}
