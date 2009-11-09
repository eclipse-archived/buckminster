/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.presentation;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @author Karel Brezina
 * 
 */
public class FindNextIUAction extends AbstractHandler
{

	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		IWorkbenchWindow wbWindow = AggregatorEditorPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow();
		if(!((AggregatorEditor)wbWindow.getActivePage().getActivePart()).findNextIU(true))
			wbWindow.getShell().getDisplay().beep();

		return null;
	}

}
