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
	 * Returns true if this is the default version.
	 */
	boolean isDefault();

	/**
	 * Returns the type for this version.
	 */
	IVersionType getType();

	/**
	 * Returns this version as a long integer if possible. This method is intended
	 * for versions that reflects timestamps or changenumbers.
	 *
	 * @return The version as a long integer or -1 if not possible.
	 */
	long toLong();

	/**
	 * Prints the version on a StringBuilder
	 * @param builder the builder that receives the version
	 */
	void toString(StringBuilder bld);
}
