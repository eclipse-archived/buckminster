/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.cspecgen.feature;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.LocalReader;
import org.eclipse.buckminster.pde.internal.actor.MergeLicenseFeature;
import org.eclipse.buckminster.pde.tasks.SourceFeatureCreator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.internal.core.ifeature.IFeature;

@SuppressWarnings("restriction")
public class CSpecFromSource extends CSpecFromFeature {
	private final Map<String, String> buildProperties;

	protected CSpecFromSource(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature, Map<String, String> buildProperties) {
		super(cspecBuilder, reader, feature);
		this.buildProperties = buildProperties;
	}

	@Override
	protected String getProductOutputFolder(String productId) {
		return buildProperties == null ? null : buildProperties.get(productId + TOP_FOLDER_SUFFIX);
	}

	@Override
	void createFeatureJarAction(IComponentRequest licenseFeature, IProgressMonitor monitor) throws CoreException {
		boolean versionedManifestPublic = licenseFeature == null;
		String versionedManifest = versionedManifestPublic ? ATTRIBUTE_MANIFEST : ATTRIBUTE_VERSIONED_MANIFEST;
		createBinIncludesArtifact(licenseFeature, versionedManifest, monitor);
		createFeatureManifestAction(versionedManifest, versionedManifestPublic);

		// Create the action that builds the final jar file for the feature
		//
		ActionBuilder featureJarBuilder = addAntAction(ATTRIBUTE_FEATURE_JAR, TASK_CREATE_FEATURE_JAR, false);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_JAR_CONTENTS);
		featureJarBuilder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

