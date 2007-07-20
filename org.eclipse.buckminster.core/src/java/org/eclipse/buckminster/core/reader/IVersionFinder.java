/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A ITagFinder will find the component tags that matches a certain query.
 * 
 * @author Thomas Hallgren
 */
public interface IVersionFinder {
	/**
	 * Find the best match for the designated component
	 * 
	 * @param monitor
	 * @return The match that best matched the conditions or null if no match
	 *         was found
	 * @throws CoreException
	 */
	VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the provider associated with this version finder
	 */
	Provider getProvider();

	/**
	 * Returns the provider that can materialize a <code>versionMatch</code>.
	 * The <code>versionMatch</code> must be produced by this finder.
	 * @return The provider
	 */
	ProviderMatch getProviderMatch(VersionMatch versionMatch, IComponentType ctype, ProviderScore providerScore) throws CoreException;

	/**
	 * Returns the query associated with this version finder
	 */
	NodeQuery getQuery();

	/**
	 * Closes all resources utilized by this instance.
	 */
	void close();
}
