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
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public class ResourceMapParser extends AbstractParser<ResourceMap>
{
	private ResourceMap m_resourceMap;

	public ResourceMapParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating)
	throws SAXException
	{
		super(parserExtensions, new String[]
   		{
			XMLConstants.XHTML_NS,
			XMLConstants.XML_NS,
   			XMLConstants.BM_COMMON_NS,
			XMLConstants.BM_RMAP_NS,
   		}, new String[]
   		{
			XMLConstants.XHTML_RESOURCE,
			XMLConstants.XML_RESOURCE,
			XMLConstants.BM_COMMON_RESOURCE,
			XMLConstants.BM_RMAP_RESOURCE,
   		}, validating);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
	throws SAXException
	{
		if("rmap".equals(localName))
		{
			String type = attrs.getValue(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type");
			ResourceMapHandler rmh = this.createContentHandler(this, ResourceMapHandler.class, uri, type);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public ResourceMap parse(String systemID, InputStream input)
	throws SAXException
	{
		this.parseInput(systemID, input);
		return m_resourceMap;
	}

	class ResourceMapSAXParser extends SAXParserWrapper implements ISAXParser<ResourceMap>
	{
		public ResourceMap getResult()
		{
			return m_resourceMap;
		}
	}

	public ISAXParser<ResourceMap> getSAXParser()
	{
		return new ResourceMapSAXParser();
	}

	void setResourceMap(ResourceMap resourceMap)
	{
		m_resourceMap = resourceMap;
	}
}

