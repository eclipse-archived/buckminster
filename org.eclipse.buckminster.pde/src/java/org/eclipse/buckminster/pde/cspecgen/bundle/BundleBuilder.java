/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.cspecgen.bundle;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.cspecgen.PDEBuilder;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.pde.internal.model.ExternalBuildModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.pde.internal.model.ExternalExtensionsModel;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundleModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModelBase;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF, plugin.xml and fragment.xml
 * files.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class BundleBuilder extends PDEBuilder implements IBuildPropertiesConstants
{
	@SuppressWarnings("serial")
	public static IPluginModelBase parsePluginModelBase(ICatalogReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		if(reader instanceof EclipsePlatformReader)
		{
			MonitorUtils.complete(monitor);
			return ((EclipsePlatformReader)reader).getPluginModelBase();
		}

		monitor.beginTask(null, 7000);
		try
		{
			IPluginModelBase modelBase = null;

			if(reader.exists(BUNDLE_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				// This is an OSGi style plugin. Most of the dependencies and
				// other
				// info that we're interested in is stored in the
				// META-INF/MANIFEST.MF
				// file.
				//
				BundleModel model = new ExternalBundleModel();
				loadModel(reader, BUNDLE_FILE, model, MonitorUtils.subMonitor(monitor, 1000));
				final boolean fragment = model.isFragmentModel();
				IBundlePluginModelBase bmodel = fragment ? new BundleFragmentModel()
					: new BundlePluginModel();

				bmodel.setEnabled(true);
				bmodel.setBundleModel(model);

				// Extensions etc. that are not part of the OSGi can still be
				// found in the plugin.xml or fragment.xml
				//
				String extensionsFile = fragment ? FRAGMENT_FILE : PLUGIN_FILE;
				if(reader.exists(extensionsFile, MonitorUtils.subMonitor(monitor, 1000)))
				{
					ExternalExtensionsModel extModel = new ExternalExtensionsModel();
					loadModel(reader, extensionsFile, extModel, MonitorUtils.subMonitor(monitor, 1000));
					bmodel.setExtensionsModel(extModel);
				}
				else
					MonitorUtils.worked(monitor, 1000);
				modelBase = bmodel;
			}
			else if(reader.exists(PLUGIN_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				modelBase = new ExternalPluginModel();
				loadModel(reader, PLUGIN_FILE, modelBase, MonitorUtils.subMonitor(monitor, 2000));
			}
			else if(reader.exists(FRAGMENT_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				modelBase = new ExternalFragmentModel();
				loadModel(reader, FRAGMENT_FILE, modelBase, MonitorUtils.subMonitor(monitor, 1000));
			}

			if(modelBase == null)
				throw new BuckminsterException(reader.getNodeQuery().getComponentRequest() + ": Could not find " + BUNDLE_FILE + ", " + PLUGIN_FILE + " or "
					+ FRAGMENT_FILE);

			if(reader.exists(BUILD_PROPERTIES_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				IBuildModel buildModel = new ExternalBuildModel();
				loadModel(reader, BUILD_PROPERTIES_FILE, buildModel, MonitorUtils.subMonitor(monitor, 1000));
				if(modelBase instanceof BundlePluginModelBase)
					((BundlePluginModelBase)modelBase).setBuildModel(buildModel);
			}
			else
				MonitorUtils.worked(monitor, 1000);
			return modelBase;
		}
		finally
		{
			monitor.done();
		}
	}

	private static void loadModel(ICatalogReader reader, String file, final IModel model,
		IProgressMonitor monitor) throws CoreException
	{
		try
		{
			reader.readFile(file, new IStreamConsumer<Object>()
			{
				public Object consumeStream(IComponentReader fileReader, String streamName,
					InputStream stream, IProgressMonitor mon) throws CoreException
				{
					model.load(stream, true);
					return null;
				}
			}, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public String getCategory()
	{
		return KeyConstants.PLUGIN_CATEGORY;
	}

	@Override
	protected void parseFile(CSpecBuilder cspecBuilder, ICatalogReader reader, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			IPluginBase pluginBase = parsePluginModelBase(reader, MonitorUtils.subMonitor(monitor, 50)).getPluginBase();
			cspecBuilder.setName(pluginBase.getId());
			cspecBuilder.setCategory(KeyConstants.PLUGIN_CATEGORY);
			cspecBuilder.setVersion(pluginBase.getVersion(), VersionFactory.OSGiType.getId());

			IBuildModel buildModel = pluginBase.getPluginModel().getBuildModel();
			boolean fromProject = (buildModel != null);
			CSpecGenerator generator;
			if(fromProject)
				generator = new CSpecFromSource(cspecBuilder, reader, pluginBase, buildModel);
			else
				generator = new CSpecFromBinary(cspecBuilder, reader, pluginBase);
			generator.generate(monitor);
		}
		finally
		{
			monitor.done();
		}
	}
}
