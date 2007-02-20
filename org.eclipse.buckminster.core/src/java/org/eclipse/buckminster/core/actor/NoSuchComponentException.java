/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import org.eclipse.buckminster.core.helpers.BuckminsterException;

/**
 * @author kolwing
 *
 */
@SuppressWarnings("serial")
public class NoSuchComponentException extends BuckminsterException
{
	public NoSuchComponentException(String compName)
	{
		super(compName);
	}
}
