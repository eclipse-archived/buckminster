/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.internal.actor.ActorFactory;
import org.eclipse.buckminster.core.internal.actor.PerformManager;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Action extends Attribute
{
	public static final String ATTR_ACTOR = "actor";

	public static final String ATTR_ALWAYS = "always";

	public static final String ATTR_ENABLED = "enabled";

	public static final String ATTR_ASSIGN_CONSOLE_SUPPORT = "assignConsoleSupport";

	public static final String ELEM_ACTOR_PROPERTIES = "actorProperties";

	public static final String ELEM_PROPERTIES = "properties";

	public static final String ELEM_PRODUCTS = "products";

	public static final boolean ALWAYS_DEFAULT = false;

	public static final boolean ENABLED_DEFAULT = true;

	public static final boolean ASSIGN_CONSOLE_SUPPORT_DEFAULT = true;

	private final Set<IPath> m_products;

	private final String m_productAlias;
	
	private final IPath m_productBase;

	private final String m_actorName;

	private final boolean m_enabled;

	private final boolean m_always;

	private final Map<String,String> m_actorProperties;

	private final Map<String,String> m_properties;

	private final boolean m_assignConsoleSupport;

	private Prerequisites m_prerequisites;

	public static final String BINDING_NAME = "binding.name";

	public Action(
		String name,
		boolean publ,
		Map<String,String> installerHints,
		Documentation documentation,
		String actorName,
		Map<String,String> actorProperties,
		Map<String,String> properties,
		Prerequisites prerequisites,
		String productAlias,
		IPath productBase,
		Set<IPath> products,
		boolean always,
		boolean enabled,
		boolean assignConsoleSupport)
	{
		super(name, publ, installerHints, documentation);
		m_actorName = actorName;
		m_prerequisites = prerequisites;
		m_always = always;
		m_enabled = enabled;
		m_assignConsoleSupport = assignConsoleSupport;
		m_productAlias = productAlias;
		m_productBase = productBase;
		m_products = UUIDKeyed.createUnmodifiablePaths(products);
		m_actorProperties = UUIDKeyed.createUnmodifiableProperties(actorProperties);
		m_properties = UUIDKeyed.createUnmodifiableProperties(properties);
	}

	@Override
	public Attribute copy()
	{
		Action copy = (Action)super.copy();
		copy.m_prerequisites = (Prerequisites)copy.m_prerequisites.copy();
		return copy;
	}

	public String getActorName()
	{
		try
		{
			return isInternal()
					? ActorFactory.getInstance().findInternalActionActorName(getName())
					: m_actorName;
		}
		catch(CoreException ce)
		{
			throw new RuntimeException(ce);
		}
	}

	public void addInstallerHints(IModelCache ctx, Map<String,String> installerHints) throws CoreException
	{
		CSpec cspec = getCSpec();
		for(Prerequisite prereq : getPrerequisites())
		{
			Attribute ag = prereq.getReferencedAttribute(cspec, ctx);
			ag.getDeepInstallerHints(ctx, installerHints);
		}
	}

	public Group getPrerequisiteGroup()
	{
		return m_prerequisites;
	}

	public IPath getPrerequisiteRebase()
	{
		return m_prerequisites.getPrerequisiteRebase();
	}

	@Override
	public List<Prerequisite> getPrerequisites()
	{
		return m_prerequisites.getPrerequisites();
	}

	public String getPrerequisitesAlias()
	{
		return m_prerequisites.getName();
	}

	public String getProductAlias()
	{
		return m_productAlias;
	}

	public List<ActionArtifact> getProductArtifacts()
	{
		return getCSpec().getActionArtifacts(this);
	}

	public IPath getProductBase()
	{
		return m_productBase;
	}

	public Set<IPath> getProductPaths()
	{
		return m_products;
	}

	public Map<String,String> getActorProperties()
	{
		return m_actorProperties;
	}

	public String getBindingName(Map<String,String> globalProps)
	{
		Map<String,String> actionProps = getProperties();
		if(actionProps.containsKey(BINDING_NAME))
		{
			ExpandingProperties allProps = new ExpandingProperties(globalProps);
			allProps.putAll(actionProps);
			return allProps.get(BINDING_NAME);
		}
		return null;
	}

	public Map<String,String>  getProperties()
	{
		return m_properties;
	}

	@Override
	public boolean isEnabled(IModelCache ctx)
	{
		return m_enabled && super.isEnabled(ctx);
	}

	public boolean assignConsoleSupport()
	{
		return m_assignConsoleSupport;
	}

	public final boolean isAlways()
	{
		return m_always;
	}

	public final boolean isInternal()
	{
		return m_actorName == null;
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx)
	{
		return true;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_actorName != null)
			Utils.addAttribute(attrs, ATTR_ACTOR, m_actorName);
		if(m_enabled != ENABLED_DEFAULT)
			Utils.addAttribute(attrs, ATTR_ENABLED, Boolean.toString(m_enabled));
		if(m_always != ALWAYS_DEFAULT)
			Utils.addAttribute(attrs, ATTR_ALWAYS, Boolean.toString(m_enabled));
		if(m_assignConsoleSupport != ASSIGN_CONSOLE_SUPPORT_DEFAULT)
			Utils.addAttribute(attrs, ATTR_ASSIGN_CONSOLE_SUPPORT, Boolean.toString(m_assignConsoleSupport));
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		super.emitElements(handler, namespace, prefix);

		if(!m_actorProperties.isEmpty())
		{
			String qName = Utils.makeQualifiedName(prefix, ELEM_ACTOR_PROPERTIES);
			handler.startElement(namespace, ELEM_ACTOR_PROPERTIES, qName, ISaxableElement.EMPTY_ATTRIBUTES);
			SAXEmitter.emitProperties(handler, m_actorProperties, namespace, prefix, true, false);
			handler.endElement(namespace, ELEM_ACTOR_PROPERTIES, qName);
		}

		if(!m_properties.isEmpty())
		{
			String qName = Utils.makeQualifiedName(prefix, ELEM_PROPERTIES);
			handler.startElement(namespace, ELEM_PROPERTIES, qName, ISaxableElement.EMPTY_ATTRIBUTES);
			SAXEmitter.emitProperties(handler, m_properties, namespace, prefix, true, false);
			handler.endElement(namespace, ELEM_PROPERTIES, qName);
		}

		if(m_prerequisites.getPrerequisites().size() > 0)
			m_prerequisites.toSax(handler, namespace, prefix, m_prerequisites.getDefaultTag());

		AttributesImpl attrs = new AttributesImpl();
		if(m_productAlias != null)
			Utils.addAttribute(attrs, Prerequisite.ATTR_ALIAS, m_productAlias);
		if(m_productBase != null)
			Utils.addAttribute(attrs, Artifact.ATTR_BASE, m_productBase.toPortableString());
		ArrayList<ISaxableElement> allProds = new ArrayList<ISaxableElement>();
		for(IPath path : m_products)
			allProds.add((SaxablePath)path);
		allProds.addAll(getCSpec().getActionArtifacts(this));
		Utils.emitCollection(namespace, prefix, ELEM_PRODUCTS, null, attrs, allProds, handler);
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local)
			throws CoreException
	{
		CSpec cspec = getCSpec();
		ArrayList<PathGroup> pathGroups = new ArrayList<PathGroup>();

		int numProducts = m_products.size();
		if(m_productBase != null || numProducts > 0)
		{
			// Add the anonymous group
			//
			IPath root;
			IPath cspecLoc = getCSpec().getComponentLocation();
			if(m_productBase != null)
			{
				root = PerformManager.expandPath(local, m_productBase);
				if(!root.isAbsolute())
					root = cspecLoc.append(root);
			}
			else
				root = cspecLoc;

			IPath[] pathArr = m_products.toArray(new IPath[numProducts]);
			while(--numProducts >= 0)
				pathArr[numProducts] = PerformManager.expandPath(local, pathArr[numProducts]);
			pathGroups.add(new PathGroup(root, pathArr));
		}

		// Add produced artifacts
		//
		for(Artifact a : cspec.getActionArtifacts(this))
			for(PathGroup pathGroup : a.getPathGroups(ctx))
				pathGroups.add(pathGroup);

		return pathGroups.toArray(new PathGroup[pathGroups.size()]);
	}

	public boolean isUpToDate(IModelCache ctx) throws CoreException
	{
		if(m_products.size() == 0)
			//
			// No product means it's never up to date.
			//
			return false;

		long first = getFirstModified(ctx);
		if(first == 0)
			//
			// We are definitely not up-to-date.
			//
			return false;

		CSpec cspec = getCSpec();
		for(Prerequisite pq : getPrerequisites())
		{
			Attribute ag = pq.getReferencedAttribute(cspec, ctx);
			long pqTime = ag.getLastModified(ctx, first);
			if(pqTime > first)
			{
				if(pqTime != Long.MAX_VALUE)
					return false;

				// That one was empty, i.e. no artifacts expected
				//
				continue;
			}
		}
		return true;
	}
	
	@Override
	void setCSPec(CSpec cspec)
	{
		super.setCSPec(cspec);
		m_prerequisites.setCSPec(cspec);
	}
}
