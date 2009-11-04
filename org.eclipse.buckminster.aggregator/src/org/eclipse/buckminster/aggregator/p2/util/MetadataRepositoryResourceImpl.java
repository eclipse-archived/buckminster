package org.eclipse.buckminster.aggregator.p2.util;

import static java.lang.String.format;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.Property;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.buckminster.aggregator.p2view.Bundle;
import org.eclipse.buckminster.aggregator.p2view.Categories;
import org.eclipse.buckminster.aggregator.p2view.Category;
import org.eclipse.buckminster.aggregator.p2view.Feature;
import org.eclipse.buckminster.aggregator.p2view.Fragment;
import org.eclipse.buckminster.aggregator.p2view.IUPresentation;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.buckminster.aggregator.p2view.OtherIU;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.Product;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;
import org.eclipse.buckminster.aggregator.util.TimeUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
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
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Query;

public class MetadataRepositoryResourceImpl extends ResourceImpl
{
	class RepositoryLoaderJob extends Job
	{
		private final MetadataRepositoryImpl repository;

		private final java.net.URI location;

		private boolean forceReload;

		private final MetadataRepositoryStructuredView repoView;

		private Exception exception;

		public RepositoryLoaderJob(MetadataRepositoryImpl repository, java.net.URI location, boolean forceReload,
				MetadataRepositoryStructuredView repoView)
		{
			super("Repository Loader");
			this.repository = repository;
			this.location = location;
			this.forceReload = forceReload;
			this.repoView = repoView;
			setUser(false);
			setSystem(true);
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
				long start = TimeUtils.getNow();
				IMetadataRepository repo;

				if(forceReload)
				{
					// This is a workaround - we need to clear the NotFound cahce to force
					// the MDR manager to fetch the repo again.
					if(mdrMgr.contains(location))
						// if the repo is known to MDR manager, it should be simply refreshed
						repo = mdrMgr.refreshRepository(location, subMon.newChild(80));
					else
					{
						// if the repo is not known to MDR manager, we call the refresh in order
						// to clear the NotFound cache only and we expect an exception to be thrown
						// due to unknown repository
						try
						{
							mdrMgr.refreshRepository(location, new NullProgressMonitor());
						}
						catch(ProvisionException e)
						{
							// this is expected - but the NotFound cache has been cleared
						}
						repo = mdrMgr.loadRepository(location, subMon.newChild(80));
					}
				}
				else
					repo = mdrMgr.loadRepository(location, subMon.newChild(80));

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

				createStructuredView();

				log.debug("Done. Took %s", TimeUtils.getFormattedDuration(start));
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

		private void createStructuredView()
		{
			repoView.setName(repository.getName());
			repoView.setInstallableUnitList(P2viewFactory.eINSTANCE.createInstallableUnits());

			Map<String, Map<Version, IUPresentation>> iuMap = new HashMap<String, Map<Version, IUPresentation>>();
			List<Category> categories = new ArrayList<Category>();
			List<Feature> features = new ArrayList<Feature>();
			List<Product> products = new ArrayList<Product>();
			List<Bundle> bundles = new ArrayList<Bundle>();
			List<Fragment> fragments = new ArrayList<Fragment>();
			List<OtherIU> miscellaneous = new ArrayList<OtherIU>();

			for(InstallableUnit iu : repository.getInstallableUnits())
			{
				IUPresentation iuPresentation;

				switch(iu.getType())
				{
				case CATEGORY:
					iuPresentation = P2viewFactory.eINSTANCE.createCategory(iu);
					if(!((Category)iuPresentation).isNested())
						categories.add((Category)iuPresentation);
					break;
				case FEATURE:
					iuPresentation = P2viewFactory.eINSTANCE.createFeature(iu);
					features.add((Feature)iuPresentation);
					break;
				case PRODUCT:
					iuPresentation = P2viewFactory.eINSTANCE.createProduct(iu);
					products.add((Product)iuPresentation);
					break;
				case BUNDLE:
					iuPresentation = P2viewFactory.eINSTANCE.createBundle(iu);
					bundles.add((Bundle)iuPresentation);
					break;
				case FRAGMENT:
					iuPresentation = P2viewFactory.eINSTANCE.createFragment(iu);
					fragments.add((Fragment)iuPresentation);
					break;
				default:
					iuPresentation = P2viewFactory.eINSTANCE.createOtherIU(iu);
					miscellaneous.add((OtherIU)iuPresentation);
				}

				iuPresentation.setId(iu.getId());
				iuPresentation.setVersion(iu.getVersion());

				String name = GeneralUtils.getLocalizedProperty(iu, IInstallableUnit.PROP_NAME);
				if(name == null || name.length() == 0)
					iuPresentation.setName(iu.getId());
				else
					iuPresentation.setName(name);

				if(name != null && name.startsWith("%"))
					name = null;

				if(iu.getType() == InstallableUnitType.CATEGORY || iu.getVersion() == null)
					iuPresentation.setLabel(name != null && name.length() > 0
							? name
							: iu.getId());
				else
					iuPresentation.setLabel(iu.getId() + " / " + iu.getVersion().toString()
							+ (name != null && name.length() > 0
									? " (" + name + ")"
									: ""));
				iuPresentation.setDescription(GeneralUtils.getLocalizedProperty(iu, IInstallableUnit.PROP_DESCRIPTION));

				Map<Version, IUPresentation> versionMap = iuMap.get(iu.getId());
				if(versionMap == null)
					iuMap.put(iu.getId(), versionMap = new HashMap<Version, IUPresentation>());
				versionMap.put(iu.getVersion(), iuPresentation);
			}

			if(categories.size() > 0)
			{
				Collections.sort(categories, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullCategoryContainer().getCategories().addAll(categories);
			}
			if(features.size() > 0)
			{
				Collections.sort(features, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullFeatureContainer().getFeatures().addAll(features);
			}
			if(products.size() > 0)
			{
				Collections.sort(products, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullProductContainer().getProducts().addAll(products);
			}
			if(bundles.size() > 0)
			{
				Collections.sort(bundles, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullBundleContainer().getBundles().addAll(bundles);
			}
			if(fragments.size() > 0)
			{
				Collections.sort(fragments, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullFragmentContainer().getFragments().addAll(fragments);
			}
			if(miscellaneous.size() > 0)
			{
				Collections.sort(miscellaneous, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullMiscellaneousContainer().getOthers().addAll(miscellaneous);
			}

			Categories categoryContainer = repoView.getInstallableUnitList().getCategoryContainer();
			if(categoryContainer != null)
				for(Category category : categoryContainer.getCategories())
					exploreCategory(category, iuMap);

			List<Property> propList = new ArrayList<Property>();
			for(Map.Entry<String, String> property : repository.getPropertyMap())
				propList.add(AggregatorFactory.eINSTANCE.createProperty(property.getKey(), property.getValue()));
			if(propList.size() > 0)
			{
				repoView.setProperties(P2viewFactory.eINSTANCE.createProperties());
				Collections.sort(propList);
				repoView.getProperties().getPropertyList().addAll(propList);
			}

			repoView.setLoaded(true);
			getContents().add(repoView);

			Aggregator aggregator = ResourceUtils.getAggregator(getResourceSet());
			if(aggregator != null)
			{
				for(MetadataRepositoryReference mdrReference : aggregator.getAllMetadataRepositoryReferences(true))
				{
					String refLocation = mdrReference.getLocation();
					if(refLocation != null && refLocation.endsWith("/"))
						refLocation = refLocation.substring(0, refLocation.length() - 1);
					if(repository.getLocation().toString().equals(refLocation))
						// force notification by formal setting the value to current value
						// once the adapter (if exists) receives the notification, it will take care of
						// refreshing labels and content of itself and its parents
						mdrReference.setLocation(mdrReference.getLocation());
				}
			}
		}

		private void exploreCategory(Category category, Map<String, Map<Version, IUPresentation>> iuMap)
		{
			List<Category> categories = new ArrayList<Category>();
			List<Feature> features = new ArrayList<Feature>();
			List<Product> products = new ArrayList<Product>();
			List<Bundle> bundles = new ArrayList<Bundle>();
			List<Fragment> fragments = new ArrayList<Fragment>();

			for(IRequiredCapability requiredCapability : category.getInstallableUnit().getRequiredCapabilityList())
			{
				VersionRange range = requiredCapability.getRange();
				if(!range.getMinimum().equals(range.getMaximum()) || !range.getIncludeMinimum()
						|| !range.getIncludeMaximum())
					continue;
				Map<Version, IUPresentation> iuCandidates = iuMap.get(requiredCapability.getName());
				if(iuCandidates == null)
					continue;

				IUPresentation iuPresentation = iuCandidates.get(range.getMinimum());
				if(iuPresentation == null)
					continue;

				if(iuPresentation instanceof Category)
				{
					categories.add((Category)iuPresentation);
					exploreCategory((Category)iuPresentation, iuMap);
				}
				else if(iuPresentation instanceof Feature)
					features.add((Feature)iuPresentation);
				else if(iuPresentation instanceof Product)
					products.add((Product)iuPresentation);
				else if(iuPresentation instanceof Fragment)
					fragments.add((Fragment)iuPresentation);
				else if(iuPresentation instanceof Bundle)
					bundles.add((Bundle)iuPresentation);
			}

			if(categories.size() > 0)
			{
				Collections.sort(categories, IUPresentation.COMPARATOR);
				category.getNotNullCategoryContainer().getCategories().addAll(categories);
			}
			if(features.size() > 0)
			{
				Collections.sort(features, IUPresentation.COMPARATOR);
				category.getNotNullFeatureContainer().getFeatures().addAll(features);
			}
			if(products.size() > 0)
			{
				Collections.sort(products, IUPresentation.COMPARATOR);
				category.getNotNullProductContainer().getProducts().addAll(products);
			}
			if(bundles.size() > 0)
			{
				Collections.sort(bundles, IUPresentation.COMPARATOR);
				category.getNotNullBundleContainer().getBundles().addAll(bundles);
			}
			if(fragments.size() > 0)
			{
				Collections.sort(fragments, IUPresentation.COMPARATOR);
				category.getNotNullFragmentContainer().getFragments().addAll(fragments);
			}
		}
	}

	@SuppressWarnings("serial")
	class UnknownStatusException extends Exception
	{
		UnknownStatusException(String message)
		{
			super(message);
		}
	}

	public static Resource getResourceForURI(String repositoryURI, Aggregator aggregator)
	{
		ResourceSet topSet = aggregator.eResource().getResourceSet();
		char c;
		if((c = repositoryURI.charAt(repositoryURI.length()-1)) == '/' || c == '\\')
			repositoryURI = repositoryURI.substring(0, repositoryURI.length()-1);

		URI repoURI = URI.createGenericURI("p2", repositoryURI, null);
		Resource mdr = null;

		synchronized(topSet)
		{
			mdr = topSet.getResource(repoURI, false);
			if(mdr == null)
				mdr = topSet.createResource(repoURI);
		}

		return mdr;
	}

	public static MetadataRepository loadRepository(String repositoryURI, Aggregator aggregator, boolean force)
	{
		Resource mdr = getResourceForURI(repositoryURI, aggregator);

		try
		{
			if(mdr != null)
			{
				if(force)
				{
					mdr.unload();
					mdr.load(Collections.emptyMap());
				}
				else if(!mdr.isLoaded())
					mdr.load(Collections.emptyMap());

				List<EObject> contents = mdr.getContents();
				if(contents.size() != 1
						|| ((MetadataRepositoryStructuredView)contents.get(0)).getMetadataRepository().getLocation() == null)
					throw new Exception(String.format("Unable to load repository %s", repositoryURI));

				return ((MetadataRepositoryStructuredView)contents.get(0)).getMetadataRepository();
			}
			else
				throw new Exception(String.format("Unable to obtain a resource for repository %s", repositoryURI));
		}
		catch(Exception e)
		{
			return null;
		}
	}

	private Exception m_lastException = null;

	private boolean m_forceReload = false;

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

		MetadataRepositoryImpl repository = (MetadataRepositoryImpl)P2Factory.eINSTANCE.createMetadataRepository();
		MetadataRepositoryStructuredView repoView = P2viewFactory.eINSTANCE.createMetadataRepositoryStructuredView(repository);
		RepositoryLoaderJob job = new RepositoryLoaderJob(repository, location, m_forceReload, repoView);
		try
		{
			boolean jobManagerReadyBeforeJobScheduled = !Job.getJobManager().isSuspended();
			job.schedule();
			job.join();

			Exception e = job.getException();
			if(e != null)
			{
				m_lastException = new Resource.IOWrappedException(e);
				return;
			}

			if(!jobManagerReadyBeforeJobScheduled)
				m_lastException = new UnknownStatusException(
						"Unknown repository status - loading job was scheduled while job manager was suspended");
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

	@Override
	protected void doUnload()
	{
		super.doUnload();
		m_forceReload = true;
	}
}
