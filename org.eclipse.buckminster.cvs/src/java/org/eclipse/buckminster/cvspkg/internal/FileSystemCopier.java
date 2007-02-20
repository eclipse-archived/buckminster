/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

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
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.ICVSFile;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSResourceVisitor;
import org.eclipse.team.internal.ccvs.core.syncinfo.FolderSyncInfo;
import org.eclipse.team.internal.ccvs.core.syncinfo.MutableResourceSyncInfo;
import org.eclipse.team.internal.ccvs.core.syncinfo.ResourceSyncInfo;

@SuppressWarnings("restriction")
public class FileSystemCopier implements ICVSResourceVisitor
{
	/** the famous CVS meta directory name */
	public static final String CVS_DIRNAME = "CVS";

	// CVS meta files located in the CVS subdirectory
	//
	public static final String REPOSITORY = "Repository";

	public static final String ROOT = "Root";

	public static final String STATIC = "Entries.Static";

	public static final String TAG = "Tag";

	public static final String ENTRIES = "Entries";

	private final IPath m_fsRoot;
	private final ICVSFolder m_cvsRoot;
	private final IProgressMonitor m_monitor;

	/**
	 * An instance of this class is created whenever we enter a folder and pushed
	 * on a stack. It manages all the sync information for the elements of that
	 * folder.
	 */
	private static class FolderInfo
	{
		private final IPath m_path;
		private final ArrayList<byte[]> m_entries;
		private final ICVSFolder m_folder;
		private boolean m_isCreated;

		FolderInfo(ICVSFolder folder, IPath path) throws CVSException
		{
			m_folder = folder;
			m_entries = new ArrayList<byte[]>();
			m_path = path;
		}

		void addEntry(byte[] entry)
		{
			m_entries.add(entry);
		}

		byte[] getFolderSyncBytes() throws CVSException
		{
			return m_folder.getSyncInfo().getBytes();
		}

		IPath getPath()
		{
			return m_path;
		}

		boolean isEmpty()
		{
			return m_entries.isEmpty();
		}

		boolean isPrefixOf(IPath path)
		{
			return m_path.isPrefixOf(path);
		}

		void assertCreated() throws CVSException
		{
			if(!m_isCreated)
			{
				createDirectory(m_path);
				m_isCreated = true;
			}
		}

		/**
		 * Writes the CVS/Root, CVS/Repository, CVS/Tag, CVS/Entries, and
		 * CVS/Entries.static files to the specified folder using the data
		 * contained in the specified FolderSyncInfo instance.
		 */
		void writeSync() throws CVSException
		{
			this.assertCreated();
			IPath cvsFolder = createDirectory(m_path.append(CVS_DIRNAME));

			// format file contents
			//
			int idx = m_entries.size();
			String[] entries = new String[idx];
			while(--idx >= 0)
				entries[idx] = new String(m_entries.get(idx));

			// write Entries
			//
			writeLines(cvsFolder, ENTRIES, entries);

			// write CVS/Root
			//
			FolderSyncInfo info = m_folder.getFolderSyncInfo();
			writeLines(cvsFolder, ROOT, info.getRoot());

			// write CVS/Repository
			//
			writeLines(cvsFolder, REPOSITORY, info.getRepository());

			if(info.getTag() != null)
				//
				// write CVS/Tag
				//
				writeLines(cvsFolder, TAG, info.getTag().toEntryLineFormat(false));

			if(info.getIsStatic())
				//
				// write CVS/Entries.Static
				// the existance of the file is all that matters
				//
				writeLines(cvsFolder, STATIC, "");
		}
	}

	// This stack helps us keep track of what folder is current and when we leave
	// that folder (the path of the top FolderInfo is no longer a prefix of the
	// current element).
	//
	private final Stack<FolderInfo> m_currentPath = new Stack<FolderInfo>();

	public FileSystemCopier(ICVSFolder cvsRoot, IPath fsRoot, IProgressMonitor monitor) throws CVSException
	{
		m_cvsRoot = cvsRoot;
		m_fsRoot = fsRoot;
		m_monitor = monitor;
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
	}

	public void visitFile(ICVSFile file) throws CVSException
	{
		this.checkFolderEnd(file.getParent());
		FolderInfo fi = m_currentPath.peek();
		fi.assertCreated();

		OutputStream out = null;
		InputStream in = null;
		File osFile = fi.getPath().append(file.getName()).toFile();
		try
		{
			out = new FileOutputStream(osFile);
			in = file.getContents();
			FileUtils.copyFile(in, out, MonitorUtils.subMonitor(m_monitor, 10));
		}
		catch(IOException e)
		{
			throw CVSException.wrapException(e);
		}
		finally
		{
			IOUtils.close(in);
			IOUtils.close(out);
		}

		ResourceSyncInfo syncInfo = file.getSyncInfo();
		Date timestamp = syncInfo.getTimeStamp();
		if(timestamp != null)
			osFile.setLastModified(timestamp.getTime());
		else
		{
			MutableResourceSyncInfo mrsi = syncInfo.cloneMutable();
			mrsi.setTimeStamp(new Date(osFile.lastModified()));
			syncInfo = mrsi;
		}
		fi.addEntry(syncInfo.getBytes());
	}

	public void visitFolder(ICVSFolder folder) throws CVSException
	{
		this.checkFolderEnd(folder);
		m_currentPath.push(new FolderInfo(folder, m_fsRoot.append(folder.getRelativePath(m_cvsRoot))));
	}

	public void done() throws CVSException
	{
		while(!m_currentPath.isEmpty())
			this.visitFolderEnd();
		m_monitor.done();
	}

	private void checkFolderEnd(ICVSFolder folder) throws CVSException
	{
		if(!m_currentPath.isEmpty())
		{
			IPath fullPath = m_fsRoot.append(folder.getRelativePath(m_cvsRoot));
			while(!m_currentPath.peek().isPrefixOf(fullPath))
				this.visitFolderEnd();
		}
	}

	private void visitFolderEnd() throws CVSException
	{
		FolderInfo info = m_currentPath.pop();
		if(info.isEmpty())
			return;

		info.writeSync();
		MonitorUtils.worked(m_monitor, 5);

		if(!m_currentPath.isEmpty())
			m_currentPath.peek().addEntry(info.getFolderSyncBytes());
	}

	private static IPath createDirectory(IPath parentFolder) throws CVSException
	{
		File dir = parentFolder.toFile();
		if(!dir.mkdirs() && !dir.exists())
			throw new CVSException("Unable to create directory " + dir);
		return parentFolder;
	}

	private static void writeLines(IPath parentFolder, String metaFile, String... lines) throws CVSException
	{
		PrintWriter out = null;
		try
		{
			out = new PrintWriter(parentFolder.append(metaFile).toFile());
			for(String line : lines)
				out.println(line);
		}
		catch(FileNotFoundException e)
		{
			throw CVSException.wrapException(e);
		}
		finally
		{
			IOUtils.close(out);
		}
	}
}
