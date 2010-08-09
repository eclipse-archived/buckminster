/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class AdvisorNodeHandler extends PropertyManagerHandler {
	public static final String TAG = AdvisorNode.TAG;

	private DocumentationHandler documentationHandler;

	private AdvisorNodeBuilder builder;

	public AdvisorNodeHandler(AbstractHandler parent) {
		super(parent, TAG);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == documentationHandler)
			builder.setDocumentation(documentationHandler.createDocumentation());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (DocumentationHandler.TAG.equals(localName)) {
			if (documentationHandler == null)
				documentationHandler = new DocumentationHandler(this);
			ch = documentationHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public Map<String, String> getProperties() {
		return builder.getProperties();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		builder = new AdvisorNodeBuilder();
		builder.setNamePattern(getOptionalPatternValue(attrs, AdvisorNode.ATTR_NAME_PATTERN));
		builder.setComponentTypeID(getOptionalStringValue(attrs, "componentType")); //$NON-NLS-1$

		String filterStr = getOptionalStringValue(attrs, AdvisorNode.ATTR_FILTER);
		if (filterStr != null) {
			try {
				builder.setFilter(FilterFactory.newInstance(filterStr));
			} catch (InvalidSyntaxException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}

		String tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_OVERLAY_FOLDER);
		if (tmp != null) {
			try {
				builder.setOverlayFolder(URLUtils.normalizeToURL(tmp));
			} catch (IOException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_MUTABLE_LEVEL);
		if (tmp != null)
			builder.setMutableLevel(MutableLevel.valueOf(tmp));

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_SOURCE_LEVEL);
		if (tmp != null)
			builder.setSourceLevel(SourceLevel.valueOf(tmp));
		try {
			builder.setVersionOverride(VersionHelper.parseVersionRangeAttributes(attrs, AdvisorNode.ATTR_VERSION_OVERRIDE,
					AdvisorNode.ATTR_VERSION_OVERRIDE_TYPE));
		} catch (CoreException e) {
			throw new SAXParseException(e.toString(), this.getDocumentLocator());
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_BRANCH_TAG_PATH);
		if (tmp != null) {
			try {
				builder.setBranchTagPath(VersionSelector.fromPath(tmp));
			} catch (IllegalArgumentException e) {
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
		}

		builder.setRevision(getOptionalStringValue(attrs, AdvisorNode.ATTR_REVISION));

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_TIMESTAMP);
		if (tmp != null) {
			try {
				builder.setTimestamp(DateAndTimeUtils.fromISOFormat(tmp));
			} catch (ParseException e) {
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_ATTRIBUTES);
		if (tmp != null) {
			StringTokenizer tokens = new StringTokenizer(tmp, ","); //$NON-NLS-1$
			while (tokens.hasMoreElements())
				builder.addAttribute(tokens.nextToken());
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_RESOLUTION_PRIO);
		if (tmp != null) {
			int max = IAdvisorNode.DEFAULT_RESOLUTION_PRIO.length;
			int[] prios = new int[max];
			try {
				int idx = 0;
				StringTokenizer tokens = new StringTokenizer(tmp, ","); //$NON-NLS-1$
				while (tokens.hasMoreElements()) {
					if (idx == max) {
						idx++; // Overflow
						break;
					}
					prios[idx++] = Integer.parseInt(tokens.nextToken());
				}
				if (idx != max)
					throw new SAXParseException(Messages.Incorrect_number_of_resolution_priorites, this.getDocumentLocator());
			} catch (NumberFormatException e) {
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
			builder.setResolutionPrio(prios);
		}

		builder.setPrune(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_PRUNE, false));
		builder.setAllowCircularDependency(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_ALLOW_CIRCULAR_DEPENDENCY, false));
		builder.setSkipComponent(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_SKIP_COMPONENT, false));

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_USE_TARGET_PLATFORM);
		if (tmp == null)
			// Get deprecated alternative
			tmp = getOptionalStringValue(attrs, "useInstalled"); //$NON-NLS-1$
		builder.setUseTargetPlatform(tmp == null || "true".equalsIgnoreCase(tmp)); //$NON-NLS-1$

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_USE_WORKSPACE);
		if (tmp == null)
			// Get deprecated alternative
			tmp = getOptionalStringValue(attrs, "useProject"); //$NON-NLS-1$
		builder.setUseWorkspace(tmp == null || "true".equalsIgnoreCase(tmp)); //$NON-NLS-1$

		builder.setUseMaterialization(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_USE_MATERIALIZATION, true));
		builder.setUseRemoteResolution(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_USE_REMOTE_RESOLUTION, true));
		builder.setSystemDiscovery(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_SYSTEM_DISCOVERY, true));

		String spacePath = getOptionalStringValue(attrs, "spacePath"); //$NON-NLS-1$
		if (spacePath != null) {
			logAttributeDeprecation(TAG, "spacePath", "property buckminster.spacePath="); //$NON-NLS-1$ //$NON-NLS-2$
			getProperties().put("buckminster.spacepath", spacePath); //$NON-NLS-1$
		}
	}

	AdvisorNodeBuilder getAdvisorNodeBuilder() {
		return builder;
	}
}
