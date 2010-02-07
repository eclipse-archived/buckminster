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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The resolution graph
 * 
 * @author Thomas Hallgren
 */
public class BillOfMaterials extends BOMNode {
	public static final String ATTR_QUERY_ID = "componentQueryId"; //$NON-NLS-1$

	public static final String ATTR_TIMESTAMP = "timestamp"; //$NON-NLS-1$

	public static final String ATTR_TOP_NODE_ID = "topNodeId"; //$NON-NLS-1$

	@SuppressWarnings("hiding")
	public static final int SEQUENCE_NUMBER = 3;

	@SuppressWarnings("hiding")
	public static final String TAG = "billOfMaterials"; //$NON-NLS-1$

	public static BillOfMaterials create(BOMNode topNode, ComponentQuery query) throws CoreException {
		return create(topNode, query, null);
	}

	public static BillOfMaterials create(BOMNode topNode, ComponentQuery query, Date timestamp) throws CoreException {
		if (topNode == null)
			throw new IllegalArgumentException(Messages.Top_node_cannot_be_null);
		if (query == null)
			throw new IllegalArgumentException(Messages.Component_query_cannot_be_null);
		if (topNode instanceof BillOfMaterials) {
			BillOfMaterials bom = (BillOfMaterials) topNode;
			if (bom.getQuery().equals(query))
				return bom;
		}
		return new BillOfMaterials(topNode, query, timestamp);
	}

	private static void addIfNotAdded(UUIDKeyed object, Set<UUID> unique, List<IDWrapper> wrappers) {
		UUID key = object.getId();
		if (unique.contains(key))
			return;
		unique.add(key);
		wrappers.add(new IDWrapper(key, object));
	}

	private static void buildNodeMap(BOMNode node, HashMap<ComponentIdentifier, BOMNode> map) throws CoreException {
		Resolution resolution = node.getResolution();
		if (resolution == null)
			return;

		ComponentIdentifier id = resolution.getCSpec().getComponentIdentifier();
		if (map.containsKey(id))
			//
			// This node is already added
			//
			return;

		map.put(id, node);
		for (BOMNode child : node.getChildren())
			buildNodeMap(child, map);
	}

	private static void collectNodeContents(BOMNode node, Set<UUID> unique, List<IDWrapper> wrappers) throws CoreException {
		UUID nodeId = node.getId();
		if (unique.contains(nodeId))
			//
			// This node is already added
			//
			return;

		// Add the ResolvedNode to the contents
		//
		unique.add(nodeId);
		if (node instanceof BillOfMaterials) {
			BillOfMaterials bom = (BillOfMaterials) node;
			wrappers.add(new IDWrapper(bom.getQueryId(), bom.getQuery()));
			collectNodeContents(bom.getTopNode(), unique, wrappers);
		} else {
			Resolution resolution = node.getResolution();
			if (resolution != null) {
				// Add the Resolution to the contents
				//
				addIfNotAdded(resolution.getProvider(), unique, wrappers);
				addIfNotAdded(resolution.getCSpec(), unique, wrappers);
				addIfNotAdded(resolution, unique, wrappers);

				// Recursively add all children of this ResolvedNode. It's
				// very important that we do this depth first since the leafs
				// must end up first in the list of IDWrappers.
				//
				for (BOMNode child : node.getChildren())
					collectNodeContents(child, unique, wrappers);
			}
		}
		wrappers.add(new IDWrapper(nodeId, node));
	}

	// Private cache that speeds up the identifier to resolved node map. A cache
	// here is quite safe since both its scope and everything it contains is
	// immutable
	//
	private transient HashMap<ComponentIdentifier, BOMNode> nodeMap;

	private final ComponentQuery query;

	private final Date timestamp;

	private final BOMNode topNode;

	public BillOfMaterials(BOMNode topNode, ComponentQuery query, Date timestamp) {
		super();
		this.topNode = topNode;
		this.query = query;
		this.timestamp = (timestamp == null) ? new Date() : timestamp;
	}

	public void addMaterializationNodes(MaterializationSpecBuilder bld) throws CoreException {
		for (Resolution res : findAll(null)) {
			if (!res.isMaterializable())
				continue;
			res.getProvider().getReaderType().addMaterializationNode(bld, res);
		}
	}

	/**
	 * Compares the two instances for equality without taking the creation date
	 * into account.
	 * 
	 * @param other
	 *            The instance that this instance is compared to
	 * @return true if the contents of the two instances is equal, not counting
	 *         the creation date
	 */
	public boolean contentEqual(BillOfMaterials other) {
		return other != null && topNode.equals(other.topNode);
	}

	@Override
	public List<Resolution> findAll(Set<Resolution> skipThese) throws CoreException {
		return getTopNode().findAll(skipThese);
	}

	public List<Resolution> findMaterializationCandidates(RMContext context, MaterializationSpec mspec) throws CoreException {
		List<Resolution> minfos = new ArrayList<Resolution>();
		addMaterializationCandidates(context, minfos, getQuery(), mspec, new HashSet<Resolution>());
		return minfos;
	}

	public BillOfMaterials fullyResolve(IProgressMonitor monitor) throws CoreException {
		return fullyResolve(new MainResolver(new ResolutionContext(getQuery())), monitor);
	}

	public BillOfMaterials fullyResolve(IResolver resolver, IProgressMonitor monitor) throws CoreException {
		return resolver.resolveRemaining(this, monitor);
	}

	@Override
	public List<BOMNode> getChildren() {
		return getTopNode().getChildren();
	}

	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public QualifiedDependency getQualifiedDependency() {
		return getTopNode().getQualifiedDependency();
	}

	@Override
	public synchronized ComponentQuery getQuery() {
		return query;
	}

