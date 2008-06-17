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

import java.util.HashMap;

import org.eclipse.equinox.p2.authoring.forms.validators.IEditValidator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.AbstractFormPart;

/**
 * An IFormPart that groups EditAdapters and manages their life cycle in an Eclipse Form.
 * 
 * @author Henrik Lindberg
 * 
 */
public class EditAdapterFormPart extends AbstractFormPart
{
	private HashMap<String, EditAdapter> m_editAdapters = new HashMap<String, EditAdapter>();

	public EditAdapterFormPart()
	{

	}

	/**
	 * Create and manage a default edit adapter.
	 * 
	 * @param key
	 * @param control
	 */
	public void createEditAdapter(String key, Control control)
	{
		m_editAdapters.put(key, new EditAdapter(control, this));
	}

	/**
	 * Create and manage a configured edit adapter
	 * 
	 * @param key
	 * @param control
	 * @param validator
	 * @param mutator
	 */
	public void createEditAdapter(String key, Control control, IEditValidator validator, IMutator mutator)
	{
		m_editAdapters.put(key, new EditAdapter(control, this, validator, mutator));
	}

	public void addAdapter(String key, EditAdapter adapter)
	{
		m_editAdapters.put(key, adapter);
	}

	public void removeAdapter(String key)
	{
		m_editAdapters.remove(key);
	}

	public EditAdapter getAdapter(String key)
	{
		return m_editAdapters.get(key);
	}

	/**
	 * Commits all edit adapters.
	 */
	@Override
	public void commit(boolean onSave)
	{
		for(EditAdapter e : m_editAdapters.values())
			e.commit(onSave);
		super.commit(onSave);
	}

	/**
	 * Refreshes all edit adapters.
	 */
	@Override
	public void refresh()
	{
		for(EditAdapter e : m_editAdapters.values())
			e.refresh();
		super.refresh();
	}
}
