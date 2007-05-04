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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.LocalResolver;
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
	public IPath getDefaultInstallRoot(MaterializationContext context) throws CoreException
	{
		return ResourcesPlugin.getWorkspace().getRoot().getLocation();
	}

	@Override
	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor)
	throws CoreException
	{
		WorkspaceBinding wb = createBindSpec(resolution, context);
		if(wb == null)
		{
			MonitorUtils.complete(monitor);
			return;
		}

		monitor.beginTask(null, 200);
		try
		{
			monitor.subTask("Binding " + wb.getWorkspaceRelativePath());
			wb = this.performPrebindAction(wb, context, MonitorUtils.subMonitor(monitor, 100));

			Materialization mat = wb.getMaterialization();
			IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 100);
			IPath wsRelativePath = wb.getWorkspaceRelativePath();
			if(wsRelativePath.segmentCount() == 1)
				this.createProjectBinding(wsRelativePath.segment(0), mat, context, subMonitor);
			else
				this.createExternalBinding(wsRelativePath, mat, subMonitor);
			wb.store();
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

	private WorkspaceBinding createBindSpec(Resolution resolution, MaterializationContext context)
	throws CoreException
	{
		Materialization mat = WorkspaceInfo.getMaterialization(resolution);
		if(mat == null)
		{
			// We still want to bind stuff produced by the local reader
			//
			String readerTypeName = resolution.getProvider().getReaderTypeId();
			if(!IReaderType.LOCAL.equals(readerTypeName))
				//
				// From the platform. Don't bind this
				//
				return null;

			IReaderType localReaderType = CorePlugin.getDefault().getReaderType(readerTypeName);
			mat = new Materialization(localReaderType.getFixedLocation(resolution), resolution);
		}

		IPath wsRelativePath;
		IPath matLoc = mat.getComponentLocation();
		if(matLoc.hasTrailingSeparator())
		{
			ComponentIdentifier ci = resolution.getComponentIdentifier();
			wsRelativePath = context.getMaterializationSpec().getResourcePath(ci);
			if(wsRelativePath == null)
				//
				// Default to project.
				//
				wsRelativePath = Path.fromPortableString(getDefaultProjectName(resolution));
		}
		else
		{
			IPath bmProjLoc = CorePlugin.getDefault().getBuckminsterProjectLocation();
			if(bmProjLoc.isPrefixOf(matLoc))
				wsRelativePath = matLoc.removeFirstSegments(bmProjLoc.segmentCount() - 1).setDevice(null);
			else
				//
				// This will become a link in the root of the .buckminster project
				//
				wsRelativePath = new Path(CorePlugin.BUCKMINSTER_PROJECT).append(matLoc.lastSegment());

			if(matLoc.hasTrailingSeparator())
				wsRelativePath.addTrailingSeparator();
		}
		return new WorkspaceBinding(wsRelativePath, mat);
	}

	private void createExternalBinding(IPath wsRelativePath, Materialization mat, IProgressMonitor monitor)
	throws CoreException,
		IOException
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
				if(locationPath.removeFirstSegments(matSegs - relSegs).setDevice(null).equals(
					projRelativePath))
				{
					if(relSegs == 1)
						locationProjRoot = locationPath;
					else
						locationProjRoot = locationPath.removeLastSegments(relSegs);
				}
			}

			boolean useLink = false;
			if(locationProjRoot == null)
			{
				// The root of the project may contain links so we can still resolve this
				//
				if(relSegs == 1)
					useLink = true;
				else
					throw new BuckminsterException("Unable to determine project root when binding "
						+ locationPath + " to <workspace>/" + wsRelativePath);
			}

			IProject projectForBinding = wsRoot.getProject(projName);
			if(!projectForBinding.exists())
			{
				// The project does not exist yet. Create it so that it appoints the root
				// of the materialization or if a link is used, in the current workspace.
				//
				if(useLink || locationProjRoot.removeLastSegments(1).equals(wsRoot.getLocation()))
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
								+ wsRelativePath + " to " + locationPath + ", the link origin '"
								+ projRelativePath + "' is already in use");

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
								+ wsRelativePath + " to " + locationPath + ", the link origin: "
								+ projRelativePath + " is already in use");
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
			WorkspaceInfo.setComponentIdentifier(projectForBinding.findMember(projRelativePath),
				mat.getComponentIdentifier());
		}
		finally
		{
			monitor.done();
		}
	}

	private void createProjectBinding(String projName, Materialization mat, MaterializationContext context,
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
		try
		{
			Resolution cr = mat.getResolution();
			try
			{
				description = workspace.loadProjectDescription(locationPath.append(".project"));
				// Consider it an error to attempt a bind using a different
				// project name then the one present in the .project file.
				//
				if(!projName.equals(description.getName()))
					throw new ProjectNameMismatchException(projName, description.getName());
			}
			catch(CoreException exception)
			{
				// Apparently this is not an Eclipse project (yet)
				//
				description = workspace.newProjectDescription(projName);
				IPath wsRootPath = wsRoot.getLocation();
				if(wsRootPath.isPrefixOf(locationPath)
					&& wsRootPath.segmentCount() == locationPath.segmentCount() - 1)
				{
					// This is heading for disaster unless the last segement of the locationPath
					// is in fact equal to the name of the project
					//
					if(!locationPath.lastSegment().equals(projName))
						description.setLocation(locationPath);
				}
				else
					description.setLocation(locationPath);
			}
			MonitorUtils.worked(monitor, 50);

			if(!project.exists())
				project.create(description, MonitorUtils.subMonitor(monitor, 50));
			else
				MonitorUtils.worked(monitor, 50);

			project.open(0, MonitorUtils.subMonitor(monitor, 20));
			IReaderType readerType = cr.getProvider().getReaderType();
			readerType.shareProject(project, cr, context, MonitorUtils.subMonitor(monitor, 30));
			WorkspaceInfo.setComponentIdentifier(project, cr.getCSpec().getComponentIdentifier());
			MonitorUtils.worked(monitor, 30);
		}
		finally
		{
			monitor.done();
		}
	}

	private String getDefaultProjectName(Resolution resolution) throws CoreException
	{
		ComponentRequest cname = resolution.getRequest();
		String name = cname.getName();
		String categoryName = cname.getCategory();
		ComponentCategory cc = ComponentCategory.getCategory(categoryName);
		if(cc == null)
			return name;

		Pattern desiredMatch = cc.getDesiredNamePattern();
		if(desiredMatch == null || desiredMatch.matcher(name).find())
			//
			// We have a category but no desire to change the name
			//
			return name;

		Pattern repFrom = cc.getSubstituteNamePattern();
		String repTo = cc.getNameSubstitution();

		if(repFrom == null || repTo == null)
			throw new BuckminsterException("Category: " + categoryName + " defines desiredNamePattern but no substitution");

		Matcher matcher = repFrom.matcher(name);
		if(matcher.matches())
		{
			String repl = matcher.replaceAll(repTo).trim();
			if(repl.length() > 0)
				name = repl;
		}
		return name;
	}

	private WorkspaceBinding performPrebindAction(WorkspaceBinding wb, MaterializationContext context,
		IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Materialization mat = wb.getMaterialization();
			Resolution resolution = mat.getResolution();
			CSpec cspec = resolution.getCSpec();
			IPerformManager performManager = CorePlugin.getPerformManager();
			Attribute bindEntryPoint = cspec.getBindEntryPoint();
			if(bindEntryPoint == null)
			{
				Attribute prebindAttr = cspec.getPrebind();
				if(prebindAttr != null)
					performManager.perform(cspec, prebindAttr.getName(), context, false, monitor);
				else
					MonitorUtils.complete(monitor);
				return wb;
			}
	
			Map<String, String> props = context.getProperties(resolution.getRequest());
			IPath productPath = bindEntryPoint.getUniquePath(mat.getComponentLocation(), new ModelCache(props));
			String bindingName = context.getBindingName(resolution, props);
	
			performManager.perform(cspec, bindEntryPoint.getName(), props, false, monitor);
			Resolution newRes = LocalResolver.fromPath(productPath, resolution.getName());
			Materialization newMat = new Materialization(productPath.addTrailingSeparator(), newRes);
			return new WorkspaceBinding(new Path(bindingName), newMat);
		}
		catch(CoreException e)
		{
			if(!context.isContinueOnError())
				throw e;
			context.addException(e.getStatus());
			return wb;
		}
	}
}
