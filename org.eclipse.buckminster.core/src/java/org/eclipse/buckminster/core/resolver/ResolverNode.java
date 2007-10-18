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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.CircularDependencyException;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ResolverNode
{
	private static final ResolverNode[] s_noChildren = new ResolverNode[0];

	private ResolverNode[] m_children;

	private GeneratorNode m_generatorNode;

	private boolean m_invalidateRun;

	private NodeQuery m_query;

	private Resolution m_resolution;

	private String m_tagInfo;

	public ResolverNode(NodeQuery query, String tagInfo)
	{
		m_query = query;
		m_children = s_noChildren;
		m_tagInfo = tagInfo;
		if(tagInfo != null)
			query.getContext().addTagInfo(query.getComponentRequest(), tagInfo);
	}

	public synchronized void addDependencyQualification(QualifiedDependency newQDep) throws CoreException
	{
		NodeQuery query = m_query.addDependencyQualification(newQDep);
		if(query == m_query)
			//
			// Old query already declared the needed purposes.
			//
			return;

		IVersionDesignator currVd = m_query.getVersionDesignator();
		IVersionDesignator newVd = query.getVersionDesignator();

		if(!(currVd == null || currVd.equals(newVd)))
		{
			String newVdStr = (newVd == null) ? "<no designator>" : newVd.toString();
			IStatus status = new Status(IStatus.WARNING, CorePlugin.getID(), String.format("Requests for: %s has conflicting version designators %s and %s",
					query.getComponentRequest().getName(), currVd.toString(), newVdStr));

			RMContext context = query.getContext();
			context.addException(m_query.getComponentRequest(), status);
			context.addException(query.getComponentRequest(), status);
		}

		if(m_resolution != null)
		{
			// Re-resolve might be necessary
			//
			if((newVd == null || newVd.designates(m_resolution.getVersion()))
				&& m_query.getQualifiedDependency().hasAllAttributes(query.getRequiredAttributes()))
			{
				m_query = query;
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
	}

	public DepNode collectNodes(Map<UUID, DepNode> nodeMap, Stack<Resolution> circularDepTrap, boolean sameTop)
	throws CoreException
	{
		if(m_generatorNode != null)
			return m_generatorNode;

		if(m_resolution == null)
			return new UnresolvedNode(m_query.getQualifiedDependency());

		UUID myID = m_resolution.getId();
		DepNode node = nodeMap.get(myID);
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

		List<DepNode> childNodes;
		int top = m_children.length;
		ComponentQuery cquery = m_query.getComponentQuery();
		if(top > 0)
		{
			try
			{
				ArrayList<DepNode> childNodeArr = new ArrayList<DepNode>(top);
				circularDepTrap.push(m_resolution);
				for(ResolverNode child : m_children)
				{
					boolean sameChildTop = cquery.equals(child.m_query.getComponentQuery());
					DepNode childNode = child.collectNodes(nodeMap, circularDepTrap, sameChildTop);
					if(childNode == null)
					{
						// We encountered an allowed circular dependency. This
						// means we must alter the resolution of this node
						//
						String depName = child.getQuery().getComponentRequest().getName();
						CSpec cspec = m_resolution.getCSpec();
						CSpecBuilder bld = new CSpecBuilder();
						bld.initFrom(cspec);
						for(Attribute attr : cspec.getAttributes().values())
						{
							for(Prerequisite pq : attr.getPrerequisites())
							{
								if(depName.equals(pq.getComponentName()))
									bld.getAttribute(attr.getName()).removePrerequisite(pq);
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

	public boolean isResolved()
	{
		return m_resolution != null;
	}

	public synchronized void setResolution(Resolution resolution, ResolverNode[] children)
	{
		if(!m_invalidateRun)
		{
			m_resolution = resolution;
			m_children = (children == null) ? s_noChildren : children;
		}
	}

	public synchronized ResolutionContext startResolvingChildren(DepNode node) throws CoreException
	{
		Resolution resolution = node.getResolution();
		if(m_invalidateRun || resolution == null)
			return null;

		ComponentQuery cquery = node.getQuery();
		ResolutionContext originalContext = m_query.getResolutionContext();
		ResolutionContext context = originalContext;
		if(!(cquery == null || cquery.equals(context.getComponentQuery())))
			context = new ResolutionContext(cquery, context);

		Map<String,Generator> generators = resolution.getCSpec().getGenerators();
		if(generators.size() > 0)
		{
			if(context == originalContext)
				context = new ResolutionContext(originalContext.getComponentQuery(), originalContext);
			context.setGenerators(generators.values());
		}

		if(context != originalContext)
			m_query = new NodeQuery(context, m_query.getQualifiedDependency());
		return context;
	}

	synchronized void clearInvalidationFlag()
	{
		m_invalidateRun = false;
	}

	String getTagInfo()
	{
		return m_tagInfo;
	}

	NodeQuery getQuery()
	{
		return m_query;
	}

	boolean isInvalidated()
	{
		return m_invalidateRun;
	}

	void setGeneratorNode(GeneratorNode generatorNode)
	{
		m_generatorNode = generatorNode;
	}

	void setQuery(NodeQuery query)
	{
		m_query = query;
	}
}
