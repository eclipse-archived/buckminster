/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public abstract class AttributeHandler extends CSpecElementHandler implements ChildPoppedListener
{
	private DocumentationHandler m_documentationHandler;

	public AttributeHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_documentationHandler)
			((AttributeBuilder)getBuilder()).setDocumentation(m_documentationHandler.createDocumentation());
	}

	@Override
	protected CSpecElementBuilder createBuilder()
	{
		return getCSpecBuilder().createAttributeBuilder();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(DocumentationHandler.TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		String filterStr = getOptionalStringValue(attrs, Attribute.ATTR_FILTER);
		if(filterStr != null)
		{
			try
			{
				getAttributeBuilder().setFilter(FilterUtils.createFilter(filterStr));
			}
			catch(InvalidSyntaxException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
	}
}
