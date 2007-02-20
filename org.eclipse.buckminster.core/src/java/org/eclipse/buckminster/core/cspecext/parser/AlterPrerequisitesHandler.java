/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.sax.AbstractHandler;

/**
 * @author Thomas Hallgren
 */
class AlterPrerequisitesHandler extends AlterGroupHandler
{
	public static final String TAG = AlterAction.ELEM_ALTER_PREREQUISITES;

	AlterPrerequisitesHandler(AbstractHandler parent)
	{
		super(parent, false);
	}

	@Override
	void addAlterPrerequisite(Prerequisite prereq) throws PrerequisiteAlreadyDefinedException
	{
		((AlterActionHandler)this.getParentHandler()).getAlterActionBuilder().addAlterPrerequisite(prereq);
	}

	@Override
	void addRemovePrerequisite(String key)
	{
		((AlterActionHandler)this.getParentHandler()).getAlterActionBuilder().addRemovePrerequisite(key);
	}
}
