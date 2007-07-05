/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.net.URI;
import java.util.ArrayList;

import org.eclipse.buckminster.core.reader.URLCatalogReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class Maven2VersionFinder extends MavenVersionFinder
{
	public Maven2VersionFinder(MavenReaderType readerType, Provider provider, NodeQuery query)
	throws CoreException
	{
		super(readerType, provider, query);
	}

	private void appendFilesInFolder(StringBuilder pbld, ArrayList<IPath> fileList, IProgressMonitor monitor) throws CoreException
	{
		for(IPath filePath : URLCatalogReaderType.list(getReaderType().createURL(getURI(), pbld.toString()), monitor))
			fileList.add(filePath);
	}

	@Override
	IPath[] createFileList(IVersionDesignator designator, IProgressMonitor monitor) throws CoreException
	{
		Maven2ReaderType readerType = (Maven2ReaderType)getReaderType();
		URI uri = getURI();
		StringBuilder pbld = new StringBuilder();
		readerType.appendFolder(pbld, uri.getPath());
		readerType.appendEntryFolder(pbld, getMapEntry());
		ArrayList<IPath> fileList = new ArrayList<IPath>();
		String rootPath = pbld.toString();
		int rootLen = rootPath.length();
		String space = getProvider().getSpace();

		monitor.beginTask(null, 2000);
		try
		{
			// Add entries from all matching folders
			//
			NodeQuery query = getQuery();
			boolean defaultIsMatched = query.isMatch(null, null, getProvider().getSpace());
			for(IPath versionPath : URLCatalogReaderType.list(readerType.createURL(uri, rootPath), MonitorUtils.subMonitor(monitor, 1000)))
			{
				if(versionPath.segmentCount() != 1)
					continue;
	
				String folderName = versionPath.segment(0);
	
				if(!defaultIsMatched)
				{
					// No use scanning this folder if the version is incompatible with
					// the query
					//
					VersionMatch versionMatch = MavenComponentType.createVersionMatch(folderName, space, null);
					if(versionMatch == null || !query.isMatch(versionMatch))
						continue;
				}

				pbld.setLength(rootLen);
				readerType.appendFolder(pbld, folderName);
				appendFilesInFolder(pbld, fileList, MonitorUtils.subMonitor(monitor, 1000));
			}
			return fileList.toArray(new IPath[fileList.size()]);
		}
		finally
		{
			monitor.done();
		}
	}
}
