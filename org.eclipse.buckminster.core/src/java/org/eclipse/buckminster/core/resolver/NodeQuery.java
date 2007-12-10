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

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;

/**
 * The <code>NodeQuery</code> combines the {@link IComponentQuery} with one
 * specific {@link IComponentRequest} during recursive resolvement of a
 * dependency tree.
 * 
 * @author Thomas Hallgren
 */
public class NodeQuery implements Comparator<VersionMatch>
{
	private final RMContext m_context;

	private final Map<String, String> m_properties;

	private final QualifiedDependency m_qDep;

	public NodeQuery(RMContext context, ComponentRequest request, Set<String> attributes)
	{
		this(context, new QualifiedDependency(request, attributes));
	}

	public NodeQuery(RMContext context, QualifiedDependency qDep)
	{
		m_context = context;
		m_qDep = qDep;
		m_properties = context.getProperties(qDep.getRequest());
	}

	/**
	 * Merges the version designator and the attributes of the new dependency with the current
	 * one. The method will return this instance if the merge is a no-op.
	 * @param newQDep the new qualified depenency
	 * @return This instance or a new instance if modifications where necessary.
	 * @throws CoreException if the qualification is in conflict with the previously
	 * defined dependency with respect to its version designator
	 */
	public NodeQuery addDependencyQualification(QualifiedDependency newQDep) throws CoreException
	{
		QualifiedDependency qDep = m_qDep.mergeDependency(newQDep);
		return qDep == m_qDep ? this : new NodeQuery(m_context, qDep);
	}

	/**
	 * Checks if the advice for this node indicates that a circular dependency
	 * is allowed.
	 * @return <code>true</code> only if an advice indicates that a circular dependency is allowed.
	 */
	public boolean allowCircularDependency() throws CoreException
	{
		return getComponentQuery().allowCircularDependency(getComponentRequest());
	}

	public int compare(VersionMatch vm1, VersionMatch vm2)
	{
		if(vm1 == vm2)
			return 0;

		int cmp = 0;
		
		// Compare the revision. If it is present, all revisions higher
		// than it are invalid
		//
		// If only one match matches the revision, it will take precedence
		// regardless of everything else.
		//
		long revision = getRevision();
		long vm1Rev = vm1.getRevision();
		long vm2Rev = vm2.getRevision();
		if(revision != -1)
		{
			if(vm1Rev != -1 && revision >= vm1Rev)
			{
				if(vm2Rev == -1 || revision < vm2Rev)
					cmp = 1; // vm1 is greater since vm2 is invalid
				
				// Both revisions are valid so the revision doesn't
				// rule anything out. We compare the revisions further
				// down.
			}
			else
			{
				if(vm2Rev != -1 && revision >= vm2Rev)
					cmp = -1; // vm2 is greater since vm1 is invalid

				// Both revisions are invalid. No use continuing the
				// comparison
				//
				return 0;
			}
		}
		if(cmp != 0)
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
		if(timestamp != null)
		{
			if(vm1Ts != null && timestamp.compareTo(vm1Ts) >= 0)
			{
				if(vm2Ts == null || timestamp.compareTo(vm2Ts) < 0)
					cmp = 1; // vm1 is greater since vm2 is invalid

				// Both revisions are valid so the revision doesn't
				// rule anything out. We compare the revisions further
				// down.
			}
			else
			{
				if(vm2Ts != null && timestamp.compareTo(vm2Ts) >= 0)
					cmp = -1; // vm2 is greater since vm1 is invalid

				// Both timestamps are invalid. No use continuing the
				// comparison
				//
				return 0;
			}
		}
		if(cmp != 0)
			return cmp;

		int[] prio = getResolutionPrio();
		for(int idx = 0; idx < prio.length; ++idx)
		{
			switch(prio[idx])
			{
			case AdvisorNode.PRIO_BRANCHTAG_PATH_INDEX:
				cmp = compareSelectors(vm1, vm2);
				break;
			case AdvisorNode.PRIO_SPACE_PATH_INDEX:
				cmp = compareSpacePaths(vm1, vm2);
				break;
			default:
				cmp = compareVersions(vm1, vm2);			
			}
			if(cmp != 0)
				return cmp;
		}

		if(vm1Rev != -1 && vm2Rev != -1 && vm1Rev != vm2Rev)
			//
			// Not same revision. The higher revision wins
			//
			return vm1Rev < vm2Rev ? -1 : 1;

		if(vm1Ts != null && vm2Ts != null)
			cmp = vm1Ts.compareTo(vm2Ts);

		return cmp;
	}

