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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipseImportReaderType;
import org.eclipse.buckminster.pde.internal.datatransfer.FileSystemStructureProvider;
import org.eclipse.buckminster.pde.internal.datatransfer.ZipFileStructureProvider;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IContainer;
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
import org.eclipse.core.runtime.SubProgressMonitor;
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
import org.eclipse.pde.internal.core.ClasspathComputer;
import org.eclipse.pde.internal.core.ClasspathUtilCore;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.SourceLocationManager;
import org.eclipse.pde.internal.core.build.WorkspaceBuildModel;
import org.eclipse.pde.internal.core.bundle.WorkspaceBundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.eclipse.pde.internal.core.natures.PDE;
import org.eclipse.pde.internal.core.util.CoreUtility;
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
		MonitorUtils.begin(monitor, classpaths.size());
		try
		{
			for(Map.Entry<IProject, IClasspathEntry[]> entry : classpaths.entrySet())
			{
				IClasspathEntry[] classpath = entry.getValue();
				if(classpath != null)
					JavaCore.create(entry.getKey()).setRawClasspath(classpath, MonitorUtils.subMonitor(monitor, 1));
			}
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}

	private IProject m_project;

	private EclipseImportReaderType m_classpathCollector;

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

	/**
	 * This method starts the import of a specific plugin. Checks if the execution environment is supported and also
	 * checks if the project already exists and needs to be replaced.
	 * 
	 * @param monitor
	 *            progress monitor
	 * @throws CoreException
	 *             if a problem occurs while importing a plugin
	 */
	public void importPlugin(IProgressMonitor monitor) throws CoreException
	{
		MaterializationContext context = (MaterializationContext)m_query.getContext();
		ComponentRequest request = m_query.getComponentRequest();
		String projectName = request.getProjectName();
		String id = m_model.getPluginBase().getId();
		MonitorUtils.begin(monitor, "Importing plugin " + id, 7);
		try
		{
			ConflictResolution conflictResolution = context.getMaterializationSpec().getConflictResolution(request);
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
					PDEPlugin.getLogger().info("Execution environment misfit. Skipping plugin %s", id);
					return;
				}
			}

			IProject project = findProject(projectName);
			if(project.exists())
			{
				switch(conflictResolution)
				{
				case FAIL:
					throw BuckminsterException.fromMessage("Project %s already exists", projectName);
				case KEEP:
					return;
				default:
				}
				if(RepositoryProvider.isShared(project))
					RepositoryProvider.unmap(project);
				if(!project.exists())
					project.create(MonitorUtils.subMonitor(monitor, 1));
				project.delete(true, true, MonitorUtils.subMonitor(monitor, 1));
			}
			else
				MonitorUtils.worked(monitor, 1);

			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IProjectDescription description = workspace.newProjectDescription(projectName);
			FileUtils.prepareDestination(m_destination.toFile(), conflictResolution, MonitorUtils
					.subMonitor(monitor, 1));

			if(!workspace.getRoot().getLocation().equals(m_destination.removeLastSegments(1)))
				description.setLocation(m_destination);

			project.create(description, MonitorUtils.subMonitor(monitor, 1));
			project.open(MonitorUtils.subMonitor(monitor, 1));
			m_project = project;

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
			if(m_classpathCollector != null)
			{
				if(project.hasNature(JavaCore.NATURE_ID) && project.findMember(".classpath") == null)
					m_classpathCollector.addProjectClasspath(project, ClasspathComputer.getClasspath(project, m_model,
							true, false));
			}
			project.delete(false, true, MonitorUtils.subMonitor(monitor, 100));
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

	public void setClasspathCollector(EclipseImportReaderType classpathCollector)
	{
		m_classpathCollector = classpathCollector;
	}

	protected void collectAdditionalResources(ZipFileStructureProvider provider, Object element,
			ArrayList<ZipEntry> collected, IProject project)
	{
		collectAdditionalResources(provider, element, collected);
		ListIterator<ZipEntry> li = collected.listIterator();
		while(li.hasNext())
		{
			ZipEntry ze = li.next();
			String name = ze.getName();
			// only import the entries that don't already exist
			if(m_project.findMember(name) != null)
			{
				li.remove();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void collectNonJavaResources(ZipFileStructureProvider provider, Object element, ArrayList collected)
	{
		super.collectNonJavaResources(provider, element, collected);

		// filter the resources we get back to include only relevant resource files
		//
		ListIterator<?> li = collected.listIterator();
		while(li.hasNext())
		{
			ZipEntry ze = (ZipEntry)li.next();
			String name = ze.getName();
			// filter out signature files - bug 175756
			if(name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".SF"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				li.remove();
			else if(m_importType == IMPORT_BINARY)
			{
				if("build.properties".equals(ze.getName())) //$NON-NLS-1$
					li.remove();
			}
		}
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
		IBuild build = buildModel.getBuild(true);
		IBuildEntry entry = build.getEntry("bin.includes"); //$NON-NLS-1$
		HashMap<String, String> libraryDirs = getSourceDirectories(build);
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
					if((m_project.findMember(token) == null)
							&& (build.getEntry(IBuildEntry.JAR_PREFIX + token) == null))
						continue;
					if(files[i].isDirectory())
					{
						token = token + "/"; //$NON-NLS-1$
						if(libraryDirs.containsKey(token))
							token = libraryDirs.get(token).toString();
					}
					entry.addToken(token);
				}
			}
			else
			{
				String[] tokens = getTopLevelResources(location);
				for(int i = 0; i < tokens.length; i++)
				{
					IResource res = m_project.findMember(tokens[i]);
					if((res == null) && (build.getEntry(IBuildEntry.JAR_PREFIX + tokens[i]) == null))
						continue;
					if((res instanceof IFolder) && (libraryDirs.containsKey(tokens[i])))
						continue;
					entry.addToken(tokens[i]);
				}
			}
			buildModel.getBuild().add(entry);
		}
	}

	private void configureSrcIncludes(WorkspaceBuildModel buildModel, List<String> list) throws CoreException
	{
		IBuildEntry entry = buildModel.getBuild(true).getEntry("src.includes"); //$NON-NLS-1$
		if(entry == null)
		{
			entry = buildModel.getFactory().createEntry("src.includes"); //$NON-NLS-1$
			for(int i = 0; i < list.size(); i++)
				entry.addToken(list.get(i));
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
				extractZipFile(new File(m_model.getInstallLocation()), m_project.getFullPath(), monitor);
				return;
			}
			ArrayList<?> collected = new ArrayList<Object>();
			collectNonJavaResources(provider, provider.getRoot(), collected);
			importContent(provider.getRoot(), m_project.getFullPath(), provider, collected, monitor);

			File file = new File(m_model.getInstallLocation());
			if(hasEmbeddedSource(provider) && m_importType == IMPORT_WITH_SOURCE)
			{
				collected = new ArrayList<Object>();
				collectJavaFiles(provider, provider.getRoot(), collected);
				importContent(provider.getRoot(), m_project.getFullPath(), provider, collected, monitor);
				collected = new ArrayList<Object>();
				collectJavaResources(provider, provider.getRoot(), collected);
				importContent(provider.getRoot(), m_project.getFullPath().append("src"), provider, collected, monitor); //$NON-NLS-1$
			}
			else
			{
				if(m_importType == IMPORT_BINARY_WITH_LINKS)
				{
					m_project.getFile(file.getName())
							.createLink(new Path(file.getAbsolutePath()), IResource.NONE, null);
				}
				else
				{
					importArchive(m_project, file, new Path(file.getName()));
				}
				if(!hasEmbeddedSource(provider))
				{
					if(m_importType == IMPORT_BINARY_WITH_LINKS)
					{
						linkSourceArchives(new SubProgressMonitor(monitor, 1));
					}
					else
					{
						importSourceArchives(new SubProgressMonitor(monitor, 1));
					}
				}
			}
			if(m_importType != IMPORT_WITH_SOURCE)
			{
				modifyBundleClasspathHeader(m_model);
			}
			else
			{
				removeSignedHeaders();
			}
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

	/**
	 * Creates a path representing a zip file that is named based on the plugin install location. Used to replace
	 * src.zip with a more unique and meaningful name.
	 * 
	 * @param model
	 *            model that the src.zip containg source for
	 * @return a new path describing the zip file
	 */
	private IPath getDefaultSourceNameForProject()
	{
		return new Path(ClasspathUtilCore.getSourceZipName(new File(m_model.getInstallLocation()).getName()));
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

	private HashMap<String, String> getSourceDirectories(IBuild build)
	{
		HashMap<String, String> set = new HashMap<String, String>();
		IBuildEntry[] entries = build.getBuildEntries();
		for(int i = 0; i < entries.length; i++)
		{
			String name = entries[i].getName();
			if(name.startsWith(IBuildEntry.JAR_PREFIX))
			{
				name = name.substring(7);
				String[] tokens = entries[i].getTokens();
				for(int j = 0; j < tokens.length; j++)
					set.put(tokens[j], name);
			}
		}
		return set;
	}

	/**
	 * Searches source locations for files to import to the new project, will ignore src.zip.
	 * 
	 * @param monitor
	 * @return list of imported files
	 * @throws CoreException
	 *             if there is a problem completing the import
	 */
	private List<String> importAdditionalResources(IProgressMonitor monitor) throws CoreException
	{
		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		File location = manager.findSourcePlugin(m_model.getPluginBase());
		if(location != null)
		{
			if(location.isDirectory())
			{
				ArrayList<File> list = new ArrayList<File>();
				Object root = location;
				File[] children = location.listFiles();
				if(children != null)
				{
					for(int i = 0; i < children.length; i++)
					{
						String name = children[i].getName();
						if(!m_project.exists(new Path(name)) && !"src.zip".equals(name)){ //$NON-NLS-1$
							list.add(children[i]);
						}
					}
					importContent(root, m_project.getFullPath(), FileSystemStructureProvider.INSTANCE, list, monitor);
					ArrayList<String> srcEntryList = new ArrayList<String>(list.size());
					for(ListIterator<File> iterator = list.listIterator(); iterator.hasNext();)
					{
						File current = iterator.next();
						String entry = current.getName();
						if(current.isDirectory())
						{
							entry += "/"; //$NON-NLS-1$
						}
						srcEntryList.add(entry);
					}
					return srcEntryList;
				}
			}
			else if(location.isFile())
			{
				ArrayList<ZipEntry> list = new ArrayList<ZipEntry>();
				ZipFile zipFile = null;
				try
				{
					zipFile = new ZipFile(location);
					ZipFileStructureProvider zipProvider = new ZipFileStructureProvider(zipFile);
					Object root = zipProvider.getRoot();
					collectAdditionalResources(zipProvider, root, list, m_project);
					importContent(root, m_project.getFullPath(), zipProvider, list, monitor);
					ArrayList<String> srcEntryList = new ArrayList<String>(list.size());
					for(Iterator<ZipEntry> iterator = list.iterator(); iterator.hasNext();)
					{
						ZipEntry current = iterator.next();
						String entry = current.getName();
						srcEntryList.add(entry);
					}
					return srcEntryList;
				}
				catch(IOException e)
				{
					IStatus status = new Status(IStatus.ERROR, PDEPlugin.getPluginId(), IStatus.ERROR, e.getMessage(),
							e);
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
		}
		return new ArrayList<String>(0);
	}

	/**
	 * Imports the contents of the plugin and imports source files as binary files that will not be compiled.
	 * 
	 * @param markAsBinary
	 *            whether to mark the project as a binary project
	 * @param monitor
	 *            progress monitor
	 * @throws CoreException
	 *             if there is a problem completing the import
	 */
	private void importAsBinary(boolean markAsBinary, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.begin(monitor, 4);
		if(isJARd())
		{
			extractJARdPlugin(MonitorUtils.subMonitor(monitor, 3));
		}
		else
		{
			importContent(new File(m_model.getInstallLocation()), m_project.getFullPath(),
					FileSystemStructureProvider.INSTANCE, null, MonitorUtils.subMonitor(monitor, 1));
			importSourceArchives(MonitorUtils.subMonitor(monitor, 1));

			// make sure all libraries have been imported
			// if any are missing, check in fragments
			IFragment[] fragments = getFragmentsFor();
			IPluginLibrary[] libraries = m_model.getPluginBase().getLibraries();

			IProgressMonitor fragmentMonitor = MonitorUtils.subMonitor(monitor, 1);
			MonitorUtils.begin(fragmentMonitor, libraries.length);
			for(int i = 0; i < libraries.length; i++)
			{
				String libraryName = libraries[i].getName();
				if(ClasspathUtilCore.containsVariables(libraryName)
						&& !m_project.exists(new Path(ClasspathUtilCore.expandLibraryName(libraryName))))
				{
					for(int j = 0; j < fragments.length; j++)
					{
						importJarFromFragment(fragments[j], libraryName);
						importSourceFromFragment(fragments[j], libraryName, MonitorUtils.subMonitor(monitor, 1));
					}
				}
				else
				{
					MonitorUtils.worked(monitor, 1);
				}
			}
		}

		if(markAsBinary)
		{
			m_project.setPersistentProperty(PDECore.EXTERNAL_PROJECT_PROPERTY, PDECore.BINARY_PROJECT_VALUE);
			importAdditionalResources(MonitorUtils.subMonitor(monitor, 1));
		}
		else
			MonitorUtils.done(monitor);
	}

	/**
	 * Imports the contents of the plugin and adds links to the source location(s).
	 * 
	 * @param monitor
	 *            progress monitor
	 * @throws CoreException
	 *             if there is a problem completing the import
	 */
	private void importAsBinaryWithLinks(IProgressMonitor monitor) throws CoreException
	{
		if(isJARd())
		{
			extractJARdPlugin(monitor);
			return;
		}

		File installLocation = new File(m_model.getInstallLocation());
		File[] items = installLocation.listFiles();
		MonitorUtils.begin(monitor, "Linking imported plugin", items.length + 1);
		if(items != null)
		{
			for(int i = 0; i < items.length; i++)
			{
				File sourceFile = items[i];
				String name = sourceFile.getName();
				if(sourceFile.isDirectory())
				{
					m_project.getFolder(name).createLink(new Path(sourceFile.getPath()), IResource.NONE,
							MonitorUtils.subMonitor(monitor, 1));
				}
				else
				{
					if(!name.equals(".project"))
					{
						m_project.getFile(name).createLink(new Path(sourceFile.getPath()), IResource.NONE,
								MonitorUtils.subMonitor(monitor, 1));
					}
					else
					{
						// if the binary project with links has a .project file, copy it instead
						// of linking (allows us to edit it)
						ArrayList<File> filesToImport = new ArrayList<File>(1);
						filesToImport.add(sourceFile);
						importContent(installLocation, m_project.getFullPath(), FileSystemStructureProvider.INSTANCE,
								filesToImport, new SubProgressMonitor(monitor, 1));
					}
				}
			}
		}
		linkSourceArchives(MonitorUtils.subMonitor(monitor, 1));
		MonitorUtils.done(monitor);

		try
		{
			RepositoryProvider.map(m_project, PDECore.BINARY_REPOSITORY_PROVIDER);
		}
		catch(TeamException e)
		{
		}
	}

	/**
	 * Imports the contents of the plugin and imports source files to source folders that will be compiled.
	 * 
	 * @param monitor
	 *            progress monitor
	 * @throws CoreException
	 *             if there is a problem completing the import
	 */
	private void importAsSource(IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.begin(monitor, 4);
		importAsBinary(false, MonitorUtils.subMonitor(monitor, 2));
		List<String> list = importAdditionalResources(MonitorUtils.subMonitor(monitor, 1));
		WorkspaceBuildModel buildModel = new WorkspaceBuildModel(m_project.getFile("build.properties")); //$NON-NLS-1$
		if(!isJARd() || containsCode(new File(m_model.getInstallLocation())))
		{
			String[] libraries = getLibraryNames(false);
			if(libraries.length == 0)
				libraries = new String[] { "." }; //$NON-NLS-1$
			for(int i = 0; i < libraries.length; i++)
			{
				if(ClasspathUtilCore.containsVariables(libraries[i]))
					continue;
				String name = ClasspathUtilCore.expandLibraryName(libraries[i]);
				IPath libraryPath = (name.equals(".") && isJARd()) //$NON-NLS-1$
						? new Path(new File(m_model.getInstallLocation()).getName())
						: new Path(name);
				IResource jarFile = m_project.findMember(libraryPath);
				if(jarFile != null)
				{
					String srcName = ClasspathUtilCore.getSourceZipName(libraryPath.lastSegment());
					IResource srcZip = jarFile.getProject().findMember(srcName);
					if(srcZip == null)
					{
						int extIndex = srcName.lastIndexOf('.');
						if(extIndex != -1)
						{
							srcZip = jarFile.getProject().findMember(srcName.substring(0, extIndex));
						}
					}
					// srcZip == null if plug-in has embedded source
					// if it jarred, all necessary files already in src folder
					if(srcZip == null && libraries[i].equals(".") && !isJARd()) //$NON-NLS-1$
						// if src does not exist (and returns null), then must not be plug-in with
						// embedded source
						srcZip = jarFile.getProject().findMember("src"); //$NON-NLS-1$
					if(srcZip != null)
					{
						String jarName = libraries[i].equals(".") ? "" : libraryPath.removeFileExtension().lastSegment(); //$NON-NLS-1$ //$NON-NLS-2$
						String folder = addBuildEntry(buildModel,
								"source." + libraries[i], "src" + (jarName.length() == 0 ? "/" : "-" + jarName + "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						IFolder dest = jarFile.getProject().getFolder(folder);

						if(srcZip instanceof IFolder)
						{
							// if the source (srcZip) equals the destination folder (dest), then we
							// don't want to delete/copy since every
							// is already where it needs to be. This happens when importing source
							// bundles in folder format declaring source with ext. point. (bug
							// 214542)
							if(!srcZip.equals(dest))
							{
								if(dest.exists())
								{
									dest.delete(true, null);
								}
								((IFolder)srcZip).move(dest.getFullPath(), true, new SubProgressMonitor(monitor, 1));
							}
						}
						else if(srcZip instanceof IFile)
						{
							if(!dest.exists())
							{
								dest.create(true, true, null);
							}
							extractZipFile(srcZip.getLocation().toFile(), dest.getFullPath(), new SubProgressMonitor(
									monitor, 1));
							srcZip.delete(true, null);
						}
						else
							monitor.worked(1);

						if(jarFile instanceof IFile)
						{
							if(isJARd())
							{
								extractJavaResources(jarFile.getLocation().toFile(), dest, new SubProgressMonitor(
										monitor, 1));
							}
							else
							{
								extractResources(jarFile.getLocation().toFile(), dest, new SubProgressMonitor(monitor,
										1));
							}
							jarFile.delete(true, null);
						}
						else
						{
							moveBinaryContents((IContainer)jarFile, dest, new SubProgressMonitor(monitor, 1));
						}
					}
				}
				else if(name.equals(".") && m_project.getFolder("src").exists()){ //$NON-NLS-1$ //$NON-NLS-2$
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
			importArchive(m_project, jar, jarPath);
	}

	private void importSourceArchives(IProgressMonitor monitor) throws CoreException
	{

		String[] libraries = getLibraryNames(true);
		if(libraries.length == 0)
			return;

		MonitorUtils.begin(monitor, "Copying source", libraries.length);

		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		Set<?> roots = null;
		if(manager.hasBundleManifestLocation(m_model.getPluginBase()))
			roots = manager.findSourceRoots(m_model.getPluginBase());

		for(String library : libraries)
		{
			String zipName = ClasspathUtilCore.getSourceZipName(library);
			IPath path = new Path(zipName);
			if(m_project.findMember(path) == null)
			{
				// if we are importing the source through a sourceBundle header...
				if(roots != null)
				{
					IPath sourceLocation = manager.findSourcePath(m_model.getPluginBase(), null);
					String currentRoot = ".".equals(library) ? "." : path.removeFileExtension().toString(); //$NON-NLS-1$ //$NON-NLS-2$
					if(roots.contains(currentRoot))
					{
						if(".".equals(currentRoot)){ //$NON-NLS-1$
							// Save to a special folder name based on the install location
							IPath sourceName = getDefaultSourceNameForProject();
							sourceName = sourceName.removeFileExtension();
							IFolder dest = m_project.getFolder(sourceName);
							if(!dest.exists())
								dest.create(true, true, null);

							// List all of the other source roots so they are not included when
							// importing source from the root, ".", of the jar
							Set<?> allBundleRoots = manager.findAllSourceRootsInSourceLocation(m_model.getPluginBase());
							List<IPath> rootsToExclude = new ArrayList<IPath>(allBundleRoots.size() - 1);
							for(Iterator<?> iterator2 = allBundleRoots.iterator(); iterator2.hasNext();)
							{
								String rootString = (String)iterator2.next();
								if(!".".equals(rootString)){ //$NON-NLS-1$
									rootsToExclude.add(new Path(rootString));
								}
							}

							// Extract folders containing java source
							extractJavaSource(new File(sourceLocation.toOSString()), rootsToExclude, dest, monitor);
						}
						else
						{
							// Extract the specific library from it's folder
							extractResourcesFromFolder(new File(sourceLocation.toOSString()), new Path(currentRoot),
									m_project, monitor);
						}
					}
				}
				else
				{
					IPath srcPath = manager.findSourcePath(m_model.getPluginBase(), path);
					if(srcPath != null)
					{
						if("src.zip".equals(zipName) && isJARd()){ //$NON-NLS-1$
							path = getDefaultSourceNameForProject();
						}
						importArchive(m_project, new File(srcPath.toOSString()), path);
					}
				}
			}
			monitor.worked(1);
		}
		monitor.done();
	}

	private void importSourceFromFragment(IFragment fragment, String libraryName, IProgressMonitor monitor)
			throws CoreException
	{
		try
		{
			IPath jarPath = new Path(ClasspathUtilCore.expandLibraryName(libraryName));
			String zipName = ClasspathUtilCore.getSourceZipName(jarPath.toString());
			IPath path = new Path(zipName);
			if(m_project.findMember(path) == null)
			{
				SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
				IPath srcPath = manager.findSourcePath(fragment, path);
				if(srcPath != null)
				{
					if(manager.hasBundleManifestLocation(fragment))
						// Extract the specific library from it's folder
						extractResourcesFromFolder(new File(srcPath.toOSString()), path.removeFileExtension(),
								m_project, monitor);
					else
						importArchive(m_project, new File(srcPath.toOSString()), path);
				}
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private boolean isExempt()
	{
		String id = m_model.getPluginBase().getId();
		if("org.apache.ant".equals(id) //$NON-NLS-1$
				|| "org.eclipse.osgi.util".equals(id) //$NON-NLS-1$
				|| "org.eclipse.osgi.services".equals(id) //$NON-NLS-1$
				|| "org.eclipse.core.runtime.compatibility.registry".equals(id)) //$NON-NLS-1$
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
		monitor.beginTask("Copying imported source", libraries.length);

		SourceLocationManager manager = PDECore.getDefault().getSourceLocationManager();
		if(manager.hasBundleManifestLocation(m_model.getPluginBase()))
		{
			IPath srcPath = manager.findSourcePath(m_model.getPluginBase(), null);
			if(srcPath != null)
			{
				// Source for all libraries is in the same bundle, just create one link to the
				// source bundle
				IPath path = new Path(m_project.getName() + "src.zip"); //$NON-NLS-1$
				IFile srcFile = m_project.getFile(path.lastSegment());
				if(!srcFile.exists())
				{
					srcFile.createLink(srcPath, IResource.NONE, new SubProgressMonitor(monitor, 1));
				}
			}
			monitor.worked(libraries.length);
		}
		else
		{
			for(int i = 0; i < libraries.length; i++)
			{
				String zipName = ClasspathUtilCore.getSourceZipName(libraries[i]);
				IPath path = new Path(zipName);
				if(m_project.findMember(path) == null)
				{
					IPath srcPath = manager.findSourcePath(m_model.getPluginBase(), path);
					if(srcPath != null)
					{
						if("src.zip".equals(zipName) && isJARd()){ //$NON-NLS-1$
							path = new Path(ClasspathUtilCore.getSourceZipName(new File(m_model.getInstallLocation())
									.getName()));
						}
						IFile zipFile = m_project.getFile(path.lastSegment());
						if(!zipFile.exists())
						{
							zipFile.createLink(srcPath, IResource.NONE, new SubProgressMonitor(monitor, 1));
						}
					}
				}
				monitor.worked(1);
			}
		}
		monitor.done();
	}

	private void modifyBundleClasspathHeader(IPluginModelBase base)
	{
		IFile file = m_project.getFile(JarFile.MANIFEST_NAME);
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

	/**
	 * Moves the binary files from the source container to the folder destination. Moves any file that isn't a .class
	 * file
	 * 
	 * @param srcFolder
	 *            container to move from
	 * @param dest
	 *            folder to move to
	 * @param monitor
	 *            progress monitor
	 */
	private void moveBinaryContents(IContainer srcFolder, IFolder dest, IProgressMonitor monitor)
	{
		try
		{
			// get all the folders for which we want to search
			IResource[] children = dest.members();
			ArrayList<IResource> validFolders = new ArrayList<IResource>();
			for(int i = 0; i < children.length; i++)
				if(children[i] instanceof IFolder)
				{
					String folderName = children[i].getName();
					IResource folder = srcFolder.findMember(folderName);
					if(folder != null && folder instanceof IFolder)
						validFolders.add(folder);
				}

			monitor.beginTask(new String(), validFolders.size());

			ListIterator<IResource> li = validFolders.listIterator();
			while(li.hasNext())
			{
				IFolder folder = (IFolder)li.next();
				int pathSegments = folder.getProjectRelativePath().segmentCount() - 1;
				Stack<IResource> stack = new Stack<IResource>();
				IResource[] resources = folder.members();
				for(int i = 0; i < resources.length; i++)
					stack.push(resources[i]);

				while(!stack.isEmpty())
				{
					IResource res = stack.pop();
					if(res instanceof IFile)
					{
						if(!res.getName().endsWith(".class")){ //$NON-NLS-1$
							String pathName = res.getProjectRelativePath().removeFirstSegments(pathSegments).toString();
							IFile destFile = dest.getFile(pathName);
							if(!destFile.getParent().exists())
							{
								CoreUtility.createFolder((IFolder)destFile.getParent());
							}
							// file might exist if previous project was deleted without removing
							// underlying resources
							if(destFile.exists())
								destFile.delete(true, null);
							res.move(destFile.getFullPath(), true, null);
						}
					}
					else
					{
						resources = ((IFolder)res).members();
						for(int i = 0; i < resources.length; i++)
							stack.push(resources[i]);
					}
				}
				folder.delete(true, null);
				monitor.worked(1);
			}
		}
		catch(CoreException e)
		{
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

	private void removeSignedHeaders()
	{
		IFile file = m_project.getFile(JarFile.MANIFEST_NAME);
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
					IResource[] children = m_project.members();
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
		IProjectDescription desc = m_project.getDescription();
		if(!desc.hasNature(PDE.PLUGIN_NATURE))
			CoreUtility.addNatureToProject(m_project, PDE.PLUGIN_NATURE, null);
		if(needsJavaNature() && !desc.hasNature(JavaCore.NATURE_ID))
			CoreUtility.addNatureToProject(m_project, JavaCore.NATURE_ID, null);
	}
}
