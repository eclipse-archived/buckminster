/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.jdt.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.service.prefs.BackingStoreException;

public class ComplianceLevelHandler extends BasicPreferenceHandler
{
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

	public static void setCompilanceOptions(String compliance) throws BackingStoreException
	{
		// We must hardcode these preferences into the instance store. Normally, only
		// those that differs from the default settings will be stored but if we do that
		// and the default java changes (which it does on the first build if the JVM is
		// not a 1.4) then all settings that where equal to the default will change.
		//
		IEclipsePreferences prefs = new InstanceScope().getNode(JavaCore.PLUGIN_ID);
		if(JavaCore.VERSION_1_6.equals(compliance))
		{
			prefs.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
			prefs.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
			prefs.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_6);
			prefs.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
			prefs.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
		}
		else if(JavaCore.VERSION_1_5.equals(compliance))
		{
			prefs.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
			prefs.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
			prefs.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
			prefs.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
			prefs.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
		}
		else if(JavaCore.VERSION_1_4.equals(compliance))
		{
			prefs.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_4);
			prefs.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_4);
			prefs.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_4);
			prefs.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.WARNING);
			prefs.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.WARNING);
		}
		else if(JavaCore.VERSION_1_3.equals(compliance))
		{
			prefs.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_3);
			prefs.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3);
			prefs.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_1);
			prefs.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.IGNORE);
			prefs.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.IGNORE);
		}
		else
		{
			throw new IllegalArgumentException("Unsupported compliance: " + compliance); //$NON-NLS-1$
		}
		prefs.flush();
	}
}
