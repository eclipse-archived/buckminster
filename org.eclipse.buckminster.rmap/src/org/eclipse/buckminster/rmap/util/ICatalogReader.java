/*******************************************************************************
 * Copyright (c) 2004, 2008
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.rmap.util;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A catalog reader knows how to read individual files from a component stored
 * at a some arbitrary location.
 * 
 * @author Thomas Hallgren
 */
public interface ICatalogReader extends IComponentReader {
	/**
	 * Returns <code>true</code> if a file with the given name exists within the
	 * component that this reader is associated with.
	 * 
	 * @param fileName
	 * @param monitor
	 *            The progress monitor.
	 * @return <code>true</code> if the file exists.
	 * @throws CoreException
	 */
	boolean exists(String fileName, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns a list of strings representing what is found at the root of this
	 * reader. Folders with end with a slash.
	 * 
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	List<String> list(IProgressMonitor monitor) throws CoreException;

	/**
	 * Materialize the catalog to the given <code>destination</code>
	 * 
	 * @param destination
	 *            The destination for the materialization
	 * @param monitor
	 *            The progress monitor.
	 * @throws CoreException
	 */
	void materialize(IPath destination, IProgressMonitor monitor) throws CoreException;

	/**
	 * Read a file and send the result to the <code>consumer</code>.
	 * 
	 * @param fileName
	 *            The name of the file relative to the root of this reader.
	 * @param consumer
	 *            The consumer that will be handed the InputStream.
	 * @param monitor
	 *            The progress monitor.
	 * @return The value returned by the consumer.
	 * @throws CoreException
	 */
	<T> T readFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException;
}
