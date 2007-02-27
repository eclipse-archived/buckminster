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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractReader implements IComponentReader
{
	private final ProviderMatch m_providerMatch;

	private final IReaderType m_readerType;

	protected AbstractReader(IReaderType readerType, ProviderMatch providerMatch)
	{
		m_providerMatch = providerMatch;
		m_readerType = readerType;
	}

	public NodeQuery getNodeQuery()
	{
		return m_providerMatch.getNodeQuery();
	}

	public ProviderMatch getProviderMatch()
	{
		return m_providerMatch;
	}

	public IReaderType getReaderType()
	{
		return m_readerType;
	}

	public void close()
	{
	}

	public IVersionConverter getVersionConverter() throws CoreException
	{
		return this.getProviderMatch().getVersionConverter();
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

	protected void copyOverlay(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		// TODO: Handle file overlays
	}

	protected File createTempFile() throws IOException
	{
		return File.createTempFile(this.getReaderType().getId(), ".tmp");
	}
}
