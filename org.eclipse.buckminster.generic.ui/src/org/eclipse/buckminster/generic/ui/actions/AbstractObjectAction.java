/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.generic.ui.actions;

import org.eclipse.buckminster.generic.ui.Messages;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Operate on the selected object adapted to generic <T>.
 * 
 * @author Henrik Lindberg
 */
public abstract class AbstractObjectAction<T> implements IObjectActionDelegate
{
	private IWorkbenchPart m_activePart;

	private T m_selected;

	public Shell getShell()
	{
		if(m_activePart == null)
			throw new IllegalStateException(Messages.active_part_not_set);
		return m_activePart.getSite().getShell();
	}

	public void run(IAction action)
	{
		if(m_activePart == null)
			return;

		IWorkbenchPartSite site = m_activePart.getSite();
		final Shell shell = site.getShell();
		if(m_selected == null)
		{
			MessageDialog.openInformation(shell, null, Messages.nothing_selected);
			return;
		}
		run(m_selected, m_activePart.getSite().getShell());
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
		m_selected = null;
		if(!(selection instanceof IStructuredSelection))
			return;

		IStructuredSelection s = (IStructuredSelection)selection;
		if(s.size() != 1)
			return;

		Object first = s.getFirstElement();
		// If the selected object is an instance of wanted type, or is adaptable to that type, use it.

		if(getType().isInstance(first))
			m_selected = getType().cast(first);
		else
			m_selected = adapt(first);

		action.setEnabled(m_selected != null);

	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
		m_activePart = targetPart;
	}

	protected T adapt(Object selected)
	{
		return getType().cast(((IAdaptable)selected).getAdapter(getType()));
	}

	protected abstract Class<T> getType();

	protected abstract void run(T instance, Shell shell);

	protected void setSelected(T selected)
	{
		m_selected = selected;
	}

	protected boolean showConfirm(String title, String question)
	{
		return MessageDialog.openConfirm(getShell(), title, question);
	}

	protected void showError(String title, String message)
	{
		ErrorDialog.openError(getShell(), title, message, new Status(IStatus.ERROR,
				"org.eclipse.buckminster.generic.ui.actions", //$NON-NLS-1$
				message));
	}

	protected void showError(String title, String message, Throwable e)
	{
		ErrorDialog.openError(getShell(), title, message, new Status(IStatus.ERROR,
				"org.eclipse.buckminster.generic.ui.actions", //$NON-NLS-1$
				e.getMessage(), e));
	}

	protected void showMessage(String title, String message)
	{
		MessageDialog.openInformation(getShell(), title, message);
	}

}
