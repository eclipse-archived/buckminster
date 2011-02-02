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

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.PropertyRef;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequestConflictException;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * The LocalResolver will attempt to resolve the query using locally available
 * resources. This includes:
 * <ul>
 * <li>Projects in the Eclipse Workspace</li>
 * <li>Materializations previously done by Buckminster using this workspace</li>
 * <li>Features and bundles in the target platform</li>
 * </ul>
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings({ "serial" })
public class LocalResolver extends HashMap<String, ResolverNode[]> implements IResolver {
	public static final Provider INSTALLED_BUNDLE_PROVIDER;

	public static final Provider INSTALLED_FEATURE_PROVIDER;
	static {
		Map<String, String> props = new HashMap<String, String>(2);
		props.put(KeyConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		props.put(KeyConstants.IS_SOURCE, "false"); //$NON-NLS-1$
		VersionConverterDesc pdeConverter = new VersionConverterDesc("tag", null, //$NON-NLS-1$
				new BidirectionalTransformer[0]);
		INSTALLED_BUNDLE_PROVIDER = new Provider(null, IReaderType.ECLIPSE_PLATFORM, new String[] { IComponentType.OSGI_BUNDLE }, pdeConverter,
				new Format("plugin/${" //$NON-NLS-1$
						+ KeyConstants.COMPONENT_NAME + "}"), null, null, null, props, null, null); //$NON-NLS-1$

		INSTALLED_FEATURE_PROVIDER = new Provider(null, IReaderType.ECLIPSE_PLATFORM, new String[] { IComponentType.ECLIPSE_FEATURE }, pdeConverter,
				new Format("feature/${" //$NON-NLS-1$
						+ KeyConstants.COMPONENT_NAME + "}"), null, null, null, props, null, null); //$NON-NLS-1$
	}

	public static Resolution fromPath(IPath productPath, String name) throws CoreException {
		return fromPath(productPath, name, null, new NullProgressMonitor());
	}

	public static Resolution fromPath(IPath productPath, String name, String givenCtypeId, IProgressMonitor monitor) throws CoreException {
		Set<String> possibleTypes;
		if (givenCtypeId != null)
			possibleTypes = Collections.singleton(givenCtypeId);
		else {
			possibleTypes = new HashSet<String>();
			for (IComponentType ctype : AbstractComponentType.getComponentTypes()) {
				if (ctype.isMetaFileBased() && ctype.hasAllRequiredMetaFiles(productPath))
					possibleTypes.add(ctype.getId());
			}
			if (possibleTypes.isEmpty())
				possibleTypes.add(IComponentType.UNKNOWN);
		}

		Format repoURI;
		try {
			String repoStr = productPath.toFile().toURI().toURL().toString();
			int nameIndex = repoStr.lastIndexOf(name);
			if (nameIndex > 0) {
				String parameterized = repoStr.substring(0, nameIndex);
				parameterized += "{0}"; //$NON-NLS-1$
				nameIndex += name.length();
				if (repoStr.length() > nameIndex)
					parameterized += repoStr.substring(nameIndex, repoStr.length());

				repoURI = new Format(parameterized);
				repoURI.addValueHolder(new PropertyRef<String>(String.class, KeyConstants.COMPONENT_NAME));
			} else
				repoURI = new Format(repoStr);
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}

		// We might have more then one possible type. Select the one that
		// produces the
		// largest CSPEC (should be fast considering the IPath is local
		//
		ComponentRequest rq = new ComponentRequest(name, givenCtypeId, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		queryBld.setPlatformAgnostic(true);
		ComponentQuery cquery = queryBld.createComponentQuery();
		ResolutionContext context = new ResolutionContext(cquery);
		NodeQuery nq = new NodeQuery(context, rq, null);
		Provider provider = new Provider(null, IReaderType.LOCAL, possibleTypes.toArray(new String[possibleTypes.size()]), null, repoURI, null, null,
				null, null, null, null);
		monitor.beginTask(null, possibleTypes.size() * 100);
		int largestCSpecSize = -1;
		Resolution bestMatch = null;
		for (String ctypeId : possibleTypes) {
			IComponentType ctype = CorePlugin.getDefault().getComponentType(ctypeId);
			ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);

			try {
				BOMNode node = ctype.getResolution(pm, MonitorUtils.subMonitor(monitor, 100));
				Resolution resolution = node.getResolution();
				if (resolution == null)
					continue;

				int imageSize = resolution.getCSpec().getImage().length;
				if (bestMatch == null || largestCSpecSize < imageSize) {
					largestCSpecSize = imageSize;
					bestMatch = resolution;
				}
			} catch (MissingCSpecSourceException e) {
				continue;
			}
		}
		if (bestMatch == null)
			throw new MissingComponentException(rq.toString());
		return bestMatch;
	}

	public static Resolution fromPath(NodeQuery query, IPath path, Resolution oldInfo) throws CoreException {
		ComponentRequest request = query.getComponentRequest();
		Resolution resolution = fromPath(path, request.getName(), request.getComponentTypeID(), new NullProgressMonitor());

		// Retain old component info if present. We only wanted the cspec
		// changes
		//
		if (oldInfo != null)
			resolution = new Resolution(resolution.getCSpec(), oldInfo);
		return resolution;
	}

	private final ResolutionContext context;

	private boolean recursiveResolve = true;

	public LocalResolver(ComponentQuery componentQuery) throws CoreException {
		this(new ResolutionContext(componentQuery));
	}

	public LocalResolver(ResolutionContext context) throws CoreException {
		this.context = context;
	}

	@Override
	public ResolutionContext getContext() {
		return context;
	}

	@Override
	public boolean isRecursiveResolve() {
		return recursiveResolve;
	}

	public BOMNode localResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException {
		boolean isSilent = query.getResolutionContext().isSilentStatus();
		Logger log = Buckminster.getLogger();
		ComponentRequest request = query.getComponentRequest();

		IProject existingProject = null;
		if (query.useMaterialization() || query.useWorkspace()) {
			if (!isSilent)
				query.logDecision(ResolverDecisionType.TRYING_PROVIDER, IReaderType.LOCAL, "materialized"); //$NON-NLS-1$

			try {
				Materialization mat = WorkspaceInfo.getMaterialization(request);
				if (mat == null) {
					if (!isSilent)
						log.debug("No materialization found for %s", request); //$NON-NLS-1$
				} else {
					if (!isSilent)
						log.debug("Materialization found for %s", request); //$NON-NLS-1$
					existingProject = WorkspaceInfo.getProject(mat);
					if (existingProject == null || !FileUtils.pathEquals(mat.getComponentLocation(), existingProject.getLocation())) {
						if (!isSilent)
							log.debug("No workspace project found at %s", mat.getComponentLocation()); //$NON-NLS-1$

						Resolution res = fromPath(query, mat.getComponentLocation(), null);
						if (!isSilent)
							query.logDecision(ResolverDecisionType.MATCH_FOUND, mat.getComponentIdentifier());

						Filter[] failingFilter = new Filter[1];
						if (res.isFilterMatchFor(query, failingFilter))

						{
							MonitorUtils.complete(monitor);
							return new ResolvedNode(query, res);
						}
						if (!isSilent)
							query.logDecision(ResolverDecisionType.MATCH_REJECTED, mat.getComponentIdentifier(),
									NLS.bind(Messages.Filter_0_does_not_match_the_current_property_set, failingFilter[0]));
					} else if (!isSilent)
						log.debug("Workspace project found at %s", mat.getComponentLocation()); //$NON-NLS-1$
				}
			} catch (MissingComponentException e) {
			}
		} else if (!isSilent)
			query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, IReaderType.LOCAL, "materialized", //$NON-NLS-1$
					Messages.materializations_disabled_in_query);

		// If we get to this point, we didn't find any existing resolution that
		// could be used.
		//
		if (query.useWorkspace()) {
			// Generate the resolution from a project in the workspace
			//
			if (existingProject == null) {
				if (!isSilent)
					query.logDecision(ResolverDecisionType.TRYING_PROVIDER, IReaderType.LOCAL, "workspace"); //$NON-NLS-1$
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				try {
					existingProject = wsRoot.getProject(request.getProjectName());
				} catch (IllegalArgumentException e) {
					// Query did not produce a name that is valid for a project
				}
			}
			if (existingProject != null && existingProject.isOpen()) {
				if (!isSilent)
					log.debug("Found workspace project for %s", request.getProjectName()); //$NON-NLS-1$
				Resolution resolution = fromPath(query, existingProject.getLocation(), null);
				ComponentIdentifier ci = resolution.getComponentIdentifier();
				if (request.designates(ci)) {
					if (!isSilent)
						query.logDecision(ResolverDecisionType.MATCH_FOUND, ci);
					Filter[] failingFilter = new Filter[1];
					if (resolution.isFilterMatchFor(query, failingFilter)) {
						// Make sure we have a materialization for the project.
						//
						StorageManager sm = StorageManager.getDefault();
						Materialization mat = new Materialization(existingProject.getLocation().addTrailingSeparator(), ci);
						mat.store(sm);
						resolution.store(sm);
						MonitorUtils.complete(monitor);
						return new ResolvedNode(query, resolution);
					}
					if (!isSilent)
						query.logDecision(ResolverDecisionType.MATCH_REJECTED, ci,
								NLS.bind(Messages.Filter_0_does_not_match_the_current_property_set, failingFilter[0]));
				} else if (!isSilent)
					log.debug("Workspace project for %s is not designated by %s", request.getProjectName(), request); //$NON-NLS-1$
			} else if (!isSilent)
				log.debug("No open workspace project found that corresponds to %s", request); //$NON-NLS-1$
		} else if (!isSilent)
			query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, IReaderType.LOCAL, "workspace", //$NON-NLS-1$
					Messages.workspace_disable_in_query);

