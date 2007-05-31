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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * A reader that reads one singleton file denoted by its URL.
 * @author Thomas Hallgren
 */
public class URLFileReader extends AbstractReader implements IFileReader
{
	private final URI m_uri;
	private FileInfoBuilder m_fileInfo;
	private ProviderMatch m_rInfo;

	protected URLFileReader(URLReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		m_uri = readerType.getURI(rInfo);
		m_rInfo = rInfo;
		m_fileInfo = null;
	}

	public boolean canMaterialize()
	throws BuckminsterException
	{
		return true;
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

	public void innerMaterialize(IPath destination, IProgressMonitor monitor)
	throws CoreException
	{
		File destFile = destination.toFile();
		URL url = this.getURL();

		monitor.beginTask(null, 1000);
		monitor.subTask("Copying from " + url);
		boolean success = false;
		try
		{
			if(destFile.toURI().toURL().equals(url))
			{
				// Materialization would result in copy onto self
				//
				success = true;
				return;
			}

			InputStream in = null;
			OutputStream out = null;
			try
			{
				in = open(MonitorUtils.subMonitor(monitor, 500));

				if (destFile.isDirectory())
				{
					String filename = getFileInfo() != null ? getFileInfo().getName() : null;
					
					if (filename == null || filename.trim().length() == 0)
					{
						//No filename is available, let's use a name built from componentname-version.ext
						String componentName = m_rInfo.getComponentName();
						StringBuilder version = new StringBuilder();
						m_rInfo.getVersionMatch().getVersion().toString(version);
						String extension = getFileInfo() != null ? getFileInfo().getExtension() : null;
						
						if (extension == null)
							extension = "dat";
						
						filename = componentName + "-" + version.toString() + "." + extension;
					}

					destination = destination.append(filename);
					destFile = destination.toFile();
				}

				File destDir = destFile.getParentFile();
				if(destFile.exists())
					throw new FileUtils.DestinationNotEmptyException(destFile);

				// Assert that parent directory exists unless
				// we are at the root.
				//
				if(destDir != null && !destDir.isDirectory())
					FileUtils.createDirectory(destDir, MonitorUtils.subMonitor(monitor, 100));
				else
					MonitorUtils.worked(monitor, 100);

				out = new FileOutputStream(new File(destDir, destination.lastSegment()));
				FileUtils.copyFile(in, out, MonitorUtils.subMonitor(monitor, 400));
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				IOUtils.close(in);
				IOUtils.close(out);
			}
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
				try
				{
					FileUtils.deleteRecursive(destFile, new NullProgressMonitor());
				}
				catch(Throwable t)
				{
					t.printStackTrace();
				}
			}
			monitor.done();
		}
	}

	public InputStream open(IProgressMonitor monitor) throws CoreException, IOException
	{
		m_fileInfo = new FileInfoBuilder();
		InputStream stream = CorePlugin.getDefault().openCachedURL(getURL(), monitor, m_fileInfo);
		
		return stream;
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

