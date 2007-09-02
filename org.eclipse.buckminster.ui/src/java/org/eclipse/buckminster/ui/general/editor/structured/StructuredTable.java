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

import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;
import org.eclipse.buckminster.ui.general.editor.Table;
import org.eclipse.buckminster.ui.general.editor.TableModifyEvent;
import org.eclipse.buckminster.ui.general.editor.TableModifyEventType;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Karel Brezina
 *
 */
public abstract class StructuredTable<T> extends Table<T> implements IStructuredTable<T>
{
	@SuppressWarnings("unchecked")
	class CompoundFieldModifyListener implements ModifyListener, SelectionListener, ITableModifyListener
	{
		public void modifyText(ModifyEvent e)
		{
			if(!m_suppressFieldListener)
				notifyFieldListeners();
		}

		public void widgetDefaultSelected(SelectionEvent e)
		{
			// nothing			
		}

		public void widgetSelected(SelectionEvent e)
		{
			if(!m_suppressFieldListener)
				notifyFieldListeners();
		}

		public void modifyTable(TableModifyEvent e)
		{
			notifyFieldListeners();
		}
	}
	
	protected final CompoundFieldModifyListener FIELD_LISTENER = new CompoundFieldModifyListener();
	
	private List<String> m_stackKeys = new ArrayList<String>();

	private Map<String, Control> m_stackMap = new HashMap<String, Control>();

	private List<IFieldModifyListener> m_fieldListeners = new ArrayList<IFieldModifyListener>();

	private boolean m_suppressFieldListener = false;
	
	/**
	 * Creates Table instance
	 * 
	 * @param data input data that will be edited
	 */
	public StructuredTable(List<T> data)
	{
		super(data);
		
		// notify field listeners about row removal
		addTableModifyListener(new ITableModifyListener<T>()
		{
			public void modifyTable(TableModifyEvent<T> e)
			{
				if(e.getEventType() == TableModifyEventType.REMOVE_ROW)
					notifyFieldListeners();
			}
		});
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

		m_suppressFieldListener = true;
		refreshRow(builder);
		m_suppressFieldListener = false;
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
	
	public void addFieldModifyListener(IFieldModifyListener listener)
	{
		if(!m_fieldListeners.contains(listener))
		{
			m_fieldListeners.add(listener);
		}
	}

	public void removeFieldModifyListener(IFieldModifyListener listener)
	{
		m_fieldListeners.remove(listener);
	}

	protected void notifyFieldListeners()
	{
		FieldModifyEvent e = new FieldModifyEvent();
		for(IFieldModifyListener listener : m_fieldListeners)
			listener.modifyField(e);
	}
	
	public T addEmptyRow()
	{
		T tableRow = createNewRow();
		getRows().add(tableRow);
		
		return tableRow;
	}
}
