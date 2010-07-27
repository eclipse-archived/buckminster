/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import static org.eclipse.buckminster.core.XMLConstants.BM_CSPEC_NS;
import static org.eclipse.buckminster.core.XMLConstants.BM_CSPEC_PREFIX;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IActionArtifact;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.IGroup;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class CSpec extends UUIDKeyed implements IUUIDPersisted, ICSpecData {
	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	public static final String ATTR_PROJECT_INFO = "projectInfo"; //$NON-NLS-1$

	public static final String ATTR_SHORT_DESC = "shortDesc"; //$NON-NLS-1$

	public static final String ELEM_ACTIONS = "actions"; //$NON-NLS-1$

	public static final String ELEM_ARTIFACTS = "artifacts"; //$NON-NLS-1$

	public static final String ELEM_DEPENDENCIES = "dependencies"; //$NON-NLS-1$

	public static final String ELEM_GENERATORS = "generators"; //$NON-NLS-1$

	public static final String ELEM_DEPENDENCY = "dependency"; //$NON-NLS-1$

	public static final String ELEM_GROUPS = "groups"; //$NON-NLS-1$

	public static final String SELF_ARTIFACT = "buckminster.component.self"; //$NON-NLS-1$

	public static final String TAG = "cspec"; //$NON-NLS-1$

	// This string should be something that is very unlikely to appear in a
	// component name
	public static final String COMPONENT_NAME_TYPE_SEPARATOR = "/!@@!/"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 4;

	private static final Comparator<Attribute> attributeSorter = new Comparator<Attribute>() {
		@Override
		public int compare(Attribute o1, Attribute o2) {
			if (o1.isPublic() == o2.isPublic())
				return o1.getName().compareTo(o2.getName());
			return o1.isPublic() ? -1 : 1;
		}
	};

	public static Set<IPath> createUnmodifiablePaths(Set<IPath> aSet) {
		if (aSet == null || aSet.size() == 0)
			aSet = Collections.emptySet();
		else {
			HashSet<IPath> saxablePaths = new HashSet<IPath>();
			for (IPath path : aSet)
				saxablePaths.add(SaxablePath.coerce(path));
			aSet = Collections.unmodifiableSet(saxablePaths);
		}
		return aSet;
	}

	public static String getTagInfo(ComponentIdentifier ci, URL projectInfoURL, String parentInfo) {
		StringBuilder bld = new StringBuilder();

		if (projectInfoURL != null) {
			bld.append("project: "); //$NON-NLS-1$
			bld.append(projectInfoURL);
			bld.append(", "); //$NON-NLS-1$
		}

		if (parentInfo != null) {
			int pathIdx = parentInfo.indexOf("path: "); //$NON-NLS-1$
			if (projectInfoURL == null)
				bld.append(parentInfo);
			else if (pathIdx >= 0)
				bld.append(parentInfo, pathIdx, parentInfo.length());

			if (pathIdx >= 0)
				bld.append(" -> "); //$NON-NLS-1$
			else
				bld.append(", path: "); //$NON-NLS-1$
		} else
			bld.append("path: "); //$NON-NLS-1$
		ci.toString(bld);
		return bld.toString();
	}

	private final Map<String, Attribute> attributes;

	private final ComponentIdentifier componentIdentifier;

	private final List<ComponentRequest> dependencies;

	private final Map<ComponentIdentifier, Generator> generators;

	private final Documentation documentation;

	private final String shortDesc;

	private final Attribute selfAttribute;

	private final Filter filter;

	private final URL projectInfo;

	public CSpec(CSpecBuilder cspecBld) {
		cspecBld.finalWrapUp();
		componentIdentifier = cspecBld.getComponentIdentifier();
		projectInfo = cspecBld.getProjectInfo();
		documentation = cspecBld.getDocumentation();
		shortDesc = cspecBld.getShortDesc();
		filter = cspecBld.getFilter();
		selfAttribute = new TopLevelAttribute(SELF_ARTIFACT) {
			@Override
			protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder) {
				// This should be OK. Noone should ever clone the self attribute
				//
				return null;
			}

			@Override
			protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, ? extends Object> local, Stack<IAttributeFilter> filters)
					throws CoreException {
				IPath me = getComponentLocation();
				PathGroup meGroup;
				if (me.hasTrailingSeparator())
					//
					// A folder will act as the base for the component
					//
					meGroup = new PathGroup(me, Trivial.EMPTY_PATH_ARRAY);
				else
					// The parent folder will be the base since the component
					// itself
					// is a file.
					//
					meGroup = new PathGroup(me.removeLastSegments(1).addTrailingSeparator(), new IPath[] { new Path(me.lastSegment()) });

				return new PathGroup[] { meGroup };
			}
		};
		selfAttribute.setCSPec(this);

		Map<String, AttributeBuilder> attrs = cspecBld.getAttributes();
		int top = (attrs == null) ? 0 : attrs.size();
		if (top == 0)
			attributes = Collections.emptyMap();
		else {
			Map<String, Attribute> map;
			Collection<AttributeBuilder> values = attrs.values();
			if (top == 1) {
				Attribute attr = values.iterator().next().createAttribute();
				attr.setCSPec(this);
				map = Collections.singletonMap(attr.getName(), attr);
			} else {
				map = new HashMap<String, Attribute>(top);
				for (AttributeBuilder bld : values) {
					Attribute attr = bld.createAttribute();
					attr.setCSPec(this);
					map.put(attr.getName(), attr);
				}
			}
			attributes = Collections.unmodifiableMap(map);
		}

		List<ComponentRequestBuilder> deps = cspecBld.getDependencyBuilders();
		top = (deps == null) ? 0 : deps.size();
		if (top == 0)
			dependencies = Collections.emptyList();
		else {
			List<ComponentRequest> list;
			if (top == 1) {
				dependencies = Collections.singletonList(deps.get(0).createComponentRequest());
			} else {
				// We use a TreeMap to assert that the dependencies will be
				// written in the exact same order at all times
				//
				list = new ArrayList<ComponentRequest>();
				for (ComponentRequestBuilder dep : deps)
					list.add(dep.createComponentRequest());
				dependencies = Collections.unmodifiableList(list);
			}
		}

		Collection<GeneratorBuilder> gens = cspecBld.getGeneratorList();
		top = gens.size();
		if (top == 0)
			generators = Collections.emptyMap();
		else {
			Map<ComponentIdentifier, Generator> map;
			if (top == 1) {
				GeneratorBuilder bld = gens.iterator().next();
				Generator gen = bld.createGenerator(this);
				map = Collections.<ComponentIdentifier, Generator> singletonMap(bld.getGeneratedIdentifier(), gen);
			} else {
				// We use a TreeMap to assert that the generators will be
				// written in the exact same order at all times
				//
				map = new TreeMap<ComponentIdentifier, Generator>();
				for (GeneratorBuilder bld : gens) {
					Generator gen = bld.createGenerator(this);
					map.put(bld.getGeneratedIdentifier(), gen);
				}
			}
			generators = Collections.unmodifiableMap(map);
		}
	}

	@Override
	public Attribute getAttribute(String name) {
		Attribute attr = attributes.get(name);
		if (attr == null && name.equals(SELF_ARTIFACT))
			attr = selfAttribute;
		return attr;
	}

	@Override
	public Map<String, Attribute> getAttributes() {
		return attributes;
	}

	public Attribute[] getAttributes(Collection<String> attributeNames) throws MissingAttributeException {
		int sz = attributeNames == null ? 0 : attributeNames.size();
		Attribute[] attrs = new Attribute[sz];
		int idx = 0;
		for (String str : attributeNames)
			attrs[idx++] = getRequiredAttribute(str);
		return attrs;
	}

	public Attribute[] getAttributes(String... attributeNames) throws MissingAttributeException {
		return getAttributes(Arrays.asList(attributeNames));
	}

	public List<Attribute> getAttributesProducedByActions(boolean includePrivate) throws CoreException {
		ModelCache ctx = new ModelCache();
		ArrayList<Attribute> attrs = new ArrayList<Attribute>();
		for (Attribute ag : attributes.values())
			if ((includePrivate || ag.isPublic()) && ag.isProducedByActions(ctx))
				attrs.add(ag);
		return attrs;
	}

	public Attribute getBindEntryPoint() {
		return getAttribute(WellknownActions.BUCKMINSTER.BIND_ENTRYPOINT.toString());
	}

	@Override
	public ComponentIdentifier getComponentIdentifier() {
		return componentIdentifier;
	}

	public IPath getComponentLocation() throws CoreException {
		return WorkspaceInfo.getComponentLocation(this);
	}

	@Override
	public String getComponentTypeID() {
		return componentIdentifier.getComponentTypeID();
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Collection<ComponentRequest> getDependencies() {
		return dependencies;
	}

	@SuppressWarnings("deprecation")
	@Deprecated
	@Override
	public ComponentRequest getDependency(String depName, String depType) throws MissingDependencyException {
		return getDependency(depName, depType, null);
	}

	@Override
	public ComponentRequest getDependency(String depName, String depType, VersionRange depRange) throws MissingDependencyException {
		int idx = dependencies.size();
		while (--idx >= 0) {
			ComponentRequest dependency = dependencies.get(idx);
			if (!depName.equals(dependency.getName()))
				continue;
			if (depType != null && dependency.getComponentTypeID() != null && !depType.equals(dependency.getComponentTypeID()))
				continue;
			if (depRange != null && dependency.getVersionRange() != null && dependency.getVersionRange().intersect(depRange) == null)
				continue;
			return dependency;
		}
		throw new MissingDependencyException(componentIdentifier.toString(), depName);
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
	public Collection<Generator> getGeneratorList() {
		return generators.values();
	}

	@Override
	public String getName() {
		return componentIdentifier.getName();
	}

	public Attribute getPrebind() {
		return getAttribute(WellknownActions.BUCKMINSTER.PREBIND.toString());
	}

	@Override
	public URL getProjectInfo() {
		return projectInfo;
	}

	/**
	 * <p>
	 * Creates a list of attribute qualified external dependencies that will be
	 * unique with respect to their respective {@link ComponentRequest}.
	 * </p>
	 * <p>
	 * Only those dependencies that are implied through <code>targets</code> are
	 * included in the result if the argument <code>prune</code> is set to
	 * <code>true</code>. When the <code>prune</code> argument is
	 * <code>false</code>, all external dependencies will be included and those
	 * not implied by a matched public artifact group will have an empty
	 * attribute array.
	 * </p>
	 * 
	 * @param attributes
	 *            The attributes that implies and qualifies dependencies. Might
	 *            be an empty array but never <code>null</code>.
	 * @param prune
	 *            True if the result should be pruned to only include
	 *            dependencies that has an attribute.
	 * @return A list of attribute qualified dependencies, unique per
	 *         IComponentRequest.
	 * @see #getActionsByName(String[])
	 * @see #getGroupsByName(String[])
	 */
	public List<QualifiedDependency> getQualifiedDependencies(boolean attributePrune) throws CoreException {
		Map<ComponentRequest, Set<String>> deps = new HashMap<ComponentRequest, Set<String>>();
		if (!attributePrune) {
			// All dependencies must be included in the result. Even the one for
			// which there is no requested attribute.
			//
			for (ComponentRequest dep : getDependencies())
				deps.put(dep, new HashSet<String>());
		}

		for (IAttribute ag : getAttributes().values())
			addDependencyBundle(deps, ag);

		if (!generators.isEmpty()) {
			// Components appointed by generators are mandatory
			//
			for (IGenerator generator : generators.values()) {
				String component = generator.getComponent();
				if (component == null) {
					addDependencyBundle(deps, getRequiredAttribute(generator.getAttribute()));
					continue;
				}

				ComponentRequest dep = getDependency(component, null, null);
				Set<String> attrs = deps.get(dep);
				if (attrs == null) {
					attrs = new HashSet<String>();
					deps.put(dep, attrs);
				}
				attrs.add(generator.getAttribute());
			}
		}

		List<QualifiedDependency> qDeps = new ArrayList<QualifiedDependency>(deps.size());
		for (Map.Entry<ComponentRequest, Set<String>> entry : deps.entrySet())
			qDeps.add(new QualifiedDependency(entry.getKey(), entry.getValue()));
		return qDeps;
	}

	public Attribute getReferencedAttribute(String componentName, String componentType, VersionRange versionRange, String attributeName,
			IModelCache ctx) throws CoreException {
		CSpec referencedCSpec = getReferencedCSpec(componentName, componentType, versionRange, ctx);
		if (referencedCSpec == null)
			return null;

		Attribute referencedAttr = referencedCSpec.getRequiredAttribute(attributeName);
		if (referencedAttr.isEnabled(ctx)) {
			if (referencedCSpec == this || referencedAttr.isPublic())
				return referencedAttr;
			throw new MissingAttributeException(referencedCSpec.getComponentIdentifier().toString(), attributeName, true);
		}
		return null;
	}

	public CSpec getReferencedCSpec(String componentName, String componentType, VersionRange versionRange, IModelCache ctx) throws CoreException {
		if (componentName == null)
			return this;

		ComponentRequest dep = getDependency(componentName, componentType, versionRange);
		if (!dep.isEnabled(ctx.getProperties()))
			return null;

		try {
			return ctx.findCSpec(this, dep);
		} catch (MissingComponentException e) {
			if (dep.isOptional())
				return null;
			throw e;
		}
	}

	public Attribute getRequiredAttribute(String name) throws MissingAttributeException {
		Attribute attr = getAttribute(name);
		if (attr == null)
			throw new MissingAttributeException(componentIdentifier.toString(), name);
		return attr;
	}

	@Override
	public String getShortDesc() {
		return shortDesc;
	}

	public String getTagInfo(String parentInfo) {
		return getTagInfo(componentIdentifier, projectInfo, parentInfo);
	}

	@Override
	public Version getVersion() {
		return componentIdentifier.getVersion();
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException {
		return sm.getCSpecs().contains(this);
	}

	public boolean isPruneApplicable(RMContext context, Map<String, ? extends Object> properties, boolean pruneForAttributes, Set<String> attrNames)
			throws CoreException {
		Collection<ComponentRequest> deps = getDependencies();
		for (ComponentRequest dep : deps) {
			Filter depFilter = dep.getFilter();
			if (depFilter == null)
				continue;

			depFilter.addConsultedAttributes(context.getFilterAttributeUsageMap());
			if (!depFilter.matchCase(properties))
				//
				// This dependency is pruned
				//
				return true;
		}

		// No dependency pruning occurred.
		// Let's check for attribute pruning
		//
		Set<String> allAttrNames = getAttributes().keySet();

		if (pruneForAttributes) {
			if (attrNames.isEmpty())
				attrNames = allAttrNames;
		} else
			attrNames = allAttrNames;

		Set<String> referencedAttrNames = new HashSet<String>();
		for (String attrName : attrNames) {
			Attribute attr = getAttribute(attrName);
			if (attr != null)
				addReferencedDependencies(null, referencedAttrNames, attr, null);
		}
		return !allAttrNames.equals(referencedAttrNames);
	}

	public CSpec prune(RMContext context, Map<String, ? extends Object> properties, boolean pruneForAttributes, Set<String> attrNames)
			throws CoreException {
		if (!isPruneApplicable(context, properties, pruneForAttributes, attrNames))
			return this;

		CSpecBuilder bld = new CSpecBuilder();
		bld.setComponentTypeID(getComponentTypeID());
		bld.setDocumentation(getDocumentation());
		bld.setName(getName());
		bld.setProjectInfo(getProjectInfo());
		bld.setShortDesc(getShortDesc());
		bld.setVersion(getVersion());
		bld.setFilter(getFilter());

		Set<String> allAttrNames = getAttributes().keySet();

		if (pruneForAttributes) {
			if (attrNames.isEmpty())
				attrNames = allAttrNames;
		} else
			attrNames = allAttrNames;

		// Find all truly referenced dependencies. A dependency can
		// be referenced from an attribute or from a generator
		//
		Collection<ComponentRequest> deps;
		if (attrNames.isEmpty() && getGeneratorList().isEmpty())
			deps = getDependencies();
		else {
			Set<ComponentRequest> referencedDeps = new HashSet<ComponentRequest>();

			Set<String> referencedAttrs = new HashSet<String>();
			for (String attrName : attrNames) {
				Attribute attr = getAttribute(attrName);
				if (attr != null)
					addReferencedDependencies(referencedDeps, referencedAttrs, attr, null);
			}

			for (IGenerator generator : getGeneratorList()) {
				String component = generator.getComponent();
				if (component == null)
					addReferencedDependencies(referencedDeps, referencedAttrs, getRequiredAttribute(generator.getAttribute()), null);
				else
					referencedDeps.add(getDependency(component, null, null));
			}
			deps = referencedDeps;
		}

		// Prune the dependencies according to LDAP filters. Add the
		// dependencies
		// that match to the new cspec
		//
		for (ComponentRequest dep : deps) {
			Filter depFilter = dep.getFilter();
			if (depFilter != null) {
				depFilter.addConsultedAttributes(context.getFilterAttributeUsageMap());
				if (!depFilter.matchCase(properties))
					//
					// This dependency is pruned
					//
					continue;
			}
			bld.addDependency(dep);
		}

		// Add all attributes that can fulfill the prerequisites transitively
		// using
		// the filtered dependency list
		//
		for (String attrName : attrNames) {
			Attribute attr = getAttribute(attrName);
			if (attr != null && dependenciesFulfilled(attr, bld, null))
				bld.addAttribute(attr);
		}

		// Add all generators that can be added with respect to dependency or
		// local attribute
		//
		for (IGenerator generator : getGeneratorList()) {
			String component = generator.getComponent();
			if (component == null) {
				if (bld.getAttribute(generator.getAttribute()) == null)
					//
					// Local attribute no longer exists
					//
					continue;
			} else if (bld.getDependency(component, null, null) == null)
				//
				// Dependency no longer exists
				//
				continue;

			bld.addGenerator(generator);
		}
		return bld.createCSpec();
	}

	@Override
	public void remove(StorageManager sm) throws CoreException {
		UUID thisId = getId();
		if (!sm.getResolutions().getReferencingKeys(thisId, "cspecId").isEmpty()) //$NON-NLS-1$
			throw new ReferentialIntegrityException(this, "remove", Messages.Referenced_from_Resolution); //$NON-NLS-1$

		sm.getCSpecs().removeElement(thisId);
	}

	@Override
	public void store(StorageManager sm) throws CoreException {
		sm.getCSpecs().putElement(this);
	}

	@Override
	public void toSax(ContentHandler receiver) throws SAXException {
		receiver.startDocument();
		toSax(receiver, BM_CSPEC_NS, BM_CSPEC_PREFIX, getDefaultTag());
		receiver.endDocument();
	}

	/**
	 * Verify that the specification is consistent. This method checks that all
	 * dependencies and attributes that are referenced from all prerequisites
	 * are present and that no circular references exists within the cspec.
	 * 
	 * @throws MissingDependencyException
	 * @throws MissingAttributeException
	 * @throws CircularReferenceException
	 */
	public void verifyConsistency() throws MissingDependencyException, MissingAttributeException, CircularReferenceException {
		// Verify the prerequisites of all attributes except
		// the action artifacts (since their prerequisites
		// will be equal to them of of the action that owns
		// them).
		//
		for (Attribute attr : attributes.values())
			if (!(attr instanceof IActionArtifact))
				verifyPrerequisites(attr, null);

		// Verify validity of generators
		//
		for (IGenerator generator : generators.values()) {
			String component = generator.getComponent();
			if (component == null)
				getRequiredAttribute(generator.getAttribute());
			else
				getDependency(component, null, null);
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		Utils.addAttribute(attrs, NamedElement.ATTR_NAME, componentIdentifier.getName());
		String ctypeID = componentIdentifier.getComponentTypeID();
		if (ctypeID != null)
			Utils.addAttribute(attrs, ComponentName.ATTR_COMPONENT_TYPE, ctypeID);

		Version version = componentIdentifier.getVersion();
		if (version != null)
			Utils.addAttribute(attrs, ComponentIdentifier.ATTR_VERSION, version.toString());

		if (projectInfo != null)
			Utils.addAttribute(attrs, ATTR_PROJECT_INFO, projectInfo.toString());

		if (shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, shortDesc);

		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());

		Utils.emitCollection(namespace, prefix, ELEM_DEPENDENCIES, ELEM_DEPENDENCY, dependencies, handler);
		Utils.emitCollection(namespace, prefix, ELEM_GENERATORS, Generator.TAG, generators.values(), handler);
		ArrayList<Attribute> topArtifacts = new ArrayList<Attribute>();
		ArrayList<Attribute> actions = new ArrayList<Attribute>();
		ArrayList<Attribute> groups = new ArrayList<Attribute>();
		for (Attribute attr : attributes.values()) {
			if (attr instanceof IAction)
				actions.add(attr);
			else if (attr instanceof IGroup)
				groups.add(attr);
			else if (!(attr instanceof IActionArtifact))
				topArtifacts.add(attr);
		}
		Collections.sort(topArtifacts, attributeSorter);
		Collections.sort(actions, attributeSorter);
		Collections.sort(groups, attributeSorter);
		Utils.emitCollection(namespace, prefix, ELEM_ARTIFACTS, null, topArtifacts, handler);
		Utils.emitCollection(namespace, prefix, ELEM_ACTIONS, null, actions, handler);
		Utils.emitCollection(namespace, prefix, ELEM_GROUPS, null, groups, handler);
	}

	@Override
	protected String getElementNamespace(String namespace) {
		return XMLConstants.BM_CSPEC_NS;
	}

	@Override
	protected String getElementPrefix(String prefix) {
		return XMLConstants.BM_CSPEC_PREFIX;
	}

	List<ActionArtifact> getActionArtifacts(Action action) {
		List<ActionArtifact> artifacts = null;
		String actionName = action.getName();
		for (IAttribute ag : attributes.values()) {
			if (!(ag instanceof IActionArtifact))
				continue;

			ActionArtifact aa = (ActionArtifact) ag;
			if (aa.getActionName().equals(actionName)) {
				if (artifacts == null)
					artifacts = new ArrayList<ActionArtifact>();
				artifacts.add(aa);
			}
		}
		if (artifacts == null)
			artifacts = Collections.emptyList();
		return artifacts;
	}

	private void addDependencyBundle(Map<ComponentRequest, Set<String>> deps, IAttribute dp) throws CoreException {
		// Make sure that the pruned CSpec has all prerequisites
		//
		for (IPrerequisite prereq : dp.getPrerequisites()) {
			if (prereq.isExternal()) {
				ComponentRequest rq = getDependency(prereq.getComponentName(), prereq.getComponentType(), prereq.getVersionRange());
				Set<String> attrs = deps.get(rq);
				if (attrs == null) {
					attrs = new HashSet<String>();
					deps.put(rq, attrs);
				}
				attrs.add(prereq.getAttribute());
			} else {
				IAttribute localGroup = getAttribute(prereq.getAttribute());
				if (localGroup instanceof IActionArtifact)
					localGroup = ((ActionArtifact) localGroup).getAction();
				addDependencyBundle(deps, localGroup);
			}
		}
	}

	private void addReferencedDependencies(Set<ComponentRequest> deps, Set<String> attrNames, Attribute attr, Stack<IAttributeFilter> filters)
			throws CoreException {
		if (attrNames.contains(attr.getName()))
			return;

		// Make sure that the pruned CSpec has all prerequisites
		//
		attrNames.add(attr.getName());

		if (attr instanceof IActionArtifact) {
			addReferencedDependencies(deps, attrNames, ((ActionArtifact) attr).getAction(), filters);
			return;
		}

		for (Prerequisite prereq : attr.getPrerequisites(filters)) {
			if (prereq.isExternal()) {
				if (deps != null)
					deps.add(getDependency(prereq.getComponentName(), prereq.getComponentType(), prereq.getVersionRange()));
			} else {
				Attribute localAttr = getRequiredAttribute(prereq.getAttribute());
				if (prereq.isPatternFilter()) {
					if (filters == null)
						filters = new Stack<IAttributeFilter>();
					filters.push(prereq);
					addReferencedDependencies(deps, attrNames, localAttr, filters);
					filters.pop();
				} else
					addReferencedDependencies(deps, attrNames, localAttr, filters);
			}
		}
	}

	private boolean dependenciesFulfilled(Attribute attr, CSpecBuilder bld, Stack<IAttributeFilter> filters) throws CoreException {
		if (attr instanceof IActionArtifact)
			attr = ((ActionArtifact) attr).getAction();

		for (Prerequisite pq : attr.getPrerequisites(filters)) {
			if (pq.isExternal()) {
				if (bld.getDependency(pq.getComponentName(), pq.getComponentType(), pq.getVersionRange()) == null)
					return false;
				continue;
			}

			if (pq.isPatternFilter()) {
				if (filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(pq);
			}
			if (!dependenciesFulfilled(getRequiredAttribute(pq.getAttribute()), bld, filters))
				return false;

			if (pq.isPatternFilter())
				filters.pop();
		}
		return true;
	}

	private void verifyNonCircularDependency(Stack<String> seenAttributes, Collection<Prerequisite> prereqs, Stack<IAttributeFilter> filters)
			throws MissingAttributeException, CircularReferenceException {
		for (Prerequisite prereq : prereqs) {
			if (prereq.isExternal())
				//
				// Can't go beyond this cspec at this stage
				//
				continue;

			Attribute ag = getRequiredAttribute(prereq.getAttribute());
			if (ag instanceof IActionArtifact)
				ag = ((ActionArtifact) ag).getAction();

			if (seenAttributes.contains(ag.getName()))
				throw new CircularReferenceException(getComponentIdentifier().toString(), seenAttributes, ag.getName());

			// Verify that this action dependency doesn't somehow
			// stem from the action itself
			//
			List<Prerequisite> agPreqs;
			if (prereq.isPatternFilter()) {
				if (filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(prereq);
				agPreqs = ag.getPrerequisites(filters);
				filters.pop();
			} else
				agPreqs = ag.getPrerequisites(filters);

			if (agPreqs.size() > 0) {
				seenAttributes.push(ag.getName());
				verifyNonCircularDependency(seenAttributes, agPreqs, filters);
				seenAttributes.pop();
			}
		}
	}

	private void verifyPrerequisites(Attribute attr, Stack<IAttributeFilter> filters) throws MissingDependencyException, MissingAttributeException,
			CircularReferenceException {
		Stack<String> seenActions = new Stack<String>();
		for (Prerequisite prereq : attr.getPrerequisites(filters)) {
			if (prereq.isExternal()) {
				// Test that the dependency is present.
				//
				getDependency(prereq.getComponentName(), prereq.getComponentType(), prereq.getVersionRange());
				continue;
			}

			Attribute ag = getRequiredAttribute(prereq.getAttribute());
			if (ag instanceof IActionArtifact)
				//
				// Verify that we can get the action
				//
				((ActionArtifact) ag).getAction();

			if (prereq.isPatternFilter()) {
				if (filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(prereq);
			}

			// Verify that this action dependency doesn't somehow
			// stem from the action itself
			//
			Collection<Prerequisite> agPreqs = ag.getPrerequisites(filters);
			if (agPreqs.size() > 0) {
				seenActions.clear();
				seenActions.push(ag.getName());
				verifyNonCircularDependency(seenActions, agPreqs, filters);
			}
			if (prereq.isPatternFilter())
				filters.pop();
		}
	}
}
