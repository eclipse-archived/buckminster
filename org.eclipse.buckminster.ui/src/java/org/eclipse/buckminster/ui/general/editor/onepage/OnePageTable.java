/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.onepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ui.general.editor.Table;
import org.eclipse.buckminster.ui.general.editor.TableModifyEventType;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.widgets.Control;

/**
 * @author Karel Brezina
 *
 */
public abstract class OnePageTable<T> extends Table<T> implements IOnePageTable<T>
{
	private List<String> m_stackKeys = new ArrayList<String>();
	private Map<String, Control> m_stackMap = new HashMap<String, Control>();
	
	/**
	 * Creates Table instance
	 * 
	 * @param data input data that will be edited
	 */
	public OnePageTable(List<T> data)
	{
		super(data);
	}

	public Control getStackControl(String stackKey)
	{
		return m_stackMap.get(stackKey);
	}

	public List<String> getStackKeys()
	{
		return m_stackKeys;
	}

	protected void addStackMapping(String key, Control control)
	{
		m_stackKeys.add(key);
		m_stackMap.put(key, control);
	}

	public int getTableViewerColumns()
	{
		return getTableViewerColumnHeaders().length;
	}

	public void save(int rowIdx) throws ValidatorException
	{
		if(rowIdx == -1)
		{
			T newTableRow = addRow();
			notifyListeners(TableModifyEventType.ADD_ROW, getRows().size() - 1, null, newTableRow);
		} else
		{
			T oldTableRow = getRow(rowIdx);
			T newTableRow = updateRow(rowIdx);
			notifyListeners(TableModifyEventType.UPDATE_ROW, rowIdx, oldTableRow, newTableRow);
		}
	}

	protected abstract T addRow() throws ValidatorException;

	protected abstract T updateRow(int rowIdx) throws ValidatorException;

	public boolean swapRows(int rowIdx, int idxOffset)
	{
		int idx = rowIdx + idxOffset;
		if(idx <= 0)
			return false;

		List<T> data = getRows();
		if(idx >= data.size())
		{
			return false;
		}

		data.set(idx - 1, data.set(idx, data.get(idx - 1)));
		
		int newIdx = rowIdx + idxOffset;
		
		if(idxOffset == 0)
		{
			newIdx = rowIdx - 1;
		}
		notifyListeners(TableModifyEventType.SWAP_ROW, newIdx, data.get(rowIdx), data.get(newIdx));
		
		return true;
	}
}
