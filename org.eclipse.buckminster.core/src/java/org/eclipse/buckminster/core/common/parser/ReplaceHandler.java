/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.Replace;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReplaceHandler extends ValueFilterHandler
{
	public static class MatchHandler extends ExtensionAwareHandler
	{
		@SuppressWarnings("hiding")
		static final String TAG = Replace.MATCH_TAG;

		private Replace.Match m_match;

		public MatchHandler(AbstractHandler parent)
		{
			super(parent);
		}

		final Replace.Match getMatch()
		{
			return m_match;
		}

		@Override
		public void handleAttributes(Attributes attrs) throws SAXException
		{
			String pattern = this.getStringValue(attrs, Replace.ATTR_PATTERN);
			String replacement = this.getStringValue(attrs, Replace.ATTR_REPLACEMENT);
			boolean quotePattern = getOptionalBooleanValue(attrs, Replace.ATTR_QUOTE_PATTERN, false);
			m_match = new Replace.Match(pattern, replacement, quotePattern);
		}
	}

	static final String TAG = Replace.TAG;

	private final MatchHandler m_matchHandler = new MatchHandler(this);

	public ReplaceHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child)
	{
		if(child == m_matchHandler)
			((Replace)this.getValueHolder()).addMatch(m_matchHandler.getMatch());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(MatchHandler.TAG.equals(localName))
			ch = m_matchHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		Replace rp = new Replace();
		String pattern = getOptionalStringValue(attrs, Replace.ATTR_PATTERN);
		String replacement = getOptionalStringValue(attrs, Replace.ATTR_REPLACEMENT);
		if(pattern != null)
		{
			if(replacement == null)
				throw new SAXParseException(Messages.ReplaceHandler_pattern_but_no_replacement, this.getDocumentLocator());
			boolean quotePattern = getOptionalBooleanValue(attrs, Replace.ATTR_QUOTE_PATTERN, false);
			rp.addMatch(new Replace.Match(pattern, replacement, quotePattern));
		}
		else if(replacement != null)
			throw new SAXParseException(Messages.ReplaceHandler_replacement_but_no_pattern, this.getDocumentLocator());

		this.setValueHolder(rp);
	}
}
