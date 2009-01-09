/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.osgi.framework.internal.core.FilterImpl;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public class FilterUtils
{
	public static class MatchAll
	{
		public MatchAll(String v)
		{
		}

		@Override
		public boolean equals(Object o)
		{
			return true;
		}

		@Override
		public int hashCode()
		{
			return MATCH_ALL.hashCode();
		}

		@Override
		public String toString()
		{
			return MATCH_ALL;
		}
	}

	public static final String MATCH_ALL = "*"; //$NON-NLS-1$

	public static final MatchAll MATCH_ALL_OBJ = new MatchAll(MATCH_ALL);

	private static final Field Filter_attr;

	private static final Field Filter_value;

	static
	{
		try
		{
			Class<? extends Filter> filterClass = FilterImpl.class;
			Filter_attr = filterClass.getDeclaredField("attr"); //$NON-NLS-1$
			Filter_value = filterClass.getDeclaredField("value"); //$NON-NLS-1$
			Filter_attr.setAccessible(true);
			Filter_value.setAccessible(true);
		}
		catch(Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * <p>
	 * Scan the filter and find all attributes that this filter makes use of. For each attribute found, the values that
	 * the attribute will be compared against are collected into a unique array of strings and put into o the provided
	 * <code>propertyChoices</code> map keyed by the name of the attribute.
	 * </p>
	 * <p>
	 * It is guaranteed that all updates to the <code>propertyChoices</code> map are synchronised.
	 * </p>
	 * 
	 * @param filter
	 *            The filter to scan
	 * @param propertyChoices
	 *            The map that will receive the results.
	 */
	public static void addConsultedAttributes(Filter filter, Map<String, String[]> propertyChoices)
	{
		if(filter == null)
			return;

		Object value = getFilterValue(filter);
		if(value instanceof Filter[])
		{
			Filter[] subFilters = (Filter[])value;
			int idx = subFilters.length;
			while(--idx >= 0)
				addConsultedAttributes(subFilters[idx], propertyChoices);
			return;
		}

		if(value instanceof Filter)
		{
			addConsultedAttributes((Filter)value, propertyChoices);
			return;
		}

		String attr = getFilterAttr(filter);
		if(attr == null || value == null)
			return;

		String stringValue;
		if(value instanceof String[])
		{
			String[] substrings = (String[])value;
			StringBuilder bld = new StringBuilder();
			int size = substrings.length;
			for(int i = 0; i < size; i++)
			{
				String substr = substrings[i];
				if(substr == null)
					bld.append('*');
				else
					bld.append(substr);
			}
			stringValue = bld.toString();
		}
		else
			stringValue = value.toString();

		// Add the attribute value as a valid choice for the attribute
		// unless it's already present.
		//
		synchronized(propertyChoices)
		{
			String[] choices = propertyChoices.get(attr);
			if(choices == null)
			{
				propertyChoices.put(attr, new String[] { stringValue });
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
			propertyChoices.put(attr, newChoices);
		}
	}

	public static Filter createFilter(String expression) throws InvalidSyntaxException
	{
		return FrameworkUtil.createFilter(expression);
	}

	public static Filter createFilter(String os, String ws, String arch, String nl)
	{
		StringBuilder bld = new StringBuilder();

		int cnt = 0;
		if(addProperty(bld, TargetPlatform.TARGET_OS, os))
			cnt++;
		if(addProperty(bld, TargetPlatform.TARGET_WS, ws))
			cnt++;
		if(addProperty(bld, TargetPlatform.TARGET_ARCH, arch))
			cnt++;
		if(addProperty(bld, TargetPlatform.TARGET_NL, nl))
			cnt++;

		if(cnt > 0)
		{
			if(cnt > 1)
			{
				StringBuilder and = new StringBuilder(bld.length() + 3);
				and.append("(&"); //$NON-NLS-1$
				and.append(bld);
				and.append(')');
				bld = and;
			}
			try
			{
				return createFilter(bld.toString());
			}
			catch(InvalidSyntaxException e)
			{
				// This should never happen
				//
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	public static boolean isMatch(Filter filter, Map<String, ? extends Object> properties)
	{
		return (filter == null || filter.match(MapToDictionary.wrap(properties)));
	}

	public static Filter replaceAttributeNames(Filter filter, String from, String to)
	{
		if(filter == null)
			return null;
		try
		{
			return createFilter(replaceAttributeNames(filter.toString(), from, to));
		}
		catch(InvalidSyntaxException e)
		{
			// This should never happen since we don't change the actual syntax
			//
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method will parse the filter and potentially change the names of the attributes in that filter. No values
	 * will be replaced. Attributes named <code>from</code> or attributes that uses a dotted notation where
	 * <code>from</code> is one of the elements will have <code>from</code> replaced with <code>to</code>.
	 * 
	 * @param filter
	 *            An LDAP filter in string format
	 * @param from
	 *            The attribute name to replace
	 * @param to
	 *            The new attribute name
	 * @return A string where the needed replacements has been made.
	 */
	public static String replaceAttributeNames(String filter, String from, String to)
	{
		if(filter == null)
			return null;

		StringBuilder bld = new StringBuilder();
		boolean lastWasFilterStart = false;
		boolean lastWasEscape = false;
		StringTokenizer tokens = new StringTokenizer(filter, "\\&|!~<>=(). \t\r\f\n", true); //$NON-NLS-1$
		while(tokens.hasMoreTokens())
		{
			String token = tokens.nextToken();
			if(lastWasFilterStart && token.equals(from))
				bld.append(to);
			else
				bld.append(token);

			if(token.length() == 1)
			{
				if(lastWasEscape)
					lastWasEscape = false;
				else
				{
					char c = token.charAt(0);
					if(Character.isWhitespace(c))
						continue;

					if(c == '(')
						lastWasFilterStart = true;
					else
						lastWasEscape = (c == '\\');
				}
			}
			else
			{
				lastWasEscape = false;
				lastWasFilterStart = false;
			}
		}
		return bld.toString();
	}

	private static boolean addProperty(StringBuilder bld, String key, String value)
	{
		value = TextUtils.notEmptyTrimmedString(value);
		if(value == null)
			return false;

		int bldStart = bld.length();
		int top = value.length();
		boolean startNew = true;
		boolean multi = false;
		for(int idx = 0; idx < top; ++idx)
		{
			if(startNew)
			{
				if(idx > 0)
				{
					bld.append(')');
					multi = true;
				}
				bld.append('(');
				bld.append(key);
				bld.append('=');
				startNew = false;
			}
			char c = value.charAt(idx);
			switch(c)
			{
			case '(':
			case ')':
			case '\\':
				bld.append('\\');
				bld.append(c);
				continue;
			case ',':
				startNew = true;
				continue;
			}
			bld.append(c);
		}

		bld.append(')');
		if(multi)
		{
			String expr = bld.substring(bldStart);
			bld.setLength(bldStart);
			bld.append("(|"); //$NON-NLS-1$
			bld.append(expr);
			bld.append(')');
		}
		return true;
	}

	private static String getFilterAttr(Filter filter)
	{
		try
		{
			return (String)Filter_attr.get(filter);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}

	private static Object getFilterValue(Filter filter)
	{
		try
		{
			return Filter_value.get(filter);
		}
		catch(IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}
}
