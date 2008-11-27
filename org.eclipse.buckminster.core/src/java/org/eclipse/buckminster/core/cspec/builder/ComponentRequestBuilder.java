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
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Filter;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestBuilder implements IComponentRequest
{
	private String m_name;

	private String m_componentType;

	private IVersionDesignator m_versionDesignator;

	private Filter m_filter;

	public void clear()
	{
		m_name = null;
		m_componentType = null;
		m_versionDesignator = null;
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
				&& (m_versionDesignator == null || m_versionDesignator.designates(id.getVersion()));
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

	public IVersionDesignator getVersionDesignator()
	{
		return m_versionDesignator;
	}

	public String getVersionDesignatorString()
	{
		return m_versionDesignator == null
				? null
				: m_versionDesignator.toString();
	}

	public IVersionType getVersionType()
	{
		return m_versionDesignator == null
				? null
				: m_versionDesignator.getVersion().getType();
	}

	public void initFrom(IComponentRequest request)
	{
		m_name = request.getName();
		m_componentType = request.getComponentTypeID();
		m_versionDesignator = request.getVersionDesignator();
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

	public void setVersionDesignator(IVersionDesignator designator)
	{
		m_versionDesignator = designator;
	}

	public void setVersionDesignator(String designatorStr, String versionType) throws CoreException
	{
		if(designatorStr == null)
			m_versionDesignator = null;
		else
			m_versionDesignator = VersionFactory.createDesignator(versionType, designatorStr);
	}
}
