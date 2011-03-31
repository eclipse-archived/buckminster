/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.views;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.ui.GenericUiPlugin;
import org.eclipse.buckminster.generic.ui.actions.IBrowseable;
import org.eclipse.buckminster.generic.ui.actions.IBrowseableFeed;
import org.eclipse.buckminster.generic.ui.actions.ViewInBrowserAction;
import org.eclipse.buckminster.ui.InvokeActionJob;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.ViewCSpecAction;
import org.eclipse.buckminster.ui.dialogs.InvokeActionDialog;
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

/**
 * This a Buckminster Component Explorer View. It shows components known to
 * Buckminster, and allows the user to perform actions on these components.
 */

public class ComponentBrowserView extends ViewPart {
	class NameSorter extends ViewerSorter {
	}

	protected TreeViewer viewer;

	private Action refreshAction;

	private Action doubleClickAction;

	private ViewInBrowserAction viewInBrowser;

	private ViewInBrowserAction viewInExternalBrowser;

	private ViewInBrowserAction viewFeedInBrowser;

	/**
	 * Call-back that creates and initializes the viewer.
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(getContentProvider());

		viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new BuckminsterLabelProvider()));
		viewer.setSorter(new NameSorter());
		viewer.addTreeListener(new ITreeViewerListener() {
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// ignored
			}

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// if there are pending nodes let them go fetch their stuff...
				Object o = event.getElement();
				if (o instanceof ITreeParentDataNode)
					((ITreeParentDataNode) o).onOpen();
			}
		});

		// This tells the resolution content provider to produce a default tree
		// of all resolutions.
		viewer.setInput(getViewSite());

		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		getViewSite().setSelectionProvider(viewer);
	}

	public boolean isAutoExpand() {
		return false;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	protected ResolutionsTreeContentProvider getContentProvider() {
		return new ResolutionsTreeContentProvider();
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillContextMenu(IMenuManager manager) {
		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection) selection).getFirstElement();

		// Other plugins can contribute actions to "default"
		manager.add(new Separator("default")); //$NON-NLS-1$

		if (obj instanceof IAdaptable) {
			if (((IAdaptable) obj).getAdapter(IBrowseableFeed.class) != null)
				manager.add(viewFeedInBrowser);
			if (((IAdaptable) obj).getAdapter(IBrowseable.class) != null) {
				manager.add(viewInBrowser);
				manager.add(viewInExternalBrowser);
			}
		}

		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				ComponentBrowserView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	// private void showMessage(String message)
	// {
	// MessageDialog.openInformation(viewer.getControl().getShell(),
	// "Component Browser View", message);
	// }

	private void makeActions() {
		viewInBrowser = new ViewInBrowserAction(viewer, true, Messages.content, false);
		viewInExternalBrowser = new ViewInBrowserAction(viewer, false, Messages.content, false);
		viewFeedInBrowser = new ViewInBrowserAction(viewer, false, Messages.feed, true);

		refreshAction = new Action() {
			@Override
			public void run() {
				// This tells the resolution content provider to produce a
				// default tree of all resolutions.
				viewer.setInput(getViewSite());
			}
		};
		refreshAction.setText(Messages.refresh);
		refreshAction.setToolTipText(Messages.refresh_component_explorer);
		refreshAction.setImageDescriptor(GenericUiPlugin.getImageDescriptor("icons/refresh.gif")); //$NON-NLS-1$

		doubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof IAdaptable) {
					// Invoke the ViewCSpec Action
					ICSpecData cspec = (ICSpecData) ((IAdaptable) obj).getAdapter(CSpec.class);
					if (cspec != null) {
						ViewCSpecAction vca = new ViewCSpecAction();
						vca.setActivePart(this, ComponentBrowserView.this.getSite().getPart());
						vca.selectionChanged(this, selection);
						vca.run(this);
						return;
					}
					// Schedule the InvokeActionJob
					Attribute attribute = (Attribute) ((IAdaptable) obj).getAdapter(IAction.class);
					if (attribute != null) {
						final ArrayList<Attribute> attributes = new ArrayList<Attribute>();
						attributes.add(attribute);

						InvokeActionDialog dialog = new InvokeActionDialog(getSite().getShell(), Messages.invoke_action, attributes);

						if (dialog.open() == Window.OK) {
							File propertiesFile = dialog.getPropertiesFile();
							boolean forceRebuild = dialog.isForceRebuild();
							InvokeActionJob job = new InvokeActionJob(attributes.get(0).getName(), attributes, propertiesFile, forceRebuild);
							job.schedule();
						}
						return;
					}

					IBrowseableFeed feed = (IBrowseableFeed) ((IAdaptable) obj).getAdapter(IBrowseableFeed.class);
					if (feed != null) {
						IObjectActionDelegate delegate = UiPlugin.getDefault().getOpenRssFeedAction();
						if (delegate != null) {
							delegate.setActivePart(this, ComponentBrowserView.this);
							delegate.selectionChanged(this, new StructuredSelection(feed));
							delegate.run(this);
							return;
						}
					}
					IBrowseable site = (IBrowseable) ((IAdaptable) obj).getAdapter(IBrowseable.class);
					if (site != null) {
						viewInBrowser.run();
						return;
					}

				}
			}
		};
	}
}
