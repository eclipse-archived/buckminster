/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * General table editor. Needs data wrapped ITable (Table) instance. Prepare
 * ITable instance and start using this editor.
 * 
 * @author Karel Brezina
 */
public class SimpleTableEditor<T> extends Composite {

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
			Object field = table.getEditorField((T) element, columnIndex);
			return field == null ? "" : field.toString(); //$NON-NLS-1$
		}
	}

	private final ISimpleTable<T> table;

	private final Image windowImage;

	private final String windowTitle;

	private final Image wizardImage;

	private final String helpURL;

	private TableViewer tableViewer;

	private Composite stackButtonComposite;

	private StackLayout stackButtonLayout;

	private Composite editButtonBox;

	private Composite viewButtonBox;

	private Button newButton;

	private Button editButton;

	private Button viewButton;

	private Button removeButton;

	private boolean enabled = true;

	/**
	 * Creates general table editor.
	 * 
	 * @param parent
	 *            parent composite
	 * @param table
	 *            wrapped editor data
	 * @param windowImage
	 *            window icon
	 * @param windowTitle
	 *            window title
	 * @param wizardImage
	 *            wizard image - bypass to row editor TableRowDialog
	 * @param helpURL
	 *            URL of help info - bypass to row editor TableRowDialog
	 * @param style
	 *            current composite style
	 */
	public SimpleTableEditor(Composite parent, ISimpleTable<T> table, Image windowImage, String windowTitle, Image wizardImage, String helpURL,
			int style) {
		super(parent, style);
		this.table = table;
		this.windowImage = windowImage;
		this.windowTitle = windowTitle;
		this.wizardImage = wizardImage;
		this.helpURL = helpURL;
		initComposite();
	}

	public void refresh() {
		tableViewer.setInput(table);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

		// tableViewer.getTable().setEnabled(enabled);

		enableDisableButtonGroup();

		// tableViewer.getTable().setForeground(enabled
		// ? null
		// :
		// tableViewer.getTable().getDisplay().getSystemColor(SWT.COLOR_GRAY));
	}

	private void createButtonBox(Composite parent) {
		stackButtonComposite = new Composite(parent, SWT.NONE);
		stackButtonComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		stackButtonLayout = new StackLayout();
		stackButtonLayout.marginHeight = stackButtonLayout.marginWidth = 0;
		stackButtonComposite.setLayout(stackButtonLayout);

		editButtonBox = new Composite(stackButtonComposite, SWT.None);
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
				editRow(false);
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

		viewButtonBox = new Composite(stackButtonComposite, SWT.NONE);
		gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		viewButtonBox.setLayout(gridLayout);
		viewButtonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		viewButton = UiUtils.createPushButton(viewButtonBox, Messages.view, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editRow(true);
			}
		});
		viewButton.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		enableDisableButtonGroup();
	}

	private void editRow(boolean readOnly) {
		SimpleTableRowDialog<T> dialog = new SimpleTableRowDialog<T>(this.getShell(), windowImage, windowTitle, wizardImage, helpURL, table,
				tableViewer.getTable().getSelectionIndex(), readOnly);

		if (dialog.open() == IDialogConstants.OK_ID) {
			refresh();
		}
	}

	private void enableDisableButtonGroup() {
		boolean rowSelected = tableViewer.getTable().getSelectionIndex() >= 0;

		if (enabled && !table.isReadOnly()) {
			newButton.setEnabled(true);
			editButton.setEnabled(rowSelected);
			removeButton.setEnabled(rowSelected);

			stackButtonLayout.topControl = editButtonBox;
		} else {
			newButton.setEnabled(false);
			editButton.setEnabled(false);
			removeButton.setEnabled(false);

			stackButtonLayout.topControl = viewButtonBox;
		}

		viewButton.setEnabled(rowSelected);
		stackButtonComposite.layout();
	}

	private void initComposite() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table tbl = new Table(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		// table.setHeaderVisible(false);
		tbl.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);

		int tableIdx = 0;
		for (int idx = 0; idx < table.getColumns(); idx++) {
			if (table.getColumnWeights()[idx] > 0) {
				TableColumn tableColumn = new TableColumn(tbl, SWT.LEFT, tableIdx);
				tableColumn.setText(table.getColumnHeaders()[idx]);
				layout.addColumnData(new ColumnWeightData(table.getColumnWeights()[idx], true));
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
				enableDisableButtonGroup();
			}
		});
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (tableViewer.getTable().getSelectionIndex() >= 0)
					editRow(!enabled || table.isReadOnly());
			}
		});

		createButtonBox(this);
	}

	private void newRow() {
		SimpleTableRowDialog<T> dialog = new SimpleTableRowDialog<T>(this.getShell(), windowImage, windowTitle, wizardImage, helpURL, table, -1,
				false);

		if (dialog.open() == IDialogConstants.OK_ID) {
			refresh();
		}
	}

	private void removeRow() {
		table.removeRow(tableViewer.getTable().getSelectionIndex());
		refresh();
	}
}
