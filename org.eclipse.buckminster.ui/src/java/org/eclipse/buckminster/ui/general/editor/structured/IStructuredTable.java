/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.structured;

import java.util.List;

import org.eclipse.buckminster.ui.general.editor.ITable;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Data wrapped for structured general table editor. If you need a structured table editor wrap your data into this
 * instance (preferably use intance of StructuredTable instead IStructuredTable) and start using OnePageTableEditor
 * 
 * @author Karel Brezina
 */
public interface IStructuredTable<T> extends ITable<T>
{
	/**
	 * Gets a value from table row at a given TableViewer position. It's used for ITableLabelProvider
	 * 
	 * @param t data table row
	 * @param columnIndex column index in a TableViewer
	 * @return required value
	 */
	public Object getTableViewerField(T t, int columnIndex);

	/**
	 * Number of columns in a TableViewer
	 * 
	 * @return number of columns
	 */
	public int getTableViewerColumns();

	/**
	 * Gets column headers for a TableViewer
	 * 
	 * @return column headers
	 */
	public String[] getTableViewerColumnHeaders();

	/**
	 * Gets column weights of columns in a TableViewer. Higher weight means larger space in the TableViewer.
	 * 
	 * @return column weights
	 */
	public int[] getTableViewerColumnWeights();

	/**
	 * Fills controls to the stack composite
	 * 
	 * @param stackComposite
	 */
	public void fillStackComposite(Composite stackComposite);

	/**
	 * Gets keys of stack levels
	 * 
	 * @return keys of stack levels
	 */
	public List<String> getStackKeys();

	/**
	 * Gets stack control for a given stack key
	 * 
	 * @param stackKey key of a stack level
	 * @return control of a stack level
	 */
	public Control getStackControl(String stackKey);

	/**
	 * Refreshes controls with values from row rowIdx of the data table
	 * 
	 * @param rowIdx row from the data table
	 */
	public void refreshRow(int rowIdx);

	/**
	 * Saves values from controls to row rowIdx of the data table.
	 * If rowIdx == -1, saves values to the end of the data table.
	 * 
	 * @param rowIdx row number where values should be saved
	 * @throws ValidatorException
	 */
	public void save(int rowIdx) throws ValidatorException;

	/**
	 * Swaps rows in TableViewer
	 * 
	 * @param rowIdx row number
	 * @param idxOffset index offset (1 moves row down, 0 moves row up)
	 * @return
	 */
	// TODO has to inform listeners about change
	public boolean swapRows(int rowIdx, int idxOffset);

	/**
	 * Enables or disables controls for keyboard focus
	 * 
	 * @param enabled true=enables controls
	 */
	public void enableFields(boolean enabled);
	
	/**
	 * Adds an empty record to the table
	 * @return
	 */
	public T addEmptyRow();
}
