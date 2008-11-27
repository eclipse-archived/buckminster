/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.generic.ui.actions.AbstractObjectAction;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.swt.widgets.Shell;

/**
 * Operate on the selected object adapted to OPML.class.
 * 
 * @author Henrik Lindberg
 */
public abstract class AbstractOPMLAction extends AbstractObjectAction<OPML>
{

	@Override
	protected Class<OPML> getType()
	{
		return OPML.class;
	}

	// restated
	@Override
	protected abstract void run(OPML instance, Shell shell);
}
