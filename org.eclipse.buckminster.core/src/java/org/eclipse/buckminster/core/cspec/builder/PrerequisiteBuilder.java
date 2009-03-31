/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.osgi.filter.Filter;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteBuilder extends CSpecElementBuilder implements IPrerequisite
{
	private String m_alias;

	private final AttributeBuilder m_attributeBuilder;

	private String m_component;

	private String m_componentType;

	private boolean m_contributor = true;

	private Pattern m_excludePattern;

	private Pattern m_includePattern;

	private Filter m_filter;

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
		m_componentType = null;
		m_contributor = true;
		m_optional = false;
		m_excludePattern = null;
		m_includePattern = null;
		m_filter = null;
	}

	public Prerequisite createPrerequisite()
	{
		return new Prerequisite(this);
	}

	public String getAlias()
	{
		return m_alias;
	}

	public String getAttribute()
	{
		return getAttributeBuilder().getName();
	}

	public AttributeBuilder getAttributeBuilder()
	{
		return m_attributeBuilder;
	}

	public String getComponentName()
	{
		return m_component;
	}

	public String getComponentType()
	{
		return m_componentType;
	}

	public Pattern getExcludePattern()
	{
		return m_excludePattern;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public Pattern getIncludePattern()
	{
		return m_includePattern;
	}

	public void initFrom(IPrerequisite prerequisite)
	{
		super.initFrom(prerequisite.getName());
		m_alias = prerequisite.getAlias();
		m_component = prerequisite.getComponentName();
		m_componentType = prerequisite.getComponentType();
		m_optional = prerequisite.isOptional();
		m_contributor = prerequisite.isContributor();
		m_excludePattern = prerequisite.getExcludePattern();
		m_includePattern = prerequisite.getIncludePattern();
		m_filter = prerequisite.getFilter();
	}

	public boolean isContributor()
	{
		return m_contributor;
	}

	public boolean isExternal()
	{
		return m_component != null;
	}

	public boolean isMatch(String component, String attribute)
	{
		return Prerequisite.isMatch(component, attribute, m_excludePattern, m_includePattern);
	}

	public boolean isOptional()
	{
		return m_optional;
	}

	public void setAlias(String alias)
	{
		m_alias = alias;
	}

	public void setComponentName(String component)
	{
		m_component = component;
	}

	public void setComponentType(String type)
	{
		m_componentType = type;
	}

	public void setContributor(boolean contributor)
	{
		m_contributor = contributor;
	}

	public void setExcludePattern(Pattern excludePattern)
	{
		m_excludePattern = excludePattern;
	}

	public void setFilter(Filter filter)
	{
		m_filter = filter;
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
			return getName();

		StringBuilder bld = new StringBuilder();
		bld.append(m_component);
		if(m_componentType != null)
		{
			bld.append(':');
			bld.append(m_componentType);
		}
		bld.append('#');
		bld.append(getName());
		return bld.toString();
	}

	void finalWrapUp(Map<String, ComponentRequestBuilder> dependencies)
	{
		if(m_componentType != null && dependencies.containsKey(m_component))
			m_componentType = null;
	}
}
