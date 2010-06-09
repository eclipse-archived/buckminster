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

import java.util.ArrayList;

/**
 * Manages a set of child nodes. When children are added or removed, a
 * "childNodeChanged(this)" bubbles up the tree towards the root.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class AbstractTreeParentDataNode extends AbstractTreeDataNode implements ITreeParentDataNode {
	private ArrayList<ITreeDataNode> children;

	public AbstractTreeParentDataNode() {
		children = new ArrayList<ITreeDataNode>();
	}

	/**
	 * Adds a child node and notifies the parent that this node has changed.
	 */
	@Override
	public void addChild(ITreeDataNode child) {
		children.add(child);
		child.setParent(this);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Disposes all children
	 */
	@Override
	public void dispose() {
		// dispose all recursively
		dispose(getChildren());
	}

	/**
	 * Returns a child node that has a data object that equals the searched
	 * data. Only the immediate children are searched for matching data.
	 */
	@Override
	public ITreeDataNode findChild(Object data) {
		for (ITreeDataNode node : children)
			if (node.getData().equals(data))
				return node;
		return null;
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	/**
	 * Returns the children of this node.
	 */
	@Override
	public ITreeDataNode[] getChildren() {
		return children.toArray(new ITreeDataNode[children.size()]);
	}

	@Override
	public boolean hasChildren() {
		return children.size() > 0;
	}

	/**
	 * Does nothing
	 */
	@Override
	public void onOpen() {
		// Does nothing
	}

	@Override
	public void removeAllChildren() {
		for (ITreeDataNode child : children)
			child.setParent(null);
		children.clear();
		childNodeChanged(this);
	}

	/**
	 * Removes a child node and notifies the parent that this node has changed.
	 */
	@Override
	public void removeChild(ITreeDataNode child) {
		children.remove(child);
		child.setParent(null);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Replaces a child node and notifies the parent that this node has changed.
	 */
	@Override
	public void replaceChild(ITreeDataNode child, ITreeDataNode newChild) {
		int idx = children.indexOf(child);
		children.set(idx, newChild);
		child.setParent(null);
		newChild.setParent(this);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Replaces a child node with several children and notifies the parent that
	 * this node has changed.
	 */
	@Override
	public void replaceChild(ITreeDataNode child, ITreeDataNode[] newChildren) {
		int idx = children.indexOf(child);
		children.remove(idx);
		child.setParent(null);
		for (int i = 0; i < newChildren.length; i++) {
			children.add(idx + i, newChildren[i]);
			newChildren[i].setParent(this);
		}
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Recursive disposal of children
	 * 
	 * @param children
	 */
	private void dispose(ITreeDataNode childArray[]) {
		if (childArray == null || childArray.length < 1)
			return;
		for (int i = 0; i < childArray.length; i++)
			childArray[i].dispose();
	}
}
