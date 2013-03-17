/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.Map;

import org.eclipse.buckminster.osgi.filter.Filter;

class NotFilterImpl extends FilterImpl {
	private final FilterImpl filter;

	NotFilterImpl(FilterImpl value) {
		super(FilterImpl.NOT, null);
		filter = value;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices) {
		filter.addConsultedAttributes(propertyChoices);
	}

	@Override
	public int compareTo(FilterImpl that) {
		int cmp = internalCompareTo(that);
		if (cmp == 0)
			cmp = filter.compareTo(((NotFilterImpl) that).filter);
		return cmp;
	}

	@Override
	public FilterImpl stripFilter(Filter subFilter) {
		if (equals(subFilter))
			return null;

		FilterImpl newFilter = (FilterImpl) filter.stripFilter(subFilter);
		if (newFilter == filter)
			return this;

		if (newFilter == null)
			return null;

		return new NotFilterImpl(newFilter);
	}

	FilterImpl getFilter() {
		return filter;
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties) {
		return !filter.match0(properties);
	}

	@Override
	void toString(StringBuilder sb) {
		sb.append('(');
		sb.append('!');
		filter.toString(sb);
		sb.append(')');
	}
}
