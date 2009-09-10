package org.eclipse.buckminster.aggregator.p2.util;

import static java.lang.String.format;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

public class MetadataRepositoryResourceImpl extends ResourceImpl
{
	class RepositoryLoaderJob extends Job
	{
		private final MetadataRepositoryImpl repository;

		private final java.net.URI location;

		private Exception exception;

		public RepositoryLoaderJob(MetadataRepositoryImpl repository, java.net.URI location)
		{
			super("Repository Loader");
			this.repository = repository;
			this.location = location;
			setUser(false);
			setPriority(Job.SHORT);
		}

		public Exception getException()
		{
			return exception;
		}

		@Override
		@SuppressWarnings("unchecked")
		protected IStatus run(IProgressMonitor monitor)
		{
			exception = null;
			Buckminster bucky = Buckminster.getDefault();
			Logger log = Buckminster.getLogger();
			IMetadataRepositoryManager mdrMgr = null;
			SubMonitor subMon = SubMonitor.convert(monitor, 100);
			try
			{
				mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
				String msg = format("Loading repository %s", location);
				log.debug(msg);
				subMon.setTaskName(msg);
				long start = System.currentTimeMillis();
				IMetadataRepository repo;
				try
				{
					repo = mdrMgr.loadRepository(location, subMon.newChild(80));
				}
				catch(ProvisionException e)
				{
					if(!mdrMgr.contains(location))
						throw e;
					repo = mdrMgr.refreshRepository(location, subMon.newChild(80));
				}
				repository.setName(repo.getName());
				repository.setLocation(repo.getLocation());
				repository.setDescription(repo.getDescription());
				repository.setProvider(repo.getProvider());
				repository.setType(repo.getType());
				repository.setVersion(repo.getVersion());
				repository.getPropertyMap().putAll(repo.getProperties());

				Collector collector = repo.query(QUERY_ALL_IUS, new Collector(), subMon.newChild(20));
				Iterator<IInstallableUnit> itor = collector.iterator();
				ArrayList<InstallableUnit> ius = new ArrayList<InstallableUnit>();
				while(itor.hasNext())
					ius.add(InstallableUnitImpl.importToModel(itor.next()));
				Collections.sort(ius);
				repository.getInstallableUnits().addAll(ius);

				repository.addRepositoryReferences(mdrMgr, repo);
				log.debug("Done. Took %d millisecs", Long.valueOf(System.currentTimeMillis() - start));
			}
			catch(Exception e)
			{
				exception = e;
				log.error(e, "Unable to load repository %s", location);
			}
			finally
			{
				bucky.ungetService(mdrMgr);
				MonitorUtils.done(subMon);
			}
			return Status.OK_STATUS;
		}
	}

	private Exception m_lastException = null;

	public static final Query QUERY_ALL_IUS = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			return candidate instanceof IInstallableUnit;
		}
	};

	public static MetadataRepository loadRepository(String repositoryURI, Aggregator aggregator)
	{
		return loadRepository(repositoryURI, aggregator, false);
	}

	public static MetadataRepository loadRepository(String repositoryURI, Aggregator aggregator, boolean force)
	{
		ResourceSet topSet = aggregator.eResource().getResourceSet();
		URI repoURI = URI.createGenericURI("p2", repositoryURI, null);
		Resource mdr = topSet.getResource(repoURI, !force);

		if(mdr == null)
		{
			force = false;
			mdr = topSet.getResource(repoURI, true);
		}

		try
		{
			if(mdr != null)
			{
				if(force)
				{
					mdr.unload();
					mdr.load(Collections.emptyMap());
				}

				List<EObject> contents = mdr.getContents();
				if(contents.size() != 1 || ((MetadataRepository)contents.get(0)).getLocation() == null)
					throw new Exception(String.format("Unable to load repository %s", repositoryURI));

				return (MetadataRepository)contents.get(0);
			}
			else
				throw new Exception(String.format("Unable to obtain a resource for repository %s", repositoryURI));
		}
		catch(Exception e)
		{
			if(mdr != null)
				topSet.getResources().remove(mdr);
			return null;
		}
	}

	public MetadataRepositoryResourceImpl(URI uri)
	{
		super(uri);
	}

	public Exception getLastException()
	{
		return m_lastException;
	}

	public void load(Map<?, ?> options) throws IOException
	{
		m_lastException = null;

		if(isLoaded)
			return;

		Notification notification = setLoaded(true);
		java.net.URI location;
		try
		{
			location = new java.net.URI(getURI().opaquePart());
		}
		catch(URISyntaxException e)
		{
			m_lastException = new Resource.IOWrappedException(e);
			return;
		}

		isLoading = true;
		if(errors != null)
		{
			errors.clear();
		}

		if(warnings != null)
		{
			warnings.clear();
		}

		P2Factory factory = P2Factory.eINSTANCE;
		synchronized(factory)
		{
			MetadataRepositoryImpl repository = (MetadataRepositoryImpl)factory.createMetadataRepository();
			RepositoryLoaderJob job = new RepositoryLoaderJob(repository, location);
			job.schedule();
			try
			{
				job.join();
				Exception e = job.getException();
				if(e != null)
				{
					m_lastException = new Resource.IOWrappedException(e);
					return;
				}

				getContents().add(repository);
			}
			catch(InterruptedException e)
			{
			}
			finally
			{
				isLoading = false;
				if(notification != null)
					eNotify(notification);
				setModified(false);
			}
		}
	}

	public void save(Map<?, ?> options) throws IOException
	{
		// Let this be a no-op.
	}
}
