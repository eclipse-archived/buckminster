/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.InvalidSyntaxException;

/**
 * Parser class for OSGi filter strings. This class parses the complete filter string and builds a tree of Filter
 * objects rooted at the parent.
 */
public class Parser
{
	public static Filter parse(String filterString) throws InvalidSyntaxException
	{
		return new Parser(filterString).internalParse();
	}

	private final String m_filter;

	private final StringBuilder m_sb = new StringBuilder();

	private int m_pos;

	private Parser(String filter)
	{
		m_filter = filter;
		m_pos = 0;
	}

	private Filter internalParse() throws InvalidSyntaxException
	{
		try
		{
			FilterImpl filter = parse_filter(true);
			if(m_pos != m_filter.length())
				throw syntaxException(Messages.FILTER_TRAILING_CHARACTERS);
			return filter;
		}
		catch(StringIndexOutOfBoundsException e)
		{
			throw new InvalidSyntaxException(NLS.bind(Messages.FILTER_PREMATURE_END, m_filter), m_filter);
		}
	}

	private FilterImpl parse_and(boolean topLevel) throws InvalidSyntaxException
	{
		skipWhiteSpace();
		char c = m_filter.charAt(m_pos);
		if(c != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		List<FilterImpl> operands = new ArrayList<FilterImpl>(10);
		while(c == '(')
		{
			FilterImpl child = parse_filter(false);
			operands.add(child);
			c = m_filter.charAt(m_pos);
		}

		return new AndOrFilterImpl(topLevel, FilterImpl.AND, operands.toArray(new FilterImpl[operands.size()]));
	}

	private String parse_attr() throws InvalidSyntaxException
	{
		skipWhiteSpace();

		int begin = m_pos;
		int end = m_pos;

		char c = m_filter.charAt(begin);
		while(!(c == '~' || c == '<' || c == '>' || c == '=' || c == '(' || c == ')'))
		{
			m_pos++;
			if(!Character.isWhitespace(c))
				end = m_pos;
			c = m_filter.charAt(m_pos);
		}
		if(end == begin)
			throw syntaxException(Messages.FILTER_MISSING_ATTR);
		return m_filter.substring(begin, end);
	}

	private FilterImpl parse_filter(boolean topLevel) throws InvalidSyntaxException
	{
		FilterImpl filter;
		skipWhiteSpace();

		if(m_filter.charAt(m_pos) != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		m_pos++;
		filter = parse_filtercomp(topLevel);

		skipWhiteSpace();

		if(m_filter.charAt(m_pos) != ')')
			throw syntaxException(Messages.FILTER_MISSING_RIGHTPAREN);

		m_pos++;
		skipWhiteSpace();

		return filter;
	}

	private FilterImpl parse_filtercomp(boolean topLevel) throws InvalidSyntaxException
	{
		skipWhiteSpace();

		char c = m_filter.charAt(m_pos);

		switch(c)
		{
		case '&':
		{
			m_pos++;
			return parse_and(topLevel);
		}
		case '|':
		{
			m_pos++;
			return parse_or(topLevel);
		}
		case '!':
		{
			m_pos++;
			return parse_not(topLevel);
		}
		}
		return parse_item(topLevel);
	}

	private FilterImpl parse_item(boolean topLevel) throws InvalidSyntaxException
	{
		String attr = parse_attr();

		skipWhiteSpace();

		char c2 = m_filter.charAt(m_pos + 1);
		switch(m_filter.charAt(m_pos))
		{
		case '~':
			if(c2 == '=')
			{
				m_pos += 2;
				return new StringFilterImpl(topLevel, FilterImpl.APPROX, attr, parse_value());
			}
			break;
		case '>':
			if(c2 == '=')
			{
				m_pos += 2;
				return new StringFilterImpl(topLevel, FilterImpl.GREATER, attr, parse_value());
			}
			break;
		case '<':
			if(c2 == '=')
			{
				m_pos += 2;
				return new StringFilterImpl(topLevel, FilterImpl.LESS, attr, parse_value());
			}
			break;
		case '=':
			if(c2 == '*')
			{
				int oldpos = m_pos;
				m_pos += 2;
				skipWhiteSpace();
				if(m_filter.charAt(m_pos) == ')')
					return new PresentFilterImpl(topLevel, attr);
				m_pos = oldpos;
			}

			m_pos++;
			Object string = parse_substring();

			return (string instanceof String)
					? new StringFilterImpl(topLevel, FilterImpl.EQUAL, attr, (String)string)
					: new SubstringFilterImpl(topLevel, attr, (String[])string);
		}
		throw syntaxException(Messages.FILTER_INVALID_OPERATOR);
	}

	private FilterImpl parse_not(boolean topLevel) throws InvalidSyntaxException
	{
		skipWhiteSpace();

		if(m_filter.charAt(m_pos) != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);
		FilterImpl child = parse_filter(false);
		return new NotFilterImpl(topLevel, child);
	}

	private FilterImpl parse_or(boolean topLevel) throws InvalidSyntaxException
	{
		skipWhiteSpace();
		char c = m_filter.charAt(m_pos);
		if(c != '(')
			throw syntaxException(Messages.FILTER_MISSING_LEFTPAREN);

		List<FilterImpl> operands = new ArrayList<FilterImpl>(10);
		while(c == '(')
		{
			FilterImpl child = parse_filter(false);
			operands.add(child);
			c = m_filter.charAt(m_pos);
		}

		return new AndOrFilterImpl(topLevel, FilterImpl.OR, operands.toArray(new FilterImpl[operands.size()]));
	}

	private Object parse_substring() throws InvalidSyntaxException
	{
		List<String> operands = null;
		m_sb.setLength(0);
		parseloop: while(true)
		{
			char c = m_filter.charAt(m_pos);

			switch(c)
			{
			case ')':
				if(m_sb.length() > 0)
				{
					String val = m_sb.toString();
					if(operands == null)
						return val;
					operands.add(val);
				}
				break parseloop;
			case '(':
				throw syntaxException(Messages.FILTER_INVALID_VALUE);
			case '*':
				if(operands == null)
					operands = new ArrayList<String>();
				if(m_sb.length() > 0)
				{
					operands.add(m_sb.toString());
					m_sb.setLength(0);
				}
				operands.add(null);
				m_pos++;
				break;

			case '\\':
				c = m_filter.charAt(++m_pos);
				/* fall through into default */

			default:
				m_sb.append(c);
				m_pos++;
				break;
			}
		}

		if(operands == null)
			throw syntaxException(Messages.FILTER_MISSING_VALUE);

		int size = operands.size();
		if(size == 1)
		{
			String single = operands.get(0);
			if(single != null)
				return single;
		}
		return operands.toArray(new String[size]);
	}

	private String parse_value() throws InvalidSyntaxException
	{
		m_sb.setLength(0);
		parseloop: while(true)
		{
			char c = m_filter.charAt(m_pos);
			switch(c)
			{
			case ')':
				break parseloop;

			case '(':
				throw syntaxException(Messages.FILTER_INVALID_VALUE);

			case '\\':
				c = m_filter.charAt(++m_pos);
				/* fall through into default */

			default:
				m_sb.append(c);
				m_pos++;
				break;
			}
		}
		int len = m_sb.length();
		while(len > 0 && m_sb.charAt(len - 1) <= ' ')
			--len;

		if(len == 0)
			throw syntaxException(Messages.FILTER_MISSING_VALUE);
		m_sb.setLength(len);
		return m_sb.toString();
	}

	private void skipWhiteSpace()
	{
		for(int top = m_filter.length(); m_pos < top && Character.isWhitespace(m_filter.charAt(m_pos)); ++m_pos)
			;
	}

	private InvalidSyntaxException syntaxException(String msg)
	{
		return new InvalidSyntaxException(NLS.bind(msg, m_filter, Integer.valueOf(m_pos)), m_filter);
	}
}
