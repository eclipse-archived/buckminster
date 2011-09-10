/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.osgi.framework.ServiceReference;

abstract class FilterImpl implements Filter, Comparable<FilterImpl> {
	/**
	 * A Map that performs case insensitive String lookups on the Map that it
	 * contains.
	 */
	private static class CaseInsensitiveMap<V> extends AbstractMap<String, V> {
		private final Map<String, String> lowerCaseMap;

		private final Map<String, ? extends V> map;

		CaseInsensitiveMap(Map<String, ? extends V> map) {
			int top = map.size();
			Map<String, String> lcMap = null;
			for (String key : map.keySet()) {
				String lowKey = key.toLowerCase(Locale.ENGLISH);
				if (key != lowKey) {
					if (lcMap == null)
						lcMap = new HashMap<String, String>(top);
					if (lcMap.put(lowKey, key) != null || map.containsKey(lowKey))
						throw new IllegalArgumentException("case variants of key: " + lowKey); //$NON-NLS-1$
				}
			}
			this.lowerCaseMap = lcMap;
			this.map = map;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<Map.Entry<String, V>> entrySet() {
			return ((Map<String, V>) map).entrySet();
		}

		@Override
		public V get(Object key) {
			String stringKey = ((String) key).toLowerCase(Locale.ENGLISH);
			if (lowerCaseMap != null) {
				String realKey = lowerCaseMap.get(stringKey);
				if (realKey != null)
					stringKey = realKey;
			}
			return map.get(stringKey);
		}
	}

	/**
	 * This Map is used for key lookup from {@link Dictionary} instances that do
	 * not implement the {@link Map} interface (most implementations do since
	 * they extend the {@link HashMap}). The implementation only supports the
	 * get operation using a String key as no other operations are used by the
	 * Filter implementation.
	 */
	@SuppressWarnings("serial")
	private static class DictionaryMap extends HashMap<String, Object> {
		private final boolean caseSensitive;

		DictionaryMap(Dictionary<String, ? extends Object> dictionary, boolean caseSensitive) {
			super(dictionary.size());
			Enumeration<String> keys = dictionary.keys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				Object value = dictionary.get(key);
				if (!caseSensitive) {
					String lowKey = key.toLowerCase(Locale.ENGLISH);
					if (containsKey(lowKey))
						throw new IllegalArgumentException("case variants of key: " + lowKey); //$NON-NLS-1$
					key = lowKey;
				}
				put(key, value);
			}
			this.caseSensitive = caseSensitive;
		}

		@Override
		public Set<Map.Entry<String, Object>> entrySet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object get(Object key) {
			return caseSensitive ? super.get(key) : super.get(((String) key).toLowerCase(Locale.ENGLISH));
		}
	}

	/**
	 * This Map is used for key lookup from a ServiceReference during filter
	 * evaluation. The Map implementation only supports the get operation using
	 * a String key as no other operations are used by the Filter
	 * implementation.
	 */
	private static class ServiceReferenceMap extends AbstractMap<String, Object> {
		private final ServiceReference<?> reference;

		ServiceReferenceMap(ServiceReference<?> reference) {
			this.reference = reference;
		}

		@Override
		public Set<Map.Entry<String, Object>> entrySet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Object get(Object key) {
			return reference.getProperty((String) key);
		}
	}

	static final int LESS = 4;

	static final int PRESENT = 5;

	static final int SUBSTRING = 6;

	static final int AND = 7;

	static final int OR = 8;

	static final int NOT = 9;

	/**
	 * Map a string for an APPROX (~=) comparison.
	 * 
	 * This implementation removes white spaces. This is the minimum
	 * implementation allowed by the OSGi spec.
	 * 
	 * @param input
	 *            Input string.
	 * @return String ready for APPROX comparison.
	 */
	static String approxString(String input) {
		boolean changed = false;
		char[] output = input.toCharArray();
		int cursor = 0;
		for (int i = 0, length = output.length; i < length; i++) {
			char c = output[i];

			if (Character.isWhitespace(c)) {
				changed = true;
				continue;
			}

			output[cursor] = c;
			cursor++;
		}

		return changed ? new String(output, 0, cursor) : input;
	}

	/**
	 * Encode the value string such that '(', '*', ')' and '\' are escaped.
	 * 
	 * @param value
	 *            unencoded value string.
	 * @return encoded value string.
	 */
	static String encodeValue(String value) {
		int inlen = value.length();
		char[] output = null;
		int cursor = 0;
		for (int i = 0; i < inlen; i++) {
			char c = value.charAt(i);
			switch (c) {
				case '(':
				case '*':
				case ')':
				case '\\':
					if (output == null) {
						output = new char[inlen << 2];
						if (i > 0) {
							value.getChars(0, i, output, 0);
							cursor = i;
						}
					}
					output[cursor++] = '\\';
					break;
			}
			if (output != null)
				output[cursor++] = c;
		}
		return output == null ? value : new String(output, 0, cursor);
	}

