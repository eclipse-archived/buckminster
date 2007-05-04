/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;

/**
 * A Component Identifier is something that uniquely identifies a
 * component.
 *
 * @author Thomas Hallgren
 */
public class ComponentIdentifier extends ComponentName
{
	private final IVersion m_version;
	public static final String ATTR_VERSION_TYPE = "versionType";
	public static final String ATTR_VERSION = "version";

	public ComponentIdentifier(String name, String categoryName, IVersion version)
	{
		super(name, categoryName);
		if(version != null && version.isDefault())
			version = null;
		m_version = version;
	}

	public static ComponentIdentifier parse(String componentIdentifierStr) throws CoreException
	{
		IVersion version = null;
		int verIdx = componentIdentifierStr.indexOf('$');
		if(verIdx >= 0)
		{
			String versionStr = componentIdentifierStr.substring(verIdx + 1);
			componentIdentifierStr = componentIdentifierStr.substring(0, verIdx);
			
			String versionType = null;
			int typeIdx = versionStr.indexOf('#');
			if(typeIdx > 0)
			{
				versionType = versionStr.substring(typeIdx + 1);
				versionStr = versionStr.substring(0, typeIdx);
			}
			version = VersionFactory.createVersion(versionType, versionStr);
		}

		String category = null;
		int catIdx = componentIdentifierStr.indexOf(':');
		if(catIdx >= 0)
		{
			category = componentIdentifierStr.substring(catIdx + 1);
			componentIdentifierStr = componentIdentifierStr.substring(0, catIdx);
		}

		return new ComponentIdentifier(componentIdentifierStr, category, version);
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;

		return this == o
			|| (super.equals(o) && Trivial.equalsAllowNull(m_version, ((ComponentIdentifier)o).m_version));
	}

	@Override
	public Map<String,String> getProperties()
	{
		Map<String,String> p = super.getProperties();
		if(m_version != null)
		{
			p.put(KeyConstants.COMPONENT_VERSION, m_version.toString());
			p.put(KeyConstants.VERSION_TYPE, m_version.getType().getId());
		}
		return p;
	}

	public final IVersion getVersion()
	{
		return m_version;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		if(m_version != null)
		{
			hc *= 37;
			hc += m_version.hashCode();
		}
		return hc;
	}

	/**
	 * <p>Match this identifier with another identifier. The match is done as
	 * follows</p>
	 * <ul>
	 * <li>If names are not equal, the match is always false</li>
	 * <li>If both instances have a category, it must be equal</li>
	 * <li>If one instance lacks a category, the categories are not considered part of the match</p>
	 * <li>If both instances have a version, it must be equal</li>
	 * <li>If one instance lacks a version, the versions are not considered part of the match</p>
	 * @param o The identifier to match with this one
	 * @return <code>true</code> if the identifiers match
	 */
	public boolean matches(ComponentIdentifier o)
	{
		return super.matches(o)
			&& (m_version == null || o.m_version == null || m_version.equals(o.m_version));
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
		if(m_version != null)
		{
			bld.append('$');
			m_version.toString(bld);
			bld.append('#');
			bld.append(m_version.getType().getId());
		}
	}

	@Override
	public int compareTo(ComponentName o)
	{
		int cmp = super.compareTo(o);
		if(cmp == 0)
			cmp = o instanceof ComponentIdentifier
				? Trivial.compareAllowNull(m_version, ((ComponentIdentifier)o).m_version)
				: 1;
		return cmp;
	}

	@Override
	public String getDefaultTag()
	{
		return null;
	}
}
