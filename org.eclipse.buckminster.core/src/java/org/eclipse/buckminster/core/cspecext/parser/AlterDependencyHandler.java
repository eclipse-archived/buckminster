/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterDependencyBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class AlterDependencyHandler extends AlterHandler
{
	private final ComponentRequestHandler m_baseHandler;

	private AlterDependencyBuilder m_builder;

	AlterDependencyHandler(AbstractHandler parent)
	{
		super(parent);
		m_baseHandler = new ComponentRequestHandler(parent, new ComponentRequestBuilder());
	}

	public void childPopped(ChildHandler child) throws SAXException
	{

		if(m_baseHandler instanceof ChildPoppedListener)
			((ChildPoppedListener)m_baseHandler).childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		return m_baseHandler.createHandler(uri, localName, attrs);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_baseHandler.handleAttributes(attrs);
		m_builder = new AlterDependencyBuilder(m_baseHandler.getBuilder());
	}

	AlterDependency getAlterDependency() throws SAXException
	{
		try
		{
			return m_builder.createAlterDependency();
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	AlterDependencyBuilder getBuilder()
	{
		return m_builder;
	}
}
