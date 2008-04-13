/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.Head;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * SAX Parser for the OPML top element
 *
 * @author Thomas Hallgren
 */
public class OPMLHandler extends ElementHandler implements ChildPoppedListener
{
	public static final String TAG = OPML.TAG;

	private final HeadHandler m_headHandler = new HeadHandler(this);
	private final BodyHandler m_bodyHandler = new BodyHandler(this);

	private String m_version;
	private Head m_head;
	private Body m_body;

	OPMLHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_version = getStringValue(attrs, OPML.ATTR_VERSION);
		m_head = null;
		m_body = null;
	}

	public OPML getOPML()
	{
		return new OPML(m_version, m_head, m_body);
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

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_bodyHandler)
			m_body = m_bodyHandler.getBody();
		else if(child == m_headHandler)
			m_head = m_headHandler.getHead();
	}
}
