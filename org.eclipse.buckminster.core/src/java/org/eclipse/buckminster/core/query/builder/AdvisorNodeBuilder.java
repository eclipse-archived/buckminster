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
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.IPath;

public class AdvisorNodeBuilder
{
	private boolean m_allowCircularDependency;

	private final ArrayList<String> m_attributes = new ArrayList<String>();

	private VersionSelector[] m_branchTagPath;

	private String m_category;

	private Documentation m_documentation;

	private MutableLevel m_mutableLevel;

	private Pattern m_namePattern;

	private URL m_overlayFolder;

	private Map<String, String> m_properties;

	private boolean m_prune;

	private int[] m_resolutionPrio;

	private long m_revision;

	private boolean m_skipComponent;

	private SourceLevel m_sourceLevel;

	private String[] m_spacePath;

	private boolean m_systemDiscovery;

	private Date m_timestamp;

	private boolean m_useInstalled;

	private boolean m_useMaterialization;

	private boolean m_useProject;

	private boolean m_useResolutionScheme;

	private IVersionDesignator m_versionOverride;

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
		m_skipComponent = false;
		m_allowCircularDependency = false;
		m_sourceLevel = SourceLevel.INDIFFERENT;
		m_useInstalled = true;
		m_useMaterialization = true;
		m_useProject = true;
		m_versionOverride = null;
		m_useResolutionScheme = true;
		m_systemDiscovery = true;
		m_branchTagPath = VersionSelector.EMPTY_PATH;
		m_spacePath = Trivial.EMPTY_STRING_ARRAY;
		m_revision = -1;
		m_timestamp = null;
		m_resolutionPrio = AdvisorNode.DEFAULT_RESOLUTION_PRIO;
	}

	public AdvisorNode create()
	{
		return new AdvisorNode(this);
	}

	public List<String> getAttributes()
	{
		return m_attributes;
	}

	public VersionSelector[] getBranchTagPath()
	{
		return m_branchTagPath;
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

	public Map<String, String> getProperties()
	{
		if(m_properties == null)
			m_properties = new HashMap<String, String>();
		return m_properties;
	}

	public long getRevision()
	{
		return m_revision;
	}

	public int[] getResolutionPrio()
	{
		return m_resolutionPrio;
	}

	public SourceLevel getSourceLevel()
	{
		return m_sourceLevel;
	}

	public String[] getSpacePath()
	{
		return m_spacePath;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}

	public IVersionDesignator getVersionOverride()
	{
		return m_versionOverride;
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
		Map<String, String> props = node.getProperties();
		if(props.size() > 0)
			m_properties = new HashMap<String, String>(props);
		m_prune = node.isPrune();
		m_skipComponent = node.skipComponent();
		m_sourceLevel = node.getSourceLevel();
		m_useInstalled = node.isUseInstalled();
		m_useMaterialization = node.isUseMaterialization();
		m_useProject = node.isUseProject();
		m_versionOverride = node.getVersionOverride();
		m_useResolutionScheme = node.isUseResolutionScheme();
		m_systemDiscovery = node.isSystemDiscovery();
		m_branchTagPath = node.getBranchTagPath();
		m_spacePath = node.getResolutionPath();
		m_revision = node.getRevision();
		m_timestamp = node.getTimestamp();
		m_resolutionPrio = node.getResolutionPrio();
	}

	public boolean isPrune()
	{
		return m_prune;
	}

	public boolean isSystemDiscovery()
	{
		return m_systemDiscovery;
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

	public boolean isUseResolutionScheme()
	{
		return m_useResolutionScheme;
	}

	public void setAllowCircularDependency(boolean allowCircularDependency)
	{
		m_allowCircularDependency = allowCircularDependency;
	}

	public void setBranchTagPath(VersionSelector[] branchTagPath)
	{
		m_branchTagPath = branchTagPath == null ? VersionSelector.EMPTY_PATH : branchTagPath;
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
		m_mutableLevel = mutableLevel == null
				? MutableLevel.INDIFFERENT
				: mutableLevel;
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

	public void setRevision(long revision)
	{
		m_revision = revision;
	}

	public void setResolutionPrio(int[] resolutionPrio)
	{
		m_resolutionPrio = resolutionPrio;
	}

	public void setSkipComponent(boolean skipComponent)
	{
		m_skipComponent = skipComponent;
	}

	public void setSourceLevel(SourceLevel sourceLevel)
	{
		m_sourceLevel = sourceLevel == null
				? SourceLevel.INDIFFERENT
				: sourceLevel;
	}

	public void setSpacePath(String[] spacePath)
	{
		m_spacePath = spacePath == null ? Trivial.EMPTY_STRING_ARRAY : spacePath;
	}

	public void setSystemDiscovery(boolean systemDiscovery)
	{
		m_systemDiscovery = systemDiscovery;
	}

	public void setTimestamp(Date timestamp)
	{
		m_timestamp = timestamp;
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

	public void setUseResolutionScheme(boolean useResolutionScheme)
	{
		m_useResolutionScheme = useResolutionScheme;
	}

	public void setVersionOverride(IVersionDesignator versionOverride)
	{
		m_versionOverride = versionOverride;
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
}
