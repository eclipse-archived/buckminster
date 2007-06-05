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
 * @author Karel Brezina
 *
 */
public interface IStructuredTable<T> extends ITable<T>
{
	/**
	 * Gets a value from table row at a given position
	 * 
	 * @param t data table row
	 * @param columnIndex column index in this table row
	 * @return required value
	 */
	public Object getTableViewerField(T t, int columnIndex);

	/**
	 * Number of columns
	 * 
	 * @return number of columns
	 */
	public int getTableViewerColumns();

	/**
	 * Gets column headers
	 * 
	 * @return column headers
	 */
	public String[] getTableViewerColumnHeaders();

	/**
	 * Gets column weights of columns in TableEditor. Higher weight means larger space in table editor.
	 * Weight 0 means that column will not be displayed in table editor (this value is accessible
	 * only thru row editing)
	 * 
	 * @return column weights
	 */
	public int[] getTableViewerColumnWeights();

	public void fillStackComposite(Composite stackComposite);

	public List<String> getStackKeys();

	public Control getStackControl(String stackKey);

	public void refreshRow(int rowIdx);

	public void save(int rowIdx) throws ValidatorException;

	// TODO has to inform listeners about change
	public boolean swapRows(int rowIdx, int idxOffset);

	public void enableFields(boolean enabled);

}
