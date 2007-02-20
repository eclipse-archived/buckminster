/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author thhal
 *
 */
public class ShortDurationURLCache extends ShortDurationFileCache
{
	public ShortDurationURLCache()
	{
		// FIXME: Should be preferences
		super(300000, "url", "cache", null);
	}

	public ShortDurationURLCache(long keepAlive, String prefix, String suffix, File tempDir)
	{
		super(keepAlive, prefix, suffix, tempDir);
	}

	public InputStream openURL(final URL url, IProgressMonitor monitor)
	throws IOException, CoreException
	{
		if("file".equalsIgnoreCase(url.getProtocol()))
			return URLUtils.openStream(url, monitor);

		return this.open(new Materializer()
		{
			public File materialize(boolean[] isTemporary, IProgressMonitor mon)
			throws IOException
			{
				OutputStream output = null;
				InputStream input = null;
				try
				{
					File tempFile = File.createTempFile("bmurl", ".cache");
					input  = URLUtils.openStream(url, mon);
					output = new FileOutputStream(tempFile);
					byte[] buf = new byte[0x2000];
					int count;
					while((count = input.read(buf)) >= 0)
						output.write(buf, 0, count);
					isTemporary[0] = true;
					return tempFile;
				}
				finally
				{
					IOUtils.close(input);
					IOUtils.close(output);
				}
			}

			public String getKey()
			{
				return url.toString();
			}
		}, monitor);
	}
}

