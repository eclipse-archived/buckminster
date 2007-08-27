/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ui.general.editor.Table;
import org.eclipse.buckminster.ui.general.editor.TableModifyEventType;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Karel Brezina
 *
 */
public abstract class StructuredTable<T> extends Table<T> implements IStructuredTable<T>
{
	private List<String> m_stackKeys = new ArrayList<String>();

	private Map<String, Control> m_stackMap = new HashMap<String, Control>();

	/**
	 * Creates Table instance
	 * 
	 * @param data input data that will be edited
	 */
	public StructuredTable(List<T> data)
	{
		super(data);
	}

	public Control getStackControl(String stackKey)
	{
		return m_stackMap.get(stackKey);
	}

	private void clearStackMapping()
	{
		m_stackKeys.clear();
		m_stackMap.clear();
	}

	public List<String> getStackKeys()
	{
		return m_stackKeys;
	}

	public void fillStackComposite(Composite stackComposite)
	{
		clearStackMapping();
		fillStack(stackComposite);
	}

	/**
	 * Fills controls to the stack composite
	 * 
	 * @param stackComposite
	 */
	protected abstract void fillStack(Composite stackComposite);

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
			T tableRow = createNewRow();
			getRows().add(tableRow);

			try
			{
				setRowValues(tableRow);
			}
			catch(ValidatorException e)
			{
				getRows().remove(tableRow);
				throw e;
			}

			notifyListeners(TableModifyEventType.ADD_ROW, getRows().size() - 1, tableRow);
		}
		else
		{
			T tableRow = getRows().get(rowIdx);
			setRowValues(tableRow);

			notifyListeners(TableModifyEventType.UPDATE_ROW, rowIdx, tableRow);
		}
	}

	/**
	 * Creates new row
	 * 
	 * @return new row
	 */
	protected abstract T createNewRow();

	/**
	 * Sets values from controls to row
	 * 
	 * @param row data row
	 * @throws ValidatorException
	 */
	protected abstract void setRowValues(T row) throws ValidatorException;

	public void refreshRow(int rowIdx)
	{
		T builder;

		if(rowIdx == -1)
		{
			builder = createNewRow();
		}
		else
		{
			builder = getRow(rowIdx);
		}

		refreshRow(builder);
	}

	/**
	 * Refreshes controls from a given data row
	 * 
	 * @param builder
	 */
	protected abstract void refreshRow(T builder);

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
		notifyListeners(TableModifyEventType.SWAP_ROW, newIdx, data.get(newIdx));
		notifyListeners(TableModifyEventType.SWAP_ROW, rowIdx, data.get(rowIdx));

		return true;
	}
}
