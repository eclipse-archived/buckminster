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
import org.eclipse.swt.widgets.Widget;

/**
 * @author Karel Brezina
 *
 */
public class WidgetWrapper implements IWidgetin
{
	private Widget m_widget;
	
	public WidgetWrapper(Widget widget)
	{
		m_widget = widget;
	}
	
	public Widget getWidget()
	{
		return m_widget;
	}
	
	public void addListener(int eventType, Listener listener)
	{
		m_widget.addListener(eventType, listener);
	}

	public Object getData()
	{
		return m_widget.getData();
	}

	public void notifyListeners(int eventType, Event event)
	{
		m_widget.notifyListeners(eventType, event);	
	}

	public void removeListener(int eventType, Listener listener)
	{
		m_widget.removeListener(eventType, listener);	
	}

	public void setData(Object data)
	{
		m_widget.setData(data);		
	}
}
