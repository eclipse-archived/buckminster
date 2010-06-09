/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Data wrapped for general table editor.
 * 
 * @author Karel Brezina
 */
public abstract class Table<T> implements ITable<T> {
	private List<T> data;

	private boolean readOnly;

	private List<ITableModifyListener<T>> listeners = new ArrayList<ITableModifyListener<T>>();

	public Table(List<T> data) {
		this(data, false);
	}

	/**
	 * Creates Table instance
	 * 
	 * @param data
	 *            input data that will be edited
	 */
	public Table(List<T> data, boolean readOnly) {
		this.data = data;
		this.readOnly = readOnly;
	}

	@Override
	public void addTableModifyListener(ITableModifyListener<T> listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public T getRow(int row) {
		return data.get(row);
	}

	@Override
	public List<T> getRows() {
		return data;
	}

	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	// no need for refreshing
	@Override
	public void refresh() {
	}

	@Override
	public void removeRow(int row) {
		T oldTableRow = data.remove(row);
		notifyListeners(TableModifyEventType.REMOVE_ROW, row, oldTableRow);
	}

	@Override
	public void removeTableModifyListener(ITableModifyListener<T> listener) {
		listeners.remove(listener);
	}

	protected void notifyListeners(TableModifyEventType eventType, int row, T tableRow) {
		TableModifyEvent<T> e = new TableModifyEvent<T>(this, eventType, row, tableRow);
		for (ITableModifyListener<T> listener : listeners)
			listener.modifyTable(e);
	}
}
