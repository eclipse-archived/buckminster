/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.cspecgen.feature;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.internal.TypedCollections;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class CSpecFromSource extends CSpecGenerator
{
	private static boolean isListOK(String list, Object item)
	{
		if(list == null || list.length() == 0)
			return true;
		StringTokenizer tokens = new StringTokenizer(list, ","); //$NON-NLS-1$
		while(tokens.hasMoreTokens())
			if(item.equals("*") || item.equals(tokens.nextElement())) //$NON-NLS-1$
				return true;
		return false;
	}

	private final Map<String, String> m_buildProperties;

	private final IFeature m_feature;

	protected CSpecFromSource(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature,
			Map<String, String> buildProperties)
	{
		super(cspecBuilder, reader);
		m_feature = feature;
		m_buildProperties = buildProperties;
	}

	@Override
	public void generate(IProgressMonitor monitor) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		cspec.setName(m_feature.getId());
		cspec.setVersion(VersionHelper.parseVersion(m_feature.getVersion()));
		cspec.setComponentTypeID(IComponentType.ECLIPSE_FEATURE);
		cspec.setFilter(FilterUtils.createFilter(m_feature.getOS(), m_feature.getWS(), m_feature.getArch(),
				m_feature.getNL()));

		// This feature and all included features. Does not imply copying since
		// the group will reference the features where they are found.
		//
		cspec.addGroup(ATTRIBUTE_FEATURE_REFS, true); // without self
		cspec.addGroup(ATTRIBUTE_SOURCE_FEATURE_REFS, true).setFilter(SOURCE_FILTER);

		// All bundles imported by this feature and all included features. Does
		// not imply copying since the group will reference the bundles where they
		// are found.
		//
		cspec.addGroup(ATTRIBUTE_BUNDLE_JARS, true);

		// Source of all bundles imported by this feature and all included features.
		//
		cspec.addGroup(ATTRIBUTE_SOURCE_BUNDLE_JARS, true).setFilter(SOURCE_FILTER);

		// This feature, its imported bundles, all included features, and their
		// imported bundles copied into a site structure (with a plugins and a
		// features folder).
		//
		cspec.addGroup(ATTRIBUTE_FEATURE_EXPORTS, true);
		cspec.addGroup(ATTRIBUTE_SITE_FEATURE_EXPORTS, true);

		cspec.addGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS, true);
		generateRemoveDirAction("build", OUTPUT_DIR, true, ATTRIBUTE_FULL_CLEAN); //$NON-NLS-1$

		addFeatures();
		addPlugins();

		MonitorUtils.begin(monitor, 100);
		if(m_buildProperties == null)
		{
			ArtifactBuilder binIncludes = null;
			for(String path : getReader().list(MonitorUtils.subMonitor(monitor, 20)))
			{
				if(FEATURE_FILE.equals(path))
					//
					// Handled separately
					//
					continue;

				if(binIncludes == null)
					binIncludes = getCSpec().addArtifact(ATTRIBUTE_JAR_CONTENTS, false, null, null);
				binIncludes.addPath(new Path(path));
			}
		}
		else
		{
			cspec.addArtifact(ATTRIBUTE_BUILD_PROPERTIES, false, null, null).addPath(new Path(BUILD_PROPERTIES_FILE));
			for(Map.Entry<String, String> entry : m_buildProperties.entrySet())
			{
				String key = entry.getKey();
				if(IBuildEntry.BIN_INCLUDES.equals(key))
				{
					createBinIncludesArtifact(entry.getValue());
					continue;
				}
			}
			MonitorUtils.worked(monitor, 20);
		}

		createFeatureManifestAction();
		createFeatureJarAction();
		createFeatureSourceManifestAction();
		createFeatureSourceJarAction();
		createFeatureExportsAction();
		createSiteFeatureExportsAction();

		if(!addProducts(MonitorUtils.subMonitor(monitor, 80)))
		{
			// No product defined a site so we add the actions for that
			// here.
			//
			createSiteRepackAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSiteSignAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSitePackAction(ATTRIBUTE_SITE_FEATURE_EXPORTS);
			createSiteAction(ATTRIBUTE_SITE_FEATURE_EXPORTS, ATTRIBUTE_MANIFEST);
			createSiteZipAction();
		}
		MonitorUtils.done(monitor);
	}

	@Override
	protected void addProductFeatures(IProductDescriptor productDescriptor) throws CoreException
	{
		if(!productDescriptor.useFeatures())
			return;

		List<VersionedName> features = TypedCollections.getProductFeatures(productDescriptor);
		if(features.size() == 0)
			return;

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder featureRefs = cspec.getRequiredGroup(ATTRIBUTE_FEATURE_REFS);
		GroupBuilder featureSourceRefs = cspec.getRequiredGroup(ATTRIBUTE_SOURCE_FEATURE_REFS);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		GroupBuilder sourceBundleJars = cspec.getRequiredGroup(ATTRIBUTE_SOURCE_BUNDLE_JARS);

		String self = cspec.getName();
		for(VersionedName feature : features)
		{
			if(feature.getId().equals(self))
				continue;

			ComponentRequestBuilder dep = createDependency(feature, IComponentType.ECLIPSE_FEATURE);
			if(skipComponent(query, dep))
				continue;

			cspec.addDependency(dep);
			featureRefs.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_FEATURE_JARS);
			featureSourceRefs.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_SOURCE_FEATURE_JARS);
			bundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_BUNDLE_JARS);
			sourceBundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_SOURCE_BUNDLE_JARS);
			fullClean.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_FULL_CLEAN);
		}
	}

	@Override
	protected String getProductOutputFolder(String productId)
	{
		return m_buildProperties == null
				? null
				: m_buildProperties.get(productId + TOP_FOLDER_SUFFIX);
	}

	@Override
	protected String getPropertyFileName()
	{
		return FEATURE_PROPERTIES_FILE;
	}

	@Override
	protected boolean isFeature()
	{
		return true;
	}

	void addFeatures() throws CoreException
	{
		IFeatureChild[] features = m_feature.getIncludedFeatures();
		if(features == null || features.length == 0)
			return;

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder featureRefs = cspec.getRequiredGroup(ATTRIBUTE_FEATURE_REFS);
		GroupBuilder featureSourceRefs = cspec.getRequiredGroup(ATTRIBUTE_SOURCE_FEATURE_REFS);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		GroupBuilder sourceBundleJars = cspec.getRequiredGroup(ATTRIBUTE_SOURCE_BUNDLE_JARS);
		GroupBuilder productConfigExports = cspec.getRequiredGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);
		for(IFeatureChild feature : features)
		{
			ComponentRequestBuilder dep = createDependency(feature);
			if(skipComponent(query, dep))
				continue;

			cspec.addDependency(dep);
			featureRefs.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_FEATURE_JARS);
			featureSourceRefs.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_SOURCE_FEATURE_JARS);
			bundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_BUNDLE_JARS);
			sourceBundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_SOURCE_BUNDLE_JARS);
			fullClean.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_FULL_CLEAN);
			productConfigExports.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);
		}
	}

	void addPlugins() throws CoreException
	{
		IFeaturePlugin[] plugins = m_feature.getPlugins();
		if(plugins == null || plugins.length == 0)
			return;

		Map<String, ? extends Object> props = getReader().getNodeQuery().getProperties();
		Object os = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_OS);
		Object ws = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_WS);
		Object arch = props.get(org.eclipse.buckminster.core.TargetPlatform.TARGET_ARCH);

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		GroupBuilder sourceBundleJars = cspec.getRequiredGroup(ATTRIBUTE_SOURCE_BUNDLE_JARS);
		GroupBuilder productConfigExports = cspec.getRequiredGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);
		PluginModelManager manager = PDECore.getDefault().getModelManager();

		String id = m_feature.getId();
		boolean hasBogusFragments = "org.eclipse.platform".equals(id) //$NON-NLS-1$
				|| "org.eclipse.equinox.executable".equals(id) //$NON-NLS-1$
				|| "org.eclipse.rcp".equals(id); //$NON-NLS-1$
		for(IFeaturePlugin plugin : plugins)
		{
			if(!(isListOK(plugin.getOS(), os) && isListOK(plugin.getWS(), ws) && isListOK(plugin.getArch(), arch)))
			{
				// Only include this if we can find it in the target platform
				//
				if(manager.findEntry(plugin.getId()) == null)
					continue;
			}

			if(hasBogusFragments && (plugin.getOS() != null || plugin.getWS() != null || plugin.getArch() != null))
			{
				// Only include this if we can find it in the target platform. See
				// https://bugs.eclipse.org/bugs/show_bug.cgi?id=213437
				//
				if(manager.findEntry(plugin.getId()) == null)
					continue;
			}

			ComponentRequestBuilder dep = createDependency(plugin);
			if(skipComponent(query, dep))
				continue;

			if(!addDependency(dep))
				continue;

			bundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(), ATTRIBUTE_BUNDLE_AND_FRAGMENTS);
			sourceBundleJars.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_BUNDLE_AND_FRAGMENTS_SOURCE);
			fullClean.addExternalPrerequisite(dep.getName(), IComponentType.OSGI_BUNDLE, ATTRIBUTE_FULL_CLEAN);
			productConfigExports.addExternalPrerequisite(dep.getName(), dep.getComponentTypeID(),
					ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);
		}
	}

	ComponentRequestBuilder createDependency(IFeatureChild feature) throws CoreException
	{
		Filter filter = FilterUtils.createFilter(feature.getOS(), feature.getWS(), feature.getArch(), feature.getNL());
		return createDependency(feature.getId(), IComponentType.ECLIPSE_FEATURE, feature.getVersion(),
				feature.getMatch(), filter);
	}

	ComponentRequestBuilder createDependency(IFeaturePlugin plugin) throws CoreException
	{
		Filter filter = FilterUtils.createFilter(plugin.getOS(), plugin.getWS(), plugin.getArch(), plugin.getNL());
		return createDependency(plugin.getId(), IComponentType.OSGI_BUNDLE, plugin.getVersion(), IMatchRules.PERFECT,
				filter);
	}

	private void createBinIncludesArtifact(String binIncludesStr) throws CoreException
	{
		ArtifactBuilder binIncludes = null;
		StringTokenizer tokens = new StringTokenizer(binIncludesStr, ","); //$NON-NLS-1$
		while(tokens.hasMoreTokens())
		{
			String path = tokens.nextToken().trim();
			if(FEATURE_FILE.equals(path))
				//
				// Handled separately
				//
				continue;

			if(binIncludes == null)
				binIncludes = getCSpec().addArtifact(ATTRIBUTE_JAR_CONTENTS, false, null, null);

			binIncludes.addPath(new Path(path));
		}
	}

	private ActionBuilder createCopyFeaturesAction() throws CoreException
	{
		// Copy all features (including this one) to the features directory.
		//
		ActionBuilder copyFeatures = addAntAction(ACTION_COPY_FEATURES, TASK_COPY_GROUP, false);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_JARS);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_JARS);
		copyFeatures.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyFeatures.setProductAlias(ALIAS_OUTPUT);
		copyFeatures.setProductBase(OUTPUT_DIR_SITE.append(FEATURES_FOLDER));
		copyFeatures.setUpToDatePolicy(UpToDatePolicy.MAPPER);
		return copyFeatures;
	}

	private ActionBuilder createCopySiteFeaturesAction() throws CoreException
	{
		// Copy all features (excluding this one) to the features directory.
		//
		ActionBuilder copyFeatures = addAntAction(ACTION_COPY_SITE_FEATURES, TASK_COPY_GROUP, false);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_JAR, null, INCLUDE_TOP_FILTER);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_JAR, null, INCLUDE_TOP_FILTER);
		copyFeatures.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyFeatures.setProductAlias(ALIAS_OUTPUT);
		copyFeatures.setProductBase(OUTPUT_DIR_SITE.append(FEATURES_FOLDER));
		copyFeatures.setUpToDatePolicy(UpToDatePolicy.MAPPER);
		return copyFeatures;
	}

	private void createFeatureExportsAction() throws CoreException
	{
		GroupBuilder featureExports = getCSpec().getRequiredGroup(ATTRIBUTE_FEATURE_EXPORTS);
		featureExports.addLocalPrerequisite(createCopyFeaturesAction());
		featureExports.addLocalPrerequisite(createCopyPluginsAction());
		featureExports.setPrerequisiteRebase(OUTPUT_DIR_SITE);
	}

	private void createFeatureJarAction() throws CoreException
	{
		CSpecBuilder cspec = getCSpec();

		// Create the action that builds the final jar file for the feature
		//
		ActionBuilder featureJarBuilder = addAntAction(ATTRIBUTE_FEATURE_JAR, TASK_CREATE_FEATURE_JAR, false);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);

		if(cspec.getArtifactBuilder(ATTRIBUTE_JAR_CONTENTS) != null)
			featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_JAR_CONTENTS);
		featureJarBuilder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

		featureJarBuilder.setProductAlias(ALIAS_OUTPUT);
		featureJarBuilder.setProductBase(OUTPUT_DIR_JAR);
		featureJarBuilder.setUpToDatePolicy(UpToDatePolicy.COUNT);
		featureJarBuilder.setProductFileCount(1);

		GroupBuilder featureJars = cspec.addGroup(ATTRIBUTE_FEATURE_JARS, true); // including self
		featureJars.addLocalPrerequisite(featureJarBuilder);
		featureJars.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS);
	}

	private void createFeatureManifestAction() throws CoreException
	{
		// Create the artifact that represents the original feature.xml file
		//
		IPath featureFile = new Path(FEATURE_FILE);
		ArtifactBuilder rawManifest = getCSpec().addArtifact(ATTRIBUTE_RAW_MANIFEST, false, null, null);
		rawManifest.addPath(featureFile);

		// Create the action that creates the version expanded feature.xml
		//
		ActionBuilder manifest = addAntAction(ATTRIBUTE_MANIFEST, TASK_EXPAND_FEATURE_VERSION, true);
		manifest.addLocalPrerequisite(ATTRIBUTE_RAW_MANIFEST, ALIAS_MANIFEST);
		manifest.addLocalPrerequisite(ATTRIBUTE_BUNDLE_JARS, ALIAS_BUNDLES);
		manifest.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS, ALIAS_FEATURES);
		if(getCSpec().getAttribute(ATTRIBUTE_BUILD_PROPERTIES) != null)
			manifest.addLocalPrerequisite(ATTRIBUTE_BUILD_PROPERTIES, ALIAS_PROPERTIES);

		manifest.setProductAlias(ALIAS_OUTPUT);
		manifest.setProductBase(OUTPUT_DIR_TEMP);
		manifest.addProductPath(featureFile);
	}

	private void createFeatureSourceJarAction() throws CoreException
	{
		CSpecBuilder cspec = getCSpec();

		// Create the action that builds the jar file with all source bundles for the feature
		//
		ActionBuilder featureJarBuilder = addAntAction(ATTRIBUTE_SOURCE_FEATURE_JAR, TASK_CREATE_FEATURE_JAR, false);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_SOURCE_MANIFEST, ALIAS_MANIFEST);

		// We use the same content as the original feature (i.e. license, etc.).
		//
		if(cspec.getArtifactBuilder(ATTRIBUTE_JAR_CONTENTS) != null)
			featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_JAR_CONTENTS);
		featureJarBuilder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

		featureJarBuilder.setProductAlias(ALIAS_OUTPUT);
		featureJarBuilder.setProductBase(OUTPUT_DIR_SOURCE_JAR);
		featureJarBuilder.setUpToDatePolicy(UpToDatePolicy.COUNT);
		featureJarBuilder.setProductFileCount(1);

		GroupBuilder featureJars = cspec.addGroup(ATTRIBUTE_SOURCE_FEATURE_JARS, true); // including self
		featureJars.addLocalPrerequisite(featureJarBuilder);
		featureJars.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS);
	}

	private void createFeatureSourceManifestAction() throws CoreException
	{
		// Create the action that creates the version expanded feature.xml for features
		// and bundles that contains source code.
		//
		ActionBuilder manifest = addAntAction(ATTRIBUTE_SOURCE_MANIFEST, TASK_CREATE_SOURCE_FEATURE, true);
		manifest.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
		manifest.addLocalPrerequisite(ATTRIBUTE_SOURCE_BUNDLE_JARS, ALIAS_BUNDLES);
		manifest.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS, ALIAS_FEATURES);
		manifest.setProductAlias(ALIAS_OUTPUT);
		manifest.setProductBase(OUTPUT_DIR_TEMP.append("source")); //$NON-NLS-1$
		manifest.addProductPath(new Path(FEATURE_FILE));
	}

	private void createSiteFeatureExportsAction() throws CoreException
	{
		GroupBuilder featureExports = getCSpec().getRequiredGroup(ATTRIBUTE_SITE_FEATURE_EXPORTS);
		featureExports.addLocalPrerequisite(createCopySiteFeaturesAction());
		featureExports.addLocalPrerequisite(ACTION_COPY_PLUGINS);
		featureExports.setPrerequisiteRebase(OUTPUT_DIR_SITE);
	}
}
