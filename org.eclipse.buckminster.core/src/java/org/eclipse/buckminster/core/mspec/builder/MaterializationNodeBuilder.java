/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationNodeBuilder extends MaterializationDirectiveBuilder implements IMaterializationNode {
	private Pattern namePattern;

	private Filter filter;

	private IPath leafArtifact;

	private String componentTypeID;

	private boolean exclude;

	private Pattern bindingNamePattern;

	private String bindingNameReplacement;

	private String suffix;

	private boolean unpack;

	private boolean expand = true;

	// Only valid when materializing into workspace
	//
	private IPath resourcePath;

	@Override
	public void clear() {
		super.clear();
		namePattern = null;
		filter = null;
		leafArtifact = null;
		componentTypeID = null;
		resourcePath = null;
		exclude = false;
		bindingNamePattern = null;
		bindingNameReplacement = null;
		suffix = null;
		unpack = false;
		expand = true;
	}

	public MaterializationNode createMaterializationNode() {
		return new MaterializationNode(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(this))
			return this;
		if (adapter.isAssignableFrom(MaterializationNode.class))
			return new MaterializationNode(this);
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public Pattern getBindingNamePattern() {
		return bindingNamePattern;
	}

	public String getBindingNameReplacement() {
		return bindingNameReplacement;
	}

	public String getComponentTypeID() {
		return componentTypeID;
	}

	public Filter getFilter() {
		return filter;
	}

	public IPath getLeafArtifact() {
		return leafArtifact;
	}

	public Pattern getNamePattern() {
		return namePattern;
	}

	public IPath getResourcePath() {
		return resourcePath;
	}

	public String getSuffix() {
		return suffix;
	}

	public void initFrom(IMaterializationNode mn) {
		super.initFrom(mn);
		namePattern = mn.getNamePattern();
		filter = mn.getFilter();
		leafArtifact = mn.getLeafArtifact();
		componentTypeID = mn.getComponentTypeID();
		resourcePath = mn.getResourcePath();
		exclude = mn.isExclude();
		bindingNamePattern = mn.getBindingNamePattern();
		bindingNameReplacement = mn.getBindingNameReplacement();
		suffix = mn.getSuffix();
		unpack = mn.isUnpack();
		expand = mn.isExpand();
	}

	public boolean isExclude() {
		return exclude;
	}

	public boolean isExpand() {
		return expand;
	}

	public boolean isUnpack() {
		return unpack;
	}

	public void setBindingNamePattern(Pattern bindingNamePattern) {
		this.bindingNamePattern = bindingNamePattern;
	}

	public void setBindingNameReplacement(String bindingNameReplacement) {
		this.bindingNameReplacement = bindingNameReplacement;
	}

	public void setComponentTypeID(String componentTypeID) {
		this.componentTypeID = componentTypeID;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setLeafArtifact(IPath leafArtifact) {
		this.leafArtifact = leafArtifact;
	}

	public void setNamePattern(Pattern namePattern) {
		this.namePattern = namePattern;
	}

	public void setResourcePath(IPath resourcePath) {
		this.resourcePath = resourcePath;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setUnpack(boolean unpack) {
		this.unpack = unpack;
	}
}
