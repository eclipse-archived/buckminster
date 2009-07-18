/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.jdt.prefs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.service.prefs.BackingStoreException;

public class ComplianceLevelHandler extends BasicPreferenceHandler
{
	public static void setCompilanceOptions(String compliance) throws BackingStoreException
	{
		// We must hardcode these preferences into the instance store. Normally, only
		// those that differs from the default settings will be stored but if we do that
		// and the default java changes (which it does on the first build if the JVM is
		// not a 1.4) then all settings that where equal to the default will change.
		//
		HashMap<String, String> options = new HashMap<String, String>();
		JavaCore.setComplianceOptions(compliance, options);
		if(options.isEmpty())
			throw new IllegalArgumentException("Unsupported compliance: " + compliance); //$NON-NLS-1$

		IEclipsePreferences prefs = new InstanceScope().getNode(JavaCore.PLUGIN_ID);
		IEclipsePreferences defaults = new DefaultScope().getNode(JavaCore.PLUGIN_ID);
		for(Map.Entry<String, String> entry : options.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			String defaultValue = defaults.get(key, null);
			if(value == null || (defaultValue != null && defaultValue.equals(value)))
				prefs.remove(key);
			else
				prefs.put(key, value);
		}
		prefs.flush();
	}

	@Override
	public String get(String defaultValue) throws CoreException
	{
		return JavaCore.getOption(JavaCore.COMPILER_COMPLIANCE);
	}

	@Override
	public void set(String complianceLevel) throws BackingStoreException
	{
		setCompilanceOptions(complianceLevel);
	}

	@Override
	public void unset() throws BackingStoreException
	{
		setCompilanceOptions((String)JavaCore.getDefaultOptions().get(JavaCore.COMPILER_COMPLIANCE));
	}
}
