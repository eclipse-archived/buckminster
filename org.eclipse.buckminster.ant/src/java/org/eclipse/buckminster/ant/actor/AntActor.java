/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant.actor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.buckminster.ant.AntRunner;
import org.eclipse.buckminster.ant.Messages;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * @author ken1
 * @author Thomas Hallgren
 */
public class AntActor extends AbstractActor {
	public static final String ACTOR_ID = "ant"; //$NON-NLS-1$

	public static final String PROP_BUILD_FILE_ID = "buildFileId"; //$NON-NLS-1$

	public static final String PROP_TARGETS = "targets"; //$NON-NLS-1$

	public static final String PROP_BUILD_FILE = "buildFile"; //$NON-NLS-1$

	private static final String BUILD_SCRIPT_POINT = AntBuilderConstants.PLUGIN_ID + ".buildScripts"; //$NON-NLS-1$

	private final static String BUILD_SCRIPT_ID = "id"; //$NON-NLS-1$

	private final static String BUILD_SCRIPT_RESOURCE = "resource"; //$NON-NLS-1$

	public static IPath getBuildFileExtension(String buildFileId) throws CoreException {
		IConfigurationElement resourceElem = null;
		IExtensionRegistry er = Platform.getExtensionRegistry();
		for (IConfigurationElement elem : er.getConfigurationElementsFor(BUILD_SCRIPT_POINT)) {
			if (elem.getAttribute(BUILD_SCRIPT_ID).equals(buildFileId)) {
				resourceElem = elem;
				break;
			}
		}

		if (resourceElem == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_No_extension_found_defines_0_1, AntActor.PROP_BUILD_FILE_ID,
					buildFileId));

