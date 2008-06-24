/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.osgi.util.NLS;
import org.jabsorb.client.HTTPSession;

/**
 * @author Thomas Hallgren
 */
public abstract class RemoteRepositoryFactory
{
	protected static final String PROP_CHANGE_NUMBER = "remote.changenumber";

	private static final Map<URI, IRepositoryServer> s_servers = new HashMap<URI, IRepositoryServer>();

	public static IRepositoryServer connect(URI location) throws ProvisionException
	{
		synchronized(s_servers)
		{
			IRepositoryServer server = s_servers.get(location);
			if(server != null)
				return server;

			HttpClient client = new HttpClient();
			HttpState httpState = new HttpState();
			client.setState(httpState);

			GetMethod method = null;
			String locationStr = location.toString();
			try
			{
				method = new GetMethod(locationStr);
				int status = client.executeMethod(method);
				if(status != HttpStatus.SC_OK)
					throw new IOException("Setup did not succeed");

				HTTPSession session = (HTTPSession)Activator.getRegistry().createSession(location.toString());
				session.setState(httpState);
				Client jsonClient = Client.create(session);
				server = (IRepositoryServer)jsonClient.openProxy(IRepositoryServer.SERVICE_NAME,
					IRepositoryServer.class);
				s_servers.put(location, server);
				return server;
			}
			catch(Exception e)
			{
				throw new ProvisionException(BuckminsterException.createStatus(e));
			}
			finally
			{
				if(method != null)
					method.releaseConnection();
			}
		}
	}

	protected static String getRepositoryName(URI repoURI)
	{
		return repoURI.getFragment();
	}

	protected static URI getServerURI(URI repoURI)
	{
		try
		{
			return new URI(repoURI.getScheme(), repoURI.getAuthority(), repoURI.getPath(),
				repoURI.getQuery(), null);
		}
		catch(URISyntaxException e)
		{
			return repoURI;
		}
	}

	protected File getCacheLocation(URI location)
	{
		File cacheArea = new File(org.eclipse.buckminster.p2.remote.Activator.getAgentLocation(), "cache");
		cacheArea.mkdir();
		UUID uuid;
		try
		{
			uuid = UUID.nameUUIDFromBytes(location.toASCIIString().getBytes("US-ASCII"));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
		return new File(cacheArea, uuid.toString());
	}

	protected abstract IRepositoryFacade getFacadeFromServer(IRepositoryServer server, String repoName)
	throws ProvisionException;

	protected IRepositoryFacade getRepositoryFacade(URI repoURI, IProgressMonitor monitor)
	throws ProvisionException
	{
		try
		{
			String repoName = getRepositoryName(repoURI);
			if(repoName == null)
				throw new FileNotFoundException(repoURI.toString());

			IRepositoryServer repoServer = connect(getServerURI(repoURI));
			return getFacadeFromServer(repoServer, repoName);
		}
		catch(ProvisionException e)
		{
			throw e;
		}
		catch(FileNotFoundException e)
		{
			String msg = NLS.bind(Messages.io_failedRead, repoURI);
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID,
				ProvisionException.REPOSITORY_NOT_FOUND, msg, e));
		}
	}
}
