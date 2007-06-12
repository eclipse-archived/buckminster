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
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
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
import org.osgi.framework.Bundle;

/**
 * @author ken1
 * @author Thomas Hallgren
 */
public class AntActor extends AbstractActor
{
	public static final String ID = "ant";

	public final static String BUILD_SCRIPT_ID = "id";

	public final static String BUILD_SCRIPT_RESOURCE = "resource";

	protected final IPath getBuildFile(IActionContext ctx) throws CoreException
	{
		// script name must always be relative to project root
		//
		String buildFileId = this.getBuildFileIdProperty(ctx);
		String buildFile = this.getBuildFileProperty(ctx);
		if(buildFile == null)
		{
			if(buildFileId == null)
				throw new BuckminsterException("Property not set: " + AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE);

			buildFileId = ExpandingProperties.expand(ctx.getProperties(), buildFileId, 0);
			return this.getBuildFileExtension(buildFileId);
		}

		if(buildFileId != null)
			throw new BuckminsterException("Properties " + AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE + " and "
				+ AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + " are mutually exclusive");

		buildFile = ExpandingProperties.expand(ctx.getProperties(), buildFile, 0);
		IPath buildFilePath = new Path(buildFile);
		if(!buildFilePath.isAbsolute())
			buildFilePath = ctx.getComponentLocation().append(buildFilePath);

		return buildFilePath;
	}

	protected String getBuildFileProperty(IActionContext ctx) throws CoreException
	{
		return TextUtils.emptyTrimmedStringAsNull(this.getActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE));
	}

	protected String getBuildFileIdProperty(IActionContext ctx) throws CoreException
	{
		return TextUtils.emptyTrimmedStringAsNull(this.getActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID));
	}

	protected final String getTargetsString(IActionContext ctx)
	{
		String tlist = this.getActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_TARGETS);

		// if no targets field has been defined, use the action name
		//
		return tlist == null ? ctx.getAction().getName() : tlist.trim();
	}

	protected final String[] getTargets(IActionContext ctx) throws CoreException
	{
		// if the user has explicitly entered a blank field, return null to
		// indicate 'use the default target'
		//
		String tlist = this.getTargetsString(ctx);
		if(tlist.length() == 0)
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
		return tlist.split("\\s+");
	}

	protected void addActorPathGroups(IActionContext ctx, Map<String, PathGroup[]> namedPathGroupArrays) throws CoreException
	{
	}

	protected Map<String, String> getDefaultProperties(IActionContext ctx) throws CoreException
	{
		return Collections.emptyMap();
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 100);
		monitor.subTask(ctx.getAction().getQualifiedName());
		
		PrintStream origOut = System.out;
		PrintStream origErr = System.err;
		try
		{
			IPath buildFile = this.getBuildFile(ctx);

			// We add the installer hints onto the context properties.
			//
			ExpandingProperties props = new ExpandingProperties();
			props.putAll(ctx.getProperties());
			props.putAll(this.getDefaultProperties(ctx));
			ctx.getAction().addInstallerHints(ctx, props);
			Map<String, PathGroup[]> namedPathGroupArrays = ctx.getNamedPathGroupArrays();
			addActorPathGroups(ctx, namedPathGroupArrays);
			addPathGroupArraysToProperties(namedPathGroupArrays, props);
			props.put("basedir", ctx.getComponentLocation().toOSString());
			MonitorUtils.worked(monitor, 10);

			System.setOut(ctx.getOutputStream());
			System.setErr(ctx.getErrorStream());

			AntRunner runner = new AntRunner();
			runner.setBuildFileLocation(buildFile);
			runner.setExecutionTargets(getTargets(ctx));
			runner.setBuildLogger("org.eclipse.buckminster.ant.support.AntBuildLogger");
			runner.addUserProperties(props);
			runner.run(MonitorUtils.subMonitor(monitor, 90));
			return Status.OK_STATUS;
		}
		catch(OperationCanceledException e)
		{
			return Status.CANCEL_STATUS;
		}
		catch(Error e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		catch(RuntimeException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		catch(CoreException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		finally
		{
			System.setOut(origOut);
			System.setErr(origErr);
			monitor.done();
		}
	}

	private IPath getBuildFileExtension(String buildFileId) throws CoreException
	{
		IConfigurationElement resourceElem = null;
		IExtensionRegistry er = Platform.getExtensionRegistry();
		for(IConfigurationElement elem : er.getConfigurationElementsFor(AntBuilderConstants.BUILD_SCRIPT_POINT))
		{
			if(elem.getAttribute(BUILD_SCRIPT_ID).equals(buildFileId))
			{
				resourceElem = elem;
				break;
			}
		}

		if(resourceElem == null)
			throw new BuckminsterException("No extension found defines "
				+ AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + ": " + buildFileId);

		// The resource must be loaded by the bundle that contributes it
		//
		String contributor = resourceElem.getContributor().getName();
		Bundle contributorBundle = Platform.getBundle(contributor);
		if(contributorBundle == null)
			throw new BuckminsterException("Unable to load bundle " + contributor);

		URL rsURL = contributorBundle.getResource(resourceElem.getAttribute(BUILD_SCRIPT_RESOURCE));
		if(rsURL == null)
			throw new BuckminsterException("Extension found using "
				+ AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + " " + buildFileId
				+ " appoints a non existing resource");

		try
		{
			rsURL = FileLocator.toFileURL(rsURL);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}

		if(!"file".equalsIgnoreCase(rsURL.getProtocol()))
			//
			// This should never happen. It's a resource in an active plugin right?
			//
			throw new BuckminsterException("Unexpected protolol");

		return FileUtils.getFileAsPath(rsURL);
	}

	private static void addPathGroupArraysToProperties(Map<String, PathGroup[]> namedPGA, Map<String, String> props)
	{
		if(namedPGA == null)
			return;

		StringBuilder sp_bld = new StringBuilder();
		StringBuilder fs_bld = new StringBuilder();
		StringBuilder key_bld = new StringBuilder();
		for(Map.Entry<String, PathGroup[]> namedPG : namedPGA.entrySet())
		{
			PathGroup[] pathGroups = namedPG.getValue();
			boolean singleton = (pathGroups.length == 1);
			fs_bld.setLength(0);
			sp_bld.setLength(0);
			for(PathGroup pathGroup : pathGroups)
			{
				IPath basePath = pathGroup.getBase();
				String base = basePath.toOSString();
				fs_bld.append('?');	// Start of path group marker
				fs_bld.append(base);
				IPath[] paths = pathGroup.getPaths();
				if(paths.length > 1)
					singleton = false;

				if(singleton)
					sp_bld.append(base);

				for(IPath path : paths)
				{
					String osPath = path.toOSString();
					fs_bld.append(FileUtils.PATH_SEP);
					fs_bld.append(osPath);
					if(singleton)
					{
						if(!basePath.hasTrailingSeparator())
							sp_bld.append(FileUtils.FILE_SEP);
						sp_bld.append(osPath);
					}
				}
			}
			String propKey = namedPG.getKey();
			key_bld.setLength(0);
			key_bld.append("fs:");
			key_bld.append(propKey);
			props.put(key_bld.toString(), fs_bld.toString());

			if(singleton)
			{
				key_bld.setLength(0);
				key_bld.append("sp:");
				key_bld.append(propKey);
				props.put(key_bld.toString(), sp_bld.toString());
			}
		}
	}
}
