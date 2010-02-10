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
import org.eclipse.buckminster.core.reader.AbstractCatalogReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.cvspkg.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
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
public class CVSReader extends AbstractCatalogReader {
	// We need to synchronize this on something static.
	// See: https://bugs.eclipse.org/bugs/show_bug.cgi?id=197301
	//
	static synchronized Date getTagDate(CVSTag tag) {
		return tag.asDate();
	}

	private final CVSTag fixed;

	private final CVSSession session;

	private RepositoryMetaData metaData;

	private RemoteFolder flatRoot;

	public CVSReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
		session = new CVSSession(rInfo.getRepositoryURI());
		fixed = CVSReaderType.getCVSTag(rInfo.getVersionMatch());
	}

	@Override
	public void close() {
		session.close();
	}

	@Override
	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 100);

		CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation) session.getLocation();

		// We avoid using the date if it is the last date known for
		// the repository.
		//
		CVSTag tag = null;
		if (fixed.getType() == CVSTag.DATE) {
			Date fixedDate = getTagDate(fixed);
			if (getMetaData(MonitorUtils.subMonitor(monitor, 20)).getLastModification().compareTo(fixedDate) > 0)
				tag = fixed;
		} else {
			tag = fixed;
			MonitorUtils.worked(monitor, 20);
		}

		MonitorUtils.testCancelStatus(monitor);
		ICVSFolder root = new RemoteFolder(null, cvsLocation, session.getModuleName(), tag);
		try {
			ICVSFolder folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, root, tag, IResource.DEPTH_INFINITE, MonitorUtils
					.subMonitor(monitor, 90));
			if (folder == null)
				throw new FileNotFoundException(toString());

			FileSystemCopier copier = new FileSystemCopier(folder, destination, MonitorUtils.subMonitor(monitor, 80));
			try {
				folder.accept(copier, true);
			} finally {
				copier.done();
				monitor.done();
			}
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public String toString() {
		return session.getRepository() + ',' + fixed.getName();
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		InputStream input = null;
		try {
			getCVSRemoteFile(fileName, monitor);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} finally {
			IOUtils.close(input);
		}
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException {
		// Build the local options
		//
		monitor.beginTask(null, 1000);
		monitor.subTask(NLS.bind(Messages.retrieving_0, fileName));

		InputStream in = null;
		OutputStream out = null;
		File tempFile = null;
		try {
			ICVSRemoteFile cvsFile = getCVSRemoteFile(fileName, MonitorUtils.subMonitor(monitor, 500));
			in = cvsFile.getContents(MonitorUtils.subMonitor(monitor, 250));
			tempFile = createTempFile();
			out = new BufferedOutputStream(new FileOutputStream(tempFile));
			IOUtils.copy(in, out, MonitorUtils.subMonitor(monitor, 250));
			FileHandle fh = new FileHandle(fileName, tempFile, true);
			tempFile = null;
			return fh;
		} finally {
			IOUtils.close(out);
			IOUtils.close(in);
			if (tempFile != null)
				tempFile.delete();
			monitor.done();
		}
	}

	@Override
	protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor) throws CoreException, IOException {
		monitor.beginTask(null, 1000 + (flatRoot == null ? 500 : 0));
		try {
			if (flatRoot == null)
				getFlatRoot(MonitorUtils.subMonitor(monitor, 500));

			ArrayList<String> matching = null;
			for (ICVSRemoteResource child : flatRoot.getChildren()) {
				String name = child.getName();
				if (pattern.matcher(name).matches()) {
					if (matching == null)
						matching = new ArrayList<String>();
					matching.add(name);
				}
			}
			if (matching == null)
				return;

			int ticksPerMatch = 1000 / matching.size();
			for (String name : matching)
				files.add(innerGetContents(name, MonitorUtils.subMonitor(monitor, ticksPerMatch)));
		} finally {
			monitor.done();
		}
	}

	@Override
	protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 1000 + (flatRoot == null ? 500 : 0));
		try {
			if (flatRoot == null)
				getFlatRoot(MonitorUtils.subMonitor(monitor, 500));

			for (ICVSRemoteResource child : flatRoot.getChildren()) {
				String name = child.getName();
				if (child.isFolder() && !name.endsWith("/")) //$NON-NLS-1$
					name = name + "/"; //$NON-NLS-1$
				files.add(child.getName());
			}
		} finally {
			monitor.done();
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		// Build the local options
		//
		monitor.beginTask(null, 1000);
		monitor.subTask(NLS.bind(Messages.retrieving_0, fileName));

		InputStream in = null;
		try {
			ICVSRemoteFile cvsFile = getCVSRemoteFile(fileName, MonitorUtils.subMonitor(monitor, 500));
			in = cvsFile.getContents(MonitorUtils.subMonitor(monitor, 250));
			return consumer.consumeStream(this, fileName, in, MonitorUtils.subMonitor(monitor, 250));
		} finally {
			IOUtils.close(in);
			monitor.done();
		}
	}

	private ICVSRemoteFile getCVSRemoteFile(String fileName, IProgressMonitor monitor) throws CoreException, FileNotFoundException {
		IPath filePath = Path.fromPortableString(fileName);
		RemoteFolder folder = flatRoot;
		if (filePath.segmentCount() > 1) {
			IPath parentPath = Path.fromPortableString(session.getModuleName()).append(filePath.removeLastSegments(1));
			CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation) session.getLocation();
			folder = new RemoteFolder(null, cvsLocation, parentPath.toPortableString(), fixed);
			folder = UpdateContentCachingService.buildRemoteTree(cvsLocation, folder, fixed, IResource.DEPTH_ONE, monitor);
		} else {
			if (flatRoot == null)
				getFlatRoot(monitor);
			else
				MonitorUtils.complete(monitor);
			folder = flatRoot;
		}

		if (folder == null)
			throw new FileNotFoundException(toString());

		ICVSResource cvsFile;
		try {
			cvsFile = folder.getChild(filePath.lastSegment());
		} catch (CVSException e) {
			throw new FileNotFoundException(e.getMessage());
		}

		if (!(cvsFile instanceof ICVSRemoteFile))
			throw new FileNotFoundException(NLS.bind(Messages.file_0_appears_to_be_folder, fileName));

		return (ICVSRemoteFile) cvsFile;
	}

	private void getFlatRoot(IProgressMonitor monitor) throws CoreException {
		if (flatRoot == null) {
			CVSRepositoryLocation cvsLocation = (CVSRepositoryLocation) session.getLocation();
			RemoteFolder root = new RemoteFolder(null, cvsLocation, session.getModuleName(), fixed);
			flatRoot = UpdateContentCachingService.buildRemoteTree(cvsLocation, root, fixed, IResource.DEPTH_ONE, monitor);
		} else
			MonitorUtils.complete(monitor);
	}

	private synchronized RepositoryMetaData getMetaData(IProgressMonitor monitor) throws CoreException {
		if (metaData == null) {
			CVSTag tag = (fixed.getType() == CVSTag.DATE) ? null : fixed;
			metaData = RepositoryMetaData.getMetaData(session, tag, monitor);
		} else
			MonitorUtils.complete(monitor);
		return metaData;
	}
}
