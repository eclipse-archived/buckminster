/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.common.parser;

import java.io.InputStream;
import java.util.Collections;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class DocumentationParser extends AbstractParser<Documentation> implements ChildPoppedListener
{
	private Documentation m_documentation;

	public DocumentationParser() throws CoreException
	{
		super(Collections.<ParserFactory.ParserExtension> emptyList(), new String[] { XMLConstants.XHTML_NS,
				XMLConstants.XML_NS }, new String[] { XMLConstants.XHTML_RESOURCE, XMLConstants.XML_RESOURCE }, true);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		m_documentation = ((DocumentationHandler)child).createDocumentation();
	}

	public Documentation parse(String systemID, InputStream input) throws CoreException
	{
		this.parseInput(systemID, input);
		return m_documentation;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(Documentation.XHTML_TAG.equals(localName))
		{
			DocumentationHandler rmh = new DocumentationHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}
}
