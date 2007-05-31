/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ObtainedDependency;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class ModelCache implements IModelCache
{
	private Map<String,String> m_properties;
	private Map<String, PathGroup[]> m_pathGroupsCache;
	private Map<ComponentRequest, CSpec> m_cspecCache;

	public ModelCache()
	{
	}

	public ModelCache(Map<String,String> properties)
	{
		m_properties = properties;
	}

	public synchronized Map<String, PathGroup[]> getPathGroupsCache()
	{
		if(m_pathGroupsCache == null)
			m_pathGroupsCache = new HashMap<String, PathGroup[]>();
		return m_pathGroupsCache;
	}

	public synchronized CSpec findCSpec(CSpec ownerCSpec, ComponentRequest request) throws CoreException
	{
		CSpec cspec = null;
		if(m_cspecCache == null)
			m_cspecCache = new HashMap<ComponentRequest, CSpec>();
		else
			cspec = m_cspecCache.get(request);

		if(cspec == null)
		{
			if(request instanceof ObtainedDependency)
				cspec = ((ObtainedDependency)request).resolveCSpec(ownerCSpec, this);
			else
				cspec = WorkspaceInfo.getResolution(request, false).getCSpec();
			m_cspecCache.put(request, cspec);
		}
		return cspec;
	}

	public synchronized Map<String, String> getProperties()
	{
		if(m_properties == null)
			m_properties = new HashMap<String, String>();
		return m_properties;
	}
}
