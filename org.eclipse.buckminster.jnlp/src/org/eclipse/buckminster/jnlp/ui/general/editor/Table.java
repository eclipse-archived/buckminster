/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public abstract class Table<T> implements ITable<T>
{
	private static IValidator s_emptyValidator = new IValidator()
	{
		public void validate(Object... arg)
		{
		}
	};

	public static IValidator getEmptyValidator()
	{
		return s_emptyValidator;
	}

	private List<T> m_data;

	private List<ITableModifyListener<T>> m_listeners = new ArrayList<ITableModifyListener<T>>();

	public Table(List<T> data)
	{
		m_data = data;
	}

	public void addRow(Object[] tableRow) throws ValidatorException
	{
		validateAllFields(tableRow);
		getRowValidator().validate(new Integer(-1), tableRow);
		T newTableRow = toRowClass(tableRow);
		m_data.add(newTableRow);
		notifyListeners(TableModifyEventType.ADD_ROW, m_data.size() - 1, null, newTableRow);
	}

	private void validateAllFields(Object[] tableRow) throws ValidatorException
	{
		for(int i = 0; i < getColumns(); i++)
		{
				getFieldValidator(i).validate(tableRow[i]);
		}
	}
	
	public void addTableModifyListener(ITableModifyListener<T> listener)
	{
		if(!m_listeners.contains(listener))
		{
			m_listeners.add(listener);
		}
	}

	public int getColumns()
	{
		return getColumnHeaders().length;
	}

	public IValidator getFieldValidator(int idx)
	{
		return s_emptyValidator;
	}

	public T getRow(int row)
	{
		return m_data.get(row);
	}

	public List<T> getRows()
	{
		return m_data;
	}

	public Object getEditorField(T t, int columnIndex)
	{
		Object[] array = toRowArray(t);

		int j = 0;
		for(int i = 0; i < getColumns(); i++)
		{
			if(getColumnWeights()[i] != 0)
			{
				if(j == columnIndex)
				{
					return array[i];
				}
				j++;
			}
		}
		
		return null;
	}

	public Control getControl(Composite parent, final int idx, Object value)
	{
		final Text text = UiUtils.createGridText(parent, 1, 0, null, SWT.NONE);
		
		String stringValue = value == null ? "" : value.toString();
		
		text.setText(stringValue);
		text.setData(stringValue);
		
		text.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				text.setData(text.getText());
				validateFieldInFieldListener(text, getFieldValidator(idx), text.getText());
			}
		});

		return text;
	}

	protected void validateFieldInFieldListener(Control fieldControl, IValidator fieldValidator, Object fieldValue)
	{
		String message = null;
		try
		{
			fieldValidator.validate(fieldValue);	
		}
		catch(ValidatorException e1)
		{
			message = e1.getMessage();
		}
		
		Event event = new Event();
		event.text = message;
		
		fieldControl.notifyListeners(ERROR_MESSAGE_EVENT_TYPE, event);
	}
	
	public IValidator getRowValidator()
	{
		return s_emptyValidator;
	}

	public void removeRow(int row)
	{
		T oldTableRow = m_data.remove(row);
		notifyListeners(TableModifyEventType.REMOVE_ROW, row, oldTableRow, null);
	}

	public void removeTableModifyListener(ITableModifyListener<T> listener)
	{
		m_listeners.remove(listener);
	}

	public void setRow(int row, Object[] tableRow) throws ValidatorException
	{
		validateAllFields(tableRow);
		getRowValidator().validate(Integer.valueOf(row), tableRow);
		T newTableRow = toRowClass(tableRow);
		T oldTableRow = m_data.set(row, newTableRow);
		notifyListeners(TableModifyEventType.UPDATE_ROW, row, oldTableRow, newTableRow);
	}

	private void notifyListeners(TableModifyEventType eventType, int row, T oldTableRow, T newTableRow)
	{
		TableModifyEvent<T> e = new TableModifyEvent<T>(this, eventType, row, oldTableRow, newTableRow);
		for(ITableModifyListener<T> listener : m_listeners)
			listener.modifyTable(e);
	}
}
