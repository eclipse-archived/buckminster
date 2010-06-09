/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.xml.sax.SAXException;

/**
 * Scans a folder for jar files containing an OSGi manifest or an Eclipse
 * feature.xml and generates a JNLP version.xml file based on the information in
 * them. The version.xml file is output in the same folder.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class JNLPSiteGenerator {
	private static final String JNLP_SUFFIX = ".jnlp"; //$NON-NLS-1$

	private static final String JAR_SUFFIX = ".jar"; //$NON-NLS-1$

	private static void emitFolderVersions(File folder, JNLPVersionModel folderVersions) throws CoreException {
		if (folderVersions == null)
			return;

		File versionsFile = new File(folder, "version.xml"); //$NON-NLS-1$
		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(versionsFile));
			Utils.serialize(folderVersions, output);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} catch (SAXException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(output);
		}
	}

	private final HashMap<String, JNLPModel.Resource> jnlpResources = new HashMap<String, JNLPModel.Resource>();

	private final File directory;

	public JNLPSiteGenerator(File directory) {
		this.directory = directory;
	}

	public void run() throws CoreException {
		try {
			JNLPVersionModel folderVersions = null;
			File featuresFolder = new File(directory, IPDEConstants.FEATURES_FOLDER);
			File[] files = featuresFolder.listFiles();
			if (files == null)
				return;

			for (File file : files) {
				IFeature feature;
				if (file.isDirectory()) {
					File featureFile = new File(file, IPDEConstants.FEATURE_FILE);
					if (!featureFile.exists())
						continue;

					InputStream input = null;
					try {
						input = new BufferedInputStream(new FileInputStream(featureFile));
						IFeatureModel model = FeatureModelReader.readFeatureModel(input);
						feature = model.getFeature();
					} finally {
						IOUtils.close(input);
					}
				} else {
					String leafName = file.getName();
					if (!leafName.endsWith(JAR_SUFFIX))
						continue;

					JarFile jarFile = null;
					try {
						jarFile = new JarFile(file);

						JarEntry entry = jarFile.getJarEntry(IPDEConstants.FEATURE_FILE);
						if (entry == null)
							continue;

						IFeatureModel model = FeatureModelReader.readFeatureModel(jarFile.getInputStream(entry));
						feature = model.getFeature();
					} finally {
						if (jarFile != null)
							jarFile.close();
					}
				}
				if (folderVersions == null)
					folderVersions = new JNLPVersionModel();
				generateFromFeature(folderVersions, featuresFolder, feature);
			}

			if (folderVersions != null) {
				emitFolderVersions(directory, folderVersions);
				folderVersions = null;
			}

			File pluginsFolder = new File(directory, IPDEConstants.PLUGINS_FOLDER);
			files = pluginsFolder.listFiles();
			if (files == null)
				return;

			for (File file : files) {
				String leafName = file.getName();
				if (!leafName.endsWith(JAR_SUFFIX))
					continue;

				JarFile jarFile = null;
				try {
					jarFile = new JarFile(file);
					Manifest mf = jarFile.getManifest();
					String symbolicName = mf.getMainAttributes().getValue(Constants.BUNDLE_SYMBOLICNAME);
					if (symbolicName == null)
						continue;

					if (folderVersions == null)
						folderVersions = new JNLPVersionModel();
					generateFromBundle(folderVersions, leafName, mf);
				} finally {
					if (jarFile != null)
						jarFile.close();
				}
			}
			emitFolderVersions(pluginsFolder, folderVersions);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	private void generateFromBundle(JNLPVersionModel folderVersions, String file, Manifest mf) throws CoreException {
		Attributes a = mf.getMainAttributes();
		String id;
		try {
			ManifestElement[] elements = ManifestElement.parseHeader(Constants.BUNDLE_SYMBOLICNAME, a.getValue(Constants.BUNDLE_SYMBOLICNAME));
			id = elements[0].getValue();
		} catch (BundleException be) {
			throw BuckminsterException.wrap(be);
		}

		String version = a.getValue(Constants.BUNDLE_VERSION);
		if (version == null)
			version = "0.0.0"; //$NON-NLS-1$

		/*
		 * We don't do this now since the requests lack os/arch information for
		 * some reason.
		 * 
		 * JNLPVersionModel.Resource resource = folderVersions.addResource(file,
		 * id + ".jar", version); JNLPModel.Resource jnlpResource =
		 * jnlpResources.get(id + "_B"); if(jnlpResource != null) { String os =
		 * jnlpResource.getOs(); if(os != null) resource.addOs(os);
		 * 
		 * String arch = jnlpResource.getArch(); if(arch != null)
		 * resource.addArch(arch); }
		 */
		folderVersions.addResource(file, id + JAR_SUFFIX, version);
	}

	private void generateFromFeature(JNLPVersionModel folderVersions, File featuresFolder, IFeature feature) throws CoreException {
		String id = feature.getId();
		String version = feature.getVersion();
		String file = id + '_' + version + JNLP_SUFFIX;

		JNLPVersionModel.Resource resource = folderVersions.addResource(file, id + JNLP_SUFFIX, version);
		String os = feature.getOS();
		if (os == null)
			os = feature.getWS();
		os = JNLPGenerator.convertOS(os);
		if (os != null)
			resource.addOs(os);

		String arch = JNLPGenerator.convertArch(feature.getArch());
		if (arch != null)
			resource.addArch(arch);

		// Generate the JNLPModel for this feature
		//
		JNLPGenerator jnlpGen = new JNLPGenerator(feature);
		JNLPModel jnlp = jnlpGen.generateJNLP();
		jnlpResources.putAll(jnlp.getResources());

		// Serialize the JNLP xml
		//
		File jnlpFile = new File(featuresFolder.getParentFile(), file);
		OutputStream jnlpOut = null;
		try {
			jnlpOut = new BufferedOutputStream(new FileOutputStream(jnlpFile));
			Utils.serialize(jnlp, jnlpOut);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} catch (SAXException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(jnlpOut);
		}
	}
}
