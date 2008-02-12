/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class ComponentTypeMismatchException extends LocalizedException
{
	private static final long serialVersionUID = 5479744816736527579L;

	public ComponentTypeMismatchException(String componentName, String expectedType, String actualType)
	{
		super("Component type mismatch exception for component %s. Expected type was %s but actual type is %s",
				componentName, expectedType, actualType);
	}
}
