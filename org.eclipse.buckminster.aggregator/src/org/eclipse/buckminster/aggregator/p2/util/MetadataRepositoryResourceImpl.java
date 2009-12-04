package org.eclipse.buckminster.aggregator.p2.util;

import static java.lang.String.format;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.ChildrenProvider;
import org.eclipse.buckminster.aggregator.InstallableUnitType;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.Property;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.loader.IRepositoryLoader;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
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
import org.eclipse.buckminster.aggregator.util.InstallableUnitUtils;
import org.eclipse.buckminster.aggregator.util.ResourceDiagnosticImpl;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;
import org.eclipse.buckminster.aggregator.util.TimeUtils;
import org.eclipse.buckminster.aggregator.util.TwoColumnMatrix;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;

public class MetadataRepositoryResourceImpl extends ResourceImpl implements StatusProvider
{
	class AsynchronousLoader extends Job
	{
		private Job m_replaceJob;

		private boolean m_force;

		public AsynchronousLoader(String name, Job replaceJob, boolean force)
		{
			super(name);
			m_replaceJob = replaceJob;
			m_force = force;
		}

		public boolean isForce()
		{
			return m_force;
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor)
		{
			class MonitorWatchDog extends Thread
			{
				private boolean m_done;

				@Override
				public void run()
				{
					while(!m_done)
					{
						if(monitor.isCanceled())
						{
							cancelLoadingJob();
							break;
						}

						try
						{
							Thread.sleep(100);
						}
						catch(InterruptedException e)
						{
							// ignore
						}
					}
				}

				public void setDone()
				{
					m_done = true;
				}
			}

			MonitorWatchDog watchDog = new MonitorWatchDog();

			try
			{
				if(m_replaceJob != null)
				{
					m_replaceJob.cancel();
					m_replaceJob.join();
				}

				watchDog.start();

				MetadataRepository mdr = loadRepository(m_force);

				IStatus status = org.eclipse.core.runtime.Status.OK_STATUS;

				if(monitor.isCanceled())
				{
					// cancelled by user
					status = org.eclipse.core.runtime.Status.CANCEL_STATUS;
					mdr = null;
				}

				synchronized(MetadataRepositoryResourceImpl.this)
				{
					String myLocation = getURI().toString();
					Aggregator aggregator = getAggregator();
					for(MetadataRepositoryReference repoRef : aggregator.getAllMetadataRepositoryReferences(true))
					{
						synchronized(repoRef)
						{
							String refLocation = repoRef.getNature() + ":" + repoRef.getResolvedLocation();
							if(myLocation.equals(refLocation))
							{
								repoRef.setMetadataRepository(mdr);
								repoRef.onRepositoryLoad();
							}
						}
					}
				}

				return status;
			}
			catch(InterruptedException e)
			{
				throw new RuntimeException("Repository load was interrupted");
			}
			finally
			{
				monitor.done();
				watchDog.setDone();

				synchronized(MetadataRepositoryResourceImpl.this)
				{
					if(m_asynchronousLoader == this)
						m_asynchronousLoader = null;
				}
			}
		}
	}

	class RepositoryLoaderJob extends Job
	{
		private final MetadataRepositoryImpl repository;

		private final java.net.URI location;

		private boolean forceReload;

		private final MetadataRepositoryStructuredView repoView;

		private Exception exception;

		private TwoColumnMatrix<IUPresentation, Object[]> allIUMatrix;

		public RepositoryLoaderJob(MetadataRepositoryImpl repository, java.net.URI location, boolean forceReload,
				MetadataRepositoryStructuredView repoView, TwoColumnMatrix<IUPresentation, Object[]> allIUMap)
		{
			super("Repository Loader");
			this.repository = repository;
			this.location = location;
			this.forceReload = forceReload;
			this.repoView = repoView;
			this.allIUMatrix = allIUMap;
			setUser(false);
			setSystem(false);
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
			Logger log = Buckminster.getLogger();
			String msg = format("Loading repository %s", location);
			SubMonitor subMon = SubMonitor.convert(monitor, msg, 100);
			try
			{
				m_loader.open(location, repository);
				log.debug(msg);
				long start = TimeUtils.getNow();

				// avoid resetting the task name when the loader converts the monitor to a SubMonitor
				// by suppressing the beginTask operation
				SubMonitor loaderMonitor = subMon.newChild(100, SubMonitor.SUPPRESS_BEGINTASK);

				if(!forceReload)
					m_loader.load(loaderMonitor);
				else
					m_loader.reload(loaderMonitor);

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
				try
				{
					m_loader.close();
				}
				catch(CoreException e)
				{
					exception = e;
					log.error(e, "Unable to close repository loader for %s", location);
				}

				MonitorUtils.done(subMon);
			}
			return org.eclipse.core.runtime.Status.OK_STATUS;
		}

