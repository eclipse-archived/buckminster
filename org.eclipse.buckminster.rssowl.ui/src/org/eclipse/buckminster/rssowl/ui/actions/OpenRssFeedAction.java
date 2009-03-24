/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.rssowl.ui.actions;

import org.eclipse.buckminster.generic.ui.actions.AbstractBrowseableFeedAction;
import org.eclipse.buckminster.generic.ui.actions.IBrowseableFeed;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.rssowl.core.persist.IBookMark;
import org.rssowl.core.persist.reference.FeedLinkReference;
import org.rssowl.core.util.CoreUtils;
import org.rssowl.ui.internal.Activator;
import org.rssowl.ui.internal.OwlUI;
import org.rssowl.ui.internal.editors.feed.FeedView;
import org.rssowl.ui.internal.editors.feed.FeedViewInput;
import org.rssowl.ui.internal.editors.feed.PerformAfterInputSet;

/**
 * Opens the selected feed in RSS OWL feed viewer.
 * (This class uses restricted APIs in RSS OWL).
 * 
 * @author Henrik Lindberg
 */
@SuppressWarnings("restriction")
public class OpenRssFeedAction extends AbstractBrowseableFeedAction
{

	@Override
	protected void run(IBrowseableFeed instance, Shell shell)
	{
		IWorkbenchPage page = OwlUI.getPage();
		if(page == null)
			return;

		// Link to the feed to open
		FeedLinkReference flink = new FeedLinkReference(instance.getBrowseableURI());

		// Find a bookmark using this link
		IBookMark bookmark = CoreUtils.getBookMark(flink);
		if(bookmark == null)
			return;
		
		// Open bookmark, with action to select first news in feed.
		FeedViewInput fvInput = new FeedViewInput(bookmark,
				PerformAfterInputSet.SELECT_FIRST_NEWS);

		try
		{
			/* First check if input already shown */
			IEditorPart existingEditor = page.findEditor(fvInput);
			if(existingEditor != null && existingEditor instanceof FeedView)
			{
				page.activate(existingEditor);
			}
			else
				page.openEditor(fvInput, FeedView.ID, true);
		
		}
		catch(PartInitException e)
		{
			Activator.getDefault().getLog().log(e.getStatus());
		}
	}

}
