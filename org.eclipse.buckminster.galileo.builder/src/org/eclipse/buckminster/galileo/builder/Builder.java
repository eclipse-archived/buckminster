package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.tools.ant.Project;
import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.BuildPackage;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.m2m.internal.qvt.oml.common.launch.TargetUriData;
import org.eclipse.m2m.internal.qvt.oml.emf.util.ModelContent;
import org.eclipse.m2m.internal.qvt.oml.runtime.launch.QvtLaunchConfigurationDelegateBase;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.DeployedQvtModule;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtInterpretedTransformation;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation;

@SuppressWarnings("restriction")
public class Builder
{
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final Project PROPERTY_REPLACER = new Project();

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	private static final TimeZone UTC = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$

	static
	{
		PROPERTY_REPLACER.initProperties();
		DATE_FORMAT.setTimeZone(UTC);
		TIME_FORMAT.setTimeZone(UTC);
	}

	/**
	 * Creates a repository location without the trailing slash that will be added if the standard
	 * {@link java.io.File#toURI()} is used.
	 * 
	 * @param repoLocation
	 *            The location. Must be an absolute path.
	 * @return The created URI.
	 * @throws IllegalArgumentException
	 *             if the argument is not an absolute path
	 */
	public static final java.net.URI createURI(File repoLocation)
	{
		if(repoLocation != null)
		{
			IPath path = Path.fromOSString(repoLocation.getPath());
			if(path.isAbsolute())
				return java.net.URI.create("file:" + path.removeTrailingSeparator().toPortableString()); //$NON-NLS-1$
		}
		throw new IllegalArgumentException();
	}

	private final File buildModelLocation;

	private Build build;

	private File buildRoot;

	private File repoLocation;

	private File tempFolder;

	private java.net.URI categoriesRepo;

	private java.net.URI targetPlatformRepo;

	private Set<IInstallableUnit> unitsToInstall;

	public Builder(File buildModelLocation) throws CoreException
	{
		this.buildModelLocation = buildModelLocation;
	}

	public void reset()
	{
		if(tempFolder != null && tempFolder.exists())
			FileUtils.deleteAll(tempFolder);

		build = null;
		buildRoot = null;
		repoLocation = null;
		tempFolder = null;
		categoriesRepo = null;
		targetPlatformRepo = null;
		unitsToInstall = null;
	}

	public void runCompositeGenerator(IProgressMonitor monitor) throws CoreException
	{
		if(build == null)
			throw new IllegalStateException("Build model is not loaded"); //$NON-NLS-1$
		unitsToInstall = null;

		CompositeRepoGenerator repoGenerator = new CompositeRepoGenerator(tempFolder, build.getLabel() + " Composite"); //$NON-NLS-1$
		repoGenerator.run(build, monitor);
	}

	public void runExtraRepoGenerator(IProgressMonitor monitor) throws CoreException
	{
		if(build == null)
			throw new IllegalStateException("Build model is not loaded"); //$NON-NLS-1$
		unitsToInstall = null;

		CategoryRepoGenerator extraGenerator = new CategoryRepoGenerator(tempFolder, build.getLabel() + " Categories"); //$NON-NLS-1$
		categoriesRepo = extraGenerator.run(build, monitor);
	}

	public void runMirroring(IProgressMonitor monitor) throws CoreException
	{
		if(unitsToInstall == null)
			throw new IllegalStateException("Repository has not been verified"); //$NON-NLS-1$
		unitsToInstall = null;

		MirrorGenerator mirrorGenerator = new MirrorGenerator(createURI(tempFolder), categoriesRepo,
				targetPlatformRepo, repoLocation, build.getLabel());
		mirrorGenerator.run(unitsToInstall, monitor);
	}

	public void runPlatformRepoGenerator(IProgressMonitor monitor, File targetPlatformLocation) throws CoreException
	{
		if(build == null)
			throw new IllegalStateException("Build model is not loaded"); //$NON-NLS-1$

		PlatformRepoGenerator tpGenerator = new PlatformRepoGenerator(tempFolder, targetPlatformLocation);
		targetPlatformRepo = tpGenerator.run(monitor);
	}

	public void runRepositoryVerifier(IProgressMonitor monitor) throws CoreException
	{
		if(targetPlatformRepo == null)
			throw new IllegalStateException("Target Platform Repository has not been generated"); //$NON-NLS-1$

		RepositoryVerifier ipt = new RepositoryVerifier(createURI(tempFolder), targetPlatformRepo,
				"org.eclipse.galileo", null); //$NON-NLS-1$
		unitsToInstall = ipt.run(build.getConfigs(), monitor);
	}

	public void runTransformation() throws CoreException
	{
		reset();

		Logger log = Buckminster.getLogger();
		log.info("Starting build transformation. Source: %s", buildModelLocation); //$NON-NLS-1$
		long now = System.currentTimeMillis();

		File generatedBuildModel = null;
		try
		{
			// Transform the model, i.e. collect all contributions and create one single build model file
			Date today = new Date();
			Map<String, Object> configuration = new HashMap<String, Object>();
			configuration.put("date", DATE_FORMAT.format(today)); //$NON-NLS-1$
			configuration.put("time", TIME_FORMAT.format(today)); //$NON-NLS-1$
			QvtTransformation transf = new QvtInterpretedTransformation(new DeployedQvtModule('/' + Activator.PLUGIN_ID
					+ "/build.qvto")); //$NON-NLS-1$
			List<ModelContent> inObjects = Collections.singletonList(transf.loadInput(URI.createFileURI(buildModelLocation.getAbsolutePath())));
			generatedBuildModel = File.createTempFile("buildModel_", "qvto"); //$NON-NLS-1$//$NON-NLS-2$

			List<TargetUriData> targetData = Collections.singletonList(new TargetUriData(
					createURI(generatedBuildModel).toString()));
			QvtLaunchConfigurationDelegateBase.doLaunch(transf, inObjects, targetData, configuration, null);

			// Load the Java model into memory
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
					Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			BuildPackage.eINSTANCE.eClass();
			URI fileURI = URI.createFileURI(generatedBuildModel.getAbsolutePath());
			Resource resource = resourceSet.getResource(fileURI, true);
			EList<EObject> content = resource.getContents();
			if(content.size() != 1)
				throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d", //$NON-NLS-1$
						Integer.valueOf(content.size()));

			Build theBuild = (Build)content.get(0);

			File theBuildRoot = new File(PROPERTY_REPLACER.replaceProperties(theBuild.getBuildRoot()));
			File temp = new File(theBuildRoot, "tmp"); //$NON-NLS-1$
			if(temp.exists())
			{
				FileUtils.deleteAll(temp);
				if(temp.exists())
					throw BuckminsterException.fromMessage("Failed to delete folder %s", temp); //$NON-NLS-1$
			}
			temp.mkdirs();
			if(!temp.exists())
				throw BuckminsterException.fromMessage("Failed to create folder %s", temp); //$NON-NLS-1$

			tempFolder = temp;
			build = theBuild;
			buildRoot = theBuildRoot;
			repoLocation = new File(buildRoot, "repository"); //$NON-NLS-1$

		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			if(generatedBuildModel != null)
				generatedBuildModel.delete();
		}
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now)); //$NON-NLS-1$
	}
}
