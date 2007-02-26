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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class AdvisorNode implements ISaxableElement, Cloneable
{
	public static final String ATTR_ATTRIBUTES = "attributes";

	public static final String ATTR_CATEGORY = "category";

	public static final String ATTR_MUTABLE_LEVEL = "mutableLevel";

	public static final String ATTR_NAME_PATTERN = "namePattern";

	public static final String ATTR_OVERLAY_FOLDER = "overlayFolder";

	public static final String ATTR_PRUNE = "prune";

	public static final String ATTR_REPLACE_FROM = "replaceFrom";

	public static final String ATTR_REPLACE_TO = "replaceTo";

	public static final String ATTR_SKIP_COMPONENT = "skipComponent";

	public static final String ATTR_SOURCE_LEVEL = "sourceLevel";

	public static final String ATTR_USE_INSTALLED = "useInstalled";

	public static final String ATTR_USE_MATERIALIZATION = "useMaterialization";

	public static final String ATTR_USE_PROJECT = "useProject";

	public static final String ATTR_VERSION_OVERRIDE = "versionOverride";

	public static final String ATTR_VERSION_OVERRIDE_TYPE = "versionOverrideType";

	public static final String ATTR_ALLOW_CIRCULAR_DEPENDENCY = "allowCircularDependency";

	public static final String ATTR_WHEN_NOT_EMPTY = "whenNotEmpty";
	
	public static final String ATTR_USE_RESOLUTION_SCHEMA = "useResolutionSchema";

	public static final String ATTR_SYSTEM_DISCOVERY = "systemDiscovery";

	public static final String ATTR_BRANCH = "branch";

	public static final String ATTR_RESOLUTION_PATH = "resolutionPath";

	public static final String TAG = "advisorNode";

	private final boolean m_allowCircularDependency;

	private final Documentation m_documentation;

	private final List<String> m_attributes;

	private final String m_category;

	private final MutableLevel m_mutableLevel;

	private final Pattern m_namePattern;

	private final URL m_overlayFolder;

	private final Map<String, String> m_properties;

	private final boolean m_prune;

	private final Pattern m_replaceFrom;

	private final String m_replaceTo;

	private final boolean m_skipComponent;

	private final SourceLevel m_sourceLevel;

	private final boolean m_useInstalled;

	private final boolean m_useMaterialization;

	private final boolean m_useProject;

	private final IVersionDesignator m_versionOverride;

	private final NotEmptyAction m_whenNotEmpty;
	
	private final boolean m_useResolutionSchema;

	private final boolean m_systemDiscovery;
	
	private final String m_branch;
	
	private final String m_resolutionPath;

	public AdvisorNode(Documentation documentation, boolean allowCircularDependency, List<String> attributes, String category, MutableLevel mutableLevel, Pattern namePattern,
			URL overlayFolder, Map<String, String> properties, boolean prune, Pattern replaceFrom, String replaceTo,
			boolean skipComponent, SourceLevel sourceLevel, boolean useInstalled, boolean useMaterialization,
			boolean useProject, IVersionDesignator versionOverride, NotEmptyAction notEmptyAction,
			boolean useResolutionSchema, boolean systemDiscovery, String branch, String resolutionPath)
	{
		m_documentation = documentation;
		m_allowCircularDependency = allowCircularDependency;
		m_namePattern = namePattern;
		m_category = category;
		m_overlayFolder = overlayFolder;
		m_prune = prune;
		m_mutableLevel = mutableLevel;
		m_sourceLevel = sourceLevel;
		m_whenNotEmpty = notEmptyAction;
		m_replaceFrom = replaceFrom;
		m_replaceTo = replaceTo;
		m_skipComponent = skipComponent;
		m_useInstalled = useInstalled;
		m_useMaterialization = useMaterialization;
		m_useProject = useProject;
		m_versionOverride = versionOverride;
		m_useResolutionSchema = useResolutionSchema;
		m_systemDiscovery = systemDiscovery;
		m_branch = branch;
		m_resolutionPath = resolutionPath;

		if(attributes == null || attributes.size() == 0)
			m_attributes = Collections.emptyList();
		else
			m_attributes = Collections.unmodifiableList(new ArrayList<String>(attributes));

		if(properties == null || properties.size() == 0)
			m_properties = Collections.emptyMap();
		else
			m_properties = Collections.unmodifiableMap(new ExpandingProperties(properties));
	}

	public boolean allowCircularDependency()
	{
		return m_allowCircularDependency;
	}

	public final List<String> getAttributes()
	{
		return m_attributes;
	}

	public final String getCategory()
	{
		return m_category;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public final IPath getMaterializationLocation(String projectName)
	{
		// TODO: Implement this
		return null;
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

	public final Pattern getReplaceFrom()
	{
		return m_replaceFrom;
	}

	public final String getReplaceTo()
	{
		return m_replaceTo;
	}

	public final SourceLevel getSourceLevel()
	{
		return m_sourceLevel;
	}

	public final IVersionDesignator getVersionOverride()
	{
		return m_versionOverride;
	}

	public final NotEmptyAction getWhenNotEmpty()
	{
		return m_whenNotEmpty;
	}

	public final boolean isPrune()
	{
		return m_prune;
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
		if(m_category != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, m_category);
		if(m_mutableLevel != MutableLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_MUTABLE_LEVEL, m_mutableLevel.name());
		if(m_sourceLevel != SourceLevel.INDIFFERENT)
			Utils.addAttribute(attrs, ATTR_SOURCE_LEVEL, m_sourceLevel.name());
		if(m_whenNotEmpty != NotEmptyAction.FAIL)
			Utils.addAttribute(attrs, ATTR_WHEN_NOT_EMPTY, m_whenNotEmpty.name());
		if(m_skipComponent)
			Utils.addAttribute(attrs, ATTR_SKIP_COMPONENT, "true");
		if(m_allowCircularDependency)
			Utils.addAttribute(attrs, ATTR_ALLOW_CIRCULAR_DEPENDENCY, "true");
		if(m_replaceFrom != null)
			Utils.addAttribute(attrs, ATTR_REPLACE_FROM, m_replaceFrom.toString());
		if(m_replaceTo != null)
			Utils.addAttribute(attrs, ATTR_REPLACE_TO, m_replaceTo);
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
		if(m_attributes.size() > 0)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, TextUtils.toCommaSeparatedList(m_attributes));
		if(m_prune)
			Utils.addAttribute(attrs, ATTR_PRUNE, "true");
		if(!m_useResolutionSchema)
			Utils.addAttribute(attrs, ATTR_USE_RESOLUTION_SCHEMA, "false");
		if(!m_systemDiscovery)
			Utils.addAttribute(attrs, ATTR_SYSTEM_DISCOVERY, "false");
		if(m_branch != null)
			Utils.addAttribute(attrs, ATTR_BRANCH, m_branch);
		if(m_resolutionPath != null)
			Utils.addAttribute(attrs, ATTR_RESOLUTION_PATH, m_resolutionPath);
			
			
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

	public final NotEmptyAction whenNotEmpty()
	{
		return m_whenNotEmpty;
	}
	
	public final boolean isUseResolutionSchema()
	{
		return m_useResolutionSchema;
	}
	
	public final boolean isSystemDiscovery()
	{
		return m_systemDiscovery;
	}
	
	public final String getBranch()
	{
		return m_branch;
	}
	
	public final String getResolutionPath()
	{
		return m_resolutionPath;
	}
}
