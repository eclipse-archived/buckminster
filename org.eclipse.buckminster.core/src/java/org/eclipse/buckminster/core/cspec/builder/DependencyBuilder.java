/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.Dependency;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Filter;

/**
 * @author Thomas Hallgren
 */
public class DependencyBuilder extends CSpecElementBuilder
{
	private String m_componentType;
	private IVersionDesignator m_versionDesignator;
	private Filter m_filter;

	DependencyBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_componentType = null;
		m_versionDesignator = null;
		m_filter = null;
	}

	public Dependency createDependency()
	{
		return new Dependency(this.getName(), m_componentType, m_versionDesignator, m_filter);
	}

	public String getComponentTypeID()
	{
		return m_componentType;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public IVersionDesignator getVersionDesignator()
	{
		return m_versionDesignator;
	}

	public String getVersionDesignatorString()
	{
		return m_versionDesignator == null ? null : m_versionDesignator.toString();
	}

	public IVersionType getVersionType()
	{
		return m_versionDesignator == null ? null : m_versionDesignator.getVersion().getType();
	}

	@Override
	public void initFrom(NamedElement depElem)
	{
		super.initFrom(depElem);
		Dependency dependency = (Dependency)depElem;
		m_componentType = dependency.getComponentTypeID();
		m_versionDesignator = dependency.getVersionDesignator();
		m_filter = dependency.getFilter();
	}

	public void setComponentTypeID(String componentType)
	{
		m_componentType = componentType;
	}

	public void setFilter(Filter filter)
	{
		m_filter = filter;
	}

	public void setVersionDesignator(String designatorStr, String versionType) throws CoreException
	{
		if(designatorStr == null)
			m_versionDesignator = null;
		else
			m_versionDesignator = VersionFactory.createDesignator(versionType, designatorStr);
	}

	public void setVersionDesignator(IVersionDesignator designator)
	{
		m_versionDesignator = designator;
	}
}
