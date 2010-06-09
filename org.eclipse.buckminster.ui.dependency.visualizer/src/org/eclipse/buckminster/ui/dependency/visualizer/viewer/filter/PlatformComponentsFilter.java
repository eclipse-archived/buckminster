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
package org.eclipse.buckminster.ui.dependency.visualizer.viewer.filter;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This filter removes all components that have been resolved from the target
 * platform.
 * 
 * @see Provider#getReaderTypeId()
 * @author Johannes Utzig
 * 
 */
public class PlatformComponentsFilter extends ViewerFilter {
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof BOMNode) {
			BOMNode node = (BOMNode) element;
			Resolution resolution = node.getResolution();
			if (resolution == null)
				return true;
			return !"eclipse.platform".equals(resolution.getProvider().getReaderTypeId()); //$NON-NLS-1$

		}
		return true;
	}

}
