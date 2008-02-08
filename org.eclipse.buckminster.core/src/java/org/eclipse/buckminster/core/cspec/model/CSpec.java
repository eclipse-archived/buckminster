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
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.UUID;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.helpers.MapToDictionary;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Filter;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class CSpec extends UUIDKeyed implements ISaxable
{
	public static final String ATTR_FILTER = "filter";

	public static final String ATTR_PROJECT_INFO = "projectInfo";

	public static final String ATTR_SHORT_DESC = "shortDesc";

	public static final String ELEM_ACTIONS = "actions";

	public static final String ELEM_ARTIFACTS = "artifacts";

	public static final String ELEM_DEPENDENCIES = "dependencies";

	public static final String ELEM_GENERATORS = "generators";

	public static final String ELEM_DEPENDENCY = "dependency";

	public static final String ELEM_GROUPS = "groups";

	public static final String SELF_ARTIFACT = "buckminster.component.self";

	public static final String TAG = "cspec";

	public static final int SEQUENCE_NUMBER = 4;

	private static final Comparator<Attribute> s_attributeSorter = new Comparator<Attribute>()
	{
		public int compare(Attribute o1, Attribute o2)
		{
			if(o1.isPublic() == o2.isPublic())
				return o1.getName().compareTo(o2.getName());
			return o1.isPublic()
					? -1
					: 1;
		}
	};

	private final Map<String, Attribute> m_attributes;

	private final ComponentIdentifier m_componentIdentifier;

	private final Map<String, ComponentRequest> m_dependencies;

	private final Map<String, Generator> m_generators;

	private final Documentation m_documentation;

	private final String m_shortDesc;

	private final Attribute m_selfAttribute;
	
	private final Filter m_filter;

	private final URL m_projectInfo;

	public CSpec(CSpecBuilder cspecBld)
	{
		m_componentIdentifier = new ComponentIdentifier(cspecBld.getName(), cspecBld.getComponentTypeID(), cspecBld.getVersion());
		m_projectInfo = cspecBld.getProjectInfo();
		m_documentation = cspecBld.getDocumentation();
		m_shortDesc = cspecBld.getShortDesc();
		m_filter = cspecBld.getFilter();
		m_selfAttribute = new TopLevelAttribute(SELF_ARTIFACT)
		{
			@Override
			protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local, Stack<IAttributeFilter> filters)
					throws CoreException
			{
				IPath me = getComponentLocation();
				PathGroup meGroup;
				if(me.hasTrailingSeparator())
					//
					// A folder will act as the base for the component
					//
					meGroup = new PathGroup(me, Trivial.EMPTY_PATH_ARRAY);
				else
					// The parent folder will be the base since the component itself
					// is a file.
					//
					meGroup = new PathGroup(me.removeLastSegments(1).addTrailingSeparator(), new IPath[] { new Path(me.lastSegment()) });

				return new PathGroup[] { meGroup };
			}

			@Override
			protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
			{
				// This should be OK. Noone should ever clone the self attribute
				//
				return null;
			}
		};
		m_selfAttribute.setCSPec(this);

		Map<String,AttributeBuilder> attributes = cspecBld.getAttributes();
		int top = (attributes == null) ? 0 : attributes.size();
		if(top == 0)
			m_attributes = Collections.emptyMap();
		else
		{
			Map<String, Attribute> map;
			Collection<AttributeBuilder> values = attributes.values();
			if(top == 1)
			{
				Attribute attr = values.iterator().next().createAttribute();
				attr.setCSPec(this);
				map = Collections.singletonMap(attr.getName(), attr);
			}
			else
			{
				map = new HashMap<String, Attribute>(top);
				for(AttributeBuilder bld : values)
				{
					Attribute attr = bld.createAttribute();
					attr.setCSPec(this);
					map.put(attr.getName(), attr);
				}
			}
			m_attributes = Collections.unmodifiableMap(map);
		}

		Map<String,DependencyBuilder> dependencies = cspecBld.getDependencies();
		top = (dependencies == null) ? 0 : dependencies.size();
		if(top == 0)
			m_dependencies = Collections.emptyMap();
		else
		{
			Map<String, ComponentRequest> map;
			Collection<DependencyBuilder> values = dependencies.values();
			if(top == 1)
			{
				DependencyBuilder bld = values.iterator().next();
				map = Collections.unmodifiableMap(Collections.singletonMap(bld.getName(), bld.createDependency()));
			}
			else
			{
				// We use a TreeMap to assert that the dependencies will be
				// written in the exact same order at all times
				//
				map = new TreeMap<String, ComponentRequest>();
				for(DependencyBuilder bld : values)
					map.put(bld.getName(), bld.createDependency());
			}
			m_dependencies = Collections.unmodifiableMap(map);
		}

		Map<String,GeneratorBuilder> generators = cspecBld.getGenerators();
		top = (generators == null) ? 0 : generators.size();
		if(top == 0)
			m_generators = Collections.emptyMap();
		else
		{
			Map<String, Generator> map;
			Collection<GeneratorBuilder> values = generators.values();
			if(top == 1)
			{
				GeneratorBuilder bld = values.iterator().next();
				map = Collections.singletonMap(bld.getGenerates(), bld.createGenerator(this));
			}
			else
			{
				// We use a TreeMap to assert that the generators will be
				// written in the exact same order at all times
				//
				map = new TreeMap<String, Generator>();
				for(GeneratorBuilder bld : values)
					map.put(bld.getGenerates(), bld.createGenerator(this));
			}
			m_generators = Collections.unmodifiableMap(map);
		}
	}

	public Attribute getAttribute(String name)
	{
		Attribute attr = m_attributes.get(name);
		if(attr == null && name.equals(SELF_ARTIFACT))
			attr = m_selfAttribute;
		return attr;
	}

	public Map<String, Attribute> getAttributes()
	{
		return m_attributes;
	}

	public Attribute[] getAttributes(Collection<String> attributeNames) throws MissingAttributeException
	{
		int sz = attributeNames == null
				? 0
				: attributeNames.size();
		Attribute[] attributes = new Attribute[sz];
		int idx = 0;
		for(String str : attributeNames)
			attributes[idx++] = getRequiredAttribute(str);
		return attributes;
	}

	public Attribute[] getAttributes(String... attributeNames) throws MissingAttributeException
	{
		return getAttributes(Arrays.asList(attributeNames));
	}

	public List<Attribute> getAttributesProducedByActions(boolean includePrivate) throws CoreException
	{
		ModelCache ctx = new ModelCache();
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		for(Attribute ag : m_attributes.values())
			if((includePrivate || ag.isPublic()) && ag.isProducedByActions(ctx))
				attributes.add(ag);
		return attributes;
	}

	public Attribute getBindEntryPoint()
	{
		return getAttribute(WellknownActions.BUCKMINSTER.BIND_ENTRYPOINT.toString());
	}

	public String getComponentTypeID()
	{
		return m_componentIdentifier.getComponentTypeID();
	}

	public IPath getComponentLocation() throws CoreException
	{
		return WorkspaceInfo.getComponentLocation(this);
	}

	public ComponentIdentifier getComponentIdentifier()
	{
		return m_componentIdentifier;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Map<String, ComponentRequest> getDependencies()
	{
		return m_dependencies;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public Map<String, Generator> getGenerators()
	{
		return m_generators;
	}

	public ComponentRequest getDependency(String dependencyName) throws MissingDependencyException
	{
		ComponentRequest dependency = m_dependencies.get(dependencyName);
		if(dependency == null)
			throw new MissingDependencyException(m_componentIdentifier.toString(), dependencyName);
		return dependency;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public String getName()
	{
		return m_componentIdentifier.getName();
	}

	public Attribute getPrebind()
	{
		return getAttribute(WellknownActions.BUCKMINSTER.PREBIND.toString());
	}

	public URL getProjectInfo()
	{
		return m_projectInfo;
	}

	public String getTagInfo(String parentInfo)
	{
		String path = null;
		String projectInfo = null;
		if(parentInfo != null)
		{
			int pathIdx = parentInfo.indexOf("path: ");
			if(pathIdx >= 0)
				path = String.format("%s -> %s", parentInfo.substring(pathIdx), m_componentIdentifier);

			if(m_projectInfo == null && parentInfo.startsWith("project: "))
				projectInfo = parentInfo.substring(0, pathIdx - 2);
		}

		if(m_projectInfo != null)
			projectInfo = String.format("project: %s", m_projectInfo);

		if(path == null)
			path = String.format("path: %s", m_componentIdentifier.toString());

		String tagInfo;
		if(projectInfo == null)
			tagInfo = path;
		else
			tagInfo = String.format("%s, %s", projectInfo, path);
		return tagInfo;
	}

	/**
	 * <p>
	 * Creates a list of attribute qualified external dependencies that will be unique with respect to their respective
	 * {@link ComponentRequest}.
	 * </p>
	 * <p>
	 * Only those dependencies that are implied through <code>targets</code> are included in the result if the
	 * argument <code>prune</code> is set to <code>true</code>. When the <code>prune</code> argument is
	 * <code>false</code>, all external dependencies will be included and those not implied by a matched public
	 * artifact group will have an empty attribute array.
	 * </p>
	 * 
	 * @param attributes
	 *            The attributes that implies and qualifies dependencies. Might be an empty array but never
	 *            <code>null</code>.
	 * @param prune
	 *            True if the result should be pruned to only include dependencies that has an attribute.
	 * @return A list of attribute qualified dependencies, unique per IComponentRequest.
	 * @see #getActionsByName(String[])
	 * @see #getGroupsByName(String[])
	 */
	public List<QualifiedDependency> getQualifiedDependencies(boolean attributePrune)
			throws CoreException
	{
		Map<ComponentRequest, Set<String>> deps = new HashMap<ComponentRequest, Set<String>>();
		if(!attributePrune)
		{
			// All dependencies must be included in the result. Even the one for
			// which there is no requested attribute.
			//
			for(ComponentRequest dep : getDependencies().values())
				deps.put(dep, new HashSet<String>());
		}

		for(Attribute ag : getAttributes().values())
			addDependencyBundle(deps, ag);

		if(!m_generators.isEmpty())
		{
			// Components appointed by generators are mandatory
			//
			for(Generator generator : m_generators.values())
			{
				String component = generator.getComponent();
				if(component == null)
				{
					addDependencyBundle(deps, getRequiredAttribute(generator.getAttribute()));
					continue;
				}

				ComponentRequest dep = getDependency(component);
				Set<String> attrs = deps.get(dep);
				if(attrs == null)
				{
					attrs = new HashSet<String>();
					deps.put(dep, attrs);
				}
				attrs.add(generator.getAttribute());
			}
		}

		List<QualifiedDependency> qDeps = new ArrayList<QualifiedDependency>(deps.size());
		for(Map.Entry<ComponentRequest, Set<String>> entry : deps.entrySet())
			qDeps.add(new QualifiedDependency(entry.getKey(), entry.getValue()));
		return qDeps;
	}

	public Attribute getRequiredAttribute(String name) throws MissingAttributeException
	{
		Attribute attr = getAttribute(name);
		if(attr == null)
			throw new MissingAttributeException(m_componentIdentifier.toString(), name);
		return attr;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public IVersion getVersion()
	{
		return m_componentIdentifier.getVersion();
	}

	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return sm.getCSpecs().contains(this);
	}

	public void remove(StorageManager sm) throws CoreException
	{
		UUID thisId = getId();
		if(!sm.getResolutions().getReferencingKeys(thisId, "cspecId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from Resolution");

		sm.getCSpecs().removeElement(thisId);
	}

	public void store(StorageManager sm) throws CoreException
	{
		sm.getCSpecs().putElement(this);
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, BM_CSPEC_NS, BM_CSPEC_PREFIX, getDefaultTag());
		receiver.endDocument();
	}

	/**
	 * Verify that the specification is consistent. This method checks that all dependencies and attributes that are
	 * referenced from all prerequisites are present and that no circular references exists within the cspec.
	 * 
	 * @throws MissingDependencyException
	 * @throws MissingAttributeException
	 * @throws CircularReferenceException
	 */
	public void verifyConsistency() throws MissingDependencyException, MissingAttributeException,
			CircularReferenceException
	{
		// Verify the prerequisites of all attributes except
		// the action artifacts (since their prerequisites
		// will be equal to them of of the action that owns
		// them).
		//
		for(Attribute attr : m_attributes.values())
			if(!(attr instanceof ActionArtifact))
				verifyPrerequisites(attr, null);

		// Verify validity of generators
		//
		for(Generator generator : m_generators.values())
		{
			String component = generator.getComponent();
			if(component == null)
				getRequiredAttribute(generator.getAttribute());
			else
				getDependency(component);
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		Utils.addAttribute(attrs, NamedElement.ATTR_NAME, m_componentIdentifier.getName());
		String ctypeID = m_componentIdentifier.getComponentTypeID();
		if(ctypeID != null)
			Utils.addAttribute(attrs, ComponentName.ATTR_COMPONENT_TYPE, ctypeID);

		IVersion version = m_componentIdentifier.getVersion();
		if(version != null)
		{
			Utils.addAttribute(attrs, ComponentIdentifier.ATTR_VERSION, version.toString());
			Utils.addAttribute(attrs, ComponentIdentifier.ATTR_VERSION_TYPE, version.getType().getId());
		}
		
		if(m_projectInfo != null)
			Utils.addAttribute(attrs, ATTR_PROJECT_INFO, m_projectInfo.toString());

		if(m_shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, m_shortDesc);

		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(handler, BM_CSPEC_NS, BM_CSPEC_PREFIX, m_documentation.getDefaultTag());

		Utils.emitCollection(BM_CSPEC_NS, BM_CSPEC_PREFIX, ELEM_DEPENDENCIES, ELEM_DEPENDENCY, m_dependencies.values(),
				handler);
		Utils.emitCollection(BM_CSPEC_NS, BM_CSPEC_PREFIX, ELEM_GENERATORS, Generator.TAG, m_generators.values(),
				handler);
		ArrayList<Attribute> topArtifacts = new ArrayList<Attribute>();
		ArrayList<Attribute> actions = new ArrayList<Attribute>();
		ArrayList<Attribute> groups = new ArrayList<Attribute>();
		for(Attribute attr : m_attributes.values())
		{
			if(attr instanceof Action)
				actions.add(attr);
			else if(attr instanceof Group)
				groups.add(attr);
			else if(!(attr instanceof ActionArtifact))
				topArtifacts.add(attr);
		}
		Collections.sort(topArtifacts, s_attributeSorter);
		Collections.sort(actions, s_attributeSorter);
		Collections.sort(groups, s_attributeSorter);
		Utils.emitCollection(BM_CSPEC_NS, BM_CSPEC_PREFIX, ELEM_ARTIFACTS, null, topArtifacts, handler);
		Utils.emitCollection(BM_CSPEC_NS, BM_CSPEC_PREFIX, ELEM_ACTIONS, null, actions, handler);
		Utils.emitCollection(BM_CSPEC_NS, BM_CSPEC_PREFIX, ELEM_GROUPS, null, groups, handler);
	}

	List<ActionArtifact> getActionArtifacts(Action action)
	{
		List<ActionArtifact> artifacts = null;
		String actionName = action.getName();
		for(Attribute ag : m_attributes.values())
		{
			if(!(ag instanceof ActionArtifact))
				continue;

			ActionArtifact aa = (ActionArtifact)ag;
			if(aa.getActionName().equals(actionName))
			{
				if(artifacts == null)
					artifacts = new ArrayList<ActionArtifact>();
				artifacts.add(aa);
			}
		}
		if(artifacts == null)
			artifacts = Collections.emptyList();
		return artifacts;
	}

	public boolean isPruned(Map<String,String> properties, boolean pruneForAttributes, Set<String> attrNames) throws CoreException
	{
		Dictionary<String,String> propsDict = null;
		Collection<ComponentRequest> deps = getDependencies().values();
		for(ComponentRequest dep : deps)
		{
			Filter filter = dep.getFilter();
			if(filter == null)
				continue;

			if(propsDict == null)
				propsDict = MapToDictionary.wrap(properties); 

			if(!filter.match(propsDict))
				//
				// This dependency is pruned
				//
				return true;
		}

		// No dependency pruning occurred.
		// Let's check for attribute pruning
		//
		Set<String> allAttrNames = getAttributes().keySet();

		if(pruneForAttributes)
		{
			if(attrNames.isEmpty())
				attrNames = allAttrNames;
		}
		else
			attrNames = allAttrNames;

		Set<String> referencedAttrNames = new HashSet<String>();
		for(String attrName : attrNames)
		{
			Attribute attr = getAttribute(attrName);
			if(attr != null)
				addReferencedDependencies(null, referencedAttrNames, attr, null);
		}
		return !allAttrNames.equals(referencedAttrNames);
	}

	public CSpec prune(Map<String,String> properties, boolean pruneForAttributes, Set<String> attrNames) throws CoreException
	{
		if(!isPruned(properties, pruneForAttributes, attrNames))
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

		if(pruneForAttributes)
		{
			if(attrNames.isEmpty())
				attrNames = allAttrNames;
		}
		else
			attrNames = allAttrNames;

		// Find all truly referenced dependencies. A dependency can
		// be referenced from an attribute or from a generator
		//
		Set<ComponentRequest> referencedDeps = new HashSet<ComponentRequest>();
		Set<String> referencedAttrs = new HashSet<String>();
		for(String attrName : attrNames)
		{
			Attribute attr = getAttribute(attrName);
			if(attr != null)
				addReferencedDependencies(referencedDeps, referencedAttrs, attr, null);
		}

		for(Generator generator : getGenerators().values())
		{
			String component = generator.getComponent();
			if(component == null)
				addReferencedDependencies(referencedDeps, referencedAttrs, getRequiredAttribute(generator.getAttribute()), null);
			else
				referencedDeps.add(getDependency(component));
		}

		// Prune the dependencies according to LDAP filters. Add the dependencies
		// that match to the new cspec
		//
		Dictionary<String,String> propsDict = null;
		for(ComponentRequest dep : referencedDeps)
		{
			Filter filter = dep.getFilter();
			if(filter != null)
			{
				if(propsDict == null)
					propsDict = MapToDictionary.wrap(properties); 

				if(!filter.match(propsDict))
					//
					// This dependency is pruned
					//
					continue;
			}
			bld.addDependency(dep);
		}

		// Add all attributes that can fulfill the prerequisites transitively using
		// the filtered dependency list
		//
		for(String attrName : attrNames)
		{
			Attribute attr = getAttribute(attrName);
			if(attr != null && dependenciesFulfilled(attr, bld, null))
				bld.addAttribute(attr);
		}

		// Add all generators that can be added with respect to dependency or
		// local attribute
		//
		for(Generator generator : getGenerators().values())
		{
			String component = generator.getComponent();
			if(component == null)
			{
				if(bld.getAttribute(generator.getAttribute()) == null)
					//
					// Local attribute no longer exists
					//
					continue;
			}
			else if(bld.getDependency(component) == null)
				//
				// Dependency no longer exists
				//
				continue;

			bld.addGenerator(generator);
		}
		return bld.createCSpec();
	}

	private boolean dependenciesFulfilled(Attribute attr, CSpecBuilder bld, Stack<IAttributeFilter> filters) throws CoreException
	{
		if(attr instanceof ActionArtifact)
			attr = ((ActionArtifact)attr).getAction();

		for(Prerequisite pq : attr.getPrerequisites(filters))
		{
			if(pq.isExternal())
			{
				if(bld.getDependency(pq.getComponentName()) == null)
					return false;
				continue;
			}

			if(pq.isPatternFilter())
			{
				if(filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(pq);
			}
			if(!dependenciesFulfilled(getRequiredAttribute(pq.getAttribute()), bld, filters))
				return false;
			
			if(pq.isPatternFilter())
				filters.pop();
		}
		return true;
	}

	private void addReferencedDependencies(Set<ComponentRequest> dependencies, Set<String> attrNames, Attribute attr, Stack<IAttributeFilter> filters) throws CoreException
	{
		if(attrNames.contains(attr.getName()))
			return;

		// Make sure that the pruned CSpec has all prerequisites
		//
		attrNames.add(attr.getName());

		if(attr instanceof ActionArtifact)
		{
			addReferencedDependencies(dependencies, attrNames, ((ActionArtifact)attr).getAction(), filters);
			return;
		}

		for(Prerequisite prereq : attr.getPrerequisites(filters))
		{
			if(prereq.isExternal())
			{
				if(dependencies != null)
					dependencies.add(getDependency(prereq.getComponentName()));
			}
			else
			{
				Attribute localAttr = getRequiredAttribute(prereq.getAttribute());
				if(prereq.isPatternFilter())
				{
					if(filters == null)
						filters = new Stack<IAttributeFilter>();
					filters.push(prereq);
					addReferencedDependencies(dependencies, attrNames, localAttr, filters);
					filters.pop();
				}
				else
					addReferencedDependencies(dependencies, attrNames, localAttr, filters);
			}
		}
	}

	private void addDependencyBundle(Map<ComponentRequest, Set<String>> deps, Attribute dp) throws CoreException
	{
		// Make sure that the pruned CSpec has all prerequisites
		//
		for(Prerequisite prereq : dp.getPrerequisites())
		{
			if(prereq.isExternal())
			{
				ComponentRequest rq = getDependency(prereq.getComponentName());
				Set<String> attributes = deps.get(rq);
				if(attributes == null)
				{
					attributes = new HashSet<String>();
					deps.put(rq, attributes);
				}
				attributes.add(prereq.getAttribute());
			}
			else
			{
				Attribute localGroup = getAttribute(prereq.getAttribute());
				if(localGroup instanceof ActionArtifact)
					localGroup = ((ActionArtifact)localGroup).getAction();
				addDependencyBundle(deps, localGroup);
			}
		}
	}

	private void verifyNonCircularDependency(Stack<String> seenAttributes, Collection<Prerequisite> prereqs, Stack<IAttributeFilter> filters)
			throws MissingAttributeException, CircularReferenceException
	{
		for(Prerequisite prereq : prereqs)
		{
			if(prereq.isExternal())
				//
				// Can't go beyond this cspec at this stage
				//
				continue;

			Attribute ag = getRequiredAttribute(prereq.getAttribute());
			if(ag instanceof ActionArtifact)
				ag = ((ActionArtifact)ag).getAction();

			if(seenAttributes.contains(ag.getName()))
				throw new CircularReferenceException(getComponentIdentifier().toString(), seenAttributes, ag
						.getName());

			// Verify that this action dependency doesn't somehow
			// stem from the action itself
			//
			List<Prerequisite> agPreqs;
			if(prereq.isPatternFilter())
			{
				if(filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(prereq);
				agPreqs = ag.getPrerequisites(filters);
				filters.pop();
			}
			else
				agPreqs = ag.getPrerequisites(filters);

			if(agPreqs.size() > 0)
			{
				seenAttributes.push(ag.getName());
				verifyNonCircularDependency(seenAttributes, agPreqs, filters);
				seenAttributes.pop();
			}
		}
	}

	private void verifyPrerequisites(Attribute attr, Stack<IAttributeFilter> filters) throws MissingDependencyException, MissingAttributeException,
			CircularReferenceException
	{
		Stack<String> seenActions = new Stack<String>();
		for(Prerequisite prereq : attr.getPrerequisites(filters))
		{
			if(prereq.isExternal())
			{
				// Test that the dependency is present.
				//
				getDependency(prereq.getComponentName());
				continue;
			}

			Attribute ag = getRequiredAttribute(prereq.getAttribute());
			if(ag instanceof ActionArtifact)
				//
				// Verify that we can get the action
				//
				((ActionArtifact)ag).getAction();

			if(prereq.isPatternFilter())
			{
				if(filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(prereq);
			}

			// Verify that this action dependency doesn't somehow
			// stem from the action itself
			//
			Collection<Prerequisite> agPreqs = ag.getPrerequisites(filters);
			if(agPreqs.size() > 0)
			{
				seenActions.clear();
				seenActions.push(ag.getName());
				verifyNonCircularDependency(seenActions, agPreqs, filters);
			}
			if(prereq.isPatternFilter())
				filters.pop();
		}
	}

	public Attribute getReferencedAttribute(String componentName, String attributeName, IModelCache ctx)
			throws CoreException
	{
		CSpec referencedCSpec;
		if(componentName == null)
			referencedCSpec = this;
		else
		{
			ComponentRequest dep = getDependency(componentName);
			if(!dep.isEnabled(ctx.getProperties()))
				return null;

			referencedCSpec = ctx.findCSpec(this, dep);
		}

		Attribute referencedAttr = referencedCSpec.getRequiredAttribute(attributeName);
		if(referencedAttr.isEnabled(ctx))
		{
			if(referencedCSpec == this || referencedAttr.isPublic())
				return referencedAttr;
			throw new MissingAttributeException(referencedCSpec.getComponentIdentifier().toString(), attributeName, true);
		}
		return null;
	}
}
