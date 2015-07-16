/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.ui.actions;

import org.eclipse.buckminster.generic.ui.GenericUiPlugin;
import org.eclipse.buckminster.generic.ui.Messages;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Opens an internal or external browser for a selected object that is adaptable
 * to IBrowseable, or to IBrowseableFeed if the boolean flag feed is true.
 *
 * @author Henrik Lindberg
 *
 */
public class ViewInBrowserAction extends AbstractAction {
	private IWorkbenchBrowserSupport browserSupport;

	private boolean internal;

	private boolean feed;

	/**
	 * @see ViewInBrowserAction#ViewInBrowserAction(Viewer, boolean, String,
	 *      boolean)
	 * @param viewer
	 * @param internal
	 */
	public ViewInBrowserAction(Viewer viewer, boolean internal) {
		this(viewer, internal, null, false);
	}

	/**
	 * Create a ViewInBrowserAction configured for a particular type of content.
	 *
	 * @param viewer
	 *            - the viewer where the action is performed
	 * @param internal
	 *            - true if internal browser should be used (may still open in
	 *            external)
	 * @param what
	 *            - a label for what is opened, can be null (e.g. "content",
	 *            "feed", "page").
	 * @param feed
	 *            - flag indicating that selected object is adapted to
	 *            IBrowseableFeed instead of IBrowseable
	 */
	public ViewInBrowserAction(Viewer viewer, boolean internal, String what, boolean feed) {
		super(viewer);
		this.browserSupport = UiUtils.getWorkbench().getBrowserSupport();
		this.internal = internal;
		this.feed = feed;
		String txt = what != null ? NLS.bind(Messages.view_0_in_1_, what, (internal ? Messages.browser_internal : Messages.browser_external)) : NLS
				.bind(Messages.view_in_0_, (internal ? Messages.browser_internal : Messages.browser_external));
		setText(txt);
		setToolTipText(txt);
		setImageDescriptor(feed ? GenericUiPlugin.getImageDescriptor("icons/rssfeed.png") //$NON-NLS-1$
				: UiUtils.getImageDescriptor("file.html")); //$NON-NLS-1$
	}

	@Override
	public void run() {
		IStructuredSelection selection = getStructuredSelection();
		Object[] selected;
		if (selection == null || (selected = selection.toArray()).length < 1) {
			showMessage(Messages.show_in_browser, Messages.nothing_was_selected);
			return;
		}
		Object sel = selected[0];
		IDescribedURL describedURL = null;
		if (sel instanceof IAdaptable) {
			if (feed)
				describedURL = ((IAdaptable) sel).getAdapter(IBrowseableFeed.class);
			else
				describedURL = ((IAdaptable) sel).getAdapter(IBrowseable.class);

		}
		if (describedURL == null) {
			showError(Messages.can_not_open_browser, Messages.no_valid_URL_for_selected_object);
			return;
		}

		IWebBrowser browser;
		try {
			browser = browserSupport.createBrowser(internal ? IWorkbenchBrowserSupport.AS_EDITOR : IWorkbenchBrowserSupport.AS_EXTERNAL, null,
					describedURL.getName(), describedURL.getTooltip());
			browser.openURL(describedURL.getBrowseableURL());
		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}
}
