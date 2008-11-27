/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.download.internal.CacheImpl;
import org.eclipse.buckminster.download.internal.FileReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class DownloadManager
{
	private static ICache s_instance;

	public static boolean isWindows()
	{
		String os = System.getProperty("os.name"); //$NON-NLS-1$
		return os != null && os.toLowerCase().startsWith("windows"); //$NON-NLS-1$
	}

	public static synchronized ICache getCache() throws CoreException
	{
		if(s_instance != null)
			return s_instance;

		File buckDir = null;
		if(isWindows())
		{
			File appData = null;
			String appDataEnv = System.getenv("APPDATA"); //$NON-NLS-1$
			if(appDataEnv != null)
				appData = new File(appDataEnv);
			else
			{
				String userHome = System.getProperty("user.home"); //$NON-NLS-1$
				if(userHome != null)
					appData = new File(userHome, "Application Data"); //$NON-NLS-1$
			}
			if(appData != null)
				buckDir = new File(appData, "buckminster"); //$NON-NLS-1$
		}
		else
		{
			String userHome = System.getProperty("user.home"); //$NON-NLS-1$
			if(userHome != null)
				buckDir = new File(new File(userHome), ".buckminster"); //$NON-NLS-1$
		}
		if(buckDir == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.system_property_0_not_set, "user.home")); //$NON-NLS-1$

		s_instance = new CacheImpl(new File(buckDir, "repository")); //$NON-NLS-1$
		return s_instance;
	}

	public static InputStream read(URL url, IConnectContext cctx) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(cctx);
		return reader.read(url);
	}

	public static IFileInfo readInfo(URL url, IConnectContext cctx) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(cctx);
		return reader.readInfo(url);
	}

	public static IFileInfo readInto(URL url, IConnectContext cctx, OutputStream output, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(cctx);
		reader.readInto(url, output, monitor);
		return reader.getLastFileInfo();
	}
}
