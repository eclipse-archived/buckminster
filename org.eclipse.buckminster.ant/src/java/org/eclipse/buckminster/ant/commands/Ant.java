/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.commands;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.ant.internal.build.AntBuilder;
import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class Ant extends AbstractCommand
{
	private static final OptionDescriptor s_basedir = new OptionDescriptor(null, "basedir", OptionValueType.REQUIRED);
	private static final OptionDescriptor s_buildfile = new OptionDescriptor('b', "buildfile", OptionValueType.REQUIRED);
	private static final OptionDescriptor s_property = new OptionDescriptor('D', null, OptionValueType.REQUIRED);
	private static final OptionDescriptor s_propertyFile = new OptionDescriptor('p', "propertyfile", OptionValueType.REQUIRED);

	// set if a clean build is requested
	//
	private IPath m_scriptFile;

	private IPath m_baseDirPath;

	private final Map<String, String> m_properties = new HashMap<String, String>();

	private final ArrayList<String> m_targets = new ArrayList<String>();

	public void addProperty(String propName, String propValue)
	{
		m_properties.put(propName, propValue);
	}

	public void addProperties(Map<String,String> properties)
	{
		m_properties.putAll(properties);
	}

	public void addBuildTarget(String target)
	{
		m_targets.add(target);
	}

	public void setScriptFile(IPath scriptFile)
	{
		m_scriptFile = scriptFile;
	}

	public void setBaseDir(IPath baseDir)
	{
		m_baseDirPath = baseDir;
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		monitor.beginTask(null, 2);
		monitor.subTask("Building ant targets");
		try
		{
			String[] targets = m_targets.toArray(new String[m_targets.size()]);
			Object builder = AntBuilder.createInternalAntBuilder();
			MonitorUtils.worked(monitor, 1);
			monitor.subTask(TextUtils.toCommaSeparatedList(targets));
			AntBuilder.invokeInternalAntBuilder(builder, m_scriptFile, m_baseDirPath, targets, m_properties, null, System.out, System.err);
			MonitorUtils.worked(monitor, 1);
		}
		catch(CoreException e)
		{
			AntPlugin.getLogger().error(e.getMessage(), e);
			throw e;
		}
		finally
		{
			monitor.done();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		appendHere.add(s_buildfile);
		appendHere.add(s_property);
		appendHere.add(s_propertyFile);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(s_property))
		{
			String pair = option.getValue();
			int eqIdx = pair.indexOf('=');
			if(eqIdx < 0 || eqIdx >= pair.length())
				throw new UsageException("Malformed property", true);
			this.addProperty(pair.substring(0, eqIdx).trim(), pair.substring(eqIdx + 1).trim());
			return;
		}

		if(option.is(s_propertyFile))
		{
			File propFile = new File(option.getValue());
			InputStream inputStream = null;
			try
			{
				inputStream = new BufferedInputStream(new FileInputStream(propFile));
				this.addProperties(new BMProperties(inputStream));
				return;
			}
			catch(IOException e)
			{
				throw new UsageException("Unable to open property file: " + e.toString());
			}
			finally
			{
				IOUtils.close(inputStream);
			}
		}
		
		if(option.is(s_buildfile))
		{
			File buildFile = new File(option.getValue());
			if(!(buildFile.isFile() && buildFile.canRead()))
				throw new UsageException("Unable to open build file: " + buildFile);
			this.setScriptFile(Path.fromOSString(buildFile.getAbsolutePath()));
			return;
		}

		if(option.is(s_basedir))
		{
			File baseDir = new File(option.getValue());
			if(!baseDir.isDirectory())
				throw new UsageException("Unable to open directory : " + baseDir);
			this.setBaseDir(Path.fromOSString(baseDir.getAbsolutePath()));
			return;
		}
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		for(String arg : unparsed)
			this.addBuildTarget(arg);
	}
}
