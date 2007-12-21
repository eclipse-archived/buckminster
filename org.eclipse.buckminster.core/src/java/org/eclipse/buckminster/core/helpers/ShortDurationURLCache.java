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

import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
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

	public InputStream openURL(final URL url, IProgressMonitor monitor) throws IOException, CoreException
	{
		return openURL(url, monitor, null);
	}

	public InputStream openURL(final URL url, IProgressMonitor monitor, FileInfoBuilder fileInfo) throws IOException,
			CoreException
	{
		if(fileInfo != null)
			fileInfo.reset();

		if("file".equalsIgnoreCase(url.getProtocol()))
			return URLUtils.openStream(url, monitor, fileInfo);

		return this.open(new Materializer()
		{
			public FileHandle materialize(IProgressMonitor mon, FileInfoBuilder info)
					throws IOException
			{
				if(info == null)
					info = new FileInfoBuilder();

				OutputStream output = null;
				InputStream input = null;
				try
				{
					MonitorUtils.ensureNotNull(mon);
					mon.beginTask(null, 1000);
					mon.subTask("Reading from " + url);

					File tempFile = File.createTempFile("bmurl", ".cache");
					input = URLUtils.openStream(url, MonitorUtils.subMonitor(mon, 100), info);
					output = new FileOutputStream(tempFile);
					byte[] buf = new byte[0x2000];
					int count;

					IProgressMonitor writeMonitor = MonitorUtils.subMonitor(mon, 900);

					writeMonitor.beginTask(null, info.getSize() > 0
							? (int)info.getSize()
							: IProgressMonitor.UNKNOWN);
					try
					{
						ProgressStatistics progress = new ProgressStatistics(info.getSize());
						progress.setConverter(ProgressStatistics.FILESIZE_CONVERTER);

						ProgressReporter progressReporter = new ProgressReporter(writeMonitor, progress, "Fetching "
								+ info.getName() + " (%s)", progress.getReportInterval());
						progressReporter.start();

						try
						{
							while((count = input.read(buf)) >= 0)
							{
								output.write(buf, 0, count);
								progress.increase(count);

								// Bump the reporter to report the change
								progressReporter.interrupt();

								MonitorUtils.worked(writeMonitor, info.getSize() > 0 ? count : 1);
							}
						}
						finally
						{
							progressReporter.stopReporting();
						}
					}
					finally
					{
						writeMonitor.done();
					}
					return new FileHandle(url.toString(), tempFile, true);
				}
				finally
				{
					IOUtils.close(input);
					IOUtils.close(output);
					MonitorUtils.complete(mon);
				}
			}

			public String getKey()
			{
				return url.toString();
			}
		}, monitor, fileInfo);
	}
}