		// The resource must be loaded by the bundle that contributes it
		//
		String contributor = resourceElem.getContributor().getName();
		Bundle contributorBundle = Platform.getBundle(contributor);
		if (contributorBundle == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_Unable_to_load_bundle_0, contributor));

		URL rsURL = contributorBundle.getResource(resourceElem.getAttribute(BUILD_SCRIPT_RESOURCE));
		if (rsURL == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_Extension_found_using_0_1_appoints_non_existing_resource,
					AntActor.PROP_BUILD_FILE_ID, buildFileId));

		try {
			rsURL = FileLocator.toFileURL(rsURL);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}

		if (!"file".equalsIgnoreCase(rsURL.getProtocol())) //$NON-NLS-1$
			//
			// This should never happen. It's a resource in an active plug-in
			// right?
			//
			throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_Unexpected_protocol_0, rsURL.getProtocol()));

		return FileUtils.getFileAsPath(rsURL);
	}

	private static void addPathGroupArraysToProperties(Map<String, PathGroup[]> namedPGA, Map<String, String> props) {
		if (namedPGA == null)
			return;

		StringBuilder sp_bld = new StringBuilder();
		StringBuilder fs_bld = new StringBuilder();
		StringBuilder key_bld = new StringBuilder();
		for (Map.Entry<String, PathGroup[]> namedPG : namedPGA.entrySet()) {
			PathGroup[] pathGroups = namedPG.getValue();
			boolean singleton = (pathGroups.length == 1);
			fs_bld.setLength(0);
			sp_bld.setLength(0);
			for (PathGroup pathGroup : pathGroups) {
				IPath basePath = pathGroup.getBase();
				String base = basePath.toOSString();
				fs_bld.append('?'); // Start of path group marker
				fs_bld.append(base);
				IPath[] paths = pathGroup.getPaths();
				if (paths.length > 1)
					singleton = false;

				if (singleton)
					sp_bld.append(base);

				for (IPath path : paths) {
					String osPath = path.toOSString();
					fs_bld.append(FileUtils.PATH_SEP);
					fs_bld.append(osPath);
					if (singleton) {
						if (!basePath.hasTrailingSeparator())
							sp_bld.append(FileUtils.FILE_SEP);
						sp_bld.append(osPath);
					}
				}
			}
			String propKey = namedPG.getKey();
			key_bld.setLength(0);
			key_bld.append("fs:"); //$NON-NLS-1$
			key_bld.append(propKey);
			props.put(key_bld.toString(), fs_bld.toString());

			if (singleton) {
				key_bld.setLength(0);
				key_bld.append("sp:"); //$NON-NLS-1$
				key_bld.append(propKey);
				props.put(key_bld.toString(), sp_bld.toString());
			}
		}
	}

	protected void addActorPathGroups(IActionContext ctx, Map<String, PathGroup[]> namedPathGroupArrays) throws CoreException {
	}

	protected final IPath getBuildFile(IActionContext ctx) throws CoreException {
		// script name must always be relative to project root
		//
		String buildFileId = getBuildFileIdProperty(ctx);
		String buildFile = getBuildFileProperty(ctx);
		if (buildFile == null) {
			if (buildFileId == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_Property_not_set_0, AntActor.PROP_BUILD_FILE));

			buildFileId = ExpandingProperties.expand(ctx.getProperties(), buildFileId, 0);
			return getBuildFileExtension(buildFileId);
		}

		if (buildFileId != null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.AntActor_Properties_0_and_1_are_mutually_exclusive, AntActor.PROP_BUILD_FILE,
					AntActor.PROP_BUILD_FILE_ID));

		buildFile = ExpandingProperties.expand(ctx.getProperties(), buildFile, 0);
		IPath buildFilePath = new Path(buildFile);
		if (!buildFilePath.isAbsolute())
			buildFilePath = ctx.getComponentLocation().append(buildFilePath);

		return buildFilePath;
	}

	protected String getBuildFileIdProperty(IActionContext ctx) throws CoreException {
		return TextUtils.notEmptyTrimmedString(getActorProperty(AntActor.PROP_BUILD_FILE_ID));
	}

	protected String getBuildFileProperty(IActionContext ctx) throws CoreException {
		return TextUtils.notEmptyTrimmedString(getActorProperty(AntActor.PROP_BUILD_FILE));
	}

	protected Map<String, String> getDefaultProperties(IActionContext ctx) throws CoreException {
		return Collections.emptyMap();
	}

	protected final String[] getTargets(IActionContext ctx) throws CoreException {
		// if the user has explicitly entered a blank field, return null to
		// indicate 'use the default target'
		//
		String tlist = getTargetsString(ctx);
		if (tlist.length() == 0)
			return null;

		// otherwise assume it's a ws separated list of targets
		// it's the users responsibility to ensure that the targets are supposed
		// to run in that specific order
		// most commonly, it's just a single target name, of course
		//
		// split on ws and return it. it's already trimmed around the edges so
		// there can be no strings that are empty or with embedded/surrounding
		// ws
		//
		return tlist.split("\\s+"); //$NON-NLS-1$
	}

	protected final String getTargetsString(IActionContext ctx) {
		String tlist = getActorProperty(AntActor.PROP_TARGETS);

		// if no targets field has been defined, use the action name
		//
		return tlist == null ? ctx.getAction().getName() : tlist.trim();
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 100);
		monitor.subTask(ctx.getAction().getQualifiedName());

		PrintStream origOut = System.out;
		PrintStream origErr = System.err;
		try {
			IPath buildFile = getBuildFile(ctx);

			// We add the installer hints onto the context properties.
			//
			ExpandingProperties<String> props = new ExpandingProperties<String>();
			for (Map.Entry<String, ? extends Object> entry : ctx.getProperties().entrySet())
				props.put(entry.getKey(), entry.getValue().toString());
			props.putAll(getDefaultProperties(ctx));
			Map<String, PathGroup[]> namedPathGroupArrays = ctx.getNamedPathGroupArrays();
			addActorPathGroups(ctx, namedPathGroupArrays);
			addPathGroupArraysToProperties(namedPathGroupArrays, props);
			props.put("basedir", ctx.getComponentLocation().toOSString()); //$NON-NLS-1$
			MonitorUtils.worked(monitor, 10);

			System.setOut(ctx.getOutputStream());
			System.setErr(ctx.getErrorStream());

			AntRunner runner = new AntRunner();
			runner.setBuildFileLocation(buildFile);
			runner.setExecutionTargets(getTargets(ctx));
			runner.setBuildLogger("org.eclipse.buckminster.ant.support.AntBuildLogger"); //$NON-NLS-1$
			runner.addUserProperties(props);
			runner.run(MonitorUtils.subMonitor(monitor, 90));
			return Status.OK_STATUS;
		} catch (OperationCanceledException e) {
			return Status.CANCEL_STATUS;
		} catch (Error e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		} catch (RuntimeException e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		} catch (CoreException e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		} finally {
			System.setOut(origOut);
			System.setErr(origErr);
			monitor.done();
		}
	}
}
