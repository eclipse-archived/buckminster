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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * The <code>NodeQuery</code> combines the {@link IComponentQuery} with one
 * specific {@link IComponentRequest} during recursive resolvement of a
 * dependency tree.
 * 
 * @author Thomas Hallgren
 */
public class NodeQuery
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

	public final ComponentQuery getComponentQuery() throws CoreException
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

	public ResolutionContext getResolutionContext()
	{
		if(m_context instanceof ResolutionContext)
			return (ResolutionContext)m_context;		
		throw new IllegalStateException("ResolutionContext requested during Materialization");
	}

	public IPath getMaterializationLocation() throws CoreException
	{
		return getComponentQuery().getMaterializationLocation(getComponentRequest());
	}

	public final URL getOverlayFolder() throws CoreException
	{
		return getComponentQuery().getOverlayFolder(getComponentRequest());
	}

	/**
	 * A matching advisor node might contain a reqular expression based
	 * name substitution intended to produce the project name that the
	 * component will use in the Eclipse workspace. If such an expression
	 * is present, and if it matches the name found in the request, then
	 * the replacement takes place and the new name is returned. If not,
	 * the component name is returned.
	 * @return The name to use for the project that corresponds to the
	 * nodes component.
	 */
	public String getProjectName() throws CoreException
	{
		return getComponentQuery().getProjectName(getComponentRequest());
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
	public ProviderScore getProviderScore(boolean mutable, boolean source) throws CoreException
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
	public List<String> getRequiredAttributes() throws CoreException
	{
		return getComponentQuery().getAttributes(getComponentRequest());
	}

	/**
	 * Returns the, possibly overriden, version designator of the component
	 * request.
	 * 
	 * @return A version selector or <code>null</code>.
	 */
	public IVersionDesignator getVersionDesignator() throws CoreException
	{
		ComponentRequest request = getComponentRequest();
		IVersionDesignator vds = getComponentQuery().getVersionOverride(request);
		if(vds == null)
			vds = request.getVersionDesignator();
		return vds;
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
	public boolean isPrune() throws CoreException
	{
		return getComponentQuery().isPrune(getComponentRequest());
	}

	/**
	 * Checks if there is an advice to skip the requested component and not
	 * include it in the resolvment. The default is to include the component.
	 * 
	 * @return <code>true</code> if the requested component should be skipped.
	 */
	public boolean skipComponent() throws CoreException
	{
		return getComponentQuery().skipComponent(getComponentRequest());
	}

	/**
	 * When the resolver sees that the location for an intended materialization
	 * is not empty while still not known as the root of a materialized
	 * component, it will ask if it should treat the existing artifacts as a
	 * materialization, if it should overwrite them, or if it should fail.
	 * 
	 * @return An enum denoting the verdict.
	 */
	public ConflictResolution useExistingArtifacts() throws CoreException
	{
		return getComponentQuery().useExistingArtifacts(getComponentRequest());
	}

	/**
	 * When the resolver finds a project that represents the component it tries
	 * to resolve, it will ask if it can be used to fulfill the request.
	 * 
	 * @param props
	 *            The properties that contains the component request.
	 * @return <code>true</code> if an existing project can be used.
	 */
	public boolean useExistingProject() throws CoreException
	{
		return getComponentQuery().useExistingProject(getComponentRequest());
	}

	/**
	 * When the resolver finds an installed feature, plugin, or fragment that
	 * represents the component it tries to resolve, it will ask if it can be
	 * used to fulfill the request.
	 * 
	 * @return <code>true</code> if installed feature or plugin can be used.
	 */
	public boolean useInstalledComponent() throws CoreException
	{
		return getComponentQuery().useInstalledComponent(getComponentRequest());
	}

	/**
	 * When the resolver finds a materialized component that is not bound to the
	 * workspace it will call this method to decide wether to use that
	 * materialization or if it should be overwritten.
	 * 
	 * @return <code>true</code> if an existing materialization can be used.
	 */
	public boolean useMaterialization() throws CoreException
	{
		return getComponentQuery().useMaterialization(getComponentRequest());
	}
}
