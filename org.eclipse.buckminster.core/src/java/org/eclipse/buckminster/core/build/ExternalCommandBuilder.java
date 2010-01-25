/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.StreamPump;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.IValueVariable;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 */
public class ExternalCommandBuilder extends AbstractBuckminsterBuilder implements ExternalCommandBuilderConstants
{
	private static Pattern s_extractQuotedItemPattern = Pattern.compile("^\"(.*?)(?<!\\\\)(\"|$)"); //$NON-NLS-1$

	private static Pattern s_whitespaceAndQuotationMarkPattern = Pattern.compile("\\s\""); //$NON-NLS-1$

	public static String getCommandLine(LauncherDefinition[] launcherDefs, String defToUse, String addArgs,
			IProject project, int kind) throws CoreException
	{
		IStringVariableManager svm = VariablesPlugin.getDefault().getStringVariableManager();

		IValueVariable projLocVar = svm.newValueVariable("buckminster.project.location", ""); //$NON-NLS-1$ //$NON-NLS-2$
		projLocVar.setValue(project.getLocation().toOSString());
		IValueVariable buildTypeVar = svm.newValueVariable("buckminster.build.type", ""); //$NON-NLS-1$ //$NON-NLS-2$
		buildTypeVar.setValue(AbstractBuckminsterBuilder.kindToString(kind));
		IValueVariable[] variables = new IValueVariable[] { projLocVar, buildTypeVar };
		svm.addVariables(variables);

		try
		{
			String rawCommandLine = null;
			try
			{
				if(defToUse == null || defToUse.length() == 0)
					throw BuckminsterException.fromMessage(Messages.Missing_value_for_definition_to_use);

				String resolvedDefsToUse = svm.performStringSubstitution(defToUse);

				Logger logger = CorePlugin.getLogger();
				if(logger.isDebugEnabled())
				{
					logger.debug("Definition to use, before: '%s'", defToUse); //$NON-NLS-1$
					logger.debug("Definition to use, after: '%s'", resolvedDefsToUse); //$NON-NLS-1$
				}

				for(LauncherDefinition ld : launcherDefs)
				{
					if(Pattern.matches(ld.getPattern(), resolvedDefsToUse))
					{
						StringBuilder sb = new StringBuilder(ld.getCommandLine());
						if(addArgs != null && addArgs.length() > 0)
							sb.append(" ").append(addArgs); //$NON-NLS-1$
						rawCommandLine = sb.toString();
						break;
					}
				}

				String commandLine = null;
				if(rawCommandLine != null)
				{
					commandLine = svm.performStringSubstitution(rawCommandLine);
					if(logger.isDebugEnabled())
					{
						logger.debug("Command line, before: '" + rawCommandLine + '\''); //$NON-NLS-1$
						logger.debug("Command line, after: '" + commandLine + '\''); //$NON-NLS-1$
					}
				}
				return commandLine;
			}
			catch(CoreException ce)
			{
				throw ce;
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		finally
		{
			svm.removeVariables(variables);
		}
	}

	public static String getDefinitionToUse(Map<String, String> args)
	{
		String s = AbstractBuckminsterBuilder.getValue(args, LAUNCHERDEFINITION_TO_USE_KEY);
		if(s == null)
			s = DEFAULT_LAUNCHERDEFINITION_TO_USE;
		return s;
	}

	public static LauncherDefinition[] getLauncherDefinitions(File launcherDefinitionsFile) throws CoreException
	{
		List<LauncherDefinition> defs = new ArrayList<LauncherDefinition>();
		if(launcherDefinitionsFile.exists())
		{
			BufferedReader br = null;
			try
			{
				br = new BufferedReader(new FileReader(launcherDefinitionsFile));
				String pattern;
				while((pattern = br.readLine()) != null)
				{
					String commandLine = br.readLine();
					if(commandLine == null)
						break;
					defs.add(new LauncherDefinition(pattern, commandLine));
				}
				br.close();
			}
			catch(Throwable t)
			{
				throw BuckminsterException.wrap(t);
			}
			finally
			{
				if(br != null)
					try
					{
						br.close();
					}
					catch(Exception e)
					{
						// noop
					}
			}
		}

		return defs.toArray(new LauncherDefinition[0]);
	}

	public static File getLauncherDefinitionsFile(Map<String, String> args, IProject project) throws CoreException
	{
		String launcherDefinitionsFile = AbstractBuckminsterBuilder.getValue(args, LAUNCHERDEFINITIONS_FILE_KEY);
		if(launcherDefinitionsFile == null || launcherDefinitionsFile.length() == 0)
			launcherDefinitionsFile = DEFAULT_LAUNCHERDEFINITIONS_FILE;

		// file name must always be relative to project root
		//
		IPath relativePath = new Path(launcherDefinitionsFile);
		if(relativePath.isAbsolute())
			throw BuckminsterException.fromMessage(NLS.bind(
					Messages.Launcher_definitions_file_name_not_relative_to_project_root_0, launcherDefinitionsFile));
		IPath fullPath = project.getLocation().append(relativePath);

		return fullPath.toFile();
	}

	@Override
	protected IProject[] doBuild(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		File launcherDefinitionsFile = getLauncherDefinitionsFile(args, this.getProject());
		LauncherDefinition[] launcherDefinitions = getLauncherDefinitions(launcherDefinitionsFile);
		String defToUse = getDefinitionToUse(args);
		String addArgs = AbstractBuckminsterBuilder.getValue(args, ADDITIONAL_ARGUMENTS_KEY);
		String fullCommandLine = getCommandLine(launcherDefinitions, defToUse, addArgs, this.getProject(), kind);

		if(fullCommandLine == null)
			throw BuckminsterException.fromMessage(Messages.Could_not_resolve_to_a_command_line);

		Logger logger = CorePlugin.getLogger();
		logger.info(NLS.bind(Messages.Command_line_0, fullCommandLine));

		String[] splitCommandLine = this.splitCommandLine(fullCommandLine);

		if(logger.isDebugEnabled())
		{
			logger.debug("Split cmd line:"); //$NON-NLS-1$
			for(String s : splitCommandLine)
				logger.debug("=> " + s); //$NON-NLS-1$
		}

		ProcessBuilder pb = new ProcessBuilder(splitCommandLine);
		pb.redirectErrorStream(true);
		pb.directory(launcherDefinitionsFile.getParentFile());
		try
		{
			Process p = pb.start();
			StreamPump thrStdOut = new StreamPump(p.getInputStream(), this.getOutStream());
			thrStdOut.start();
			thrStdOut.join();
			int exitValue = p.waitFor();
			if(exitValue != 0)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.External_command_0_exited_with_1,
						fullCommandLine, Integer.valueOf(exitValue)));
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}

		return null;
	}

