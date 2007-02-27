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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.NotEmptyAction;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.core.runtime.IPath;


public class AdvisorNodeBuilder
{
	private boolean m_allowCircularDependency;

	private final ArrayList<String> m_attributes = new ArrayList<String>();

	private String m_category;

	private Documentation m_documentation;

	private MutableLevel m_mutableLevel;

	private Pattern m_namePattern;

	private URL m_overlayFolder;

	private Map<String,String> m_properties;

	private boolean m_prune;

	private Pattern m_replaceFrom;

	private String m_replaceTo;

	private boolean m_skipComponent;

	private SourceLevel m_sourceLevel;

	private boolean m_useInstalled;

	private boolean m_useMaterialization;

	private boolean m_useProject;

	private IVersionDesignator m_versionOverride;

	private NotEmptyAction m_whenNotEmpty;

	private boolean m_useResolutionSchema;

	private boolean m_systemDiscovery;
	
	private String m_branch;
	
	private String m_resolutionPath;

	public AdvisorNodeBuilder()
	{
		this.clear();
	}

	public void addAttribute(String attribute)
	{
		m_attributes.add(attribute);
	}

	public void addAttributes(List<String> attributes)
	{
		m_attributes.addAll(attributes);
	}

	public boolean allowCircularDependency()
	{
		return m_allowCircularDependency;
	}

	public void clear()
	{
		m_attributes.clear();
		m_properties = null;
		m_documentation = null;
		m_category = null;
		m_mutableLevel = MutableLevel.INDIFFERENT;
		m_namePattern = null;
		m_overlayFolder = null;
		m_prune = false;
		m_replaceFrom = null;
		m_replaceTo = null;
		m_skipComponent = false;
		m_allowCircularDependency = false;
		m_sourceLevel = SourceLevel.INDIFFERENT;
		m_useInstalled = true;
		m_useMaterialization = true;
		m_useProject = true;
		m_versionOverride = null;
		m_whenNotEmpty = NotEmptyAction.FAIL;
		m_useResolutionSchema = true;
		m_systemDiscovery = true;
		m_branch = null;
		m_resolutionPath = null;
	}

	public AdvisorNode create()
	{
		return new AdvisorNode(m_documentation, m_allowCircularDependency, m_attributes, m_category, m_mutableLevel,
				m_namePattern, m_overlayFolder, m_properties, m_prune,
				m_replaceFrom, m_replaceTo, m_skipComponent,
				m_sourceLevel, m_useInstalled, m_useMaterialization,
				m_useProject, m_versionOverride, m_whenNotEmpty,
				m_useResolutionSchema, m_systemDiscovery, m_branch, m_resolutionPath);
	}
	
	public List<String> getAttributes()
	{
		return m_attributes;
	}

