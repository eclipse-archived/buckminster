/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import java.io.InputStream;
import java.util.Collections;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.europatools.EuropaTools;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class SiteContributionParser extends AbstractParser<SiteContribution>
{
	private SiteContribution m_siteContribution;

	public SiteContributionParser()
	throws SAXException
	{
		super(Collections.<ParserFactory.ParserExtension>emptyList(), new String[]
   		{
			XMLConstants.XHTML_NS,
			XMLConstants.XML_NS,
   			XMLConstants.BM_COMMON_NS,
			EuropaTools.BM_SITE_CONTRIBUTION_NS
   		}, new String[]
   		{
			XMLConstants.XHTML_RESOURCE,
			XMLConstants.XML_RESOURCE,
			XMLConstants.BM_COMMON_RESOURCE,
			EuropaTools.BM_SITE_CONTRIBUTION_RESOURCE
   		}, true);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
	throws SAXException
	{
		if(SiteContribution.TAG.equals(localName))
		{
			String type = attrs.getValue(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type");
			SiteContributionHandler rmh = this.createContentHandler(this, SiteContributionHandler.class, uri, type);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public SiteContribution parse(String systemID, InputStream input)
	throws SAXException
	{
		this.parseInput(systemID, input);
		return m_siteContribution;
	}

	class SiteContributionSAXParser extends SAXParserWrapper implements ISAXParser<SiteContribution>
	{
		public SiteContribution getResult()
		{
			return m_siteContribution;
		}
	}

	public ISAXParser<SiteContribution> getSAXParser()
	{
		return new SiteContributionSAXParser();
	}

	void setSiteContribution(SiteContribution siteContribution)
	{
		m_siteContribution = siteContribution;
	}
}

