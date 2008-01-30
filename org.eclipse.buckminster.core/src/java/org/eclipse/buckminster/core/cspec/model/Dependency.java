/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Filter;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A ComponentRequest is part of a requirement. All referenced components must be available in a
 * workspace for a requirment to be fulfilled. A component can be further qualified using target
 * references in cases when only a part of the component is needed. The ComponentRequest uses a
 * singleton pattern and is optimized for use as key in a Map or Set.
 * @author thhal
 */
public class Dependency extends ComponentRequest
{
	@SuppressWarnings("hiding")
	public static final String TAG = "component";

	static public final String ATTR_FILTER = "filter";

	private final Filter m_filter;

	public Dependency(DependencyBuilder bld)
	{
		super(bld.getName(), bld.getComponentTypeID(), bld.getVersionDesignator());
		m_filter = bld.getFilter();
	}

	public Dependency(String name, String componentType, String versionDesignatorStr, String versionTypeId, Filter filter)
	throws CoreException
	{
		super(name, componentType, versionDesignatorStr, versionTypeId);
		m_filter = filter;
	}

	/**
	 * Returns true if this component reference is equal to obj with respect to name,
	 * versionSelector, and match rule.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		return super.equals(o)
			&& Trivial.equalsAllowNull(m_filter, ((Dependency)o).m_filter);
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public boolean isEnabled(Map<String, String> properties)
	{
		return m_filter == null || FilterUtils.isMatch(m_filter, properties);
	}

	@Override
	public void appendViewName(StringBuilder bld)
	{
		super.appendViewName(bld);
		if(m_filter != null)
			bld.append(m_filter);
	}

	/**
	 * Returns the hashCode for this component request.
	 */
	@Override
	public int hashCode()
	{
		int hash = super.hashCode();
		return 31 * hash + (m_filter == null ? 0 : m_filter.hashCode());
	}
	
	@Override
	public ComponentName toPureComponentName()
	{
		return new ComponentName(this);
	}

	@Override
	public void toString(StringBuilder bld)
	{
		super.toString(bld);
		if(m_filter != null)
			bld.append(m_filter);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}
}
