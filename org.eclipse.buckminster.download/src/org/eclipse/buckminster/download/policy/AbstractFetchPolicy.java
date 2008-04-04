/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.IFetchPolicy;
import org.eclipse.buckminster.download.internal.CacheImpl;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractFetchPolicy implements IFetchPolicy
{
	private final ICache m_cache;

	protected AbstractFetchPolicy(ICache cache)
	{
		m_cache = cache;
	}

	protected ICache getCache()
	{
		return m_cache;
	}

	protected void saveLocalFileInfo(URL url, IFileInfo fileInfo) throws CoreException
	{
		FileInfoBuilder fiBld = (fileInfo instanceof FileInfoBuilder) ? (FileInfoBuilder)fileInfo : new FileInfoBuilder(fileInfo);
		Properties saveProps = new Properties();
		fiBld.addProperties(saveProps);
		OutputStream out = null;
		try
		{
			out = new BufferedOutputStream(new FileOutputStream(getFileInfoFile(url)));
			saveProps.store(out, null);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(out);
		}
	}

	protected IFileInfo readLocalFileInfo(URL url) throws CoreException
	{
		InputStream in = null;
		Properties loadProps = new Properties();
		try
		{
			in = new BufferedInputStream(new FileInputStream(getFileInfoFile(url)));
			loadProps.load(in);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(in);
		}
		return new FileInfoBuilder(loadProps);
	}

	protected static void safeRename(File sourceFile, File destFile) throws CoreException
	{
		File toDelete = null;
		if(destFile.exists())
		{
			toDelete = new File(destFile.getPath() + ".old");
			if(toDelete.exists())
				toDelete.delete();
			if(!destFile.renameTo(toDelete))
				throw BuckminsterException.fromMessage("Unable to rename %s", destFile);
		}

		if(sourceFile.renameTo(destFile))
		{
			if(toDelete != null)
				toDelete.delete();
		}
		else
		{
			if(toDelete != null)
				toDelete.renameTo(destFile);
			throw BuckminsterException.fromMessage("Unable to rename temp file to %s", destFile);
		}
	}
	
	protected File getFileInfoFile(URL url)
	{
		CacheImpl cache = (CacheImpl)getCache();
		File folder = cache.getSubFolder(url);
		UUID hash = cache.getHash(url.toString());
		return new File(folder, hash.toString() + ".properties");
	}
}
