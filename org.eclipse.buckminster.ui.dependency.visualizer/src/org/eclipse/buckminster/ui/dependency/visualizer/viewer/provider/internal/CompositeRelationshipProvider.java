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
 * This relationship provider does no computing on its own but aggregates
 * multiple stragegies. All given {@link IRelationshipProvider}s are getting a
 * chance to compute interesting relationships and the CompositeRelationship
 * provider gathers all results in one map.
 * <p>
 * Node that it's possible that one provider overwrites the results of another,
 * so the order <b>does</b> matter.
 * 
 * @author Johannes Utzig
 * 
 */
public class CompositeRelationshipProvider implements IRelationshipProvider {

	private IRelationshipProvider[] providers;

	/**
	 * instanciate the provider with the given {@link IRelationshipProvider}s.
	 * <p>
	 * The results of the individual providers are gathered in a common map so
	 * one provider might overwrite the results of another provider, therefore
	 * the order of the providers matters.
	 * <p>
	 * Lowest priority should be at the beginning of the array, highest at the
	 * end.
	 * 
	 * @param providers
	 */
	public CompositeRelationshipProvider(IRelationshipProvider... providers) {
		super();
		this.providers = providers;
	}

	@Override
	public Map<EntityConnectionData, ConnectionCategory> getInterestingRelationships(BOMNode root, BOMNode currentSelection, Object[] connections) {
		Map<EntityConnectionData, ConnectionCategory> aggregatedData = new HashMap<EntityConnectionData, ConnectionCategory>();
		for (int i = 0; i < providers.length; i++) {
			aggregatedData.putAll(providers[i].getInterestingRelationships(root, currentSelection, connections));
		}
		return aggregatedData;
	}

}
