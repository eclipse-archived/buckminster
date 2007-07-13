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

import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class Prerequisite extends NamedElement implements ISaxableElement, IAttributeFilter
{
	public static final String ATTR_ALIAS = "alias";

	public static final String ATTR_COMPONENT = "component";

	public static final String ATTR_CONTRIBUTOR = "contributor";

	public static final String ATTR_EXCLUDE_PATTERN = "excludePattern";

	public static final String ATTR_INCLUDE_PATTERN = "includePattern";

	public static final String ATTR_OPTIONAL = "optional";

	public static final String TAG = "attribute";

	private final String m_alias;
	private final String m_componentName;
	private final boolean m_contributor;
	private final Pattern m_excludePattern;
	private final Pattern m_includePattern;
	private final boolean m_optional;

	public Prerequisite(PrerequisiteBuilder bld)
	{
		super(bld.getName());
		m_alias = bld.getAlias();
		m_contributor = bld.isContributor();
		m_optional = bld.isOptional();
		m_componentName = bld.getComponent();
		m_excludePattern = bld.getExcludePattern();
		m_includePattern = bld.getIncludePattern();
	}

	public final String getAlias()
	{
		return m_alias;
	}

	public final String getAttribute()
	{
		return this.getName();
	}

	public final String getComponentName()
	{
		return m_componentName;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Pattern getExcludePattern()
	{
		return m_excludePattern;
	}

	public Pattern getIncludePattern()
	{
		return m_includePattern;
	}

	public Attribute getReferencedAttribute(CSpec ownerCSpec, IModelCache ctx) throws CoreException
	{
		return ownerCSpec.getReferencedAttribute(m_componentName, this.getName(), ctx);
	}

	public boolean isContributor()
	{
		return m_contributor;
	}

	public boolean isExternal()
	{
		return m_componentName != null;
	}

	public boolean isFilter()
	{
		return m_excludePattern != null || m_includePattern != null;
	}

	public boolean isMatch(String component, String attribute)
	{
		CharSequence tmp;
		if(attribute == null)
		{
			if(component == null)
				return false;
			tmp = component;
		}
		else
		{
			StringBuilder bld = new StringBuilder();
			if(component != null)
				bld.append(component);
			bld.append('#');
			bld.append(attribute);
			tmp = bld;
		}
		if(m_excludePattern != null)
		{
			Matcher m = m_excludePattern.matcher(tmp);
			if(m.matches())
				return false;
		}
		if(m_includePattern != null)
		{
			Matcher m = m_includePattern.matcher(tmp);
			if(!m.matches())
				return false;
		}
		return true;
	}

	public final boolean isOptional()
	{
		return m_optional;
	}

	@Override
	public final String toString()
	{
		if(m_componentName == null)
			return this.getName();

		return m_componentName + '#' + this.getAttribute();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_alias != null)
			Utils.addAttribute(attrs, ATTR_ALIAS, m_alias);
		if(!m_contributor)
			Utils.addAttribute(attrs, ATTR_CONTRIBUTOR, "false");
		if(m_excludePattern != null)
			Utils.addAttribute(attrs, ATTR_EXCLUDE_PATTERN, m_excludePattern.toString());
		if(m_includePattern != null)
			Utils.addAttribute(attrs, ATTR_INCLUDE_PATTERN, m_includePattern.toString());
		if(m_optional)
			Utils.addAttribute(attrs, ATTR_OPTIONAL, "true");
		if(m_componentName != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, m_componentName);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
	}
}
