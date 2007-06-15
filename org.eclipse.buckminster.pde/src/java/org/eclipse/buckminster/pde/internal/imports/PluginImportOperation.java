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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.datatransfer.FileSystemStructureProvider;
import org.eclipse.buckminster.pde.internal.datatransfer.ZipFileStructureProvider;
import org.eclipse.buckminster.pde.internal.plugin.ClasspathComputer;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironmentsManager;
import org.eclipse.osgi.service.environment.Constants;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.plugin.IFragment;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginLibrary;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.ModelEntry;
import org.eclipse.pde.internal.core.ClasspathUtilCore;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.SourceLocationManager;
import org.eclipse.pde.internal.core.build.WorkspaceBuildModel;
import org.eclipse.pde.internal.core.bundle.WorkspaceBundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.osgi.framework.BundleException;

@SuppressWarnings("restriction")
public class PluginImportOperation extends JarImportOperation
{
	public static final int IMPORT_BINARY = 1;

	public static final int IMPORT_BINARY_WITH_LINKS = 2;

	public static final int IMPORT_WITH_SOURCE = 3;

	public static void setClasspaths(IProgressMonitor monitor, Map<IProject, IClasspathEntry[]> classpaths)
			throws JavaModelException
	{
		monitor.beginTask("setting classpaths", classpaths.size());
		try
		{
			for(Map.Entry<IProject, IClasspathEntry[]> entry : classpaths.entrySet())
			{
				IClasspathEntry[] classpath = entry.getValue();
				if(classpath == null)
					continue;
				IProject project = entry.getKey();
				JavaCore.create(project).setRawClasspath(classpath, MonitorUtils.subMonitor(monitor, 1));
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private IProject fProject;

	private Map<IProject, IClasspathEntry[]> m_classpaths;

	private final IPath m_destination;

	private final int m_importType;

	private final IPluginModelBase m_model;

	private final NodeQuery m_query;

	public PluginImportOperation(IPluginModelBase model, NodeQuery query, IPath destination, int importType)
	{
		m_model = model;
		m_query = query;
		m_importType = importType;
		m_destination = destination;
	}

	public void importPlugin(IProgressMonitor monitor) throws CoreException
	{
		MaterializationContext context = (MaterializationContext)m_query.getContext();
		ComponentRequest request = m_query.getComponentRequest();
		String projectName = request.getProjectName();
		String id = m_model.getPluginBase().getId();
		monitor.beginTask("Importing plugin " + id, 7);
		try
		{
			ConflictResolution conflictResolution = context.getMaterializationSpec().getConflictResolution(request);
			IProject project = findProject(projectName);
			BundleDescription desc = m_model.getBundleDescription();
			if(desc != null)
			{
				IExecutionEnvironmentsManager manager = JavaRuntime.getExecutionEnvironmentsManager();
				String[] envs = desc.getExecutionEnvironments();
				boolean found = false;
				for(int i = 0; i < envs.length; i++)
				{
					if(manager.getEnvironment(envs[i]) != null)
					{
						found = true;
						break;
					}
				}
				if(envs.length > 0 && !found)
				{
					// TODO: There's a misfit in execution environment. The AdvisorNode should have
					// a way
					// to handle this.
					PDEPlugin.getLogger().info("Execution environment misfit. Skipping plugin " + id);
					return;
				}
			}

			if(project.exists())
			{
				switch(conflictResolution)
				{
				case FAIL:
					throw new BuckminsterException("Project " + projectName + " already exists");
				case KEEP:
					return;
				default:
				}
				project.delete(true, true, MonitorUtils.subMonitor(monitor, 1));
				try
				{
					RepositoryProvider.unmap(project);
				}
				catch(TeamException e)
				{
				}
			}
			else
				MonitorUtils.worked(monitor, 1);

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProjectDescription description = workspace.newProjectDescription(projectName);
			FileUtils.prepareDestination(m_destination.toFile(),
					conflictResolution == ConflictResolution.REPLACE, MonitorUtils.subMonitor(monitor, 1));
			description.setLocation(m_destination);

			project.create(description, MonitorUtils.subMonitor(monitor, 1));
			project.open(MonitorUtils.subMonitor(monitor, 1));
			fProject = project;

			IProgressMonitor importMonitor = MonitorUtils.subMonitor(monitor, 4);
			switch(m_importType)
			{
			case IMPORT_BINARY:
				importAsBinary(true, importMonitor);
				break;
			case IMPORT_BINARY_WITH_LINKS:
				if(id.startsWith("org.eclipse.swt") && !isJARd())
					importAsBinary(true, importMonitor);
				else
					importAsBinaryWithLinks(importMonitor);
				break;
			case IMPORT_WITH_SOURCE:
				if(isExempt())
					importAsBinary(true, importMonitor);
				else
					importAsSource(importMonitor);
			}

			setProjectDescription();
			IClasspathEntry[] classpaths = null;

			if(m_classpaths != null && project.hasNature(JavaCore.NATURE_ID)
					&& project.findMember(".classpath") == null)
				classpaths = ClasspathComputer.getClasspath(project, m_model, true);
			m_classpaths.put(project, classpaths);
		}
		finally
		{
			monitor.done();
		}
	}

	public void run(IProgressMonitor monitor) throws CoreException, OperationCanceledException
	{
		importPlugin(monitor);
		MonitorUtils.testCancelStatus(monitor);
	}

	public void setClasspathCollector(Map<IProject, IClasspathEntry[]> classpaths)
	{
		m_classpaths = classpaths;
	}

	private String addBuildEntry(WorkspaceBuildModel model, String key, String value) throws CoreException
	{
		IBuild build = model.getBuild(true);
		IBuildEntry entry = build.getEntry(key);
		if(entry == null)
		{
			entry = model.getFactory().createEntry(key);
			entry.addToken(value);
			build.add(entry);
		}
		String[] tokens = entry.getTokens();
		return (tokens.length > 0)
				? tokens[0]
				: "src/"; //$NON-NLS-1$
	}

	private void configureBinIncludes(WorkspaceBuildModel buildModel) throws CoreException
	{
		IBuildEntry entry = buildModel.getBuild(true).getEntry("bin.includes"); //$NON-NLS-1$
		if(entry == null)
		{
			entry = buildModel.getFactory().createEntry("bin.includes"); //$NON-NLS-1$
			File location = new File(m_model.getInstallLocation());
			if(location.isDirectory())
			{
				File[] files = location.listFiles();
				for(int i = 0; i < files.length; i++)
				{
					String token = files[i].getName();
					if(files[i].isDirectory())
						token = token + "/"; //$NON-NLS-1$
					entry.addToken(token);
				}
			}
			else
			{
				String[] tokens = getTopLevelResources(location);
				for(int i = 0; i < tokens.length; i++)
				{
					entry.addToken(tokens[i]);
				}
			}
			buildModel.getBuild().add(entry);
		}
	}

	private void configureSrcIncludes(WorkspaceBuildModel buildModel, List<File> list) throws CoreException
	{
		IBuildEntry entry = buildModel.getBuild(true).getEntry("src.includes"); //$NON-NLS-1$
		if(entry == null)
		{
			entry = buildModel.getFactory().createEntry("src.includes"); //$NON-NLS-1$
			for(File location : list)
			{
				String token = location.getName();
				if(location.isDirectory())
					token += "/"; //$NON-NLS-1$
				entry.addToken(token);
			}
			buildModel.getBuild().add(entry);
		}
	}

	private void extractJARdPlugin(IProgressMonitor monitor) throws CoreException
	{
		ZipFile zipFile = null;
		try
		{
			zipFile = new ZipFile(m_model.getInstallLocation());
			ZipFileStructureProvider provider = new ZipFileStructureProvider(zipFile);
			if(!containsCode(provider))
			{
				extractZipFile(new File(m_model.getInstallLocation()), fProject.getFullPath(), monitor);
				return;
			}
			ArrayList<?> collected = new ArrayList<Object>();
			collectNonJavaResources(provider, provider.getRoot(), collected);
			importContent(provider.getRoot(), fProject.getFullPath(), provider, collected, monitor);

			File file = new File(m_model.getInstallLocation());
			if(hasEmbeddedSource(provider) && m_importType == IMPORT_WITH_SOURCE)
			{
				collected = new ArrayList<Object>();
				collectJavaFiles(provider, provider.getRoot(), collected);
				importContent(provider.getRoot(), fProject.getFullPath(), provider, collected, monitor);
				collected = new ArrayList<Object>();
				collectJavaResources(provider, provider.getRoot(), collected);
				importContent(provider.getRoot(), fProject.getFullPath().append("src"), provider, collected, monitor); //$NON-NLS-1$
			}
			else
			{
				if(m_importType == IMPORT_BINARY_WITH_LINKS)
				{
					fProject.getFile(file.getName()).createLink(new Path(file.getAbsolutePath()), IResource.NONE, null);
				}
				else
				{
					importArchive(fProject, file, new Path(file.getName()));
				}
				if(!hasEmbeddedSource(provider))
				{
					if(m_importType == IMPORT_BINARY_WITH_LINKS)
					{
						linkSourceArchives(MonitorUtils.subMonitor(monitor, 1));
					}
					else
					{
						importSourceArchives(MonitorUtils.subMonitor(monitor, 1));
					}
				}
			}
			if(m_importType != IMPORT_WITH_SOURCE)
				modifyBundleClasspathHeader(fProject, m_model);
			else
				removeSignedHeaders(fProject);
			setPermissions();
		}
		catch(IOException e)
		{
			IStatus status = new Status(IStatus.ERROR, PDEPlugin.getPluginId(), IStatus.ERROR, e.getMessage(), e);
			throw new CoreException(status);
		}
		finally
		{
			if(zipFile != null)
			{
				try
				{
					zipFile.close();
				}
				catch(IOException e)
				{
				}
			}
		}
	}

	private IProject findProject(String id)
	{
		ModelEntry entry = PDECore.getDefault().getModelManager().findEntry(id);
		if(entry != null)
		{
			IPluginModelBase model = entry.getModel();
			if(model != null)
			{
				IResource resource = model.getUnderlyingResource();
				if(resource != null)
					return resource.getProject();
			}
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(id);
	}

	private IFragment[] getFragmentsFor()
	{
		// TODO: Need different solution for this. What search scope should
		// be used when finding fragments?
		return new IFragment[0];
	}

	private String[] getLibraryNames(boolean expand)
	{
		IPluginLibrary[] libraries = m_model.getPluginBase().getLibraries();
		int top = libraries.length;
		if(top == 0 && isJARd())
			return new String[] { "." };

		String[] list = new String[top];
		for(int i = 0; i < top; i++)
		{
			list[i] = expand
					? ClasspathUtilCore.expandLibraryName(libraries[i].getName())
					: libraries[i].getName();
		}
		return list;
	}

	private List<File> importAdditionalResources(IProject project, IPluginModelBase model, IProgressMonitor monitor)
			throws CoreException
	{
		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		File location = manager.findSourceFile(model.getPluginBase(), null);
		File[] children = location == null
				? null
				: location.listFiles();
		ArrayList<File> list = new ArrayList<File>();
		if(children != null)
		{
			for(int i = 0; i < children.length; i++)
			{
				String name = children[i].getName();
				if(!project.exists(new Path(name)) && !"src.zip".equals(name)){ //$NON-NLS-1$
					list.add(children[i]);
				}
			}

			importContent(location, project.getFullPath(), FileSystemStructureProvider.INSTANCE, list, monitor);
		}
		return list;
	}

	private void importAsBinary(boolean markAsBinary, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask("", 3); //$NON-NLS-1$
		if(isJARd())
		{
			extractJARdPlugin(MonitorUtils.subMonitor(monitor, 1));
		}
		else
		{
			importContent(new File(m_model.getInstallLocation()), fProject.getFullPath(),
					FileSystemStructureProvider.INSTANCE, null, MonitorUtils.subMonitor(monitor, 1));
			importSourceArchives(MonitorUtils.subMonitor(monitor, 1));

			// make sure all libraries have been imported
			// if any are missing, check in fragments
			IFragment[] fragments = getFragmentsFor();
			IPluginLibrary[] libraries = m_model.getPluginBase().getLibraries();

			for(int i = 0; i < libraries.length; i++)
			{
				String libraryName = libraries[i].getName();
				if(ClasspathUtilCore.containsVariables(libraryName)
						&& !fProject.exists(new Path(ClasspathUtilCore.expandLibraryName(libraryName))))
				{
					for(int j = 0; j < fragments.length; j++)
					{
						importJarFromFragment(fragments[j], libraryName);
						importSourceFromFragment(fragments[j], libraryName);
					}
				}
			}
		}

		if(markAsBinary)
		{
			fProject.setPersistentProperty(PDECore.EXTERNAL_PROJECT_PROPERTY, PDECore.BINARY_PROJECT_VALUE);
			importAdditionalResources(fProject, m_model, MonitorUtils.subMonitor(monitor, 1));
		}
		else
			monitor.done();
	}

	private void importAsBinaryWithLinks(IProgressMonitor monitor) throws CoreException
	{
		if(isJARd())
		{
			extractJARdPlugin(monitor);
		}
		else
		{
			File[] items = new File(m_model.getInstallLocation()).listFiles();
			if(items != null)
			{
				monitor.beginTask("PDEUIMessages.PluginImportOperation_linking", items.length + 1);
				for(int i = 0; i < items.length; i++)
				{
					File sourceFile = items[i];
					String name = sourceFile.getName();
					if(sourceFile.isDirectory())
					{
						fProject.getFolder(name).createLink(new Path(sourceFile.getPath()), IResource.NONE,
								MonitorUtils.subMonitor(monitor, 1));
					}
					else
					{
						if(!name.equals(".project")){ //$NON-NLS-1$ 
							fProject.getFile(name).createLink(new Path(sourceFile.getPath()), IResource.NONE,
									MonitorUtils.subMonitor(monitor, 1));
						}
					}
				}
			}
			linkSourceArchives(MonitorUtils.subMonitor(monitor, 1));
		}
		try
		{
			RepositoryProvider.map(fProject, PDECore.BINARY_REPOSITORY_PROVIDER);
		}
		catch(TeamException e)
		{
		}
	}

	private void importAsSource(IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask("", 4); //$NON-NLS-1$
		importAsBinary(false, MonitorUtils.subMonitor(monitor, 2));
		List<File> list = importAdditionalResources(fProject, m_model, MonitorUtils.subMonitor(monitor, 1));

		WorkspaceBuildModel buildModel = new WorkspaceBuildModel(fProject.getFile("build.properties")); //$NON-NLS-1$
		if(!isJARd() || containsCode(new File(m_model.getInstallLocation())))
		{
			String[] libraries = getLibraryNames(false);
			for(int i = 0; i < libraries.length; i++)
			{
				if(ClasspathUtilCore.containsVariables(libraries[i]))
					continue;
				String name = ClasspathUtilCore.expandLibraryName(libraries[i]);
				IPath libraryPath = (name.equals(".") && isJARd()) //$NON-NLS-1$
						? new Path(new File(m_model.getInstallLocation()).getName())
						: new Path(name);
				IResource jarFile = fProject.findMember(libraryPath);
				if(jarFile != null)
				{
					IResource srcZip = jarFile.getProject().findMember(
							ClasspathUtilCore.getSourceZipName(jarFile.getName()));
					if(srcZip != null)
					{
						String jarName = libraries[i].equals(".") ? "" : libraryPath.removeFileExtension().lastSegment(); //$NON-NLS-1$ //$NON-NLS-2$
						String folder = addBuildEntry(buildModel,
								"source." + libraries[i], "src" + (jarName.length() == 0 ? "/" : "-" + jarName + "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						IFolder dest = jarFile.getProject().getFolder(folder);
						if(!dest.exists())
						{
							dest.create(true, true, null);
						}
						extractZipFile(srcZip.getLocation().toFile(), dest.getFullPath(), MonitorUtils.subMonitor(
								monitor, 1));
						if(isJARd())
						{
							extractJavaResources(jarFile.getLocation().toFile(), dest, MonitorUtils.subMonitor(monitor,
									1));
						}
						else
						{
							extractResources(jarFile.getLocation().toFile(), dest, MonitorUtils.subMonitor(monitor, 1));
						}
						srcZip.delete(true, null);
						jarFile.delete(true, null);
					}
				}
				else if(name.equals(".") && fProject.getFolder("src").exists()){ //$NON-NLS-1$ //$NON-NLS-2$
					addBuildEntry(buildModel, "source..", "src/"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		configureBinIncludes(buildModel);
		if(list.size() > 0)
			configureSrcIncludes(buildModel, list);
		buildModel.save();
	}

	private void importJarFromFragment(IFragment fragment, String name) throws CoreException
	{
		IPath jarPath = new Path(ClasspathUtilCore.expandLibraryName(name));
		File jar = new File(fragment.getModel().getInstallLocation(), jarPath.toString());
		if(jar.exists())
		{
			importArchive(fProject, jar, jarPath);
		}
	}

	private void importSourceArchives(IProgressMonitor monitor) throws CoreException
	{

		String[] libraries = getLibraryNames(true);
		monitor.beginTask("PDEUIMessages.ImportWizard_operation_copyingSource", libraries.length);

		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		for(int i = 0; i < libraries.length; i++)
		{
			String zipName = ClasspathUtilCore.getSourceZipName(libraries[i]);
			IPath path = new Path(zipName);
			if(fProject.findMember(path) == null)
			{
				IPath srcPath = manager.findSourcePath(m_model.getPluginBase(), path);
				if(srcPath != null)
				{
					if("src.zip".equals(zipName) && isJARd()){ //$NON-NLS-1$
						path = new Path(ClasspathUtilCore.getSourceZipName(new File(m_model.getInstallLocation())
								.getName()));
					}
					importArchive(fProject, new File(srcPath.toOSString()), path);
				}
			}
			MonitorUtils.worked(monitor, 1);
		}
		monitor.done();
	}

	private void importSourceFromFragment(IFragment fragment, String name) throws CoreException
	{
		IPath jarPath = new Path(ClasspathUtilCore.expandLibraryName(name));
		IPath srcPath = new Path(ClasspathUtilCore.getSourceZipName(jarPath.toString()));
		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		File srcFile = manager.findSourceFile(fragment, srcPath);
		if(srcFile != null)
		{
			importArchive(fProject, srcFile, srcPath);
		}
	}

	private boolean isExempt()
	{
		String id = m_model.getPluginBase().getId();
		if("org.apache.ant".equals(id) //$NON-NLS-1$
				|| "org.eclipse.osgi.util".equals(id) //$NON-NLS-1$
				|| "org.eclipse.osgi.services".equals(id) //$NON-NLS-1$
				|| "org.eclipse.team.cvs.ssh2".equals(id)) //$NON-NLS-1$
			return true;

		if("org.eclipse.swt".equals(id) && !isJARd()) //$NON-NLS-1$
			return true;
		return false;
	}

	private boolean isInterestingResource(String name)
	{
		return name.endsWith(".jnilib") //$NON-NLS-1$
				|| name.endsWith(".sl") //$NON-NLS-1$
				|| name.endsWith(".a") //$NON-NLS-1$
				|| name.indexOf(".so") != -1; //$NON-NLS-1$
	}

	private boolean isJARd()
	{
		return new File(m_model.getInstallLocation()).isFile();
	}

	private void linkSourceArchives(IProgressMonitor monitor) throws CoreException
	{

		String[] libraries = getLibraryNames(true);
		monitor.beginTask("PDEUIMessages.ImportWizard_operation_copyingSource", libraries.length);

		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		for(int i = 0; i < libraries.length; i++)
		{
			String zipName = ClasspathUtilCore.getSourceZipName(libraries[i]);
			IPath path = new Path(zipName);
			if(fProject.findMember(path) == null)
			{
				IPath srcPath = manager.findSourcePath(m_model.getPluginBase(), path);
				if(srcPath != null)
				{
					if("src.zip".equals(zipName) && isJARd()){ //$NON-NLS-1$
						path = new Path(ClasspathUtilCore.getSourceZipName(new File(m_model.getInstallLocation())
								.getName()));
					}
					IFile zipFile = fProject.getFile(path.lastSegment());
					if(!zipFile.exists())
					{
						zipFile.createLink(srcPath, IResource.NONE, MonitorUtils.subMonitor(monitor, 1));
					}
				}
			}
			MonitorUtils.worked(monitor, 1);
		}
		monitor.done();
	}

	private void modifyBundleClasspathHeader(IProject project, IPluginModelBase base)
	{
		IFile file = project.getFile(JarFile.MANIFEST_NAME);
		if(file.exists())
		{
			WorkspaceBundleModel bmodel = new WorkspaceBundleModel(file);
			IBundle bundle = bmodel.getBundle();
			String classpath = bundle.getHeader(org.osgi.framework.Constants.BUNDLE_CLASSPATH);
			if(classpath == null)
			{
				bundle.setHeader(org.osgi.framework.Constants.BUNDLE_CLASSPATH, ClasspathUtilCore.getFilename(base));
			}
			else
			{
				try
				{
					ManifestElement[] elements = ManifestElement.parseHeader(
							org.osgi.framework.Constants.BUNDLE_CLASSPATH, classpath);
					StringBuffer buffer = new StringBuffer();
					for(int i = 0; i < elements.length; i++)
					{
						if(buffer.length() > 0)
						{
							buffer.append(","); //$NON-NLS-1$
							buffer.append(System.getProperty("line.separator")); //$NON-NLS-1$
							buffer.append(" "); //$NON-NLS-1$
						}
						if(elements[i].getValue().equals(".")) //$NON-NLS-1$
							buffer.append(ClasspathUtilCore.getFilename(base));
						else
							buffer.append(elements[i].getValue());
					}
					bundle.setHeader(org.osgi.framework.Constants.BUNDLE_CLASSPATH, buffer.toString());
				}
				catch(BundleException e)
				{
				}
			}
			bmodel.save();
		}
	}

	private boolean needsJavaNature()
	{
		if(m_model.getPluginBase().getLibraries().length > 0)
			return true;

		BundleDescription desc = m_model.getBundleDescription();
		if(desc != null)
		{
			if(desc.getExportPackages().length > 0)
				return true;
			if(desc.getRequiredBundles().length > 0)
				return true;
			if(desc.getImportPackages().length > 0)
				return true;
		}
		return false;
	}

	private void removeSignedHeaders(IProject project)
	{
		IFile file = project.getFile(JarFile.MANIFEST_NAME);
		if(!file.exists())
			return;
		WorkspaceBundleModel model = new WorkspaceBundleModel(file);
		model.save();
	}

	private void setPermissions()
	{
		try
		{
			if(!Platform.getOS().equals(Constants.OS_WIN32) && m_model instanceof IFragmentModel)
			{
				IFragment fragment = ((IFragmentModel)m_model).getFragment();
				if("org.eclipse.swt".equals(fragment.getPluginId())){ //$NON-NLS-1$
					IResource[] children = fProject.members();
					for(int i = 0; i < children.length; i++)
					{
						if(children[i] instanceof IFile && isInterestingResource(children[i].getName()))
						{
							Runtime.getRuntime().exec(
									new String[] { "chmod", "755", children[i].getLocation().toOSString() }).waitFor(); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
		}
		catch(CoreException e)
		{
		}
		catch(InterruptedException e)
		{
		}
		catch(IOException e)
		{
		}
	}

	private void setProjectDescription() throws CoreException
	{
		IProjectDescription desc = fProject.getDescription();
		if(needsJavaNature())
			desc.setNatureIds(new String[] { JavaCore.NATURE_ID, IPDEConstants.PLUGIN_NATURE });
		else
			desc.setNatureIds(new String[] { IPDEConstants.PLUGIN_NATURE });
		fProject.setDescription(desc, null);
	}

}
