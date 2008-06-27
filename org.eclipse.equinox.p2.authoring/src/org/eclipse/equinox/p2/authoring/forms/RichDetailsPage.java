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

import org.eclipse.equinox.p2.authoring.forms.EditAdapterFormPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;

/**
 * Common functionality for Rich Detail pages.
 * @author Henrik Lindberg
 *
 */
public abstract class RichDetailsPage implements IDetailsPage
{

	protected IManagedForm m_mform;
	protected EditAdapterFormPart m_editAdapters;
	
	public RichDetailsPage()
	{
		m_editAdapters = new EditAdapterFormPart();
	}
	public void initialize(IManagedForm mform)
	{
		m_mform = mform;
		m_mform.addPart(m_editAdapters);
	}

	/**
	 * Called when master selection changes.
	 */
	public abstract void selectionChanged(IFormPart part, ISelection selection);


	public boolean setFormInput(Object input)
	{
		return false;
	}

	public void refresh()
	{
		m_editAdapters.refresh();
	}

	/**
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	public void commit(boolean onSave)
	{
		m_editAdapters.commit(onSave);
	}

	public boolean isDirty()
	{
		return m_editAdapters.isDirty();
	}
	public void dispose()
	{
	}
	public boolean isStale()
	{
		return false;
	}

}
