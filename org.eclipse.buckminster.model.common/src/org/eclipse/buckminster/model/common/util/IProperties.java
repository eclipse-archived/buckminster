/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.model.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

/**
 * @author Thomas Hallgren
 */
public interface IProperties<T> extends Map<String, T> {
	/**
	 * Returns the set of keys that are immutable in this map. If the
	 * implementation doesn't support immutable keys, it will return an empty
	 * set.
	 * 
	 * @return A set of immutable keys.
	 */
	Set<String> immutableKeySet();

	/**
	 * Returns true if the value stored by key is either mutable or does not
	 * exist.
	 * 
	 * @param key
	 *            The key to check for
	 * @return true if its permitted to change or add this value.
	 */
	boolean isMutable(String key);

	/**
	 * Returns the set of keys that are mutable in this map. If the
	 * implementation doesn't support immutable keys, it will return the full
	 * key set.
	 * 
	 * @return A set of mutable keys.
	 */
	Set<String> mutableKeySet();

	/**
	 * Some property maps are created as an overlay on another map. This method
	 * will return the keys of the overlay only, i.e. the keys from the overaied
	 * map will not be included. If overlay is not supported, this method will
	 * return the full key set.
	 * 
	 * @return A set representing the keys in the overlay.
	 */
	Set<String> overlayKeySet();

/**
	 * Just like {@link java.util.Map#put(Object,Object) but with an additional argument that defines the mutability of
	 * the added value.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated.
	 * @param value
	 *            value to be associated with the specified key.
	 * @param mutable
	 *            <code>true</code> if the added value should be mutable.
	 * @return previous value associated with specified key, or <tt>null</tt> if there was no mapping for key.
	 * @throws UnsupportedOperationException
	 *             if the <code>mutable</code> flag is <code>false</code> and this map does not support mutability
	 */
	T put(String key, T value, boolean mutable);

	/**
	 * Provided this map supports mutability and holds a value with the name
	 * <code>key</code>, the status of that value will be changed according to
	 * <code>flag</code>.
	 * 
	 * @param key
	 * @param flag
	 * @throws UnsupportedOperationException
	 *             if the <code>mutable</code> flag is <code>false</code> and
	 *             this map does not support mutability
	 */
	void setMutable(String key, boolean flag) throws UnsupportedOperationException;

	/**
	 * Same as
	 * {@link java.lang.Properties#store(java.io.OutputStream, java.lang.String)}
	 * but without escapes for &quot;:&quot;
	 * 
	 * @param theString
	 *            the String to convert.
	 * @boolean escapeSpace true if space should be escaped.
	 */
	void store(OutputStream out, String comments) throws IOException;

	/**
	 * Returns <code>true</code> if this map supports mutability.
	 * 
	 * @return <code>true</code> if this map supports mutability.
	 */
	boolean supportsMutability();
}
