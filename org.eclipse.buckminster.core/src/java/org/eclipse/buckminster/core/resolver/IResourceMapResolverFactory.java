/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.net.URL;

import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Filip Hrbek
 */
public interface IResourceMapResolverFactory extends IResolverFactory
{
	public ResourceMap getResourceMap(ResolutionContext context, URL url) throws CoreException;

	public int getResolverThreadsMax();

	/**
	 * Obtains the {@link #RESOURCE_MAP_URL_PARAM} setting for this factory from the preference store. If not found
	 * there, it defaults to the value set in the extension definition.
	 * 
	 * @return The URL or <code>null</code> if it has not been set.
	 */
	public URL getResourceMapURL() throws CoreException;

	/**
	 * Obtains the {@link #LOCAL_RESOLVE_PARAM} setting for this factory from the preference store. If not found there,
	 * it defaults to the value set in the extension definition.
	 * 
	 * @return <code>true</code>ue if local resolutions should be performed.
	 */
	public boolean isLocalResolve();

	/**
	 * Obtains the {@link #OVERRIDE_QUERY_URL_PARAM} setting for this factory from the preference store. If not found
	 * there, it defaults to the value set in the extension definition.
	 * 
	 * @return the overrideQueryURL
	 */
	public boolean isOverrideQueryURL();
}
