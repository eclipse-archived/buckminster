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

package org.eclipse.buckminster.generic.model.tree;

import java.util.EventListener;

/**
 * Interface for a listener to TreeDataEvents.
 * 
 * @author Henrik Lindberg
 * 
 */
public interface ITreeDataListener extends EventListener {
	public void treeNodeChanged(TreeDataEvent event);
}
