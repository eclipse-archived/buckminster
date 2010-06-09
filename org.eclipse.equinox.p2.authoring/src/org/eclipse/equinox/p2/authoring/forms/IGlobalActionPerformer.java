// TODO: Remove license that should not be used
/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/
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

package org.eclipse.equinox.p2.authoring.forms;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.Clipboard;

/**
 * @author henrik
 *
 */
public interface IGlobalActionPerformer
{
	/**
	 * Perform a global action as identified by the global action id, and return true if the action was handled.
	 * @param globalActionId
	 * @return true if action was handled.
	 */
	boolean doGlobalAction(String globalActionId);
	
	/**
	 * Query method to determine if this global action performer can paste the content of the clipboard.
	 * @param clipboard
	 * @return true if content can be pasted.
	 */
	boolean canPaste(Clipboard clipboard);

	/**
	 * Query method to determine if this global action performer can paste the content of the clipboard.
	 * @param clipboard
	 * @return true if content can be pasted.
	 */
	boolean canCopy(ISelection selection);

	/**
	 * Query method to determine if this global action performer can paste the content of the clipboard.
	 * @param clipboard
	 * @return true if content can be pasted.
	 */
	boolean canCut(ISelection selection);
	
}
