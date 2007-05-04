/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.URLCatalogReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class MavenReaderType extends URLCatalogReaderType
{
	static StringBuilder appendFileName(StringBuilder bld, String artifactID, IVersionSelector vs, String extension) throws CoreException
	{
		bld.append(artifactID);
		bld.append('-');
		appendMavenVersionName(bld, vs);
		bld.append(extension);
		return bld;
	}

	static void appendMavenVersionName(StringBuilder bld, IVersionSelector vs) throws CoreException
	{
		if(!vs.isDefaultBranch())
		{
			bld.append(vs.getBranchName());
			bld.append('-');
		}

		switch(vs.getType())
		{
		case TAG:
			bld.append(vs.getQualifier());
			break;
		case LATEST:
			bld.append("SNAPSHOT");
			break;
		case TIMESTAMP:
			bld.append(VersionFactory.TimestampType.coerce(new Date(vs.getNumericQualifier())));
			break;
		default:
			throw new BuckminsterException("A Maven reader cannot handle plain version selector " + vs);
		}
	}

	static MapEntry getGroupAndArtifact(Provider provider, ComponentRequest request)
	throws BuckminsterException
	{
		String name = request.getName();
		return (provider instanceof MavenProvider)
			? ((MavenProvider)provider).getGroupAndArtifact(name)
			: MavenProvider.getDefaultGroupAndArtifact(name);
	}

	private final LocalCache m_localCache;

	public MavenReaderType()
	{
		m_localCache = new LocalCache(getLocalRepoPath());
	}

	public IPath getLocalRepoPath()
	{
		// TODO: Control using preference setting
		//
		return getDefaultLocalRepoPath();
	}

	@Override
	public IPath getMaterializationLocation(Resolution cr, MaterializationContext context, boolean[] optional)
	throws CoreException
	{
		MapEntry ga = getGroupAndArtifact(cr.getProvider(), cr.getRequest());
		IVersionSelector vs = cr.getVersionMatch().getFixedVersionSelector();
		StringBuilder pbld = new StringBuilder();
		appendFolder(pbld, getMaterializationFolder());
		appendPathToArtifact(pbld, ga, vs);
		optional[0] = true;
		return CorePlugin.getDefault().getBuckminsterProjectLocation().append(pbld.toString());
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new MavenReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new MavenVersionFinder(this, provider, nodeQuery);
	}

	void appendArtifactFolder(StringBuilder pbld, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		appendFolder(pbld, mapEntry.getGroupId());
		appendFolder(pbld, "jars");
	}

	void appendFolder(StringBuilder pbld, String folder)
	{
		pbld.append(folder);
		if(!folder.endsWith("/"))
			pbld.append('/');
	}

	void appendPathToArtifact(StringBuilder pbld, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		appendArtifactFolder(pbld, mapEntry, vs);
		String extension;
		if(vs == null || vs.getTypeInfo() == null)
			extension = ".jar";
		else
			extension = '.' + vs.getTypeInfo();
		appendFileName(pbld, mapEntry.getArtifactId(), vs, extension);
	}

	void appendPathToPom(StringBuilder pbld, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		appendPomFolder(pbld, mapEntry, vs);
		appendFileName(pbld, mapEntry.getArtifactId(), vs, ".pom");
	}

	void appendPomFolder(StringBuilder pbld, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		appendFolder(pbld, mapEntry.getGroupId());
		appendFolder(pbld, "poms");
	}

	URL createURL(URI repoURI, String path) throws CoreException
	{
		try
		{
			return new URI(repoURI.getScheme(), repoURI.getAuthority(), path, null, null).toURL();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	IPath getArtifactPath(MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		StringBuilder pbld = new StringBuilder();
		appendPathToArtifact(pbld, mapEntry, vs);
		return new Path(pbld.toString());
	}

	URL getArtifactURL(URI repoURI, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		StringBuilder pbld = new StringBuilder();
		appendFolder(pbld, repoURI.getPath());
		appendPathToArtifact(pbld, mapEntry, vs);
		return createURL(repoURI, pbld.toString());
	}

	IPath getDefaultLocalRepoPath()
	{
		return new Path(System.getProperty("user.home")).append(".maven").append("cache");
	}

	LocalCache getLocalCache()
	{
		return m_localCache;
	}

	String getMaterializationFolder()
	{
		return "maven";
	}

	IPath getPomPath(MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		StringBuilder pbld = new StringBuilder();
		appendPathToPom(pbld, mapEntry, vs);
		return new Path(pbld.toString());
	}

	URL getPomURL(URI repoURI, MapEntry mapEntry, IVersionSelector vs) throws CoreException
	{
		StringBuilder pbld = new StringBuilder();
		appendFolder(pbld, repoURI.getPath());
		appendPathToPom(pbld, mapEntry, vs);
		return createURL(repoURI, pbld.toString());
	}
}
