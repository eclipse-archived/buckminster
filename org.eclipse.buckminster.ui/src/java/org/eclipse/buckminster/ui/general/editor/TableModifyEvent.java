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
public class TableModifyEvent<T> {
	private ITable<T> table;

	private TableModifyEventType eventType;

	private int row;

	private T changedTableRow;

	public TableModifyEvent(ITable<T> table, TableModifyEventType eventType, int row, T changedTableRow) {
		this.table = table;
		this.eventType = eventType;
		this.row = row;
		this.changedTableRow = changedTableRow;
	}

	public T getChangedTableRow() {
		return changedTableRow;
	}

	public TableModifyEventType getEventType() {
		return eventType;
	}

	public int getRow() {
		return row;
	}

	public ITable<T> getTable() {
		return table;
	}
}
