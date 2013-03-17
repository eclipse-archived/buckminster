/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.UUID;

import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.core.runtime.CoreException;

/**
 * Base class for immutable UUID keyed classes. The contract specifies that such
 * a class may only declare immutable (i.e. final) properties since the id of
 * the instance is calculated from the byte array that is the result of a XML
 * serialization.
 * 
 * @author Thomas Hallgren
 */
public interface IUUIDPersisted {
	/**
	 * Returns the id of this keyed instance. The instance will be persisted in
	 * its storage the first time this method is called.
	 * 
	 * @return the id of this resolution
	 */
	UUID getId();

	/**
	 * Returns the byte image (the XML output) that represents this element.
	 */
	byte[] getImage();

	/**
	 * Returns true if this element has been persisted.
	 * 
	 * @throws CoreException
	 */
	boolean isPersisted(StorageManager sm) throws CoreException;

	/**
	 * Remove this instance from persistent storage. Referential integrity is
	 * maintained.
	 */
	void remove(StorageManager sm) throws CoreException;

	/**
	 * Used by the persistent store when it knows the id and image of an element
	 * that it just restored from disk.
	 * 
	 * @param id
	 *            The identifier for the element
	 */
	void setId(UUID id);

	/**
	 * Make sure this instance is stored
	 * 
	 * @throws CoreException
	 */
	void store(StorageManager sm) throws CoreException;
}
