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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.jdt.JdtPlugin;
import org.eclipse.buckminster.jdt.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;

public class BMClasspathContainer implements IClasspathContainer {
	public static final IPath PATH = new Path(JdtPlugin.CLASSPATH_CONTAINER_ID);

	private final IProject project;

	private final IClasspathEntry[] entries;

	public BMClasspathContainer(IProject project, String topTarget) throws CoreException {
		this.project = project;
		ArrayList<IClasspathEntry> cpes = new ArrayList<IClasspathEntry>();
		CSpec cspec = WorkspaceInfo.getCSpec(project);
		if (cspec != null)
			this.addNodeToClassPath(cspec, cpes, new ModelCache(), new HashSet<ComponentIdentifier>(), 0);
		this.entries = cpes.toArray(new IClasspathEntry[cpes.size()]);
	}

	/**
	 * Checks wether this container has the exact same entries as cp. The
	 * entries are compared for equality but not resolved. The order of the
	 * entries is significant.
	 * 
	 * @param cp
	 *            The container to compare with.
	 * @return true if the two containers have the exact same classpath.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof BMClasspathContainer))
			return false;
		BMClasspathContainer that = (BMClasspathContainer) o;

		if (!Arrays.equals(this.entries, that.entries))
			return false;

		return true;
	}

	public final IClasspathEntry[] getClasspathEntries() {
		return entries;
	}

	public String getDescription() {
		return Messages.BMClasspathContainer_description;
	}

	public int getKind() {
		return K_APPLICATION;
	}

	public IPath getPath() {
		return PATH;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(entries);
	}

	private void addNodeToClassPath(CSpec cspec, ArrayList<IClasspathEntry> cpes, IModelCache cache, HashSet<ComponentIdentifier> seenIDs, int depth)
			throws CoreException {
		// Depth == 0 is the component itself, depth == 1 is the component
		// scanning its
		// own dependencies.
		//
		// TODO: We must currently assume that all dependencies are re-exported.
		// Rectify
		// this when the actions enters the scene.
		//
		// if(cref != null && depth > 1 && !cref.isExported())
		// return;

		ComponentIdentifier cid = cspec.getComponentIdentifier();
		if (seenIDs.contains(cid))
			return;
		seenIDs.add(cid);

		for (ComponentRequest refToChild : cspec.getDependencies()) {
			CSpec childSpec = cache.findCSpec(cspec, refToChild);
			this.addNodeToClassPath(childSpec, cpes, cache, seenIDs, depth + 1);
		}

		IPath componentHome = cspec.getComponentLocation();
		IProject proj = WorkspaceInfo.getProject(cid);
		if (proj != null) {
			if (proj.getName().equals(project.getName()))
				//
				// Skip the project itself.
				//
				return;

			if (proj.isOpen() && proj.hasNature(JavaCore.NATURE_ID)) {
				// If we have a project entry, add that and nothing else.
				//
				IClasspathEntry cpe = JavaCore.newProjectEntry(proj.getFullPath());
				if (!cpes.contains(cpe))
					cpes.add(cpe);
			}
			return;
		}

		if (!componentHome.toFile().isFile())
			return;

		if (componentHome.lastSegment().endsWith(".jar")) //$NON-NLS-1$
		{
			IClasspathEntry cpe = JavaCore.newLibraryEntry(componentHome, null, null);
			if (!cpes.contains(cpe))
				cpes.add(cpe);
		}
	}
}
