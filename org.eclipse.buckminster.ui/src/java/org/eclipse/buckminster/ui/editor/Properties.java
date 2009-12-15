/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.dialogs.Property;
import org.eclipse.buckminster.ui.dialogs.PropertyDialog;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.osgi.util.NLS;
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

	private List<Property> m_properties;

	private TableViewer m_tableViewer;

	private Button m_newButton;

	private Button m_editButton;

	private Button m_removeButton;

	private final ArrayList<PropertiesModifyListener> m_listeners = new ArrayList<PropertiesModifyListener>();

	public Properties(Composite parent, int style)
	{
		super(parent, style);
		m_properties = Collections.emptyList();
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

	public List<Property> getProperties()
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
			catch(CoreException e)
			{
				addPropertyErrorDialog(e);
			}
		}
	}

	private void addProperty(Property newProp) throws CoreException
	{
		String key = newProp.getKey();

		int idx = -1;

		for(Property prop : m_properties)
		{
			if(prop.getKey().compareTo(key) == 0)
			{
				throw BuckminsterException.fromMessage(NLS.bind(Messages.duplicity_of_key_0, key));
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
		MessageDialog.openError(this.getShell(), Messages.error, NLS.bind(Messages.a_0_property_will_not_be_added,
				e.getMessage()));
	}

	private void createButtonBox(Composite parent)
	{
		Composite buttonBox = new Composite(parent, SWT.None);
		buttonBox.setLayout(new FillLayout(SWT.VERTICAL));
		buttonBox.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

		m_newButton = UiUtils.createPushButton(buttonBox, Messages.new_label, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				newProperty();
			}
		});

		m_editButton = UiUtils.createPushButton(buttonBox, Messages.edit, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				editProperty();
			}
		});

		m_removeButton = UiUtils.createPushButton(buttonBox, Messages.remove, new SelectionAdapter()
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
		PropertyDialog dialog = new PropertyDialog(this.getShell(), getSelectedProperty());
		int idx = m_tableViewer.getTable().getSelectionIndex();

		if(dialog.open() == IDialogConstants.OK_ID)
		{
			m_properties.set(idx, dialog.getProperty());
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

		String[] columnNames = new String[] { Messages.key, Messages.value };
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
		PropertyDialog dialog = new PropertyDialog(this.getShell(), null);
		if(dialog.open() == IDialogConstants.OK_ID)
		{
			try
			{
				addProperty(dialog.getProperty());
				notifyListeners();
				refreshList();
			}
			catch(CoreException e)
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
