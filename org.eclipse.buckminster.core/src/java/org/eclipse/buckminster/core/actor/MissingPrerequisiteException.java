/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

public class MissingPrerequisiteException extends LocalizedException
{
	private static final long serialVersionUID = -5156211146234700929L;

	public MissingPrerequisiteException(Action action, Object alias)
	{
		super(NLS
				.bind(Messages.action_0_is_missing_required_prerequisite_with_alias_1, action.getQualifiedName(), alias));
	}

	public MissingPrerequisiteException(String name, String attribute, String prereqName)
	{
		super(NLS.bind(Messages.CSpec_0_attribute_1_does_not_define_prerequisite_2, new Object[] { name, attribute,
				prereqName }));
	}
}
