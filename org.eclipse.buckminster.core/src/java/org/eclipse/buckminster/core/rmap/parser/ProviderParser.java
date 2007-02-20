/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public class ProviderParser extends AbstractParser<Provider> implements ChildPoppedListener
{
	private Provider m_provider;

	public ProviderParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating)
	throws SAXException
	{
		super(parserExtensions, new String[]
		{
			XMLConstants.XHTML_NS,
			XMLConstants.XML_NS,
			XMLConstants.BM_COMMON_NS,
			XMLConstants.BM_RMAP_NS
		}, new String[]
		{
			XMLConstants.XHTML_RESOURCE,
			XMLConstants.XML_RESOURCE,
			XMLConstants.BM_COMMON_RESOURCE,
			XMLConstants.BM_RMAP_RESOURCE
		}, validating);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
	throws SAXException
	{
		if(ProviderHandler.TAG.equals(localName))
		{
			String type = attrs.getValue(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type");
			ProviderHandler rmh = createContentHandler(this, ProviderHandler.class, uri, type);
			pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public Provider parse(String systemID, InputStream input) throws SAXException
	{
		parseInput(systemID, input);
		return m_provider;
	}

	class ProviderSAXParser extends SAXParserWrapper implements ISAXParser<Provider>
	{
		public Provider getResult()
		{
			return m_provider;
		}
	}

	public ISAXParser<Provider> getSAXParser()
	{
		return new ProviderSAXParser();
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_provider = ((ProviderHandler)child).getProvider();
	}
}

