/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.parser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.TopHandler;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Parser for OPML
 * @author Thomas Hallgren
 */
public class OPMLParser extends TopHandler implements ChildPoppedListener
{
	private OPML m_opml;

	public OPMLParser(boolean validating, boolean withNamespace) throws SAXException
	{
		super(Utils.createXMLReader(validating, withNamespace));
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(OPML.TAG.equals(qName))
		{
			OPMLHandler rmh = new OPMLHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public OPML parse(String systemId, InputStream input) throws SAXException, IOException
	{
		if(!(input instanceof BufferedInputStream || input instanceof ByteArrayInputStream))
			input = new BufferedInputStream(input);
		InputSource source = new InputSource(input);
		if(systemId != null)
			source.setSystemId(systemId);
		getXMLReader().parse(source);
		return m_opml;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_opml = ((OPMLHandler)child).getOPML();
	}
}
