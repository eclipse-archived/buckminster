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
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.tasks.FeatureRootAdvice.ConfigAdvice;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.core.helpers.StringHelper;
import org.eclipse.equinox.internal.p2.publisher.FileSetDescriptor;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;
import org.eclipse.equinox.p2.publisher.eclipse.URLEntry;
import org.eclipse.equinox.p2.repository.artifact.spi.ArtifactDescriptor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.Utils;

@SuppressWarnings("restriction")
public class FeaturesAction extends org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction {
	private static final Project PROPERTY_REPLACER = new Project();

	public static int getMatchRule(String matchRuleString) {
		if (matchRuleString == null)
			return IMatchRules.NONE;

		String[] table = IMatchRules.RULE_NAME_TABLE;
		int idx = table.length;
		while (--idx >= 0)
			if (matchRuleString.equalsIgnoreCase(table[idx]))
				return idx;
		return IMatchRules.NONE;
	}

	public static Version limitWithMatchRule(Version v, int matchRule) {
		if (v == null || matchRule == IMatchRules.NONE || matchRule == IMatchRules.PERFECT)
			return v;

		org.osgi.framework.Version ov = new org.osgi.framework.Version(v.toString());
		switch (matchRule) {
			case IMatchRules.EQUIVALENT:
				v = Version.createOSGi(ov.getMajor(), ov.getMinor(), 0);
				break;
			case IMatchRules.COMPATIBLE:
				v = Version.createOSGi(ov.getMajor(), 0, 0);
				break;
			default:
				v = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro());
		}
		return v;
	}

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

	private final Map<IVersionedId, Properties> properties = new HashMap<IVersionedId, Properties>();

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
					properties.put(vn, props);
					try {
						input = new BufferedInputStream(new FileInputStream(buildProps));
						props.load(input);
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
	protected IInstallableUnit createGroupIU(Feature feature, List<IInstallableUnit> childIUs, IPublisherInfo publisherInfo) {
		IVersionedId vn = new VersionedId(feature.getId(), feature.getVersion());
		Properties props = properties.get(vn);
		boolean retainLowerBound = false;
		int pdeMatchRule = IMatchRules.EQUIVALENT;

		if (props != null) {
			String dfltMatchRule = props.getProperty(IPDEConstants.PROP_PDE_MATCH_RULE_DEFAULT);
			String rtl = props.getProperty(IPDEConstants.PROP_PDE_MATCH_RULE_RETAIN_LOWER);
			pdeMatchRule = getMatchRule(dfltMatchRule);
			if (rtl != null)
				retainLowerBound = Boolean.parseBoolean(rtl);
		}
		if (pdeMatchRule == IMatchRules.NONE || pdeMatchRule == IMatchRules.PERFECT)
			return super.createGroupIU(feature, childIUs, publisherInfo);

		Feature newFeature = new Feature(feature.getId(), feature.getVersion());
		final String canonicalMatchRule = IMatchRules.RULE_NAME_TABLE[pdeMatchRule];
		FeatureEntry[] entries = feature.getEntries();
		int idx = entries.length;
		while (--idx >= 0) {
			FeatureEntry entry = entries[idx];
			if (entry.isPatch() || entry.isRequires() || getMatchRule(entry.getMatch()) != IMatchRules.NONE) {
				newFeature.addEntry(entry);
				continue;
			}

			Version version = Version.create(entry.getVersion());
			if (!retainLowerBound)
				version = limitWithMatchRule(Version.create(entry.getVersion()), pdeMatchRule);

			String vstr = version == null ? null : version.toString();
			FeatureEntry newEntry = FeatureEntry.createRequires(entry.getId(), vstr, canonicalMatchRule, entry.getFilter(), entry.isPlugin());
			newEntry.setEnvironment(entry.getOS(), entry.getWS(), entry.getArch(), entry.getNL());
			newEntry.setFragment(entry.isFragment());
			newEntry.setOptional(entry.isOptional());
			newEntry.setUnpack(entry.isUnpack());
			newEntry.setURL(entry.getURL());
			newFeature.addEntry(newEntry);
		}
		for (URLEntry site : feature.getDiscoverySites())
			newFeature.addDiscoverySite(site.getAnnotation(), site.getURL());
		newFeature.setApplication(feature.getApplication());
		newFeature.setColocationAffinity(feature.getColocationAffinity());
		newFeature.setCopyright(feature.getCopyright());
		newFeature.setCopyrightURL(feature.getCopyrightURL());
		newFeature.setDescription(feature.getDescription());
		newFeature.setDescriptionURL(feature.getDescriptionURL());
		newFeature.setEnvironment(feature.getOS(), feature.getWS(), feature.getArch(), feature.getNL());
		newFeature.setExclusive(feature.isExclusive());
		newFeature.setImage(feature.getImage());
		newFeature.setInstallHandler(feature.getInstallHandler());
		newFeature.setInstallHandlerLibrary(feature.getInstallHandlerLibrary());
		newFeature.setInstallHandlerURL(feature.getInstallHandlerURL());
		newFeature.setLabel(feature.getLabel());
		newFeature.setLicense(feature.getLicense());
		newFeature.setLicenseURL(feature.getLicenseURL());
		newFeature.setLocalizations(feature.getLocalizations());
		newFeature.setLocation(feature.getLocation());
		newFeature.setPlugin(feature.getPlugin());
		newFeature.setPrimary(feature.isPrimary());
		newFeature.setProviderName(feature.getProviderName());
		URLEntry site = feature.getUpdateSite();
		if (site != null) {
			newFeature.setUpdateSiteLabel(site.getAnnotation());
			newFeature.setUpdateSiteURL(site.getURL());
		}
		return super.createGroupIU(newFeature, childIUs, publisherInfo);
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
}
