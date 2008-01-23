/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.internal.version.VersionDesignator;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceInfo
{
	/**
	 * Qualified name of the project persistent property where the {@link UUID} of the component is stored.<br/> This
	 * property will be set on IResource elements that represent component roots such as projects or resources that is
	 * inner bindings in projects.
	 */
	public static final QualifiedName PPKEY_COMPONENT_ID = new QualifiedName(CorePlugin.CORE_NAMESPACE, "componentID");

	/**
	 * Qualified name of the project persistent property where the a boolean value that indicates if the CSpec has been
	 * generated from other artifacts is stored.<br/> This property will be set on IResource elements that represent
	 * component roots such as projects or resources that is inner bindings in projects.
	 */
	public static final QualifiedName PPKEY_GENERATED_CSPEC = new QualifiedName(CorePlugin.CORE_NAMESPACE,
			"generatedCSpec");

	private static final HashMap<ComponentIdentifier, IPath> s_locationCache = new HashMap<ComponentIdentifier, IPath>();

	private static final HashMap<ComponentIdentifier, Resolution> s_resolutionCache = new HashMap<ComponentIdentifier, Resolution>();

	private static final IResource[] s_noResources = new IResource[0];

	public static void clearResolutionCache(ComponentIdentifier cid)
	{
		synchronized(s_resolutionCache)
		{
			s_resolutionCache.remove(cid);
		}
	}

	public static void clearCachedLocation(ComponentIdentifier cid)
	{
		synchronized(s_locationCache)
		{
			s_locationCache.remove(cid);
		}
	}

	public static void clearPersistentPropertyOnAll() throws CoreException
	{
		ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor()
		{
			public boolean visit(IResource resource) throws CoreException
			{
				if((resource instanceof IProject) && !((IProject)resource).isOpen())
					return false;

				String cidStr = resource.getPersistentProperty(PPKEY_COMPONENT_ID);
				if(cidStr != null)
					resource.setPersistentProperty(PPKEY_COMPONENT_ID, null);
				return true;
			}
		});
	}

	public static void forceRefreshOnAll(IProgressMonitor monitor)
	{
		MultiStatus status = new MultiStatus(CorePlugin.getID(), IStatus.OK, "Problems during metadata refresh", null);
		monitor.beginTask("Refreshing meta-data", 1000);
		try
		{
			clearPersistentPropertyOnAll();
			MonitorUtils.worked(monitor, 50);

			Resolution[] resolutions = StorageManager.getDefault().getResolutions().getElements();
			int ticksPerRefresh = 900 / resolutions.length;

			// Re-resolve all known bundles from the target platform
			//
			for(Resolution res : resolutions)
			{
				if(!IReaderType.ECLIPSE_PLATFORM.equals(res.getProvider().getReaderTypeId()))
					continue;

				try
				{
					resolveLocal(res.getRequest(), false);
				}
				catch(CoreException e)
				{
					status.add(e.getStatus());
				}
				MonitorUtils.worked(monitor, ticksPerRefresh);
			}

			// Re-resolve all projects
			//
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			MonitorUtils.worked(monitor, 50);
			MetadataSynchronizer mds = MetadataSynchronizer.getDefault();
			for(IProject project : projects)
			{
				try
				{
					mds.refreshProject(project, MonitorUtils.subMonitor(monitor, ticksPerRefresh));
				}
				catch(CoreException e)
				{
					status.add(e.getStatus());
				}
			}
		}
		catch(CoreException e)
		{
			status.add(e.getStatus());
		}
		finally
		{
			monitor.done();
		}
		CorePlugin.logWarningsAndErrors(status);
	}

	private static IPath getResolutionLocation(ISaxableStorage<Materialization> mats, Resolution res)
			throws CoreException
	{
		// Obtain the storage manager outside of the synchronization to avoid
		// possible deadlock.
		//
		StorageManager.getDefault();

		IPath location;
		ComponentIdentifier ci = res.getComponentIdentifier();
		synchronized(s_locationCache)
		{
			location = s_locationCache.get(ci);
			if(location == null)
			{
				for(Materialization mat : mats.getElements())
				{
					if(mat.getComponentIdentifier().equals(ci))
					{
						location = mat.getComponentLocation();
						break;
					}
				}
				if(location == null)
					location = res.getProvider().getReaderType().getFixedLocation(res);
				if(location != null)
					s_locationCache.put(ci, location);
			}
		}
		return location;
	}

	public static Resolution[] getActiveResolutions() throws CoreException
	{
		// Add the newest version of each known resolution
		//
		StorageManager sm = StorageManager.getDefault();
		HashMap<ComponentName, TimestampedKey> resolutionKeys = new HashMap<ComponentName, TimestampedKey>();
		ArrayList<TimestampedKey> duplicates = null;
		ISaxableStorage<Resolution> ress = sm.getResolutions();
		ISaxableStorage<Materialization> mats = sm.getMaterializations();
		for(Resolution res : ress.getElements())
		{
			UUID resId = res.getId();
			ComponentIdentifier ci = res.getComponentIdentifier();

			IPath location = getResolutionLocation(mats, res);
			if(location == null)
				continue;

			ComponentName cn = ci.toPureComponentName();
			TimestampedKey tsKey = new TimestampedKey(resId, ress.getCreationTime(resId));
			TimestampedKey prevTsKey = resolutionKeys.put(cn, tsKey);
			if(prevTsKey == null)
				continue;

			// Check real existence of locations. For performance reasons we only do
			// this when ambiguities arise.
			//
			Resolution prevRes = ress.getElement(prevTsKey.getKey());
			IPath prevLocation = getResolutionLocation(mats, prevRes);
			if(prevLocation == null)
				continue;

			if(location.toFile().exists())
			{
				if(location.equals(prevLocation))
				{
					// Discriminate using timestamp
					//
					if(prevTsKey.getCreationTime() > tsKey.getCreationTime())
					{
						// We just replaced a newer entry. Put it back!
						//
						resolutionKeys.put(cn, prevTsKey);
					}
					continue;
				}

				if(!prevLocation.toFile().exists())
					continue;

				// A resolution towards the target platform will always have a lower
				// precedence.
				//
				if(prevRes.getProvider().getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
				{
					if(!res.getProvider().getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
						continue;
				}
				else
				{
					if(res.getProvider().getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
					{
						resolutionKeys.put(cn, prevTsKey);
						continue;
					}
				}

				boolean versionEqual = false;
				IVersion currVersion = ci.getVersion();
				IVersion prevVersion = prevRes.getComponentIdentifier().getVersion();
				if(currVersion == null)
					versionEqual = (prevVersion == null);
				else if(prevVersion != null)
					versionEqual = currVersion.equalsUnqualified(prevVersion);

				if(versionEqual)
				{
					// Discriminate using timestamp
					//
					if(prevTsKey.getCreationTime() > tsKey.getCreationTime())
						resolutionKeys.put(cn, prevTsKey);
					continue;
				}

				// Apparently we have both locations present so we cannot
				// discriminate one of them
				//
				if(duplicates == null)
					duplicates = new ArrayList<TimestampedKey>();
				duplicates.add(prevTsKey);

				CorePlugin.getLogger().debug(
						"Found two entries for component %s. Version %s located at %s and version %s at %s", cn,
						currVersion, location, prevVersion, prevLocation);
				continue;
			}

			if(prevLocation.toFile().exists())
			{
				// New entry is bogus and old entry is valid
				//
				resolutionKeys.put(cn, prevTsKey);
			}
			else
			{
				// None of the entries were valid. Simply remove the entry
				//
				resolutionKeys.remove(cn);
			}
		}

		int top = resolutionKeys.size();
		if(duplicates != null)
			top += duplicates.size();

		Resolution[] result = new Resolution[top];
		int idx = 0;
		for(TimestampedKey tsKey : resolutionKeys.values())
			result[idx++] = ress.getElement(tsKey.getKey());

		if(duplicates != null)
			for(TimestampedKey tsKey : duplicates)
				result[idx++] = ress.getElement(tsKey.getKey());
		return result;
	}

	public static List<Resolution> getAllResolutions() throws CoreException
	{
		HashSet<Resolution> bld = new HashSet<Resolution>();
		for(Resolution cr : WorkspaceInfo.getActiveResolutions())
			bld.add(cr);

		for(ComponentIdentifier ci : TargetPlatform.getInstance().getComponents())
			bld.add(getResolution(ci));

		ArrayList<Resolution> sorted = new ArrayList<Resolution>(bld);
		Collections.sort(sorted, new Comparator<Resolution>()
		{
			public int compare(Resolution o1, Resolution o2)
			{
				int cmp = o1.getName().compareTo(o2.getName());
				if(cmp == 0)
				{
					IVersion v1 = o1.getVersion();
					IVersion v2 = o2.getVersion();
					if(v1 == null)
						cmp = v2 == null
								? 0
								: -1;
					else if(v2 == null)
						cmp = 1;
					else
						cmp = v1.compareTo(v2);
				}
				return cmp;
			}
		});
		return sorted;
	}

	public static ComponentIdentifier getComponentIdentifier(IResource resource)
	{
		String componentId = null;
		try
		{
			componentId = resource.getPersistentProperty(PPKEY_COMPONENT_ID);
			return componentId == null
					? null
					: ComponentIdentifier.parse(componentId);
		}
		catch(CoreException e)
		{
			return null;
		}
	}

	/**
	 * Returns the full path of the materialization for the component denoted by the <code>componentIdentifier</code>
	 * 
	 * @param componentIdentifier
	 *            The identifier of the component
	 * @return The path of the location
	 * @throws MissingComponentException
	 *             if the component cannot be found
	 * @throws AmbigousComponentException
	 *             if more then one component is an equally good match
	 * @throws CoreException
	 *             for other persistent storage related issues
	 */
	public static IPath getComponentLocation(ComponentIdentifier componentIdentifier) throws CoreException
	{
		// Obtain the storage manager outside of the synchronization to avoid
		// possible deadlock.
		//
		StorageManager.getDefault();

		synchronized(s_locationCache)
		{
			IPath location = s_locationCache.get(componentIdentifier);
			if(location != null)
				return location;

			Materialization mat = getMaterialization(componentIdentifier);
			if(mat == null)
			{
				Resolution resolution = getResolution(componentIdentifier);
				location = resolution.getProvider().getReaderType().getFixedLocation(resolution);
				if(location == null)
					throw new MissingComponentException(componentIdentifier.toString());
			}
			else
				location = mat.getComponentLocation();

			s_locationCache.put(componentIdentifier, location);
			return location;
		}
	}

	/**
	 * Returns the full path of the materialization for the component denoted by the <code>componentIdentifier</code>
	 * 
	 * @param cspec
	 *            The cspec of the component
	 * @return The path of the location
	 * @throws MissingComponentException
	 *             if the component cannot be found
	 * @throws AmbigousComponentException
	 *             if more then one component is an equally good match
	 * @throws CoreException
	 *             for other persistent storage related issues
	 */
	public static IPath getComponentLocation(CSpec cspec) throws CoreException
	{
		return getComponentLocation(cspec.getComponentIdentifier());
	}

	public static CSpec getCSpec(IResource resource) throws CoreException
	{
		ComponentIdentifier id = getComponentIdentifier(resource);
		return id == null
				? null
				: getResolution(id).getCSpec();
	}

	public static Materialization getMaterialization(ComponentIdentifier cid) throws CoreException
	{
		// Add all components for which we have a materialization
		//
		for(Materialization mat : StorageManager.getDefault().getMaterializations().getElements())
		{
			if(cid.equals(mat.getComponentIdentifier()))
			{
				if(!mat.getComponentLocation().toFile().exists())
				{
					mat.remove(StorageManager.getDefault());
					mat = null;
				}
				return mat;
			}
		}
		return null;
	}

	/**
	 * Returns the optional <code>Materialization</code> for the component. Components found in the target platform
	 * will not have a materialization.
	 * 
	 * @param resolution
	 *            The resolution for which we want a Materialization
	 * @return The materialization or <code>null</code> if it could not be found.
	 * @throws CoreException
	 */
	public static Materialization getMaterialization(Resolution resolution) throws CoreException
	{
		return getMaterialization(resolution.getComponentIdentifier());
	}

	/**
	 * Finds the open project that corresponds to the <code>componentIdentifier</code> and return it.
	 * 
	 * @param componentIdentifier
	 * @return The found project or <code>null</code> if no open project was found.
	 * @throws CoreException
	 */
	public static IProject getProject(ComponentIdentifier componentIdentifier) throws CoreException
	{
		return extractProject(getResources(componentIdentifier));
	}

	/**
	 * Finds the open project that corresponds to the <code>materialization</code> and return it.
	 * 
	 * @param materialization
	 * @return The found project or <code>null</code> if no open project was found.
	 * @throws CoreException
	 */
	public static IProject getProject(Materialization materialization) throws CoreException
	{
		return extractProject(getResources(materialization));
	}

	public static Resolution getResolution(ComponentIdentifier wanted) throws CoreException
	{
		// Obtain the storage manager outside of the synchronization to avoid
		// possible deadlock.
		//
		StorageManager.getDefault();

		synchronized(s_resolutionCache)
		{
			Resolution candidate = s_resolutionCache.get(wanted);
			if(candidate == null)
			{
				for(Resolution res : getActiveResolutions())
				{
					ComponentIdentifier cid = res.getCSpec().getComponentIdentifier();
					if(!wanted.matches(cid))
						continue;

					if(wanted.getVersion() != null)
					{
						candidate = res;
						break;
					}

					if(candidate != null)
						throw new AmbigousComponentException(wanted.toString());

					candidate = res;
				}
			}

			if(candidate != null)
			{
				s_resolutionCache.put(wanted, candidate);
				return candidate;
			}
		}

		IVersion v = wanted.getVersion();
		IVersionDesignator vd = (v == null)
				? null
				: VersionDesignator.explicit(v);
		try
		{
			return resolveLocal(new ComponentRequest(wanted.getName(), wanted.getComponentTypeID(), vd), true);
		}
		catch(CoreException e)
		{
			throw new MissingComponentException(wanted.toString());
		}
	}

	/**
	 * Returns the <code>CSpec</code> that best corresponds to the given <code>request</code>.
	 * 
	 * @param request
	 *            The component request
	 * @return The found Resolution
	 * @throws MissingComponentException
	 *             if the component cannot be found
	 * @throws AmbigousComponentException
	 *             if more then one component is an equally good match
	 * @throws CoreException
	 *             for other persistent storage related issues
	 */
	public static Resolution getResolution(ComponentRequest request, boolean fromResolver) throws CoreException
	{
		Resolution candidate = null;
		for(Resolution res : getActiveResolutions())
		{
			ComponentIdentifier id = res.getComponentIdentifier();
			if(!request.designates(id))
				continue;

			if(candidate != null)
			{
				// Compare versions
				//
				try
				{
					int cmp = Trivial
							.compareAllowNull(id.getVersion(), candidate.getComponentIdentifier().getVersion());
					if(cmp == 0)
						throw new AmbigousComponentException(id.toString());
					if(cmp < 0)
						continue;
				}
				catch(IllegalArgumentException e)
				{
					// Versions were not of the same type
					//
					continue;
				}
			}
			candidate = res;
		}

		if(candidate == null)
		{
			if(fromResolver)
				throw new MissingComponentException(request.toString());

			try
			{
				candidate = resolveLocal(request, true);
			}
			catch(CoreException e)
			{
				throw new MissingComponentException(request.toString());
			}
		}
		return candidate;
	}

	/**
	 * Obtains the resources currently bound to the given <code>componentIdentifier</code> and returns them. An empty
	 * array is returned when no resource was found.
	 * 
	 * @param componentIdentifier
	 *            The component to search for
	 * @return The found workspace resources.
	 * @throws CoreException
	 */
	public static IResource[] getResources(ComponentIdentifier componentIdentifier) throws CoreException
	{
		ISaxableStorage<Materialization> mats = StorageManager.getDefault().getMaterializations();
		for(Materialization mat : mats.getElements())
		{
			if(componentIdentifier.equals(mat.getComponentIdentifier()))
				return getResources(mat);
		}
		return s_noResources;
	}

	public static IResource[] getResources(ComponentRequest request) throws CoreException
	{
		IResource[] allFound = s_noResources;
		ISaxableStorage<Materialization> mats = StorageManager.getDefault().getMaterializations();
		for(Materialization mat : mats.getElements())
		{
			if(!request.designates(mat.getComponentIdentifier()))
				continue;

			IResource[] resources = getResources(mat);
			int top = resources.length;
			if(top == 0)
				continue;

			if(allFound == s_noResources)
				allFound = resources;
			else
			{
				IResource[] concat = new IResource[allFound.length + top];
				System.arraycopy(allFound, 0, concat, 0, allFound.length);
				System.arraycopy(resources, 0, concat, allFound.length, top);
				allFound = concat;
			}
		}
		return allFound;
	}

	public static IResource[] getResources(Materialization mat) throws CoreException
	{
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath location = mat.getComponentLocation();
		return location.hasTrailingSeparator()
				? wsRoot.findContainersForLocation(location)
				: wsRoot.findFilesForLocation(location);
	}

	public static Resolution resolveLocal(ComponentRequest request, boolean useWorkspace) throws CoreException
	{
		ComponentQueryBuilder qbld = new ComponentQueryBuilder();
		qbld.setRootRequest(request);
		qbld.setPlatformAgnostic(true);

		// Add an advisor node that matches the request and prohibits that we
		// do something using an existing materialization or something external.
		//
		AdvisorNodeBuilder nodeBld = new AdvisorNodeBuilder();
		nodeBld.setNamePattern(Pattern.compile("^\\Q" + request.getName() + "\\E$"));
		nodeBld.setComponentTypeID(request.getComponentTypeID());
		nodeBld.setUseTargetPlatform(true);
		nodeBld.setUseWorkspace(useWorkspace);
		nodeBld.setUseMaterialization(false);
		nodeBld.setUseRemoteResolution(false);
		qbld.addAdvisorNode(nodeBld);

		// Add an advisor node that matches all remaining components and prohibits that we
		// do something external.
		//
		nodeBld = new AdvisorNodeBuilder();
		nodeBld.setNamePattern(Pattern.compile(".*"));
		nodeBld.setUseTargetPlatform(true);
		nodeBld.setUseWorkspace(useWorkspace);
		nodeBld.setUseMaterialization(useWorkspace);
		nodeBld.setUseRemoteResolution(false);
		qbld.addAdvisorNode(nodeBld);

		IResolver main = new MainResolver(new ResolutionContext(qbld.createComponentQuery()));
		Resolution res = main.resolve(new NullProgressMonitor()).getResolution();
		if(res == null)
			throw new MissingComponentException(request.toString());
		return res;
	}

	public static void setComponentIdentifier(IResource resource, ComponentIdentifier identifier) throws CoreException
	{
		resource.setPersistentProperty(PPKEY_COMPONENT_ID, identifier.toString());
	}

	public static void validateMaterializations() throws CoreException
	{
		StorageManager sm = StorageManager.getDefault();
		for(Materialization mt : StorageManager.getDefault().getMaterializations().getElements())
			if(!mt.getComponentLocation().toFile().exists())
				mt.remove(sm);
	}

	private static IProject extractProject(IResource[] resources)
	{
		int idx = resources.length;
		while(--idx >= 0)
		{
			IResource resource = resources[idx];
			if(resource instanceof IProject)
			{
				IProject project = (IProject)resource;
				if(project.isOpen())
					return project;
			}
		}
		return null;
	}
}