		if (query.useTargetPlatform()) {
			Resolution res = WorkspaceInfo.getResolution(request, true);
			if (res != null)
				return new ResolvedNode(query, res);

			if (!isSilent)
				query.logDecision(ResolverDecisionType.TRYING_PROVIDER, IReaderType.LOCAL, "target"); //$NON-NLS-1$
			// Generate the resolution from the target platform
			//
			Provider provider;
			String ctypeID = request.getComponentTypeID();
			if (IComponentType.OSGI_BUNDLE.equals(ctypeID))
				provider = INSTALLED_BUNDLE_PROVIDER;
			else if (IComponentType.ECLIPSE_FEATURE.equals(ctypeID))
				provider = INSTALLED_FEATURE_PROVIDER;
			else {
				query.logDecision(ResolverDecisionType.COMPONENT_TYPE_MISMATCH, ctypeID);
				return null;
			}

			MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null); //$NON-NLS-1$
			ProviderMatch match = provider.findMatch(query, problemCollector, new NullProgressMonitor());
			if (match == null)
				// The reason will have been logged already
				return null;

			monitor.beginTask(null, 30);
			try {
				IComponentReader[] reader = new IComponentReader[] { match.getReader(MonitorUtils.subMonitor(monitor, 10)) };
				BOMNode node = match.getComponentType().getResolutionBuilder(reader[0], MonitorUtils.subMonitor(monitor, 10))
						.build(reader, false, MonitorUtils.subMonitor(monitor, 10));
				IOUtils.close(reader[0]);

				res = node.getResolution();
				Filter[] failingFilter = new Filter[1];
				if (res.isFilterMatchFor(query, failingFilter)) {
					if (!isSilent)
						query.logDecision(ResolverDecisionType.MATCH_FOUND, res.getComponentIdentifier());
					res.store(StorageManager.getDefault());
					return node;
				}
				query.logDecision(ResolverDecisionType.FILTER_MISMATCH, failingFilter[0]);
				return null;
			} finally {
				monitor.done();
			}
		} else if (!isSilent)
			query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, IReaderType.LOCAL, "target", //$NON-NLS-1$
					Messages.target_platform_disabled_in_query);

		return null;
	}

	@Override
	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		return context.logDecision(request, decisionType, args);
	}

	@Override
	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return context.logDecision(decisionType, args);
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try {
			NodeQuery query = context.getNodeQuery(request);
			ResolverNode node = deepResolve(context, new HashMap<ComponentName, ResolverNode>(), new UnresolvedNode(query.getQualifiedDependency()),
					null, monitor);
			return createBillOfMaterials(node);
		} finally {
			monitor.done();
		}
	}

	@Override
	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException {
		return resolve(context.getComponentQuery().getExpandedRootRequest(context), monitor);
	}

	@Override
	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException {
		if (bom.isFullyResolved(context)) {
			MonitorUtils.complete(monitor);
			return bom;
		}

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try {
			ComponentQuery cquery = bom.getQuery();
			ResolutionContext ctx = (cquery == null || cquery.equals(context.getComponentQuery())) ? context : new ResolutionContext(cquery, context);
			BillOfMaterials newBom = createBillOfMaterials(deepResolve(ctx, new HashMap<ComponentName, ResolverNode>(), bom, bom.getTagInfo(),
					monitor));
			if (!newBom.contentEqual(bom))
				bom = newBom;
			return bom;
		} finally {
			monitor.done();
		}
	}

	@Override
	public void setRecursiveResolve(boolean flag) {
		recursiveResolve = flag;
	}

	BillOfMaterials createBillOfMaterials(ResolverNode topNode) throws CoreException {
		HashMap<UUID, BOMNode> nodeMap = new HashMap<UUID, BOMNode>();
		Stack<Resolution> circularDepTrap = new Stack<Resolution>();
		BOMNode node = topNode.collectNodes(nodeMap, circularDepTrap, true);
		if (node == null)
			node = new UnresolvedNode(topNode.getQuery().getQualifiedDependency());
		return BillOfMaterials.create(node, getContext().getComponentQuery());
	}

	ResolverNode createResolverNode(ResolutionContext ctx, QualifiedDependency qDep, String requestorInfo) {
		return new ResolverNode(ctx.getNodeQuery(qDep), requestorInfo);
	}

	ResolverNode getResolverNode(ResolutionContext ctx, QualifiedDependency qDep, String requestorInfo) throws CoreException {
		// We use a ComponentName as the key since we don't want the
		// designator to play a role here.
		//
		ComponentRequest request = qDep.getRequest();
		String key = request.getName();
		String type = request.getComponentTypeID();
		ResolverNode[] nrs;
		boolean infant;
		synchronized (this) {
			nrs = get(key);
			infant = (nrs == null);
			if (infant) {
				nrs = new ResolverNode[] { createResolverNode(ctx, qDep, requestorInfo) };
				put(key, nrs);
			}
		}

		ResolverNode nr;
		if (infant)
			return nrs[0];

		int top = nrs.length;
		for (int idx = 0; idx < top; ++idx) {
			nr = nrs[idx];
			if (qDep.equals(nr.getQuery().getQualifiedDependency()))
				return nr;
		}

		boolean newRqOptional = request.isOptional();
		boolean invalidateInfant = false;
		for (int idx = 0; idx < top; ++idx) {
			nr = nrs[idx];
			ComponentRequest oldRq = nr.getQuery().getComponentRequest();
			if (!(type == null || oldRq.getComponentTypeID() == null || type.equals(oldRq.getComponentTypeID())))
				continue;

			if (newRqOptional != oldRq.isOptional()) {
				// We don't want a version conflict if one of the ranges are
				// optional.
				//
				try {
					request.mergeDesignator(oldRq);
				} catch (ComponentRequestConflictException e) {
					if (oldRq.isOptional()) {
						// Previous request now in conflict and must be
						// discarded.
						//
						nr.forceUnresolved();
						continue;
					}

					// New request is optional and in conflict. Invalidate the
					// new infant.
					//
					invalidateInfant = true;
					break;
				}
			}

			try {
				nr.addDependencyQualification(qDep, requestorInfo);
				return nr;
			} catch (ComponentRequestConflictException e) {
				// We have a conflict. Two components with the same
				// name but incompatible versions or filters.
				//
				IStatus err = e.getStatus();
				context.addRequestStatus(nr.getQuery().getComponentRequest(), new Status(IStatus.WARNING, err.getPlugin(), err.getMessage()));
			}
		}

		synchronized (this) {
			// No known ResolverNode could accommodate the requirements from
			// this qualified dependency. We need a new one.
			//
			nrs = get(key);
			if (nrs.length == top) {
				nr = createResolverNode(context, qDep, requestorInfo);
				if (invalidateInfant)
					nr.forceUnresolved();
				ResolverNode[] newNrs = new ResolverNode[top + 1];
				System.arraycopy(nrs, 0, newNrs, 0, top);
				newNrs[top] = nr;
				put(key, newNrs);
			} else {
				// Someone beat us to it. Break out from the synchronization
				// and try again from square one
				//
				nr = null;
			}
		}

		if (nr == null)
			//
			// Start from square one.
			//
			nr = getResolverNode(context, qDep, requestorInfo);

		return nr;
	}

	private ResolverNode deepResolve(ResolutionContext ctx, Map<ComponentName, ResolverNode> visited, BOMNode depNode, String tagInfo,
			IProgressMonitor monitor) throws CoreException {
		QualifiedDependency qDep = depNode.getQualifiedDependency();
		ComponentName key = qDep.getRequest().toPureComponentName();

		// The visited map is to prevent endless recursion. The LocalResolver
		// needs this since the query is often
		// created on-the-fly and without a chance to allow circular
		// dependencies.
		//
		ResolverNode node = visited.get(key);
		if (node != null)
			return node;

		node = getResolverNode(ctx, qDep, tagInfo);
		visited.put(key, node);

		if (node.isResolved())
			return node;

		NodeQuery query = ctx.getNodeQuery(qDep);
		if (query.skipComponent())
			return node;

		GeneratorNode generatorNode = query.getResolutionContext().getGeneratorNode(qDep.getRequest());
		if (generatorNode != null) {
			node.setGeneratorNode(generatorNode);
			return node;
		}

		Resolution res = depNode.getResolution();
		if (res == null) {
			try {
				depNode = localResolve(query, MonitorUtils.subMonitor(monitor, 1));
			} catch (CoreException e) {
				if (!context.isContinueOnError())
					throw e;
			}

			if (depNode == null)
				//
				// We don't get any further.
				//
				return node;

			res = depNode.getResolution();
			if (res == null)
				return node;
		}

		ctx = node.startResolvingChildren(depNode);
		if (ctx == null)
			//
			// Resolution was unsuccessful
			//
			return node;

		List<BOMNode> children = depNode.getChildren();
		int top = children.size();
		if (top == 0) {
			node.setResolution(res, null);
			return node;
		}

		ResolverNode[] resolvedChildren = new ResolverNode[top];
		String childTagInfo = res.getCSpec().getTagInfo(tagInfo);
		for (int idx = 0; idx < top; ++idx) {
			BOMNode child = children.get(idx);
			ComponentQuery cquery = child.getQuery();
			ResolutionContext childContext = (cquery == null) ? ctx : new ResolutionContext(cquery, ctx);

			resolvedChildren[idx] = recursiveResolve ? deepResolve(childContext, visited, child, childTagInfo, monitor) : getResolverNode(
					childContext, child.getQualifiedDependency(), childTagInfo);
		}
		node.setResolution(res, resolvedChildren);
		return node;
	}
}
