/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download;

import java.io.File;

import org.eclipse.buckminster.download.cache.CacheImpl;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 *
 */
public class DownloadManager
{
	private static ICache s_instance;

	public static boolean isWindows()
	{
		String os = System.getProperty("os.name");
		return os != null && os.toLowerCase().startsWith("windows");
	}

	public static synchronized ICache getCache() throws CoreException
	{
		if(s_instance != null)
			return s_instance;

		File buckDir = null;
		if(isWindows())
		{
			File appData = null;
			String appDataEnv = System.getenv("APPDATA");
			if(appDataEnv != null)
				appData = new File(appDataEnv);
			else
			{
				String userHome = System.getProperty("user.home");
				if(userHome != null)
					appData = new File(userHome, "Application Data");
			}
			if(appData != null)
				buckDir = new File(appData, "buckminster");
		}
		else
		{
			String userHome = System.getProperty("user.home");
			if(userHome != null)
				buckDir = new File(new File(userHome), ".buckminster");
		}
		if(buckDir == null)
			throw BuckminsterException.fromMessage("user.home system property is not set");

		s_instance = new CacheImpl(new File(buckDir, "repository"));
		return s_instance;
	}
}
