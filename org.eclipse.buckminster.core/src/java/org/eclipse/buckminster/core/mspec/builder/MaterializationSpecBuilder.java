/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationSpecBuilder extends MaterializationDirectiveBuilder implements IMaterializationSpec {
	private final List<MaterializationNodeBuilder> nodes = new ArrayList<MaterializationNodeBuilder>();

	private String shortDesc;

	private String name;

	private String url;

	private URL contextURL;

	public MaterializationNodeBuilder addNodeBuilder() {
		MaterializationNodeBuilder node = new MaterializationNodeBuilder();
		nodes.add(node);
		return node;
	}

	@Override
	public void clear() {
		super.clear();
		name = null;
		shortDesc = null;
		url = null;
		contextURL = null;
		nodes.clear();
	}

	public MaterializationSpec createMaterializationSpec() {
		return new MaterializationSpec(this);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(this))
			return this;
		if (adapter.isAssignableFrom(MaterializationSpec.class))
			return createMaterializationSpec();
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public URL getContextURL() {
		return contextURL;
	}

	@Override
	public IMaterializationNode getMatchingNode(IComponentName cName) {
		return getMatchingNodeBuilder(cName);
	}

	@Override
	public IMaterializationNode getMatchingNode(Resolution res) {
		return getMatchingNodeBuilder(res);
	}

	public MaterializationNodeBuilder getMatchingNodeBuilder(IComponentName cName) {
		String cn = cName.getName();
		for (MaterializationNodeBuilder aNode : nodes) {
			Pattern pattern = aNode.getNamePattern();
			if (pattern != null && pattern.matcher(cn).find()) {
				String matchingCType = aNode.getComponentTypeID();
				if (matchingCType == null || matchingCType.equals(cName.getComponentTypeID()))
					return aNode;
			}
		}
		return null;
	}

	public MaterializationNodeBuilder getMatchingNodeBuilder(Resolution res) {
		Map<String, ? extends Object> props = null;
		ComponentIdentifier ci = res.getComponentIdentifier();
		for (MaterializationNodeBuilder aNode : nodes) {
			Pattern pattern = aNode.getNamePattern();
			if (!(pattern == null || pattern.matcher(ci.getName()).find()))
				continue;

			String matchingCType = aNode.getComponentTypeID();
			if (!(matchingCType == null || matchingCType.equals(ci.getComponentTypeID())))
				continue;

			Filter filter = aNode.getFilter();
			if (filter != null) {
				if (props == null)
					props = res.getProperties();
				if (!filter.matches(props))
					continue;
			}
			return aNode;
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	public List<MaterializationNodeBuilder> getNodeBuilders() {
		return nodes;
	}

	@Override
	public List<? extends IMaterializationNode> getNodes() {
		return getNodeBuilders();
	}

	@Override
	public URL getResolvedURL() {
		return URLUtils.resolveURL(contextURL, url);
	}

	@Override
	public String getShortDesc() {
		return shortDesc;
	}

	@Override
	public String getURL() {
		return url;
	}

	public void initFrom(IMaterializationSpec mspec) {
		super.initFrom(mspec);
		name = mspec.getName();
		shortDesc = mspec.getShortDesc();
		url = mspec.getURL();
		contextURL = mspec.getContextURL();
		for (IMaterializationNode node : mspec.getNodes()) {
			MaterializationNodeBuilder nodeBuilder = new MaterializationNodeBuilder();
			nodeBuilder.initFrom(node);
			nodes.add(nodeBuilder);
		}
	}

	public void setContextURL(URL contextURL) {
		this.contextURL = contextURL;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setURL(String url) {
		this.url = url;
	}

	@Override
	public void toSax(ContentHandler receiver) throws SAXException {
		MaterializationSpec mspec = new MaterializationSpec(this);
		mspec.toSax(receiver);
	}
}
