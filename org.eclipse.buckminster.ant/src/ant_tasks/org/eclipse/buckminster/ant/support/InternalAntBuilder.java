/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.support;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.TaskAdapter;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.eclipse.ant.core.AntCorePlugin;
import org.eclipse.ant.core.AntCorePreferences;
import org.eclipse.ant.core.Property;
import org.eclipse.ant.core.Task;
import org.eclipse.ant.core.Type;
import org.eclipse.buckminster.ant.AntPreferences;
import org.eclipse.buckminster.ant.types.FileSetGroup;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class InternalAntBuilder
{
	public InternalAntBuilder(IPath antHome)
	{
		System.setProperty("ant.home", antHome.toOSString());
		System.setProperty("ant.library.dir", antHome.append("lib").toOSString());
	}

	public void build(IPath absoluteScriptFile, IPath baseDir, String[] targets,
		Map<String, String> userProps, Map<String, List<IPath>> filesetgroups, PrintStream out,
		PrintStream err) throws Throwable
	{
		// Make absolutely sure that this is executed serially. We can't have
		// two invocations manipulating static variables.
		//
		synchronized(Path.class)
		{
			Path saveSysClasspath = Path.systemClasspath;

			// This kind of replacement is really only suited for a single-threaded
			// environment. We don't have much choice though, since System.out
			// and System.err are very widely used. If we don't do this we will
			// for instance loose all compiler output. Let's just hope nobody else
			// changes them.
			//
			PrintStream saveOut = System.out;
			PrintStream saveErr = System.err;
			System.setOut(out);
			System.setErr(err);

			try
			{
				Path.systemClasspath = new Path(null, this.getJavaClassPath());
				internalBuild(absoluteScriptFile, baseDir, targets, userProps, filesetgroups, out, err);
			}
			finally
			{
				Path.systemClasspath = saveSysClasspath;
				System.setOut(saveOut);
				System.setErr(saveErr);
			}
		}
	}

	protected void internalBuild(IPath absoluteScriptFile, IPath baseDir, String[] targets,
		Map<String, String> userProps, Map<String, List<IPath>> filesetgroups, PrintStream out,
		PrintStream err) throws Throwable
	{
		Project project = new Project();
		project.init();

		if(out == null)
			out = System.out;
		if(err == null)
			err = System.err;

		AntBuildLogger logger = new AntBuildLogger();
		logger.setOutputPrintStream(out);
		logger.setErrorPrintStream(err);

		int logLevel;
		switch(AntPreferences.getLogLevel())
		{
		case Logger.DEBUG:
			logLevel = Project.MSG_DEBUG;
			break;
		case Logger.INFO:
			logLevel = Project.MSG_INFO;
			break;
		case Logger.WARNING:
			logLevel = Project.MSG_WARN;
			break;
		default:
			logLevel = Project.MSG_ERR;
		}
		logger.setMessageOutputLevel(logLevel);

		project.addBuildListener(logger);

		ProjectHelper helper = ProjectHelper.getProjectHelper();

		// We add the project helper as a reference since some Buckminster
		// tasks depend on it.
		//
		project.addReference("ant.projectHelper", helper);

		IPath scriptDir = absoluteScriptFile.removeLastSegments(1);
		if(baseDir != null && !baseDir.equals(scriptDir))
		{
			String antBaseDirStr = baseDir.removeTrailingSeparator().toOSString();
			project.setBasedir(antBaseDirStr);

			// This line might seem superfluous but it isn't. If the basedir
			// is different from the directory where the build script resides
			// and if the build script contains antcall statements, those
			// calls will actually change the basedir if this property is
			// not set explicitly.
			//
			project.setUserProperty("basedir", antBaseDirStr);
		}
		else
			baseDir = scriptDir;

		File scriptFile = absoluteScriptFile.toFile();
		project.setUserProperty("ant.file", scriptFile.getAbsolutePath());

		this.setProperties(project, userProps);
		this.setTasksAndTypes(project);

		if(filesetgroups != null)
		{
			for(Map.Entry<String, List<IPath>> entry : filesetgroups.entrySet())
				addFileSets(baseDir, entry.getKey(), entry.getValue(), project,
					logLevel >= Project.MSG_VERBOSE);
		}
		helper.parse(project, scriptFile);

		if(targets == null || targets.length == 0)
			targets = new String[] { project.getDefaultTarget() };

		project.fireBuildStarted();
		project.log("Buildfile: " + scriptFile);

		try
		{
			project.getExecutor().executeTargets(project, targets);
			project.fireBuildFinished(null);
		}
		catch(Throwable t)
		{
			project.fireBuildFinished(t);
		}

		Throwable buildResult = logger.getBuildResult();
		if(buildResult != null)
			throw buildResult;
	}

	private static void addFileSets(IPath baseDir, String key, List<IPath> paths, Project project,
		boolean logVerbose)
	{
		int top = paths.size();
		FileSet currentFS = null;
		List<String> currentFSInfo = null;
		File currentRoot = null;

		ArrayList<FileSet> fileSets = new ArrayList<FileSet>();
		Path antPath = new Path(project);
		project.addReference(key, antPath);

		Map<File, List<String>> filesetInfos = null;
		if(logVerbose)
			//
			// We use a LinkedHashMap here to preserve order
			//
			filesetInfos = new LinkedHashMap<File, List<String>>();

		for(int idx = 0; idx < top; ++idx)
		{
			IPath path = paths.get(idx);
			if(path.isAbsolute())
			{
				// Start of a new fileset
				//
				currentRoot = path.toFile();
				currentFS = new FileSet();
				currentFS.setProject(project);
				currentFS.setDir(currentRoot);
				currentFS.setDefaultexcludes(true);
				fileSets.add(currentFS);
				if(filesetInfos != null)
				{
					currentFSInfo = new ArrayList<String>();
					filesetInfos.put(currentRoot, currentFSInfo);
				}

				if(idx + 1 == top || paths.get(idx + 1).isAbsolute())
				{
					// Empty path group. Count it as a valid path anyway
					//
					Path.PathElement pathElem = antPath.createPathElement();
					pathElem.setLocation(currentRoot);
				}
				continue;
			}

			if(currentFS == null)
				throw new BuildException("pathGroup did not start with an absolute path");

			if(path.segmentCount() == 0)
				//
				// We don't add empty paths here since that would mess things up
				// for the include mechanism.
				//
				continue;

			Path.PathElement pathElem = antPath.createPathElement();
			pathElem.setLocation(new File(currentRoot, path.toOSString()));
			String include = path.toPortableString();
			currentFS.createInclude().setName(include);
			if(currentFSInfo != null)
				currentFSInfo.add(include);
		}

		// Set property if the fileset denotes one single file or path
		//
		IPath singlePath = null;
		if(paths.size() == 1)
			singlePath = paths.get(0);
		else if(paths.size() == 2 && !paths.get(1).isAbsolute())
			singlePath = paths.get(0).append(paths.get(1));

		if(singlePath != null)
		{
			String singlePathStr = singlePath.toOSString();
			project.log(String.format("Setting property %s to %s", key, singlePathStr), Project.MSG_VERBOSE);
			project.setUserProperty(key, singlePathStr);
		}

		top = fileSets.size();
		if(top == 1)
		{
			if(filesetInfos != null)
			{
				List<String> theOneAndOnly = filesetInfos.values().iterator().next();
				project.log(String.format("Adding FileSet reference %s.fileset. Includes = %s", key,
					TextUtils.concat(theOneAndOnly, ",")), Project.MSG_VERBOSE);
			}
			project.addReference(key + ".fileset", fileSets.get(0));
		}

		FileSetGroup fsg = new FileSetGroup();
		project.addReference(key + ".filesetgroup", fsg);
		for(FileSet fs : fileSets)
			fsg.addFileset(fs);

		if(filesetInfos != null)
		{
			StringBuilder bld = new StringBuilder();
			bld.append("Adding FileSetGroup reference ");
			bld.append(key);
			bld.append(".filesetgroup. Included filesets:");
			for(Map.Entry<File, List<String>> entry : filesetInfos.entrySet())
			{
				bld.append("\n    base = ");
				bld.append(entry.getKey());
				bld.append(". Includes = ");
				TextUtils.concat(bld, entry.getValue(), ",");
			}
			project.log(bld.toString(), Project.MSG_VERBOSE);
		}
	}

	@SuppressWarnings("unchecked")
	private void setProperties(Project project, Map<String, String> props)
	{
		if(props != null)
		{
			for(Map.Entry<String, String> entry : props.entrySet())
				project.setUserProperty(entry.getKey(), entry.getValue());
		}

		project.setUserProperty("ant.version", Main.getAntVersion());

		AntCorePreferences prefs = AntCorePlugin.getPlugin().getPreferences();
		List<Property> properties = prefs.getProperties();
		if(properties != null)
		{
			for(Property property : properties)
			{
				String value = property.getValue(true);
				project.setUserProperty(property.getName(), value);
			}
		}
	}

	private String getJavaClassPath()
	{
		AntCorePreferences prefs = AntCorePlugin.getPlugin().getPreferences();
		StringBuilder bld = new StringBuilder();
		File file = null;
		String pathSep = null;
		for(URL url : prefs.getURLs())
		{
			if(pathSep == null)
				pathSep = System.getProperty("path.separator");
			else
				bld.append(pathSep);

			try
			{
				file = new File(FileLocator.toFileURL(url).getPath());
			}
			catch(IOException e)
			{
				continue;
			}
			bld.append(file.getAbsolutePath());
		}
		return bld.toString();
	}

	@SuppressWarnings({ "unchecked", "restriction" })
	private void setTasksAndTypes(Project project)
	{
		AntCorePreferences prefs = AntCorePlugin.getPlugin().getPreferences();
		List<Task> tasks = prefs.getTasks();
		ComponentHelper helper = ComponentHelper.getComponentHelper(project);
		ClassLoader loader = this.getClass().getClassLoader();
		for(Task task : tasks)
		{
			AntTypeDefinition def = new AntTypeDefinition();
			def.setName(task.getTaskName());
			def.setClassName(task.getClassName());
			def.setClassLoader(loader);
			def.setAdaptToClass(Task.class);
			def.setAdapterClass(TaskAdapter.class);
			helper.addDataTypeDefinition(def);
		}

		List<Type> types = prefs.getTypes();
		for(Type type : types)
		{
			AntTypeDefinition def = new AntTypeDefinition();
			def.setName(type.getTypeName());
			def.setClassName(type.getClassName());
			def.setClassLoader(loader);
			helper.addDataTypeDefinition(def);
		}
	}
}
