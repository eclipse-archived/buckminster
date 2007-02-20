/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author Thomas Hallgren
 */
public class IllegalOverlayException extends LocalizedException
{
	private static final long serialVersionUID = -4820969561462830483L;

	protected IllegalOverlayException(String defaultMessageFormat)
	{
		super(defaultMessageFormat);
	}

	@Override
	protected String[] getArguments()
	{
		return Trivial.EMPTY_STRING_ARRAY;
	}

}
