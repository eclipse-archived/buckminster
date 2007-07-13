/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.model.Prerequisite;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteBuilder extends CSpecElementBuilder
{
	private String m_alias;

	private final AttributeBuilder m_attributeBuilder;
	private String m_component;
	private boolean m_contributor = true;
	private Pattern m_excludePattern;
	private Pattern m_includePattern;
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
		m_excludePattern = null;
		m_includePattern = null;
	}

	public Prerequisite createPrerequisite()
	{
		return new Prerequisite(this);
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

	public Pattern getExcludePattern()
	{
		return m_excludePattern;
	}

	public Pattern getIncludePattern()
	{
		return m_includePattern;
	}

	public void initFrom(Prerequisite prerequisite)
	{
		super.initFrom(prerequisite);
		m_alias = prerequisite.getAlias();
		m_component = prerequisite.getComponentName();
		m_optional = prerequisite.isOptional();
		m_contributor = prerequisite.isContributor();
		m_excludePattern = prerequisite.getExcludePattern();
		m_includePattern = prerequisite.getIncludePattern();
	}

	public boolean isContributor()
	{
		return m_contributor;
	}

	public boolean isOptional()
	{
		return m_optional;
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

	public void setExcludePattern(Pattern excludePattern)
	{
		m_excludePattern = excludePattern;
	}

	public void setIncludePattern(Pattern includePattern)
	{
		m_includePattern = includePattern;
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
