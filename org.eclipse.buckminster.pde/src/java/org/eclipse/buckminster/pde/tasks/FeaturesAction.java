package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet.NameEntry;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.OrSelector;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.cspecgen.CSpecGenerator;
import org.eclipse.buckminster.pde.tasks.FeatureRootAdvice.ConfigAdvice;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.core.helpers.StringHelper;
import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.publisher.FileSetDescriptor;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.metadata.expression.IMatchExpression;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;
import org.eclipse.equinox.p2.repository.artifact.spi.ArtifactDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.Utils;

@SuppressWarnings("restriction")
public class FeaturesAction extends org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction {
	private static final Project PROPERTY_REPLACER = new Project();

	private static FeatureRootAdvice createRootAdvice(String featureId, Properties buildProperties, IPath baseDirectory, String[] configs) {
		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> configMap = Utils.processRootProperties(buildProperties, true);
		if (configMap.size() == 1) {
			Map<String, String> entry = configMap.get(Utils.ROOT_COMMON);
			if (entry != null && entry.isEmpty())
				return null;
		}

		FeatureRootAdvice advice = new FeatureRootAdvice(featureId);
		for (Map.Entry<String, Map<String, String>> entry : configMap.entrySet()) {
			String config = entry.getKey();
			Map<String, String> rootMap = entry.getValue();
			populateConfigAdvice(advice, config, rootMap, baseDirectory, configs);
		}
		return advice;
	}

	private static void populateConfigAdvice(FeatureRootAdvice advice, String config, Map<String, String> rootMap, IPath baseDirectory,
			String[] configs) {
		if (config.equals(Utils.ROOT_COMMON))
			config = ""; //$NON-NLS-1$
		else {
			config = reorderConfig(config);
			int idx = configs.length;
			while (--idx >= 0)
				if (config.equals(configs[idx]))
					break;

			if (idx < 0)
				// Config was not on the list
				return;
		}

		ConfigAdvice configAdvice = advice.getConfigAdvice(config);
		FileSetDescriptor descriptor = configAdvice.getDescriptor();
		List<String> permissionsKeys = new ArrayList<String>();
		for (Map.Entry<String, String> rootEntry : rootMap.entrySet()) {
			String key = rootEntry.getKey();
			if (key.equals(Utils.ROOT_LINK)) {
				descriptor.setLinks(rootEntry.getValue());
				continue;
			}

			if (key.startsWith(Utils.ROOT_PERMISSIONS)) {
				permissionsKeys.add(key);
				continue;
			}

			for (String rootValue : StringHelper.getArrayFromString(rootEntry.getValue(), ',')) {
				String rootName = rootValue;
				boolean isAbsolute = rootName.startsWith("absolute:"); //$NON-NLS-1$
				if (isAbsolute)
					rootName = rootName.substring(9);

				boolean isFile = rootName.startsWith("file:"); //$NON-NLS-1$
				if (isFile)
					rootName = rootName.substring(5);

				if (rootName.length() == 0)
					continue;

				IPath basePath;
				String pattern;

				// Base path cannot contain wild card characters
				//
				IPath rootPath = Path.fromPortableString(rootName);
				int firstStar = -1;
				int numSegs = rootPath.segmentCount();
				for (int idx = 0; idx < numSegs; ++idx)
					if (rootPath.segment(idx).indexOf('*') >= 0) {
						firstStar = idx;
						break;
					}

				if (firstStar == -1) {
					if (isFile) {
						pattern = rootPath.lastSegment();
						basePath = rootPath.removeLastSegments(1);
					} else {
						pattern = "**"; //$NON-NLS-1$
						basePath = rootPath;
					}
				} else {
					basePath = rootPath.removeLastSegments(rootPath.segmentCount() - (firstStar + 1));
					pattern = rootPath.removeFirstSegments(firstStar).toPortableString();
				}

				if (!isAbsolute)
					basePath = baseDirectory.append(basePath.makeRelative());

				FileSet fileset = new FileSet();
				fileset.setProject(PROPERTY_REPLACER);
				fileset.setErrorOnMissingDir(false);
				File base = basePath.toFile();
				fileset.setDir(base);
				NameEntry include = fileset.createInclude();
				include.setName(pattern);

				String[] files = fileset.getDirectoryScanner().getIncludedFiles();
				if (files.length == 0) {
					PDEPlugin.getLogger().warning(
							NLS.bind(Messages.rootAdviceForConfig_0_in_1_at_2_does_not_appoint_existing_artifacts, new Object[] { config,
									IPDEBuildConstants.PROPERTIES_FILE, baseDirectory.toOSString() }));
					continue;
				}

				IPath destBaseDir = Path.fromPortableString(key);
				for (String found : files) {
					IPath foundFile = Path.fromPortableString(found);
					String destDir = destBaseDir.append(foundFile.removeLastSegments(1)).toPortableString();
					configAdvice.addRootfile(new File(base, found), destDir);
				}
			}
		}

		for (String permissionKey : permissionsKeys) {
			String permissionString = rootMap.get(permissionKey);
			String[] names = StringHelper.getArrayFromString(permissionString, ',');

			OrSelector orSelector = new OrSelector();
			orSelector.setProject(PROPERTY_REPLACER);
			for (String name : names) {
				// Workaround for bogus entries in the equinox executable
				// feature
				if ("${launcherName}.app/Contents/MacOS/${launcherName}".equals(name)) //$NON-NLS-1$
					name = "Eclipse.app/Contents/MacOS/launcher"; //$NON-NLS-1$

				FilenameSelector nameSelector = new FilenameSelector();
				nameSelector.setProject(PROPERTY_REPLACER);
				nameSelector.setName(name);
				orSelector.addFilename(nameSelector);
			}

			permissionKey = permissionKey.substring(Utils.ROOT_PERMISSIONS.length());
			for (File file : configAdvice.getFiles()) {
				IPath finalFilePath = configAdvice.computePath(file);
				if (orSelector.isSelected(null, finalFilePath.toOSString(), null))
					descriptor.addPermissions(new String[] { permissionKey, finalFilePath.toPortableString() });
			}
		}
	}

