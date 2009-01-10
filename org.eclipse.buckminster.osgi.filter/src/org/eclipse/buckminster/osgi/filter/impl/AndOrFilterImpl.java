/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.Map;

class AndOrFilterImpl extends FilterImpl
{
	private final FilterImpl[] m_filters;

	AndOrFilterImpl(boolean topLevel, int operation, String attr, FilterImpl[] filters)
	{
		super(topLevel, operation, attr);
		m_filters = filters;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices)
	{
		for(int i = 0; i < m_filters.length; i++)
			m_filters[i].addConsultedAttributes(propertyChoices);
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties)
	{
		switch(getOp())
		{
		case AND:
			for(int i = 0, size = m_filters.length; i < size; i++)
				if(!m_filters[i].match0(properties))
					return false;
			return true;
		case OR:
			for(int i = 0, size = m_filters.length; i < size; i++)
				if(m_filters[i].match0(properties))
					return true;
			return false;
		}
		return false;
	}

	@Override
	void toString(StringBuilder sb)
	{
		sb.append('(');
		if(getOp() == AND)
			sb.append('&');
		else
			sb.append('|');

		for(int i = 0, size = m_filters.length; i < size; i++)
			sb.append(m_filters[i].toString());
		sb.append(')');
	}
}
