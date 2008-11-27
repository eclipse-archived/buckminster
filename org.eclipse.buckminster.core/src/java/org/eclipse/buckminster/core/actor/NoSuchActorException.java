/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class NoSuchActorException extends LocalizedException
{
	private static final long serialVersionUID = 3990531669819240624L;

	public NoSuchActorException(String actorName, String actionName)
	{
		super("Action {0} refers to actor with id {1} but no such actor has been registered with extension-point {2}",
				actionName, actorName, CorePlugin.ACTORS_POINT);
	}
}
