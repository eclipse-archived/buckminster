/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.rssowl.ui;

import org.eclipse.buckminster.rssowl.OwlSynchronizer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * An action to trigger OWL sync
 * @author henrik
 *
 */
public class SynchronizeRssOwlAction implements IViewActionDelegate
{

	public void init(IViewPart view)
	{
	}

	public void run(IAction action)
	{
		try
		{
			OwlSynchronizer.syncAllResolutions();
		}
		catch(CoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}

}
