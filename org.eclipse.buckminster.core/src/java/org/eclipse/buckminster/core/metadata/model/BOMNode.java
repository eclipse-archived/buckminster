/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class BOMNode extends UUIDKeyed implements IUUIDPersisted
{
	public static final int SEQUENCE_NUMBER = 3;

	public static final String TAG = "depnode";

	abstract void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery query,
			MaterializationSpec mspec, Set<Resolution> perused) throws CoreException;

	public void addUnresolved(List<ComponentRequest> unresolved, Set<Resolution> skipThese)
	{
	}

	void collectAll(Set<Resolution> notThese, List<Resolution> all) throws CoreException
	{
	}

	private void collectNodes(Set<BOMNode> nodes) throws CoreException
	{
		if(nodes.add(this))
			for(BOMNode child : getChildren())
				child.collectNodes(nodes);
	}

	public List<Resolution> findAll(Set<Resolution> skipThese) throws CoreException
	{
		return Collections.emptyList();
	}

	public List<BOMNode> getChildren()
	{
		return Collections.emptyList();
	}

	public abstract QualifiedDependency getQualifiedDependency();

	public ComponentQuery getQuery() throws CoreException
	{
		return null;
	}

	public UUID getQueryId()
	{
		return null;
	}

	public abstract ComponentRequest getRequest();

	public Resolution getResolution()
	{
		return null;
	}

	public UUID getResolutionId() throws CoreException
	{
		return null;
	}

	public abstract String getViewName() throws CoreException;

	public boolean isChild(BOMNode node) throws CoreException
	{
		return false;
	}

	public boolean isFullyResolved(ComponentQuery query) throws CoreException
	{
		return query.skipComponent(getRequest());
	}

	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return false;
	}

	/**
	 * Returns <code>true</code> if this DepNode references the given <code>nodeId</code>, directly or indirectly
	 * through the children.
	 * 
	 * @param node
	 *            The node to check for
	 * @param shallow
	 *            When true, compare only with the id of the node itself and the id of all children. Do not recurse down
	 *            into children.
	 * @return true if the node identified by <code>resolverNodeId</code> is equal to this node or if it is found in the
	 *         graph extending from this node
	 * @throws CoreException
	 */
	public boolean isReferencing(BOMNode node, boolean shallow) throws CoreException
	{
		return equals(node);
	}

	public void remove(StorageManager sm) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	BOMNode replaceNode(BOMNode topReplacer, BOMNode node, Map<BOMNode, BOMNode> visited) throws CoreException
	{
		BOMNode self = visited.get(this);
		if(self == null)
		{
			QualifiedDependency qDep = getQualifiedDependency();
			if(topReplacer.getQualifiedDependency().equals(qDep))
				self = topReplacer;
			else if(topReplacer != node && node.getQualifiedDependency().equals(qDep))
				self = node;
			else
				self = this;
			visited.put(this, self);
		}
		return self;
	}

	public void store(StorageManager sm) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public final void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		this.toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, this.getDefaultTag());
		receiver.endDocument();
	}

	public int uniqueNodeCount() throws CoreException
	{
		HashSet<BOMNode> allNodes = new HashSet<BOMNode>();
		collectNodes(allNodes);
		return allNodes.size();
	}
}