	public String getCategory()
	{
		return m_category;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public IPath getMaterializationLocation(String projectName)
	{
		// TODO: Implement this
		return null;
	}

	public MutableLevel getMutableLevel()
	{
		return m_mutableLevel;
	}

	public Pattern getNamePattern()
	{
		return m_namePattern;
	}

	public URL getOverlayFolder()
	{
		return m_overlayFolder;
	}

	public Map<String,String> getProperties()
	{
		if(m_properties == null)
			m_properties = new HashMap<String,String>();
		return m_properties;
	}

	public Pattern getReplaceFrom()
	{
		return m_replaceFrom;
	}

	public String getReplaceTo()
	{
		return m_replaceTo;
	}

	public SourceLevel getSourceLevel()
	{
		return m_sourceLevel;
	}

	public IVersionDesignator getVersionOverride()
	{
		return m_versionOverride;
	}

	public NotEmptyAction getWhenNotEmpty()
	{
		return m_whenNotEmpty;
	}

	public void initFrom(AdvisorNode node)
	{
		this.clear();
		m_allowCircularDependency = node.allowCircularDependency();
		m_attributes.addAll(node.getAttributes());
		m_category = node.getCategory();
		m_documentation = node.getDocumentation();
		m_mutableLevel = node.getMutableLevel();
		m_namePattern = node.getNamePattern();
		m_overlayFolder = node.getOverlayFolder();
		Map<String,String> props = node.getProperties();
		if(props.size() > 0)
			m_properties = new HashMap<String,String>(props);
		m_prune = node.isPrune();
		m_replaceFrom = node.getReplaceFrom();
		m_replaceTo = node.getReplaceTo();
		m_skipComponent = node.skipComponent();
		m_sourceLevel = node.getSourceLevel();
		m_useInstalled = node.isUseInstalled();
		m_useMaterialization = node.isUseMaterialization();
		m_useProject = node.isUseProject();
		m_versionOverride = node.getVersionOverride();
		m_whenNotEmpty = node.getWhenNotEmpty();
		m_useResolutionSchema = node.isUseResolutionSchema();
		m_systemDiscovery = node.isSystemDiscovery();
		m_branch = node.getBranch();
		m_resolutionPath = node.getResolutionPath();
	}

	public boolean isPrune()
	{
		return m_prune;
	}

	public boolean isUseInstalled()
	{
		return m_useInstalled;
	}

	public boolean isUseMaterialization()
	{
		return m_useMaterialization;
	}

	public boolean isUseProject()
	{
		return m_useProject;
	}

	public boolean isUseResolutionSchema()
	{
		return m_useResolutionSchema;
	}
	
	public boolean isSystemDiscovery()
	{
		return m_systemDiscovery;
	}
	
	public String getBranch()
	{
		return m_branch;
	}
	
	public String getResolutionPath()
	{
		return m_resolutionPath;
	}
	
	public void setAllowCircularDependency(boolean allowCircularDependency)
	{
		m_allowCircularDependency = allowCircularDependency;
	}

	public void setCategory(String category)
	{
		m_category = category;
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public void setMutableLevel(MutableLevel mutableLevel)
	{
		m_mutableLevel = mutableLevel == null ? MutableLevel.INDIFFERENT : mutableLevel;
	}

	public void setNamePattern(Pattern namePattern)
	{
		m_namePattern = namePattern;
	}

	public void setOverlayFolder(URL addOnFolder)
	{
		m_overlayFolder = addOnFolder;
	}

	public void setPrune(boolean prune)
	{
		m_prune = prune;
	}

	public void setReplaceFrom(Pattern replaceFrom)
	{
		m_replaceFrom = replaceFrom;
	}

	public void setReplaceTo(String replaceTo)
	{
		m_replaceTo = replaceTo;
	}

	public void setSkipComponent(boolean skipComponent)
	{
		m_skipComponent = skipComponent;
	}

	public void setSourceLevel(SourceLevel sourceLevel)
	{
		m_sourceLevel = sourceLevel == null ? SourceLevel.INDIFFERENT : sourceLevel;
	}

	public void setUseInstalled(boolean useInstalled)
	{
		m_useInstalled = useInstalled;
	}
	public void setUseMaterialization(boolean useMaterialization)
	{
		m_useMaterialization = useMaterialization;
	}

	public void setUseProject(boolean useProject)
	{
		m_useProject = useProject;
	}

	public void setVersionOverride(IVersionDesignator versionOverride)
	{
		m_versionOverride = versionOverride;
	}

	public void setWhenNotEmpty(NotEmptyAction whenNotEmpty)
	{
		m_whenNotEmpty = whenNotEmpty == null ? NotEmptyAction.FAIL : whenNotEmpty;
	}

	public void setUseResolutionSchema(boolean useResolutionSchema)
	{
		m_useResolutionSchema = useResolutionSchema;
	}
	
	public void setSystemDiscovery(boolean systemDiscovery)
	{
		m_systemDiscovery = systemDiscovery;
	}
	
	public void setBranch(String branch)
	{
		m_branch = branch;
	}
	
	public void setResolutionPath(String resolutionPath)
	{
		m_resolutionPath = resolutionPath;
	}
	
	public boolean skipComponent()
	{
		return m_skipComponent;
	}

	public boolean useInstalled()
	{
		return m_useInstalled;
	}

	public boolean useMaterialization()
	{
		return m_useMaterialization;
	}

	public boolean useProject()
	{
		return m_useProject;
	}

	public NotEmptyAction whenNotEmpty()
	{
		return m_whenNotEmpty;
	}
}
