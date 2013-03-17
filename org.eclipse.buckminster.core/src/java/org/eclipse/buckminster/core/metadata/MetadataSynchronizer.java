/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.EclipsePreferencesReader;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.AttachableProgressMonitor;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class MetadataSynchronizer implements IResourceChangeListener {
	class MetadataRefreshJob extends Job {
		public MetadataRefreshJob() {
			super(Messages.Metadata_refresh);
			setPriority(SHORT);
			setSystem(true);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			int ticks;
			synchronized (MetadataSynchronizer.this) {
				ticks = removedEntries.size() * 30 + projectsNeedingUpdate.size() * 70;
			}
			if (ticks == 0) {
				MonitorUtils.complete(monitor);
				return Status.OK_STATUS;
			}
			monitor.beginTask(null, ticks);
			try {
				StorageManager sm = StorageManager.getDefault();
				boolean didSomething = true;
				for (; didSomething && defaultSynchronizer != null;) {
					didSomething = false;
					IPath removedEntry;
					while (defaultSynchronizer != null && (removedEntry = getNextRemovedEntry()) != null) {
						didSomething = true;
						for (Materialization mat : sm.getMaterializations().getElements()) {
							if (mat.getComponentLocation().equals(removedEntry)) {
								// The project has been removed. This does't
								// mean that
								// we have to remove the materialization. For
								// that to
								// happen, the actual content must have been
								// removed
								// too.
								//
								if (!removedEntry.toFile().exists()) {
									// Try and remove the resolution. It might
									// not work
									//
									Resolution res = WorkspaceInfo.getResolution(mat.getComponentIdentifier());
									try {
										res.remove(sm);
									} catch (ReferentialIntegrityException e) {
									}
									mat.remove(sm);
								}
								break;
							}
						}
						MonitorUtils.worked(monitor, 30);
					}
					IProject project;
					while (defaultSynchronizer != null && (project = getNextProjectNeedingUpdate()) != null) {
						didSomething = true;
						monitor.subTask(NLS.bind(Messages.Refreshing_0, project.getName()));
						try {
							refreshProject(project, MonitorUtils.subMonitor(monitor, 70));
						} catch (Exception e) {
							if (defaultSynchronizer != null && project.isAccessible())
								CorePlugin.getLogger().error(e, NLS.bind(Messages.Project_refresh_on_0_failed_1, project.getName(), e.getMessage()));
						}
					}
				}
				return Status.OK_STATUS;
			} catch (Exception e) {
				if (defaultSynchronizer != null)
					CorePlugin.getLogger().error(e, e.toString());
				return BuckminsterException.wrap(e).getStatus();
			}
		}
	}

	static class ResetVisitor implements IResourceVisitor {
		@Override
		public boolean visit(IResource resource) throws CoreException {
			if ((resource instanceof IProject) && !((IProject) resource).isOpen())
				return false;

			String cidStr = resource.getPersistentProperty(WorkspaceInfo.PPKEY_COMPONENT_ID);
			if (cidStr != null)
				resource.setPersistentProperty(WorkspaceInfo.PPKEY_COMPONENT_ID, null);
			return true;
		}
	}

	static class WorkspaceCatchUpJob extends Job {

		private static final QualifiedName QN_ATTACHABLE_PROGRESS_MONITOR = new QualifiedName(CorePlugin.getID(), "attachableProgressMonitor"); //$NON-NLS-1$

		private AttachableProgressMonitor attachableMonitor;

		public WorkspaceCatchUpJob() {
			super(Messages.Buckminster_workspace_catch_up);

			// We need very high prio on this since we wait
			// for it to complete during plug-in activation
			//
			setPriority(Job.SHORT);
			attachableMonitor = new AttachableProgressMonitor();
			setProperty(QN_ATTACHABLE_PROGRESS_MONITOR, attachableMonitor);
		}

		@Override
		public boolean belongsTo(Object family) {
			if (family == MetadataSynchronizer.class)
				return true;
			return false;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			monitor = attachableMonitor.wrap(monitor);
			monitor.beginTask(Messages.Refreshing_project_meta_data, 1000);

			try {
				workspaceCatchUp(monitor);
			} catch (Exception e) {
				CorePlugin.getLogger().warning(e, NLS.bind(Messages.Problem_during_meta_data_refresh_0, e.getMessage()));
			} finally {
				setProperty(QN_ATTACHABLE_PROGRESS_MONITOR, null);
				monitor.done();
			}
			return Status.OK_STATUS;
		}
	}

	private class Visitor implements IResourceDeltaVisitor {
		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			int kind = delta.getKind();
			IResource resource = delta.getResource();
			if (kind == IResourceDelta.REMOVED) {
				if ((delta.getFlags() & IResourceDelta.MOVED_TO) != 0)
					return false;

				if (resource == null)
					return false;

				// Project is probably removed. If it is, we should have it in
				// our cache
				//
				IProject project = resource.getProject();
				if (project == null)
					return true;

				IPath relPath = resource.getProjectRelativePath();
				IPath path = resource.getLocation();
				if (path == null) {
					IPath projPath = deletedProjectLocations.get(project.getName());
					if (projPath == null)
						//
						// No location and not a remove project. We have no clue
						// what to do with this.
						//
						return false;

					path = projPath.append(relPath);
				}

				if (!(resource instanceof IFile))
					path = path.addTrailingSeparator();

				synchronized (MetadataSynchronizer.this) {
					removedEntries.add(path);
					if (isCSpecSource(resource, relPath)) {
						projectsNeedingUpdate.add(resource.getProject());
						return false;
					}
					return true;
				}
			}

			if (kind == IResourceDelta.ADDED && (delta.getFlags() & IResourceDelta.MOVED_FROM) != 0) {
				synchronized (MetadataSynchronizer.this) {
					WorkspaceInfo.setComponentIdentifier(resource, null);
					projectsNeedingUpdate.add(resource.getProject());
				}
				return false;
			}

			if (kind == IResourceDelta.ADDED || (delta.getFlags() & (IResourceDelta.CONTENT | IResourceDelta.REPLACED)) != 0) {
				if (resource instanceof IProject || isCSpecSource(resource, resource.getProjectRelativePath())) {
					synchronized (MetadataSynchronizer.this) {
						projectsNeedingUpdate.add(resource.getProject());
					}
					return false;
				}
			}
			return true;
		}
	}

	private static MetadataSynchronizer defaultSynchronizer = new MetadataSynchronizer();

	public static MetadataSynchronizer getDefault() {
		return defaultSynchronizer;
	}

	public static void refreshProject(IProject project, IProgressMonitor monitor) throws CoreException {
		if (project.getName().equals(CorePlugin.BUCKMINSTER_PROJECT) || !project.isAccessible()) {
			MonitorUtils.complete(monitor);
			return;
		}

		IPath location = project.getLocation();
		if (location == null)
			return;

		Resolution oldInfo = null;
		ComponentIdentifier cid = WorkspaceInfo.getComponentIdentifier(project);
		if (cid != null)
			oldInfo = WorkspaceInfo.getResolution(cid);

		monitor.beginTask(null, 100);
		try {
			ComponentRequest request;
			if (oldInfo == null)
				request = new ComponentRequest(project.getName(), null, null);
			else
				request = new ComponentRequest(oldInfo.getRequest().getName(), null, null);

			ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
			queryBld.setRootRequest(request);
			queryBld.setPlatformAgnostic(true);
			ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
			Resolution res = LocalResolver.fromPath(context.getRootNodeQuery(), project.getLocation(), oldInfo);
			if (!res.equals(oldInfo)) {
				StorageManager sm = StorageManager.getDefault();
				res.store(sm);

				ComponentIdentifier ci = res.getComponentIdentifier();
				Materialization mat = new Materialization(location.addTrailingSeparator(), ci);
				mat.store(sm);
				WorkspaceInfo.setComponentIdentifier(project, ci);

				if (oldInfo != null) {
					try {
						oldInfo.remove(sm);
						oldInfo.getCSpec().remove(sm);
					} catch (ReferentialIntegrityException e) {
						// Old resolution is being held by a BillOfMaterials. It
						// cannot be removed at this point.
					}
				}
			}
			updateProjectReferences(project, res.getCSpec(), MonitorUtils.subMonitor(monitor, 50));
		} finally {
			monitor.done();
		}
	}

	public static void setUp() {
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		if (exReg == null)
			return; // We died before we got the chance

		IConfigurationElement[] elems = exReg.getConfigurationElementsFor(CorePlugin.COMPONENT_TYPE_POINT);

		// The extension file can exist in every project but we use Buckminster
		// component
		// type as a placeholder.
		//
		defaultSynchronizer.registerCSpecSource(CorePlugin.CSPECEXT_FILE);
		defaultSynchronizer.registerCSpecSource(EclipsePreferencesReader.BUCKMINSTER_PROJECT_PREFS_PATH);

		for (IConfigurationElement elem : elems) {
			for (IConfigurationElement metaFile : elem.getChildren("metaFile")) //$NON-NLS-1$
			{
				String metaPath = metaFile.getAttribute("path"); //$NON-NLS-1$
				if (metaPath == null)
					continue;
				metaPath = metaPath.trim();
				if (metaPath.length() > 0)
					defaultSynchronizer.registerCSpecSource(metaPath);

				for (String alias : TextUtils.split(metaFile.getAttribute("aliases"), ",")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					alias = alias.trim();
					if (alias.length() > 0)
						defaultSynchronizer.registerCSpecSource(alias);
				}
			}
		}

		IWorkspace ws = ResourcesPlugin.getWorkspace();
		ws.addResourceChangeListener(defaultSynchronizer, IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.POST_CHANGE);
	}

	public static void tearDown() {
		MetadataSynchronizer mds = defaultSynchronizer;
		if (mds == null)
			return;

		defaultSynchronizer = null;
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		if (ws != null)
			ws.removeResourceChangeListener(mds);

		synchronized (mds) {
			if (mds.currentRefreshJob != null) {
				mds.currentRefreshJob.cancel();
				try {
					// Give the job some time to cancel
					//
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void workspaceCatchUp(IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		wsRoot.accept(new ResetVisitor());
		MonitorUtils.worked(monitor, 50);
		IProject[] projects = wsRoot.getProjects();
		MonitorUtils.worked(monitor, 50);

		// Re-resolve all projects
		//
		if (projects.length > 0) {
			int ticksPerRefresh = 900 / projects.length;
			for (IProject project : projects) {
				try {
					refreshProject(project, MonitorUtils.subMonitor(monitor, ticksPerRefresh));
				} catch (Exception e) {
					CorePlugin.getLogger().warning(e, NLS.bind(Messages.Problem_during_meta_data_refresh_0, e.getMessage()));
				}
			}
		}
	}

	private static void updateProjectReferences(IProject project, ICSpecData cspec, IProgressMonitor monitor) throws CoreException {
		Collection<? extends IComponentRequest> crefs = cspec.getDependencies();
		if (crefs.size() == 0) {
			// No use continuing. Project doesn't have any references.
			//
			MonitorUtils.complete(monitor);
			return;
		}

		// Create a set containing the references that are already present
		//
		ProjectDescription projDesc = (ProjectDescription) project.getDescription();
		IProject[] refs = projDesc.getAllReferences(false);
		HashSet<String> oldSet = new HashSet<String>(refs.length);
		for (IProject oldRef : refs)
			oldSet.add(oldRef.getName());

		Logger logger = CorePlugin.getLogger();
		monitor.beginTask(null, 50 + crefs.size() * 10);
		ArrayList<IProject> refdProjs = null;
		for (IComponentRequest cref : crefs) {
			for (IResource resource : WorkspaceInfo.getResources(cref)) {
				if (!(resource instanceof IProject))
					//
					// Component is not a project. This is OK but the component
					// doesn't
					// qualify as a project dependency (it can't be built).
					//
					continue;

				IProject refdProj = (IProject) resource;
				if (!refdProj.isOpen()) {
					logger.warning(NLS.bind(Messages.Project_0_references_closed_project_1, project.getName(), cref.getName()));
				} else if (!oldSet.contains(refdProj.getName())) {
					// Didn't have this one.
					//
					if (refdProjs == null) {
						refdProjs = new ArrayList<IProject>();
						for (IProject dynRef : projDesc.getDynamicReferences(false))
							refdProjs.add(dynRef);
					}
					refdProjs.add(refdProj);
				}
			}
			monitor.worked(10);
		}

		if (refdProjs != null) {
			refs = refdProjs.toArray(new IProject[refdProjs.size()]);
			projDesc.setDynamicReferences(refs);
			project.setDescription(projDesc, MonitorUtils.subMonitor(monitor, 50));
			if (logger.isDebugEnabled()) {
				StringBuilder bld = new StringBuilder();
				bld.append(NLS.bind(Messages.Project_0_now_has_dynamic_dependencies_to, project.getName()));
				for (IProject ref : refs) {
					bld.append(' ');
					bld.append(ref.getName());
				}
				logger.debug(bld.toString());
			}
		}
		monitor.done();
	}

	private final HashMap<String, Pattern> cspecSources = new HashMap<String, Pattern>();

	private MetadataRefreshJob currentRefreshJob;

	private final HashMap<String, IPath> deletedProjectLocations = new HashMap<String, IPath>();

	private final Set<IProject> projectsNeedingUpdate = new HashSet<IProject>();

	private final Set<IPath> removedEntries = new HashSet<IPath>();

	public void registerCSpecSource(String path) {
		path = TextUtils.notEmptyTrimmedString(path);
		if (path == null)
			return;

		StringBuilder bld = new StringBuilder(path.length() + 10);
		bld.append('^');
		int top = path.length();
		for (int idx = 0; idx < top; ++idx) {
			char c = path.charAt(idx);
			switch (c) {
				case '\\':
					bld.append('/'); // We compare with path.toPortableString()
					break;
				case '.':
				case '{':
				case '}':
				case '[':
				case ']':
				case '+':
				case '^':
				case '$':
				case '(':
				case ')':
				case '|':
				case '&':
					bld.append('\\');
					bld.append(c);
					break;
				case '?':
					bld.append('.');
					break;
				case '*':
					bld.append(".*"); //$NON-NLS-1$
					break;
				default:
					bld.append(c);
			}
		}
		bld.append('$');
		int flags = 0;
		if (FileUtils.CASE_INSENSITIVE_FS)
			flags = Pattern.CASE_INSENSITIVE;

		cspecSources.put(path, Pattern.compile(bld.toString(), flags));
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (defaultSynchronizer == null)
			//
			// We're shutting down so never mind.
			//
			return;

		if (event.getType() == IResourceChangeEvent.PRE_DELETE) {
			IResource resource = event.getResource();
			if (resource instanceof IProject)
				deletedProjectLocations.put(resource.getName(), resource.getLocation());
			return;
		}

		try {
			event.getDelta().accept(new Visitor());
			deletedProjectLocations.clear();
		} catch (CoreException e) {
			CorePlugin.getLogger().error(e, e.getMessage());
		}
		synchronized (this) {
			if (currentRefreshJob == null && (projectsNeedingUpdate.size() > 0 || removedEntries.size() > 0)) {
				// Start a refresh job.
				//
				currentRefreshJob = new MetadataRefreshJob();
				currentRefreshJob.addJobChangeListener(new JobChangeAdapter() {
					@Override
					public void done(IJobChangeEvent ev) {
						// I'm about to terminate. First make absolutely sure
						// that there's nothing
						// left to do
						//
						if (defaultSynchronizer == null)
							//
							// We're shutting down
							//
							return;

						synchronized (MetadataSynchronizer.this) {
							if (removedEntries.isEmpty() && projectsNeedingUpdate.isEmpty()) {
								// We're done
								//
								currentRefreshJob = null;
							} else
								currentRefreshJob.schedule();
						}
					}
				});
				currentRefreshJob.schedule();
			}
		}
	}

	synchronized IProject getNextProjectNeedingUpdate() {
		if (projectsNeedingUpdate.isEmpty())
			return null;
		IProject entry = projectsNeedingUpdate.iterator().next();
		projectsNeedingUpdate.remove(entry);
		return entry;
	}

	synchronized IPath getNextRemovedEntry() {
		if (removedEntries.isEmpty())
			return null;
		IPath entry = removedEntries.iterator().next();
		removedEntries.remove(entry);
		return entry;
	}

	private boolean isCSpecSource(IResource resource, IPath path) {
		String pathStr = path.toPortableString();
		for (Pattern pattern : cspecSources.values()) {
			Matcher m = pattern.matcher(pathStr);
			if (m.matches())
				return true;
		}
		IProject project = resource.getProject();
		if (project == null)
			return false;

		ProjectScope scope = new ProjectScope(project);
		IEclipsePreferences prefs = scope.getNode(CorePlugin.getID());

		String tmp = AbstractResolutionBuilder.getMetadataFile(prefs, IComponentType.PREF_CSPEC_FILE, CorePlugin.CSPEC_FILE);
		if (path.equals(Path.fromPortableString(tmp)))
			return true;

		tmp = AbstractResolutionBuilder.getMetadataFile(prefs, IComponentType.PREF_CSPEX_FILE, CorePlugin.CSPECEXT_FILE);
		return path.equals(Path.fromPortableString(tmp));
	}
}
