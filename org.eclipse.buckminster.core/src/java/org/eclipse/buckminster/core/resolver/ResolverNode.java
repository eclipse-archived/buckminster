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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.CircularDependencyException;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class ResolverNode
{
	private static final ResolverNode[] s_noChildren = new ResolverNode[0];

	private ResolverNode[] m_children;

	private GeneratorNode m_generatorNode;

	private boolean m_invalidateRun;

	private NodeQuery m_query;

	private Resolution m_resolution;

	private boolean m_forceUnresolved;

	private final String m_tagInfo;

	public ResolverNode(NodeQuery query, String tagInfo)
	{
		m_query = query;
		m_children = s_noChildren;
		m_tagInfo = tagInfo;
		if(tagInfo != null)
			query.getContext().addTagInfo(query.getComponentRequest(), tagInfo);
	}

	public synchronized void addDependencyQualification(QualifiedDependency newQDep, String tagInfo)
			throws CoreException
	{
		NodeQuery query = m_query.addDependencyQualification(newQDep);
		if(query == m_query)
			//
			// Old query already declared the needed purposes.
			//
			return;

		VersionRange newVd = query.getVersionRange();
		if(m_resolution != null)
		{
			// Re-resolve might be necessary
			//
			if((newVd == null || newVd.isIncluded(m_resolution.getVersion()))
					&& m_query.getQualifiedDependency().hasAllAttributes(query.getRequiredAttributes()))
			{
				// Nope, the resolution is still valid for this new query
				//
				m_query = query;
				if(tagInfo != null)
					query.getContext().addTagInfo(query.getComponentRequest(), tagInfo);
				return;
			}
		}

		// New version constraints or new attributes were introduced that invalidated the
		// current resolution. We need to invalidate what we have and make sure its done
		// again.
		//
		m_resolution = null;
		m_children = s_noChildren;
		m_query = query;
		m_invalidateRun = true;
		if(tagInfo != null)
			query.getContext().addTagInfo(query.getComponentRequest(), tagInfo);
	}

	public BOMNode collectNodes(Map<UUID, BOMNode> nodeMap, Stack<Resolution> circularDepTrap, boolean sameTop)
			throws CoreException
	{
		if(m_query.skipComponent())
			return null;

		if(m_generatorNode != null)
			return m_generatorNode;

		if(m_resolution == null)
			return new UnresolvedNode(m_query.getQualifiedDependency());

		UUID myID = m_resolution.getId();
		BOMNode node = nodeMap.get(myID);
		if(node != null)
			return node;

		if(circularDepTrap.contains(m_resolution))
		{
			if(m_query.allowCircularDependency())
				return null;

			ArrayList<String> attrs = new ArrayList<String>(circularDepTrap.size());
			for(Resolution res : circularDepTrap)
				attrs.add(res.getCSpec().getName());
			attrs.add(m_resolution.getName());
			throw new CircularDependencyException(attrs);
		}

		List<BOMNode> childNodes;
		int top = m_children.length;
		ComponentQuery cquery = m_query.getComponentQuery();
		if(top > 0)
		{
			try
			{
				ArrayList<BOMNode> childNodeArr = new ArrayList<BOMNode>(top);
				circularDepTrap.push(m_resolution);
				for(ResolverNode child : m_children)
				{
					boolean sameChildTop = cquery.equals(child.m_query.getComponentQuery());
					BOMNode childNode = child.collectNodes(nodeMap, circularDepTrap, sameChildTop);
					if(childNode == null)
					{
						// We encountered a skipped component or an allowed circular dependency. This
						// means we must alter the resolution of this node
						//
						String depName = child.getQuery().getComponentRequest().getName();
						CSpec cspec = m_resolution.getCSpec();
						CSpecBuilder bld = new CSpecBuilder();
						bld.initFrom(cspec);
						for(IAttribute attr : cspec.getAttributes().values())
						{
							for(IPrerequisite pq : attr.getPrerequisites())
							{
								if(depName.equals(pq.getComponentName()))
									((TopLevelAttributeBuilder)bld.getAttribute(attr.getName())).removePrerequisite(pq);
							}
						}
						bld.removeDependency(depName);
						cspec = bld.createCSpec();
						m_resolution = new Resolution(cspec, m_resolution);
					}
					else
						childNodeArr.add(childNode);
				}
				circularDepTrap.pop();
				childNodes = childNodeArr;
			}
			catch(CircularDependencyException e)
			{
				if(m_query.allowCircularDependency())
					return null;
				throw e;
			}
		}
		else
			childNodes = Collections.emptyList();

		node = new ResolvedNode(m_resolution, childNodes);
		if(!sameTop)
			node = BillOfMaterials.create(node, cquery);

		nodeMap.put(myID, node);
		return node;
	}

	public synchronized void forceUnresolved()
	{
		m_resolution = null;
		m_children = s_noChildren;
		m_invalidateRun = true;
		m_forceUnresolved = true;
	}

	public boolean isForceUnresolved()
	{
		return m_forceUnresolved;
	}

	public boolean isResolved()
	{
		return m_resolution != null;
	}

	public synchronized void setResolution(Resolution resolution, ResolverNode[] children)
	{
		if(!m_invalidateRun)
		{
			m_resolution = resolution;
			m_children = (children == null)
					? s_noChildren
					: children;
		}
	}

	public synchronized ResolutionContext startResolvingChildren(BOMNode node) throws CoreException
	{
		Resolution resolution = node.getResolution();
		if(m_invalidateRun || resolution == null)
			return null;

		ComponentQuery cquery = node.getQuery();
		ResolutionContext originalContext = m_query.getResolutionContext();
		ResolutionContext context = originalContext;
		if(!(cquery == null || cquery.equals(context.getComponentQuery())))
			context = new ResolutionContext(cquery, context);

		CSpec cspec = resolution.getCSpec();
		Collection<Generator> generators = cspec.getGeneratorList();
		if(generators.size() > 0)
		{
			if(context == originalContext)
				context = new ResolutionContext(originalContext.getComponentQuery(), originalContext);
			context.setGenerators(cspec, generators);
		}

		if(context != originalContext)
			m_query = context.getNodeQuery(m_query.getQualifiedDependency());
		return context;
	}

	synchronized void clearInvalidationFlag()
	{
		if(!m_forceUnresolved)
			m_invalidateRun = false;
	}

	NodeQuery getQuery()
	{
		return m_query;
	}

	String getTagInfo()
	{
		return m_tagInfo;
	}

	boolean isInvalidated()
	{
		return m_invalidateRun;
	}

	void setGeneratorNode(GeneratorNode generatorNode)
	{
		m_generatorNode = generatorNode;
	}
}
