package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.io.FileOutputStream;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.tools.ant.Project;
import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.BuildPackage;
import org.eclipse.amalgam.releng.build.Contact;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.amalgam.releng.build.Promotion;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.NullOutputStream;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.gmf.internal.xpand.ant.XpandFacade;
import org.eclipse.m2m.internal.qvt.oml.common.launch.TargetUriData;
import org.eclipse.m2m.internal.qvt.oml.emf.util.ModelContent;
import org.eclipse.m2m.internal.qvt.oml.runtime.launch.QvtLaunchConfigurationDelegateBase;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.DeployedQvtModule;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtInterpretedTransformation;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.service.packageadmin.PackageAdmin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

@SuppressWarnings("restriction")
public class Builder implements IApplication {

	public static enum PackedStrategy {
		/**
		 * If the source contains packed artifacts, copy and store only the
		 * them. This is the default strategy.
		 */
		COPY,

		/**
		 * Same as {@link #COPY} but unpack and then discard the unpacked.
		 */
		VERIFY,

		/**
		 * Same as {@link #COPY} but unpack and store as sibling.
		 */
		UNPACK_AS_SIBLING,

		/**
		 * Use the packed artifacts for the transfer but only store the unpacked
		 * artifact.
		 */
		UNPACK,

		/**
		 * Don't consider packed artifacts at all.
		 */
		SKIP
	}

	public static final String ALL_CONTRIBUTED_CONTENT_FEATURE = "all.contributed.content.feature.group"; //$NON-NLS-1$

	public static final Version ALL_CONTRIBUTED_CONTENT_VERSION = Version.createOSGi(1, 0, 0);

	public static final String COMPOSITE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID + ".compositeRepository"; //$NON-NLS-1$

	public static final String COMPOSITE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID + ".compositeRepository"; //$NON-NLS-1$

	public static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String NAMESPACE_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String PROFILE_ID = "GalileoTest"; //$NON-NLS-1$

	public static final String REPO_FOLDER_CATEGORIES = "categories"; //$NON-NLS-1$

	public static final String REPO_FOLDER_FINAL = "final"; //$NON-NLS-1$

	public static final String REPO_FOLDER_INTERIM = "interim"; //$NON-NLS-1$

	public static final String REPO_FOLDER_AGGREGATE = "aggregate"; //$NON-NLS-1$

	public static final String SIMPLE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID + ".simpleRepository"; //$NON-NLS-1$

	public static final String SIMPLE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID + ".simpleRepository"; //$NON-NLS-1$

	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmm"); //$NON-NLS-1$

	static final String FEATURE_GROUP_SUFFIX = ".feature.group"; //$NON-NLS-1$

	private static final String BUNDLE_ECF_FS_PROVIDER = "org.eclipse.ecf.provider.filetransfer"; //$NON-NLS-1$

	private static final String BUNDLE_EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	private static final String BUNDLE_UPDATESITE = "org.eclipse.equinox.p2.updatesite"; //$NON-NLS-1$

	private static final String CORE_BUNDLE = "org.eclipse.equinox.p2.core"; //$NON-NLS-1$

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final String PROP_P2_DATA_AREA = "eclipse.p2.data.area"; //$NON-NLS-1$

	private static final String PROP_P2_PROFILE = "eclipse.p2.profile"; //$NON-NLS-1$

	private static final Project PROPERTY_REPLACER = new Project();

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	public static IInstallableUnit getIU(IMetadataRepository mdr, String id, String version) {
		version = Trivial.trim(version);
		InstallableUnitQuery query = version == null ? new InstallableUnitQuery(id) : new InstallableUnitQuery(id, Version.create(version));
		Collector c = mdr.query(query, new Collector(), null);
		IInstallableUnit[] result = (IInstallableUnit[]) c.toArray(IInstallableUnit.class);
		return result.length > 0 ? result[0] : null;
	}

	public static boolean isCapabilitiesBundle(org.eclipse.amalgam.releng.build.Bundle bundle) {
		String id = bundle.getId();
		return id.endsWith(".capabilities") && bundle.getRepo() != null;
	}

	public static boolean isCapabilitiesFeature(Feature feature) {
		String id = feature.getId();
		return (id.endsWith(".capabilities") || id.endsWith(".capabilities.feature")) && feature.getRepo() != null
				&& feature.getCategory().size() == 0;
	}

	private static InternetAddress contactToAddress(Contact contact) throws UnsupportedEncodingException {
		InternetAddress addr = new InternetAddress();
		addr.setPersonal(contact.getName());
		addr.setAddress(contact.getEmail());
		return addr;
	}

