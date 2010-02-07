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

import java.io.File;

import org.eclipse.pde.internal.core.bundle.BundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundleModelFactory;
import org.eclipse.pde.internal.core.text.bundle.BundleModelFactory;

/**
 * A BundleModel found at some arbitrary location, i.e. not necessarily in the
 * workspace.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ExternalBundleModel extends BundleModel {
	private static final long serialVersionUID = 6529464212517724764L;

	private final File installLocation;

	public ExternalBundleModel(File installLocation) {
		this.installLocation = installLocation;
	}

	public IBundleModelFactory getFactory() {
		return new BundleModelFactory(this);
	}

	@Override
	public String getInstallLocation() {
		return installLocation == null ? null : installLocation.getAbsolutePath();
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
