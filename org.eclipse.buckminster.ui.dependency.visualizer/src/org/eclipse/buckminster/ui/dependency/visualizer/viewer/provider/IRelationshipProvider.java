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

import java.util.Map;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.ui.dependency.visualizer.connections.ConnectionCategory;
import org.eclipse.zest.core.viewers.EntityConnectionData;

/**
 * IRelationshipProviders are used to compute all interesting relationships for
 * a given {@link BOMNode}
 * 
 * @author Johannes Utzig
 * 
 */
public interface IRelationshipProvider {

	/**
	 * 
	 * @param root
	 *            - the root request
	 * @param currentSelection
	 *            - the selected node
	 * @param connections
	 *            - all connections in the graph
	 * @return a map containing the interesting Entity Connections and a
	 *         {@link ConnectionCategory} for each connection
	 */
	Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root, BOMNode currentSelection, Object[] connections);

}
