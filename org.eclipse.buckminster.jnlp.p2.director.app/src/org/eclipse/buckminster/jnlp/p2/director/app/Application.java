/*******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Cloudsmith - https://bugs.eclipse.org/bugs/show_bug.cgi?id=226401
 *     EclipseSource - ongoing development
 *******************************************************************************/
package org.eclipse.buckminster.jnlp.p2.director.app;

import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.APP_LAUNCHED_SYNC_STRING;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.DEFAULT_MAX_CAPTURED_LINES;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.DEFAULT_STARTUP_TIME;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_PROPERTY_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.PROP_MAX_CAPTURED_LINES;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.PROP_STARTUP_TIME;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.PROP_SUPPORT_EMAIL;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants;
import org.eclipse.buckminster.jnlp.p2.bootstrap.ErrorDialog;
import org.eclipse.buckminster.jnlp.p2.bootstrap.JNLPException;
import org.eclipse.buckminster.jnlp.p2.bootstrap.SplashWindow;
import org.eclipse.buckminster.jnlp.p2.bootstrap.TailLineBuffer;
import org.eclipse.buckminster.jnlp.p2.bootstrap.Utils;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.p2.core.helpers.LogHelper;
import org.eclipse.equinox.internal.p2.core.helpers.ServiceHelper;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.*;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.director.*;
import org.eclipse.equinox.internal.provisional.p2.engine.*;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.*;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.*;
import org.osgi.service.packageadmin.PackageAdmin;

@SuppressWarnings("restriction")
public class Application implements IApplication
{
	private static final Integer EXIT_ERROR = new Integer(13);

	static private final String ANT_PROPERTY_PREFIX = "${"; //$NON-NLS-1$

	static private final String FLAVOR_DEFAULT = "tooling"; //$NON-NLS-1$

	static private final String EXEMPLARY_SETUP = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	static private final String FRAMEWORKADMIN_EQUINOX = "org.eclipse.equinox.frameworkadmin.equinox"; //$NON-NLS-1$

	static private final String SIMPLE_CONFIGURATOR_MANIPULATOR = "org.eclipse.equinox.simpleconfigurator.manipulator"; //$NON-NLS-1$

	public static final int COMMAND_INSTALL = 0;

	public static final int COMMAND_UNINSTALL = 1;

	public static final int COMMAND_LIST = 2;

