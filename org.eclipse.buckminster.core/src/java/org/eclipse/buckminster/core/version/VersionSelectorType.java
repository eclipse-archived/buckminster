/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.version;

/**
 * @author Thomas Hallgren
 */
public enum VersionSelectorType
{
	/**
	 * <code>PLAIN</code> represents a version that is not associated with
	 * a branch or a tag. A provider that represents a source code control
	 * systems will typically use a <code>PLAIN</code> version to
	 * determine valid branches or valid tags on a specific branch. A
	 * CSpec should typcially only contain <code>PLAIN</code> versions.
	 */
	PLAIN,

	/**
	 * <code>TAG</code> represents a specific tag on a branch.
	 */
	TAG,

	/**
	 * <code>LATEST</code> represents the latest revision on a branch.
	 */
	LATEST,

	/**
	 * <code>TIMESTAMP</code> represents a snapshot of a branch on a
	 * specific point in time.
	 */
	TIMESTAMP,

	/**
	 * <code>CHANGE_NUMBER</code> represents a specific change number on a
	 * branch.
	 */
	CHANGE_NUMBER
}
