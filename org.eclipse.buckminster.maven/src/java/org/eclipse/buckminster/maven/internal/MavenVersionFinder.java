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

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.URLCatalogReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The URL used by the MavenReader denotes the group directory within one
 * specific repository. The format must be <br/>
 * <code>[&lt;schema&gt;][//&lt;authority&gt;]&lt;path to group&gt;#&lt;artifact&gt;</code><br/>
 * The ability to search trhough multiple repositories is obtained by using the
 * <code>SearchPath</code> or the <code>ResourceMap</code>. The
 * 
 * @author Thomas Hallgren
 */
public class MavenVersionFinder implements IVersionFinder
{
	private static final String[] s_allowedExtensions = new String[] { ".jar", ".mar" };
	private static final String s_defaultExtension = ".jar";

	private final MavenReaderType m_readerType;

	private final MapEntry m_mapEntry;

	private final URI m_uri;

	private IPath[] m_fileList;

	public MavenVersionFinder(MavenReaderType readerType, Provider provider, NodeQuery query) throws CoreException
	{
		m_readerType = readerType;
		m_uri = readerType.getURI(provider, query.getProperties());
		m_mapEntry = MavenReaderType.getGroupAndArtifact(provider, query.getComponentRequest());
	}

	/**
	 * Maven always uses plugin style versioning.
	 * 
	 * @return the version selector that is highest in magnitude.
	 */
	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		try
		{
			IVersionDesignator designator = VersionFactory.createExplicitDesignator(VersionFactory.defaultVersion());
			IVersionQuery query = VersionSelectorFactory.createQuery(null, designator);
			VersionMatch best = getBestVersion(query, MonitorUtils.subMonitor(monitor, 1000));
			if(best != null)
			{
				MonitorUtils.worked(monitor, 1000);
				return best;
			}

			designator = VersionFactory.createDesignator(VersionFactory.TripletType, "0.0.0");
			query = VersionSelectorFactory.createQuery(null, designator);
			return getBestVersion(query, MonitorUtils.subMonitor(monitor, 1000));
		}
		finally
		{
			monitor.done();
		}
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		VersionMatch best = null;
		for(VersionMatch candidate : getComponentVersions(query, monitor))
			best = getBestVersion(candidate, best);
		return best;
	}

	public void close()
	{
		m_fileList = null;
	}

	IPath[] createFileList(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		StringBuilder pbld = new StringBuilder();
		m_readerType.appendFolder(pbld, m_uri.getPath());
		m_readerType.appendFolder(pbld, m_mapEntry.getGroupId());
		m_readerType.appendFolder(pbld,  "jars");
		URL jarsURL = m_readerType.createURL(m_uri, pbld.toString());
		return URLCatalogReaderType.list(jarsURL, monitor);
	}

	static VersionMatch getBestVersion(VersionMatch a, VersionMatch b)
	{
		if(a == null)
			return b;
		
		if(b == null)
			return a;

		IVersion av = a.getVersion();
		IVersion bv = b.getVersion();
		IVersionType at = av.getType();
		IVersionType bt = bv.getType();
		if(at.isComparableTo(bt))
			return (av.compareTo(bv) < 0) ? b : a;

		// We only deal with triplets, timestamps, and snapshots here. The
		// order of precedence is triplet, timestamp, snapshot
		// 
		//
		if(at.equals(VersionFactory.TripletType))
			return a;

		if(bt.equals(VersionFactory.TripletType))
			return b;

		if(at.equals(VersionFactory.TimestampType))
			return a;

		// at is a snapshot and since they are not comparable, bt has to be a timestamp
		// type at this point
		//
		return b;
	}

	/**
	 * Returns an array of versions known to this repository.
	 * 
	 * @return known versions or <code>null</code> if not applicable.
	 * @throws CoreException
	 */
	List<VersionMatch> getComponentVersions(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		IVersionDesignator designator = query.getDesignator();
		if(designator.getVersion().getType().equals(VersionFactory.OSGiType))
		{
			// Convert the OSGi version to a Triplet version instead.
			//
			designator = VersionFactory.createDesignator(VersionFactory.TripletType, designator.toString());
			query = VersionSelectorFactory.createQuery(query.getConverter(), designator);
		}

		List<VersionMatch> versions = new ArrayList<VersionMatch>();
		String artifact = m_mapEntry.getArtifactId() + '-';
		int artifactLen = artifact.length();
		for(IPath path : getFileList(query, monitor))
		{
			if(path.segmentCount() != 1)
				continue;

			String fileName = path.segment(0);
			if(!fileName.startsWith(artifact))
				continue;

			String extension = null;
			for(String allowedExtension : s_allowedExtensions)
			{
				if(fileName.endsWith(allowedExtension))
				{
					extension = allowedExtension;
					break;
				}	
			}
			if(extension == null)
				continue;

			fileName = fileName.substring(artifactLen, fileName.length() - extension.length());
			String typeInfo = s_defaultExtension.equals(extension) ? null : extension.substring(1);
			VersionMatch versionMatch = MavenComponentType.createVersionMatch(fileName, typeInfo);
			if(versionMatch != null && query.matches(versionMatch.getVersion()))
				versions.add(versionMatch);
		}
		return versions;
	}

	MapEntry getMapEntry()
	{
		return m_mapEntry;
	}

	MavenReaderType getReaderType()
	{
		return m_readerType;
	}

	URI getURI()
	{
		return m_uri;
	}

	private IPath[] getFileList(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		if(m_fileList == null)
			m_fileList = createFileList(query, monitor);
		return m_fileList;
	}
}
