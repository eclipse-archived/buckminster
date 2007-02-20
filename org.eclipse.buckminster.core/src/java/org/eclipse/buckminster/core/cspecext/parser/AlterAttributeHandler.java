/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.parser.AttributeHandler;
import org.eclipse.buckminster.core.cspec.parser.IAttributeBuilderSupport;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class AlterAttributeHandler extends AlterHandler implements IAttributeBuilderSupport
{
	private final AttributeHandler m_baseHandler;

	private final AlterPropertiesHandler m_alterInstallationHints = new AlterPropertiesHandler(this, AlterAttribute.ELEM_ALTER_INSTALLER_HINTS)
	{
		@Override
		public ExpandingProperties getProperties()
		{
			return m_builder.getAlteredHints();
		}

		@Override
		protected void addRemovedProperty(String key) throws SAXException
		{
			m_builder.addRemovedInstallerHint(key);
		}		
	};

	private AlterAttributeBuilder m_builder;

	AlterAttributeHandler(AbstractHandler parent, AttributeHandler baseHandler)
	{
		super(parent);
		m_baseHandler = baseHandler;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		((ChildPoppedListener)m_baseHandler).childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(m_alterInstallationHints.getTAG().equals(localName))
			ch = m_alterInstallationHints;
		else
			ch = m_baseHandler.createHandler(uri, localName, attrs);
		return ch;
	}

	public AttributeBuilder getAttributeBuilder()
	{
		return m_baseHandler.getAttributeBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_baseHandler.handleAttributes(attrs);
		m_builder = this.createAlterAttributeBuilder(m_baseHandler.getAttributeBuilder());
		m_builder.setCSpecName(this.getCSpecExtensionName());
	}

	abstract AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder);

	AlterAttribute<? extends Attribute> getAlterAttribute()
	{
		return m_builder.createAlterAttribute();
	}

	AlterAttributeBuilder getBuilder()
	{
		return m_builder;
	}

	AttributeHandler getBaseHandler()
	{
		return m_baseHandler;
	}
}
