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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.reader.LocalReaderType;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.cspecgen.PDEBuilder;
import org.eclipse.buckminster.pde.internal.EclipseImportReader;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.pde.internal.model.ExternalBuildModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.pde.internal.model.ExternalExtensionsModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundleModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModelBase;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;
import org.osgi.framework.Constants;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF, plugin.xml and fragment.xml files.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class BundleBuilder extends PDEBuilder implements IBuildPropertiesConstants
{
	private static IPath s_platformPluginsFolder = Path.fromOSString(
			TargetPlatform.getPlatformInstallLocation().getAbsolutePath()).append(IPDEConstants.PLUGINS_FOLDER);

	public static IPluginModelBase parsePluginModelBase(ICatalogReader reader, boolean forResolutionAidOnly,
			IProgressMonitor monitor) throws CoreException
	{
		if(reader instanceof EclipsePlatformReader)
		{
			MonitorUtils.complete(monitor);
			try
			{
				IPluginModelBase pluginModelBase = ((EclipsePlatformReader)reader).getPluginModelBase();
				if(forResolutionAidOnly)
					return pluginModelBase;

				String location = pluginModelBase.getInstallLocation();
				if(s_platformPluginsFolder.isPrefixOf(Path.fromOSString(location)))
					return pluginModelBase;

				File locationFile = new File(location);
				if(locationFile.isFile())
					return pluginModelBase;

				// Self hosted from workspace. We can (and must) build this one from
				// source
				//
				reader = (ICatalogReader)LocalReaderType.getReader(locationFile.toURI().toURL(), null); //$NON-NLS-1$
			}
			catch(IllegalStateException e)
			{
				throw new MissingCSpecSourceException(reader.getProviderMatch());
			}
			catch(MalformedURLException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}

		monitor.beginTask(null, 7000);
		try
		{
			// This is an OSGi style plugin. Most of the dependencies and
			// other
			// info that we're interested in is stored in the
			// META-INF/MANIFEST.MF
			// file.
			//
			try
			{
				boolean fragment = false;
				BundleModel model = new ExternalBundleModel();
				loadModel(reader, BUNDLE_FILE, model, MonitorUtils.subMonitor(monitor, 1000));
				if(model.getBundle().getHeader(Constants.BUNDLE_SYMBOLICNAME) == null)
					throw new FileNotFoundException(Messages.not_an_OSGi_manifest);

				fragment = model.isFragmentModel();
				BundlePluginModelBase bmodel = fragment
						? new BundleFragmentModel()
						: new BundlePluginModel();
				bmodel.setBundleModel(model);
				bmodel.setEnabled(true);

				if(forResolutionAidOnly)
					return bmodel;

				// Extensions etc. that are not part of the OSGi can still be
				// found in the plugin.xml or fragment.xml
				//
				try
				{
					String extensionsFile = fragment
							? FRAGMENT_FILE
							: PLUGIN_FILE;
					ExternalExtensionsModel extModel = new ExternalExtensionsModel();
					loadModel(reader, extensionsFile, extModel, MonitorUtils.subMonitor(monitor, 1000));
					bmodel.setExtensionsModel(extModel);
				}
				catch(FileNotFoundException e)
				{
				}

				try
				{
					IBuildModel buildModel = new ExternalBuildModel();
					loadModel(reader, BUILD_PROPERTIES_FILE, buildModel, MonitorUtils.subMonitor(monitor, 1000));
					bmodel.setBuildModel(buildModel);
				}
				catch(FileNotFoundException e)
				{
				}
				return bmodel;
			}
			catch(FileNotFoundException e)
			{
			}

			try
			{
				ExternalPluginModel pm = new ExternalPluginModel();
				loadModel(reader, PLUGIN_FILE, pm, MonitorUtils.subMonitor(monitor, 1000));
				return pm;
			}
			catch(FileNotFoundException e1)
			{
			}

			try
			{
				ExternalFragmentModel pm = new ExternalFragmentModel();
				loadModel(reader, FRAGMENT_FILE, pm, MonitorUtils.subMonitor(monitor, 1000));
				return pm;
			}
			catch(FileNotFoundException e1)
			{
				throw new MissingCSpecSourceException(reader.getProviderMatch());
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void loadModel(ICatalogReader reader, String file, final IModel model, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		try
		{
			reader.readFile(file, new IStreamConsumer<Object>()
			{
				public Object consumeStream(IComponentReader fileReader, String streamName, InputStream stream,
						IProgressMonitor mon) throws CoreException
				{
					int len;
					byte[] buf = new byte[4096];
					AccessibleByteArrayOutputStream bld = new AccessibleByteArrayOutputStream();
					try
					{
						while((len = stream.read(buf)) > 0)
						{
							for(int idx = 0; idx < len; ++idx)
							{
								byte b = buf[idx];
								if(b != '\r')
									bld.write(b);
							}
						}
					}
					catch(IOException e)
					{
						throw BuckminsterException.wrap(e);
					}
					model.load(bld.getInputStream(), true);
					return null;
				}
			}, monitor);
		}
		catch(FileNotFoundException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public String getComponentTypeID()
	{
		return IComponentType.OSGI_BUNDLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder#createResolution(org.eclipse.buckminster.core.reader
	 * .IComponentReader, org.eclipse.buckminster.core.cspec.builder.CSpecBuilder,
	 * org.eclipse.buckminster.opml.builder.OPMLBuilder)
	 */
	@Override
	protected Resolution createResolution(IComponentReader reader, CSpecBuilder cspecBuilder, OPMLBuilder opmlBuilder)
			throws CoreException
	{
		if(reader instanceof EclipseImportReader)
		{
			EclipseImportReader eclipseImportReader = (EclipseImportReader)reader;
			return super.createResolution(reader, cspecBuilder, opmlBuilder, eclipseImportReader.isUnpack());
		}
		return super.createResolution(reader, cspecBuilder, opmlBuilder);
	}

	@Override
	protected void parseFile(CSpecBuilder cspecBuilder, boolean forResolutionAidOnly, ICatalogReader reader,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			IPluginBase pluginBase = parsePluginModelBase(reader, forResolutionAidOnly,
					MonitorUtils.subMonitor(monitor, 50)).getPluginBase();
			cspecBuilder.setName(pluginBase.getId());
			cspecBuilder.setComponentTypeID(getComponentTypeID());
			cspecBuilder.setVersion(pluginBase.getVersion(), IVersionType.OSGI);
			if(forResolutionAidOnly)
				return;

			IPluginModelBase model = pluginBase.getPluginModel();
			setModel(model);

			IBuildModel buildModel = model.getBuildModel();
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
