/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.p2.bootstrap;

import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_CORRUPTED_FILE_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_DOWNLOAD_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_JAVA_HOME_NOT_SET_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_JAVA_RUNTIME_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_LAUNCHER_NOT_FOUND_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MISSING_ARGUMENT_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_PROPERTY_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_RUNTIME_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_SITE_ROOT_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_HELP_URL;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.w3c.dom.DOMException;

/**
 * This class is supposed to be called as a JNLP application. It pops up a splash and the in will access a resource. The
 * idea is that the resource should be declared for lazy downloading and thus not triggered until someone tries to
 * access it. Since that access happens after the splash has popped up, everything should be done in the right order.
 * 
 * @author Thomas Hallgren
 */
public class Main
{
	private static final String PROP_CONFIG_URL = "configURL";

	private static final String PROP_EXTRA = "extra";

	private static final String PROP_DIRECTOR_ARCHIVE_URL = "directorArchiveURL";

	private static final String PROP_DIRECTOR_BUILD_PROPERTIES_URL = "directorBuildPropertiesURL";

	private static final String PROP_AR_URL = "arURL";

	private static final String PROP_MR_URL = "mrURL";

	private static final String PROP_ROOT_IU = "rootIU";

	public static final String PROP_SPLASH_IMAGE_BOOT = "splashImageBoot"; //$NON-NLS-1$

	public static final String PROP_SPLASH_IMAGE = "splashImage"; //$NON-NLS-1$

	public static final String PROP_WINDOW_ICON = "windowIcon"; //$NON-NLS-1$

	public static final String PROP_SERVICE_AVAILABLE = "serviceAvailable"; //$NON-NLS-1$

	public static final String PROP_SERVICE_MESSAGE = "serviceMessage"; //$NON-NLS-1$

	public static final String PROP_MAX_CAPTURED_LINES = "maxErrorLines"; //$NON-NLS-1$

	public static final int DEFAULT_MAX_CAPTURED_LINES = 1000;

	public static final String PROP_ERROR_URL = "errorURL"; //$NON-NLS-1$

	public static final String PROP_STARTUP_TIME = "startupTime"; //$NON-NLS-1$

	public static final int DEFAULT_STARTUP_TIME = 4000;

	public static final String PROP_STARTUP_TIMEOUT = "startupTimeout"; //$NON-NLS-1$

	public static final String PROP_BASE_PATH_URL = "basePathURL"; //$NON-NLS-1$

	public static final String REPORT_ERROR_VIEW = "feedback.seam"; //$NON-NLS-1$

	public static final String REPORT_ERROR_PREFIX = "Materializator-"; //$NON-NLS-1$

	public static final int DEFAULT_STARTUP_TIMEOUT = 60000;

	private static final String INSTALLER_FOLDER_NAME = "installer";

	private static String s_basePathUrl = null;

