/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.P2ReaderType;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;

public class WorkspaceMaterializer extends FileSystemMaterializer {
	private static Materialization getMaterialization(Resolution resolution) throws CoreException {
		Materialization mat = WorkspaceInfo.getMaterialization(resolution);
		if (mat != null)
			return mat;

		// We still want to bind stuff produced by the local reader
		//
		String readerTypeName = resolution.getReaderTypeId();
		if (!IReaderType.LOCAL.equals(readerTypeName))
			//
			// From the platform. Don't bind this
			//
			return null;

		IReaderType localReaderType = CorePlugin.getDefault().getReaderType(readerTypeName);
		return new Materialization(localReaderType.getFixedLocation(resolution), resolution.getComponentIdentifier());
	}

	private static boolean isSegmentPrefix(IPath self, IPath other) {
		String device = self.getDevice();
		if (device != null && other.getDevice() != null && !device.equalsIgnoreCase(other.getDevice()))
			return false;
		if (self.isEmpty() || (self.isRoot() && other.isAbsolute()))
			return true;

		String[] segments = self.segments();
		int len = segments.length;
		String[] otherSegments = other.segments();
		if (len > otherSegments.length)
			return false;

		if (FileUtils.CASE_INSENSITIVE_FS) {
			for (int i = 0; i < len; i++)
				if (!segments[i].equalsIgnoreCase(otherSegments[i]))
					return false;
		} else {
			for (int i = 0; i < len; i++)
				if (!segments[i].equals(otherSegments[i]))
					return false;
		}
		return true;
	}

	private static void storeBelow(Resolution resolution, BOMNode node, StorageManager sm, boolean isBelow) throws CoreException {
		IResolution nodeRes = node.getResolution();
		if (nodeRes == null)
			return;

		if (!isBelow)
			isBelow = nodeRes.equals(resolution);

		if (isBelow) {
			// Store the resolution unless it stems from the current target
			// platform
			//
			String readerType = resolution.getReaderTypeId();
			if (!IReaderType.ECLIPSE_PLATFORM.equals(readerType)) {
				resolution.store(sm);
				Materialization mat = getMaterialization(resolution);
				if (mat != null)
					mat.store(sm);
			}
		}

		for (BOMNode child : node.getChildren())
			storeBelow(resolution, child, sm, isBelow);
	}

