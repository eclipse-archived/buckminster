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

	NotFilterImpl(boolean topLevel, FilterImpl value)
	{
		super(topLevel, FilterImpl.NOT, null);
		m_filter = value;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices)
	{
		m_filter.addConsultedAttributes(propertyChoices);
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties)
	{
		return !m_filter.match0(properties);
	}

	@Override
	FilterImpl stripFilter(Filter subFilter, boolean topLevel)
	{
		if(equals(subFilter))
			return null;

		FilterImpl newFilter = m_filter.stripFilter(subFilter, false);
		if(newFilter == m_filter)
			return this;

		if(newFilter == null)
			return null;

		return new NotFilterImpl(topLevel, newFilter);
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