	private final int op;

	static final int EQUAL = 1;

	static final int APPROX = 2;

	static final int GREATER = 3;

	/** filter attribute or null if operation AND, OR or NOT */
	private final String attr;

	FilterImpl(int operation, String attr) {
		this.op = operation;
		this.attr = attr;
	}

	@Override
	public void addConsultedAttributes(Map<String, String[]> propertyChoices) {
		String stringValue = getValueAsString();

		// Add the attribute value as a valid choice for the attribute
		// unless it's already present.
		//
		synchronized (propertyChoices) {
			String[] choices = propertyChoices.get(getAttr());
			if (choices == null) {
				propertyChoices.put(getAttr(), new String[] { stringValue });
				return;
			}

			int top = choices.length;
			int idx = top;
			while (--idx >= 0)
				if (stringValue.equals(choices[idx]))
					return;

			String[] newChoices = new String[top + 1];
			System.arraycopy(choices, 0, newChoices, 0, top);
			newChoices[top] = stringValue;
			propertyChoices.put(getAttr(), newChoices);
		}
	}

	@Override
	public Filter addFilterWithAnd(Filter subFilter) {
		return subFilter == null ? this : addFilter((FilterImpl) subFilter, AND);
	}

	@Override
	public Filter addFilterWithOr(Filter subFilter) {
		return subFilter == null ? this : addFilter((FilterImpl) subFilter, OR);
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || (obj instanceof FilterImpl && compareTo((FilterImpl) obj) == 0)
				|| (obj instanceof Filter && toString().equals(obj.toString()));
	}

	public int getOp() {
		return op;
	}

	@Override
	public int hashCode() {
		return 11 * toString().hashCode();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean match(Dictionary properties) {
		return match(properties, false);
	}

	@Override
	public boolean match(ServiceReference<?> reference) {
		return match0(reference == null ? Collections.<String, Object> emptyMap() : new ServiceReferenceMap(reference));
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean matchCase(Dictionary dictionary) {
		return match(dictionary, true);
	}

	@Override
	public boolean matchCase(Map<String, ? extends Object> properties) {
		return match0(properties == null ? Collections.<String, Object> emptyMap() : properties);
	}

	@Override
	public boolean matches(Map<String, ? extends Object> properties) {
		return match0(properties == null ? Collections.<String, Object> emptyMap() : new CaseInsensitiveMap<Object>(properties));
	}

	@Override
	public Filter stripFilter(Filter subFilter) {
		return equals(subFilter) ? null : this;
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	FilterImpl addFilter(FilterImpl subFilter, int operator) {
		int cmp = compareTo(subFilter);
		if (cmp == 0)
			return this;

		ArrayList<FilterImpl> filters = new ArrayList<FilterImpl>(2);
		filters.add(this);
		filters.add(subFilter);
		return Parser.normalize(filters, operator);
	}

	final boolean compare(Object value1) {
		if (value1 == null)
			return false;

		if (value1 instanceof Collection<?>) {
			for (Iterator<?> iterator = ((Collection<?>) value1).iterator(); iterator.hasNext();)
				if (compare(iterator.next()))
					return true;
			return false;
		}

		if (value1 instanceof Object[]) {
			Object[] array = (Object[]) value1;
			for (int i = 0, size = array.length; i < size; i++)
				if (compare(array[i]))
					return true;
			return false;
		}
		return internalCompare(value1);
	}

	String getAttr() {
		return attr;
	}

	FilterImpl[] getFilterImpls() {
		return new FilterImpl[] { this };
	}

	String getValueAsString() {
		return null;
	}

	boolean internalCompare(Object value) {
		return false;
	}

	int internalCompareTo(FilterImpl o) {
		int cmp = op > o.op ? 1 : (op < o.op ? -1 : 0);
		if (cmp == 0) {
			if (attr == null) {
				if (o.attr != null)
					cmp = -1;
			} else {
				cmp = o.attr == null ? 1 : attr.compareTo(o.attr);
			}
		}
		return cmp;
	}

	abstract boolean match0(Map<String, ? extends Object> properties);

	abstract void toString(StringBuilder bld);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean match(Dictionary dictionary, boolean caseSensitive) {
		Map props = null;
		if (dictionary instanceof Map) {
			props = (Map) dictionary;
			if (!caseSensitive)
				props = new CaseInsensitiveMap(props);
		} else if (dictionary == null)
			props = Collections.emptyMap();
		else
			props = new DictionaryMap(dictionary, caseSensitive);
		return match0(props);
	}
}
