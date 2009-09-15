/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.Map;

import org.eclipse.buckminster.osgi.filter.Filter;

class NotFilterImpl extends FilterImpl
{
	private final FilterImpl m_filter;

	NotFilterImpl(FilterImpl value)
	{
		super(FilterImpl.NOT, null);
		m_filter = value;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices)
	{
		m_filter.addConsultedAttributes(propertyChoices);
	}

	public int compareTo(FilterImpl filter)
	{
		int cmp = internalCompareTo(filter);
		if(cmp == 0)
			cmp = m_filter.compareTo(((NotFilterImpl)filter).m_filter);
		return cmp;
	}

	@Override
	public FilterImpl stripFilter(Filter subFilter)
	{
		if(equals(subFilter))
			return null;

		FilterImpl newFilter = (FilterImpl)m_filter.stripFilter(subFilter);
		if(newFilter == m_filter)
			return this;

		if(newFilter == null)
			return null;

		return new NotFilterImpl(newFilter);
	}

	FilterImpl getFilter()
	{
		return m_filter;
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties)
	{
		return !m_filter.match0(properties);
	}

	@Override
	void toString(StringBuilder sb)
	{
		sb.append('(');
		sb.append('!');
		m_filter.toString(sb);
		sb.append(')');
	}
}
