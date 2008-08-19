package org.eclipse.buckminster.core.metadata.model;

import java.util.Map;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public interface IModelCache
{
	/**
	 * Returns the CSpec that corresponds to the given request. The method goes
	 * through a cache that is active throughout the invocation.
	 * @param ownerCSpec The cspec where the request was declared
	 * @param request The request to search for
	 * @return The found CSpec.
	 * @throws org.eclipse.buckminster.core.metadata.MissingComponentException If no such component could be found
	 */
	CSpec findCSpec(ICSpecData ownerCSpec, ComponentRequest request) throws CoreException;

	/**
	 * Returns the cache used when evaulating attribute path groups
	 */
	Map<String,PathGroup[]> getPathGroupsCache();

	/**
	 * Returns the invocation properties.
	 * @return The properties for this invocation
	 */
	Map<String, String> getProperties();
}