		private void addIUsToMap(Object container, List<? extends IUPresentation> iuPresentations)
		{
			Object[] treePath = new Object[4];
			treePath[0] = MetadataRepositoryResourceImpl.this;
			treePath[1] = repoView;
			treePath[2] = repoView.getInstallableUnitList();
			treePath[3] = container;

			for(IUPresentation iup : iuPresentations)
				allIUMatrix.add(iup, treePath);
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

				switch(InstallableUnitUtils.getType(iu))
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

				if(InstallableUnitUtils.getType(iu) == InstallableUnitType.CATEGORY || iu.getVersion() == null)
					iuPresentation.setLabel(name != null && name.length() > 0
							? name
							: iu.getId());
				else
					iuPresentation.setLabel(iu.getId() + " / " + GeneralUtils.stringifyVersion(iu.getVersion())
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

				addIUsToMap(repoView.getInstallableUnitList().getCategoryContainer(), categories);
			}
			if(features.size() > 0)
			{
				Collections.sort(features, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullFeatureContainer().getFeatures().addAll(features);

				addIUsToMap(repoView.getInstallableUnitList().getFeatureContainer(), features);
			}
			if(products.size() > 0)
			{
				Collections.sort(products, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullProductContainer().getProducts().addAll(products);

				addIUsToMap(repoView.getInstallableUnitList().getProductContainer(), products);
			}
			if(bundles.size() > 0)
			{
				Collections.sort(bundles, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullBundleContainer().getBundles().addAll(bundles);

				addIUsToMap(repoView.getInstallableUnitList().getBundleContainer(), bundles);
			}
			if(fragments.size() > 0)
			{
				Collections.sort(fragments, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullFragmentContainer().getFragments().addAll(fragments);

				addIUsToMap(repoView.getInstallableUnitList().getFragmentContainer(), fragments);
			}
			if(miscellaneous.size() > 0)
			{
				Collections.sort(miscellaneous, IUPresentation.COMPARATOR);
				repoView.getInstallableUnitList().getNotNullMiscellaneousContainer().getOthers().addAll(miscellaneous);

				addIUsToMap(repoView.getInstallableUnitList().getMiscellaneousContainer(), miscellaneous);
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
			getContents().add((EObject)repoView);

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

			int idx = allIUMatrix.indexOf(category);
			Object[] oldTreePath = allIUMatrix.getValue(idx);
			int len = oldTreePath.length;
			Object[] categoryTreePath = new Object[len + 1];
			System.arraycopy(oldTreePath, 0, categoryTreePath, 0, len);
			categoryTreePath[len] = category;

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

				allIUMatrix.add(++idx, iuPresentation, categoryTreePath);
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

	private static final String NOTIFICATION_KEY = "notification";

	public static void cancelLoadRepository(String nature, String repositoryLocation, Aggregator aggregator)
	{
		Resource mdr = getResourceForNatureAndLocation(nature, repositoryLocation, aggregator);

		if(mdr instanceof MetadataRepositoryResourceImpl)
			((MetadataRepositoryResourceImpl)mdr).cancelLoadingJob();
	}

	public static Resource getResourceForNatureAndLocation(String nature, String repositoryLocation,
			Aggregator aggregator)
	{
		if(nature == null || repositoryLocation == null)
			return null;

		ResourceSet topSet = ((EObject)aggregator).eResource().getResourceSet();
		char c;
		if((c = repositoryLocation.charAt(repositoryLocation.length() - 1)) == '/' || c == '\\')
			repositoryLocation = repositoryLocation.substring(0, repositoryLocation.length() - 1);

		URI repoURI = getResourceUriForNatureAndLocation(nature, repositoryLocation);
		Resource mdr = null;

		synchronized(topSet)
		{
			mdr = topSet.getResource(repoURI, false);
			if(mdr == null)
				mdr = topSet.createResource(repoURI);
		}

		if(!(mdr instanceof MetadataRepositoryResourceImpl))
		{
			topSet.getResources().remove(mdr);
			mdr = null;
		}

		return mdr;
	}

	public static URI getResourceUriForNatureAndLocation(String nature, String location)
	{
		return URI.createGenericURI(nature, location, null);
	}

	private static Throwable unwrap(Exception loadException)
	{
		Throwable rootCauseCandidate = loadException;

		while(rootCauseCandidate.getCause() != null)
			rootCauseCandidate = rootCauseCandidate.getCause();

		return rootCauseCandidate;
	}

	private AsynchronousLoader m_asynchronousLoader;

	private IRepositoryLoader m_loader;

	private MetadataRepositoryStructuredView repoView;

	private final TwoColumnMatrix<IUPresentation, Object[]> allIUPresentationMatrix = new TwoColumnMatrix<IUPresentation, Object[]>();

	private Exception m_lastException = null;

	private boolean m_forceReload = false;

	private RepositoryLoaderJob m_loadingJob;

	private org.eclipse.emf.common.util.Diagnostic m_diagnostic;

	private Status m_status = AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK);

	public MetadataRepositoryResourceImpl(URI uri, IRepositoryLoader loader)
	{
		super(uri);
		m_loader = loader;
	}

	public synchronized void cancelLoadingJob()
	{
		if(m_loadingJob != null)
			m_loadingJob.cancel();
	}

	public Object[] findIUPresentation(Pattern iuIdPattern, VersionRange iuVersionRange, Object[] startAfterPath,
			boolean forward)
	{
		List<Object> firstNodePath = null;

		if(startAfterPath != null && startAfterPath[0] == this && startAfterPath.length > 1)
			firstNodePath = getFirstNode(startAfterPath, forward);
		else
			firstNodePath = getFirstNode(null, forward);

		if(firstNodePath == null)
			return null;

		List<Object> foundIUPath = findIU(firstNodePath, iuIdPattern, iuVersionRange, forward);

		return foundIUPath == null
				? null
				: foundIUPath.toArray();
	}

	// each IU is located in the structured view twice 1) under Categories node, 2) under Features (Bundles, ..) node
	// skipCategoriesSubTree filters out the Categories subtree
	public TwoColumnMatrix<IUPresentation, Object[]> findIUPresentations(Pattern iuIdPattern,
			VersionRange iuVersionRange, boolean skipCategoriesSubTree)
	{
		TwoColumnMatrix<IUPresentation, Object[]> found = new TwoColumnMatrix<IUPresentation, Object[]>();

		for(int i = 0; i < allIUPresentationMatrix.size(); i++)
		{
			IUPresentation iup = allIUPresentationMatrix.getKey(i);
			if(iup == null)
				continue;

			InstallableUnit iu = iup.getInstallableUnit();

			if(iuIdPattern.matcher(iu.getId()).find() && iuVersionRange.isIncluded(iu.getVersion()))
				if(!skipCategoriesSubTree || !(allIUPresentationMatrix.getValue(i)[2] instanceof Categories))
					found.add(iup, allIUPresentationMatrix.getValue(i));
		}

		return found;
	}

	public org.eclipse.emf.common.util.Diagnostic getDiagnostic()
	{
		return m_diagnostic;
	}

	public Exception getLastException()
	{
		return m_lastException;
	}

	public Status getStatus()
	{
		return m_status;
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

		MetadataRepositoryImpl repository = (MetadataRepositoryImpl)P2Factory.eINSTANCE.createMetadataRepository();

		repoView = P2viewFactory.eINSTANCE.createMetadataRepositoryStructuredView(repository);
		allIUPresentationMatrix.clear();

		m_loadingJob = new RepositoryLoaderJob(repository, location, m_forceReload, repoView, allIUPresentationMatrix);

		try
		{
			boolean jobManagerReadyBeforeJobScheduled = !Job.getJobManager().isSuspended();
			m_loadingJob.schedule();
			m_loadingJob.join();

			Exception e = m_loadingJob.getException();
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
			{
				if(options.containsKey(NOTIFICATION_KEY))
				{
					Notification notificationRef[] = (Notification[])options.get(NOTIFICATION_KEY);
					notificationRef[0] = notification;
				}
				else
					eNotify(notification);
			}
			setModified(false);
		}
	}

	public MetadataRepository loadRepository(boolean force)
	{
		Exception loadException = null;
		Map<String, Notification[]> notificationCollector = Collections.singletonMap(NOTIFICATION_KEY,
				new Notification[] { null });

		try
		{
			if(warnings != null)
				warnings.clear();

			if(errors != null)
				errors.clear();

			setStatus(AggregatorFactory.eINSTANCE.createStatus(StatusCode.WAITING));

			if(force)
			{
				unload();
				load(notificationCollector);
			}
			else if(!isLoaded())
				load(notificationCollector);

			if(getLastException() != null)
				throw getLastException();

			List<EObject> contents = getContents();
			if(contents.size() != 1
					|| ((MetadataRepositoryStructuredView)contents.get(0)).getMetadataRepository().getLocation() == null)
				throw new Exception(String.format("Unable to load repository %s", getURI().toString()));

			return ((MetadataRepositoryStructuredView)contents.get(0)).getMetadataRepository();
		}
		catch(Exception e)
		{
			loadException = e;
			return null;
		}
		finally
		{
			if(loadException != null)
			{
				getErrors().add(new ResourceDiagnosticImpl(loadException.getMessage(), getURI().toString()));
				String message = GeneralUtils.trimmedOrNull(loadException.getMessage());
				if(message == null && unwrap(loadException) instanceof OperationCanceledException)
					message = "Repository loading was cancelled";
				setStatus(AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN, message));
			}
			else
			{
				setStatus(AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK));
			}

			Notification notificationRef[] = notificationCollector.get(NOTIFICATION_KEY);
			Notification notification = notificationRef[0];
			if(notification != null && eNotificationRequired())
				eNotify(notification);
		}
	}

	public void save(Map<?, ?> options) throws IOException
	{
		// Let this be a no-op.
	}

	synchronized public void startAsynchronousLoad(boolean forceReload)
	{
		setStatus(AggregatorFactory.eINSTANCE.createStatus(StatusCode.WAITING));

		// force listeners to update status
		// TODO is this necessary?
		eNotify(setLoaded(isLoaded));

		String myLocation = getURI().toString();
		MetadataRepository myMDR;
		boolean mdrFinal = false;
		if(!forceReload && isLoaded() && !isLoading())
		{
			myMDR = loadRepository(false);
			mdrFinal = true;
		}
		else
			myMDR = null;

		Aggregator aggregator = getAggregator();
		for(MetadataRepositoryReference mdr : aggregator.getAllMetadataRepositoryReferences(true))
		{
			String refLocation = mdr.getNature() + ":" + mdr.getResolvedLocation();
			if(myLocation.equals(refLocation))
			{
				mdr.setMetadataRepository(myMDR);
				if(mdrFinal)
					mdr.onRepositoryLoad();
			}
		}

		ResourceUtils.cleanUpResources(aggregator, false);

		if(mdrFinal)
			return;

		AsynchronousLoader lastLoader = m_asynchronousLoader;

		if(lastLoader == null || forceReload && !lastLoader.isForce())
		{
			m_asynchronousLoader = new AsynchronousLoader("Loading " + myLocation, lastLoader, forceReload);
			m_asynchronousLoader.setUser(false);
			m_asynchronousLoader.schedule();
		}
	}

	@Override
	protected void doUnload()
	{
		super.doUnload();
		m_forceReload = true;
	}

	private List<Object> findIU(List<Object> nodePath, Pattern iuIdPattern, VersionRange iuVersionRange, boolean forward)
	{
		List<Object> foundInSubTreePath = null;

		if(forward)
			foundInSubTreePath = findIUInSubTree(nodePath, iuIdPattern, iuVersionRange);
		else
			foundInSubTreePath = findIUInNode(nodePath, iuIdPattern, iuVersionRange);

		if(foundInSubTreePath != null)
			return foundInSubTreePath;

		List<Object> nextNodePath = getNextNode(nodePath, forward);

		if(nextNodePath != null)
			return findIU(nextNodePath, iuIdPattern, iuVersionRange, forward);

		return null;
	}

	private List<Object> findIUInNode(List<Object> nodePath, Pattern iuIdPattern, VersionRange iuVersionRange)
	{
		Object node = nodePath.get(nodePath.size() - 1);

		if(node instanceof IUPresentation)
		{
			IUPresentation iup = (IUPresentation)node;
			InstallableUnit iu = iup.getInstallableUnit();
			if(iuIdPattern.matcher(iu.getId()).find() && iuVersionRange.isIncluded(iu.getVersion()))
				return nodePath;
		}

		return null;
	}

	private List<Object> findIUInSubTree(List<Object> nodePath, Pattern iuIdPattern, VersionRange iuVersionRange)
	{
		List<Object> foundIUPath = findIUInNode(nodePath, iuIdPattern, iuVersionRange);
		if(foundIUPath != null)
			return foundIUPath;

		Object node = nodePath.get(nodePath.size() - 1);

		if(node instanceof ChildrenProvider<?>)
		{
			for(Object child : ((ChildrenProvider<?>)node).getChildren())
			{
				List<Object> childPath = new ArrayList<Object>(nodePath);
				childPath.add(child);

				foundIUPath = findIUInSubTree(childPath, iuIdPattern, iuVersionRange);

				if(foundIUPath != null)
					return foundIUPath;
			}
		}

		return null;
	}

	private Aggregator getAggregator()
	{
		return (Aggregator)getResourceSet().getResources().get(0).getContents().get(0);
	}

	private List<Object> getFirstNode(Object[] startAfterPath, boolean forward)
	{
		List<Object> firstNodePath = new ArrayList<Object>();

		if(startAfterPath == null)
		{
			firstNodePath.add(this);
			firstNodePath.add(repoView);

			if(!forward)
				firstNodePath = getLastChild(firstNodePath);
		}
		else
			firstNodePath = getNextNode(Arrays.asList(startAfterPath), forward);

		return firstNodePath;
	}

	private List<Object> getLastChild(List<Object> nodePath)
	{
		if(!(nodePath.get(nodePath.size() - 1) instanceof ChildrenProvider<?>))
			return nodePath;

		EList<?> children = ((ChildrenProvider<?>)nodePath.get(nodePath.size() - 1)).getChildren();

		if(children == null || children.size() == 0)
			return nodePath;

		Object lastChild = children.get(children.size() - 1);

		List<Object> childPath = new ArrayList<Object>(nodePath);
		childPath.add(lastChild);

		return getLastChild(childPath);
	}

	private List<Object> getNextNode(List<Object> nodePath, boolean forward)
	{
		Object parent = nodePath.get(nodePath.size() - 2);

		if(forward)
		{
			if(parent instanceof ChildrenProvider<?>)
			{
				EList<?> children = ((ChildrenProvider<?>)parent).getChildren();
				int nodeIndex = children.indexOf(nodePath.get(nodePath.size() - 1));
				if(nodeIndex < (children.size() - 1))
				{
					List<Object> nextNodePath = new ArrayList<Object>(nodePath);
					nextNodePath.remove(nodePath.size() - 1);
					nextNodePath.add(children.get(nodeIndex + 1));

					return nextNodePath;
				}
			}

			List<Object> parentPath = new ArrayList<Object>(nodePath);
			parentPath.remove(nodePath.size() - 1);

			return parentPath.size() > 2
					? getNextNode(parentPath, forward)
					: null;
		}
		else
		{
			if(parent instanceof ChildrenProvider<?>)
			{
				EList<?> children = ((ChildrenProvider<?>)parent).getChildren();
				int nodeIndex = children.indexOf(nodePath.get(nodePath.size() - 1));
				if(nodeIndex > 0)
				{
					List<Object> nextNodePath = new ArrayList<Object>(nodePath);
					nextNodePath.remove(nodePath.size() - 1);
					nextNodePath.add(children.get(nodeIndex - 1));

					return getLastChild(nextNodePath);
				}
			}

			List<Object> parentPath = new ArrayList<Object>(nodePath);
			parentPath.remove(nodePath.size() - 1);

			return parentPath.size() > 2
					? parentPath
					: null;
		}
	}

	private void setStatus(Status status)
	{
		m_status = status;
	}
}
