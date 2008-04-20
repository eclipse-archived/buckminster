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
 * @author Henrik Lindberg
 *
 */
public class BasicTreeDataNode extends AbstractTreeDataNode
{
	private final Object m_data;
	
	public BasicTreeDataNode(Object data)
	{
		m_data = data;
		if(data instanceof IPropertyChange)
			((IPropertyChange)data).addPropertyChangeListener(this);		
	}
	
	/**
	 * Returns the data object "toString()"
	 */
	@Override
	public String toString()
	{
		return getData().toString();
	}
	@Override
	public Object getData()
	{
		return m_data;
	}
	@Override
	public void dispose()
	{
		if(m_data instanceof IPropertyChange)
			((IPropertyChange)m_data).removePropertyChangeListener(this);		
		super.dispose();
	}
}
