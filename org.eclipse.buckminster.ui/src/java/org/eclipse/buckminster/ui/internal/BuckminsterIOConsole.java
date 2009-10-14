/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.internal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;

/**
 * @author Karel Brezina
 * 
 */
public class BuckminsterIOConsole extends IOConsole implements IPropertyChangeListener
{
	private static final RGB DEFAULT_COLOR = new RGB(0, 0, 0);

	private Set<IOConsoleOutputStream> m_messageStreamSet = new HashSet<IOConsoleOutputStream>();

	private Set<IOConsoleOutputStream> m_errorStreamSet = new HashSet<IOConsoleOutputStream>();

	private boolean m_showOnMessage;

	private boolean m_showOnError;

	private Color m_messageColor;

	private Color m_errorColor;

	public BuckminsterIOConsole(String name, String type)
	{
		super(name, type, null);
		initLimitOutput();
		m_showOnMessage = BuckminsterPreferences.isConsoleShowOnMessage();
		m_showOnError = BuckminsterPreferences.isConsoleShowOnError();
		m_messageColor = createColor(BuckminsterPreferences.getConsoleMessageColor());
		m_errorColor = createColor(BuckminsterPreferences.getConsoleErrorColor());
		UiPlugin.getDefault().getBuckminsterPreferenceStore().addPropertyChangeListener(this);
	}

	public IOConsoleOutputStream newOutputStream(boolean errorStream)
	{
		removeClosedStreams();

		IOConsoleOutputStream newStream = super.newOutputStream();

		if(errorStream)
		{
			newStream.setActivateOnWrite(m_showOnError);
			newStream.setColor(m_errorColor);
			m_errorStreamSet.add(newStream);
		}
		else
		{
			newStream.setActivateOnWrite(m_showOnMessage);
			newStream.setColor(m_messageColor);
			m_messageStreamSet.add(newStream);
		}

		return newStream;
	}

	public void propertyChange(PropertyChangeEvent event)
	{
		removeClosedStreams();

		String property = event.getProperty();

		if(property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_MESSAGE_COLOR))
		{
			Color newColor = createColor(BuckminsterPreferences.getConsoleMessageColor());
			setColor(m_messageStreamSet, newColor);
			m_messageColor.dispose();
			m_messageColor = newColor;
		}
		else if(property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_ERROR_COLOR))
		{
			Color newColor = createColor(BuckminsterPreferences.getConsoleErrorColor());
			setColor(m_errorStreamSet, newColor);
			m_errorColor.dispose();
			m_errorColor = newColor;
		}
		else if(property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_LIMIT_OUTPUT)
				|| property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_HIGH_WATER_MARK))
		{
			initLimitOutput();
		}
		else if(property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_SHOW_ON_MESSAGE))
		{
			m_showOnMessage = BuckminsterPreferences.isConsoleShowOnMessage();
			setActivateOnWrite(m_messageStreamSet, m_showOnMessage);
		}
		else if(property.equals(IBuckminsterPreferenceConstants.PREF_CONSOLE_SHOW_ON_ERROR))
		{
			m_showOnError = BuckminsterPreferences.isConsoleShowOnError();
			setActivateOnWrite(m_errorStreamSet, m_showOnError);
		}
	}

	/**
	 * Returns a color instance based on RGB color string (e.g. "0,0,256" - blue)
	 */
	private Color createColor(String colorString)
	{
		RGB rgb = StringConverter.asRGB(colorString, null);
		if(rgb == null)
			rgb = DEFAULT_COLOR;

		return new Color(Display.getCurrent(), rgb);
	}

	private void initLimitOutput()
	{
		if(BuckminsterPreferences.isConsoleLimitOutput())
			setWaterMarks(1000, BuckminsterPreferences.getConsoleHighWaterMark());
		else
			setWaterMarks(-1, 0);
	}

	private void removeClosedStreams()
	{
		removeClosedStreams(m_messageStreamSet.iterator());
		removeClosedStreams(m_errorStreamSet.iterator());
	}

	private void removeClosedStreams(Iterator<IOConsoleOutputStream> iterator)
	{
		while(iterator.hasNext())
			if(iterator.next().isClosed())
				iterator.remove();
	}

	private void setActivateOnWrite(Set<IOConsoleOutputStream> streams, boolean activateOnWrite)
	{
		if(streams != null)
			for(IOConsoleOutputStream stream : streams)
				stream.setActivateOnWrite(activateOnWrite);
	}

	private void setColor(Set<IOConsoleOutputStream> streams, Color color)
	{
		if(streams != null)
			for(IOConsoleOutputStream stream : streams)
				stream.setColor(color);
	}

}
