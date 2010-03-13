/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IllegalPrerequisiteException;
import org.eclipse.buckminster.core.actor.MissingPrerequisiteException;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryFactory;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.SimpleMetadataRepositoryFactory;
import org.eclipse.equinox.internal.p2.publisher.eclipse.FeatureManifestParser;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.p2.publisher.eclipse.ProductFile;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;
import org.eclipse.equinox.p2.publisher.eclipse.URLEntry;
import org.eclipse.equinox.p2.repository.IRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.spi.p2.publisher.LocalizationHelper;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

@SuppressWarnings("restriction")
public class P2SiteGenerator extends AbstractActor {
	public static final String ID = "p2SiteGenerator"; //$NON-NLS-1$

	public static final String ALIAS_SITE = "site"; //$NON-NLS-1$

	public static final String ALIAS_SITE_DEFINER = "site.definer"; //$NON-NLS-1$

	public static final String ALIAS_PRODUCT_CONFIGS = "product.configs"; //$NON-NLS-1$

	private final static String MATCH_ALL = "*"; //$NON-NLS-1$

	private static final String WS_CARBON = "carbon"; //$NON-NLS-1$

	private static final String WS_COCOA = "cocoa"; //$NON-NLS-1$

	private static final String WS_GTK = "gtk"; //$NON-NLS-1$

	private static final String WS_MOTIF = "motif"; //$NON-NLS-1$

	private static final String WS_WIN32 = "win32"; //$NON-NLS-1$

	private static final String OS_AIX = "aix"; //$NON-NLS-1$

	private static final String OS_HPUX = "hpux"; //$NON-NLS-1$

	private static final String OS_LINUX = "linux"; //$NON-NLS-1$

	private static final String OS_MACOSX = "macosx"; //$NON-NLS-1$

	private static final String OS_SOLARIS = "solaris"; //$NON-NLS-1$

	private static final String OS_WIN32 = "win32"; //$NON-NLS-1$

	private static final String ARCH_IA64_32 = "ia64_32"; //$NON-NLS-1$

	private static final String ARCH_PPC = "ppc"; //$NON-NLS-1$

	private static final String ARCH_PPC64 = "ppc64"; //$NON-NLS-1$

	private static final String ARCH_SPARC = "sparc"; //$NON-NLS-1$

	private static final String ARCH_X86 = "x86"; //$NON-NLS-1$

	private static final String ARCH_X86_64 = "x86_64"; //$NON-NLS-1$

	private final static String[][] defaultKnownConfigs = { //
	{ WS_CARBON, OS_MACOSX, ARCH_PPC }, //
			{ WS_CARBON, OS_MACOSX, ARCH_X86 }, //
			{ WS_COCOA, OS_MACOSX, ARCH_X86 }, //
			{ WS_COCOA, OS_MACOSX, ARCH_X86_64 }, //
			{ WS_GTK, OS_LINUX, ARCH_PPC }, //
			{ WS_GTK, OS_LINUX, ARCH_PPC64 }, //
			{ WS_GTK, OS_LINUX, ARCH_X86 }, //
			{ WS_GTK, OS_LINUX, ARCH_X86_64 }, //
			{ WS_GTK, OS_SOLARIS, ARCH_SPARC }, //
			{ WS_GTK, OS_SOLARIS, ARCH_X86 }, //
			{ WS_MOTIF, OS_AIX, ARCH_PPC }, //
			{ WS_MOTIF, OS_HPUX, ARCH_IA64_32 }, //
			{ WS_MOTIF, OS_LINUX, ARCH_X86 }, //
			{ WS_MOTIF, OS_SOLARIS, ARCH_SPARC }, //
			{ WS_WIN32, OS_WIN32, ARCH_X86 }, //
			{ WS_WIN32, OS_WIN32, ARCH_X86_64 }, //
	};

	public static String[] getConfigurations(Map<String, ? extends Object> props) {
		// Return a list of configurations. Typically only one but might be
		// several if one or several of ws, os, or arch contains a wildcard
		//
		String targetWS = props.get(TargetPlatform.TARGET_WS).toString();
		if (targetWS == null)
			targetWS = org.eclipse.pde.core.plugin.TargetPlatform.getWS();

		String targetOS = props.get(TargetPlatform.TARGET_OS).toString();
		if (targetOS == null)
			targetOS = org.eclipse.pde.core.plugin.TargetPlatform.getOS();

		String targetArch = props.get(TargetPlatform.TARGET_ARCH).toString();
		if (targetArch == null)
			targetArch = org.eclipse.pde.core.plugin.TargetPlatform.getOSArch();

		if (!(MATCH_ALL.equals(targetOS) || MATCH_ALL.equals(targetWS) || MATCH_ALL.equals(targetArch)))
			return new String[] { targetWS + '.' + targetOS + '.' + targetArch };

		// TODO: Add a way to extend the list of known configurations. Or,
		// possibly
		// use what's in the executable feature.
		//
		ArrayList<String> possibleMatches = new ArrayList<String>();
		for (String[] config : defaultKnownConfigs) {
			if (isMatch(config, targetWS, targetOS, targetArch))
				possibleMatches.add(config[0] + '.' + config[1] + '.' + config[2]);
		}
		return possibleMatches.toArray(new String[possibleMatches.size()]);
	}

