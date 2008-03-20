/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.File;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.IFetchPolicy;
import org.eclipse.buckminster.runtime.BuckminsterException;
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
}
