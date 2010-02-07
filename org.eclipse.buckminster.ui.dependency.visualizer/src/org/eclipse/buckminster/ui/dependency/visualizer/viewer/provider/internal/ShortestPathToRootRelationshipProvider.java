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
package org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.ui.dependency.visualizer.connections.ConnectionCategory;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.IRelationshipProvider;
import org.eclipse.zest.core.viewers.EntityConnectionData;

/**
 * The ShortestPathToRootRelationshipProvider returns the shortest path through
 * the graph from a given {@link BOMNode} to the root of the graph
 * 
 * @author Johannes Utzig
 * 
 */
public class ShortestPathToRootRelationshipProvider implements IRelationshipProvider {

	public Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root, BOMNode currentSelection, Object[] connections) {
		List<BOMNode> paths = ShortesPathCalculation.calculatePath(root, currentSelection);
		HashMap<EntityConnectionData, ConnectionCategory> relations = new HashMap<EntityConnectionData, ConnectionCategory>();
		for (int i = 0; i < paths.size() - 1; i++) {
			relations.put(new EntityConnectionData(paths.get(i + 1), paths.get(i)), ConnectionCategory.PATH_TO_ROOT);
		}
		return relations;
	}

}
