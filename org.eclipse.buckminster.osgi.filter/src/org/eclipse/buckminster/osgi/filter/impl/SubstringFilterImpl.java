/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.Map;

class SubstringFilterImpl extends FilterImpl
{
	private final String[] m_value;

	SubstringFilterImpl(String attr, String[] value)
	{
		super(FilterImpl.SUBSTRING, attr);
		m_value = value;
	}

	public int compareTo(FilterImpl filter)
	{
		int cmp = internalCompareTo(filter);
		if(cmp != 0)
			return cmp;

		String[] o_value = ((SubstringFilterImpl)filter).m_value;
		int top = m_value.length;
		if(top > o_value.length)
			return 1;

		if(top < o_value.length)
			return -1;

		for(int idx = 0; idx < top; ++idx)
		{
			String m = m_value[idx];
			String o = o_value[idx];
			if(m == null)
			{
				if(o != null)
					return -1;
			}
			else
			{
				if(o == null)
					return 1;

				cmp = m.compareTo(o);
				if(cmp != 0)
					return cmp;
			}
		}
		return 0;
	}

	@Override
	String getValueAsString()
	{
		StringBuilder bld = new StringBuilder();
		int size = m_value.length;
		for(int i = 0; i < size; i++)
		{
			String substr = m_value[i];
			if(substr == null)
				bld.append('*');
			else
				bld.append(substr);
		}
		return bld.toString();
	}

	@Override
	boolean internalCompare(Object value)
	{
		if(!(value instanceof String))
			return false;

		String string = (String)value;
		int pos = 0;
		for(int i = 0, size = m_value.length; i < size; i++)
		{
			String substr = m_value[i];

			if(i + 1 < size) /* if this is not that last substr */
			{
				if(substr == null) /* * */
				{
					String substr2 = m_value[i + 1];

					if(substr2 == null) /* ** */
						continue; /* ignore first star */
					/* xxx */
					int index = string.indexOf(substr2, pos);
					if(index == -1)
					{
						return false;
					}

					pos = index + substr2.length();
					if(i + 2 < size) // if there are more substrings, increment over the string we just matched;
						// otherwise need to do the last substr check
						i++;
				}
				else
				/* xxx */{
					int len = substr.length();

					if(string.regionMatches(pos, substr, 0, len))
					{
						pos += len;
					}
					else
					{
						return false;
					}
				}
			}
			else
			/* last substr */{
				if(substr == null) /* * */
				{
					return true;
				}
				/* xxx */
				return string.endsWith(substr);
			}
		}

		return true;
	}

	@Override
	boolean match0(Map<String, ? extends Object> properties)
	{
		Object prop = (properties == null)
				? null
				: properties.get(getAttr());
		return prop instanceof String
				? compare(prop)
				: false;
	}

	@Override
	void toString(StringBuilder sb)
	{
		sb.append('(');
		sb.append(getAttr());
		sb.append('=');

		for(int i = 0, size = m_value.length; i < size; i++)
		{
			String substr = m_value[i];

			if(substr == null) /* * */
				sb.append('*');
			else
				sb.append(encodeValue(substr));
		}
		sb.append(')');
	}
}
