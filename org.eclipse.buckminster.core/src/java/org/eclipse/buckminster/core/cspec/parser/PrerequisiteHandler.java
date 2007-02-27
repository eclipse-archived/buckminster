/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.NamedElementBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteHandler extends CSpecElementHandler
{
	public static final String TAG = Prerequisite.TAG;

	private String m_component;

	public PrerequisiteHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_component = null;
		super.handleAttributes(attrs);
		PrerequisiteBuilder builder = (PrerequisiteBuilder)this.getBuilder();
		if(m_component == null)
			m_component = getOptionalStringValue(attrs, Prerequisite.ATTR_COMPONENT);
		builder.setComponent(m_component);
		builder.setContributor(getOptionalBooleanValue(attrs, Prerequisite.ATTR_CONTRIBUTOR, true));
		builder.setOptional(getOptionalBooleanValue(attrs, Prerequisite.ATTR_OPTIONAL, false));
		builder.setAlias(getOptionalStringValue(attrs, Prerequisite.ATTR_ALIAS));
	}

	@Override
	protected NamedElementBuilder createBuilder()
	{
		return this.getAttributeBuilder().createPrerequisiteBuilder();
	}
}
