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
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.ui.dependency.visualizer.resources.ImageCache;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.zest.core.viewers.EntityConnectionData;

/**
 * A simple {@link LabelProvider} that computes text and icon for a given {@link BOMNode}
 * 
 * @author Johannes Utzig
 * 
 */
public class DependencyLabelProvider extends LabelProvider
{

	@Override
	public Image getImage(Object element)
	{
		if(element instanceof BOMNode)
		{
			BOMNode node = (BOMNode)element;
			String componentTypeID = node.getRequest().getComponentTypeID();
			Resolution resolution = node.getResolution();
			if(resolution != null)
			{
				componentTypeID = resolution.getComponentTypeId();
			}
			if("osgi.bundle".equals(componentTypeID)) //$NON-NLS-1$
				return ImageCache.getImage("icons/osgi16x16.png"); //$NON-NLS-1$
			else if("maven".equals(componentTypeID)) //$NON-NLS-1$
				return ImageCache.getImage("icons/apache.png"); //$NON-NLS-1$

			else if("buckminster".equals(componentTypeID)) //$NON-NLS-1$
				return ImageCache.getImage("icons/component.png"); //$NON-NLS-1$
			else if("eclipse.feature".equals(componentTypeID)) //$NON-NLS-1$
				return ImageCache.getImage("icons/eclipse.gif"); //$NON-NLS-1$

		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element)
	{

		if(element instanceof BOMNode)
		{
			BOMNode node = (BOMNode)element;
			return node.getRequest().getName();

		}
		if(element instanceof EntityConnectionData)
		{

			return ""; //$NON-NLS-1$
		}
		return super.getText(element);
	}

}
