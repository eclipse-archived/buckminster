/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import org.eclipse.buckminster.ui.general.editor.ITable;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.widgets.Composite;

/**
 * Data wrapped for simple general table editor. If you need a table editor wrap your data into this
 * instance (preferably use intance of SimpleTable instead ISimpleTable) and start using SimpleTableEditor
 * 
 * @author Karel Brezina
 */
public interface ISimpleTable<T> extends ITable<T>
{
	final int ERROR_MESSAGE_EVENT_TYPE = 954814;
	
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
	 * Creates empty member of this data table
	 * @return empty member of this data table
	 */
	public T createRowClass();
	
	/**
	 * Updates member of this data table
	 * 
	 * @param arg member of this data table
	 * @param args new values
	 * @throws ValidatorException
	 */
	public void updateRowClass(T arg, Object[] args) throws ValidatorException;
	
	/**
	 * Fills a grid composite with widgeds and returns those widgets. For each column there must be just one widget.
	 * It's used in TableRowDialog for creating a row editing dialog area
	 * 
	 * @param parent parent grid composite
	 * @param fieldValues values of widget fields
	 * @return widgets added to the composite
	 */
	public IWidgetin[] fillGrid(Composite parent, Object[] fieldValues);

	
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
	 * Number of columns
	 * 
	 * @return number of columns
	 */
	public int getColumns();
		
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
}
