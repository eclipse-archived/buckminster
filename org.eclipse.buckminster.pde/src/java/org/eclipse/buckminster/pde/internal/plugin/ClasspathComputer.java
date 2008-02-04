/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal.plugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModelStatus;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.environments.IExecutionEnvironment;
import org.eclipse.jdt.launching.environments.IExecutionEnvironmentsManager;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginLibrary;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ClasspathUtilCore;
import org.eclipse.pde.internal.core.ExecutionEnvironmentAnalyzer;
import org.eclipse.pde.internal.core.JavadocLocationManager;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.build.WorkspaceBuildModel;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.eclipse.team.core.RepositoryProvider;

/**
 * Copied from org.eclipse.pde.internal.ui.wizards.plugin since we need this one
 * in headless operations
 */
@SuppressWarnings("restriction")
public class ClasspathComputer {
	
	private static Hashtable<String,Integer> fSeverityTable = null;
	private static final int SEVERITY_ERROR = 3;
	private static final int SEVERITY_WARNING = 2;
	private static final int SEVERITY_IGNORE = 1;
	
	public static void setClasspath(IProject project, IPluginModelBase model) throws CoreException {
		IClasspathEntry[] entries = getClasspath(project, model, false);
		JavaCore.create(project).setRawClasspath(entries, null);
	}
	
	public static IClasspathEntry[] getClasspath(IProject project, IPluginModelBase model, boolean clear) throws CoreException {

		ArrayList<IClasspathEntry> result = new ArrayList<IClasspathEntry>();
				
		IBuild build = getBuild(project);

		// add own libraries/source
		addSourceAndLibraries(project, model, build, clear, result);
	
		// add JRE and set compliance options
		String ee = getExecutionEnvironment(model.getBundleDescription());	
		result.add(createJREEntry(ee));
		setComplianceOptions(JavaCore.create(project), ExecutionEnvironmentAnalyzer.getCompliance(ee));

		// add pde container
		result.add(createContainerEntry());

		IClasspathEntry[] entries = (IClasspathEntry[]) result.toArray(new IClasspathEntry[result.size()]);
		IJavaProject javaProject = JavaCore.create(project);
		IJavaModelStatus validation = 
			JavaConventions.validateClasspath(
								javaProject, 
								entries, 
								javaProject.getOutputLocation());
		if (!validation.isOK()) {
			PDECore.logErrorMessage(validation.getMessage());
			throw new CoreException(validation);
		}
		return (IClasspathEntry[])result.toArray(new IClasspathEntry[result.size()]);
	}

	public static void addSourceAndLibraries(IProject project, IPluginModelBase model, IBuild build, boolean clear, List<IClasspathEntry> result) throws CoreException
	{	
		HashSet<IPath> paths = new HashSet<IPath>();

		if(!clear)
		{
			// keep existing source folders
			//
			for(IClasspathEntry entry : JavaCore.create(project).getRawClasspath())
				if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE && paths.add(entry.getPath()))
					result.add(entry);
		}

