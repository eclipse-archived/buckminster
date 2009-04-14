package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tools.ant.Project;
import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.BuildPackage;
import org.eclipse.amalgam.releng.build.Contact;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Promotion;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.NullOutputStream;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.m2m.internal.qvt.oml.common.launch.TargetUriData;
import org.eclipse.m2m.internal.qvt.oml.emf.util.ModelContent;
import org.eclipse.m2m.internal.qvt.oml.runtime.launch.QvtLaunchConfigurationDelegateBase;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.DeployedQvtModule;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtInterpretedTransformation;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;

@SuppressWarnings("restriction")
public class Builder implements IApplication
{
	public static final String NAMESPACE_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String PROFILE_ID = "GalileoTest";

	private static final String BUNDLE_ECF_FS_PROVIDER = "org.eclipse.ecf.provider.filetransfer"; //$NON-NLS-1$

	private static final String BUNDLE_EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	private static final String BUNDLE_UPDATESITE = "org.eclipse.equinox.p2.updatesite"; //$NON-NLS-1$

	private static final String CORE_BUNDLE = "org.eclipse.equinox.p2.core"; //$NON-NLS-1$

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final String PROP_P2_DATA_AREA = "eclipse.p2.data.area";

	private static final String PROP_P2_PROFILE = "eclipse.p2.profile";

	private static final Project PROPERTY_REPLACER = new Project();

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	private static final String TP_CONTRIBUTION_LABEL = "Eclipse"; //$NON-NLS-1$