	public static final String[] COMMAND_NAMES = { "-installIU", "-uninstallIU", "-list" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private Path m_destination;

	private URI[] m_artifactRepositoryLocations;

	private URI[] m_metadataRepositoryLocations;

	private URI[] m_metadataReposForRemoval;

	private URI[] m_artifactReposForRemoval;

	private IArtifactRepositoryManager m_artifactManager;

	private IMetadataRepositoryManager m_metadataManager;

	private String m_root;

	private Version m_version = null;

	private String m_flavor;

	private String m_profileId;

	private String m_profileProperties; // a comma-separated list of property pairs "tag=value"

	private String m_bundlePool = null;

	private String m_nl;

	private String m_os;

	private String m_arch;

	private String m_ws;

	private boolean m_roamingProfile = false;

	private IPlanner m_planner;

	private IEngine m_engine;

	private boolean m_noProfileId = false;

	private Properties m_configProps;

	private String m_configURL = null;

	private String m_extraArgs = null;

	private int m_command = -1;

	private ServiceReference m_packageAdminRef;

	private PackageAdmin m_packageAdmin;
	
	private Image m_splashImageBoot;
	
	private Image m_splashImage;
	
	private Image m_windowIconImage;
	
	private boolean m_productStarted;

	private void ambigousCommand(int cmd1, int cmd2) throws CoreException
	{
		throw new CoreException(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Ambigous_Command,
				new Object[] { COMMAND_NAMES[cmd1], COMMAND_NAMES[cmd2] })));
	}

	private ProfileChangeRequest buildProvisioningRequest(IProfile profile, Collector roots, boolean install)
	{
		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		markRoots(request, roots);
		if(install)
		{
			request.addInstallableUnits((IInstallableUnit[])roots.toArray(IInstallableUnit.class));
		}
		else
		{
			request.removeInstallableUnits((IInstallableUnit[])roots.toArray(IInstallableUnit.class));
		}
		return request;
	}

	synchronized Bundle getBundle(String symbolicName)
	{
		if(m_packageAdmin == null)
			return null;

		Bundle[] bundles = m_packageAdmin.getBundles(symbolicName, null);
		if(bundles == null)
			return null;
		// Return the first bundle that is not installed or uninstalled
		for(int i = 0; i < bundles.length; i++)
		{
			if((bundles[i].getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
			{
				return bundles[i];
			}
		}
		return null;
	}

	private String getEnvironmentProperty()
	{
		Properties values = new Properties();
		if(m_os != null)
			values.put("osgi.os", m_os); //$NON-NLS-1$
		if(m_nl != null)
			values.put("osgi.nl", m_nl); //$NON-NLS-1$
		if(m_ws != null)
			values.put("osgi.ws", m_ws); //$NON-NLS-1$
		if(m_arch != null)
			values.put("osgi.arch", m_arch); //$NON-NLS-1$
		if(values.isEmpty())
			return null;
		return toString(values);
	}

	private IProfile initializeProfile() throws CoreException
	{
		if(m_profileId == null)
		{
			m_profileId = IProfileRegistry.SELF;
			m_noProfileId = true;
		}
		IProfile profile = ProvisioningHelper.getProfile(m_profileId);
		if(profile == null)
		{
			if(m_destination == null)
				missingArgument("destination"); //$NON-NLS-1$
			if(m_flavor == null)
				m_flavor = System.getProperty("eclipse.p2.configurationFlavor", FLAVOR_DEFAULT); //$NON-NLS-1$

			Properties props = new Properties();
			props.setProperty(IProfile.PROP_INSTALL_FOLDER, m_destination.toOSString());
			props.setProperty(IProfile.PROP_FLAVOR, m_flavor);
			if(m_bundlePool != null)
				if(m_bundlePool.equals(Messages.destination_commandline))
					props.setProperty(IProfile.PROP_CACHE, m_destination.toOSString());
				else
					props.setProperty(IProfile.PROP_CACHE, m_bundlePool);
			if(m_roamingProfile)
				props.setProperty(IProfile.PROP_ROAMING, Boolean.TRUE.toString());

			String env = getEnvironmentProperty();
			if(env != null)
				props.setProperty(IProfile.PROP_ENVIRONMENTS, env);
			if(m_profileProperties != null)
			{
				putProperties(m_profileProperties, props);
			}
			profile = ProvisioningHelper.addProfile(m_profileId, props);
			String currentFlavor = profile.getProperty(IProfile.PROP_FLAVOR);
			if(currentFlavor != null && !currentFlavor.endsWith(m_flavor))
				throw new RuntimeException(NLS.bind(
						"Install flavor {0} not consistent with profile flavor {1}", m_flavor, currentFlavor)); //$NON-NLS-1$
		}
		return profile;
	}

	private void initializeRepositories(boolean throwException) throws CoreException
	{
		if(m_artifactRepositoryLocations == null)
		{
			if(throwException)
				missingArgument("artifactRepository"); //$NON-NLS-1$
		}
		else
		{
			m_artifactManager = (IArtifactRepositoryManager)ServiceHelper.getService(Activator.getContext(),
					IArtifactRepositoryManager.class.getName());
			if(m_artifactManager == null)
			{
				if(throwException)
					throw new ProvisionException(Messages.Application_NoManager);
			}
			else
			{
				int removalIdx = 0;
				boolean anyValid = false; // do we have any valid repos or did they all fail to load?
				m_artifactReposForRemoval = new URI[m_artifactRepositoryLocations.length];
				for(int i = 0; i < m_artifactRepositoryLocations.length; i++)
				{
					try
					{
						if(!m_artifactManager.contains(m_artifactRepositoryLocations[i]))
						{
							m_artifactManager.loadRepository(m_artifactRepositoryLocations[i], null);
							m_artifactReposForRemoval[removalIdx++] = m_artifactRepositoryLocations[i];
						}
						anyValid = true;
					}
					catch(ProvisionException e)
					{
						// one of the repositories did not load
						LogHelper.log(new Status(IStatus.ERROR, Activator.ID, m_artifactRepositoryLocations[i].toString()
								+ " failed to load", e)); //$NON-NLS-1$
					}
				}
				if(throwException && !anyValid)
					// all repositories failed to load
					throw new ProvisionException(Messages.Application_NoRepositories);
			}
		}

		if(m_metadataRepositoryLocations == null)
		{
			if(throwException)
				missingArgument("metadataRepository"); //$NON-NLS-1$
		}
		else
		{
			m_metadataManager = (IMetadataRepositoryManager)ServiceHelper.getService(Activator.getContext(),
					IMetadataRepositoryManager.class.getName());
			if(m_metadataManager == null)
			{
				if(throwException)
					throw new ProvisionException(Messages.Application_NoManager);
			}
			else
			{
				int removalIdx = 0;
				boolean anyValid = false; // do we have any valid repos or did they all fail to load?
				m_metadataReposForRemoval = new URI[m_metadataRepositoryLocations.length];
				for(int i = 0; i < m_metadataRepositoryLocations.length; i++)
				{
					try
					{
						if(!m_metadataManager.contains(m_metadataRepositoryLocations[i]))
						{
							m_metadataManager.loadRepository(m_metadataRepositoryLocations[i], null);
							m_metadataReposForRemoval[removalIdx++] = m_metadataRepositoryLocations[i];
						}
						anyValid = true;
					}
					catch(ProvisionException e)
					{
						// one of the repositories did not load
						LogHelper.log(new Status(IStatus.ERROR, Activator.ID, m_metadataRepositoryLocations[i].toString()
								+ " failed to load", e)); //$NON-NLS-1$
					}
				}
				if(throwException && !anyValid)
					// all repositories failed to load
					throw new ProvisionException(Messages.Application_NoRepositories);
			}
		}
	}

	private void initializeServices()
	{
		IDirector director = (IDirector)ServiceHelper.getService(Activator.getContext(), IDirector.class.getName());
		if(director == null)
			throw new RuntimeException(Messages.Missing_director);

		m_planner = (IPlanner)ServiceHelper.getService(Activator.getContext(), IPlanner.class.getName());
		if(m_planner == null)
			throw new RuntimeException(Messages.Missing_planner);

		m_engine = (IEngine)ServiceHelper.getService(Activator.getContext(), IEngine.SERVICE_NAME);
		if(m_engine == null)
			throw new RuntimeException(Messages.Missing_Engine);
	}

	private void markRoots(ProfileChangeRequest request, Collector roots)
	{
		for(Iterator iterator = roots.iterator(); iterator.hasNext();)
		{
			request.setInstallableUnitProfileProperty((IInstallableUnit)iterator.next(),
					IInstallableUnit.PROP_PROFILE_ROOT_IU, Boolean.TRUE.toString());
		}
	}

	private void missingArgument(String argumentName) throws CoreException
	{
		throw new CoreException(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Missing_Required_Argument,
				argumentName)));
	}

	private IStatus planAndExecute(IProfile profile, ProvisioningContext context, ProfileChangeRequest request,
			IProgressMonitor monitor)
	{
		ProvisioningPlan result;
		IStatus operationStatus;
		result = m_planner.getProvisioningPlan(request, context, monitor);
		if(!result.getStatus().isOK())
			operationStatus = result.getStatus();
		else
		{
			operationStatus = PlanExecutionHelper.executePlan(result, m_engine, context, monitor);
		}
		return operationStatus;
	}

	private void printRequest(ProfileChangeRequest request)
	{
		IInstallableUnit[] toAdd = request.getAddedInstallableUnits();
		IInstallableUnit[] toRemove = request.getRemovedInstallableUnits();
		for(int i = 0; i < toAdd.length; i++)
		{
			System.out.println(NLS.bind(Messages.Installing, toAdd[i].getId(), toAdd[i].getVersion()));
		}
		for(int i = 0; i < toRemove.length; i++)
		{
			System.out.println(NLS.bind(Messages.Uninstalling, toRemove[i].getId(), toRemove[i].getVersion()));
		}
	}

	public void processArguments(String[] args) throws Exception
	{
		if(args == null)
			return;
		for(int i = 0; i < args.length; i++)
		{

			String opt = args[i];
			if(opt.equals("-roaming")){ //$NON-NLS-1$
				m_roamingProfile = true;
			}

			if(opt.equals(COMMAND_NAMES[COMMAND_LIST]))
			{
				if(m_command != -1)
					ambigousCommand(COMMAND_LIST, m_command);
				m_command = COMMAND_LIST;
			}

			// check for args without parameters (i.e., a flag arg)

			// check for args with parameters. If we are at the last
			// argument or
			// if the next one
			// has a '-' as the first character, then we can't have an arg
			// with
			// a parm so continue.
			if(i == args.length - 1 || args[i + 1].startsWith("-")) //$NON-NLS-1$
				continue;

			String arg = args[++i];

			if(opt.equalsIgnoreCase("-profile")) //$NON-NLS-1$
				m_profileId = arg;

			if(opt.equalsIgnoreCase("-profileProperties") || opt.equalsIgnoreCase("-props")) //$NON-NLS-1$ //$NON-NLS-2$
				m_profileProperties = arg;

			// we create a path object here to handle ../ entries in the middle of paths
			if(opt.equalsIgnoreCase("-destination") || opt.equalsIgnoreCase("-dest")){ //$NON-NLS-1$ //$NON-NLS-2$
				if(arg.startsWith("file:")) //$NON-NLS-1$
					arg = arg.substring(5);
				m_destination = new Path(arg);
			}

			// we create a path object here to handle ../ entries in the middle of paths
			if(opt.equalsIgnoreCase("-bundlepool") || opt.equalsIgnoreCase("-bp")){ //$NON-NLS-1$ //$NON-NLS-2$
				if(arg.startsWith("file:")) //$NON-NLS-1$
					arg = arg.substring(5);
				m_bundlePool = new Path(arg).toOSString();
			}

			if(opt.equalsIgnoreCase("-metadataRepository") || opt.equalsIgnoreCase("-metadataRepositories") || opt.equalsIgnoreCase("-mr")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				m_metadataRepositoryLocations = getURIs(arg);

			if(opt.equalsIgnoreCase("-artifactRepository") || opt.equalsIgnoreCase("-artifactRepositories") || opt.equalsIgnoreCase("-ar")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				m_artifactRepositoryLocations = getURIs(arg);

			if(opt.equalsIgnoreCase("-flavor")) //$NON-NLS-1$
				m_flavor = arg;

			if(opt.equalsIgnoreCase(COMMAND_NAMES[COMMAND_INSTALL]))
			{
				if(m_command != -1)
					ambigousCommand(COMMAND_INSTALL, m_command);
				m_root = arg;
				m_command = COMMAND_INSTALL;
			}

			if(opt.equalsIgnoreCase("-version")){ //$NON-NLS-1$
				if(arg != null && !arg.startsWith(ANT_PROPERTY_PREFIX))
					m_version = new Version(arg);
			}

			if(opt.equalsIgnoreCase(COMMAND_NAMES[COMMAND_UNINSTALL]))
			{
				if(m_command != -1)
					ambigousCommand(COMMAND_UNINSTALL, m_command);
				m_root = arg;
				m_command = COMMAND_UNINSTALL;
			}

			if(opt.equalsIgnoreCase("-p2.os")){ //$NON-NLS-1$
				m_os = arg;
			}
			if(opt.equalsIgnoreCase("-p2.ws")){ //$NON-NLS-1$
				m_ws = arg;
			}
			if(opt.equalsIgnoreCase("-p2.nl")){ //$NON-NLS-1$
				m_nl = arg;
			}
			if(opt.equalsIgnoreCase("-p2.arch")){ //$NON-NLS-1$
				m_arch = arg;
			}
			if(opt.equalsIgnoreCase("-configURL")){ //$NON-NLS-1$
				m_configURL = arg;
			}
			if(opt.equalsIgnoreCase("-extra")){ //$NON-NLS-1$
				m_extraArgs = arg;
			}
		}
	}

	/**
	 * @param pairs
	 *            a comma separated list of tag=value pairs
	 * @param properties
	 *            the collection into which the pairs are put
	 */
	private void putProperties(String pairs, Properties properties)
	{
		StringTokenizer tok = new StringTokenizer(pairs, ",", true); //$NON-NLS-1$
		while(tok.hasMoreTokens())
		{
			String next = tok.nextToken().trim();
			int i = next.indexOf('=');
			if(i > 0 && i < next.length() - 1)
			{
				String tag = next.substring(0, i).trim();
				String value = next.substring(i + 1, next.length()).trim();
				if(tag.length() > 0 && value.length() > 0)
				{
					properties.put(tag, value);
				}
			}
		}
	}

	public Object run(String[] args, IProgressMonitor monitor) throws Exception
	{
		long time = -System.currentTimeMillis();
		initializeServices();
		processArguments(args);

		IStatus operationStatus = Status.OK_STATUS;
		InstallableUnitQuery query;
		Collector roots;
		try
		{
			initializeRepositories(m_command == COMMAND_INSTALL);
			switch(m_command)
			{
			case COMMAND_INSTALL:
			case COMMAND_UNINSTALL:

				IProfile profile = initializeProfile();
				query = new InstallableUnitQuery(m_root, m_version == null
						? VersionRange.emptyRange
						: new VersionRange(m_version, true, m_version, true));
				roots = collectRootIUs(m_metadataRepositoryLocations, new CompositeQuery(new Query[] { query,
						new LatestIUVersionQuery() }), new Collector(), monitor);
				if(roots.size() <= 0)
					roots = profile.query(query, roots, monitor);
				if(roots.size() <= 0)
				{
					System.out.println(NLS.bind(Messages.Missing_IU, m_root));
					logFailure(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Missing_IU, m_root)));
					return EXIT_ERROR;
				}
				// keep this result status in case there is a problem so we can report it to the user
				boolean wasRoaming = Boolean.valueOf(profile.getProperty(IProfile.PROP_ROAMING)).booleanValue();
				try
				{
					IStatus updateRoamStatus = updateRoamingProperties(profile, monitor);
					if(!updateRoamStatus.isOK())
					{
						MultiStatus multi = new MultiStatus(Activator.ID, IStatus.ERROR, NLS.bind(
								Messages.Cant_change_roaming, profile.getProfileId()), null);
						multi.add(updateRoamStatus);
						System.out.println(multi.getMessage());
						System.out.println(updateRoamStatus.getMessage());
						logFailure(multi);
						return EXIT_ERROR;
					}
					ProvisioningContext context = new ProvisioningContext(m_metadataRepositoryLocations);
					context.setArtifactRepositories(m_artifactRepositoryLocations);
					ProfileChangeRequest request = buildProvisioningRequest(profile, roots, m_command == COMMAND_INSTALL);
					printRequest(request);
					operationStatus = planAndExecute(profile, context, request, monitor);
				}
				finally
				{
					// if we were originally were set to be roaming and we changed it, change it back before we return
					if(wasRoaming && !Boolean.valueOf(profile.getProperty(IProfile.PROP_ROAMING)).booleanValue())
						setRoaming(profile, monitor);
				}
				break;
			case COMMAND_LIST:
				query = new InstallableUnitQuery(null, VersionRange.emptyRange);
				if(m_metadataRepositoryLocations == null)
					missingArgument("metadataRepository"); //$NON-NLS-1$

				roots = collectRootIUs(m_metadataRepositoryLocations, query, null, monitor);
				Iterator unitIterator = roots.iterator();
				while(unitIterator.hasNext())
				{
					IInstallableUnit iu = (IInstallableUnit)unitIterator.next();
					System.out.println(iu.getId());
				}
				break;
			}
		}
		finally
		{
			cleanupRepositories();
		}

		time += System.currentTimeMillis();
		if(operationStatus.isOK())
			System.out.println(NLS.bind(Messages.Operation_complete, new Long(time)));
		else
		{
			System.out.println(Messages.Operation_failed);
			logFailure(operationStatus);
			return EXIT_ERROR;
		}
		return IApplication.EXIT_OK;
	}

	private void loadConfigProperties(String configURL) throws JNLPException
	{
		if(m_configProps != null)
			return;

		m_configProps = new Properties();

		if(configURL != null)
		{
			URL propertiesURL = null;
			InputStream propStream = null;

			try
			{
				propertiesURL = new URL(configURL.trim());
				propStream = new BufferedInputStream(propertiesURL.openStream());
				m_configProps.load(propStream);
			}
			catch(MalformedURLException e)
			{
				throw new JNLPException(Messages.cannot_read_URL_to_configuration_properties,
						Messages.report_the_problem, ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
			}
			catch(IOException e)
			{
				throw new JNLPException(Messages.cannot_read_configuration_properties,
						Messages.check_your_system_permissions_internet_connection_and_try_again,
						ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
			}
			finally
			{
				Utils.close(propStream);
			}
		}
	}

	private void startSplash(boolean forceSplashVisible) throws JNLPException
	{
		if(!SplashWindow.isSplashUp())
		{
			SplashWindow.splash(getSplashImageBoot(), getSplashImage(), getWindowIconImage(), forceSplashVisible ? 0 : BootstrapConstants.SPLASH_WINDOW_DELAY);
		}
	}
	
	private Image getSplashImageBoot()
	{
		if(m_splashImageBoot == null && m_configProps != null)
		{
			try
			{
				m_splashImageBoot = Utils.createImage(m_configProps
						.getProperty(BootstrapConstants.PROP_SPLASH_IMAGE_BOOT));
			}
			catch(JNLPException e)
			{
				// stays null
			}
		}
			
		return m_splashImageBoot;
	}

	private Image getSplashImage()
	{
		if(m_splashImage == null && m_configProps != null)
		{
			try
			{
				m_splashImage = Utils.createImage(m_configProps
						.getProperty(BootstrapConstants.PROP_SPLASH_IMAGE));
			}
			catch(JNLPException e)
			{
				// stays null
			}
		}
			
		return m_splashImage;
	}

	private Image getWindowIconImage()
	{
		if(m_windowIconImage == null && m_configProps != null)
		{
			try
			{
				m_windowIconImage = Utils.createImage(m_configProps
						.getProperty(BootstrapConstants.PROP_WINDOW_ICON));
			}
			catch(JNLPException e)
			{
				// stays null
			}
		}
			
		return m_windowIconImage;
	}

	private void stopSplash()
	{
		SplashWindow.disposeSplash();
	}

	private void cleanupRepositories()
	{
		if(m_artifactReposForRemoval != null && m_artifactManager != null)
		{
			for(int i = 0; i < m_artifactReposForRemoval.length && m_artifactReposForRemoval[i] != null; i++)
			{
				m_artifactManager.removeRepository(m_artifactReposForRemoval[i]);
			}
		}
		if(m_metadataReposForRemoval != null && m_metadataManager != null)
		{
			for(int i = 0; i < m_metadataReposForRemoval.length && m_metadataReposForRemoval[i] != null; i++)
			{
				m_metadataManager.removeRepository(m_metadataReposForRemoval[i]);
			}
		}
	}

	class LocationQueryable implements IQueryable
	{
		private URI location;

		public LocationQueryable(URI location)
		{
			this.location = location;
		}

		public Collector query(Query query, Collector collector, IProgressMonitor monitor)
		{
			return ProvisioningHelper.getInstallableUnits(location, query, collector, monitor);
		}
	}

	private Collector collectRootIUs(URI[] locations, Query query, Collector collector, IProgressMonitor monitor)
	{
		if(locations == null || locations.length == 0)
			return ProvisioningHelper.getInstallableUnits(null, query, collector, monitor);

		Collector result = collector != null
				? collector
				: new Collector();
		IQueryable[] locationQueryables = new IQueryable[locations.length];
		for(int i = 0; i < locations.length; i++)
		{
			locationQueryables[i] = new LocationQueryable(locations[i]);
		}
		return new CompoundQueryable(locationQueryables).query(query, result, monitor);
	}

	private synchronized void setPackageAdmin(PackageAdmin service)
	{
		m_packageAdmin = service;
	}

	private boolean startEarly(String bundleName) throws BundleException
	{
		Bundle bundle = getBundle(bundleName);
		if(bundle == null)
			return false;
		bundle.start(Bundle.START_TRANSIENT);
		return true;
	}

	public Object start(IApplicationContext context) throws Exception
	{
		IProgressMonitor monitor = null;
		Object result = null;
		Exception exception = null;
		boolean productInstalled = false;
		
		try
		{
			// tell jnlp.p2.bootstrap that the director app is started
			System.out.println(BootstrapConstants.APP_LAUNCHED_SYNC_STRING);

			String[] args = (String[])context.getArguments().get("application.args");
			
			String configURL = readArgs(args, "-configURL");
			loadConfigProperties(configURL);

			monitor = new JNLPProgressMonitor();
			
			boolean forceSplashVisible = "true".equals(readArgs(args, "-forceSplash"));
			startSplash(forceSplashVisible);
			
			m_packageAdminRef = Activator.getContext().getServiceReference(PackageAdmin.class.getName());
			setPackageAdmin((PackageAdmin)Activator.getContext().getService(m_packageAdminRef));
			if(!startEarly(EXEMPLARY_SETUP))
			{
				logFailure(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Missing_bundle, EXEMPLARY_SETUP)));
				return EXIT_ERROR;
			}
			if(!startEarly(SIMPLE_CONFIGURATOR_MANIPULATOR))
			{
				logFailure(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Missing_bundle,
						SIMPLE_CONFIGURATOR_MANIPULATOR)));
				return EXIT_ERROR;
			}
			if(!startEarly(FRAMEWORKADMIN_EQUINOX))
			{
				logFailure(new Status(IStatus.ERROR, Activator.ID, NLS.bind(Messages.Missing_bundle,
						FRAMEWORKADMIN_EQUINOX)));
				return EXIT_ERROR;
			}

			result = run(args, monitor);

			productInstalled = result == IApplication.EXIT_OK;
			
			if(productInstalled)
				startProduct(monitor);
		}
		catch(OperationCanceledException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			exception = e;
			throw e;
		}
		finally
		{
			stopSplash();
			
			if(exception != null || !productInstalled)
			{
				String title;
				if(productInstalled)
					title = "Materialization wizard cannot be launched";
				else
					title = "Materialization wizard cannot be installed";
				
				String errorCode;
				String problem;
				String solution;
				Throwable throwableToReport;
				
				if(exception instanceof JNLPException)
				{
					JNLPException jnlpException = (JNLPException)exception;
					errorCode = jnlpException.getErrorCode();
					problem = jnlpException.getProblem();
					solution = jnlpException.getSolution();
					throwableToReport = jnlpException.isReportable() ? exception : null;
				}
				else
				{
					errorCode = BootstrapConstants.ERROR_CODE_MATERIALIZER_INSTALL_EXCEPTION;
					problem = "An unexpected error occurred.\n\nThis could be because of intermittent network problems.";
					solution = "Try again, and if the problem persists, please report the problem.";
					throwableToReport = exception;
				}
								
				new ErrorDialog(
						getWindowIconImage(),
						title,
						problem,
						solution,
						(String)m_configProps.get(PROP_SUPPORT_EMAIL),
						"Cannot launch materializer",
						throwableToReport)
						.open();

				try
				{
					Utils.reportToServer((String)m_configProps.get(BootstrapConstants.PROP_BASE_PATH_URL), errorCode, exception);
				}
				catch(IOException e)
				{
					// no report
				}
			}
		}

		return result;
	}

	private String readArgs(String[] args, String key)
	{
		if(key == null)
			return null;
		
		for(int i = 0; i < args.length; i++)		
			if(key.equals(args[i]))
				return args[++i];

		return null;
	}

	private void startProduct(IProgressMonitor monitor) throws JNLPException
	{
		ArrayList<String> allArgs = new ArrayList<String>();
		allArgs.add(m_destination.toString() + "/eclipse"); //$NON-NLS-1$
		//allArgs.add("-consoleLog"); //$NON-NLS-1$

		allArgs.add("-data"); //$NON-NLS-1$
		allArgs.add(m_destination.removeLastSegments(1).toString());

		allArgs.add("-configURL"); //$NON-NLS-1$
		allArgs.add(m_configURL);

		allArgs.add("-syncString"); //$NON-NLS-1$
		allArgs.add(APP_LAUNCHED_SYNC_STRING);

		int startupTime = Integer.getInteger(PROP_STARTUP_TIME, DEFAULT_STARTUP_TIME).intValue();
		long popupAfter = (new Date()).getTime() + startupTime;

		allArgs.add("-popupAfter"); //$NON-NLS-1$
		allArgs.add("" + popupAfter); //$NON-NLS-1$

		allArgs.add("-vmargs");
		allArgs.add("-Xmx512m"); //$NON-NLS-1$		
		allArgs.addAll(Utils.getProxySettings());
		allArgs.addAll(Utils.parseExtraArgs(m_extraArgs));

		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		final TailLineBuffer tailOut = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES, DEFAULT_MAX_CAPTURED_LINES)
				.intValue());
		final TailLineBuffer tailErr = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES, DEFAULT_MAX_CAPTURED_LINES)
				.intValue());

		try
		{
			process = runtime.exec(allArgs.toArray(new String[allArgs.size()]));
			InputStream is = process.getInputStream();
			InputStream eis = process.getErrorStream();
			final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			final BufferedReader erd = new BufferedReader(new InputStreamReader(eis));

			new Thread()
			{
				@Override
				public void run()
				{
					String line;
					try
					{
						while((line = rd.readLine()) != null)
						{
							if(APP_LAUNCHED_SYNC_STRING.equals(line))
								m_productStarted = true;
							tailOut.writeLine(line);
						}
					}
					catch(IOException e)
					{
						System.err
								.println("Error reading from JNLP application standard output:" + e.getMessage());
					}
					finally
					{
						Utils.close(rd);
					}
				}
			}.start();

			new Thread()
			{
				@Override
				public void run()
				{
					String line;
					try
					{
						while((line = erd.readLine()) != null)
							tailErr.writeLine(line);
					}
					catch(IOException e)
					{
						System.err
								.println("Error reading from JNLP application standard error:" + e.getMessage());
					}
					finally
					{
						Utils.close(erd);
					}
				}
			}.start();
		}
		catch(IOException e)
		{
			throw new JNLPException("Cannot launch materialization wizard", "Check your system permissions and try again",
					ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION, e);
		}

		try
		{
			if(!SplashWindow.isSplashUp())
				SplashWindow.splash(null, getSplashImage(), getWindowIconImage());
			else
				SplashWindow.forceShowSplash();
				SplashWindow.setSplashImage(SplashWindow.SPLASH_IMAGE_ID);

			try
			{
				// Two seconds to start, with progressbar. The time is an
				// estimate of course.

				startupTime /= 100;
				monitor.beginTask("starting materializer", startupTime);
				while(--startupTime >= 0 && !m_productStarted)
				{
					if(monitor.isCanceled())
						throw new OperationCanceledException();

					Thread.sleep(100);
					monitor.worked(1);
				}

				monitor.done();

				int processExitValue = 0;
				boolean processTerminated = false;

				// Add some grace startup time with progress bar frozen at 100%
				// Check often if the process is still alive; if not, break the loop
				if(process != null)
				{
					int startupTimeOut = Integer.getInteger(BootstrapConstants.PROP_STARTUP_TIMEOUT, BootstrapConstants.DEFAULT_STARTUP_TIMEOUT).intValue() / 100;
					while(--startupTimeOut >= 0 && !m_productStarted)
						try
						{
							if(monitor.isCanceled())
								throw new OperationCanceledException();

							processExitValue = process.exitValue();
							processTerminated = true;
							break;
						}
						catch(IllegalThreadStateException e)
						{
							// The process is still alive, let's wait
							Thread.sleep(100);
						}
				}

				if(!m_productStarted)
				{
					if(processTerminated)
					{
						String capturedErrors = tailErr.getLinesAsString();
						String capturedOutput = tailOut.getLinesAsString();

						throw new JNLPException("Cannot launch materialization wizard" + "\nExit code: " + processExitValue
										+ (capturedErrors != null
												? "\n"	+ "captured errors:" + "\n" + capturedErrors
												: "") + (capturedOutput != null //$NON-NLS-1$
												? "\n"	+ "captured output:" + "\n" + capturedOutput
												: ""), "Read error description above",
								BootstrapConstants.ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
					}

					process.destroy();
					throw new JNLPException("Cannot launch materialization wizard within timeout",
							"Check your machine (might be too slow or too busy)",
							BootstrapConstants.ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
				}
			}
			catch(InterruptedException e)
			{
			}
		}
		catch(OperationCanceledException e)
		{
			if(process != null)
			{
				process.destroy();
			}
			throw e;
		}
		finally
		{
		}
	}

	private String getErrorURL()
	{
		String errorURL = null;

		if(m_configProps != null)
			errorURL = (String)m_configProps.get(BootstrapConstants.PROP_ERROR_URL);

		if(errorURL == null)
			errorURL = BootstrapConstants.ERROR_HELP_URL;

		return errorURL;
	}

	public void stop()
	{
		setPackageAdmin(null);
		Activator.getContext().ungetService(m_packageAdminRef);
	}

	private String toString(Properties context)
	{
		StringBuffer result = new StringBuffer();
		for(Enumeration iter = context.keys(); iter.hasMoreElements();)
		{
			String key = (String)iter.nextElement();
			result.append(key);
			result.append('=');
			result.append(context.get(key));
			if(iter.hasMoreElements())
				result.append(',');
		}
		return result.toString();
	}

	private IStatus updateRoamingProperties(IProfile profile, IProgressMonitor monitor)
	{
		// if the user didn't specify a destination path on the command-line
		// then we assume they are installing into the currently running
		// instance and we don't have anything to update
		if(m_destination == null)
			return Status.OK_STATUS;

		// if the user didn't set a profile id on the command-line this is ok if they
		// also didn't set the destination path. (handled in the case above) otherwise throw an error.
		if(m_noProfileId) // && destination != null
			return new Status(IStatus.ERROR, Activator.ID, Messages.Missing_profileid);

		// make sure that we are set to be roaming before we update the values
		if(!Boolean.valueOf(profile.getProperty(IProfile.PROP_ROAMING)).booleanValue())
			return Status.OK_STATUS;

		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		File destinationFile = m_destination.toFile();
		if(!destinationFile.equals(new File(profile.getProperty(IProfile.PROP_INSTALL_FOLDER))))
			request.setProfileProperty(IProfile.PROP_INSTALL_FOLDER, m_destination.toOSString());
		if(!destinationFile.equals(new File(profile.getProperty(IProfile.PROP_CACHE))))
			request.setProfileProperty(IProfile.PROP_CACHE, m_destination.toOSString());
		if(request.getProfileProperties().size() == 0)
			return Status.OK_STATUS;

		// otherwise we have to make a change so set the profile to be non-roaming so the
		// values don't get recalculated to the wrong thing if we are flushed from memory - we
		// will set it back later (see bug 269468)
		request.setProfileProperty(IProfile.PROP_ROAMING, "false"); //$NON-NLS-1$

		ProvisioningContext context = new ProvisioningContext(new URI[0]);
		context.setArtifactRepositories(new URI[0]);
		ProvisioningPlan result = m_planner.getProvisioningPlan(request, context, monitor);
		return PlanExecutionHelper.executePlan(result, m_engine, context, monitor);
	}

	/*
	 * Set the roaming property on the given profile.
	 */
	private IStatus setRoaming(IProfile profile, IProgressMonitor monitor)
	{
		ProfileChangeRequest request = new ProfileChangeRequest(profile);
		request.setProfileProperty(IProfile.PROP_ROAMING, "true"); //$NON-NLS-1$
		ProvisioningContext context = new ProvisioningContext(new URI[0]);
		context.setArtifactRepositories(new URI[0]);
		ProvisioningPlan result = m_planner.getProvisioningPlan(request, context, monitor);
		return PlanExecutionHelper.executePlan(result, m_engine, context, monitor);
	}

	private static URI[] getURIs(String spec)
	{
		if(spec == null)
			return null;
		String[] urlSpecs = getArrayFromString(spec, ","); //$NON-NLS-1$
		ArrayList result = new ArrayList(urlSpecs.length);
		for(int i = 0; i < urlSpecs.length; i++)
		{
			try
			{
				result.add(URIUtil.fromString(urlSpecs[i]));
			}
			catch(URISyntaxException e)
			{
				LogHelper.log(new Status(IStatus.WARNING, Activator.ID, NLS.bind(Messages.Ignored_repo, urlSpecs[i])));
			}
		}
		if(result.size() == 0)
			return null;
		return (URI[])result.toArray(new URI[result.size()]);
	}

	/**
	 * Convert a list of tokens into an array. The list separator has to be specified.
	 */
	public static String[] getArrayFromString(String list, String separator)
	{
		if(list == null || list.trim().equals("")) //$NON-NLS-1$
			return new String[0];
		List result = new ArrayList();
		for(StringTokenizer tokens = new StringTokenizer(list, separator); tokens.hasMoreTokens();)
		{
			String token = tokens.nextToken().trim();
			if(!token.equals("")) //$NON-NLS-1$
				result.add(token);
		}
		return (String[])result.toArray(new String[result.size()]);
	}

	private void logFailure(IStatus status)
	{
		FrameworkLog log = (FrameworkLog)ServiceHelper.getService(Activator.getContext(), FrameworkLog.class.getName());
		if(log != null)
			System.err.println("Application failed, log file location: " + log.getFile()); //$NON-NLS-1$
		LogHelper.log(status);
	}
}