		featureJarBuilder.setProductAlias(ALIAS_OUTPUT);
		featureJarBuilder.setProductBase(OUTPUT_DIR_JAR);
		featureJarBuilder.setUpToDatePolicy(UpToDatePolicy.COUNT);
		featureJarBuilder.setProductFileCount(1);
	}

	@Override
	void createFeatureSourceJarAction() throws CoreException {
		CSpecBuilder cspec = getCSpec();
		boolean translations = cspec.getAttribute(ATTRIBUTE_BIN_INCLUDES) != null;
		createFeatureSourceManifestAction(translations);

		// Create the action that builds the jar file with all source bundles
		// for the feature
		//
		ActionBuilder featureJarBuilder = addAntAction(ATTRIBUTE_SOURCE_FEATURE_JAR, TASK_CREATE_FEATURE_JAR, false);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_SOURCE_MANIFEST, ALIAS_MANIFEST);
		if (translations) {
			// We use the same content as the original feature (i.e. license,
			// etc.).
			//
			featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_BIN_INCLUDES);
			featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_SOURCE_LOCALIZATION, ATTRIBUTE_SOURCE_LOCALIZATION);
		}

		featureJarBuilder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

		featureJarBuilder.setProductAlias(ALIAS_OUTPUT);
		featureJarBuilder.setProductBase(OUTPUT_DIR_SOURCE_JAR);
		featureJarBuilder.setUpToDatePolicy(UpToDatePolicy.COUNT);
		featureJarBuilder.setProductFileCount(1);

		GeneratorBuilder genBld = cspec.createGeneratorBuilder();
		genBld.setAttribute(ATTRIBUTE_SOURCE_FEATURE_JAR);
		genBld.setGeneratesType(IComponentType.ECLIPSE_FEATURE);
		genBld.setName(SourceFeatureCreator.createSourceFeatureId(cspec.getName()));
		cspec.addGenerator(genBld);
	}

	@Override
	void createSiteActions(IProgressMonitor monitor) throws CoreException {
		createSiteFeatureExportsAction();

		if (!addProducts(MonitorUtils.subMonitor(monitor, 80))) {
			// No product defined a site so we add the actions for that
			// here.
			//
			createSiteRepackAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSiteSignAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSitePackAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSiteAction(ATTRIBUTE_SITE_FEATURE_EXPORTS, ATTRIBUTE_MANIFEST);
			createSiteZipAction();
		}
	}

	private void createBinIncludesArtifact(IComponentRequest licenseFeature, String versionedManifest, IProgressMonitor monitor) throws CoreException {
		CSpecBuilder cspec = getCSpec();

		ArtifactBuilder binIncludesArtifact = cspec.createArtifactBuilder();
		binIncludesArtifact.setPublic(false);
		if (buildProperties == null) {
			for (String path : getReader().list(monitor)) {
				if (FEATURE_MANIFEST.equals(path))
					//
					// Handled separately
					//
					continue;
				binIncludesArtifact.addPath(new Path(path));
			}
		} else {
			List<String> binIncludes;
			if (getReader() instanceof LocalReader) {
				File baseDir;
				try {
					baseDir = new File(((LocalReader) getReader()).getURL().toURI());
				} catch (URISyntaxException e) {
					throw BuckminsterException.wrap(e);
				}
				binIncludes = expandBinFiles(baseDir, buildProperties);
			} else {
				binIncludes = Collections.emptyList();
			}

			cspec.addArtifact(ATTRIBUTE_BUILD_PROPERTIES, false, null).addPath(new Path(BUILD_PROPERTIES_FILE));
			for (String path : binIncludes) {
				if (FEATURE_MANIFEST.equals(path))
					//
					// Handled separately
					//
					continue;

				binIncludesArtifact.addPath(new Path(path));
			}
			MonitorUtils.complete(monitor);
		}

		if (licenseFeature == null) {
			GroupBuilder jarContents = cspec.addGroup(ATTRIBUTE_JAR_CONTENTS, true);
			if (!binIncludesArtifact.getPaths().isEmpty()) {
				binIncludesArtifact.setName(ATTRIBUTE_BIN_INCLUDES);
				cspec.addAttribute(binIncludesArtifact);
				jarContents.addLocalPrerequisite(binIncludesArtifact);
			}
			jarContents.addLocalPrerequisite(versionedManifest);
		} else {
			ActionBuilder mergeLicense = cspec.addAction(ATTRIBUTE_JAR_CONTENTS, true, MergeLicenseFeature.ID, false);
			mergeLicense.addExternalPrerequisite(licenseFeature, CSpec.SELF_ARTIFACT).setAlias(ALIAS_LICENSE_FEATURE);
			mergeLicense.addExternalPrerequisite(licenseFeature, ATTRIBUTE_JAR_CONTENTS).setAlias(ALIAS_LICENSE_FEATURE_CONTENTS);
			mergeLicense.addExternalPrerequisite(licenseFeature, ATTRIBUTE_MANIFEST).setAlias(ALIAS_LICENSE_MANIFEST);
			if (!binIncludesArtifact.getPaths().isEmpty()) {
				binIncludesArtifact.setName(ATTRIBUTE_BIN_INCLUDES + ".raw"); //$NON-NLS-1$
				cspec.addAttribute(binIncludesArtifact);
				mergeLicense.addLocalPrerequisite(binIncludesArtifact).setAlias(IBuildEntry.BIN_INCLUDES);
			}
			mergeLicense.addLocalPrerequisite(versionedManifest, ALIAS_MANIFEST);
			ActionArtifactBuilder output = mergeLicense.addProductArtifact(ATTRIBUTE_BIN_INCLUDES, true, OUTPUT_DIR.append(ATTRIBUTE_BIN_INCLUDES));
			output.setAlias(ALIAS_OUTPUT);
			ActionArtifactBuilder manifest = mergeLicense.addProductArtifact(ATTRIBUTE_MANIFEST, true, OUTPUT_DIR_TEMP.append(ATTRIBUTE_MANIFEST));
			manifest.setAlias(ALIAS_MANIFEST);
			manifest.addPath(Path.fromPortableString(FEATURE_MANIFEST));
		}
	}

	private ActionBuilder createCopySiteFeaturesAction() throws CoreException {
		// Copy all features (excluding this one) to the features directory.
		//
		ActionBuilder copyFeatures = addAntAction(ACTION_COPY_SITE_FEATURES, TASK_COPY_GROUP, false);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_JAR, null, INCLUDE_TOP_FILTER);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_JAR, null, INCLUDE_TOP_SOURCE_FILTER);
		copyFeatures.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyFeatures.setProductAlias(ALIAS_OUTPUT);
		copyFeatures.setProductBase(OUTPUT_DIR_SITE.append(FEATURES_FOLDER));
		copyFeatures.setUpToDatePolicy(UpToDatePolicy.MAPPER);
		return copyFeatures;
	}

	private void createFeatureManifestAction(String actionName, boolean asPublic) throws CoreException {
		// Create the artifact that represents the original feature.xml file
		//
		IPath featureFile = new Path(FEATURE_MANIFEST);
		ArtifactBuilder rawManifest = getCSpec().addArtifact(ATTRIBUTE_RAW_MANIFEST, false, null);
		rawManifest.addPath(featureFile);

		// Create the action that creates the version expanded feature.xml
		//
		ActionBuilder manifest = addAntAction(actionName, TASK_EXPAND_FEATURE_VERSION, asPublic);
		manifest.addLocalPrerequisite(ATTRIBUTE_RAW_MANIFEST, ALIAS_MANIFEST);
		manifest.addLocalPrerequisite(ATTRIBUTE_BUNDLE_JARS, ALIAS_BUNDLES);
		manifest.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS, ALIAS_FEATURES);
		if (getCSpec().getAttribute(ATTRIBUTE_BUILD_PROPERTIES) != null)
			manifest.addLocalPrerequisite(ATTRIBUTE_BUILD_PROPERTIES, ALIAS_PROPERTIES);

		manifest.setProductAlias(ALIAS_OUTPUT);
		manifest.setProductBase(OUTPUT_DIR_TEMP.append(actionName));
		manifest.addProductPath(featureFile);
	}

	private void createFeatureSourceManifestAction(boolean translations) throws CoreException {
		// Create the action that creates the version expanded feature.xml for
		// features
		// and bundles that contains source code.
		//
		ActionBuilder manifest;
		IPath productCommonPath = OUTPUT_DIR_TEMP.append("source"); //$NON-NLS-1$
		if (translations) {
			manifest = addAntAction(ATTRIBUTE_SOURCE_MANIFEST + ".with.localization", TASK_CREATE_SOURCE_FEATURE, true); //$NON-NLS-1$
			ActionArtifactBuilder manifestResult = manifest.addProductArtifact(ATTRIBUTE_SOURCE_MANIFEST, true, productCommonPath);
			manifestResult.addPath(new Path(FEATURE_MANIFEST));
			manifestResult.setAlias(ALIAS_OUTPUT);
			ArtifactBuilder translatedResult = manifest.addProductArtifact(ATTRIBUTE_SOURCE_LOCALIZATION, true, productCommonPath);
			translatedResult.addPath(new Path("feature.properties")); //$NON-NLS-1$
			manifest.addLocalPrerequisite(ATTRIBUTE_BIN_INCLUDES, ALIAS_TRANSLATIONS);
		} else {
			manifest = addAntAction(ATTRIBUTE_SOURCE_MANIFEST, TASK_CREATE_SOURCE_FEATURE, true);
			manifest.setProductAlias(ALIAS_OUTPUT);
			manifest.setProductBase(productCommonPath);
			manifest.addProductPath(new Path(FEATURE_MANIFEST));
		}
		manifest.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
		manifest.addLocalPrerequisite(ATTRIBUTE_SOURCE_BUNDLE_JARS, ALIAS_BUNDLES);
		manifest.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS, ALIAS_FEATURES);
	}

	private void createSiteFeatureExportsAction() throws CoreException {
		GroupBuilder featureExports = getCSpec().getRequiredGroup(ATTRIBUTE_SITE_FEATURE_EXPORTS);
		featureExports.addLocalPrerequisite(createCopySiteFeaturesAction());
		featureExports.addLocalPrerequisite(ACTION_COPY_PLUGINS);
		featureExports.setPrerequisiteRebase(OUTPUT_DIR_SITE);
	}
}
