/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.feature.FeatureChild;
import org.eclipse.pde.internal.core.feature.FeaturePlugin;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class SourceFeatureCreator implements IPDEConstants, IBuildPropertiesConstants {
	private static final String FEATURE_SUFFIX = ".feature"; //$NON-NLS-1$

	private static final String SOURCE_SUFFIX = ".source"; //$NON-NLS-1$

	public static String createSourceFeatureId(String originalId) {
		StringBuilder sourceIdBld = new StringBuilder();
		if (originalId.endsWith(FEATURE_SUFFIX)) {
			sourceIdBld.append(originalId, 0, originalId.length() - FEATURE_SUFFIX.length());
			sourceIdBld.append(SOURCE_SUFFIX);
			sourceIdBld.append(FEATURE_SUFFIX);
		} else {
			sourceIdBld.append(originalId);
			sourceIdBld.append(SOURCE_SUFFIX);
		}
		return sourceIdBld.toString();
	}

	private final File inputFile;

	private final File outputFile;

	private List<File> featuresAndBundles;

	public SourceFeatureCreator(File inputFile, File outputFile, List<File> featuresAndBundles) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.featuresAndBundles = featuresAndBundles;
	}

	public void run() throws CoreException, FileNotFoundException {
		IFeature originalFeature = FeatureModelReader.readEditableFeatureModel(inputFile).getFeature();
		EditableFeatureModel featureModel = new EditableFeatureModel(outputFile);
		featureModel.setDirty(true);
		IFeature sourceFeature = featureModel.getFeature();

		String originalId = originalFeature.getId();
		sourceFeature.setId(createSourceFeatureId(originalId));
		sourceFeature.setVersion(originalFeature.getVersion());
		sourceFeature.setLabel("Source bundles for " + originalFeature.getLabel()); //$NON-NLS-1$

		sourceFeature.setOS(originalFeature.getOS());
		sourceFeature.setArch(originalFeature.getArch());
		sourceFeature.setWS(originalFeature.getWS());
		sourceFeature.setNL(originalFeature.getNL());
		sourceFeature.setProviderName(originalFeature.getProviderName());
		sourceFeature.setURL(originalFeature.getURL());
		for (File featureOrBundle : featuresAndBundles) {
			InputStream input = null;
			try {
				input = GroupConsolidator.getInput(featureOrBundle, FEATURE_FILE);
				IFeatureModel model = FeatureModelReader.readFeatureModel(input);
				IFeature feature = model.getFeature();

				FeatureChild fc = new FeatureChild();
				fc.setModel(featureModel);
				fc.loadFrom(model.getFeature());
				fc.setArch(feature.getArch());
				fc.setOS(feature.getOS());
				fc.setWS(feature.getWS());
				fc.setNL(feature.getNL());
				fc.setLabel(feature.getLabel());
				fc.setVersion(feature.getVersion());

				sourceFeature.addIncludedFeatures(new IFeatureChild[] { fc });
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
				input = null;
			}
			try {
				input = GroupConsolidator.getInput(featureOrBundle, BUNDLE_FILE);
				ExternalBundleModel model = new ExternalBundleModel(featureOrBundle);
				model.load(input, true);
				IBundlePluginModelBase bmodel = model.isFragmentModel() ? new BundleFragmentModel() : new BundlePluginModel();

				bmodel.setEnabled(true);
				bmodel.setBundleModel(model);
				IPluginBase plugin = bmodel.getPluginBase();
				if (plugin.getId() == null)
					throw BuckminsterException.fromMessage("Unable to extract feature.xml or a valid OSGi bundle manifest from %s", //$NON-NLS-1$
							featureOrBundle.getAbsolutePath());

				FeaturePlugin fp = new FeaturePlugin();
				fp.loadFrom(plugin);
				fp.setModel(featureModel);
				fp.setUnpack(false);
				fp.setVersion(plugin.getVersion());

				// Load arch etc. from corresponding original plug-in (if we
				// find it)
				//
				String ver = plugin.getVersion();
				String id = plugin.getId();
				if (id.endsWith(SOURCE_SUFFIX)) {
					String origId = id.substring(0, id.length() - SOURCE_SUFFIX.length());
					for (IFeaturePlugin originalPlugin : originalFeature.getPlugins()) {
						if (originalPlugin.getId().equals(origId) && originalPlugin.getVersion().equals(ver)) {
							fp.setArch(originalPlugin.getArch());
							fp.setOS(originalPlugin.getOS());
							fp.setWS(originalPlugin.getWS());
							fp.setNL(originalPlugin.getNL());
							break;
						}
					}
				}
				sourceFeature.addPlugins(new IFeaturePlugin[] { fp });
				continue;
			} catch (FileNotFoundException e) {
			} finally {
				IOUtils.close(input);
			}
		}
		featureModel.save();
	}
}
