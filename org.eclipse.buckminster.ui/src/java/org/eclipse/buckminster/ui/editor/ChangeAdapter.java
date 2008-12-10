/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;

/**
 * A SelectionAdapter that also can act as a ModifyListener. Useful when listening to both text, button, and combo
 * events.
 * 
 * @author Thomas Hallgren
 */
public abstract class ChangeAdapter extends SelectionAdapter implements ModifyListener, VersionDesignatorListener
{
	public void modifyText(ModifyEvent e)
	{
		this.onChange(e);
	}

	public void modifyVersionDesignator(VersionDesignatorEvent e)
	{
		this.onChange(e);
	}

	@Override
	public void widgetSelected(SelectionEvent e)
	{
		this.onChange(e);
	}

	protected abstract void onChange(TypedEvent e);
}
