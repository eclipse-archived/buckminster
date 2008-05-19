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
import java.net.URL;

import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.TopHandler;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * Parser for OPML
 * @author Thomas Hallgren
 */
public class OPMLParser extends TopHandler implements ChildPoppedListener, ErrorHandler
{
	private final URL m_schemaURL;

	private OPMLBuilder m_opml;

	public OPMLParser(boolean validating) throws SAXException
	{
		super(Utils.createXMLReader(validating, false));
		XMLReader reader = getXMLReader();
		if(validating)
		{
			reader.setFeature("http://apache.org/xml/features/validation/schema", true);
			reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
		}

		setNamespaceAware(false);
		setErrorHandler(this);

		m_schemaURL = OPMLParser.class.getResource("/opml-2.0.xsd");
		if(m_schemaURL == null)
			throw new SAXException("Unable to find XMLSchema for opml");
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_opml = ((OPMLHandler)child).getOPML();
	}

	@Override
	public void error(SAXParseException e)
	throws SAXException
    {
		throw e;
    }

	public OPML parseInput(String systemId, InputStream input) throws SAXException, IOException
	{
		if(!(input instanceof BufferedInputStream || input instanceof ByteArrayInputStream))
			input = new BufferedInputStream(input);
		InputSource source = new InputSource(input);
		if(systemId != null)
			source.setSystemId(systemId);
		XMLReader reader = getXMLReader();
		try
		{
			reader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", m_schemaURL.toString());
			reader.parse(source);
		}
		finally
		{
			getXMLReader().setContentHandler(this);
		}
		return new OPML(m_opml);
	}

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
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

	@Override
	public void warning(SAXParseException e)
	throws SAXException
    {
		throw e;
    }
}
