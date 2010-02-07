/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.jdt.internal;

import java.text.Format;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.buckminster.core.build.PropertiesEmitter;
import org.eclipse.buckminster.core.helpers.ArrayUtils;
import org.eclipse.buckminster.jdt.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.ClasspathEntry;

/**
 * A Builder that emits the fully resolved classpath of the current project.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ClasspathEmitter extends PropertiesEmitter {
	public static final String ARG_FORMAT_CLASSPATH = "format.classpath"; //$NON-NLS-1$

	public static final String ARG_TARGET = "target"; //$NON-NLS-1$

	/**
	 * Path separator. Defaults to setting of system property
	 * &quot;path.separator&quot;
	 */
	public static final String ARG_PATH_SEPARATOR = "path.separator"; //$NON-NLS-1$

	public static final Format FORMAT_CLASSPATH = new MessageFormat("bm.classpath"); //$NON-NLS-1$

	private static final String pathSeparator = System.getProperty("path.separator", ":"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Obtains the classpath that has been declared for the current project. The
	 * classpath entries are resolved down to their absolute location in the
	 * local file system. And empty list will be returned if the given project
	 * is not represented by a <code>IJavaProject</code> in the java model.
	 * 
	 * @param project
	 *            The project for which the classpath should be resolved.
	 * @param target
	 *            The target for which the classpath should be resolved or null
	 *            if the current project classpath should be used.
	 * @return A list of absolute paths in the local file system.
	 * @throws CoreException
	 */
	public static List<IPath> finalClasspathResolve(IProject project, String target) throws CoreException {
		IJavaModel model = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());

		HashSet<IPath> seenPaths = new HashSet<IPath>();
		HashSet<String> seenProjects = new HashSet<String>();
		ArrayList<IPath> finalClasspath = new ArrayList<IPath>();

		appendPaths(model, project, target, finalClasspath, seenPaths, seenProjects, true);
		return finalClasspath;
	}

	/**
	 * Returns the default output folder relative to the project.
	 * 
	 * @param project
	 * @return The folder or <code>null</code> if not applicable.
	 * @throws CoreException
	 */
	public static IPath getDefaultOutputFolder(IProject project) throws CoreException {
		String projectName = project.getName();
		IJavaModel model = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
		IJavaProject javaProject = model.getJavaProject(projectName);
		if (javaProject == null || !javaProject.exists())
			return null;

		return javaProject.getOutputLocation().removeFirstSegments(1);
	}

	private static void appendPaths(IJavaModel model, IProject project, String target, List<IPath> path, HashSet<IPath> seenPaths,
			HashSet<String> seenProjects, boolean atTop) throws CoreException {
		Logger log = Buckminster.getLogger();
		String projectName = project.getName();
		if (seenProjects.contains(projectName))
			return;
		seenProjects.add(projectName);
		log.debug("Emitting classpath for project %s...", projectName);

		IJavaProject javaProject = model.getJavaProject(projectName);
		IClasspathEntry[] entries;
		if (javaProject == null || !javaProject.exists()) {
			// The project may still be a component that exports jar files.
			//
			BMClasspathContainer container = new BMClasspathContainer(project, target);
			entries = container.getClasspathEntries();
			log.debug(" not a java project, contains %d entries", Integer.valueOf(entries.length));
		} else {
			entries = (atTop && target != null) ? changeClasspathForTarget(javaProject, target) : javaProject.getResolvedClasspath(false);
			log.debug(" java project, contains %d entries", Integer.valueOf(entries.length));
		}

		for (IClasspathEntry entry : entries) {
			IPath entryPath;
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_LIBRARY:
					log.debug(" found library with path: %s", entry.getPath());
					if (!(atTop || entry.isExported())) {
						log.debug(" skipping path %s. It's neither at top nor exported", entry.getPath());
						continue;
					}

					entryPath = entry.getPath();
					break;
				case IClasspathEntry.CPE_SOURCE:
					entryPath = entry.getOutputLocation();
					if (entryPath == null) {
						// Uses default output location
						//
						IJavaProject proj = model.getJavaProject(entry.getPath().segment(0));
						if (proj == null)
							continue;
						entryPath = proj.getOutputLocation();
					}
					log.debug(" found source with path: %s", entryPath);
					break;
				case IClasspathEntry.CPE_PROJECT:
					projectName = entry.getPath().segment(0);
					log.debug(" found project: %s", projectName);
					if (!(atTop || entry.isExported())) {
						log.debug(" skipping project %s. It's neither at top nor exported", projectName);
						continue;
					}

					IProject conProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
					appendPaths(model, conProject, null, path, seenPaths, seenProjects, false);
					continue;
				default:
					throw BuckminsterException.fromMessage(Messages.unexpected_classpath_entry_kind);
			}

			IResource folder = ResourcesPlugin.getWorkspace().getRoot().findMember(entryPath);
			if (folder != null) {
				log.debug(" path %s is inside workspace, switching to %s", entryPath, folder.getLocation());
				entryPath = folder.getLocation();
			}

			if (!seenPaths.contains(entryPath)) {
				seenPaths.add(entryPath);
				path.add(entryPath);
				log.debug(" path %s added", entryPath);
			}
		}
	}

	/**
	 * This method obtains the raw classpath from the javaProject and scans it
	 * for BMClasspathContainer. The first one found is either kept if it
	 * corresponds to the target or replaced if not. All other
	 * BMClasspathContainers are removed. If no BMClasspathContainer was found,
	 * a new one that represents the target is added first in the list. All
	 * IClasspathEntry instances are then resolved.
	 * 
	 * @param javaProject
	 * @param target
	 * @return
	 * @throws JavaModelException
	 */
	private static IClasspathEntry[] changeClasspathForTarget(IJavaProject javaProject, String target) throws CoreException {
		boolean entriesChanged = false;
		boolean haveOtherBMCPs = false;
		boolean targetContainerInstalled = false;

		Logger log = Buckminster.getLogger();
		log.debug("Changing classpath for project %s into %s", javaProject.getProject().getName(), target);
		IPath desiredContainer = BMClasspathContainer.PATH.append(target);
		IClasspathEntry[] rawEntries = javaProject.readRawClasspath();
		int top = rawEntries.length;
		for (int idx = 0; idx < top; ++idx) {
			IClasspathEntry rawEntry = rawEntries[idx];
			if (rawEntry.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
				IPath entryPath = rawEntry.getPath();
				if (BMClasspathContainer.PATH.isPrefixOf(entryPath)) {
					if (!targetContainerInstalled) {
						if (!desiredContainer.equals(entryPath)) {
							// This is not the desired container. Replace it.
							//
							rawEntries[idx] = JavaCore.newContainerEntry(desiredContainer);
							entriesChanged = true;
						}
						targetContainerInstalled = true;
					} else
						haveOtherBMCPs = true;
				}
			}
		}

		if (targetContainerInstalled) {
			if (haveOtherBMCPs) {
				// Remove other Buckminster containers
				//
				ArrayList<IClasspathEntry> newEntries = new ArrayList<IClasspathEntry>(top);
				for (int idx = 0; idx < top; ++idx) {
					IClasspathEntry rawEntry = rawEntries[idx];
					if (rawEntry.getEntryKind() != IClasspathEntry.CPE_CONTAINER || rawEntry.getPath().equals(desiredContainer))
						newEntries.add(rawEntry);
				}
				rawEntries = newEntries.toArray(new IClasspathEntry[newEntries.size()]);
				entriesChanged = true;
			}
		} else {
			rawEntries = ArrayUtils.appendFirst(rawEntries, new IClasspathEntry[] { JavaCore.newContainerEntry(desiredContainer) });
			entriesChanged = true;
		}

		log.debug(entriesChanged ? " changes detected" : " no changes detected");

		return entriesChanged ? getResolvedClasspath(javaProject, rawEntries) : javaProject.getResolvedClasspath(false);
	}

	private static IClasspathEntry[] getResolvedClasspath(IJavaProject project, IClasspathEntry[] entries) throws CoreException {
		ArrayList<IClasspathEntry> resolvedEntries = new ArrayList<IClasspathEntry>();
		for (IClasspathEntry rawEntry : entries) {
			switch (rawEntry.getEntryKind()) {
				case IClasspathEntry.CPE_VARIABLE:

					IClasspathEntry resolvedEntry = null;
					try {
						resolvedEntry = JavaCore.getResolvedClasspathEntry(rawEntry);
					} catch (AssertionFailedException e) {
					}
					if (resolvedEntry == null)
						throw new JavaModelException(ClasspathEntry.validateClasspathEntry(project, rawEntry, false, false));
					break;

				case IClasspathEntry.CPE_CONTAINER:
					IPath entryPath = rawEntry.getPath();
					IClasspathContainer container;
					if (BMClasspathContainer.PATH.isPrefixOf(entryPath))
						//
						// This one is probably not in the project classpath
						//
						container = new BMClasspathContainer(project.getProject(), entryPath.segmentCount() > 1 ? entryPath.lastSegment() : null);
					else
						container = JavaCore.getClasspathContainer(entryPath, project);

					if (container == null)
						throw new JavaModelException(ClasspathEntry.validateClasspathEntry(project, rawEntry, false, false));

					IClasspathEntry[] containerEntries = container.getClasspathEntries();
					if (containerEntries == null)
						continue;

					for (IClasspathEntry cEntry : containerEntries) {
						// if container is exported or restricted, then its
						// nested
						// entries must in turn be exported
						//
						cEntry = ((ClasspathEntry) cEntry).combineWith((ClasspathEntry) rawEntry);
						resolvedEntries.add(cEntry);
					}
					continue;
			}
			resolvedEntries.add(rawEntry);
		}
		return resolvedEntries.toArray(new IClasspathEntry[resolvedEntries.size()]);
	}

	@Override
	protected void addFormatters() {
		this.addFormat(ARG_FORMAT_CLASSPATH, FORMAT_CLASSPATH);
	}

	@Override
	protected void appendProperties() throws CoreException {
		IProject project = this.getProject();
		StringBuilder bld = new StringBuilder();
		String target = this.getArgument(ARG_TARGET);
		String pathSep = this.getArgument(ARG_PATH_SEPARATOR);
		if (pathSep == null)
			pathSep = pathSeparator;

		for (IPath location : finalClasspathResolve(project, target)) {
			if (bld.length() > 0)
				bld.append(pathSeparator);
			bld.append(this.formatPath(location));
		}
		this.addProperty(ARG_FORMAT_CLASSPATH, new String[] { project.getName(), target }, bld.toString());
	}
}
