/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal.imports;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.datatransfer.IImportStructureProvider;
import org.eclipse.buckminster.pde.internal.datatransfer.ImportOperation;
import org.eclipse.buckminster.pde.internal.dialogs.IOverwriteQuery;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeatureInstallHandler;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;

@SuppressWarnings("restriction")
public class FeatureImportOperation implements IWorkspaceRunnable
{
	private final IFeatureModel m_model;

	private final NodeQuery m_query;

	private final Map<IProject,IClasspathEntry[]>  m_classpathCollector;

	private final boolean m_binary;

	private final IWorkspaceRoot m_root;

	private final IPath m_destination;

	/**
	 * 
	 * @param models
	 * @param targetPath
	 *            a parent of external project or null
	 * @param replaceQuery
	 */
	public FeatureImportOperation(Map<IProject,IClasspathEntry[]> classpathCollector, IFeatureModel model, NodeQuery query, IPath destination, boolean binary)
	{
		m_classpathCollector = classpathCollector;
		m_model = model;
		m_binary = binary;
		m_query = query;
		m_root = ResourcesPlugin.getWorkspace().getRoot();
		m_destination = destination;
	}

	/*
	 * @see IWorkspaceRunnable#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws CoreException, OperationCanceledException
	{
		createProject(monitor);
		MonitorUtils.testCancelStatus(monitor);
	}

	private void createProject(IProgressMonitor monitor) throws CoreException
	{
		String projectName = m_query.getProjectName();
		monitor.beginTask("Importing feature " + projectName, 100);
		IProject project = m_root.getProject(projectName);
		try
		{
			if(project.exists())
			{
				switch(m_query.useExistingArtifacts())
				{
				case FAIL:
					throw new BuckminsterException("Project " + projectName + " already exists");
				case KEEP:
					return;
				default:
				}
				
				// Overwrite, i.e. remove current contents.
				//
				project.delete(true, true, MonitorUtils.subMonitor(monitor, 10));
				try
				{
					RepositoryProvider.unmap(project);
				}
				catch(TeamException e)
				{
				}
			}
			else
    			MonitorUtils.worked(monitor, 10);

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProjectDescription description = workspace.newProjectDescription(projectName);
			FileUtils.prepareDestination(m_destination.toFile(),
				m_query.useExistingArtifacts() == ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 10));
			description.setLocation(m_destination);
			project.create(description, MonitorUtils.subMonitor(monitor, 5));
			project.open(MonitorUtils.subMonitor(monitor, 5));
			m_classpathCollector.put(project, null);
			File featureDir = new File(m_model.getInstallLocation());

			importContent(featureDir, project.getFullPath(),
					org.eclipse.buckminster.pde.internal.datatransfer.FileSystemStructureProvider.INSTANCE, null,
					MonitorUtils.subMonitor(monitor, 50));

			if(m_binary)
			{
				// Mark this project so that we can show image overlay
				// using the label decorator
				project.setPersistentProperty(PDECore.EXTERNAL_PROJECT_PROPERTY, PDECore.BINARY_PROJECT_VALUE);
			}

			setProjectNatures(project, m_model, MonitorUtils.subMonitor(monitor, 10));
			if(project.hasNature(JavaCore.NATURE_ID))
				setClasspath(project, m_model, MonitorUtils.subMonitor(monitor, 10));

		}
		finally
		{
			monitor.done();
		}
	}

	private void importContent(Object source, IPath destPath, IImportStructureProvider provider, List<?> filesToImport,
			IProgressMonitor monitor) throws CoreException
	{
		IOverwriteQuery query = new IOverwriteQuery()
		{
			public String queryOverwrite(String file)
			{
				return ALL;
			}
		};
		ImportOperation op = new ImportOperation(destPath, source, provider, query);
		op.setCreateContainerStructure(false);
		if(filesToImport != null)
		{
			op.setFilesToImport(filesToImport);
		}

		IStatus status = op.execute(monitor);
		if(status == Status.CANCEL_STATUS || monitor.isCanceled())
			throw new OperationCanceledException(status.getMessage());

		if(!status.isOK())
			throw new CoreException(status);
	}

	private void setProjectNatures(IProject project, IFeatureModel model, IProgressMonitor monitor)
			throws CoreException
	{
		IProjectDescription desc = project.getDescription();
		if(needsJavaNature(model))
		{
			desc.setNatureIds(new String[] { JavaCore.NATURE_ID, IPDEConstants.FEATURE_NATURE });
		}
		else
		{
			desc.setNatureIds(new String[] { IPDEConstants.FEATURE_NATURE });
		}
		project.setDescription(desc, monitor);
	}

	private void setClasspath(IProject project, IFeatureModel model, IProgressMonitor monitor)
			throws JavaModelException
	{
		IJavaProject jProject = JavaCore.create(project);

		IClasspathEntry jreCPEntry = JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER")); //$NON-NLS-1$

		String libName = model.getFeature().getInstallHandler().getLibrary();
		IClasspathEntry handlerCPEntry = JavaCore.newLibraryEntry(project.getFullPath().append(libName), null, null);

		jProject.setRawClasspath(new IClasspathEntry[] { jreCPEntry, handlerCPEntry }, monitor);
	}

	private boolean needsJavaNature(IFeatureModel model)
	{
		IFeatureInstallHandler handler = model.getFeature().getInstallHandler();
		if(handler == null)
		{
			return false;
		}
		String libName = handler.getLibrary();
		if(libName == null || libName.length() <= 0)
		{
			return false;
		}
		File lib = new File(model.getInstallLocation(), libName);
		return lib.exists();
	}

}
