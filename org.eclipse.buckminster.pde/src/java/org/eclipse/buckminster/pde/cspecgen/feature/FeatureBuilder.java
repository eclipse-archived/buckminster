/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.cspecgen.feature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.PropertiesParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.pde.cspecgen.PDEBuilder;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A CSpec builder that creates a cspec from a Eclipse plugin.xml file.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
public class FeatureBuilder extends PDEBuilder
{
	public static void addRootsPermissions(Map<String, String> hints, String perm, String filesAndFolders, String[] spec)
	{
		StringBuilder bld = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(filesAndFolders, ",");
		while(tokenizer.hasMoreTokens())
		{
			if(bld.length() > 0)
				bld.append(',');
			bld.append(tokenizer.nextToken().trim());
			bld.append(':');
			bld.append(perm);
		}

		if(bld.length() > 0)
		{
			String key = (spec != null && spec.length >= 3)
					? String.format("%s/%s.%s.%s", HINT_PERMISSIONS, spec[0], spec[1], spec[2])
					: HINT_PERMISSIONS;

			String permissions = hints.get(key);
			if(permissions != null)
			{
				bld.append(',');
				bld.append(permissions);
			}
			hints.put(key, bld.toString());
		}
	}

	@Override
	public int compareTo(IResolutionBuilder other)
	{
		return 1;
	}

	@Override
	public String getComponentTypeID()
	{
		return IComponentType.ECLIPSE_FEATURE;
	}

	@Override
	protected void parseFile(CSpecBuilder cspecBuilder, boolean forResolutionAidOnly, ICatalogReader reader,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		IFeature feature;
		try
		{
			IFeatureModel model;
			if(reader instanceof EclipsePlatformReader)
			{
				model = ((EclipsePlatformReader)reader).getFeatureModel();
				MonitorUtils.worked(monitor, 40);
			}
			else
			{
				try
				{
					model = reader.readFile("feature.xml", new FeatureModelReader(), MonitorUtils.subMonitor(monitor,
							40));
				}
				catch(FileNotFoundException e)
				{
					model = null;
				}
			}
			if(model == null)
				throw new MissingCSpecSourceException(reader.getProviderMatch());
			setModel(model);
			feature = model.getFeature();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}

		Map<String, String> buildProperties = null;
		if(!forResolutionAidOnly)
		{
			try
			{
				buildProperties = reader.readFile("build.properties", new PropertiesParser(), MonitorUtils.subMonitor(
						monitor, 40));
			}
			catch(FileNotFoundException e)
			{
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		CSpecFromSource generator = new CSpecFromSource(cspecBuilder, reader, feature, buildProperties);
		generator.generate(MonitorUtils.subMonitor(monitor, 20));
		monitor.done();
	}
}
