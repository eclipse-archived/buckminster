package org.eclipse.buckminster.core.actor;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

public class IllegalPrerequisiteException extends LocalizedException
{
	private static final long serialVersionUID = 8763205578172653550L;

	public IllegalPrerequisiteException(Action action, String name)
	{
		super(NLS.bind(Messages.prerequisite_named_0_is_not_known_to_action_1, name, action.getQualifiedName()));
	}
}
