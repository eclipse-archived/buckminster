/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.osgi.filter.Filter;

class AndOrFilterImpl extends FilterImpl
{
	private final FilterImpl[] m_filters;

	AndOrFilterImpl(boolean topLevel, int operation, FilterImpl[] filters)
	{
		super(topLevel, operation, null);
		m_filters = filters;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices)
	{
		for(int i = 0; i < m_filters.length; i++)
			m_filters[i].addConsultedAttributes(propertyChoices);
	}

	public Filter[] getFilters()
	{
		return m_filters;
	}

	@Override
	FilterImpl addFilter(FilterImpl subFilter, int op)
	{
		FilterImpl result;
		if(getOp() == op)
		{
			int top = m_filters.length;

			// Prevent that the same filter is concatenated twice.
			for(int idx = 0; idx < top; ++idx)
				if(m_filters[idx].equals(subFilter))
					return this;

			FilterImpl[] newArray = new FilterImpl[top + 1];
			System.arraycopy(m_filters, 0, newArray, 0, top);
			newArray[top] = subFilter;
			result = new AndOrFilterImpl(true, op, newArray);
		}
		else
			result = super.addFilter(subFilter, op);
		return result;
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
	FilterImpl stripFilter(Filter subFilter, boolean topLevel)
	{
		ArrayList<FilterImpl> newList = new ArrayList<FilterImpl>(m_filters.length);
		boolean change = false;
		for(int idx = 0; idx < m_filters.length; ++idx)
		{
			FilterImpl child = m_filters[idx];
			if(child.equals(subFilter))
			{
				change = true;
				continue;
			}

			if(child instanceof AndOrFilterImpl)
			{
				FilterImpl newChild = ((AndOrFilterImpl)child).stripFilter(subFilter, false);
				if(child != newChild)
					change = true;
				if(newChild != null)
					newList.add(newChild);
			}
			else
				newList.add(child);
		}

		if(!change)
			return this;

		int newSize = newList.size();
		if(newSize == 0)
			return null;
		if(newSize == 1)
			return newList.get(0);
		return new AndOrFilterImpl(topLevel, getOp(), newList.toArray(new FilterImpl[newSize]));
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