	private static String reorderConfig(String config) {
		String[] parsed = StringHelper.getArrayFromString(config, '.');
		return parsed[1] + '.' + parsed[0] + '.' + parsed[2];
	}

	private final Map<IVersionedId, CSpec> cspecs;

	private final Map<IVersionedId, Map<String, String>> properties = new HashMap<IVersionedId, Map<String, String>>();

	public FeaturesAction(File[] featureBinaries, Map<IVersionedId, CSpec> cspecs) {
		super(featureBinaries);
		this.cspecs = cspecs;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		for (Entry<IVersionedId, CSpec> entry : cspecs.entrySet()) {
			CSpec cspec = entry.getValue();
			try {
				IPath location = cspec.getComponentLocation();
				if (location.hasTrailingSeparator()) {
					File buildProps = location.append(IPDEBuildConstants.PROPERTIES_FILE).toFile();
					InputStream input = null;
					IVersionedId vn = entry.getKey();
					Properties props = new Properties();
					try {
						input = new BufferedInputStream(new FileInputStream(buildProps));
						props.load(input);
						properties.put(vn, new BMProperties(props));
						IPublisherAdvice rootAdvice = createRootAdvice(cspec.getName(), props, location, publisherInfo.getConfigurations());
						if (rootAdvice != null)
							publisherInfo.addAdvice(rootAdvice);
					} catch (FileNotFoundException e) {
						// OK, we don't have any roots
					} catch (IOException e) {
						throw BuckminsterException.wrap(e);
					} finally {
						IOUtils.close(input);
					}
				}
			} catch (CoreException e) {
				return e.getStatus();
			}
		}
		return super.perform(publisherInfo, results, monitor);
	}

	@Override
	protected void generateFeatureIUs(Feature[] featureList, IPublisherResult result) {
		for (Feature feature : featureList)
			addCapabilityAdvice(feature);
		super.generateFeatureIUs(featureList, result);

	}

	@Override
	protected ArrayList<IInstallableUnit> generateRootFileIUs(Feature feature, IPublisherResult result, IPublisherInfo publisherInfo) {
		ArrayList<IInstallableUnit> ius = new ArrayList<IInstallableUnit>();

		Collection<FeatureRootAdvice> collection = publisherInfo.getAdvice(null, false, feature.getId(), Version.parseVersion(feature.getVersion()),
				FeatureRootAdvice.class);
		if (collection.isEmpty())
			return ius;

		for (FeatureRootAdvice advice : collection) {
			String[] configs = advice.getConfigs();
			for (int i = 0; i < configs.length; i++) {
				String config = configs[i];
				ConfigAdvice configAdvice = advice.getConfigAdvice(config);
				if (configAdvice == null)
					continue;

				File[] files = configAdvice.getFiles();
				if (files.length == 0)
					continue;

				IInstallableUnit iu = createFeatureRootFileIU(feature.getId(), feature.getVersion(), null, configAdvice.getDescriptor());
				Collection<IArtifactKey> keys = iu.getArtifacts();
				if (keys.isEmpty())
					continue;

				IArtifactKey artifactKey = keys.iterator().next();
				ArtifactDescriptor artifactDescriptor = new ArtifactDescriptor(artifactKey);
				publishArtifact(artifactDescriptor, files, null, publisherInfo, configAdvice);
				result.addIU(iu, IPublisherResult.NON_ROOT);
				ius.add(iu);
			}
		}
		return ius;
	}

