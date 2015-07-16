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
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.core.target.ITargetDefinition;
import org.eclipse.pde.core.target.ITargetLocation;
import org.eclipse.pde.core.target.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.DirectoryBundleContainer;

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
		try {
			ITargetPlatformService tpService = Buckminster.getDefault().getService(ITargetPlatformService.class);
			ITargetDefinition tpDef = tpService.getWorkspaceTargetDefinition();
			if (tpDef != null) {
				ITargetLocation[] containers = tpDef.getTargetLocations();
				if (containers != null) {
					for (ITargetLocation container : containers) {
						// bug 285449: the directory bundle container is
						// actually
						// the only we one we can use
						if (container instanceof DirectoryBundleContainer) {
							targetPath = new Path(((DirectoryBundleContainer) container).getLocation(true));
						}
					}
				}
			}
		} catch (CoreException e) {
			Logger.getDefault().warning(e, "Problems when determining target platfrom location");
		}
		if (targetPath == null)
			targetPath = new Path(TargetPlatform.getDefaultLocation());
		return targetPath;
	}

	protected BuildException missingAttribute(String property) {
		return new BuildException("Missing attribute \"" + property + '"', this.getLocation()); //$NON-NLS-1$
	}
}
