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

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
	 * @param monitor The progress monitor.
	 * @return a handle to a file
	 */
	FileHandle getContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException;

	/**
	 * Obtain files from the root directory whos name matches the given pattern.
	 * @param matchPattern
	 * @param monitor
	 * @return A list of files, possibly empty but never null
	 * @throws CoreException
	 * @throws IOException
	 */
	List<FileHandle> getRootFiles(Pattern matchPattern, IProgressMonitor monitor) throws CoreException, IOException;

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
