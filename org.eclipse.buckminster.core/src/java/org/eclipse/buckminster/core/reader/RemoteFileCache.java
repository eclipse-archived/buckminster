/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.helpers.ShortDurationFileCache;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class RemoteFileCache extends ShortDurationFileCache
{
	public RemoteFileCache(long keepAlive, String prefix, String suffix, File tempDir)
	{
		super(keepAlive, prefix, suffix, tempDir);
	}

	public InputStream openRemoteFile(final RemoteFile remoteFile, IProgressMonitor monitor)
	throws IOException, CoreException
	{
		return this.open(new Materializer()
		{
			public File materialize(boolean[] isTemporary, IProgressMonitor mon)
			throws IOException, CoreException
			{
				return remoteFile.getContents(isTemporary, mon);
			}

			public String getKey()
			{
				return remoteFile.toString();
			}
		}, monitor);
	}
}

