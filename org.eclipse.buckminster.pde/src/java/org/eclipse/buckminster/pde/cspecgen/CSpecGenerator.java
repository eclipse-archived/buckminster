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
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
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
import org.eclipse.buckminster.pde.tasks.FeaturesAction;
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
import org.eclipse.equinox.internal.p2.metadata.VersionFormat;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ProductFile;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.core.plugin.IFragment;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.ibundle.IBundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.text.bundle.ManifestHeader;
import org.eclipse.pde.internal.core.text.bundle.RequireBundleObject;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 * 
 */
@SuppressWarnings("restriction")
public abstract class CSpecGenerator implements IBuildPropertiesConstants, IPDEConstants
{
	public static class ImportSpecification
	{
		private final String m_name;

		private final VersionRange m_range;

		private final boolean m_exported;

		private final boolean m_optional;

		ImportSpecification(BundleSpecification bundleSpec)
		{
			m_name = bundleSpec.getName();
			m_range = VersionRange.fromOSGiVersionRange(bundleSpec.getVersionRange());
			m_exported = bundleSpec.isExported();
			m_optional = bundleSpec.isOptional();
		}

		ImportSpecification(String name, VersionRange range, boolean exported, boolean optional)
		{
			m_name = name;
			m_range = range;
			m_exported = exported;
			m_optional = optional;
		}

		public String getName()
		{
			return m_name;
		}

		public VersionRange getVersionRange()
		{
			return m_range;
		}

		public boolean isExported()
		{
			return m_exported;
		}

		public boolean isOptional()
		{
			return m_optional;
		}
	}

