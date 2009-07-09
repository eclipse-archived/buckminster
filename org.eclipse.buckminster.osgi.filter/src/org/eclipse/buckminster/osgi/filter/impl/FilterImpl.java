/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.osgi.framework.ServiceReference;

abstract class FilterImpl implements Filter
{
	/**
	 * A Map that performs case insensitive String lookups on the Map that it contains.
	 */
	private static class CaseInsensitiveMap<V> extends AbstractMap<String, V>
	{
		private final Map<String, String> m_ciMap;

		private final Map<String, ? extends V> m_map;

		CaseInsensitiveMap(Map<String, ? extends V> map)
		{
			int top = map.size();
			Map<String, String> ciMap = null;
			for(String key : map.keySet())
			{
				String lowKey = key.toLowerCase();
				if(key != lowKey)
				{
					if(ciMap == null)
						ciMap = new HashMap<String, String>(top);
					if(ciMap.put(lowKey, key) != null || map.containsKey(lowKey))
						throw new IllegalArgumentException("case variants of key: " + lowKey); //$NON-NLS-1$
				}
			}
			m_ciMap = ciMap;
			m_map = map;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<Map.Entry<String, V>> entrySet()
		{
			return ((Map<String, V>)m_map).entrySet();
		}

		@Override
		public V get(Object key)
		{
			String stringKey = ((String)key).toLowerCase();
			if(m_ciMap != null)
			{
				String realKey = m_ciMap.get(stringKey);
				if(realKey != null)
					stringKey = realKey;
			}
			return m_map.get(stringKey);
		}
	}

	/**
	 * This Map is used for key lookup from {@link Dictionary} instances that do not implement the {@link Map} interface
	 * (most implementations do since they extend the {@link HashMap}). The implementation only supports the get
	 * operation using a String key as no other operations are used by the Filter implementation.
	 */
	@SuppressWarnings("serial")
	private static class DictionaryMap extends HashMap<String, Object>
	{
		private final boolean m_caseSensitive;

		DictionaryMap(Dictionary<String, ? extends Object> dictionary, boolean caseSensitive)
		{
			super(dictionary.size());
			Enumeration<String> keys = dictionary.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				Object value = dictionary.get(key);
				if(!caseSensitive)
				{
					String lowKey = key.toLowerCase();
					if(containsKey(lowKey))
						throw new IllegalArgumentException("case variants of key: " + lowKey); //$NON-NLS-1$
					key = lowKey;
				}
				put(key, value);
			}
			m_caseSensitive = caseSensitive;
		}

		@Override
		public Set<Map.Entry<String, Object>> entrySet()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Object get(Object key)
		{
			return m_caseSensitive
					? super.get(key)
					: super.get(((String)key).toLowerCase());
		}
	}

	/**
	 * This Map is used for key lookup from a ServiceReference during filter evaluation. The Map implementation only
	 * supports the get operation using a String key as no other operations are used by the Filter implementation.
	 */
	private static class ServiceReferenceMap extends AbstractMap<String, Object>
	{
		private final ServiceReference m_reference;

		ServiceReferenceMap(ServiceReference reference)
		{
			m_reference = reference;
		}

