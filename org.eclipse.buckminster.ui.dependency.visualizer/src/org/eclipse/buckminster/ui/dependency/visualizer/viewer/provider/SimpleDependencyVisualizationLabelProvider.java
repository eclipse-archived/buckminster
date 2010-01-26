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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.ui.dependency.visualizer.connections.ConnectionCategory;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.figures.BOMNodeTooltipFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.EntityConnectionData;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.ZestStyles;

/**
 * 
 * a simple implementation for a {@link IDependencyVisualizationLabelProvider}.
 * 
 * 
 * @author Johannes Utzig
 * 
 */
public class SimpleDependencyVisualizationLabelProvider implements IDependencyVisualizationLabelProvider
{

	private BOMNode root;

	private BOMNode selection;

	private ILabelProvider baseLabelProvider;

	private IRelationshipProvider relationshipProvider;

	private GraphViewer graphViewer;

	private Map<BOMNode, ConnectionCategory> interestingNodes = new HashMap<BOMNode, ConnectionCategory>();

	private Map<EntityConnectionData, ConnectionCategory> interestingConnections = new HashMap<EntityConnectionData, ConnectionCategory>();

	public Color BLACK = new Color(Display.getDefault(), 0, 0, 0);

	public Color WHITE = new Color(Display.getDefault(), 255, 255, 255);

	public Color USAGE_COLOR = new Color(Display.getDefault(), 100, 100, 250);

	public Color DEPENDENCY_COLOR = new Color(Display.getDefault(), 255, 196, 0);

	public Color PATH_TO_ROOT_COLOR = new Color(Display.getDefault(), 0, 127, 0);

	public Color SELECTION_COLOR = new Color(Display.getDefault(), 255, 255, 0);

	public Color DEFAULT_CONNECTION_COLOR = new Color(Display.getDefault(), 220, 220, 220);

	public Color HIGHLIGHT_CONNECTION_COLOR = new Color(Display.getDefault(), 255, 0, 0);

	public Color UNRESOLVED_NODE_COLOR = new Color(Display.getDefault(), 255, 0, 0);

	public Color ROOT_REQUEST_COLOR = new Color(Display.getDefault(), 180, 255, 180);

	public Color DEFAULT_NODE_COLOR = new Color(Display.getDefault(), 216, 228, 248);

	public Color DEFAULT_BORDER_COLOR = new Color(Display.getDefault(), 0, 255, 0);

	public Color DEFAULT_BORDER_HIGHLIGHT_COLOR = new Color(Display.getDefault(), 255, 255, 0);

	public SimpleDependencyVisualizationLabelProvider(ILabelProvider baseLabelProvider, GraphViewer viewer)
	{
		this.baseLabelProvider = baseLabelProvider;
		this.graphViewer = viewer;
	}

	public void addListener(ILabelProviderListener listener)
	{
		// TODO Auto-generated method stub

	}

	public void clear()
	{
		unreveal(selection);
		interestingConnections.clear();
		interestingNodes.clear();
		root = null;
		selection = null;
	}

	public void dispose()
	{
		BLACK.dispose();
		WHITE.dispose();
		DEFAULT_BORDER_COLOR.dispose();
		DEFAULT_BORDER_HIGHLIGHT_COLOR.dispose();
		DEFAULT_CONNECTION_COLOR.dispose();
		DEFAULT_NODE_COLOR.dispose();
		DEPENDENCY_COLOR.dispose();
		HIGHLIGHT_CONNECTION_COLOR.dispose();
		PATH_TO_ROOT_COLOR.dispose();
		SELECTION_COLOR.dispose();
		UNRESOLVED_NODE_COLOR.dispose();
		USAGE_COLOR.dispose();

	}

	public boolean fisheyeNode(Object entity)
	{
		if(root == entity)
			return true;
		if(selection == entity)
			return true;
		return false;
	}

	public Color getBackgroundColour(Object entity)
	{
		if(entity instanceof BOMNode)
		{
			BOMNode node = (BOMNode)entity;
			if(node.getResolution() == null)
			{
				return UNRESOLVED_NODE_COLOR;
			}
		}

		if(entity == root)
		{
			return ROOT_REQUEST_COLOR;
		}
		if(entity == selection)
		{
			return SELECTION_COLOR;
		}

		return DEFAULT_NODE_COLOR;
	}

	public Color getBorderColor(Object entity)
	{
		return DEFAULT_BORDER_COLOR;
	}

	public Color getBorderHighlightColor(Object entity)
	{
		return DEFAULT_BORDER_HIGHLIGHT_COLOR;
	}

	public int getBorderWidth(Object entity)
	{
		return 0;
	}

	public Color getColor(Object rel)
	{
		ConnectionCategory type = interestingConnections.get(rel);
		if(type == null)
			return DEFAULT_CONNECTION_COLOR;
		switch(type)
		{
		case DEPENDENCY:
			return DEPENDENCY_COLOR;
		case USAGE:
			return USAGE_COLOR;
		case PATH_TO_ROOT:
			return PATH_TO_ROOT_COLOR;
		}
		return DEFAULT_CONNECTION_COLOR;
	}