	private String[] splitCommandLine(String arg)
	{
		String trimmedArg = arg.trim();
		if(trimmedArg.length() == 0)
			return Trivial.EMPTY_STRING_ARRAY;

		Matcher wsAndQMmatcher = s_whitespaceAndQuotationMarkPattern.matcher(trimmedArg);

		List<String> parsed = new ArrayList<String>();

		if(trimmedArg.startsWith("\"")) //$NON-NLS-1$
		{
			// now extract the first item from the string (skipping the citation
			// marks)
			// recursively parse the rest of the line (if any)
			//
			Matcher extractQIMatcher = s_extractQuotedItemPattern.matcher(trimmedArg);
			if(extractQIMatcher.find())
			{
				parsed.add(extractQIMatcher.group(1));
				int end = extractQIMatcher.end(1);
				if(trimmedArg.length() > end)
					Collections.addAll(parsed, this.splitCommandLine(trimmedArg.substring(end + 1)));
			}
			else
				throw new RuntimeException(Messages.Unexpected_non_match);
		}
		else if(wsAndQMmatcher.find())
		{
			// divvy it up into two parts
			// the left part can be split on simple whitespace only
			// the right part should be recursively parsed
			//
			int end = wsAndQMmatcher.end();
			String left = trimmedArg.substring(0, wsAndQMmatcher.end() - 1);
			String right = trimmedArg.substring(end - 1);
			Collections.addAll(parsed, left.split("\\s")); //$NON-NLS-1$
			Collections.addAll(parsed, this.splitCommandLine(right));
		}
		else
		{
			// no attempts to guard embedded whitespace exists, just split it
			// all
			//
			Collections.addAll(parsed, trimmedArg.split("\\s")); //$NON-NLS-1$
		}

		return parsed.toArray(new String[parsed.size()]);
	}

}
