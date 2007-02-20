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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A catalog reader knows how to read individual files from a component stored at a some
 * arbitrary location.
 * @author thhal
 */
public interface ICatalogReader extends IComponentReader
{
	/**
	 * Returns <code>true</code> if a file with the given name exists within
	 * the component that this reader is associated with.
	 * @param fileName
	 * @param monitor The progress monitor.
	 * @return <code>true</code> if the file exists.
	 * @throws CoreException
	 */
	boolean exists(String fileName, IProgressMonitor monitor) throws CoreException;

	/**
	 * Obtain the content of file <code>fileName</code> as a local file. 
	 * @param fileName The file to obtain
	 * @param isTemporary A one element boolean array where the file temporary status is returned
	 * @param monitor The progress monitor.
	 * @return An abstract {@link File} handle and a flag in the <code>isTemporary</code> that denotes if
	 * this file is a permanent file or if it is temporary and should be deleted by the caller.
	 */
	File getContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor) throws CoreException, IOException;

	/**
	 * Read a file and send the result to the <code>consumer</code>.
	 * @param fileName The name of the file relative to the root of this reader.
	 * @param consumer The consumer that will be handed the InputStream.
	 * @param monitor The progress monitor.
	 * @return The value returned by the consumer.
	 * @throws CoreException
	 */
	<T> T readFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException;
}
