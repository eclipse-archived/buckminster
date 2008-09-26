/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.util.StringTokenizer;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 *
 */
public class Maven2ReaderType extends MavenReaderType
{
	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new Maven2VersionFinder(this, provider, ctype, nodeQuery);
	}

	@Override
	void appendFileName(StringBuilder pbld, String artifactID, VersionMatch vm, String extension)
			throws CoreException
	{
		if(extension == null)
		{
			String artifactPath = vm.getArtifactInfo();
			pbld.append(artifactPath, artifactPath.lastIndexOf('/') + 1, artifactPath.length());
		}
		else
		{
			pbld.append(artifactID);
			pbld.append('-');
			pbld.append(vm.getVersion());
			pbld.append(extension);
		}
	}

	@Override
	void appendArtifactFolder(StringBuilder pbld, MapEntry mapEntry, VersionMatch vm) throws CoreException
	{
		String artifactPath = vm.getArtifactInfo();
		appendEntryFolder(pbld, mapEntry);
		pbld.append(artifactPath, 0, artifactPath.lastIndexOf('/') + 1);
	}

	@Override
	void appendPomFolder(StringBuilder pbld, MapEntry mapEntry, VersionMatch vs) throws CoreException
	{
		appendArtifactFolder(pbld, mapEntry, vs);
	}

	@Override
	void appendPathToArtifact(StringBuilder pbld, MapEntry mapEntry, VersionMatch vs) throws CoreException
	{
		appendEntryFolder(pbld, mapEntry);
		pbld.append(vs.getArtifactInfo());
	}

	void appendEntryFolder(StringBuilder pbld, MapEntry mapEntry) throws CoreException
	{
		StringTokenizer tokens = new StringTokenizer(mapEntry.getGroupId(), ".");
		while(tokens.hasMoreTokens())
			appendFolder(pbld, tokens.nextToken());
		appendFolder(pbld, mapEntry.getArtifactId());
	}

	@Override
	IPath getDefaultLocalRepoPath()
	{
		return new Path(System.getProperty("user.home")).append(".m2").append("repository");
	}

	@Override
	String getMaterializationFolder()
	{
		return "maven2";
	}
}
