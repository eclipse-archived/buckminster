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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractCatalogReader extends AbstractReader implements ICatalogReader
{
	protected AbstractCatalogReader(IReaderType readerType, ProviderMatch providerMatch)
	{
		super(readerType, providerMatch);
	}

	public final File getContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor) throws CoreException, IOException
	{
		ProviderMatch ri = getProviderMatch();
		Logger logger = CorePlugin.getLogger();

		monitor.beginTask(null, 100);
		try
		{
			File addOnFolder = getOverlayFolder(MonitorUtils.subMonitor(monitor, 10));
			if(addOnFolder != null)
			{
				File addOnFile = new File(addOnFolder, fileName);
				if(addOnFile.exists())
				{
					if(logger.isDebugEnabled())
					{
						logger.debug(String.format("Provider %s(%s): getContents will use overlay %s for file = %s",
								getReaderType().getId(),
								ri.getRepositoryURI(),
								addOnFile,
								fileName));
					}
					MonitorUtils.worked(monitor, 90);
					isTemporary[0] = false;
					return addOnFile;
				}
			}	
			return innerGetContents(fileName, isTemporary, MonitorUtils.subMonitor(monitor, 90));
		}
		finally
		{
			monitor.done();
		}
	}

	public final boolean exists(String fileName, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			File addOnFolder = getOverlayFolder(MonitorUtils.subMonitor(monitor, 10));
			if(addOnFolder != null && new File(addOnFolder, fileName).exists())
			{
				MonitorUtils.worked(monitor, 90);
				return true;
			}
			return innerExists(fileName, MonitorUtils.subMonitor(monitor, 90));
		}
		finally
		{
			monitor.done();
		}
	}

	public final void materialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		ProviderMatch pm = this.getProviderMatch();
		Logger logger = CorePlugin.getLogger();
		if(logger.isDebugEnabled())
		{
			logger.debug(String.format("Provider %s(%s): materializing to %s",
				this.getReaderType().getId(),
				pm.getRepositoryURI(),
				destination));
		}

		monitor.beginTask(null, 100);
		try
		{
			innerMaterialize(destination, MonitorUtils.subMonitor(monitor, 80));
			copyOverlay(destination, MonitorUtils.subMonitor(monitor, 10));
		}
		finally
		{
			monitor.done();
		}
	}

	public final <T> T readFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException
	{
		monitor.beginTask(null, 100);
		try
		{
			File addOnFolder = getOverlayFolder(MonitorUtils.subMonitor(monitor, 10));
			if(addOnFolder == null)
				return innerReadFile(fileName, consumer, MonitorUtils.subMonitor(monitor, 90));
	
			InputStream tmp = null;
			IProgressMonitor overlayReadMon = MonitorUtils.subMonitor(monitor, 10);
			try
			{
				File file = new File(addOnFolder, fileName);
				tmp = new BufferedInputStream(new FileInputStream(file));
				return consumer.consumeStream(this, file.getAbsolutePath(), tmp, overlayReadMon);
			}
			catch(FileNotFoundException e)
			{
				// This is OK, just continue with the innerReadFile
				MonitorUtils.complete(overlayReadMon);
			}
			finally
			{
				IOUtils.close(tmp);
			}
			return innerReadFile(fileName, consumer, MonitorUtils.subMonitor(monitor, 80));
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	protected void copyOverlay(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			File addOnFolder = getOverlayFolder(MonitorUtils.subMonitor(monitor, 50));
			if(addOnFolder != null)
			{
				// Copy the addOnFolder. Overwrite is always OK for addOnFolders
				//
				File destDir = destination.toFile();
				destDir.mkdirs();
				FileUtils.deepCopyUnchecked(addOnFolder, destDir, MonitorUtils.subMonitor(monitor, 50));
			}
			else
				MonitorUtils.worked(monitor, 50);
		}
		finally
		{
			monitor.done();
		}
	}

	protected File innerGetContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor) throws CoreException, IOException
	{
		isTemporary[0] = false;
		OutputStream tmp = null;
		File tempFile = null;
		try
		{
			tempFile = createTempFile();
			tmp = new FileOutputStream(tempFile);
			final OutputStream out = tmp;
			readFile(fileName, new IStreamConsumer<Object>()
			{
				public Object consumeStream(IComponentReader reader, String streamName, InputStream stream,
						IProgressMonitor mon) throws IOException
				{
					FileUtils.copyFile(stream, out, mon);
					return null;
				}
			}, monitor);
			isTemporary[0] = true;
			return tempFile;
		}
		catch(FileNotFoundException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(tmp);
			if(!isTemporary[0] && tempFile != null)
				tempFile.delete();
		}
	}

	protected File getOverlayFolder(IProgressMonitor monitor) throws CoreException
	{
		URL overlay = getNodeQuery().getOverlayFolder();
		if(overlay == null)
			return null;

		File fileOverlay;
		try
		{
			fileOverlay = FileUtils.getFile(FileLocator.toFileURL(overlay));
			if(fileOverlay == null)
				return obtainRemoteOverlayFolder(overlay, monitor);

			if(!fileOverlay.isAbsolute())
			{
				// Relative overlays are relative to the workspace root so that they
				// can reside in other projects residing in the workspace from which
				// prototyping takes place.
				//
				IPath wsRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
				fileOverlay = wsRoot.append(new Path(fileOverlay.toString())).toFile();
			}

			String fos = fileOverlay.toString();
			if(fos.endsWith(".zip") || fos.endsWith(".jar"))
			{
				File dest = FileUtils.createTempFolder("bmovl", ".tmp");
				FileUtils.unzip(URLUtils.normalizeToURL(fos), null, dest, false, monitor);
				return dest;
			}

			if(!fileOverlay.isDirectory())
				throw new IllegalOverlayException("Only folders, zip, and jar archives allowed");
			
			// Monitor was not used for anything so make it complete
			//
			MonitorUtils.complete(monitor);
			return fileOverlay;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private static File obtainRemoteOverlayFolder(URL url, IProgressMonitor monitor) throws CoreException
	{
		String path = url.getPath();
		if(!(path.endsWith(".zip") || path.endsWith(".jar")))
			throw new IllegalOverlayException("Only zip and jar archives allowed for remote overlays");

		File dest = FileUtils.createTempFolder("bmovl", ".tmp");
		FileUtils.unzip(url, null, dest, false, monitor);
		return dest;
	}

	protected abstract boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException;

	protected abstract <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException;
}
