package org.eclipse.buckminster.maven.internal;

import java.util.List;

interface IMapEntry {
	List<GroupAndArtifact> getAliases();

	String getArtifactId();

	String getGroupId();

	String getName();

	boolean isMatchFor(String gid, String aid);
}