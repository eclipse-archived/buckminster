/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.p2.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.buckminster.aggregator.loader.IRepositoryLoader;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Query;

public class P2RepositoryLoader implements IRepositoryLoader
{
	private URI m_location;

	private MetadataRepositoryImpl m_repository;

	private Buckminster m_bucky = Buckminster.getDefault();

	private IMetadataRepositoryManager mdrMgr;

	private static final Query QUERY_ALL_IUS = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			return candidate instanceof IInstallableUnit;
		}
	};

	public void close()
	{
		m_bucky.ungetService(mdrMgr);
	}

	public IArtifactRepository getArtifactRepository(IMetadataRepository mdr, IProgressMonitor monitor)
			throws CoreException
	{
		Buckminster bucky = Buckminster.getDefault();
		IArtifactRepositoryManager arMgr = null;

		try
		{
			arMgr = bucky.getService(IArtifactRepositoryManager.class);
			return arMgr.loadRepository(mdr.getLocation(), monitor);
		}
		finally
		{
			bucky.ungetService(arMgr);
		}
	}

	public void load(IProgressMonitor monitor) throws CoreException
	{
		load(monitor, false);
	}

	public void open(URI location, MetadataRepositoryImpl mdr) throws CoreException
	{
		m_location = location;
		m_repository = mdr;
		mdrMgr = m_bucky.getService(IMetadataRepositoryManager.class);
	}

	public void reload(IProgressMonitor monitor) throws CoreException
	{
		load(monitor, true);
	}

	@SuppressWarnings("unchecked")
	private void load(IProgressMonitor monitor, boolean avoidCache) throws CoreException
	{
		SubMonitor subMon = SubMonitor.convert(monitor, 100);

		IMetadataRepository repo = null;

		// This is a workaround. If aggregator editor is loading repositories too early
		// (while restoring the workbench before the framework starts up completely),
		// the SAX parser service may not be ready yet. If this is the case, up to 3 attempts
		// are made to obtain the service.
		for(int i = 2; i >= 0; i--)
		{
			try
			{
				if(avoidCache)
				{
					// This is a workaround - we need to clear the NotFound cahce to force
					// the MDR manager to fetch the repo again.
					if(mdrMgr.contains(m_location))
						// if the repo is known to MDR manager, it should be simply refreshed
						repo = mdrMgr.refreshRepository(m_location, subMon.newChild(80));
					else
					{
						// if the repo is not known to MDR manager, we call the refresh in order
						// to clear the NotFound cache only and we expect an exception to be thrown
						// due to unknown repository
						try
						{
							mdrMgr.refreshRepository(m_location, new NullProgressMonitor());
						}
						catch(ProvisionException e)
						{
							// this is expected - but the NotFound cache has been cleared
						}
						repo = mdrMgr.loadRepository(m_location, subMon.newChild(80));
					}
				}
				else
					repo = mdrMgr.loadRepository(m_location, subMon.newChild(80));

				break;
			}
			catch(ProvisionException e)
			{
				Throwable t = e.getCause();
				if(i > 0 && t instanceof IOException && t.getMessage() != null
						&& t.getMessage().contains("SAX parser service"))
				{
					Buckminster.getLogger().warning(
							"Error loading repository: %s Trying to recover (attempts remaining: %d)", t.getMessage(),
							i);
					try
					{
						Thread.sleep(1000 * (3 - i));
					}
					catch(InterruptedException ie)
					{
						// ignore
					}
					continue;
				}

				throw (e);
			}
		}

		m_repository.setName(repo.getName());
		m_repository.setLocation(repo.getLocation());
		m_repository.setDescription(repo.getDescription());
		m_repository.setProvider(repo.getProvider());
		m_repository.setType(repo.getType());
		m_repository.setVersion(repo.getVersion());
		m_repository.getPropertyMap().putAll(repo.getProperties());

		Collector collector = repo.query(QUERY_ALL_IUS, new Collector(), subMon.newChild(20));
		Iterator<IInstallableUnit> itor = collector.iterator();
		ArrayList<InstallableUnit> ius = new ArrayList<InstallableUnit>();
		while(itor.hasNext())
			ius.add(InstallableUnitImpl.importToModel(itor.next()));
		Collections.sort(ius);
		m_repository.getInstallableUnits().addAll(ius);

		m_repository.addRepositoryReferences(mdrMgr, repo);
	}

}
