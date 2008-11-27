/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.model;

/**
 * The MutableLevel is used when choosing the {@link IProvider} that will be the best fit for a component requirement.
 * 
 * @author Thomas Hallgren
 */
public enum MutableLevel
{
	/**
	 * Don't care if the provider is mutable or not.
	 */
	INDIFFERENT,

	/**
	 * Reject a mutable provider. This is used when the found component must be stable.
	 */
	REJECT,

	/**
	 * A mutable provider will score higher than an immutable provider. Both are ok.
	 */
	DESIRE,

	/**
	 * A mutable provider will score higher than an immutable provider. Both are ok.
	 */
	AVOID,

	/**
	 * Reject an immutable provider. Typically used when the intention is to modify source.
	 */
	REQUIRE
}
