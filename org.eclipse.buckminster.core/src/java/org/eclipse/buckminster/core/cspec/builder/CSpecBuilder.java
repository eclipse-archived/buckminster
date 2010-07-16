/*****************************************************************************
 * Copyright (c) 2006-2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 * - Cloudsmith Inc - initial API and implementation.
 * - Carsten Reckord, Yatta Solutions GmbH - Synthetic source bundle
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.P2Constants;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.GeneratorAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.MissingAttributeException;
import org.eclipse.buckminster.core.cspec.model.MissingDependencyException;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.metadata.RequiredCapability;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.expression.IMatchExpression;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CSpecBuilder implements ICSpecData {
	private HashMap<String, AttributeBuilder> attributes;

	private String componentType;

	private HashMap<String, ComponentRequestBuilder> dependencies;

	private Documentation documentation;

	private HashMap<IComponentIdentifier, GeneratorBuilder> generators;

	private String name;

	private URL projectInfo;

	private String shortDesc;

	private Version version;

	private Filter filter;

	public CSpecBuilder() {
	}

	@Deprecated
	public CSpecBuilder(IMetadataRepository mdr, IInstallableUnit iu) throws CoreException {
		this(RMContext.getGlobalPropertyAdditions(), mdr, iu);
	}

	public CSpecBuilder(Map<String, ? extends Object> properties, IMetadataRepository mdr, IInstallableUnit iu) throws CoreException {
		String id = iu.getId();
		boolean isFeature = id.endsWith(P2Constants.FEATURE_GROUP);
		if (isFeature) {
			id = id.substring(0, id.length() - P2Constants.FEATURE_GROUP.length());
			setComponentTypeID(IComponentType.ECLIPSE_FEATURE);
		} else
			setComponentTypeID(IComponentType.OSGI_BUNDLE);

		setName(id);
		setVersion(iu.getVersion());

		IMatchExpression<IInstallableUnit> filterExpr = iu.getFilter();
		if (filterExpr != null) {
			// TODO: Rewrite to accept non-osgi type filters
			boolean filterOK = false;
			Object[] parameters = filterExpr.getParameters();
			if (parameters.length == 1) {
				Object param = parameters[0];
				if (param instanceof org.osgi.framework.Filter) {
					try {
						Filter flt = FilterFactory.newInstance(param.toString());
						flt = FilterUtils.replaceAttributeNames(flt, "osgi", TargetPlatform.TARGET_PREFIX); //$NON-NLS-1$
						setFilter(flt);
						filterOK = true;
					} catch (InvalidSyntaxException e) {
						throw BuckminsterException.wrap(e);
					}
				}
			}
			if (!filterOK)
				throw BuckminsterException.fromMessage("Unable to convert requirement filter %s into an LDAP filter", filterExpr); //$NON-NLS-1$
		}

		boolean hasBogusFragments = false;
		if (isFeature) {
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=213437
			Object tmp = properties.get("buckminster.handle.incomplete.platform.features"); //$NON-NLS-1$
			if (tmp instanceof String && "true".equalsIgnoreCase((String) tmp)) { //$NON-NLS-1$
				// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=213437
				hasBogusFragments = "org.eclipse.platform".equals(id) //$NON-NLS-1$
						|| "org.eclipse.equinox.executable".equals(id) //$NON-NLS-1$
						|| "org.eclipse.rcp".equals(id); //$NON-NLS-1$
			} else {
				// We still need this here due to
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=319345
				hasBogusFragments = "org.eclipse.equinox.executable".equals(id); //$NON-NLS-1$
			}
		}

		for (IRequirement cap : iu.getRequirements()) {
			// We only bother with direct dependencies to other IU's here
			// since package imports etc. are not yet supported
			//
			IMatchExpression<IInstallableUnit> matches = cap.getMatches();
			String namespace = RequiredCapability.extractNamespace(matches);
			if (namespace == null)
				continue;

			id = RequiredCapability.extractName(matches);
			if (id == null)
				continue;
			if (id.endsWith("_root") || id.contains("_root.")) //$NON-NLS-1$ //$NON-NLS-2$
				// TODO: Handle binary feature contribution.
				continue;

			String ctype;
			if (IInstallableUnit.NAMESPACE_IU_ID.equals(namespace)) {
				if (id.endsWith(P2Constants.FEATURE_GROUP)) {
					id = id.substring(0, id.length() - P2Constants.FEATURE_GROUP.length());
					ctype = IComponentType.ECLIPSE_FEATURE;
				} else if (isFeature)
					ctype = IComponentType.OSGI_BUNDLE;
				else
					continue;
			} else if (IComponentType.OSGI_BUNDLE.equals(namespace))
				ctype = namespace;
			else
				// Package or something else that we don't care about here
				continue;

			filterExpr = cap.getFilter();
			String filterStr = null;
			if (filterExpr != null) {
				// TODO: Rewrite to accept non-osgi type filters
				boolean filterOK = false;
				Object[] parameters = filterExpr.getParameters();
				if (parameters.length == 1) {
					Object param = parameters[0];
					if (param instanceof org.osgi.framework.Filter) {
						filterStr = param.toString();
						filterOK = true;
					}
				}
				if (!filterOK)
					throw BuckminsterException.fromMessage("Unable to convert requirement filter %s into an LDAP filter", filterExpr); //$NON-NLS-1$
			}

			if (cap.getMin() == 0) {
				if (filterStr == null)
					filterStr = ComponentRequest.FILTER_ECLIPSE_P2_OPTIONAL;
				else {
					filterStr = "(&" + ComponentRequest.FILTER_ECLIPSE_P2_OPTIONAL + filterStr + ')'; //$NON-NLS-1$
				}
			} else if (hasBogusFragments && ctype == IComponentType.OSGI_BUNDLE && filterStr != null) {
				// Don't add unless this requirement can be satisfied within the
				// same mdr
				IQuery<IInstallableUnit> query = QueryUtil.createMatchQuery(matches);
				IQueryResult<IInstallableUnit> result = mdr.query(query, null);
				if (result.isEmpty())
					continue;
			}

			ComponentRequestBuilder crb = new ComponentRequestBuilder();
			crb.setName(id);
			crb.setComponentTypeID(ctype);
			crb.setVersionRange(RequiredCapability.extractRange(matches));

			if (filterStr != null) {
				try {
					Filter flt = FilterFactory.newInstance(filterStr);
					flt = FilterUtils.replaceAttributeNames(flt, "osgi", TargetPlatform.TARGET_PREFIX); //$NON-NLS-1$
					crb.setFilter(flt);
				} catch (InvalidSyntaxException e) {
					throw BuckminsterException.wrap(e);
				}
			}
			addDependency(crb);
		}
		if (!isFeature && !name.endsWith(".source")) { //$NON-NLS-1$
			ComponentRequestBuilder srcDep = createDependencyBuilder();
			srcDep.setName(name + ".source"); //$NON-NLS-1$
			srcDep.setComponentTypeID(IComponentType.OSGI_BUNDLE);
			srcDep.setVersionRange(VersionHelper.exactRange(iu.getVersion()));
			try {
				srcDep.setFilter(FilterFactory.newInstance(ComponentRequest.FILTER_OPTIONAL_SOURCE_BUNDLE));
			} catch (InvalidSyntaxException e) {
				// This won't happen on that particular filter
			}
			addDependency(srcDep);
		}
	}

	public ActionBuilder addAction(String actionName, boolean publ, String actorName, boolean always) throws AttributeAlreadyDefinedException {
		ActionBuilder bld = createActionBuilder();
		bld.setName(actionName);
		bld.setPublic(publ);
		bld.setActorName(actorName);
		bld.setAlways(always);
		addAttribute(bld);
		return bld;
	}

	public ArtifactBuilder addArtifact(String n, boolean publ, IPath base) throws AttributeAlreadyDefinedException {
		ArtifactBuilder bld = createArtifactBuilder();
		bld.setName(n);
		bld.setPublic(publ);
		bld.setBase(base);
		addAttribute(bld);
		return bld;
	}

	public void addAttribute(IAttribute attribute) throws AttributeAlreadyDefinedException {
		String attrName = attribute.getName();
		if (attributes == null)
			attributes = new HashMap<String, AttributeBuilder>();
		else if (attributes.containsKey(attrName))
			throw new AttributeAlreadyDefinedException(name, attrName);
		attributes.put(attrName, attribute.getAttributeBuilder(this));
	}

	public boolean addDependency(IComponentRequest dependency) throws CoreException {
		String depName = dependency.getName();
		String depType = dependency.getComponentTypeID();
		ComponentRequestBuilder bld;
		if (dependency instanceof ComponentRequestBuilder)
			bld = (ComponentRequestBuilder) dependency;
		else {
			bld = createDependencyBuilder();
			bld.initFrom(dependency);
		}

		ComponentRequestBuilder old = getDependency(depName, depType);
		if (old == null) {
			if (dependencies == null)
				dependencies = new HashMap<String, ComponentRequestBuilder>();

			dependencies.put(depName, bld);
			return true;
		}

		String oldType = old.getComponentTypeID();
		if (oldType != null && depType != null && !oldType.equals(depType)) {
			// The types of the components differ. Remove the unqualified
			// entry and add the new qualified ones
			//
			dependencies.remove(depName);
			StringBuilder nameBld = new StringBuilder(depName);
			nameBld.append(CSpec.COMPONENT_NAME_TYPE_SEPARATOR);
			int len = nameBld.length();
			nameBld.append(oldType);
			dependencies.put(nameBld.toString(), old);
			nameBld.setLength(len);
			nameBld.append(depType);
			dependencies.put(nameBld.toString(), bld);
			return true;
		}

		// We cannot determine a difference in component type so the
		// ranges must be mergeable
		//
		VersionRange vd = old.getVersionRange();
		VersionRange nvd = dependency.getVersionRange();
		if (vd == null)
			vd = nvd;
		else {
			if (nvd != null) {
				VersionRange isect = vd.intersect(nvd);
				if (isect == null) {
					// Version ranges were not possible to merge, i.e. no
					// intersection. We
					// log a warning about this and select the higher range.
					//
					CorePlugin.getLogger()
							.warning(NLS.bind(Messages.Dependency_0_is_defined_more_then_once_in_component_1, getName(), old.getName()));
					if (vd.getMinimum().compareTo(nvd.getMaximum()) < 0)
						vd = nvd;
				} else
					vd = isect;
			}
		}

		Filter fl = old.getFilter();
		Filter nfl = dependency.getFilter();
		if (fl == null || nfl == null)
			fl = null;
		else {
			if (!fl.equals(nfl)) {
				try {
					fl = FilterFactory.newInstance("(|" + fl + nfl + ')'); //$NON-NLS-1$
				} catch (InvalidSyntaxException e) {
					throw BuckminsterException.wrap(e);
				}
			}
		}

		if (vd == old.getVersionRange() && fl == old.getFilter())
			return false;

		if (oldType == null && depType != null)
			old.setComponentTypeID(depType);
		old.setVersionRange(vd);
		old.setFilter(fl);
		return false;
	}

	public void addGenerator(IGenerator generator) throws GeneratorAlreadyDefinedException {
		IComponentIdentifier ci = generator.getGeneratedIdentifier();
		if (generators == null)
			generators = new HashMap<IComponentIdentifier, GeneratorBuilder>();
		else if (generators.containsKey(ci))
			throw new GeneratorAlreadyDefinedException(name, ci);

		GeneratorBuilder bld = createGeneratorBuilder();
		bld.initFrom(generator);
		generators.put(ci, bld);
	}

	public GroupBuilder addGroup(String groupName, boolean publ) throws AttributeAlreadyDefinedException {
		GroupBuilder bld = createGroupBuilder();
		bld.setName(groupName);
		bld.setPublic(publ);
		addAttribute(bld);
		return bld;
	}

	public ActionBuilder addInternalAction(String actionName, boolean publ) throws AttributeAlreadyDefinedException {
		return addAction(actionName, publ, null, true);
	}

	public void clear() {
		name = null;
		componentType = null;
		version = null;
		filter = null;
		projectInfo = null;
		documentation = null;
		shortDesc = null;
		dependencies = null;
		attributes = null;
		generators = null;
	}

	public ActionArtifactBuilder createActionArtifactBuilder() {
		return new ActionArtifactBuilder(this);
	}

	public ActionBuilder createActionBuilder() {
		return new ActionBuilder(this);
	}

	public ArtifactBuilder createArtifactBuilder() {
		return new ArtifactBuilder(this);
	}

	public AttributeBuilder createAttributeBuilder() {
		return new AttributeBuilder(this);
	}

	public CSpec createCSpec() {
		return new CSpec(this);
	}

	public ComponentRequestBuilder createDependencyBuilder() {
		return new ComponentRequestBuilder();
	}

	public GeneratorBuilder createGeneratorBuilder() {
		return new GeneratorBuilder(this);
	}

	public GroupBuilder createGroupBuilder() {
		return new GroupBuilder(this);
	}

	public void finalWrapUp() {
		if (attributes != null && dependencies != null) {
			for (AttributeBuilder attr : attributes.values()) {
				if (attr instanceof GroupBuilder)
					((GroupBuilder) attr).finalWrapUp(dependencies);
				else if (attr instanceof ActionBuilder)
					((ActionBuilder) attr).getPrerequisitesBuilder().finalWrapUp(dependencies);
			}
		}
	}

	public ActionBuilder getActionBuilder(String actionName) {
		if (attributes != null) {
			AttributeBuilder attr = attributes.get(actionName);
			if (attr instanceof ActionBuilder)
				return (ActionBuilder) attr;
		}
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapterType) {
		if (CSpecBuilder.class.isAssignableFrom(adapterType))
			return this;

		if (CSpec.class.isAssignableFrom(adapterType))
			return createCSpec();

		return Platform.getAdapterManager().getAdapter(this, adapterType);
	}

	public ArtifactBuilder getArtifactBuilder(String artifactName) {
		AttributeBuilder attr = attributes.get(artifactName);
		return attr instanceof ArtifactBuilder ? (ArtifactBuilder) attr : null;
	}

	@Override
	public AttributeBuilder getAttribute(String attrName) {
		return attributes == null ? null : attributes.get(attrName);
	}

	@Override
	public Map<String, AttributeBuilder> getAttributes() {
		return attributes;
	}

	@Override
	public ComponentIdentifier getComponentIdentifier() {
		return new ComponentIdentifier(name, componentType, version);
	}

	@Override
	public String getComponentTypeID() {
		return componentType;
	}

	@Override
	public Collection<ComponentRequestBuilder> getDependencies() {
		return dependencies == null ? Collections.<ComponentRequestBuilder> emptyList() : dependencies.values();
	}

	@Override
	public ComponentRequestBuilder getDependency(String dependencyName, String depType) throws MissingDependencyException {
		ComponentRequestBuilder dependency = null;
		if (dependencies != null) {
			dependency = dependencies.get(dependencyName);
			if (dependency == null && depType != null)
				dependency = dependencies.get(dependencyName + CSpec.COMPONENT_NAME_TYPE_SEPARATOR + depType);
		}
		return dependency;
	}

	public Map<String, ComponentRequestBuilder> getDependencyMap() {
		return dependencies;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public Collection<GeneratorBuilder> getGeneratorList() {
		return generators == null ? Collections.<GeneratorBuilder> emptySet() : generators.values();
	}

	public GroupBuilder getGroup(String groupName) {
		AttributeBuilder attr = attributes.get(groupName);
		return attr instanceof GroupBuilder ? (GroupBuilder) attr : null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public URL getProjectInfo() {
		return projectInfo;
	}

	public ActionBuilder getRequiredAction(String actionName) throws MissingAttributeException {
		AttributeBuilder attr = attributes.get(actionName);
		if (attr instanceof ActionBuilder)
			return (ActionBuilder) attr;
		throw new MissingAttributeException(name, actionName);
	}

	public ArtifactBuilder getRequiredArtifact(String artifactName) throws MissingAttributeException {
		if (attributes != null) {
			AttributeBuilder attr = attributes.get(artifactName);
			if (attr instanceof ArtifactBuilder)
				return (ArtifactBuilder) attr;
		}
		throw new MissingAttributeException(name, artifactName);
	}

	public AttributeBuilder getRequiredAttribute(String attrName) throws MissingAttributeException {
		if (attributes != null) {
			AttributeBuilder attr = attributes.get(attrName);
			if (attr != null)
				return attr;
		}
		throw new MissingAttributeException(name, attrName);
	}

	public ComponentRequestBuilder getRequiredDependency(String dependencyName, String componentTypeID) throws MissingDependencyException {
		ComponentRequestBuilder dependency = getDependency(dependencyName, componentTypeID);
		if (dependency == null)
			throw new MissingDependencyException(name, dependencyName);
		return dependency;
	}

	public GroupBuilder getRequiredGroup(String groupName) throws MissingAttributeException {
		AttributeBuilder attr = attributes.get(groupName);
		if (attr instanceof GroupBuilder)
			return (GroupBuilder) attr;
		throw new MissingAttributeException(name, groupName);
	}

	@Override
	public String getShortDesc() {
		return shortDesc;
	}

	public String getTagInfo(String parentInfo) {
		return CSpec.getTagInfo(getComponentIdentifier(), projectInfo, parentInfo);
	}

	@Override
	public Version getVersion() {
		return version;
	}

	public void initFrom(ICSpecData cspec) throws CoreException {
		name = cspec.getName();
		componentType = cspec.getComponentTypeID();
		version = cspec.getVersion();
		filter = cspec.getFilter();
		projectInfo = cspec.getProjectInfo();
		documentation = cspec.getDocumentation();
		shortDesc = cspec.getShortDesc();

		Map<String, ? extends IAttribute> attrs = cspec.getAttributes();
		if (attrs.size() > 0) {
			attributes = new HashMap<String, AttributeBuilder>(attrs.size());
			for (IAttribute attr : attrs.values())
				attributes.put(attr.getName(), attr.getAttributeBuilder(this));
		} else
			attributes = null;

		Collection<? extends IComponentRequest> deps = cspec.getDependencies();
		if (deps.size() > 0) {
			dependencies = new HashMap<String, ComponentRequestBuilder>(deps.size());
			for (IComponentRequest dep : deps)
				addDependency(dep);
		} else
			dependencies = null;

		Collection<? extends IGenerator> gens = cspec.getGeneratorList();
		if (gens.size() > 0) {
			generators = new HashMap<IComponentIdentifier, GeneratorBuilder>(gens.size());
			for (IGenerator gen : gens) {
				GeneratorBuilder gb = createGeneratorBuilder();
				gb.initFrom(gen);
				generators.put(gen.getGeneratedIdentifier(), gb);
			}
		} else
			generators = null;
	}

	public void removeAttribute(String attributeName) {
		if (attributes != null)
			attributes.remove(attributeName);
	}

	public void removeDependency(String dependencyName) {
		if (dependencies != null)
			dependencies.remove(dependencyName);
	}

	public void removeGenerator(String generatorName) {
		if (generators != null)
			generators.remove(generatorName);
	}

	public void setComponentTypeID(String componentType) {
		this.componentType = componentType;
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProjectInfo(URL projectInfo) {
		this.projectInfo = projectInfo;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
}
