/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.download.Installer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializerEndPoint {
	public static MaterializerEndPoint create(IPath location, String remoteName, Resolution resolution, MaterializationContext ctx)
			throws CoreException {
		String suffixedName = ctx.getSuffixedName(resolution, remoteName);
		Installer installer = (suffixedName == null) ? Installer.getPlainInstaller() : Installer.getInstaller(suffixedName, resolution.isUnpack()
				|| ctx.getMaterializationSpec().isExpand(resolution));
		return new MaterializerEndPoint(location, installer);
	}

	private final IPath finalDestination;

	private final Installer installer;

	MaterializerEndPoint(IPath finalLocation, Installer installer) {
		this.finalDestination = finalLocation;
		this.installer = installer;
	}

	public IPath getFinalDestination() {
		return finalDestination;
	}

	public void unpack(InputStream input, IProgressMonitor monitor) throws IOException, CoreException {
		installer.install(input, finalDestination.toFile(), monitor);
	}
}
