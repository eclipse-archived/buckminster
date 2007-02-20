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

import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.parser.ISAXParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBindingParser extends MetaDataParser<WorkspaceBinding>
{
	public WorkspaceBindingParser(List<ParserFactory.ParserExtension> parserExtensions)
	throws SAXException
	{
		super(parserExtensions);
	}

	private WorkspaceBinding m_workspaceBinding;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
	{
		if(WorkspaceBindingHandler.TAG.equals(localName))
		{
			WorkspaceBindingHandler rmh = new WorkspaceBindingHandler(this);
			this.pushHandler(rmh, attrs);
		}
		else
			super.startElement(uri, localName, qName, attrs);
	}

	public WorkspaceBinding parse(String systemID, InputStream input) throws SAXException
	{
		this.parseInput(systemID, input);
		return m_workspaceBinding;
	}

	class WorkspaceBindingSAXParser extends SAXParserWrapper implements ISAXParser<WorkspaceBinding>
	{
		public WorkspaceBinding getResult()
		{
			return m_workspaceBinding;
		}
	}

	public ISAXParser<WorkspaceBinding> getSAXParser()
	{
		return new WorkspaceBindingSAXParser();
	}

	public void childPopped(ChildHandler child)
	throws SAXException
	{
		m_workspaceBinding = ((WorkspaceBindingHandler)child).getWorkspaceBinding();
	}
}

