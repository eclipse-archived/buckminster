/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.osgi.framework.internal.core.FilterImpl;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public class FilterUtils
{
	public static final String MATCH_ALL = "*";

	public static Filter createFilter(String expression) throws InvalidSyntaxException
	{
		return new MatchAllAwareFilterImpl(expression);
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
				and.append("(&");
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

	public static boolean isMatch(Filter filter, Map<String, String> properties)
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
	 * This method will parse the filter and potentially change the names of the attributes in that
	 * filter. No values will be replaced.
	 * Attributes named <code>from</code> or attributes that uses a dotted notation where <code>from</code> is
	 * one of the elements will have <code>from</code> replaced with <code>to</code>.
	 * @param filter An LDAP filter in string format
	 * @param from The attribute name to replace
	 * @param to The new attribute name
	 * @return A string where the needed replacements has been made. 
	 */
	public static String replaceAttributeNames(String filter, String from, String to)
	{
		if(filter == null)
			return null;

		StringBuilder bld = new StringBuilder();
		boolean lastWasFilterStart = false;
		boolean lastWasEscape = false;
		StringTokenizer tokens = new StringTokenizer(filter, "\\&|!~<>=(). \t\r\f\n", true);
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
			bld.append("(|");
			bld.append(expr);
			bld.append(')');
		}
		return true;
	}

	static class MatchAllAwareFilterImpl extends FilterImpl
	{
		public MatchAllAwareFilterImpl(String expression) throws InvalidSyntaxException
		{
			super(expression);
			if(value instanceof Filter[])
			{
				Filter[] subFilters = (Filter[])value;
				int idx = subFilters.length;
				while(--idx >= 0)
					subFilters[idx] = new MatchAllAwareFilterImpl(subFilters[idx].toString());
			}
		}

		@Override
		protected boolean compare_String(int op, String string, Object value2)
		{
			return MATCH_ALL.equals(string)
				? true
				: super.compare_String(op, string, value2);
		}
	}
}
