/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.rmap.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class BidirectionalTransformer extends AbstractSaxableElement
{
	public static final String TAG = "transform";

	public static final String ATTR_TO_PATTERN = "toPattern";

	public static final String ATTR_TO_REPLACEMENT = "toReplacement";

	public static final String ATTR_FROM_PATTERN = "fromPattern";

	public static final String ATTR_FROM_REPLACEMENT = "fromReplacement";

	private static String replace(String source, Pattern pattern, String replacement)
	{
		Matcher matcher = pattern.matcher(source);
		if(matcher.find())
		{
			StringBuffer sb = new StringBuffer();
			do
			{
				matcher.appendReplacement(sb, replacement);
			} while(matcher.find());
			matcher.appendTail(sb);
			return sb.toString();
		}
		return null;
	}

	private final Pattern m_toPattern;

	private final String m_toReplacement;

	private final Pattern m_fromPattern;

	private final String m_fromReplacement;

	public BidirectionalTransformer(Pattern toPattern, String toReplacement, Pattern fromPattern, String fromReplacement)
	{
		m_toPattern = toPattern;
		m_toReplacement = toReplacement;
		m_fromPattern = fromPattern;
		m_fromReplacement = fromReplacement;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_TO_PATTERN, m_toPattern.toString());
		Utils.addAttribute(attrs, ATTR_TO_REPLACEMENT, m_toReplacement);
		Utils.addAttribute(attrs, ATTR_FROM_PATTERN, m_fromPattern.toString());
		Utils.addAttribute(attrs, ATTR_FROM_REPLACEMENT, m_fromReplacement);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public final Pattern getFromPattern()
	{
		return m_fromPattern;
	}

	public final String getFromReplacement()
	{
		return m_fromReplacement;
	}

	public final Pattern getToPattern()
	{
		return m_toPattern;
	}

	public final String getToReplacement()
	{
		return m_toReplacement;
	}

	private String transform(String source, Pattern pattern, String replacement, Pattern reversePattern,
			String reverseReplacement) throws TransformerMismatchException
	{
		String result = replace(source, pattern, replacement);
		if(result == null)
			return null;

		String reverse = replace(result, reversePattern, reverseReplacement);
		if(reverse == null)
			//
			// Matches only one direction. Don't replace then.
			//
			return null;

		// This pattern was possible to replace in both directions. The result should
		// be the same in that case.
		//
		if(source.equals(reverse))
			return result;

		throw new TransformerMismatchException(this);
	}

	public String transformFrom(String source) throws TransformerMismatchException
	{
		return transform(source, m_fromPattern, m_fromReplacement, m_toPattern, m_toReplacement);
	}

	public String transformTo(String source) throws TransformerMismatchException
	{
		return transform(source, m_toPattern, m_toReplacement, m_fromPattern, m_fromReplacement);
	}
}