	static
	{
		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		PROPERTY_REPLACER.initProperties();
		DATE_FORMAT.setTimeZone(utc);
		TIME_FORMAT.setTimeZone(utc);
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
	public static final URI createURI(File repoLocation)
	{
		if(repoLocation != null)
		{
			IPath path = Path.fromOSString(repoLocation.getPath());
			if(path.isAbsolute())
				return URI.create("file:" + path.removeTrailingSeparator().toPortableString()); //$NON-NLS-1$
		}
		throw new IllegalArgumentException();
	}

	public static String getExceptionMessages(Throwable e)
	{
		StringBuilder bld = new StringBuilder();
		getExceptionMessages(e, bld);
		return bld.toString();
	}

	private static InternetAddress contactToAddress(Contact contact) throws UnsupportedEncodingException
	{
		InternetAddress addr = new InternetAddress();
		addr.setPersonal(contact.getName());
		addr.setAddress(contact.getEmail());
		return addr;
	}

	private static synchronized Bundle getBundle(PackageAdmin packageAdmin, String symbolicName)
	{
		Bundle[] bundles = packageAdmin.getBundles(symbolicName, null);
		if(bundles == null)
			return null;

		// Return the first bundle that is not installed or uninstalled
		for(int i = 0; i < bundles.length; i++)
		{
			Bundle bundle = bundles[i];
			if((bundle.getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
				return bundle;
		}
		return null;
	}

	private static void getExceptionMessages(Throwable e, StringBuilder bld)
	{
		bld.append(e.getClass().getName());
		bld.append(": ");
		if(e.getMessage() != null)
			bld.append(e.getMessage());

		if(e instanceof CoreException)
			e = ((CoreException)e).getStatus().getException();
		else
		{
			Throwable t = e.getCause();
			e = (t == e)
					? null
					: t;
		}
		if(e != null)
		{
			bld.append("\nCaused by: ");
			getExceptionMessages(e, bld);
		}
	}

	private static boolean startEarly(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	private static boolean stopBundle(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null || bundle.getState() != Bundle.ACTIVE)
			return false;
		bundle.stop(Bundle.STOP_TRANSIENT);
		return true;
	}

	private Build build;

	private String buildID;

	private File buildModelLocation;

	private File buildRoot;

	private URI categoriesRepo;

	private PrintStream logOutput;

	private String mockEmailCC;

	private String mockEmailTo;

	private boolean production;

	private URI targetPlatformRepo;

	private File tempFolder;

	private Set<IInstallableUnit> unitsToInstall;

	private boolean update;

	private boolean verifyOnly;

	public Build getBuild()
	{
		return build;
	}

	public String getBuildID()
	{
		return buildID;
	}

	public File getBuildModelLocation()
	{
		return buildModelLocation;
	}

	public URI getCategoriesRepo()
	{
		return categoriesRepo;
	}

	public URI getGlobalRepoURI()
	{
		return createURI(tempFolder);
	}

	public URI getTargetPlatformRepo()
	{
		return targetPlatformRepo;
	}

	public File getTempFolder()
	{
		return tempFolder;
	}

	public Set<IInstallableUnit> getUnitsToInstall()
	{
		return unitsToInstall;
	}

	public boolean isProduction()
	{
		return production;
	}

	public boolean isUpdate()
	{
		return update;
	}

	public boolean isVerifyOnly()
	{
		return verifyOnly;
	}

	/**
	 * Run the build
	 * 
	 * @param monitor
	 */
	public void run(IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.begin(monitor, verifyOnly
				? 100
				: 1100);

		if(buildModelLocation == null)
			throw BuckminsterException.fromMessage("No buildmodel has been set");

		if(buildID == null)
		{
			Date now = new Date();
			buildID = "build-" + DATE_FORMAT.format(now) + TIME_FORMAT.format(now);
		}

		runTransformation();
		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
		try
		{
			stopBundle(packageAdmin, BUNDLE_EXEMPLARY_SETUP);
			stopBundle(packageAdmin, CORE_BUNDLE);

			System.setProperty(PROP_P2_DATA_AREA, new File(buildRoot, "p2").toString());
			System.setProperty(PROP_P2_PROFILE, PROFILE_ID);

			if(!startEarly(packageAdmin, BUNDLE_ECF_FS_PROVIDER))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_ECF_FS_PROVIDER);
			if(!startEarly(packageAdmin, CORE_BUNDLE))
				throw BuckminsterException.fromMessage("Missing bundle %s", CORE_BUNDLE);
			if(!startEarly(packageAdmin, BUNDLE_EXEMPLARY_SETUP))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_EXEMPLARY_SETUP);
			if(!startEarly(packageAdmin, BUNDLE_UPDATESITE))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_UPDATESITE);
		}
		catch(BundleException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			bucky.ungetService(packageAdmin);
		}

		try
		{
			runCompositeGenerator(MonitorUtils.subMonitor(monitor, 70));
			runCategoriesRepoGenerator(MonitorUtils.subMonitor(monitor, 10));
			runPlatformRepoGenerator();
			runRepositoryVerifier(MonitorUtils.subMonitor(monitor, 20));
			if(!verifyOnly)
				runMirroring(MonitorUtils.subMonitor(monitor, 1000));
		}
		finally
		{
			monitor.done();
		}
	}

