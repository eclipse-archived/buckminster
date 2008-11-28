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
 * Manages a set of child nodes. When children are added or removed, a "childNodeChanged(this)" bubbles up the tree
 * towards the root.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class AbstractTreeParentDataNode extends AbstractTreeDataNode implements ITreeParentDataNode
{
	private ArrayList<ITreeDataNode> m_children;

	public AbstractTreeParentDataNode()
	{
		m_children = new ArrayList<ITreeDataNode>();
	}

	/**
	 * Adds a child node and notifies the parent that this node has changed.
	 */
	public void addChild(ITreeDataNode child)
	{
		m_children.add(child);
		child.setParent(this);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Disposes all children
	 */
	@Override
	public void dispose()
	{
		// dispose all recursively
		dispose(getChildren());
	}

	/**
	 * Returns a child node that has a data object that equals the searched data. Only the immediate children are
	 * searched for matching data.
	 */
	public ITreeDataNode findChild(Object data)
	{
		for(ITreeDataNode node : m_children)
			if(node.getData().equals(data))
				return node;
		return null;
	}

	public int getChildCount()
	{
		return m_children.size();
	}

	/**
	 * Returns the children of this node.
	 */
	public ITreeDataNode[] getChildren()
	{
		return m_children.toArray(new ITreeDataNode[m_children.size()]);
	}

	public boolean hasChildren()
	{
		return m_children.size() > 0;
	}

	/**
	 * Does nothing
	 */
	public void onOpen()
	{
		// Does nothing
	}

	public void removeAllChildren()
	{
		for(ITreeDataNode child : m_children)
			child.setParent(null);
		m_children.clear();
		childNodeChanged(this);
	}

	/**
	 * Removes a child node and notifies the parent that this node has changed.
	 */
	public void removeChild(ITreeDataNode child)
	{
		m_children.remove(child);
		child.setParent(null);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Replaces a child node and notifies the parent that this node has changed.
	 */
	public void replaceChild(ITreeDataNode child, ITreeDataNode newChild)
	{
		int idx = m_children.indexOf(child);
		m_children.set(idx, newChild);
		child.setParent(null);
		newChild.setParent(this);
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Replaces a child node with several children and notifies the parent that this node has changed.
	 */
	public void replaceChild(ITreeDataNode child, ITreeDataNode[] newChildren)
	{
		int idx = m_children.indexOf(child);
		m_children.remove(idx);
		child.setParent(null);
		for(int i = 0; i < newChildren.length; i++)
		{
			m_children.add(idx + i, newChildren[i]);
			newChildren[i].setParent(this);
		}
		childNodeChanged(this); // this node was changed
	}

	/**
	 * Recursive disposal of children
	 * 
	 * @param children
	 */
	private void dispose(ITreeDataNode children[])
	{
		if(children == null || children.length < 1)
			return;
		for(int i = 0; i < children.length; i++)
			children[i].dispose();
	}
}
