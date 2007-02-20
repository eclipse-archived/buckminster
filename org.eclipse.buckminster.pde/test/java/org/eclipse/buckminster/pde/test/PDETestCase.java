/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.test;

import org.eclipse.buckminster.core.test.AbstractTestCase;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.internal.core.ExternalModelManager;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;

/**
 * @author thhal
 */
@SuppressWarnings("restriction")
public abstract class PDETestCase extends AbstractTestCase
{
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * Returns the location of the target platform
	 */
	public IPath getTargetLocation()
	{
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath targetPath = null;
		if(ICoreConstants.VALUE_USE_OTHER.equals(preferences.getString(ICoreConstants.TARGET_MODE)))
		{
			String targetPlatform = preferences.getString(ICoreConstants.PLATFORM_PATH);
			if(targetPlatform != null)
				targetPath = new Path(targetPlatform);
		}

		if(targetPath == null)
			targetPath = new Path(ExternalModelManager.computeDefaultPlatformPath());
		return targetPath;
	}

}

