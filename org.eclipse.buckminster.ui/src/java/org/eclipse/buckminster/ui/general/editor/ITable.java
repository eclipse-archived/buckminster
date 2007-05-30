/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

import java.util.List;

import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;

/**
 * Data wrapped for general table editors.
 * 
 * @author Karel Brezina
 */
public interface ITable<T>
{
	/**
	 * Gets row of this data table
	 * 
	 * @param row row number
	 * @return required row
	 */
	public T getRow(int row);
	
	/**
	 * Gets all table rows
	 * 
	 * @return all rows
	 */
	public List<T> getRows();	
	
	/**
	 * Removes row from this table
	 * 
	 * @param row row number
	 */
	public void removeRow(int row);
	
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
