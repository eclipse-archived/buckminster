/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 * @author Guillaume CHATELET
 */
public interface IExpander {
	/**
	 * Expands the content read from the <code>input</code> stream into a folder
	 * structure rooted at <code>finalLocation</code>. The finalLocation can be
	 * null to indicate a dry run that just verifies the consistency of the
	 * <code>input</code>.
	 * 
	 * @param input
	 *            The stream to expand into folders and files
	 * @param finalLocation
	 *            The root location for the folder structure or
	 *            <code>null</code> for verification only
	 * @param monitor
	 *            The progress monitor. May be <code>null</code>.
	 * @throws IOException
	 * @throws CoreException
	 */
	void expand(InputStream input, File finalLocation, IProgressMonitor monitor) throws IOException, CoreException;

	/**
	 * Specify a filter to include/exclude patterns of filename to extract.
	 * 
	 * @param filter
	 *            null means no filtering.
	 */
	void setFilter(FileFilter filter);

	/**
	 * Specify whether the folder hierarchy within the archive should be flatten
	 * or not. Default is no.
	 * 
	 * @param shouldFlatten
	 */
	void setFlattenHierarchy(boolean shouldFlatten);
}
