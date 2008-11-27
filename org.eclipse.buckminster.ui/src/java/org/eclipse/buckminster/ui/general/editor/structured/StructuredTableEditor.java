/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
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
public abstract class StructuredTableEditor<T> extends Composite
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
			Object field = m_table.getTableViewerField((T)element, columnIndex);
			return field == null
					? "" //$NON-NLS-1$
					: field.toString();
		}
	}

	private final static int DONT_SAVE = -99;

	private final IStructuredTable<T> m_table;

	private final boolean m_swapButtonsFlag;

	private TableViewer m_tableViewer;

	private int m_lastSelectedRow = -1;

	private int m_lastEditedRow = -1;

	private Button m_newButton;

	private Button m_editButton;

	private Button m_removeButton;

	private Button m_moveUpButton;

	private Button m_moveDownButton;

	private Tree m_stackOptions;

	private StackLayout m_stackLayout;

	private Composite m_stackComposite;

	private boolean m_enabled = true;

	public StructuredTableEditor(Composite parent, IStructuredTable<T> table, boolean swapButtonsFlag, int style)
	{
		super(parent, style);
		m_table = table;
		m_swapButtonsFlag = swapButtonsFlag;

		initComposite();
	}

	@Override
	public boolean isEnabled()
	{
		return m_enabled;
	}

	public abstract void refresh();

	public boolean selectRow(T row)
	{
		int idx = m_table.getRows().indexOf(row);

		if(idx == -1)
			return false;

		m_tableViewer.getTable().setSelection(idx);
		updateLastRow();

		return true;
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		m_enabled = enabled;
		enableDisableButtonGroup();
		m_tableViewer.getTable().setForeground(enabled
				? null
				: m_tableViewer.getTable().getDisplay().getSystemColor(SWT.COLOR_GRAY));
	}

	protected void createStack(Composite parent)
	{
		m_stackComposite = new Composite(parent, SWT.NONE);
		m_stackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_stackLayout = new StackLayout();
		m_stackLayout.marginHeight = m_stackLayout.marginWidth = 0;
		m_stackComposite.setLayout(m_stackLayout);

		m_table.fillStackComposite(m_stackComposite);
	}

	protected void createStackOptions(Composite parent)
	{
		m_stackOptions = new Tree(parent, SWT.BORDER);
		m_stackOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		m_stackOptions.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(e.item != null)
				{
					TreeItem item = (TreeItem)e.item;
					m_stackLayout.topControl = m_table.getStackControl(item.getText());
					m_stackComposite.layout();
					focusStackComposite();
				}
			}
		});
	}

	protected void createTableButtons(Composite parent)
	{
		Composite buttonBox = createTableButtonsComposite(parent);

		m_newButton = UiUtils.createPushButton(buttonBox, Messages.new_label, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				newRow();
			}
		});

		m_editButton = UiUtils.createPushButton(buttonBox, Messages.edit, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editRow();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox, Messages.remove, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeRow();
			}
		});

		if(m_swapButtonsFlag)
		{
			m_moveUpButton = UiUtils.createPushButton(buttonBox, Messages.move_up, new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(0, -1);
				}
			});

			m_moveDownButton = UiUtils.createPushButton(buttonBox, Messages.move_down, new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(1, 0);
				}
			});
		}
	}

	protected abstract Composite createTableButtonsComposite(Composite parent);

	protected void createTableGroup(Composite parent)
	{
		Composite componentTableGroup = createTableGroupComposite(parent);

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION);

		// table.setHeaderVisible(false);
		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);

		int tableIdx = 0;
		for(int idx = 0; idx < m_table.getTableViewerColumns(); idx++)
		{
			if(m_table.getTableViewerColumnWeights()[idx] > 0)
			{
				TableColumn tableColumn = new TableColumn(table, SWT.LEFT, tableIdx);
				tableColumn.setText(m_table.getTableViewerColumnHeaders()[idx]);
				layout.addColumnData(new ColumnWeightData(m_table.getTableViewerColumnWeights()[idx], true));
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
				rowSelection();
			}
		});
		m_tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				if(m_tableViewer.getTable().getSelectionIndex() >= 0)
				{
					if(m_enabled)
						editRow();
				}
			}
		});

		createTableButtons(componentTableGroup);
	}

	protected abstract Composite createTableGroupComposite(Composite parent);

	protected abstract void editRow();

	protected abstract void enableDisableButtonGroup();

	protected void enableFields(boolean enabled)
	{
		m_table.enableFields(enabled);
	}

	protected void fillStackOptions()
	{
		for(String stackKey : m_table.getStackKeys())
		{
			TreeItem item = new TreeItem(m_stackOptions, SWT.NONE);
			item.setText(stackKey);
		}
	}

	protected void focusStackComposite()
	{
		Control focusControl = (Control)m_stackLayout.topControl.getData("focusControl"); //$NON-NLS-1$
		if(focusControl != null)
		{
			focusControl.setFocus();
		}
	}

	protected Button getEditButton()
	{
		return m_editButton;
	}

	protected int getLastEditedRow()
	{
		return m_lastEditedRow;
	}

	protected int getLastSelectedRow()
	{
		return m_lastSelectedRow;
	}

	protected Button getMoveDownButton()
	{
		return m_moveDownButton;
	}

	protected Button getMoveUpButton()
	{
		return m_moveUpButton;
	}

	protected Button getNewButton()
	{
		return m_newButton;
	}

	protected Button getRemoveButton()
	{
		return m_removeButton;
	}

	protected int getSelectionIndex()
	{
		return m_tableViewer.getTable().getSelectionIndex();
	}

	protected IStructuredTable<T> getTable()
	{
		return m_table;
	}

	protected TableViewer getTableViewer()
	{
		return m_tableViewer;
	}

	protected abstract void initComposite();

	protected boolean isSwapButtonAllowed()
	{
		return m_swapButtonsFlag;
	}

	protected abstract void newRow();

	protected void refreshRow()
	{
		m_table.refreshRow(getSelectionIndex());

		if(m_stackOptions.getSelectionCount() == 0)
		{
			setStackOption(0);
		}
	}

	protected void refreshTable()
	{
		m_table.refresh();
		m_tableViewer.setInput(m_table);

		if(getSelectionIndex() == -1 && m_table.getRows().size() > 0)
		{
			if(m_lastSelectedRow == -1)
			{
				m_tableViewer.getTable().setSelection(0);
			}
			else
			{
				if(m_lastSelectedRow >= m_table.getRows().size())
				{
					m_lastSelectedRow = m_table.getRows().size() - 1;
				}
				m_tableViewer.getTable().setSelection(m_lastSelectedRow);
			}
		}
		updateLastRow();
	}

	protected void removeRow()
	{
		int row = getSelectionIndex();
		if(row != -1)
		{
			m_table.removeRow(row);
			m_lastEditedRow = DONT_SAVE;
			refresh();
		}
	}

	protected abstract boolean rowSelectionEvent();

	protected void saveRow() throws ValidatorException
	{
		if(m_lastEditedRow == DONT_SAVE)
			return;

		m_table.save(m_lastEditedRow);
		refresh();

		enableDisableButtonGroup();
	}

	protected void setEditButton(Button editButton)
	{
		m_editButton = editButton;
	}

	protected void setMoveDownButton(Button moveDownButton)
	{
		m_moveDownButton = moveDownButton;
	}

	protected void setMoveUpButton(Button moveUpButton)
	{
		m_moveUpButton = moveUpButton;
	}

	protected void setNewButton(Button newButton)
	{
		m_newButton = newButton;
	}

	protected void setRemoveButton(Button removeButton)
	{
		m_removeButton = removeButton;
	}

	protected void setStackOption(int idx)
	{
		String stackKey = m_table.getStackKeys().get(idx);
		m_stackOptions.setSelection(m_stackOptions.getItem(idx));
		m_stackLayout.topControl = m_table.getStackControl(stackKey);
		m_stackComposite.layout();
	}

	protected void swapAndReselect(int idxOffset, int selectionOffset)
	{
		if(m_table.swapRows(getSelectionIndex(), idxOffset))
		{
			refresh();

			Table table = m_tableViewer.getTable();
			int idx = table.getSelectionIndex() + idxOffset;
			table.select(idx + selectionOffset);
			enableDisableButtonGroup();
		}
	}

	protected void updateLastRow()
	{
		if(getSelectionIndex() != -1)
		{
			m_lastSelectedRow = getSelectionIndex();
		}

		m_lastEditedRow = getSelectionIndex();
	}

	private void rowSelection()
	{
		if(rowSelectionEvent())
			updateLastRow();
	}
}
