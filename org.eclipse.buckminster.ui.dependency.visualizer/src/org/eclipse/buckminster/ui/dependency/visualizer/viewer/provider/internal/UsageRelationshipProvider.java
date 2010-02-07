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
import java.util.Map;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.ui.dependency.visualizer.connections.ConnectionCategory;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.IRelationshipProvider;
import org.eclipse.zest.core.viewers.EntityConnectionData;

/**
 * The UsageRelationshipProvider gathers all connections to {@link BOMNode}s
 * that directly depend on the given {@link BOMNode}.
 * 
 * @author Johannes Utzig
 * 
 */
public class UsageRelationshipProvider implements IRelationshipProvider {

	public Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root, BOMNode currentSelection, Object[] connections) {
		HashMap<EntityConnectionData, ConnectionCategory> relationships = new HashMap<EntityConnectionData, ConnectionCategory>();
		for (int i = 0; i < connections.length; i++) {
			Object o = connections[i];
			if (o instanceof EntityConnectionData) {
				EntityConnectionData data = (EntityConnectionData) o;
				if (data.dest == currentSelection)
					relationships.put(data, ConnectionCategory.USAGE);

			}
		}
		return relationships;
	}

}
