/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.cspecgen.feature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.PropertiesParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.URLFileReader;
import org.eclipse.buckminster.core.reader.ZipArchiveReader;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
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
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class FeatureBuilder extends PDEBuilder
{
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
					model = reader.readFile("feature.xml", new FeatureModelReader(), MonitorUtils.subMonitor(monitor, //$NON-NLS-1$
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

		CSpecGenerator generator;
		if(reader instanceof ZipArchiveReader && ((ZipArchiveReader)reader).getFileReader() instanceof URLFileReader)
		{
			URI uri = ((URLFileReader)((ZipArchiveReader)reader).getFileReader()).getURI();
			generator = new CSpecFromBinary(cspecBuilder, reader, feature, uri);
		}
		else
		{
			Map<String, String> buildProperties = null;
			if(!forResolutionAidOnly)
			{
				try
				{
					buildProperties = reader.readFile(
							"build.properties", new PropertiesParser(), MonitorUtils.subMonitor( //$NON-NLS-1$
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
			generator = new CSpecFromSource(cspecBuilder, reader, feature, buildProperties);
		}
		generator.generate(MonitorUtils.subMonitor(monitor, 20));
		monitor.done();
	}
}