	/**
	 * This method prepares argument with proxy information which will be passed to the application. Notice that there
	 * arguments don't set system properties, they are supposed to be parsed in the application to set up the proxy
	 * rules internally.
	 * 
	 * The algorithm of getting proxy information is not ideal since the proxy selector might use non-trivial rules.
	 * However, we don't know which proxy selector implementation will handle our requests and there is no way of
	 * retrieving all the proxy rules.
	 * 
	 * Let's keep it simple - we try to use dummy addresses for the most common protocols. This will guarantee that we
	 * inherit most probable browser proxy settings.
	 * 
	 * If the rules are not guessed optimally, there should be an option in the launched application to override
	 * automatic proxy discovery with user's own rules, with the possibility to persist the settings in the application
	 * installation directory.
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	private static List<String> getProxySettings() throws URISyntaxException
	{
		List<String> args = new ArrayList<String>();
		ProxySelector proxySelector = ProxySelector.getDefault();

		for(URI uri : new URI[] { new URI("http://dummy.host.com"), new URI("https://dummy.host.com"), //$NON-NLS-1$ //$NON-NLS-2$
				new URI("ftp://dummy.host.com") }) //$NON-NLS-1$
		{
			List<Proxy> proxies = proxySelector.select(uri);
			String protocol = uri.getScheme();

			for(Proxy proxy : proxies)
			{
				if(Proxy.NO_PROXY.equals(proxy))
					break;

				SocketAddress address = proxy.address();
				if(address instanceof InetSocketAddress)
				{
					InetSocketAddress iaddr = (InetSocketAddress)address;
					args.add("-D" + protocol + ".proxyHost"); //$NON-NLS-1$ //$NON-NLS-2$
					args.add(iaddr.getHostName());
					args.add("-D" + protocol + ".proxyPort"); //$NON-NLS-1$ //$NON-NLS-2$
					args.add("" + iaddr.getPort()); //$NON-NLS-1$
					args.add("-D" + protocol + ".nonProxyHosts"); //$NON-NLS-1$ //$NON-NLS-2$
					args.add("localhost|127.0.0.1"); //$NON-NLS-1$
					break;
				}
			}
		}

		return args;
	}

	private File m_applicationData;

	private File m_installLocation;

	private String m_installerFolderName;

	private String m_errorURL = ERROR_HELP_URL;

	private boolean m_jnlpProductStarted = false;

	private Process m_process = null;

	private TailLineBuffer m_tailOut = null;

	private TailLineBuffer m_tailErr = null;

	private Image m_splashImageBoot = null;

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$"); //$NON-NLS-1$

	public static boolean isAix()
	{
		return isOs("aix"); //$NON-NLS-1$
	}

	public static boolean isMaxOSx()
	{
		return isOs("mac os x"); //$NON-NLS-1$
	}

	public static boolean isOs(String osName)
	{
		String os = System.getProperty("os.name"); //$NON-NLS-1$
		return os != null && os.length() >= osName.length()
				&& osName.equalsIgnoreCase(os.substring(0, osName.length()));
	}

	public static boolean isWindows()
	{
		return isOs("windows"); //$NON-NLS-1$
	}

	public static void launch(final String[] args, boolean fromApplet)
	{
		final Main main = new Main();
		try
		{
			ThreadGroup trustedGroup = new ThreadGroup("buckminster.bootstrap.threadgroup"); //$NON-NLS-1$

			class BootstrapThread extends Thread
			{
				Throwable m_t = null;

				public BootstrapThread(ThreadGroup group, String name)
				{
					super(group, name);
				}

				public Throwable getError()
				{
					return m_t;
				}

				@Override
				public void run()
				{
					try
					{
						main.run(args);
					}
					catch(Throwable t)
					{
						m_t = t;
					}
				}
			}

			BootstrapThread bootstrap = null;
			SimpleJNLPCacheSecurityManager cacheSecurityManager = SimpleJNLPCacheSecurityManager.getInstance();

			try
			{
				cacheSecurityManager.addTrustedThreadGroup(trustedGroup);
				bootstrap = new BootstrapThread(trustedGroup, "buckminster.bootstrap.thread"); //$NON-NLS-1$
				bootstrap.start();
				bootstrap.join();
			}
			finally
			{
				cacheSecurityManager.removeTrustedThreadGroup(trustedGroup);
			}

			if(bootstrap.getError() != null)
				throw bootstrap.getError();

			if(!fromApplet)
				Runtime.getRuntime().exit(0);
		}
		catch(OperationCanceledException e)
		{
			System.err.println(Messages.getString("warning_operation_was_canceled_by_user")); //$NON-NLS-1$

			if(!fromApplet)
				Runtime.getRuntime().exit(-1);
		}
		catch(Throwable t)
		{
			String errorCode;

			if(t instanceof JNLPException)
			{
				JNLPException e = (JNLPException)t;
				String problem = e.getMessage();
				errorCode = e.getErrorCode();

				if(e.getCause() != null)
				{
					problem += "\n\n" + Messages.getString("stack_trace_colon") + "\n" + getStackTrace(e.getCause()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}

				new ErrorDialog(main.getWindowIconImage(),
						Messages.getString("materializer_can_not_be_started"), problem, e.getSolution(), //$NON-NLS-1$
						main.getErrorURL() == null
								? null
								: main.getErrorURL() + "?errorCode=" + errorCode).open(); //$NON-NLS-1$
			}
			else
			{
				String problem = t.getMessage();
				errorCode = ERROR_CODE_RUNTIME_EXCEPTION;

				if(problem == null)
				{
					problem = Messages.getString("unknown_runtime_exception"); //$NON-NLS-1$
				}

				problem += "\n\n" + Messages.getString("stack_trace_colon") + "\n" + getStackTrace(t); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

				new ErrorDialog(main.getWindowIconImage(),
						Messages.getString("materializer_can_not_be_started"), problem, //$NON-NLS-1$
						Messages.getString("check_your_java_installation_and_try_again"), main.getErrorURL() == null //$NON-NLS-1$
								? null
								: main.getErrorURL() + "?errorCode=" + errorCode).open(); //$NON-NLS-1$
			}

			try
			{
				File errorFile = new File(main.getInstallLocation(), "error.log"); //$NON-NLS-1$
				PrintStream ps = new PrintStream(new FileOutputStream(errorFile));
				t.printStackTrace(ps);
				ps.close();
			}
			catch(Throwable ignore)
			{
			}

			try
			{
				reportToServer(errorCode);
			}
			catch(IOException e)
			{
				// no report
			}

			if(!fromApplet)
				Runtime.getRuntime().exit(-1);
		}
	}

	/**
	 * Standard entry point for launching the application from command line or with java web start
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args, false);
	}

	private static String findJavaExe() throws JNLPException
	{
		String javaHome = System.getProperty("java.home"); //$NON-NLS-1$
		if(javaHome == null)
		{
			throw new JNLPException(
					Messages.getString("system_property_0_is_not_set", "java.home"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages
							.getString("set_the_system_property_which_should_point_to_java_home_directory_and_try_again"), //$NON-NLS-1$
					ERROR_CODE_JAVA_HOME_NOT_SET_EXCEPTION);
		}

		File javaBin = new File(javaHome, "bin"); //$NON-NLS-1$
		File javaExe = new File(javaBin, isWindows()
				? "javaw.exe" //$NON-NLS-1$
				: "java"); //$NON-NLS-1$

		if(!javaExe.exists())
		{
			throw new JNLPException(
					Messages.getString("unable_to_locate_java_runtime"), Messages.getString("check_java_installation_and_try_again"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
		}

		return javaExe.toString();
	}

	private static String getStackTrace(Throwable e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.close();

		return sw.toString();
	}

	private static void reportToServer(String errorCode) throws IOException
	{
		if(s_basePathUrl == null)
			return;

		String javaVersion = URLEncoder.encode(System.getProperty("java.version"), "UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
		String javaVendor = URLEncoder.encode(System.getProperty("java.vendor"), "UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$

		String string = s_basePathUrl + REPORT_ERROR_VIEW + "?errorCode=" + REPORT_ERROR_PREFIX + errorCode + //$NON-NLS-1$
				"&javaVersion=" + javaVersion + "&javaVendor=" + javaVendor; //$NON-NLS-1$ //$NON-NLS-2$
		URL feedbackURL = new URL(string);
		// ping feedback view to report it to apache log
		InputStream is = feedbackURL.openStream();

		byte[] copyBuf = new byte[8192];
		while(is.read(copyBuf) > 0)
			;

		is.close();
	}

	private Image m_splashImage = null;

	private Image m_windowIconImage = null;

	public String findEclipseLauncher(String applicationFolder) throws JNLPException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find
		// it.
		//
		File siteRoot = new File(applicationFolder);
		if(!siteRoot.isDirectory())
		{
			throw new JNLPException(
					Messages.getString("unable_to_locate_the_site_root_of_0", getInstallLocation()), //$NON-NLS-1$
					Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_SITE_ROOT_EXCEPTION); //$NON-NLS-1$
		}

		File pluginsDir = new File(siteRoot, "plugins"); //$NON-NLS-1$
		String[] names = pluginsDir.list();
		if(names == null)
		{
			throw new JNLPException(
					Messages.getString("_0_is_not_a_directory", pluginsDir), Messages.getString("report_the_error_and_try_later"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_DIRECTORY_EXCEPTION);
		}

		String found = null;
		String foundVer = null;
		int idx = names.length;
		while(--idx >= 0)
		{
			String name = names[idx];
			java.util.regex.Matcher matcher = s_launcherPattern.matcher(name);
			if(matcher.matches())
			{
				String version = matcher.group(1);
				if(foundVer == null || foundVer.compareTo(version) > 0)
				{
					found = name;
					foundVer = version;
				}
			}
		}
		File launcher;
		if(found == null)
		{
			// Are we building against an older platform perhaps?
			//
			launcher = new File(siteRoot, "startup.jar"); //$NON-NLS-1$
			if(!launcher.exists())
			{
				throw new JNLPException(
						Messages.getString("can_not_find_file_colon") + pluginsDir //$NON-NLS-1$
								+ "org.eclipse.equinox.launcher_<version>.jar", //$NON-NLS-1$
						Messages.getString("clear_your_java_cache_browser_cache_and_try_again"), ERROR_CODE_LAUNCHER_NOT_FOUND_EXCEPTION); //$NON-NLS-1$
			}
		}
		else
			launcher = new File(pluginsDir, found);

		return launcher.toString();
	}

	public synchronized File getApplicationDataLocation() throws JNLPException
	{
		if(m_applicationData == null)
		{
			if(isWindows())
			{
				String appDataEnv = System.getenv("APPDATA"); //$NON-NLS-1$

				if(appDataEnv != null)
					m_applicationData = new File(appDataEnv);
				else
				{
					String userHome = System.getProperty("user.home"); //$NON-NLS-1$
					if(userHome != null)
						m_applicationData = new File(userHome, "Application Data"); //$NON-NLS-1$
				}
			}
			else
			{
				String userHome = System.getProperty("user.home"); //$NON-NLS-1$
				if(userHome != null)
					m_applicationData = new File(userHome);
			}
		}
		return m_applicationData;
	}

	public synchronized File getInstallLocation() throws JNLPException
	{
		if(m_installLocation == null)
		{
			if(getApplicationDataLocation() != null)
			{
				if(isWindows())
					m_installLocation = new File(getApplicationDataLocation(), "buckminster"); //$NON-NLS-1$
				else
					m_installLocation = new File(getApplicationDataLocation(), ".buckminster"); //$NON-NLS-1$
			}
			else
			{
				try
				{
					m_installLocation = File.createTempFile("bucky", ".site"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				catch(IOException e)
				{
					throw new JNLPException(
							Messages.getString("can_not_create_a_temp_file"), //$NON-NLS-1$
							Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
				}
			}
			m_installLocation.mkdirs();
		}

		return m_installLocation;
	}

	public String getWorkspaceDir() throws JNLPException
	{
		// have the workspace location the same as the product installation
		return getInstallLocation().getAbsolutePath();
	}

	public void installProduct(String applicationFolder, Map<String, String> inputArgMap, List<String> proxySettings,
			final ProgressFacade monitor) throws JNLPException
	{
		String launcherFile = findEclipseLauncher(applicationFolder);
		String javaExe = findJavaExe();

		ArrayList<String> allArgs = new ArrayList<String>();
		allArgs.add(javaExe);
		allArgs.add("-jar"); //$NON-NLS-1$
		allArgs.add(launcherFile);
		allArgs.add("director");
		allArgs.add("-ar");
		allArgs.add(inputArgMap.get(PROP_AR_URL));
		allArgs.add("-mr");
		allArgs.add(inputArgMap.get(PROP_MR_URL));
		allArgs.add("-dest");
		allArgs.add(getInstallerFolderName());
		allArgs.add("-bundlepool");
		allArgs.add(getInstallerFolderName());
		allArgs.add("-profile");
		allArgs.add("P2-materializer");
		allArgs.add("-installIU");
		allArgs.add(inputArgMap.get(PROP_ROOT_IU));
		allArgs.add("-vmargs");
		allArgs.add("-Xmx512m"); //$NON-NLS-1$
		allArgs.add("-Declipse.p2.data.area=");
		allArgs.add(getInstallerFolderName() + "/p2");
		allArgs.addAll(proxySettings);

		try
		{
			allArgs.addAll(getProxySettings());
		}
		catch(URISyntaxException e)
		{
			throw new JNLPException(
					Messages.getString("unable_to_detect_proxy_settings"), Messages.getString("report_the_problem"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
		}

		//allArgs.add("-consoleLog"); //$NON-NLS-1$

		Runtime runtime = Runtime.getRuntime();
		final TailLineBuffer tailOut = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES,
				DEFAULT_MAX_CAPTURED_LINES).intValue());
		final TailLineBuffer tailErr = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES,
				DEFAULT_MAX_CAPTURED_LINES).intValue());

		try
		{
			final Process p2Installprocess = runtime.exec(allArgs.toArray(new String[allArgs.size()]));
			InputStream is = p2Installprocess.getInputStream();
			InputStream eis = p2Installprocess.getErrorStream();
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
							tailOut.writeLine(line);
					}
					catch(IOException e)
					{
						System.err
								.println(Messages
										.getString("error_reading_from_director_application_standard_output_colon") + e.getMessage()); //$NON-NLS-1$
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
								.println(Messages
										.getString("error_reading_from_director_application_standard_error_colon") + e.getMessage()); //$NON-NLS-1$
					}
					finally
					{
						Utils.close(erd);
					}
				}
			}.start();

			final File pluginsFolder = new File(getInstallerFolderName(), "plugins");

			Thread monitorThread = new Thread()
			{
				@Override
				public void run()
				{
					int pluginsCount = 100;
					try
					{
						monitor.setTask(Messages.getString("installing materialization wizard"), pluginsCount); //$NON-NLS-1$
						int loopCounter = 0;
						int lastBundleCount = 0;

						while(true)
						{
							loopCounter++;

							if(monitor.isCanceled())
							{
								p2Installprocess.destroy();
								break;
							}

							int currentBundleCount = pluginsFolder.list() == null
									? 0
									: pluginsFolder.list().length;

							// if(loopCounter > 120 && currentBundleCount == 0)
							// {
							// throw new JNLPException("", "", "");
							// }

							monitor.taskIncrementalProgress(currentBundleCount - lastBundleCount);
							lastBundleCount = currentBundleCount;

							try
							{
								p2Installprocess.exitValue();
								break;
							}
							catch(IllegalThreadStateException e)
							{
								// the process has not finished yet
								Thread.sleep(250);
							}
						}
					}
					catch(InterruptedException e)
					{
					}
					finally
					{
						monitor.taskDone();
					}
				}
			};

			monitorThread.start();

			try
			{
				p2Installprocess.waitFor();
				monitorThread.join();
			}
			catch(InterruptedException e)
			{
				throw new JNLPException(
						Messages.getString("director_application_was_interrupted"), Messages.getString("try_again"), //$NON-NLS-1$ //$NON-NLS-2$
						ERROR_CODE_DOWNLOAD_EXCEPTION, e);
			}

			if(p2Installprocess.exitValue() != 0)
			{
				if(monitor.isCanceled())
					throw new JNLPException(
							Messages.getString("director_application_was_interrupted"), Messages.getString("try_again"), //$NON-NLS-1$ //$NON-NLS-2$
							ERROR_CODE_DOWNLOAD_EXCEPTION);

				String capturedErrors = tailErr.getLinesAsString();
				String capturedOutput = tailOut.getLinesAsString();

				throw new JNLPException(
						Messages.getString("materializer_was_not_installed_correctly") + "\nExit code: " + p2Installprocess.exitValue() //$NON-NLS-1$ //$NON-NLS-2$
								+ (capturedErrors != null
										? "\n" + Messages.getString("captured_errors_colon") + "\n" + capturedErrors //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
										: "") + (capturedOutput != null //$NON-NLS-1$
										? "\n" + Messages.getString("captured_output_colon") + "\n" + capturedOutput //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
										: ""), Messages.getString("read_error_description_above"), //$NON-NLS-1$ //$NON-NLS-2$
						ERROR_CODE_CORRUPTED_FILE_EXCEPTION);
			}
		}
		catch(IOException e)
		{
			throw new JNLPException(
					Messages.getString("can_not_read_materialization_wizard_resource"), Messages.getString("check_your_internet_connection_and_try_again"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_REMOTE_IO_EXCEPTION, e);
		}
	}

	public void startProduct(String applicationFolder, Map<String, String> inputArgMap, List<String> proxySettings,
			int startupTime) throws JNLPException
	{
		ArrayList<String> allArgs = new ArrayList<String>();
		allArgs.add(applicationFolder + "/eclipse"); //$NON-NLS-1$
		//allArgs.add("-consoleLog"); //$NON-NLS-1$

		String wsDir = getWorkspaceDir();
		if(wsDir != null)
		{
			allArgs.add("-data"); //$NON-NLS-1$
			allArgs.add(wsDir);
		}
		allArgs.add("-configURL"); //$NON-NLS-1$
		allArgs.add(inputArgMap.get(PROP_CONFIG_URL));

		final String syncString = "sync info: application launched"; //$NON-NLS-1$
		allArgs.add("-syncString"); //$NON-NLS-1$
		allArgs.add(syncString);

		long popupAfter = (new Date()).getTime() + startupTime;

		allArgs.add("-popupAfter"); //$NON-NLS-1$
		allArgs.add("" + popupAfter); //$NON-NLS-1$

		allArgs.add("-vmargs");
		allArgs.add("-Xmx512m"); //$NON-NLS-1$		
		allArgs.addAll(proxySettings);
		allArgs.addAll(parseExtraArgs(inputArgMap.get(PROP_EXTRA)));

		Runtime runtime = Runtime.getRuntime();
		m_tailOut = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES, DEFAULT_MAX_CAPTURED_LINES)
				.intValue());
		m_tailErr = new TailLineBuffer(Integer.getInteger(PROP_MAX_CAPTURED_LINES, DEFAULT_MAX_CAPTURED_LINES)
				.intValue());

		try
		{
			m_process = runtime.exec(allArgs.toArray(new String[allArgs.size()]));
			InputStream is = m_process.getInputStream();
			InputStream eis = m_process.getErrorStream();
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
							if(syncString.equals(line))
								m_jnlpProductStarted = true;
							m_tailOut.writeLine(line);
						}
					}
					catch(IOException e)
					{
						System.err
								.println(Messages
										.getString("error_reading_from_JNLP_application_standard_output_colon") + e.getMessage()); //$NON-NLS-1$
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
							m_tailErr.writeLine(line);
					}
					catch(IOException e)
					{
						System.err
								.println(Messages.getString("error_reading_from_JNLP_application_standard_error_colon") + e.getMessage()); //$NON-NLS-1$
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
			throw new JNLPException(
					Messages.getString("can_not_run_materializer_wizard"), Messages.getString("check_your_system_permissions_and_try_again"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION, e);
		}
	}

	void run(String[] args) throws JNLPException, DOMException, OperationCanceledException
	{
		try
		{
			Map<String, String> inputArgMap = loadInputMap(args);
			Properties configProps = loadConfigProperties(inputArgMap);

			s_basePathUrl = configProps.getProperty(PROP_BASE_PATH_URL);

			String tmp = configProps.getProperty(PROP_ERROR_URL);

			if(tmp != null)
			{
				m_errorURL = tmp;
			}

			tmp = configProps.getProperty(PROP_SERVICE_AVAILABLE);

			boolean serviceAvailable = true;

			if(tmp != null && "false".equalsIgnoreCase(tmp)) //$NON-NLS-1$
			{
				serviceAvailable = false;
			}

			String serviceMessage = configProps.getProperty(PROP_SERVICE_MESSAGE);

			if(!serviceAvailable || (serviceMessage != null && serviceMessage.length() > 0))
			{
				new ServiceDialog(getWindowIconImage(), serviceMessage, serviceAvailable).open();

				if(!serviceAvailable)
				{
					return;
				}
			}

			m_splashImageBoot = Utils.createImage(configProps.getProperty(PROP_SPLASH_IMAGE_BOOT));
			m_splashImage = Utils.createImage(configProps.getProperty(PROP_SPLASH_IMAGE));
			m_windowIconImage = Utils.createImage(configProps.getProperty(PROP_WINDOW_ICON));

			final ProgressFacade monitor = SplashWindow.getDownloadServiceListener();

			/*
			 * // Uncomment to get two testloops of progress - do not use in production // test loop - uncomment to test
			 * splash progress without actually // running under Java Web Start - i.e. keep this comment in the code.
			 * DownloadServiceListener xdsl = SplashWindow.getDownloadServiceListener(); for(int i = 0; i < 101; i++) {
			 * xdsl.progress(null,"", 0L, 0L, i); Thread.sleep(50); } for(int i = 0; i < 51; i++) {
			 * xdsl.progress(null,"", 0L, 0L, i); Thread.sleep(50); } // For debugging purposes - obtain data from the
			 * splash and put them in user's clipboard // SplashWindow.disposeSplash();
			 * System.err.print(SplashWindow.getDebugString());
			 */
			/*
			 * try { // Assume we don't have an installed product // DownloadService ds =
			 * (DownloadService)ServiceManager.lookup("javax.jnlp.DownloadService"); // DownloadServiceListener dsl =
			 * ds.getDefaultProgressWindow(); if(!ds.isPartCached(PRODUCT)) { if(!SplashWindow.splashIsUp() &&
			 * (splashImageBootData != null || splashImageData != null)) SplashWindow.splash(m_splashImageBoot,
			 * m_splashImage, m_windowIconImage); // SplashWindow.disposeSplash(); ds.loadPart(PRODUCT, monitor); //
			 * SplashWindow.splash(splashData); productUpdated = true; } } catch(Exception e) { throw new
			 * JNLPException("Can not download materialization wizard", "Check disk space, system permissions, internet
			 * connection and try again", ERROR_CODE_DOWNLOAD_EXCEPTION, e); }
			 */

