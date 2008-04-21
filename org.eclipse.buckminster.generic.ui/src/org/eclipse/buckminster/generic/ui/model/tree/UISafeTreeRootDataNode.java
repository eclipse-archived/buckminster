/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.buckminster.generic.ui.model.tree;

import org.eclipse.buckminster.generic.model.tree.AbstractTreeRootDataNode;
import org.eclipse.buckminster.generic.model.tree.TreeDataEvent;
import org.eclipse.swt.widgets.Display;

/**
 * A Tree Root that is safe to use when triggering event listeners that must be called on
 * the UI thread.
 * 
 * @author Henrik Lindberg
 *
 */
public class UISafeTreeRootDataNode extends AbstractTreeRootDataNode 
{
	public UISafeTreeRootDataNode(Object data)
	{
		super(data);
	}

	@Override
	protected void triggerListeners(final TreeDataEvent e)
	{
		// Make sure listeners are notified in the UI thread
		Display.getDefault().asyncExec(new Runnable()
		{
			public void run()
			{
				UISafeTreeRootDataNode.this.inProcTriggerListeners(e);
			}
			
		});
	}

}
