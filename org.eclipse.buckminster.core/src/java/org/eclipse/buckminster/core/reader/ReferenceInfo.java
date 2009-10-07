package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.version.VersionSelector;

public class ReferenceInfo
{
	private final String m_repositoryLocation;

	private final VersionSelector m_selector;

	private final String m_projectName;

	public ReferenceInfo(String repositoryLocation, VersionSelector selector, String projectName)
	{
		m_repositoryLocation = repositoryLocation;
		m_selector = selector;
		m_projectName = projectName;
	}

	public String getProjectName()
	{
		return m_projectName;
	}

	public String getRepositoryLocation()
	{
		return m_repositoryLocation;
	}

	public VersionSelector getSelector()
	{
		return m_selector;
	}
}
