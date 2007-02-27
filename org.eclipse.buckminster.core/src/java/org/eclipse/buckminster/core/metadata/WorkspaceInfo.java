/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.HashMap;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
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
	public static final QualifiedName PPKEY_GENERATED_CSPEC = new QualifiedName(CorePlugin.CORE_NAMESPACE, "generatedCSpec");

	private static final HashMap<CSpec,IPath> s_locationCache = new HashMap<CSpec, IPath>();

	public static void clearCachedLocation(CSpec cspec)
	{
		synchronized(s_locationCache)
		{
			s_locationCache.remove(cspec);
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
		MultiStatus status = new MultiStatus(CorePlugin.getID(), IStatus.OK, "Problems during metadata refresh",null);
		try
		{
			clearPersistentPropertyOnAll();
			MetadataSynchronizer mds = MetadataSynchronizer.getDefault();
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			monitor.beginTask(null, projects.length * 50);
			for(IProject project : projects)
			{
				monitor.subTask("Refreshing " + project.getName());
				try
				{
					mds.refreshProject(project, MonitorUtils.subMonitor(monitor, 50));
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

	public static Resolution[] getActiveResolutions() throws CoreException
	{
		// Add the newest version of each known resoluton
		//
		HashMap<ComponentName,TimestampedKey> resolutionKeys = new HashMap<ComponentName, TimestampedKey>();
		ISaxableStorage<Resolution> ress = StorageManager.getDefault().getResolutions();
		for(Resolution res : ress.getElements())
		{
			UUID resId = res.getId();
			ComponentIdentifier ci = res.getComponentIdentifier();
			TimestampedKey tsKey = new TimestampedKey(resId, ress.getCreationTime(resId));
			TimestampedKey prevTsKey = resolutionKeys.put(ci, tsKey);
			if(prevTsKey != null && prevTsKey.getCreationTime() > tsKey.getCreationTime())
				//
				// We just replaced a newer entry. Put it back!
				//
				resolutionKeys.put(ci, prevTsKey);
		}

		int top = resolutionKeys.size();
		Resolution[] result = new Resolution[top];
		int idx = 0;
		for(TimestampedKey tsKey : resolutionKeys.values())
			result[idx++] = ress.getElement(tsKey.getKey());
		return result;
	}

	public static ComponentIdentifier getComponentIdentifier(IResource resource)
	{
		String componentId = null;
		try
		{
			componentId = resource.getPersistentProperty(PPKEY_COMPONENT_ID);
			return componentId == null ? null : ComponentIdentifier.parse(componentId);
		}
		catch(CoreException e)
		{
			return null;
		}
	}

	/**
	 * Returns the full path of the materialization for the component
	 * denoted by the <code>componentIdentifier</code>
	 *
	 * @param componentIdentifier The identifier of the component
	 * @return The path of the location
	 * @throws MissingComponentException if the component cannot be found
	 * @throws AmbigousComponentException if more then one component is an equally good match
	 * @throws CoreException for other persistent storage related issues
	 */
	public static IPath getComponentLocation(ComponentIdentifier componentIdentifier) throws CoreException
	{
		return getComponentLocation(getResolution(componentIdentifier));
	}

	/**
	 * Returns the full path of the materialization for the component
	 * denoted by the <code>componentIdentifier</code>
	 *
	 * @param cspec The cspec of the component
	 * @return The path of the location
	 * @throws MissingComponentException if the component cannot be found
	 * @throws AmbigousComponentException if more then one component is an equally good match
	 * @throws CoreException for other persistent storage related issues
	 */
	public static IPath getComponentLocation(CSpec cspec) throws CoreException
	{
		synchronized(s_locationCache)
		{
			IPath location = s_locationCache.get(cspec);
			if(location == null)
			{
				location = getComponentLocation(getResolution(cspec.getComponentIdentifier()));
				s_locationCache.put(cspec, location);
			}
			return location;
		}
	}

	public static IPath getComponentLocation(Resolution resolution) throws CoreException
	{
		IPath fixed = resolution.getProvider().getReaderType().getFixedLocation(resolution);
		if(fixed != null)
			return fixed;

		Materialization mat = getMaterialization(resolution);
		if(mat == null)
			throw new MissingComponentException(resolution.getCSpec().getComponentIdentifier().toString());
		return mat.getComponentLocation();
	}

	public static CSpec getCSpec(IResource resource) throws CoreException
	{
		ComponentIdentifier id = getComponentIdentifier(resource);
		if(id == null && resource instanceof IProject)
		{
			resyncAll();
			id = getComponentIdentifier(resource);
		}
		return id == null ? null : getResolution(id).getCSpec();
	}

	public static Materialization getMaterialization(ComponentIdentifier cid) throws CoreException
	{
		// Add all components for which we have a materialization
		//
		for(Materialization mat : StorageManager.getDefault().getMaterializations().getElements())
			if(cid.equals(mat.getComponentIdentifier()))
				return mat;
		return null;
	}

	/**
	 * Returns the optional <code>Materialization</code> for the component. Components found in the
	 * target platform will not have a materialization.
	 * @param resolution The resolution for which we want a Materialization
	 * @return The materialization or <code>null</code> if it could not be found.
	 * @throws CoreException
	 */
	public static Materialization getMaterialization(Resolution resolution) throws CoreException
	{
		UUID resId = resolution.getId();
		for(Materialization mat : StorageManager.getDefault().getMaterializations().getElements())
			if(mat.getResolutionId().equals(resId))
				return mat;
		return null;
	}

	/**
	 * Returns the optional <code>WorkspaceBinding</code> for the materialized component.
	 * @param materialization The materialization for which we want a WorkspaceBinding
	 * @return The binding or <code>null</code> if it could not be found.
	 * @throws CoreException
	 */
	public static WorkspaceBinding getWorkspaceBinding(Materialization materialization) throws CoreException
	{
		UUID matId = materialization.getId();
		for(WorkspaceBinding wb : StorageManager.getDefault().getWorkspaceBindings().getElements())
			if(wb.getMaterializationId().equals(matId))
				return wb;
		return null;
	}

	public static Resolution getResolution(ComponentIdentifier componentIdentifier) throws CoreException
	{
		Resolution candidate = null;
		for(Resolution res : getActiveResolutions())
		{
			ComponentIdentifier cid = res.getCSpec().getComponentIdentifier();
			if(!componentIdentifier.matches(cid))
				continue;

			if(componentIdentifier.getVersion() != null)
			{
				candidate = res;
				break;
			}

			if(candidate != null)
				throw new AmbigousComponentException(componentIdentifier.toString());
			
			candidate = res;
		}

		if(candidate == null)
			throw new MissingComponentException(componentIdentifier.toString());

		return candidate;
	}

	/**
	 * Returns the <code>CSpec</code> that best corresponds to the given <code>request</code>.
	 * @param request The component request
	 * @return The found Resolution
	 * @throws MissingComponentException if the component cannot be found
	 * @throws AmbigousComponentException if more then one component is an equally good match
	 * @throws CoreException for other persistent storage related issues
	 */
	public static Resolution getResolution(ComponentRequest request) throws CoreException
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
				int cmp = Trivial.compareAllowNull(id.getVersion(), candidate.getComponentIdentifier().getVersion());
				if(cmp == 0)
					throw new AmbigousComponentException(id.toString());
				if(cmp < 0)
					continue;
			}
			candidate = res;
		}

		if(candidate == null)
			throw new MissingComponentException(request.toString());
		return candidate;
	}

	/**
	 * Obtains the resource that is currently bound to the given <code>componentIdentifier</code> and
	 * returns it. This method may return <code>null</code> to indicate that no resource was found.
	 * @param componentIdentifier The component to search for
	 * @return The found workspace resource or <code>null</code> if no such resource exists.
	 * @throws CoreException
	 */
	public static IResource getResource(ComponentIdentifier componentIdentifier) throws CoreException
	{
		ISaxableStorage<WorkspaceBinding> bindings = StorageManager.getDefault().getWorkspaceBindings();
		WorkspaceBinding candidate = null;
		long candidateTs = 0;
		ComponentIdentifier candidateId = null;
		for(TimestampedKey bindingKey : bindings.getTimestampedKeys())
		{
			WorkspaceBinding binding = bindings.getElement(bindingKey.getKey());
			CSpec cspec = binding.getMaterialization().getCSpec();

			ComponentIdentifier cid = cspec.getComponentIdentifier();
			if(componentIdentifier.equals(cid))
			{
				candidate = binding;
				break;
			}

			if(!componentIdentifier.matches(cid))
				continue;

			if(candidate != null)
			{
				// Compare versions
				//
				int cmp = Trivial.compareAllowNull(cid.getVersion(), candidateId.getVersion());
				if(cmp == 0)
				{
					long timeDiff = bindingKey.getCreationTime() - candidateTs;
					if(timeDiff == 0)
						throw new AmbigousComponentException(cid.toString());
					cmp = timeDiff > 0 ? 1 : -1;
				}
				if(cmp < 0)
					continue;
			}
			candidate = binding;
			candidateTs = bindingKey.getCreationTime();
			candidateId = cid;
		}

		if(candidate == null)
			return null;

		return ResourcesPlugin.getWorkspace().getRoot().findMember(candidate.getWorkspaceRelativePath());
	}

	public static IResource getResource(ComponentRequest request) throws CoreException
	{
		ISaxableStorage<WorkspaceBinding> bindings = StorageManager.getDefault().getWorkspaceBindings();
		WorkspaceBinding candidate = null;
		long candidateTs = 0;
		ComponentIdentifier candidateId = null;
		for(TimestampedKey bindingKey : bindings.getTimestampedKeys())
		{
			WorkspaceBinding binding = bindings.getElement(bindingKey.getKey());
			CSpec cspec = binding.getMaterialization().getCSpec();

			ComponentIdentifier cid = cspec.getComponentIdentifier();
			if(!request.designates(cid))
				continue;

			IVersionDesignator vd = request.getVersionDesignator();
			if(vd != null && vd.isExplicit())
			{
				candidate = binding;
				break;
			}

			if(candidate != null)
			{
				// Compare versions
				//
				int cmp = Trivial.compareAllowNull(cid.getVersion(), candidateId.getVersion());
				if(cmp == 0)
				{
					long timeDiff = bindingKey.getCreationTime() - candidateTs;
					if(timeDiff == 0)
						throw new AmbigousComponentException(cid.toString());
					cmp = timeDiff > 0 ? 1 : -1;
				}
				if(cmp < 0)
					continue;
			}
			candidate = binding;
			candidateTs = bindingKey.getCreationTime();
			candidateId = cid;
		}

		if(candidate == null)
			return null;

		return ResourcesPlugin.getWorkspace().getRoot().findMember(candidate.getWorkspaceRelativePath());
	}

	public static void resyncAll() throws CoreException
	{
		validateMaterializations();
		final HashMap<IPath,WorkspaceBinding> allBindings = new HashMap<IPath, WorkspaceBinding>();
		for(WorkspaceBinding wb : StorageManager.getDefault().getWorkspaceBindings().getElements())
			allBindings.put(wb.getWorkspaceRelativePath(), wb);

		ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor()
		{
			public boolean visit(IResource resource) throws CoreException
			{
				if((resource instanceof IProject) && !((IProject)resource).isOpen())
					return false;

				IPath path = resource.getFullPath().makeRelative().addTrailingSeparator();
				String cidStr = resource.getPersistentProperty(PPKEY_COMPONENT_ID);
				WorkspaceBinding wb = allBindings.get(path);
				if(wb == null)
				{
					if(cidStr != null)
						resource.setPersistentProperty(PPKEY_COMPONENT_ID, null);
				}
				else
				{
					String rcidStr = wb.getMaterialization().getResolution().getComponentIdentifier().toString();
					if(cidStr == null || !cidStr.equals(rcidStr))
						resource.setPersistentProperty(PPKEY_COMPONENT_ID, rcidStr);
					allBindings.remove(path);
				}
				return true;
			}
		});

		if(allBindings.size() > 0)
		{
			for(WorkspaceBinding wb : allBindings.values())
				wb.remove();
		}
	}

	public static void setComponentIdentifier(IResource resource, ComponentIdentifier identifier) throws CoreException
	{
		resource.setPersistentProperty(PPKEY_COMPONENT_ID, identifier.toString());
	}

	public static void validateMaterializations() throws CoreException
	{
		for(Materialization mt : StorageManager.getDefault().getMaterializations().getElements())
			if(!mt.getComponentLocation().toFile().exists())
				mt.remove();
	}
}