	public void sendEmail(Contribution contrib, List<String> errors)
	{
		boolean useMock = (mockEmailTo != null);
		if(!(production || useMock) && build.isSendmail())
			return;

		Logger log = Buckminster.getLogger();
		try
		{
			List<InternetAddress> toList = new ArrayList<InternetAddress>();
			for(Contact contact : contrib.getContacts())
				toList.add(contactToAddress(contact));

			StringBuilder msgBld = new StringBuilder();
			msgBld.append("The following error");
			if(errors.size() > 1)
				msgBld.append('s');
			msgBld.append(" occured when building ");
			msgBld.append(build.getLabel());
			msgBld.append(":\n\n");
			for(String error : errors)
			{
				msgBld.append(error);
				msgBld.append("\n\n");
			}
			msgBld.append("Check the log file for more information: ");
			msgBld.append(build.getBuilderURL());
			msgBld.append(buildID);
			msgBld.append(".log.txt\n");
			if(useMock)
			{
				msgBld.append("\nThis is a mock mail. Real recipients would have been:\n");
				for(InternetAddress to : toList)
				{
					msgBld.append("  ");
					msgBld.append(to);
					msgBld.append('\n');
				}
			}
			Properties props = new Properties();
			props.put("mail.smtp.host", "localhost");
			props.put("mail.smtp.port", "25");
			MimeMessage msg = new MimeMessage(Session.getInstance(props));
			InternetAddress buildMaster = contactToAddress(build.getBuildmaster());
			msg.setFrom(buildMaster);

			if(useMock)
			{
				List<InternetAddress> recipients = mockRecipients();
				msg.setRecipients(RecipientType.TO, recipients.toArray(new InternetAddress[recipients.size()]));
				InternetAddress ccRecipient = mockCCRecipient();
				if(ccRecipient != null)
					msg.setRecipient(RecipientType.CC, ccRecipient);
			}
			else
			{
				msg.setRecipients(RecipientType.TO, toList.toArray(new InternetAddress[toList.size()]));
				msg.setRecipient(RecipientType.CC, buildMaster);
			}

			msg.setText(msgBld.toString());
			msg.setSubject(String.format("%s build failed", build.getLabel()));

			// For some odd reason, the Geronimo SMTPTransport class chooses to output
			// lots of completely meaningless output to System.out and there's absolutely
			// no way to prevent that from happening.
			PrintStream sysOut = System.out;
			sysOut.flush();
			System.setOut(new PrintStream(new NullOutputStream()));
			try
			{
				Transport.send(msg);
			}
			finally
			{
				System.setOut(sysOut);
			}

			msgBld.setLength(0);
			msgBld.append("Email sent to: ");
			for(InternetAddress to : toList)
			{
				msgBld.append(to);
				msgBld.append(',');
			}
			msgBld.append(buildMaster);
			log.info(msgBld.toString());
		}
		catch(MessagingException e)
		{
			log.error(e, "Failed to send email: %s", e.getMessage());
		}
		catch(UnsupportedEncodingException e)
		{
			log.error(e, "Failed to send email: %s", e.getMessage());
		}
	}

	public void setBuildID(String buildId)
	{
		this.buildID = buildId;
	}

	public void setBuildModelLocation(File buildModelLocation)
	{
		this.buildModelLocation = buildModelLocation;
	}

	public void setBuildRoot(File buildRoot)
	{
		this.buildRoot = buildRoot;
	}

	public void setCategoriesRepo(URI categoriesRepo)
	{
		this.categoriesRepo = categoriesRepo;
	}

	public void setMockEmailCC(String mockEmailCc)
	{
		this.mockEmailCC = mockEmailCc;
	}

	public void setMockEmailTo(String mockEmailTo)
	{
		this.mockEmailTo = mockEmailTo;
	}

	public void setProduction(boolean production)
	{
		this.production = production;
	}

	public void setTargetPlatformRepo(URI targetPlatformRepo)
	{
		this.targetPlatformRepo = targetPlatformRepo;
	}

	public void setUnitsToInstall(Set<IInstallableUnit> unitsToInstall)
	{
		this.unitsToInstall = unitsToInstall;
	}

	public void setUpdate(boolean update)
	{
		this.update = update;
	}

	public void setVerifyOnly(boolean verifyOnly)
	{
		this.verifyOnly = verifyOnly;
	}

	public Object start(IApplicationContext context) throws Exception
	{
		parseCommandLineArgs((String[])context.getArguments().get("application.args")); //$NON-NLS-1$
		try
		{
			run(new NullProgressMonitor());
		}
		catch(Exception e)
		{
			Buckminster.getLogger().error(e, e.getMessage());
			return Integer.valueOf(1);
		}
		return IApplication.EXIT_OK;
	}

	public void stop()
	{
		if(logOutput != null)
		{
			Logger.setOutStream(System.out);
			Logger.setErrStream(System.err);
			IOUtils.close(logOutput);
			logOutput = null;
		}
	}

