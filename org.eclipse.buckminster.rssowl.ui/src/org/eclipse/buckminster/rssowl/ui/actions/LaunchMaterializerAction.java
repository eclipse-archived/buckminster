/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
 
package org.eclipse.buckminster.rssowl.ui.actions;

import org.eclipse.buckminster.jnlp.ide.IDEApplication;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;


/**
 * An Test action that triggers a hardcoded materialization from Cloudsmith.
 * @author Henrik Lindberg
 *
 */
public class LaunchMaterializerAction implements IViewActionDelegate
{

	public void init(IViewPart view)
	{
	}

	public void run(IAction action)
	{
		IDEApplication app = new IDEApplication();
		try
		{
			app.start("http://www.cloudsmith.com/dynamic/prop/jnlp/mspec-81428344.prop");
		}
		catch(Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}

}
