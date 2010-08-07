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

import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;

/**
 * @author thhal
 * 
 */
public class ShortDurationURLCache extends ShortDurationFileCache {
	public ShortDurationURLCache(long keepAlive) {
		super(keepAlive);
	}

	public InputStream openURL(final URL url, final IConnectContext cctx, IProgressMonitor monitor) throws IOException, CoreException {
		if ("file".equalsIgnoreCase(url.getProtocol())) //$NON-NLS-1$
			return url.openStream();

		return this.open(new Materializer() {
			@Override
			public String getKey() {
				return url.toString();
			}

			@Override
			public FileHandle materialize(IProgressMonitor mon, FileInfoBuilder info) throws IOException, CoreException {
				if (info == null)
					info = new FileInfoBuilder();

				File tempFile = null;
				boolean success = false;
				try {
					tempFile = File.createTempFile("bmurl", ".cache"); //$NON-NLS-1$ //$NON-NLS-2$
					OutputStream output = new FileOutputStream(tempFile);
					try {
						DownloadManager.readInto(url, cctx, output, mon);
					} finally {
						IOUtils.close(output);
					}
					success = true;
					return new FileHandle(url.toString(), tempFile, true);
				} finally {
					if (!success && tempFile != null)
						tempFile.delete();
				}
			}
		}, monitor);
	}
}
