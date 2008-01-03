/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.runtime.Trivial;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class ValueHolderFilter extends ValueHolder
{
	private ArrayList<ValueHolder> m_valueHolders;

	/**
	 * Add a value holder that will become part of the source
	 * to this filter.
	 * @param valueHolder The holder to add.
	 */
	public void addValueHolder(ValueHolder valueHolder)
	{
		if(m_valueHolders == null)
			m_valueHolders = new ArrayList<ValueHolder>();
		m_valueHolders.add(valueHolder);
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && Trivial.equalsAllowNull(m_valueHolders, ((ValueHolderFilter)o).m_valueHolders);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (m_valueHolders == null ? 0 : m_valueHolders.hashCode());
		return hc;
	}

	String checkedGetSourceValue(Map<String, String> properties, int recursionGuard)
	{
		int top = (m_valueHolders == null) ? 0 : m_valueHolders.size();

		if(top == 0)
			return NO_VALUE;
		else if(top == 1)
			return m_valueHolders.get(0).checkedGetValue(properties, recursionGuard);
		
		StringBuilder bld = new StringBuilder();
		for(int idx = 0; idx < top; ++idx)
		{
			ValueHolder valueHolder = m_valueHolders.get(idx);
			if(valueHolder.isMultiValueProducer())
			{
				String[] vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for(int vidx = 0; vidx < vs.length; ++vidx)
					bld.append(vs[vidx]);
			}
			else
				bld.append(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return bld.toString();
	}

	protected String[] checkedGetSourceValues(Map<String, String> properties, int recursionGuard)
	{
		int top = (m_valueHolders == null) ? 0 : m_valueHolders.size();

		if(top == 0)
			return Trivial.EMPTY_STRING_ARRAY;
		else if(top == 1)
			return m_valueHolders.get(0).checkedGetValues(properties, recursionGuard);

		ArrayList<String> parameters = new ArrayList<String>(top);
		for(int idx = 0; idx < top; ++idx)
		{
			ValueHolder valueHolder = m_valueHolders.get(idx);
			if(valueHolder.isMultiValueProducer())
			{
				String[] vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for(int vidx = 0; vidx < vs.length; ++vidx)
					parameters.add(vs[vidx]);
			}
			else
				parameters.add(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return parameters.toArray(new String[parameters.size()]);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix)
	throws SAXException
	{
		if(m_valueHolders != null)
		{
			for(ValueHolder vh : m_valueHolders)
				vh.toSax(handler, namespace, prefix, vh.getDefaultTag());
		}
	}

	@Override
	protected String getElementNamespace(String namespace)
	{
		return XMLConstants.BM_COMMON_NS;
	}

	@Override
	protected String getElementPrefix(String prefix)
	{
		return XMLConstants.BM_COMMON_PREFIX;
	}
}

