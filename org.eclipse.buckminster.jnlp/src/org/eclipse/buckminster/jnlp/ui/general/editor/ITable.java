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
 * Data wrapped for general table editor. If you need a table editor wrap your data into this
 * instance (preferably use intance of Table instead ITable) and start using TableEditor
 * 
 * @author Karel Brezina
 */
public interface ITable<T>
{
	final int ERROR_MESSAGE_EVENT_TYPE = 954814;
	
	/**
	 * Gets row of this data table
	 * 
	 * @param row row number
	 * @return required row
	 */
	public T getRow(int row);
	
	/**
	 * Converts row of this data table to Object[] data
	 */
	public Object[] toRowArray(T t);
	
	/**
	 * Gets a value from table row at a given position
	 * 
	 * @param t data table row
	 * @param columnIndex column index in this table row
	 * @return required value
	 */
	public Object getEditorField(T t, int columnIndex);
	
	/**
	 * Converts Object[] data to row of this data table 
	 */
	public T toRowClass(Object[] args) throws ValidatorException;
	
	/**
	 * Gets all table rows
	 * 
	 * @return all rows
	 */
	public List<T> getRows();
	
	/**
	 * Gets control widget of a given column
	 * 
	 * @param parent parent composite
	 * @param idx column index
	 * @param value initial value
	 * @return required control
	 */
	public Control getControl(Composite parent, int idx, Object value);
	
	/**
	 * Sets table row to a row of this table
	 * 
	 * @param row row number
	 * @param tableRow row data
	 */
	public void setRow(int row, Object[] tableRow) throws ValidatorException;
	
	/**
	 * Adds new row to the end of this table
	 * 
	 * @param tableRow row data
	 */
	public void addRow(Object[] tableRow) throws ValidatorException;
	
	/**
	 * Removes row from this table
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
	 * Gets column weights of columns in TableEditor. Higher weight means larger space in table editor.
	 * Weight 0 means that column will not be displayed in table editor (this value is accessible
	 * only thru row editing)
	 * 
	 * @return column weights
	 */
	public int[] getColumnWeights();
		
	
	/**
	 * Gets column Validator - checks value of a column field
	 * 
	 * @param idx column index
	 * @return column Validator
	 */
	public IValidator getFieldValidator(int idx);
	
	/**
	 * Gets row Validator - checks whole row
	 * 
	 * @return row Validator
	 */
	public IValidator getRowValidator();
	
	/**
	 * Adds table modify listener
	 * 
	 * @param table modify listener
	 */
	public void addTableModifyListener(ITableModifyListener<T> listener);
	
	/**
	 * Removes table modify listener
	 * 
	 * @param table modify listener
	 */
	public void removeTableModifyListener(ITableModifyListener<T> listener);
}
