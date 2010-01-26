/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Prerequisite extends NamedElement implements IPrerequisite
{
	public static final String ATTR_ALIAS = "alias"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_CONTRIBUTOR = "contributor"; //$NON-NLS-1$

	public static final String ATTR_EXCLUDE_PATTERN = "excludePattern"; //$NON-NLS-1$

	public static final String ATTR_INCLUDE_PATTERN = "includePattern"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	public static final String TAG = "attribute"; //$NON-NLS-1$

	public static boolean isMatch(String component, String attribute, Pattern excludePattern, Pattern includePattern)
	{
		CharSequence tmp;
		if(attribute == null && component == null)
			return false;

		if(excludePattern == null && includePattern == null)
			return true;

		if(attribute == null)
			tmp = component;
		else
		{
			StringBuilder bld = new StringBuilder();
			if(component != null)
				bld.append(component);
			bld.append('#');
			bld.append(attribute);
			tmp = bld;
		}
		if(excludePattern != null)
		{
			Matcher m = excludePattern.matcher(tmp);
			if(m.matches())
				return false;
		}
		if(includePattern != null)
		{
			Matcher m = includePattern.matcher(tmp);
			if(!m.matches())
				return false;
		}
		return true;
	}

	private final String m_alias;

	private final String m_componentName;

	private final String m_componentType;

	private final boolean m_contributor;

	private final Pattern m_excludePattern;

	private final Pattern m_includePattern;

	private final Filter m_filter;

	public Prerequisite(PrerequisiteBuilder bld)
	{
		super(bld.getName());
		m_alias = bld.getAlias();
		m_contributor = bld.isContributor();
		m_componentName = bld.getComponentName();
		m_componentType = bld.getComponentType();
		m_excludePattern = bld.getExcludePattern();
		m_includePattern = bld.getIncludePattern();
		m_filter = bld.getFilter();
	}

	public final String getAlias()
	{
		return m_alias;
	}

	public final String getAttribute()
	{
		return getName();
	}

	public final String getComponentName()
	{
		return m_componentName;
	}

	public final String getComponentType()
	{
		return m_componentType;
	}

	public String getDefaultTag()
	{
		return TAG;
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

	public Attribute getReferencedAttribute(CSpec ownerCSpec, IModelCache ctx) throws CoreException
	{
		return (m_filter == null || m_filter.match(ctx.getProperties()))
				? ownerCSpec.getReferencedAttribute(m_componentName, m_componentType, getName(), ctx)
				: null;
	}

	public CSpec getReferencedCSpec(CSpec ownerCSpec, IModelCache ctx) throws CoreException
	{
		return (m_filter == null || m_filter.match(ctx.getProperties()))
				? ownerCSpec.getReferencedCSpec(m_componentName, m_componentType, ctx)
				: null;
	}

	public boolean isContributor()
	{
		return m_contributor;
	}

	public boolean isEnabled(IModelCache cache, CSpec cspec) throws CoreException
	{
		if(!(m_filter == null || m_filter.match(cache.getProperties())))
			return false;

		return isExternal()
				? (getReferencedAttribute(cspec, cache) != null)
				: cspec.getAttribute(getAttribute()).isEnabled(cache);
	}

	public boolean isExternal()
	{
		return m_componentName != null;
	}

	public boolean isMatch(String component, String attribute)
	{
		return isMatch(component, attribute, m_excludePattern, m_includePattern);
	}

	public boolean isPatternFilter()
	{
		return m_excludePattern != null || m_includePattern != null;
	}

	@Override
	public final String toString()
	{
		if(m_componentName == null)
			return getName();

		return m_componentName + '#' + getAttribute();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_alias != null)
			Utils.addAttribute(attrs, ATTR_ALIAS, m_alias);
		if(!m_contributor)
			Utils.addAttribute(attrs, ATTR_CONTRIBUTOR, "false"); //$NON-NLS-1$
		if(m_excludePattern != null)
			Utils.addAttribute(attrs, ATTR_EXCLUDE_PATTERN, m_excludePattern.toString());
		if(m_includePattern != null)
			Utils.addAttribute(attrs, ATTR_INCLUDE_PATTERN, m_includePattern.toString());
		if(m_componentName != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, m_componentName);
		if(m_componentType != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentType);
		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
	}
}
