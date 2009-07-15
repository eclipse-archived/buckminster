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

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * 
 * @author Johannes Utzig
 * 
 */
public class DependencyTreeContentProvider implements ITreeContentProvider
{
	private TreeViewer treeViewer;

	public DependencyTreeContentProvider(TreeViewer treeViewer)
	{
		this.treeViewer = treeViewer;
	}

	public void dispose()
	{
		// nothing to dispose

	}

	public Object[] getChildren(Object parentElement)
	{

		if(parentElement instanceof BillOfMaterials)
		{
			BillOfMaterials bom = (BillOfMaterials)parentElement;
			return bom.getChildren().toArray();

		}
		else if(parentElement instanceof BOMNode)
		{
			BOMNode node = (BOMNode)parentElement;
			return node.getChildren().toArray();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement)
	{

		if(inputElement instanceof Collection)
		{
			Collection collection = (Collection)inputElement;
			return collection.toArray();

		}

		return new Object[] {};

	}

	public Object getParent(Object element)
	{
		// TODO not implemented yet
		return null;
	}

	public boolean hasChildren(Object element)
	{
		Object[] children = getChildren(element);
		if(children == null || children.length == 0)
			return false;
		ViewerFilter[] filters = treeViewer.getFilters();
		for(int i = 0; i < filters.length; i++)
		{
			children = filters[i].filter(treeViewer, element, children);
		}
		return children.length > 0;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		// nothing to do

	}

}
