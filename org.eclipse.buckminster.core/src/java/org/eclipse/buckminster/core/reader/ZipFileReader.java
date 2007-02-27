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

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author Thomas Hallgren
 */
public class ZipFileReader extends AbstractCatalogReader
{
	private final String m_relPath;
	private Set<String> m_sourceEntries;
	private final URL m_sourceURL;

	public ZipFileReader(IReaderType readerType, ProviderMatch rInfo)
	throws CoreException
	{
		super(readerType, rInfo);
		try
		{
			URI uri = new URI(rInfo.getRepositoryURI());
			m_relPath = uri.getFragment();
			if(m_relPath != null)
				uri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), uri.getQuery(), null);
			m_sourceURL = uri.toURL();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public boolean canMaterialize()
	throws BuckminsterException
	{
		return true;
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor)
	throws CoreException
	{
		FileUtils.unzip(m_sourceURL, m_relPath, destination.toFile(), false, monitor);
	}

	public boolean isWritable()
	{
		return false;
	}

	@Override
	public String toString()
	{
		String src = m_sourceURL.toString();
		return m_relPath == null ? src : src + '!' + m_relPath;
	}

	protected final URL getURL()
	{
		return m_sourceURL;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor)
	throws CoreException
	{
		try
		{
			return this.getSourceEntries(monitor).contains(fileName);
		}
		catch(IOException e)
		{
			return false;
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor)
	throws CoreException, IOException
	{
		monitor.beginTask(null, 3000);
		monitor.subTask("Reading " + fileName + " from zip archive");
		String urlName = m_sourceURL.toString() + '!' + fileName;

		ZipInputStream input = null;
		try
		{
			if(!this.getSourceEntries(MonitorUtils.subMonitor(monitor, 1000)).contains(fileName))
				throw new FileNotFoundException(urlName);

			ZipEntry ze;
			input = this.getZipInputStream(MonitorUtils.subMonitor(monitor, 1000));
			while((ze = input.getNextEntry()) != null)
			{
				String name = ze.getName();
				if(m_relPath != null)
				{
					if(!name.startsWith(m_relPath))
						continue;
					name = name.substring(m_relPath.length() + 1);
					if(name.length() == 0)
						continue;
				}
				if(fileName.equals(name))
				{
					return consumer.consumeStream(this, urlName, input, MonitorUtils.subMonitor(monitor, 1000));
				}
			}
			throw new FileNotFoundException(urlName);
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}

	private synchronized Set<String> getSourceEntries(IProgressMonitor monitor)
	throws CoreException, IOException
	{
		if(m_sourceEntries != null)
			return m_sourceEntries;

		HashSet<String> entries = new HashSet<String>();
		ZipInputStream input = null;
		monitor.beginTask(null, 2000);
		try
		{
			ZipEntry ze;
			input = this.getZipInputStream(MonitorUtils.subMonitor(monitor, 1000));
			int ticksLeft = 1000;
			while((ze = input.getNextEntry()) != null)
			{
				String name = ze.getName();
				if(m_relPath != null)
				{
					if(!name.startsWith(m_relPath))
						continue;
					name = name.substring(m_relPath.length() + 1);
					if(name.length() == 0)
						continue;
				}
				entries.add(name);
				if(ticksLeft > 0)
				{
					MonitorUtils.worked(monitor, 10);
					ticksLeft -= 10;
				}
			}
			if(ticksLeft > 0)
				MonitorUtils.worked(monitor, ticksLeft);
			m_sourceEntries = entries;
			return entries;
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}

	private ZipInputStream getZipInputStream(IProgressMonitor monitor)
	throws IOException, CoreException
	{
		return new ZipInputStream(new BufferedInputStream(CorePlugin.getDefault().openCachedURL(m_sourceURL, monitor)));
	}
}

