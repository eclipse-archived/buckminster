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
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
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
import org.eclipse.buckminster.core.cspec.model.Dependency;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.iproduct.IProduct;
import org.eclipse.pde.internal.core.iproduct.IProductFeature;
import org.eclipse.pde.internal.core.iproduct.IProductPlugin;
import org.eclipse.pde.internal.core.product.ProductModel;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.Version;

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

	public static String convertMatchRule(int pdeMatchRule, String version)
	{
		if(version == null || version.length() == 0 || version.equals("0.0.0"))
			return null;

		char c = version.charAt(0);
		if(c == '[' || c == '(')
			//
			// Already an OSGi range, just ignor the rule then.
			//
			return version;

		Version v;
		StringBuilder vbld;
		switch(pdeMatchRule)
		{
		case IMatchRules.GREATER_OR_EQUAL:
			break;
		case IMatchRules.PERFECT:
			// If the version was sanitized, a perfect match will not find
			// it.
			//
			version = "[" + version + ',' + version + "]";
			break;
		case IMatchRules.EQUIVALENT:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			v = Version.parseVersion(version);
			vbld = new StringBuilder();
			vbld.append('[');
			vbld.append(version);
			vbld.append(',');
			vbld.append(v.getMajor());
			vbld.append('.');
			vbld.append(v.getMinor() + 1);
			vbld.append(')');
			version = vbld.toString();
			break;
		case IMatchRules.COMPATIBLE:
			//
			// Create a range that requires major and minor numbers
			// to be equal.
			//
			v = Version.parseVersion(version);
			vbld = new StringBuilder();
			vbld.append('[');
			vbld.append(version);
			vbld.append(',');
			vbld.append(v.getMajor() + 1);
			vbld.append(')');
			version = vbld.toString();
		}
		return version;
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
			List<FileHandle> productConfigs = m_reader.getRootFiles(PRODUCT_CONFIGURATION_FILE_PATTERN, MonitorUtils.subMonitor(monitor, 500));
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
			boolean headless = "_removethisfile".equalsIgnoreCase(product.getLauncherInfo().getLauncherName());
			if(headless)
				createProduct.addProperty(PROP_DELETE_UILAUNCHER, "true", false);
			createProduct.addProperty(PROP_PRODUCT_FILE, productConfig.getName(), false);

			AttributeBuilder rootFiles = cspec.getAttribute(ATTRIBUTE_PRODUCT_ROOT_FILES);
			if(rootFiles != null)
				createProduct.addLocalPrerequisite(rootFiles);

			ComponentQuery query = m_reader.getNodeQuery().getComponentQuery();
			GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
			if(product.useFeatures())
			{
				for(IProductFeature feature : product.getFeatures())
				{
					Dependency dep = createDependency(feature.getId(), IComponentType.ECLIPSE_FEATURE, feature.getVersion(), null);
					if(dep.getName().equals(cspec.getName()))
						createProduct.addLocalPrerequisite(ATTRIBUTE_FEATURE_EXPORTS);
					else if(!query.skipComponent(dep))
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
					Dependency dep = createDependency(plugin.getId(), IComponentType.OSGI_BUNDLE, null, null);
					if(dep.getName().equals(cspec.getName()))
						continue;
					else if(!query.skipComponent(dep))
					{
						if(addDependency(dep))
							bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JARS);
					}
				}

				GroupBuilder featureExports = cspec.addGroup(ATTRIBUTE_FEATURE_EXPORTS, true);
				featureExports.addLocalPrerequisite(createCopyPluginsAction());
				featureExports.setRebase(OUTPUT_DIR_SITE);
				createProduct.addLocalPrerequisite(featureExports);
			}

			// Ensure that the launcher is present
			//
			Dependency dep = createDependency("org.eclipse.equinox.launcher", IComponentType.OSGI_BUNDLE, null, null);
			if(addDependency(dep))
				bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JARS);

			createProduct.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
			createProduct.setProductAlias(ALIAS_OUTPUT);
			createProduct.setProductBase(OUTPUT_DIR);
			String outputFolder = TextUtils.notEmptyTrimmedString(getProductOutputFolder(product.getId()));
			if(outputFolder == null)
				outputFolder = "eclipse";
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

	protected boolean addDependency(Dependency dependency) throws CoreException
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

		DependencyBuilder bld = m_cspecBuilder.createDependencyBuilder();
		bld.initFrom(dependency);
		bld.setVersionDesignator(vd);
		bld.setFilter(fl);
		m_cspecBuilder.removeDependency(dependency.getName());
		m_cspecBuilder.addDependency(bld);
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

	protected Dependency createDependency(IPluginReference pluginReference, String category)
	throws CoreException
	{
		return createDependency(pluginReference.getId(), category, pluginReference.getVersion(), pluginReference.getMatch(), null);
	}

	protected Dependency createDependency(String name, String componentType, String version, Filter filter)
	throws CoreException
	{
		if(version != null && (version.length() == 0 || version.equals("0.0.0")))
			version = null;
		return new Dependency(name, componentType, version, VersionFactory.OSGiType.getId(), filter);
	}

	protected Dependency createDependency(String name, String componentType, String version, int pdeMatchRule, Filter filter)
	throws CoreException
	{
		return new Dependency(name, componentType, convertMatchRule(pdeMatchRule, version),
				VersionFactory.OSGiType.getId(), filter);
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
			ctx.addException(query.getComponentRequest(), status);
		}
	}
}
