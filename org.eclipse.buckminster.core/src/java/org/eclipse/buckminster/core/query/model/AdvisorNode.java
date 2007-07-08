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
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class AdvisorNode implements ISaxableElement, Cloneable
{
	public static final int PRIO_VERSION_DESIGNATOR = 1;
	public static final int PRIO_BRANCHTAG_PATH_INDEX = 2;
	public static final int PRIO_SPACE_PATH_INDEX = 3;

	public static final int[] DEFAULT_RESOLUTION_PRIO = { PRIO_VERSION_DESIGNATOR, PRIO_BRANCHTAG_PATH_INDEX, PRIO_SPACE_PATH_INDEX };

	public static final String ATTR_ATTRIBUTES = "attributes";

	public static final String ATTR_COMPONENT_TYPE = "category";

	public static final String ATTR_MUTABLE_LEVEL = "mutableLevel";

	public static final String ATTR_NAME_PATTERN = "namePattern";

	public static final String ATTR_OVERLAY_FOLDER = "overlayFolder";

	public static final String ATTR_PRUNE = "prune";

	public static final String ATTR_SKIP_COMPONENT = "skipComponent";

	public static final String ATTR_SOURCE_LEVEL = "sourceLevel";

	public static final String ATTR_USE_INSTALLED = "useInstalled";

	public static final String ATTR_USE_MATERIALIZATION = "useMaterialization";

	public static final String ATTR_USE_PROJECT = "useProject";

	public static final String ATTR_VERSION_OVERRIDE = "versionOverride";

	public static final String ATTR_VERSION_OVERRIDE_TYPE = "versionOverrideType";

	public static final String ATTR_ALLOW_CIRCULAR_DEPENDENCY = "allowCircularDependency";

	public static final String ATTR_WHEN_NOT_EMPTY = "whenNotEmpty";

	public static final String ATTR_USE_RESOLUTION_SCHEME = "useResolutionSchema";

	public static final String ATTR_SYSTEM_DISCOVERY = "systemDiscovery";

	public static final String ATTR_BRANCH_TAG_PATH = "branchTagPath";

	public static final String ATTR_SPACE_PATH = "spacePath";

	public static final String ATTR_REVISION = "revision";

	public static final String ATTR_TIMESTAMP = "timestamp";

	public static final String ATTR_RESOLUTION_PRIO = "resolutionPrio";

	public static final String TAG = "advisorNode";

	private final boolean m_allowCircularDependency;

	private final Documentation m_documentation;

	private final List<String> m_attributes;

	private final String m_componentTypeID;

	private final MutableLevel m_mutableLevel;

	private final Pattern m_namePattern;

	private final URL m_overlayFolder;

	private final Map<String, String> m_properties;

	private final boolean m_prune;

	private final boolean m_skipComponent;

	private final SourceLevel m_sourceLevel;

	private final boolean m_useInstalled;

	private final boolean m_useMaterialization;

	private final boolean m_useProject;

	private final IVersionDesignator m_versionOverride;

	private final boolean m_useResolutionScheme;

	private final boolean m_systemDiscovery;

	private final VersionSelector[] m_branchTagPath;

	private final String[] m_spacePath;

	private final long m_revision;

	private final Date m_timestamp;

	private final int[] m_resolutionPrio;

	public AdvisorNode(AdvisorNodeBuilder bld)
	{
		m_documentation = bld.getDocumentation();
		m_allowCircularDependency = bld.allowCircularDependency();
		m_namePattern = bld.getNamePattern();
		m_componentTypeID = bld.getComponentTypeID();
		m_overlayFolder = bld.getOverlayFolder();
		m_prune = bld.isPrune();
		m_mutableLevel = bld.getMutableLevel();
		m_sourceLevel = bld.getSourceLevel();
		m_skipComponent = bld.skipComponent();
		m_useInstalled = bld.useInstalled();
		m_useMaterialization = bld.useMaterialization();
		m_useProject = bld.useProject();
		m_versionOverride = bld.getVersionOverride();
		m_useResolutionScheme = bld.isUseResolutionScheme();
		m_systemDiscovery = bld.isSystemDiscovery();
		m_branchTagPath = bld.getBranchTagPath();
		m_spacePath = bld.getSpacePath();
		m_revision = bld.getRevision();
		m_timestamp = bld.getTimestamp();
		m_resolutionPrio = bld.getResolutionPrio();
		m_attributes = UUIDKeyed.createUnmodifiableList(bld.getAttributes());
		m_properties = UUIDKeyed.createUnmodifiableProperties(bld.getProperties());
	}

	public boolean allowCircularDependency()
	{
		return m_allowCircularDependency;
	}

	public final List<String> getAttributes()
	{
		return m_attributes;
	}

	public final VersionSelector[] getBranchTagPath()
	{
		return m_branchTagPath;
	}

	public final String getComponentTypeID()
	{
		return m_componentTypeID;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public final MutableLevel getMutableLevel()
	{
		return m_mutableLevel;
	}

	public final Pattern getNamePattern()
	{
		return m_namePattern;
	}

	public URL getOverlayFolder()
	{
		return m_overlayFolder;
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public final String[] getResolutionPath()
	{
		return m_spacePath;
	}

	public int[] getResolutionPrio()
	{
		return m_resolutionPrio;
	}

	public final SourceLevel getSourceLevel()
	{
		return m_sourceLevel;
	}

	public final IVersionDesignator getVersionOverride()
	{
		return m_versionOverride;
	}

	public final boolean isPrune()
	{
		return m_prune;
	}

	public final boolean isSystemDiscovery()
	{
		return m_systemDiscovery;
	}

	public final boolean isUseInstalled()
	{
		return m_useInstalled;
	}

	public final boolean isUseMaterialization()
	{
		return m_useMaterialization;
	}

	public final boolean isUseProject()
	{
		return m_useProject;
	}

	public final boolean isUseResolutionScheme()
	{
		return m_useResolutionScheme;
	}

	public final boolean skipComponent()
	{
		return m_skipComponent;
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_NAME_PATTERN, m_namePattern.toString());
		if(m_overlayFolder != null)
			Utils.addAttribute(attrs, ATTR_OVERLAY_FOLDER, m_overlayFolder.toString());
		if(m_componentTypeID != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentTypeID);
		if(m_mutableLevel != MutableLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_MUTABLE_LEVEL, m_mutableLevel.name());
		if(m_sourceLevel != SourceLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_SOURCE_LEVEL, m_sourceLevel.name());
		if(m_skipComponent)
			Utils.addAttribute(attrs, ATTR_SKIP_COMPONENT, "true");
		if(m_allowCircularDependency)
			Utils.addAttribute(attrs, ATTR_ALLOW_CIRCULAR_DEPENDENCY, "true");
		if(!m_useInstalled)
			Utils.addAttribute(attrs, ATTR_USE_INSTALLED, "false");
		if(!m_useMaterialization)
			Utils.addAttribute(attrs, ATTR_USE_MATERIALIZATION, "false");
		if(!m_useProject)
			Utils.addAttribute(attrs, ATTR_USE_PROJECT, "false");

		if(m_versionOverride != null)
		{
			Utils.addAttribute(attrs, ATTR_VERSION_OVERRIDE, m_versionOverride.toString());
			Utils.addAttribute(attrs, ATTR_VERSION_OVERRIDE_TYPE, m_versionOverride.getVersion().getType().getId());
		}
		String tmp = TextUtils.concat(m_attributes, ",");
		if(tmp != null)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, tmp);
		if(m_prune)
			Utils.addAttribute(attrs, ATTR_PRUNE, "true");
		if(!m_useResolutionScheme)
			Utils.addAttribute(attrs, ATTR_USE_RESOLUTION_SCHEME, "false");
		if(!m_systemDiscovery)
			Utils.addAttribute(attrs, ATTR_SYSTEM_DISCOVERY, "false");

		tmp = VersionSelector.toString(m_branchTagPath);
		if(tmp != null)
			Utils.addAttribute(attrs, ATTR_BRANCH_TAG_PATH, tmp);

		tmp = TextUtils.concat(m_spacePath, ",");
		if(tmp != null)
			Utils.addAttribute(attrs, ATTR_SPACE_PATH, tmp);

		if(!Arrays.equals(m_resolutionPrio, DEFAULT_RESOLUTION_PRIO))
		{
			StringBuilder bld = new StringBuilder();
			bld.append(m_resolutionPrio[0]);
			for(int idx = 1; idx < m_resolutionPrio.length; ++idx)
			{
				bld.append(',');
				bld.append(m_resolutionPrio[idx]);
			}
			Utils.addAttribute(attrs, ATTR_RESOLUTION_PRIO, bld.toString());
		}

		if(m_revision != -1)
			Utils.addAttribute(attrs, ATTR_REVISION, Long.toString(m_revision));

		if(m_timestamp != null)
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(m_timestamp));

		handler.startElement(namespace, localName, qName, attrs);
		if(m_documentation != null)
			m_documentation.toSax(handler, namespace, prefix, m_documentation.getDefaultTag());
		SAXEmitter.emitProperties(handler, m_properties, namespace, prefix, true, false);
		handler.endElement(namespace, localName, qName);
	}

	public final boolean useInstalled()
	{
		return m_useInstalled;
	}

	public final boolean useMaterialization()
	{
		return m_useMaterialization;
	}

	public final boolean useProject()
	{
		return m_useProject;
	}

	public String[] getSpacePath()
	{
		return m_spacePath;
	}

	public long getRevision()
	{
		return m_revision;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}
}