	private static void deleteAllButWorkspace(File folder, IPath wsPath) throws CoreException {
		File[] children = folder.listFiles();
		if (children == null)
			return;

		for (File child : children) {
			IPath childPath = Path.fromOSString(child.getAbsolutePath());
			if (childPath.isPrefixOf(wsPath)) {
				if (!childPath.equals(wsPath))
					deleteAllButWorkspace(child, wsPath);
				continue;
			}
			FileUtils.deleteAll(child);
			if (child.exists())
				throw BuckminsterException.fromMessage("Failed to delete folder %s", childPath);
		}
	}

	private static synchronized Bundle getBundle(PackageAdmin packageAdmin, String symbolicName) {
		Bundle[] bundles = packageAdmin.getBundles(symbolicName, null);
		if (bundles == null)
			return null;

		// Return the first bundle that is not installed or uninstalled
		for (int i = 0; i < bundles.length; i++) {
			Bundle bundle = bundles[i];
			if ((bundle.getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
				return bundle;
		}
		return null;
	}

	private static void getExceptionMessages(Throwable e, StringBuilder bld) {
		bld.append(e.getClass().getName());
		bld.append(": ");
		if (e.getMessage() != null)
			bld.append(e.getMessage());

		if (e instanceof CoreException)
			e = ((CoreException) e).getStatus().getException();
		else {
			Throwable t = e.getCause();
			e = (t == e) ? null : t;
		}
		if (e != null) {
			bld.append("\nCaused by: ");
			getExceptionMessages(e, bld);
		}
	}

	private static void requiresArgument(String opt) {
		throw new IllegalArgumentException("Option " + opt + " requires an argument");
	}

	private static boolean startEarly(PackageAdmin packageAdmin, String bundleName) throws BundleException {
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if (bundle == null)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	private static boolean stopBundle(PackageAdmin packageAdmin, String bundleName) throws BundleException {
		Bundle bundle = getBundle(packageAdmin, bundleName);
		if (bundle == null || bundle.getState() != Bundle.ACTIVE)
			return false;
		bundle.stop(Bundle.STOP_TRANSIENT);
		return true;
	}

	private final List<String> trustedContributions = new ArrayList<String>();

	static {
		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		PROPERTY_REPLACER.initProperties();
		DATE_FORMAT.setTimeZone(utc);
		TIME_FORMAT.setTimeZone(utc);
		TIMESTAMP_FORMAT.setTimeZone(utc);
	}

	/**
	 * Creates a repository location without the trailing slash that will be
	 * added if the standard {@link java.io.File#toURI()} is used.
	 * 
	 * @param repoLocation
	 *            The location. Must be an absolute path.
	 * @return The created URI.
	 * @throws CoreException
	 *             if the argument is not an absolute path
	 */
	public static final URI createURI(File repoLocation) throws CoreException {
		if (repoLocation != null) {
			IPath path = Path.fromOSString(repoLocation.getPath());
			if (path.isAbsolute())
				try {
					String pathStr = path.removeTrailingSeparator().toPortableString();
					if (!pathStr.startsWith("/"))
						// Path starts with a drive letter
						pathStr = "/" + pathStr; //$NON-NLS-1$
					return new URI("file", null, pathStr, null); //$NON-NLS-1$
				} catch (URISyntaxException e) {
					throw BuckminsterException.wrap(e);
				}
		}
		throw BuckminsterException.fromMessage("File %s is not an absolute path", repoLocation);
	}

	public static String getExceptionMessages(Throwable e) {
		StringBuilder bld = new StringBuilder();
		getExceptionMessages(e, bld);
		return bld.toString();
	}

	private Build build;

	private String buildID;

	private String buildLabel;

	private String buildMasterEmail;

	private String buildMasterName;

	private File buildModelLocation;

	private File buildRoot;

	private String brandingContribution;

	private Feature brandingFeature;

	private URI categoriesRepo;

	private String emailFrom;

	private String emailFromName;

	private int logLevel = Logger.INFO;

	private String logURL;

	private String mockEmailCC;

	private String mockEmailTo;

	private boolean brandingBuild = true;

	private boolean production = false;

	private PackedStrategy packedStrategy = PackedStrategy.COPY;

	private ResourceSet resourceSet;

	private boolean sendmail = false;

	private String smtpHost;

	private String smtpPassword;

	private int smtpPort;

	private String smtpUser;

	private String subjectPrefix;

	private Set<IInstallableUnit> unitsToAggregate;

	private Set<IInstallableUnit> trustedUnits;

	private boolean update = false;

	private boolean verifyOnly = false;

	private URI[] trustedContributionRepos;

	public Feature getBrandingFeature() {
		return brandingFeature;
	}

	public Build getBuild() {
		return build;
	}

	public String getBuildID() {
		return buildID;
	}

	public File getBuildModelLocation() {
		return buildModelLocation;
	}

	public File getBuildRoot() {
		return buildRoot;
	}

	public URI getCategoriesRepo() {
		return categoriesRepo;
	}

	public URI getGlobalRepoURI() throws CoreException {
		return createURI(new File(buildRoot, REPO_FOLDER_INTERIM));
	}

	public URI getMirrorsURI(boolean aggregate) throws CoreException {
		Promotion promotion = build.getPromotion();
		if (promotion == null)
			throw BuckminsterException.fromMessage("Missing required element <promition>");

		URI mirrorsURI = URI.create(PROPERTY_REPLACER.replaceProperties(promotion.getBaseURL()));
		String downloadDirectory = PROPERTY_REPLACER.replaceProperties(promotion.getDownloadDirectory());
		if (downloadDirectory != null) {
			try {
				if (mirrorsURI.getPath().endsWith("/download.php")) //$NON-NLS-1$
				{
					String query = mirrorsURI.getQuery();
					Map<String, String> params = (query == null) ? new HashMap<String, String>() : URLUtils.queryAsParameters(query);
					if (aggregate) {
						if (downloadDirectory.endsWith("/"))
							downloadDirectory += (REPO_FOLDER_AGGREGATE + '/');
						else
							downloadDirectory += ('/' + REPO_FOLDER_AGGREGATE);
					}
					params.put("file", downloadDirectory); //$NON-NLS-1$
					if (!params.containsKey("protocol")) //$NON-NLS-1$
						params.put("protocol", "http"); //$NON-NLS-1$//$NON-NLS-2$
					if (!params.containsKey("format")) //$NON-NLS-1$
						params.put("format", "xml"); //$NON-NLS-1$//$NON-NLS-2$
					mirrorsURI = new URI(mirrorsURI.getScheme(), mirrorsURI.getAuthority(), mirrorsURI.getPath(), URLUtils
							.encodeFromQueryPairs(params), mirrorsURI.getFragment());
				} else
					mirrorsURI = new URI(mirrorsURI.getScheme(), mirrorsURI.getHost(), mirrorsURI.getPath() + '/' + downloadDirectory, mirrorsURI
							.getFragment());
			} catch (URISyntaxException e) {
				throw BuckminsterException.wrap(e);
			}
		}
		return mirrorsURI;
	}

	public PackedStrategy getPackedStrategy() {
		return packedStrategy;
	}

	public URI[] getTrustedContributionRepos() {
		return trustedContributionRepos;
	}

	public Set<IInstallableUnit> getTrustedUnits() {
		return trustedUnits;
	}

	public Set<IInstallableUnit> getUnitsToAggregate() {
		return unitsToAggregate;
	}

	public boolean isBrandingBuild() {
		return brandingBuild;
	}

	public boolean isProduction() {
		return production;
	}

	public boolean isUpdate() {
		return update;
	}

	public boolean isVerifyOnly() {
		return verifyOnly;
	}

	/**
	 * Run the build
	 * 
	 * @param monitor
	 */
	public Object run(IProgressMonitor monitor) {
		MonitorUtils.begin(monitor, verifyOnly ? 200 : 1100);

		try {
			if (buildModelLocation == null)
				throw BuckminsterException.fromMessage("No buildmodel has been set");

			Date now = new Date();
			if (buildID == null)
				buildID = "build-" + DATE_FORMAT.format(now) + TIME_FORMAT.format(now);

			if (smtpHost == null)
				smtpHost = "localhost";

			if (smtpPort <= 0)
				smtpPort = 25;

			if (!update) {
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				FileUtils.deleteAll(new File(wsRoot.getLocation().toOSString(), ".metadata/.plugins/org.eclipse.buckminster.core"));
				wsRoot.delete(IResource.FORCE | IResource.ALWAYS_DELETE_PROJECT_CONTENT, new NullProgressMonitor());
			}

			// Verify that all contributions can be parsed, i.e. contains
			// well-formed XML
			verifyContributions();

			runTransformation(now);
			List<Contribution> contributions = build.getContributions();

			if (brandingContribution != null) {
				brandingFeature = null;
				for (Contribution contrib : contributions) {
					if (!brandingContribution.equals(contrib.getLabel()))
						continue;
					List<Feature> features = contrib.getFeatures();
					if (features.size() == 1) {
						brandingFeature = features.get(0);
						break;
					}
					throw BuckminsterException.fromMessage("The capability contribution %s does not have exactly one feature", brandingContribution);
				}

				if (brandingFeature == null)
					throw BuckminsterException.fromMessage("Unable to find capability contribution %s in the build model", brandingContribution);
			}

			if (trustedContributions.size() > 0) {
				ArrayList<URI> allRepos = new ArrayList<URI>(trustedContributions.size());
				for (String trusted : trustedContributions) {
					Contribution tc = null;
					for (Contribution contrib : contributions)
						if (trusted.equals(contrib.getLabel())) {
							tc = contrib;
							break;
						}
					if (tc == null)
						throw BuckminsterException.fromMessage("Unable to find capability contribution %s in the build model", trusted);
					for (Repository repo : tc.getRepositories())
						allRepos.add(URI.create(repo.getLocation()));
				}
				trustedContributionRepos = allRepos.toArray(new URI[allRepos.size()]);
			} else
				trustedContributionRepos = new URI[0];

			Buckminster bucky = Buckminster.getDefault();
			PackageAdmin packageAdmin = bucky.getService(PackageAdmin.class);
			try {
				stopBundle(packageAdmin, BUNDLE_EXEMPLARY_SETUP);
				stopBundle(packageAdmin, CORE_BUNDLE);

				String p2DataArea = new File(buildRoot, "p2").toString();
				System.setProperty(PROP_P2_DATA_AREA, p2DataArea);
				System.setProperty(PROP_P2_PROFILE, PROFILE_ID);

				if (!startEarly(packageAdmin, BUNDLE_ECF_FS_PROVIDER))
					throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_ECF_FS_PROVIDER);
				if (!startEarly(packageAdmin, CORE_BUNDLE))
					throw BuckminsterException.fromMessage("Missing bundle %s", CORE_BUNDLE);
				if (!startEarly(packageAdmin, BUNDLE_EXEMPLARY_SETUP))
					throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_EXEMPLARY_SETUP);
				if (!startEarly(packageAdmin, BUNDLE_UPDATESITE))
					throw BuckminsterException.fromMessage("Missing bundle %s", BUNDLE_UPDATESITE);

				IProfile profile = null;
				IProfileRegistry profileRegistry = bucky.getService(IProfileRegistry.class);
				if (update)
					profile = profileRegistry.getProfile(PROFILE_ID);

				if (profile == null) {
					String instArea = buildRoot.toString();
					Map<String, String> props = new HashMap<String, String>();
					props.put(IProfile.PROP_FLAVOR, "tooling"); //$NON-NLS-1$
					props.put(IProfile.PROP_NAME, build.getLabel());
					props.put(IProfile.PROP_DESCRIPTION, String.format("Default profile during %s build", build.getLabel()));
					props.put(IProfile.PROP_CACHE, instArea); //$NON-NLS-1$
					props.put(IProfile.PROP_INSTALL_FOLDER, instArea);
					profile = profileRegistry.addProfile(PROFILE_ID, props);
				}
				bucky.ungetService(profileRegistry);
			} catch (BundleException e) {
				throw BuckminsterException.wrap(e);
			} finally {
				bucky.ungetService(packageAdmin);
			}

			runTemplateExpansion(resourceSet, "build2page::Main", new File(buildRoot, "index.php"));
			runTemplateExpansion(resourceSet, "build2xml::Main", new File(buildRoot, "index.xml"));
			runCompositeGenerator(MonitorUtils.subMonitor(monitor, 70));
			runBrandingFeatureBuild(MonitorUtils.subMonitor(monitor, 100));
			runCategoriesRepoGenerator(MonitorUtils.subMonitor(monitor, 10));
			runRepositoryVerifier(MonitorUtils.subMonitor(monitor, 20));
			if (!verifyOnly)
				runMirroring(MonitorUtils.subMonitor(monitor, 900));
		} catch (Throwable e) {
			Buckminster.getLogger().error(e, "Build failed! Exception was %s", getExceptionMessages(e));
			if (e instanceof Error)
				throw (Error) e;
			return Integer.valueOf(1);
		} finally {
			monitor.done();
		}
		return IApplication.EXIT_OK;
	}

	public void sendEmail(Contribution contrib, List<String> errors) {
		boolean useMock = (mockEmailTo != null);
		if (!(production || useMock) && sendmail)
			return;

		Logger log = Buckminster.getLogger();
		try {
			InternetAddress buildMaster = new InternetAddress();
			buildMaster.setAddress(buildMasterEmail);
			buildMaster.setPersonal(buildMasterName);
			InternetAddress emailFromAddr;
			if (emailFrom != null) {
				emailFromAddr = new InternetAddress();
				emailFromAddr.setAddress(emailFrom);
				if (emailFromName != null)
					emailFromAddr.setPersonal(emailFromName);
			} else
				emailFromAddr = buildMaster;

			List<InternetAddress> toList = new ArrayList<InternetAddress>();
			if (contrib == null)
				toList.add(buildMaster);
			else
				for (Contact contact : contrib.getContacts())
					toList.add(contactToAddress(contact));

			StringBuilder msgBld = new StringBuilder();
			msgBld.append("The following error");
			if (errors.size() > 1)
				msgBld.append('s');
			msgBld.append(" occured when building ");
			msgBld.append(buildLabel);
			msgBld.append(":\n\n");
			for (String error : errors) {
				msgBld.append(error);
				msgBld.append("\n\n");
			}

			if (logURL != null) {
				msgBld.append("Check the log file for more information: ");
				msgBld.append(logURL);
				msgBld.append('\n');
			}

			if (useMock) {
				msgBld.append("\nThis is a mock mail. Real recipients would have been:\n");
				for (InternetAddress to : toList) {
					msgBld.append("  ");
					msgBld.append(to);
					msgBld.append('\n');
				}
			}
			String msgContent = msgBld.toString();
			if (subjectPrefix == null)
				subjectPrefix = buildLabel;

			String subject = String.format("[%s] Failed for build %s", subjectPrefix, buildID);

			msgBld.setLength(0);
			msgBld.append("Sending email to: ");
			for (InternetAddress to : toList) {
				msgBld.append(to);
				msgBld.append(',');
			}
			msgBld.append(buildMaster);
			if (useMock) {
				msgBld.append(" *** Using mock: ");
				if (mockEmailTo != null) {
					msgBld.append(mockEmailTo);
					if (mockEmailCC != null) {
						msgBld.append(',');
						msgBld.append(mockEmailTo);
					}
				} else
					msgBld.append(mockEmailCC);
				msgBld.append(" ***");
			}
			log.info(msgBld.toString());
			log.info("From: %s", emailFromAddr);
			log.info("Subject: %s", subject);
			log.info("Message content: %s", msgContent);

			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(emailFromAddr);

			if (useMock) {
				List<InternetAddress> recipients = mockRecipients();
				msg.setRecipients(RecipientType.TO, recipients.toArray(new InternetAddress[recipients.size()]));
				InternetAddress ccRecipient = mockCCRecipient();
				if (ccRecipient != null)
					msg.setRecipient(RecipientType.CC, ccRecipient);
			} else {
				msg.setRecipients(RecipientType.TO, toList.toArray(new InternetAddress[toList.size()]));
				if (contrib != null)
					msg.setRecipient(RecipientType.CC, buildMaster);
			}

			msg.setText(msgContent);
			msg.setSubject(subject);

			// For some odd reason, the Geronimo SMTPTransport class chooses to
			// output
			// lots of completely meaningless output to System.out and there's
			// absolutely
			// no way to prevent that from happening.
			PrintStream sysOut = System.out;
			sysOut.flush();
			System.setOut(new PrintStream(new NullOutputStream()));
			try {
				Transport transport = session.getTransport("smtp");
				transport.connect(smtpHost, smtpPort, smtpUser, smtpPassword);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
			} finally {
				System.setOut(sysOut);
			}

		} catch (MessagingException e) {
			log.error(e, "Failed to send email: %s", e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e, "Failed to send email: %s", e.getMessage());
		}
	}

	public void setBrandingBuild(boolean brandingBuild) {
		this.brandingBuild = brandingBuild;
	}

	public void setBrandingContribution(String brandingContribution) {
		this.brandingContribution = brandingContribution;
	}

	public void setBuildID(String buildId) {
		this.buildID = buildId;
	}

	public void setBuildModelLocation(File buildModelLocation) {
		this.buildModelLocation = buildModelLocation;
	}

	public void setBuildRoot(File buildRoot) {
		this.buildRoot = buildRoot;
	}

	public void setCategoriesRepo(URI categoriesRepo) {
		this.categoriesRepo = categoriesRepo;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setEmailFromName(String emailFromName) {
		this.emailFromName = emailFromName;
	}

	public void setLogLevel(int level) {
		logLevel = level;
	}

	public void setLogURL(String logURL) {
		this.logURL = logURL;
	}

	public void setMockEmailCC(String mockEmailCc) {
		this.mockEmailCC = mockEmailCc;
	}

	public void setMockEmailTo(String mockEmailTo) {
		this.mockEmailTo = mockEmailTo;
	}

	public void setPackedStrategy(PackedStrategy packedStrategy) {
		this.packedStrategy = packedStrategy;
	}

	public void setProduction(boolean production) {
		this.production = production;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public void setTrustedContributions(String trustedContribs) {
		int idx = trustedContribs.indexOf(',');
		while (idx > 0) {
			String tc = Trivial.trim(trustedContribs.substring(0, idx));
			if (tc != null)
				trustedContributions.add(tc);
			trustedContribs = trustedContribs.substring(idx + 1);
			idx = trustedContribs.indexOf(',');
		}
		trustedContribs = Trivial.trim(trustedContribs);
		if (trustedContribs != null)
			trustedContributions.add(trustedContribs);
	}

	public void setTrustedUnits(Set<IInstallableUnit> trustedUnits) {
		this.trustedUnits = trustedUnits;
	}

	public void setUnitsToAggregate(Set<IInstallableUnit> unitsToAggregate) {
		this.unitsToAggregate = unitsToAggregate;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public void setVerifyOnly(boolean verifyOnly) {
		this.verifyOnly = verifyOnly;
	}

	public boolean skipFeature(Feature feature, List<String> errors) {
		if (feature.getRepo() == null) {
			if (brandingFeature != feature) {
				if (errors != null) {
					String error = String.format("Skipping feature %s/%s since it has no repository", feature.getId(), feature.getVersion());
					errors.add(error);
					Buckminster.getLogger().error(error);
				}
			}
			return true;
		}
		return isCapabilitiesFeature(feature);
	}

	public Object start(IApplicationContext context) throws Exception {

		String[] args = (String[]) context.getArguments().get("application.args");
		Logger.setEclipseLoggerLevelThreshold(Logger.SILENT);
		Logger log = Buckminster.getLogger();
		StringBuilder msgBld = new StringBuilder();
		msgBld.append("Running with arguments:");
		for (String arg : args) {
			msgBld.append(LINE_SEPARATOR);
			msgBld.append("  '");
			msgBld.append(arg);
			msgBld.append('\'');
		}
		String msg = msgBld.toString();
		try {
			parseCommandLineArgs(args); //$NON-NLS-1$
			Logger.setConsoleLevelThreshold(logLevel);
			log.debug(msg);
		} catch (Exception e) {
			// We use error level when the arguments are corrupt since the user
			// didn't
			// have a chance to set the debug level
			log.info(msg);
			return Integer.valueOf(1);
		}
		return run(new NullProgressMonitor());
	}

	public void stop() {
	}

	private InternetAddress mockCCRecipient() throws UnsupportedEncodingException {
		InternetAddress mock = null;
		if (mockEmailCC != null) {
			mock = new InternetAddress();
			mock.setAddress(mockEmailCC);
		}
		return mock;
	}

	private List<InternetAddress> mockRecipients() throws UnsupportedEncodingException {
		if (mockEmailTo != null) {
			InternetAddress mock = new InternetAddress();
			mock.setAddress(mockEmailTo);
			return Collections.singletonList(mock);
		}
		return Collections.emptyList();
	}

	private void parseCommandLineArgs(String[] args) {
		int top = args.length;
		for (int idx = 0; idx < top; ++idx) {
			String arg = args[idx];
			if ("-verifyOnly".equalsIgnoreCase(arg)) {
				setVerifyOnly(true);
				continue;
			}
			if ("-updateOnly".equalsIgnoreCase(arg)) {
				setUpdate(true);
				continue;
			}
			if ("-production".equalsIgnoreCase(arg)) {
				setProduction(true);
				continue;
			}
			if ("-packedStrategy".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				String strategyStr = args[idx];
				PackedStrategy strategy;
				if ("copy".equalsIgnoreCase(strategyStr))
					strategy = PackedStrategy.COPY;
				else if ("verify".equalsIgnoreCase(strategyStr))
					strategy = PackedStrategy.VERIFY;
				else if ("unpackAsSibling".equalsIgnoreCase(strategyStr))
					strategy = PackedStrategy.UNPACK_AS_SIBLING;
				else if ("unpack".equalsIgnoreCase(strategyStr))
					strategy = PackedStrategy.UNPACK;
				else if ("skip".equalsIgnoreCase(strategyStr))
					strategy = PackedStrategy.SKIP;
				else
					throw new IllegalArgumentException(String.format("%s is not a valid packed strategy", strategyStr));

				setPackedStrategy(strategy);
				continue;
			}
			if ("-logLevel".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				String levelStr = args[idx];
				int level;
				if ("debug".equalsIgnoreCase(levelStr))
					level = Logger.DEBUG;
				else if ("info".equalsIgnoreCase(levelStr))
					level = Logger.INFO;
				else if ("warning".equalsIgnoreCase(levelStr))
					level = Logger.WARNING;
				else if ("error".equalsIgnoreCase(levelStr))
					level = Logger.WARNING;
				else
					throw new IllegalArgumentException(String.format("%s is not a valid logLevel", levelStr));

				setLogLevel(level);
				continue;
			}
			if ("-logURL".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setLogURL(args[idx]);
				continue;
			}
			if ("-mockEmailTo".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setMockEmailTo(args[idx]);
				continue;
			}
			if ("-subjectPrefix".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setSubjectPrefix(args[idx]);
				continue;
			}
			if ("-emailFrom".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setEmailFrom(args[idx]);
				continue;
			}
			if ("-emailFromName".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setEmailFromName(args[idx]);
				continue;
			}
			if ("-smtpHost".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setSmtpHost(args[idx]);
				continue;
			}
			if ("-smtpPort".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				int portNumber = 0;
				try {
					portNumber = Integer.parseInt(args[idx]);
				} catch (NumberFormatException e) {
				}
				if (portNumber <= 0)
					requiresArgument(arg);
				setSmtpPort(portNumber);
				continue;
			}
			if ("-smtpUser".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setSmtpUser(args[idx]);
				continue;
			}
			if ("-smtpPassword".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setSmtpPassword(args[idx]);
				continue;
			}
			if ("-mockEmailCC".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setMockEmailCC(args[idx]);
				continue;
			}
			if ("-buildModel".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				File buildModel = new File(args[idx]);
				if (!buildModel.canRead())
					throw new IllegalArgumentException(String.format("Unable to read %s", buildModel));
				setBuildModelLocation(buildModel);
				continue;
			}
			if ("-buildRoot".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setBuildRoot(new File(args[idx]));
				continue;
			}
			if ("-buildId".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setBuildID(args[idx]);
				continue;
			}
			if ("-brandingContribution".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setBrandingContribution(args[idx]);
				continue;
			}
			if ("-noBrandingBuild".equalsIgnoreCase(arg)) {
				setBrandingBuild(false);
				continue;
			}
			if ("-trustedContributions".equalsIgnoreCase(arg)) {
				if (++idx >= top)
					requiresArgument(arg);
				setTrustedContributions(args[idx]);
				continue;
			}
			String msg = String.format("Unknown option %s", arg);
			Buckminster.getLogger().error(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	private void runBrandingFeatureBuild(IProgressMonitor monitor) throws CoreException {
		if (brandingFeature == null)
			return;
		BrandingFeatureCompiler brandingFeatureCompiler = new BrandingFeatureCompiler(this, brandingFeature);
		brandingFeatureCompiler.run(monitor);
	}

	private void runCategoriesRepoGenerator(IProgressMonitor monitor) throws CoreException {
		CategoryRepoGenerator extraGenerator = new CategoryRepoGenerator(this);
		extraGenerator.run(monitor);
	}

	private void runCompositeGenerator(IProgressMonitor monitor) throws CoreException {
		CompositeRepoGenerator repoGenerator = new CompositeRepoGenerator(this);
		repoGenerator.run(monitor);
	}

	private void runMirroring(IProgressMonitor monitor) throws CoreException {
		MirrorGenerator mirrorGenerator = new MirrorGenerator(this);
		mirrorGenerator.run(monitor);
	}

	private void runRepositoryVerifier(IProgressMonitor monitor) throws CoreException {
		RepositoryVerifier ipt = new RepositoryVerifier(this);
		ipt.run(monitor);
	}

	private void runTemplateExpansion(ResourceSet rs, String name, File outFile) throws IOException {
		XpandFacade xf = new XpandFacade(rs);
		xf.addLocation("platform:/plugin/org.eclipse.buckminster.galileo.builder/templates/");
		xf.addLocation("platform:/plugin/org.eclipse.amalgam.releng.builder/transformations/");
		String result = xf.xpand(name, build);
		FileOutputStream os = new FileOutputStream(outFile);
		os.write(result.getBytes());
		os.close();
	}

	/**
	 * Runs the transformation and loads the model into memory
	 * 
	 * @throws CoreException
	 *             If something goes wrong with during the process
	 */
	private void runTransformation(Date now) throws CoreException {
		File generatedBuildModel = null;
		try {
			// Transform the model, i.e. collect all contributions and create
			// one single build model file
			Map<String, Object> configuration = new HashMap<String, Object>();
			configuration.put("date", DATE_FORMAT.format(now)); //$NON-NLS-1$
			configuration.put("time", TIME_FORMAT.format(now)); //$NON-NLS-1$
			QvtTransformation transf = new QvtInterpretedTransformation(new DeployedQvtModule('/' + Activator.PLUGIN_ID + "/build.qvto")); //$NON-NLS-1$
			List<ModelContent> inObjects = Collections.singletonList(transf.loadInput(org.eclipse.emf.common.util.URI
					.createFileURI(buildModelLocation.getAbsolutePath())));
			generatedBuildModel = File.createTempFile("buildModel_", ".tmp"); //$NON-NLS-1$//$NON-NLS-2$

			List<TargetUriData> targetData = Collections.singletonList(new TargetUriData(createURI(generatedBuildModel).toString()));
			QvtLaunchConfigurationDelegateBase.doLaunch(transf, inObjects, targetData, configuration, null);

			// Load the Java model into memory
			resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION,
					new XMIResourceFactoryImpl());
			BuildPackage.eINSTANCE.eClass();
			org.eclipse.emf.common.util.URI fileURI = org.eclipse.emf.common.util.URI.createFileURI(generatedBuildModel.getAbsolutePath());
			Resource resource = resourceSet.getResource(fileURI, true);
			EList<EObject> content = resource.getContents();
			if (content.size() != 1)
				throw BuckminsterException.fromMessage("ECore Resource did not contain one resource. It had %d", Integer.valueOf(content.size()));

			build = (Build) content.get(0);
			if (buildRoot == null)
				buildRoot = new File(PROPERTY_REPLACER.replaceProperties(build.getBuildRoot()));

			if (!update) {
				if (buildRoot.exists()) {
					IPath wsPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
					if (Path.fromOSString(buildRoot.getAbsolutePath()).isPrefixOf(wsPath))
						deleteAllButWorkspace(buildRoot, wsPath);
					else {
						FileUtils.deleteAll(buildRoot);
						if (buildRoot.exists())
							throw BuckminsterException.fromMessage("Failed to delete folder %s", buildRoot.getAbsolutePath());
					}
				}
			}
			buildRoot.mkdirs();
			if (!buildRoot.exists())
				throw BuckminsterException.fromMessage("Failed to create folder %s", buildRoot);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			if (generatedBuildModel != null)
				generatedBuildModel.delete();
		}
	}

	private void verifyContributions() throws CoreException {
		URI buildModelFolderURI = buildModelLocation.getParentFile().toURI();
		DocumentBuilderFactory docBldFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBld;
		try {
			docBld = docBldFact.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Element masterBuild;
		try {
			Document doc = docBld.parse(buildModelLocation);
			masterBuild = doc.getDocumentElement();
			sendmail = "true".equalsIgnoreCase(masterBuild.getAttribute("sendmail"));
			buildLabel = Trivial.trim(masterBuild.getAttribute("label"));
		} catch (Exception e) {
			String msg;
			if (e instanceof SAXParseException)
				msg = String.format("Unable to parse file: %s: Error at line %s: %s", buildModelLocation, Integer.valueOf(((SAXParseException) e)
						.getLineNumber()), e.getMessage());
			else
				msg = String.format("Unable to parse file: %s: %s", buildModelLocation, e.getMessage());
			throw BuckminsterException.fromMessage(msg);
		}

		List<File> contributionFiles = new ArrayList<File>();
		for (Node child = masterBuild.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (!(child instanceof Element))
				continue;

			Element elem = (Element) child;
			if ("contributions".equals(elem.getNodeName())) {
				String attr = Trivial.trim(elem.getAttribute("href"));
				if (attr == null)
					continue;
				if (attr.endsWith("#/"))
					attr = attr.substring(0, attr.length() - 2);
				URI uri = buildModelFolderURI.resolve(URI.create(attr));
				contributionFiles.add(new File(uri));
			}

			if ("buildmaster".equals(elem.getNodeName())) {
				buildMasterEmail = Trivial.trim(elem.getAttribute("email"));
				buildMasterName = Trivial.trim(elem.getAttribute("name"));
			}
		}

		List<String> errors = new ArrayList<String>();
		for (File buildFile : contributionFiles) {
			try {
				docBld.parse(buildFile);
			} catch (Exception e) {
				String msg;
				if (e instanceof SAXParseException)
					msg = String.format("Unable to parse file: %s: Error at line %s: %s", buildFile, Integer.valueOf(((SAXParseException) e)
							.getLineNumber()), e.getMessage());
				else
					msg = String.format("Unable to parse file: %s: %s", buildFile, e.getMessage());
				Buckminster.getLogger().error(e, msg);
				errors.add(msg);
			} finally {
				docBld.reset();
			}
		}

		if (errors.size() > 0) {
			sendEmail(null, errors);
			throw BuckminsterException.fromMessage("Not all contributions could be parsed");
		}
	}
}
