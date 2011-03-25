/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
abstract class GroupConsolidator extends VersionQualifierTask implements IPDEConstants, IBuildPropertiesConstants {
	static void addVersion(Map<String, Version[]> versionMap, String id, String versionStr) {
		if (versionStr == null)
			return;

		Version version = Version.parseVersion(versionStr);
		Version[] arr = versionMap.get(id);
		if (arr == null)
			arr = new Version[] { version };
		else {
			for (Version old : arr)
				if (old.equals(version))
					return;

			Version[] newArr = new Version[arr.length + 1];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			newArr[arr.length] = version;
			arr = newArr;
		}
		versionMap.put(id, arr);
	}

	static Version findBestVersion(Map<String, Version[]> versionMap, String id, String componentType, String refId, String vstr)
			throws CoreException {
		Version version = Version.create(vstr);
		if (version != null && Version.emptyVersion.equals(version))
			version = null;

		Version candidate = null;
		Version[] versions = versionMap.get(refId);
		if (versions != null) {
			for (Version v : versions) {
				if (v == null)
					continue;

				if (version == null) {
					// Highest found version wins
					//
					if (candidate == null || v.compareTo(candidate) > 0)
						candidate = v;
					continue;
				}

				if (VersionHelper.equalsUnqualified(version, v)) {
					if (candidate == null || v.compareTo(candidate) > 0)
						candidate = v;
				}
			}
		}
		if (candidate == null) {
			String idWithoutSource = null;
			if (refId.endsWith(".source")) //$NON-NLS-1$
				idWithoutSource = refId.substring(0, refId.length() - 7);
			else if (refId.endsWith(".source.feature")) //$NON-NLS-1$
				idWithoutSource = refId.substring(0, refId.length() - 15) + ".feature"; //$NON-NLS-1$
			if (idWithoutSource != null)
				candidate = findBestVersion(versionMap, id, componentType, idWithoutSource, vstr);

			if (candidate == null)
				//
				// Nothing found that can replace the version
				//
				candidate = version;
		}

		return candidate;
	}

	static InputStream getInput(File dirOrZip, String fileName) throws CoreException, FileNotFoundException {
		if (dirOrZip.isDirectory())
			return new BufferedInputStream(new FileInputStream(new File(dirOrZip, fileName)));

		JarFile jarFile = null;
		try {
			jarFile = new JarFile(dirOrZip);
			JarEntry entry = jarFile.getJarEntry(fileName);
			if (entry == null)
				throw new FileNotFoundException(String.format("%s[%s]", dirOrZip, fileName)); //$NON-NLS-1$

			// Closing the jarFile is hereby the responsibility of the user of
			// the returned InputStream
			//
			final JarFile innerJarFile = jarFile;
			jarFile = null;
			return new FilterInputStream(innerJarFile.getInputStream(entry)) {
				@Override
				public void close() throws IOException {
					try {
						super.close();
					} catch (IOException e) {
					}
					innerJarFile.close();
				}
			};
		} catch (FileNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw BuckminsterException.fromMessage(e, NLS.bind(Messages.unable_to_read_0, dirOrZip));
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					PDEPlugin.getLogger().error(e, NLS.bind(Messages.error_while_closing_0, dirOrZip));
				}
			}
		}
	}

	private final File outputFile;

	private final FeatureVersionSuffixGenerator suffixGenerator;

	private final Map<String, Version[]> pluginVersions = new HashMap<String, Version[]>();

	private final Map<String, Version[]> featureVersions = new HashMap<String, Version[]>();

	GroupConsolidator(File outputFile, File propertiesFile, List<File> featuresAndBundles, String qualifier, boolean generateVersionSuffix,
			int maxVersionSuffixLength, int significantDigits) throws CoreException {
		super(propertiesFile, qualifier);
		this.outputFile = outputFile;
		if (generateVersionSuffix)
			this.suffixGenerator = new FeatureVersionSuffixGenerator(getIntProperty(PROPERTY_GENERATED_VERSION_LENGTH, maxVersionSuffixLength),
					getIntProperty(PROPERTY_SIGNIFICANT_VERSION_DIGITS, significantDigits));
		else
			this.suffixGenerator = null;

		for (File featureOrBundle : featuresAndBundles) {
			InputStream input = null;
			try {
				input = getInput(featureOrBundle, FEATURE_MANIFEST);
				IFeatureModel model = FeatureModelReader.readFeatureModel(input);
				IFeature feature = model.getFeature();
				String id = feature.getId();
				String version = feature.getVersion();
				if (version == null)
					continue;

				int ctxQualLen = -1;
				if (version.indexOf('-') > 0) {
					IOUtils.close(input);
					input = getInput(featureOrBundle, FEATURE_MANIFEST);
					ctxQualLen = EditableFeatureModel.getContextQualifierLength(input);
				}
				if (suffixGenerator != null)
					suffixGenerator.addContextQualifierLength(id, ctxQualLen);
				addVersion(featureVersions, id, version);
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
				input = null;
			}
			try {
				input = getInput(featureOrBundle, BUNDLE_FILE);
				ExternalBundleModel model = new ExternalBundleModel(featureOrBundle);
				model.load(input, true);
				IBundlePluginModelBase bmodel = model.isFragmentModel() ? new BundleFragmentModel() : new BundlePluginModel();

				bmodel.setEnabled(true);
				bmodel.setBundleModel(model);
				IPluginBase pb = bmodel.getPluginBase();

				addVersion(pluginVersions, pb.getId(), pb.getVersion());
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
				input = null;
			}
			try {
				input = getInput(featureOrBundle, PLUGIN_FILE);
				ExternalPluginModel model = new ExternalPluginModel();
				model.load(input, true);
				IPluginBase pb = model.getPluginBase();
				addVersion(pluginVersions, pb.getId(), pb.getVersion());
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
				input = null;
			}
			try {
				input = getInput(featureOrBundle, FRAGMENT_FILE);
				ExternalFragmentModel model = new ExternalFragmentModel();
				model.load(input, true);
				IPluginBase pb = model.getPluginBase();
				addVersion(pluginVersions, pb.getId(), pb.getVersion());
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
			}
		}
	}

	/**
	 * Version suffix generation. Modeled after
	 * {@link org.eclipse.pde.internal.build.builder.FeatureBuildScriptGenerator#generateFeatureVersionSuffix(org.eclipse.pde.internal.build.site.BuildTimeFeature buildFeature)}
	 * 
	 * @return The generated suffix or <code>null</code>
	 * @throws CoreException
	 */
	String generateFeatureVersionSuffix(List<IVersionedId> features, List<IVersionedId> bundles) throws CoreException {
		return suffixGenerator == null ? null : suffixGenerator.generateSuffix(features, bundles);
	}

	Map<String, Version[]> getFeatureVersions() {
		return featureVersions;
	}

	int getIntProperty(String property, int defaultValue) {
		int result = defaultValue;

		Object value = getProperties().get(property);
		if (value instanceof String) {
			try {
				result = Integer.parseInt((String) value);
				if (result < 1)
					// It has to be a positive integer. Use the default.
					result = defaultValue;
			} catch (NumberFormatException e) {
				// Leave as default value
			}
		}
		return result;
	}

	File getOutputFile() {
		return outputFile;
	}

	Map<String, Version[]> getPluginVersions() {
		return pluginVersions;
	}
}
