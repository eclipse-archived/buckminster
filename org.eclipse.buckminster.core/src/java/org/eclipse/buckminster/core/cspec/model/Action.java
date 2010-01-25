/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.internal.actor.ActorFactory;
import org.eclipse.buckminster.core.internal.actor.PerformManager;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.runtime.Logger;
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
public class Action extends TopLevelAttribute implements IAction
{
	public static final String ATTR_ACTOR = "actor"; //$NON-NLS-1$

	public static final String ATTR_ALWAYS = "always"; //$NON-NLS-1$

	public static final String ATTR_ASSIGN_CONSOLE_SUPPORT = "assignConsoleSupport"; //$NON-NLS-1$

	public static final String ATTR_PRODUCT_FILE_COUNT = "fileCount"; //$NON-NLS-1$

	public static final String ATTR_UP_TO_DATE_POLICY = "upToDatePolicy"; //$NON-NLS-1$

	public static final String ELEM_ACTOR_PROPERTIES = "actorProperties"; //$NON-NLS-1$

	public static final String ELEM_PROPERTIES = "properties"; //$NON-NLS-1$

	public static final String ELEM_PRODUCTS = "products"; //$NON-NLS-1$

	public static final boolean ALWAYS_DEFAULT = false;

	public static final boolean ASSIGN_CONSOLE_SUPPORT_DEFAULT = true;

	private final Set<IPath> m_products;

	private final String m_productAlias;

	private final IPath m_productBase;

	private final String m_actorName;

	private final boolean m_always;

	private final int m_productFileCount;

	private final Map<String, String> m_actorProperties;

	private final Map<String, String> m_properties;

	private final boolean m_assignConsoleSupport;

	private final UpToDatePolicy m_upToDatePolicy;

	private Prerequisites m_prerequisites;

	public static final String BINDING_NAME = "binding.name"; //$NON-NLS-1$

	public Action(ActionBuilder builder)
	{
		super(builder);
		m_actorName = builder.getActorName();
		m_prerequisites = new Prerequisites(this, builder.getPrerequisitesBuilder());
		m_always = builder.isAlways();
		m_assignConsoleSupport = builder.isAssignConsoleSupport();
		m_productAlias = builder.getProductAlias();
		m_productBase = builder.getProductBase();
		m_productFileCount = builder.getProductFileCount();
		m_products = CSpec.createUnmodifiablePaths(builder.getProductPaths());
		m_actorProperties = ExpandingProperties.createUnmodifiableProperties(builder.getActorProperties());
		m_properties = ExpandingProperties.createUnmodifiableProperties(builder.getProperties());
		m_upToDatePolicy = builder.getUpToDatePolicy();
	}

	@Override
	public IAttribute copy()
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

	public Map<String, String> getActorProperties()
	{
		return m_actorProperties;
	}

	public String getBindingName(Map<String, ? extends Object> globalProps)
	{
		Map<String, String> actionProps = getProperties();
		if(actionProps.containsKey(BINDING_NAME))
		{
			ExpandingProperties<Object> allProps = new ExpandingProperties<Object>(globalProps);
			allProps.putAll(actionProps);
			return (String)allProps.get(BINDING_NAME);
		}
		return null;
	}

	public IPath getExpandedBase(IPath productBase, Map<String, ? extends Object> local)
	{
		if(productBase == null)
			return getExpandedDefaultBase(local);

		productBase = PerformManager.expandPath(local, productBase);
		if(!productBase.isAbsolute())
			productBase = getExpandedDefaultBase(local).append(productBase);
		return productBase;
	}

