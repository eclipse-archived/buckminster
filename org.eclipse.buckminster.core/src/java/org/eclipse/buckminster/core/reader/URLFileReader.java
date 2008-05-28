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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerEndPoint;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A reader that reads one singleton file denoted by its URL.
 * @author Thomas Hallgren
 */
public class URLFileReader extends AbstractReader implements IFileReader
{
	private final URI m_uri;
	private IFileInfo m_fileInfo;

	protected URLFileReader(IReaderType readerType, ProviderMatch rInfo, URI uri) throws CoreException
	{
		super(readerType, rInfo);
		m_uri = uri;
		m_fileInfo = null;
	}

	public boolean exists(IProgressMonitor monitor) throws CoreException, IOException
	{
		InputStream input = null;
		try
		{
			input = open(monitor);
			return true;
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public void materialize(IPath location, Resolution resolution, MaterializationContext ctx, IProgressMonitor monitor)
	throws CoreException
	{
		URL url = this.getURL();

		monitor.beginTask(null, 1000);
		monitor.subTask("Copying from " + url);

		InputStream in = null;
		try
		{
			IFileInfo[] fiHandle = new IFileInfo[1];
			in = DownloadManager.getCache().open(url, null, fiHandle, MonitorUtils.subMonitor(monitor, 800));
			m_fileInfo = fiHandle[0];

			MaterializerEndPoint unpacker = MaterializerEndPoint.create(location, m_fileInfo.getName(), resolution, ctx);
			File destFile = unpacker.getFinalDestination().toFile();

			if(destFile.toURI().toURL().equals(url))
				//
				// Materialization would result in copy onto self
				//
				return;

			// Assert that parent directory exists unless
			// we are at the root.
			//
			File destDir = destFile.getParentFile();
			if(destDir != null && !destDir.isDirectory())
				FileUtils.createDirectory(destDir, MonitorUtils.subMonitor(monitor, 100));
			else
				MonitorUtils.worked(monitor, 100);

			unpacker.unpack(in, MonitorUtils.subMonitor(monitor, 100));
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(in);
			monitor.done();
		}
	}

	public InputStream open(IProgressMonitor monitor) throws CoreException, IOException
	{
		ICache cache = DownloadManager.getCache();
		IFileInfo[] fiHandle = new IFileInfo[1];
		InputStream input = cache.open(getURL(), null, fiHandle, MonitorUtils.subMonitor(monitor, 800));
		m_fileInfo = fiHandle[0];
		return input;
	}

	public IFileInfo getFileInfo()
	{
		return m_fileInfo;
	}

	public final <T> T readFile(IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException
	{
		monitor.beginTask(null, 100);
		InputStream input = null;
		try
		{
			input = open(MonitorUtils.subMonitor(monitor, 50));
			return consumer.consumeStream(this, getURL().toString(), input, MonitorUtils.subMonitor(monitor, 50));
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}

	@Override
	public String toString()
	{
		return m_uri.toString();
	}

	protected final URI getURI()
	{
		return m_uri;
	}

	protected URL getURL() throws CoreException
	{
		try
		{
			return m_uri.toURL();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}

