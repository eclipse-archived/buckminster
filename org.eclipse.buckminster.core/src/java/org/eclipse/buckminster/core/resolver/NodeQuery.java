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

import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.core.version.VersionType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;

/**
 * The <code>NodeQuery</code> combines the {@link IComponentQuery} with one
 * specific {@link IComponentRequest} during recursive resolution of a
 * dependency tree.
 * 
 * @author Thomas Hallgren
 */
public class NodeQuery implements Comparator<VersionMatch>, IResolverBackchannel {
	private final RMContext context;

	private final Map<String, ? extends Object> properties;

	private final QualifiedDependency qDep;

	private transient IComponentType componentType;

	public NodeQuery(NodeQuery query, Map<String, ? extends Object> additionalProperties) {
		this(query, additionalProperties, true);
	}

	public NodeQuery(NodeQuery query, Map<String, ? extends Object> additionalProperties, boolean additionalPrioritized) {
		context = query.getContext();
		qDep = query.getQualifiedDependency();

		Map<String, ? extends Object> qprops = query.getProperties();
		if (additionalProperties.isEmpty())
			properties = qprops;
		else {
			ExpandingProperties<Object> propUnion = new ExpandingProperties<Object>(qprops.size() + additionalProperties.size());
			if (additionalPrioritized) {
				propUnion.putAll(qprops, true);
				propUnion.putAll(additionalProperties);
			} else {
				propUnion.putAll(additionalProperties, true);
				propUnion.putAll(qprops);
			}
			properties = propUnion;
		}
	}

	public NodeQuery(RMContext context, ComponentRequest request, Set<String> attributes) {
		this(context, new QualifiedDependency(request, attributes));
	}

	public NodeQuery(RMContext context, QualifiedDependency qDep) {
		this.context = context;
		this.qDep = qDep;
		this.properties = context.getProperties(qDep.getRequest());
	}

	/**
	 * Merges the version designator and the attributes of the new dependency
	 * with the current one. The method will return this instance if the merge
	 * is a no-op.
	 * 
	 * @param newQDep
	 *            the new qualified depenency
	 * @return This instance or a new instance if modifications where necessary.
	 * @throws CoreException
	 *             if the qualification is in conflict with the previously
	 *             defined dependency with respect to its version designator
	 */
	public NodeQuery addDependencyQualification(QualifiedDependency newQDep) throws CoreException {
		QualifiedDependency mqDep = qDep.mergeDependency(newQDep);
		return qDep == mqDep ? this : context.getNodeQuery(mqDep);
	}

	/**
	 * Checks if the advice for this node indicates that a circular dependency
	 * is allowed.
	 * 
	 * @return <code>true</code> only if an advice indicates that a circular
	 *         dependency is allowed.
	 */
	public boolean allowCircularDependency() throws CoreException {
		return getComponentQuery().allowCircularDependency(getComponentRequest(), context);
	}

