/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.cvspkg.internal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteFile;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteResource;
import org.eclipse.team.internal.ccvs.core.ICVSResource;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolder;
import org.eclipse.team.internal.ccvs.core.resources.UpdateContentCachingService;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSReader extends AbstractRemoteReader
{
	private final CVSTag m_fixed;

	private final CVSSession m_session;

	private RepositoryMetaData m_metaData;

	public CVSReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		m_session = new CVSSession(rInfo.getRepositoryURI());
		m_fixed = CVSReaderType.getCVSTag(rInfo.getVersionMatch());
	}

	@Override
	public void close()
	{
		m_session.close();
	}

	// We need to synchronize this on something static.
	// See: https://bugs.eclipse.org/bugs/show_bug.cgi?id=197301
	//
	static synchronized Date getTagDate(CVSTag tag)
	{
		return tag.asDate();
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);

		CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation)m_session.getLocation();

		// We avoid using the date if it is the last date known for
		// the repository.
		//
		CVSTag tag = null;
		if(m_fixed.getType() == CVSTag.DATE)
		{
			Date fixedDate = getTagDate(m_fixed);
			if(getMetaData(MonitorUtils.subMonitor(monitor, 20)).getLastModification().compareTo(fixedDate) > 0)
				tag = m_fixed;
		}
		else
		{
			tag = m_fixed;
			MonitorUtils.worked(monitor, 20);
		}

		MonitorUtils.testCancelStatus(monitor);
		ICVSFolder root = new RemoteFolder(null, cvsLocation, m_session.getModuleName(), tag);
		ICVSFolder folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, root, tag,
			IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 90));
		FileSystemCopier copier = new FileSystemCopier(folder, destination, MonitorUtils.subMonitor(monitor,
			80));
		try
		{
			folder.accept(copier, true);
		}
		finally
		{
			copier.done();
			monitor.done();
		}
	}

	private RemoteFolder m_flatRoot;

	private void getFlatRoot(IProgressMonitor monitor) throws CoreException
	{
		if(m_flatRoot == null)
		{
			CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation)m_session.getLocation();
			RemoteFolder root = new RemoteFolder(null, cvsLocation, m_session.getModuleName(), m_fixed);
			m_flatRoot = UpdateContentCachingService.buildRemoteTree(cvsLocation, root, m_fixed,
					IResource.DEPTH_ONE, monitor);
		}
		else
			MonitorUtils.complete(monitor);
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor)
	throws CoreException,
		IOException
	{
		// Build the local options
		//
		IPath filePath = Path.fromPortableString(fileName);
		monitor.beginTask(null, filePath.segmentCount() > 1 || m_flatRoot == null ? 1500 : 1000);
		monitor.subTask("Retrieving " + fileName);

		InputStream in = null;
		OutputStream out = null;
		File tempFile = null;
		try
		{
			RemoteFolder folder = m_flatRoot;
			if(filePath.segmentCount() > 1)
			{
				IPath parentPath = Path.fromPortableString(m_session.getModuleName()).append(filePath.removeLastSegments(1));
				CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation)m_session.getLocation();
				folder = new RemoteFolder(null, cvsLocation, parentPath.toPortableString(), m_fixed);
				folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, folder, m_fixed, IResource.DEPTH_ONE, MonitorUtils.subMonitor(monitor, 500));
			}
			else
			{
				if(m_flatRoot == null)
					getFlatRoot(MonitorUtils.subMonitor(monitor, 500));
				folder = m_flatRoot;
			}

			ICVSResource cvsFile;
			try
			{
				cvsFile = folder.getChild(filePath.lastSegment());
			}
			catch(CVSException e)
			{
				throw new FileNotFoundException(e.getMessage());
			}

			if(!(cvsFile instanceof ICVSRemoteFile))
				throw new FileNotFoundException(fileName + " appears to be a folder");

			in = ((ICVSRemoteFile)cvsFile).getContents(MonitorUtils.subMonitor(monitor, 50));
			tempFile = createTempFile();
			out = new BufferedOutputStream(new FileOutputStream(tempFile));
			IOUtils.copy(in, out);
			FileHandle fh = new FileHandle(fileName, tempFile, true);
			tempFile = null;
			return fh;
		}
		finally
		{
			IOUtils.close(out);
			IOUtils.close(in);
			if(tempFile != null)
				tempFile.delete();
			monitor.done();
		}
	}

	@Override
	public String toString()
	{
		return m_session.getRepository() + ',' + m_fixed.getName();
	}

	@Override
	protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor)
	throws CoreException, IOException
	{
		monitor.beginTask(null, 1000 + (m_flatRoot == null ? 500 : 0));
		try
		{
			if(m_flatRoot == null)
				getFlatRoot(MonitorUtils.subMonitor(monitor, 500));

			ArrayList<String> matching = null;
			for(ICVSRemoteResource child : m_flatRoot.getChildren())
			{
				String name = child.getName();
				if(pattern.matcher(name).matches())
				{
					if(matching == null)
						matching = new ArrayList<String>();
					matching.add(name);
				}
			}	
			if(matching == null)
				return;

			int ticksPerMatch = 1000 / matching.size();
			for(String name : matching)
				files.add(innerGetContents(name, MonitorUtils.subMonitor(monitor, ticksPerMatch)));
		}
		finally
		{
			monitor.done();
		}
	}

	private synchronized RepositoryMetaData getMetaData(IProgressMonitor monitor) throws CoreException
	{
		if(m_metaData == null)
		{
			CVSTag tag = (m_fixed.getType() == CVSTag.DATE) ? null : m_fixed;
			m_metaData = RepositoryMetaData.getMetaData(m_session, tag, monitor);
		}
		else
			MonitorUtils.complete(monitor);
		return m_metaData;
	}
}
