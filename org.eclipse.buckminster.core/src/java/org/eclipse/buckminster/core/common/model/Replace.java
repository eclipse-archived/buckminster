/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;



/**
 * The Replace class will perform a regular expression based match
 * and replace on its source. The pattern can be quoted in order to
 * make the replace function as a normal (non expression based)
 * replace.
 *
 * @author Thomas Hallgren
 */
public class Replace extends ValueHolderFilter
{
	public static final String TAG = "replace";
	public static final String MATCH_TAG = "match";
	public static final String ATTR_PATTERN = "pattern";
	public static final String ATTR_REPLACEMENT = "replacement";
	public static final String ATTR_QUOTE_PATTERN = "quotePattern";

	public static final class Match extends AbstractSaxableElement
	{
		private final Pattern		m_pattern;
		private final String		m_patternString;
		private final String		m_replacement;
		private final boolean		m_quotePattern;

		/**
		 * Create a Replace.Match that will replace every subsequence of the value
		 * provided by a <code>valueProvider</code> that matches the
		 * <code>pattern</code> with the given <code>replacement</code> string.
		 * 
		 * @param pattern
		 *            The pattern that defines the replacement
		 * @param replacement
		 *            The replacement string
		 * @param quotePattern
		 *            Set to true if the pattern should be quoted.
		 * @see java.util.regex.Matcher#replaceAll(String)
		 * @see java.util.regex.Pattern#quote(String)
		 */
		public Match(String pattern, String replacement, boolean quotePattern)
		{
			m_patternString = pattern;
			m_quotePattern = quotePattern;
			m_replacement = replacement;
			if(quotePattern)
				pattern = Pattern.quote(pattern);
			m_pattern = Pattern.compile(pattern);
		}


		@Override
		public boolean equals(Object o)
		{
			if(o == this)
				return true;

			if(!(o instanceof Match))
				return false;

			return m_quotePattern == ((Match)o).m_quotePattern
				&& m_patternString.equals(((Match)o).m_patternString)
				&& m_replacement.equals(((Match)o).m_replacement);
		}

		@Override
		public int hashCode()
		{
			int hc = m_patternString.hashCode();
			hc = 37 * hc + m_replacement.hashCode();
			hc = 37 * hc + (m_quotePattern ? 17 : 0);
			return hc;
		}

		public String getDefaultTag()
		{
			return MATCH_TAG;
		}

		@Override
		protected void addAttributes(AttributesImpl attrs) throws SAXException
		{
			Utils.addAttribute(attrs, ATTR_PATTERN, m_patternString);
			Utils.addAttribute(attrs, ATTR_REPLACEMENT, m_replacement);
			if(m_quotePattern)
				Utils.addAttribute(attrs, ATTR_QUOTE_PATTERN, "true");
		}

		String match(String resolved)
		{
			Matcher matcher = m_pattern.matcher(resolved);
			if(matcher.find())
			{
				StringBuffer sb = new StringBuffer();
				do
				{
					matcher.appendReplacement(sb, m_replacement);
				} while(matcher.find());
				matcher.appendTail(sb);
				return sb.toString();
			}
			return null;
		}
	}

	private final ArrayList<Match> m_matchers = new ArrayList<Match>();

	public void addMatch(Match match)
	{
		m_matchers.add(match);
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && m_matchers.equals(((Replace)o).m_matchers);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_matchers.hashCode();
		return hc;
	}

	@Override
	public String checkedGetValue(Map<String, String> props, int recursionGuard)
	{
		String resolved = this.checkedGetSourceValue(props, recursionGuard);
		if(resolved == null || NO_VALUE.equals(resolved))
			return NO_VALUE;

		for(Match match : m_matchers)
		{
			String result = match.match(resolved);
			if(result != null)
				return result;
		}
		return resolved;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	throws SAXException
	{
		// If we have exactly one, then use attributes instead of
		// subelements
		//
		if(m_matchers.size() == 1)
			m_matchers.get(0).addAttributes(attrs);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix)
	throws SAXException
	{
		super.emitElements(handler, namespace, prefix);
		int top = m_matchers.size();

		// If there's only one it will be added as an attribute.
		//
		if(top > 1)
		{
			for(int idx = 0; idx < top; ++idx)
			{
				Match match = m_matchers.get(idx);
				match.toSax(handler, namespace, prefix, match.getDefaultTag());
			}
		}
	}
}

