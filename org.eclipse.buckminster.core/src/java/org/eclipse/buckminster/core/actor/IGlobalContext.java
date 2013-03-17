/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

public interface IGlobalContext extends IModelCache {
	/**
	 * Adds a generated resolution to the context that will be available
	 * througout the perform call.
	 * 
	 * @param resolution
	 *            The resolution to add
	 * @param location
	 *            The path of the resolution
	 */
	void addGeneratedResolution(Resolution resolution, IPath location);

	/**
	 * Add a new property to the context
	 * 
	 * @param key
	 * @param value
	 */
	void addProperty(String key, Object value);

	/**
	 * Returns the property set to use when performing <code>attribute</code>.
	 * 
	 * @param attribute
	 *            The attribute for which we need the properties
	 * @return The properties to use for attribute.
	 */
	Map<String, Object> getExecutionProperties(Attribute attribute) throws CoreException;

	/**
	 * Obtains a generated materialization from the context for the given
	 * identifier
	 * 
	 * @param cid
	 *            The component identifier
	 * @return A generated materialization or <code>null</code> if no
	 *         materialization for the given cid has been added to this context.
	 */
	Materialization getGeneratedMaterialization(IComponentIdentifier cid);

	/**
	 * Obtains a generated resolution from the context that matches the request
	 * 
	 * @param request
	 *            A request for the resolution
	 * @return A generated resolution or <code>null</code> if no matching
	 *         resolution has been added to this context.
	 */
	Resolution getGeneratedResolution(IComponentRequest request);

	/**
	 * Returns the cache that is in effect for the whole top level invocation
	 * 
	 * @return A cache that various actions can use for arbitrary purposes
	 */
	Map<UUID, Object> getInvocationCache();

	/**
	 * Returns the status of the last perform issued using this context
	 * 
	 * @return The status of the last perform.
	 */
	IStatus getStatus();

	/**
	 * Schedule the removal of <code>path</code> when the top build invocation
	 * ends. The <code>path</code> must be an absolute path.
	 * 
	 * @param path
	 *            The absolute path of the file or directory to remove
	 */
	void scheduleRemoval(IPath path) throws CoreException;
}
