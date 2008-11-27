/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import java.util.List;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public abstract class AbstractChosenCSpecAction implements IWorkbenchWindowActionDelegate
{
	static class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			Resolution cr = (Resolution)element;
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = cr.getRequest().getViewName();
				break;
			case 1:
				IVersion vs = cr.getVersion();
				lbl = vs == null ? "" : vs.toString(); //$NON-NLS-1$
				break;
			default:
				lbl = null;
			}
			return lbl;
		}
	}

	static class ComponentsDialog extends Dialog
	{
		private final List<Resolution> m_resolutions;

		private Resolution m_selectedComponent;

		private String m_title;

		private TableViewer m_viewer;

		ComponentsDialog(Shell parentShell, String title, List<Resolution> resolutions)
		{
			super(parentShell);
			m_title = title;
			m_resolutions = resolutions;
		}

		@Override
		protected void configureShell(Shell newShell)
		{
			super.configureShell(newShell);
			newShell.setText(m_title);
		}

		@Override
		protected Control createDialogArea(Composite parent)
		{
			Composite superArea = (Composite)super.createDialogArea(parent);
			Table table = new Table(superArea, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

			String[] columnNames = new String[] { Messages.name, Messages.version };
			int[] columnWeights = new int[] { 70, 30 };

			table.setHeaderVisible(true);
			DynamicTableLayout layout = new DynamicTableLayout(450);
			for(int idx = 0; idx < columnNames.length; idx++)
			{
				TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
				tableColumn.setText(columnNames[idx]);
				layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
			}
			table.setLayout(layout);
			table.setSize(450,450);
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			m_viewer = new TableViewer(table);
			m_viewer.setLabelProvider(new ComponentLabelProvider());
			m_viewer.setContentProvider(new ArrayContentProvider());
			m_viewer.addSelectionChangedListener(new ISelectionChangedListener()
			{
				public void selectionChanged(SelectionChangedEvent event)
				{
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					getButton(IDialogConstants.OK_ID).setEnabled(selection.size() == 1);
				}
			});
			List<Resolution> input = m_resolutions.size() > 15
				? m_resolutions.subList(0, 15)
				: m_resolutions;
			m_viewer.setInput(input);
			return superArea;
		}

		@Override
		protected void initializeBounds()
		{
			super.initializeBounds();
			m_viewer.setInput(m_resolutions);
			((Table)m_viewer.getControl()).select(0);
		}

		@Override
		protected void okPressed()
		{
			IStructuredSelection selection = (IStructuredSelection)m_viewer.getSelection();
			if(selection.size() == 1)
			{
				m_selectedComponent = (Resolution)selection.getFirstElement();
				super.okPressed();
			}
		}

		Resolution getSelectedComponent()
		{
			return m_selectedComponent;
		}
	}

	private IWorkbenchWindow m_window;

	public void dispose()
	{
	}

	public void init(IWorkbenchWindow window)
	{
		m_window = window;
	}

	public void run(IAction action)
	{
		Shell shell = m_window.getShell();
		try
		{
			ComponentsDialog dialog = new ComponentsDialog(shell, Messages.components_known_to_buckminster, WorkspaceInfo.getAllResolutions());
			if(dialog.open() != Window.OK)
				return;

			Resolution cinfo = dialog.getSelectedComponent();
			if(cinfo != null)
				run(cinfo.getCSpec(), m_window);
		}
		catch(Exception e)
		{
			UiUtils.openError(shell, Messages.errors_during_loading, e);
		}
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}

	protected abstract void run(CSpec cspec, IWorkbenchWindow wbWin);
}
