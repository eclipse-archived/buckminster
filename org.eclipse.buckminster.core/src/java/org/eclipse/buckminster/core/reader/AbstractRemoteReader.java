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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractRemoteReader extends AbstractCatalogReader
{
	protected AbstractRemoteReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException
	{
		InputStream input = null;
		try
		{
			input = CorePlugin.getDefault().openCachedRemoteFile(this, fileName, monitor);
			return true;
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
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
		monitor.beginTask(fileName, 2000);
		try
		{
			input = CorePlugin.getDefault()
					.openCachedRemoteFile(this, fileName, MonitorUtils.subMonitor(monitor, 1000));
			return consumer.consumeStream(this, this.toString() + ',' + fileName, input, MonitorUtils.subMonitor(
					monitor, 1000));
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}
}
