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
 * The SourceLevel is used when choosing the {@link IProvider} that will be the best fit for a component requirement.
 * 
 * @author Thomas Hallgren
 */
public enum SourceLevel
{
	/**
	 * Don't care if the provider has source or not.
	 */
	INDIFFERENT,

	/**
	 * Reject a provider that provides source. This is used when only pre-compiled material is desired.
	 */
	REJECT,

	/**
	 * A provider that provides source will score higher than one that does not. Both are ok.
	 */
	DESIRE,

	/**
	 * A provider that provides source will score lower than one that does not. Both are ok.
	 */
	AVOID,

	/**
	 * Reject providers that have no source. Typically used when the intention is to browse or modify source.
	 */
	REQUIRE
}
