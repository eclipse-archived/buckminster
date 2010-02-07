/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal.model;

import org.eclipse.pde.internal.core.build.BuildModel;

/**
 * A BuildModel found at some arbitrary location, i.e. not necessarily in the
 * workspace.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ExternalBuildModel extends BuildModel {
	private static final long serialVersionUID = 3697820164252115L;

	public String getInstallLocation() {
		return null;
	}

	public boolean isEditable() {
		return false;
	}

	public boolean isInSync() {
		return true;
	}

	@Override
	public void load() {
	}

	@Override
	protected void updateTimeStamp() {
	}
}
