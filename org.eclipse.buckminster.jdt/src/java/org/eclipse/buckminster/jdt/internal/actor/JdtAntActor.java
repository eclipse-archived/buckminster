/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.jdt.internal.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.jdt.internal.ClasspathEmitter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class JdtAntActor extends AntActor {
	@SuppressWarnings("hiding")
	public static final String ACTOR_ID = "jdt.ant"; //$NON-NLS-1$

	public static final String PROPERTY_PROJECT_CLASSPATH = "project.classpath"; //$NON-NLS-1$

	@Override
	protected void addActorPathGroups(IActionContext ctx, Map<String, PathGroup[]> namedPathGroupArrays) throws CoreException {
		IProject project = WorkspaceInfo.getProject(ctx.getCSpec().getComponentIdentifier());
		if (project == null)
			return;

		List<IPath> paths = ClasspathEmitter.finalClasspathResolve(project, null);
		int top = paths.size();
		if (top > 0) {
			HashMap<IPath, ArrayList<IPath>> blds = new HashMap<IPath, ArrayList<IPath>>();
			for (int idx = 0; idx < top; ++idx) {
				IPath relPath = null;
				IPath path = paths.get(idx);
				if (path.toFile().isFile()) {
					relPath = new Path(path.lastSegment());
					path = path.removeLastSegments(1);
				}
				ArrayList<IPath> bld = blds.get(path);
				if (bld == null) {
					bld = new ArrayList<IPath>();
					blds.put(path, bld);
				}
				if (relPath != null)
					bld.add(relPath);
			}

			ArrayList<PathGroup> pgs = new ArrayList<PathGroup>();
			for (Map.Entry<IPath, ArrayList<IPath>> entry : blds.entrySet()) {
				ArrayList<IPath> bld = entry.getValue();
				int nPaths = bld.size();
				IPath[] blda = nPaths == 0 ? Trivial.EMPTY_PATH_ARRAY : bld.toArray(new IPath[nPaths]);
				pgs.add(new PathGroup(entry.getKey(), blda));
			}
			namedPathGroupArrays.put(PROPERTY_PROJECT_CLASSPATH, pgs.toArray(new PathGroup[pgs.size()]));
		}
	}
}
