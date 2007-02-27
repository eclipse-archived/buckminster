/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
abstract class AttributesHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport
{
	private AttributeHandler m_publicHandler;
	private AttributeHandler m_privateHandler;

	AttributesHandler(AbstractHandler parent)
	{
		super(parent);
	}

	abstract AttributeHandler createAttributeHandler(boolean publ);

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(Attribute.PUBLIC_TAG.equals(localName))
		{
			if(m_publicHandler == null)
				m_publicHandler = this.createAttributeHandler(true);
			ch = m_publicHandler;
		}
		else if(Attribute.PRIVATE_TAG.equals(localName))
		{
			if(m_privateHandler == null)
				m_privateHandler = this.createAttributeHandler(false);
			ch = m_privateHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		this.addAttribute(((AttributeHandler)child).getAttributeBuilder());
	}

	final void addAttribute(AttributeBuilder attribute) throws SAXException
	{
		try
		{
			this.getCSpecBuilder().addAttribute(attribute);
		}
		catch(AttributeAlreadyDefinedException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	public CSpecBuilder getCSpecBuilder()
	{
		return ((ICSpecBuilderSupport)this.getParentHandler()).getCSpecBuilder();
	}
}
