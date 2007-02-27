/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A Component reader knows how to read a component stored at some arbitrary location. An instance
 * of a component reader is always associated with one specific component.
 * @see IReaderType
 * @author thhal
 */
public interface IComponentReader
{
	/**
	 * Returns <code>true</code> if this reader is capable of materializing components.
	 */
	boolean canMaterialize() throws BuckminsterException;

	/**
	 * Free resources used by this connection
	 * @throws BuckminsterException
	 */
	void close();

	/**
	 * Returns the current node query.
	 */
	NodeQuery getNodeQuery();

	/**
	 * Returns the provider match
	 */
	ProviderMatch getProviderMatch();

	/**
	 * Returns the type that this reader belongs to.
	 */
	IReaderType getReaderType();

	/**
	 * Returns the version converter that converts plain versions into fully fledged version
	 * selectors.
	 * @return A version converter.
	 * @throws CoreException
	 */
	IVersionConverter getVersionConverter() throws CoreException;

	/**
	 * Same as {@link #materialize(IPath, IProgressMonitor)} but overlay folders are not considered.
	 * @param destination The destination for the materialization
	 * @param monitor The progress monitor.
	 * @throws CoreException
	 */
	void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException;

	/**
	 * Materialize (copy, fetch, or checkout) the entire repository denoted by this reader into the
	 * specified <code>destination</code>. The implementation must ensure that the
	 * materialization is atomic. This method will take overlay folders specified in the component
	 * query into account.
	 * @param destination The destination for the materialization
	 * @param monitor The progress monitor.
	 * @throws CoreException
	 */
	void materialize(IPath destination, IProgressMonitor monitor) throws CoreException;
}
