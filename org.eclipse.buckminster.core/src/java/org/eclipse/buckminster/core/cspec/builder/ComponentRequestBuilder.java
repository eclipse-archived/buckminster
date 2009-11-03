/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ComponentRequestBuilder implements IComponentRequest
{
	private String m_name;

	private String m_componentType;

	private VersionRange m_versionRange;

	private Filter m_filter;

	public void clear()
	{
		m_name = null;
		m_componentType = null;
		m_versionRange = null;
		m_filter = null;
	}

	public ComponentRequest createComponentRequest()
	{
		return new ComponentRequest(this);
	}

	public boolean designates(IComponentIdentifier id)
	{
		return Trivial.equalsAllowNull(getName(), id.getName())
				&& (m_componentType == null || m_componentType.equals(id.getComponentTypeID()))
				&& (m_versionRange == null || m_versionRange.isIncluded(id.getVersion()));
	}

	public String getComponentTypeID()
	{
		return m_componentType;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public String getName()
	{
		return m_name;
	}

	public VersionRange getVersionRange()
	{
		return m_versionRange;
	}

	public void initFrom(IComponentRequest request)
	{
		m_name = request.getName();
		m_componentType = request.getComponentTypeID();
		m_versionRange = request.getVersionRange();
		m_filter = request.getFilter();
	}

	public void setComponentTypeID(String componentType)
	{
		m_componentType = componentType;
	}

	public void setFilter(Filter filter)
	{
		m_filter = filter;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public void setVersionRange(VersionRange versionRange)
	{
		m_versionRange = versionRange;
	}
}
