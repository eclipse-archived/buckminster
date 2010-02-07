package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.version.VersionSelector;

public class ReferenceInfo {
	private final String repositoryLocation;

	private final VersionSelector selector;

	private final String projectName;

	public ReferenceInfo(String repositoryLocation, VersionSelector selector, String projectName) {
		this.repositoryLocation = repositoryLocation;
		this.selector = selector;
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getRepositoryLocation() {
		return repositoryLocation;
	}

	public VersionSelector getSelector() {
		return selector;
	}
}
