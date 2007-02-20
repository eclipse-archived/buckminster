/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.PropertiesParser;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.internal.actor.FeatureScriptGenerator;
import org.eclipse.buckminster.pde.internal.actor.PdeAntActor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.internal.core.TargetPlatform;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

/**
 * A CSpec builder that creates a cspec from a Eclipse plugin.xml file.
 * @author thhal
 */
@SuppressWarnings("restriction")
public class FeatureBuilder extends PDEBuilder
{
	private static final String BUILD_SCRIPT_NAME = "build-feature.xml";

	private static final String ROOT = "root";

	private static final String PERMISSIONS = ".permissions.";

	private static final String ACTION_CREATE_EXPORT_FEATURE = "create.export.feature";

	private static final String ACTION_CREATE_RUNTIME_FEATURE = "create.runtime.feature";

	private static final String ACTION_COPY_FEATURES = "copy.features";

	private static final String ACTION_COPY_PLUGINS = "copy.plugins";

	private static final String ATTRIBUTE_BIN_INCLUDES = "bin.includes";

	private static final String ATTRIBUTE_INTERNAL_PRODUCT_ROOT = "internal.product.root";

	private static final String GROUP_EXPORT_FEATURE_REFS = "export.feature.refs";

	private static final String GROUP_RUNTIME_FEATURE_REFS = "runtime.feature.refs";

	private static final IPath ARTIFACT_OUTPUT_DIR = OUTPUT_DIR.append("artifact");

	private static final IPath EXPORT_OUTPUT = new Path("export");

	private static final IPath RUNTIME_OUTPUT = new Path("runtime");

	@Override
	public String getCategory()
	{
		return KeyConstants.FEATURE_CATEGORY;
	}

