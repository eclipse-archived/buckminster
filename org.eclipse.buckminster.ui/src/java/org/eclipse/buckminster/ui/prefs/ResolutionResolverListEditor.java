/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.ui.prefs;

import java.util.ArrayList;

import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;

/**
 * @author Thomas Hallgren
 *
 */
public class ResolutionResolverListEditor extends FieldEditor
{
	private String m_value;

	private Button m_addButton;

	private Button m_moveDownButton;

	private Button m_moveUpButton;

	private ListViewer m_queryResolvers;

	private ListViewer m_queryResolversToAdd;

	private String[] m_registeredResolverFactories;

	private Button m_removeButton;

	private Group m_resolveOrderGroup;

	public ResolutionResolverListEditor(String name, String labelText, Composite parent)
	{
		init(name, labelText);
		createControl(parent);
	}

	public Control getControl(Composite parent, int numColumns)
	{
		if(m_resolveOrderGroup == null)
		{
			m_resolveOrderGroup = new Group(parent, SWT.NONE);
			m_resolveOrderGroup.setLayout(new GridLayout(3, false));
			m_resolveOrderGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, numColumns, 1));
			m_resolveOrderGroup.setText("Order of resolution");
			m_queryResolvers = new ListViewer(m_resolveOrderGroup, SWT.BORDER);
			m_queryResolvers.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			m_queryResolvers.setContentProvider(new ArrayContentProvider());
			m_queryResolvers.setLabelProvider(new LabelProvider());
			m_queryResolvers.addSelectionChangedListener(new ISelectionChangedListener()
			{
				public void selectionChanged(SelectionChangedEvent event)
				{
					enableDisableUpDownButtons(true);
				}
			});

			Composite buttonBox = new Composite(m_resolveOrderGroup, SWT.NONE);
			buttonBox.setLayout(new GridLayout(1, true));
			buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));
	
			m_addButton = UiUtils.createPushButton(buttonBox, "<-- Add", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					addResolver();
				}
			});
			m_addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	
			m_removeButton = UiUtils.createPushButton(buttonBox, "Remove -->", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					removeResolver();
				}
			});
			m_removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	
			m_moveUpButton = UiUtils.createPushButton(buttonBox, "Move up", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(0, -1);
				}
			});
			m_moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	
			m_moveDownButton = UiUtils.createPushButton(buttonBox, "Move down", new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					swapAndReselect(1, 0);
				}
			});
			m_moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	
			m_queryResolversToAdd = new ListViewer(m_resolveOrderGroup, SWT.BORDER);
			m_queryResolversToAdd.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			m_queryResolversToAdd.setContentProvider(new ArrayContentProvider());
			m_queryResolversToAdd.setLabelProvider(new LabelProvider());
			m_queryResolversToAdd.addSelectionChangedListener(new ISelectionChangedListener()
			{
				public void selectionChanged(SelectionChangedEvent event)
				{
					enableDisableUpDownButtons(false);
				}
			});
		}
		return m_resolveOrderGroup;
	}

	@Override
	public int getNumberOfControls()
	{
		return 1;
	}

	@Override
	protected void adjustForNumColumns(int numColumns)
	{
		((GridData)m_resolveOrderGroup.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns)
	{
		getControl(parent, numColumns);
	}

	@Override
	protected void doLoad()
	{
		loadLists(getPreferenceStore().getString(getPreferenceName()));
	}

	@Override
	protected void doLoadDefault()
	{
		ResolverFactoryMaintainer.getInstance().setDefaultResolutionOrder();
		loadLists(getPreferenceStore().getString(getPreferenceName()));
	}

	@Override
	protected void doStore()
	{
		String value = BuckminsterPreferences.createQueryResolverSortOrder((m_queryResolvers == null)
				? getRegisteredResolverFactories()
				: m_queryResolvers.getList().getItems());

		IPreferenceStore store = getPreferenceStore();
		String prefName = getPreferenceName();
		if(value == null)
		{
			store.setToDefault(prefName);
			BuckminsterPreferences.setCustomQueryResolverSortOrder(false);
		}
		else
		{
			if(value.equals(store.getString(prefName)))
				return;

			store.setValue(prefName, value);
			BuckminsterPreferences.setCustomQueryResolverSortOrder(true);
		}
	}

	void addResolver()
	{
		moveItem(m_queryResolversToAdd, m_queryResolvers);
	}

	void enableDisableUpDownButtons(boolean fromResolvers)
	{
		String oldValue = m_value;
		List list = m_queryResolvers.getList();
		int top = list.getItemCount();
		int idx = list.getSelectionIndex();
		if(fromResolvers)
		{
			m_value = idx >= 0 ? list.getItem(idx) : null;
			if(!Trivial.equalsAllowNull(oldValue, m_value))
				fireValueChanged(VALUE, oldValue, m_value);
		}

		List addList = m_queryResolversToAdd.getList();
		m_addButton.setEnabled(addList.getItemCount() > 0 && addList.getSelectionIndex() >= 0);

		m_moveUpButton.setEnabled(idx > 0);
		m_removeButton.setEnabled(idx >= 0);
		m_moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
	}

	void removeResolver()
	{
		moveItem(m_queryResolvers, m_queryResolversToAdd);
	}

	void swapAndReselect(int idxOffset, int selectionOffset)
	{
		List list = m_queryResolvers.getList();
		int idx = list.getSelectionIndex() + idxOffset;
		if(idx <= 0)
			return;

		String[] items = list.getItems();
		if(idx >= items.length)
			return;

		String moved = items[idx - 1];
		items[idx - 1] = items[idx];
		items[idx] = moved;
		list.setItems(items);
		list.select(idx + selectionOffset);
		enableDisableUpDownButtons(true);
	}

	private String[] getPossibleResolverAdditions()
	{
		String[] registered = getRegisteredResolverFactories();
		String[] current = m_queryResolvers.getList().getItems();
		ArrayList<String> possible = null;
		for(String name : registered)
		{
			boolean found = false;
			for(String currName : current)
				if(currName.equals(name))
				{
					found = true;
					break;
				}
			if(found)
				continue;
			if(possible == null)
				possible = new ArrayList<String>();
			possible.add(name);
		}
		return possible == null
				? Trivial.EMPTY_STRING_ARRAY
				: possible.toArray(new String[possible.size()]);
	}

	private synchronized String[] getRegisteredResolverFactories()
	{
		if(m_registeredResolverFactories == null)
			m_registeredResolverFactories = ResolverFactoryMaintainer.getRegisterFactoryIDs();
		return m_registeredResolverFactories;
	}

	private void loadLists(String prefValue)
	{
		String[] resolvers = (prefValue == null || prefValue.length() == 0) ? Trivial.EMPTY_STRING_ARRAY : prefValue.split(",");
		m_queryResolvers.setInput(resolvers);
		m_queryResolvers.getList().select(0);
		m_queryResolversToAdd.setInput(getPossibleResolverAdditions());
		enableDisableUpDownButtons(true);
	}

	private void moveItem(ListViewer from, ListViewer to)
	{
		List list = from.getList();
		int idx = list.getSelectionIndex();
		if(idx < 0)
			return;

		to.add(list.getItem(idx));
		list.remove(idx);
		int top = list.getItemCount();
		if(idx >= top)
			idx = top - 1;
		if(idx >= 0)
			list.select(idx);
		enableDisableUpDownButtons(true);
	}
}
