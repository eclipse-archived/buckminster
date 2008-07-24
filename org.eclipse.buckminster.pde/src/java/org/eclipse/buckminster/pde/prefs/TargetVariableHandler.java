/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Custom preference handler for the target preferences.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
abstract class TargetVariableHandler extends BasicPreferenceHandler implements ICoreConstants
{
	private final String m_prefName;

	TargetVariableHandler(String prefName)
	{
		m_prefName = prefName;
	}

	@Override
	public String get(String defaultValue)
	{
		return PDECore.getDefault().getPluginPreferences().getString(m_prefName);
	}

	@Override
	public void set(String value) throws BackingStoreException
	{
		for(String knownValue : this.getKnownValues())
		{
			if(knownValue.equals(value))
			{
				PDECore pdePlugin = PDECore.getDefault();
				pdePlugin.getPluginPreferences().setValue(m_prefName, value);
				pdePlugin.savePluginPreferences();
				return;
			}
		}
		throw new BackingStoreException(value + " is not a valid setting for the target" + m_prefName + " preference");
	}

	abstract String[] getKnownValues();
}
