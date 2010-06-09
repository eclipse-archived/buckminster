/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.IRepositoryFacade;
import org.eclipse.buckminster.p2.remote.IRepositoryServer;
import org.eclipse.buckminster.p2.remote.change.RepositoryChange;
import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
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

/**
 * @author Thomas Hallgren
 */
public class RemoteMetadataRepositoryFactory extends RemoteRepositoryFactory implements
	IMetadataRepositoryFactory
{
	@SuppressWarnings("unchecked")
	public IMetadataRepository create(URL location, String name, String type, Map properties)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IMetadataRepository load(URL locationURL, IProgressMonitor monitor) throws ProvisionException
	{
		URI location = IOUtils.uri(locationURL);
		File localRepoCache = getCacheLocation(location);
		URI localRepoLoc = localRepoCache.toURI();
		File realLocation = LocalMetadataRepository.getActualLocation(IOUtils.url(localRepoLoc));

		MetadataRepositoryIO repositoryIO = new MetadataRepositoryIO();
		IMetadataRepository result;
		IRepositoryDataStream rds = null;
		IRepositoryFacade facade = getRepositoryFacade(location, monitor);

		InputStream input = null;
		try
		{
			try
			{
				input = new BufferedInputStream(new FileInputStream(realLocation));
				result = repositoryIO.read(IOUtils.url(realLocation), input, monitor);
			}
			catch(FileNotFoundException e)
			{
				rds = facade.getRepositoryData();
				input = new GZIPInputStream(new BufferedInputStream(new RemoteInputStream(rds)));
				result = repositoryIO.read(IOUtils.url(realLocation), input, monitor);
			}
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
			((LocalMetadataRepository)result).initializeAfterLoad(IOUtils.url(localRepoLoc));
		else if(result instanceof URLMetadataRepository)
			((URLMetadataRepository)result).initializeAfterLoad(IOUtils.url(localRepoLoc));

		if(rds != null)
			result.setProperty(PROP_CHANGE_NUMBER, Long.toString(rds.getLastChangeNumber()));
		else
		{
			// We found a cache so let's refresh it.
			//
			Object cns = result.getProperties().get(PROP_CHANGE_NUMBER);
			long cn = cns == null ? 0L : Long.parseLong(cns.toString());
			SynchronizationBlock sb = facade.getChanges(cn);
			for(RepositoryChange c : sb.getChanges())
				c.apply(result);
			result.setProperty(PROP_CHANGE_NUMBER, Long.toString(sb.getLastChangeNumber()));
		}
		return result;
	}

	public IStatus validate(URL location, IProgressMonitor monitor)
	{
		try
		{
			getRepositoryFacade(location.toURI(), monitor);
			return Status.OK_STATUS;
		}
		catch(CoreException e)
		{
			return e.getStatus();
		}
		catch(URISyntaxException e)
		{
			return BuckminsterException.createStatus(e);
		}
	}

	@Override
	protected IRepositoryFacade getFacadeFromServer(IRepositoryServer server, String repoName)
	throws ProvisionException
	{
		return server.getMetadataRepositoryFacade(repoName);
	}
}
