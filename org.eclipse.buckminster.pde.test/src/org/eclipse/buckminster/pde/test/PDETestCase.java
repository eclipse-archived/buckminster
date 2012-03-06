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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;

/**
 * @author thhal
 */
@SuppressWarnings({ "restriction", "deprecation" })
public abstract class PDETestCase extends AbstractTestCase {
	/**
	 * @param projectFolder
	 *            TestData Folder
	 * @param workspace
	 *            {@link IWorkspace} to create the new Project in
	 * @return new initialized {@link IProject}�
	 * @throws CoreException
	 */
	protected IProject createProject(File projectFolder, IWorkspace workspace) throws CoreException {
		// Bind project
		IPath locationPath = Path.fromOSString(projectFolder.getAbsolutePath());
		IProjectDescription description = workspace.loadProjectDescription(locationPath.append(".project")); //$NON-NLS-1$
		IWorkspaceRoot wsRoot = workspace.getRoot();
		IProject project = wsRoot.getProject(description.getName());
		IProgressMonitor monitor = new NullProgressMonitor();
		project.create(description, monitor);
		project.open(monitor);
		boolean errors = false;
		List<String> messages = new ArrayList<String>();
		IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		for (IMarker marker : markers) {
			if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
				errors = true;
			messages.add(marker.getAttribute(IMarker.LOCATION, "<unknown location>") + ": "
					+ marker.getAttribute(IMarker.MESSAGE, "<unknown problem>"));
		}
		if (errors) {
			for (String message : messages)
				System.out.println(message);
			fail();
		}
		return project;
	}

	/**
	 * Returns the location of the target platform
	 */
	public IPath getTargetLocation() {
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath targetPath = null;
		if (ICoreConstants.VALUE_USE_OTHER.equals(preferences.getString(ICoreConstants.TARGET_MODE))) {
			String targetPlatform = preferences.getString(ICoreConstants.PLATFORM_PATH);
			if (targetPlatform != null)
				targetPath = new Path(targetPlatform);
		}

		if (targetPath == null)
			targetPath = new Path(TargetPlatform.getDefaultLocation());
		return targetPath;
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

}