	public IPath getExpandedDefaultBase(Map<String, ? extends Object> local)
	{
		return PerformManager.expandPath(local, Path.fromPortableString(KeyConstants.ACTION_OUTPUT_REF));
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
	public List<Prerequisite> getPrerequisites(Stack<IAttributeFilter> filters)
	{
		return m_prerequisites.getPrerequisites(filters);
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

	public int getProductFileCount()
	{
		return m_productFileCount;
	}

	public Set<IPath> getProductPaths()
	{
		return m_products;
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public UpToDatePolicy getUpToDatePolicy()
	{
		return m_upToDatePolicy;
	}

	public final boolean isAlways()
	{
		return m_always;
	}

	public boolean isAssignConsoleSupport()
	{
		return m_assignConsoleSupport;
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

	public boolean isUpToDate(IModelCache ctx) throws CoreException
	{
		Logger logger = CorePlugin.getLogger();
		String failLeadIn = ""; //$NON-NLS-1$
		boolean isDebug = logger.isDebugEnabled();
		if(isDebug)
			failLeadIn = String.format("Action %s using 'up to date' policy %s: Rebuild needed: ", this, //$NON-NLS-1$
					m_upToDatePolicy);

		if(m_upToDatePolicy == UpToDatePolicy.ACTOR)
		{
			if(ActorFactory.getInstance().getActor(this).isUpToDate(this, ctx))
				return true;

			if(isDebug)
				logger.debug("%sActor decision", failLeadIn); //$NON-NLS-1$
			return false;
		}

		if(m_upToDatePolicy == UpToDatePolicy.MAPPER)
		{
			Map<String, Long> prereqFiles = getPrerequisiteRelativeFiles(ctx);
			Map<String, Long> productFiles = getProductRelativeFiles(ctx);

			int expectedFileCount = prereqFiles.size();
			if(m_productFileCount > 0)
				expectedFileCount += m_productFileCount;

			if(productFiles.size() < expectedFileCount)
			{
				// Not enough files
				//
				if(isDebug)
					logger.debug("%sFile count(%d) < expected(%d)", failLeadIn, Integer.valueOf(productFiles.size()), //$NON-NLS-1$
							Integer.valueOf(expectedFileCount));
				return false;
			}

			// Don't consider products that we don't need since their timestamp
			// might effect the outcome negatively
			//
			for(Map.Entry<String, Long> entry : prereqFiles.entrySet())
			{
				Long tsObj = productFiles.get(entry.getKey());
				if(tsObj == null)
				{
					// Oops, missing product
					//
					if(isDebug)
						logger.debug(String.format("%sNo product is matching requirement %s", failLeadIn, entry //$NON-NLS-1$
						.getKey()));
					return false;
				}

				long productTs = tsObj.longValue();
				long prereqTs = entry.getValue().longValue();
				if(prereqTs > productTs)
				{
					// Prerequisite is newer
					//
					if(isDebug)
						logger.debug(String.format(
								"%sThe product for %s of age %s is older then its matching requirement with age %s", //$NON-NLS-1$
								failLeadIn, entry.getKey(), new Date(productTs), new Date(prereqTs)));
					return false;
				}
			}
			if(isDebug)
				logger.debug(String.format("Action %s using 'up to date' policy %s: Product is up to date", this, //$NON-NLS-1$
						m_upToDatePolicy));
			return true;
		}

		int expectedFileCount;
		switch(m_upToDatePolicy)
		{
		case COUNT:
			expectedFileCount = m_productFileCount;
			break;

		case NOT_EMPTY:
			expectedFileCount = 0;
			break;

		default:
			expectedFileCount = -1;
		}

		int[] fileCountBin = new int[] { 0 };
		long oldest = getFirstModified(ctx, expectedFileCount, fileCountBin);
		int fileCount = fileCountBin[0];
		if(oldest == 0L || (expectedFileCount > 0 && expectedFileCount > fileCount))
		{
			if(isDebug)
			{
				switch(m_upToDatePolicy)
				{
				case DEFAULT:
					logger.debug(String.format("%sProduct has folders", failLeadIn)); //$NON-NLS-1$
					break;
				case NOT_EMPTY:
					logger.debug(String.format("%sProduct is empty", failLeadIn)); //$NON-NLS-1$
					break;
				default:
					logger.debug(String.format("%sFile count(%d) < expected(%d)", failLeadIn, Integer //$NON-NLS-1$
					.valueOf(fileCountBin[0]), Integer.valueOf(expectedFileCount)));
					break;
				}
			}
			return false;
		}

		fileCountBin[0] = 0;
		long prereqAge = getPrerequisiteGroup().getLastModified(ctx, oldest, fileCountBin);
		if(oldest >= prereqAge)
		{
			if(isDebug)
				logger.debug(String.format("Action %s using 'up to date' policy %s: Product is up to date", this, //$NON-NLS-1$
						m_upToDatePolicy));
			return true;
		}
		if(isDebug)
			logger.debug(String.format("%s: Product of age %s is older then prerequisite of age %s", failLeadIn, //$NON-NLS-1$
					new Date(oldest), new Date(prereqAge)));
		return false;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_actorName != null)
			Utils.addAttribute(attrs, ATTR_ACTOR, m_actorName);
		if(m_always != ALWAYS_DEFAULT)
			Utils.addAttribute(attrs, ATTR_ALWAYS, Boolean.toString(m_always));
		if(m_assignConsoleSupport != ASSIGN_CONSOLE_SUPPORT_DEFAULT)
			Utils.addAttribute(attrs, ATTR_ASSIGN_CONSOLE_SUPPORT, Boolean.toString(m_assignConsoleSupport));
	}

	@Override
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		return cspecBuilder.createActionBuilder();
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
		if(m_productFileCount >= 0)
			Utils.addAttribute(attrs, ATTR_PRODUCT_FILE_COUNT, Integer.toString(m_productFileCount));
		if(m_upToDatePolicy != UpToDatePolicy.DEFAULT)
			Utils.addAttribute(attrs, ATTR_UP_TO_DATE_POLICY, m_upToDatePolicy.name());
		ArrayList<ISaxableElement> allProds = new ArrayList<ISaxableElement>();
		for(IPath path : m_products)
			allProds.add((SaxablePath)path);
		allProds.addAll(getCSpec().getActionArtifacts(this));
		Utils.emitCollection(namespace, prefix, ELEM_PRODUCTS, null, attrs, allProds, handler);
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, ? extends Object> local,
			Stack<IAttributeFilter> filters) throws CoreException
	{
		CSpec cspec = getCSpec();
		ArrayList<PathGroup> pathGroups = new ArrayList<PathGroup>();

		int numProducts = m_products.size();
		if(m_productBase != null || numProducts > 0)
		{
			// Add the anonymous group
			//
			IPath productBase = getExpandedBase(m_productBase, local);
			IPath[] pathArr = m_products.toArray(new IPath[numProducts]);
			while(--numProducts >= 0)
				pathArr[numProducts] = PerformManager.expandPath(local, pathArr[numProducts]);
			pathGroups.add(new PathGroup(productBase, pathArr));
		}

		// Add produced artifacts
		//
		for(Artifact a : cspec.getActionArtifacts(this))
			for(PathGroup pathGroup : a.getPathGroups(ctx, filters))
				pathGroups.add(pathGroup);

		return pathGroups.toArray(new PathGroup[pathGroups.size()]);
	}

	@Override
	void setCSPec(CSpec cspec)
	{
		super.setCSPec(cspec);
		m_prerequisites.setCSPec(cspec);
	}

	private Map<String, Long> getPrerequisiteRelativeFiles(IModelCache ctx) throws CoreException
	{
		HashMap<String, Long> filesAndDates = new HashMap<String, Long>();
		CSpec cspec = getCSpec();
		for(Prerequisite pq : getPrerequisites(null))
		{
			if(!pq.isContributor())
				continue;

			IAttribute ag = pq.getReferencedAttribute(cspec, ctx);
			if(ag instanceof TopLevelAttribute)
				((TopLevelAttribute)ag).appendRelativeFiles(ctx, filesAndDates);
		}
		return filesAndDates;
	}

	private Map<String, Long> getProductRelativeFiles(IModelCache ctx) throws CoreException
	{
		HashMap<String, Long> filesAndDates = new HashMap<String, Long>();
		appendRelativeFiles(ctx, filesAndDates);
		return filesAndDates;
	}
}
