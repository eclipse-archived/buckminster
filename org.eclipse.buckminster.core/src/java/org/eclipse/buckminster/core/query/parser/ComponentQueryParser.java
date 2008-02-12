/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.parser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ComponentQueryParser extends AbstractParser<ComponentQuery> implements ChildPoppedListener
{
	private ComponentQuery m_componentQuery;
	private URL m_contextURL;

	public ComponentQueryParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating)
	throws CoreException
	{
		super(parserExtensions, new String[]
 		{
			XMLConstants.XHTML_NS,
			XMLConstants.XML_NS,
			XMLConstants.BM_COMMON_NS,
			XMLConstants.BM_CSPEC_NS,
			XMLConstants.BM_CQUERY_NS
 		}, new String[]
  		{
			XMLConstants.XHTML_RESOURCE,
			XMLConstants.XML_RESOURCE,
			XMLConstants.BM_COMMON_RESOURCE,
			XMLConstants.BM_CSPEC_RESOURCE,
			XMLConstants.BM_CQUERY_RESOURCE
  		}, validating);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(ComponentQuery.TAG.equals(localName))
		{
			ComponentQueryHandler rmh = new ComponentQueryHandler(this, m_contextURL);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public ComponentQuery parse(String systemId, InputStream input) throws CoreException
	{
		try
		{
			m_contextURL = URLUtils.normalizeToURL(systemId);
		}
		catch(MalformedURLException e)
		{
			m_contextURL = null;
		}
		this.parseInput(systemId, input);
		return m_componentQuery;
	}

	class ComponentQuerySAXParser extends SAXParserWrapper implements ISAXParser<ComponentQuery>
	{
		public ComponentQuery getResult()
		{
			return m_componentQuery;
		}
	}

	public ISAXParser<ComponentQuery> getSAXParser()
	{
		return new ComponentQuerySAXParser();
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_componentQuery = ((ComponentQueryHandler)child).getComponentQuery();
	}
}

