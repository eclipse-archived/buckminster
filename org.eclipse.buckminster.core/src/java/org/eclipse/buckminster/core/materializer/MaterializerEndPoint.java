/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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
public class MaterializerEndPoint
{
	public static MaterializerEndPoint create(IPath location, String remoteName, Resolution resolution,
			MaterializationContext ctx) throws CoreException
	{
		String suffixedName = ctx.getSuffixedName(resolution, remoteName);
		Installer installer = (suffixedName == null)
				? Installer.getPlainInstaller()
				: Installer.getInstaller(suffixedName, ctx.getMaterializationSpec().isExpand(
						resolution.getComponentIdentifier()));
		return new MaterializerEndPoint(location, installer);
	}

	private final IPath m_finalDestination;

	private final Installer m_installer;

	MaterializerEndPoint(IPath finalLocation, Installer installer)
	{
		m_finalDestination = finalLocation;
		m_installer = installer;
	}

	public IPath getFinalDestination()
	{
		return m_finalDestination;
	}

	public void unpack(InputStream input, IProgressMonitor monitor) throws IOException, CoreException
	{
		m_installer.install(input, m_finalDestination.toFile(), monitor);
	}
}
