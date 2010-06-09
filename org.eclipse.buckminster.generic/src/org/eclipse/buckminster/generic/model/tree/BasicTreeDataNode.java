/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.buckminster.generic.model.tree;

import org.eclipse.buckminster.generic.model.IPropertyChange;

/**
 * A Basic Tree Data Node that passively refers to a data object.
 * 
 * @author Henrik Lindberg
 * 
 */
public class BasicTreeDataNode extends AbstractTreeDataNode {
	private final Object data;

	public BasicTreeDataNode(Object data) {
		this.data = data;
		if (data instanceof IPropertyChange)
			((IPropertyChange) data).addPropertyChangeListener(this);
	}

	@Override
	public void dispose() {
		if (data instanceof IPropertyChange)
			((IPropertyChange) data).removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public Object getData() {
		return data;
	}

	/**
	 * Returns the data object "toString()"
	 */
	@Override
	public String toString() {
		return getData().toString();
	}
}