	public int compare(VersionMatch vm1, VersionMatch vm2) {
		if (vm1 == vm2)
			return 0;

		int cmp = 0;

		// Compare the revision. If it is present, all revisions higher
		// than it are invalid
		//
		// If only one match matches the revision, it will take precedence
		// regardless of everything else.
		//
		String revision = getRevision();
		if (vm1.satisfiesRevision(revision)) {
			if (!vm2.satisfiesRevision(revision))
				cmp = -1;
		} else if (vm2.satisfiesRevision(revision))
			cmp = 1;
		else
			// Both revisions are invalid. No use continuing the
			// comparison
			//
			return 0;

		if (cmp != 0)
			return cmp;

		// Compare the timestamp. If it is present, all timestamps younger
		// than it are invalid
		//
		// If only one match matches the timestamp, it will take precedence
		// regardless of everything else.
		//
		Date timestamp = getTimestamp();
		Date vm1Ts = vm1.getTimestamp();
		Date vm2Ts = vm2.getTimestamp();
		if (timestamp != null) {
			if (vm1Ts != null && timestamp.compareTo(vm1Ts) >= 0) {
				if (vm2Ts == null || timestamp.compareTo(vm2Ts) < 0)
					cmp = 1; // vm1 is greater since vm2 is invalid

				// Both revisions are valid so the revision doesn't
				// rule anything out. We compare the revisions further
				// down.
			} else {
				if (vm2Ts != null && timestamp.compareTo(vm2Ts) >= 0)
					cmp = -1; // vm2 is greater since vm1 is invalid
				else
					// Both timestamps are invalid. No use continuing the
					// comparison
					//
					return 0;
			}
		}
		if (cmp != 0)
			return cmp;

		int[] prio = getResolutionPrio();
		for (int idx = 0; idx < prio.length; ++idx) {
			switch (prio[idx]) {
				case IAdvisorNode.PRIO_BRANCHTAG_PATH_INDEX:
					cmp = compareSelectors(vm1, vm2);
					break;
				default:
					cmp = compareVersions(vm1, vm2);
			}
			if (cmp != 0)
				return cmp;
		}

		String vm1Str = vm1.getRevision();
		String vm2Str = vm2.getRevision();
		if (vm1Str != null && vm2Str != null && !vm1Str.equals(vm2Str)) {
			// Not same revision. The higher revision wins if they are numeric
			//
			try {
				return Long.parseLong(vm1Str) < Long.parseLong(vm2Str) ? -1 : 1;
			} catch (NumberFormatException e) {
				//
			}
		}

		if (vm1Ts != null && vm2Ts != null)
			cmp = vm1Ts.compareTo(vm2Ts);

		return cmp;
	}

	/**
	 * Returns the attributes designated by this query.
	 * 
	 * @param cspec
	 *            The cspec containing the needed attributes.
	 * @return An array of attributes, possibly empty but never
	 *         <code>null</code>.
	 * @throws CoreException
	 *             when this query declares an attribute that cannot be found in
	 *             <code>cspec</code>.
	 */
	public IAttribute[] getAttributes(CSpec cspec) throws CoreException {
		return cspec.getAttributes(getRequiredAttributes());
	}

	/**
	 * Returns the path consisting of branches and/or tags
	 * 
	 * @return A path that might be empty but never <code>null</code>.
	 */
	public VersionSelector[] getBranchTagPath() {
		return getComponentQuery().getBranchTagPath(getComponentRequest(), context);
	}

	public final ComponentQuery getComponentQuery() {
		return context.getComponentQuery();
	}

	public final ComponentRequest getComponentRequest() {
		return qDep.getRequest();
	}

	public synchronized IComponentType getComponentType() {
		if (componentType == null) {
			try {
				componentType = getComponentRequest().getComponentType();
			} catch (CoreException e) {
				throw new IllegalStateException(Messages.Unable_to_obtain_component_type, e);
			}
		}
		return componentType;
	}

	public RMContext getContext() {
		return context;
	}

	public long getNumericRevision() {
		String revision = getRevision();
		return revision == null ? -1 : Long.parseLong(revision);
	}

	public final URL getOverlayFolder() {
		return getComponentQuery().getOverlayFolder(getComponentRequest(), context);
	}

	/**
	 * Returns properties to use in the resolvement process.
	 * 
	 * @param request
	 *            The component request.
	 * @return the properties that the resolver should use.
	 */
	public Map<String, ? extends Object> getProperties() {
		return properties;
	}

	public Object getProperty(String mapName) {
		return getProperties().get(mapName);
	}

	/**
	 * When the resolver finds a provider, that provider will state that it can
	 * produce mutable or immutable artifacts and that those artifacts are in
	 * source or binary form. The resolver will use this method to find out how
	 * good the provider is based on those facts.
	 * 
	 * @param mutable
	 *            <code>true</code> if the provider will provide a mutable
	 *            artifact.
	 * @param source
	 *            <code>true</code> if the provider will provide source.
	 * @return A score that the resolver will use when it compares the provider
	 *         to other providers.
	 */
	public ProviderScore getProviderScore(boolean mutable, boolean source) {
		return getComponentQuery().getProviderScore(getComponentRequest(), mutable, source, context);
	}

	/**
	 * Returns the qualified dependency (i.e. the request plus required
	 * attributes).
	 * 
	 * @return The qualified dependency.
	 */
	public QualifiedDependency getQualifiedDependency() {
		return qDep;
	}

