/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingPropertyException extends LocalizedException
{
	private static final long serialVersionUID = -2064690747503780031L;

	public MissingPropertyException(String name, String attribute, String propertyCategory, String propertyName)
	{
		super("CSpec %s, attribute %s has no %s named %s", name, attribute, propertyCategory, propertyName);
	}
}

