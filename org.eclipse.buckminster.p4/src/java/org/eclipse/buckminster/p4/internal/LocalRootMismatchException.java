/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.p4.internal;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

public class LocalRootMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -2811429378808277844L;

	public LocalRootMismatchException(String clientName, String p4Port, IPath preferencePath, IPath serverPath)
	{
		super("The local path declared in P4 preferences for client %s and p4 port %s is %s. " +
				"It should be %s according to the client specification found on the server",
				clientName, p4Port, preferencePath.toOSString(), serverPath.toOSString());
	}
}
