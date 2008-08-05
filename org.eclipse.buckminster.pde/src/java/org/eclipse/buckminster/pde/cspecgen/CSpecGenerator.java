/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.cspecgen;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.ant.AntBuilderConstants;
import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.PropertiesParser;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.OSGiVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.pde.core.plugin.IFragment;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.iproduct.ILauncherInfo;
import org.eclipse.pde.internal.core.iproduct.IProduct;
import org.eclipse.pde.internal.core.iproduct.IProductFeature;
import org.eclipse.pde.internal.core.iproduct.IProductPlugin;
import org.eclipse.pde.internal.core.product.ProductModel;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 * 
 */
@SuppressWarnings("restriction")
public abstract class CSpecGenerator implements IBuildPropertiesConstants, IPDEConstants
{
	public static final IPath OUTPUT_DIR_JAR = OUTPUT_DIR.append("jar");

	public static final IPath OUTPUT_DIR_FRAGMENTS = OUTPUT_DIR.append("fragments");

	public static final IPath OUTPUT_DIR_SITE = OUTPUT_DIR.append("site");

	public static final IPath OUTPUT_DIR_TEMP = OUTPUT_DIR.append("temp");

	public static final String LAUNCHER_BUNDLE = "org.eclipse.equinox.launcher";

	public static final String LAUNCHER_FEATURE_3_2 = "org.eclipse.platform.launchers";

	public static final String LAUNCHER_FEATURE = "org.eclipse.equinox.executable";

	public static String convertMatchRule(int pdeMatchRule, String version) throws CoreException
	{
		if(version == null || version.length() == 0 || version.equals("0.0.0"))
			return null;

		char c = version.charAt(0);
		if(c == '[' || c == '(')
			//
			// Already an OSGi range, just ignore the rule then.
			//
			return version;

		OSGiVersion v = (OSGiVersion)VersionFactory.OSGiType.fromString(version);
		boolean qualifierTag = "qualifier".equals(v.getQualifier());
		if(qualifierTag)
			v = (OSGiVersion)v.replaceQualifier(null);

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
				vbld.append(".0,");
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
				vbld.append(".0");
			vbld.append(',');
			vbld.append(v.getMajor());
			vbld.append('.');
			vbld.append(v.getMinor() + 1);
			vbld.append(".0)");
			break;

		case IMatchRules.COMPATIBLE:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			vbld.append('[');
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0");
			vbld.append(',');
			vbld.append(v.getMajor() + 1);
			vbld.append(".0.0)");
			break;
		default:
			vbld.append(v);
			if(qualifierTag)
				vbld.append(".0");
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
			bld.append(".jar");
		else
			bld.append('/');
		return bld.toString();
	}

	private final CSpecBuilder m_cspecBuilder;

	private final ICatalogReader m_reader;

	private Map<String,String> m_properties;

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

	protected void addProducts(final IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 2000);
		try
		{
			List<FileHandle> productConfigs = m_reader.getRootFiles(PRODUCT_CONFIGURATION_FILE_PATTERN, MonitorUtils
					.subMonitor(monitor, 500));
			if(productConfigs.size() > 0)
			{
				int ticksPerConfig = 1500 / productConfigs.size();
				for(FileHandle productConfig : productConfigs)
					addProduct(productConfig, MonitorUtils.subMonitor(monitor, ticksPerConfig));
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		monitor.done();
	}

	protected abstract String getProductOutputFolder(String productId);

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

	private void addProduct(FileHandle productConfig, IProgressMonitor monitor) throws CoreException, IOException
	{
		InputStream stream = null;
		try
		{
			stream = new BufferedInputStream(new FileInputStream(productConfig.getFile()));
			ProductModel model = new ProductModel();
			model.load(stream, true);
			IProduct product = model.getProduct();

			CSpecBuilder cspec = getCSpec();

			ActionBuilder createProduct = addAntAction("create." + product.getId(), TASK_CREATE_ECLIPSE_PRODUCT, true);
			ILauncherInfo info = product.getLauncherInfo();
			if(info != null && "_removethisfile".equalsIgnoreCase(info.getLauncherName()))
				createProduct.addProperty(PROP_DELETE_UILAUNCHER, "true", false);
			createProduct.addProperty(PROP_PRODUCT_FILE, productConfig.getName(), false);

			AttributeBuilder rootFiles = cspec.getAttribute(ATTRIBUTE_PRODUCT_ROOT_FILES);

			ComponentQuery query = m_reader.getNodeQuery().getComponentQuery();
			GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
			if(product.useFeatures())
			{
				for(IProductFeature feature : product.getFeatures())
				{
					DependencyBuilder dep = createDependency(feature.getId(), IComponentType.ECLIPSE_FEATURE, feature
							.getVersion(), IMatchRules.PERFECT, null);
					if(dep.getName().equals(cspec.getName()))
						createProduct.addLocalPrerequisite(ATTRIBUTE_FEATURE_EXPORTS);
					else if(!skipComponent(query, dep))
					{
						addDependency(dep);
						createProduct.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_EXPORTS);
					}
				}
			}
			else
			{
				for(IProductPlugin plugin : product.getPlugins())
				{
					DependencyBuilder dep = createDependency(plugin.getId(), IComponentType.OSGI_BUNDLE, null, null);
					if(dep.getName().equals(cspec.getName()))
						continue;
					else if(!skipComponent(query, dep))
					{
						if(addDependency(dep))
							bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JARS);
					}
				}

				GroupBuilder featureExports = cspec.addGroup(ATTRIBUTE_FEATURE_EXPORTS, true);
				featureExports.addLocalPrerequisite(createCopyPluginsAction());
				featureExports.setRebase(OUTPUT_DIR_SITE);
				createProduct.addLocalPrerequisite(featureExports);

				IFeatureModel launcherFeature = EclipsePlatformReaderType.getBestFeature(LAUNCHER_FEATURE, null, null);
				if(launcherFeature == null)
					launcherFeature = EclipsePlatformReaderType.getBestFeature(LAUNCHER_FEATURE_3_2, null, null);

				if(launcherFeature != null)
				{
					IFeature feature = launcherFeature.getFeature();
					IVersion version = VersionFactory.OSGiType.fromString(feature.getVersion());
					DependencyBuilder dep = createDependency(feature.getId(),
							IComponentType.ECLIPSE_FEATURE,
							version.toString(), IMatchRules.PERFECT,
							null);

					if(addDependency(dep))
					{
						if(rootFiles == null)
							rootFiles = cspec.addGroup(ATTRIBUTE_PRODUCT_ROOT_FILES, true);
						((GroupBuilder)rootFiles).addExternalPrerequisite(dep.getName(), ATTRIBUTE_PRODUCT_ROOT_FILES);
						featureExports.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_EXPORTS);
					}
				}
			}

