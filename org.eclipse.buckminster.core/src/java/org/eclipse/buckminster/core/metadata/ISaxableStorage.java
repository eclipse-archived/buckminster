/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.List;
import java.util.UUID;

import org.eclipse.buckminster.core.metadata.model.ElementNotFoundException;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public interface ISaxableStorage<T extends IUUIDKeyed>
{
	/**
	 * Checks if this storage contains the given element
	 * @return true if the element is found in this storage
	 */
	boolean contains(T element) throws CoreException;

	/**
	 * Returns the name of this storage.
	 * @return The name that identifies this storage.
	 */
	String getName();

	/**
	 * Drop all entries from this storage and commit the change
	 * immediately.
	 */
	void clear();

	/**
	 * Returns true if the sequence number of this storage changed since
	 * it was last used. The sequence number can be regarded as a
	 * sequential version number.
	 * @return true if the sequence number changed
	 */
	boolean sequenceChanged();

	/**
	 * Obtains an array of the key of all instances in this storage.
	 * @return The array of all id's known to this storage.
	 */
	public UUID[] getKeys();

	/**
	 * Return the timestamp denoting the time when the element identified with
	 * <code>elementId</code> was created.
	 * @param elementId The id to search for.
	 * @return The found element or <code>null</code> if no such element exists.
	 * @throws CoreException
	 */
	long getCreationTime(UUID elementId) throws CoreException;

	/**
	 * Return the element that corresponds to <code>elementId</code>.
	 * an exception.
	 * @param elementId The id to search for.
	 * @return The found element.
	 * @throws ElementNotFoundException If no such element can be found
	 * @throws CoreException
	 */
	T getElement(UUID elementId) throws CoreException, ElementNotFoundException;

	/**
	 * Returns all elements in this storage
	 * @return The element array.
	 * @throws CoreException
	 */
	T[] getElements() throws CoreException;

	/**
	 * Obtains an array of all the timestamped key for all instances in this storage.
	 * @return The timestamped key array. It might be empty but never <code>null</code>.
	 * @throws CoreException
	 */
	TimestampedKey[] getTimestampedKeys();

	/**
	 * Find all instances that has a property named
	 * <code>keyName</code> that appoints the <code>foreignKey</code>.
	 * Analog to a SQL statement like:<br/>
	 * <code>SELECT id FROM storage WHERE keyName = foreignKey</code>
	 * @param foreignKey The desired value for the property
	 * @param keyName The name of the property, case insensitive
	 * @return A list of ID's. Might be empty but never <code>null</code>
	 */
	List<UUID> getReferencingKeys(UUID foreignKey, String keyName) throws CoreException;

	/**
	 * Store an element in this storage.
	 * @param element The element to store.
	 * @return The id that identifies the element in this storage.
	 * @throws CoreException
	 */
	void putElement(T element) throws CoreException;

	/**
	 * Store an element in this storage under a specific id.
	 * @param id The id of the element.
	 * @param element The element to store.
	 * @return The id that identifies the element in this storage.
	 * @throws CoreException
	 */
	void putElement(UUID id, T element) throws CoreException;

	/**
	 * Remove the element identified with <code>elementId</code> from
	 * this storage.
	 * @param elementId The id of the element to be removed.
	 */
	void removeElement(UUID elementId) throws CoreException;
}
