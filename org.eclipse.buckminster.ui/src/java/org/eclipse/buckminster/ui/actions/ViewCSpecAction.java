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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.CSpecEditorInput;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ViewCSpecAction extends ArrayList<CSpec> implements IObjectActionDelegate
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6061262678606311390L;

	/**
	 * Opens a CSpec viewer on the given file resource.
	 * @param file the file resource
	 */
	void openCSpec(CSpec cspec)
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
		IEditorDescriptor ed = editorRegistry.getDefaultEditor(input.getName(), input.getContentDescription().getContentType());
		try
		{
			page.openEditor(input, ed.getId());
		}
		catch(PartInitException e)
		{
			UiUtils.openError(page.getWorkbenchWindow().getShell(), "Unable to open editor", e);
		}
	}

	/*
	 * (non-Javadoc) Method declared on IAction.
	 */
	public void run(IAction action)
	{
		for(CSpec cspec : this)
			openCSpec(cspec);
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
		this.clear();
		if(!(selection instanceof IStructuredSelection))
			return;

		Iterator<?> iter = ((IStructuredSelection)selection).iterator();
		while (iter.hasNext())
		{
			Object tmp = iter.next();
			if(!(tmp instanceof IResource))
				continue;

			IResource resource = (IResource)tmp;
			while(resource != null)
			{
				try
				{
					CSpec cspec = WorkspaceInfo.getCSpec(resource);
					if(cspec != null)
					{
						if(!contains(cspec))
							add(cspec);
						break;
					}
					resource = resource.getParent();
				}
				catch(CoreException e1)
				{
				}
			}
		}
	}
}
