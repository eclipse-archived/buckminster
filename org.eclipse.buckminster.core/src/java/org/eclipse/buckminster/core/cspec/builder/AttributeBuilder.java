/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AttributeBuilder extends CSpecElementBuilder implements IAttribute
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

	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder)
	{
		return specBuilder == getCSpecBuilder()
				? this
				: new AttributeBuilder(specBuilder);
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public Map<String, String> getInstallerHints()
	{
		return Collections.emptyMap();
	}

	public IPath getPrerequisiteRebase()
	{
		return null;
	}

	public List<? extends IPrerequisite> getPrerequisites()
	{
		return Collections.emptyList();
	}

	public String getQualifiedName()
	{
		return getCSpecBuilder().getComponentIdentifier().toString() + '#' + getName();
	}

	public void initFrom(IAttribute attribute)
	{
		super.initFrom(attribute.getName());
		m_documentation = attribute.getDocumentation();
		m_filter = attribute.getFilter();
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
}
