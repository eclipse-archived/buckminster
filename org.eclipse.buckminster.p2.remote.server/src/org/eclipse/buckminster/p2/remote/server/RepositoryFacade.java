/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URI;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

/**
 * @author Thomas Hallgren
 */
public abstract class RepositoryFacade implements IRepositoryFacade
{
	public static final String PROP_MIRROR_PREFIX = "buckminster.mirror.";

	private final String m_name;

	private final LoggingRepository m_repository;

	public RepositoryFacade(String name, LoggingRepository repository)
	{
		super();
		m_name = name;
		m_repository = repository;
	}

	public SynchronizationBlock getChanges(long sequenceNumber) throws ProvisionException
	{
		return m_repository.getChangeLog().getChangesSince(sequenceNumber);
	}

	public String getName()
	{
		return m_name;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getProperties()
	{
		return m_repository.getProperties();
	}

	public LoggingRepository getRepository()
	{
		return m_repository;
	}

	public IRepositoryDataStream getRepositoryData() throws ProvisionException
	{
		try
		{
			PipedInputStream input = new PipedInputStream();
			final OutputStream output = new GZIPOutputStream(new BufferedOutputStream(new PipedOutputStream(
				input), 0x8000));
			Thread pumper = new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						writeRepository(output);
					}
					catch(IOException e)
					{
						Buckminster.getLogger().error(e, "Error writing repository to output pipe");
					}
				}
			};
			pumper.start();
			return new RepositoryDataStream(input, m_repository.getChangeLog().getLastChangeNumber());
		}
		catch(IOException e)
		{
			throw new ProvisionException(BuckminsterException.createStatus(e));
		}
	}

	public void refresh() throws ProvisionException
	{
		for(Map.Entry<String, Query> entry : getRepository().getMirrors().getMirrors().entrySet())
			refreshMirror(IOUtils.uri(entry.getKey()), entry.getValue());
	}

	public void registerMirror(URI repositoryMirror, Query query) throws ProvisionException
	{
		if(getRepository().getMirrors().addMirror(repositoryMirror, query))
			refresh();
	}

	protected abstract void refreshMirror(URI repoURI, Query query) throws ProvisionException;

	protected abstract void writeRepository(OutputStream output) throws IOException;
}
