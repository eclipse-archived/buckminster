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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.buckminster.ant.AntPlugin;
import org.eclipse.buckminster.ant.actor.AntActor;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.jdt.internal.ClasspathReader;
import org.eclipse.buckminster.pde.internal.actor.PdeAntActor;
import org.eclipse.buckminster.pde.internal.actor.PluginScriptGenerator;
import org.eclipse.buckminster.pde.internal.model.ExternalBuildModel;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.pde.internal.model.ExternalExtensionsModel;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.build.IBuild;
import org.eclipse.pde.core.build.IBuildEntry;
import org.eclipse.pde.core.build.IBuildModel;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginImport;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.ISharedPluginModel;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundleModel;
import org.eclipse.pde.internal.core.bundle.BundlePlugin;
import org.eclipse.pde.internal.core.bundle.BundlePluginBase;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModelBase;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

/**
 * A CSpec builder that creates a cspec using the META-INF/MANIFEST.MF, plugin.xml and fragment.xml
 * files.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PluginBuilder extends PDEBuilder implements IBuildPropertiesConstants
{
	private static final String ATTRIBUTE_BUNDLE_EXTRAJARS = "bundle.extrajars";

	private static final String ATTRIBUTE_ECLIPSE_BUILD = WellknownActions.ECLIPSE.BUILD.toString();

	private static final String ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS = ATTRIBUTE_ECLIPSE_BUILD + ".requirements";

	private static final String ATTRIBUTE_ECLIPSE_BUILD_SOURCE = ATTRIBUTE_ECLIPSE_BUILD + ".source";

	private static final String ATTRIBUTE_ECLIPSE_CLEAN = WellknownActions.ECLIPSE.CLEAN.toString();

	private static final String BUILD_SCRIPT_NAME = "build-plugin.xml";

	private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";

	private static final String PREFIX_ECLIPSE_BUILD_OUTPUT = "eclipse.build.output.";

	private static final String PROPERTY_MANIFEST = "buckminster.jar.manifest";

	private static final String SYSTEM_BUNDLE = "org.eclipse.osgi";

	@SuppressWarnings("serial")
	public static IPluginModelBase parsePluginModelBase(ICatalogReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		if(reader instanceof EclipsePlatformReader)
		{
			MonitorUtils.complete(monitor);
			return ((EclipsePlatformReader)reader).getPluginModelBase();
		}

		monitor.beginTask(null, 7000);
		try
		{
			IPluginModelBase modelBase = null;

			if(reader.exists(BUNDLE_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				// This is an OSGi style plugin. Most of the dependencies and
				// other
				// info that we're interested in is stored in the
				// META-INF/MANIFEST.MF
				// file.
				//
				BundleModel model = new ExternalBundleModel();
				loadModel(reader, BUNDLE_FILE, model, MonitorUtils.subMonitor(monitor, 1000));
				final boolean fragment = model.isFragmentModel();
				IBundlePluginModelBase bmodel = fragment ? new BundleFragmentModel()
					: new BundlePluginModel();

				bmodel.setEnabled(true);
				bmodel.setBundleModel(model);

				// Extensions etc. that are not part of the OSGi can still be
				// found in the plugin.xml or fragment.xml
				//
				String extensionsFile = fragment ? FRAGMENT_FILE : PLUGIN_FILE;
				if(reader.exists(extensionsFile, MonitorUtils.subMonitor(monitor, 1000)))
				{
					ExternalExtensionsModel extModel = new ExternalExtensionsModel();
					loadModel(reader, extensionsFile, extModel, MonitorUtils.subMonitor(monitor, 1000));
					bmodel.setExtensionsModel(extModel);
				}
				else
					MonitorUtils.worked(monitor, 1000);
				modelBase = bmodel;
			}
			else if(reader.exists(PLUGIN_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				modelBase = new ExternalPluginModel();
				loadModel(reader, PLUGIN_FILE, modelBase, MonitorUtils.subMonitor(monitor, 2000));
			}
			else if(reader.exists(FRAGMENT_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				modelBase = new ExternalFragmentModel();
				loadModel(reader, FRAGMENT_FILE, modelBase, MonitorUtils.subMonitor(monitor, 1000));
			}

			if(modelBase == null)
				throw new BuckminsterException("Could not find " + BUNDLE_FILE + ", " + PLUGIN_FILE + " or "
					+ FRAGMENT_FILE);

			if(reader.exists(BUILD_PROPERTIES_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				IBuildModel buildModel = new ExternalBuildModel();
				loadModel(reader, BUILD_PROPERTIES_FILE, buildModel, MonitorUtils.subMonitor(monitor, 1000));
				if(modelBase instanceof BundlePluginModelBase)
					((BundlePluginModelBase)modelBase).setBuildModel(buildModel);
			}
			else
				MonitorUtils.worked(monitor, 1000);
			return modelBase;
		}
		finally
		{
			monitor.done();
		}
	}

	private static IPath asProjectRelativeFolder(IPath classpathEntryPath)
	{
		return classpathEntryPath.removeFirstSegments(1).addTrailingSeparator();
	}

	private static String getArtifactName(IPath buildOutput)
	{
		return PREFIX_ECLIPSE_BUILD_OUTPUT + pathToName(buildOutput);
	}

	private static IPath getDefaultOutputLocation(IClasspathEntry[] classPath)
	{
		for(IClasspathEntry cpe : classPath)
		{
			if(cpe.getContentKind() == ClasspathEntry.K_OUTPUT)
				return cpe.getPath();
		}
		return null;
	}

	private static boolean guessUnpack(IBuildModel buildModel)
	{
		IBuild build = buildModel.getBuild();
		IBuildEntry jarsCompileOrder = build.getEntry(PROPERTY_JAR_ORDER);
		if(jarsCompileOrder == null)
			return false;

		for(String token : jarsCompileOrder.getTokens())
			if(!".".equals(token))
				return true;

		return false;
	}

	private static boolean isEnvironmentCompatible(IPluginModelBase modelBase) throws CoreException
	{
		BundleDescription bundleDesc = modelBase.getBundleDescription();
		if(bundleDesc == null)
			//
			// Assume that this is compatible
			//
			return true;

		String platformFilter = bundleDesc.getPlatformFilter();
		if(platformFilter == null)
			//
			// No specific requirements. Should work then.
			//
			return true;

		try
		{
			return FrameworkUtil.createFilter(platformFilter).match(TargetPlatformHelper.getTargetEnvironment());
		}
		catch(InvalidSyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private static void loadModel(ICatalogReader reader, String file, final IModel model,
		IProgressMonitor monitor) throws CoreException
	{
		try
		{
			reader.readFile(file, new IStreamConsumer<Object>()
			{
				public Object consumeStream(IComponentReader fileReader, String streamName,
					InputStream stream, IProgressMonitor mon) throws CoreException
				{
					model.load(stream, true);
					return null;
				}
			}, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private static String pathToName(IPath path)
	{
		return path.removeTrailingSeparator().toPortableString().replace('/', '.');
	}

	@Override
	public String getCategory()
	{
		return KeyConstants.PLUGIN_CATEGORY;
	}

	@Override
	void parseFile(ICatalogReader reader, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			IPluginBase pluginBase = parsePluginModelBase(reader, MonitorUtils.subMonitor(monitor, 50)).getPluginBase();
			CSpecBuilder cspec = getCSpec();
			cspec.setName(pluginBase.getId());
			cspec.setCategory(KeyConstants.PLUGIN_CATEGORY);
			cspec.setVersion(sanitizeVersion(pluginBase.getVersion()), VersionFactory.OSGiType.getId());
			convertModel(reader, pluginBase, MonitorUtils.subMonitor(monitor, 50));
		}
		finally
		{
			monitor.done();
		}
	}

	private void addExternalPrerequisite(GroupBuilder group, String component, String name, boolean optional)
	throws CoreException
	{
		PrerequisiteBuilder pqBld = group.createPrerequisiteBuilder();
		pqBld.setComponent(component);
		pqBld.setName(name);
		pqBld.setOptional(optional);
		group.addPrerequisite(pqBld);
	}

	private void addImports(IComponentReader reader, IPluginBase plugin, boolean isProject) throws CoreException
	{
		IPluginImport[] imports = plugin.getImports();
		boolean isFragment = plugin.getPluginModel().isFragmentModel();

		ComponentQuery query = reader.getNodeQuery().getComponentQuery();
		CSpecBuilder cspec = getCSpec();

		GroupBuilder fullClean = cspec.getRequiredGroup(ATTRIBUTE_FULL_CLEAN);
		GroupBuilder reExports = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);
		GroupBuilder closure = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_CLOSURE);

		if(!isFragment && (reader instanceof ISiteReader))
		{
			// Add known fragments from the target platform as dependencies.
			//
			List<IFragmentModel> frags = ((ISiteReader)reader).getFragmentsFor(cspec.getName());
			for(IFragmentModel frag : frags)
			{
				if(!isEnvironmentCompatible(frag))
					continue;

				ComponentRequest fragDep = createComponentRequest(frag);
				if(query.skipComponent(fragDep) || !addDependency(fragDep))
					continue;

				String component = fragDep.getName();
				addExternalPrerequisite(fullClean, component, ATTRIBUTE_FULL_CLEAN, false);
				if(isProject)
					addExternalPrerequisite(getAttributeBuildRequirements(), component, ATTRIBUTE_JAVA_BINARIES, false);
				addExternalPrerequisite(reExports, component, ATTRIBUTE_JAVA_BINARIES, false);
				addExternalPrerequisite(closure, component, ATTRIBUTE_BUNDLE_CLOSURE, false);
			}
		}

		if(imports == null || imports.length == 0)
		{
			// Just add the mandatory system bundle. It's needed since
			// that bundle defines the execution environments.
			//
			if(!(isFragment || SYSTEM_BUNDLE.equals(cspec.getName())))
			{
				cspec.addDependency(new ComponentRequest(SYSTEM_BUNDLE, KeyConstants.PLUGIN_CATEGORY, null));
				addExternalPrerequisite(closure, SYSTEM_BUNDLE, ATTRIBUTE_BUNDLE_CLOSURE, false);
			}
			return;
		}

		for(IPluginImport pluginImport : imports)
		{
			boolean optional = pluginImport.isOptional();
			if(optional && isUsingInstalledReader())
				//
				// We don't care about expressing dependencies to
				// optional plugins when we peruse the runtime
				// environment.
				//
				continue;

			String pluginId = pluginImport.getId();
			if(pluginId.equals("system.bundle"))
				continue;

			ComponentRequest dependency = createComponentRequest(pluginImport, KeyConstants.PLUGIN_CATEGORY);
			if(query.skipComponent(dependency) || !addDependency(dependency))
				continue;

			String component = dependency.getName();
			addExternalPrerequisite(fullClean, component, ATTRIBUTE_FULL_CLEAN, optional);
			addExternalPrerequisite(closure,   component, ATTRIBUTE_BUNDLE_CLOSURE, optional);
			if(isProject)
				addExternalPrerequisite(getAttributeBuildRequirements(), component, ATTRIBUTE_JAVA_BINARIES, optional);
			if(pluginImport.isReexported())
				addExternalPrerequisite(reExports, component, ATTRIBUTE_JAVA_BINARIES, optional);
		}
	}

	private void convertModel(ICatalogReader reader, IPluginBase plugin, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			createPublicAttributes();

			IBuildModel buildModel = plugin.getPluginModel().getBuildModel();
			boolean fromProject = (buildModel != null);

			addImports(reader, plugin, fromProject);
			MonitorUtils.worked(monitor, 10);

			if(fromProject)
			{
				createProjectBuildAttributes(reader, plugin, buildModel, MonitorUtils.subMonitor(monitor, 90));
				createScriptExecutionAttributes(buildModel);
			}
			else
				createBinaryBuildAttributes(reader, plugin, MonitorUtils.subMonitor(monitor, 90));
		}
		finally
		{
			monitor.done();
		}
	}

	/**
	 * Creates the attributes needed for a prebuilt bundle. The target bundle can
	 * be represented as a folder or a jar file.
	 * @param reader
	 * @param plugin
	 * @param monitor
	 * @throws CoreException
	 */
	private void createBinaryBuildAttributes(ICatalogReader reader, IPluginBase plugin, IProgressMonitor monitor)
	throws CoreException
	{
		// There are two types of binaries. The one that contain jar files (and must be unpacked
		// in order to function) and the one that is a jar file in itself.
		//
		CSpecBuilder cspec = getCSpec();
		GroupBuilder generic = cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_RUNTIME);
		GroupBuilder classpath = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);

		IPath parentDir = new Path("..");
		ISharedPluginModel model = plugin.getModel();
		String location = model.getInstallLocation();
		boolean isFile = (location != null && new File(location).isFile());

		if(isFile)
		{
			// No unpacked bundle exists (or should ever exist). We're happy with what we have.
			//
			ArtifactBuilder pluginExport = cspec.addArtifact(ATTRIBUTE_BUNDLE_EXPORT, true,
				ATTRIBUTE_BUNDLE_EXPORT, null);
			pluginExport.addPath(new Path(buildArtifactName(plugin.getId(), plugin.getVersion(), true)));
			pluginExport.setBase(parentDir);
			generic.addLocalPrerequisite(pluginExport);
			classpath.addLocalPrerequisite(pluginExport);
		}
		else
		{
			// This bundle is a folder. Gather artifacts to be included in the classpath
			//
			String bundleClassPath = null;
			if(plugin instanceof BundlePluginBase)
				bundleClassPath = ((BundlePluginBase)plugin).getBundle().getHeader(Constants.BUNDLE_CLASSPATH);

			if(bundleClassPath == null)
				classpath.addSelfRequirement();
			else
			{
				// Create an artifact that contains all entries listed in the classpath
				//
				ArtifactBuilder bundleClasspath = cspec.addArtifact(ATTRIBUTE_BUNDLE_CLASSPATH, false,
					ATTRIBUTE_JAVA_BINARIES, null);
				StringTokenizer tokens = new StringTokenizer(bundleClassPath, ",");
				while(tokens.hasMoreTokens())
				{
					String token = tokens.nextToken().trim();
					bundleClasspath.addPath(new Path(token));
				}

				classpath.addLocalPrerequisite(bundleClasspath);
			}

			// We need two types of exports. One that reflects the bundle jar file (suitable
			// for an update site) and one that we can use when creating a product, i.e. not
			// jared. We already have the latter.
			//
			ArtifactBuilder bundleFolder = cspec.addArtifact(ATTRIBUTE_BUNDLE_FOLDER, true, null, parentDir);
			IPath bundlePath = new Path(buildArtifactName(plugin.getId(), plugin.getVersion(), false));
			bundleFolder.addPath(bundlePath);

			// The generic version (the one used at runtime) is the unpacked one
			//
			generic.addLocalPrerequisite(bundleFolder);

			// Create the packed one (need a special temporary directory for this)
			//
			createBundleExportAction(plugin, bundleFolder, parentDir.append(bundlePath));
		}
	}

	/**
	 * Creates an action that will create the exported (the jar) of a plugin
	 * in folder format
	 *
	 * @param plugin
	 * @param unpackedPlugin
	 * @param unpackedPluginPath
	 * @throws CoreException
	 */
	private void createBundleExportAction(IPluginBase plugin, AttributeBuilder unpackedPlugin,
		IPath unpackedPluginPath) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();

		// In order to create a jar of the unpackedPlugin, we need a temporary directory
		// since this artifact is not a workspace artifact
		//
		String jarName = buildArtifactName(plugin.getId(), plugin.getVersion(), true);
		ActionBuilder bundleExport = cspec.addAction(ATTRIBUTE_BUNDLE_EXPORT, true, AntActor.ID, false);

		bundleExport.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, TASK_CREATE_JAR_WM, false);
		bundleExport.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);

		bundleExport.addProperty(PROPERTY_MANIFEST, MANIFEST_PATH, false);

		bundleExport.setProductAlias(ALIAS_OUTPUT);

		// We cannot use OUTPUT_DIR here since it might be relative to the component root
		// which in this case would point into the installation of the target platform
		//
		bundleExport.setProductBase(TEMP_DIR);
		bundleExport.addProductPath(new Path(jarName));

		bundleExport.setPrerequisitesAlias(ALIAS_REQUIREMENTS);
		bundleExport.setPrerequisitesRebase(new Path("."));
		bundleExport.addLocalPrerequisite(unpackedPlugin);
	}

	private void createProjectBuildAttributes(ICatalogReader reader, IPluginBase plugin, IBuildModel buildModel, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 100);
		IClasspathEntry[] classPath;
		try
		{
			classPath = ClasspathReader.getClasspath(reader, MonitorUtils.subMonitor(monitor, 50));
		}
		catch(CoreException e)
		{
			classPath = null;
		}

		if(classPath == null)
			//
			// Not much of a build unless we have a classpath.
			//
			return;
		
		CSpecBuilder cspec = this.getCSpec();

		GroupBuilder fullClean = cspec.getRequiredGroup(ATTRIBUTE_FULL_CLEAN);
		fullClean.addLocalPrerequisite(generateRemoveDirAction("build", OUTPUT_DIR, false));
		fullClean.addLocalPrerequisite(getAttributeEclipseClean());

		// Exported entries in the classpath must be added to the
		// java.binaries export
		//
		ActionBuilder eclipseBuild = getAttributeEclipseBuild();

		IPath defaultOutputLocation = null;
		for(IClasspathEntry cpe : classPath)
		{
			if(cpe.getEntryKind() != IClasspathEntry.CPE_SOURCE)
				continue;

			// The output declared in the source entry is a product of the
			// Eclipse build. If there's no output declared, we use the
			// default.
			//
			IPath output = cpe.getOutputLocation();
			if(output == null)
			{
				if(defaultOutputLocation == null)
					defaultOutputLocation = getDefaultOutputLocation(classPath);
				output = defaultOutputLocation;
			}

			if(output != null)
			{
				// Several source may contribute to the same output directory. Make
				// sure we only add it once.
				//
				output = asProjectRelativeFolder(output);
				String outputName = getArtifactName(output);
				if(eclipseBuild.getProductArtifact(outputName) == null)
					eclipseBuild.addProductArtifact(outputName, false, WellKnownExports.JAVA_BINARIES, output);
			}
			getAttributeEclipseBuildSource(true).addPath(asProjectRelativeFolder(cpe.getPath()));
		}

		// The classpath already contains all the re-exported stuff (it was
		// added when the imports were added). Only thing missing is the
		// output from the Eclipse build and the optional extra jar files.
		//
		GroupBuilder classpath = cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES);
		classpath.addLocalPrerequisite(eclipseBuild);

		IBuild build = buildModel.getBuild();
		ArrayList<String> jarsToCompile = null;
		for(IBuildEntry entry : build.getBuildEntries())
		{
			String name = entry.getName();
			if(name.startsWith("source.") && name.endsWith(".jar") && name.length() > 11)
			{
				if(jarsToCompile == null)
					jarsToCompile = new ArrayList<String>();
				jarsToCompile.add(name.substring(7));
			}
		}

		String bundleClassPath = null;
		if(plugin instanceof BundlePlugin)
		{
			IBundle bundle = ((BundlePlugin)plugin).getBundle();
			bundleClassPath = bundle.getHeader(Constants.BUNDLE_CLASSPATH);
			if(bundleClassPath != null)
			{
				StringTokenizer tokens = new StringTokenizer(bundleClassPath, ",");
				while(tokens.hasMoreTokens())
				{
					String token = tokens.nextToken().trim();
					IPath path = new Path(token);
					if(jarsToCompile != null && jarsToCompile.contains(token))
						//
						// Assume that this jar is produced by the eclipse build
						// and thus covered by inclusion from the project classpath
						// above.
						//
						continue;

					getAttributeExtraJars().addPath(path);
				}
			}
		}
	}

	private void createPublicAttributes() throws CoreException
	{
		CSpecBuilder cspec = getCSpec();

		cspec.addGroup(ATTRIBUTE_BUNDLE_RUNTIME, true);
		cspec.addGroup(ATTRIBUTE_JAVA_BINARIES, true);
		cspec.addGroup(ATTRIBUTE_FULL_CLEAN, true);
		GroupBuilder closure = cspec.addGroup(ATTRIBUTE_BUNDLE_CLOSURE, true);
		closure.addSelfRequirement();
	}

	private void createScriptExecutionAttributes(IBuildModel buildModel) throws CoreException
	{
		CSpecBuilder cspec = getCSpec();
		ActionBuilder scriptBuilder = cspec.addAction(
			PluginScriptGenerator.ATTRIBUTE_GENERATED_PLUGIN_SCRIPT, false,
			PluginScriptGenerator.ACTOR_ID, false);
		scriptBuilder.setProductBase(TEMP_DIR);
		scriptBuilder.addProductPath(new Path(BUILD_SCRIPT_NAME));
		scriptBuilder.addLocalPrerequisite(ATTRIBUTE_BUNDLE_CLOSURE);

		ActionBuilder bundleExport = cspec.addAction(ATTRIBUTE_BUNDLE_EXPORT, true, PdeAntActor.ACTOR_ID,
			false);
		bundleExport.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, "build.update.jar", false);
		bundleExport.setProductBase(OUTPUT_DIR.append("export").addTrailingSeparator());
		bundleExport.addLocalPrerequisite(scriptBuilder.getName(), PdeAntActor.ALIAS_GENERATED_SCRIPT);

		ActionBuilder bundleFolder = cspec.addAction(ATTRIBUTE_BUNDLE_FOLDER, true, AntActor.ID, false);
		bundleFolder.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_TARGETS, TASK_UNJAR_NAMED, false);
		bundleFolder.addActorProperty(AntPlugin.ANT_ACTOR_PROPERTY_BUILD_FILE_ID, BUILD_FILE_ID, false);
		bundleFolder.setProductBase(OUTPUT_DIR.append("folder"));
		bundleFolder.setProductAlias(ALIAS_OUTPUT);
		bundleFolder.addLocalPrerequisite(bundleExport);
		bundleFolder.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

		cspec.getRequiredGroup(ATTRIBUTE_BUNDLE_RUNTIME).addLocalPrerequisite(guessUnpack(buildModel) ? bundleFolder : bundleExport);
	}

	private GroupBuilder getAttributeBuildRequirements() throws CoreException
	{
		CSpecBuilder cspec = this.getCSpec();
		GroupBuilder eclipseBuildReqs = cspec.getGroup(ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS);
		if(eclipseBuildReqs == null)
		{
			eclipseBuildReqs = cspec.addGroup(ATTRIBUTE_ECLIPSE_BUILD_REQUIREMENTS, false);
			getAttributeEclipseBuild().addLocalPrerequisite(eclipseBuildReqs);
		}
		return eclipseBuildReqs;
	}

	private ActionBuilder getAttributeEclipseBuild() throws CoreException
	{
		CSpecBuilder cspec = this.getCSpec();
		ActionBuilder eclipseBuild = cspec.getAction(ATTRIBUTE_ECLIPSE_BUILD);
		if(eclipseBuild == null)
			eclipseBuild = cspec.addInternalAction(ATTRIBUTE_ECLIPSE_BUILD, false);
		return eclipseBuild;
	}

	private ArtifactBuilder getAttributeEclipseBuildSource(boolean createIfMissing) throws CoreException
	{
		CSpecBuilder cspec = this.getCSpec();
		ArtifactBuilder buildSource = cspec.getArtifact(ATTRIBUTE_ECLIPSE_BUILD_SOURCE);
		if(buildSource == null && createIfMissing)
		{
			buildSource = cspec.addArtifact(ATTRIBUTE_ECLIPSE_BUILD_SOURCE, false,
					WellKnownExports.JAVA_SOURCES, null);
			getAttributeEclipseBuild().addLocalPrerequisite(buildSource);
		}
		return buildSource;
	}

	private ActionBuilder getAttributeEclipseClean() throws CoreException
	{
		CSpecBuilder cspec = this.getCSpec();
		ActionBuilder eclipseClean = cspec.getAction(ATTRIBUTE_ECLIPSE_CLEAN);
		if(eclipseClean == null)
			eclipseClean = cspec.addInternalAction(ATTRIBUTE_ECLIPSE_CLEAN, false);
		return eclipseClean;
	}

	private ArtifactBuilder getAttributeExtraJars() throws CoreException
	{
		CSpecBuilder cspec = this.getCSpec();
		ArtifactBuilder extraJars = cspec.getArtifact(ATTRIBUTE_BUNDLE_EXTRAJARS);
		if(extraJars == null)
		{
			extraJars = cspec.addArtifact(ATTRIBUTE_BUNDLE_EXTRAJARS, false, WellKnownExports.JAVA_BINARIES,
					null);
			cspec.getRequiredGroup(ATTRIBUTE_JAVA_BINARIES).addLocalPrerequisite(extraJars);
			getAttributeBuildRequirements().addLocalPrerequisite(extraJars);
		}
		return extraJars;
	}
}
