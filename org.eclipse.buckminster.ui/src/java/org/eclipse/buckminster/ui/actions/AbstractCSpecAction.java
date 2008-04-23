/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractCSpecAction implements IObjectActionDelegate
{
	private IWorkbenchPart m_activePart;

	private CSpec m_selectedComponent;

	public void run(IAction action)
	{
		if(m_activePart == null)
			return;

		IWorkbenchPartSite site = m_activePart.getSite();
		final Shell shell = site.getShell();
		if(m_selectedComponent == null)
		{
			MessageDialog.openInformation(shell, null, "No component is selected");
			return;
		}
		run(m_selectedComponent, m_activePart.getSite().getShell());
	}

	protected abstract void run(CSpec cspec, Shell shell);

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_activePart = targetPart;
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
		m_selectedComponent = null;
		if(!(selection instanceof IStructuredSelection))
			return;

		IStructuredSelection s = (IStructuredSelection)selection;
		if(s.size() != 1)
			return;

		Object first = s.getFirstElement();
		// If the selected object is a CSpec, or adaptable to CSpec, use it.
		if(first instanceof IAdaptable)
		{
			m_selectedComponent = (CSpec)((IAdaptable)first).getAdapter(CSpec.class);
			if(m_selectedComponent != null)
				return;
		}
		if(!(first instanceof IResource))
			return;

		// TODO - this can be very nicely replaced with an adapter factory for IResource
		IResource resource = (IResource)first;
		while(resource != null)
		{
			try
			{
				CSpec cspec = WorkspaceInfo.getCSpec(resource);
				if(cspec != null)
				{
					m_selectedComponent = cspec;
					break;
				}
				resource = resource.getParent();
			}
			catch(CoreException e)
			{
				resource = null;
				CorePlugin.getLogger().warning(e, e.getMessage());
			}
		}
	}

	protected void setSelectedComponent(CSpec cspec)
	{
		m_selectedComponent = cspec;
	}
}
