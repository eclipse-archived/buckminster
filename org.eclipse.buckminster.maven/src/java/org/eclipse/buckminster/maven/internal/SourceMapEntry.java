package org.eclipse.buckminster.maven.internal;

import java.util.List;

class SourceMapEntry implements IMapEntry {

	private final IMapEntry binary;

	SourceMapEntry(IMapEntry binary) {
		this.binary = binary;
	}

	@Override
	public List<GroupAndArtifact> getAliases() {
		return binary.getAliases();
	}

	@Override
	public String getArtifactId() {
		return binary.getArtifactId();
	}

	@Override
	public String getGroupId() {
		return binary.getGroupId();
	}

	@Override
	public String getName() {
		return binary.getName() + ".source"; //$NON-NLS-1$
	}

	@Override
	public boolean isMatchFor(String gid, String aid) {
		return binary.isMatchFor(gid, aid);
	}
}
