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

import org.eclipse.core.runtime.CoreException;

/**
 * The IVersionQuery is used by component readers to select valid component
 * versions.
 * @author Thomas Hallgren
 */
public interface IVersionQuery
{
	/**
	 * Creates a version from the repository selector.
	 */
	IVersion createVersion(IVersionSelector selector) throws CoreException;

	/**
	 * Returns the branch on which this query should be performed in case the
	 * query is of type <code>TAG</code>, <code>TIMESTAMP</code>, or
	 * <code>CHANGE_NUMBER</code>. The branch for a query of type
	 * <code>LATEST</code> must be determined using {@link #getExactMatch()}
	 * or {@link #getFilter()} methods.
	 */
	String getBranch();

	/**
	 * Returns the version converter.
	 */
	IVersionConverter getConverter();

	/**
	 * Returns a version designator that must be matched in order to satisfy a query.
	 */
	IVersionDesignator getDesignator();

	/**
	 * Returns a version selector that must be matched exactly
	 * in order to satisfy a query. The method will return <code>null</code>
	 * if no such match should be performed.
	 * @return A selector that must be matched or <code>null</code> if not
	 *         applicable.
	 * @see #getType()
	 */
	IVersionSelector getExactMatch();

	/**
	 * Returns the type for this query. The type controls the semantics for the
	 * {@link #getExactMatch()}, and {@link #matches()} methods.
	 * @return The type that controls the semantics for the query.
	 */
	VersionSelectorType getType();

	/**
	 * Returns <code>true</code> if <code>version</code> matches this query.
	 * @param version The version to match.
	 * @return <code>true</code> if the version matches.
	 */
	boolean matches(IVersion version);

	/**
	 * Returns <code>true</code> if <code>selector</code> matches this query.
	 * @param selector The selector to match.
	 * @return <code>true</code> if the selector matches.
	 */
	boolean matches(IVersionSelector selector);
}
