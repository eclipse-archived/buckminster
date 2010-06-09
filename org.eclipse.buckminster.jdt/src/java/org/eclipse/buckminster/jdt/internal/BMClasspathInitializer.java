/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.jdt.internal;

import org.eclipse.buckminster.jdt.JdtPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * 
 */
public class BMClasspathInitializer extends ClasspathContainerInitializer implements IResourceChangeListener {
	/**
	 * Constructor for RequiredPluginsInitializer.
	 */
	public BMClasspathInitializer() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.PRE_BUILD);
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#initialize(IPath,
	 *      IJavaProject)
	 */
	@Override
	public void initialize(IPath containerPath, IJavaProject javaProject) throws CoreException {
		if (javaProject == null || !BMClasspathContainer.PATH.isPrefixOf(containerPath))
			return;

		String targetDesignator = null;
		if (containerPath.segmentCount() == 2)
			targetDesignator = containerPath.lastSegment();

		IProject project = javaProject.getProject();
		BMClasspathContainer newCP = new BMClasspathContainer(project, targetDesignator);
		IClasspathContainer oldCP = JavaCore.getClasspathContainer(containerPath, javaProject);
		if (newCP.equals(oldCP))
			return;

		JavaCore.setClasspathContainer(containerPath, new IJavaProject[] { javaProject }, new IClasspathContainer[] { newCP }, null);
		JdtPlugin.getLogger().debug("%s container initialized for project %s", containerPath, project.getName()); //$NON-NLS-1$
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IPath path = BMClasspathContainer.PATH;
		try {
			IJavaModel model = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
			for (IJavaProject javaProject : model.getJavaProjects()) {
				for (IClasspathEntry rawEntry : javaProject.readRawClasspath()) {
					if (rawEntry.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
						IPath entryPath = rawEntry.getPath();
						if (path.isPrefixOf(entryPath)) {
							this.initialize(entryPath, javaProject);
							return;
						}
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
