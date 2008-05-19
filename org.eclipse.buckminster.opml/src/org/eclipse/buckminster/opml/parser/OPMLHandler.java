/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.Head;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * SAX Parser for the OPML top element
 *
 * @author Thomas Hallgren
 */
public class OPMLHandler extends ElementHandler
{
	public static final String TAG = OPML.TAG;

	private final OPMLBuilder m_opml = new OPMLBuilder();
	private final HeadHandler m_headHandler = new HeadHandler(this, m_opml.getHeadBuilder());
	private final BodyHandler m_bodyHandler = new BodyHandler(this, m_opml.getBodyBuilder());

	public OPMLHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_opml.clear();
		m_opml.setVersion(getStringValue(attrs, OPML.ATTR_VERSION));
	}

	public OPMLBuilder getOPML()
	{
		return m_opml;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Head.TAG.equals(localName))
			ch = m_headHandler;
		else if(Body.TAG.equals(localName))
			ch = m_bodyHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
}
