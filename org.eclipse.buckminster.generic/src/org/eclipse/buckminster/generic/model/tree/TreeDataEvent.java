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
 * 
 * @author Henrik Lindberg
 * 
 */
public class TreeDataEvent extends EventObject {

	public enum Type {
		CHANGE, ;
	}

	private static final long serialVersionUID = -7493609364097227394L;

	public static TreeDataEvent changed(ITreeRootNode source, ITreeDataNode node) {
		return new TreeDataEvent(source, node, Type.CHANGE);
	};

	private final ITreeDataNode node;

	private final Type type;

	public TreeDataEvent(ITreeRootNode treeSource, ITreeDataNode node, Type type) {
		super(treeSource);
		this.node = node;
		this.type = type;
	}

	public ITreeDataNode getNode() {
		return node;
	}

	public Type getType() {
		return type;
	}
}
