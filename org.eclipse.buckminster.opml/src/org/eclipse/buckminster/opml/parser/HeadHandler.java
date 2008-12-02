/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.eclipse.buckminster.opml.Messages;
import org.eclipse.buckminster.opml.builder.HeadBuilder;
import org.eclipse.buckminster.opml.model.Head;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.StringElementHandler;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * SAX Parser for the OPML Head element
 * 
 * @author Thomas Hallgren
 */
class HeadHandler extends ChildHandler
{
	class StringHandler extends StringElementHandler
	{
		StringHandler()
		{
			super(HeadHandler.this);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException
		{
			super.endElement(uri, localName, qName);
			String name = (localName == null || localName.length() == 0)
					? qName
					: localName;

			if(Head.ELEM_DATE_CREATED.equals(name))
				m_head.setDateCreated(getDate(name));
			else if(Head.ELEM_DATE_MODIFIED.equals(name))
				m_head.setDateModified(getDate(name));
			else if(Head.ELEM_DOCS.equals(name))
				m_head.setDocs(getURI(name));
			else if(Head.ELEM_EXPANSION_STATE.equals(name))
				m_head.setExpansionState(getIntList(name));
			else if(Head.ELEM_OWNER_EMAIL.equals(name))
				m_head.setOwnerEmail(getString());
			else if(Head.ELEM_OWNER_ID.equals(name))
				m_head.setOwnerId(getURI(name));
			else if(Head.ELEM_OWNER_NAME.equals(name))
				m_head.setOwnerName(getString());
			else if(Head.ELEM_TITLE.equals(name))
				m_head.setTitle(getString());
			else if(Head.ELEM_VERT_SCROLL_STATE.equals(name))
				m_head.setVertScrollState(getInt(name));
			else if(Head.ELEM_WINDOW_BOTTOM.equals(name))
				m_head.setWindowBottom(getInt(name));
			else if(Head.ELEM_WINDOW_LEFT.equals(name))
				m_head.setWindowLeft(getInt(name));
			else if(Head.ELEM_WINDOW_RIGHT.equals(name))
				m_head.setWindowRight(getInt(name));
			else if(Head.ELEM_WINDOW_TOP.equals(name))
				m_head.setWindowTop(getInt(name));

			// According to the spec, elements that are not recognized should
			// be ignored.
		}

		private Date getDate(String localName) throws SAXException
		{
			try
			{
				return Head.RFC_822_FORMAT.parse(getString());
			}
			catch(ParseException e)
			{
				throw new SAXParseException(NLS.bind(
						Messages.element_0_does_not_represent_a_valid_RFC822_formatted_date, localName),
						getDocumentLocator());
			}
		}

		private int getInt(String localName) throws SAXException
		{
			try
			{
				return Integer.parseInt(getString());
			}
			catch(NumberFormatException e)
			{
				throw new SAXParseException(NLS.bind(Messages.element_0_does_not_represent_a_valid_integer, localName),
						getDocumentLocator());
			}
		}

		private int[] getIntList(String localName) throws SAXException
		{
			try
			{
				StringTokenizer tokens = new StringTokenizer(getString(), ","); //$NON-NLS-1$
				ArrayList<String> values = new ArrayList<String>();
				while(tokens.hasMoreTokens())
					values.add(tokens.nextToken().trim());
				int idx = values.size();
				int[] intList = new int[idx];
				while(--idx >= 0)
					intList[idx] = Integer.parseInt(values.get(idx));
				return intList;
			}
			catch(NumberFormatException e)
			{
				throw new SAXParseException(NLS.bind(Messages.element_0_does_not_represent_a_valid_list_of_integers,
						localName), getDocumentLocator());
			}
		}

		private String getString()
		{
			return new String(getBuffer(), 0, getLengthAndReset());
		}

		private URI getURI(String localName) throws SAXException
		{
			try
			{
				return new URI(getString());
			}
			catch(URISyntaxException e)
			{
				throw new SAXParseException(NLS.bind(Messages.element_0_does_not_represent_a_valid_URI, localName),
						getDocumentLocator());
			}
		}
	}

	public static final String TAG = Head.TAG;

	private final HeadBuilder m_head;

	private final StringHandler m_stringHandler = new StringHandler();

	HeadHandler(AbstractHandler parent, HeadBuilder head)
	{
		super(parent);
		m_head = head;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		return m_stringHandler;
	}
}
