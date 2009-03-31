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
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;

@SuppressWarnings("restriction")
public class AdvisorNodeBuilder implements IAdvisorNode
{
	private boolean m_allowCircularDependency;

	private final ArrayList<String> m_attributes = new ArrayList<String>();

	private VersionSelector[] m_branchTagPath;

	private String m_componentType;

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

	private boolean m_systemDiscovery;

	private Date m_timestamp;

	private boolean m_useTargetPlatform;

	private boolean m_useMaterialization;

	private boolean m_useWorkspace;

	private boolean m_useRemoteResolution;

	private VersionRange m_versionOverride;

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
		m_componentType = null;
		m_mutableLevel = MutableLevel.INDIFFERENT;
		m_namePattern = null;
		m_overlayFolder = null;
		m_prune = false;
		m_skipComponent = false;
		m_allowCircularDependency = false;
		m_sourceLevel = SourceLevel.INDIFFERENT;
		m_useTargetPlatform = true;
		m_useMaterialization = true;
		m_useWorkspace = true;
		m_versionOverride = null;
		m_useRemoteResolution = true;
		m_systemDiscovery = true;
		m_branchTagPath = VersionSelector.EMPTY_PATH;
		m_revision = -1;
		m_timestamp = null;
		m_resolutionPrio = IAdvisorNode.DEFAULT_RESOLUTION_PRIO;
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

	public String getComponentTypeID()
	{
		return m_componentType;
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

	public int[] getResolutionPrio()
	{
		return m_resolutionPrio;
	}

	public long getRevision()
	{
		return m_revision;
	}

	public SourceLevel getSourceLevel()
	{
		return m_sourceLevel;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}

	public VersionRange getVersionOverride()
	{
		return m_versionOverride;
	}

	public void initFrom(IAdvisorNode node)
	{
		this.clear();
		m_allowCircularDependency = node.allowCircularDependency();
		m_attributes.addAll(node.getAttributes());
		m_componentType = node.getComponentTypeID();
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
		m_useMaterialization = node.isUseMaterialization();
		m_useRemoteResolution = node.isUseRemoteResolution();
		m_useTargetPlatform = node.isUseTargetPlatform();
		m_useWorkspace = node.isUseWorkspace();
		m_versionOverride = node.getVersionOverride();
		m_systemDiscovery = node.isSystemDiscovery();
		m_branchTagPath = node.getBranchTagPath();
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

	public boolean isUseMaterialization()
	{
		return m_useMaterialization;
	}

	public boolean isUseRemoteResolution()
	{
		return m_useRemoteResolution;
	}

	public boolean isUseTargetPlatform()
	{
		return m_useTargetPlatform;
	}

	public boolean isUseWorkspace()
	{
		return m_useWorkspace;
	}

	public void setAllowCircularDependency(boolean allowCircularDependency)
	{
		m_allowCircularDependency = allowCircularDependency;
	}

	public void setBranchTagPath(VersionSelector[] branchTagPath)
	{
		m_branchTagPath = branchTagPath == null
				? VersionSelector.EMPTY_PATH
				: branchTagPath;
	}

	public void setComponentTypeID(String componentType)
	{
		m_componentType = componentType;
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

	public void setResolutionPrio(int[] resolutionPrio)
	{
		m_resolutionPrio = resolutionPrio;
	}

	public void setRevision(long revision)
	{
		m_revision = revision;
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

	public void setSystemDiscovery(boolean systemDiscovery)
	{
		m_systemDiscovery = systemDiscovery;
	}

	public void setTimestamp(Date timestamp)
	{
		m_timestamp = timestamp;
	}

	public void setUseMaterialization(boolean useMaterialization)
	{
		m_useMaterialization = useMaterialization;
	}

	public void setUseRemoteResolution(boolean useRemoteResolution)
	{
		m_useRemoteResolution = useRemoteResolution;
	}

	public void setUseTargetPlatform(boolean useInstalled)
	{
		m_useTargetPlatform = useInstalled;
	}

	public void setUseWorkspace(boolean useProject)
	{
		m_useWorkspace = useProject;
	}

	public void setVersionOverride(VersionRange versionOverride)
	{
		m_versionOverride = versionOverride;
	}

	public boolean skipComponent()
	{
		return m_skipComponent;
	}
}
