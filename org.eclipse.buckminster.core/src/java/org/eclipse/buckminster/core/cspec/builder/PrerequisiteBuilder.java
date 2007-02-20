/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.Prerequisite;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteBuilder extends CSpecElementBuilder
{
	private final AttributeBuilder m_attributeBuilder;

	private String m_alias;
	private String m_component;
	private boolean m_contributor = true;
	private boolean m_optional = false;

	PrerequisiteBuilder(AttributeBuilder attributeBuilder)
	{
		super(attributeBuilder.getCSpecBuilder());
		m_attributeBuilder = attributeBuilder;
	}

	@Override
	public void clear()
	{
		super.clear();
		m_alias = null;
		m_component = null;
		m_contributor = true;
		m_optional = false;
	}

	public String getAlias()
	{
		return m_alias;
	}

	public AttributeBuilder getAttributeBuilder()
	{
		return m_attributeBuilder;
	}

	public String getComponent()
	{
		return m_component;
	}

	public boolean isOptional()
	{
		return m_optional;
	}

	public boolean isContributor()
	{
		return m_contributor;
	}

	public Prerequisite createPrerequisite()
	{
		return new Prerequisite(m_component, this.getName(), m_alias, m_optional, m_contributor);
	}

	public void initFrom(Prerequisite prerequisite)
	{
		super.initFrom(prerequisite);
		m_alias = prerequisite.getAlias();
		m_component = prerequisite.getComponentName();
		m_optional = prerequisite.isOptional();
		m_contributor = prerequisite.isContributor();
	}

	public void setAlias(String alias)
	{
		m_alias = alias;
	}

	public void setComponent(String component)
	{
		m_component = component;
	}

	public void setContributor(boolean contributor)
	{
		m_contributor = contributor;
	}

	public void setOptional(boolean optional)
	{
		m_optional = optional;
	}

	@Override
	public String toString()
	{
		if(m_component == null)
			return this.getName();
		return m_component + '.' + this.getName();
	}
}
