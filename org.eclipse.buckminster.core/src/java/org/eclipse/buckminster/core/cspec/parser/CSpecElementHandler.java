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
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.builder.NamedElementBuilder;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class CSpecElementHandler extends ExtensionAwareHandler implements IAttributeBuilderSupport, ICSpecBuilderSupport
{
	private NamedElementBuilder m_builder;

	protected CSpecElementHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public AttributeBuilder getAttributeBuilder()
	{
		return ((IAttributeBuilderSupport)this.getParentHandler()).getAttributeBuilder();
	}

	public NamedElementBuilder getBuilder()
	{
		return m_builder;
	}

	public CSpecBuilder getCSpecBuilder()
	{
		return ((ICSpecBuilderSupport)this.getParentHandler()).getCSpecBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_builder = this.createBuilder();
		m_builder.setName(this.getNameAttribute(attrs));
	}

	protected abstract NamedElementBuilder createBuilder();

	protected CSpecElementBuilder getCSpecElementBuilder()
	{
		return (CSpecElementBuilder)this.getBuilder();
	}
	
	protected String getNameAttribute(Attributes attrs)
	throws SAXException
	{
		return this.getStringValue(attrs, NamedElement.ATTR_NAME);
	}
}
