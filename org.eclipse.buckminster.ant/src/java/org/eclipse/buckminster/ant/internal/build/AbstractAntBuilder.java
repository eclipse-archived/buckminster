/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.internal.build;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/*
 * @author kolwing
 */
public class AbstractAntBuilder extends AbstractBuckminsterBuilder implements AntBuilderConstants
{
	private IFile m_scriptFile;

	/**
	 * Returns the script file
	 * @param args The map of arguments that where passed to the build.
	 * @return The script file.
	 */
	protected IFile getScriptFile(Map<String, String> args) throws CoreException
	{
		if(m_scriptFile == null)
		{
			// script name must always be relative to project root
			//
			String scriptFile = getValue(args, ARG_SCRIPT_FILE_KEY);
			if(scriptFile == null)
				scriptFile = DEFAULT_SCRIPT_FILE;
			IPath relativeScriptFilePath = new Path(scriptFile);
			if(relativeScriptFilePath.isAbsolute())
				throw new BuckminsterException("The script file name must be relative to the project root: "
					+ scriptFile);
			m_scriptFile = this.getProject().getFile(relativeScriptFilePath);
			this.notifyOnChangedResources(new IResource[] { m_scriptFile });

		}
		return m_scriptFile;
	}

	/**
	 * The path to use as <code>basedir</code> in the ant build.
	 * @param args The map of arguments that where passed to the build.
	 * @return The basedir of the build or <code>null</code> if not set.
	 */
	protected IPath getBaseDir(Map<String, String> args)
	{
		String baseDir = getValue(args, ARG_OVERRIDE_BASEDIR_KEY);
		IPath baseDirPath = null;
		if(baseDir != null)
		{
			baseDirPath = new Path(baseDir);
			if(!baseDirPath.isAbsolute())
				baseDirPath = this.getProject().getLocation().append(baseDirPath);
		}
		return baseDirPath;
	}

	/**
	 * Returns the target for a specific <code>kind</code> of build or <code>null</code> if no
	 * target has been specificed for the <code>kind</code>.
	 * @param args The map of arguments that where passed to the build.
	 * @param kind The
	 *            {@link org.eclipse.core.resources.IncrementalProjectBuilder IncrementalProjectBuilder}
	 *            build kind.
	 * @return The name of the target.
	 */
	protected String getTarget(Map<String, String> args, int kind)
	{
		String target = null;
		if(kind == AUTO_BUILD)
			target = getValue(args, ARG_AUTO_KIND_TARGET_KEY);
		else if(kind == CLEAN_BUILD)
		{
			target = getValue(args, ARG_CLEAN_KIND_TARGET_KEY);
			if(target == null)
				target = DEFAULT_CLEAN_KIND_TARGET;
		}
		else if(kind == FULL_BUILD)
			target = getValue(args, ARG_FULL_KIND_TARGET_KEY);
		else if(kind == INCREMENTAL_BUILD)
			target = getValue(args, ARG_INCREMENTAL_KIND_TARGET_KEY);
		return target;
	}

	/**
	 * Returns a new map with fixed properties that are guaranteed not to change between each build.
	 * The method will return a new map for each call and it is ok if the caller wishes to add more
	 * entries to that map.
	 * @param args The map of arguments that where passed to the build.
	 * @return A map of fixed properties.
	 */
	protected Map<String, String> getFixedProperties(Map<String, String> args)
	{
		Map<String, String> props = new HashMap<String, String>();

		// only set the 'component name' property if a propname is given
		//
		String componentPropName = getValue(args, ARG_COMPONENT_NAME_PROPERTY_KEY);
		if(componentPropName != null)
			props.put(componentPropName, this.getProject().getName());
		return props;
	}

	@Override
	protected void resourcesChangeNotification(IResource[] changedResources)
	{
		// should only be the scriptfile at this time...
		// if someone did something to the build script, force a full build
		//
		for(IResource r : changedResources)
			if(r.equals(m_scriptFile))
			{
				this.forgetLastBuiltState();
				this.scriptFileChanged();
			}
	}

	protected void scriptFileChanged()
	{
		// noop
	}
}
