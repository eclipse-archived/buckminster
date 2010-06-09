/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.internal.build;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.buckminster.ant.AntRunner;
import org.eclipse.buckminster.ant.Messages;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * This builder will execute Ant scripts fast and efficiently. Contrary to the
 * normal Eclipse builder, it not instantiate a new ClassLoader each time it is
 * called. The <code>ClassLoader</code> is in fact only instantiated once for
 * all bulder instances and the classpath can therefore not vary between those
 * instances.
 * </p>
 * <p>
 * The drawback with this design is that it is less flexible. The class path
 * cannot be modified on a per-builder basis (only for Ant as a whole)</code>
 * can vary.
 * </p>
 * <p>
 * A strong motivation for the selected design was that an approach where a new
 * chain of ClassLoaders where set up each time around was extremely memory
 * consuming. Caches used by the <code>sun.misc.URL.Classpath</code> (probably
 * timed caches) caused <code>OutOfMemoryException</code> when many builds where
 * executed within a short timeframe.
 * </p>
 * 
 * @author Thomas Hallgren
 */
public class AntBuilder extends AbstractBuckminsterBuilder implements AntBuilderConstants {
	private IFile scriptFile;

	@Override
	protected IProject[] doBuild(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		PrintStream origOut = System.out;
		PrintStream origErr = System.err;
		try {
			String target = getTarget(args, kind);
			String[] targets = target == null ? null : new String[] { target };

			// only set the 'kind' property if a propname is given
			//
			String kindPropName = AbstractBuckminsterBuilder.getValue(args, ARG_BUILD_KIND_PROPERTY_KEY);
			Map<String, String> props = getFixedProperties(args);
			if (kindPropName != null)
				props.put(kindPropName, AbstractBuckminsterBuilder.kindToString(kind));

			IPath baseDir = getBaseDir(args);
			if (baseDir != null)
				props.put("basedir", baseDir.toOSString()); //$NON-NLS-1$

			System.setOut(getOutStream());
			System.setErr(getErrStream());

			AntRunner runner = new AntRunner();
			runner.setBuildFileLocation(getScriptFile(args).getLocation());
			runner.setExecutionTargets(targets);
			runner.setBuildLogger("org.eclipse.buckminster.ant.support.AntBuildLogger"); //$NON-NLS-1$
			runner.addUserProperties(props);
			runner.run(monitor);
		} catch (CoreException e) {
			CorePlugin.getLogger().error(e, e.getMessage());
			throw e;
		} finally {
			System.setOut(origOut);
			System.setErr(origErr);
		}
		return null;
	}

	/**
	 * The path to use as <code>basedir</code> in the ant build.
	 * 
	 * @param args
	 *            The map of arguments that where passed to the build.
	 * @return The basedir of the build or <code>null</code> if not set.
	 */
	protected IPath getBaseDir(Map<String, String> args) {
		String baseDir = getValue(args, ARG_OVERRIDE_BASEDIR_KEY);
		IPath baseDirPath = null;
		if (baseDir != null) {
			baseDirPath = new Path(baseDir);
			if (!baseDirPath.isAbsolute())
				baseDirPath = getProject().getLocation().append(baseDirPath);
		}
		return baseDirPath;
	}

	/**
	 * Returns a new map with fixed properties that are guaranteed not to change
	 * between each build. The method will return a new map for each call and it
	 * is ok if the caller wishes to add more entries to that map.
	 * 
	 * @param args
	 *            The map of arguments that where passed to the build.
	 * @return A map of fixed properties.
	 */
	protected Map<String, String> getFixedProperties(Map<String, String> args) {
		Map<String, String> props = new HashMap<String, String>();

		// only set the 'component name' property if a propname is given
		//
		String componentPropName = getValue(args, ARG_COMPONENT_NAME_PROPERTY_KEY);
		if (componentPropName != null)
			props.put(componentPropName, getProject().getName());
		return props;
	}

	/**
	 * Returns the script file
	 * 
	 * @param args
	 *            The map of arguments that where passed to the build.
	 * @return The script file.
	 */
	protected IFile getScriptFile(Map<String, String> args) throws CoreException {
		if (scriptFile == null) {
			// script name must always be relative to project root
			//
			String sf = getValue(args, ARG_SCRIPT_FILE_KEY);
			if (sf == null)
				sf = DEFAULT_SCRIPT_FILE;
			IPath relativeScriptFilePath = new Path(sf);
			if (relativeScriptFilePath.isAbsolute())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.AntBuilder_the_script_file_name_must_be_relative_to_the_project_root_0, sf));
			scriptFile = getProject().getFile(relativeScriptFilePath);
			notifyOnChangedResources(new IResource[] { scriptFile });
		}
		return scriptFile;
	}

	/**
	 * Returns the target for a specific <code>kind</code> of build or
	 * <code>null</code> if no target has been specificed for the
	 * <code>kind</code>.
	 * 
	 * @param args
	 *            The map of arguments that where passed to the build.
	 * @param kind
	 *            The
	 *            {@link org.eclipse.core.resources.IncrementalProjectBuilder
	 *            IncrementalProjectBuilder} build kind.
	 * @return The name of the target.
	 */
	protected String getTarget(Map<String, String> args, int kind) {
		String target = null;
		if (kind == AUTO_BUILD)
			target = getValue(args, ARG_AUTO_KIND_TARGET_KEY);
		else if (kind == CLEAN_BUILD) {
			target = getValue(args, ARG_CLEAN_KIND_TARGET_KEY);
			if (target == null)
				target = DEFAULT_CLEAN_KIND_TARGET;
		} else if (kind == FULL_BUILD)
			target = getValue(args, ARG_FULL_KIND_TARGET_KEY);
		else if (kind == INCREMENTAL_BUILD)
			target = getValue(args, ARG_INCREMENTAL_KIND_TARGET_KEY);
		return target;
	}

	@Override
	protected void resourcesChangeNotification(IResource[] changedResources) {
		// should only be the scriptfile at this time...
		// if someone did something to the build script, force a full build
		//
		for (IResource r : changedResources)
			if (r.equals(scriptFile)) {
				forgetLastBuiltState();
				scriptFile = null;
				break;
			}
	}
}
