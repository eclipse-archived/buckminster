/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

public class MissingPrerequisiteException extends LocalizedException
{
	private static final long serialVersionUID = -5156211146234700929L;

	public MissingPrerequisiteException(String name, String attribute, String prereqName)
	{
		super("CSpec %s, attribute %s does not define prerequisite %s", name, attribute, prereqName);
	}
}