	private InternetAddress mockCCRecipient() throws UnsupportedEncodingException
	{
		InternetAddress mock = null;
		if(mockEmailCC != null)
		{
			mock = new InternetAddress();
			mock.setAddress(mockEmailCC);
		}
		return mock;
	}

	private List<InternetAddress> mockRecipients() throws UnsupportedEncodingException
	{
		if(mockEmailTo != null)
		{
			InternetAddress mock = new InternetAddress();
			mock.setAddress(mockEmailTo);
			return Collections.singletonList(mock);
		}
		return Collections.emptyList();
	}

	private void parseCommandLineArgs(String[] args)
	{
		int top = args.length;
		for(int idx = 0; idx < top; ++idx)
		{
			String arg = args[idx];
			if("-verifyOnly".equalsIgnoreCase(arg))
			{
				setVerifyOnly(true);
				continue;
			}
			if("-update".equalsIgnoreCase(arg))
			{
				setUpdate(true);
				continue;
			}
			if("-production".equalsIgnoreCase(arg))
			{
				setProduction(true);
				continue;
			}
			if("-mockEmailTo".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-mockEmailTo requires an argument");
				setMockEmailTo(args[idx]);
				continue;
			}
			if("-mockEmailCC".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-mockEmailCC requires an argument");
				setMockEmailCC(args[idx]);
				continue;
			}
			if("-buildModel".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-buildModel requires an argument");
				File buildModel = new File(args[idx]);
				if(!buildModel.canRead())
					throw new IllegalArgumentException(String.format("Unable to read %s", buildModel));
				setBuildModelLocation(buildModel);
				continue;
			}
			if("-buildRoot".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-buildRoot requires an argument");
				setBuildRoot(new File(args[idx]));
				continue;
			}
			if("-buildId".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-buildId requires an argument");
				setBuildID(args[idx]);
				continue;
			}
			if("-targetPlatformRepository".equalsIgnoreCase(arg))
			{
				if(++idx >= top)
					throw new IllegalArgumentException("-targetPlatformRepository requires an argument");
				setTargetPlatformRepo(URI.create(args[idx]));
				continue;
			}
			throw new IllegalArgumentException(String.format("Unknown option %s", arg));
		}
	}

	private void runCategoriesRepoGenerator(IProgressMonitor monitor) throws CoreException
	{
		CategoryRepoGenerator extraGenerator = new CategoryRepoGenerator(this, tempFolder, build.getLabel()
				+ " Categories"); //$NON-NLS-1$
		extraGenerator.run(monitor);
	}

	private void runCompositeGenerator(IProgressMonitor monitor) throws CoreException
	{
		CompositeRepoGenerator repoGenerator = new CompositeRepoGenerator(this, tempFolder, build.getLabel()
				+ " Composite"); //$NON-NLS-1$
		repoGenerator.run(monitor);
	}

