/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.helpers.LocalizedException;

public class VersionSyntaxException extends LocalizedException
{
	private static final long serialVersionUID = 948708968483188011L;

	public VersionSyntaxException(String reason, String versionString, int errorPos)
	{
		super("Syntax error in version string %s at position %d: %s", versionString, Integer.valueOf(errorPos), reason);
	}
}