			boolean hasLauncherFeature = false;
			for(DependencyBuilder dep : cspec.getDependencies().values())
			{
				if(dep.getComponentTypeID() != IComponentType.ECLIPSE_FEATURE)
					continue;

				if(dep.getName().equals(LAUNCHER_FEATURE) || dep.getName().equals(LAUNCHER_FEATURE_3_2))
				{
					hasLauncherFeature = true;
					break;
				}
			}

			if(!hasLauncherFeature)
			{
				// Ensure that the launcher is present if it exists in the current target platform
				//
				IPluginModelBase launcherBundle = EclipsePlatformReaderType.getBestPlugin("org.eclipse.equinox.launcher", null, null);
				if(launcherBundle != null)
				{
					IPluginBase plugin = launcherBundle.getPluginBase();
					IVersion version = VersionFactory.OSGiType.fromString(plugin.getVersion());
					DependencyBuilder dep = createDependency(plugin.getId(), IComponentType.OSGI_BUNDLE, version.toString(), IMatchRules.PERFECT, null);
					if(addDependency(dep))
						bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JARS);
				}
			}

			if(rootFiles != null)
				createProduct.addLocalPrerequisite(rootFiles);

			createProduct.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
			createProduct.setProductAlias(ALIAS_OUTPUT);
			createProduct.setProductBase(OUTPUT_DIR);
			createProduct.setUpToDatePolicy(UpToDatePolicy.NOT_EMPTY);
			String outputFolder = TextUtils.notEmptyTrimmedString(getProductOutputFolder(product.getId()));
			if(outputFolder == null)
			{
				ILauncherInfo launcherInfo = product.getLauncherInfo();
				if(launcherInfo != null)
				{
					outputFolder = launcherInfo.getLauncherName();
					if(outputFolder != null && outputFolder.endsWith(".exe"))
						outputFolder = outputFolder.substring(0, outputFolder.length() - 4);
				}

				if(outputFolder == null)
					outputFolder = "eclipse";
			}
			createProduct.addProductPath(Path.fromPortableString(outputFolder).addTrailingSeparator());
		}
		finally
		{
			IOUtils.close(stream);
			if(productConfig.isTemporary())
				productConfig.getFile().delete();
		}
	}

	protected ActionBuilder addAntAction(String actionName, String targetName, boolean asPublic) throws CoreException
	{
		ActionBuilder action = m_cspecBuilder.addAction(actionName, asPublic, AntActor.ID, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_TARGETS, targetName, false);
		action.addActorProperty(AntBuilderConstants.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		return action;
	}

	protected void addBundleHostDependency(IFragmentModel fragmentModel) throws CoreException
	{
		IFragment fragment = fragmentModel.getFragment();
		DependencyBuilder bundleHostDep = m_cspecBuilder.createDependencyBuilder();
		bundleHostDep.setName(fragment.getPluginId());
		bundleHostDep.setVersionDesignator(fragment.getPluginVersion(), IVersionType.OSGI);
		bundleHostDep.setComponentTypeID(IComponentType.OSGI_BUNDLE);
		try
		{
			bundleHostDep.setFilter(FilterUtils.createFilter("(bundleHost=true)"));
		}
		catch(InvalidSyntaxException e)
		{
			// This won't happen on that particular filter
		}
		addDependency(bundleHostDep);
	}

	protected boolean addDependency(DependencyBuilder dependency) throws CoreException
	{
		DependencyBuilder old = m_cspecBuilder.getDependency(dependency.getName());
		if(old == null)
		{
			m_cspecBuilder.addDependency(dependency);
			return true;
		}

		IVersionDesignator vd = old.getVersionDesignator();
		IVersionDesignator nvd = dependency.getVersionDesignator();
		if(vd == null)
			vd = nvd;
		else
		{
			if(nvd != null)
			{
				vd = vd.merge(nvd);
				if(vd == null)
					//
					// Version ranges were not possible to merge, i.e. no intersection
					//
					throw new DependencyAlreadyDefinedException(old.getCSpecName(), old.getName());
			}
		}

		Filter fl = old.getFilter();
		Filter nfl = dependency.getFilter();
		if(fl == null || nfl == null)
			fl = null;
		else
		{
			if(!fl.equals(nfl))
			{
				try
				{
					fl = FilterUtils.createFilter("(|" + fl + nfl + ')');
				}
				catch(InvalidSyntaxException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
		}

		if(vd == old.getVersionDesignator() && fl == old.getFilter())
			return false;

		old.setVersionDesignator(vd);
		old.setFilter(fl);
		return false;
	}

	protected ActionBuilder createCopyPluginsAction() throws CoreException
	{
		// Copy all plug-ins that all features (including this one) is including.
		//
		ActionBuilder copyPlugins = addAntAction(ACTION_COPY_PLUGINS, TASK_COPY_GROUP, false);
		copyPlugins.addLocalPrerequisite(ATTRIBUTE_BUNDLE_JARS);
		copyPlugins.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyPlugins.setProductAlias(ALIAS_OUTPUT);
		copyPlugins.setProductBase(OUTPUT_DIR_SITE.append(PLUGINS_FOLDER));
		copyPlugins.setUpToDatePolicy(UpToDatePolicy.MAPPER);
		return copyPlugins;
	}

	protected DependencyBuilder createDependency(IPluginReference pluginReference, String category)
			throws CoreException
	{
		return createDependency(pluginReference.getId(), category, pluginReference.getVersion(), pluginReference
				.getMatch(), null);
	}

	protected DependencyBuilder createDependency(String name, String componentType, String versionDesignator,
			Filter filter) throws CoreException
	{
		if(versionDesignator != null && (versionDesignator.length() == 0 || versionDesignator.equals("0.0.0")))
			versionDesignator = null;

		DependencyBuilder bld = getCSpec().createDependencyBuilder();
		bld.setName(name);
		bld.setComponentTypeID(componentType);
		bld.setVersionDesignator(versionDesignator, IVersionType.OSGI);
		bld.setFilter(filter);
		return bld;
	}

	protected DependencyBuilder createDependency(String name, String componentType, String version, int pdeMatchRule,
			Filter filter) throws CoreException
	{
		return createDependency(name, componentType, convertMatchRule(pdeMatchRule, version), filter);
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ) throws CoreException
	{
		return generateRemoveDirAction(dirTag, dirPath, publ, "buckminster.rm." + dirTag + ".dir");
	}

	protected AttributeBuilder generateRemoveDirAction(String dirTag, IPath dirPath, boolean publ, String actionName)
			throws CoreException
	{
		ActionBuilder rmDir = addAntAction(actionName, TASK_DELETE_DIR, publ);
		rmDir.addProperty(PROP_DELETE_DIR, dirPath.toPortableString(), false);
		return rmDir;
	}

	protected boolean skipComponent(ComponentQuery query, DependencyBuilder bld)
	{
		return query.skipComponent(new ComponentName(bld.getName(), bld.getComponentTypeID()));
	}

	protected void setFilter(String filterStr) throws CoreException
	{
		filterStr = TextUtils.notEmptyTrimmedString(filterStr);
		if(filterStr == null)
			return;

		try
		{
			Filter filter = FilterUtils.createFilter(filterStr);
			getCSpec().setFilter(FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX));
		}
		catch(InvalidSyntaxException e)
		{
			NodeQuery query = m_reader.getNodeQuery();
			IStatus status = new Status(IStatus.WARNING, PDEPlugin.getPluginId(),
					"Manifest has malformed LDAP rule for " + ICoreConstants.PLATFORM_FILTER + ": " + e.getMessage());
			RMContext ctx = query.getContext();
			if(!ctx.isContinueOnError())
				throw new CoreException(status);
			ctx.addRequestStatus(query.getComponentRequest(), status);
		}
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
				m_properties = m_reader.readFile(getPropertyFileName(), new PropertiesParser(), new NullProgressMonitor());
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
	
	protected abstract String getPropertyFileName();
}
