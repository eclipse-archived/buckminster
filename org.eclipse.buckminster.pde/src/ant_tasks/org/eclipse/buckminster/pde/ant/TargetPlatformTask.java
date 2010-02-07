/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;

/**
 * Ant task that assings the location of the target platform to a given
 * property.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public abstract class TargetPlatformTask extends Task {
	/**
	 * Returns the location of the target platform
	 */
	public IPath getTargetLocation() {
		IPath targetPath = null;
		IPreferencesService prefService = Platform.getPreferencesService();
		if (ICoreConstants.VALUE_USE_OTHER.equals(prefService.getString(PDECore.PLUGIN_ID, ICoreConstants.TARGET_MODE, null, null))) {
			String targetPlatform = prefService.getString(PDECore.PLUGIN_ID, ICoreConstants.PLATFORM_PATH, null, null);
			if (targetPlatform != null)
				targetPath = new Path(targetPlatform);
		}

		if (targetPath == null)
			targetPath = new Path(TargetPlatform.getDefaultLocation());
		return targetPath;
	}

	protected BuildException missingAttribute(String property) {
		return new BuildException("Missing attribute \"" + property + '"', this.getLocation()); //$NON-NLS-1$
	}
}
