/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.eclipse.buckminster.p2.remote.Activator;
import org.eclipse.buckminster.p2.remote.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.p2.metadata.repository.MetadataRepositoryIO;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MetadataRepositoryFacade extends RepositoryFacade
{
	private static final Query s_matchAll = new Query()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			return true;
		}
	};

	public MetadataRepositoryFacade(String name, LoggingMetadataRepository repository)
	{
		super(name, repository);
	}

	@Override
	protected void refreshMirror(URI uri, Query query) throws ProvisionException
	{
		if(query == null)
			query = s_matchAll;

		IMetadataRepository source = ProvisioningHelper.getMetadataRepository(RepositoryServer.url(uri));
		if(source == null)
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID, NLS.bind(
				Messages.noSuchMetadataRepository, uri), null));

		Collector result = source.query(query, new Collector(), null);
		((IMetadataRepository)getRepository()).addInstallableUnits((IInstallableUnit[])result.toArray(IInstallableUnit.class));
	}

	@Override
	protected void writeRepository(OutputStream output) throws IOException
	{
		new MetadataRepositoryIO().write((IMetadataRepository)getRepository(), output);
	}
}
