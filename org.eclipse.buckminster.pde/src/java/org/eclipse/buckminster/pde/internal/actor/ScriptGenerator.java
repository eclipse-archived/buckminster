/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.io.File;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.ResolverError;
import org.eclipse.osgi.service.resolver.State;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.IXMLConstants;
import org.eclipse.pde.internal.build.PDEUIStateWrapper;
import org.eclipse.pde.internal.build.builder.AbstractBuildScriptGenerator;
import org.eclipse.pde.internal.build.site.BuildTimeSiteFactory;
import org.eclipse.pde.internal.core.PDEState;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

/**
 * This abstract actor implements functionality common to all PDE script generating
 * actors.
 *
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public abstract class ScriptGenerator extends AbstractActor implements IXMLConstants,  IPDEBuildConstants
{
	public static final String PROPERTY_BUILDING_OSGI = "buildingOSGi";
	public static final String PROPERTY_SIGN_JARS = "signJars";

	/**
	 * Extracts the full path of the generated build script from the products of the
	 * invoking action.
	 *
	 * @param ctx The context used for the action invocation
	 * @return The full filesystem path of the build script
	 * @throws CoreException
	 */
	protected IPath getScriptPath(IActionContext ctx) throws CoreException
	{
		Action action = ctx.getAction();
		PathGroup[] groups = action.getPathGroups(ctx);
		if(!(groups.length == 1 || groups[0].getPaths().length == 1))
			throw new BuckminsterException("The " + this.getId() + " must produce exactly one path");

		PathGroup product = groups[0];
		IPath fullPath = product.getBase().append(product.getPaths()[0]);

		// Ensure that the directory exists
		//
		fullPath.removeLastSegments(1).toFile().mkdirs();
		return fullPath;
	}

	/**
	 * Perform basic build script generator initialization. This involves assigning a correctly
	 * initialized {@link BuildTimeSiteFactory} that reflects the current {@link PDEState} and
	 * setting some sane defaults.
	 *
	 * @param generator The generator to initialize
	 * @param ctx The context used for the action invocation
	 * @throws CoreException
	 */
	protected void initScript(AbstractBuildScriptGenerator generator, IActionContext ctx, PDEState pdeState) throws CoreException
	{
		State state = pdeState.getState();
		BuildTimeSiteFactory factory = new BuildTimeSiteFactory();
		PDEUIStateWrapper wrapper = new PDEUIStateWrapper();
		wrapper.setState(state);
		wrapper.setNextId(pdeState.getNextId());
		wrapper.setExtraData(TargetPlatformHelper.getBundleClasspaths(pdeState), TargetPlatformHelper.getPatchMap(pdeState));
		factory.setSitePaths(TargetPlatformHelper.getFeaturePaths());
		factory.setInitialState(wrapper);

		generator.setBuildingOSGi(getBooleanProperty(ctx.getProperties(), PROPERTY_BUILDING_OSGI, true));
		generator.setReportResolutionErrors(true);
		generator.setIgnoreMissingPropertiesFile(true);
		generator.includePlatformIndependent(true);
		generator.setCompiledElements(generator.getCompiledElements());
		generator.setBuildSiteFactory(factory);
	}

	private static final UUID KNOWN_LOCATIONS = UUID.randomUUID();
	private static final UUID PDE_STATE = UUID.randomUUID();

	@SuppressWarnings("unchecked")
	private static Set<File> getKnownLocations(IActionContext ctx)
	{
		Map<UUID,Object> invocationCache = ctx.getInvocationCache();
		synchronized(invocationCache)
		{
			Set<File> knownLocations = (Set<File>)invocationCache.get(KNOWN_LOCATIONS);
			if(knownLocations == null)
			{
				knownLocations = new HashSet<File>();
				invocationCache.put(KNOWN_LOCATIONS, knownLocations);
			}
			return knownLocations;
		}
	}

	private static PDEState getPDEState(IActionContext ctx)
	{
		Map<UUID,Object> invocationCache = ctx.getInvocationCache();
		synchronized(invocationCache)
		{
			PDEState state = (PDEState)invocationCache.get(PDE_STATE);
			if(state == null)
			{
				state = new PDEState(Trivial.EMPTY_URL_ARRAY, Trivial.EMPTY_URL_ARRAY, true, new NullProgressMonitor());
				invocationCache.put(PDE_STATE, state);
			}
			return state;
		}
	}

	PDEState createStateFromPrerequisites(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Set<File> knownLocations = getKnownLocations(ctx);
			HashMap<String, PathGroup[]> pgas = new HashMap<String, PathGroup[]>();
			ctx.addPrerequisitePathGroups(pgas);
			HashSet<File> ofInterest = new HashSet<File>();
			for(PathGroup[] pathGroups : pgas.values())
			{
				for(PathGroup pathGroup : pathGroups)
				{
					IPath base = pathGroup.getBase();
					IPath relPaths[] = pathGroup.getPaths();
					if(relPaths.length > 0)
					{
						for(IPath relPath : relPaths)
						{
							File file = base.append(relPath).toFile();
							if(!knownLocations.contains(file))
								ofInterest.add(file);
						}
						continue;
					}

					// We don't have a path so we need to resolve
					// the base. Everything beneath it is considered
					// part of the prerequisite
					//
					for(File file : base.toFile().listFiles())
					{
						String name = file.getName();
						if((name.endsWith(".jar") || name.endsWith(".zip")) && !knownLocations.contains(file))
							ofInterest.add(file);
					}
				}
			}

			PDEState pdeState = getPDEState(ctx);
			int top = ofInterest.size();
			if(top == 0)
				return pdeState;

			boolean didAdd = false;
			Dictionary<?,?>[] platformProperties = pdeState.getState().getPlatformProperties();
			for(File file : ofInterest.toArray(new File[ofInterest.size()]))
			{
				BundleDescription bundle = pdeState.addBundle(file, pdeState.getNextId());
				if(bundle == null)
					continue;

				String platformFilter = bundle.getPlatformFilter();
				boolean filterMatch = (platformFilter == null);
				if(!filterMatch)
				{
					try
					{
						Filter filter = FrameworkUtil.createFilter(platformFilter);
						for(int i = 0; i < platformProperties.length; i++)
							if (filter.match(platformProperties[i]))
							{
								filterMatch = true;
								break;
							}
					}
					catch (InvalidSyntaxException e)
					{
					}
				}
				if(filterMatch)
				{
					didAdd = true;
					knownLocations.add(file);
				}
				else
					pdeState.removeBundleDescription(bundle);
			}

			if(!didAdd)
				return pdeState;

			pdeState.resolveState(false);
			State state = pdeState.getState();
			String pid = PDEPlugin.getPluginId();
			MultiStatus problems = null;
			for(BundleDescription desc : state.getBundles())
			{
				ResolverError[] errors = pdeState.getState().getResolverErrors(desc);
				for(ResolverError error : errors)
				{
					if(problems == null)
						problems = new MultiStatus(pid, IStatus.ERROR, "Unable to resolve state", null);
					problems.add(new Status(IStatus.ERROR, pid, IStatus.OK, error.toString(), null));
				}
			}
			if(problems != null)
				throw new CoreException(problems);
			return pdeState;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
