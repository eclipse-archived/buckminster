/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import java.util.List;

import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.Table;
import org.eclipse.buckminster.ui.general.editor.TableModifyEventType;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;

/**
 * Data wrapped for general table editor. If you need a table editor wrap your data into this
 * instance (preferably use intance of Table instead ITable) and start using TableEditor
 * 
 * @author Karel Brezina
 */
public abstract class SimpleTable<T> extends Table<T> implements ISimpleTable<T>
{
	private static class NotEmptyStringValidator implements IValidator
	{
		private String m_exceptionMessage;
		
		public NotEmptyStringValidator(String exceptionMessage)
		{
			m_exceptionMessage = exceptionMessage;
		}
		
		public void validate(Object... arg) throws ValidatorException
		{
			String string = (String) arg[0];
			
			if(string == null || string.length() == 0)
			{
				throw new ValidatorException(m_exceptionMessage);
			}
		}
	}
	
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

	/**
	 * Creates Table instance
	 * 
	 * @param data input data that will be edited
	 */
	public SimpleTable(List<T> data)
	{
		super(data);
	}

	public void addRow(Object[] tableRow) throws ValidatorException
	{
		validateAllFields(tableRow);
		getRowValidator().validate(new Integer(-1), tableRow);
		T newTableRow = toRowClass(tableRow);
		getRows().add(newTableRow);
		notifyListeners(TableModifyEventType.ADD_ROW, getRows().size() - 1, null, newTableRow);
	}

	private void validateAllFields(Object[] tableRow) throws ValidatorException
	{
		for(int i = 0; i < getColumns(); i++)
		{
				getFieldValidator(i).validate(tableRow[i]);
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

	protected IWidgetin getWidgetin(Composite parent, final int idx, Object value)
	{
		return getTextWidgetin(parent, idx, value);
	}
		
	protected IWidgetin getTextWidgetin(Composite parent, final int idx, Object value)
	{
		final Text text = UiUtils.createGridText(parent, 1, 0, SWT.NONE, null);
		
		final IWidgetin widgetin = new WidgetinWrapper(text);
		
		String stringValue = value == null ? "" : value.toString();
		
		text.setText(stringValue);
		text.setData(stringValue);
		
		text.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				widgetin.setData(text.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), text.getText());
			}
		});

		return widgetin;
	}

	protected IWidgetin getBooleanCheckBoxWidgetin(Composite parent, final int idx, Object value)
	{
		final Button checkBox = new Button(parent, SWT.CHECK);
		final IWidgetin widgetin = new WidgetinWrapper(checkBox);

		GridData data = new GridData(SWT.FILL, SWT.CENTER, false, false);
		checkBox.setLayoutData(data);

		Boolean realValue = value == null ? Boolean.FALSE : (Boolean) value;

		checkBox.setSelection(realValue.booleanValue());
		checkBox.setData(realValue);
		
		checkBox.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Boolean selectionValue = Boolean.valueOf(checkBox.getSelection());
				checkBox.setData(selectionValue);
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), selectionValue);				
			}
		});

		return widgetin;		
	}

	public IWidgetin[] fillGrid(Composite parent, Object[] fieldValues)
	{
		((GridLayout) parent.getLayout()).numColumns = 2; 
		
		IWidgetin[] widgetins = new IWidgetin[getColumns()];
		
		for(int i = 0; i < getColumns(); i++)
		{
			UiUtils.createGridLabel(parent, getColumnHeaders()[i] + ":", 1, 0, SWT.NONE);
			widgetins[i] = getWidgetin(parent, i, fieldValues[i]);
		}
		
		return widgetins;
	}
	
	protected void validateFieldInFieldListener(IWidgetin fieldControl, IValidator fieldValidator, Object fieldValue)
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

	public static IValidator createNotEmptyStringValidator(String exceptionMessage)
	{
		return new NotEmptyStringValidator(exceptionMessage);
	}
	
	public void setRow(int row, Object[] tableRow) throws ValidatorException
	{
		validateAllFields(tableRow);
		getRowValidator().validate(Integer.valueOf(row), tableRow);
		T newTableRow = toRowClass(tableRow);
		T oldTableRow = getRows().set(row, newTableRow);
		notifyListeners(TableModifyEventType.UPDATE_ROW, row, oldTableRow, newTableRow);
	}
}
