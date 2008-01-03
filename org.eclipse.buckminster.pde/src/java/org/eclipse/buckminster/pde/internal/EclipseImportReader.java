/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.cspecgen.bundle.BundleBuilder;
import org.eclipse.buckminster.pde.internal.imports.FeatureImportOperation;
import org.eclipse.buckminster.pde.internal.imports.PluginImportOperation;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.IPluginEntry;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EclipseImportReader extends AbstractRemoteReader implements ISiteReader, IPDEConstants
{
	private EclipseImportBase m_base;

	private IModel m_model;

	protected EclipseImportReader(EclipseImportReaderType readerType, ProviderMatch rInfo)
	throws CoreException
	{
		super(readerType, rInfo);
		m_base = EclipseImportBase.obtain(rInfo.getRepositoryURI(),
			rInfo.getNodeQuery().getComponentRequest());

		IVersion version = rInfo.getVersionMatch().getVersion();
		m_model = m_base.isFeature() ? getFeatureModel(version, new NullProgressMonitor()) : getPluginModel(
			version, new NullProgressMonitor());

		if(m_model == null)
			throw new BuckminsterException("Unable to load model for " + m_base.getComponentName());
	}

	public List<IFragmentModel> getFragmentsFor(String pluginId) throws CoreException
	{
		EclipseImportReaderType rt = (EclipseImportReaderType)getReaderType();
		return rt.getFragmentsFor(m_base.getRemoteLocation(), getProviderMatch(), pluginId);
	}

	public IPluginModelBase getPluginModelBase(String pluginId, String version) throws CoreException
	{
		EclipseImportReaderType rt = (EclipseImportReaderType)getReaderType();
		return rt.getPluginModelBase(m_base.getRemoteLocation(), pluginId, version, getProviderMatch());
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1000);
		boolean isPlugin = m_model instanceof IPluginModelBase;

		if(m_base.isLocal())
			MonitorUtils.worked(monitor, 800);
		else
		{
			ProviderMatch ri = getProviderMatch();
			monitor.subTask("Retrieving " + destination + " from remote source");
			m_base = ((EclipseImportReaderType)getReaderType()).localizeContents(ri, isPlugin,
				MonitorUtils.subMonitor(monitor, 790));

			// Model is now local, so reset it.
			//
			IVersion version = ri.getVersionMatch().getVersion();
			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 10);
			m_model = isPlugin ? getPluginModel(version, subMon) : getFeatureModel(version, subMon);
			if(m_model == null)
				throw new BuckminsterException("Unable to load localized model for " + m_base.getComponentName());
		}

		IWorkspaceRunnable job = isPlugin ? getPluginImportJob((IPluginModelBase)m_model, destination)
			: getFeatureImportJob((IFeatureModel)m_model, destination);

		try
		{
			ResourcesPlugin.getWorkspace().run(job, MonitorUtils.subMonitor(monitor, 200));
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor)
	throws CoreException,
		IOException
	{
		monitor.beginTask(null, 1000);

		File destFile = null;
		OutputStream output = null;
		try
		{
			destFile = createTempFile();
			output = new FileOutputStream(destFile);

			MonitorUtils.worked(monitor, 200);
			if(m_base.isLocal())
			{
				InputStream input = null;
				try
				{
					File source = getInstallLocation();
					if(source.isDirectory())
						input = new FileInputStream(new File(source, fileName));
					else
					{
						ZipFile zipFile = new ZipFile(source);
						ZipEntry entry = zipFile.getEntry(fileName);
						if(entry == null)
							throw new FileNotFoundException(source.getName() + '!' + fileName);
						input = zipFile.getInputStream(entry);
					}
					FileUtils.copyFile(input, output, MonitorUtils.subMonitor(monitor, 800));
				}
				finally
				{
					IOUtils.close(input);
				}
			}
			else
			{
				ICatalogReader reader = createRemoteReader(m_model instanceof IPluginModelBase
					? PLUGINS_FOLDER : FEATURES_FOLDER, MonitorUtils.subMonitor(monitor, 200));
				try
				{
					final OutputStream out = output;
					reader.readFile(fileName, new IStreamConsumer<Object>()
					{
						public Object consumeStream(IComponentReader rdr, String streamName, InputStream stream,
							IProgressMonitor sub) throws IOException
						{
							FileUtils.copyFile(stream, out, sub);
							return null;
						}
					}, MonitorUtils.subMonitor(monitor, 600));
				}
				finally
				{
					reader.close();
				}
			}
			FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		}
		finally
		{
			IOUtils.close(output);
			if(destFile != null)
				destFile.delete();
		}
	}

	@SuppressWarnings("deprecation")
	IPluginModelBase getPluginModel(IVersion version, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, m_base.isLocal() ? 1000 : 2000);
		monitor.subTask("Downloading " + m_base.getComponentName());
		try
		{
			EclipseImportReaderType readerType = (EclipseImportReaderType)getReaderType();
			if(!m_base.isLocal())
			{
				// Create a local base.
				//
				try
				{
					IPluginEntry entry = null;
					for(IPluginEntry candidate : m_base.getPluginEntries(readerType, MonitorUtils.subMonitor(
						monitor, 1000)))
					{
						if(version == null
							|| version.toString().equals(
								candidate.getVersionedIdentifier().getVersion().toString()))
						{
							entry = candidate;
							break;
						}
					}
					if(entry == null)
						return null;

					ICatalogReader reader = createRemoteReader(PLUGINS_FOLDER, MonitorUtils.subMonitor(monitor,
						200));
					try
					{
						return BundleBuilder.parsePluginModelBase(reader, false, MonitorUtils.subMonitor(monitor,
							800));
					}
					finally
					{
						reader.close();
					}
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
			IPluginModelBase model = null;
			for(IPluginModelBase candidate : m_base.getPluginModels(readerType, MonitorUtils.subMonitor(
				monitor, 1000)))
			{
				if(version == null
					|| version.toString().equals(candidate.getBundleDescription().getVersion().toString()))
				{
					model = candidate;
					break;
				}
			}
			return model;
		}
		finally
		{
			monitor.done();
		}
	}

	private URL createRemoteComponentURL(String subDir) throws MalformedURLException, CoreException
	{
		return EclipseImportReaderType.createRemoteComponentURL(m_base.getRemoteLocation(), m_base.getComponentName(), getProviderMatch().getVersionMatch().getVersion(), subDir);
	}

	private ICatalogReader createRemoteReader(String subDir, IProgressMonitor monitor) throws CoreException, IOException
	{
		URL remoteURL = createRemoteComponentURL(subDir);
		ProviderMatch myRI = getProviderMatch();
		IComponentType ctype = myRI.getComponentType();
		Provider myP = myRI.getProvider();
		ProviderMatch match = new ProviderMatch(new Provider(myP.getSearchPath(), IReaderType.URL_ZIPPED, new String[] { ctype.getId() },
			myP.getVersionConverterDesc(), new Format(remoteURL.toString()), null, null, myP.getSpace(),
			myP.isMutable(), myP.hasSource(), null, null), ctype, myRI.getVersionMatch(), ProviderScore.PREFERRED,
			myRI.getNodeQuery());

		return (ICatalogReader)match.getReader(monitor);
	}

	private IWorkspaceRunnable getFeatureImportJob(IFeatureModel model, IPath destination)
	{
		return new FeatureImportOperation((EclipseImportReaderType)getReaderType(), model,
			getNodeQuery(), destination, m_base.getType() == PluginImportOperation.IMPORT_BINARY);
	}

	@SuppressWarnings("deprecation")
	private IFeatureModel getFeatureModel(IVersion version, IProgressMonitor monitor) throws CoreException
	{
		IFeatureModel model = null;
		monitor.beginTask(null, m_base.isLocal() ? 1000 : 3000);
		try
		{
			EclipseImportReaderType readerType = (EclipseImportReaderType)getReaderType();
			if(!m_base.isLocal())
			{
				IFeatureReference found = null;
				for(IFeatureReference candidate : m_base.getFeatureReferences(readerType,
					MonitorUtils.subMonitor(monitor, 1000)))
				{
					if(version == null
						|| version.toString().equals(
							candidate.getVersionedIdentifier().getVersion().toString()))
					{
						found = candidate;
						break;
					}
				}
				if(found == null)
					return null;

				ICatalogReader reader = null;
				try
				{
					reader = createRemoteReader(FEATURES_FOLDER, MonitorUtils.subMonitor(monitor, 200));
					return reader.readFile(FEATURE_FILE, new FeatureModelReader(), MonitorUtils.subMonitor(
						monitor, 800));
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
				finally
				{
					if(reader != null)
						reader.close();
				}
			}

			for(IFeatureModel candidate : m_base.getFeatureModels(readerType, MonitorUtils.subMonitor(monitor,
				1000)))
			{
				if(version == null || version.toString().equals(candidate.getFeature().getVersion()))
				{
					model = candidate;
					break;
				}
			}
			return model;
		}
		finally
		{
			monitor.done();
		}
	}

	private File getInstallLocation()
	{
		String location = (m_model instanceof IPluginModelBase)
			? ((IPluginModelBase)m_model).getInstallLocation()
			: ((IFeatureModel)m_model).getInstallLocation();

		return new File(location);
	}

	private IWorkspaceRunnable getPluginImportJob(IPluginModelBase model, IPath destination)
	{
		PluginImportOperation job = new PluginImportOperation(model, getNodeQuery(),
			destination, m_base.getType());
		job.setClasspathCollector((EclipseImportReaderType)getReaderType());
		return job;
	}
}
