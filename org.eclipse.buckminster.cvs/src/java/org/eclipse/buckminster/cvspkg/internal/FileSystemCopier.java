/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.cvspkg.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.cvspkg.Messages;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.ICVSFile;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSResourceVisitor;
import org.eclipse.team.internal.ccvs.core.syncinfo.FolderSyncInfo;
import org.eclipse.team.internal.ccvs.core.syncinfo.MutableResourceSyncInfo;
import org.eclipse.team.internal.ccvs.core.syncinfo.ResourceSyncInfo;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class FileSystemCopier implements ICVSResourceVisitor {
	/**
	 * An instance of this class is created whenever we enter a folder and
	 * pushed on a stack. It manages all the sync information for the elements
	 * of that folder.
	 */
	private static class FolderInfo {
		private final IPath path;

		private final ArrayList<byte[]> entries;

		private final ICVSFolder folder;

		private boolean isCreated;

		FolderInfo(ICVSFolder folder, IPath path) throws CVSException {
			this.folder = folder;
			this.entries = new ArrayList<byte[]>();
			this.path = path;
		}

		void addEntry(byte[] entry) {
			entries.add(entry);
		}

		void assertCreated() throws CVSException {
			if (!isCreated) {
				createDirectory(path);
				isCreated = true;
			}
		}

		byte[] getFolderSyncBytes() throws CVSException {
			return folder.getSyncInfo().getBytes();
		}

		IPath getPath() {
			return path;
		}

		boolean isEmpty() {
			return entries.isEmpty();
		}

		boolean isPrefixOf(IPath test) {
			return path.isPrefixOf(test);
		}

		/**
		 * Writes the CVS/Root, CVS/Repository, CVS/Tag, CVS/Entries, and
		 * CVS/Entries.static files to the specified folder using the data
		 * contained in the specified FolderSyncInfo instance.
		 */
		void writeSync() throws CVSException {
			this.assertCreated();
			IPath cvsFolder = createDirectory(path.append(CVS_DIRNAME));

			// format file contents
			//
			int idx = entries.size();
			String[] entryArray = new String[idx];
			while (--idx >= 0)
				entryArray[idx] = new String(entries.get(idx));

			// write Entries
			//
			writeLines(cvsFolder, ENTRIES, entryArray);

			// write CVS/Root
			//
			FolderSyncInfo info = folder.getFolderSyncInfo();
			writeLines(cvsFolder, ROOT, info.getRoot());

			// write CVS/Repository
			//
			writeLines(cvsFolder, REPOSITORY, info.getRepository());

			if (info.getTag() != null)
				//
				// write CVS/Tag
				//
				writeLines(cvsFolder, TAG, info.getTag().toEntryLineFormat(false));

			if (info.getIsStatic())
				//
				// write CVS/Entries.Static
				// the existence of the file is all that matters
				//
				writeLines(cvsFolder, STATIC, ""); //$NON-NLS-1$
		}
	}

	/** the famous CVS meta directory name */
	public static final String CVS_DIRNAME = "CVS"; //$NON-NLS-1$

	// CVS meta files located in the CVS subdirectory
	//
	public static final String REPOSITORY = "Repository"; //$NON-NLS-1$

	public static final String ROOT = "Root"; //$NON-NLS-1$

	public static final String STATIC = "Entries.Static"; //$NON-NLS-1$

	public static final String TAG = "Tag"; //$NON-NLS-1$

	public static final String ENTRIES = "Entries"; //$NON-NLS-1$

	private static IPath createDirectory(IPath parentFolder) throws CVSException {
		File dir = parentFolder.toFile();
		if (!dir.mkdirs() && !dir.exists())
			throw new CVSException(NLS.bind(Messages.unable_to_create_directory_0, dir));
		return parentFolder;
	}

	private static void writeLines(IPath parentFolder, String metaFile, String... lines) throws CVSException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(parentFolder.append(metaFile).toFile());
			for (String line : lines)
				out.println(line);
		} catch (FileNotFoundException e) {
			throw CVSException.wrapException(e);
		} finally {
			IOUtils.close(out);
		}
	}

	private final IPath fsRoot;

	private final ICVSFolder cvsRoot;

	private final IProgressMonitor monitor;

	// This stack helps us keep track of what folder is current and when we
	// leave
	// that folder (the path of the top FolderInfo is no longer a prefix of the
	// current element).
	//
	private final Stack<FolderInfo> currentPath = new Stack<FolderInfo>();

	public FileSystemCopier(ICVSFolder cvsRoot, IPath fsRoot, IProgressMonitor monitor) throws CVSException {
		this.cvsRoot = cvsRoot;
		this.fsRoot = fsRoot;
		this.monitor = monitor;
		this.monitor.beginTask(null, IProgressMonitor.UNKNOWN);
	}

	public void done() throws CVSException {
		while (!currentPath.isEmpty())
			this.visitFolderEnd();
		monitor.done();
	}

	@Override
	public void visitFile(ICVSFile file) throws CVSException {
		this.checkFolderEnd(file.getParent());
		FolderInfo fi = currentPath.peek();
		fi.assertCreated();

		OutputStream out = null;
		InputStream in = null;
		File osFile = fi.getPath().append(file.getName()).toFile();
		try {
			out = new FileOutputStream(osFile);
			in = file.getContents();
			FileUtils.copyFile(in, out, MonitorUtils.subMonitor(monitor, 10));
		} catch (IOException e) {
			throw CVSException.wrapException(e);
		} finally {
			IOUtils.close(in);
			IOUtils.close(out);
		}

		ResourceSyncInfo syncInfo = file.getSyncInfo();
		Date timestamp = syncInfo.getTimeStamp();
		if (timestamp != null)
			osFile.setLastModified(timestamp.getTime());
		else {
			MutableResourceSyncInfo mrsi = syncInfo.cloneMutable();
			mrsi.setTimeStamp(new Date(osFile.lastModified()));
			syncInfo = mrsi;
		}
		fi.addEntry(syncInfo.getBytes());
	}

	@Override
	public void visitFolder(ICVSFolder folder) throws CVSException {
		this.checkFolderEnd(folder);
		currentPath.push(new FolderInfo(folder, fsRoot.append(folder.getRelativePath(cvsRoot))));
	}

	private void checkFolderEnd(ICVSFolder folder) throws CVSException {
		if (!currentPath.isEmpty()) {
			IPath fullPath = fsRoot.append(folder.getRelativePath(cvsRoot));
			while (!currentPath.peek().isPrefixOf(fullPath))
				this.visitFolderEnd();
		}
	}

	private void visitFolderEnd() throws CVSException {
		FolderInfo info = currentPath.pop();
		if (info.isEmpty())
			return;

		info.writeSync();
		MonitorUtils.worked(monitor, 5);

		if (!currentPath.isEmpty())
			currentPath.peek().addEntry(info.getFolderSyncBytes());
	}
}
