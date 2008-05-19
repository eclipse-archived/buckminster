/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import org.eclipse.buckminster.opml.builder.BodyBuilder;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * SAX Parser for the OPML Body element
 *
 * @author Thomas Hallgren
 */
class BodyHandler extends ElementHandler
{
	public static final String TAG = Body.TAG;

	private final BodyBuilder m_bodyBuilder;

	BodyHandler(AbstractHandler parent, BodyBuilder body)
	{
		super(parent);
		m_bodyBuilder = body;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_bodyBuilder.clear();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Outline.TAG.equals(localName))
			ch = new OutlineHandler(this, m_bodyBuilder.addOutline());
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
	
	protected BodyBuilder getBodyBuilder()
	{
		return m_bodyBuilder;
	}
}