	public int getConnectionStyle(Object rel)
	{
		if(interestingConnections.containsKey(rel))
		{
			return ZestStyles.CONNECTIONS_DASH | ZestStyles.CONNECTIONS_DIRECTED;
		}
		return ZestStyles.CONNECTIONS_DIRECTED;
	}

	public Color getForegroundColour(Object entity)
	{
		ConnectionCategory category = interestingNodes.get(entity);
		if(category == null || entity == selection || entity == root)
			return BLACK;
		switch(category)
		{
		case PATH_TO_ROOT:
			return WHITE;
		default:
			return BLACK;
		}

	}

	public Color getHighlightColor(Object rel)
	{
		return HIGHLIGHT_CONNECTION_COLOR;
	}

	public Image getImage(Object element)
	{
		return baseLabelProvider.getImage(element);
	}

	public int getLineWidth(Object rel)
	{
		if(interestingConnections.containsKey(rel))
		{
			return 2;
		}
		return -1;
	}

	public Color getNodeHighlightColor(Object entity)
	{
		if(entity == root)
			return ROOT_REQUEST_COLOR;
		if(entity == selection)
			return SELECTION_COLOR;
		ConnectionCategory category = interestingNodes.get(entity);
		if(category == null)
			return DEFAULT_NODE_COLOR;
		switch(category)
		{
		case DEPENDENCY:
			return DEPENDENCY_COLOR;
		case USAGE:
			return USAGE_COLOR;
		case PATH_TO_ROOT:
			return PATH_TO_ROOT_COLOR;
		default:
			return DEFAULT_NODE_COLOR;
		}

	}

	public String getText(Object element)
	{
		return baseLabelProvider.getText(element);
	}

	public IFigure getTooltip(Object entity)
	{
		if(entity instanceof BOMNode)
		{
			BOMNode node = (BOMNode)entity;
			return new BOMNodeTooltipFigure(node);
		}
		return null;
	}

	public void highlightSelection(BOMNode nodeSelection)
	{
		if(nodeSelection == selection)
			return;
		highlightSelectionInternal(nodeSelection);

	}

	public boolean isLabelProperty(Object element, String property)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener)
	{
		// TODO Auto-generated method stub

	}

	public void setRelationshipProvider(IRelationshipProvider provider)
	{
		this.relationshipProvider = provider;
		highlightSelectionInternal(selection);
	}

	public void setRoot(BOMNode node)
	{
		BOMNode previousRoot = root;
		this.root = node;

		// make sure to update what used to be root to get rid of any highlighing
		if(previousRoot != null)
		{
			graphViewer.unReveal(previousRoot);
			graphViewer.update(previousRoot, null);
		}
		if(root != null)
		{
			graphViewer.reveal(root);
			graphViewer.update(root, null);
		}
	}

	private void highlightSelectionInternal(BOMNode nodeSelection)
	{
		BOMNode previousSelection = selection;
		selection = nodeSelection;
		unreveal(previousSelection);

		if(nodeSelection == null)
			return;
		Object[] connections = graphViewer.getConnectionElements();
		interestingConnections = new HashMap<EntityConnectionData, ConnectionCategory>(
				relationshipProvider.getInterestingRelationships(root, nodeSelection, connections));
		interestingNodes.clear();
		for(Entry<EntityConnectionData, ConnectionCategory> entry : interestingConnections.entrySet())
		{
			EntityConnectionData data = entry.getKey();
			BOMNode dest = (BOMNode)data.dest;
			BOMNode source = (BOMNode)data.source;
			interestingNodes.put(dest, entry.getValue());
			interestingNodes.put(source, entry.getValue());
			graphViewer.reveal(dest);
			graphViewer.reveal(source);

		}
		List<Object> objectsToUpdate = new ArrayList<Object>(interestingConnections.size() + interestingNodes.size());
		objectsToUpdate.addAll(interestingConnections.keySet());
		objectsToUpdate.addAll(interestingNodes.keySet());
		objectsToUpdate.add(root);
		objectsToUpdate.add(nodeSelection);
		graphViewer.update(objectsToUpdate.toArray(), null);

	}

	private void unreveal(Object previousSelection)
	{
		Iterator<BOMNode> nodeIterator = interestingNodes.keySet().iterator();
		while(nodeIterator.hasNext())
		{
			BOMNode node = nodeIterator.next();
			nodeIterator.remove();
			graphViewer.unReveal(node);
			graphViewer.update(node, null);
		}
		if(root != null)
		{
			graphViewer.update(root, null);
		}
		if(previousSelection != null)
		{
			graphViewer.unReveal(previousSelection);
			graphViewer.update(previousSelection, null);
		}

		Iterator<EntityConnectionData> connectionIterator = interestingConnections.keySet().iterator();
		while(connectionIterator.hasNext())
		{
			EntityConnectionData entityConnectionData = connectionIterator.next();
			connectionIterator.remove();
			graphViewer.unReveal(entityConnectionData);
			graphViewer.update(entityConnectionData, null);

		}

	}

}
