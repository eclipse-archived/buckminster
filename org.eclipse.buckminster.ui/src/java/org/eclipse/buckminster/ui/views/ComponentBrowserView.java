/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.ui.providers.BuckminsterLabelProvider;
import org.eclipse.buckminster.ui.providers.ResolutionsTreeContentProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

/**
 * This a Buckminster Component Explorer View. It shows components known to Buckminster, and
 * allows the user to perform actions on these components.
 */

public class ComponentBrowserView extends ViewPart
{
//	private TableViewer viewer;
	private TreeViewer m_viewer;
	
	private Action action1;

	private Action action2;

	private Action doubleClickAction;

//	private DrillDownAdapter m_drillDownAdapter;

	class ViewLabelProvider extends BuckminsterLabelProvider implements ITableLabelProvider
	{
		public String getColumnText(Object element, int columnIndex)
		{
			Resolution cr = (Resolution)element;
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = super.getText(cr);
				// lbl = cr.getRequest().getViewName();
				break;
			case 1:
				IVersion vs = cr.getVersion();
				lbl = vs == null ? "" : vs.toString();
				break;
			default:
				lbl = null;
			}
			return lbl;
		}
		public Image getColumnImage(Object obj, int index)
		{
			return index != 0 ? null : getImage(obj);
		}

	}


	class NameSorter extends ViewerSorter
	{
	}

	/**
	 * The constructor.
	 */
	public ComponentBrowserView()
	{
	}
	/**
	 * Call-back that creates and initializes the viewer.
	 */
	@Override
	public void createPartControl(Composite parent)
	{
		m_viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		m_drillDownAdapter = new DrillDownAdapter(m_viewer);
		m_viewer.setContentProvider(new ResolutionsTreeContentProvider());
		m_viewer.setLabelProvider(new BuckminsterLabelProvider());
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
		List<Resolution> resolutions;
		try
		{
			resolutions = WorkspaceInfo.getAllResolutions();
		}
		catch(CoreException e)
		{
			resolutions = new ArrayList<Resolution>(0);
			e.printStackTrace();
		}
		m_viewer.setInput(resolutions);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();		
	}

//	/**
//	 * This is a callback that will allow us to create the viewer and initialize it.
//	 */
//	@Override
//	public void createPartControl(Composite parent)
//	{
//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		final Table table = viewer.getTable();
//		table.setHeaderVisible(true);
//		String[] columnNames = new String[] { "Name", "Version" };
//		int[] columnWeights = new int[] { 70, 30 };
//
//		table.setHeaderVisible(true);
//		DynamicTableLayout layout = new DynamicTableLayout(450);
//		for(int idx = 0; idx < columnNames.length; idx++)
//		{
//			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
//			tableColumn.setText(columnNames[idx]);
//			
//			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
//		}
//		table.setLayout(layout);
//		
//		viewer.setContentProvider(new ArrayContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter());
//		List<Resolution> resolutions;
//		try
//		{
//			resolutions = WorkspaceInfo.getAllResolutions();
//		}
//		catch(CoreException e)
//		{
//			resolutions = new ArrayList<Resolution>(0);
//			e.printStackTrace();
//		}
//		viewer.setInput(resolutions);
//		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
//		contributeToActionBars();
//	}

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
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager)
	{
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions()
	{
		action1 = new Action()
		{
			@Override
			public void run()
			{
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
				ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action()
		{
			@Override
			public void run()
			{
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
				ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action()
		{
			@Override
			public void run()
			{
				ISelection selection = m_viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on " + obj.toString());
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

	private void showMessage(String message)
	{
		MessageDialog.openInformation(m_viewer.getControl().getShell(), "Component Browser View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{
		m_viewer.getControl().setFocus();
	}
}
