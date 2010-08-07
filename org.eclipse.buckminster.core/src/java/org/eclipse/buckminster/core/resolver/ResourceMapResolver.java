/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.helpers.FibonacciMonitorWrapper;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.builder.ResolutionBuilder;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.URLCatalogReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapConstants;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class ResourceMapResolver extends LocalResolver implements IJobChangeListener, IResolver {

	public static final String TAGGED_PREFIX = "tagged."; //$NON-NLS-1$

	public static final String COMPONENT_NAME_PARAM = TAGGED_PREFIX + "name"; //$NON-NLS-1$

	public static final String COMPONENT_VERSION_PARAM = TAGGED_PREFIX + "version"; //$NON-NLS-1$

	public static final String BRANCH_PARAM = TAGGED_PREFIX + "branch"; //$NON-NLS-1$

	public static final String TAG_PARAM = TAGGED_PREFIX + "tag"; //$NON-NLS-1$

	public static final String REVISION_PARAM = TAGGED_PREFIX + "revision"; //$NON-NLS-1$

	public static final String TIMESTAMP_PARAM = TAGGED_PREFIX + "timestamp"; //$NON-NLS-1$

	public static final String OS_PARAM = TAGGED_PREFIX + "os"; //$NON-NLS-1$

	public static final String WS_PARAM = TAGGED_PREFIX + "ws"; //$NON-NLS-1$

	public static final String ARCH_PARAM = TAGGED_PREFIX + "arch"; //$NON-NLS-1$

	public static final String NL_PARAM = TAGGED_PREFIX + "nl"; //$NON-NLS-1$

	public static Resolution createResolution(URIMatcher matcher, ProviderMatch pm) throws CoreException {
		Map<String, String> matchMap = pm.getMatcherMap();
		if (matchMap == null)
			return null;

		CSpecBuilder bld = new CSpecBuilder();
		bld.setName(matchMap.get(COMPONENT_NAME_PARAM));
		String tmp = matchMap.get(COMPONENT_VERSION_PARAM);
		if (tmp != null) {
			try {
				bld.setVersion(matcher.getVersionFormat().parse(tmp));
			} catch (IllegalArgumentException e) {
				throw BuckminsterException.wrap(e);
			}
		}

		IComponentType ctype = pm.getComponentType();
		bld.setComponentTypeID(ctype.getId());
		bld.setFilter(getFilter(matchMap));

		try {
			IFileInfo info = DownloadManager.readInfo(URLUtils.normalizeToURL(pm.getRepositoryURI()), null);
			NodeQuery nq = pm.getNodeQuery();
			ResolutionBuilder resBld = new ResolutionBuilder(bld);
			resBld.getRequest().initFrom(nq.getComponentRequest());
			resBld.setAttributes(nq.getRequiredAttributes());
			resBld.setProvider(pm.getProvider());
			resBld.setRepository(pm.getProvider().getURI(nq.getProperties()));
			resBld.setComponentTypeId(ctype.getId());
			resBld.setVersionMatch(pm.getVersionMatch());
			resBld.setFileInfo(info);
			return new Resolution(resBld);
		} catch (FileNotFoundException e) {
			return null;
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public static ProviderMatch getMatch(URIMatcher matcher, Provider provider, NodeQuery query, IProgressMonitor monitor) throws CoreException {
		Logger logger = CorePlugin.getLogger();
		ComponentRequest cq = query.getComponentRequest();
		VersionMatch candidate = null;
		Map<String, String> candidateMap = null;

		URL baseURL;
		try {
			baseURL = new URL(ExpandingProperties.expand(query.getProperties(), matcher.getBase(), 0));
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}

		for (URL urlToMatch : URLCatalogReaderType.list(baseURL, query.getComponentQuery().getConnectContext(), monitor)) {
			Map<String, String> matchMap = matcher.getMatchMap(urlToMatch.toString());
			if (matchMap == null)
				continue;

			String matchedName = matchMap.get(COMPONENT_NAME_PARAM);
			if (!cq.getName().equals(matchedName)) {
				logger.debug("URI name %s does not match %s", matchedName, cq.getName()); //$NON-NLS-1$
				continue;
			}

			Version version = null;
			String tmp = matchMap.get(COMPONENT_VERSION_PARAM);
			if (tmp != null) {
				try {
					IVersionFormat fmt = matcher.getVersionFormat();
					if (fmt == null)
						fmt = VersionHelper.getVersionType(matcher.getVersionType()).getFormat();
					version = fmt.parse(tmp);
					VersionRange vd = cq.getVersionRange();
					if (!(vd == null || vd.isIncluded(version))) {
						logger.debug("URI version %s is not designated by %s", version, vd); //$NON-NLS-1$
						continue;
					}
				} catch (IllegalArgumentException e) {
					logger.warning(e, e.getMessage());
					continue;
				}
			}

			Filter filter = getFilter(matchMap);
			if (!(filter == null || filter.matchCase(query.getProperties()))) {
				logger.debug("URI filter %s does not match current environment", filter); //$NON-NLS-1$
				continue;
			}

			VersionSelector vs = null;
			tmp = matchMap.get(BRANCH_PARAM);
			if (tmp != null)
				vs = VersionSelector.branch(tmp);
			else {
				tmp = matchMap.get(TAG_PARAM);
				if (tmp != null)
					vs = VersionSelector.tag(tmp);
			}

			long revision = -1;
			tmp = matchMap.get(REVISION_PARAM);
			if (tmp != null) {
				try {
					revision = Long.parseLong(tmp);
				} catch (NumberFormatException e) {
					logger.warning(e, e.getMessage());
				}
			}

			Date timestamp = null;
			tmp = matchMap.get(TIMESTAMP_PARAM);
			if (tmp != null) {
				try {
					timestamp = DateAndTimeUtils.fromString(tmp);
				} catch (ParseException e) {
					logger.warning(e, e.getMessage());
				}
			}

			VersionMatch vm = new VersionMatch(version, vs, revision, timestamp, null);
			if (candidate == null || query.compare(vm, candidate) > 0) {
				// Verify that the URI created using this matchMap is readable
				//
				candidate = vm;
				candidateMap = matchMap;
			}
		}
		if (candidate == null)
			return null;

		query = query.getContext().getNodeQuery(query.getQualifiedDependency());
		query = new NodeQuery(query, candidateMap);
		ProviderMatch pm = new ProviderMatch(provider, CorePlugin.getDefault().getComponentType(matcher.getComponentType()), candidate, query);
		pm.setMatcherMap(candidateMap);
		return pm;
	}

	public static Provider immutableProvider(String readerType, String componentType, String uri, Filter resolutionFilter) {
		Provider provider = RmapFactory.eINSTANCE.createProvider();
		provider.setReaderType(readerType);
		provider.getComponentTypes().add(componentType);
		Format format = CommonFactory.eINSTANCE.createFormat();
		format.setFormat(uri);
		provider.setURI(format);
		provider.setResolutionFilter(resolutionFilter);
		Map<String, String> props = provider.getProperties();
		props.put(RmapConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		props.put(RmapConstants.IS_SOURCE, "false"); //$NON-NLS-1$
		return provider;
	}

	/**
	 * Returns true if this provider is a match for the given <code>query</code>
	 * with respect to provided properties. The method will update the filter
	 * attributes map of the query context.
	 * 
	 * @param The
	 *            query to match
	 * @param A
	 *            one element array that will receive the failing filter. Can be
	 *            <code>null</code>.
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public static boolean isFilterMatchFor(NodeQuery query, Filter resFilter) {
		if (resFilter == null)
			return true;

		Map<String, String[]> attributeUsageMap = query.getContext().getFilterAttributeUsageMap();
		resFilter.addConsultedAttributes(attributeUsageMap);
		return resFilter.matchCase(query.getProperties());
	}

	static ProviderMatch findMatch(Provider provider, NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor) throws CoreException {
		String readerType = provider.getReaderType();
		String providerURI = getProviderURI(query, provider);
		ProviderScore score = query.getProviderScore(provider.isMutable(), provider.isSource());
		if (score == ProviderScore.REJECTED) {
			ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
					Messages.Score_is_below_threshold);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
			return null;
		}

		URIMatcher uriMatcher = provider.getMatcher();
		if (uriMatcher != null)
			return getMatch(uriMatcher, provider, query, monitor);

		IVersionFinder versionFinder = null;
		monitor.beginTask(null, 120);
		try {
			ComponentRequest request = query.getComponentRequest();
			String componentTypeID = request.getComponentTypeID();

			// The component request is equipped with a component type. It must
			// match the types that this provider provides.
			//
			List<String> componentTypes = provider.getComponentTypes();
			if (componentTypeID != null) {
				boolean found = false;
				int idx = componentTypes.size();
				while (--idx >= 0) {
					String ctype = componentTypes.get(idx);
					if (ctype.equals(componentTypeID)) {
						// Limit the component types to this one type
						//
						componentTypes = Collections.singletonList(ctype);
						found = true;
						break;
					}
				}

				if (!found) {
					// The ECLIPSE_PLATFORM reader is silent here since it is
					// always consulted
					//
					if (!provider.getReaderType().equals(IReaderType.ECLIPSE_PLATFORM)) {
						ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
								String.format(NLS.bind(Messages.Components_of_type_0_are_not_supported, componentTypeID)));
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
					}
					return null;
				}
			}

			VersionMatch candidate = null;
			IComponentType ctypeUsed = null;
			CoreException problem = null;
			try {
				for (String ctypeId : componentTypes) {
					try {
						CorePlugin plugin = CorePlugin.getDefault();
						IComponentType ctype = plugin.getComponentType(ctypeId);
						versionFinder = plugin.getReaderType(provider.getReaderType()).getVersionFinder(provider, ctype, query,
								MonitorUtils.subMonitor(monitor, 20));
						candidate = versionFinder.getBestVersion(MonitorUtils.subMonitor(monitor, 80));
						if (candidate == null)
							continue;
						ctypeUsed = ctype;
					} catch (MissingCSpecSourceException e) {
						continue;
					}
					break;
				}
			} catch (CoreException e) {
				problem = e;
			}

			if (candidate == null) {
				ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
						Messages.No_component_match_was_found);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), problem == null ? null
						: BuckminsterException.unwind(problem)));
				return null;
			}
			query.logDecision(ResolverDecisionType.MATCH_FOUND, candidate);
			return versionFinder.getProviderMatch(candidate, ctypeUsed, score);
		} finally {
			if (versionFinder != null)
				versionFinder.close();
			monitor.done();
		}
	}

	static ProviderMatch getProvider(SearchPath searchPath, NodeQuery query, ArrayList<Provider> noGoodList, MultiStatus problemCollector,
			IProgressMonitor monitor) throws CoreException {
		List<org.eclipse.buckminster.rmap.Provider> providers = searchPath.getProviders();
		int provCnt = providers.size();
		if (provCnt == 0)
			throw new CoreException(problemCollector);

		monitor.beginTask("Matching providers", provCnt * 1000); //$NON-NLS-1$
		try {
			ProviderMatch bestMatch = null;
			VersionRange desiredVersion = query.getVersionRange();

			for (Provider provider : providers) {
				if (noGoodList.contains(provider))
					continue;

				Filter resolutionFilter = provider.getResolutionFilter();
				if (!isFilterMatchFor(query, resolutionFilter)) {
					query.logDecision(ResolverDecisionType.FILTER_MISMATCH, resolutionFilter);
					continue;
				}

				query.logDecision(ResolverDecisionType.TRYING_PROVIDER, provider.getReaderType(), getProviderURI(query, provider));
				ProviderMatch match = findMatch(provider, query, problemCollector, MonitorUtils.subMonitor(monitor, 1000));
				if (match == null) {
					noGoodList.add(provider);
					continue;
				}

				String readerType = provider.getReaderType();
				ProviderScore score = match.getProviderScore();

				if ((score.ordinal() >= ProviderScore.FAIR.ordinal() && IReaderType.LOCAL.equals(readerType))
						|| (score.ordinal() >= ProviderScore.GOOD.ordinal() && desiredVersion != null
								&& desiredVersion.getMinimum().equals(match.getVersionMatch().getVersion()) && desiredVersion.getMinimum().equals(
								desiredVersion.getMaximum()))) {
					// No use continuing the search. It won't get better
					// than this.
					//
					bestMatch = match;
					break;
				}

				if (bestMatch == null || match.compareTo(bestMatch) > 0) {
					if (bestMatch != null) {
						Provider rejected = bestMatch.getOriginalProvider();
						query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, rejected.getReaderType(), getProviderURI(query, rejected),
								NLS.bind(Messages._0_1_is_producing_a_better_match, provider.getReaderType(), getProviderURI(query, provider)));
					}
					bestMatch = match;
					continue;
				}

				Provider best = bestMatch.getOriginalProvider();
				query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, provider.getReaderType(), getProviderURI(query, provider),
						NLS.bind(Messages._0_1_is_producing_a_better_match, best.getReaderType(), getProviderURI(query, best)));
			}

			if (bestMatch == null) {
				query.logDecision(ResolverDecisionType.PROVIDER_NOT_FOUND);
				throw new CoreException(problemCollector);
			}

			Provider best = bestMatch.getOriginalProvider();
			query.logDecision(ResolverDecisionType.USING_PROVIDER, best.getReaderType(), getProviderURI(query, best));
			return bestMatch;
		} finally {
			monitor.done();
		}
	}

	static String getProviderURI(NodeQuery query, Provider provider) {
		return provider.getURI().toString() + '[' + getProviderURI(provider, query.getProperties()) + ']';
	}

	static String getProviderURI(Provider provider, Map<String, String> properties) {
		return provider.getURI().getValue(properties);
	}

	private static Filter getFilter(Map<String, String> matchMap) {
		return FilterUtils.createFilter(matchMap.get(OS_PARAM), matchMap.get(WS_PARAM), matchMap.get(ARCH_PARAM), matchMap.get(NL_PARAM));
	}

	private static IStatus transformToWarning(IStatus status) {
		if (status instanceof MultiStatus) {
			IStatus[] children = status.getChildren();
			int idx = children.length;
			while (--idx >= 0)
				children[idx] = transformToWarning(children[idx]);
			return new MultiStatus(status.getPlugin(), status.getCode(), children, status.getMessage(), status.getException());
		}

		return (status.getSeverity() < IStatus.ERROR) ? status : new Status(IStatus.WARNING, status.getPlugin(), status.getCode(),
				status.getMessage(), status.getException());
	}

	private boolean singleThreaded = false;

	private boolean holdQueue = false;

	private static int jobCounter = 0;

	private final ArrayList<IProgressMonitor> jobMonitors = new ArrayList<IProgressMonitor>();

	private final IResourceMapResolverFactory factory;

	private IProgressMonitor topMonitor;

	private final LinkedList<ResolverNodeWithJob> waitQueue = new LinkedList<ResolverNodeWithJob>();

	public ResourceMapResolver(IResourceMapResolverFactory factory, ResolutionContext context, boolean singleThreaded) throws CoreException {
		super(context);
		this.factory = factory;
		this.singleThreaded = singleThreaded;
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}

	@Override
	public void done(IJobChangeEvent event) {
		ResolverNodeWithJob.NodeResolutionJob job = (ResolverNodeWithJob.NodeResolutionJob) event.getJob();
		job.removeJobChangeListener(this);
		ResolverNodeWithJob node = job.getNode();

		synchronized (node) {
			node.setScheduled(false);
			if (node.isInvalidated() && !node.isForceUnresolved())
				schedule(node);
		}
	}

	public ResourceMap getResourceMap(ResourceMap rmap, Map<String, String> properties, Redirect redirect, NodeQuery query) throws CoreException {
		String expanded = ExpandingProperties.expand(properties, redirect.getHref(), 0);
		URL resolvedURL = URLUtils.resolveURL(rmap.getContextURL(), expanded);
		query.logDecision(ResolverDecisionType.REDIRECT_TO_RESOURCE_MAP, resolvedURL);
		return factory.getResourceMap(query.getResolutionContext(), resolvedURL, query.getComponentQuery().getConnectContext());
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException {
		beginTopMonitor(monitor);
		try {
			ResolutionContext ctx = getContext();
			ComponentQuery query = ctx.getComponentQuery();
			ResolverNodeWithJob topNode = (ResolverNodeWithJob) getResolverNode(ctx,
					new QualifiedDependency(request, query.getAttributes(request, ctx)), null);

			if (singleThreaded) {
				beginTopMonitor(monitor);
				schedule(topNode);
				endTopMonitor();
			} else {
				schedule(topNode);
				waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
			}
			return createBillOfMaterials(topNode);
		} finally {
			endTopMonitor();
		}
	}

	@Override
	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException {
		if (bom.isFullyResolved(getContext())) {
			MonitorUtils.complete(monitor);
			return bom;
		}

		beginTopMonitor(monitor);
		try {
			ComponentQuery cquery = bom.getQuery();
			ResolutionContext context = getContext();
			if (!(cquery == null || cquery.equals(context.getComponentQuery())))
				context = new ResolutionContext(cquery, context);

			ResolverNodeWithJob topNode = (ResolverNodeWithJob) getResolverNode(context, bom.getQualifiedDependency(), bom.getTagInfo());

			holdQueue = true;
			if (topNode.rebuildTree(bom)) {
				holdQueue = false;
				if (singleThreaded) {
					IStatus status = context.getStatus();
					if (status.getSeverity() == IStatus.ERROR && !context.isContinueOnError())
						throw new CoreException(status);
				} else {
					scheduleNext();
					waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
				}
				BillOfMaterials newBom = createBillOfMaterials(topNode);
				if (!newBom.contentEqual(bom))
					bom = newBom;
			}
			return bom;
		} finally {
			holdQueue = false;
			endTopMonitor();
		}
	}

	@Override
	public synchronized void running(IJobChangeEvent event) {
		if (topMonitor != null)
			MonitorUtils.worked(topMonitor, 1);
	}

	@Override
	public void scheduled(IJobChangeEvent event) {
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}

	synchronized void addJobMonitor(IProgressMonitor monitor) {
		if (singleThreaded)
			return;

		if (topMonitor == null || topMonitor.isCanceled()) {
			monitor.setCanceled(true);
			return;
		}

		int idx = jobMonitors.size();
		while (--idx >= 0)
			if (jobMonitors.get(idx) == monitor)
				return;

		jobMonitors.add(monitor);
	}

	synchronized void cancelTopMonitor() {
		if (topMonitor != null)
			topMonitor.setCanceled(true);
	}

	@Override
	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep, String requestorInfo) {
		return new ResolverNodeWithJob(this, context, qDep, requestorInfo);
	}

	BOMNode innerResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 100);
		try {
			BOMNode node = null;
			if (factory.isLocalResolve()) {
				query.logDecision(ResolverDecisionType.USING_RESOLVER, "Local resolver"); //$NON-NLS-1$
				node = localResolve(query, MonitorUtils.subMonitor(monitor, 5));
			} else {
				query.logDecision(ResolverDecisionType.RESOLVER_REJECTED, "All local resolvers"); //$NON-NLS-1$
				MonitorUtils.worked(monitor, 5);
			}

			if (node == null && query.useResolutionService()) {
				ComponentQuery cquery = query.getComponentQuery();
				URL rmapURL = cquery.getResolvedResourceMapURL();
				ResourceMap rmap = factory.getResourceMap(getContext(), rmapURL, cquery.getConnectContext());
				query.logDecision(ResolverDecisionType.USING_RESOURCE_MAP, rmapURL);
				node = resolve(rmap, query, MonitorUtils.subMonitor(monitor, 95));
			} else
				MonitorUtils.worked(monitor, 95);
			return node;
		} catch (CoreException e) {
			RMContext context = getContext();
			if (!context.isContinueOnError())
				throw e;
			context.addRequestStatus(query.getComponentRequest(), e.getStatus());
			return null;
		} finally {
			monitor.done();
		}
	}

	synchronized void removeJobMonitor(IProgressMonitor monitor) {
		if (singleThreaded)
			return;

		int idx = jobMonitors.size();
		while (--idx >= 0) {
			if (jobMonitors.get(idx) == monitor) {
				jobMonitors.remove(idx);
				break;
			}
		}
	}

	synchronized void resolutionPartDone() {
		if (singleThreaded)
			return;

		// Allow another job to enter. The resolution part of the
		// calling job is done.
		//
		--jobCounter;
		scheduleNext();
	}

	BOMNode resolve(NodeQuery query, SearchPath searchPath, IProgressMonitor monitor) throws CoreException {
		MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.ERROR, NLS.bind(
				Messages.No_suitable_provider_for_component_0_was_found_in_searchPath_1, query.getComponentRequest(), searchPath.getName()), null);

		ArrayList<Provider> noGoodList = new ArrayList<Provider>();
		try {
			for (boolean first = true;; first = false) {
				ProviderMatch providerMatch = getProvider(searchPath, query, noGoodList, problemCollector,
						MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
				MonitorUtils.testCancelStatus(monitor);

				Provider provider = providerMatch.getProvider();
				IComponentType cType = providerMatch.getComponentType();
				try {
					BOMNode node = cType.getResolution(providerMatch, MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
					Resolution resolution = node.getResolution();
					MonitorUtils.testCancelStatus(monitor);
					Filter[] filterHandle = new Filter[1];
					if (!resolution.isFilterMatchFor(query, filterHandle)) {
						ResolverDecision decision = query.logDecision(ResolverDecisionType.FILTER_MISMATCH, filterHandle[0]);
						noGoodList.add(providerMatch.getOriginalProvider());
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
						continue;
					}

					CSpec cspec = resolution.getCSpec();

					// Assert that the cspec can handle required actions and
					// exports
					//
					Version version = cspec.getVersion();
					VersionRange range = query.getVersionRange();
					if (range != null && provider.getVersionConverter() == null) {
						// A missing version converter means that the actual
						// version check is deferred
						// and later performed on the retreived CSpec. Later is
						// now ...
						//
						if (!range.isIncluded(version)) {
							ResolverDecision decision = query.logDecision(ResolverDecisionType.VERSION_REJECTED, version,
									NLS.bind(Messages.Not_designated_by_0, range));
							noGoodList.add(providerMatch.getOriginalProvider());
							problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
							continue;
						}
					}

					// Verify that all required attributes are in this cspec
					//
					cspec.getAttributes(query.getRequiredAttributes());
					if (version != null) {
						// Replace the resolution version if it is default and
						// a non default version is present in the CSpec
						//
						VersionMatch vm = resolution.getVersionMatch();
						if (vm.getVersion() == null)
							node = new ResolvedNode(new Resolution(version, resolution), node.getChildren());
					}
					return node;
				} catch (CoreException e) {
					ResolverDecision decision = query.logDecision(ResolverDecisionType.EXCEPTION, e.getMessage());
					problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), e));
					noGoodList.add(providerMatch.getOriginalProvider());
				}
			}
		} finally {
			monitor.done();
		}
	}

	BOMNode resolve(ResourceMap rmap, NodeQuery query, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 2000);

		ComponentRequest request = query.getComponentRequest();
		MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), 0, NLS.bind(
				Messages.no_suitable_provider_for_0_was_found_in_resourceMap_1, request, rmap.getContextURL()), null);

		Map<String, String> props;
		if (rmap.getProperties().isEmpty())
			props = query.getProperties();
		else {
			query = new NodeQuery(query, rmap.getProperties(), false);
			props = query.getProperties();
		}

		String componentName = request.getName();
		for (Matcher matcher : rmap.getMatchers()) {
			if (!matcher.matches(componentName))
				continue;

			if (matcher instanceof Redirect)
				return resolve(getResourceMap(rmap, props, (Redirect) matcher, query), query, monitor);

			Locator locator = (Locator) matcher;
			try {
				SearchPath sp = locator.getSearchPath();
				query.logDecision(ResolverDecisionType.USING_SEARCH_PATH, sp.getName());
				return resolve(query, sp, monitor);
			} catch (CoreException e) {
				problemCollector.add(e.getStatus());
				if (locator.isFailOnError())
					break;
			}
		}

		if (problemCollector.getChildren().length == 0) {
			query.logDecision(ResolverDecisionType.SEARCH_PATH_NOT_FOUND, (Object) null);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, NLS.bind(Messages.Unable_to_find_a_searchPath_for_0,
					request), null));
		}

		if (request.isOptional()) {
			// The component is optional so this should not be considered an
			// error
			// A warning is appropriate though, since this probably indicates
			// some
			// kind of problem.
			//
			if (!request.isSynthetic())
				query.getContext().addRequestStatus(request, transformToWarning(problemCollector));
			return new UnresolvedNode(new QualifiedDependency(request, query.getRequiredAttributes()));
		}

		throw new CoreException(problemCollector);
	}

	boolean schedule(ResolverNodeWithJob node) {
		synchronized (node) {
			if (node.isScheduled() || node.isResolved())
				return false;
			node.setScheduled(true);
		}

		if (singleThreaded) {
			node.run(MonitorUtils.subMonitor(topMonitor, 1));
			node.setScheduled(false);
		} else {
			pushOnWaitQueue(node);
			if (!holdQueue)
				scheduleNext();
		}
		return true;
	}

	private synchronized void beginTopMonitor(IProgressMonitor monitor) {
		monitor = new FibonacciMonitorWrapper(monitor);
		monitor.beginTask(null, 50);
		topMonitor = monitor;
	}

	private void cancelAllJobs() {
		synchronized (waitQueue) {
			waitQueue.clear();
		}

		synchronized (this) {
			int idx = jobMonitors.size();
			while (--idx >= 0)
				jobMonitors.get(idx).setCanceled(true);
			if (topMonitor != null)
				topMonitor.setCanceled(true);
		}
	}

	private synchronized void endTopMonitor() {
		topMonitor.done();
		topMonitor = null;
	}

	private ResolverNodeWithJob popWaitQueue() {
		synchronized (waitQueue) {
			return waitQueue.poll();
		}
	}

	private void pushOnWaitQueue(ResolverNodeWithJob node) {
		synchronized (waitQueue) {
			waitQueue.add(node);
		}
	}

	private boolean scheduleNext() {
		ArrayList<ResolverNodeWithJob> nodes = null;
		synchronized (getClass()) {
			int jobsToSchedule = factory.getResolverThreadsMax() - jobCounter;
			while (--jobsToSchedule >= 0) {
				ResolverNodeWithJob node = popWaitQueue();
				if (node == null)
					break;

				if (nodes == null)
					nodes = new ArrayList<ResolverNodeWithJob>();
				nodes.add(node);
				++jobCounter;
			}
		}

		if (nodes == null)
			return false;

		int top = nodes.size();
		for (int idx = 0; idx < top; ++idx) {
			ResolverNodeWithJob.NodeResolutionJob job = nodes.get(idx).getJob();
			job.addJobChangeListener(this);
			job.schedule();
		}
		return true;
	}

	private void waitForCompletion(IProgressMonitor monitor) throws CoreException {
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addNameBlock(Messages.Building_workspace);
		jobBlocker.addNameBlock(Messages.Periodic_workspace_save);
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try {
			IStatus status;
			RMContext context = getContext();
			try {
				for (;;) {
					Job.getJobManager().join(this, MonitorUtils.subMonitor(monitor, 1));

					// The waitQueue is ours but the job counter is share
					// between instances so we might run into situations
					// where we're not yet allowed to schedule anything.
					//
					if (waitQueue.isEmpty())
						break;

					while (!scheduleNext())
						//
						// Sleep a while, then try again
						//
						Thread.sleep(100);
				}
				status = context.getStatus();
			} catch (OperationCanceledException e) {
				status = Status.CANCEL_STATUS;
			} catch (InterruptedException e) {
				status = Status.CANCEL_STATUS;
			}

			if (status.getSeverity() == IStatus.CANCEL) {
				cancelAllJobs();
				throw new OperationCanceledException();
			}

			if (status.getSeverity() == IStatus.ERROR && !context.isContinueOnError()) {
				context.clearStatus();
				throw new CoreException(status);
			}
		} finally {
			monitor.done();
			jobBlocker.release();
		}
	}
}
