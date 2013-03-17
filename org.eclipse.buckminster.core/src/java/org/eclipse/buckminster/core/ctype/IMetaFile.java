/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.core.runtime.IPath;

/**
 * The component type meta file element
 * 
 * @author Thomas Hallgren
 */
public interface IMetaFile {
	/**
	 * Return other names for the file (i.e. Maven uses pom.xml and project.xml)
	 * 
	 * @return Aliases for the meta-file
	 */
	IPath[] getAliases();

	/**
	 * Returns the path to the file relative to the component root
	 * 
	 * @return The path to the meta-file
	 */
	IPath getPath();

	/**
	 * The component type will require some files in order to produce a cspec.
	 * Other files can be used to elaborate its contents. Such files are often
	 * optional.
	 * 
	 * @return <code>true</code> if the meta-file is optional.
	 */
	boolean isOptional();
}
