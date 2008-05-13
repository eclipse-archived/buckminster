/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.views;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.ui.GenericUiPlugin;
import org.eclipse.buckminster.generic.ui.actions.IBrowseable;
import org.eclipse.buckminster.generic.ui.actions.IBrowseableFeed;
import org.eclipse.buckminster.generic.ui.actions.ViewInBrowserAction;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.actions.ViewCSpecAction;
import org.eclipse.buckminster.ui.providers.BuckminsterLabelProvider;
import org.eclipse.buckminster.ui.providers.ResolutionsTreeContentProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

/**
 * This a Buckminster Component Explorer View. It shows components known to Buckminster, and
 * allows the user to perform actions on these components.
 */

public class ComponentBrowserView extends ViewPart
{
	protected TreeViewer m_viewer;
	
	private Action m_refreshAction;

	private Action doubleClickAction;
	
	private ViewInBrowserAction m_viewInBrowser;
	private ViewInBrowserAction m_viewInExternalBrowser;
	private ViewInBrowserAction m_viewFeedInBrowser;
	
	class NameSorter extends ViewerSorter
	{
	}
	public boolean isAutoExpand()
	{
		return false;
	}
	/**
	 * Call-back that creates and initializes the viewer.
	 */
	@Override
	public void createPartControl(Composite parent)
	{
		m_viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		m_viewer.setContentProvider(getContentProvider());
		
		m_viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new BuckminsterLabelProvider()));
		m_viewer.setSorter(new NameSorter());
		m_viewer.addTreeListener(new ITreeViewerListener(){
			public void treeCollapsed(TreeExpansionEvent event)
			{
				// ignored
			}
			public void treeExpanded(TreeExpansionEvent event)
			{
				// if there are pending nodes let them go fetch their stuff...
				Object o = event.getElement();
				if(o instanceof ITreeParentDataNode)
					((ITreeParentDataNode)o).onOpen();
			}			
		});

		// This tells the resolution content provider to produce a default tree of all resolutions.
		m_viewer.setInput(getViewSite());
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		getViewSite().setSelectionProvider(m_viewer);
	}
	protected ResolutionsTreeContentProvider getContentProvider()
	{
		return new ResolutionsTreeContentProvider();
	}
	private void hookContextMenu()
	{
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager manager)
			{
				ComponentBrowserView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(m_viewer.getControl());
		m_viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, m_viewer);
	}

	private void contributeToActionBars()
	{
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager)
	{		
		manager.add(m_refreshAction);
	}

	private void fillContextMenu(IMenuManager manager)
	{
		ISelection selection = m_viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();

		// Other plugins can contribute actions to "default" 
		manager.add(new Separator("default"));
		
		if(obj instanceof IAdaptable)
		{
			if(((IAdaptable)obj).getAdapter(IBrowseableFeed.class) != null)	
				manager.add(m_viewFeedInBrowser);
			if(((IAdaptable)obj).getAdapter(IBrowseable.class) != null)
			{
				manager.add(m_viewInBrowser);
				manager.add(m_viewInExternalBrowser);
			}
		}

		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(m_refreshAction);
	}

	private void makeActions()
	{
		m_viewInBrowser = new ViewInBrowserAction(m_viewer, true, "Content", false);
		m_viewInExternalBrowser = new ViewInBrowserAction(m_viewer, false, "Content", false);
		m_viewFeedInBrowser = new ViewInBrowserAction(m_viewer, false, "Feed", true);
		
		m_refreshAction = new Action()
		{
			@Override
			public void run()
			{
				// This tells the resolution content provider to produce a default tree of all resolutions.
				m_viewer.setInput(getViewSite());
			}
		};
		m_refreshAction.setText("Refresh");
		m_refreshAction.setToolTipText("Refresh Component Explorer");
		m_refreshAction.setImageDescriptor(GenericUiPlugin.getImageDescriptor("icons/refresh.gif"));
		
		doubleClickAction = new Action()
		{
			@Override
			public void run()
			{
				ISelection selection = m_viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				if(obj instanceof IAdaptable)
				{
					// Invoke the ViewCSpec Action
					CSpec cspec = (CSpec)((IAdaptable)obj).getAdapter(CSpec.class);
					if(cspec != null)
					{
						ViewCSpecAction vca = new ViewCSpecAction();
						vca.setActivePart(this, ComponentBrowserView.this.getSite().getPart());
						vca.selectionChanged(this, selection);
						vca.run(this);
						return;
					}
					IBrowseableFeed feed = (IBrowseableFeed)((IAdaptable)obj).getAdapter(IBrowseableFeed.class);
					if(feed != null)
					{
						 IObjectActionDelegate delegate = UiPlugin.getDefault().getOpenRssFeedAction();
						 if(delegate != null)
						 {
							 delegate.setActivePart(this, ComponentBrowserView.this);
							 delegate.selectionChanged(this, new StructuredSelection(feed));
							 delegate.run(this);
							 return;
						 }	 
					}
					IBrowseable site = (IBrowseable)((IAdaptable)obj).getAdapter(IBrowseable.class);
					if(site != null)
					{
						m_viewInBrowser.run();
						return;
					}
				
				}
			}
		};
	}

	private void hookDoubleClickAction()
	{
		m_viewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				doubleClickAction.run();
			}
		});
	}

//	private void showMessage(String message)
//	{
//		MessageDialog.openInformation(m_viewer.getControl().getShell(), "Component Browser View", message);
//	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{
		m_viewer.getControl().setFocus();
	}
}
