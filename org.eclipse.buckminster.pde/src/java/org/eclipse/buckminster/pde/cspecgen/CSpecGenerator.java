/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.cspecgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.PropertiesParser;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.jarprocessor.JarProcessorActor;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReaderType;
import org.eclipse.buckminster.pde.internal.TypedCollections;
import org.eclipse.buckminster.pde.tasks.P2SiteGenerator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.publisher.VersionedName;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ProductFile;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionFormat;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.pde.core.plugin.IFragment;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 * 
 */
@SuppressWarnings("restriction")
public abstract class CSpecGenerator implements IBuildPropertiesConstants, IPDEConstants
{
	public static final IPath OUTPUT_DIR_JAR = OUTPUT_DIR.append("jar"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SOURCE_JAR = OUTPUT_DIR.append("source.jar"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_FRAGMENTS = OUTPUT_DIR.append("fragments"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE = OUTPUT_DIR.append("site"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE_P2 = OUTPUT_DIR.append("site.p2"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE_REPACKED = OUTPUT_DIR.append("site.repacked"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE_PACKED = OUTPUT_DIR.append("site.packed"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE_SIGNED = OUTPUT_DIR.append("site.signed"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_TEMP = OUTPUT_DIR.append("temp"); //$NON-NLS-1$

	public static final String LAUNCHER_BUNDLE = "org.eclipse.equinox.launcher"; //$NON-NLS-1$

	public static final String LAUNCHER_FEATURE_3_2 = "org.eclipse.platform.launchers"; //$NON-NLS-1$

	public static final String LAUNCHER_FEATURE = "org.eclipse.equinox.executable"; //$NON-NLS-1$

	public static final Filter SOURCE_FILTER;

	public static final Filter SIGNING_ENABLED;

	public static final Filter SIGNING_DISABLED;

	public static final Filter PACK_ENABLED;

	public static final Filter PACK_DISABLED;

	public static final Filter SIGNING_AND_PACK_DISABLED;

	public static final Filter SIGNING_ENABLED_AND_PACK_DISABLED;

	static
	{
		try
		{
			SOURCE_FILTER = FilterFactory.newInstance("(!(cbi.include.source=false))"); //$NON-NLS-1$
			SIGNING_ENABLED = FilterFactory.newInstance("(site.signing=true)"); //$NON-NLS-1$
			SIGNING_DISABLED = FilterFactory.newInstance("(!(site.signing=true))"); //$NON-NLS-1$
			PACK_ENABLED = FilterFactory.newInstance("(site.pack200=true)"); //$NON-NLS-1$
			PACK_DISABLED = FilterFactory.newInstance("(!(site.pack200=true))"); //$NON-NLS-1$
			SIGNING_AND_PACK_DISABLED = FilterFactory.newInstance("(&(!(site.pack200=true))(!(site.signing=true)))"); //$NON-NLS-1$
			SIGNING_ENABLED_AND_PACK_DISABLED = FilterFactory
					.newInstance("(&(!(site.pack200=true))(site.signing=true))"); //$NON-NLS-1$
		}
		catch(InvalidSyntaxException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public static String convertMatchRule(int pdeMatchRule, String version) throws CoreException
	{
		version = Trivial.trim(version);
		if(version == null || version.equals("0.0.0")) //$NON-NLS-1$
			return null;

		char c = version.charAt(0);
		if(c == '[' || c == '(')
			//
			// Already an OSGi range, just ignore the rule then.
			//
			return version;

		Version v = Version.parseVersion(version);
		boolean qualifierTag = "qualifier".equals(v.getQualifier()); //$NON-NLS-1$
		if(qualifierTag)
			v = VersionHelper.replaceQualifier(v, null);

		StringBuilder vbld = new StringBuilder();
		switch(pdeMatchRule)
		{
		case IMatchRules.PERFECT:
			vbld.append('[');
			vbld.append(v);
			if(qualifierTag)
			{
				// Generate a version range that matches the given version with
				// any qualifier.
				//
				vbld.append(".0,"); //$NON-NLS-1$
				vbld.append(v.getMajor());
				vbld.append('.');
				vbld.append(v.getMinor());
				vbld.append('.');
				vbld.append(v.getMicro() + 1);
				vbld.append(')');
			}
			else
			{
				// Use a true explicit version range
				//
				vbld.append(',');
				vbld.append(v);
				vbld.append(']');
			}
			break;

		case IMatchRules.EQUIVALENT:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			vbld.append('[');
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
			vbld.append(',');
			vbld.append(v.getMajor());
			vbld.append('.');
			vbld.append(v.getMinor() + 1);
			vbld.append(".0)"); //$NON-NLS-1$
			break;

		case IMatchRules.COMPATIBLE:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			vbld.append('[');
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
			vbld.append(',');
			vbld.append(v.getMajor() + 1);
			vbld.append(".0.0)"); //$NON-NLS-1$
			break;
		default:
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
		}
		return vbld.toString();
	}

	protected static String buildArtifactName(String id, String ver, boolean asJar)
	{
		StringBuilder bld = new StringBuilder();
		bld.append(id);
		if(ver != null)
		{
			bld.append('_');
			bld.append(ver);
		}
		if(asJar)
			bld.append(".jar"); //$NON-NLS-1$
		else
			bld.append('/');
		return bld.toString();
	}

	private final CSpecBuilder m_cspecBuilder;

	private final ICatalogReader m_reader;

	private Map<String, String> m_properties;

	protected CSpecGenerator(CSpecBuilder cspecBuilder, ICatalogReader reader)
	{
		m_cspecBuilder = cspecBuilder;
		m_reader = reader;
	}

	public abstract void generate(IProgressMonitor monitor) throws CoreException;

	public CSpecBuilder getCSpec()
	{
		return m_cspecBuilder;
	}

	public ICatalogReader getReader()
	{
		return m_reader;
	}

	protected ActionBuilder addAntAction(String actionName, String targetName, boolean asPublic) throws CoreException
	{
		ActionBuilder action = m_cspecBuilder.addAction(actionName, asPublic, AntActor.ACTOR_ID, false);
		action.addActorProperty(AntActor.PROP_TARGETS, targetName, false);
		action.addActorProperty(AntActor.PROP_BUILD_FILE_ID, BUILD_FILE_ID, false);
		return action;
	}

	protected void addBundleHostDependency(IFragmentModel fragmentModel) throws CoreException
	{
		IFragment fragment = fragmentModel.getFragment();
		ComponentRequestBuilder bundleHostDep = m_cspecBuilder.createDependencyBuilder();
		bundleHostDep.setName(fragment.getPluginId());
		bundleHostDep
				.setVersionRange(VersionHelper.createRange(VersionFormat.OSGI_FORMAT, fragment.getPluginVersion()));
		bundleHostDep.setComponentTypeID(IComponentType.OSGI_BUNDLE);
		try
		{
			bundleHostDep.setFilter(FilterFactory.newInstance("(bundleHost=true)")); //$NON-NLS-1$
		}
		catch(InvalidSyntaxException e)
		{
			// This won't happen on that particular filter
		}
		addDependency(bundleHostDep);
	}

	protected boolean addDependency(ComponentRequestBuilder dependency) throws CoreException
	{
		return m_cspecBuilder.addDependency(dependency);
	}

	protected void addProductBundles(IProductDescriptor productDescriptor) throws CoreException
	{
		if(productDescriptor.useFeatures())
			return;

		List<VersionedName> bundles = TypedCollections.getProductBundles(productDescriptor, true);
		if(bundles.size() == 0)
			return;

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder fullClean = cspec.getRequiredGroup(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);

		String self = null;
		if(IComponentType.OSGI_BUNDLE.equals(cspec.getComponentTypeID()))
			self = cspec.getName();

		for(VersionedName bundle : bundles)
		{
			String pluginId = bundle.getId();
			if(self != null && self.equals(pluginId))
				continue;

			if(pluginId.equals("system.bundle")) //$NON-NLS-1$
				continue;

			ComponentRequestBuilder dependency = createDependency(bundle, IComponentType.OSGI_BUNDLE);
			if(skipComponent(query, dependency) || !addDependency(dependency))
				continue;

			String component = dependency.getName();
			fullClean.addExternalPrerequisite(component, ATTRIBUTE_FULL_CLEAN);
			bundleJars.addExternalPrerequisite(component, ATTRIBUTE_BUNDLE_JARS);
		}
	}

	protected void addProductFeatures(IProductDescriptor productDescriptor) throws CoreException
	{
		// Only the feature generator will act on this since adding features to a bundle
		// would cause circular dependencies. Buckminster requires that a feature based
		// product is placed in a feature.
		//
		// TODO: Perhaps print a warning?
	}

	protected boolean addProducts(final IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		try
		{
			List<FileHandle> productConfigs = m_reader.getRootFiles(PRODUCT_CONFIGURATION_FILE_PATTERN, MonitorUtils
					.subMonitor(monitor, 500));
			if(productConfigs.size() == 0)
				return false;

			boolean theOneAndOnly = productConfigs.size() == 1;
			int ticksPerConfig = 1500 / productConfigs.size();
			for(FileHandle productConfig : productConfigs)
				addProduct(productConfig, theOneAndOnly, MonitorUtils.subMonitor(monitor, ticksPerConfig));
			return theOneAndOnly;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	protected ActionBuilder createCopyPluginsAction() throws CoreException
	{
		// Copy all plug-ins that all features (including this one) is including.
		//
		ActionBuilder copyPlugins = addAntAction(ACTION_COPY_PLUGINS, TASK_COPY_GROUP, false);
		copyPlugins.addLocalPrerequisite(ATTRIBUTE_BUNDLE_JARS);
		if(isFeature())
			copyPlugins.addLocalPrerequisite(ATTRIBUTE_SOURCE_BUNDLE_JARS);
		copyPlugins.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyPlugins.setProductAlias(ALIAS_OUTPUT);
		copyPlugins.setProductBase(OUTPUT_DIR_SITE.append(PLUGINS_FOLDER));
		copyPlugins.setUpToDatePolicy(UpToDatePolicy.MAPPER);
		return copyPlugins;
	}

	protected ComponentRequestBuilder createDependency(IPluginReference pluginReference, String category)
			throws CoreException
	{
		return createDependency(pluginReference.getId(), category, pluginReference.getVersion(), pluginReference
				.getMatch(), null);
	}

	protected ComponentRequestBuilder createDependency(String name, String componentType, String versionDesignator,
			Filter filter) throws CoreException
	{
		versionDesignator = Trivial.trim(versionDesignator);
		if(versionDesignator != null && versionDesignator.equals("0.0.0")) //$NON-NLS-1$
			versionDesignator = null;

		ComponentRequestBuilder bld = getCSpec().createDependencyBuilder();
		bld.setName(name);
		bld.setComponentTypeID(componentType);
		bld.setVersionRange(VersionHelper.createRange(VersionFormat.OSGI_FORMAT, versionDesignator));
		bld.setFilter(filter);
		return bld;
	}

	protected ComponentRequestBuilder createDependency(String name, String componentType, String version,
			int pdeMatchRule, Filter filter) throws CoreException
	{
		return createDependency(name, componentType, convertMatchRule(pdeMatchRule, version), filter);
	}

	protected ComponentRequestBuilder createDependency(VersionedName versionedName, String componentType)
			throws CoreException
	{
		String versionRange = null;
		Version v = versionedName.getVersion();
		if(!(v == null || Version.emptyVersion.equals(v)))
			versionRange = new VersionRange(v, true, v, true).toString();
		return createDependency(versionedName.getId(), componentType, versionRange, null);
	}

	protected void createSiteAction(String rawSiteAttribute, String siteDefiningAttribute) throws CoreException
	{
		ActionBuilder siteBuilder = getCSpec().addAction(ATTRIBUTE_SITE_P2, true, ACTOR_P2_SITE_GENERATOR, false);

		siteBuilder.addLocalPrerequisite(rawSiteAttribute, P2SiteGenerator.ALIAS_SITE, SIGNING_AND_PACK_DISABLED);
		siteBuilder.addLocalPrerequisite(ATTRIBUTE_SITE_PACKED, P2SiteGenerator.ALIAS_SITE, PACK_ENABLED);
		siteBuilder.addLocalPrerequisite(ATTRIBUTE_SITE_SIGNED, P2SiteGenerator.ALIAS_SITE,
				SIGNING_ENABLED_AND_PACK_DISABLED);
		siteBuilder.addLocalPrerequisite(siteDefiningAttribute, P2SiteGenerator.ALIAS_SITE_DEFINER);
		siteBuilder.addLocalPrerequisite(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS, P2SiteGenerator.ALIAS_PRODUCT_CONFIGS);
		siteBuilder.setProductBase(OUTPUT_DIR_SITE_P2);
	}

	protected void createSitePackAction(String rawSiteAttribute) throws CoreException
	{
		ActionBuilder siteBuilder = getCSpec().addAction(ATTRIBUTE_SITE_PACKED, true, JarProcessorActor.ACTOR_ID, true);
		siteBuilder.addLocalPrerequisite(rawSiteAttribute, JarProcessorActor.ALIAS_JAR_FOLDER, SIGNING_DISABLED);
		siteBuilder.addLocalPrerequisite(ATTRIBUTE_SITE_SIGNED, JarProcessorActor.ALIAS_JAR_FOLDER, SIGNING_ENABLED);
		siteBuilder.getProperties().put(JarProcessorActor.PROP_COMMAND, JarProcessorActor.COMMAND_PACK);
		siteBuilder.setProductBase(OUTPUT_DIR_SITE_PACKED);
	}

	protected void createSiteRepackAction(String rawSiteAttribute) throws CoreException
	{
		ActionBuilder siteBuilder = getCSpec().addAction(ATTRIBUTE_SITE_REPACKED, false, JarProcessorActor.ACTOR_ID,
				true);
		siteBuilder.addLocalPrerequisite(rawSiteAttribute, JarProcessorActor.ALIAS_JAR_FOLDER);
		siteBuilder.getProperties().put(JarProcessorActor.PROP_COMMAND, JarProcessorActor.COMMAND_REPACK);
		siteBuilder.setProductBase(OUTPUT_DIR_SITE_REPACKED);
	}

	protected void createSiteSignAction(String rawSiteAttribute) throws CoreException
	{
		ActionBuilder siteBuilder = getCSpec().addAction(ATTRIBUTE_SITE_SIGNED, true, AntActor.ACTOR_ID, true);
		Map<String, String> actorProps = siteBuilder.getActorProperties();
		actorProps.put(AntActor.PROP_BUILD_FILE_ID, "buckminster.signing"); //$NON-NLS-1$
		actorProps.put(AntActor.PROP_TARGETS, "sign.jars"); //$NON-NLS-1$

		siteBuilder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		siteBuilder.addLocalPrerequisite(ATTRIBUTE_SITE_REPACKED, null, PACK_ENABLED);
		siteBuilder.addLocalPrerequisite(rawSiteAttribute, null, PACK_DISABLED);
		siteBuilder.setProductBase(OUTPUT_DIR_SITE_SIGNED);
		siteBuilder.setProductAlias(ALIAS_OUTPUT);
	}

	protected void createSiteZipAction() throws CoreException
	{
		ActionBuilder siteZip = addAntAction(ATTRIBUTE_SITE_ZIP, TASK_CREATE_SITE_ZIP, true);
		siteZip.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
		siteZip.addLocalPrerequisite(ATTRIBUTE_SITE_P2, ALIAS_REQUIREMENTS);
		siteZip.setProductBase(OUTPUT_DIR);
		siteZip.setProductAlias(ALIAS_OUTPUT);
	}

	protected String expand(String value) throws CoreException
	{
		value = TextUtils.notEmptyTrimmedString(value);
		if(value == null)
			return null;

		if(value.charAt(0) != '%')
			return value;

		if(m_properties == null)
		{
			try
			{
				m_properties = m_reader.readFile(getPropertyFileName(), new PropertiesParser(),
						new NullProgressMonitor());
			}
			catch(FileNotFoundException e)
			{
				m_properties = Collections.emptyMap();
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		String expValue = m_properties.get(value.substring(1));
		if(expValue != null)
			value = expValue;

		return value;
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ) throws CoreException
	{
		return generateRemoveDirAction(dirTag, dirPath, publ, "buckminster.rm." + dirTag + ".dir"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ, String actionName)
			throws CoreException
	{
		ActionBuilder rmDir = addAntAction(actionName, TASK_DELETE_DIR, publ);
		rmDir.addProperty(PROP_DELETE_DIR, dirPath.toPortableString(), false);
		return rmDir;
	}

	protected abstract String getProductOutputFolder(String productId);

	protected abstract String getPropertyFileName();

	protected Set<String> getRequiredBundleNames(BundleDescription bundleDesc)
	{
		HashSet<String> requiredBundles = null;
		if(bundleDesc != null)
		{
			BundleSpecification[] rqBundles = bundleDesc.getRequiredBundles();
			if(rqBundles != null && rqBundles.length > 0)
			{
				requiredBundles = new HashSet<String>();
				for(BundleSpecification rqBundle : rqBundles)
					requiredBundles.add(rqBundle.getName());
			}
		}
		return requiredBundles;
	}

	protected boolean isFeature()
	{
		return false;
	}

	protected void setFilter(String filterStr) throws CoreException
	{
		filterStr = TextUtils.notEmptyTrimmedString(filterStr);
		if(filterStr == null)
			return;

		try
		{
			Filter filter = FilterFactory.newInstance(filterStr);
			getCSpec().setFilter(FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX)); //$NON-NLS-1$
		}
		catch(InvalidSyntaxException e)
		{
			NodeQuery query = m_reader.getNodeQuery();
			IStatus status = new Status(IStatus.WARNING, PDEPlugin.getPluginId(),
					Messages.manifest_has_malformed_LDAP_rule_for + ICoreConstants.PLATFORM_FILTER
							+ ": " + e.getMessage()); //$NON-NLS-1$
			RMContext ctx = query.getContext();
			if(!ctx.isContinueOnError())
				throw new CoreException(status);
			ctx.addRequestStatus(query.getComponentRequest(), status);
		}
	}

	protected boolean skipComponent(ComponentQuery query, ComponentRequestBuilder bld)
	{
		return query.skipComponent(new ComponentName(bld.getName(), bld.getComponentTypeID()));
	}

	private void addProduct(FileHandle productConfig, boolean theOneAndOnly, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		try
		{
			File productConfigFile = productConfig.getFile();
			IProductDescriptor productDescriptor;
			try
			{
				productDescriptor = new ProductFile(productConfigFile.getAbsolutePath());
			}
			catch(RuntimeException e)
			{
				throw e;
			}
			catch(IOException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}

			CSpecBuilder cspec = getCSpec();
			ArtifactBuilder productConfigArtifact = cspec.addArtifact(productDescriptor.getId(), false, null, null);
			productConfigArtifact.addPath(Path.fromOSString(productConfigFile.getName()));
			GroupBuilder productConfigs = cspec.getGroup(ATTRIBUTE_PRODUCT_CONFIGS);
			if(productConfigs == null)
			{
				productConfigs = cspec.addGroup(ATTRIBUTE_PRODUCT_CONFIGS, false);
				cspec.getRequiredGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS).addLocalPrerequisite(productConfigs);
			}
			productConfigs.addLocalPrerequisite(productConfigArtifact);
			if(productDescriptor.useFeatures())
				addProductFeatures(productDescriptor);
			else
				addProductBundles(productDescriptor);

			if(!theOneAndOnly)
				// We're done here.
				return;

			if(!isFeature())
			{
				// This bundle must be able to create a site for its product
				//
				GroupBuilder featureExports = cspec.addGroup(ATTRIBUTE_FEATURE_EXPORTS, true);
				featureExports.addLocalPrerequisite(createCopyPluginsAction());
				featureExports.setPrerequisiteRebase(OUTPUT_DIR_SITE);

				IFeatureModel launcherFeature = EclipsePlatformReaderType.getBestFeature(LAUNCHER_FEATURE, null, null);
				if(launcherFeature == null)
					launcherFeature = EclipsePlatformReaderType.getBestFeature(LAUNCHER_FEATURE_3_2, null, null);

				if(launcherFeature != null)
				{
					IFeature feature = launcherFeature.getFeature();
					Version version = Version.parseVersion(feature.getVersion());
					ComponentRequestBuilder dep = createDependency(feature.getId(), IComponentType.ECLIPSE_FEATURE,
							version.toString(), IMatchRules.PERFECT, null);

					// Ensure that a dependency exists to the executable feature.
					//
					if(addDependency(dep))
						featureExports.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_EXPORTS);
				}
			}
			createSiteRepackAction(ATTRIBUTE_FEATURE_EXPORTS);
			createSiteSignAction(ATTRIBUTE_FEATURE_EXPORTS);
			createSitePackAction(ATTRIBUTE_FEATURE_EXPORTS);
			createSiteAction(ATTRIBUTE_FEATURE_EXPORTS, productConfigArtifact.getName());
			createSiteZipAction();
		}
		finally
		{
			if(productConfig.isTemporary())
				productConfig.getFile().delete();
		}
	}
}
