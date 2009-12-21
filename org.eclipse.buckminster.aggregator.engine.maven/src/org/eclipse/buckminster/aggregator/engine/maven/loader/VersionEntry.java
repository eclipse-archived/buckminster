/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven.loader;

import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

public class VersionEntry
{
	String groupId;

	String artifactId;

	Version version;

	public VersionEntry(String groupId, String artifactId, Version version)
	{
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder(groupId);
		sb.append('/');
		sb.append(artifactId);
		sb.append('#');
		sb.append(version.getOriginal());
		return sb.toString();
	}
}
