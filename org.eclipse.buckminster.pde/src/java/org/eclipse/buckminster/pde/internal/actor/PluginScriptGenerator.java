/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.pde.internal.build.AbstractScriptGenerator;
import org.eclipse.pde.internal.build.Config;
import org.eclipse.pde.internal.build.builder.ModelBuildScriptGenerator;

/**
 * This actor generates an ant script that builds various aspecs of a plugin.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PluginScriptGenerator extends ScriptGenerator
{
	public static final String ACTOR_ID = "plugin.script.generator";
	public static final String ATTRIBUTE_GENERATED_PLUGIN_SCRIPT = "generated.plugin.script";

	private static final String COMPILER_ARGS_PREFIX = "javaCompiler.";
	private static final String COMPILER_ARGS_SUFFIX = ".args";

	class MyModelBuildScriptGenerator extends ModelBuildScriptGenerator
	{
		boolean isCustom() throws CoreException
		{
			return "true".equalsIgnoreCase((String)getBuildProperties().get(PROPERTY_CUSTOM));
		}
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		Map<String,String> properties = ctx.getProperties();
		IPath scriptPath = getScriptPath(ctx);
		String scriptName = scriptPath.lastSegment();
		IPath buildFolder = scriptPath.removeLastSegments(1);

		AbstractScriptGenerator.setConfigInfo(Config.genericConfig().toString(","));
		MyModelBuildScriptGenerator generator = new MyModelBuildScriptGenerator();
		initScript(generator, ctx);

		generator.setSignJars(getBooleanProperty(properties, PROPERTY_SIGN_JARS, false));
		ComponentIdentifier cid = ctx.getAction().getCSpec().getComponentIdentifier();
		IVersion version = cid.getVersion();
		generator.setModelId(cid.getName(), version == null ? null : version.toString());

		File baseDir = new Path(generator.getModel().getLocation()).toFile();
		boolean custom = generator.isCustom();
		File movedScriptFile;
		if(custom)
			movedScriptFile = new File(baseDir, DEFAULT_BUILD_SCRIPT_FILENAME);
		else
		{
			generator.setBuildScriptFileName(scriptName);
			movedScriptFile = new File(baseDir, scriptName);
		}

		ArrayList<File> javaCompilerArgsFiles = null; 
		try
		{
			generator.generate();

			// The generator puts stuff right into the source directory. We don't like
			// that. There's not much we can do about the actual generation so instead,
			// we move them to the PROPERTY_BUILD_TEMP directory after the fact.
			//
			String[] files = baseDir.list();
			if(files == null)
				//
				// No files here? That's rather odd.
				//
				throw new FileNotFoundException("Not a directory: " + baseDir);

			for(String rootFile : baseDir.list())
			{
				if(rootFile.startsWith(COMPILER_ARGS_PREFIX) && rootFile.endsWith(COMPILER_ARGS_SUFFIX))
				{
					if(javaCompilerArgsFiles == null)
						javaCompilerArgsFiles = new ArrayList<File>();
					javaCompilerArgsFiles.add(new File(baseDir, rootFile));
				}
			}

			// Move generated files into a new location
			//
			File buildDir = buildFolder.toFile();
			if(javaCompilerArgsFiles != null)
			{
				for(File javaCompilerArgsFile : javaCompilerArgsFiles)
					FileUtils.copyFile(javaCompilerArgsFile, buildDir, javaCompilerArgsFile.getName(), null);

				// While moving the script, we also replace pointers to moved files
				//
				LineNumberReader reader = null;
				PrintWriter writer = null;
				File targetScriptFile = new File(buildDir, scriptName);
				try
				{
					reader = new LineNumberReader(new FileReader(movedScriptFile));
					writer = new PrintWriter(targetScriptFile);
					String line;
					while((line = reader.readLine()) != null)
						writer.println(line.replace(
							"${basedir}/" + COMPILER_ARGS_PREFIX,
							"${" + PROPERTY_BUILD_TEMP + "}/" + COMPILER_ARGS_PREFIX));
				}
				finally
				{
					IOUtils.close(reader);
					IOUtils.close(writer);
				}
			}
			else
			{
				// We didn't have any compiler args files so simply move the script file. No
				// replacements are needed when this is the case.
				//
				FileUtils.copyFile(movedScriptFile, buildDir, scriptName, null);
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			// Remove files that are now moved and manipulated.
			//
			if(javaCompilerArgsFiles != null)
				for(File javaCompilerArgsFile : javaCompilerArgsFiles)
					javaCompilerArgsFile.delete();
			
			if(!custom)
				movedScriptFile.delete();
		}
		return Status.OK_STATUS;
	}
}
