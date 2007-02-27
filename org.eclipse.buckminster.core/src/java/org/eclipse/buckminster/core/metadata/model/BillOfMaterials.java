/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class BillOfMaterials extends DepNode
{
	public static final String ATTR_QUERY_ID = "componentQueryId";
	public static final String ATTR_TIMESTAMP = "timestamp";
	public static final String ATTR_TOP_NODE_ID = "topNodeId";

	@SuppressWarnings("hiding")
	public static final int SEQUENCE_NUMBER = 2;

	@SuppressWarnings("hiding")
	public static final String TAG = "billOfMaterials";

	public static BillOfMaterials create(DepNode topNode, ComponentQuery query) throws CoreException
	{
		return create(topNode, query, null);
	}

	public static BillOfMaterials create(DepNode topNode, ComponentQuery query, Date timestamp) throws CoreException
	{
		if(topNode == null)
			throw new IllegalArgumentException("Top node cannot be null");
		if(query == null)
			throw new IllegalArgumentException("Component query cannot be null");
		if(topNode instanceof BillOfMaterials)
		{
			BillOfMaterials bom = (BillOfMaterials)topNode;
			if(bom.getQuery().equals(query))
				return bom;
		}
		return new BillOfMaterials(topNode, query, timestamp);
	}

	/**
	 * Imports a previously exported <code>BillOfMaterials</code> into the workspace meta-data storage. The
	 * resulting workspace internal <code>BillOfMaterials</code> is returned.
	 * @param exported The external BillOfMaterials
	 * @return the workspace internal BillOfMaterials
	 * @throws CoreException
	 */
	public static BillOfMaterials importGraph(ExportedBillOfMaterials exported) throws CoreException
	{
		StorageManager sm = StorageManager.getDefault();
		List<IDWrapper> wrappers = exported.getContents();
		ISaxableStorage<CSpec> cspecStore = sm.getCSpecs();
		ISaxableStorage<ComponentQuery> queryStore = sm.getQueries();
		ISaxableStorage<Provider> providerStore = sm.getProviders();

		for(IDWrapper wrapper : wrappers)
		{
			IUUIDKeyed wrapped = wrapper.getWrapped();
			if(wrapped instanceof CSpec)
				cspecStore.putElement(wrapper.getId(), (CSpec)wrapped);
			else if(wrapped instanceof ComponentQuery)
				queryStore.putElement(wrapper.getId(), (ComponentQuery)wrapped);
			else if(wrapped instanceof Provider)
				providerStore.putElement(wrapper.getId(), (Provider)wrapped);
		}

		ISaxableStorage<Resolution> resolutionStore = sm.getResolutions();
		for(IDWrapper wrapper : wrappers)
		{
			IUUIDKeyed wrapped = wrapper.getWrapped();
			if(wrapped instanceof Resolution)
				resolutionStore.putElement(wrapper.getId(), (Resolution)wrapped);
		}

		ISaxableStorage<DepNode> nodeStore = sm.getDepNodes();
		for(IDWrapper wrapper : wrappers)
		{
			IUUIDKeyed wrapped = wrapper.getWrapped();
			if(wrapped instanceof DepNode)
				nodeStore.putElement(wrapper.getId(), (DepNode)wrapped);
		}

		BillOfMaterials bom = new BillOfMaterials(exported.getTopNodeId(), exported.getQueryId(), exported.getTimestamp());
		bom.store();	
		return bom;
	}

	private static void addIfNotAdded(UUID key, IUUIDKeyed object, Set<UUID> unique, List<IDWrapper> wrappers)
	{
		if(unique.contains(key))
			return;
		unique.add(key);
		wrappers.add(new IDWrapper(key, object));
	}

	private static void buildNodeMap(DepNode node, HashMap<ComponentIdentifier, DepNode> map) throws CoreException
	{
		Resolution resolution = node.getResolution();
		if(resolution == null)
			return;

		ComponentIdentifier id = resolution.getCSpec().getComponentIdentifier();
		if(map.containsKey(id))
			//
			// This node is already added
			//
			return;

		map.put(id, node);
		for(DepNode child : node.getChildren())
			buildNodeMap(child, map);
	}

	private static void collectNodeContents(DepNode node, Set<UUID> unique, List<IDWrapper> wrappers) throws CoreException
	{
		UUID nodeId = node.getId();
		if(unique.contains(nodeId))
			//
			// This node is already added
			//
			return;

		// Add the ResolvedNode to the contents
		//
		unique.add(nodeId);
		if(node instanceof BillOfMaterials)
		{
			BillOfMaterials bom = (BillOfMaterials)node;
			wrappers.add(new IDWrapper(bom.getQueryId(), bom.getQuery()));
			collectNodeContents(bom.getTopNode(), unique, wrappers);
		}
		else
		{
			Resolution resolution = node.getResolution();
			if(resolution != null)
			{
				// Add the Resolution to the contents
				//
				addIfNotAdded(resolution.getProviderId(), resolution.getProvider(), unique, wrappers);
				addIfNotAdded(resolution.getCSpecId(), resolution.getCSpec(), unique, wrappers);
				addIfNotAdded(resolution.getId(), resolution, unique, wrappers);
	
				// Recursively add all children of this ResolvedNode. It's
				// very important that we do this depth first since the leafs
				// must end up first in the list of IDWrappers.
				//
				for(DepNode child : node.getChildren())
					collectNodeContents(child, unique, wrappers);
			}
		}
		wrappers.add(new IDWrapper(nodeId, node));
	}
	
	// Private cache that speeds up the identifier to resolved node map. A cache
	// here is quite safe since both its scope and everything it contains is immutable
	//
	private transient HashMap<ComponentIdentifier,DepNode> m_nodeMap;

	private transient ComponentQuery m_query;

	private final UUID m_queryId;

	private final Date m_timestamp;

	private transient DepNode m_topNode;

	private final UUID m_topNodeId;

	public BillOfMaterials(UUID topNodeId, UUID queryId, Date timestamp)
	{
		if(topNodeId == null)
			throw new IllegalArgumentException("Top node ID cannot be null");
		if(queryId == null)
			throw new IllegalArgumentException("Component query ID cannot be null");
		if(timestamp == null)
			timestamp = new Date();
		m_topNodeId = topNodeId;
		m_queryId = queryId;
		m_timestamp = timestamp;
	}

	private BillOfMaterials(DepNode topNode, ComponentQuery query, Date timestamp)
	{
		m_topNode = topNode;
		m_query = query;
		m_topNodeId = topNode.getId();
		m_queryId = query.getId();
		m_timestamp = (timestamp == null) ? new Date() : timestamp;
	}

	/**
	 * Compares the two instances for equality without taking the creation date
	 * into account.
	 * @param other The instance that this instance is compared to
	 * @return true if the contents of the two instances is equal, not counting the creation date
	 */
	public boolean contentEqual(BillOfMaterials other)
	{
		return other != null && m_queryId.equals(other.m_queryId) && m_topNodeId.equals(other.m_topNodeId);
	}

	public List<Materialization> createMaterializations(RMContext context, Set<Resolution> skipThese)
	throws CoreException
	{
		List<Materialization> minfos = new ArrayList<Materialization>();
		HashSet<Resolution> excludes = new HashSet<Resolution>();
		if(skipThese != null)
			excludes.addAll(skipThese);
		getTopNode().addMaterializations(minfos, context, excludes);
		return minfos;
	}

	public ExportedBillOfMaterials exportGraph() throws CoreException
	{
		store();
		ArrayList<IDWrapper> wrappers = new ArrayList<IDWrapper>();
		HashSet<UUID> unique = new HashSet<UUID>();
		wrappers.add(new IDWrapper(m_queryId, getQuery()));
		collectNodeContents(getTopNode(), unique, wrappers);
		return new ExportedBillOfMaterials(getId(), m_topNodeId, m_queryId, m_timestamp, wrappers);
	}

	@Override
	public List<Resolution> findAll(Set<Resolution> skipThese) throws CoreException
	{
		return getTopNode().findAll(skipThese);
	}

	public BillOfMaterials fullyResolve(IProgressMonitor monitor) throws CoreException
	{
		return fullyResolve(new MainResolver(new RMContext(getQuery())), monitor);
	}

	public BillOfMaterials fullyResolve(IResolver resolver, IProgressMonitor monitor) throws CoreException
	{
		return resolver.resolveRemaining(this, monitor);
	}

	@Override
	public List<DepNode> getChildren() throws CoreException
	{
		return getTopNode().getChildren();
	}

	@Override
	public List<UUID> getChildrenIDs() throws CoreException
	{
		return getTopNode().getChildrenIDs();
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public QualifiedDependency getQualifiedDependency() throws CoreException
	{
		return getTopNode().getQualifiedDependency();
	}

	@Override
	public synchronized ComponentQuery getQuery() throws CoreException
	{
		if(m_query == null)
			m_query = StorageManager.getDefault().getQueries().getElement(m_queryId);
		return m_query;
	}

	@Override
	public UUID getQueryId()
	{
		return m_queryId;
	}

	@Override
	public ComponentRequest getRequest() throws CoreException
	{
		return getTopNode().getRequest();
	}

	@Override
	public Resolution getResolution() throws CoreException
	{
		return getTopNode().getResolution();
	}

	@Override
	public UUID getResolutionId() throws CoreException
	{
		return getTopNode().getResolutionId();
	}

	public synchronized DepNode getResolvedNode(ComponentIdentifier identifier) throws CoreException
	{
		if(m_nodeMap == null)
		{
			HashMap<ComponentIdentifier,DepNode> nodeMap = new HashMap<ComponentIdentifier, DepNode>();
			buildNodeMap(getTopNode(), nodeMap);
			m_nodeMap = nodeMap;
		}
		DepNode node = m_nodeMap.get(identifier);
		if(node == null)
			throw new MissingComponentException(identifier.toString());
		return node;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}

	public UUID getTopNodeId()
	{
		return m_topNodeId;
	}

	@Override
	public String getViewName() throws CoreException
	{
		return getTopNode().getViewName();
	}

	@Override
	public boolean isChildId(UUID nodeId) throws CoreException
	{
		return getTopNode().isChildId(nodeId);
	}

	public boolean isFullyResolved() throws CoreException
	{
		return getTopNode().isFullyResolved(getQuery());
	}

	@Override
	public boolean isFullyResolved(ComponentQuery query) throws CoreException
	{
		return getTopNode().isFullyResolved(query);
	}

	@Override
	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	@Override
	public final boolean isReferencing(UUID nodeId, boolean shallow) throws CoreException
	{
		return nodeId == getId() || getTopNode().isReferencing(nodeId, shallow);
	}

	@Override
	public void remove() throws CoreException
	{
		ISaxableStorage<DepNode> depNodes = getStorage();
		depNodes.removeElement(getId());
		getTopNode().remove();
	}

	public BillOfMaterials replaceNode(DepNode node) throws CoreException
	{
		return replaceNode(node, node);
	}

	@Override
	public void store() throws CoreException
	{
		if(m_topNode == null)
			getTopNode();
		else
			m_topNode.store();

		if(m_query == null)
			getQuery();
		else
			m_query.store();

		getStorage().putElement(this);
	}

	@Override
	void addAttributes(AttributesImpl attrs)
	{
		if(m_topNodeId != null)
			Utils.addAttribute(attrs, ATTR_TOP_NODE_ID, m_topNodeId.toString());
		Utils.addAttribute(attrs, ATTR_QUERY_ID, m_queryId.toString());
		Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(m_timestamp));
	}

	@Override
	void addMaterializations(List<Materialization> minfos, RMContext context, Set<Resolution> skipThese)
	throws CoreException
	{
		getTopNode().addMaterializations(minfos, context, skipThese);
	}

	@Override
	void collectAll(Set<Resolution> notThese, List<Resolution> all) throws CoreException
	{
		getTopNode().collectAll(notThese, all);
	}

	BillOfMaterials replaceNode(DepNode topReplacer, DepNode node) throws CoreException
	{
		List<DepNode> children = node.getChildren();
		int idx = children.size();
		BillOfMaterials self = this;
		while(--idx >= 0)
			self = self.replaceNode(node, children.get(idx));
		return (BillOfMaterials)self.replaceNode(node, node, new HashMap<DepNode,DepNode>());
	}

	@Override
	DepNode replaceNode(DepNode topReplacer, DepNode node, Map<DepNode,DepNode> visited) throws CoreException
	{
		if(node instanceof BillOfMaterials && node.getQuery().equals(getQuery()))
			node = ((BillOfMaterials)node).getTopNode();
		else
		{
			DepNode newNode = super.replaceNode(topReplacer, node, visited);
			if(newNode != this)
				return newNode;
		}

		DepNode oldTop = getTopNode();
		DepNode newTop = oldTop.replaceNode(topReplacer, node, visited);
		return (oldTop == newTop) ? this : create(newTop, getQuery());
	}

	private synchronized DepNode getTopNode() throws CoreException
	{
		if(m_topNode == null)
			m_topNode = StorageManager.getDefault().getDepNodes().getElement(m_topNodeId);
		return m_topNode;
	}
}
