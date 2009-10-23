/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven;

import java.util.Map;

import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class InstallableUnitOverrider implements IInstallableUnit
{
	private IInstallableUnit m_installableUnit;

	private String m_id;

	private IRequiredCapability[] m_requiredCapabilities;

	private IArtifactKey[] m_artifacts;

	public InstallableUnitOverrider(IInstallableUnit iu)
	{
		m_installableUnit = iu;
	}

	@SuppressWarnings("unchecked")
	public int compareTo(Object o)
	{
		return m_installableUnit.compareTo(o);
	}

	public IArtifactKey[] getArtifacts()
	{
		if(m_artifacts != null)
			return m_artifacts;

		return m_installableUnit.getArtifacts();
	}

	public ICopyright getCopyright()
	{
		return m_installableUnit.getCopyright();
	}

	public String getFilter()
	{
		return m_installableUnit.getFilter();
	}

	public IInstallableUnitFragment[] getFragments()
	{
		return m_installableUnit.getFragments();
	}

	public String getId()
	{
		if(m_id != null)
			return m_id;

		return m_installableUnit.getId();
	}

	public ILicense getLicense()
	{
		return m_installableUnit.getLicense();
	}

	public IRequiredCapability[] getMetaRequiredCapabilities()
	{
		return m_installableUnit.getMetaRequiredCapabilities();
	}

	@SuppressWarnings("unchecked")
	public Map getProperties()
	{
		return m_installableUnit.getProperties();
	}

	public String getProperty(String key)
	{
		return m_installableUnit.getProperty(key);
	}

	public IProvidedCapability[] getProvidedCapabilities()
	{
		return m_installableUnit.getProvidedCapabilities();
	}

	public IRequiredCapability[] getRequiredCapabilities()
	{
		if(m_requiredCapabilities != null)
			return m_requiredCapabilities;

		return m_installableUnit.getRequiredCapabilities();
	}

	public ITouchpointData[] getTouchpointData()
	{
		return m_installableUnit.getTouchpointData();
	}

	public ITouchpointType getTouchpointType()
	{
		return m_installableUnit.getTouchpointType();
	}

	public IUpdateDescriptor getUpdateDescriptor()
	{
		return m_installableUnit.getUpdateDescriptor();
	}

	public Version getVersion()
	{
		return m_installableUnit.getVersion();
	}

	public boolean isFragment()
	{
		return m_installableUnit.isFragment();
	}

	public boolean isResolved()
	{
		return m_installableUnit.isResolved();
	}

	public boolean isSingleton()
	{
		return m_installableUnit.isSingleton();
	}

	public void overrideArtifacts(IArtifactKey[] artifacts)
	{
		m_artifacts = artifacts;
	}

	public void overrideId(String id)
	{
		m_id = id;
	}

	public void overrideRequiredCapabilities(IRequiredCapability[] requiredCapabilities)
	{
		m_requiredCapabilities = requiredCapabilities;
	}

	public boolean satisfies(IRequiredCapability candidate)
	{
		return m_installableUnit.satisfies(candidate);
	}

	public IInstallableUnit unresolved()
	{
		return m_installableUnit.unresolved();
	}

}
