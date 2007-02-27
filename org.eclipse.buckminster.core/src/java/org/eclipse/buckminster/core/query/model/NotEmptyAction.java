/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.model;

/**
 * An enum that describes what to do when content exists
 * at the location where the component should be materialized.
 * 
 * @author Thomas Hallgren
 */
public enum NotEmptyAction
{
	/**
	 * Fail with an exception.
	 */
	FAIL,

	/**
	 * Clear the location prior to materializing.
	 */
	OVERWRITE,

	/**
	 * Reuse the content at the location and don't
	 * perform a new materialization.
	 */
	REUSE
}
