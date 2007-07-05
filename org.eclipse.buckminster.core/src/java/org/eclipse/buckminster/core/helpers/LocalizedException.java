/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;


public abstract class LocalizedException extends BuckminsterException
{
	private final String m_messageFormat;

	private static String getLocalizedFormat(Class<?> c, String defaultMessageFormat)
	{
		ResourceBundle bundle = CorePlugin.getDefault().getResourceBundle();
		if(bundle == null)
			return defaultMessageFormat;

		// Strip of package name from the class name
		//
		int pkgLen = 0;
		Package p = c.getPackage();
		if(p != null)
		{
			pkgLen = p.getName().length();
			if(pkgLen > 0)
				//
				// Account for '.'
				//
				++pkgLen;
		}

		String name = c.getName();
		if(pkgLen > 0)
			name = name.substring(pkgLen);

		try
		{
			return bundle.getString(name);
		}
		catch(MissingResourceException e)
		{
			return defaultMessageFormat;
		}
	}

	protected LocalizedException(String defaultMessageFormat)
	{
		super(defaultMessageFormat);
		m_messageFormat = getLocalizedFormat(this.getClass(), defaultMessageFormat);
	}

	protected LocalizedException(String defaultMessageFormat, Throwable cause)
	{
		super(defaultMessageFormat, cause);
		m_messageFormat = getLocalizedFormat(this.getClass(), defaultMessageFormat);
	}

	@Override
	public String getMessage()
	{
		return this.formatMessage(super.getMessage());
	}

	@Override
	public String getLocalizedMessage()
	{
		return this.formatMessage(m_messageFormat);
	}

	protected void assignMessage()
	{
		this.setMessage(this.formatMessage(super.getMessage()));
	}

	protected String formatMessage(String format)
	{
		try
		{
			return MessageFormat.format(format, (Object[])this.getArguments());
		}
		catch(Throwable t)
		{
			return format;
		}
	}

	protected abstract String[] getArguments();
}