	/**
	 * Checks if there is a matching advice that declares specific actions
	 * 
	 * @return The names of the adviced actions or an empty array.
	 */
	public List<String> getRequiredAttributes() {
		return getComponentQuery().getAttributes(getComponentRequest(), context);
	}

	public ResolutionContext getResolutionContext() {
		if (context instanceof ResolutionContext)
			return (ResolutionContext) context;
		throw new IllegalStateException(Messages.ResolutionContext_requested_during_Materialization);
	}

	public int[] getResolutionPrio() {
		return getComponentQuery().getResolutionPrio(getComponentRequest(), context);
	}

	/**
	 * Returns the revision number or -1 if not applicable
	 * 
	 * @return The revision number to search for
	 */
	public String getRevision() {
		return getComponentQuery().getRevision(getComponentRequest(), context);
	}

	/**
	 * Returns the timestamp to search for or <code>null</code> if not
	 * applicable
	 * 
	 * @return The timestamp to search for
	 */
	public Date getTimestamp() {
		return getComponentQuery().getTimestamp(getComponentRequest(), context);
	}

	/**
	 * Returns the, possibly overriden, version designator of the component
	 * request.
	 * 
	 * @return A version selector or <code>null</code>.
	 */
	public VersionRange getVersionRange() {
		ComponentRequest request = getComponentRequest();
		VersionRange vds = getComponentQuery().getVersionOverride(request, context);
		if (vds == null)
			vds = request.getVersionRange();
		if (vds == null)
			return vds;

		IComponentType ctype = getComponentType();
		if (ctype == null && VersionHelper.getVersionType(vds.getMinimum()).equals(VersionType.TRIPLET)) {
			try {
				ctype = CorePlugin.getDefault().getComponentType("maven"); //$NON-NLS-1$
			} catch (CoreException e) {
				return vds;
			}
		}
		return ctype == null ? vds : ctype.getTypeSpecificDesignator(vds);
	}

	/**
	 * Returns true if the given version will match this query with respect to
	 * the current version designator, branchTag path, and space path.
	 * 
	 * @param version
	 *            The version to match or <code>null</code> if not applicable
	 * @param branchOrTag
	 *            The branch or tag to match or <code>null</code> if not
	 *            applicable
	 * @param space
	 *            The space to match or <code>null</code> if not applicable
	 * @param spacePathResolver
	 *            the space path resolver to use when expanding the space path.
	 * @return true if the given values matches this query
	 */
	public boolean isMatch(Version version, VersionSelector branchOrTag) {
		if (!isMatch(branchOrTag))
			return false;

		VersionRange versionRange = getVersionRange();
		if (versionRange != null && !versionRange.isIncluded(version)) {
			logDecision(ResolverDecisionType.VERSION_REJECTED, version, NLS.bind(Messages.Not_designated_by_0, versionRange));
			return false;
		}
		return true;
	}

	/**
	 * Returns true if the given <code>versionMatch</code> will match this query
	 * with respect to the current version designator, branchTag path, and space
	 * path.
	 * 
	 * @param versionMatch
	 *            The version match to match
	 * @param spacePathResolver
	 *            the space path resolver to use when expanding the space path.
	 * @return true if the given values matches this query
	 */
	public boolean isMatch(VersionMatch versionMatch) {
		if (versionMatch == null)
			versionMatch = VersionMatch.DEFAULT;
		return isMatch(versionMatch.getVersion(), versionMatch.getBranchOrTag());
	}

