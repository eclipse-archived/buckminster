/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttributeHandler extends AttributeHandler
{
	private final boolean m_public;

	private PropertyManagerHandler m_installerHintsHandler;

	public TopLevelAttributeHandler(AbstractHandler parent, boolean publ)
	{
		super(parent);
		m_public = publ;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(TopLevelAttribute.ELEM_INSTALLER_HINTS.equals(localName))
		{
			if(m_installerHintsHandler == null)
				m_installerHintsHandler = new PropertyManagerHandler(this, TopLevelAttribute.ELEM_INSTALLER_HINTS)
				{
					@Override
					public ExpandingProperties getProperties()
					{
						return ((TopLevelAttributeBuilder)getBuilder()).getInstallerHintsForAdd();
					}
				};
			ch = m_installerHintsHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public final TopLevelAttributeBuilder getAttributeBuilder()
	{
		return (TopLevelAttributeBuilder)getBuilder();
	}

	@Override
	protected CSpecElementBuilder createBuilder()
	{
		TopLevelAttributeBuilder bld = createAttributeBuilder();
		bld.setPublic(m_public);
		return bld;
	}

	protected abstract TopLevelAttributeBuilder createAttributeBuilder();
}
