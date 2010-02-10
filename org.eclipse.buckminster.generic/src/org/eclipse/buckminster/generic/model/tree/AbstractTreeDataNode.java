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

import java.beans.PropertyChangeEvent;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * A node (leaf or parent) in a data tree. A node has a Data Element that it
 * represents. This abstract class handles property change listening - See
 * "propertyChange(PropertyChangeEvent)" method, and bubbling of node change
 * notification towards the root of the tree.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class AbstractTreeDataNode implements ITreeDataNode, IAdaptable {
	private ITreeParentDataNode parent;

	public AbstractTreeDataNode() {
		parent = null;
	}

	/**
	 * Notifies the parent of this node that the node 'child' has changed in
	 * some way.
	 */
	@Override
	public void childNodeChanged(ITreeDataNode child) {
		ITreeParentDataNode p = getParent();
		if (p != null)
			p.childNodeChanged(child);
	}

	/**
	 * Does nothing.
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Default implementation of IAdaptable.getAdapter() - if the data object is
	 * instance of the wanted class, it is returned immediately.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		Object data = getData();
		if (adapter.isInstance(data))
			return data;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public abstract Object getData();

	@Override
	public ITreeParentDataNode getParent() {
		return parent;
	}

	/**
	 * If a subclass adds itself as a listener, this method will be called. This
	 * method bubbles a childNodeChanged for this node up the tree. If a derived
	 * class wants to do something else, this method should be overridden and
	 * this 'super method' does not have to be called. The dervied class should
	 * call childNodeChanged on itself if the event caused some change in the
	 * node (or among its children; if the node is a parent node).
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ITreeParentDataNode p = getParent();
		if (p != null)
			p.childNodeChanged(this);
	}

	@Override
	public void setParent(ITreeParentDataNode parent) {
		this.parent = parent;
	}

	@Override
	public abstract String toString();
}
