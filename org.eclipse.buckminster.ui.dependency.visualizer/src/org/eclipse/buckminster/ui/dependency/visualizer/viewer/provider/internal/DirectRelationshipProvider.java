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
 * The DirectRelationshipProvider gathers all connections to direct dependencies of a {@link BOMNode}
 * 
 * @author Johannes Utzig
 * 
 */
public class DirectRelationshipProvider implements IRelationshipProvider
{

	public Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root,
			BOMNode currentSelection, Object[] allConnections)
	{
		List<BOMNode> nodes = currentSelection.getChildren();
		Map<EntityConnectionData, ConnectionCategory> connections = new HashMap<EntityConnectionData, ConnectionCategory>();
		for(BOMNode bomNode : nodes)
		{
			connections.put(new EntityConnectionData(currentSelection, bomNode), ConnectionCategory.DEPENDENCY);
		}
		return connections;
	}

}
