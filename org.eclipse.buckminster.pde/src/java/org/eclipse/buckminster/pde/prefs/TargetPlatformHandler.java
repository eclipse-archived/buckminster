/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import org.eclipse.buckminster.cmdline.prefs.BasicPreferenceHandler;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;

/**
 * Custom preference handler for the targetPlatform preference. Needed since
 * manipulating {@link ICoreConstants#PLATFORM_PATH} preference also changes the
 * preference {@link ICoreConstants#TARGET_MODE}.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class TargetPlatformHandler extends BasicPreferenceHandler
{
	@Override
	public String get(String defaultValue)
	{
		return PDECore.getDefault().getPluginPreferences().getString(ICoreConstants.PLATFORM_PATH);
	}

	@Override
	public void set(String targetPlatform)
	{
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath newPath = new Path(targetPlatform);
		Platform.getInstallLocation();
		IPath defaultPath = new Path(TargetPlatform.getDefaultLocation());
		String mode = defaultPath.equals(newPath)
				? ICoreConstants.VALUE_USE_THIS
				: ICoreConstants.VALUE_USE_OTHER;
		preferences.setValue(ICoreConstants.TARGET_MODE, mode);
		preferences.setValue(ICoreConstants.PLATFORM_PATH, targetPlatform);
		pdePlugin.savePluginPreferences();
	}

	@Override
	public void unset()
	{
		this.set(TargetPlatform.getDefaultLocation());
	}
}
