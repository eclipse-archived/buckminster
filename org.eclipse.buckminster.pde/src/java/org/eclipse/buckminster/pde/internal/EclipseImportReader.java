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
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
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
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EclipseImportReader extends AbstractRemoteReader implements IPDEConstants {
	private EclipseImportBase base;

	private IModel importModel;

	protected EclipseImportReader(EclipseImportReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
		base = EclipseImportBase.obtain(rInfo.getNodeQuery(), rInfo.getRepositoryURI());

		VersionMatch vm = rInfo.getVersionMatch();
		if (vm.getArtifactInfo() == null) {
			Version version = rInfo.getVersionMatch().getVersion();
			importModel = base.isFeature() ? getFeatureModel(version, new NullProgressMonitor()) : getPluginModel(version, new NullProgressMonitor());

			if (importModel == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_load_model_for_0, base.getComponentName()));
		}
	}

	@Override
	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 1000);
		try {
			localize(!base.isFeature(), MonitorUtils.subMonitor(monitor, 800));
			IWorkspaceRunnable job = base.isFeature() ? getFeatureImportJob((IFeatureModel) importModel, destination) : getPluginImportJob(
					(IPluginModelBase) importModel, destination);
			ResourcesPlugin.getWorkspace().run(job, MonitorUtils.subMonitor(monitor, 200));
		} finally {
			monitor.done();
		}
	}

	public boolean isUnpack() {
		return base.isUnpack();
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException {
		monitor.beginTask(null, 1000);

		File destFile = null;
		OutputStream output = null;
		try {
			boolean isPlugin = importModel instanceof IPluginModelBase;
			localize(isPlugin, MonitorUtils.subMonitor(monitor, 890));
			destFile = createTempFile();
			output = new FileOutputStream(destFile);
			MonitorUtils.worked(monitor, 10);
			ZipFile zipFile = null;
			InputStream input = null;
			try {
				File source = getInstallLocation();
				if (source.isDirectory())
					input = new FileInputStream(new File(source, fileName));
				else {
					zipFile = new ZipFile(source);
					ZipEntry entry = zipFile.getEntry(fileName);
					if (entry == null)
						throw new FileNotFoundException(source.getName() + '!' + fileName);
					input = zipFile.getInputStream(entry);
				}
				FileUtils.copyFile(input, output, MonitorUtils.subMonitor(monitor, 100));
			} finally {
				IOUtils.close(input);
				if (zipFile != null)
					try {
						zipFile.close();
					} catch (IOException e) {
						// Ignore
					}
			}
			FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		} finally {
			IOUtils.close(output);
			if (destFile != null)
				destFile.delete();
		}
	}

	IPluginModelBase getPluginModel(Version version, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, base.isLocal() ? 1000 : 2000);
		monitor.subTask(NLS.bind(Messages.downloading_0, base.getComponentName()));
		try {
			EclipseImportReaderType readerType = (EclipseImportReaderType) getReaderType();
			if (!base.isLocal())
				localize(true, MonitorUtils.subMonitor(monitor, 1000));

			IPluginModelBase model = null;
			for (IPluginModelBase candidate : base.getPluginModels(readerType, MonitorUtils.subMonitor(monitor, 1000))) {
				if (version == null || version.toString().equals(candidate.getBundleDescription().getVersion().toString())) {
					model = candidate;
					break;
				}
			}
			return model;
		} finally {
			monitor.done();
		}
	}

	private IWorkspaceRunnable getFeatureImportJob(IFeatureModel model, IPath destination) {
		return new FeatureImportOperation((EclipseImportReaderType) getReaderType(), model, getNodeQuery(), destination,
				getImportType() == PluginImportOperation.IMPORT_BINARY);
	}

	private IFeatureModel getFeatureModel(Version version, IProgressMonitor monitor) throws CoreException {
		IFeatureModel model = null;
		monitor.beginTask(null, base.isLocal() ? 1000 : 3000);
		try {
			if (!base.isLocal())
				localize(false, MonitorUtils.subMonitor(monitor, 1000));

			EclipseImportReaderType readerType = (EclipseImportReaderType) getReaderType();
			for (IFeatureModel candidate : base.getFeatureModels(readerType, MonitorUtils.subMonitor(monitor, 1000))) {
				if (version == null || version.toString().equals(candidate.getFeature().getVersion())) {
					model = candidate;
					break;
				}
			}
			return model;
		} finally {
			monitor.done();
		}
	}

	private int getImportType() {
		int importType = base.getType();
		if (importType == PluginImportOperation.IMPORT_UNKNOWN)
			importType = getProviderMatch().getProvider().hasSource() ? PluginImportOperation.IMPORT_WITH_SOURCE
					: PluginImportOperation.IMPORT_BINARY;
		return importType;
	}

	private File getInstallLocation() {
		String location = (importModel instanceof IPluginModelBase) ? ((IPluginModelBase) importModel).getInstallLocation()
				: ((IFeatureModel) importModel).getInstallLocation();

		return new File(location);
	}

	private IWorkspaceRunnable getPluginImportJob(IPluginModelBase model, IPath destination) {
		PluginImportOperation job = new PluginImportOperation(model, getNodeQuery(), destination, getImportType());
		job.setClasspathCollector((EclipseImportReaderType) getReaderType());
		return job;
	}

	private void localize(boolean isPlugin, IProgressMonitor monitor) throws CoreException {
		if (base.isLocal() && getProviderMatch().getVersionMatch().getArtifactInfo() == null) {
			MonitorUtils.complete(monitor);
			return;
		}

		monitor.beginTask(null, 1000);
		ProviderMatch ri = getProviderMatch();
		base = ((EclipseImportReaderType) getReaderType()).localizeContents(ri, isPlugin, MonitorUtils.subMonitor(monitor, 950));

		// Model is now local, so reset it.
		//
		Version version = ri.getVersionMatch().getVersion();
		IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 50);
		importModel = isPlugin ? getPluginModel(version, subMon) : getFeatureModel(version, subMon);
		if (importModel == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_load_localized_model_for_0, base.getComponentName()));
		monitor.done();
	}
}
