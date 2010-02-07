/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

/**
 * retrieves all components of a given {@link BOMNode}
 * 
 * @author Johannes Utzig
 * 
 */
public class DependencyContentProvider implements IGraphEntityContentProvider {

	public void dispose() {
		// nothing to dispose

	}

	public Object[] getConnectedTo(Object entity) {
		if (entity instanceof BOMNode) {
			BOMNode node = (BOMNode) entity;
			return node.getChildren().toArray();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof List<?>) {
			List<BOMNode> input = (List<BOMNode>) inputElement;
			Set<BOMNode> nodes = new HashSet<BOMNode>();

			for (BOMNode bomNode : input) {
				nodes.add(bomNode);
				getElementsRecursivly(bomNode, nodes);
			}
			return nodes.toArray();
		}
		return null;

	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// nothing to do

	}

	private Set<BOMNode> getElementsRecursivly(BOMNode node, Set<BOMNode> allElements) {
		Collection<BOMNode> children = node.getChildren();
		for (BOMNode bomNode : children) {
			allElements.add(bomNode);
			getElementsRecursivly(bomNode, allElements);

		}
		allElements.addAll(children);
		return allElements;
	}

}
