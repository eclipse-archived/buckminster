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

/**
 * Ant task that assings the location of the target platform to a given
 * property.
 * 
 * @author Thomas Hallgren
 */
public class TargetPlatformLocation extends TargetPlatformTask {
	private String property;

	@Override
	public void execute() throws BuildException {
		if (property == null)
			throw missingAttribute("property"); //$NON-NLS-1$
		this.getProject().setProperty(property, this.getTargetLocation().toOSString());
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
