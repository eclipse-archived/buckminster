/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.query.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class AdvisorNodeBuilder implements IAdvisorNode {
	private boolean allowCircularDependency;

	private final ArrayList<String> attributes = new ArrayList<String>();

	private VersionSelector[] branchTagPath;

	private String componentType;

	private Documentation documentation;

	private Filter filter;

	private MutableLevel mutableLevel;

	private Pattern namePattern;

	private URL overlayFolder;

	private Map<String, String> properties;

	private boolean prune;

	private int[] resolutionPrio;

	private String revision;

	private boolean skipComponent;

	private SourceLevel sourceLevel;

	private boolean systemDiscovery;

	private Date timestamp;

	private boolean useTargetPlatform;

	private boolean useMaterialization;

	private boolean useWorkspace;

	private boolean useRemoteResolution;

	private VersionRange versionOverride;

	public AdvisorNodeBuilder() {
		this.clear();
	}

	public void addAttribute(String attribute) {
		attributes.add(attribute);
	}

	public void addAttributes(List<String> attrs) {
		attributes.addAll(attrs);
	}

	public boolean allowCircularDependency() {
		return allowCircularDependency;
	}

	public void clear() {
		attributes.clear();
		properties = null;
		documentation = null;
		componentType = null;
		filter = null;
		mutableLevel = MutableLevel.INDIFFERENT;
		namePattern = null;
		overlayFolder = null;
		prune = false;
		skipComponent = false;
		allowCircularDependency = false;
		sourceLevel = SourceLevel.INDIFFERENT;
		useTargetPlatform = true;
		useMaterialization = true;
		useWorkspace = true;
		versionOverride = null;
		useRemoteResolution = true;
		systemDiscovery = true;
		branchTagPath = VersionSelector.EMPTY_PATH;
		revision = null;
		timestamp = null;
		resolutionPrio = IAdvisorNode.DEFAULT_RESOLUTION_PRIO;
	}

	public AdvisorNode create() {
		return new AdvisorNode(this);
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public VersionSelector[] getBranchTagPath() {
		return branchTagPath;
	}

	public String getComponentTypeID() {
		return componentType;
	}

	public Documentation getDocumentation() {
		return documentation;
	}

	public Filter getFilter() {
		return filter;
	}

	public IPath getMaterializationLocation(String projectName) {
		// TODO: Implement this
		return null;
	}

	public MutableLevel getMutableLevel() {
		return mutableLevel;
	}

	public Pattern getNamePattern() {
		return namePattern;
	}

	public URL getOverlayFolder() {
		return overlayFolder;
	}

	public Map<String, String> getProperties() {
		if (properties == null)
			properties = new HashMap<String, String>();
		return properties;
	}

	public int[] getResolutionPrio() {
		return resolutionPrio;
	}

	public String getRevision() {
		return revision;
	}

	public SourceLevel getSourceLevel() {
		return sourceLevel;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public VersionRange getVersionOverride() {
		return versionOverride;
	}

	public void initFrom(IAdvisorNode node) {
		this.clear();
		allowCircularDependency = node.allowCircularDependency();
		attributes.addAll(node.getAttributes());
		componentType = node.getComponentTypeID();
		documentation = node.getDocumentation();
		filter = node.getFilter();
		mutableLevel = node.getMutableLevel();
		namePattern = node.getNamePattern();
		overlayFolder = node.getOverlayFolder();
		Map<String, String> props = node.getProperties();
		if (props.size() > 0)
			properties = new HashMap<String, String>(props);
		prune = node.isPrune();
		skipComponent = node.skipComponent();
		sourceLevel = node.getSourceLevel();
		useMaterialization = node.isUseMaterialization();
		useRemoteResolution = node.isUseRemoteResolution();
		useTargetPlatform = node.isUseTargetPlatform();
		useWorkspace = node.isUseWorkspace();
		versionOverride = node.getVersionOverride();
		systemDiscovery = node.isSystemDiscovery();
		branchTagPath = node.getBranchTagPath();
		revision = node.getRevision();
		timestamp = node.getTimestamp();
		resolutionPrio = node.getResolutionPrio();
	}

	public boolean isPrune() {
		return prune;
	}

	public boolean isSystemDiscovery() {
		return systemDiscovery;
	}

	public boolean isUseMaterialization() {
		return useMaterialization;
	}

	public boolean isUseRemoteResolution() {
		return useRemoteResolution;
	}

	public boolean isUseTargetPlatform() {
		return useTargetPlatform;
	}

	public boolean isUseWorkspace() {
		return useWorkspace;
	}

	public void setAllowCircularDependency(boolean allowCircularDependency) {
		this.allowCircularDependency = allowCircularDependency;
	}

	public void setBranchTagPath(VersionSelector[] branchTagPath) {
		this.branchTagPath = branchTagPath == null ? VersionSelector.EMPTY_PATH : branchTagPath;
	}

	public void setComponentTypeID(String componentType) {
		this.componentType = componentType;
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setMutableLevel(MutableLevel mutableLevel) {
		this.mutableLevel = mutableLevel == null ? MutableLevel.INDIFFERENT : mutableLevel;
	}

	public void setNamePattern(Pattern namePattern) {
		this.namePattern = namePattern;
	}

	public void setOverlayFolder(URL addOnFolder) {
		this.overlayFolder = addOnFolder;
	}

	public void setPrune(boolean prune) {
		this.prune = prune;
	}

	public void setResolutionPrio(int[] resolutionPrio) {
		this.resolutionPrio = resolutionPrio;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setSkipComponent(boolean flag) {
		this.skipComponent = flag;
	}

	public void setSourceLevel(SourceLevel sourceLevel) {
		this.sourceLevel = sourceLevel == null ? SourceLevel.INDIFFERENT : sourceLevel;
	}

	public void setSystemDiscovery(boolean flag) {
		this.systemDiscovery = flag;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setUseMaterialization(boolean flag) {
		this.useMaterialization = flag;
	}

	public void setUseRemoteResolution(boolean flag) {
		this.useRemoteResolution = flag;
	}

	public void setUseTargetPlatform(boolean flag) {
		this.useTargetPlatform = flag;
	}

	public void setUseWorkspace(boolean flag) {
		this.useWorkspace = flag;
	}

	public void setVersionOverride(VersionRange versionOverride) {
		this.versionOverride = versionOverride;
	}

	public boolean skipComponent() {
		return skipComponent;
	}
}
