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
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.LocalResolver;
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

public class WorkspaceMaterializer extends FileSystemMaterializer
{
	@Override
	public IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution) throws CoreException
	{
		IPath location = context.getWorkspaceLocation(resolution);
		IPath leaf = context.getLeafArtifact(resolution);
		
		// There are two conditions for putting this into the .buckminster project
		//
		// 1. There is a leaf artifact that indicates that what we have here
		//    is a file.
		// 2. The leaf artifact is null but the materialization will perform an
		//    unpack. This normally means that an archive (zip or tar.gz) has a
		//    root folder that isn't known until the unpack is complete. Such
		//    a root cannot be used as a project at this point.
		//
		if(leaf == null)
		{
			if(context.getMaterializationSpec().isUnpack(resolution.getComponentIdentifier()))
				location = location.append(CorePlugin.BUCKMINSTER_PROJECT);				
		}
		else if(!leaf.hasTrailingSeparator())
		{
			location = location.append(CorePlugin.BUCKMINSTER_PROJECT);
		}
		return location;
	}

	@Override
	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			WorkspaceBinding wb = createBindSpec(resolution, context);
			if(wb == null)
			{
				MonitorUtils.complete(monitor);
				return;
			}

			IPath wsRoot = wb.getWorkspaceRoot();
			if(FileUtils.pathEquals(wsRoot, ResourcesPlugin.getWorkspace().getRoot().getLocation()))
				installLocal(wb, context, monitor);
			else
			{
				// Don't install in this workspace. Instead store it for later installation
				// in the appointed workspace
				//
				ExternalDataArea dataArea = new ExternalDataArea(wsRoot, context.getMaterializationSpec().getConflictResolution(resolution.getComponentIdentifier()));
				StorageManager sm = new StorageManager(dataArea.getStateLocation(CorePlugin.getID()).toFile());
				wb.store(sm);
				storeBelow(resolution, context.getBillOfMaterials(), sm, false);
			}
		}
		catch(CoreException e)
		{
			if(!context.isContinueOnError())
				throw e;
			context.addException(resolution.getRequest(), e.getStatus());
		}
	}

	private static void storeBelow(Resolution resolution, DepNode node, StorageManager sm, boolean isBelow) throws CoreException
	{
		Resolution nodeRes = node.getResolution();
		if(nodeRes == null)
			return;

		if(!isBelow)
			isBelow = nodeRes.equals(resolution);

		if(isBelow)
		{
			// Store the resolution unless it stems from the current target platform
			//
			String readerType = resolution.getProvider().getReaderTypeId();
			if(!IReaderType.ECLIPSE_PLATFORM.equals(readerType))
			{
				resolution.store(sm);
				Materialization mat = getMaterialization(resolution);
				if(mat != null)
					mat.store(sm);
			}
		}

		for(DepNode child : node.getChildren())
			storeBelow(resolution, child, sm, isBelow);
	}

	public void installLocal(WorkspaceBinding wb, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 200);
		try
		{
			StorageManager sm = StorageManager.getDefault();
			monitor.subTask("Binding " + wb.getWorkspaceRelativePath());

			Materialization mat = wb.getMaterialization();
			mat.store(sm);
			MonitorUtils.worked(monitor, 10);

			wb = performPrebindAction(wb, context, MonitorUtils.subMonitor(monitor, 95));
			IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 95);
			IPath wsRelativePath = wb.getWorkspaceRelativePath();
			if(wsRelativePath.segmentCount() == 1)
				createProjectBinding(wsRelativePath.segment(0), mat, context, subMonitor);
			else
				createExternalBinding(wsRelativePath, mat, subMonitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	private static Materialization getMaterialization(Resolution resolution) throws CoreException
	{
		Materialization mat = WorkspaceInfo.getMaterialization(resolution);
		if(mat != null)
			return mat;
		
		// We still want to bind stuff produced by the local reader
		//
		String readerTypeName = resolution.getProvider().getReaderTypeId();
		if(!IReaderType.LOCAL.equals(readerTypeName))
			//
			// From the platform. Don't bind this
			//
			return null;

		IReaderType localReaderType = CorePlugin.getDefault().getReaderType(readerTypeName);
		return new Materialization(localReaderType.getFixedLocation(resolution), resolution.getComponentIdentifier());
	}

	private WorkspaceBinding createBindSpec(Resolution resolution, MaterializationContext context) throws CoreException
	{
		Materialization mat = getMaterialization(resolution);
		if(mat == null)
			return null;

		IPath wsRoot = context.getWorkspaceLocation(resolution);
		IPath wsRelativePath;
		IPath matLoc = mat.getComponentLocation();
		IPath bmProjLoc = CorePlugin.getDefault().getBuckminsterProjectLocation();
		if(matLoc.hasTrailingSeparator() && !bmProjLoc.isPrefixOf(matLoc))
		{
			ComponentIdentifier ci = resolution.getComponentIdentifier();
			wsRelativePath = context.getMaterializationSpec().getResourcePath(ci);
			if(wsRelativePath == null)
				//
				// Default to project.
				//
				wsRelativePath = Path.fromPortableString(getDefaultProjectName(context.getMaterializationSpec(),
						resolution));
		}
		else
		{
			IPath localWsRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			if(!FileUtils.pathEquals(wsRoot, localWsRoot))
			{
				if(localWsRoot.isPrefixOf(bmProjLoc))
					//
					// Switch ws root for the bmProject
					//
					bmProjLoc = wsRoot.append(bmProjLoc.removeFirstSegments(localWsRoot.segmentCount()));
			}

			if(bmProjLoc.isPrefixOf(matLoc))
				wsRelativePath = matLoc.removeFirstSegments(bmProjLoc.segmentCount() - 1).setDevice(null);
			else
				//
				// This will become a link in the root of the .buckminster project
				//
				wsRelativePath = new Path(CorePlugin.BUCKMINSTER_PROJECT).append(matLoc.lastSegment());

			if(matLoc.hasTrailingSeparator())
				wsRelativePath = wsRelativePath.addTrailingSeparator();
		}
		return new WorkspaceBinding(matLoc, mat.getComponentIdentifier(), wsRoot, wsRelativePath, context.getBindingProperties());
	}

	private void createExternalBinding(IPath wsRelativePath, Materialization mat, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		IPath locationPath = mat.getComponentLocation();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();

		monitor.beginTask(null, 200);
		monitor.subTask("Binding " + locationPath);
		try
		{
			String projName = wsRelativePath.segment(0);
			IPath projRelativePath = wsRelativePath.removeFirstSegments(1);

			// The directory in the materialization that corresponds to the project
			// root can be found by comparing the tail of the materialization with
			// the workspace relative location.
			//
			IPath locationProjRoot = null;
			int relSegs = projRelativePath.segmentCount();
			int matSegs = locationPath.segmentCount();
			if(matSegs >= relSegs)
			{
				if(locationPath.removeFirstSegments(matSegs - relSegs).setDevice(null).equals(projRelativePath))
					locationProjRoot = locationPath.removeLastSegments(relSegs);
			}

			boolean useLink = false;
			if(locationProjRoot == null)
			{
				// The root of the project may contain links so we can still resolve this
				//
				if(relSegs == 1)
					useLink = true;
				else
					throw new BuckminsterException("Unable to determine project root when binding " + locationPath
							+ " to <workspace>/" + wsRelativePath);
			}

			IProject projectForBinding = wsRoot.getProject(projName);
			if(!projectForBinding.exists())
			{
				// The project does not exist yet. Create it so that it appoints the root
				// of the materialization or if a link is used, in the current workspace.
				//
				if(useLink || FileUtils.pathEquals(locationProjRoot.removeLastSegments(1), wsRoot.getLocation()))
					projectForBinding.create(MonitorUtils.subMonitor(monitor, 50));
				else
				{
					IProjectDescription desc = ResourcesPlugin.getWorkspace().newProjectDescription(projName);
					desc.setLocation(locationProjRoot);
					projectForBinding.create(desc, MonitorUtils.subMonitor(monitor, 50));
				}
				projectForBinding.open(MonitorUtils.subMonitor(monitor, 50));
			}
			else if(!projectForBinding.isOpen())
				projectForBinding.open(MonitorUtils.subMonitor(monitor, 100));
			else
				MonitorUtils.worked(monitor, 100);

			if(useLink)
			{
				if(locationPath.toFile().isDirectory())
				{
					IFolder folder = projectForBinding.getFolder(projRelativePath);
					if(folder.exists())
					{
						if(!(folder.isLinked() && FileUtils.pathEquals(folder.getRawLocation(), locationPath)))
							throw new BuckminsterException("Unable to create a folder link from <workspace>/"
									+ wsRelativePath + " to " + locationPath + ", the link origin '" + projRelativePath
									+ "' is already in use");

						MonitorUtils.worked(monitor, 50);
					}
					else
						folder.createLink(locationPath, 0, MonitorUtils.subMonitor(monitor, 50));
				}
				else
				{
					IFile ifile = projectForBinding.getFile(projRelativePath);
					if(ifile.exists())
					{
						if(!(ifile.isLinked() && FileUtils.pathEquals(ifile.getRawLocation(), locationPath)))
							throw new BuckminsterException("Unable to create a file link from <workspace>/"
									+ wsRelativePath + " to " + locationPath + ", the link origin: " + projRelativePath
									+ " is already in use");
						MonitorUtils.worked(monitor, 50);
					}
					else
						ifile.createLink(locationPath, 0, MonitorUtils.subMonitor(monitor, 50));
				}
			}
			else
				MonitorUtils.worked(monitor, 50);

			// This resource now resides within the project but a refresh is needed
			//
			projectForBinding.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));
			WorkspaceInfo.setComponentIdentifier(projectForBinding.findMember(projRelativePath), mat
					.getComponentIdentifier());
		}
		finally
		{
			monitor.done();
		}
	}

	private void createProjectBinding(String projName, Materialization mat, RMContext context,
			IProgressMonitor monitor) throws CoreException, IOException
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = workspace.getRoot();
		IProject project = wsRoot.getProject(projName);

		// Get the absolute path for the ProjectBinding
		//
		IPath locationPath = mat.getComponentLocation();

		// Check that the source directory is present.
		//
		if(!locationPath.toFile().exists())
		{
			MonitorUtils.complete(monitor);
			throw new FileNotFoundException(locationPath.toString());
		}

		// Find the .project file and load the description
		//
		IProjectDescription description = null;
		monitor.beginTask(null, 150);
		monitor.subTask("Binding " + projName);

		// Some special treatment is needed for projects that are rooted in the workspace location
		//
		IPath wsRootPath = wsRoot.getLocation();
		boolean isRootedInWorkspace = (wsRootPath.segmentCount() == locationPath.segmentCount() - 1 && isSegmentPrefix(wsRootPath, locationPath));

		try
		{
			if(project.exists())
			{
				description = workspace.loadProjectDescription(locationPath.append(".project"));

				// Consider it an error to attempt a bind using a different
				// project name then the one present in the .project file.
				//
				if(isRootedInWorkspace && !projName.equals(description.getName()))
					throw new ProjectNameMismatchException(projName, description.getName());
				MonitorUtils.worked(monitor, 50);
			}
			else
			{
				if(isRootedInWorkspace)
				{
					// This is heading for disaster unless the last segment of the locationPath
					// is in fact equal to the name of the project
					//
					String forcedName = locationPath.lastSegment();
					if(!forcedName.equals(projName))
					{
						// We can't use another name since the project is directly below the workspace.
						// Eclipse stipulates that the name *has* to be the same name as the folder
						// at this point. So we start over here...
						//
						createProjectBinding(forcedName, mat, context, MonitorUtils.subMonitor(monitor, 150));
						return;
					}
				}
				else
				{
					description = workspace.newProjectDescription(projName);
					description.setLocation(locationPath);
				}
				project.create(description, MonitorUtils.subMonitor(monitor, 50));
			}

			project.open(0, MonitorUtils.subMonitor(monitor, 20));
			Resolution cr = mat.getResolution();
			IReaderType readerType = cr.getProvider().getReaderType();
			readerType.shareProject(project, cr, context, MonitorUtils.subMonitor(monitor, 50));
			WorkspaceInfo.setComponentIdentifier(project, cr.getCSpec().getComponentIdentifier());
			MonitorUtils.worked(monitor, 30);
		}
		finally
		{
			monitor.done();
		}
	}

	private static boolean isSegmentPrefix(IPath self, IPath other)
	{
		String device = self.getDevice();
		if(device != null && other.getDevice() != null && !device.equalsIgnoreCase(other.getDevice()))
			return false;
		if(self.isEmpty() || (self.isRoot() && other.isAbsolute()))
			return true;

		String[] segments = self.segments();
		int len = segments.length;
		String[] otherSegments = other.segments();
		if(len > otherSegments.length)
			return false;

		if(FileUtils.CASE_INSENSITIVE_FS)
		{
			for(int i = 0; i < len; i++)
				if(!segments[i].equalsIgnoreCase(otherSegments[i]))
					return false;
		}
		else
		{
			for(int i = 0; i < len; i++)
				if(!segments[i].equals(otherSegments[i]))
					return false;
		}
		return true;
	}

	private String getDefaultProjectName(MaterializationSpec mspec, Resolution resolution) throws CoreException
	{
		return mspec.getProjectName(resolution.getRequest());
	}

	private WorkspaceBinding performPrebindAction(WorkspaceBinding wb, RMContext context,
			IProgressMonitor monitor) throws CoreException
	{
		StorageManager sm = StorageManager.getDefault();
		Materialization mat = wb.getMaterialization();
		Resolution resolution = mat.getResolution();
		CSpec cspec = resolution.getCSpec();
		try
		{
			IPerformManager performManager = CorePlugin.getPerformManager();
			Attribute bindEntryPoint = cspec.getBindEntryPoint();
			if(!(bindEntryPoint instanceof TopLevelAttribute))
			{
				Attribute prebindAttr = cspec.getPrebind();
				if(prebindAttr != null)
					performManager.perform(cspec, prebindAttr.getName(), context, false, monitor);
				else
					MonitorUtils.complete(monitor);
				return wb;
			}

			Map<String, String> props = context.getProperties(resolution.getRequest());
			IPath productPath = ((TopLevelAttribute)bindEntryPoint).getUniquePath(mat.getComponentLocation(), new ModelCache(props));
			String bindingName = context.getBindingName(resolution, props);

			performManager.perform(cspec, bindEntryPoint.getName(), props, false, monitor);

			cspec = LocalResolver.fromPath(productPath, resolution.getName());
			resolution = new Resolution(cspec, resolution);
			resolution.store(sm);
			Materialization newMat = new Materialization(productPath.addTrailingSeparator(), cspec
					.getComponentIdentifier());
			newMat.store(sm);
			return new WorkspaceBinding(newMat.getComponentLocation(), newMat.getComponentIdentifier(), wb.getWorkspaceRoot(), new Path(bindingName), null);
		}
		catch(CoreException e)
		{
			if(!context.isContinueOnError())
				throw e;
			context.addException(resolution.getRequest(), e.getStatus());
			return wb;
		}
	}
}