		IClasspathAttribute[] attrs = getClasspathAttributes(project, model);
		IPluginLibrary[] libraries = model.getPluginBase().getLibraries();
		if (libraries.length == 0)
		{
			IBuildEntry buildEntry = build == null ? null : build.getEntry("source..");
			if (buildEntry != null && hasSourceFolder(buildEntry, project))
				addSourceFolder(buildEntry, project, paths, result);
			else if (ClasspathUtilCore.hasBundleStructure(model))
				addJARdPlugin(project, ClasspathUtilCore.getFilename(model), attrs, result);
		}
		else
		{
			for (IPluginLibrary library : libraries)
			{
				IBuildEntry buildEntry = build == null ? null : build.getEntry("source." + library.getName()); //$NON-NLS-1$
				if (buildEntry != null && hasSourceFolder(buildEntry, project))
					addSourceFolder(buildEntry, project, paths, result);
				else
				{
					if (library.getName().equals(".")) //$NON-NLS-1$
						addJARdPlugin(project, ClasspathUtilCore.getFilename(model), attrs, result);
					else
						addLibraryEntry(project, library, attrs, result);
				}
			}
		}
	}
	
	private static IClasspathAttribute[] getClasspathAttributes(IProject project, IPluginModelBase model) {
		IClasspathAttribute[] attributes = new IClasspathAttribute[0];
		if (!RepositoryProvider.isShared(project)) {			
			JavadocLocationManager manager = PDECore.getDefault().getJavadocLocationManager();
			String javadoc = manager.getJavadocLocation(model);
			if (javadoc != null) {
				attributes = new IClasspathAttribute[] 
				   {JavaCore.newClasspathAttribute(IClasspathAttribute.JAVADOC_LOCATION_ATTRIBUTE_NAME, javadoc)};
			}
		}
		return attributes;
	}

	private static boolean hasSourceFolder(IBuildEntry buildEntry, IProject project) throws CoreException
	{
		for(String folderName : buildEntry.getTokens())
		{
			IResource folder = project.findMember(folderName);
			if(folder == null || !(folder instanceof IFolder))
				continue;
			if(((IFolder)folder).members().length > 0)
				return true;
		}
		return false;
	}
	
	private static void addSourceFolder(IBuildEntry buildEntry, IProject project, Set<IPath> paths, List<IClasspathEntry> result) throws CoreException {
		String[] folders = buildEntry.getTokens();
		for (int j = 0; j < folders.length; j++) {
			String folder = folders[j];
			IPath path = project.getFullPath().append(folder);
			if (paths.add(path)) {
				if (project.findMember(folder) == null) {
					CoreUtility.createFolder(project.getFolder(folder));
				} else {
					IPackageFragmentRoot root = JavaCore.create(project).getPackageFragmentRoot(path.toString());
					if (root.exists() && root.getKind() == IPackageFragmentRoot.K_BINARY) {
						result.add(root.getRawClasspathEntry());
						continue;
					} 
				}
				result.add(JavaCore.newSourceEntry(path));
			} 
		}	
	}
	
	protected static IBuild getBuild(IProject project) throws CoreException {
		IFile buildFile = project.getFile("build.properties"); //$NON-NLS-1$
		IBuildModel buildModel = null;
		if (buildFile.exists()) {
			buildModel = new WorkspaceBuildModel(buildFile);
			buildModel.load();
		}
		return (buildModel != null) ? buildModel.getBuild() : null;
	}
	
	private static void addLibraryEntry(IProject project, IPluginLibrary library, IClasspathAttribute[] attrs, List<IClasspathEntry> result) throws JavaModelException {
		String name = ClasspathUtilCore.expandLibraryName(library.getName());
		IResource jarFile = project.findMember(name);
		if (jarFile == null)
			return;
		
		IPackageFragmentRoot root = JavaCore.create(project).getPackageFragmentRoot(jarFile);
		if (root.exists() && root.getKind() == IPackageFragmentRoot.K_BINARY) {
			IClasspathEntry oldEntry = root.getRawClasspathEntry();
			if (oldEntry.getSourceAttachmentPath() != null && !result.contains(oldEntry)) {
				result.add(oldEntry);		
				return;
			}
		}
			
		IResource resource = project.findMember(getSourceZipName(name));
		IPath srcAttachment = resource != null ? resource.getFullPath() : null;
		IClasspathEntry entry = JavaCore.newLibraryEntry(jarFile.getFullPath(), srcAttachment, null, new IAccessRule[0], attrs, library.isExported());
		if (!result.contains(entry))
			result.add(entry);
	}

	private static void addJARdPlugin(IProject project, String filename, IClasspathAttribute[] attrs, List<IClasspathEntry> result) {		
		String name = ClasspathUtilCore.expandLibraryName(filename);
		IResource jarFile = project.findMember(name);
		if (jarFile != null) {
			IResource resource = project.findMember(getSourceZipName(name));
			IPath srcAttachment = resource != null ? resource.getFullPath() : jarFile.getFullPath();
			IClasspathEntry entry =
				JavaCore.newLibraryEntry(jarFile.getFullPath(), srcAttachment, null, new IAccessRule[0], attrs, true);
			if (!result.contains(entry))
				result.add(entry);
		}
	}

	public static String getSourceZipName(String libraryName) {
		int dot = libraryName.lastIndexOf('.');
		return (dot != -1) ? libraryName.substring(0, dot) + "src.zip" : libraryName;	 //$NON-NLS-1$
	}
	
	private static String getExecutionEnvironment(BundleDescription bundleDescription) {
		if (bundleDescription != null) {
			String[] envs = bundleDescription.getExecutionEnvironments();
			if (envs.length > 0)
				return envs[0];
		}
		return null;
	}

	public static void setComplianceOptions(IJavaProject project, String compliance) {
		Map<String,String> map = getProjectOptions(project);		
		if (compliance == null) {
			if (map.size() > 0) {
				map.remove(JavaCore.COMPILER_COMPLIANCE);
				map.remove(JavaCore.COMPILER_SOURCE);
				map.remove(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM);
				map.remove(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER);
				map.remove(JavaCore.COMPILER_PB_ENUM_IDENTIFIER);	
			} else {
				return;
			}
		} else if (JavaCore.VERSION_1_6.equals(compliance)) {
			map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
			map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
			map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_6);
			map.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
			map.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
		} else if (JavaCore.VERSION_1_5.equals(compliance)) {
			map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
			map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
			map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
			map.put(JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR);
			map.put(JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR);
		} else if (JavaCore.VERSION_1_4.equals(compliance)) {
			map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_4);
			map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3);
			map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_2);
			updateSeverityComplianceOption(map, JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.WARNING);
			updateSeverityComplianceOption(map, JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.WARNING);
		} else if (JavaCore.VERSION_1_3.equals(compliance)) {
			map.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_3);
			map.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3);
			map.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_1);
			updateSeverityComplianceOption(map, JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.IGNORE);
			updateSeverityComplianceOption(map, JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.IGNORE);
		}
		project.setOptions(map);		
	}
	
	private static void updateSeverityComplianceOption(Map<String,String> map, String key, String value) {
		Integer current_value = null;
		Integer new_value = null;
		String current_string_value = null;
		int current_int_value = 0;
		int new_int_value = 0;
		// Initialize the severity table (only once)
		if (fSeverityTable == null) {
			fSeverityTable = new Hashtable<String,Integer>(SEVERITY_ERROR);
			fSeverityTable.put(JavaCore.IGNORE, new Integer(SEVERITY_IGNORE));
			fSeverityTable.put(JavaCore.WARNING, new Integer(SEVERITY_WARNING));
			fSeverityTable.put(JavaCore.ERROR, new Integer(SEVERITY_ERROR));
		}		
		// Get the current severity
		current_string_value = (String)map.get(key);
		if (current_string_value != null) {
			current_value = (Integer)fSeverityTable.get(current_string_value);
			if (current_value != null) {
				current_int_value = current_value.intValue();
			}
		}
		// Get the new severity
		new_value = (Integer)fSeverityTable.get(value);
		if (new_value != null) {
			new_int_value = new_value.intValue();
		}
		// If the current severity is not higher than the new severity, replace it
		if (new_int_value > current_int_value) {
			map.put(key, value);
		}
	}
	
	
	public static IClasspathEntry createJREEntry(String ee) {
		IPath path = null;
		if (ee != null) {
			IExecutionEnvironmentsManager manager = JavaRuntime.getExecutionEnvironmentsManager();
			IExecutionEnvironment env = manager.getEnvironment(ee);
			if (env != null) 
				path = JavaRuntime.newJREContainerPath(env);
		}
		if (path == null)
			path = JavaRuntime.newDefaultJREContainerPath();
		return JavaCore.newContainerEntry(path);
	}
	
	public static IClasspathEntry createContainerEntry() {
		return JavaCore.newContainerEntry(PDECore.REQUIRED_PLUGINS_CONTAINER_PATH);
	}

	@SuppressWarnings("unchecked")
	private static Map<String,String> getProjectOptions(IJavaProject project)
	{
		return project.getOptions(false);
	}
}
