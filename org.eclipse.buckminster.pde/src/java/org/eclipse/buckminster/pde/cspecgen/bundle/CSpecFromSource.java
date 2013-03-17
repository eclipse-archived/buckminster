/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.cspecgen.bundle;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.buckminster.ant.actor.CopyActor;
import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.ProjectDescReader;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.jdt.ClasspathReader;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.internal.actor.FragmentsActor;
import org.eclipse.buckminster.pde.tasks.VersionConsolidator;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.internal.resources.LinkDescription;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.bundle.BundlePlugin;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.osgi.framework.Constants;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF,
 * plugin.xml and fragment.xml files.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CSpecFromSource extends CSpecGenerator {
	private static final String ATTRIBUTE_BUNDLE_EXTRAJARS = "bundle.extrajars"; //$NON-NLS-1$

	private static final String ATTRIBUTE_ECLIPSE_BUILD = WellknownActions.ECLIPSE.BUILD.toString();

	private static final String ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS = ATTRIBUTE_ECLIPSE_BUILD + ".requirements"; //$NON-NLS-1$

	private static final String ATTRIBUTE_ECLIPSE_BUILD_SOURCE = ATTRIBUTE_ECLIPSE_BUILD + ".source"; //$NON-NLS-1$

	private static final String ATTRIBUTE_ECLIPSE_CLEAN = WellknownActions.ECLIPSE.CLEAN.toString();

	private static final String PREFIX_CREATE_JAR = "create."; //$NON-NLS-1$

	private static final String PREFIX_ECLIPSE_BUILD_OUTPUT = "eclipse.build.output."; //$NON-NLS-1$

	private static final String PREFIX_ROUGE_SOURCE = "rouge.sources."; //$NON-NLS-1$

	private static final IClasspathEntry[] emptyClasspath = new IClasspathEntry[0];

	private static String getArtifactName(IPath buildOutput) {
		return PREFIX_ECLIPSE_BUILD_OUTPUT + pathToName(buildOutput);
	}

	private static String pathToName(IPath path) {
		path = path.setDevice(null).makeRelative();
		if (path.segmentCount() > 2)
			path = path.removeFirstSegments(path.segmentCount() - 2);
		return path.removeTrailingSeparator().toPortableString().replace('/', '.');
	}

	private final IBuildModel buildModel;

	private final IPluginBase plugin;

	private IProjectDescription projectDesc;

	public CSpecFromSource(CSpecBuilder cspecBuilder, ICatalogReader reader, IPluginBase plugin, IBuildModel buildModel) {
		super(cspecBuilder, reader);
		this.plugin = plugin;
		this.buildModel = buildModel;
	}

	@Override
	public void generate(IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 100);

		CSpecBuilder cspec = getCSpec();
		GroupBuilder classpath = cspec.addGroup(ATTRIBUTE_JAVA_BINARIES, true);
		GroupBuilder fullClean = cspec.addGroup(ATTRIBUTE_FULL_CLEAN, true);
		GroupBuilder bundleJars = cspec.addGroup(ATTRIBUTE_BUNDLE_JARS, true);
		cspec.addGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS, true);

		if (getReader().isFileSystemReader())
			projectDesc = ProjectDescReader.getProjectDescription(getReader(), MonitorUtils.subMonitor(monitor, 15));
		else {
			projectDesc = null;
			MonitorUtils.worked(monitor, 15);
		}

		addImports();
		MonitorUtils.worked(monitor, 5);

		IClasspathEntry[] classPath;
		try {
			classPath = ClasspathReader.getClasspath(getReader(), MonitorUtils.subMonitor(monitor, 45));
		} catch (CoreException e) {
			classPath = null;
		}
		if (classPath == null)
			classPath = new IClasspathEntry[0];

		fullClean.addLocalPrerequisite(generateRemoveDirAction("build", OUTPUT_DIR, false)); //$NON-NLS-1$
		fullClean.addLocalPrerequisite(cspec.addInternalAction(ATTRIBUTE_ECLIPSE_CLEAN, false));

		boolean simpleBundle = false;
		IBuild build = buildModel.getBuild();
		ArrayList<String> jarsToCompile = null;
		Map<IPath, IPath> outputMap = new HashMap<IPath, IPath>();
		for (IBuildEntry entry : build.getBuildEntries()) {
			String name = entry.getName();
			if (name.startsWith("source.")) //$NON-NLS-1$
			{
				if (name.length() == 8 && name.charAt(7) == '.') {
					simpleBundle = true;
				} else if (name.endsWith(".jar") && name.length() > 11) { //$NON-NLS-1$
					if (jarsToCompile == null)
						jarsToCompile = new ArrayList<String>();
					jarsToCompile.add(name.substring(7));
				}
			} else if (name.startsWith("output.") && name.length() > 7) { //$NON-NLS-1$
				String[] tokens = entry.getTokens();
				if (tokens.length == 1)
					outputMap.put(Path.fromPortableString(tokens[0]), Path.fromPortableString(name.substring(7)));
			}
		}

		// Exported entries in the classpath must be added to the
		// java.binaries export
		//
		ActionBuilder eclipseBuild = getAttributeEclipseBuild();
		Set<IPath> derivedArtifacts = new HashSet<IPath>();

		IPath[] projectRootReplacement = new IPath[1];
		HashMap<IPath, AttributeBuilder> eclipseBuildProducts = new HashMap<IPath, AttributeBuilder>();
		IPath componentHome = Path.fromPortableString(KeyConstants.ACTION_HOME_REF);
		IPath defaultOutputLocation = null;
		GroupBuilder ebSrcBld = null;
		int cnt = 0;
		for (IClasspathEntry cpe : classPath) {
			if (cpe.getEntryKind() != IClasspathEntry.CPE_SOURCE)
				continue;

			// add the class path entry to build sources
			IPath cpePath = asProjectRelativeFolder(cpe.getPath(), projectRootReplacement);
			ArtifactBuilder ab = cspec.addArtifact(ATTRIBUTE_ECLIPSE_BUILD_SOURCE + '_' + cnt++, false, projectRootReplacement[0]);
			ab.setBase(cpePath);
			if (ebSrcBld == null)
				ebSrcBld = getGroupEclipseBuildSource(true);
			ebSrcBld.addLocalPrerequisite(ab);

			// The output declared in the source entry is a product of the
			// Eclipse build. If there's no output declared, we use the
			// default.
			//
			IPath output = cpe.getOutputLocation();
			if (output == null) {
				if (defaultOutputLocation == null)
					defaultOutputLocation = getDefaultOutputLocation(classPath, projectRootReplacement);
				output = defaultOutputLocation;
			} else
				output = asProjectRelativeFolder(output, projectRootReplacement);

			if (output == null)
				continue;

			// Several source may contribute to the same output directory.
			// Make sure we only add it once.
			//
			if (eclipseBuildProducts.containsKey(output))
				continue;

			// Products use ${buckminster.output} as the default base so we
			// need
			// to prefix the project relative output here
			//
			IPath base;
			IPath relPath = null;
			if (output.isAbsolute()) {
				base = output;
			} else {
				base = projectRootReplacement[0];
				if (base == null)
					base = componentHome;
				base = base.append(output);

				// Find the expected location in the
				// bundle jar. We don't care about
				// '.' since that is handled elsewhere
				relPath = outputMap.get(output);
				if (relPath != null && relPath.segmentCount() == 1 && ".".equals(relPath.segment(0))) //$NON-NLS-1$
					relPath = null;

				if (relPath != null)
					derivedArtifacts.add(relPath);
			}

			GroupBuilder productRebase = null;
			String artifactName = getArtifactName(output);
			if (relPath != null && base.segmentCount() > relPath.segmentCount()) {
				// Rebase if possible. relPath must be a suffix of base
				IPath cmp = base.removeFirstSegments(base.segmentCount() - relPath.segmentCount());
				if (cmp.equals(relPath)) {
					productRebase = cspec.addGroup(artifactName, false);
					productRebase.setName(artifactName);

					// Change the name of the actual product and use
					// it as a prerequisite to this group
					artifactName += ".raw"; //$NON-NLS-1$
					productRebase.addLocalPrerequisite(artifactName);
					productRebase.setPrerequisiteRebase(base.removeLastSegments(relPath.segmentCount()));
				}
			}

			ArtifactBuilder ab2 = eclipseBuild.addProductArtifact(artifactName, false, base);

			// Add either the rebased group or the product as the
			// built product for later perusal
			eclipseBuildProducts.put(output, productRebase == null ? ab2 : productRebase);
		}

		AttributeBuilder buildSource = null;
		if (ebSrcBld != null)
			buildSource = normalizeGroup(ebSrcBld);

		// The classpath already contains all the re-exported stuff (it was
		// added when the imports were added). Only thing missing is the
		// output from the Eclipse build and the optional extra jar files.
		// TODO: We want to limit the 'eclipseBuild' prerequisite to
		// what this bundle actually exports
		//
		classpath.addLocalPrerequisite(eclipseBuild);

		// The bundle classpath can contain artifacts that can stem from three
		// different locations:
		// 1. The bundle itself, i.e. a simpleBundle containing .class files
		// rooted
		// at the bundle root
		// 2. Jars compiled from .class files produced by the eclipse build
		// (build entries)
		// 3. Pre-built extra jar files present in the bundle.
		//
		List<String> binIncludes;
		List<String> srcIncludes;
		if (getReader().isFileSystemReader()) {
			File baseDir = getReader().getLocation();
			binIncludes = expandBinFiles(baseDir, build);
			srcIncludes = expandSrcFiles(baseDir, build);
		} else {
			binIncludes = Collections.emptyList();
			srcIncludes = Collections.emptyList();
		}

		String bundleClassPath = null;
		if (plugin instanceof BundlePlugin) {
			IBundle bundle = ((BundlePlugin) plugin).getBundle();
			setFilter(bundle.getHeader(ICoreConstants.PLATFORM_FILTER));

			cspec.setShortDesc(expand(bundle.getHeader(Constants.BUNDLE_NAME)));
			bundleClassPath = bundle.getHeader(Constants.BUNDLE_CLASSPATH);
			if (bundleClassPath != null) {
				cnt = 0;
				GroupBuilder eaBld = null;
				StringTokenizer tokens = new StringTokenizer(bundleClassPath, ","); //$NON-NLS-1$
				while (tokens.hasMoreTokens()) {
					String token = tokens.nextToken().trim();
					if (simpleBundle && token.equals(".") || token.equals("./")) //$NON-NLS-1$ //$NON-NLS-2$
						continue;

					if (jarsToCompile != null && jarsToCompile.contains(token))
						//
						// Assume that this jar is produced by the eclipse build
						// and thus covered by inclusion from the project
						// classpath above.
						//
						continue;

					if (binIncludes.contains(token))
						// This one is covered by bin.includes so don't add it
						// here
						continue;

					// We don't know how this entry came about. Chances are it
					// has been checked in with the source.
					//
					ArtifactBuilder ab = cspec.addArtifact(ATTRIBUTE_BUNDLE_EXTRAJARS + '_' + cnt++, false, null);
					IPath eaPath = resolveLink(Path.fromPortableString(token), projectRootReplacement);
					ab.setBase(projectRootReplacement[0]);
					ab.addPath(eaPath);
					if (eaBld == null)
						eaBld = getGroupExtraJars();
					eaBld.addLocalPrerequisite(ab);
				}

				if (eaBld != null)
					normalizeGroup(eaBld);
			}
		}

		IBuildEntry secondaryDeps = build.getEntry(IBuildEntry.SECONDARY_DEPENDENCIES);
		if (secondaryDeps != null) {
			// Add dependencies unless they have been added as imports already
			//
			for (String depName : secondaryDeps.getTokens()) {
				ComponentRequestBuilder dep = cspec.createDependencyBuilder();
				dep.setName(depName);
				dep.setComponentTypeID(IComponentType.OSGI_BUNDLE);
				addDependency(dep);
			}
		}

		// The manifest version may end with "qualifier" in which case it needs
		// to be expanded.
		// The expansion will create a new copy in a different location. In case
		// there is no
		// expansion, we can use the original file.
		//
		IPath manifestFolder = resolveLink(new Path(IPDEBuildConstants.MANIFEST_FOLDER).append(MANIFEST), null).removeLastSegments(1)
				.addTrailingSeparator();
		AttributeBuilder manifest = null;
		boolean versionExpansion = false;
		Version version = cspec.getVersion();
		if (version != null) {
			String versionQualifier = VersionHelper.getQualifier(version);
			if (versionQualifier != null)
				versionExpansion = versionQualifier.endsWith(VersionQualifierTask.QUALIFIER_SUFFIX);
		}

		// Add the build.properties artifact. We want to manage that separately
		// since it
		// is one of the requirements for expanding the bundle version
		//
		cspec.addArtifact(ATTRIBUTE_BUILD_PROPERTIES, true, null).addPath(new Path(BUILD_PROPERTIES_FILE));

		if (versionExpansion) {
			// Add the action that will create the manifest copy with the
			// version expanded
			// Another action that will do the same for the manifest used for
			// the source bundle
			//
			IPath manifestPath = new Path(MANIFEST);
			ArtifactBuilder rawManifest = cspec.addArtifact(ATTRIBUTE_RAW_MANIFEST, false, manifestFolder);
			rawManifest.addPath(manifestPath);

			ActionBuilder versionExpansionAction = addAntAction(ATTRIBUTE_MANIFEST, TASK_EXPAND_BUNDLE_VERSION, false);

			versionExpansionAction.addLocalPrerequisite(ATTRIBUTE_RAW_MANIFEST, ALIAS_MANIFEST);
			versionExpansionAction.addLocalPrerequisite(ATTRIBUTE_BUILD_PROPERTIES, ALIAS_PROPERTIES);

			versionExpansionAction.setProductAlias(ALIAS_OUTPUT);
			versionExpansionAction.setProductBase(OUTPUT_DIR_TEMP);
			versionExpansionAction.addProductPath(manifestPath);
			manifest = versionExpansionAction;
		} else {
			// No expansion needed, use original file.
			//
			ArtifactBuilder rawManifest = cspec.addArtifact(ATTRIBUTE_MANIFEST, true, manifestFolder);
			rawManifest.addPath(new Path(MANIFEST));
			manifest = rawManifest;
		}

		// Create an action for building each jar.
		//
		if (jarsToCompile != null) {
			for (String jarName : jarsToCompile)
				derivedArtifacts.add(createJarAction(jarName, classPath, build, outputMap, simpleBundle ? eclipseBuildProducts : null));
		}

		if (simpleBundle) {
			derivedArtifacts.add(new Path(".")); //$NON-NLS-1$
			derivedArtifacts.add(new Path("./")); // Uncertain which one is used //$NON-NLS-1$
		}

		// The jar contents group represents all contents of the final jar
		// except the
		// manifest
		//
		GroupBuilder jarContents = getAttributeJarContents();

		// Add additional artifacts to be included in the bundle
		//
		boolean includeBuildProps = false;
		boolean considerAboutMappings = VersionConsolidator.getBooleanProperty(getReader().getNodeQuery().getProperties(),
				PROP_PDE_CONSIDER_ABOUT_MAPPINGS, true);
		if (!binIncludes.isEmpty()) {
			GroupBuilder binIncludesSource = null;
			cnt = 0;
			for (String token : binIncludes) {
				if (BUNDLE_FILE.equalsIgnoreCase(token))
					//
					// Handled separately (might be derived)
					//
					continue;

				if (versionExpansion && BUILD_PROPERTIES_FILE.equals(token)) {
					includeBuildProps = true;
					continue;
				}

				IPath binInclude = new Path(token);
				if (derivedArtifacts.contains(binInclude))
					//
					// Not an artifact. This is produced by some action
					//
					continue;

				if (binIncludesSource == null)
					binIncludesSource = cspec.addGroup(ATTRIBUTE_BIN_INCLUDES, false);

				if (considerAboutMappings && ABOUT_MAPPINGS_FILE.equalsIgnoreCase(token)) {
					AttributeBuilder aboutMappings = addAboutMappingsAction(binInclude, projectRootReplacement[0]);
					binIncludesSource.addLocalPrerequisite(aboutMappings);
				} else {
					IPath biPath = resolveLink(binInclude, projectRootReplacement);
					ArtifactBuilder ab = cspec.addArtifact(ATTRIBUTE_BIN_INCLUDES + '_' + cnt++, false, projectRootReplacement[0]);
					ab.addPath(biPath);
					binIncludesSource.addLocalPrerequisite(ab);
				}
			}

			if (binIncludesSource != null) {
				normalizeGroup(binIncludesSource);
				jarContents.addLocalPrerequisite(ATTRIBUTE_BIN_INCLUDES);
			}
			if (includeBuildProps)
				jarContents.addLocalPrerequisite(ATTRIBUTE_BUILD_PROPERTIES);
		}

		GroupBuilder srcIncludesSource = null;
		if (!srcIncludes.isEmpty()) {
			cnt = 0;
			for (String token : srcIncludes) {
				IPath srcInclude = new Path(token);

				if (srcIncludesSource == null)
					srcIncludesSource = cspec.addGroup(ATTRIBUTE_SRC_INCLUDES, false);

				IPath biPath = resolveLink(srcInclude, projectRootReplacement);
				ArtifactBuilder ab = cspec.addArtifact(ATTRIBUTE_SRC_INCLUDES + '_' + cnt++, false, projectRootReplacement[0]);
				ab.addPath(biPath);
				srcIncludesSource.addLocalPrerequisite(ab);
			}
		}

		if (buildSource != null) {
			if (srcIncludesSource == null)
				srcIncludesSource = cspec.addGroup(IBuildEntry.SRC_INCLUDES, false);
			srcIncludesSource.addLocalPrerequisite(buildSource);
		}

		if (simpleBundle && !binIncludes.isEmpty()) {
			// These products from the eclipse.build will contain the .class
			// files for the bundle
			//
			String excludesStr = null;
			IBuildEntry excludes = build.getEntry(PROPERTY_EXCLUDE_PREFIX + '.');
			if (excludes != null)
				excludesStr = TextUtils.concat(excludes.getTokens(), ","); //$NON-NLS-1$

			for (AttributeBuilder product : eclipseBuildProducts.values()) {
				if (excludesStr == null)
					jarContents.addLocalPrerequisite(product);
				else {
					String pruneName = product.getName() + ".pruned"; //$NON-NLS-1$
					ActionBuilder prune = cspec.addAction(pruneName, false, CopyActor.ID, false);
					prune.addLocalPrerequisite(product);
					prune.addProperty(CopyActor.PROP_EXCLUDES, excludesStr, false);
					prune.setProductBase(OUTPUT_DIR_TEMP.append(pruneName));
					jarContents.addLocalPrerequisite(prune);
				}
			}
		}

		ActionBuilder buildPlugin;
		String jarName = plugin.getId() + '_' + plugin.getVersion() + ".jar"; //$NON-NLS-1$
		IPath jarPath = Path.fromPortableString(jarName);
		if (getReader().isFileSystemReader()
				&& (getReader().exists(jarName, new NullProgressMonitor()) || getLinkDescriptions().containsKey(jarPath))) {
			buildPlugin = addAntAction(ATTRIBUTE_BUNDLE_JAR, TASK_COPY_GROUP, true);
			buildPlugin.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
			IPath resolvedJarPath = resolveLink(jarPath, projectRootReplacement);
			ArtifactBuilder importedJar = cspec.addArtifact(ATTRIBUTE_IMPORTED_JAR, false, projectRootReplacement[0]);
			importedJar.addPath(resolvedJarPath);
			buildPlugin.getPrerequisitesBuilder().addLocalPrerequisite(importedJar);
		} else {
			buildPlugin = addAntAction(ATTRIBUTE_BUNDLE_JAR, TASK_CREATE_BUNDLE_JAR, true);
			buildPlugin.addLocalPrerequisite(manifest.getName(), ALIAS_MANIFEST);
			buildPlugin.addLocalPrerequisite(jarContents.getName(), ALIAS_REQUIREMENTS);
		}
		addGenerateApiDescription(build);

		buildPlugin.setProductAlias(ALIAS_OUTPUT);
		buildPlugin.setProductBase(OUTPUT_DIR_JAR);
		buildPlugin.setUpToDatePolicy(UpToDatePolicy.COUNT);
		buildPlugin.setProductFileCount(1);

		GroupBuilder bundleAndFragments = cspec.addGroup(ATTRIBUTE_BUNDLE_AND_FRAGMENTS, true);
		IPluginModelBase model = plugin.getPluginModel();
		if (model instanceof IFragmentModel)
			addBundleHostDependency((IFragmentModel) model);
		else {
			ActionBuilder copyTargetFragments = cspec.addAction(ATTRIBUTE_TARGET_FRAGMENTS, false, ACTOR_COPY_TARGET_FRAGMENTS, false);
			copyTargetFragments.setProductAlias(ALIAS_OUTPUT);
			copyTargetFragments.setProductBase(OUTPUT_DIR_FRAGMENTS);
			copyTargetFragments.addLocalPrerequisite(getAttributeEclipseBuild());
			copyTargetFragments.setUpToDatePolicy(UpToDatePolicy.ACTOR);
			copyTargetFragments.addProperty(FragmentsActor.PROP_FRAGMENT_ATTRIBUTE, ATTRIBUTE_BUNDLE_JAR, false);
			bundleAndFragments.addLocalPrerequisite(copyTargetFragments);
		}
		bundleAndFragments.addLocalPrerequisite(buildPlugin);
		bundleJars.addLocalPrerequisite(bundleAndFragments);

		GroupBuilder bundleAndFragmentsSource = cspec.addGroup(ATTRIBUTE_BUNDLE_AND_FRAGMENTS_SOURCE, true);
		if (!(model instanceof IFragmentModel)) {
			ActionBuilder copyTargetFragmentsSource = cspec.addAction(ATTRIBUTE_TARGET_FRAGMENTS_SOURCE, false, ACTOR_COPY_TARGET_FRAGMENTS, false);
			copyTargetFragmentsSource.setProductAlias(ALIAS_OUTPUT);
			copyTargetFragmentsSource.setProductBase(OUTPUT_DIR_FRAGMENTS_SOURCE);
			copyTargetFragmentsSource.setUpToDatePolicy(UpToDatePolicy.ACTOR);
			copyTargetFragmentsSource.addProperty(FragmentsActor.PROP_FRAGMENT_ATTRIBUTE, ATTRIBUTE_SOURCE_BUNDLE_JAR, false);
			bundleAndFragmentsSource.addLocalPrerequisite(copyTargetFragmentsSource);
		}

		if (srcIncludesSource != null) {
			// Add Actions to create a source bundle jar

			// this is for the source manifest
			ActionBuilder sourceManifestAction = addAntAction(ATTRIBUTE_SOURCE_MANIFEST, TASK_CREATE_SOURCE_MANIFEST, false);
			sourceManifestAction.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
			sourceManifestAction.addLocalPrerequisite(ATTRIBUTE_BUILD_PROPERTIES, ALIAS_PROPERTIES);

			sourceManifestAction.setProductAlias(ALIAS_OUTPUT);
			sourceManifestAction.setProductBase(OUTPUT_DIR_TEMP);
			IPath sourceManifestPath = new Path(SOURCE_MANIFEST);
			sourceManifestAction.addProductPath(sourceManifestPath);

			// this is for the source bundle
			ActionBuilder sourceBundleAction = addAntAction(ATTRIBUTE_SOURCE_BUNDLE_JAR, TASK_CREATE_BUNDLE_JAR, true);
			sourceBundleAction.addLocalPrerequisite(IBuildEntry.SRC_INCLUDES, ALIAS_REQUIREMENTS);
			sourceBundleAction.addLocalPrerequisite(ATTRIBUTE_SOURCE_MANIFEST, ALIAS_MANIFEST);
			sourceBundleAction.setProductAlias(ALIAS_OUTPUT);
			sourceBundleAction.setProductBase(OUTPUT_DIR_SOURCE_JAR);
			bundleAndFragmentsSource.addLocalPrerequisite(sourceBundleAction);

			GeneratorBuilder genBld = cspec.createGeneratorBuilder();
			genBld.setAttribute(ATTRIBUTE_SOURCE_BUNDLE_JAR);
			genBld.setGeneratesType(IComponentType.OSGI_BUNDLE);
			genBld.setName(cspec.getName() + ".source"); //$NON-NLS-1$
			cspec.addGenerator(genBld);
		} else {
			// Add an empty group so that it can be referenced
			cspec.addGroup(ATTRIBUTE_SOURCE_BUNDLE_JAR, true);
		}

		addProducts(MonitorUtils.subMonitor(monitor, 20));
		monitor.done();
	}

	protected AttributeBuilder addAboutMappingsAction(IPath rawAboutMappingPath, IPath projectRoot) throws CoreException {
		ArtifactBuilder rawAboutMappings = getCSpec().addArtifact(ATTRIBUTE_RAW_ABOUT_MAPPINGS, false, projectRoot);
		rawAboutMappings.addPath(rawAboutMappingPath);
		ActionBuilder mappingAction = addAntAction(ACTION_ABOUT_MAPPINGS, TASK_REPLACE_TWO_TOKENS, false);
		mappingAction.addProperty("token1", "@build@", false); //$NON-NLS-1$ //$NON-NLS-2$
		mappingAction.addProperty("value1", "${build.id}", false); //$NON-NLS-1$ //$NON-NLS-2$
		mappingAction.addProperty("token2", "@version@", false); //$NON-NLS-1$ //$NON-NLS-2$
		mappingAction.addProperty("value2", "${" + getCSpec().getName() + ".unqualified.owner.version}", false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		mappingAction.addLocalPrerequisite(ATTRIBUTE_RAW_ABOUT_MAPPINGS, "action.input"); //$NON-NLS-1$
		mappingAction.addLocalPrerequisite(getCSpec().addAction("ownerVersion", false, "ownerVersionExtractor", false)); //$NON-NLS-1$//$NON-NLS-2$
		mappingAction.setProductAlias(ALIAS_OUTPUT);
		mappingAction.setProductBase(OUTPUT_DIR_TEMP);
		mappingAction.addProductPath(Path.fromPortableString(ABOUT_MAPPINGS_FILE));
		return mappingAction;
	}

	protected void addGenerateApiDescription(IBuild build) throws CoreException {
		IBuildEntry apiDescription = build.getEntry("generateAPIDescription"); //$NON-NLS-1$
		if (apiDescription == null)
			return;

		String[] tokens = apiDescription.getTokens();
		if (tokens.length != 1 || !Boolean.parseBoolean(tokens[0]))
			return;

		CSpecBuilder cspec = getCSpec();
		GroupBuilder jarContentsOld = getAttributeJarContents();
		cspec.removeAttribute(ATTRIBUTE_JAR_CONTENTS);
		jarContentsOld.setName(ATTRIBUTE_JAR_CONTENTS + ".wo_apidesc"); //$NON-NLS-1$
		cspec.addAttribute(jarContentsOld);

		ActionBuilder apiGeneration = addAntAction(ACTION_GENERATE_API_DESCRIPTION, TASK_API_GENERATION, true);
		apiGeneration.addLocalPrerequisite(ATTRIBUTE_MANIFEST, ALIAS_MANIFEST);
		apiGeneration.addLocalPrerequisite(jarContentsOld.getName(), ALIAS_BINARY);
		apiGeneration.addLocalPrerequisite(ATTRIBUTE_ECLIPSE_BUILD_SOURCE, ALIAS_SOURCE);
		apiGeneration.setProductAlias(ALIAS_OUTPUT);
		apiGeneration.setProductBase(OUTPUT_DIR.append("api_description")); //$NON-NLS-1$
		apiGeneration.addProductPath(Path.fromPortableString(".api_description")); //$NON-NLS-1$

		GroupBuilder jarContents = getAttributeJarContents();
		jarContents.addLocalPrerequisite(jarContentsOld);
		jarContents.addLocalPrerequisite(apiGeneration);
	}

	protected void addImports() throws CoreException {
		ImportSpecification[] imports = getImports(plugin);
		if (imports.length == 0)
			return;

		ComponentQuery query = getReader().getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder fullClean = cspec.getRequiredGroup(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder reExports = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);
		GroupBuilder bundleJars = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_JARS);
		GroupBuilder productConfigExports = cspec.getRequiredGroup(ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);

		for (ImportSpecification pluginImport : imports) {
			String pluginId = pluginImport.getName();
			if (pluginId.equals(Constants.SYSTEM_BUNDLE_SYMBOLICNAME))
				continue;

			ComponentRequestBuilder dependency = createDependency(pluginImport, IComponentType.OSGI_BUNDLE);
			if (skipComponent(query, dependency) || !addDependency(dependency))
				continue;

			addExternalPrerequisite(fullClean, dependency, ATTRIBUTE_FULL_CLEAN);
			addExternalPrerequisite(bundleJars, dependency, ATTRIBUTE_BUNDLE_JARS);
			addExternalPrerequisite(getAttributeBuildRequirements(), dependency, ATTRIBUTE_JAVA_BINARIES);
			if (pluginImport.isExported())
				addExternalPrerequisite(reExports, dependency, ATTRIBUTE_JAVA_BINARIES);
			addExternalPrerequisite(productConfigExports, dependency, ATTRIBUTE_PRODUCT_CONFIG_EXPORTS);
		}
	}

	@Override
	protected String getProductOutputFolder(String productId) {
		IBuildEntry entry = buildModel.getBuild().getEntry(productId + TOP_FOLDER_SUFFIX);
		if (entry != null) {
			String[] tokens = entry.getTokens();
			if (tokens.length == 1)
				return tokens[0];
		}
		return null;
	}

	@Override
	protected String getPropertyFileName() {
		return PLUGIN_PROPERTIES_FILE;
	}

	private IPath asProjectRelativeFolder(IPath classpathEntryPath, IPath[] projectRootReplacement) {
		return resolveLink(classpathEntryPath.removeFirstSegments(1).addTrailingSeparator(), projectRootReplacement);
	}

	private IPath createJarAction(String jarName, IClasspathEntry[] classPath, IBuild build, Map<IPath, IPath> outputMap,
			Map<IPath, AttributeBuilder> eclipseBuildProducts) throws CoreException {
		CSpecBuilder cspec = getCSpec();
		IPath jarPath = new Path(jarName);

		String jarFlatName = pathToName(jarPath);
		ActionBuilder action = addAntAction(PREFIX_CREATE_JAR + jarFlatName, TASK_CREATE_JAR, false);
		action.setProductBase(OUTPUT_DIR_TEMP);
		action.addProductPath(jarPath);

		action.setProductAlias(ALIAS_OUTPUT);
		action.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		getAttributeJarContents().addLocalPrerequisite(action);

		// Check if the source for this jar is included as a source in the
		// development classpath. If it is, then assume that we can use
		// the eclipse.build to produce the needed .class files.
		//
		IPath[][] missingEntriesRet = new IPath[1][];
		IClasspathEntry[] srcEntries = getSourceEntries(classPath, jarName, build, missingEntriesRet);
		IPath[] missingEntries = missingEntriesRet[0];

		if (missingEntries.length > 0) {
			// We have sources that are not input to the eclipse.build. We need
			// some custom action here in order to deal with them.
			// TODO: investigate
			ArtifactBuilder rougeSources = cspec.addArtifact(PREFIX_ROUGE_SOURCE + jarFlatName, false, null);
			for (IPath notFound : missingEntries)
				rougeSources.addPath(notFound);
			action.addLocalPrerequisite(rougeSources);
		}

		// Remaining sources corresponds to IClasspathEntries in the development
		// classpath so let's trust the output from the eclipse.build
		//
		IPath[] projectRootReplacement = new IPath[1];
		IPath defaultOutputLocation = getDefaultOutputLocation(classPath, projectRootReplacement);
		for (IClasspathEntry cpe : srcEntries) {
			IPath output = cpe.getOutputLocation();
			if (output == null) {
				output = defaultOutputLocation;
				if (output == null)
					continue;
			} else
				output = asProjectRelativeFolder(output, projectRootReplacement);

			// Several source entries might share the same output folder
			//
			String artifactName = getArtifactName(output);
			IPath targetOutput = outputMap.get(output);
			if (targetOutput != null) {
				IBuildEntry excludes = build.getEntry(PROPERTY_EXCLUDE_PREFIX + targetOutput.toPortableString());
				if (excludes != null) {
					String pruneName = artifactName + ".pruned"; //$NON-NLS-1$
					if (cspec.getAttribute(pruneName) != null)
						continue;

					String excludesString = TextUtils.concat(excludes.getTokens(), ","); //$NON-NLS-1$
					ActionBuilder prune = cspec.addAction(pruneName, false, CopyActor.ID, false);
					prune.addProperty(CopyActor.PROP_EXCLUDES, excludesString, false);
					prune.addLocalPrerequisite(artifactName);
					prune.setProductBase(OUTPUT_DIR_TEMP.append(pruneName));
					action.addLocalPrerequisite(pruneName);
					if (eclipseBuildProducts != null)
						eclipseBuildProducts.remove(output);
					continue;
				}
			}

			if (action.getPrerequisite(artifactName) == null) {
				action.addLocalPrerequisite(artifactName);
				if (eclipseBuildProducts != null)
					eclipseBuildProducts.remove(output);
			}
		}
		return jarPath;
	}

	private GroupBuilder getAttributeBuildRequirements() throws CoreException {
		CSpecBuilder cspec = getCSpec();
		GroupBuilder eclipseBuildReqs = cspec.getGroup(ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS);
		if (eclipseBuildReqs == null) {
			eclipseBuildReqs = cspec.addGroup(ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS, false);
			getAttributeEclipseBuild().addLocalPrerequisite(eclipseBuildReqs);
		}
		return eclipseBuildReqs;
	}

	private ActionBuilder getAttributeEclipseBuild() throws CoreException {
		CSpecBuilder cspec = getCSpec();
		ActionBuilder eclipseBuild = cspec.getActionBuilder(ATTRIBUTE_ECLIPSE_BUILD);
		if (eclipseBuild == null)
			eclipseBuild = cspec.addInternalAction(ATTRIBUTE_ECLIPSE_BUILD, false);
		return eclipseBuild;
	}

	private GroupBuilder getAttributeJarContents() throws CoreException {
		CSpecBuilder cspec = getCSpec();
		GroupBuilder jarContent = cspec.getGroup(ATTRIBUTE_JAR_CONTENTS);
		if (jarContent == null)
			jarContent = cspec.addGroup(ATTRIBUTE_JAR_CONTENTS, false);
		return jarContent;
	}

	private IPath getDefaultOutputLocation(IClasspathEntry[] classPath, IPath[] projectRootReplacement) {
		for (IClasspathEntry cpe : classPath) {
			if (cpe.getContentKind() == ClasspathEntry.K_OUTPUT)
				return asProjectRelativeFolder(cpe.getPath(), projectRootReplacement);
		}
		return null;
	}

	private GroupBuilder getGroupEclipseBuildSource(boolean createIfMissing) throws CoreException {
		CSpecBuilder cspec = getCSpec();
		GroupBuilder buildSource = cspec.getGroup(ATTRIBUTE_ECLIPSE_BUILD_SOURCE);
		if (buildSource == null && createIfMissing) {
			buildSource = cspec.addGroup(ATTRIBUTE_ECLIPSE_BUILD_SOURCE, true);
			getAttributeEclipseBuild().addLocalPrerequisite(ATTRIBUTE_ECLIPSE_BUILD_SOURCE);
		}
		return buildSource;
	}

	private GroupBuilder getGroupExtraJars() throws CoreException {
		CSpecBuilder cspec = getCSpec();
		GroupBuilder extraJars = cspec.getGroup(ATTRIBUTE_BUNDLE_EXTRAJARS);
		if (extraJars == null) {
			extraJars = cspec.addGroup(ATTRIBUTE_BUNDLE_EXTRAJARS, false);
			cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES).addLocalPrerequisite(ATTRIBUTE_BUNDLE_EXTRAJARS);
			getAttributeBuildRequirements().addLocalPrerequisite(ATTRIBUTE_BUNDLE_EXTRAJARS);
		}
		return extraJars;
	}

	private Map<IPath, LinkDescription> getLinkDescriptions() {
		Map<IPath, LinkDescription> linkDescriptors = null;
		if (projectDesc instanceof ProjectDescription)
			linkDescriptors = ((ProjectDescription) projectDesc).getLinks();

		if (linkDescriptors == null)
			linkDescriptors = Collections.emptyMap();
		return linkDescriptors;
	}

	private IClasspathEntry[] getSourceEntries(IClasspathEntry[] classPath, String jarName, IBuild build, IPath[][] notFound) {
		IBuildEntry srcIncl = build.getEntry(IBuildEntry.JAR_PREFIX + jarName);
		if (srcIncl == null) {
			notFound[0] = Trivial.EMPTY_PATH_ARRAY;
			return emptyClasspath;
		}

		List<IClasspathEntry> cpEntries = null;
		List<IPath> missingEntries = null;
		if (classPath == null) {
			for (String src : srcIncl.getTokens()) {
				if (missingEntries == null)
					missingEntries = new ArrayList<IPath>();
				missingEntries.add(resolveLink(Path.fromPortableString(src).addTrailingSeparator(), null));
			}
		} else {
			for (String src : srcIncl.getTokens()) {
				boolean found = false;
				IPath srcPath = resolveLink(Path.fromPortableString(src), null).addTrailingSeparator();
				for (IClasspathEntry ce : classPath) {
					if (ce.getEntryKind() != IClasspathEntry.CPE_SOURCE)
						continue;

					IPath cePath = asProjectRelativeFolder(ce.getPath(), null);
					if (cePath.equals(srcPath)) {
						found = true;
						if (cpEntries == null)
							cpEntries = new ArrayList<IClasspathEntry>();
						cpEntries.add(ce);
						break;
					}
				}
				if (!found) {
					if (missingEntries == null)
						missingEntries = new ArrayList<IPath>();
					missingEntries.add(srcPath);
				}
			}
		}
		notFound[0] = missingEntries == null ? Trivial.EMPTY_PATH_ARRAY : missingEntries.toArray(new IPath[missingEntries.size()]);
		return cpEntries == null ? emptyClasspath : cpEntries.toArray(new IClasspathEntry[cpEntries.size()]);
	}

	private AttributeBuilder normalizeGroup(GroupBuilder bld) throws CoreException {
		if (bld == null)
			return null;

		List<PrerequisiteBuilder> preqs = bld.getPrerequisites();
		if (preqs.size() == 0)
			return null;

		boolean singleCanReplace = true;
		CSpecBuilder cspec = getCSpec();
		HashMap<IPath, ArtifactBuilder> byBase = new HashMap<IPath, ArtifactBuilder>();
		for (PrerequisiteBuilder pq : new ArrayList<PrerequisiteBuilder>(preqs)) {
			if (pq.getComponentName() != null) {
				singleCanReplace = false;
				continue;
			}

			AttributeBuilder ab = cspec.getAttribute(pq.getName());
			if (!(ab instanceof ArtifactBuilder)) {
				singleCanReplace = false;
				continue;
			}

			ArtifactBuilder arb = (ArtifactBuilder) ab;
			ArtifactBuilder prev = byBase.get(arb.getBase());
			if (prev == null) {
				byBase.put(arb.getBase(), arb);
				continue;
			}
			for (IPath path : arb.getPaths())
				prev.addPath(path);
			bld.removePrerequisite(pq.toString());
			cspec.removeAttribute(arb.getName());
		}

		if (singleCanReplace && byBase.size() == 1) {
			ArtifactBuilder ab = byBase.values().iterator().next();
			String name = bld.getName();
			cspec.removeAttribute(name);
			cspec.removeAttribute(ab.getName());
			ab.setName(name);
			getCSpec().addAttribute(ab);
			return ab;
		}
		return bld;
	}

	private IPath resolveLink(IPath path, IPath[] projectRootReplacement) {
		if (projectRootReplacement != null)
			projectRootReplacement[0] = null;

		if (path == null || path.isAbsolute() || path.isEmpty())
			return path;

		for (Map.Entry<IPath, LinkDescription> entry : getLinkDescriptions().entrySet()) {
			IPath linkSource = entry.getKey();
			if (linkSource.isPrefixOf(path)) {
				URI locationURI = entry.getValue().getLocationURI();

				/*
				 * Explicitly resolve relative path via workspace linked
				 * resources
				 */
				URI resolvedURI = ResourcesPlugin.getWorkspace().getPathVariableManager().resolveURI(locationURI);
				IPath linkTarget = FileUtil.toPath(resolvedURI);
				int sourceSegs = linkSource.segmentCount();

				if (projectRootReplacement != null) {
					if (linkTarget.setDevice(null).removeFirstSegments(linkTarget.segmentCount() - sourceSegs).equals(linkSource)) {
						projectRootReplacement[0] = linkTarget.removeLastSegments(sourceSegs);
						break;
					}
				}

				if (sourceSegs == path.segmentCount()) {
					if (path.hasTrailingSeparator())
						path = linkTarget.addTrailingSeparator();
					else
						path = linkTarget;
				} else
					path = linkTarget.append(path.removeFirstSegments(sourceSegs));
				break;
			}
		}
		return path;
	}
}