	@Override
	public IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution) throws CoreException {
		IPath location = context.getWorkspaceLocation(resolution);
		IPath leaf = context.getLeafArtifact(resolution);

		// There are two conditions for putting this into the .buckminster
		// project
		//
		// 1. There is a leaf artifact that indicates that what we have here
		// is a file.
		// 2. The leaf artifact is null but the materialization will perform an
		// unpack. This normally means that an archive (zip or tar.gz) has a
		// root folder that isn't known until the unpack is complete. Such
		// a root cannot be used as a project at this point.
		//
		if (leaf == null) {
			if (context.getMaterializationSpec().isUnpack(resolution))
				location = location.append(CorePlugin.BUCKMINSTER_PROJECT);
		} else if (!leaf.hasTrailingSeparator()) {
			IReaderType readerType = getMaterializationReaderType(resolution);
			if (!IReaderType.ECLIPSE_IMPORT.equals(readerType.getId()))
				location = location.append(CorePlugin.BUCKMINSTER_PROJECT);
		}
		return location;
	}

	@Override
	public IReaderType getMaterializationReaderType(Resolution resolution) throws CoreException {
		// If this is a OSGi bundle in binary form, we must use the
		// "eclipse.import"
		// reader in order to materialize
		//
		IReaderType rt = super.getMaterializationReaderType(resolution);
		if (rt instanceof P2ReaderType)
			//
			// At present, materializing from a p2 repository into a workspace
			// can only mean one thing. Importing...
			//
			rt = CorePlugin.getDefault().getReaderType(IReaderType.ECLIPSE_IMPORT);

		if (rt instanceof CatalogReaderType)
			return rt;

		String ctId = resolution.getComponentTypeId();
		if (IComponentType.OSGI_BUNDLE.equals(ctId) || IComponentType.ECLIPSE_FEATURE.equals(ctId))
			rt = CorePlugin.getDefault().getReaderType(IReaderType.ECLIPSE_IMPORT);

		return rt;
	}

	public void installLocal(WorkspaceBinding wb, RMContext context, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 200);
		try {
			StorageManager sm = StorageManager.getDefault();
			monitor.subTask(NLS.bind(Messages.Binding_0, wb.getWorkspaceRelativePath()));

			Materialization mat = wb.getMaterialization();
			mat.store(sm);
			MonitorUtils.worked(monitor, 10);

			wb = performPrebindAction(wb, context, MonitorUtils.subMonitor(monitor, 95));
			IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 95);
			IPath wsRelativePath = wb.getWorkspaceRelativePath();
			if (wsRelativePath.segmentCount() == 1)
				createProjectBinding(wsRelativePath.segment(0), wb, context, subMonitor);
			else
				createExternalBinding(wsRelativePath, wb, subMonitor);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			monitor.done();
		}
	}

	@Override
	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		try {
			WorkspaceBinding wb = createBindSpec(resolution, context);
			if (wb == null) {
				MonitorUtils.complete(monitor);
				return;
			}

			IPath wsRoot = wb.getWorkspaceRoot();
			if (FileUtils.pathEquals(wsRoot, ResourcesPlugin.getWorkspace().getRoot().getLocation()))
				installLocal(wb, context, monitor);
			else {
				// Don't install in this workspace. Instead store it for later
				// installation
				// in the appointed workspace
				//
				ExternalDataArea dataArea = new ExternalDataArea(wsRoot, context.getMaterializationSpec().getConflictResolution(resolution));
				StorageManager sm = new StorageManager(dataArea.getStateLocation(CorePlugin.getID()).toFile());
				wb.store(sm);
				storeBelow(resolution, context.getBillOfMaterials(), sm, false);
			}
		} catch (CoreException e) {
			if (!context.isContinueOnError())
				throw e;
			context.addRequestStatus(resolution.getRequest(), e.getStatus());
		}
	}

	@Override
	protected IPath getArtifactLocation(MaterializationContext context, Resolution resolution) throws CoreException {
		IPath installLocation = context.getInstallLocation(resolution);
		IPath leafArtifact = context.getLeafArtifact(resolution);
		if (leafArtifact == null)
			installLocation = installLocation.addTrailingSeparator();
		else {
			IReaderType readerType = getMaterializationReaderType(resolution);
			if (IReaderType.ECLIPSE_IMPORT.equals(readerType.getId()))
				installLocation = installLocation.append(resolution.getName()).addTrailingSeparator();
			else
				installLocation = installLocation.append(leafArtifact);
		}
		return installLocation;
	}

	private WorkspaceBinding createBindSpec(Resolution resolution, MaterializationContext context) throws CoreException {
		Materialization mat = getMaterialization(resolution);
		if (mat == null)
			return null;

		IPath wsRoot = context.getWorkspaceLocation(resolution);
		IPath wsRelativePath;
		IPath matLoc = mat.getComponentLocation();
		IPath bmProjLoc = CorePlugin.getDefault().getBuckminsterProjectLocation();
		if (matLoc.hasTrailingSeparator() && !bmProjLoc.isPrefixOf(matLoc)) {
			wsRelativePath = context.getMaterializationSpec().getResourcePath(resolution);
			if (wsRelativePath == null)
				//
				// Default to project.
				//
				wsRelativePath = Path.fromPortableString(getDefaultProjectName(context.getMaterializationSpec(), resolution));
		} else {
			IPath localWsRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			if (!FileUtils.pathEquals(wsRoot, localWsRoot)) {
				if (localWsRoot.isPrefixOf(bmProjLoc))
					//
					// Switch ws root for the bmProject
					//
					bmProjLoc = wsRoot.append(bmProjLoc.removeFirstSegments(localWsRoot.segmentCount()));
			}

			if (bmProjLoc.isPrefixOf(matLoc))
				wsRelativePath = matLoc.removeFirstSegments(bmProjLoc.segmentCount() - 1).setDevice(null);
			else
				//
				// This will become a link in the root of the .buckminster
				// project
				//
				wsRelativePath = new Path(CorePlugin.BUCKMINSTER_PROJECT).append(matLoc.lastSegment());

			if (matLoc.hasTrailingSeparator())
				wsRelativePath = wsRelativePath.addTrailingSeparator();
		}
		return new WorkspaceBinding(matLoc, resolution, wsRoot, wsRelativePath, context.getBindingProperties());
	}

	private void createExternalBinding(IPath wsRelativePath, WorkspaceBinding mat, IProgressMonitor monitor) throws CoreException, IOException {
		IPath locationPath = mat.getComponentLocation();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		monitor.beginTask(null, 200);
		monitor.subTask(NLS.bind(Messages.Binding_0, locationPath));
		try {
			String projName = wsRelativePath.segment(0);
			IPath projRelativePath = wsRelativePath.removeFirstSegments(1);

			// The directory in the materialization that corresponds to the
			// project
			// root can be found by comparing the tail of the materialization
			// with
			// the workspace relative location.
			//
			IPath locationProjRoot = null;
			int relSegs = projRelativePath.segmentCount();
			int matSegs = locationPath.segmentCount();
			if (matSegs >= relSegs) {
				if (locationPath.removeFirstSegments(matSegs - relSegs).setDevice(null).equals(projRelativePath))
					locationProjRoot = locationPath.removeLastSegments(relSegs);
			}

			boolean useLink = false;
			if (locationProjRoot == null) {
				// The root of the project may contain links so we can still
				// resolve this
				//
				if (relSegs == 1)
					useLink = true;
				else
					throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_determine_project_root_when_binding_0_to_workspace_1,
							locationPath, wsRelativePath));
			}

			IProject projectForBinding = wsRoot.getProject(projName);
			if (!projectForBinding.exists()) {
				// The project does not exist yet. Create it so that it appoints
				// the root
				// of the materialization or if a link is used, in the current
				// workspace.
				//
				if (useLink || FileUtils.pathEquals(locationProjRoot.removeLastSegments(1), wsRoot.getLocation()))
					projectForBinding.create(MonitorUtils.subMonitor(monitor, 50));
				else {
					IProjectDescription desc = ResourcesPlugin.getWorkspace().newProjectDescription(projName);
					desc.setLocation(locationProjRoot);
					projectForBinding.create(desc, MonitorUtils.subMonitor(monitor, 50));
				}
				projectForBinding.open(MonitorUtils.subMonitor(monitor, 50));
			} else if (!projectForBinding.isOpen())
				projectForBinding.open(MonitorUtils.subMonitor(monitor, 100));
			else
				MonitorUtils.worked(monitor, 100);

			if (useLink) {
				if (locationPath.toFile().isDirectory()) {
					IFolder folder = projectForBinding.getFolder(projRelativePath);
					if (folder.exists()) {
						if (!(folder.isLinked() && FileUtils.pathEquals(folder.getRawLocation(), locationPath)))
							throw BuckminsterException.fromMessage(NLS.bind(
									Messages.Unable_to_create_folder_link_from_workspace_0_to_1_2_already_in_use, new Object[] { wsRelativePath,
											locationPath, projRelativePath }));

						MonitorUtils.worked(monitor, 50);
					} else
						folder.createLink(locationPath, 0, MonitorUtils.subMonitor(monitor, 50));
				} else {
					IFile ifile = projectForBinding.getFile(projRelativePath);
					if (ifile.exists()) {
						if (!(ifile.isLinked() && FileUtils.pathEquals(ifile.getRawLocation(), locationPath)))
							throw BuckminsterException.fromMessage(NLS.bind(
									Messages.Unable_to_create_file_link_from_workspace_0_to_1_link_origin_2_already_in_use, new Object[] {
											wsRelativePath, locationPath, projRelativePath }));
						MonitorUtils.worked(monitor, 50);
					} else
						ifile.createLink(locationPath, 0, MonitorUtils.subMonitor(monitor, 50));
				}
			} else
				MonitorUtils.worked(monitor, 50);

			// This resource now resides within the project but a refresh is
			// needed
			//
			projectForBinding.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));
			IResource resource = projectForBinding.findMember(projRelativePath);
			if (resource == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_obtain_resource_0_from_workspace_1, wsRelativePath,
						projRelativePath));

			WorkspaceInfo.setComponentIdentifier(projectForBinding.findMember(projRelativePath), mat.getComponentIdentifier());
		} finally {
			monitor.done();
		}
	}

	private void createProjectBinding(String suggestedProjectName, WorkspaceBinding wb, RMContext context, IProgressMonitor monitor)
			throws CoreException, IOException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		// Get the absolute path for the ProjectBinding
		//
		IPath locationPath = wb.getComponentLocation();

		// Check that the source directory is present.
		//
		if (!locationPath.toFile().exists()) {
			MonitorUtils.complete(monitor);
			throw new FileNotFoundException(locationPath.toString());
		}

		// Find the .project file and load the description
		//
		monitor.beginTask(null, 150);
		monitor.subTask(NLS.bind(Messages.Binding_0, suggestedProjectName));
		IProjectDescription description;
		try {
			description = workspace.loadProjectDescription(locationPath.append(".project")); //$NON-NLS-1$
		} catch (CoreException e) {
			description = null;
		}
		MonitorUtils.worked(monitor, 50);

		// Some special treatment is needed for projects that are rooted in the
		// workspace location
		//
		IPath wsRootPath = wsRoot.getLocation();
		boolean isRootedInWorkspace = (wsRootPath.segmentCount() == locationPath.segmentCount() - 1 && isSegmentPrefix(wsRootPath, locationPath));

		try {
			if (description == null) {
				if (isRootedInWorkspace) {
					// This is heading for disaster unless the last segment of
					// the locationPath
					// is in fact equal to the name of the project. For some
					// reason, Eclipse
					// stipulates that this has to be the case for this
					// particular physical
					// layout.
					//
					String forcedName = locationPath.lastSegment();
					if (!forcedName.equals(suggestedProjectName))
						throw new ProjectNameMismatchException(suggestedProjectName, forcedName);
					description = workspace.newProjectDescription(suggestedProjectName);
				} else {
					description = workspace.newProjectDescription(suggestedProjectName);
					description.setLocation(locationPath);
				}
			}

			IProject project = wsRoot.getProject(suggestedProjectName);
			if (!project.exists()) {
				IProject describedProject = wsRoot.getProject(description.getName());
				if (describedProject.exists()) {
					URI describedLocation = describedProject.getLocationURI();
					if (describedLocation.equals(description.getLocationURI())) {
						Buckminster.getLogger().warning(
								NLS.bind("Name of project {0} conflicts with previously bound name {1}", suggestedProjectName, //$NON-NLS-1$
										description.getName()));
						project = describedProject;
						MonitorUtils.worked(monitor, 50);
					} else {
						// This is probably a feature that is named in a way
						// that conflicts with a bundle (or something similar).
						// In any case, we need to bind both so we must alter
						// the description here.
						//
						Buckminster.getLogger().warning(
								NLS.bind("Name of project {0} conflicts with name {1} found in .project file.", suggestedProjectName, //$NON-NLS-1$
										description.getName()));
						description.setName(suggestedProjectName);
						project.create(description, MonitorUtils.subMonitor(monitor, 50));
					}
				} else
					project.create(description, MonitorUtils.subMonitor(monitor, 50));
			}

			project.open(0, MonitorUtils.subMonitor(monitor, 20));
			Resolution cr = wb.getResolution(StorageManager.getDefault());
			IReaderType readerType = getMaterializationReaderType(cr);
			readerType.shareProject(project, cr, context, MonitorUtils.subMonitor(monitor, 50));
			WorkspaceInfo.setComponentIdentifier(project, cr.getCSpec().getComponentIdentifier());
			MonitorUtils.worked(monitor, 30);
		} finally {
			monitor.done();
		}
	}

	private String getDefaultProjectName(MaterializationSpec mspec, Resolution resolution) throws CoreException {
		return mspec.getProjectName(resolution);
	}

	private WorkspaceBinding performPrebindAction(WorkspaceBinding wb, RMContext context, IProgressMonitor monitor) throws CoreException {
		StorageManager sm = StorageManager.getDefault();
		Resolution resolution = wb.getResolution(StorageManager.getDefault());
		CSpec cspec = resolution.getCSpec();
		try {
			IPerformManager performManager = CorePlugin.getPerformManager();
			Attribute bindEntryPoint = cspec.getBindEntryPoint();
			if (!(bindEntryPoint instanceof TopLevelAttribute)) {
				Attribute prebindAttr = cspec.getPrebind();
				if (prebindAttr != null)
					performManager.perform(cspec, prebindAttr.getName(), context, false, false, monitor);
				else
					MonitorUtils.complete(monitor);
				return wb;
			}

			Map<String, String> props = context.getProperties(resolution.getRequest());
			IPath productPath = ((TopLevelAttribute) bindEntryPoint).getUniquePath(wb.getComponentLocation(), new ModelCache(props));
			String bindingName = context.getBindingName(resolution, props);

			performManager.perform(cspec, bindEntryPoint.getName(), props, false, false, monitor);

			Resolution newRes = LocalResolver.fromPath(productPath, resolution.getName());
			newRes = new Resolution(newRes.getCSpec(), resolution);
			newRes.store(sm);
			Materialization newMat = new Materialization(productPath.addTrailingSeparator(), newRes.getComponentIdentifier());
			newMat.store(sm);
			return new WorkspaceBinding(newMat.getComponentLocation(), newRes, wb.getWorkspaceRoot(), new Path(bindingName), null);
		} catch (CoreException e) {
			if (!context.isContinueOnError())
				throw e;
			context.addRequestStatus(resolution.getRequest(), e.getStatus());
			return wb;
		}
	}
}
