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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class RegExFilter extends ViewerFilter {

	private Pattern pattern;

	private boolean invert;

	public RegExFilter(String regex, boolean invert) {
		this.invert = invert;
		try {
			pattern = Pattern.compile(regex);
		} catch (Exception e) {

		}

	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (pattern == null)
			return true;
		if (element instanceof BOMNode) {
			BOMNode node = (BOMNode) element;
			Matcher mat = pattern.matcher(node.getRequest().getName());
			if (invert)
				return !mat.find();
			return mat.find();

		}
		return true;
	}

}
