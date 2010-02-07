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

class AndOrFilterImpl extends FilterImpl {
	private final FilterImpl[] filters;

	AndOrFilterImpl(int operation, FilterImpl[] filters) {
		super(operation, null);
		this.filters = filters;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices) {
		for (int i = 0; i < filters.length; i++)
			filters[i].addConsultedAttributes(propertyChoices);
	}

	public int compareTo(FilterImpl o) {
		int cmp = internalCompareTo(o);
		if (cmp != 0)
			return cmp;

		FilterImpl[] o_filters = ((AndOrFilterImpl) o).filters;
		int top = filters.length;
		if (top > o_filters.length)
			return 1;

		if (top < o_filters.length)
			return -1;

		for (int idx = 0; idx < top; ++idx) {
			cmp = filters[idx].compareTo(o_filters[idx]);
			if (cmp != 0)
				return cmp;
		}
		return 0;
	}

	@Override
	public FilterImpl stripFilter(Filter subFilter) {
		ArrayList<FilterImpl> newList = new ArrayList<FilterImpl>(filters.length);
		boolean change = false;
		for (int idx = 0; idx < filters.length; ++idx) {
			FilterImpl child = filters[idx];
			if (child.equals(subFilter)) {
				change = true;
				continue;
			}

			if (child instanceof AndOrFilterImpl) {
				FilterImpl newChild = ((AndOrFilterImpl) child).stripFilter(subFilter);
				if (child != newChild)
					change = true;
				if (newChild != null)
					newList.add(newChild);
			} else
				newList.add(child);
		}
		return change ? Parser.normalize(newList, getOp()) : this;
	}

	@Override
	FilterImpl addFilter(FilterImpl subFilter, int op) {
		FilterImpl result;
		if (getOp() == op) {
			int top = filters.length;

			// Prevent that the same filter is concatenated twice.
			for (int idx = 0; idx < top; ++idx)
				if (filters[idx].equals(subFilter))
					return this;

			ArrayList<FilterImpl> filterList = new ArrayList<FilterImpl>(top + 1);
			for (int idx = 0; idx < top; ++idx)
				filterList.add(filters[idx]);
			filterList.add(subFilter);
			result = Parser.normalize(filterList, getOp());
		} else
			result = super.addFilter(subFilter, op);
		return result;
	}

	@Override
	FilterImpl[] getFilterImpls() {
		return filters;
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties) {
		switch (getOp()) {
			case AND:
				for (int i = 0, size = filters.length; i < size; i++)
					if (!filters[i].match0(properties))
						return false;
				return true;
			case OR:
				for (int i = 0, size = filters.length; i < size; i++)
					if (filters[i].match0(properties))
						return true;
				return false;
		}
		return false;
	}

	@Override
	void toString(StringBuilder sb) {
		sb.append('(');
		if (getOp() == AND)
			sb.append('&');
		else
			sb.append('|');

		for (int i = 0, size = filters.length; i < size; i++)
			sb.append(filters[i].toString());
		sb.append(')');
	}
}
