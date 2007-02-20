/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.internal.build;

import java.io.PrintStream;
import java.util.Map;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 */
public class StrictAntBuilder extends AbstractAntBuilder
{
	@Override
	protected IProject[] doBuild(int kind, Map<String,String> args, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			AntRunner antRunner = new AntRunner();
			antRunner.setBuildFileLocation(this.getScriptFile(args).getLocation().toFile().getAbsolutePath());

			Map<String, String> bmProperties = this.getFixedProperties(args);

			// only set 'basedir' if given; it will otherwise default to the
			// script file dir location
			//
			// TODO: maybe we need to use setArguments(-Dbasedir=foo) instead?
			//
			IPath baseDir = this.getBaseDir(args);
			if (baseDir != null)
				bmProperties.put("basedir", baseDir.toOSString());

			// only set the 'kind' property if a propname is given
			//
			String kindPropName = AbstractBuckminsterBuilder.getValue(args, ARG_BUILD_KIND_PROPERTY_KEY);
			if (kindPropName != null)
				bmProperties.put(kindPropName, AbstractBuckminsterBuilder.kindToString(kind));

			antRunner.addUserProperties(bmProperties);

			String target = this.getTarget(args, kind);

			if (target != null)
				antRunner.setExecutionTargets(new String[] { target.toLowerCase() });

			antRunner.addBuildLogger("org.eclipse.buckminster.ant.support.AntBuildLogger");
			
			PrintStream savedSysOut = System.out;
			System.setOut(this.getOutStream());
			try
			{
				antRunner.run(MonitorUtils.subMonitor(monitor, Integer.MAX_VALUE));
			}
			finally
			{
				System.setOut(savedSysOut);
			}

			return null;
		}
		catch (Exception ce)
		{
			throw BuckminsterException.wrap(ce);
		}
	}
}
