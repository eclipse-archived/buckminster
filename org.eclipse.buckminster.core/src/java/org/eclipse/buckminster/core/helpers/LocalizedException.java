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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;


@SuppressWarnings("serial")
public abstract class LocalizedException extends CoreException
{
	private static String getLocalizedFormat(Class<?> c, String defaultMessageFormat)
	{
		CorePlugin core = CorePlugin.getDefault();
		if(core == null)
			return defaultMessageFormat;

		ResourceBundle bundle = core.getResourceBundle();
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

	private final String m_defaultMessageFormat;
	private final Object[] m_arguments;

	protected LocalizedException(String defaultMessageFormat, Object...args)
	{
		this(null, defaultMessageFormat, args);
	}

	protected LocalizedException(Throwable cause, String defaultMessageFormat, Object...args)
	{
		super(BuckminsterException.createStatus(defaultMessageFormat, args));
		m_defaultMessageFormat = defaultMessageFormat;
		m_arguments = args;
	}

	@Override
	public String getLocalizedMessage()
	{
		return String.format(getLocalizedFormat(getClass(), m_defaultMessageFormat), m_arguments);
	}
}

