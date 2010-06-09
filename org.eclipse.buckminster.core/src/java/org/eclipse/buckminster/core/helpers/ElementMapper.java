/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

/**
 * @author Filip Hrbek Declares a method for mapping SmartArrayList elements in
 *         its map method.
 */
public interface ElementMapper<E, M> {

	/**
	 * Maps an element into another element.
	 * 
	 * @param element
	 * @return mapped element, or null if the element should not be included in
	 *         the result
	 */
	public M mapping(E element) throws MappingException;
}
