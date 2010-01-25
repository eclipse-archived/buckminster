/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MissingAttributeException extends LocalizedException
{
	private static final long serialVersionUID = 7072912960847819828L;

	public MissingAttributeException(String name, String target)
	{
		this(name, target, false);
	}

	public MissingAttributeException(String name, String target, boolean withVisibility)
	{

		super(NLS.bind(Messages.CSpec_0_has_no_1_action_group_or_local_artifact_named_2, new Object[] { name,
				withVisibility
						? "public " //$NON-NLS-1$
						: "", target })); //$NON-NLS-1$
	}
}
