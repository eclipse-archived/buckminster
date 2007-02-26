/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.UiUtils;
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
 * @author Karel Brezina
 * 
 */
public class Properties extends Composite
{

	// TODO add listener to detect changes on properties

	class PropertiesContentProvider implements IStructuredContentProvider
	{
		public void dispose()
		{
			// Nothing to dispose
		}

		public Object[] getElements(Object inputElement)
		{
			return m_properties.toArray();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			// Nothing to do
		}
	}

	class PropertiesLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			Property prop = (Property)element;
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = prop.getKey().toString();
				break;
			case 1:
				lbl = prop.getValue();
				break;
			default:
				lbl = null;
			}
			return lbl;
		}
	}

	private ArrayList<Property> m_properties;

	private TableViewer m_tableViewer;

	private Button m_newButton;

	private Button m_editButton;

	private Button m_removeButton;

	private final ArrayList<PropertiesModifyListener> m_listeners = new ArrayList<PropertiesModifyListener>();

	public Properties(Composite parent, int style)
	{
		super(parent, style);

		initComposite();
	}

	public void addPropertiesModifyListener(PropertiesModifyListener listener)
	{
		if(!m_listeners.contains(listener))
		{
			m_listeners.add(listener);
		}
	}

	public void fillProperties(Map<String, String> mappedProperties)
	{
		mappedProperties.clear();

		for(Property prop : m_properties)
		{
			mappedProperties.put(prop.getKey(), prop.getValue());
		}
	}

	public ArrayList<Property> getProperties()
	{
		return m_properties;
	}

	public void refreshList()
	{
		m_tableViewer.setInput(m_properties);
	}

	public void removePropertiesModifyListener(PropertiesModifyListener listener)
	{
		m_listeners.remove(listener);
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

	public void setProperties(Map<String, String> mappedProperties)
	{
		m_properties = new ArrayList<Property>();

		for(String key : mappedProperties.keySet())
		{
			Property newProp = new Property(key, mappedProperties.get(key));

			try
			{
				addProperty(newProp);
			}
			catch(BuckminsterException e)
			{
				addPropertyErrorDialog(e);
			}
		}
	}

	private void addProperty(Property newProp) throws BuckminsterException
	{
		String key = newProp.getKey();

		int idx = -1;

		for(Property prop : m_properties)
		{
			if(prop.getKey().compareTo(key) == 0)
			{
				throw new BuckminsterException("Duplicity of key '" + key + "'");
			}
			if(prop.getKey().compareTo(key) > 0)
			{
				idx = m_properties.indexOf(prop);
				break;
			}
		}

		if(idx >= 0)
		{
			m_properties.add(idx, newProp);
		}
		else
		{
			m_properties.add(newProp);
		}
	}

	private void addPropertyErrorDialog(Throwable e)
	{
		MessageDialog.openError(this.getShell(), "Error", e.getMessage() + " - property will not be added");
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
				newProperty();
			}
		});

		m_editButton = UiUtils.createPushButton(buttonBox, "Edit", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editProperty();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox, "Remove", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeProperty();
			}
		});

		enableDisableButtonGroup();
	}

	private void editProperty()
	{
		PropertyDialog dialog = new PropertyDialog(this.getShell(), false);
		Property prop = getSelectedProperty();
		int idx = m_tableViewer.getTable().getSelectionIndex();
		dialog.setInput(prop);

		prop = dialog.open();

		if(prop != null)
		{
			m_properties.set(idx, prop);
			notifyListeners();
			refreshList();
		}
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

	private Property getSelectedProperty()
	{
		int idx = m_tableViewer.getTable().getSelectionIndex();
		return idx >= 0
				? (Property)m_tableViewer.getElementAt(idx)
				: null;
	}

	private void initComposite()
	{
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		table.setHeaderVisible(false);

		String[] columnNames = new String[] { "Key", "Value" };
		int[] columnWeights = new int[] { 5, 15 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);
		for(int idx = 0; idx < columnNames.length; idx++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.widthHint = 600;
		table.setLayoutData(gridData);

		m_tableViewer = new TableViewer(table);
		m_tableViewer.setLabelProvider(new PropertiesLabelProvider());
		m_tableViewer.setContentProvider(new PropertiesContentProvider());
		m_tableViewer.setInput(m_properties);
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
					editProperty();
				}
			}
		});

		createButtonBox(this);
	}

	private void newProperty()
	{
		PropertyDialog dialog = new PropertyDialog(this.getShell(), true);
		Property newProp = dialog.open();
		if(newProp != null)
		{
			try
			{
				addProperty(newProp);
				notifyListeners();
				refreshList();
			}
			catch(BuckminsterException e)
			{
				addPropertyErrorDialog(e);
			}
		}
	}

	private void notifyListeners()
	{
		PropertiesModifyEvent e = new PropertiesModifyEvent(this);
		for(PropertiesModifyListener listener : m_listeners)
			listener.modifyProperties(e);
	}

	private void removeProperty()
	{
		Property prop = getSelectedProperty();
		m_properties.remove(prop);
		notifyListeners();
		refreshList();
	}
}
