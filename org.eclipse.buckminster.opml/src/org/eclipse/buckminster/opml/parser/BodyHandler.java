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
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * SAX Parser for the OPML Body element
 *
 * @author Thomas Hallgren
 */
class BodyHandler extends ElementHandler implements ChildPoppedListener
{
	public static final String TAG = Body.TAG;

	private OutlineHandler m_outlineHandler;

	private BodyBuilder m_bodyBuilder;

	BodyHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		if(m_bodyBuilder == null)
			m_bodyBuilder = createBodyBuilder();
		else
			m_bodyBuilder.clear();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Outline.TAG.equals(localName))
		{
			if(m_outlineHandler == null)
				 m_outlineHandler = new OutlineHandler(this);
			ch = m_outlineHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_outlineHandler)
			m_bodyBuilder.addOutline((Outline)m_outlineHandler.getBody());
	}

	public Body getBody()
	{
		return new Body(m_bodyBuilder);
	}
	
	protected BodyBuilder getBodyBuilder()
	{
		return m_bodyBuilder;
	}
	
	protected BodyBuilder createBodyBuilder()
	{
		return new BodyBuilder();
	}
}
