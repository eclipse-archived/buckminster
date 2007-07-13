/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.editor;

import org.eclipse.buckminster.jnlp.ui.DynamicTableLayout;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * General table editor.
 * Needs data wrapped ITable (Table) instance. Prepare ITable instance and start using this editor.
 *   
 * @author Karel Brezina
 */
public class TableEditor<T> extends Composite
{

	class TableContentProvider implements IStructuredContentProvider
	{
		public void dispose()
		{
			// Nothing to dispose
		}

		public Object[] getElements(Object inputElement)
		{
			return m_table.getRows().toArray();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			// Nothing to do
		}
	}

	class TableLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		@SuppressWarnings("unchecked")
		public String getColumnText(Object element, int columnIndex)
		{
			Object field = m_table.getEditorField((T)element, columnIndex);
			return field == null ? "" : field.toString();
		}
	}

	private final ITable<T> m_table;
	
	private final Image m_windowImage;
	
	private final String m_windowTitle;

	private final Image m_wizardImage;
	
	private final String m_helpURL;
	
	private TableViewer m_tableViewer;

	private Button m_newButton;

	private Button m_editButton;

	private Button m_removeButton;

	/**
	 * Creates general table editor.
	 * 
	 * @param parent parent composite
	 * @param table wrapped editor data
	 * @param windowImage window icon
	 * @param windowTitle window title
	 * @param wizardImage wizard image - bypass to row editor TableRowDialog
	 * @param helpURL URL of help info - bypass to row editor TableRowDialog
	 * @param style current composite style
	 */
	public TableEditor(Composite parent, ITable<T> table, Image windowImage, String windowTitle, Image wizardImage, String helpURL, int style)
	{
		super(parent, style);
		m_table = table;
		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
		m_wizardImage = wizardImage;
		m_helpURL = helpURL;
		initComposite();
	}

	public void refresh()
	{
		m_tableViewer.setInput(m_table);
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		m_tableViewer.getTable().setEnabled(enabled);

		if(enabled)
		{
			m_newButton.setEnabled(true);
			enableDisableButtonGroup();

		}
		else
		{
			m_newButton.setEnabled(false);
			m_editButton.setEnabled(false);
			m_removeButton.setEnabled(false);
		}
	}

	private void createButtonBox(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.None);
		buttonBox.setLayout(new FillLayout(SWT.VERTICAL));
		buttonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		m_newButton = UiUtils.createPushButton(buttonBox, "New", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				newRow();
			}
		});

		m_editButton = UiUtils.createPushButton(buttonBox, "Edit", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editRow();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox, "Remove", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeRow();
			}
		});

		enableDisableButtonGroup();
	}

	private void enableDisableButtonGroup()
	{
		boolean enable = false;

		if(m_tableViewer.getTable().getSelectionIndex() >= 0)
		{
			enable = true;
		}

		m_editButton.setEnabled(enable);
		m_removeButton.setEnabled(enable);
	}

	private void initComposite()
	{
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		//table.setHeaderVisible(false);
		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);
		
		int tableIdx = 0;
		for(int idx = 0; idx < m_table.getColumns(); idx++)
		{
			if(m_table.getColumnWeights()[idx] > 0)
			{
				TableColumn tableColumn = new TableColumn(table, SWT.LEFT, tableIdx);
				tableColumn.setText(m_table.getColumnHeaders()[idx]);
				layout.addColumnData(new ColumnWeightData(m_table.getColumnWeights()[idx], true));
				tableIdx++;
			}
		}
		table.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.widthHint = 600;
		table.setLayoutData(gridData);

		m_tableViewer = new TableViewer(table);
		m_tableViewer.setLabelProvider(new TableLabelProvider());
		m_tableViewer.setContentProvider(new TableContentProvider());
		m_tableViewer.setInput(m_table);
		m_tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				enableDisableButtonGroup();
			}
		});
		m_tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				if(m_tableViewer.getTable().getSelectionIndex() >= 0)
				{
					editRow();
				}
			}
		});

		createButtonBox(this);
	}

	private void newRow()
	{
		TableRowDialog<T> dialog =
			new TableRowDialog<T>(this.getShell(), m_windowImage, m_windowTitle, m_wizardImage, m_helpURL, m_table, -1);
		
		if(dialog.open() == IDialogConstants.OK_ID)
		{
			refresh();
		}
	}

	private void editRow()
	{
		TableRowDialog<T> dialog =
			new TableRowDialog<T>(
					this.getShell(), m_windowImage, m_windowTitle, m_wizardImage, m_helpURL,
					m_table, m_tableViewer.getTable().getSelectionIndex());
		
		if(dialog.open() == IDialogConstants.OK_ID)
		{
			refresh();
		}
	}

	private void removeRow()
	{
		m_table.removeRow(m_tableViewer.getTable().getSelectionIndex());
		refresh();
	}
}
