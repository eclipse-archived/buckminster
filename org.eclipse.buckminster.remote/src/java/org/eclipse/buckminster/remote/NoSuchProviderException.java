/*****************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
*****************************************************************************/

package org.eclipse.buckminster.remote;

import org.eclipse.buckminster.runtime.BuckminsterException;

/**
 * @author Filip Hrbek
 *
 */
public class NoSuchProviderException extends BuckminsterException
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1862882467383623237L;


	public NoSuchProviderException()
	{
		super();
	}

	public NoSuchProviderException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NoSuchProviderException(String message)
	{
		super(message);
	}
}