	@Override
	public UUID getQueryId() {
		return query.getId();
	}

	@Override
	public ComponentRequest getRequest() {
		return getTopNode().getRequest();
	}

	@Override
	public Resolution getResolution() {
		return getTopNode().getResolution();
	}

	@Override
	public UUID getResolutionId() throws CoreException {
		return getTopNode().getResolutionId();
	}

	public synchronized BOMNode getResolvedNode(IComponentIdentifier identifier) throws CoreException {
		if (nodeMap == null) {
			HashMap<ComponentIdentifier, BOMNode> nm = new HashMap<ComponentIdentifier, BOMNode>();
			buildNodeMap(getTopNode(), nm);
			nodeMap = nm;
		}
		BOMNode node = nodeMap.get(identifier);
		if (node == null)
			throw new MissingComponentException(identifier.toString());
		return node;
	}

	public String getTagInfo() {
		return getQuery().getTagInfo();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public List<ComponentRequest> getUnresolvedList() {
		ArrayList<ComponentRequest> unresolved = new ArrayList<ComponentRequest>();
		topNode.addUnresolved(unresolved, new HashSet<Resolution>());
		return unresolved;
	}

	@Override
	public String getViewName() throws CoreException {
		return getTopNode().getViewName();
	}

	@Override
	public boolean isChild(BOMNode node) throws CoreException {
		return getTopNode().isChild(node);
	}

	@Override
	public boolean isFullyResolved(ComponentQuery cquery, Map<String, ? extends Object> properties) throws CoreException {
		return getTopNode().isFullyResolved(cquery, properties);
	}

	public boolean isFullyResolved(Map<String, ? extends Object> properties) throws CoreException {
		return getTopNode().isFullyResolved(getQuery(), properties);
	}

	@Override
	public final boolean isReferencing(BOMNode node, boolean shallow) throws CoreException {
		return equals(node) || getTopNode().isReferencing(node, shallow);
	}

	public BillOfMaterials replaceNode(BOMNode node) throws CoreException {
		return (BillOfMaterials) replaceNode(node, new HashMap<BOMNode, BOMNode>());
	}

	public BillOfMaterials switchContent(BillOfMaterials other) {
		if (topNode.equals(other.getTopNode()))
			return this;

		return new BillOfMaterials(other.getTopNode(), query, new Date());
	}

	@Override
	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException {
		// Note, we do not call super here since we want the top element to
		// contain the collection
		//
		HashMap<String, String> prefixMappings = new HashMap<String, String>();
		ArrayList<IDWrapper> wrappers = new ArrayList<IDWrapper>();
		HashSet<UUID> unique = new HashSet<UUID>();
		wrappers.add(new IDWrapper(query.getId(), query));
		try {
			collectNodeContents(getTopNode(), unique, wrappers);
		} catch (CoreException e) {
			throw new SAXException(e);
		}

		for (IDWrapper wrapper : wrappers) {
			UUIDKeyed wrapped = wrapper.getWrapped();
			if (wrapped instanceof Provider)
				((Provider) wrapped).addPrefixMappings(prefixMappings);
		}

		Set<Map.Entry<String, String>> pfxMappings = prefixMappings.entrySet();
		if (pfxMappings.size() > 0) {
			for (Map.Entry<String, String> pfxMapping : pfxMappings)
				receiver.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());
		}

		AttributesImpl attrs = new AttributesImpl();
		addAttributes(attrs);
		Utils.emitCollection(namespace, prefix, localName, IDWrapper.TAG, attrs, wrappers, receiver);

		if (pfxMappings.size() > 0) {
			for (Map.Entry<String, String> pfxMapping : pfxMappings)
				receiver.endPrefixMapping(pfxMapping.getKey());
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		if (topNode != null)
			Utils.addAttribute(attrs, ATTR_TOP_NODE_ID, topNode.getId().toString());
		Utils.addAttribute(attrs, ATTR_QUERY_ID, query.getId().toString());
		Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(timestamp));
	}

	@Override
	void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery cquery, MaterializationSpec mspec,
			Set<Resolution> perused) throws CoreException {
		getTopNode().addMaterializationCandidates(context, resolutions, cquery, mspec, perused);
	}

	@Override
	void collectAll(Set<Resolution> notThese, List<Resolution> all) throws CoreException {
		getTopNode().collectAll(notThese, all);
	}

	@Override
	BOMNode replaceNode(BOMNode node, Map<BOMNode, BOMNode> visited) throws CoreException {
		BOMNode self = visited.get(this);
		if (self != null)
			return self;

		BOMNode oldTop = getTopNode();
		if (node instanceof BillOfMaterials && node.getQuery().equals(getQuery())) {
			BOMNode newTop = ((BillOfMaterials) node).getTopNode();
			if (oldTop.getQualifiedDependency().equals(newTop.getQualifiedDependency())) {
				visited.put(this, node);
				return node;
			}
		}

		BOMNode newTop = oldTop.replaceNode(node, visited);
		self = (oldTop == newTop) ? this : create(newTop, getQuery());
		visited.put(this, self);
		return self;
	}

	/**
	 * Special sax output method that is used for BillOfMaterials inlined in
	 * other BillOfMaterials since the attributes are sufficient here. All
	 * IdWrapper instances will be emitted in the outermost BillOfMaterials
	 * 
	 * @param receiver
	 * @param namespace
	 * @param prefix
	 * @param localName
	 * @throws SAXException
	 */
	void wrappedToSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException {
		AttributesImpl attrs = new AttributesImpl();
		addAttributes(attrs);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}

	private synchronized BOMNode getTopNode() {
		return topNode;
	}
}
