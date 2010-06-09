/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.forms;

/**
 * Pages that need to record and restore a memento of their state to enable undo/redo to properly set
 * a 'context/state' on a page should implement this interface. A memento must be immutable, a page may not alter
 * a memento that has been returned from {@link #getPageMemento}.
 * 
 * @author Henrik Lindberg
 *
 */
public interface IPageMementoProvider
{
	/**
	 * Return an immutable memento that the page later can make use of to restore its state. Typically, the page
	 * would want to record selection.
	 * @return
	 */
	Object getPageMemento();
	
	/** Restore the page state from the given memento. This memento should be a memento that has been returned by
	 * the {@link #getPageMemento} method.
	 * @param memento
	 */
	void setPageMemento(Object memento);
}
