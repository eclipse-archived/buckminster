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

import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IAdaptable;

/**
 * A node in a Tree Data tree.
 * 
 * @author Henrik Lindberg
 * 
 */
public interface ITreeDataNode extends PropertyChangeListener, IAdaptable {
	static final ITreeDataNode[] EMPTY_NODE_ARRAY = new ITreeDataNode[0];

	/**
	 * Should be called when a node has been changed - either this node, or a
	 * node below. The event should bubble up to the root of the tree.
	 */
	void childNodeChanged(ITreeDataNode child);

	void dispose();

	/**
	 * Return the data this node represents.
	 * 
	 * @return
	 */
	Object getData();

	ITreeParentDataNode getParent();

	void setParent(ITreeParentDataNode parent);

	@Override
	String toString();
}
