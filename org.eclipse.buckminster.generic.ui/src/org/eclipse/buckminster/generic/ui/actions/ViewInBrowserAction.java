/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.ui.actions;

import org.eclipse.buckminster.generic.ui.GenericUiPlugin;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Opens an internal or external browser for a selected object that is adaptable to
 * IBrowseable, or to IBrowseableFeed if the boolean flag feed is true.
 * 
 * @author Henrik Lindberg
 *
 */
public class ViewInBrowserAction extends AbstractAction
{
	private IWorkbenchBrowserSupport m_browserSupport;
	private boolean m_internal;
	private boolean m_feed;
	
	/**
	 * @see ViewInBrowserAction#ViewInBrowserAction(Viewer, boolean, String, boolean)
	 * @param viewer
	 * @param internal
	 */
	public ViewInBrowserAction(Viewer viewer, boolean internal)
	{
		this(viewer, internal, null, false);
	}
	/**
	 * Create a ViewInBrowserAction configured for a particular type of content.
	 * 
	 * @param viewer - the viewer where the action is performed
	 * @param internal - true if internal browser should be used (may still open in external)
	 * @param what - a label for what is opened, can be null (e.g. "content", "feed", "page").
	 * @param feed - flag indicating that selected object is adapted to IBrowseableFeed instead of IBrowseable
	 */
	public ViewInBrowserAction(Viewer viewer, boolean internal, String what, boolean feed )
	{
		super(viewer);
		IWorkbenchBrowserSupport browserSupport = UiUtils.getWorkbench().getBrowserSupport();
		m_browserSupport = browserSupport;
		m_internal = internal;
		m_feed = feed;
		StringBuilder bld = new StringBuilder();
		bld.append("View");
		if(what != null)
		{
			bld.append(' ');
			bld.append(what);
		}
		bld.append(" in ");
		bld.append((internal ? "internal" : "external"));
		bld.append(" browser...");
		String txt = bld.toString();
		setText(txt);
		setToolTipText(txt);
		setImageDescriptor(m_feed 
				? GenericUiPlugin.getImageDescriptor("icons/rssfeed.png")
				: UiUtils.getImageDescriptor("file.html"));
	}
	@Override
	public void run()
	{
		IStructuredSelection selection = getStructuredSelection();
		Object[] selected;
		if(selection == null || (selected = selection.toArray()).length <1)
		{
			showMessage("Show in browser", "Nothing was selected");
			return;
		}
		Object sel = selected[0];
		IDescribedURL describedURL = null;
		if(sel instanceof IAdaptable)
		{
			if(m_feed)
				describedURL = (IDescribedURL)((IAdaptable)sel).getAdapter(IBrowseableFeed.class);
			else
				describedURL = (IDescribedURL)((IAdaptable)sel).getAdapter(IBrowseable.class);
			
		}
		if(describedURL == null)
		{
			showError("Can not open browser", "Selected object does not have a valid URL");
			return;
		}
		
		IWebBrowser browser;
		try
		{
			browser = m_browserSupport.createBrowser( m_internal ? 
					IWorkbenchBrowserSupport.AS_EDITOR : IWorkbenchBrowserSupport.AS_EXTERNAL, 
					null,
					describedURL.getName(), describedURL.getTooltip());
			browser.openURL(describedURL.getBrowseableURL());
		}
		catch(PartInitException e)
		{
			e.printStackTrace();
		}
		
	}
}

