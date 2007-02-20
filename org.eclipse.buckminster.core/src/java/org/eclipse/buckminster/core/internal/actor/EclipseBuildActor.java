/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.WellknownActions;

/**
 * @author ken1
 * 
 */
public class EclipseBuildActor extends AbstractBuildIntegrationActor
{
	public static final String ID = "eclipse.build";

	public static final String KIND_PROPERTY = "kind";

	@Override
	protected String getNameForKind(IActionContext ctx)
	{
		String kindAsString = this.getActorProperty(KIND_PROPERTY);

		if(kindAsString == null)
			return WellknownActions.ECLIPSE.INCREMENTAL.toString();

		for(WellknownActions.ECLIPSE e : WellknownActions.ECLIPSE.values())
			if(kindAsString.equals(e.toString()))
				return kindAsString;

		throw new IllegalArgumentException("Invalid kind: " + kindAsString);
	}
}
