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

import java.lang.reflect.Method;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * A Builder that emits the location of all components that are contained in, or referenced by, the
 * current project.
 * @author Thomas Hallgren
 */
public class LocationEmitter extends PropertiesEmitter
{
	public static final String ARG_PURPOSE = "purpose";
	public static final String ARG_FORMAT_LOCATION = "format.location";
	public static final String ARG_FORMAT_ARTIFACTS = "format.artifacts";

	public static final Format FORMAT_LOCATION = new MessageFormat("bm.location.{0}");
	public static final Format FORMAT_LOCATION_ARTIFACT = new MessageFormat("bm.artifacts.{0}.{1}");

	@Override
	protected void addFormatters()
	{
		this.addFormat(ARG_FORMAT_LOCATION, FORMAT_LOCATION);
		this.addFormat(ARG_FORMAT_ARTIFACTS, FORMAT_LOCATION_ARTIFACT);
	}

	@Override
	protected void appendProperties() throws CoreException
	{
		try
		{
			IModelCache cache = new ModelCache();
			CSpec cspec = WorkspaceInfo.getCSpec(this.getProject());
			String attr = this.getArgument(ARG_PURPOSE);
			Set<String> attrs = attr == null ? Collections.<String>emptySet() : Collections.singleton(attr);
			this.appendComponentProperties(cspec, attrs, cache, new HashSet<ComponentIdentifier>());
		}
		catch(MissingComponentException e)
		{
		}
	}

	private void appendComponentProperties(CSpec cspec, Set<String> attributes, IModelCache cache, HashSet<ComponentIdentifier> seenIds) throws CoreException
	{
		ComponentIdentifier cid = cspec.getComponentIdentifier();
		if(seenIds.contains(cid))
			return;

		IPath location = cspec.getComponentLocation();

		String componentName = cspec.getName();
		if(location.toFile().isFile())
		{
			this.addProperty(ARG_FORMAT_LOCATION, new String[] { componentName }, this.formatPath(location.removeLastSegments(1)) );
			this.addProperty(ARG_FORMAT_ARTIFACTS, new String[] { componentName, "default" }, location.lastSegment());
		}
		else
		{
			IResource resource = WorkspaceInfo.getResource(cid);			
			if(resource instanceof IProject)
			{
				// If this is a java project with a default output folder, then emitt that as a default
				// artifact.
				//
				IPath dfltOutput = getDefaultOutputFolder((IProject)resource);
				if(dfltOutput != null)
					this.addProperty(ARG_FORMAT_ARTIFACTS, new String[] { componentName, "default" }, dfltOutput.toOSString());
			}
			this.addProperty(ARG_FORMAT_LOCATION, new String[] { componentName }, this.formatPath(location) );
		}

		// Emit properties of all dependencies
		//
		boolean prune = !attributes.isEmpty();
		Attribute[] attrs = prune ? cspec.getAttributes(attributes) : null;

		for(QualifiedDependency dep : cspec.getQualifiedDependencies(attrs, prune))
		{
			CSpec childSpec = cache.findCSpec(cspec, dep.getRequest());
			appendComponentProperties(childSpec, dep.getAttributeNames(), cache, seenIds);
		}
	}
	private static boolean s_stateKnown = false;
	private static Method s_getDefaultOutputFolder;

	public static synchronized boolean isJDTPresent()
	{
		if(!s_stateKnown)
		{
			Bundle bundle = Platform.getBundle("org.eclipse.buckminster.jdt");
			if(bundle == null)
			{
				s_stateKnown = true;
				return false;
			}

			try
			{
				Class<?> classpathEmitterClass = bundle.loadClass("org.eclipse.buckminster.jdt.internal.ClasspathEmitter");
				s_getDefaultOutputFolder = classpathEmitterClass.getMethod("getDefaultOutputFolder", new Class[] { IProject.class });
			}
			catch(Exception e)
			{
			}
			s_stateKnown = true;
		}
		return s_getDefaultOutputFolder != null;
	}

	public static IPath getDefaultOutputFolder(IProject project)
	throws CoreException
	{
		if(!isJDTPresent())
			return null;

		try
		{
			return (IPath)s_getDefaultOutputFolder.invoke(null, new Object[] { project });
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

}
