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

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class CSpec extends UUIDKeyed implements ISaxable, ISaxableElement
{
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

	public CSpec(String name, String componentType, IVersion version, Documentation documentation, String shortDesc,
			Map<String, DependencyBuilder> dependencies, Map<String, GeneratorBuilder> generators,
			Map<String, AttributeBuilder> attributes)
	{
		m_componentIdentifier = new ComponentIdentifier(name, componentType, version);
		m_documentation = documentation;
		m_shortDesc = shortDesc;
		m_selfAttribute = new Attribute(SELF_ARTIFACT, true, null, null)
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
		};
		m_selfAttribute.setCSPec(this);

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
			attributes[idx++] = this.getRequiredAttribute(str);
		return attributes;
	}

	public Attribute[] getAttributes(String... attributeNames) throws MissingAttributeException
	{
		return this.getAttributes(Arrays.asList(attributeNames));
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
	 * @param targets
	 *            The targets that implies and qualifies dependencies. Might be an empty array but never
	 *            <code>null</code>.
	 * @param prune
	 *            True if the result should be pruned to only include dependencies that has an attribute.
	 * @return A list of attribute qualified dependencies, unique per IComponentRequest.
	 * @see #getActionsByName(String[])
	 * @see #getGroupsByName(String[])
	 */
	public List<QualifiedDependency> getQualifiedDependencies(Attribute[] attributes, boolean prune)
			throws CoreException
	{
		Map<ComponentRequest, Set<String>> deps = new HashMap<ComponentRequest, Set<String>>();
		if(!prune)
		{
			// All dependencies must be included in the result. Even the one for
			// which there is no requested attribute.
			//
			for(ComponentRequest dep : this.getDependencies().values())
				deps.put(dep, new HashSet<String>());
		}

		if(attributes != null)
		{
			for(Attribute ag : attributes)
				this.addDependencyBundle(deps, ag);
		}

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

				ComponentRequest request = getDependency(component);
				Set<String> attrs = deps.get(request);
				if(attrs == null)
				{
					attrs = new HashSet<String>();
					deps.put(request, attrs);
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
		Attribute attr = this.getAttribute(name);
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

	public boolean isPersisted() throws CoreException
	{
		return StorageManager.getDefault().getCSpecs().contains(this);
	}

	public void remove() throws CoreException
	{
		UUID thisId = this.getId();
		StorageManager sm = StorageManager.getDefault();
		if(!sm.getResolutions().getReferencingKeys(thisId, "cspecId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from Resolution");

		sm.getCSpecs().removeElement(thisId);
	}

	public void store() throws CoreException
	{
		StorageManager.getDefault().getCSpecs().putElement(this);
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, BM_CSPEC_NS, BM_CSPEC_PREFIX, this.getDefaultTag());
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
				this.verifyPrerequisites(attr, null);

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

	public final void toSax(ContentHandler handler, String namespace, String prefix, String localName)
			throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		this.addAttributes(attrs);
		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		this.emitElements(handler, namespace, prefix);
		handler.endElement(namespace, localName, qName);
	}

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
		if(m_shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, m_shortDesc);
	}

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

	private void addDependencyBundle(Map<ComponentRequest, Set<String>> deps, Attribute dp) throws CoreException
	{
		// Make sure that the pruned CSpec has all prerequisites
		//
		for(Prerequisite prereq : dp.getPrerequisites())
		{
			if(prereq.isExternal())
			{
				ComponentRequest rq = this.getDependency(prereq.getComponentName());
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
				Attribute localGroup = this.getAttribute(prereq.getAttribute());
				if(localGroup instanceof ActionArtifact)
					localGroup = ((ActionArtifact)localGroup).getAction();
				this.addDependencyBundle(deps, localGroup);
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

			Attribute ag = this.getRequiredAttribute(prereq.getAttribute());
			if(ag instanceof ActionArtifact)
				ag = ((ActionArtifact)ag).getAction();

			if(seenAttributes.contains(ag.getName()))
				throw new CircularReferenceException(this.getComponentIdentifier().toString(), seenAttributes, ag
						.getName());

			// Verify that this action dependency doesn't somehow
			// stem from the action itself
			//
			List<Prerequisite> agPreqs;
			if(prereq.isFilter())
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
				this.verifyNonCircularDependency(seenAttributes, agPreqs, filters);
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
				this.getDependency(prereq.getComponentName());
				continue;
			}

			Attribute ag = this.getRequiredAttribute(prereq.getAttribute());
			if(ag instanceof ActionArtifact)
				//
				// Verify that we can get the action
				//
				((ActionArtifact)ag).getAction();

			if(prereq.isFilter())
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
				this.verifyNonCircularDependency(seenActions, agPreqs, filters);
			}
			if(prereq.isFilter())
				filters.pop();
		}
	}

	public Attribute getReferencedAttribute(String componentName, String attributeName, IModelCache ctx)
			throws CoreException
	{
		if(componentName == null)
			return getRequiredAttribute(attributeName);

		CSpec referencedCSpec = ctx.findCSpec(this, getDependency(componentName));
		Attribute referencedAttr = referencedCSpec.getRequiredAttribute(attributeName);
		if(!referencedAttr.isPublic())
			throw new MissingAttributeException(referencedCSpec.getComponentIdentifier().toString(), attributeName,
					true);
		return referencedAttr;
	}
}
