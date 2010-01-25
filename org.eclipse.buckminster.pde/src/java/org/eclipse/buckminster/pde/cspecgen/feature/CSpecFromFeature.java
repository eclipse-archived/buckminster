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
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public abstract class CSpecFromFeature extends CSpecGenerator
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

	private final IFeature m_feature;

	protected CSpecFromFeature(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature)
	{
		super(cspecBuilder, reader);
		m_feature = feature;
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

		createFeatureJarAction(MonitorUtils.subMonitor(monitor, 20));
		createFeatureSourceJarAction();
		createFeatureExportsAction();

		GroupBuilder featureJars = cspec.addGroup(ATTRIBUTE_FEATURE_JARS, true); // including self
		featureJars.addLocalPrerequisite(ATTRIBUTE_FEATURE_JAR);
		featureJars.addLocalPrerequisite(ATTRIBUTE_FEATURE_REFS);

		GroupBuilder sourceFeatureJars = cspec.addGroup(ATTRIBUTE_SOURCE_FEATURE_JARS, true); // including self
		sourceFeatureJars.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_JAR);
		sourceFeatureJars.addLocalPrerequisite(ATTRIBUTE_SOURCE_FEATURE_REFS);

		createSiteActions(MonitorUtils.subMonitor(monitor, 80));
		MonitorUtils.done(monitor);
	}

	@Override
	protected void addProductFeatures(IProductDescriptor productDescriptor) throws CoreException
	{
		if(!productDescriptor.useFeatures())
			return;

		List<IVersionedId> features = productDescriptor.getFeatures();
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
		for(IVersionedId feature : features)
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
		if(feature.isOptional())
			filter = ComponentRequest.P2_OPTIONAL_FILTER.addFilterWithAnd(filter);
		return createDependency(feature.getId(), IComponentType.ECLIPSE_FEATURE, feature.getVersion(),
				feature.getMatch(), filter);
	}

	ComponentRequestBuilder createDependency(IFeaturePlugin plugin) throws CoreException
	{
		Filter filter = FilterUtils.createFilter(plugin.getOS(), plugin.getWS(), plugin.getArch(), plugin.getNL());
		return createDependency(plugin.getId(), IComponentType.OSGI_BUNDLE, plugin.getVersion(), IMatchRules.PERFECT,
				filter);
	}

	abstract void createFeatureJarAction(IProgressMonitor monitor) throws CoreException;

	abstract void createFeatureSourceJarAction() throws CoreException;

	abstract void createSiteActions(IProgressMonitor monitor) throws CoreException;

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

	private void createFeatureExportsAction() throws CoreException
	{
		GroupBuilder featureExports = getCSpec().getRequiredGroup(ATTRIBUTE_FEATURE_EXPORTS);
		featureExports.addLocalPrerequisite(createCopyFeaturesAction());
		featureExports.addLocalPrerequisite(createCopyPluginsAction());
		featureExports.setPrerequisiteRebase(OUTPUT_DIR_SITE);
	}
}
