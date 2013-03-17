/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
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
import org.eclipse.pde.internal.core.ifeature.IFeatureInfo;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class SourceFeatureCreator implements IPDEConstants, IBuildPropertiesConstants {
	private static final String FEATURE_SUFFIX = ".feature"; //$NON-NLS-1$

	private static final String SOURCE_SUFFIX = ".source"; //$NON-NLS-1$

	private static final String DESCRIPTION_FORMAT = "Generated source feature. The original description is:\n\n{0}"; //$NON-NLS-1$

	private static final String LABEL_FORMAT = "Source for {0}"; //$NON-NLS-1$

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

	private final List<File> translationFiles;

	private final List<File> featuresAndBundles;

	private Map<String, String> translations;

	private Map<String, String> usedTranslations;

	public SourceFeatureCreator(File inputFile, List<File> translationFiles, File outputFile, List<File> featuresAndBundles) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
		this.featuresAndBundles = featuresAndBundles;
		this.translationFiles = translationFiles;
	}

	public void run() throws CoreException, FileNotFoundException {
		IFeature originalFeature = FeatureModelReader.readEditableFeatureModel(inputFile).getFeature();
		EditableFeatureModel featureModel = new EditableFeatureModel(outputFile);
		featureModel.setDirty(true);
		IFeature sourceFeature = featureModel.getFeature();

		IActionContext ctx = AbstractActor.getActiveContext();

		String originalId = originalFeature.getId();
		String sourceId = createSourceFeatureId(originalId);
		sourceFeature.setId(sourceId);
		sourceFeature.setVersion(originalFeature.getVersion());
		String label = originalFeature.getLabel();
		Map<String, ? extends Object> props = ctx.getProperties();
		String format = (String) props.get(IPDEConstants.PROP_PDE_SOURCE_FEATURE_LABEL_FORMAT);
		if (format == null)
			format = LABEL_FORMAT;

		if (!addLocalizedProperty(ctx, label, format))
			label = MessageFormat.format(format, label);
		sourceFeature.setLabel(label);

		sourceFeature.setOS(originalFeature.getOS());
		sourceFeature.setArch(originalFeature.getArch());
		sourceFeature.setWS(originalFeature.getWS());
		sourceFeature.setNL(originalFeature.getNL());

		String providerName = originalFeature.getProviderName();
		addLocalizedProperty(ctx, providerName, null);
		sourceFeature.setProviderName(providerName);
		sourceFeature.setURL(originalFeature.getURL());

		IFeatureInfo copyright = originalFeature.getFeatureInfo(IFeature.INFO_COPYRIGHT);
		if (copyright != null)
			cloneInfo(ctx, sourceFeature, copyright, null);

		IFeatureInfo license = originalFeature.getFeatureInfo(IFeature.INFO_LICENSE);
		if (license != null)
			cloneInfo(ctx, sourceFeature, license, null);

		IFeatureInfo description = originalFeature.getFeatureInfo(IFeature.INFO_DESCRIPTION);
		if (description != null) {
			format = (String) props.get(IPDEConstants.PROP_PDE_SOURCE_FEATURE_DESCRIPTION_FORMAT);
			if (format == null)
				format = DESCRIPTION_FORMAT;
			cloneInfo(ctx, sourceFeature, description, format);
		}

		for (File featureOrBundle : featuresAndBundles) {
			InputStream input = null;
			try {
				input = GroupConsolidator.getInput(featureOrBundle, FEATURE_MANIFEST);
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
		saveTranslations();
	}

	private boolean addLocalizedProperty(IActionContext ctx, String key, String format) throws CoreException {
		if (key == null || !key.startsWith("%")) //$NON-NLS-1$
			return false;

		if (translations == null) {
			// We need to get hold of the feature.properties from the
			// original source
			for (File translationFile : translationFiles) {
				if ("feature.properties".equals(translationFile.getName())) { //$NON-NLS-1$
					InputStream inStream = null;
					try {
						inStream = new BufferedInputStream(new FileInputStream(translationFile));
						translations = new BMProperties(inStream);
					} catch (IOException e) {
					} finally {
						IOUtils.close(inStream);
					}
					break;
				}
			}
			if (translations == null)
				translations = Collections.emptyMap();
		}
		String mapKey = key.substring(1);
		String translated = translations.get(mapKey);
		if (translated == null) {
			PDEPlugin.getLogger().warning("Unable to find translation for property %s", key); //$NON-NLS-1$
			return false;
		}
		if (format != null)
			translated = MessageFormat.format(format, translated);
		if (usedTranslations == null)
			usedTranslations = new HashMap<String, String>();
		usedTranslations.put(mapKey, translated);
		return true;
	}

	private void cloneInfo(IActionContext ctx, IFeature newOwner, IFeatureInfo original, String descriptionFormat) throws CoreException {
		IFeatureInfo copy = newOwner.getModel().getFactory().createInfo(original.getIndex());
		String description = original.getDescription();
		if (!addLocalizedProperty(ctx, description, descriptionFormat) && descriptionFormat != null)
			description = MessageFormat.format(descriptionFormat, description);
		copy.setDescription(description);
		addLocalizedProperty(ctx, original.getLabel(), null);
		copy.setLabel(original.getLabel());
		addLocalizedProperty(ctx, original.getURL(), null);
		copy.setURL(original.getURL());
		newOwner.setFeatureInfo(copy, original.getIndex());
	}

	private void saveTranslations() throws CoreException {
		if (usedTranslations != null) {
			OutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(new File(outputFile.getParentFile(), "feature.properties"))); //$NON-NLS-1$
				BMProperties.store(usedTranslations, out, null);
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			} finally {
				IOUtils.close(out);
			}
		}
	}
}
