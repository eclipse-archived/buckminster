/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 *
 */
public class FileFolderMismatchException extends LocalizedException
{
	private static final long serialVersionUID = 3524432129593758451L;

	private final String[] m_args;
	public FileFolderMismatchException(ComponentIdentifier ci, IPath path)
	{
		super("Unable to reuse location {1} for component {0}. A file was expected but a folder was found or vice versa");
		m_args = new String[] { ci.toString(), path.toOSString() };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
