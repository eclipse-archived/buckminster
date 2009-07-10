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
public abstract class Table<T> implements ITable<T>
{
	private List<T> m_data;

	private boolean m_readOnly;

	private List<ITableModifyListener<T>> m_listeners = new ArrayList<ITableModifyListener<T>>();

	public Table(List<T> data)
	{
		this(data, false);
	}

	/**
	 * Creates Table instance
	 * 
	 * @param data
	 *            input data that will be edited
	 */
	public Table(List<T> data, boolean readOnly)
	{
		m_data = data;
		m_readOnly = readOnly;
	}

	public void addTableModifyListener(ITableModifyListener<T> listener)
	{
		if(!m_listeners.contains(listener))
		{
			m_listeners.add(listener);
		}
	}

	public T getRow(int row)
	{
		return m_data.get(row);
	}

	public List<T> getRows()
	{
		return m_data;
	}

	public boolean isReadOnly()
	{
		return m_readOnly;
	}

	// no need for refreshing
	public void refresh()
	{
	}

	public void removeRow(int row)
	{
		T oldTableRow = m_data.remove(row);
		notifyListeners(TableModifyEventType.REMOVE_ROW, row, oldTableRow);
	}

	public void removeTableModifyListener(ITableModifyListener<T> listener)
	{
		m_listeners.remove(listener);
	}

	protected void notifyListeners(TableModifyEventType eventType, int row, T tableRow)
	{
		TableModifyEvent<T> e = new TableModifyEvent<T>(this, eventType, row, tableRow);
		for(ITableModifyListener<T> listener : m_listeners)
			listener.modifyTable(e);
	}
}
