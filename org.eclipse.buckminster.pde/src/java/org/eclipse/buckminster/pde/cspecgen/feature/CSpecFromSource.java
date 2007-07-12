package org.eclipse.buckminster.pde.cspecgen.feature;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelManager;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class CSpecFromSource extends CSpecGenerator
{
	private static final String ACTION_COPY_FEATURES = "copy.features";

	private static final String ACTION_COPY_PLUGINS = "copy.plugins";

	private static final String ATTRIBUTE_FEATURE_REFS = "feature.references";

	private static final String ATTRIBUTE_INTERNAL_PRODUCT_ROOT = "internal.product.root";

	public static void addRootsPermissions(Map<String, String> hints, String perm, String filesAndFolders)
	{
		StringBuilder bld = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(filesAndFolders, ",");
		while(tokenizer.hasMoreTokens())
		{
			if(bld.length() > 0)
				bld.append(',');

			bld.append(tokenizer.nextToken().trim());
			bld.append(':');
			bld.append(perm);
		}

		if(perm.length() > 0)
		{
			String permissions = hints.get(HINT_PERMISSIONS);
			if(permissions != null)
			{
				bld.append(',');
				bld.append(permissions);
			}
			hints.put(HINT_PERMISSIONS, bld.toString());
		}
	}

	private static boolean isListOK(String list, String item)
	{
		if(list == null || list.length() == 0)
			return true;
		StringTokenizer tokens = new StringTokenizer(list, ",");
		while(tokens.hasMoreTokens())
			if(item.equals(tokens.nextElement()))
				return true;
		return false;
	}

	private final Map<String, String> m_buildProperties;

	private final IFeature m_feature;

	private final ICatalogReader m_reader;

	protected CSpecFromSource(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature,
			Map<String, String> buildProperties)
	{
		super(cspecBuilder);
		m_reader = reader;
		m_feature = feature;
		m_buildProperties = buildProperties;
	}

	@Override
	public void generate(IProgressMonitor monitor) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		cspec.setName(m_feature.getId());
		cspec.setVersion(m_feature.getVersion(), VersionFactory.OSGiType.getId());
		cspec.setComponentTypeID(IComponentType.ECLIPSE_FEATURE);

		// This feature and all included features. Does not imply copying since
		// the group will reference the features where they are found.
		//
		GroupBuilder featureRefs = cspec.addGroup(ATTRIBUTE_FEATURE_REFS, true); // without self

		// All bundles imported by this feature and all included features. Does
		// not imply copying since the group will reference the bundles where they
		// are found.
		//
		cspec.addGroup(ATTRIBUTE_BUNDLE_JARS, true);

		// This feature, its imported bundles, all included features, and their
		// imported bundles copied into a site structure (with a plugins and a
		// features folder).
		//
		cspec.addGroup(ATTRIBUTE_FEATURE_EXPORTS, true);

		cspec.addGroup(PRODUCT_ROOT_FILES, true);
		generateRemoveDirAction("build", OUTPUT_DIR, true, ATTRIBUTE_FULL_CLEAN);

		addFeatures();
		addPlugins();

		if(m_buildProperties != null)
		{
			cspec.addArtifact(ATTRIBUTE_BUILD_PROPERTIES, false, null, new Path(BUILD_PROPERTIES_FILE));
			String os = TargetPlatform.getOS();
			String ws = TargetPlatform.getWS();
			String arch = TargetPlatform.getOSArch();
			String triplet = '.' + os + '.' + ws + '.' + arch;
			String pfRoot = ROOT + triplet;
			String permRoot = ROOT + PERMISSIONS;
			String pfPermRoot = pfRoot + PERMISSIONS;

			for(Map.Entry<String, String> entry : m_buildProperties.entrySet())
			{
				String key = entry.getKey();
				if(IBuildEntry.BIN_INCLUDES.equals(key))
				{
					createBinIncludesArtifact(entry.getValue());
					continue;
				}

				if(ROOT.equals(key) || pfRoot.equals(key))
				{
					createRootsArtifact(entry.getValue());
					continue;
				}

				int permIndex;
				if(key.startsWith(permRoot))
					permIndex = permRoot.length();
				else if(key.startsWith(pfPermRoot))
					permIndex = pfPermRoot.length();
				else
					continue;

				addRootsPermissions(featureRefs.getInstallerHintsForAdd(), key.substring(permIndex), entry.getValue());
			}

			GroupBuilder productRoots = cspec.getGroup(ATTRIBUTE_INTERNAL_PRODUCT_ROOT);
			if(productRoots != null)
			{
				List<PrerequisiteBuilder> preqs = productRoots.getPrerequisites();
				if(preqs.size() == 1)
				{
					// Replace the internal product root group with it's one and only
					// prerequisite.
					//
					String prName = productRoots.getName();
					ArtifactBuilder artifact = cspec.getArtifact(preqs.iterator().next().getName());
					cspec.removeAttribute(prName);

					cspec.removeAttribute(artifact.getName());
					artifact.setName(prName);
					cspec.addAttribute(artifact);
				}
			}
		}

		createFeatureManifestAction();
		createFeatureJarAction();
		createFeatureExportsAction();
	}

	void addFeatures() throws CoreException
	{
		IFeatureChild[] features = m_feature.getIncludedFeatures();
		if(features == null || features.length == 0)
			return;

		ComponentQuery query = m_reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder featureRefs = cspec.getRequiredGroup(ATTRIBUTE_FEATURE_REFS);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		GroupBuilder productRootFiles = cspec.getRequiredGroup(PRODUCT_ROOT_FILES);
		for(IFeatureChild feature : features)
		{
			ComponentRequest dep = createComponentRequest(feature);
			if(query.skipComponent(dep))
				continue;

			cspec.addDependency(dep);
			featureRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_JARS);
			bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JARS);
			fullClean.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FULL_CLEAN);
			productRootFiles.addExternalPrerequisite(dep.getName(), PRODUCT_ROOT_FILES);
		}
	}

	void addPlugins() throws CoreException
	{
		IFeaturePlugin[] plugins = m_feature.getPlugins();
		if(plugins == null || plugins.length == 0)
			return;

		String os = TargetPlatform.getOS();
		String ws = TargetPlatform.getWS();
		String arch = TargetPlatform.getOSArch();
		ComponentQuery query = m_reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		PluginModelManager manager = PDECore.getDefault().getModelManager();
		for(IFeaturePlugin plugin : plugins)
		{
			// This is a quick and dirty solution. If the plugin is found in the
			// target platform, include it. If not, don't.
			// TODO: The correct solution is to always include it but to make
			// it os/ws/arch dependent. Awaits implementation
			//
			if(!(isListOK(plugin.getOS(), os) && isListOK(plugin.getWS(), ws) && isListOK(plugin.getArch(), arch)))
				if(manager.findEntry(plugin.getId()) == null)
					continue;

			ComponentRequest dep = createComponentRequest(plugin);
			if(query.skipComponent(dep))
				continue;

			cspec.addDependency(dep);
			bundleJars.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_JAR);
			fullClean.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FULL_CLEAN);
		}
	}

	ComponentRequest createComponentRequest(IFeatureChild feature) throws CoreException
	{
		return createComponentRequest(feature.getId(), IComponentType.ECLIPSE_FEATURE, feature.getVersion(), feature
				.getMatch());
	}

	ComponentRequest createComponentRequest(IFeaturePlugin pluginReference) throws CoreException
	{
		return createComponentRequest(pluginReference.getId(), IComponentType.OSGI_BUNDLE, pluginReference.getVersion());
	}

	private void createBinIncludesArtifact(String binIncludesStr) throws CoreException
	{
		ArtifactBuilder binIncludes = null;
		StringTokenizer tokens = new StringTokenizer(binIncludesStr, ",");
		while(tokens.hasMoreTokens())
		{
			String path = tokens.nextToken().trim();
			if(FEATURE_FILE.equals(path) || BUILD_PROPERTIES_FILE.equals(path))
				//
				// Handled separately
				//
				continue;

			if(binIncludes == null)
				binIncludes = getCSpec().addArtifact(ATTRIBUTE_JAR_CONTENTS, false, null, null);
			binIncludes.addPath(new Path(path));
		}
	}

	private void createFeatureExportsAction() throws CoreException
	{
		// Copy all features (including this one) to the features directory.
		//
		ActionBuilder copyFeatures = addAntAction(ACTION_COPY_FEATURES, TASK_COPY_GROUP, false);
		copyFeatures.addLocalPrerequisite(ATTRIBUTE_FEATURE_JARS);
		copyFeatures.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyFeatures.setProductAlias(ALIAS_OUTPUT);
		copyFeatures.setProductBase(OUTPUT_DIR_SITE.append(FEATURES_FOLDER));
		copyFeatures.setUpToDatePolicy(UpToDatePolicy.MAPPER);

		// Copy all plug-ins that all features (including this one) is including.
		//
		ActionBuilder copyPlugins = addAntAction(ACTION_COPY_PLUGINS, TASK_COPY_GROUP, false);
		copyPlugins.addLocalPrerequisite(ATTRIBUTE_BUNDLE_JARS);
		copyPlugins.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyPlugins.setProductAlias(ALIAS_OUTPUT);
		copyPlugins.setProductBase(OUTPUT_DIR_SITE.append(PLUGINS_FOLDER));
		copyPlugins.setUpToDatePolicy(UpToDatePolicy.MAPPER);

		GroupBuilder featureExports = getCSpec().getRequiredGroup(ATTRIBUTE_FEATURE_EXPORTS);
		featureExports.addLocalPrerequisite(ACTION_COPY_FEATURES);
		featureExports.addLocalPrerequisite(ACTION_COPY_PLUGINS);
		featureExports.setRebase(OUTPUT_DIR_SITE);
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

	private void createFeatureJarAction() throws CoreException
	{
		CSpecBuilder cspec = getCSpec();

		// Create the action that builds the final jar file for the feature
		//
		ActionBuilder featureJarBuilder = addAntAction(ATTRIBUTE_FEATURE_JAR, TASK_CREATE_FEATURE_JAR, false);
		featureJarBuilder.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);

		if(cspec.getArtifact(ATTRIBUTE_JAR_CONTENTS) != null)
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

	private void createRootsArtifact(String filesAndFolders) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		StringTokenizer tokenizer = new StringTokenizer(filesAndFolders, ",");
		GroupBuilder productRoots = null;
		while(tokenizer.hasMoreTokens())
		{
			// Here PDE decided that files needed a prefix whereas folders did not and that
			// absolute paths needed another prefix whereas relative paths did not. I'm curious;
			// why not say that entries ending with '/' are folders and use standard notation
			// for absolute paths?
			//
			String token = tokenizer.nextToken().trim();
			if(token.startsWith("absolute:"))
				//
				// Why whould a feature copy anything using absolute paths? If it did, it would
				// make it hopelessly dependent on install location. Not good. We don't permit
				// it here.
				//
				throw new BuckminsterException("Component " + getCSpec().getName()
						+ " contains absolute paths in build.properties");

			IPath path;
			boolean isFile = token.startsWith("file:");
			if(isFile)
			{
				if("file:bin/win32/win32/x86/eclipse.exe".equals(token))
					token = "file:bin/win32/win32/x86/launcher.exe";
				else if("file:bin/wpf/win32/x86/eclipse.exe".equals(token))
					token = "file:bin/wpf/win32/x86/launcher.exe";

				path = new Path(token.substring(5));
			}
			else
				path = new Path(token);

			// Make sure it really is relative
			//
			IPath leaf = null;
			path = path.makeRelative().setDevice(null);
			if(isFile)
			{
				leaf = new Path(path.lastSegment());
				path = path.removeLastSegments(1);
			}
			path = path.addTrailingSeparator();

			if(productRoots == null)
				productRoots = getInternalProductRoot();

			ArtifactBuilder productRoot = null;
			List<PrerequisiteBuilder> preqs = productRoots.getPrerequisites();
			for(PrerequisiteBuilder preq : preqs)
			{
				ArtifactBuilder ag = cspec.getRequiredArtifact(preq.getName());
				if(ag.getBase().equals(path))
				{
					productRoot = ag;
					break;
				}
			}

			if(productRoot == null)
			{
				int n = preqs.size();
				productRoot = cspec.addArtifact(ATTRIBUTE_INTERNAL_PRODUCT_ROOT + '.' + n, false, null, path);
				productRoots.addLocalPrerequisite(productRoot);
			}

			if(isFile)
				productRoot.addPath(leaf);
		}
	}

	private GroupBuilder getInternalProductRoot() throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		GroupBuilder productRoot = cspec.getGroup(ATTRIBUTE_INTERNAL_PRODUCT_ROOT);
		if(productRoot == null)
		{
			productRoot = cspec.addGroup(ATTRIBUTE_INTERNAL_PRODUCT_ROOT, false);
			cspec.getRequiredGroup(PRODUCT_ROOT_FILES).addLocalPrerequisite(productRoot);
		}
		return productRoot;
	}
}
