/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ObtainedDependency;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.parser.ElementRefHandler;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class ResolvedNode extends DepNode
{
	public static final String ATTR_RESOLUTION_ID = "resolutionId";

	public static final String CHILD_TAG = "child";

	@SuppressWarnings("hiding")
	public static final String TAG = "resolvedNode";

	private final List<UUID> m_childIds;

	private transient List<DepNode> m_children;

	private transient Resolution m_resolution;

	private final UUID m_resolutionId;

	/**
	 * This constructor will create a resolved node that has all of its children unresolved. The set
	 * of children is and how the children are qualified are determined using the attributes and
	 * prune flag defined in the <code>cquery</code>.
	 * @param cquery The component query whos advice will be used
	 * @param resolution The resolution for the created resolved node
	 * @throws CoreException
	 */
	public ResolvedNode(NodeQuery query, Resolution resolution) throws CoreException
	{
		m_resolution = resolution;
		m_resolutionId = resolution.getId();

		CSpec cspec = resolution.getCSpec();
		Attribute[] attributes = query.getAttributes(cspec);
		List<QualifiedDependency> qDeps = cspec.getQualifiedDependencies(attributes, query.isPrune());
		int nDeps = qDeps.size();
		if(nDeps == 0)
		{
			m_children = Collections.emptyList();
			m_childIds = Collections.emptyList();
		}
		else
		{
			ComponentQuery cquery = query.getComponentQuery();
			List<DepNode> children = new ArrayList<DepNode>(nDeps);
			List<UUID> childIds = new ArrayList<UUID>(nDeps);
			for(QualifiedDependency qDep : qDeps)
			{
				ComponentRequest request = qDep.getRequest();
				if(request instanceof ObtainedDependency)
					//
					// Implicitly obtained from another dependency. Don't
					// include it here.
					//
					continue;

				AdvisorNode override = cquery.getMatchingNode(request);
				if(override != null)
					qDep = qDep.applyAdvice(override);
				UnresolvedNode node = new UnresolvedNode(qDep);
				children.add(node);
				childIds.add(node.getId());
			}
			m_children = Collections.unmodifiableList(children);
			m_childIds = Collections.unmodifiableList(childIds);
		}
	}

	public ResolvedNode(Resolution resolution, List<DepNode> children)
	{
		m_resolution = resolution;
		m_resolutionId = resolution.getId();
		m_children = UUIDKeyed.createUnmodifiableList(children);

		int top = (children == null) ? 0 : children.size();
		if(top == 0)
			m_childIds = Collections.emptyList();
		else
		{
			ArrayList<UUID> childIds = new ArrayList<UUID>(top);
			for(int idx = 0; idx < top; ++idx)
				childIds.add(children.get(idx).getId());
			m_childIds = Collections.unmodifiableList(childIds);
		}
	}

	public ResolvedNode(UUID resolutionId, List<UUID> childIds)
	{
		m_resolutionId = resolutionId;
		m_childIds = UUIDKeyed.createUnmodifiableList(childIds);
	}

	@Override
	public List<Resolution> findAll(Set<Resolution> skipThese) throws CoreException
	{
		HashSet<Resolution> notThese = new HashSet<Resolution>();
		if(skipThese != null)
			notThese.addAll(skipThese);
		List<Resolution> all = new ArrayList<Resolution>();
		collectAll(notThese, all);
		return all;
	}

	@Override
	public synchronized List<DepNode> getChildren() throws CoreException
	{
		if(m_children == null)
			m_children = getChildren(getStorage());
		return m_children;
	}

	@Override
	public List<UUID> getChildrenIDs()
	{
		return m_childIds;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public QualifiedDependency getQualifiedDependency() throws CoreException
	{
		return getResolution().getQualifiedDependency();
	}

	@Override
	public ComponentRequest getRequest() throws CoreException
	{
		return getResolution().getRequest();
	}

	@Override
	public synchronized Resolution getResolution() throws CoreException
	{
		if(m_resolution == null)
			m_resolution = StorageManager.getDefault().getResolutions().getElement(m_resolutionId);
		return m_resolution;
	}

	@Override
	public UUID getResolutionId()
	{
		return m_resolutionId;
	}

	@Override
	public String getViewName() throws CoreException
	{
		Resolution resolution = getResolution();
		String name = resolution.getRequest().getViewName();
		IVersionSelector version = resolution.getVersionMatch().getFixedVersionSelector();
		if(version != null)
			name = name + ':' + version;
		return name;
	}

	/**
	 * Checks if the nodeID is equal to one of the children ids.
	 * @param nodeID The nodeID to check for.
	 * @return true if the nodeID was equal to one of the children ids.
	 */
	@Override
	public boolean isChildId(UUID nodeId)
	{
		for(UUID child : m_childIds)
			if(nodeId.equals(child))
				return true;
		return false;
	}

	@Override
	public boolean isFullyResolved(ComponentQuery query) throws CoreException
	{
		for(DepNode child : getChildren())
			if(!child.isFullyResolved(query))
				return false;
		return true;
	}

	@Override
	public final boolean isReferencing(UUID nodeId, boolean shallow) throws CoreException
	{
		if(getId().equals(nodeId))
			return true;

		for(UUID childId : m_childIds)
			if(childId.equals(nodeId))
				return true;

		if(!shallow)
		{
			for(DepNode child : getChildren())
				if(child.isReferencing(nodeId, shallow))
					return true;
		}
		return false;
	}

	@Override
	public void store() throws CoreException
	{
		ISaxableStorage<DepNode> nodes = getStorage();

		if(m_resolution == null)
			getResolution();
		else
			m_resolution.store();

		if(m_children == null)
			m_children = getChildren(nodes);
		else
		{
			for(DepNode child : m_children)
				child.store();
		}
		nodes.putElement(this);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		Utils.addAttribute(attrs, ATTR_RESOLUTION_ID, m_resolutionId.toString());
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		if(m_childIds.size() > 0)
		{
			String childName = Utils.makeQualifiedName(prefix, CHILD_TAG);
			for(UUID child : m_childIds)
			{
				AttributesImpl attrs = new AttributesImpl();
				Utils.addAttribute(attrs, ElementRefHandler.ATTR_REFID, child.toString());
				receiver.startElement(namespace, CHILD_TAG, childName, attrs);
				receiver.endElement(namespace, CHILD_TAG, childName);
			}
		}
	}

	@Override
	void addMaterializations(List<Materialization> minfos, RMContext context, Set<Resolution> skipThese)
	throws CoreException
	{
		for(DepNode child : getChildren())
			child.addMaterializations(minfos, context, skipThese);
		Resolution resolution = getResolution();
		if(!skipThese.contains(resolution))
		{
			skipThese.add(resolution);
			resolution.addMaterialization(minfos, context);
		}
	}

	@Override
	void collectAll(Set<Resolution> notThese, List<Resolution> all) throws CoreException
	{
		Resolution resolution = getResolution();
		if(notThese.contains(resolution))
			return;
		notThese.add(resolution);

		// It's rather important that we do depth first here and store
		// the child before its parent since they need to be materialized
		// and bound in that order.
		//
		for(DepNode child : getChildren())
			child.collectAll(notThese, all);
		all.add(getResolution());
	}

	@Override
	void removeChildren() throws CoreException
	{
		// Scan all nodes for children that appoints this node
		//
		UUID thisId = getId();
		ISaxableStorage<DepNode> nodes = getStorage();
		for(DepNode node : nodes.getElements())
		{
			for(UUID childId : node.getChildrenIDs())
				if(childId.equals(thisId))
					throw new ReferentialIntegrityException(this, "remove", "Child of other ResolvedNode");
		}
	}

	@Override
	DepNode replaceNode(DepNode topReplacer, DepNode node, Map<DepNode,DepNode> visited) throws CoreException
	{
		DepNode self = super.replaceNode(topReplacer, node, visited);
		if(self != this)
			return self;

		List<DepNode> newChildren = null;
		List<DepNode> oldChildren = getChildren();
		int numChildren = oldChildren.size();
		for(int idx = 0; idx < numChildren; ++idx)
		{
			DepNode oldChild = oldChildren.get(idx);
			DepNode newChild = oldChild.replaceNode(topReplacer, node, visited);
			if(oldChild == newChild)
				continue;

			if(newChildren == null)
			{
				newChildren = new ArrayList<DepNode>(numChildren);
				newChildren.addAll(oldChildren);
			}
			newChildren.set(idx, newChild);
		}

		if(newChildren == null)
			return this;

		self = new ResolvedNode(getResolution(), newChildren);
		visited.put(this, self);
		return self;
	}

	private List<DepNode> getChildren(ISaxableStorage<DepNode> storage) throws CoreException
	{
		int top = m_childIds.size();
		ArrayList<DepNode> childNodes = new ArrayList<DepNode>(top);
		for(int idx = 0; idx < top; ++idx)
			childNodes.add(storage.getElement(m_childIds.get(idx)));
		return Collections.unmodifiableList(childNodes);
	}
}
