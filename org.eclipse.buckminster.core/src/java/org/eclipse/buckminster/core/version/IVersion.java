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

public interface IVersion extends Comparable<IVersion>
{
	/**
	 * Returns the version qualifier or null if not applicable
	 */
	String getQualifier();

	/**
	 * Returns the type for this version.
	 */
	IVersionType getType();

	/**
	 * Creates and returns a new version where the qualifier has been replaced. If the old version did not have a
	 * qualifier, the new qualifier will be added. If the version is of a kind that does not support qualifiers, a call
	 * to this method will simply return the called instance.
	 * 
	 * @param string
	 *            The new qualifier
	 * @return A new version with the new qualifier
	 */
	IVersion replaceQualifier(String string);

	/**
	 * Returns this version as a long integer if possible. This method is intended for versions that reflects timestamps
	 * or changenumbers.
	 * 
	 * @return The version as a long integer or -1 if not possible.
	 */
	long toLong();

	/**
	 * Prints the version on a StringBuilder
	 * 
	 * @param builder
	 *            the builder that receives the version
	 */
	void toString(StringBuilder bld);

	/**
	 * Returns <code>true</code> if this version is equal to <code>version</code> in all but
	 * the version qualifier.
	 * 
	 * @param version
	 * @return true when the versions are equal irrespective of qualifiers
	 */
	boolean equalsUnqualified(IVersion version);
}
