/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryIO;
import org.eclipse.equinox.internal.p2.metadata.repository.URLMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.IMetadataRepositoryFactory;
import org.eclipse.osgi.util.NLS;
import org.jabsorb.client.ErrorResponse;
import org.jabsorb.client.HTTPSession;

public class RemoteMetadataRepositoryFactory implements IMetadataRepositoryFactory
{
	public static IRepositoryServer connect(URI location) throws ProvisionException
	{
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
			return (IRepositoryServer)jsonClient.openProxy(IRepositoryServer.SERVICE_NAME,
				IRepositoryServer.class);
		}
		catch(Exception e)
		{
			throw new ProvisionException(BuckminsterException.wrap(e).getStatus());
		}
		finally
		{
			if(method != null)
				method.releaseConnection();
		}

	}

	@SuppressWarnings("unchecked")
	public IMetadataRepository create(URL location, String name, String type, Map properties)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IMetadataRepository load(URL location, IProgressMonitor monitor) throws ProvisionException
	{
		InputStream input = null;
		IMetadataRepository result;
		try
		{
			input = new GZIPInputStream(new RemoteInputStream(
				getRepositoryFacade(location, monitor).getRepositoryData()));
			result = new MetadataRepositoryIO().read(location, input, monitor);
		}
		catch(IOException e)
		{
			String msg = NLS.bind(Messages.io_failedRead, location);
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID,
				ProvisionException.REPOSITORY_FAILED_READ, msg, e));
		}
		finally
		{
			IOUtils.close(input);
		}

		if(result instanceof LocalMetadataRepository)
			((LocalMetadataRepository)result).initializeAfterLoad(location);
		if(result instanceof URLMetadataRepository)
			((URLMetadataRepository)result).initializeAfterLoad(location);
		return result;
	}

	public IStatus validate(URL location, IProgressMonitor monitor)
	{
		try
		{
			getRepositoryFacade(location, monitor);
			return Status.OK_STATUS;
		}
		catch(CoreException e)
		{
			return e.getStatus();
		}
	}

	private IRepositoryFacade getRepositoryFacade(URL location, IProgressMonitor monitor)
	throws ProvisionException
	{
		try
		{
			URI repoURI = location.toURI();
			String path = repoURI.getPath();
			String repoName = repoURI.getFragment();
			if(repoName == null || !path.endsWith("content.json"))
				throw new FileNotFoundException(repoURI.toString());

			IRepositoryServer repoServer = connect(new URI(repoURI.getScheme(), repoURI.getAuthority(), path,
				repoURI.getQuery(), null));
			return repoServer.getMetadataRepositoryFacade(repoName);
		}
		catch(ErrorResponse e)
		{
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID,
				ProvisionException.REPOSITORY_FAILED_READ, e.getMessage(), null));
		}
		catch(ProvisionException e)
		{
			throw e;
		}
		catch(FileNotFoundException e)
		{
			String msg = NLS.bind(Messages.io_failedRead, location);
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID,
				ProvisionException.REPOSITORY_NOT_FOUND, msg, e));
		}
		catch(Exception e)
		{
			String msg = NLS.bind(Messages.io_failedRead, location);
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID,
				ProvisionException.REPOSITORY_FAILED_READ, msg, e));
		}
	}
}