	@Override
	void parseFile(ICatalogReader reader, IProgressMonitor monitor) throws CoreException
	{
		IFeature feature;
		try
		{
			IFeatureModel model = isUsingInstalledReader()
				? ((EclipsePlatformReader)reader).getFeatureModel() : reader.readFile("feature.xml",
					new FeatureModelReader(), monitor);

			feature = model.getFeature();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}

		Map<String, String> buildProperties = null;
		try
		{
			buildProperties = reader.readFile("build.properties", new PropertiesParser(), monitor);
		}
		catch(FileNotFoundException e)
		{
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		convertModel(reader, feature, buildProperties);
	}

	private static void addNonContributingPrerequisite(GroupBuilder group, String name)
	throws PrerequisiteAlreadyDefinedException
	{
		PrerequisiteBuilder prb = group.createPrerequisiteBuilder();
		prb.setName(name);
		prb.setContributor(false);
		group.addPrerequisite(prb);
	}

	void convertModel(IComponentReader reader, IFeature feature, Map<String, String> properties) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		cspec.setName(feature.getId());
		cspec.setVersion(sanitizeVersion(feature.getVersion()), VersionFactory.OSGiType.getId());
		cspec.setCategory(KeyConstants.FEATURE_CATEGORY);

		// Add the private groups for the export and runtime feature references. Their
		// public correspondance includes this feature as well
		//
		GroupBuilder exportRefs = cspec.addGroup(GROUP_EXPORT_FEATURE_REFS, false);
		GroupBuilder runtimeRefs = cspec.addGroup(GROUP_RUNTIME_FEATURE_REFS, false);

		GroupBuilder pluginRuntimeRefs = cspec.addGroup(ATTRIBUTE_BUNDLE_RUNTIME, true);
		GroupBuilder pluginExportRefs = cspec.addGroup(ATTRIBUTE_BUNDLE_EXPORT, true);
		cspec.addGroup(ATTRIBUTE_BUNDLE_CLOSURE, true);
		cspec.addGroup(PRODUCT_ROOT_FILES, true);

		ActionBuilder scriptBuilder = cspec.addAction(
			FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT, false,
			FeatureScriptGenerator.ACTOR_ID, false);
		scriptBuilder.setProductBase(TEMP_DIR);
		scriptBuilder.addProductPath(new Path(BUILD_SCRIPT_NAME));
		scriptBuilder.addLocalPrerequisite(ATTRIBUTE_BUNDLE_CLOSURE);
		scriptBuilder.addLocalPrerequisite(CSpec.SELF_ARTIFACT);

		// Ensure that the script builder is run before we do anything else since
		// it builds up the PDE state.
		//
		addNonContributingPrerequisite(exportRefs, FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT);
		addNonContributingPrerequisite(runtimeRefs, FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT);
		addNonContributingPrerequisite(pluginRuntimeRefs, FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT);
		addNonContributingPrerequisite(pluginExportRefs, FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT);

		generateRemoveDirAction("build", OUTPUT_DIR, true, ATTRIBUTE_FULL_CLEAN);

		GroupBuilder featureExport = cspec.addGroup(ATTRIBUTE_FEATURE_EXPORT, true);
		GroupBuilder featureRuntime = cspec.addGroup(ATTRIBUTE_FEATURE_RUNTIME, true);

		if(properties != null)
		{
			String os = TargetPlatform.getOS();
			String ws = TargetPlatform.getWS();
			String arch = TargetPlatform.getOSArch();
			String triplet = '.' + os + '.' + ws + '.' + arch;
			String pfRoot = ROOT + triplet;
			String permRoot = ROOT + PERMISSIONS;
			String pfPermRoot = pfRoot + PERMISSIONS;

			for(Map.Entry<String, String> entry : properties.entrySet())
			{
				String key = entry.getKey();
				if(IBuildEntry.BIN_INCLUDES.equals(key))
				{
					createBinIncludesArtifact(feature, entry.getValue());
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

				// Add installer hints only to the runtime export. They are only needed when we
				// build a product
				//
				addRootsPermissions(featureRuntime.getInstallerHintsForAdd(), key.substring(permIndex),
					entry.getValue());
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

		if(cspec.getAttribute(ATTRIBUTE_BIN_INCLUDES) == null)
			createBinIncludesArtifact(feature, FEATURE_FILE);

		createExportFeatureAction(feature);
		createRuntimeFeatureAction(feature);

		addFeatures(reader, feature.getIncludedFeatures());
		addPlugins(reader, feature.getPlugins());

		featureExport.addLocalPrerequisite(GROUP_EXPORT_FEATURE_REFS);
		featureExport.addLocalPrerequisite(ACTION_CREATE_EXPORT_FEATURE);

		featureRuntime.addLocalPrerequisite(GROUP_RUNTIME_FEATURE_REFS);
		featureRuntime.addLocalPrerequisite(ACTION_CREATE_RUNTIME_FEATURE);

		createCopyToStructure(true);
		createCopyToStructure(false);
	}

	private void createCopyToStructure(boolean forSiteExport) throws CoreException
	{
		String copyFeaturesAction;
		String copyPluginsAction;
		String featuresPrereq;
		String pluginsPrereq;
		String exportName;
		IPath structureRoot;
		if(forSiteExport)
		{
			structureRoot = OUTPUT_DIR.append(EXPORT_OUTPUT);
			copyFeaturesAction = ACTION_COPY_FEATURES + ".export";
			copyPluginsAction = ACTION_COPY_PLUGINS + ".export";
			featuresPrereq = ATTRIBUTE_FEATURE_EXPORT;
			pluginsPrereq = ATTRIBUTE_BUNDLE_EXPORT;
			exportName = ATTRIBUTE_FEATURE_EXPORTS;
		}
		else
		{
			structureRoot = OUTPUT_DIR.append(RUNTIME_OUTPUT);
			copyFeaturesAction = ACTION_COPY_FEATURES + ".runtime";
			copyPluginsAction = ACTION_COPY_PLUGINS + ".runtime";
			featuresPrereq = ATTRIBUTE_FEATURE_RUNTIME;
			pluginsPrereq = ATTRIBUTE_BUNDLE_RUNTIME;
			exportName = ATTRIBUTE_FEATURE_RUNTIMES;
		}
		CSpecBuilder cspec = getCSpec();

		// Copy all features (including this one) to the features directory.
		//
		ActionBuilder copyFeatures = cspec.addAction(copyFeaturesAction, false, "ant", false);
		copyFeatures.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		copyFeatures.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, TASK_COPY_GROUP, false);
		copyFeatures.addLocalPrerequisite(featuresPrereq);
		copyFeatures.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyFeatures.setProductBase(structureRoot);
		copyFeatures.addProductPath(new Path(FEATURES_FOLDER).addTrailingSeparator());
		copyFeatures.setProductAlias(ALIAS_OUTPUT);

		// Copy all plugins that all features (including this one) is including.
		//
		ActionBuilder copyPlugins = cspec.addAction(copyPluginsAction, false, "ant", false);
		copyPlugins.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		copyPlugins.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, TASK_COPY_GROUP, false);
		copyPlugins.addLocalPrerequisite(pluginsPrereq);
		copyPlugins.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		copyPlugins.setProductBase(structureRoot);
		copyPlugins.addProductPath(new Path(PLUGINS_FOLDER).addTrailingSeparator());
		copyPlugins.setProductAlias(ALIAS_OUTPUT);

		GroupBuilder exportFeature = cspec.addGroup(exportName, true);
		exportFeature.addLocalPrerequisite(copyFeaturesAction);
		exportFeature.addLocalPrerequisite(copyPluginsAction);
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
				path = new Path(token.substring(5));
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

	private void createBinIncludesArtifact(IFeature feature, String binIncludesStr) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		ArtifactBuilder binIncludes = cspec.addArtifact(ATTRIBUTE_BIN_INCLUDES, false, null, null);
		StringTokenizer tokens = new StringTokenizer(binIncludesStr, ",");
		while(tokens.hasMoreTokens())
		{
			String path = tokens.nextToken().trim();
			binIncludes.addPath(new Path(path));
		}
	}

	private void createExportFeatureAction(IFeature feature) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		ActionBuilder featureExportBuilder = cspec.addAction(ACTION_CREATE_EXPORT_FEATURE, false,
			PdeAntActor.ACTOR_ID, false);
		featureExportBuilder.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, "build.update.jar", false);
		featureExportBuilder.setProductBase(ARTIFACT_OUTPUT_DIR.append(EXPORT_OUTPUT).append(FEATURES_FOLDER).addTrailingSeparator());
		featureExportBuilder.addLocalPrerequisite(FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT,
			PdeAntActor.ALIAS_GENERATED_SCRIPT);
	}

	private void createRuntimeFeatureAction(IFeature feature) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		ActionBuilder featureRuntimeBuilder = cspec.addAction(ACTION_CREATE_RUNTIME_FEATURE, false,
			PdeAntActor.ACTOR_ID, false);

		featureRuntimeBuilder.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, "gather.bin.parts",
			false);

		// The generated script will use ${feature.base}/features as its output.
		//
		IPath featureBase = ARTIFACT_OUTPUT_DIR.append(RUNTIME_OUTPUT);
		featureRuntimeBuilder.setProductBase(featureBase.append(FEATURES_FOLDER).addTrailingSeparator());
		featureRuntimeBuilder.addProperty("feature.base", featureBase.toOSString(), false);

		// We call the target agnostic ant-task here
		//
		featureRuntimeBuilder.addProperty("arch", "group", false);
		featureRuntimeBuilder.addProperty("ws", "group", false);
		featureRuntimeBuilder.addProperty("nl", "group", false);
		featureRuntimeBuilder.addProperty("os", "group", false);

		featureRuntimeBuilder.addProperty("feature.temp.folder", "${feature.base}", false);
		featureRuntimeBuilder.addLocalPrerequisite(FeatureScriptGenerator.ATTRIBUTE_GENERATED_FEATURE_SCRIPT,
			PdeAntActor.ALIAS_GENERATED_SCRIPT);
	}

	void addFeatures(IComponentReader reader, IFeatureChild[] features) throws CoreException
	{
		if(features == null)
			return;

		ComponentQuery query = reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder exportFeatureRefs = cspec.getRequiredGroup(GROUP_EXPORT_FEATURE_REFS);
		GroupBuilder runtimeFeatureRefs = cspec.getRequiredGroup(GROUP_RUNTIME_FEATURE_REFS);
		GroupBuilder exportPluginRefs = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_EXPORT);
		GroupBuilder runtimePluginRefs = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_RUNTIME);
		GroupBuilder pluginStateClosure = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_CLOSURE);
		GroupBuilder productRootFiles = cspec.getGroup(PRODUCT_ROOT_FILES);
		for(IFeatureChild feature : features)
		{
			ComponentRequest dep = createComponentRequest(feature);
			if(query.skipComponent(dep))
				continue;

			cspec.addDependency(dep);
			exportFeatureRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_EXPORT);
			runtimeFeatureRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FEATURE_RUNTIME);
			exportPluginRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_EXPORT);
			runtimePluginRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_RUNTIME);
			fullClean.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FULL_CLEAN);
			productRootFiles.addExternalPrerequisite(dep.getName(), PRODUCT_ROOT_FILES);
			pluginStateClosure.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_CLOSURE);
		}
	}

	void addPlugins(IComponentReader reader, IFeaturePlugin[] plugins) throws CoreException
	{
		if(plugins == null)
			return;

		String os = TargetPlatform.getOS();
		String ws = TargetPlatform.getWS();
		String arch = TargetPlatform.getOSArch();
		ComponentQuery query = reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();
		ActionBuilder fullClean = cspec.getRequiredAction(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder exportPluginRefs = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_EXPORT);
		GroupBuilder runtimePluginRefs = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_RUNTIME);
		GroupBuilder pluginStateClosure = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_CLOSURE);
		for(IFeaturePlugin plugin : plugins)
		{
			if(!(isListOK(plugin.getOS(), os) && isListOK(plugin.getWS(), ws) && isListOK(plugin.getArch(),
				arch)))
				continue;

			ComponentRequest dep = createComponentRequest(plugin);
			if(query.skipComponent(dep))
				continue;
	
			cspec.addDependency(dep);
			exportPluginRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_EXPORT);
			runtimePluginRefs.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_RUNTIME);
			fullClean.addExternalPrerequisite(dep.getName(), ATTRIBUTE_FULL_CLEAN);
			pluginStateClosure.addExternalPrerequisite(dep.getName(), ATTRIBUTE_BUNDLE_CLOSURE);
		}
	}

	@Override
	public int compareTo(IResolutionBuilder other)
	{
		return 1;
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

	ComponentRequest createComponentRequest(IFeaturePlugin pluginReference) throws CoreException
	{
		return createComponentRequest(pluginReference.getId(), KeyConstants.PLUGIN_CATEGORY,
			pluginReference.getVersion());
	}

	ComponentRequest createComponentRequest(IFeatureChild feature) throws CoreException
	{
		return createComponentRequest(feature.getId(), KeyConstants.FEATURE_CATEGORY, feature.getVersion(),
			feature.getMatch());
	}
}
