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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.pde.core.IModelChangedEvent;
import org.eclipse.pde.core.IModelChangedListener;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureImport;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class FeatureConsolidator extends GroupConsolidator implements IModelChangedListener {
	private final EditableFeatureModel featureModel;

	public FeatureConsolidator(File inputFile, File outputFile, File propertiesFile, List<File> featuresAndBundles, String qualifier,
			boolean generateVersionSuffix, int maxVersionSuffixLength, int significantDigits) throws CoreException {
		super(outputFile, propertiesFile, featuresAndBundles, qualifier, generateVersionSuffix, maxVersionSuffixLength, significantDigits);
		featureModel = FeatureModelReader.readEditableFeatureModel(inputFile);
		featureModel.addModelChangedListener(this);
	}

	@Override
	public void modelChanged(IModelChangedEvent event) {
		featureModel.setDirty(true);
	}

	public void run() throws CoreException, FileNotFoundException {
		IFeature feature = featureModel.getFeature();
		String id = feature.getId();

		Map<String, Version[]> featureVers = getFeatureVersions();
		ArrayList<ComponentIdentifier> deps = new ArrayList<ComponentIdentifier>();
		for (IFeatureChild ref : feature.getIncludedFeatures()) {
			String vstr = ref.getVersion();
			Version version = findBestVersion(featureVers, id, "feature", ref.getId(), vstr); //$NON-NLS-1$
			if (version != null) {
				ComponentIdentifier cid = new ComponentIdentifier(id, IComponentType.ECLIPSE_FEATURE, version);
				deps.add(cid);
				String nvstr = cid.getVersion().toString();
				if (!nvstr.equals(vstr))
					ref.setVersion(nvstr);
			}
		}

		Map<String, Version[]> pluginVers = getPluginVersions();
		for (IFeaturePlugin ref : feature.getPlugins()) {
			String vstr = ref.getVersion();
			Version version = findBestVersion(pluginVers, id, "plugin", ref.getId(), vstr); //$NON-NLS-1$
			if (version != null) {
				ComponentIdentifier cid = new ComponentIdentifier(id, IComponentType.OSGI_BUNDLE, version);
				deps.add(cid);
				String nvstr = cid.getVersion().toString();
				if (!nvstr.equals(vstr))
					ref.setVersion(nvstr);
			}
		}
		consolidateFeatureVersion(deps);
		featureModel.save(getOutputFile());
	}

	private void consolidateFeatureVersion(List<ComponentIdentifier> deps) throws CoreException {
		IFeature feature = featureModel.getFeature();
		String versionStr = feature.getVersion();
		if (versionStr == null)
			return;

		Version version;
		try {
			version = Version.parseVersion(versionStr);
		} catch (IllegalArgumentException e) {
			return;
		}

		if (versionStr.endsWith(PROPERTY_QUALIFIER)) {
			ComponentIdentifier ci = new ComponentIdentifier(feature.getId(), IComponentType.ECLIPSE_FEATURE, version);
			Version newVersion = replaceQualifier(ci, deps);
			if (newVersion != null && !version.equals(newVersion)) {
				String newVersionStr = newVersion.toString();
				feature.setVersion(newVersionStr);
				if (isContextReplacement()) {
					int lastDot = versionStr.lastIndexOf("."); //$NON-NLS-1$
					featureModel.setContextQualifierLength(newVersionStr.length() - lastDot - 1);
				}
				version = newVersion;
			}
			if (isUsingGenerator(ci))
				return;
		}

		if (featureModel.getContextQualifierLength() == -1)
			return;

		IFeatureChild[] features = feature.getIncludedFeatures();
		List<IVersionedId> featureList;
		if (features.length == 0)
			featureList = Collections.emptyList();
		else {
			featureList = new ArrayList<IVersionedId>(features.length);
			for (IFeatureChild f : features)
				featureList.add(new VersionedId(f.getId(), f.getVersion()));
		}

		IFeatureImport[] bundles = feature.getImports();
		List<IVersionedId> bundleList;
		if (features.length == 0)
			bundleList = Collections.emptyList();
		else {
			bundleList = new ArrayList<IVersionedId>(bundles.length);
			for (IFeatureImport f : bundles)
				bundleList.add(new VersionedId(f.getId(), f.getVersion()));
		}

		String suffix = generateFeatureVersionSuffix(featureList, bundleList);
		if (suffix == null)
			return;

		String qualifier = VersionHelper.getQualifier(version);
		if (qualifier == null)
			qualifier = suffix;
		else {
			StringBuilder bld = new StringBuilder();
			bld.append(qualifier, 0, featureModel.getContextQualifierLength());
			bld.append('-');
			bld.append(suffix);
			qualifier = bld.toString();
		}
		feature.setVersion(VersionHelper.replaceQualifier(version, qualifier).toString());
	}
}
