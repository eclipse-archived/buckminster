/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class MetadataSynchronizer implements IResourceChangeListener
{
	private static final MetadataSynchronizer s_default = new MetadataSynchronizer();

	private final HashMap<IPath,String> m_cspecSources = new HashMap<IPath,String>();

	private final Set<IProject> m_projectsNeedingUpdate = new HashSet<IProject>();

	private final Set<IPath> m_removedEntries = new HashSet<IPath>();

	private class Visitor implements IResourceDeltaVisitor
	{
		public boolean visit(IResourceDelta delta) throws CoreException
		{
			int kind = delta.getKind();
			if(kind == IResourceDelta.REMOVED)
			{
				IResource resource = delta.getResource();
				IPath path = resource.getLocation();
				if(!(resource instanceof IFile))
					path = path.addTrailingSeparator();

				synchronized(MetadataSynchronizer.this)
				{
					m_removedEntries.add(path);
				}
			}
			else
			{
				if(kind == IResourceDelta.ADDED || (delta.getFlags() & (IResourceDelta.CONTENT | IResourceDelta.REPLACED)) != 0)
				{
					IPath path = delta.getFullPath();
					if(path.segmentCount() > 1 && m_cspecSources.containsKey(path.removeFirstSegments(1)))
					{
						synchronized(MetadataSynchronizer.this)
						{
							m_projectsNeedingUpdate.add(delta.getResource().getProject());
						}
						return false;
					}
				}
			}
			return true;
		}
	}

	public static MetadataSynchronizer getDefault()
	{
		return s_default;
	}

	public Map<IPath,String> getCSpecSources()
	{
		return Collections.unmodifiableMap(m_cspecSources);
	}

	public void registerCSpecSource(IPath path, String componentType)
	{
		m_cspecSources.put(path, componentType);
	}

	synchronized IPath getNextRemovedEntry()
	{
		if(m_removedEntries.isEmpty())
			return null;
		IPath entry = m_removedEntries.iterator().next();
		m_removedEntries.remove(entry);
		return entry;
	}

	synchronized IProject getNextProjectNeedingUpdate()
	{
		if(m_projectsNeedingUpdate.isEmpty())
			return null;
		IProject entry = m_projectsNeedingUpdate.iterator().next();
		m_projectsNeedingUpdate.remove(entry);
		return entry;
	}

	private MetadataRefreshJob m_currentRefreshJob;

	class MetadataRefreshJob extends Job
	{
		public MetadataRefreshJob()
		{
			super("Metadata refresh");
			setPriority(BUILD);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{

			int ticks;
			synchronized(MetadataSynchronizer.this)
			{
				ticks = m_removedEntries.size() * 30 + m_projectsNeedingUpdate.size() * 70;
			}
			if(ticks == 0)
			{
				MonitorUtils.complete(monitor);
				return Status.OK_STATUS;
			}
			monitor.beginTask(null,ticks);
			try
			{
				for(;;)
				{
					IPath removedEntry;
					while((removedEntry = getNextRemovedEntry()) != null)
					{
						for(Materialization mat : StorageManager.getDefault().getMaterializations().getElements())
						{
							if(mat.getComponentLocation().equals(removedEntry))
							{
								mat.remove();
								break;
							}
						}
						MonitorUtils.worked(monitor, 30);
					}
					IProject project;
					while((project = getNextProjectNeedingUpdate()) != null)
					{
						monitor.subTask("Refreshing " + project.getName());
						try
						{
							refreshProject(project, MonitorUtils.subMonitor(monitor, 70));
						}
						catch(CoreException e)
						{
							CorePlugin.getLogger().error("Project refresh on " + project.getName() + " failed: " + e.getMessage(), e);
						}
					}

					// I'm about to terminate. First make absolutely sure that there's nothing
					// left todo
					//
					synchronized(MetadataSynchronizer.this)
					{
						if(m_removedEntries.isEmpty() && m_projectsNeedingUpdate.isEmpty())
						{
							// We're done here
							//
							return Status.OK_STATUS;
						}	
					}
				}
			}
			catch(CoreException e)
			{
				CorePlugin.getLogger().error(e.toString(), e);
				return e.getStatus();
			}
			finally
			{
				synchronized(MetadataSynchronizer.this)
				{
					if(m_currentRefreshJob == this)
						m_currentRefreshJob = null;
				}
			}
		}
	}

	public void resourceChanged(IResourceChangeEvent event)
	{
		try
		{
			event.getDelta().accept(new Visitor());
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().error(e.getMessage(), e);
		}
		synchronized(this)
		{
			if(m_currentRefreshJob == null && m_projectsNeedingUpdate.size() > 0 || m_removedEntries.size() > 0)
			{
				// Start a refresh job.
				//
				m_currentRefreshJob = new MetadataRefreshJob();
				m_currentRefreshJob.schedule();
			}
		}
	}

	public static void setUp()
	{
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = exReg.getConfigurationElementsFor(CorePlugin.COMPONENT_TYPE_POINT);

		// The extension file can exist in every project but we use Buckminster component
		// type as a placeholder.
		//
		s_default.registerCSpecSource(new Path(CorePlugin.CSPECEXT_FILE), IComponentType.BUCKMINSTER);

		for(IConfigurationElement elem : elems)
		{
			String componentType = elem.getAttribute("id");
			for(IConfigurationElement metaFile : elem.getChildren("metaFile"))
			{
				String metaPath = metaFile.getAttribute("path");
				if(metaPath == null)
					continue;
				metaPath = metaPath.trim();
				if(metaPath.length() > 0)
					s_default.registerCSpecSource(new Path(metaPath), componentType);
			}
		}
		ResourcesPlugin.getWorkspace().addResourceChangeListener(s_default, IResourceChangeEvent.PRE_BUILD);
	}

	public boolean refreshProject(IProject project, IProgressMonitor monitor) throws CoreException
	{
		if(project.getName().equals(CorePlugin.BUCKMINSTER_PROJECT))
			return false;

		if(!project.exists())
		{
			// Project has been removed. Verify that we have no dangling
			// materializations.
			//
			for(Materialization mat : StorageManager.getDefault().getMaterializations().getElements())
			{
				if(!mat.getComponentLocation().toFile().exists())
					mat.remove();
			}
			return false;
		}

		if(!project.isOpen())
		{
			MonitorUtils.complete(monitor);
			return false;
		}

		CSpec oldCSpec = WorkspaceInfo.getCSpec(project);
		Resolution oldInfo = null;
		if(oldCSpec != null)
			oldInfo = WorkspaceInfo.getResolution(oldCSpec.getComponentIdentifier());

		monitor.beginTask(null, 100);
		try
		{
			ComponentRequest request;
			if(oldInfo == null)
				request = new ComponentRequest(project.getName(), null, null);
			else
				request = oldInfo.getRequest();

			ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
			queryBld.setRootRequest(request);
			ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
			Resolution res = LocalResolver.fromPath(context.getRootNodeQuery(), project.getLocation(), oldInfo);
			if(res.getCSpec().equals(oldCSpec))
				return false;
	
			Resolution oldRes = null;
			if(oldCSpec != null)
			{
				oldRes = WorkspaceInfo.getResolution(oldCSpec.getComponentIdentifier());
				res = new Resolution(res.getCSpec(), oldRes);
				res.store();
			}

			ComponentIdentifier ci = res.getComponentIdentifier();
			Materialization mat = new Materialization(project.getLocation().addTrailingSeparator(), ci);
			mat.store();
			WorkspaceInfo.setComponentIdentifier(project, ci);
	
			if(oldRes != null)
			{
				try
				{
					oldRes.remove();
					oldCSpec.remove();
				}
				catch(ReferentialIntegrityException e)
				{
					// Old resolution is being held by a BillOfMaterials. It
					// cannot be removed at this point.
				}
			}
			updateProjectReferences(project, res, MonitorUtils.subMonitor(monitor, 30));
			return true;
		}
		finally
		{
			monitor.done();
		}
	}

	private void updateProjectReferences(IProject project, Resolution resolution, IProgressMonitor monitor) throws CoreException
	{
		CSpec cspec = resolution.getCSpec();
		Collection<ComponentRequest> crefs = cspec.getDependencies().values();
		if(crefs.size() == 0)
		{
			// No use continuing. Project doesn't have any references.
			//
			MonitorUtils.worked(monitor, 50);
			return;
		}

		// Create a set containing the references that are already present
		//
		IProject[] oldRefs = project.getReferencedProjects();
		HashSet<String> oldSet = new HashSet<String>(oldRefs.length);
		ArrayList<IProject> refdProjs = new ArrayList<IProject>();
		for(IProject oldRef : oldRefs)
		{
			oldSet.add(oldRef.getName());
			refdProjs.add(oldRef);
		}

		boolean changed = false;
		for(ComponentRequest cref : crefs)
		{
			for(IResource resource : WorkspaceInfo.getResources(cref))
			{
				if(!(resource instanceof IProject))
					//
					// Component is not a project. This is OK but the component doesn't
					// qualify as a project dependency (it can't be built).
					//
					continue;

				IProject refdProj = (IProject)resource;
				if(!refdProj.isOpen())
				{
					CorePlugin.getLogger().warning(
						"Project " + project.getName() + " references a closed project: " + cref.getName());
				}
				else if(!oldSet.contains(refdProj.getName()))
				{
					// Didn't have this one.
					//
					refdProjs.add(refdProj);
					changed = true;
				}
			}
		}

		if(changed)
		{
			IProjectDescription projDesc = project.getDescription();
			projDesc.setDynamicReferences(refdProjs.toArray(new IProject[refdProjs.size()]));
			project.setDescription(projDesc, MonitorUtils.subMonitor(monitor, 50));
		}
		else
			MonitorUtils.complete(monitor);
	}
}
