/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public abstract class AttributeHandler extends CSpecElementHandler implements ChildPoppedListener
{
	private final boolean m_public;

	private DocumentationHandler m_documentationHandler;

	private PropertyManagerHandler m_installerHintsHandler;

	public AttributeHandler(AbstractHandler parent, boolean publ)
	{
		super(parent);
		m_public = publ;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_documentationHandler)
			this.getAttributeBuilder().setDocumentation(m_documentationHandler.createDocumentation());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Attribute.ELEM_INSTALLER_HINTS.equals(localName))
		{
			if(m_installerHintsHandler == null)
				m_installerHintsHandler = new PropertyManagerHandler(this, Attribute.ELEM_INSTALLER_HINTS)
				{
					@Override
					public ExpandingProperties getProperties()
					{
						return ((AttributeBuilder)getBuilder()).getInstallerHintsForAdd();
					}
				};
			ch = m_installerHintsHandler;
		}
		else if(DocumentationHandler.TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final AttributeBuilder getAttributeBuilder()
	{
		return (AttributeBuilder)getBuilder();
	}

	@Override
	protected CSpecElementBuilder createBuilder()
	{
		AttributeBuilder bld = this.createAttributeBuilder();
		bld.setPublic(m_public);
		return bld;
	}

	protected abstract AttributeBuilder createAttributeBuilder();
}