	public static final IPath OUTPUT_DIR_JAR = OUTPUT_DIR.append("jar"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SOURCE_JAR = OUTPUT_DIR.append("source.jar"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_FRAGMENTS = OUTPUT_DIR.append("fragments"); //$NON-NLS-1$

	public static final IPath OUTPUT_DIR_SITE = OUTPUT_DIR.append("site"); //$NON-NLS-1$

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

	public static final Filter INCLUDE_TOP_FILTER;

	public static final Filter INCLUDE_TOP_SOURCE_FILTER;

	static
	{
		try
		{
			SOURCE_FILTER = FilterFactory.newInstance("(!(cbi.include.source=false))"); //$NON-NLS-1$
			INCLUDE_TOP_FILTER = FilterFactory.newInstance("(site.include.top=true)"); //$NON-NLS-1$
			INCLUDE_TOP_SOURCE_FILTER = FilterFactory.newInstance("(&(site.include.top=true)(!(cbi.include.source=false)))"); //$NON-NLS-1$
			SIGNING_ENABLED = FilterFactory.newInstance("(site.signing=true)"); //$NON-NLS-1$
			SIGNING_DISABLED = FilterFactory.newInstance("(!(site.signing=true))"); //$NON-NLS-1$
			PACK_ENABLED = FilterFactory.newInstance("(site.pack200=true)"); //$NON-NLS-1$
			PACK_DISABLED = FilterFactory.newInstance("(!(site.pack200=true))"); //$NON-NLS-1$
			SIGNING_AND_PACK_DISABLED = FilterFactory.newInstance("(&(!(site.pack200=true))(!(site.signing=true)))"); //$NON-NLS-1$
			SIGNING_ENABLED_AND_PACK_DISABLED = FilterFactory.newInstance("(&(!(site.pack200=true))(site.signing=true))"); //$NON-NLS-1$
		}
		catch(InvalidSyntaxException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	private static final ImportSpecification[] s_noRequiredBundles = new ImportSpecification[0];

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

	protected static ImportSpecification[] getImports(IPluginBase plugin) throws CoreException
	{
		IPluginModelBase model = plugin.getPluginModel();
		BundleDescription bundleDesc = model.getBundleDescription();
		BundleSpecification[] imports;
		if(bundleDesc != null)
		{
			imports = bundleDesc.getRequiredBundles();
			if(imports == null)
				return s_noRequiredBundles;

			int sz = imports.length;
			if(sz == 0)
				return s_noRequiredBundles;

			ImportSpecification[] importSpecs = new ImportSpecification[sz];
			while(--sz >= 0)
				importSpecs[sz] = new ImportSpecification(imports[sz]);
			return importSpecs;
		}

		if(!(model instanceof IBundlePluginModelBase))
			return s_noRequiredBundles;

		IBundleModel bundleModel = ((IBundlePluginModelBase)model).getBundleModel();
		ManifestHeader header = (ManifestHeader)bundleModel.getBundle().getManifestHeader(Constants.REQUIRE_BUNDLE);
		if(header == null)
			return s_noRequiredBundles;

		ManifestElement[] elems = null;
		try
		{
			elems = ManifestElement.parseHeader(header.getKey(), header.getValue());
		}
		catch(BundleException e)
		{
		}
		if(elems == null)
			return s_noRequiredBundles;

		int sz = elems.length;
		if(sz == 0)
			return s_noRequiredBundles;

		ImportSpecification[] importSpecs = new ImportSpecification[sz];
		while(--sz >= 0)
		{
			RequireBundleObject r = new RequireBundleObject(header, elems[sz]);
			importSpecs[sz] = new ImportSpecification(r.getId(), new VersionRange(r.getVersion()), r.isReexported(),
					r.isOptional());
		}
		return importSpecs;
	}

	private final CSpecBuilder m_cspecBuilder;

	private final ICatalogReader m_reader;

	private Map<String, String> m_properties;

	protected CSpecGenerator(CSpecBuilder cspecBuilder, ICatalogReader reader)
	{
		m_cspecBuilder = cspecBuilder;
		m_reader = reader;
	}

	public String convertMatchRule(int pdeMatchRule, String version) throws CoreException
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
		boolean qualifierTag = "qualifier".equals(VersionHelper.getQualifier(v)); //$NON-NLS-1$
		if(qualifierTag)
			v = VersionHelper.replaceQualifier(v, null);

		org.osgi.framework.Version ov = Version.toOSGiVersion(v);
		boolean retainLowerBound = true;
		if(pdeMatchRule == IMatchRules.NONE)
		{
			Map<String, String> props = getProperties();
			pdeMatchRule = FeaturesAction.getMatchRule(props.get(PROP_PDE_MATCH_RULE_DEFAULT));
			String rtl = props.get(PROP_PDE_MATCH_RULE_RETAIN_LOWER);
			if(rtl != null)
				retainLowerBound = Boolean.parseBoolean(rtl);
		}

		StringBuilder vbld = new StringBuilder();
		switch(pdeMatchRule)
		{
		case IMatchRules.NONE:
		case IMatchRules.PERFECT:
			vbld.append('[');
			vbld.append(v);
			if(qualifierTag)
			{
				// Generate a version range that matches the given version with
				// any qualifier.
				//
				vbld.append(".0,"); //$NON-NLS-1$
				vbld.append(ov.getMajor());
				vbld.append('.');
				vbld.append(ov.getMinor());
				vbld.append('.');
				vbld.append(ov.getMicro() + 1);
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
			if(retainLowerBound)
				vbld.append(v);
			else
			{
				vbld.append(ov.getMajor());
				vbld.append('.');
				vbld.append(ov.getMinor());
				vbld.append(".0"); //$NON-NLS-1$
			}
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
			vbld.append(',');
			vbld.append(ov.getMajor());
			vbld.append('.');
			vbld.append(ov.getMinor() + 1);
			vbld.append(".0)"); //$NON-NLS-1$
			break;

		case IMatchRules.COMPATIBLE:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			vbld.append('[');
			if(retainLowerBound)
				vbld.append(v);
			else
			{
				vbld.append(ov.getMajor());
				vbld.append(".0.0"); //$NON-NLS-1$
			}
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
			vbld.append(',');
			vbld.append(ov.getMajor() + 1);
			vbld.append(".0.0)"); //$NON-NLS-1$
			break;
		default:
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0"); //$NON-NLS-1$
		}
		return vbld.toString();
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
		bundleHostDep.setVersionRange(VersionHelper.createRange(VersionFormat.OSGI_FORMAT, fragment.getPluginVersion()));
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

	protected void addExternalPrerequisite(GroupBuilder group, String component, String type, String name)
			throws CoreException
	{
		PrerequisiteBuilder pqBld = group.createPrerequisiteBuilder();
		pqBld.setComponentName(component);
		pqBld.setComponentType(type);
		pqBld.setName(name);
		group.addPrerequisite(pqBld);
	}

	protected void addProductBundles(IProductDescriptor productDescriptor) throws CoreException
	{
		if(productDescriptor.useFeatures())
			return;

		List<IVersionedId> bundles = productDescriptor.getBundles(true);
		if(bundles.size() == 0)
			return;

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder fullClean = cspec.getRequiredGroup(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);

		String self = null;
		if(IComponentType.OSGI_BUNDLE.equals(cspec.getComponentTypeID()))
			self = cspec.getName();

		for(IVersionedId bundle : bundles)
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
			fullClean.addExternalPrerequisite(component, IComponentType.OSGI_BUNDLE, ATTRIBUTE_FULL_CLEAN);
			bundleJars.addExternalPrerequisite(component, IComponentType.OSGI_BUNDLE, ATTRIBUTE_BUNDLE_JARS);
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
			List<FileHandle> productConfigs = m_reader.getRootFiles(PRODUCT_CONFIGURATION_FILE_PATTERN,
					MonitorUtils.subMonitor(monitor, 500));
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

	protected ComponentRequestBuilder createDependency(ImportSpecification pluginImport, String category)
			throws CoreException
	{
		Filter filter = null;
		if(pluginImport.isOptional())
			filter = ComponentRequest.P2_OPTIONAL_FILTER;
		return createDependency(pluginImport.getName(), category, pluginImport.getVersionRange(), filter);
	}

	protected ComponentRequestBuilder createDependency(IVersionedId versionedName, String componentType)
			throws CoreException
	{
		String versionRange = null;
		Version v = versionedName.getVersion();
		if(!(v == null || Version.emptyVersion.equals(v)))
			versionRange = new VersionRange(v, true, v, true).toString();
		return createDependency(versionedName.getId(), componentType, versionRange, null);
	}

	protected ComponentRequestBuilder createDependency(String name, String componentType, String versionDesignator,
			Filter filter) throws CoreException
	{
		versionDesignator = Trivial.trim(versionDesignator);
		if(versionDesignator != null && versionDesignator.equals("0.0.0")) //$NON-NLS-1$
			versionDesignator = null;
		return createDependency(name, componentType, VersionHelper.createRange(VersionFormat.OSGI_FORMAT,
				versionDesignator), filter);
	}

	protected ComponentRequestBuilder createDependency(String name, String componentType, String version,
			int pdeMatchRule, Filter filter) throws CoreException
	{
		return createDependency(name, componentType, convertMatchRule(pdeMatchRule, version), filter);
	}

	protected ComponentRequestBuilder createDependency(String name, String componentType, VersionRange versionRange,
			Filter filter) throws CoreException
	{
		ComponentRequestBuilder bld = getCSpec().createDependencyBuilder();
		bld.setName(name);
		bld.setComponentTypeID(componentType);
		bld.setVersionRange(versionRange);
		bld.setFilter(filter);
		return bld;
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
		siteBuilder.setProductBase(IPDEConstants.OUTPUT_DIR_SITE_P2);
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
		siteZip.setProductBase(OUTPUT_DIR_SITE_ZIP);
		siteZip.setProductAlias(ALIAS_OUTPUT);
	}

	protected String expand(String value) throws CoreException
	{
		value = TextUtils.notEmptyTrimmedString(value);
		if(value == null)
			return null;

		if(value.charAt(0) != '%')
			return value;

		String expValue = getProperties().get(value.substring(1));
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

	protected Map<String, String> getProperties() throws CoreException
	{
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
		return m_properties;
	}

	protected abstract String getPropertyFileName();

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
		return query.skipComponent(new ComponentName(bld.getName(), bld.getComponentTypeID()),
				getReader().getNodeQuery().getContext());
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
			ArtifactBuilder productConfigArtifact = cspec.addArtifact(productDescriptor.getId(), false, null);
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
