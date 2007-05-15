/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.editor;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Karel Brezina
 *
 */
public interface ITable<T>
{
	public final int ERROR_MESSAGE_EVENT_TYPE = 954814;
	
	/**
	 * Gets row
	 * 
	 * @param row row number
	 * @return required row
	 */
	public T getRow(int row);
	
	public Object[] toRowArray(T t);
	
	public Object getEditorField(T t, int columnIndex);

	public T toRowClass(Object[] strings) throws ValidatorException;
	
	/**
	 * Gets all rows
	 * 
	 * @return all rows
	 */
	public List<T> getRows();
	
	public Control getControl(Composite parent, int idx, Object value);
	
	/**
	 * Sets row
	 * 
	 * @param row row number
	 * @param tableRow row data
	 */
	public void setRow(int row, Object[] tableRow) throws ValidatorException;
	
	/**
	 * Adds new row
	 * 
	 * @param tableRow row data
	 */
	public void addRow(Object[] tableRow) throws ValidatorException;
	
	/**
	 * Removes row
	 * 
	 * @param row row number
	 */
	public void removeRow(int row);
	
	/**
	 * Number of columns
	 * 
	 * @return number of columns
	 */
	public int getColumns();
	
	/**
	 * Gets column headers
	 * 
	 * @return column headers
	 */
	public String[] getColumnHeaders();
	
	/**
	 * Gets column weights
	 * 
	 * @return column weights
	 */
	public int[] getColumnWeights();
		
	
	/**
	 * Gets field Validator
	 * 
	 * @param idx field idx
	 * @return field Validator
	 */
	public IValidator getFieldValidator(int idx);
	
	/**
	 * Gets row Validator
	 * 
	 * @return row Validator
	 */
	public IValidator getRowValidator();
	
	public void addTableModifyListener(ITableModifyListener<T> listener);
	
	public void removeTableModifyListener(ITableModifyListener<T> listener);

}
