package org.eclipse.buckminster.galileo.builder;

import java.io.File;
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

	static private final String EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup";

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	private static final Project PROPERTY_REPLACER = new Project();

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
		bld.append(e.getClass());
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

	private static InternetAddress mockCCRecipient() throws UnsupportedEncodingException
	{
		InternetAddress mock = new InternetAddress();
		mock.setAddress("thomas@tada.se");
		mock.setPersonal("Thomas Hallgren (Tada)");
		return mock;
	}

	private static List<InternetAddress> mockRecipients() throws UnsupportedEncodingException
	{
		InternetAddress mock = new InternetAddress();
		mock.setAddress("thomas.hallgren@cloudsmith.com");
		mock.setPersonal("Thomas Hallgren (Cloudsmith)");
		return Collections.singletonList(mock);
	}

	private static boolean startEarly(PackageAdmin packageAdmin, String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if(bundle == null)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	private File buildModelLocation;

	private boolean update;

	private boolean verifyOnly;

	private Build build;

	private File buildRoot;

	private File tempFolder;

	private URI categoriesRepo;

	private URI targetPlatformRepo;

	private Set<IInstallableUnit> unitsToInstall;

	private String buildID = "no-buildId-assigned";

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

		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
		try
		{
			if(!startEarly(packageAdmin, EXEMPLARY_SETUP))
				throw BuckminsterException.fromMessage("Missing bundle %s", EXEMPLARY_SETUP);
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
			runTransformation();
			runCompositeGenerator(MonitorUtils.subMonitor(monitor, 70));
			runCategoriesRepoGenerator(MonitorUtils.subMonitor(monitor, 3));
			runPlatformRepoGenerator(MonitorUtils.subMonitor(monitor, 7));
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
		Logger log = Buckminster.getLogger();
		try
		{
			List<InternetAddress> toList = new ArrayList<InternetAddress>();
			for(Contact contact : contrib.getContacts())
				toList.add(contactToAddress(contact));

			boolean useMock = true;
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
			msgBld.append('/');
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

			List<InternetAddress> recipients = useMock
					? mockRecipients()
					: toList;
			msg.setRecipients(RecipientType.TO, recipients.toArray(new InternetAddress[recipients.size()]));
			msg.setRecipient(RecipientType.CC, useMock
					? mockCCRecipient()
					: buildMaster);

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
		buildID = buildId;
	}

	public void setBuildModelLocation(File buildModelLocation)
	{
		this.buildModelLocation = buildModelLocation;
	}

	public void setCategoriesRepo(URI categoriesRepo)
	{
		this.categoriesRepo = categoriesRepo;
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
		run(new NullProgressMonitor());
		return Integer.valueOf(0);
	}

	public void stop()
	{
		// TODO Auto-generated method stub

	}

	private void parseCommandLineArgs(String[] strings)
	{
		// TODO Auto-generated method stub

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

	private void runPlatformRepoGenerator(IProgressMonitor monitor) throws CoreException
	{
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
		build = null;
		buildRoot = null;
		tempFolder = null;
		categoriesRepo = null;
		targetPlatformRepo = null;
		unitsToInstall = null;

		Logger log = Buckminster.getLogger();
		log.info("Starting build transformation. Source: %s", buildModelLocation);
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
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}
}
