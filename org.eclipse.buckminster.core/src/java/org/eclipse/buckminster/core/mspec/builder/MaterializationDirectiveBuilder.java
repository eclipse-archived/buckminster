/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.IMaterializationDirective;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 * 
 */
public abstract class MaterializationDirectiveBuilder implements IMaterializationDirective
{
	private Documentation m_documentation;

	private IPath m_installLocation;

	private IPath m_workspaceLocation;

	private String m_materializerID;

	private final HashMap<String, String> m_properties = new HashMap<String, String>();

	private ConflictResolution m_conflictResolution;

	private int m_maxParallelJobs = -1;

	public void clear()
	{
		m_maxParallelJobs = -1;
		m_installLocation = null;
		m_workspaceLocation = null;
		m_materializerID = null;
		m_conflictResolution = null;
		m_documentation = null;
		m_properties.clear();
	}

	public ConflictResolution getConflictResolution()
	{
		return m_conflictResolution;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public IPath getInstallLocation()
	{
		return m_installLocation;
	}

	public String getMaterializerID()
	{
		return m_materializerID;
	}

	public int getMaxParallelJobs()
	{
		return m_maxParallelJobs;
	}

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public IPath getWorkspaceLocation()
	{
		return m_workspaceLocation;
	}

	public void initFrom(IMaterializationDirective md)
	{
		clear();
		m_documentation = md.getDocumentation();
		m_installLocation = md.getInstallLocation();
		m_workspaceLocation = md.getWorkspaceLocation();
		m_materializerID = md.getMaterializerID();
		m_maxParallelJobs = md.getMaxParallelJobs();
		m_conflictResolution = md.getConflictResolution();
		m_properties.putAll(md.getProperties());
	}

	public void setConflictResolution(ConflictResolution whenPresent)
	{
		m_conflictResolution = whenPresent;
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public void setInstallLocation(IPath installLocation)
	{
		m_installLocation = installLocation;
	}

	public void setMaterializerID(String materializerID)
	{
		m_materializerID = materializerID;
	}

	public void setMaxParallelJobs(int maxParallelJobs)
	{
		m_maxParallelJobs = maxParallelJobs;
	}

	public void setWorkspaceLocation(IPath workspaceLocation)
	{
		m_workspaceLocation = workspaceLocation;
	}
}
