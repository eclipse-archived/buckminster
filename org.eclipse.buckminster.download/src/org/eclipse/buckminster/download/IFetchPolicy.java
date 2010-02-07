/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public interface IFetchPolicy {
	public static final int DEFAULT_MAX_LOCAL_AGE = 30000;

	boolean update(URL remoteFile, File localFile, boolean checkOnly, IFileInfo[] fileInfoHandle, IProgressMonitor monitor) throws CoreException,
			FileNotFoundException;
}
