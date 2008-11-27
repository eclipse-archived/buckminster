/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.sax.AbstractHandler;

/**
 * @author Thomas Hallgren
 */
class GroupsHandler extends AttributesHandler
{
	public static final String TAG = CSpec.ELEM_GROUPS;

	GroupsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	TopLevelAttributeHandler createAttributeHandler(boolean publ)
	{
		return new GroupHandler(this, publ);
	}
}
