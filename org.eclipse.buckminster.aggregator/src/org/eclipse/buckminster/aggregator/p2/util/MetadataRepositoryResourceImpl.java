package org.eclipse.buckminster.aggregator.p2.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
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
			setUser(true);
			setPriority(Job.SHORT);
		}

		public Exception getException()
		{
			return exception;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			exception = null;
			Buckminster bucky = Buckminster.getDefault();
			IMetadataRepositoryManager mdrMgr = null;
			MonitorUtils.begin(monitor, 100);
			try
			{
				mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
				IMetadataRepository repo = mdrMgr.loadRepository(location, MonitorUtils.subMonitor(monitor, 80));
				repository.setName(repo.getName());
				repository.setLocation(repo.getLocation());
				repository.setDescription(repo.getDescription());
				repository.setProvider(repo.getProvider());
				repository.setType(repo.getType());
				repository.setVersion(repo.getVersion());
				@SuppressWarnings("unchecked")
				Map<String, String> props = repo.getProperties();
				repository.getPropertyMap().putAll(props);

				Collector collector = repo.query(QUERY_ALL_IUS, new Collector(), MonitorUtils.subMonitor(monitor, 20));
				@SuppressWarnings("unchecked")
				Iterator<IInstallableUnit> itor = collector.iterator();
				while(itor.hasNext())
					repository.getInstallableUnits().add(InstallableUnitImpl.importToModel(itor.next()));

				repository.addRepositoryReferences(mdrMgr, repo);
			}
			catch(Exception e)
			{
				exception = e;
			}
			finally
			{
				bucky.ungetService(mdrMgr);
				MonitorUtils.done(monitor);
			}
			return Status.OK_STATUS;
		}
	}

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
		ResourceSet topSet = aggregator.eResource().getResourceSet();
		Resource mdr = topSet.getResource(URI.createGenericURI("p2", repositoryURI, null), true);
		List<EObject> contents = mdr.getContents();
		if(contents.size() != 1)
			return (MetadataRepository)contents.get(0);

		// We should normally never get to this point
		//
		throw new RuntimeException(String.format("Unable to load repository %s", repositoryURI));
	}

	public MetadataRepositoryResourceImpl(URI uri)
	{
		super(uri);
	}

	public void load(Map<?, ?> options) throws IOException
	{
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
			throw new Resource.IOWrappedException(e);
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

		MetadataRepositoryImpl repository = (MetadataRepositoryImpl)P2Factory.eINSTANCE.createMetadataRepository();
		RepositoryLoaderJob job = new RepositoryLoaderJob(repository, location);
		job.schedule();
		try
		{
			job.join();
			Exception e = job.getException();
			if(e != null)
				throw new Resource.IOWrappedException(e);

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

	public void save(Map<?, ?> options) throws IOException
	{
		// Let this be a no-op.
	}
}
