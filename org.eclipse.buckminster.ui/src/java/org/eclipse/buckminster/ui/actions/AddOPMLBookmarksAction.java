/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.actions;

import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.swt.widgets.Shell;

public class AddOPMLBookmarksAction extends AbstractOPMLAction
{
	@Override
	protected void run(OPML opml, Shell shell)
	{
		// TODO Implement OPML Export
		showMessage(Messages.not_yet_implemented, Messages.add_opml_bookmarks_to_rss_reader);
	}
}
