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

import java.util.EventObject;

/**
 * Event object describing the changed node in a Tree Data tree.
 * @author Henrik Lindberg
 *
 */
public class TreeDataEvent extends EventObject
{

	private static final long serialVersionUID = -7493609364097227394L;
	private ITreeDataNode m_node;
	public enum Type {
		CHANGE,
		;
	};
	private Type m_type;
	
	public TreeDataEvent(ITreeRootNode treeSource, ITreeDataNode node, Type type)
	{
		super(treeSource);
		m_node = node;
		m_type = type;
	}
	public Type getType()
	{
		return m_type;
	}
	
	public ITreeDataNode getNode()
	{
		return m_node;
	}
	public static TreeDataEvent changed(ITreeRootNode source, ITreeDataNode node)
	{
		return new TreeDataEvent(source, node, Type.CHANGE);
	}
}
