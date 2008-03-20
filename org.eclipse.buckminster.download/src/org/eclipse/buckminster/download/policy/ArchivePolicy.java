/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.download.cache.ICache;
import org.eclipse.buckminster.download.cache.Installer;
import org.eclipse.buckminster.download.download.FileReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 * 
 */
public class ArchivePolicy extends AbstractFetchPolicy
{
	private final String m_remoteName;

	public ArchivePolicy(ICache cache, String remoteName)
	{
		super(cache);
		m_remoteName = remoteName;
	}

	public boolean update(URL remoteFile, File localFile, boolean checkOnly, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.begin(monitor, 1000);
		try
		{
			if(localFile.exists())
			{
				IFileInfo fi;
				try
				{
					fi = getCache().getRemoteInfo(remoteFile);
				}
				catch(FileNotFoundException e)
				{
					localFile.delete();
					throw BuckminsterException.wrap(e);
				}
				if(fi.getSize() == localFile.length() && fi.getLastModified() <= localFile.lastModified())
					return false;
			}
			MonitorUtils.worked(monitor, 100);
			if(checkOnly)
				return true;

			File tempFile = new File(localFile.getPath() + ".tmp");
			String fileName = readRemoteFile(remoteFile, tempFile, MonitorUtils.subMonitor(monitor, 800));
			if(m_remoteName != null)
				fileName = m_remoteName;

			try
			{
				Installer.getInstaller(fileName, true).validate(tempFile, MonitorUtils.subMonitor(monitor, 100));
			}
			catch(CoreException e)
			{
				tempFile.delete();
				throw e;
			}
			safeRename(tempFile, localFile);
			return true;
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}

	protected String readRemoteFile(URL url, File localFile, IProgressMonitor monitor) throws CoreException
	{
		// Set up the file transfer
		//
		OutputStream output = null;
		try
		{
			localFile = localFile.getCanonicalFile();
			File parentFolder = localFile.getParentFile();
			if(parentFolder != null)
				parentFolder.mkdirs();
			output = new FileOutputStream(localFile);
			FileReader retriever = new FileReader();
			retriever.readInto(url, output, monitor);
			return retriever.getLastFileInfo().getName();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}
}
