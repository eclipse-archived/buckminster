package org.eclipse.buckminster.aggregator.engine;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.mail.MailMessage;
import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionedId;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

public class Builder extends AbstractCommand implements IApplication
{
	public enum ActionType
	{
		CLEAN, VERIFY, BUILD, CLEAN_BUILD
	}

	private static class EmailAddress
	{
		private final String address;

		private final String personal;

		EmailAddress(String address, String personal)
		{
			this.address = address;
			this.personal = personal;
		}

		@Override
		public String toString()
		{
			if(personal == null)
				return address;

			return personal + " <" + address + ">";
		}
	}

	public static final String ALL_CONTRIBUTED_CONTENT_FEATURE = "all.contributed.content.feature.group"; //$NON-NLS-1$

	public static final String PDE_TARGET_PLATFORM_NAMESPACE = "A.PDE.Target.Platform";

	public static final Version ALL_CONTRIBUTED_CONTENT_VERSION = Version.createOSGi(1, 0, 0);

	public static final String COMPOSITE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID
			+ ".compositeRepository"; //$NON-NLS-1$

	public static final String COMPOSITE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID
			+ ".compositeRepository"; //$NON-NLS-1$

	public static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String NAMESPACE_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String PROFILE_ID = "GalileoTest"; //$NON-NLS-1$

	public static final String REPO_FOLDER_VERIFICATION = "verification"; //$NON-NLS-1$

	public static final String REPO_FOLDER_FINAL = "final"; //$NON-NLS-1$

	public static final String REPO_FOLDER_INTERIM = "interim"; //$NON-NLS-1$

	public static final String REPO_FOLDER_TEMP = "temp"; //$NON-NLS-1$

	public static final String REPO_FOLDER_AGGREGATE = "aggregate"; //$NON-NLS-1$

	public static final String SIMPLE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID
			+ ".simpleRepository"; //$NON-NLS-1$

	public static final String SIMPLE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID
			+ ".simpleRepository"; //$NON-NLS-1$

	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmm"); //$NON-NLS-1$

	static final String FEATURE_GROUP_SUFFIX = ".feature.group"; //$NON-NLS-1$

	static final IArtifactKey[] NO_ARTIFACT_KEYS = new IArtifactKey[0];

	private static final String BUNDLE_ECF_FS_PROVIDER = "org.eclipse.ecf.provider.filetransfer"; //$NON-NLS-1$

	private static final String BUNDLE_EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	private static final String BUNDLE_UPDATESITE = "org.eclipse.equinox.p2.updatesite"; //$NON-NLS-1$

	private static final String BUNDLE_CORE = "org.eclipse.equinox.p2.core"; //$NON-NLS-1$

	static private final String BUNDLE_ENGINE = "org.eclipse.equinox.p2.engine"; //$NON-NLS-1$

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final String PROP_P2_DATA_AREA = "eclipse.p2.data.area"; //$NON-NLS-1$

	private static final String PROP_P2_PROFILE = "eclipse.p2.profile"; //$NON-NLS-1$

	private static final Project PROPERTY_REPLACER = new Project();

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	static
	{
		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		PROPERTY_REPLACER.initProperties();
		DATE_FORMAT.setTimeZone(utc);
		TIME_FORMAT.setTimeZone(utc);
		TIMESTAMP_FORMAT.setTimeZone(utc);
	}

