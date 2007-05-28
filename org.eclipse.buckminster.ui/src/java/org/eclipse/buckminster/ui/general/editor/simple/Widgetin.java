/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Karel Brezina
 *
 */
public class Widgetin implements IWidgetin
{
	private Object m_data = null;
	
	private Map<Integer, List<Listener>> m_listenersMap = new HashMap<Integer, List<Listener>>();
	
	public void addListener(int eventType, Listener listener)
	{
		List<Listener> eventListeners = m_listenersMap.get(Integer.valueOf(eventType));
		
		if(eventListeners == null)
		{
			eventListeners = new ArrayList<Listener>();
			m_listenersMap.put(Integer.valueOf(eventType), eventListeners);
		}
		
		if(! eventListeners.contains(listener))
		{
			eventListeners.add(listener);
		}
	}

	public Object getData()
	{
		return m_data;
	}

	public void notifyListeners(int eventType, Event event)
	{
		List<Listener> eventListeners = m_listenersMap.get(Integer.valueOf(eventType));
		
		if(eventListeners != null)
		{
			for(Listener listener : eventListeners)
			{
				listener.handleEvent(event);
			}
		}
	}

	public void removeListener(int eventType, Listener listener)
	{
		m_listenersMap.remove(Integer.valueOf(eventType));
	}

	public void setData(Object data)
	{
		m_data = data;
	}

	
}
