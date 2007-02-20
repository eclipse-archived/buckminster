/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.internal.version;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.core.runtime.CoreException;


/**
 * @author Thomas Hallgren
 */
public class DefaultQuery implements IVersionQuery
{
	private static final IVersionConverter s_defaultConverter;

	static
	{
		try
		{
			s_defaultConverter = CorePlugin.getDefault().getVersionConverter(null);
		}
		catch(CoreException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	private final IVersionSelector m_baseSelector;
	private final IVersionConverter m_converter;
	private final IVersionDesignator m_desiredVersions;

	public DefaultQuery(IVersionConverter converter, IVersionDesignator desiredVersions)
	throws CoreException
	{
		m_converter = converter;
		m_desiredVersions = desiredVersions;
		
		if(converter == null)
			converter = s_defaultConverter;
		m_baseSelector = converter.createSelector(m_desiredVersions.getVersion());
	}

	public IVersion createVersion(IVersionSelector selector) throws CoreException
	{
		IVersionConverter converter = m_converter;
		if(converter == null)
			converter = s_defaultConverter;
		return converter.createVersion(m_desiredVersions.getVersion().getType(), selector);
	}

	public String getBranch()
	{
		switch(getType())
		{
		case TAG:
		case TIMESTAMP:
		case CHANGE_NUMBER:
			return m_baseSelector.getBranchName();
		default:
			return null;
		}
	}

	public IVersionConverter getConverter()
	{
		return m_converter;
	}

	public IVersionDesignator getDesignator()
	{
		return m_desiredVersions;
	}

	public IVersionSelector getExactMatch()
	{
		return m_desiredVersions.isExplicit() ? m_baseSelector : null;
	}

	public VersionSelectorType getType()
	{
		return (m_converter == null) ? VersionSelectorType.TAG : m_converter.getType();
	}

	public boolean matches(IVersion version)
	{
		return m_desiredVersions.designates(version);
	}

	public boolean matches(IVersionSelector selector)
	{
		try
		{
			IVersion version = m_desiredVersions.getVersion();
			if(version.isDefault())
				return true;
			version = this.createVersion(selector);
			return version == null ? false : this.matches(version);
		}
		catch(CoreException e)
		{
			return false;
		}
	}
}