	static private final OptionDescriptor optAction = new OptionDescriptor("action", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optBuildModel = new OptionDescriptor("buildModel", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optBuildId = new OptionDescriptor("buildId", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optBuildRoot = new OptionDescriptor("buildRoot", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optProduction = new OptionDescriptor("production", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor optMockEmailCc = new OptionDescriptor("mockEmailCc", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optEmailFrom = new OptionDescriptor("emailFrom", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optEmailFromName = new OptionDescriptor(
			"emailFromName", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optMockEmailTo = new OptionDescriptor("mockEmailTo", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optLogURL = new OptionDescriptor("logURL", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optSmtpHost = new OptionDescriptor("smtpHost", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optSmtpPort = new OptionDescriptor("smtpPort", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor optSubjectPrefix = new OptionDescriptor(
			"subjectPrefix", OptionValueType.REQUIRED); //$NON-NLS-1$

	/**
	 * Creates a repository location without the trailing slash that will be added if the standard
	 * {@link java.io.File#toURI()} is used.
	 * 
	 * @param repoLocation
	 *            The location. Must be an absolute path.
	 * @return The created URI.
	 * @throws CoreException
	 *             if the argument is not an absolute path
	 */
	public static final URI createURI(File repoLocation) throws CoreException
	{
		if(repoLocation != null)
		{
			IPath path = Path.fromOSString(repoLocation.getPath());
			if(path.isAbsolute())
				try
				{
					String pathStr = path.removeTrailingSeparator().toPortableString();
					if(!pathStr.startsWith("/"))
						// Path starts with a drive letter
						pathStr = "/" + pathStr; //$NON-NLS-1$
					return new URI("file", null, pathStr, null); //$NON-NLS-1$
				}
				catch(URISyntaxException e)
				{
					throw BuckminsterException.wrap(e);
				}
		}
		throw BuckminsterException.fromMessage("File %s is not an absolute path", repoLocation);
	}

	public static String getExceptionMessages(Throwable e)
	{
		StringBuilder bld = new StringBuilder();
		getExceptionMessages(e, bld);
		return bld.toString();
	}

	public static IInstallableUnit getIU(IMetadataRepository mdr, String id, String version)
	{
		version = Trivial.trim(version);
		InstallableUnitQuery query = version == null
				? new InstallableUnitQuery(id)
				: new InstallableUnitQuery(id, Version.create(version));
		Collector c = mdr.query(query, new Collector(), null);
		IInstallableUnit[] result = (IInstallableUnit[])c.toArray(IInstallableUnit.class);
		return result.length > 0
				? result[0]
				: null;
	}

	private static void deleteAndCheck(File folder, String fileName) throws CoreException
	{
		File file = new File(folder, fileName);
		file.delete();
		if(file.exists())
			throw BuckminsterException.fromMessage("Unable to delete file %s\n", file.getAbsolutePath());
	}

	private static void deleteMetadataRepository(IMetadataRepositoryManager mdrMgr, File repoFolder)
			throws CoreException
	{
		URI repoURI = Builder.createURI(repoFolder);
		mdrMgr.removeRepository(repoURI);
		deleteAndCheck(repoFolder, "compositeContent.jar");
		deleteAndCheck(repoFolder, "compositeContent.xml");
		deleteAndCheck(repoFolder, "content.jar");
		deleteAndCheck(repoFolder, "content.xml");
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

	private static void send(String host, int port, EmailAddress from, List<EmailAddress> toList, EmailAddress cc,
			String subject, String message) throws IOException
	{
		MailMessage mailMessage = new MailMessage(host, port);
		mailMessage.from(from.toString());
		for(EmailAddress to : toList)
			mailMessage.to(to.toString());
		if(cc != null)
			mailMessage.cc(cc.toString());
		mailMessage.setSubject(subject);
		mailMessage.setHeader("Date", DateUtils.getDateForHeader());
		mailMessage.setHeader("Content-Type", "text/plain; charset=us-ascii");
		PrintStream out = mailMessage.getPrintStream();
		out.print(message);
		mailMessage.sendAndClose();
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

	private Aggregator aggregator;

	private String buildID;

	private String buildLabel;

	private String buildMasterEmail;

	private String buildMasterName;

	private File buildModelLocation;

	private File buildRoot;

	private List<InstallableUnit> categoryIUs;

	private String emailFrom;

	private String emailFromName;

	private String logURL;

	private String mockEmailCC;

	private String mockEmailTo;

	private boolean mirrorReferences = false;;

	private Pattern referenceIncludePattern;

	private Pattern referenceExcludePattern;

	private boolean production = false;

	private ResourceSet resourceSet;

	private boolean sendmail = false;

	private String smtpHost;

	private int smtpPort;

	private String subjectPrefix;

	final private Set<IInstallableUnit> unitsToAggregate = new HashSet<IInstallableUnit>();

	private Set<IInstallableUnit> unverifiedUnits;

	private HashMap<MappedRepository, List<VersionedId>> exclusions;

	ActionType action = ActionType.BUILD;

	private CompositeMetadataRepository sourceComposite;

	private String preservedDataArea;

	private String preservedProfile;

	private Map<VersionedId, Version> replacements;

	/**
	 * Prevent that the {@link IInstallableUnit} identified by <code>versionedName</code> is mapped from
	 * <code>repository</code>.
	 * 
	 * @param repository
	 *            The repository for which to exclude a mapping
	 * @param versionedName
	 *            Identifies the IInstallableUnit to exclude.
	 */
	public void addMappingExclusion(MappedRepository repository, VersionedId versionedName, Version replacement)
	{
		List<VersionedId> exclInRepo = null;
		if(exclusions == null)
		{
			exclusions = new HashMap<MappedRepository, List<VersionedId>>();
			replacements = new HashMap<VersionedId, Version>();
		}
		else
			exclInRepo = exclusions.get(repository);

		if(exclInRepo == null)
		{
			exclInRepo = new ArrayList<VersionedId>();
			exclusions.put(repository, exclInRepo);
		}
		exclInRepo.add(versionedName);
		replacements.put(versionedName, replacement);
	}

	/**
	 * Checks if the <code>iu</code> has a required capability with a name space equal to
	 * {@link #PDE_TARGET_PLATFORM_NAMESPACE}. If it has, the <code>iu</code> is only intended for target platforms and
	 * should never be installed in a runtime environment. Consequently, it should not participate in the P2 planner
	 * verification phase.
	 * 
	 * @param iu
	 *            The installable unit to check
	 * @return <code>true</code> if a matching required capability was found.
	 */
	public boolean excludeFromVerification(IInstallableUnit iu)
	{
		for(IRequiredCapability rq : iu.getRequiredCapabilities())
		{
			if(PDE_TARGET_PLATFORM_NAMESPACE.equals(rq.getNamespace()))
			{
				if(unverifiedUnits == null)
					unverifiedUnits = new HashSet<IInstallableUnit>();
				unverifiedUnits.add(iu);
				return true;
			}
		}
		return false;
	}

	public Aggregator getAggregator()
	{
		return aggregator;
	}

	public String getBuildID()
	{
		return buildID;
	}

	public File getBuildModelLocation()
	{
		return buildModelLocation;
	}

	public File getBuildRoot()
	{
		return buildRoot;
	}

	public List<InstallableUnit> getCategoryIUs()
	{
		return categoryIUs;
	}

	public Map<VersionedId, Version> getReplacementMap()
	{
		return replacements == null
				? Collections.<VersionedId, Version> emptyMap()
				: replacements;
	}

	public CompositeMetadataRepository getSourceComposite()
	{
		return sourceComposite;
	}

	public URI getSourceCompositeURI() throws CoreException
	{
		return createURI(new File(buildRoot, REPO_FOLDER_INTERIM));
	}

	public File getTempRepositoryFolder()
	{
		return new File(buildRoot, REPO_FOLDER_TEMP);
	}

	public Set<IInstallableUnit> getUnitsToAggregate()
	{
		return unitsToAggregate;
	}

	public Set<IInstallableUnit> getUnverifiedUnits()
	{
		return unverifiedUnits == null
				? Collections.<IInstallableUnit> emptySet()
				: unverifiedUnits;
	}

	public boolean isCleanBuild()
	{
		return action == ActionType.CLEAN_BUILD;
	}

	/**
	 * Checks if the repository can be included verbatim. If it can, the builder will include a reference to it in a
	 * composite repository instead of copying everything into an aggregate
	 * 
	 * @param repo
	 *            The repository to check
	 * @return true if the repository is mapped verbatim.
	 */
	public boolean isMapVerbatim(MappedRepository repo)
	{
		return !(repo.isMapExclusive() || repo.isMirrorArtifacts()) && Trivial.trim(repo.getCategoryPrefix()) == null
				&& !(exclusions != null && exclusions.containsKey(repo));
	}

	public boolean isMatchedReference(String reference)
	{
		reference = Trivial.trim(reference);
		if(reference == null)
			return false;

		if(referenceIncludePattern != null)
		{
			Matcher m = referenceIncludePattern.matcher(reference);
			if(!m.matches())
				return false;
		}

		if(referenceExcludePattern != null)
		{
			Matcher m = referenceExcludePattern.matcher(reference);
			if(m.matches())
				return false;
		}
		return true;
	}

	public boolean isMirrorReferences()
	{
		return mirrorReferences;
	}

	public boolean isProduction()
	{
		return production;
	}

	public boolean isTopLevelCategory(IInstallableUnit iu)
	{
		return iu != null && "true".equalsIgnoreCase(iu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY))
				&& !"true".equalsIgnoreCase(iu.getProperty(IInstallableUnit.PROP_TYPE_GROUP));
	}

	public boolean isVerifyOnly()
	{
		return action == ActionType.VERIFY;
	}

	/**
	 * Run the build
	 * 
	 * @param monitor
	 */
	public int run(boolean fromIDE, IProgressMonitor monitor)
	{
		int ticks;
		switch(action)
		{
		case CLEAN:
			ticks = 50;
			break;
		case VERIFY:
			ticks = 200;
			break;
		case BUILD:
			ticks = 2150;
			break;
		default:
			ticks = 2200;
		}
		MonitorUtils.begin(monitor, ticks);

		try
		{
			if(buildModelLocation == null)
				throw BuckminsterException.fromMessage("No buildmodel has been set");

			Date now = new Date();
			if(buildID == null)
				buildID = "build-" + DATE_FORMAT.format(now) + TIME_FORMAT.format(now);

			if(smtpHost == null)
				smtpHost = "localhost";

			if(smtpPort <= 0)
				smtpPort = 25;

			// Verify that all contributions can be parsed, i.e. contains
			// well-formed XML
			//
			verifyContributions();
			runTransformation(now);
			switch(action)
			{
			case CLEAN:
			case CLEAN_BUILD:
				cleanAll();
				break;
			default:
				cleanMetadata();
			}

			if(action == ActionType.CLEAN)
				return 0;

			buildRoot.mkdirs();
			if(!buildRoot.exists())
				throw BuckminsterException.fromMessage("Failed to create folder %s", buildRoot);

			Buckminster bucky = Buckminster.getDefault();
			restartP2Bundles();
			try
			{
				// Remove previously used profiles.
				//
				IProfileRegistry profileRegistry = bucky.getService(IProfileRegistry.class);
				try
				{
					List<String> knownIDs = new ArrayList<String>();
					for(IProfile profile : profileRegistry.getProfiles())
					{
						String id = profile.getProfileId();
						if(id.startsWith(PROFILE_ID))
							knownIDs.add(id);
					}

					for(String id : knownIDs)
						profileRegistry.removeProfile(id);

					String instArea = buildRoot.toString();
					Map<String, String> props = new HashMap<String, String>();
					props.put(IProfile.PROP_FLAVOR, "tooling"); //$NON-NLS-1$
					props.put(IProfile.PROP_NAME, aggregator.getLabel());
					props.put(IProfile.PROP_DESCRIPTION, format("Default profile during %s build",
							aggregator.getLabel()));
					props.put(IProfile.PROP_CACHE, instArea);
					props.put(IProfile.PROP_INSTALL_FOLDER, instArea);
					profileRegistry.addProfile(PROFILE_ID, props);
				}
				finally
				{
					bucky.ungetService(profileRegistry);
				}

				runCompositeGenerator(MonitorUtils.subMonitor(monitor, 70));
				runVerificationFeatureGenerator(MonitorUtils.subMonitor(monitor, 15));
				runCategoriesRepoGenerator(MonitorUtils.subMonitor(monitor, 15));
				runRepositoryVerifier(MonitorUtils.subMonitor(monitor, 100));
				if(action != ActionType.VERIFY)
					runMirroring(MonitorUtils.subMonitor(monitor, 2000));
				return 0;
			}
			finally
			{
				restoreP2Bundles();
			}
		}
		catch(Throwable e)
		{
			Buckminster.getLogger().error(e, "Build failed! Exception was %s", getExceptionMessages(e));
			if(e instanceof Error)
				throw (Error)e;
			return 1;
		}
		finally
		{
			if(fromIDE && buildRoot != null)
			{
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				for(IContainer rootContainer : wsRoot.findContainersForLocationURI(buildRoot.toURI()))
					try
					{
						rootContainer.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 10));
					}
					catch(CoreException e)
					{
						// ignore
					}
			}
			monitor.done();
		}
	}

	public void sendEmail(Contribution contrib, List<String> errors)
	{
		boolean useMock = (mockEmailTo != null);
		if(!((production || useMock) && sendmail))
			return;

		Logger log = Buckminster.getLogger();
		try
		{
			EmailAddress buildMaster = new EmailAddress(buildMasterEmail, buildMasterName);
			EmailAddress emailFromAddr;
			if(emailFrom != null)
				emailFromAddr = new EmailAddress(emailFrom, emailFromName);
			else
				emailFromAddr = buildMaster;

			List<EmailAddress> toList = new ArrayList<EmailAddress>();
			if(contrib == null)
				toList.add(buildMaster);
			else
				for(Contact contact : contrib.getContacts())
					toList.add(new EmailAddress(contact.getEmail(), contact.getName()));

			StringBuilder msgBld = new StringBuilder();
			msgBld.append("The following error");
			if(errors.size() > 1)
				msgBld.append('s');
			msgBld.append(" occured when building ");
			msgBld.append(buildLabel);
			msgBld.append(":\n\n");
			for(String error : errors)
			{
				msgBld.append(error);
				msgBld.append("\n\n");
			}

			if(logURL != null)
			{
				msgBld.append("Check the log file for more information: ");
				msgBld.append(logURL);
				msgBld.append('\n');
			}

			if(useMock)
			{
				msgBld.append("\nThis is a mock mail. Real recipients would have been:\n");
				for(EmailAddress to : toList)
				{
					msgBld.append("  ");
					msgBld.append(to);
					msgBld.append('\n');
				}
			}
			String msgContent = msgBld.toString();
			if(subjectPrefix == null)
				subjectPrefix = buildLabel;

			String subject = format("[%s] Failed for build %s", subjectPrefix, buildID);

			msgBld.setLength(0);
			msgBld.append("Sending email to: ");
			for(EmailAddress to : toList)
			{
				msgBld.append(to);
				msgBld.append(',');
			}
			msgBld.append(buildMaster);
			if(useMock)
			{
				msgBld.append(" *** Using mock: ");
				if(mockEmailTo != null)
				{
					msgBld.append(mockEmailTo);
					if(mockEmailCC != null)
					{
						msgBld.append(',');
						msgBld.append(mockEmailTo);
					}
				}
				else
					msgBld.append(mockEmailCC);
				msgBld.append(" ***");
			}
			log.info(msgBld.toString());
			log.info("From: %s", emailFromAddr);
			log.info("Subject: %s", subject);
			log.info("Message content: %s", msgContent);

			List<EmailAddress> recipients;
			EmailAddress ccRecipient = null;
			if(useMock)
			{
				recipients = mockRecipients();
				ccRecipient = mockCCRecipient();
			}
			else
			{
				recipients = toList;
				if(contrib != null)
					ccRecipient = buildMaster;
			}
			send(smtpHost, smtpPort, emailFromAddr, recipients, ccRecipient, subject, msgContent);

		}
		catch(IOException e)
		{
			log.error(e, "Failed to send email: %s", e.getMessage());
		}
	}

	public void setAction(ActionType action)
	{
		this.action = action;
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

	public void setCategoryIUs(List<InstallableUnit> categoryIUs)
	{
		this.categoryIUs = categoryIUs;
	}

	public void setEmailFrom(String emailFrom)
	{
		this.emailFrom = emailFrom;
	}

	public void setEmailFromName(String emailFromName)
	{
		this.emailFromName = emailFromName;
	}

	public void setLogLevel(int level)
	{
		Logger.setConsoleLevelThreshold(level);
	}

	public void setLogURL(String logURL)
	{
		this.logURL = logURL;
	}

	public void setMirrorReferences(boolean mirrorReferences)
	{
		this.mirrorReferences = mirrorReferences;
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

	public void setReferenceExcludePattern(String pattern)
	{
		pattern = Trivial.trim(pattern);
		referenceExcludePattern = pattern == null
				? null
				: Pattern.compile(pattern);
	}

	public void setReferenceIncludePattern(String pattern)
	{
		pattern = Trivial.trim(pattern);
		referenceIncludePattern = pattern == null
				? null
				: Pattern.compile(pattern);
	}

	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}

	public void setSmtpPort(int smtpPort)
	{
		this.smtpPort = smtpPort;
	}

	public void setSourceComposite(CompositeMetadataRepository sourceComposite)
	{
		this.sourceComposite = sourceComposite;
	}

	public void setSubjectPrefix(String subjectPrefix)
	{
		this.subjectPrefix = subjectPrefix;
	}

	@SuppressWarnings("unchecked")
	public Object start(IApplicationContext context) throws Exception
	{
		String[] args = (String[])context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
		String[] allArgs = new String[args.length + 1];
		allArgs[0] = "aggregate";
		System.arraycopy(args, 0, allArgs, 1, args.length);
		List<OptionDescriptor> opts = new ArrayList<OptionDescriptor>();
		getOptionDescriptors(opts);
		for(int idx = 1; idx < allArgs.length; ++idx)
		{
			String arg = allArgs[idx];
			if(arg.length() > 2 && arg.charAt(0) == '-' && arg.charAt(1) != '-')
			{
				// It's likely that this is an old style option, i.e. -buildModel
				// instead of --buildModel
				//
				arg = arg.substring(1);
				for(OptionDescriptor opt : opts)
					if(opt.getLongName().equalsIgnoreCase(arg))
					{
						// Yepp. There it was. So let's replace it
						allArgs[idx] = "--" + arg;
						break;
					}
			}
		}
		context.getArguments().put(IApplicationContext.APPLICATION_ARGS, allArgs);
		Headless headless = new Headless();
		return headless.start(context);
	}

	public void stop()
	{
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		super.getOptionDescriptors(appendHere);
		appendHere.add(optAction);
		appendHere.add(optBuildId);
		appendHere.add(optBuildModel);
		appendHere.add(optBuildRoot);
		appendHere.add(optProduction);
		appendHere.add(optMockEmailCc);
		appendHere.add(optEmailFrom);
		appendHere.add(optEmailFromName);
		appendHere.add(optMockEmailTo);
		appendHere.add(optLogURL);
		appendHere.add(optSmtpHost);
		appendHere.add(optSmtpPort);
		appendHere.add(optSubjectPrefix);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(optAction))
			action = ActionType.valueOf(option.getValue().toUpperCase());
		else if(option.is(optBuildId))
			setBuildID(option.getValue());
		else if(option.is(optBuildModel))
		{
			File buildModel = new File(option.getValue());
			if(!buildModel.canRead())
				throw new IllegalArgumentException(format("Unable to read %s", buildModel));
			setBuildModelLocation(buildModel);
		}
		else if(option.is(optBuildRoot))
			setBuildRoot(new File(option.getValue()));
		else if(option.is(optEmailFrom))
			setEmailFrom(option.getValue());
		else if(option.is(optEmailFromName))
			setEmailFromName(option.getValue());
		else if(option.is(optLogURL))
			setLogURL(option.getValue());
		else if(option.is(optMockEmailCc))
			setMockEmailCC(option.getValue());
		else if(option.is(optMockEmailTo))
			setMockEmailTo(option.getValue());
		else if(option.is(optProduction))
			setProduction(true);
		else if(option.is(optSmtpHost))
			setSmtpHost(option.getValue());
		else if(option.is(optSmtpPort))
			setSmtpPort(Integer.parseInt(option.getValue()));
		else if(option.is(optSubjectPrefix))
			setSubjectPrefix(option.getValue());
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length > 0)
			throw new UsageException("too many arguments");
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(buildModelLocation == null)
			throw new UsageException("Missing required argument --buildModel <path to model>");
		return run(false, monitor);
	}

	private void cleanAll() throws CoreException
	{
		cleanMetadata();

		Buckminster bucky = Buckminster.getDefault();
		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		try
		{
			File destination = new File(buildRoot, Builder.REPO_FOLDER_FINAL);
			arMgr.removeRepository(Builder.createURI(destination));
			arMgr.removeRepository(Builder.createURI(new File(destination, Builder.REPO_FOLDER_AGGREGATE)));
		}
		finally
		{
			bucky.ungetService(arMgr);
		}

		if(buildRoot.exists())
		{
			FileUtils.deleteAll(buildRoot);
			if(buildRoot.exists())
				throw BuckminsterException.fromMessage("Failed to delete folder %s", buildRoot.getAbsolutePath());
		}
	}

	private void cleanMetadata() throws CoreException
	{
		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		try
		{
			File finalRepo = new File(buildRoot, Builder.REPO_FOLDER_FINAL);
			deleteMetadataRepository(mdrMgr, finalRepo);
			deleteMetadataRepository(mdrMgr, new File(finalRepo, Builder.REPO_FOLDER_AGGREGATE));
			File interimRepo = new File(buildRoot, Builder.REPO_FOLDER_INTERIM);
			deleteMetadataRepository(mdrMgr, interimRepo);
			deleteMetadataRepository(mdrMgr, new File(interimRepo, Builder.REPO_FOLDER_VERIFICATION));
		}
		finally
		{
			bucky.ungetService(mdrMgr);
		}
	}

	private EmailAddress mockCCRecipient() throws UnsupportedEncodingException
	{
		EmailAddress mock = null;
		if(mockEmailCC != null)
			mock = new EmailAddress(mockEmailCC, null);
		return mock;
	}

	private List<EmailAddress> mockRecipients() throws UnsupportedEncodingException
	{
		if(mockEmailTo != null)
			return Collections.singletonList(new EmailAddress(mockEmailTo, null));
		return Collections.emptyList();
	}

	private void restartP2Bundles() throws CoreException
	{
		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);

		try
		{
			stopBundle(packageAdmin, BUNDLE_EXEMPLARY_SETUP);
			stopBundle(packageAdmin, BUNDLE_ENGINE);
			stopBundle(packageAdmin, BUNDLE_CORE);

			String p2DataArea = new File(buildRoot, "p2").toString();
			preservedDataArea = System.setProperty(PROP_P2_DATA_AREA, p2DataArea);
			preservedProfile = System.setProperty(PROP_P2_PROFILE, PROFILE_ID);

			if(!startEarly(packageAdmin, BUNDLE_ECF_FS_PROVIDER))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_ECF_FS_PROVIDER);
			if(!startEarly(packageAdmin, BUNDLE_CORE))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_CORE);
			if(!startEarly(packageAdmin, BUNDLE_ENGINE))
				throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_ENGINE);
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
	}

	private void restoreP2Bundles() throws CoreException
	{
		Buckminster bucky = Buckminster.getDefault();
		PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);

		try
		{
			stopBundle(packageAdmin, BUNDLE_EXEMPLARY_SETUP);
			stopBundle(packageAdmin, BUNDLE_ENGINE);
			stopBundle(packageAdmin, BUNDLE_CORE);

			if(preservedDataArea == null)
				System.clearProperty(PROP_P2_DATA_AREA);
			else
				System.setProperty(PROP_P2_DATA_AREA, preservedDataArea);
			if(preservedProfile == null)
				System.clearProperty(PROP_P2_PROFILE);
			else
				System.setProperty(PROP_P2_PROFILE, preservedProfile);

			startEarly(packageAdmin, BUNDLE_CORE);
			startEarly(packageAdmin, BUNDLE_ENGINE);
			startEarly(packageAdmin, BUNDLE_EXEMPLARY_SETUP);
		}
		catch(BundleException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			bucky.ungetService(packageAdmin);
		}
	}

	private void runCategoriesRepoGenerator(IProgressMonitor monitor) throws CoreException
	{
		CategoriesGenerator generator = new CategoriesGenerator(this);
		generator.run(monitor);
	}

	private void runCompositeGenerator(IProgressMonitor monitor) throws CoreException
	{
		SourceCompositeGenerator generator = new SourceCompositeGenerator(this);
		generator.run(monitor);
	}

	private void runMirroring(IProgressMonitor monitor) throws CoreException
	{
		MirrorGenerator generator = new MirrorGenerator(this);
		generator.run(monitor);
	}

	private void runRepositoryVerifier(IProgressMonitor monitor) throws CoreException
	{
		RepositoryVerifier ipt = new RepositoryVerifier(this);
		ipt.run(monitor);
	}

	/**
	 * Runs the transformation and loads the model into memory
	 * 
	 * @throws CoreException
	 *             If something goes wrong with during the process
	 */
	private void runTransformation(Date now) throws CoreException
	{
		try
		{
			// Load the Java model into memory
			resourceSet = new ResourceSetImpl();
			org.eclipse.emf.common.util.URI fileURI = org.eclipse.emf.common.util.URI.createFileURI(buildModelLocation.getAbsolutePath());
			Resource resource = resourceSet.getResource(fileURI, true);
			EList<EObject> content = resource.getContents();
			if(content.size() != 1)
				throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d",
						Integer.valueOf(content.size()));

			aggregator = (Aggregator)content.get(0);
			Diagnostic diag = Diagnostician.INSTANCE.validate(aggregator);
			if(diag.getSeverity() == Diagnostic.ERROR)
			{
				Logger log = Buckminster.getLogger();
				for(Diagnostic childDiag : diag.getChildren())
					log.error(childDiag.getMessage());
				throw BuckminsterException.fromMessage("Build model validation failed: %s", diag.getMessage());
			}

			if(buildRoot == null)
				setBuildRoot(new File(PROPERTY_REPLACER.replaceProperties(aggregator.getBuildRoot())));

			if(!buildRoot.isAbsolute())
				buildRoot = new File(buildModelLocation.getParent(), buildRoot.getPath());
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private void runVerificationFeatureGenerator(IProgressMonitor monitor) throws CoreException
	{
		VerificationFeatureGenerator generator = new VerificationFeatureGenerator(this);
		generator.run(monitor);
	}

	private void verifyContributions() throws CoreException
	{
		URI buildModelFolderURI = buildModelLocation.getParentFile().toURI();
		DocumentBuilderFactory docBldFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBld;
		try
		{
			docBld = docBldFact.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			throw new RuntimeException(e);
		}

		Element masterBuild;
		try
		{
			Document doc = docBld.parse(buildModelLocation);
			masterBuild = doc.getDocumentElement();
			sendmail = "true".equalsIgnoreCase(masterBuild.getAttribute("sendmail"));
			buildLabel = Trivial.trim(masterBuild.getAttribute("label"));
		}
		catch(Exception e)
		{
			String msg;
			if(e instanceof SAXParseException)
				msg = format("Unable to parse file: %s: Error at line %s: %s", buildModelLocation,
						Integer.valueOf(((SAXParseException)e).getLineNumber()), e.getMessage());
			else
				msg = format("Unable to parse file: %s: %s", buildModelLocation, e.getMessage());
			throw BuckminsterException.fromMessage(msg);
		}

		List<File> contributionFiles = new ArrayList<File>();
		for(Node child = masterBuild.getFirstChild(); child != null; child = child.getNextSibling())
		{
			if(!(child instanceof Element))
				continue;

			Element elem = (Element)child;
			if("contributions".equals(elem.getNodeName()))
			{
				String attr = Trivial.trim(elem.getAttribute("href"));
				if(attr == null)
					continue;
				if(attr.endsWith("#/"))
					attr = attr.substring(0, attr.length() - 2);
				URI uri = buildModelFolderURI.resolve(URI.create(attr));
				contributionFiles.add(new File(uri));
			}

			if("buildmaster".equals(elem.getNodeName()))
			{
				buildMasterEmail = Trivial.trim(elem.getAttribute("email"));
				buildMasterName = Trivial.trim(elem.getAttribute("name"));
			}
		}

		List<String> errors = new ArrayList<String>();
		for(File buildFile : contributionFiles)
		{
			try
			{
				docBld.parse(buildFile);
			}
			catch(Exception e)
			{
				String msg;
				if(e instanceof SAXParseException)
					msg = format("Unable to parse file: %s: Error at line %s: %s", buildFile,
							Integer.valueOf(((SAXParseException)e).getLineNumber()), e.getMessage());
				else
					msg = format("Unable to parse file: %s: %s", buildFile, e.getMessage());
				Buckminster.getLogger().error(e, msg);
				errors.add(msg);
			}
			finally
			{
				docBld.reset();
			}
		}

		if(errors.size() > 0)
		{
			sendEmail(null, errors);
			throw BuckminsterException.fromMessage("Not all contributions could be parsed");
		}
	}
}
