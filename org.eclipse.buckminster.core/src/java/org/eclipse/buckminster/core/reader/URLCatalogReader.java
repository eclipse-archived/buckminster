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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;


/**
 * @author Thomas Hallgren
 */
public class URLCatalogReader extends AbstractCatalogReader
{
	private final URI m_uri;

	protected URLCatalogReader(URLCatalogReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		m_uri = readerType.getURI(rInfo);
	}

	/**
	 * This method currently returns <code>true</code> if the protocol is &quot;file&quot; since
	 * it's a bit cumbersome to browse otherwise. All other reader id uses will yield <code>true</code>
	 * always.
	 */
	public boolean canMaterialize()
	throws BuckminsterException
	{
		// FIXME: Should allow browsing of FTP sites.
		//
		return "file".equals(this.getURI().getScheme());
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor)
	throws CoreException
	{
		URL url = this.getURL();
		File source = FileUtils.getFile(url);
		if(source == null)
			throw new UnsupportedOperationException("Only file protocol is supported at this time");

		File destDir = destination.toFile();
		boolean success = false;
		try
		{
			if(destDir.toURI().toURL().equals(url))
			{
				// Component is already where it's supposed to be.
				//
				success = true;
				return;
			}
			FileUtils.deepCopy(source, destDir, false, monitor);
			success = true;
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			if(!success)
			{
				// Remove any stray stuff. The materialization should be
				// as atomic as possible.
				//
				try
				{
					FileUtils.deleteRecursive(destDir, new NullProgressMonitor());
				}
				catch(Throwable t)
				{
					t.printStackTrace();
				}
			}
		}
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

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor)
	throws CoreException
	{
		InputStream input = null;
		try
		{
			URL fileUrl = new URL(this.getURL(), fileName);
			input = CorePlugin.getDefault().openCachedURL(fileUrl, monitor);
			return true;
		}
		catch(IOException e)
		{
			return false;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor)
	throws CoreException, IOException
	{
		InputStream input = null;
		monitor.beginTask(fileName, 2);
		try
		{
			URL fileUrl = new URL(this.getURL(), fileName);
			input = new BufferedInputStream(CorePlugin.getDefault().openCachedURL(fileUrl, MonitorUtils.subMonitor(monitor, 1)));
			return consumer.consumeStream(this, fileUrl.toString(), input, MonitorUtils.subMonitor(monitor, 1));
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}
}

