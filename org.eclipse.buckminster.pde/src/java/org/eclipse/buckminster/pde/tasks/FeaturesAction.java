package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.PatternSet.NameEntry;
import org.apache.tools.ant.types.selectors.FilenameSelector;
import org.apache.tools.ant.types.selectors.OrSelector;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.pde.internal.TypedCollections;
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
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.ArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.pde.internal.build.IPDEBuildConstants;
import org.eclipse.pde.internal.build.Utils;

@SuppressWarnings("restriction")
public class FeaturesAction extends org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction
{
	private static final Project PROPERTY_REPLACER = new Project();

	private static FeatureRootAdvice createRootAdvice(String featureId, Properties buildProperties, IPath baseDirectory)
	{
		Map<String, Map<String, String>> configMap = TypedCollections.processRootProperties(buildProperties, true);
		if(configMap.size() == 1)
		{
			Map<String, String> entry = configMap.get(Utils.ROOT_COMMON);
			if(entry != null && entry.isEmpty())
				return null;
		}

		FeatureRootAdvice advice = new FeatureRootAdvice(featureId);
		for(Map.Entry<String, Map<String, String>> entry : configMap.entrySet())
		{
			String config = entry.getKey();
			Map<String, String> rootMap = entry.getValue();
			populateConfigAdvice(advice, config, rootMap, baseDirectory);
		}
		return advice;
	}

	private static void populateConfigAdvice(FeatureRootAdvice advice, String config, Map<String, String> rootMap,
			IPath baseDirectory)
	{
		if(config.equals(Utils.ROOT_COMMON))
			config = ""; //$NON-NLS-1$
		else
			config = reorderConfig(config);

		ConfigAdvice configAdvice = advice.getConfigAdvice(config);
		FileSetDescriptor descriptor = configAdvice.getDescriptor();
		List<String> permissionsKeys = new ArrayList<String>();
		for(Map.Entry<String, String> rootEntry : rootMap.entrySet())
		{
			String key = rootEntry.getKey();
			if(key.equals(Utils.ROOT_LINK))
			{
				descriptor.setLinks(rootEntry.getValue());
				continue;
			}

			if(key.startsWith(Utils.ROOT_PERMISSIONS))
			{
				permissionsKeys.add(key);
				continue;
			}

			for(String rootName : StringHelper.getArrayFromString(rootEntry.getValue(), ','))
			{
				boolean isAbsolute = rootName.startsWith("absolute:"); //$NON-NLS-1$
				if(isAbsolute)
					rootName = rootName.substring(9);

				boolean isFile = rootName.startsWith("file:"); //$NON-NLS-1$
				if(isFile)
					rootName = rootName.substring(5);

				if(rootName.length() == 0)
					continue;

				IPath basePath;
				String pattern;
				if(isAbsolute)
				{
					// Base path cannot contain wild card characters
					//
					IPath rootPath = Path.fromPortableString(rootName);
					int firstStar = -1;
					int numSegs = rootPath.segmentCount();
					for(int idx = 0; idx < numSegs; ++idx)
						if(rootPath.segment(idx).indexOf('*') >= 0)
						{
							firstStar = idx;
							break;
						}

					if(firstStar == -1)
					{
						if(isFile)
						{
							pattern = rootPath.lastSegment();
							basePath = rootPath.removeLastSegments(1);
						}
						else
						{
							pattern = "**"; //$NON-NLS-1$
							basePath = rootPath;
						}
					}
					else
					{
						basePath = rootPath.removeLastSegments(rootPath.segmentCount() - (firstStar + 1));
						pattern = rootPath.removeFirstSegments(firstStar).toPortableString();
					}
				}
				else
				{
					basePath = baseDirectory;
					if(!isFile && rootName.indexOf('*') < 0)
						rootName = Path.fromPortableString(rootName).append("**").toPortableString(); //$NON-NLS-1$
					pattern = rootName;
				}

				FileSet fileset = new FileSet();
				fileset.setProject(PROPERTY_REPLACER);
				File base = basePath.toFile();
				fileset.setDir(base);
				NameEntry include = fileset.createInclude();
				include.setName(pattern);

				String[] files = fileset.getDirectoryScanner().getIncludedFiles();
				for(String found : files)
					configAdvice.addRootfile(new File(base, found), key);
			}
		}

		for(String permissionKey : permissionsKeys)
		{
			String permissionString = rootMap.get(permissionKey);
			String[] names = StringHelper.getArrayFromString(permissionString, ',');

			OrSelector orSelector = new OrSelector();
			orSelector.setProject(PROPERTY_REPLACER);
			for(String name : names)
			{
				// Workaround for bogus entries in the equinox executable feature
				if("${launcherName}.app/Contents/MacOS/${launcherName}".equals(name)) //$NON-NLS-1$
					name = "Eclipse.app/Contents/MacOS/launcher"; //$NON-NLS-1$

				FilenameSelector nameSelector = new FilenameSelector();
				nameSelector.setProject(PROPERTY_REPLACER);
				nameSelector.setName(name);
				orSelector.addFilename(nameSelector);
			}

			permissionKey = permissionKey.substring(Utils.ROOT_PERMISSIONS.length());
			for(File file : configAdvice.getFiles())
			{
				IPath finalFilePath = configAdvice.computePath(file);
				if(orSelector.isSelected(null, finalFilePath.toOSString(), null))
					descriptor.addPermissions(new String[] { permissionKey, finalFilePath.toPortableString() });
			}
		}
	}

