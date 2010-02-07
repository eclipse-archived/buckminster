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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.reader.AbstractCatalogReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A Reader that knows about features and plugins that are part of an Eclipse
 * installation.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EclipsePlatformReader extends AbstractCatalogReader {
	public enum InstalledType {
		FEATURE, PLUGIN
	}

	/**
	 * A File filter and collector that will collect all occurances of a named
	 * component along with its version number.
	 */
	class PluginFilter implements FilenameFilter {
		private final ArrayList<Version> collector;

		private final Pattern pattern;

		PluginFilter(String componentName, ArrayList<Version> collector) {
			this.pattern = Pattern.compile('^' + Pattern.quote(componentName) + "_(.*?)(?:\\.jar)?$"); //$NON-NLS-1$
			this.collector = collector;
		}

		public boolean accept(File directory, String pathname) {
			Matcher m = pattern.matcher(pathname);
			if (!m.matches())
				return false;
			try {
				collector.add(Version.parseVersion(m.group(1)));
				return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
	}

	private final String componentName;

	private IModel model;

	private final InstalledType type;

	public EclipsePlatformReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
		String uri = rInfo.getRepositoryURI();
		IPath path = new Path(uri);
		if (path.segmentCount() == 2) {
			type = InstalledType.valueOf(path.segment(0).toUpperCase());
			if (type != null) {
				componentName = path.segment(1);
				return;
			}
		}
		throw new MalformedProviderURIException(readerType, uri);
	}

	@Override
	public boolean canMaterialize() {
		return false;
	}

	public synchronized IFeatureModel getFeatureModel() {
		if (type != InstalledType.FEATURE)
			return null;

		if (model == null)
			model = getBestFeature();
		return (IFeatureModel) model;
	}

	public synchronized IPluginModelBase getPluginModelBase() throws CoreException {
		if (type != InstalledType.PLUGIN)
			throw new IllegalStateException(Messages.plugin_requested_from_feature_reader);

		if (model == null) {
			model = getBestPlugin();
			if (model == null)
				throw new MissingComponentException(componentName);
		}
		return (IPluginModelBase) model;
	}

	public InstalledType getType() {
		return type;
	}

	/**
	 * This method should never be called. If a user wants to materialize an
	 * installed plugin, that should be done using the import plugin wizard.
	 */
	public void innerMaterialize(IPath destination, IProgressMonitor monitor) {
		throw new UnsupportedOperationException("checkout"); //$NON-NLS-1$
	}

	protected String getResolvedFile(String relativeFile, InputStream[] isReturn) throws IOException, CoreException {
		File modelRoot = getModelRoot();
		if (modelRoot == null)
			throw new FileNotFoundException(relativeFile);

		String fileName;
		if (modelRoot.isDirectory()) {
			File wantedFile = new File(modelRoot, relativeFile);
			fileName = wantedFile.toString();
			if (!wantedFile.exists())
				throw new FileNotFoundException(fileName);
			if (isReturn != null)
				isReturn[0] = new FileInputStream(wantedFile);
		} else {
			if (!modelRoot.getName().endsWith(".jar")) //$NON-NLS-1$
				throw new FileNotFoundException(modelRoot.toString());

			fileName = modelRoot.getName() + '!' + relativeFile;

			final JarFile jf = new JarFile(modelRoot);
			JarEntry entry = jf.getJarEntry(relativeFile);
			if (entry == null) {
				jf.close();
				throw new FileNotFoundException(fileName);
			}
			if (isReturn == null)
				jf.close();
			else {
				// Return a special InputStream that makes sure that the
				// entry and the JarFile that it stems from are both closed
				//
				isReturn[0] = new FilterInputStream(jf.getInputStream(entry)) {
					@Override
					public void close() throws IOException {
						try {
							super.close();
						} catch (IOException e) {
						}
						jf.close();
					}
				};
			}
		}
		return fileName;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		try {
			getResolvedFile(fileName, null);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException {
		File modelRoot = getModelRoot();
		if (modelRoot == null)
			return;

		File[] content = modelRoot.listFiles();
		if (content != null) {
			for (File file : content) {
				String name = file.getName();
				if (file.isDirectory())
					name = name + "/"; //$NON-NLS-1$
				files.add(name);
			}
			return;
		}

		if (!modelRoot.getName().endsWith(".jar")) //$NON-NLS-1$
			return;

		ZipInputStream zi = null;
		try {
			ZipEntry ze;
			zi = new ZipInputStream(new BufferedInputStream(new FileInputStream(modelRoot)));
			while ((ze = zi.getNextEntry()) != null) {
				String name = ze.getName();
				if (name.endsWith("/")) //$NON-NLS-1$
					name = name.substring(name.length() - 1);
				if (name.indexOf('/', 1) < 0) {
					if (ze.isDirectory())
						name = name + "/"; //$NON-NLS-1$
					files.add(name);
				}
			}
		} catch (IOException e) {
		} finally {
			IOUtils.close(zi);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		InputStream input = null;
		try {
			InputStream[] isHolder = new InputStream[1];
			String systemId = getResolvedFile(fileName, isHolder);
			input = new BufferedInputStream(isHolder[0]);
			return consumer.consumeStream(this, systemId, input, monitor);
		} finally {
			IOUtils.close(input);
		}
	}

	private IFeatureModel getBestFeature() {
		return EclipsePlatformReaderType.getBestFeature(componentName, getDesiredVersion(), null);
	}

	private IPluginModelBase getBestPlugin() {
		return EclipsePlatformReaderType.getBestPlugin(componentName, getDesiredVersion(), null);
	}

	private VersionRange getDesiredVersion() {
		VersionRange desiredVersion = null;
		ProviderMatch vsMatch = getProviderMatch();
		if (vsMatch != null) {
			Version version = vsMatch.getVersionMatch().getVersion();
			if (version != null)
				desiredVersion = VersionHelper.exactRange(version);
		}
		return desiredVersion;
	}

	private File getModelRoot() throws CoreException {
		String installLocation;
		if (type == InstalledType.PLUGIN) {
			IPluginModelBase pluginModel;
			try {
				pluginModel = getPluginModelBase();
			} catch (IllegalStateException e) {
				return null;
			}
			installLocation = pluginModel.getInstallLocation();
		} else {
			IFeatureModel featureModel = getFeatureModel();
			if (featureModel == null)
				return null;
			installLocation = featureModel.getInstallLocation();
		}
		return new File(installLocation);
	}
}
