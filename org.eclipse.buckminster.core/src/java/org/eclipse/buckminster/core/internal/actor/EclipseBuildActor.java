/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.osgi.util.NLS;

/**
 * @author ken1
 * 
 */
public class EclipseBuildActor extends AbstractBuildIntegrationActor {
	public static final String ID = "eclipse.build"; //$NON-NLS-1$

	public static final String KIND_PROPERTY = "kind"; //$NON-NLS-1$

	@Override
	protected String getNameForKind(IActionContext ctx) {
		String kindAsString = this.getActorProperty(KIND_PROPERTY);

		if (kindAsString == null)
			return WellknownActions.ECLIPSE.INCREMENTAL.toString();

		for (WellknownActions.ECLIPSE e : WellknownActions.ECLIPSE.values())
			if (kindAsString.equals(e.toString()))
				return kindAsString;

		throw new IllegalArgumentException(NLS.bind(Messages.Invalid_kind_0, kindAsString));
	}
}