	private void runMirroring(IProgressMonitor monitor) throws CoreException
	{
		Promotion promotion = build.getPromotion();
		if(promotion == null)
			throw BuckminsterException.fromMessage("Missing required element <promition>");

		File uploadLocation = new File(PROPERTY_REPLACER.replaceProperties(promotion.getUploadDirectory()));
		URI mirrorsURI = URI.create(PROPERTY_REPLACER.replaceProperties(promotion.getBaseURL()));
		String downloadDirectory = PROPERTY_REPLACER.replaceProperties(promotion.getDownloadDirectory());
		if(downloadDirectory != null)
		{
			try
			{
				if(mirrorsURI.getPath().endsWith("/download.php")) //$NON-NLS-1$
				{
					String query = mirrorsURI.getQuery();
					Map<String, String> params = (query == null)
							? new HashMap<String, String>()
							: URLUtils.queryAsParameters(query);
					params.put("file", downloadDirectory); //$NON-NLS-1$
					if(!params.containsKey("protocol")) //$NON-NLS-1$
						params.put("protocol", "http"); //$NON-NLS-1$//$NON-NLS-2$
					if(!params.containsKey("format")) //$NON-NLS-1$
						params.put("format", "xml"); //$NON-NLS-1$//$NON-NLS-2$
					mirrorsURI = new URI(mirrorsURI.getScheme(), mirrorsURI.getAuthority(), mirrorsURI.getPath(),
							URLUtils.encodeFromQueryPairs(params), mirrorsURI.getFragment());
				}
				else
					mirrorsURI = new URI(mirrorsURI.getScheme(), mirrorsURI.getHost(), mirrorsURI.getPath() + '/'
							+ downloadDirectory, mirrorsURI.getFragment());
			}
			catch(URISyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}

		MirrorGenerator mirrorGenerator = new MirrorGenerator(this, mirrorsURI, uploadLocation);
		mirrorGenerator.run(monitor);
	}

	private void runPlatformRepoGenerator() throws CoreException
	{
		if(targetPlatformRepo != null)
			return;

		for(Contribution contrib : build.getContributions())
			if(TP_CONTRIBUTION_LABEL.equals(contrib.getLabel()))
			{
				List<Repository> repos = contrib.getRepositories();
				if(repos.size() == 1)
				{
					targetPlatformRepo = URI.create(repos.get(0).getLocation());
					return;
				}
			}
		if(targetPlatformRepo == null)
			throw BuckminsterException.fromMessage(
					"The build requires that a contribution named '%s' and appoints one repository. This is where the build extracts the target platform", //$NON-NLS-1$
					TP_CONTRIBUTION_LABEL);
	}

	private void runRepositoryVerifier(IProgressMonitor monitor) throws CoreException
	{
		if(targetPlatformRepo == null)
			throw new IllegalStateException("Target Platform Repository has not been generated");

		RepositoryVerifier ipt = new RepositoryVerifier(this, "org.eclipse.galileo", null); //$NON-NLS-1$
		ipt.run(monitor);
	}

	/**
	 * Runs the transformation and loads the model into memory
	 * 
	 * @throws CoreException
	 *             If something goes wrong with during the process
	 */
	private void runTransformation() throws CoreException
	{
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
			List<ModelContent> inObjects = Collections.singletonList(transf.loadInput(org.eclipse.emf.common.util.URI.createFileURI(buildModelLocation.getAbsolutePath())));
			generatedBuildModel = File.createTempFile("buildModel_", ".tmp"); //$NON-NLS-1$//$NON-NLS-2$

			List<TargetUriData> targetData = Collections.singletonList(new TargetUriData(
					createURI(generatedBuildModel).toString()));
			QvtLaunchConfigurationDelegateBase.doLaunch(transf, inObjects, targetData, configuration, null);

			// Load the Java model into memory
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
					Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
			BuildPackage.eINSTANCE.eClass();
			org.eclipse.emf.common.util.URI fileURI = org.eclipse.emf.common.util.URI.createFileURI(generatedBuildModel.getAbsolutePath());
			Resource resource = resourceSet.getResource(fileURI, true);
			EList<EObject> content = resource.getContents();
			if(content.size() != 1)
				throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d",
						Integer.valueOf(content.size()));

			build = (Build)content.get(0);
			if(buildRoot == null)
				buildRoot = new File(PROPERTY_REPLACER.replaceProperties(build.getBuildRoot()));

			tempFolder = new File(buildRoot, "tmp"); //$NON-NLS-1$
			if(tempFolder.exists())
			{
				if(!update)
				{
					FileUtils.deleteAll(tempFolder);
					if(tempFolder.exists())
						throw BuckminsterException.fromMessage("Failed to delete folder %s", tempFolder);
				}
			}
			tempFolder.mkdirs();
			if(!tempFolder.exists())
				throw BuckminsterException.fromMessage("Failed to create folder %s", tempFolder);

			logOutput = new PrintStream(new FileOutputStream(new File(buildRoot, buildID + ".log.txt")));
			Logger.setOutStream(logOutput);
			Logger.setErrStream(logOutput);
			Logger.setEclipseLoggerLevelThreshold(Logger.SILENT);
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
	}
}
