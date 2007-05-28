/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Emulates part of Widget functionality - handles with listeners and can save data Object
 * 
 * @author Karel Brezina
 */
public interface IWidgetin
{
	public void addListener(int eventType, Listener listener);
	
	public Object getData();
	
	public void notifyListeners(int eventType, Event event);
	
	public void removeListener(int eventType, Listener listener);
	
	public void setData(Object data);
}
