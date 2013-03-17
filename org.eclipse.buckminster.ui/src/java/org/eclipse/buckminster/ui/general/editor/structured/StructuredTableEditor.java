/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author Karel Brezina
 * 
 */
public abstract class StructuredTableEditor<T> extends Composite {
	class TableContentProvider implements IStructuredContentProvider {
		@Override
		public void dispose() {
			// Nothing to dispose
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return table.getRows().toArray();
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// Nothing to do
		}
	}

	class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		@SuppressWarnings("unchecked")
		public String getColumnText(Object element, int columnIndex) {
			Object field = table.getTableViewerField((T) element, columnIndex);
			return field == null ? "" //$NON-NLS-1$
					: field.toString();
		}
	}

	private final static int DONT_SAVE = -99;

	private final IStructuredTable<T> table;

	private final boolean swapButtonsFlag;

	private TableViewer tableViewer;

	private int lastSelectedRow = -1;

	private int lastEditedRow = -1;

	private Composite stackButtonComposite;

	private StackLayout stackButtonLayout;

	private Composite editButtonBox;

	private Composite viewButtonBox;

	private Button newButton;

	private Button editButton;

	private Button viewButton;

	private Button removeButton;

	private Button moveUpButton;

	private Button moveDownButton;

	private Tree stackOptions;

	private StackLayout stackLayout;

	private Composite stackComposite;

	private boolean enabled = true;

	public StructuredTableEditor(Composite parent, IStructuredTable<T> table, boolean swapButtonsFlag, int style) {
		super(parent, style);
		this.table = table;
		this.swapButtonsFlag = swapButtonsFlag;

		initComposite();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public abstract void refresh();

	public boolean selectRow(T row) {
		int idx = table.getRows().indexOf(row);

		if (idx == -1)
			return false;

		tableViewer.getTable().setSelection(idx);
		updateLastRow();

		return true;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		enableDisableButtonGroup();
	}

	protected void createStack(Composite parent) {
		stackComposite = new Composite(parent, SWT.NONE);
		stackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		stackLayout = new StackLayout();
		stackLayout.marginHeight = stackLayout.marginWidth = 0;
		stackComposite.setLayout(stackLayout);

		table.fillStackComposite(stackComposite);
	}

	protected void createStackOptions(Composite parent) {
		stackOptions = new Tree(parent, SWT.BORDER);
		stackOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		stackOptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null) {
					TreeItem item = (TreeItem) e.item;
					stackLayout.topControl = table.getStackControl(item.getText());
					stackComposite.layout();
					focusStackComposite();
				}
			}
		});
	}

	protected void createTableButtons(Composite parent) {
		stackButtonComposite = new Composite(parent, SWT.NONE);
		stackButtonComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		stackButtonLayout = new StackLayout();
		stackButtonLayout.marginHeight = stackButtonLayout.marginWidth = 0;
		stackButtonComposite.setLayout(stackButtonLayout);

		editButtonBox = new Composite(stackButtonComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = gridLayout.verticalSpacing = 0;
		editButtonBox.setLayout(gridLayout);
		editButtonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		newButton = UiUtils.createPushButton(editButtonBox, Messages.new_label, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newRow();
			}
		});
		newButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		editButton = UiUtils.createPushButton(editButtonBox, Messages.edit, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editRow(false, false);
			}
		});
		editButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		removeButton = UiUtils.createPushButton(editButtonBox, Messages.remove, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeRow();
			}
		});
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		if (swapButtonsFlag) {
			moveUpButton = UiUtils.createPushButton(editButtonBox, Messages.move_up, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					swapAndReselect(0, -1);
				}
			});
			moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

			moveDownButton = UiUtils.createPushButton(editButtonBox, Messages.move_down, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					swapAndReselect(1, 0);
				}
			});
			moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		}

		viewButtonBox = new Composite(stackButtonComposite, SWT.NONE);
		gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		viewButtonBox.setLayout(gridLayout);
		viewButtonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		viewButton = UiUtils.createPushButton(viewButtonBox, Messages.view, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editRow(false, true);
			}
		});
		viewButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
	}

	protected void createTableGroup(Composite parent) {
		Composite componentTableGroup = createTableGroupComposite(parent);

		Table tbl = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		// table.setHeaderVisible(false);
		tbl.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);

		int tableIdx = 0;
		for (int idx = 0; idx < table.getTableViewerColumns(); idx++) {
			if (table.getTableViewerColumnWeights()[idx] > 0) {
				TableColumn tableColumn = new TableColumn(tbl, SWT.LEFT, tableIdx);
				tableColumn.setText(table.getTableViewerColumnHeaders()[idx]);
				layout.addColumnData(new ColumnWeightData(table.getTableViewerColumnWeights()[idx], true));
				tableIdx++;
			}
		}
		tbl.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.widthHint = 600;
		tbl.setLayoutData(gridData);

		tableViewer = new TableViewer(tbl);
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new TableContentProvider());
		tableViewer.setInput(table);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				rowSelection();
			}
		});
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (tableViewer.getTable().getSelectionIndex() >= 0)
					editRow(false, !enabled || table.isReadOnly());
			}
		});

		createTableButtons(componentTableGroup);
	}

	protected abstract Composite createTableGroupComposite(Composite parent);

	protected abstract void editRow(boolean newRow, boolean readOnly);

	protected void enableDisableButtonGroup() {
		Table tbl = getTableViewer().getTable();
		int top = tbl.getItemCount();
		int idx = getSelectionIndex();

		if (isEnabled() && !table.isReadOnly()) {
			newButton.setEnabled(true);
			editButton.setEnabled(idx >= 0);
			removeButton.setEnabled(idx >= 0);

			if (isSwapButtonAllowed()) {
				moveUpButton.setEnabled(idx > 0);
				moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
			}

			stackButtonLayout.topControl = editButtonBox;
		} else {
			newButton.setEnabled(false);
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
			if (isSwapButtonAllowed()) {
				moveUpButton.setEnabled(false);
				moveDownButton.setEnabled(false);
			}

			stackButtonLayout.topControl = viewButtonBox;
		}

		viewButton.setEnabled(idx >= 0);
		stackButtonComposite.layout();
	}

	protected void enableFields(boolean flag) {
		table.enableFields(flag);
	}

	protected void fillStackOptions() {
		for (String stackKey : table.getStackKeys()) {
			TreeItem item = new TreeItem(stackOptions, SWT.NONE);
			item.setText(stackKey);
		}
	}

	protected void focusStackComposite() {
		Control focusControl = (Control) stackLayout.topControl.getData("focusControl"); //$NON-NLS-1$
		if (focusControl != null) {
			focusControl.setFocus();
		}
	}

	protected Button getEditButton() {
		return editButton;
	}

	protected int getLastEditedRow() {
		return lastEditedRow;
	}

	protected int getLastSelectedRow() {
		return lastSelectedRow;
	}

	protected Button getMoveDownButton() {
		return moveDownButton;
	}

	protected Button getMoveUpButton() {
		return moveUpButton;
	}

	protected Button getNewButton() {
		return newButton;
	}

	protected Button getRemoveButton() {
		return removeButton;
	}

	protected int getSelectionIndex() {
		return tableViewer.getTable().getSelectionIndex();
	}

	protected IStructuredTable<T> getTable() {
		return table;
	}

	protected TableViewer getTableViewer() {
		return tableViewer;
	}

	protected Button getViewButton() {
		return viewButton;
	}

	protected abstract void initComposite();

	protected boolean isSwapButtonAllowed() {
		return swapButtonsFlag;
	}

	protected abstract void newRow();

	protected void refreshRow() {
		table.refreshRow(getSelectionIndex());

		if (stackOptions.getSelectionCount() == 0) {
			setStackOption(0);
		}
	}

	protected void refreshTable() {
		table.refresh();
		tableViewer.setInput(table);

		if (getSelectionIndex() == -1 && table.getRows().size() > 0) {
			if (lastSelectedRow == -1) {
				tableViewer.getTable().setSelection(0);
			} else {
				if (lastSelectedRow >= table.getRows().size()) {
					lastSelectedRow = table.getRows().size() - 1;
				}
				tableViewer.getTable().setSelection(lastSelectedRow);
			}
		}
		updateLastRow();
	}

	protected void removeRow() {
		int row = getSelectionIndex();
		if (row != -1) {
			table.removeRow(row);
			lastEditedRow = DONT_SAVE;
			refresh();
		}
	}

	protected abstract boolean rowSelectionEvent();

	protected void saveRow() throws ValidatorException {
		if (lastEditedRow == DONT_SAVE)
			return;

		table.save(lastEditedRow);
		refresh();

		enableDisableButtonGroup();
	}

	protected void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

	protected void setMoveDownButton(Button moveDownButton) {
		this.moveDownButton = moveDownButton;
	}

	protected void setMoveUpButton(Button moveUpButton) {
		this.moveUpButton = moveUpButton;
	}

	protected void setNewButton(Button newButton) {
		this.newButton = newButton;
	}

	protected void setRemoveButton(Button removeButton) {
		this.removeButton = removeButton;
	}

	protected void setStackOption(int idx) {
		String stackKey = table.getStackKeys().get(idx);
		stackOptions.setSelection(stackOptions.getItem(idx));
		stackLayout.topControl = table.getStackControl(stackKey);
		stackComposite.layout();
	}

	protected void setViewButton(Button viewButton) {
		this.viewButton = viewButton;
	}

	protected void swapAndReselect(int idxOffset, int selectionOffset) {
		if (table.swapRows(getSelectionIndex(), idxOffset)) {
			refresh();

			Table tbl = tableViewer.getTable();
			int idx = tbl.getSelectionIndex() + idxOffset;
			tbl.select(idx + selectionOffset);
			enableDisableButtonGroup();
		}
	}

	protected void updateLastRow() {
		if (getSelectionIndex() != -1) {
			lastSelectedRow = getSelectionIndex();
		}

		lastEditedRow = getSelectionIndex();
	}

	private void rowSelection() {
		if (rowSelectionEvent())
			updateLastRow();
	}
}
