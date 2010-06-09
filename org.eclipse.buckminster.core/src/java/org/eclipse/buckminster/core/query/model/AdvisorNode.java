/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.query.model;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class AdvisorNode extends AbstractSaxableElement implements Cloneable, IAdvisorNode {
	public static final String ATTR_ATTRIBUTES = "attributes"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	public static final String ATTR_MUTABLE_LEVEL = "mutableLevel"; //$NON-NLS-1$

	public static final String ATTR_NAME_PATTERN = "namePattern"; //$NON-NLS-1$

	public static final String ATTR_OVERLAY_FOLDER = "overlayFolder"; //$NON-NLS-1$

	public static final String ATTR_PRUNE = "prune"; //$NON-NLS-1$

	public static final String ATTR_SKIP_COMPONENT = "skipComponent"; //$NON-NLS-1$

	public static final String ATTR_SOURCE_LEVEL = "sourceLevel"; //$NON-NLS-1$

	public static final String ATTR_USE_TARGET_PLATFORM = "useTargetPlatform"; //$NON-NLS-1$

	public static final String ATTR_USE_MATERIALIZATION = "useMaterialization"; //$NON-NLS-1$

	public static final String ATTR_USE_WORKSPACE = "useWorkspace"; //$NON-NLS-1$

	public static final String ATTR_VERSION_OVERRIDE = "versionOverride"; //$NON-NLS-1$

	public static final String ATTR_VERSION_OVERRIDE_TYPE = "versionOverrideType"; //$NON-NLS-1$

	public static final String ATTR_ALLOW_CIRCULAR_DEPENDENCY = "allowCircularDependency"; //$NON-NLS-1$

	public static final String ATTR_WHEN_NOT_EMPTY = "whenNotEmpty"; //$NON-NLS-1$

	public static final String ATTR_USE_REMOTE_RESOLUTION = "useRemoteResolution"; //$NON-NLS-1$

	public static final String ATTR_SYSTEM_DISCOVERY = "systemDiscovery"; //$NON-NLS-1$

	public static final String ATTR_BRANCH_TAG_PATH = "branchTagPath"; //$NON-NLS-1$

	public static final String ATTR_REVISION = "revision"; //$NON-NLS-1$

	public static final String ATTR_TIMESTAMP = "timestamp"; //$NON-NLS-1$

	public static final String ATTR_RESOLUTION_PRIO = "resolutionPrio"; //$NON-NLS-1$

	public static final String TAG = "advisorNode"; //$NON-NLS-1$

	private final boolean allowCircularDependency;

	private final Documentation documentation;

	private final List<String> attributes;

	private final String componentTypeID;

	private final Filter filter;

	private final MutableLevel mutableLevel;

	private final Pattern namePattern;

	private final URL overlayFolder;

	private final Map<String, String> properties;

	private final boolean prune;

	private final boolean skipComponent;

	private final SourceLevel sourceLevel;

	private final boolean useTargetPlatform;

	private final boolean useMaterialization;

	private final boolean useWorkspace;

	private final VersionRange versionOverride;

	private final boolean useRemoteResolution;

	private final boolean systemDiscovery;

	private final VersionSelector[] branchTagPath;

	private final String revision;

	private final Date timestamp;

	private final int[] resolutionPrio;

	public AdvisorNode(AdvisorNodeBuilder bld) {
		documentation = bld.getDocumentation();
		allowCircularDependency = bld.allowCircularDependency();
		namePattern = bld.getNamePattern();
		filter = bld.getFilter();
		componentTypeID = bld.getComponentTypeID();
		overlayFolder = bld.getOverlayFolder();
		prune = bld.isPrune();
		mutableLevel = bld.getMutableLevel();
		sourceLevel = bld.getSourceLevel();
		skipComponent = bld.skipComponent();
		useMaterialization = bld.isUseMaterialization();
		useTargetPlatform = bld.isUseTargetPlatform();
		useWorkspace = bld.isUseWorkspace();
		versionOverride = bld.getVersionOverride();
		useRemoteResolution = bld.isUseRemoteResolution();
		systemDiscovery = bld.isSystemDiscovery();
		branchTagPath = bld.getBranchTagPath();
		revision = bld.getRevision();
		timestamp = bld.getTimestamp();
		resolutionPrio = bld.getResolutionPrio();
		attributes = Utils.createUnmodifiableList(bld.getAttributes());
		properties = ExpandingProperties.createUnmodifiableProperties(bld.getProperties());
	}

	@Override
	public boolean allowCircularDependency() {
		return allowCircularDependency;
	}

	@Override
	public final List<String> getAttributes() {
		return attributes;
	}

	@Override
	public final VersionSelector[] getBranchTagPath() {
		return branchTagPath;
	}

	@Override
	public final String getComponentTypeID() {
		return componentTypeID;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public final MutableLevel getMutableLevel() {
		return mutableLevel;
	}

	@Override
	public final Pattern getNamePattern() {
		return namePattern;
	}

	@Override
	public URL getOverlayFolder() {
		return overlayFolder;
	}

	@Override
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public int[] getResolutionPrio() {
		return resolutionPrio;
	}

	@Override
	public String getRevision() {
		return revision;
	}

	@Override
	public final SourceLevel getSourceLevel() {
		return sourceLevel;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public final VersionRange getVersionOverride() {
		return versionOverride;
	}

	@Override
	public final boolean isPrune() {
		return prune;
	}

	@Override
	public final boolean isSystemDiscovery() {
		return systemDiscovery;
	}

	@Override
	public final boolean isUseMaterialization() {
		return useMaterialization;
	}

	@Override
	public final boolean isUseRemoteResolution() {
		return useRemoteResolution;
	}

	@Override
	public final boolean isUseTargetPlatform() {
		return useTargetPlatform;
	}

	@Override
	public final boolean isUseWorkspace() {
		return useWorkspace;
	}

	@Override
	public final boolean skipComponent() {
		return skipComponent;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (namePattern != null)
			Utils.addAttribute(attrs, ATTR_NAME_PATTERN, namePattern.toString());
		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
		if (overlayFolder != null)
			Utils.addAttribute(attrs, ATTR_OVERLAY_FOLDER, overlayFolder.toString());
		if (componentTypeID != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, componentTypeID);
		if (mutableLevel != MutableLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_MUTABLE_LEVEL, mutableLevel.name());
		if (sourceLevel != SourceLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_SOURCE_LEVEL, sourceLevel.name());
		if (skipComponent)
			Utils.addAttribute(attrs, ATTR_SKIP_COMPONENT, "true"); //$NON-NLS-1$
		if (allowCircularDependency)
			Utils.addAttribute(attrs, ATTR_ALLOW_CIRCULAR_DEPENDENCY, "true"); //$NON-NLS-1$
		if (!systemDiscovery)
			Utils.addAttribute(attrs, ATTR_SYSTEM_DISCOVERY, "false"); //$NON-NLS-1$
		if (!useMaterialization)
			Utils.addAttribute(attrs, ATTR_USE_MATERIALIZATION, "false"); //$NON-NLS-1$
		if (!useRemoteResolution)
			Utils.addAttribute(attrs, ATTR_USE_REMOTE_RESOLUTION, "false"); //$NON-NLS-1$
		if (!useTargetPlatform)
			Utils.addAttribute(attrs, ATTR_USE_TARGET_PLATFORM, "false"); //$NON-NLS-1$
		if (!useWorkspace)
			Utils.addAttribute(attrs, ATTR_USE_WORKSPACE, "false"); //$NON-NLS-1$

		if (versionOverride != null)
			Utils.addAttribute(attrs, ATTR_VERSION_OVERRIDE, versionOverride.toString());
		String tmp = TextUtils.concat(attributes, ","); //$NON-NLS-1$
		if (tmp != null)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, tmp);
		if (prune)
			Utils.addAttribute(attrs, ATTR_PRUNE, "true"); //$NON-NLS-1$

		tmp = VersionSelector.toString(branchTagPath);
		if (tmp != null)
			Utils.addAttribute(attrs, ATTR_BRANCH_TAG_PATH, tmp);

		if (!Arrays.equals(resolutionPrio, DEFAULT_RESOLUTION_PRIO)) {
			StringBuilder bld = new StringBuilder();
			bld.append(resolutionPrio[0]);
			for (int idx = 1; idx < resolutionPrio.length; ++idx) {
				bld.append(',');
				bld.append(resolutionPrio[idx]);
			}
			Utils.addAttribute(attrs, ATTR_RESOLUTION_PRIO, bld.toString());
		}

		if (revision != null)
			Utils.addAttribute(attrs, ATTR_REVISION, revision);

		if (timestamp != null)
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(timestamp));
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());
		SAXEmitter.emitProperties(handler, properties, namespace, prefix, true, false);
	}
}
