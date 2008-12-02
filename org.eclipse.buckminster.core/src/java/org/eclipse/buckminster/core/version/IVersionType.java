/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.IBuckminsterExtension;

public interface IVersionType extends IBuckminsterExtension
{
	public static final String OSGI = "OSGi"; //$NON-NLS-1$

	public static final String STRING = "String"; //$NON-NLS-1$

	public static final String TIMESTAMP = "Timestamp"; //$NON-NLS-1$

	public static final String TRIPLET = "Triplet"; //$NON-NLS-1$

	/**
	 * Coerce the object argument into a version of this type if possible.
	 * 
	 * @param object
	 *            The object to coerce
	 * @return the version that is the result of the coercion or <code>null</code> if coercion could was impossible.
	 */
	IVersion coerce(Object object);

	/**
	 * Parse a version from the given versionString.
	 * 
	 * @param versionString
	 *            The string to parse
	 * @return The parsed version.
	 * @throws VersionSyntaxException
	 */
	IVersion fromString(String versionString) throws VersionSyntaxException;

	/**
	 * Parse a version from the given versionString starting at position startPos. The positition where the parsing
	 * ended will be returned in endPos at index zero.
	 * 
	 * @param versionString
	 *            The string to parse
	 * @param startPos
	 *            Start position for the parse.
	 * @param endPos
	 *            Position of the first character after the parsed version. This position might indicate the end of the
	 *            versionString.
	 * @return The parsed version.
	 * @throws VersionSyntaxException
	 */
	IVersion fromString(String versionString, int startPos, int[] endPos) throws VersionSyntaxException;

	/**
	 * Return true if this type can be compared to the other type
	 */
	boolean isComparableTo(IVersionType other);
}
