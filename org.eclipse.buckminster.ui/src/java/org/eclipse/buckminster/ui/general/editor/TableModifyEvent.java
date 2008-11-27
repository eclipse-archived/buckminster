/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

/**
 * @author Karel Brezina
 * 
 */
public class TableModifyEvent<T>
{
	private ITable<T> m_table;

	private TableModifyEventType m_eventType;

	private int m_row;

	private T m_changedTableRow;

	public TableModifyEvent(ITable<T> table, TableModifyEventType eventType, int row, T changedTableRow)
	{
		m_table = table;
		m_eventType = eventType;
		m_row = row;
		m_changedTableRow = changedTableRow;
	}

	public T getChangedTableRow()
	{
		return m_changedTableRow;
	}

	public TableModifyEventType getEventType()
	{
		return m_eventType;
	}

	public int getRow()
	{
		return m_row;
	}

	public ITable<T> getTable()
	{
		return m_table;
	}
}
