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
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class ResolverNode {
	private static final ResolverNode[] noChildren = new ResolverNode[0];

	private ResolverNode[] children;

	private GeneratorNode generatorNode;

	private boolean invalidateRun;

	private NodeQuery query;

	private Resolution resolution;

	private boolean forceUnresolved;

	private final String tagInfo;

	public ResolverNode(NodeQuery query, String tagInfo) {
		this.query = query;
		this.children = noChildren;
		this.tagInfo = tagInfo;
		if (tagInfo != null)
			query.getContext().addTagInfo(query.getComponentRequest(), tagInfo);
	}

	public synchronized void addDependencyQualification(QualifiedDependency newQDep, String tagInf) throws CoreException {
		NodeQuery qualifiedQuery = query.addDependencyQualification(newQDep);
		if (qualifiedQuery == query)
			//
			// Old query already declared the needed purposes.
			//
			return;

		VersionRange newVd = qualifiedQuery.getVersionRange();
		if (resolution != null) {
			// Re-resolve might be necessary
			//
			if ((newVd == null || newVd.isIncluded(resolution.getVersion()))
					&& query.getQualifiedDependency().hasAllAttributes(qualifiedQuery.getRequiredAttributes())) {
				// Nope, the resolution is still valid for this new query
				//
				query = qualifiedQuery;
				if (tagInfo != null)
					qualifiedQuery.getContext().addTagInfo(qualifiedQuery.getComponentRequest(), tagInf);
				return;
			}
		}

		// New version constraints or new attributes were introduced that
		// invalidated the
		// current resolution. We need to invalidate what we have and make sure
		// its done
		// again.
		//
		resolution = null;
		children = noChildren;
		query = qualifiedQuery;
		invalidateRun = true;
		if (tagInfo != null)
			qualifiedQuery.getContext().addTagInfo(qualifiedQuery.getComponentRequest(), tagInf);
	}

	public BOMNode collectNodes(Map<UUID, BOMNode> nodeMap, Stack<Resolution> circularDepTrap, boolean sameTop) throws CoreException {
		if (query.skipComponent())
			return null;

		if (generatorNode != null)
			return generatorNode;

		if (resolution == null)
			return new UnresolvedNode(query.getQualifiedDependency());

		UUID myID = resolution.getId();
		BOMNode node = nodeMap.get(myID);
		if (node != null)
			return node;

		if (circularDepTrap.contains(resolution)) {
			if (query.allowCircularDependency())
				return null;

			ArrayList<String> attrs = new ArrayList<String>(circularDepTrap.size());
			for (Resolution res : circularDepTrap)
				attrs.add(res.getCSpec().getName());
			attrs.add(resolution.getName());
			throw new CircularDependencyException(attrs);
		}

		List<BOMNode> childNodes;
		int top = children.length;
		ComponentQuery cquery = query.getComponentQuery();
		if (top > 0) {
			try {
				ArrayList<BOMNode> childNodeArr = new ArrayList<BOMNode>(top);
				circularDepTrap.push(resolution);
				for (ResolverNode child : children) {
					Resolution childRes = child.resolution;
					if (childRes != null && IComponentType.OSGI_BUNDLE.equals(childRes.getComponentTypeId())) {
						String childName = childRes.getName();
						if (childName.endsWith(".source") && childName.length() == resolution.getName().length() + 7 && childName.startsWith(resolution.getName())) //$NON-NLS-1$
							// We don't traverse source bundles since that
							// dependency is synthesized
							continue;
					}

					boolean sameChildTop = cquery.equals(child.query.getComponentQuery());
					BOMNode childNode = child.collectNodes(nodeMap, circularDepTrap, sameChildTop);
					if (childNode == null) {
						// We encountered a skipped component or an allowed
						// circular dependency. This
						// means we must alter the resolution of this node
						//
						String depName = child.getQuery().getComponentRequest().getName();
						CSpec cspec = resolution.getCSpec();
						CSpecBuilder bld = new CSpecBuilder();
						bld.initFrom(cspec);
						for (IAttribute attr : cspec.getAttributes().values()) {
							for (IPrerequisite pq : attr.getPrerequisites()) {
								if (depName.equals(pq.getComponentName()))
									((TopLevelAttributeBuilder) bld.getAttribute(attr.getName())).removePrerequisite(pq);
							}
						}
						bld.removeDependency(depName);
						cspec = bld.createCSpec();
						resolution = new Resolution(cspec, resolution);
					} else
						childNodeArr.add(childNode);
				}
				circularDepTrap.pop();
				childNodes = childNodeArr;
			} catch (CircularDependencyException e) {
				if (query.allowCircularDependency())
					return null;
				throw e;
			}
		} else
			childNodes = Collections.emptyList();

		node = new ResolvedNode(resolution, childNodes);
		if (!sameTop)
			node = BillOfMaterials.create(node, cquery);

		nodeMap.put(myID, node);
		return node;
	}

	public synchronized void forceUnresolved() {
		resolution = null;
		children = noChildren;
		invalidateRun = true;
		forceUnresolved = true;
	}

	public boolean isForceUnresolved() {
		return forceUnresolved;
	}

	public boolean isResolved() {
		return resolution != null;
	}

	public synchronized void setResolution(Resolution resolution, ResolverNode[] children) {
		if (!invalidateRun) {
			this.resolution = resolution;
			this.children = (children == null) ? noChildren : children;
		}
	}

	public synchronized ResolutionContext startResolvingChildren(BOMNode node) throws CoreException {
		Resolution nodeRes = node.getResolution();
		if (invalidateRun || nodeRes == null)
			return null;

		ComponentQuery cquery = node.getQuery();
		ResolutionContext originalContext = query.getResolutionContext();
		ResolutionContext context = originalContext;
		if (!(cquery == null || cquery.equals(context.getComponentQuery())))
			context = new ResolutionContext(cquery, context);

		CSpec cspec = nodeRes.getCSpec();
		Collection<Generator> generators = cspec.getGeneratorList();
		if (generators.size() > 0) {
			if (context == originalContext)
				context = new ResolutionContext(originalContext.getComponentQuery(), originalContext);
			context.setGenerators(cspec, generators);
		}

		if (context != originalContext)
			query = context.getNodeQuery(query.getQualifiedDependency());
		return context;
	}

	synchronized void clearInvalidationFlag() {
		if (!forceUnresolved)
			invalidateRun = false;
	}

	NodeQuery getQuery() {
		return query;
	}

	String getTagInfo() {
		return tagInfo;
	}

	boolean isInvalidated() {
		return invalidateRun;
	}

	void setGeneratorNode(GeneratorNode generatorNode) {
		this.generatorNode = generatorNode;
	}
}
