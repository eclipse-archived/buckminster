/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.runtime;

/**
 * @author Filip Hrbek
 *
 */
public interface IFileInfo
{
	/**
	 * @return the name (may return null)
	 */
	public String getName();

	/**
	 * @return the contentType (may return null)
	 */
	public String getContentType();

	/**
	 * @return the size (may return null)
	 */
	public Long getSize();
	
	/**
	 * @return the most suitable extension (may return null)
	 */
	public String getExtension();
	
	/**
	 * @return all extensions mapped to the file's content type (if not known, returns an empty array)
	 */
	public String[] getExtensions();
}
