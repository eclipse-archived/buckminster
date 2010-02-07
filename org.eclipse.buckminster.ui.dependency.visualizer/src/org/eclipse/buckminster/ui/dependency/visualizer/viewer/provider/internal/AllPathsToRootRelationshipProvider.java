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
 * This relationship provider calculates the shortest paths from a given
 * {@link BOMNode} to all direct dependencies of the root request.
 * 
 * @author Johannes Utzig
 * 
 */
public class AllPathsToRootRelationshipProvider implements IRelationshipProvider {

	public Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root, BOMNode currentSelection, Object[] connections) {
		Map<EntityConnectionData, ConnectionCategory> relationships = new HashMap<EntityConnectionData, ConnectionCategory>();
		List<BOMNode> nodes = root.getChildren();
		for (BOMNode bomNode : nodes) {
			List<BOMNode> path = ShortesPathCalculation.calculatePath(bomNode, currentSelection);
			if (path.size() > 1) {
				EntityConnectionData data = new EntityConnectionData(root, bomNode);
				relationships.put(data, ConnectionCategory.PATH_TO_ROOT);
				for (int i = 0; i < path.size() - 1; i++) {
					data = new EntityConnectionData(path.get(i + 1), path.get(i));
					relationships.put(data, ConnectionCategory.PATH_TO_ROOT);
				}
			}
		}

		return relationships;
	}

}
