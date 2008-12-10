package org.eclipse.buckminster.pde.ui.actions;

import org.eclipse.buckminster.ui.ViewCSpecAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;

public class ViewBundleCSpecAction extends ViewCSpecAction
{
	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{
		setSelectedComponent(SelectionHelper.selectionChanged(selection));
	}
}
