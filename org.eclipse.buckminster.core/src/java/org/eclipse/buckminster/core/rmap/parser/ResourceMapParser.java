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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ResourceMapParser extends AbstractParser<ResourceMap>
{
	private ResourceMap m_resourceMap;

	private URL m_contextURL;

	public ResourceMapParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating)
			throws CoreException
	{
		super(parserExtensions, new String[] { XMLConstants.XHTML_NS, XMLConstants.XML_NS, XMLConstants.BM_COMMON_NS,
				XMLConstants.BM_RMAP_NS, }, new String[] { XMLConstants.XHTML_RESOURCE, XMLConstants.XML_RESOURCE,
				XMLConstants.BM_COMMON_RESOURCE, XMLConstants.BM_RMAP_RESOURCE, }, validating);
	}

	public ResourceMap parse(String systemID, InputStream input) throws CoreException
	{
		try
		{
			m_contextURL = URLUtils.normalizeToURL(systemID);
		}
		catch(MalformedURLException e)
		{
			m_contextURL = null;
		}
		this.parseInput(systemID, input);
		return m_resourceMap;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if("rmap".equals(localName)) //$NON-NLS-1$
		{
			String type = attrs.getValue(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type"); //$NON-NLS-1$
			ResourceMapHandler rmh = this.createContentHandler(this, ResourceMapHandler.class, uri, type);
			rmh.setContextURL(m_contextURL);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	void setResourceMap(ResourceMap resourceMap)
	{
		m_resourceMap = resourceMap;
	}
}
