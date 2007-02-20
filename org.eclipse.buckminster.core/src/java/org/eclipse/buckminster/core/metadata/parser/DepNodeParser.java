/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class DepNodeParser extends MetaDataParser<DepNode>
{
	private DepNode m_resolvedNode;

	public DepNodeParser(List<ParserFactory.ParserExtension> parserExtensions)
	throws SAXException
	{
		super(parserExtensions);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(ResolvedNodeHandler.TAG.equals(localName))
		{
			ResolvedNodeHandler rmh = new ResolvedNodeHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else if(UnresolvedNodeHandler.TAG.equals(localName))
		{
			UnresolvedNodeHandler rmh = new UnresolvedNodeHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else if(GeneratorNodeHandler.TAG.equals(localName))
		{
			GeneratorNodeHandler rmh = new GeneratorNodeHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else if(BillOfMaterialsHandler.TAG.equals(localName))
		{
			BillOfMaterialsHandler rmh = new BillOfMaterialsHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public DepNode parse(String systemID, InputStream input) throws SAXException
	{
		this.parseInput(systemID, input);
		return m_resolvedNode;
	}

	class ResolvedNodeSAXParser extends SAXParserWrapper implements ISAXParser<DepNode>
	{
		public DepNode getResult()
		{
			return m_resolvedNode;
		}
	}

	public ISAXParser<DepNode> getSAXParser()
	{
		return new ResolvedNodeSAXParser();
	}

	public void childPopped(ChildHandler child)
	throws SAXException
	{
		m_resolvedNode = ((DepNodeHandler)child).getDepNode();
	}
}