	/**
	 * Returns true if the given version will match this query with respect to
	 * the current version designator, branchTag path, and space path.
	 * 
	 * @param version
	 *            The version to match or <code>null</code> if not applicable
	 * @param branchOrTag
	 *            The branch or tag to match or <code>null</code> if not
	 *            applicable
	 * @param space
	 *            The space to match or <code>null</code> if not applicable
	 * @param spacePathResolver
	 *            the space path resolver to use when expanding the space path.
	 * @return true if the given values matches this query
	 */
	public boolean isMatch(VersionSelector branchOrTag) {
		VersionSelector[] branchTagPath = getBranchTagPath();
		if (branchTagPath.length > 0) {
			if (branchOrTag == null)
				branchOrTag = VersionSelector.branch(VersionSelector.DEFAULT_BRANCH);

			if (VersionSelector.indexOf(branchTagPath, branchOrTag) < 0) {
				logDecision(branchOrTag == null || branchOrTag.getType() == VersionSelector.BRANCH ? ResolverDecisionType.BRANCH_REJECTED
						: ResolverDecisionType.TAG_REJECTED, branchOrTag, NLS.bind(Messages.Not_in_path_0, VersionSelector.toString(branchTagPath)));
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there is an advice to prune the external dependencies from the
	 * requested component. Pruning means that all dependencies that has no
	 * declared purposes will be omitted. The default is to not prune the
	 * dependencies.
	 * 
	 * @return <code>true</code> if the external dependencies of the requested
	 *         component should be pruned.
	 */
	public boolean isPrune() {
		return getComponentQuery().isPrune(getComponentRequest(), context);
	}

	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		return getResolutionContext().logDecision(request, decisionType, args);
	}

	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return getResolutionContext().logDecision(getComponentRequest(), decisionType, args);
	}

	/**
	 * Checks if there is an advice to skip the requested component and not
	 * include it in the resolvment. The default is to include the component.
	 * 
	 * @return <code>true</code> if the requested component should be skipped.
	 */
	public boolean skipComponent() {
		return getComponentQuery().skipComponent(getComponentRequest(), context);
	}

	/**
	 * When the resolver finds a materialized component that is not bound to the
	 * workspace it will call this method to decide wether to use that
	 * materialization or if it should be overwritten.
	 * 
	 * @return <code>true</code> if an existing materialization can be used.
	 */
	public boolean useMaterialization() {
		return getComponentQuery().useMaterialization(getComponentRequest(), context);
	}

	/**
	 * Use remote resolution such as an rmap or a service.
	 * 
	 * @return <code>true</code> if remote resolution can be used.
	 */
	public boolean useResolutionService() {
		return getComponentQuery().useResolutionService(getComponentRequest(), context);
	}

	/**
	 * When the resolver finds an installed feature, plugin, or fragment that
	 * represents the component it tries to resolve, it will ask if it can be
	 * used to fulfill the request.
	 * 
	 * @return <code>true</code> if installed feature or plugin can be used.
	 */
	public boolean useTargetPlatform() {
		return getComponentQuery().useTargetPlatform(getComponentRequest(), context);
	}

	/**
	 * When the resolver finds a project that represents the component it tries
	 * to resolve, it will ask if it can be used to fulfill the request.
	 * 
	 * @param props
	 *            The properties that contains the component request.
	 * @return <code>true</code> if an existing project can be used.
	 */
	public boolean useWorkspace() {
		return getComponentQuery().useWorkspace(getComponentRequest(), context);
	}

	private int compareSelectors(VersionMatch vm1, VersionMatch vm2) {
		int cmp = 0;
		VersionSelector[] branchTagPath = getBranchTagPath();
		if (branchTagPath.length > 0) {
			// The match with the lower index is considered greater. A match
			// with no index (-1) will always loose
			//
			int v1idx = VersionSelector.indexOf(branchTagPath, vm1.getBranchOrTag());
			int v2idx = VersionSelector.indexOf(branchTagPath, vm2.getBranchOrTag());
			if (v1idx >= 0) {
				if (v2idx >= 0)
					cmp = (v1idx < v2idx) ? 1 : ((v1idx == v2idx) ? 0 : -1);
				else
					cmp = 1;
			} else {
				if (v2idx >= 0)
					cmp = -1;
			}
		}
		return cmp;
	}

	private int compareVersions(VersionMatch vm1, VersionMatch vm2) {
		// Compare the versions.
		//
		Version v1 = vm1.getVersion();
		Version v2 = vm2.getVersion();
		VersionRange vd = getVersionRange();

		if (vd != null) {
			// Only consider designated versions
			//
			if (v1 != null && !vd.isIncluded(v1))
				v1 = null;
			if (v2 != null && !vd.isIncluded(v2))
				v2 = null;
		}

		int cmp = 0;
		if (v1 == null) {
			if (v2 != null)
				cmp = -1; // Consider v1 to be less
		} else {
			if (v2 == null)
				cmp = 1; // Consider v2 to be less
			else
				cmp = v1.compareTo(v2);
		}
		return cmp;
	}
}
