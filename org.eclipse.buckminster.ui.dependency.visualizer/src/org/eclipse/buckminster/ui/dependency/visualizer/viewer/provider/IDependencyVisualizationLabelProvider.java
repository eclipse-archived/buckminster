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

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;
import org.eclipse.zest.core.viewers.IEntityStyleProvider;

/**
 * 
 * @author Johannes Utzig
 * 
 */
public interface IDependencyVisualizationLabelProvider extends ILabelProvider, IConnectionStyleProvider, IEntityStyleProvider {

	/**
	 * clear all cached data about the selection and the model
	 */
	public void clear();

	/**
	 * gets called on a selection change event.
	 * <p>
	 * The Label Provider is responsible for updating the visual representation
	 * of the underlaying {@link GraphViewer}
	 * 
	 * @param selection
	 */
	public void highlightSelection(BOMNode selection);

	/**
	 * 
	 * @param provider
	 *            - the {@link IRelationshipProvider} provider to use for
	 *            highlighting
	 */
	public void setRelationshipProvider(IRelationshipProvider provider);

	/**
	 * gets called when a new {@link BOMNode} should serve as the root request
	 * 
	 * @param node
	 */
	public void setRoot(BOMNode node);

}
