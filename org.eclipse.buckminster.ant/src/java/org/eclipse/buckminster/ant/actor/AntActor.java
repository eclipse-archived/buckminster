/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant.actor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.ant.internal.build.AntBuilder;
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

	// This is the actual ant project.
	//
	private Object m_internalAntActor;

	protected final IPath getBuildFile(IActionContext ctx) throws CoreException
	{
		// script name must always be relative to project root
		//
		String buildFileId = this.getBuildFileIdProperty(ctx);
		String buildFile = this.getBuildFileProperty(ctx);
		if(buildFile == null)
		{
			if(buildFileId == null)
				throw new BuckminsterException("Property not set: " + AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE);

			buildFileId = ExpandingProperties.expand(ctx.getProperties(), buildFileId, 0);
			return this.getBuildFileExtension(buildFileId);
		}

		if(buildFileId != null)
			throw new BuckminsterException("Properties " + AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE + " and "
				+ AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + " are mutually exclusive");

		buildFile = ExpandingProperties.expand(ctx.getProperties(), buildFile, 0);
		IPath buildFilePath = new Path(buildFile);
		if(!buildFilePath.isAbsolute())
			buildFilePath = ctx.getComponentLocation().append(buildFilePath);

		return buildFilePath;
	}

	protected String getBuildFileProperty(IActionContext ctx) throws CoreException
	{
		return TextUtils.emptyTrimmedStringAsNull(this.getActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE));
	}

	protected String getBuildFileIdProperty(IActionContext ctx) throws CoreException
	{
		return TextUtils.emptyTrimmedStringAsNull(this.getActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID));
	}

	protected final String getTargetsString(IActionContext ctx)
	{
		String tlist = this.getActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS);

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
		try
		{
			IPath buildFile = this.getBuildFile(ctx);

			if(m_internalAntActor == null)
				m_internalAntActor = AntBuilder.createInternalAntBuilder();

			// We add the installer hints onto the context properties.
			//
			ExpandingProperties props = new ExpandingProperties();
			props.putAll(ctx.getProperties());
			props.putAll(this.getDefaultProperties(ctx));
			ctx.getAction().addInstallerHints(ctx, props);
			Map<String, PathGroup[]> namedPathGroupArrays = ctx.getNamedPathGroupArrays();
			addActorPathGroups(ctx, namedPathGroupArrays);
			Map<String, List<IPath>> fileSetGroups = convertNamedPathGroupArrays(namedPathGroupArrays);
			MonitorUtils.worked(monitor, 10);

			IPath location = ctx.getComponentLocation();
			AntBuilder.invokeInternalAntBuilder(m_internalAntActor, buildFile, location,
				getTargets(ctx), props, fileSetGroups, ctx.getOutputStream(),
				ctx.getErrorStream());
			MonitorUtils.worked(monitor, 90);
			return Status.OK_STATUS;
		}
		catch(OperationCanceledException e)
		{
			return Status.CANCEL_STATUS;
		}
		catch(Error e)
		{
			Throwable t = BuckminsterException.unwind(e);
			AntPlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		catch(RuntimeException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			AntPlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		catch(CoreException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			AntPlugin.getLogger().error(t.toString(), t);
			throw e;
		}
		finally
		{
			monitor.done();
		}
	}

	private IPath getBuildFileExtension(String buildFileId) throws CoreException
	{
		IConfigurationElement resourceElem = null;
		IExtensionRegistry er = Platform.getExtensionRegistry();
		for(IConfigurationElement elem : er.getConfigurationElementsFor(AntPlugin.BUILD_SCRIPT_POINT))
		{
			if(elem.getAttribute(BUILD_SCRIPT_ID).equals(buildFileId))
			{
				resourceElem = elem;
				break;
			}
		}

		if(resourceElem == null)
			throw new BuckminsterException("No extension found defines "
				+ AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + ": " + buildFileId);

		// The resource must be loaded by the bundle that contributes it
		//
		String contributor = resourceElem.getContributor().getName();
		Bundle contributorBundle = Platform.getBundle(contributor);
		if(contributorBundle == null)
			throw new BuckminsterException("Unable to load bundle " + contributor);

		URL rsURL = contributorBundle.getResource(resourceElem.getAttribute(BUILD_SCRIPT_RESOURCE));
		if(rsURL == null)
			throw new BuckminsterException("Extension found using "
				+ AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID + " " + buildFileId
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

	private static Map<String, List<IPath>> convertNamedPathGroupArrays(Map<String, PathGroup[]> namedPGA)
	{
		if(namedPGA == null)
			return null;

		HashMap<String, List<IPath>> namedFSG = new HashMap<String, List<IPath>>();
		for(Map.Entry<String, PathGroup[]> namedPG : namedPGA.entrySet())
		{
			List<IPath> namedFS = namedFSG.get(namedPG.getKey());
			if(namedFS == null)
			{
				namedFS = new ArrayList<IPath>();
				namedFSG.put(namedPG.getKey(), namedFS);
			}
			for(PathGroup pg : namedPG.getValue())
			{
				namedFS.add(pg.getBase());
				for(IPath path : pg.getPaths())
					namedFS.add(path);
			}
		}
		return namedFSG;
	}
}