	private int compareVersions(VersionMatch vm1, VersionMatch vm2)
	{
		// Compare the versions.
		//
		IVersion v1 = vm1.getVersion();
		IVersion v2 = vm2.getVersion();
		IVersionDesignator vd = getVersionDesignator();

		if(vd != null)
		{
			// Only consider designated versions
			//
			if(v1 != null && !vd.designates(v1))
				v1 = null;
			if(v2 != null && !vd.designates(v2))
				v2 = null;
		}

		int cmp = 0;
		if(v1 == null)
		{
			if(v2 != null)
				cmp = -1; // Consider v1 to be less
		}
		else
		{
			if(v2 == null)
				cmp = 1; // Consider v2 to be less
			else
			{
				// When the versions are of different type (this
				// can only happen when no versionDesignator was
				// present to discriminate above) we will consider
				// them equal so that they don't affect the
				// outcome.
				//
				if(v1.getType().isComparableTo(v2.getType()))
					cmp = v1.compareTo(v2);
			}
		}
		return cmp;
	}

	private int compareSelectors(VersionMatch vm1, VersionMatch vm2)
	{
		int cmp = 0;
		VersionSelector[] branchTagPath = getBranchTagPath();
		if(branchTagPath.length > 0)
		{
			// The match with the lower index is considered greater. A match
			// with no index (-1) will always loose
			//
			int v1idx = VersionSelector.indexOf(branchTagPath, vm1.getBranchOrTag());
			int v2idx = VersionSelector.indexOf(branchTagPath, vm2.getBranchOrTag());
			if(v1idx >= 0)
			{
				if(v2idx >= 0)
					cmp = (v1idx < v2idx) ? 1 : ((v1idx == v2idx) ? 0 : -1);
				else
					cmp = 1;
			}
			else
			{
				if(v2idx >= 0)
					cmp = -1;
			}
		}
		return cmp;
	}

	private int compareSpacePaths(VersionMatch vm1, VersionMatch vm2)
	{
		int cmp = 0;
		String[] spacePath = getSpacePath();
		if(spacePath.length > 0)
		{
			// The match with the lower index is considered greater. A match
			// with no index (-1) will always loose
			//
			int v1idx = TextUtils.indexOf(spacePath, vm1.getSpace());
			int v2idx = TextUtils.indexOf(spacePath, vm2.getSpace());
			if(v1idx >= 0)
			{
				if(v2idx >= 0)
					cmp = (v1idx < v2idx) ? 1 : ((v1idx == v2idx) ? 0 : -1);
				else
					cmp = 1;
			}
			else
			{
				if(v2idx >= 0)
					cmp = -1;
			}
		}
		return cmp;
	}

	/**
	 * Returns the attributes designated by this query.
	 * @param cspec The cspec containing the needed attributes.
	 * @return An array of attributes, possibly empty but never <code>null</code>.
	 * @throws CoreException when this query declares an attribute
	 * that cannot be found in <code>cspec</code>.
	 */
	public Attribute[] getAttributes(CSpec cspec) throws CoreException
	{
		return cspec.getAttributes(getRequiredAttributes());
	}

	/**
	 * Returns the path consisting of branches and/or tags
	 * 
	 * @return A path that might be empty but never <code>null</code>.
	 */
	public VersionSelector[] getBranchTagPath()
	{
		return getComponentQuery().getBranchTagPath(getComponentRequest());
	}

	public final ComponentQuery getComponentQuery()
	{
		return m_context.getComponentQuery();
	}

	public final ComponentRequest getComponentRequest()
	{
		return m_qDep.getRequest();
	}

	public RMContext getContext()
	{
		return m_context;
	}

	public final URL getOverlayFolder()
	{
		return getComponentQuery().getOverlayFolder(getComponentRequest());
	}