	private static String reorderConfig(String config)
	{
		String[] parsed = StringHelper.getArrayFromString(config, '.');
		return parsed[1] + '.' + parsed[0] + '.' + parsed[2];
	}

	private final Map<VersionedName, CSpec> m_cspecs;

	public FeaturesAction(File[] featureBinaries, Map<VersionedName, CSpec> cspecs)
	{
		super(featureBinaries);
		m_cspecs = cspecs;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		for(CSpec cspec : m_cspecs.values())
		{
			try
			{
				IPath location = cspec.getComponentLocation();
				if(location.hasTrailingSeparator())
				{
					File buildProps = location.append(IPDEBuildConstants.PROPERTIES_FILE).toFile();
					InputStream input = null;
					Properties properties = new Properties();
					try
					{
						input = new BufferedInputStream(new FileInputStream(buildProps));
						properties.load(input);
						IPublisherAdvice rootAdvice = createRootAdvice(cspec.getName(), properties, location);
						if(rootAdvice != null)
							publisherInfo.addAdvice(rootAdvice);
					}
					catch(FileNotFoundException e)
					{
						// OK, we don't have any roots
					}
					catch(IOException e)
					{
						throw BuckminsterException.wrap(e);
					}
					finally
					{
						IOUtils.close(input);
					}
				}
			}
			catch(CoreException e)
			{
				return e.getStatus();
			}
		}
		return super.perform(publisherInfo, results, monitor);
	}

	@Override
	protected ArrayList<IInstallableUnit> generateRootFileIUs(Feature feature, IPublisherResult result,
			IPublisherInfo publisherInfo)
	{
		ArrayList<IInstallableUnit> ius = new ArrayList<IInstallableUnit>();

		Collection<FeatureRootAdvice> collection = TypedCollections.getAdvice(publisherInfo, null, false,
				feature.getId(), Version.parseVersion(feature.getVersion()), FeatureRootAdvice.class);
		if(collection.isEmpty())
			return ius;

		for(FeatureRootAdvice advice : collection)
		{
			String[] configs = advice.getConfigs();
			for(int i = 0; i < configs.length; i++)
			{
				String config = configs[i];
				ConfigAdvice configAdvice = advice.getConfigAdvice(config);
				if(configAdvice == null)
					continue;

				File[] files = configAdvice.getFiles();
				if(files.length == 0)
					continue;

				IInstallableUnit iu = createFeatureRootFileIU(feature.getId(), feature.getVersion(), null,
						configAdvice.getDescriptor());

				IArtifactKey artifactKey = iu.getArtifacts()[0];
				ArtifactDescriptor artifactDescriptor = new ArtifactDescriptor(artifactKey);
				publishArtifact(artifactDescriptor, files, null, publisherInfo, configAdvice);
				result.addIU(iu, IPublisherResult.NON_ROOT);
				ius.add(iu);
			}
		}
		return ius;
	}
}
