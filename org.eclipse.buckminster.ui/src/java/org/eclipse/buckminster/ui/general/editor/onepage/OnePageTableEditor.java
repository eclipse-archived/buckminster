/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.onepage;

import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author Karel Brezina
 *
 */
public class OnePageTableEditor<T> extends Composite
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
			return field == null ? "" : field.toString();
		}
	}

	private IOnePageTable<T> m_table;
	private boolean m_swapButtonsFlag;
	
	private boolean m_nodeEditMode = false;
	
	private TableViewer m_tableViewer;
	
	private Button m_newOrSaveButton;
	private Button m_editOrCancelButton;
	private Button m_removeButton;
	private Button m_moveUpButton;
	private Button m_moveDownButton;

	
	private Tree m_stackOptions;
	
	private StackLayout m_stackLayout;
	
	private Composite m_stackComposite;
	
	public OnePageTableEditor(Composite parent, IOnePageTable<T> table, boolean swapButtonsFlag, int style)
	{
		super(parent, style);
		m_table = table;
		m_swapButtonsFlag = swapButtonsFlag;
		initComposite();
	}

	private void initComposite()
	{
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = layout.marginWidth = 0;
		setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		setLayoutData(gridData);

		createTableGroup();

		createStackOptions();

		createStack();
		
		fillStackOptions();
	}
	
	private void createTableGroup()
	{
		Composite componentTableGroup = new Composite(this, SWT.NONE);
		GridLayout gl = new GridLayout(1, true);
		gl.marginHeight = gl.marginWidth = 0;
		componentTableGroup.setLayout(gl);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION);

		//table.setHeaderVisible(false);
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
				 rowSelectionEvent();
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
		
		createTableButtons(componentTableGroup);
	}

	private void createTableButtons(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.NULL);
		buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		FillLayout layout = new FillLayout(SWT.VERTICAL);
		layout.marginWidth = layout.marginHeight = 0;
		layout.spacing = 3;
		buttonBox.setLayout(layout);

		Composite buttonBox1 = new Composite(buttonBox, SWT.NULL);
		// buttonBox1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
		// false));
		layout = new FillLayout(SWT.HORIZONTAL);
		layout.marginWidth = layout.marginHeight = 0;
		buttonBox1.setLayout(layout);

		m_newOrSaveButton = UiUtils.createPushButton(buttonBox1, "New", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				newOrSaveRow();
			}
		});

		m_editOrCancelButton = UiUtils.createPushButton(buttonBox1, "Edit", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editOrCancelRow();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox1, "Remove", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeRow();
			}
		});

		if(m_swapButtonsFlag)
		{
			Composite buttonBox2 = new Composite(buttonBox, SWT.NULL);
			// buttonBox2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
			// false));
			layout = new FillLayout(SWT.HORIZONTAL);
			layout.marginWidth = layout.marginHeight = 0;
			buttonBox2.setLayout(layout);

			m_moveUpButton = UiUtils.createPushButton(buttonBox2, "Move up", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(0, -1);
				}
			});

			m_moveDownButton = UiUtils.createPushButton(buttonBox2, "Move down", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(1, 0);
				}
			});
		}
	}

	private void newOrSaveRow()
	{
		if(m_nodeEditMode)
			saveRow();
		else
			newNode();
	}

	private void newNode()
	{
		m_tableViewer.getTable().deselectAll();
		refreshRow();
		editRow();
	}

	private void editOrCancelRow()
	{
		if(m_nodeEditMode)
			cancelRow();
		else
			editRow();
	}

	private void editRow()
	{
		m_nodeEditMode = true;
		enableDisableButtonGroup();
	}

	public void cancelRow()
	{
		m_nodeEditMode = false;
		enableDisableButtonGroup();
		refreshRow();
	}

	public boolean isInEditMode()
	{
		return m_nodeEditMode;
	}
	
	private boolean saveRow()
	{
		boolean refreshListNeeded = false;
		
		try
		{
			m_table.save(getSelectionIndex());
			
			// TODO set it properly
			refreshListNeeded = true;
		}
		catch(ValidatorException e)
		{
			MessageDialog.openError(getShell(), "Error", e.getMessage());
			return false;
		}
		
		if(refreshListNeeded)
		{
			refresh();
		}

		m_nodeEditMode = false;
		enableDisableButtonGroup();
		return true;
	}
	
	private void removeRow()
	{
		int row = getSelectionIndex();
		if(row != -1)
		{
			m_table.removeRow(row);
			refresh();
		}
	}

	private int getSelectionIndex()
	{
		return m_tableViewer.getTable().getSelectionIndex();
	}
	
	private void rowSelectionEvent()
	{
		enableDisableButtonGroup();
		refreshRow();
	}

	private void swapAndReselect(int idxOffset, int selectionOffset)
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

	private void refreshRow()
	{
		m_table.refreshRow(getSelectionIndex());
	}
	
	public void refresh()
	{
		int lastSelected = getSelectionIndex();
		
		m_tableViewer.setInput(m_table);
		
		if(getSelectionIndex() == -1 && m_table.getRows().size() > 0)
		{
			if(lastSelected == -1 || lastSelected >= m_table.getRows().size())
			{
				m_tableViewer.getTable().setSelection(0);
			} else
			{
				m_tableViewer.getTable().setSelection(lastSelected);
			}
		}
		
		enableDisableButtonGroup();
		refreshRow();
		
		if(m_stackOptions.getSelectionCount() == 0)
		{
			setStackOption(0);
		}
	}

	private void setStackOption(int idx)
	{
		String stackKey = m_table.getStackKeys().get(idx);
		m_stackOptions.setSelection(m_stackOptions.getItem(idx));
		m_stackLayout.topControl = m_table.getStackControl(stackKey);
		m_stackComposite.layout();
	}
	
	private void enableDisableButtonGroup()
	{
		if(m_nodeEditMode)
		{
			// A node is being edited
			//
			m_newOrSaveButton.setText("Save");
			m_editOrCancelButton.setText("Cancel");
			m_editOrCancelButton.setEnabled(true);
			m_removeButton.setEnabled(false);
			
			if(m_swapButtonsFlag)
			{
				m_moveUpButton.setEnabled(false);
				m_moveDownButton.setEnabled(false);
			}
		}
		else
		{
			Table table = m_tableViewer.getTable();
			int top = table.getItemCount();
			int idx = table.getSelectionIndex();
			m_newOrSaveButton.setText("New");
			m_editOrCancelButton.setText("Edit");
			m_editOrCancelButton.setEnabled(idx >= 0);
			m_removeButton.setEnabled(idx >= 0);
			if(m_swapButtonsFlag)
			{
				m_moveUpButton.setEnabled(idx > 0);
				m_moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
			}
		}
		m_tableViewer.getTable().setEnabled(!m_nodeEditMode);
		m_table.enableFields(m_nodeEditMode);
	}

	private void createStackOptions()
	{
		Composite treeComposite = new Composite(this, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = layout.marginWidth = 0;
		treeComposite.setLayout(layout);
		treeComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		m_stackOptions = new Tree(treeComposite, SWT.BORDER);

		int width = m_stackOptions.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, true);
		gridData.widthHint = width + 40; // m_nodeTree.setSelection made it
		// too small
		m_stackOptions.setLayoutData(gridData);
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
				}
			}
		});
	}

	private void fillStackOptions()
	{
		for(String stackKey : m_table.getStackKeys())
		{
			TreeItem item = new TreeItem(m_stackOptions, SWT.NONE);
			item.setText(stackKey);
		}
	}
	
	private void createStack()
	{
		m_stackComposite = new Composite(this, SWT.NONE);
		m_stackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_stackLayout = new StackLayout();
		m_stackLayout.marginHeight = m_stackLayout.marginWidth = 0;
		m_stackComposite.setLayout(m_stackLayout);

		m_table.fillStack(m_stackComposite);
	}

}
