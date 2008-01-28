package org.eclipse.buckminster.pde.ui.actions;

import org.eclipse.buckminster.ui.actions.InvokeAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;

public class InvokeBundleAction extends InvokeAction
{
	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{
		setSelectedComponent(SelectionHelper.selectionChanged(selection));
	}
}
