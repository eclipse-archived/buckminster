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

import org.eclipse.buckminster.generic.model.IPropertyChange;

/**
 * A Basic Parent Tree Data Node - it passivly refers to some data, and passivly holds
 * children. An instance of this class is usful for static "folder nodes" in a tree.
 * 
 * @author Henrik Lindberg
 *
 */
public class BasicTreeParentDataNode extends AbstractTreeParentDataNode
{
	private final Object m_data;
	public BasicTreeParentDataNode(Object data)
	{
		m_data = data;
		if(data instanceof IPropertyChange)
			((IPropertyChange)data).addPropertyChangeListener(this);
	}
	@Override
	public String toString()
	{
		return getData().toString();
	}

	@Override
	public Object getData()
	{
		return m_data;
	}

	/** 
	 * Disposes all children
	 */
	@Override
	public void dispose()
	{
		if(m_data instanceof IPropertyChange)
			((IPropertyChange)m_data).removePropertyChangeListener(this);
		// dispose all recursively
		dispose(getChildren());
	}
	/**
	 * Recursive disposal of children
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