	private void addCapabilityAdvice(Feature feature) {
		IVersionedId vn = new VersionedId(feature.getId(), feature.getVersion());
		Map<String, String> localProps = properties.get(vn);
		Map<String, ? extends Object> props = AbstractActor.getActiveContext().getProperties();
		if (localProps != null)
			props = new MapUnion<String, Object>(localProps, props);

		if (!VersionConsolidator.getBooleanProperty(props, IPDEConstants.PROP_PDE_FEATURE_RANGE_GENERATION, true))
			// Generation is turned off
			return;

		boolean retainLowerBound = false;
		int pdeMatchRule = IMatchRules.EQUIVALENT;

		if (props != null) {
			String dfltMatchRule = (String) props.get(IPDEConstants.PROP_PDE_MATCH_RULE_DEFAULT);
			retainLowerBound = VersionConsolidator.getBooleanProperty(props, IPDEConstants.PROP_PDE_MATCH_RULE_RETAIN_LOWER, retainLowerBound);
			pdeMatchRule = CSpecGenerator.getMatchRule(dfltMatchRule);
		}
		if (pdeMatchRule == IMatchRules.NONE || pdeMatchRule == IMatchRules.PERFECT)
			return;

		boolean requirementGreedy = VersionConsolidator.getBooleanProperty(props, IPDEConstants.PROP_PDE_FEATURE_REQUIREMENTS_GREEDY, true);
		CapabilityAdvice advice = new CapabilityAdvice(vn.getId() + IPDEConstants.FEATURE_GROUP, vn.getVersion());
		FeatureEntry[] entries = feature.getEntries();
		int idx = entries.length;
		while (--idx >= 0) {
			FeatureEntry entry = entries[idx];
			if (entry.isPatch())
				continue;

			String id = entry.getId();
			if (!entry.isPlugin())
				id = id + IPDEConstants.FEATURE_GROUP;
			Version version = Version.create(entry.getVersion());
			if (version == null || version.equals(Version.emptyVersion))
				version = null;

			int min = entry.isOptional() ? 0 : 1;
			if (entry.isRequires()) {
				// Stick to the rule specified in the requirement
				if (requirementGreedy)
					continue;

				// Advice to replace with a non greedy requirement
				VersionRange range = CSpecGenerator.createRuleBasedRange(CSpecGenerator.getMatchRule(entry.getMatch()), true, version);
				advice.addRequirement(MetadataFactory.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, id, range, getFilter(entry), min, 1, false));
			}

			if (version == null || CSpecGenerator.getMatchRule(entry.getMatch()) != IMatchRules.NONE)
				continue;

			VersionRange range = CSpecGenerator.createRuleBasedRange(pdeMatchRule, retainLowerBound, version);
			advice.addRequirement(MetadataFactory.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, id, range, getFilter(entry), min, 1, true));
		}
		if (!advice.isEmpty())
			info.addAdvice(advice);
	}

	private void expandFilter(String filter, String osgiFilterValue, StringBuilder result) {
		String[] filters = StringHelper.getArrayFromString(filter, ',');
		if (filters.length > 1)
			result.append("(|"); //$NON-NLS-1$
		for (int idx = 0; idx < filters.length; ++idx) {
			result.append('(');
			result.append(osgiFilterValue);
			result.append('=');
			result.append(filters[0]);
			result.append(')');
		}
		if (filters.length > 1)
			result.append(')');
	}

	private IMatchExpression<IInstallableUnit> getFilter(FeatureEntry entry) {
		StringBuilder result = new StringBuilder();
		result.append("(&"); //$NON-NLS-1$
		if (entry.getFilter() != null)
			result.append(entry.getFilter());
		expandFilter(entry.getOS(), "osgi.os", result); //$NON-NLS-1$
		expandFilter(entry.getWS(), "osgi.ws", result); //$NON-NLS-1$
		expandFilter(entry.getArch(), "osgi.arch", result);//$NON-NLS-1$
		expandFilter(entry.getNL(), "osgi.nl", result); //$NON-NLS-1$
		if (result.length() == 2)
			return null;
		result.append(')');
		return InstallableUnit.parseFilter(result.toString());
	}
}
