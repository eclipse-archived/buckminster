/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.osgi.framework.Filter;

/**
 * @author Thomas Hallgren
 */
public class AttributeBuilder extends CSpecElementBuilder
{
	private Documentation m_documentation;

	private Filter m_filter = null;

	AttributeBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_documentation = null;
		m_filter = null;
	}

	public Attribute createAttribute()
	{
		return new Attribute(this);
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public boolean isPublic()
	{
		return true;
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public void setFilter(Filter filter)
	{
		m_filter = filter;
	}

	@Override
	public void initFrom(NamedElement namedElement)
	{
		Attribute attribute = (Attribute)namedElement;
		super.initFrom(attribute);
		m_documentation = attribute.getDocumentation();
		m_filter = attribute.getFilter();
	}
}
