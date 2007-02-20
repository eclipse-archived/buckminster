/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisites;

public class PrerequisitesBuilder extends GroupBuilder
{
	PrerequisitesBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public Group createAttribute()
	{
		return new Prerequisites(this.getName(), this.getRebase(), getPrerequisiteList());
	}
}
