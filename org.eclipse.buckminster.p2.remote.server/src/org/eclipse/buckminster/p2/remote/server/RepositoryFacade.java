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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.core.repository.IRepository;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

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

	public SynchronizationBlock getChanges(long sequenceNumber, boolean refresh) throws CoreException
	{
		if(refresh)
			refresh();
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

	public IRepositoryDataStream getRepositoryData() throws IOException
	{
		final PipedInputStream input = new PipedInputStream();
		OutputStream output = new GZIPOutputStream(new BufferedOutputStream(new PipedOutputStream(input),
			0x8000));
		String oldTimestamp = (String)m_repository.getProperties().get(IRepository.PROP_TIMESTAMP);
		m_repository.setProperty(IRepository.PROP_TIMESTAMP, Long.toString(System.currentTimeMillis()));
		try
		{
			writeRepository(output);
		}
		finally
		{
			m_repository.setProperty(IRepository.PROP_TIMESTAMP, oldTimestamp);
		}
		return new RepositoryDataStream(input, m_repository.getChangeLog().getLastChangeNumber());
	}

	public void refresh() throws ProvisionException
	{
		Map<String, String> props = getProperties();
		for(Map.Entry<String, String> entry : props.entrySet())
		{
			String key = entry.getKey();
			if(key.startsWith(PROP_MIRROR_PREFIX))
			{
				String value = entry.getValue();
				int splitIdx = value.indexOf(' ');
				String uriStr;
				Filter filter = null;
				try
				{
					if(splitIdx >= 0)
					{
						uriStr = value.substring(0, splitIdx);
						filter = FrameworkUtil.createFilter(value.substring(splitIdx + 1));
					}
					else
						uriStr = value;
					refreshMirror(new URI(uriStr), filter);
				}
				catch(InvalidSyntaxException e)
				{
					throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
				}
				catch(URISyntaxException e)
				{
					throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
				}
			}
		}
	}

	public void registerMirror(URI repositoryMirror, String ldapFilter) throws ProvisionException
	{
		String uriStr = repositoryMirror.toString();
		String fullValue = uriStr;
		if(ldapFilter != null)
		{
			// Verify that the filter is syntactically correct
			//
			try
			{
				FrameworkUtil.createFilter(ldapFilter);
			}
			catch(InvalidSyntaxException e)
			{
				throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
			}
			fullValue = fullValue + ' ' + ldapFilter;
		}

		List<String> mirrors = null;
		Map<String, String> props = getProperties();
		for(Map.Entry<String, String> entry : props.entrySet())
		{
			String key = entry.getKey();
			if(key.startsWith(PROP_MIRROR_PREFIX))
			{
				String value = entry.getValue();
				if(value.startsWith(uriStr) && value.length() == uriStr.length()
					|| value.charAt(uriStr.length()) == ' ')
				{
					// We've found an old entry for the same URI.
					//
					if(!value.equals(fullValue))
						m_repository.setProperty(key, fullValue);
					return;
				}
				if(mirrors == null)
					mirrors = new ArrayList<String>();
				int mirrorNumber = Integer.parseInt(key.substring(PROP_MIRROR_PREFIX.length()));
				while(mirrors.size() <= mirrorNumber)
					mirrors.add(null);
				mirrors.set(mirrorNumber, value);
			}
		}

		String prop;
		if(mirrors == null)
			prop = PROP_MIRROR_PREFIX + '0';
		else
		{
			// Use first free entry
			//
			int top = mirrors.size();
			int idx;
			for(idx = 0; idx < top; ++idx)
				if(mirrors.get(idx) == null)
					break;
			prop = PROP_MIRROR_PREFIX + idx;
		}
		m_repository.setProperty(prop, fullValue);
	}

	protected abstract void refreshMirror(URI repoURI, Filter filter) throws ProvisionException;

	protected abstract void writeRepository(OutputStream output) throws IOException;
}
