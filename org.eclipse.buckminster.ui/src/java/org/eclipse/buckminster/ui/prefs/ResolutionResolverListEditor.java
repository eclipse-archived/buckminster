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
import org.eclipse.buckminster.ui.Messages;
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
public class ResolutionResolverListEditor extends FieldEditor {
	private String value;

	private Button addButton;

	private Button moveDownButton;

	private Button moveUpButton;

	private ListViewer queryResolvers;

	private ListViewer queryResolversToAdd;

	private String[] registeredResolverFactories;

	private Button removeButton;

	private Group resolveOrderGroup;

	public ResolutionResolverListEditor(String name, String labelText, Composite parent) {
		init(name, labelText);
		createControl(parent);
	}

	public Control getControl(Composite parent, int numColumns) {
		if (resolveOrderGroup == null) {
			resolveOrderGroup = new Group(parent, SWT.NONE);
			resolveOrderGroup.setLayout(new GridLayout(3, false));
			resolveOrderGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, numColumns, 1));
			resolveOrderGroup.setText(Messages.order_of_resolution);
			queryResolvers = new ListViewer(resolveOrderGroup, SWT.BORDER);
			queryResolvers.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			queryResolvers.setContentProvider(new ArrayContentProvider());
			queryResolvers.setLabelProvider(new LabelProvider());
			queryResolvers.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					enableDisableUpDownButtons(true);
				}
			});

			Composite buttonBox = new Composite(resolveOrderGroup, SWT.NONE);
			buttonBox.setLayout(new GridLayout(1, true));
			buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));

			addButton = UiUtils.createPushButton(buttonBox, Messages.add_with_arrow_left, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					addResolver();
				}
			});
			addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			removeButton = UiUtils.createPushButton(buttonBox, Messages.remove_with_arrow_right, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					removeResolver();
				}
			});
			removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			moveUpButton = UiUtils.createPushButton(buttonBox, Messages.move_up, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					swapAndReselect(0, -1);
				}
			});
			moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			moveDownButton = UiUtils.createPushButton(buttonBox, Messages.move_down, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					swapAndReselect(1, 0);
				}
			});
			moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			queryResolversToAdd = new ListViewer(resolveOrderGroup, SWT.BORDER);
			queryResolversToAdd.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			queryResolversToAdd.setContentProvider(new ArrayContentProvider());
			queryResolversToAdd.setLabelProvider(new LabelProvider());
			queryResolversToAdd.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					enableDisableUpDownButtons(false);
				}
			});
		}
		return resolveOrderGroup;
	}

	@Override
	public int getNumberOfControls() {
		return 1;
	}

	@Override
	protected void adjustForNumColumns(int numColumns) {
		((GridData) resolveOrderGroup.getLayoutData()).horizontalSpan = numColumns;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		getControl(parent, numColumns);
	}

	@Override
	protected void doLoad() {
		loadLists(getPreferenceStore().getString(getPreferenceName()));
	}

	@Override
	protected void doLoadDefault() {
		ResolverFactoryMaintainer.getInstance().setDefaultResolutionOrder();
		loadLists(getPreferenceStore().getString(getPreferenceName()));
	}

	@Override
	protected void doStore() {
		String v = BuckminsterPreferences.createQueryResolverSortOrder((queryResolvers == null) ? getRegisteredResolverFactories() : queryResolvers
				.getList().getItems());

		IPreferenceStore store = getPreferenceStore();
		String prefName = getPreferenceName();
		if (v == null) {
			store.setToDefault(prefName);
			BuckminsterPreferences.setCustomQueryResolverSortOrder(false);
		} else {
			if (v.equals(store.getString(prefName)))
				return;

			store.setValue(prefName, v);
			BuckminsterPreferences.setCustomQueryResolverSortOrder(true);
		}
	}

	void addResolver() {
		moveItem(queryResolversToAdd, queryResolvers);
	}

	void enableDisableUpDownButtons(boolean fromResolvers) {
		String oldValue = value;
		List list = queryResolvers.getList();
		int top = list.getItemCount();
		int idx = list.getSelectionIndex();
		if (fromResolvers) {
			value = idx >= 0 ? list.getItem(idx) : null;
			if (!Trivial.equalsAllowNull(oldValue, value))
				fireValueChanged(VALUE, oldValue, value);
		}

		List addList = queryResolversToAdd.getList();
		addButton.setEnabled(addList.getItemCount() > 0 && addList.getSelectionIndex() >= 0);

		moveUpButton.setEnabled(idx > 0);
		removeButton.setEnabled(idx >= 0);
		moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
	}

	void removeResolver() {
		moveItem(queryResolvers, queryResolversToAdd);
	}

	void swapAndReselect(int idxOffset, int selectionOffset) {
		List list = queryResolvers.getList();
		int idx = list.getSelectionIndex() + idxOffset;
		if (idx <= 0)
			return;

		String[] items = list.getItems();
		if (idx >= items.length)
			return;

		String moved = items[idx - 1];
		items[idx - 1] = items[idx];
		items[idx] = moved;
		list.setItems(items);
		list.select(idx + selectionOffset);
		enableDisableUpDownButtons(true);
	}

	private String[] getPossibleResolverAdditions() {
		String[] registered = getRegisteredResolverFactories();
		String[] current = queryResolvers.getList().getItems();
		ArrayList<String> possible = null;
		for (String name : registered) {
			boolean found = false;
			for (String currName : current)
				if (currName.equals(name)) {
					found = true;
					break;
				}
			if (found)
				continue;
			if (possible == null)
				possible = new ArrayList<String>();
			possible.add(name);
		}
		return possible == null ? Trivial.EMPTY_STRING_ARRAY : possible.toArray(new String[possible.size()]);
	}

	private synchronized String[] getRegisteredResolverFactories() {
		if (registeredResolverFactories == null)
			registeredResolverFactories = ResolverFactoryMaintainer.getRegisterFactoryIDs();
		return registeredResolverFactories;
	}

	private void loadLists(String prefValue) {
		String[] resolvers = (prefValue == null || prefValue.length() == 0) ? Trivial.EMPTY_STRING_ARRAY : prefValue.split(","); //$NON-NLS-1$
		queryResolvers.setInput(resolvers);
		queryResolvers.getList().select(0);
		queryResolversToAdd.setInput(getPossibleResolverAdditions());
		enableDisableUpDownButtons(true);
	}

	private void moveItem(ListViewer from, ListViewer to) {
		List list = from.getList();
		int idx = list.getSelectionIndex();
		if (idx < 0)
			return;

		to.add(list.getItem(idx));
		list.remove(idx);
		int top = list.getItemCount();
		if (idx >= top)
			idx = top - 1;
		if (idx >= 0)
			list.select(idx);
		enableDisableUpDownButtons(true);
	}
}
