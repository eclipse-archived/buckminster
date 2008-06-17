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
import java.util.Hashtable;
import java.util.Map;

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
import org.osgi.framework.Filter;
import org.osgi.framework.Version;

/**
 * @author Thomas Hallgren
 */
public class MetadataRepositoryFacade extends RepositoryFacade
{
	private static final class LDAPQuery extends Query
	{
		private final Filter m_filter;

		public LDAPQuery(Filter filter)
		{
			m_filter = filter;
		}

		@Override
		public boolean isMatch(Object candidate)
		{
			if(!(candidate instanceof IInstallableUnit))
				return false;

			IInstallableUnit iu = (IInstallableUnit)candidate;
			Hashtable<String, String> filterProps = new Hashtable<String, String>();
			filterProps.putAll(getIUProperties(iu));
			filterProps.put("id", iu.getId());

			Version version = iu.getVersion();
			if(version != null)
				filterProps.put("version", version.toString());

			return m_filter.match(filterProps);
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getIUProperties(IInstallableUnit iu)
	{
		return iu.getProperties();
	}

	public MetadataRepositoryFacade(String name, LoggingMetadataRepository repository)
	{
		super(name, repository);
	}

	@Override
	protected void refreshMirror(URI uri, Filter filter) throws ProvisionException
	{
		Query query;
		if(filter != null)
			query = new LDAPQuery(filter);
		else
		{
			query = new Query()
			{
				@Override
				public boolean isMatch(Object candidate)
				{
					return true;
				}
			};
		}

		IMetadataRepository source = ProvisioningHelper.getMetadataRepository(RepositoryServer.url(uri));
		if(source == null)
			throw new ProvisionException(new Status(IStatus.ERROR, Activator.ID, NLS.bind(
				Messages.noSuchArtifactRepository, uri), null));

		Collector result = source.query(query, new Collector(), null);
		((IMetadataRepository)getRepository()).addInstallableUnits((IInstallableUnit[])result.toArray(IInstallableUnit.class));
	}

	@Override
	protected void writeRepository(OutputStream output) throws IOException
	{
		new MetadataRepositoryIO().write((IMetadataRepository)getRepository(), output);
	}
}
