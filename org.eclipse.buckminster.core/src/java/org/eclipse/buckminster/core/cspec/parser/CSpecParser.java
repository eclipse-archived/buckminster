/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class CSpecParser extends AbstractParser<CSpec> implements ChildPoppedListener
{
	private CSpec m_cSpec;

	public CSpecParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating) throws CoreException
	{
		super(parserExtensions, new String[] { XMLConstants.BM_COMMON_NS, XMLConstants.BM_CSPEC_NS,
				XMLConstants.XHTML_NS, XMLConstants.XML_NS }, new String[] { XMLConstants.BM_COMMON_RESOURCE,
				XMLConstants.BM_CSPEC_RESOURCE, XMLConstants.XHTML_RESOURCE, XMLConstants.XML_RESOURCE }, validating);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		try
		{
			m_cSpec = ((CSpecHandler)child).getCSpec();
			m_cSpec.verifyConsistency();
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	public CSpec parse(String systemId, InputStream input) throws CoreException
	{
		this.parseInput(systemId, input);
		return m_cSpec;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(CSpecHandler.TAG.equals(localName))
		{
			CSpecHandler rmh = new CSpecHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}
}
