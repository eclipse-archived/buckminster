/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class DependencyBuilder extends CSpecElementBuilder
{
	private String m_category;
	private IVersionDesignator m_versionDesignator;

	DependencyBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_category = null;
		m_versionDesignator = null;
	}

	public ComponentRequest createDependency()
	{
		return new ComponentRequest(this.getName(), m_category, m_versionDesignator);
	}

	public String getCategory()
	{
		return m_category;
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

	public void initFrom(ComponentRequest dependency)
	{
		super.initFrom(dependency);
		m_category = dependency.getCategory();
		m_versionDesignator = dependency.getVersionDesignator();
	}

	public void setCategory(String category)
	{
		m_category = category;
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
