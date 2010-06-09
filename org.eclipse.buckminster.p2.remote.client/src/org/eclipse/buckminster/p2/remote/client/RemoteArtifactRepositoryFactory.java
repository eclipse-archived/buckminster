/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepository;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryIO;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class RemoteArtifactRepositoryFactory extends RemoteRepositoryFactory implements
	org.eclipse.equinox.internal.provisional.spi.p2.artifact.repository.IArtifactRepositoryFactory
{
	@SuppressWarnings("unchecked")
	public IArtifactRepository create(URL location, String name, String type, Map properties)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IArtifactRepository load(URL locationURL, IProgressMonitor monitor) throws ProvisionException
	{
		URI location = IOUtils.uri(locationURL);
		InputStream input = null;
		IArtifactRepository result;
		try
		{
			IRepositoryFacade facade = getRepositoryFacade(location, monitor);
			input = new GZIPInputStream(new RemoteInputStream(facade.getRepositoryData()));
			result = new SimpleArtifactRepositoryIO().read(IOUtils.url(location), input, monitor);
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

		if(result instanceof SimpleArtifactRepository)
			((SimpleArtifactRepository)result).initializeAfterLoad(IOUtils.url(location));
		return result;
	}

	@Override
	protected IRepositoryFacade getFacadeFromServer(IRepositoryServer server, String repoName)
	throws ProvisionException
	{
		return server.getArtifactRepositoryFacade(repoName);
	}
}
