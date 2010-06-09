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
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
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

public abstract class AbstractChosenCSpecAction implements IWorkbenchWindowActionDelegate {
	static class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Resolution cr = (Resolution) element;
			String lbl;
			switch (columnIndex) {
				case 0:
					lbl = cr.getRequest().getViewName();
					break;
				case 1:
					lbl = VersionHelper.getHumanReadable(cr.getVersion());
					break;
				default:
					lbl = null;
			}
			return lbl;
		}
	}

	static class ComponentsDialog extends Dialog {
		private final List<Resolution> resolutions;

		private Resolution selectedComponent;

		private String title;

		private TableViewer viewer;

		ComponentsDialog(Shell parentShell, String title, List<Resolution> resolutions) {
			super(parentShell);
			this.title = title;
			this.resolutions = resolutions;
		}

		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(title);
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite superArea = (Composite) super.createDialogArea(parent);
			Table table = new Table(superArea, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

			String[] columnNames = new String[] { Messages.name, Messages.version };
			int[] columnWeights = new int[] { 70, 30 };

			table.setHeaderVisible(true);
			DynamicTableLayout layout = new DynamicTableLayout(450);
			for (int idx = 0; idx < columnNames.length; idx++) {
				TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
				tableColumn.setText(columnNames[idx]);
				layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
			}
			table.setLayout(layout);
			table.setSize(450, 450);
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			viewer = new TableViewer(table);
			viewer.setLabelProvider(new ComponentLabelProvider());
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					getButton(IDialogConstants.OK_ID).setEnabled(selection.size() == 1);
				}
			});
			List<Resolution> input = resolutions.size() > 15 ? resolutions.subList(0, 15) : resolutions;
			viewer.setInput(input);
			return superArea;
		}

		@Override
		protected void initializeBounds() {
			super.initializeBounds();
			viewer.setInput(resolutions);
			((Table) viewer.getControl()).select(0);
		}

		@Override
		protected void okPressed() {
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			if (selection.size() == 1) {
				selectedComponent = (Resolution) selection.getFirstElement();
				super.okPressed();
			}
		}

		Resolution getSelectedComponent() {
			return selectedComponent;
		}
	}

	private IWorkbenchWindow window;

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow w) {
		window = w;
	}

	@Override
	public void run(IAction action) {
		Shell shell = window.getShell();
		try {
			ComponentsDialog dialog = new ComponentsDialog(shell, Messages.components_known_to_buckminster, WorkspaceInfo.getAllResolutions());
			if (dialog.open() != Window.OK)
				return;

			Resolution cinfo = dialog.getSelectedComponent();
			if (cinfo != null)
				run(cinfo.getCSpec(), window);
		} catch (Exception e) {
			UiUtils.openError(shell, Messages.errors_during_loading, e);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	protected abstract void run(CSpec cspec, IWorkbenchWindow wbWin);
}
