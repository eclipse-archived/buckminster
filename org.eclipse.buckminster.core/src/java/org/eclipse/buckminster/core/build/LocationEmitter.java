/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import java.lang.reflect.Method;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * A Builder that emits the location of all components that are contained in, or
 * referenced by, the current project.
 * 
 * @author Thomas Hallgren
 */
public class LocationEmitter extends PropertiesEmitter {
	public static final String ARG_PURPOSE = "purpose"; //$NON-NLS-1$

	public static final String ARG_FORMAT_LOCATION = "format.location"; //$NON-NLS-1$

	public static final String ARG_FORMAT_ARTIFACTS = "format.artifacts"; //$NON-NLS-1$

	public static final Format FORMAT_LOCATION = new MessageFormat("bm.location.{0}"); //$NON-NLS-1$

	public static final Format FORMAT_LOCATION_ARTIFACT = new MessageFormat("bm.artifacts.{0}.{1}"); //$NON-NLS-1$

	private static boolean stateKnown = false;

	private static Method getDefaultOutputFolder;

	public static IPath getDefaultOutputFolder(IProject project) throws CoreException {
		if (!isJDTPresent())
			return null;

		try {
			return (IPath) getDefaultOutputFolder.invoke(null, new Object[] { project });
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public static synchronized boolean isJDTPresent() {
		if (!stateKnown) {
			Bundle bundle = Platform.getBundle("org.eclipse.buckminster.jdt"); //$NON-NLS-1$
			if (bundle == null) {
				stateKnown = true;
				return false;
			}

			try {
				Class<?> classpathEmitterClass = bundle.loadClass("org.eclipse.buckminster.jdt.internal.ClasspathEmitter"); //$NON-NLS-1$
				getDefaultOutputFolder = classpathEmitterClass.getMethod("getDefaultOutputFolder", //$NON-NLS-1$
						new Class[] { IProject.class });
			} catch (Exception e) {
			}
			stateKnown = true;
		}
		return getDefaultOutputFolder != null;
	}

	@Override
	protected void addFormatters() {
		addFormat(ARG_FORMAT_LOCATION, FORMAT_LOCATION);
		addFormat(ARG_FORMAT_ARTIFACTS, FORMAT_LOCATION_ARTIFACT);
	}

	@Override
	protected void appendProperties() throws CoreException {
		try {
			IModelCache cache = new ModelCache();
			CSpec cspec = WorkspaceInfo.getCSpec(getProject());
			String attr = getArgument(ARG_PURPOSE);
			Set<String> attrs = attr == null ? Collections.<String> emptySet() : Collections.singleton(attr);
			appendComponentProperties(cspec, attrs, cache, new HashSet<ComponentIdentifier>());
		} catch (MissingComponentException e) {
		}
	}

	private void appendComponentProperties(CSpec cspec, Set<String> attributes, IModelCache cache, HashSet<ComponentIdentifier> seenIds)
			throws CoreException {
		IComponentIdentifier cid = cspec.getComponentIdentifier();
		if (seenIds.contains(cid))
			return;

		IPath location = cspec.getComponentLocation();

		String componentName = cspec.getName();
		if (location.toFile().isFile()) {
			addProperty(ARG_FORMAT_LOCATION, new String[] { componentName }, formatPath(location.removeLastSegments(1)));
			addProperty(ARG_FORMAT_ARTIFACTS, new String[] { componentName, "default" }, location.lastSegment()); //$NON-NLS-1$
		} else {
			IProject project = WorkspaceInfo.getProject(cid);
			if (project != null) {
				// If this is a java project with a default output folder, then
				// emitt that as a default
				// artifact.
				//
				IPath dfltOutput = getDefaultOutputFolder(project);
				if (dfltOutput != null)
					addProperty(ARG_FORMAT_ARTIFACTS, new String[] { componentName, "default" }, dfltOutput //$NON-NLS-1$
							.toOSString());
			}
			addProperty(ARG_FORMAT_LOCATION, new String[] { componentName }, formatPath(location));
		}

		// Emit properties of all dependencies
		//
		cspec = cspec.prune(null, RMContext.getGlobalPropertyAdditions(), false, attributes);
		for (QualifiedDependency dep : cspec.getQualifiedDependencies(false)) {
			CSpec childSpec = cache.findCSpec(cspec, dep.getRequest());
			appendComponentProperties(childSpec, dep.getAttributeNames(), cache, seenIds);
		}
	}

}