		@Override
		public Set<Map.Entry<String, Object>> entrySet()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Object get(Object key)
		{
			return m_reference.getProperty((String)key);
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
	 * This implementation removes white spaces. This is the minimum implementation allowed by the OSGi spec.
	 * 
	 * @param input
	 *            Input string.
	 * @return String ready for APPROX comparison.
	 */
	static String approxString(String input)
	{
		boolean changed = false;
		char[] output = input.toCharArray();
		int cursor = 0;
		for(int i = 0, length = output.length; i < length; i++)
		{
			char c = output[i];

			if(Character.isWhitespace(c))
			{
				changed = true;
				continue;
			}

			output[cursor] = c;
			cursor++;
		}

		return changed
				? new String(output, 0, cursor)
				: input;
	}

	/**
	 * Encode the value string such that '(', '*', ')' and '\' are escaped.
	 * 
	 * @param value
	 *            unencoded value string.
	 * @return encoded value string.
	 */
	static String encodeValue(String value)
	{
		int inlen = value.length();
		char[] output = null;
		int cursor = 0;
		for(int i = 0; i < inlen; i++)
		{
			char c = value.charAt(i);
			switch(c)
			{
			case '(':
			case '*':
			case ')':
			case '\\':
				if(output == null)
				{
					output = new char[inlen << 2];
					if(i > 0)
					{
						value.getChars(0, i, output, 0);
						cursor = i;
					}
				}
				output[cursor++] = '\\';
				break;
			}
			if(output != null)
				output[cursor++] = c;
		}
		return output == null
				? value
				: new String(output, 0, cursor);
	}

	private final int m_op;

	static final int EQUAL = 1;

	static final int APPROX = 2;

	static final int GREATER = 3;

	/** filter attribute or null if operation AND, OR or NOT */
	private final String m_attr;

	/* normalized filter string for topLevel Filter object */
	private transient volatile String m_filterString;

	/* true if root Filter object */
	private final boolean m_topLevel;

	FilterImpl(boolean topLevel, int operation, String attr)
	{
		m_topLevel = topLevel;
		m_op = operation;
		m_attr = attr;
	}

	public void addConsultedAttributes(Map<String, String[]> propertyChoices)
	{
		String stringValue = getValueAsString();

		// Add the attribute value as a valid choice for the attribute
		// unless it's already present.
		//
		synchronized(propertyChoices)
		{
			String[] choices = propertyChoices.get(getAttr());
			if(choices == null)
			{
				propertyChoices.put(getAttr(), new String[] { stringValue });
				return;
			}

			int top = choices.length;
			int idx = top;
			while(--idx >= 0)
				if(stringValue.equals(choices[idx]))
					return;

			String[] newChoices = new String[top + 1];
			System.arraycopy(choices, 0, newChoices, 0, top);
			newChoices[top] = stringValue;
			propertyChoices.put(getAttr(), newChoices);
		}
	}

	public Filter addFilterWithAnd(Filter subFilter)
	{
		return subFilter == null
				? this
				: addFilter((FilterImpl)subFilter, AND);
	}

	public Filter addFilterWithOr(Filter subFilter)
	{
		return subFilter == null
				? this
				: addFilter((FilterImpl)subFilter, OR);
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj == this || (obj instanceof Filter && toString().equals(obj.toString()));
	}

	public int getOp()
	{
		return m_op;
	}

	@Override
	public int hashCode()
	{
		return 11 * toString().hashCode();
	}

	@SuppressWarnings("unchecked")
	public boolean match(Dictionary properties)
	{
		return match(properties, false);
	}

	public boolean match(Map<String, ? extends Object> properties)
	{
		return match0(properties == null
				? Collections.<String, Object> emptyMap()
				: new CaseInsensitiveMap<Object>(properties));
	}

	public boolean match(ServiceReference reference)
	{
		return match0(reference == null
				? Collections.<String, Object> emptyMap()
				: new ServiceReferenceMap(reference));
	}

	@SuppressWarnings("unchecked")
	public boolean matchCase(Dictionary dictionary)
	{
		return match(dictionary, true);
	}

	public boolean matchCase(Map<String, ? extends Object> properties)
	{
		return match0(properties == null
				? Collections.<String, Object> emptyMap()
				: properties);
	}

	public Filter stripFilter(Filter subFilter)
	{
		return stripFilter(subFilter, true);
	}

	@Override
	public String toString()
	{
		String result = m_filterString;
		if(result == null)
		{
			StringBuilder bld = new StringBuilder();
			toString(bld);
			result = bld.toString();
			if(m_topLevel)
				m_filterString = result;
		}
		return result;
	}

	FilterImpl addFilter(FilterImpl subFilter, int op)
	{
		if(equals(subFilter))
			return this;
		return new AndOrFilterImpl(true, op, new FilterImpl[] { this, subFilter });
	}

	final boolean compare(Object value1)
	{
		if(value1 == null)
			return false;

		if(value1 instanceof Collection<?>)
		{
			for(Iterator<?> iterator = ((Collection<?>)value1).iterator(); iterator.hasNext();)
				if(compare(iterator.next()))
					return true;
			return false;
		}

		if(value1 instanceof Object[])
		{
			Object[] array = (Object[])value1;
			for(int i = 0, size = array.length; i < size; i++)
				if(compare(array[i]))
					return true;
			return false;
		}
		return internalCompare(value1);
	}

	String getAttr()
	{
		return m_attr;
	}

	String getValueAsString()
	{
		return null;
	}

	boolean internalCompare(Object value)
	{
		return false;
	}

	abstract boolean match0(Map<String, ? extends Object> properties);

	FilterImpl stripFilter(Filter subFilter, boolean topLevel)
	{
		return equals(subFilter)
				? null
				: this;
	}

	abstract void toString(StringBuilder bld);

	@SuppressWarnings("unchecked")
	private boolean match(Dictionary dictionary, boolean caseSensitive)
	{
		Map props = null;
		if(dictionary instanceof Map)
		{
			props = (Map)dictionary;
			if(!caseSensitive)
				props = new CaseInsensitiveMap(props);
		}
		else if(dictionary == null)
			props = Collections.emptyMap();
		else
			props = new DictionaryMap(dictionary, caseSensitive);
		return match0(props);
	}
}