	private static void addProductAction(File sourceFolder, List<IPublisherAction> actions, IProductDescriptor product,
			Map<String, String> buildProperties) throws CoreException {
		String flavor = buildProperties.get("org.eclipse.p2.flavor"); //$NON-NLS-1$
		if (flavor == null)
			flavor = "tooling"; //$NON-NLS-1$

		File exeFeature = null;

		IFeatureModel launcherFeature = EclipsePlatformReaderType.getBestFeature(CSpecGenerator.LAUNCHER_FEATURE, null, null);
		if (launcherFeature == null)
			launcherFeature = EclipsePlatformReaderType.getBestFeature(CSpecGenerator.LAUNCHER_FEATURE_3_2, null, null);

		if (launcherFeature != null)
			exeFeature = new File(launcherFeature.getInstallLocation());

		if (product.useFeatures()) {
			List<IVersionedId> features = product.getFeatures();
			actions.add(new CategoriesAction(sourceFolder, buildProperties, features));
		}
		actions.add(new ProductAction(null, product, flavor, exeFeature));
	}

	private static IProductDescriptor getProductDescriptor(File productFile) throws CoreException {
		try {
			return new ProductFile(productFile.getAbsolutePath());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	private static boolean isMatch(String[] config, String targetWS, String targetOS, String targetArch) {
		return (MATCH_ALL.equals(targetWS) || config[0].equals(targetWS)) && (MATCH_ALL.equals(targetOS) || config[1].equals(targetOS))
				&& (MATCH_ALL.equals(targetArch) || config[2].equals(targetArch));
	}

	private static Feature parse(File sourceFolder, File featureFile) throws CoreException {
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(featureFile));
			FeatureManifestParser parser = new FeatureManifestParser();
			Feature feature = parser.parse(input);
			if (feature == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_parse_feature_manifest_file_0, featureFile));

			List<String> messageKeys = parser.getMessageKeys();
			String[] keyStrings = messageKeys.toArray(new String[messageKeys.size()]);
			feature.setLocalizations(LocalizationHelper.getDirPropertyLocalizations(sourceFolder, "feature", null, keyStrings)); //$NON-NLS-1$
			feature.setLocation(sourceFolder.toString());
			return feature;
		} catch (IOException e) {
			throw BuckminsterException.fromMessage(e, NLS.bind(Messages.unable_to_parse_feature_manifest_file_0, featureFile));
		} finally {
			IOUtils.close(input);
		}
	}

	private static Map<String, String> readBuildProperties(File sourceFolder) throws CoreException {
		InputStream input = null;
		try {
			File buildProps = new File(sourceFolder, IPDEConstants.BUILD_PROPERTIES_FILE);
			input = new BufferedInputStream(new FileInputStream(buildProps));
			return new BMProperties(input);
		} catch (FileNotFoundException e) {
			// This is OK. The build.properties file is not required
			return Collections.emptyMap();
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

	@Override
	public boolean isUpToDate(Action action, IModelCache ctx, long prerequisiteAge, long oldestTarget) throws CoreException {
		if (!super.isUpToDate(action, ctx, prerequisiteAge, oldestTarget))
			// Prerequisite is younger
			return false;

		IPath outputDir = action.getProductBase();
		if (outputDir == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.missing_product_base_in_0_actor, ID));

		Map<String, ? extends Object> properties = ctx.getProperties();
		outputDir = new Path(ExpandingProperties.expand(properties, outputDir.toPortableString(), 0));

		// We could of course check that all files referenced by the
		// artifacts.jar exists too but
		// we trust that the output is consistent. If it isn't, someone has
		// manually removed things
		// from it and then reverted the timestamp of the folder. It would be
		// somewhat paranoid to
		// check for htat.
		return outputDir.append("content.jar").toFile().exists() && outputDir.append("artifacts.jar").toFile().exists(); //$NON-NLS-1$//$NON-NLS-2$
	}

	public void run(IActionContext ctx, File siteDefiner, File sourceFolder, List<File> productConfigs, File siteFolder,
			Map<String, ? extends Object> properties) throws CoreException {
		if (siteDefiner == null || siteFolder == null)
			// Nothing to do
			return;

		// The site can be defined by a feature or a product.
		//
		String fileName = siteDefiner.getName();
		String siteDescriptorName;

		PublisherInfo info = new PublisherInfo();
		info.setConfigurations(getConfigurations(properties));

		Object siteDescriptor;
		if (fileName.equals("feature.xml")) //$NON-NLS-1$
		{
			Feature feature = parse(sourceFolder, siteDefiner);
			if (feature == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_parse_feature_from_0, siteDefiner));
			siteDescriptor = feature;
			siteDescriptorName = feature.getLabel();
			if (siteDescriptorName == null)
				siteDescriptorName = feature.getId();
		} else {
			IProductDescriptor productDesc = getProductDescriptor(siteDefiner);
			siteDescriptorName = productDesc.getProductName();
			if (siteDescriptorName == null)
				siteDescriptorName = productDesc.getId();

			siteDescriptor = productDesc;
		}

		IProvisioningAgent agent = CorePlugin.getDefault().getResolverAgent();
		URI siteURI = siteFolder.toURI();
		SimpleArtifactRepositoryFactory arFactory = new SimpleArtifactRepositoryFactory();
		arFactory.setAgent(agent);
		IArtifactRepository ar = arFactory.create(siteURI, siteDescriptorName + " - Artifact Repository", null, null); //$NON-NLS-1$
		String trueStr = Boolean.toString(true);
		ar.setProperty(IRepository.PROP_COMPRESSED, trueStr);
		ar.setProperty(Publisher.PUBLISH_PACK_FILES_AS_SIBLINGS, trueStr);

		info.setArtifactRepository(ar);
		info.setArtifactOptions(IPublisherInfo.A_PUBLISH | IPublisherInfo.A_INDEX);

		SimpleMetadataRepositoryFactory mdrFactory = new SimpleMetadataRepositoryFactory();
		mdrFactory.setAgent(agent);
		IMetadataRepository mdr = mdrFactory.create(siteURI, siteDescriptorName, null, null);
		mdr.setProperty(IRepository.PROP_COMPRESSED, trueStr);
		info.setMetadataRepository(mdr);

		IPublisherAction[] actions = createActions(ctx, sourceFolder, siteDescriptor, siteFolder, productConfigs);
		Publisher publisher = new Publisher(info);
		IStatus result = publisher.publish(actions, new NullProgressMonitor());
		if (result.getSeverity() == IStatus.ERROR)
			throw new CoreException(result);
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		Action action = ctx.getAction();
		IPath outputPath = AbstractActor.getSingleAttributePath(ctx, action, false);
		IPath site = null;
		IPath siteDefiner = null;
		CSpec cspec = action.getCSpec();
		List<IPath> productConfigs = null;
		for (Prerequisite preq : action.getPrerequisites()) {
			if (ALIAS_SITE.equals(preq.getAlias())) {
				// This prerequisite should appoint the site as a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt == null)
					continue;

				site = AbstractActor.getSingleAttributePath(ctx, rt, true);
				continue;
			}

			if (ALIAS_SITE_DEFINER.equals(preq.getAlias())) {
				// This prerequisite should appoint the site defining feature as
				// a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				if (rt != null)
					siteDefiner = AbstractActor.getSingleAttributePath(ctx, rt, false);
				continue;
			}

			if (ALIAS_PRODUCT_CONFIGS.equals(preq.getAlias())) {
				// This prerequisite should appoint the site defining feature as
				// a folder
				//
				Attribute rt = preq.getReferencedAttribute(cspec, ctx);
				productConfigs = AbstractActor.getPathList(ctx, rt, false);
				continue;
			}

			throw new IllegalPrerequisiteException(action, preq.getName());
		}

		if (site == null)
			throw new MissingPrerequisiteException(action, ALIAS_SITE);

		if (siteDefiner == null)
			throw new MissingPrerequisiteException(action, ALIAS_SITE_DEFINER);

		if (!outputPath.hasTrailingSeparator())
			throw new IllegalArgumentException(NLS.bind(org.eclipse.buckminster.core.Messages.output_of_action_0_must_be_folder, action
					.getQualifiedName()));

		File outputDir = outputPath.toFile().getAbsoluteFile();
		outputDir.mkdirs();

		Map<String, ? extends Object> props = ctx.getProperties();
		File siteDir = null;
		File siteFile = site.toFile().getAbsoluteFile();
		if (siteFile.isDirectory()) {
			// If input is a folder, then output should be a mirror of that.
			// Just
			// copy the structure.
			//
			org.eclipse.buckminster.core.helpers.FileUtils.deepCopy(siteFile, outputDir, ConflictResolution.REPLACE, monitor);
			siteDir = outputDir;
		} else if (siteFile.getName().endsWith(".zip")) //$NON-NLS-1$
		{
			// We need a temporary folder where we expand the site since we want
			// the output
			// to contain a zip when the input is a zip.
			siteDir = new File(props.get(KeyConstants.ACTION_TEMP).toString());
			try {
				FileUtils.unzipFile(siteFile, siteDir);
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			}
		}

		File siteDefinerFile = siteDefiner.toFile().getAbsoluteFile();

		List<File> productConfigFiles;
		if (productConfigs == null)
			productConfigFiles = Collections.emptyList();
		else {
			productConfigFiles = new ArrayList<File>(productConfigs.size());
			for (IPath path : productConfigs) {
				File productConfigFile = path.toFile().getAbsoluteFile();
				if (!productConfigFile.equals(siteDefinerFile))
					productConfigFiles.add(productConfigFile);
			}
		}

		run(ctx, siteDefinerFile, ctx.getComponentLocation().toFile(), productConfigFiles, siteDir, ctx.getProperties());
		if (siteDir != outputDir) {
			// Zip the content of the siteDir. The name of the zip should
			// be the same as the name of the input zip.
			//
			File outputZip = new File(outputDir, siteFile.getName());
			try {
				FileUtils.zip(siteDir.listFiles(), null, outputZip, FileUtils.createRootPathComputer(siteDir));
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			}
		}
		return Status.OK_STATUS;
	}

	private void collectFeatures(CSpec cspec, Map<IVersionedId, CSpec> cspecs, IActionContext ctx) throws CoreException {
		ComponentIdentifier ci = cspec.getComponentIdentifier();
		if (cspecs.put(new VersionedId(ci.getName(), ci.getVersion()), cspec) != null)
			return;

		Attribute refs = cspec.getAttribute(IPDEConstants.ATTRIBUTE_FEATURE_REFS);
		for (Prerequisite preq : refs.getPrerequisites()) {
			Attribute ref = preq.getReferencedAttribute(cspec, ctx);
			if (ref != null)
				collectFeatures(ref.getCSpec(), cspecs, ctx);
		}
	}

	private Map<IVersionedId, CSpec> collectFeatures(IActionContext ctx) throws CoreException {
		CSpec cspec = ctx.getAction().getCSpec();
		Map<IVersionedId, CSpec> cspecs = new HashMap<IVersionedId, CSpec>();
		collectFeatures(cspec, cspecs, ctx);
		return cspecs;
	}

	private IPublisherAction[] createActions(IActionContext ctx, File sourceFolder, Object siteDescriptor, File siteFolder, List<File> productConfigs)
			throws CoreException {
		ArrayList<IPublisherAction> actions = new ArrayList<IPublisherAction>();
		actions.add(new FeaturesAction(new File[] { new File(siteFolder, "features") }, collectFeatures(ctx))); //$NON-NLS-1$
		actions.add(new BundlesAction(new File[] { new File(siteFolder, "plugins") })); //$NON-NLS-1$

		Map<String, String> buildProperties = readBuildProperties(sourceFolder);
		if (siteDescriptor instanceof Feature) {
			Feature topFeature = (Feature) siteDescriptor;
			URLEntry[] siteRefs = topFeature.getDiscoverySites();
			if (siteRefs.length > 0)
				actions.add(new SiteReferencesAction(siteRefs));

			URLEntry mirrorsSite = topFeature.getUpdateSite();
			if (mirrorsSite != null && mirrorsSite.getURL() != null)
				actions.add(new MirrorsSiteAction(mirrorsSite.getURL()));

			ArrayList<IVersionedId> featureList = new ArrayList<IVersionedId>();
			for (FeatureEntry fe : topFeature.getEntries()) {
				if (fe.isPatch() || fe.isPlugin() || fe.isRequires())
					continue;

				featureList.add(new VersionedId(fe.getId(), fe.getVersion()));
			}
			actions.add(new CategoriesAction(sourceFolder, buildProperties, featureList));
		} else {
			IProductDescriptor product = (IProductDescriptor) siteDescriptor;
			addProductAction(sourceFolder, actions, product, buildProperties);
		}

		for (File productConfigFile : productConfigs) {
			File productSourceFolder = productConfigFile.getParentFile();
			addProductAction(productSourceFolder, actions, getProductDescriptor(productConfigFile), readBuildProperties(productSourceFolder));
		}
		if (!Boolean.parseBoolean(ctx.getProperties().get("site.retain.unpacked").toString())) //$NON-NLS-1$
			actions.add(new RemoveUnpackedSiblingsAction());
		return actions.toArray(new IPublisherAction[actions.size()]);
	}
}
