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

package org.eclipse.equinox.p2.authoring.internal;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Interface for a listener to editor events.
 * @author henrik
 *
 */
public interface IEditorListener extends EventListener
{
	/**
	 * Notifies the listener about a editor event.
	 */
	public void notify(EventObject o);

}