	/**
	 * Returns properties to use in the resolvement process.
	 * 
	 * @param request
	 *            The component request.
	 * @return the properties that the resolver should use.
	 */
	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public String getProperty(String mapName)
	{
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
	public ProviderScore getProviderScore(boolean mutable, boolean source)
	{
		return getComponentQuery().getProviderScore(getComponentRequest(), mutable, source);
	}

	/**
	 * Returns the qualified dependency (i.e. the request plus required attributes).
	 * @return The qualified dependency.
	 */
	public QualifiedDependency getQualifiedDependency()
	{
		return m_qDep;
	}

	/**
	 * Checks if there is a matching advice that declares specific actions
	 * @return The names of the adviced actions or an empty array.
	 */
	public List<String> getRequiredAttributes()
	{
		return getComponentQuery().getAttributes(getComponentRequest());
	}

	public ResolutionContext getResolutionContext()
	{
		if(m_context instanceof ResolutionContext)
			return (ResolutionContext)m_context;		
		throw new IllegalStateException("ResolutionContext requested during Materialization");
	}

	public int[] getResolutionPrio()
	{
		return getComponentQuery().getResolutionPrio(getComponentRequest());
	}

	/**
	 * Returns the revision number or -1 if not applicable
	 * 
	 * @return The revision number to search for
	 */
	public long getRevision()
	{
		return getComponentQuery().getRevision(getComponentRequest());
	}

	/**
	 * Returns the space path
	 * 
	 * @return A path that might be empty but never <code>null</code>.
	 */
	public String[] getSpacePath()
	{
		return getComponentQuery().getSpacePath(getComponentRequest());
	}

	/**
	 * Returns the timestamp to search for or <code>null</code> if not applicable
	 * 
	 * @return The timestamp to search for
	 */
	public Date getTimestamp()
	{
		return getComponentQuery().getTimestamp(getComponentRequest());
	}


	/**
	 * Returns the, possibly overriden, version designator of the component
	 * request.
	 * 
	 * @return A version selector or <code>null</code>.
	 */
	public IVersionDesignator getVersionDesignator()
	{
		ComponentRequest request = getComponentRequest();
		IVersionDesignator vds = getComponentQuery().getVersionOverride(request);
		if(vds == null)
			vds = request.getVersionDesignator();
		return vds;
	}

	/**
	 * Returns true if the given <code>versionMatch</code> will match this query with respect to
	 * the current version designator, branchTag path, and space path.
	 *
	 * @param versionMatch The version match to match
	 * @return true if the given values matches this query
	 */
	public boolean isMatch(VersionMatch versionMatch)
	{
		if(versionMatch == null)
			versionMatch = VersionMatch.DEFAULT;
		return isMatch(versionMatch.getVersion(), versionMatch.getBranchOrTag(), versionMatch.getSpace());
	}

	/**
	 * Returns true if the given version will match this query with respect to
	 * the current version designator, branchTag path, and space path.
	 *
	 * @param version The version to match or <code>null</code> if not applicable
	 * @param branchOrTag The branch or tag to match or <code>null</code> if not applicable
	 * @param space The space to match or <code>null</code> if not applicable
	 * @return true if the given values matches this query
	 */
	public boolean isMatch(IVersion version, VersionSelector branchOrTag, String space)
	{
		VersionSelector[] branchTagPath = getBranchTagPath();
		if(branchTagPath.length > 0 && VersionSelector.indexOf(branchTagPath, branchOrTag) < 0)
			return false;

		String[] spacePath = getSpacePath();
		if(spacePath.length > 0 && TextUtils.indexOf(spacePath, space) < 0)
			return false;

		IVersionDesignator designator = getVersionDesignator();
		if(designator != null && !designator.designates(version))
			return false;

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
	public boolean isPrune()
	{
		return getComponentQuery().isPrune(getComponentRequest());
	}

	/**
	 * Checks if there is an advice to skip the requested component and not
	 * include it in the resolvment. The default is to include the component.
	 * 
	 * @return <code>true</code> if the requested component should be skipped.
	 */
	public boolean skipComponent()
	{
		return getComponentQuery().skipComponent(getComponentRequest());
	}

	/**
	 * When the resolver finds a project that represents the component it tries
	 * to resolve, it will ask if it can be used to fulfill the request.
	 * 
	 * @param props
	 *            The properties that contains the component request.
	 * @return <code>true</code> if an existing project can be used.
	 */
	public boolean useExistingProject()
	{
		return getComponentQuery().useWorkspace(getComponentRequest());
	}

	/**
	 * When the resolver finds an installed feature, plugin, or fragment that
	 * represents the component it tries to resolve, it will ask if it can be
	 * used to fulfill the request.
	 * 
	 * @return <code>true</code> if installed feature or plugin can be used.
	 */
	public boolean useInstalledComponent()
	{
		return getComponentQuery().useTargetPlatform(getComponentRequest());
	}

	/**
	 * When the resolver finds a materialized component that is not bound to the
	 * workspace it will call this method to decide wether to use that
	 * materialization or if it should be overwritten.
	 * 
	 * @return <code>true</code> if an existing materialization can be used.
	 */
	public boolean useMaterialization()
	{
		return getComponentQuery().useMaterialization(getComponentRequest());
	}

	/**
	 * Use remote resolution such as an rmap or a service.
	 * 
	 * @return <code>true</code> if remote resolution can be used.
	 */
	public boolean useResolutionService()
	{
		return getComponentQuery().useResolutionService(getComponentRequest());
	}
}