			DirectorInstaller installer = new DirectorInstaller(getInstallLocation());

			if(!SplashWindow.splashIsUp())
			{
				SplashWindow.splash(m_splashImageBoot, m_splashImage, m_windowIconImage);
			}

			try
			{
				installer.installDirector(inputArgMap.get(PROP_DIRECTOR_ARCHIVE_URL), inputArgMap
						.get(PROP_DIRECTOR_BUILD_PROPERTIES_URL), monitor);
			}
			catch(OperationCanceledException e)
			{
				installer.removeDirector();
				throw e;
			}
			catch(CorruptedFileException e)
			{
				throw new JNLPException(Messages.getString("director_application_contains_a_corrupted_file"), //$NON-NLS-1$
						Messages.getString("trigger_the_materialization_again"), ERROR_CODE_CORRUPTED_FILE_EXCEPTION); //$NON-NLS-1$
			}

			// NOTE: keep this to enable debugging - uncomment in splash window too. Stores the debug data
			// in the clipboard.
			// ClipboardService clipservice = (ClipboardService)ServiceManager.lookup("javax.jnlp.ClipboardService");
			// StringSelection ss = new StringSelection(SplashWindow.getDebugString());
			// clipservice.setContents(ss);

			List<String> proxySettings;
			try
			{
				proxySettings = getProxySettings();
			}
			catch(URISyntaxException e)
			{
				throw new JNLPException(
						Messages.getString("unable_to_detect_proxy_settings"), Messages.getString("report_the_problem"), //$NON-NLS-1$ //$NON-NLS-2$
						ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
			}

			int startupTime = Integer.getInteger(PROP_STARTUP_TIME, DEFAULT_STARTUP_TIME).intValue();

			installProduct(installer.getDirectorFolder().toString(), inputArgMap, proxySettings, monitor);
			startProduct(getInstallerFolderName(), inputArgMap, proxySettings, startupTime);
			try
			{
				// Two seconds to start, with progressbar. The time is an
				// estimate of course.
				//
				if(m_splashImage != null)
				{
					// Switch splash screen
					//
					if(!SplashWindow.splashIsUp())
						SplashWindow.splash(null, m_splashImage, m_windowIconImage);
					else
						SplashWindow.setSplashImage(SplashWindow.SPLASH_IMAGE_ID);
				}

				startupTime /= 100;
				monitor.setTask(Messages.getString("starting"), startupTime); //$NON-NLS-1$
				while(--startupTime >= 0 && !m_jnlpProductStarted)
				{
					monitor.checkCanceled();
					Thread.sleep(100);
					monitor.taskIncrementalProgress(1);
				}

				monitor.taskDone();

				int processExitValue = 0;
				boolean processTerminated = false;

				// Add some grace startup time with progress bar frozen at 100%
				// Check often if the process is still alive; if not, break the loop
				if(m_process != null)
				{
					int startupTimeOut = Integer.getInteger(PROP_STARTUP_TIMEOUT, DEFAULT_STARTUP_TIMEOUT).intValue() / 100;
					while(--startupTimeOut >= 0 && !m_jnlpProductStarted)
						try
						{
							monitor.checkCanceled();
							processExitValue = m_process.exitValue();
							processTerminated = true;
							break;
						}
						catch(IllegalThreadStateException e)
						{
							// The process is still alive, let's wait
							Thread.sleep(100);
						}
				}

				if(!m_jnlpProductStarted)
				{
					if(processTerminated)
					{
						String capturedErrors = m_tailErr.getLinesAsString();
						String capturedOutput = m_tailOut.getLinesAsString();

						throw new JNLPException(
								Messages.getString("unable_to_launch_materializer_colon") + "\nExit code: " + processExitValue //$NON-NLS-1$ //$NON-NLS-2$
										+ (capturedErrors != null
												? "\n"	+ Messages.getString("captured_errors_colon") + "\n" + capturedErrors //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
												: "") + (capturedOutput != null //$NON-NLS-1$
												? "\n"	+ Messages.getString("captured_output_colon") + "\n" + capturedOutput //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
												: ""), Messages.getString("read_error_description_above"), //$NON-NLS-1$ //$NON-NLS-2$
								ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
					}

					m_process.destroy();
					throw new JNLPException(Messages.getString("unable_to_launch_materializer_within_timeout"), //$NON-NLS-1$
							Messages.getString("check_your_machine_might_be_too_slow_or_too_busy"), //$NON-NLS-1$
							ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
				}
			}
			catch(InterruptedException e)
			{
			}
		}
		catch(OperationCanceledException e)
		{
			if(m_process != null)
			{
				m_process.destroy();
			}
			throw e;
		}
		finally
		{
			SplashWindow.disposeSplash();
		}
	}

	private String getErrorURL()
	{
		return m_errorURL;
	}

	private String getInstallerFolderName() throws JNLPException
	{
		if(m_installerFolderName == null)
		{
			m_installerFolderName = getInstallLocation().toString() + "/" + INSTALLER_FOLDER_NAME;
		}

		return m_installerFolderName;
	}

	private Image getWindowIconImage()
	{
		return m_windowIconImage;
	}

	private Properties loadConfigProperties(Map<String, String> inputArgMap) throws JNLPException
	{
		String configURL = inputArgMap.get(PROP_CONFIG_URL);

		if(configURL == null)
		{
			throw new JNLPException(Messages.getString("missing_required_argument_configURL_URL_to_config_properties"), //$NON-NLS-1$
					Messages.getString("report_the_error_and_try_later"), ERROR_CODE_MISSING_ARGUMENT_EXCEPTION); //$NON-NLS-1$
		}

		InputStream propStream = null;
		OutputStream localStream = null;
		try
		{
			URL propertiesURL = null;

			try
			{
				propertiesURL = new URL(configURL.trim());
			}
			catch(MalformedURLException e)
			{
				throw new JNLPException(
						Messages.getString("can_not_read_URL_to_config_properties"), Messages.getString("report_the_error_and_try_later"), //$NON-NLS-1$ //$NON-NLS-2$
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
			}
			if(!"file".equals(propertiesURL)) //$NON-NLS-1$
			{
				// Copy to local file. The installer that we bootstrap will need
				// this too and we don't want an extra http GET just to get it.
				//
				int count;
				byte[] bytes = new byte[8192];
				ByteArrayOutputStream bld = new ByteArrayOutputStream();
				try
				{
					propStream = propertiesURL.openStream();
					while((count = propStream.read(bytes, 0, bytes.length)) > 0)
						bld.write(bytes, 0, count);

					propStream.close();
				}
				catch(IOException e)
				{
					throw new JNLPException(
							Messages.getString("unable_to_get_information_about_the_materialization"), //$NON-NLS-1$
							Messages.getString("check_your_internet_connection_and_try_again"), ERROR_CODE_PROPERTY_IO_EXCEPTION, e); //$NON-NLS-1$
				}
				bytes = bld.toByteArray();
				propStream = new ByteArrayInputStream(bytes);

				// Create the local file
				//
				File localTemp = new File(getInstallLocation(), "temp"); //$NON-NLS-1$
				if(!(localTemp.exists() || localTemp.mkdirs()))
					throw new JNLPException(
							Messages.getString("unable_to_create_directory") + localTemp, //$NON-NLS-1$
							Messages.getString("check_your_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION); //$NON-NLS-1$

				File localProps;
				try
				{
					localProps = File.createTempFile("config", "properties", localTemp); //$NON-NLS-1$ //$NON-NLS-2$
				}
				catch(IOException e)
				{
					throw new JNLPException(
							Messages.getString("can_not_create_a_temp_file"), //$NON-NLS-1$
							Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
				}
				try
				{
					localStream = new FileOutputStream(localProps);
					localStream.write(bytes);
				}
				catch(IOException e)
				{
					throw new JNLPException(
							Messages.getString("can_not_write_to_a_temp_file"), //$NON-NLS-1$
							Messages.getString("check_your_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
				}

				// Replace the configURL option value in the argument array with a pointer
				// to the local file. We convert to URI first since the toURL() on File
				// is broken (it doesn't convert spaces correctly).
				//
				try
				{
					inputArgMap.put(PROP_CONFIG_URL, localProps.toURI().toURL().toExternalForm());
				}
				catch(MalformedURLException e)
				{
					throw new JNLPException(
							Messages.getString("can_not_read_from_a_temp_file"), //$NON-NLS-1$
							Messages.getString("check_your_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
				}
			}
			else
				try
				{
					propStream = new BufferedInputStream(propertiesURL.openStream());
				}
				catch(IOException e)
				{
					throw new JNLPException(
							Messages.getString("unable_to_get_information_about_the_materialization"), //$NON-NLS-1$
							Messages.getString("check_your_internet_connection_and_try_again"), ERROR_CODE_PROPERTY_IO_EXCEPTION, e); //$NON-NLS-1$
				}

			Properties props = new Properties();
			try
			{
				props.load(propStream);
			}
			catch(IOException e)
			{
				throw new JNLPException(Messages.getString("unable_to_read_materialization_information"), //$NON-NLS-1$
						Messages.getString("check_your_system_permissions_internet_connection_and_try_again"), //$NON-NLS-1$
						ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
			}
			return props;
		}
		finally
		{
			Utils.close(propStream);
			Utils.close(localStream);
		}
	}

	private Map<String, String> loadInputMap(String[] args)
	{
		Map<String, String> prop = new HashMap<String, String>();
		for(int i = 0; i < (args.length / 2); i++)
		{
			prop.put(args[2 * i].replaceAll("^-", ""), args[2 * i + 1]);
		}

		return prop;
	}

	/**
	 * Converts a single -extra string parameter into a list of parameters. Parameters are delimited by space. Example:
	 * -extra "-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y"
	 * 
	 * @param args
	 * @return
	 */
	private List<String> parseExtraArgs(String extraArgsString)
	{
		if(extraArgsString != null && !"null".equals(extraArgsString)) //$NON-NLS-1$
		{
			String[] extraArgs = extraArgsString.split(" "); //$NON-NLS-1$

			return Arrays.asList(extraArgs);
		}

		return Collections.emptyList();
	}

}
