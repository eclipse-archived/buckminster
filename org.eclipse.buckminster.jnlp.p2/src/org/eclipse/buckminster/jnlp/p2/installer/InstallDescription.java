/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Code 9 - ongoing development
 *     Cloudsmith - ongoing development
 *******************************************************************************/
package org.eclipse.buckminster.jnlp.p2.installer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

/**
 * An install information captures all the data needed to perform a product install. This includes information on where
 * the installed product comes from, what will be installed, and where it will be installed.
 */
public class InstallDescription
{
	private URL[] m_artifactRepos;

	private IPath m_installLocation;

	private IPath m_agentLocation;

	private IPath m_bundleLocation;

	private boolean m_isAutoStart;

	private String m_launcherName;

	private URL[] m_metadataRepos;

	private String m_productName;

	private VersionedName[] m_roots;

	private final Map<String, String> m_profileProperties = new HashMap<String, String>();

	/**
	 * Returns the p2 agent location, or <code>null</code> to indicate the default agent location.
	 */
	public IPath getAgentLocation()
	{
		return m_agentLocation;
	}

	/**
	 * Returns the locations of the artifact repositories to install from
	 * 
	 * @return a list of artifact repository URLs
	 */
	public URL[] getArtifactRepositories()
	{
		return m_artifactRepos;
	}

	/**
	 * Returns the bundle pool location, or <code>null</code> to indicate the default bundle pool location.
	 */
	public IPath getBundleLocation()
	{
		return m_bundleLocation;
	}

	/**
	 * Returns the local file system location to install into.
	 * 
	 * @return a local file system location
	 */
	public IPath getInstallLocation()
	{
		return m_installLocation;
	}

	/**
	 * Returns the name of the product's launcher executable
	 * 
	 * @return the name of the launcher executable
	 */
	public String getLauncherName()
	{
		return m_launcherName;
	}

	/**
	 * Returns the locations of the metadata repositories to install from
	 * 
	 * @return a list of metadata repository URLs
	 */
	public URL[] getMetadataRepositories()
	{
		return m_metadataRepos;
	}

	/**
	 * Returns the profile properties for this install.
	 */
	public Map<String, String> getProfileProperties()
	{
		return m_profileProperties;
	}

	/**
	 * Returns a human-readable name for this install.
	 * 
	 * @return the name of the product
	 */
	public String getProductName()
	{
		return m_productName;
	}

	/**
	 * Returns whether the installed product should be started upon successful install.
	 * 
	 * @return <code>true</code> if the product should be started upon successful install, and <code>false</code>
	 *         otherwise
	 */
	public boolean isAutoStart()
	{
		return m_isAutoStart;
	}

	public void setAgentLocation(IPath agentLocation)
	{
		this.m_agentLocation = agentLocation;
	}

	public void setArtifactRepositories(URL[] value)
	{
		this.m_artifactRepos = value;
	}

	public void setAutoStart(boolean value)
	{
		this.m_isAutoStart = value;
	}

	public void setBundleLocation(IPath bundleLocation)
	{
		this.m_bundleLocation = bundleLocation;
	}

	public void setInstallLocation(IPath location)
	{
		this.m_installLocation = location;
	}

	public void setLauncherName(String name)
	{
		this.m_launcherName = name;
	}

	public void setMetadataRepositories(URL[] value)
	{
		this.m_metadataRepos = value;
	}

	/**
	 * Supplies a set of profile properties to be added when the profile is created.
	 * 
	 * @param properties
	 *            the profile properties to be added
	 */
	public void setProfileProperties(Map<String, String> properties)
	{
		m_profileProperties.putAll(properties);
	}

	/**
	 * Returns the set of roots to be installed for this installation
	 * 
	 * @return the roots to install
	 */
	public VersionedName[] getRoots()
	{
		return m_roots;
	}

	/**
	 * Set the list of roots to install
	 * 
	 * @param value
	 *            the set of roots to install
	 */
	public void setRoots(VersionedName[] value)
	{
		m_roots = value;
	}

	/**
	 * Set the name of the product being installed.
	 * 
	 * @param value
	 *            the new name of the product to install
	 */
	public void setProductName(String value)
	{
		m_productName = value;
	}

}
