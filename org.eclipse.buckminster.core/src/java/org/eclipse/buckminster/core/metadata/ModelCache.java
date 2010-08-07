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

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class ModelCache implements IModelCache {
	private Map<String, String> properties;

	private Map<String, PathGroup[]> pathGroupsCache;

	private Map<ComponentRequest, CSpec> cspecCache;

	public ModelCache() {
	}

	public ModelCache(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public synchronized CSpec findCSpec(ICSpecData ownerCSpec, ComponentRequest request) throws CoreException {
		CSpec cspec = null;
		if (cspecCache == null)
			cspecCache = new HashMap<ComponentRequest, CSpec>();
		else
			cspec = cspecCache.get(request);

		if (cspec == null) {
			cspec = WorkspaceInfo.getResolution(request, false).getCSpec();
			cspecCache.put(request, cspec);
		}
		return cspec;
	}

	@Override
	public synchronized Map<String, PathGroup[]> getPathGroupsCache() {
		if (pathGroupsCache == null)
			pathGroupsCache = new HashMap<String, PathGroup[]>();
		return pathGroupsCache;
	}

	@Override
	public synchronized Map<String, String> getProperties() {
		if (properties == null)
			properties = new HashMap<String, String>();
		return properties;
	}
}
