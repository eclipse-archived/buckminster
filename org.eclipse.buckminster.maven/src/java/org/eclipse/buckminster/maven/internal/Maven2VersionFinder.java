/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionFormat;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.w3c.dom.Document;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class Maven2VersionFinder extends MavenVersionFinder
{
	public static IPath getDefaultLocalRepoPath()
	{
		return new Path(System.getProperty("user.home")).append(".m2").append("repository"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * @deprecated Use
	 *             {@link Maven2ReaderType#getMetadataDocument(DocumentBuilder,URL,LocalCache,IConnectContext,IProgressMonitor)}
	 *             instead
	 */
	@Deprecated
	public static Document getMetadataDocument(DocumentBuilder docBld, URL url, LocalCache cache, IConnectContext cctx,
			IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return Maven2ReaderType.getMetadataDocument(docBld, url, cache, cctx, monitor);
	}

	/**
	 * @deprecated Use {@link Maven2ReaderType#getSnapshotVersion(Document,String)} instead
	 */
	@Deprecated
	public static String getSnapshotVersion(Document doc, String version)
	{
		return Maven2ReaderType.getSnapshotVersion(doc, version);
	}

	/**
	 * @deprecated Use {@link Maven2ReaderType#getVersions(Document)} instead
	 */
	@Deprecated
	public static List<String> getVersions(Document doc)
	{
		return Maven2ReaderType.getVersions(doc);
	}

	public Maven2VersionFinder(MavenReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query)
			throws CoreException
	{
		super(readerType, provider, ctype, query);
	}

	/**
	 * Returns an array of versions known to this repository.
	 * 
	 * @return known versions or <code>null</code> if not applicable.
	 * @throws CoreException
	 */
	@Override
	List<VersionMatch> getComponentVersions(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = getQuery();
		VersionRange range = query.getVersionRange();
		if(range != null)
		{
			if(range.getFormat().equals(VersionFormat.OSGI_FORMAT))
				//
				// Convert the OSGi version to a Triplet version instead.
				//
				range = VersionHelper.createRange(MavenComponentType.getTripletFormat(), range.toString());
		}

		List<VersionMatch> versions = new ArrayList<VersionMatch>();
		Maven2ReaderType readerType = (Maven2ReaderType)getReaderType();
		MapEntry mapEntry = getMapEntry();
		URI uri = getURI();
		StringBuilder pbld = new StringBuilder();
		readerType.appendFolder(pbld, uri.getPath());
		readerType.appendEntryFolder(pbld, getMapEntry());
		String rootPath = pbld.toString();
		LocalCache lc = getReaderType().getLocalCache();
		monitor.beginTask(null, 2000);
		try
		{
			DocumentBuilder docBld = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = Maven2ReaderType.getMetadataDocument(docBld, MavenReaderType.createURL(uri, rootPath
					+ "maven-metadata.xml"), //$NON-NLS-1$
					lc, getConnectContext(), monitor);
			for(String versionStr : Maven2ReaderType.getVersions(doc))
			{
				VersionMatch vm = readerType.createVersionMatch(docBld, this, mapEntry, range, versionStr);
				if(vm != null)
					versions.add(vm);
			}
			return versions;
		}
		catch(FileNotFoundException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(ParserConfigurationException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}
}
