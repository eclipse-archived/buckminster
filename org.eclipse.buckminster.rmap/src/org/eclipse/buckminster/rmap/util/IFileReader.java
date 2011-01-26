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
import java.io.InputStream;

import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A file reader can read one single file that constitutes the component.
 * 
 * @author Thomas Hallgren
 */
public interface IFileReader extends IComponentReader {
	/**
	 * Returns true if the artifact appointed by this reader exists
	 */
	boolean exists(IProgressMonitor monitor) throws CoreException, IOException;;

	/**
	 * Returns the info about the file, or null if it's not available.
	 * 
	 * @return file info associated with the stream, or null if no info is
	 *         available
	 */
	IFileInfo getFileInfo();

	/**
	 * Returns the input stream that was created at the call to {@link #open()}
	 */
	InputStream open(IProgressMonitor monitor) throws CoreException, IOException;

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
	<T> T readFile(IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException;
}
