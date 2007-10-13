/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import static org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.buckminster.jnlp.cache.SimpleJNLPCacheSecurityManager;
import org.eclipse.buckminster.jnlp.cache.SimpleJNLPCache;
import org.eclipse.buckminster.jnlp.cache.SimpleJNLPCacheAdapter;
import org.eclipse.buckminster.jnlp.cache.Utils;
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
	// The package of the product.zip must correspond with the package declaration
	// in the product.jnlp file.
	//
	private static final String PRODUCT_INSTALLER_CLASS = "org.eclipse.buckminster.jnlp.product.ProductInstaller";

	// private static final String PRODUCT = "product";

	public static final String PROP_SPLASH_IMAGE_BOOT = "splashImageBoot";

	public static final String PROP_SPLASH_IMAGE = "splashImage";

	public static final String PROP_WINDOW_ICON = "windowIcon";

	public static final String PROP_SERVICE_AVAILABLE = "serviceAvailable";

	public static final String PROP_SERVICE_MESSAGE = "serviceMessage";

	public static final String PROP_MAX_CAPTURED_LINES = "maxErrorLines";

	public static final int DEFAULT_MAX_CAPTURED_LINES = 1000;

	public static final String PROP_ERROR_URL = "errorURL";

	public static final String PROP_STARTUP_TIME = "startupTime";

	public static final int DEFAULT_STARTUP_TIME = 4000;

	public static final String PROP_STARTUP_TIMEOUT = "startupTimeout";

	public static final int DEFAULT_STARTUP_TIMEOUT = 30000;

	private File m_applicationData;

	private File m_installLocation;

	private String m_errorURL = ERROR_HELP_URL;

	private boolean m_jnlpProductStarted = false;

	private Process m_process = null;

	private TailLineBuffer m_tailOut = null;

	private TailLineBuffer m_tailErr = null;

	private Image m_splashImageBoot = null;

	private Image m_splashImage = null;

	private Image m_windowIconImage = null;

	/**
	 * Standard entry point for launching the application from command line or with java web start
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args, false);
	}

	public static void launch(final String[] args, boolean fromApplet)
	{
		final Main main = new Main();
		try
		{
			ThreadGroup trustedGroup = new ThreadGroup("buckminster.bootstrap.threadgroup");

			class BootstrapThread extends Thread
			{
				Throwable m_t = null;

				public BootstrapThread(ThreadGroup group, String name)
				{
					super(group, name);
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

				public Throwable getError()
				{
					return m_t;
				}
			}

			BootstrapThread bootstrap = null;
			SimpleJNLPCacheSecurityManager cacheSecurityManager = SimpleJNLPCacheSecurityManager.getInstance();

			try
			{
				cacheSecurityManager.addTrustedThreadGroup(trustedGroup);
				bootstrap = new BootstrapThread(trustedGroup, "buckminster.bootstrap.thread");
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
			System.err.println("Warning: Operation was canceled by user");
		}
		catch(Throwable t)
		{

			if(t instanceof JNLPException)
			{
				JNLPException e = (JNLPException)t;
				String problem = e.getMessage();

				if(e.getCause() != null)
				{
					problem += "\n\nStack Trace:\n" + getStackTrace(e.getCause());
				}

				new ErrorDialog(main.getWindowIconImage(), "Materializer can not be started", problem, e.getSolution(),
						main.getErrorURL() == null
								? null
								: main.getErrorURL() + "?errorCode=" + e.getErrorCode()).open();
			}
			else
			{
				String problem = t.getMessage();

				if(problem == null)
				{
					problem = "Unknonw runtime exception";
				}

				problem += "\n\nStack Trace:\n" + getStackTrace(t);

				new ErrorDialog(main.getWindowIconImage(), "Materializer can not be started", problem,
						"Check your java installation and try again", main.getErrorURL() == null
								? null
								: main.getErrorURL() + "?errorCode=" + ERROR_CODE_RUNTIME_EXCEPTION).open();
			}

			try
			{
				File errorFile = new File(main.getInstallLocation(), "error.log");
				PrintStream ps = new PrintStream(new FileOutputStream(errorFile));
				t.printStackTrace(ps);
				ps.close();
			}
			catch(Throwable ignore)
			{
			}

			if(!fromApplet)
				Runtime.getRuntime().exit(-1);
		}
	}

	private static String getStackTrace(Throwable e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.close();

		return sw.toString();
	}

	public synchronized File getApplicationDataLocation() throws JNLPException
	{
		if(m_applicationData == null)
		{
			if(isWindows())
			{
				String appDataEnv = System.getenv("APPDATA");

				if(appDataEnv != null)
					m_applicationData = new File(appDataEnv);
				else
				{
					String userHome = System.getProperty("user.home");
					if(userHome != null)
						m_applicationData = new File(userHome, "Application Data");
				}
			}
			else
			{
				String userHome = System.getProperty("user.home");
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
					m_installLocation = new File(getApplicationDataLocation(), "buckminster");
				else
					m_installLocation = new File(getApplicationDataLocation(), ".buckminster");
			}
			else
			{
				try
				{
					m_installLocation = File.createTempFile("bucky", ".site");
				}
				catch(IOException e)
				{
					throw new JNLPException("Can not create a temp file",
							"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION, e);
				}
			}
			m_installLocation.mkdirs();
		}

		return m_installLocation;
	}

	public static boolean isWindows()
	{
		return isOs("windows");
	}

	public static boolean isAix()
	{
		return isOs("aix");
	}

	public static boolean isMaxOSx()
	{
		return isOs("mac os x");
	}

	public static boolean isOs(String osName)
	{
		String os = System.getProperty("os.name");
		return os != null && os.length() >= osName.length()
				&& osName.equalsIgnoreCase(os.substring(0, osName.length()));
	}

	public String getWorkspaceDir() throws JNLPException
	{
		// have the workspace location the same as the product installation
		return getInstallLocation().getAbsolutePath();
	}

	public File getCacheLocation() throws JNLPException
	{
		return new File(getInstallLocation(), "cache");
	}

	private String getJnlpRef(String[] args) throws JNLPException
	{
		for(int idx = 0; idx < args.length; ++idx)
		{
			if("-productJNLP".equals(args[idx]))
			{
				if(++idx < args.length)
				{
					String arg = args[idx];
					if(arg != null && arg.trim().length() > 0)
					{
						return arg;
					}
				}
				break;
			}
		}

		throw new JNLPException("Missing required argument -productJNLP <URL to product JNLP descriptor>",
				"Report the error and try later", ERROR_CODE_MISSING_ARGUMENT_EXCEPTION);
	}

	private Properties parseArguments(String[] args) throws JNLPException
	{
		int urlIdx = -1;
		for(int idx = 0; idx < args.length; ++idx)
		{
			if("-configURL".equals(args[idx]))
			{
				if(++idx < args.length)
				{
					String arg = args[idx];
					if(arg != null && arg.trim().length() > 0)
					{
						urlIdx = idx;
						break;
					}
				}
				break;
			}
		}

		if(urlIdx == -1)
		{
			throw new JNLPException("Missing required argument -configURL <URL to config properties>",
					"Report the error and try later", ERROR_CODE_MISSING_ARGUMENT_EXCEPTION);
		}

		InputStream propStream = null;
		OutputStream localStream = null;
		try
		{
			URL propertiesURL = null;

			try
			{
				propertiesURL = new URL(args[urlIdx].trim());
			}
			catch(MalformedURLException e)
			{
				throw new JNLPException("Can not read URL to config properties", "Report the error and try later",
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
			}
			if(!"file".equals(propertiesURL))
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
					throw new JNLPException("Unable to get information about the materialization",
							"Check your internet connection and try again", ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
				}
				bytes = bld.toByteArray();
				propStream = new ByteArrayInputStream(bytes);

				// Create the local file
				//
				File localTemp = new File(getInstallLocation(), "temp");
				if(!(localTemp.exists() || localTemp.mkdirs()))
					throw new JNLPException("Unable to create directory " + localTemp,
							"Check your system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION);

				File localProps;
				try
				{
					localProps = File.createTempFile("config", "properties", localTemp);
				}
				catch(IOException e)
				{
					throw new JNLPException("Can not create a temp file",
							"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION, e);
				}
				try
				{
					localStream = new FileOutputStream(localProps);
					localStream.write(bytes);
				}
				catch(IOException e)
				{
					throw new JNLPException("Can not write to a temp file",
							"Check your system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION, e);
				}

				// Replace the configURL option value in the argument array with a pointer
				// to the local file. We convert to URI first since the toURL() on File
				// is broken (it doesn't convert spaces correctly).
				//
				try
				{
					args[urlIdx] = localProps.toURI().toURL().toExternalForm();
				}
				catch(MalformedURLException e)
				{
					throw new JNLPException("Can not read from a temp file",
							"Check your system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION, e);
				}
			}
			else
				try
				{
					propStream = new BufferedInputStream(propertiesURL.openStream());
				}
				catch(IOException e)
				{
					throw new JNLPException("Unable to get information about the materialization",
							"Check your internet connection and try again", ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
				}

			Properties props = new Properties();
			try
			{
				props.load(propStream);
			}
			catch(IOException e)
			{
				throw new JNLPException("Unable to read materialization information",
						"Check your system permissions, internet connection and try again",
						ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
			}
			return props;
		}
		finally
		{
			close(propStream);
			close(localStream);
		}
	}

	void run(String[] args) throws JNLPException, DOMException, OperationCanceledException
	{
		try
		{
			Properties props = parseArguments(args);

			String tmp = props.getProperty(PROP_ERROR_URL);

			if(tmp != null)
			{
				m_errorURL = tmp;
			}

			tmp = props.getProperty(PROP_SERVICE_AVAILABLE);

			boolean serviceAvailable = true;

			if(tmp != null && "false".equalsIgnoreCase(tmp))
			{
				serviceAvailable = false;
			}

			String serviceMessage = props.getProperty(PROP_SERVICE_MESSAGE);

			if(!serviceAvailable || (serviceMessage != null && serviceMessage.length() > 0))
			{
				new ServiceDialog(getWindowIconImage(), serviceMessage, serviceAvailable).open();

				if(!serviceAvailable)
				{
					return;
				}
			}

			int startupTime = Integer.getInteger(PROP_STARTUP_TIME, DEFAULT_STARTUP_TIME).intValue();
			byte[] splashImageBootData = loadData(props.getProperty(PROP_SPLASH_IMAGE_BOOT));
			byte[] splashImageData = loadData(props.getProperty(PROP_SPLASH_IMAGE));
			byte[] windowIconData = loadData(props.getProperty(PROP_WINDOW_ICON));

			m_splashImageBoot = splashImageBootData != null
					? Toolkit.getDefaultToolkit().createImage(splashImageBootData)
					: null;

			m_splashImage = splashImageData != null
					? Toolkit.getDefaultToolkit().createImage(splashImageData)
					: null;

			m_windowIconImage = windowIconData != null
					? Toolkit.getDefaultToolkit().createImage(windowIconData)
					: null;

			final ProgressFacade monitor = SplashWindow.getDownloadServiceListener();
			SimpleJNLPCache cache = new SimpleJNLPCache(getCacheLocation());
			if(splashImageBootData != null || splashImageData != null)
			{
				cache.addListener(new SimpleJNLPCacheAdapter()
				{
					@Override
					public void updateStarted(URL jnlp)
					{
						SplashWindow.splash(m_splashImageBoot, m_splashImage, m_windowIconImage);
					}
				});
			}

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
			String jnlpString = getJnlpRef(args);
			URL url;
			try
			{
				url = new URL(jnlpString);
			}
			catch(MalformedURLException e)
			{
				throw new JNLPException("Unable to create a URL from " + jnlpString + ": " + e.getMessage(),
						"Report to vendor", ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
			}

			boolean productUpdated = cache.registerJNLP(url, monitor);

			IProductInstaller installer;

			try
			{
				// Class<?> installerClass = Class.forName(PRODUCT_INSTALLER_CLASS);
				Class<?> installerClass = cache.getClassLoader().loadClass(PRODUCT_INSTALLER_CLASS);
				installer = (IProductInstaller)installerClass.newInstance();
			}
			catch(Exception e)
			{
				throw new JNLPException("Can not find materialization wizard resource",
						"Report the error and try later", ERROR_CODE_RESOURCE_EXCEPTION, e);
			}

			if(productUpdated || !installer.isInstalled(getInstallLocation()))
			{
				if(!SplashWindow.splashIsUp())
				{
					SplashWindow.splash(m_splashImageBoot, m_splashImage, m_windowIconImage);
				}
				
				try
				{
					installer.installProduct(this, monitor);
				}
				catch(OperationCanceledException e)
				{
					for(String installFolder : installer.getInstallFolders())
					{
						Utils.deleteRecursive(new File(getInstallLocation(), installFolder));
					}
					throw e;
				}
			}
			// NOTE: keep this to enable debugging - uncomment in splash window too. Stores the debug data
			// in the clipboard.
			// ClipboardService clipservice = (ClipboardService)ServiceManager.lookup("javax.jnlp.ClipboardService");
			// StringSelection ss = new StringSelection(SplashWindow.getDebugString());
			// clipservice.setContents(ss);
			startProduct(installer.getApplicationFolder(), args, (new Date()).getTime() + startupTime);
			try
			{
				// Two seconds to start, with progressbar. The time is an
				// estimate of course.
				//
				if(splashImageData != null)
				{
					// Switch splash screen
					//
					if(!SplashWindow.splashIsUp())
						SplashWindow.splash(null, m_splashImage, m_windowIconImage);
					else
						SplashWindow.setSplashImage(SplashWindow.SPLASH_IMAGE_ID);
				}

				startupTime /= 100;
				monitor.setTask("Starting", startupTime);
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

						throw new JNLPException("Unable to launch materializer:\nExit code: " + processExitValue
								+ (capturedErrors != null
										? "\nCaptured errors:\n" + capturedErrors
										: "") + (capturedOutput != null
										? "\nCaptured output:\n" + capturedOutput
										: ""), "Read error description above",
								ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
					}

					m_process.destroy();
					throw new JNLPException("Unable to launch materializer within timeout",
							"Check your machine (might be too slow or too busy)",
							ERROR_CODE_LAUNCHER_NOT_STARTED_EXCEPTION);
				}
			}
			catch(InterruptedException e)
			{
			}
		} catch(OperationCanceledException e)
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

	private byte[] loadData(String url) throws JNLPException
	{
		byte[] data = null;
		if(url != null)
		{
			InputStream is = null;
			try
			{
				is = new URL(url).openStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buf = new byte[0x1000];
				int count;
				while((count = is.read(buf)) > 0)
					os.write(buf, 0, count);
				data = os.toByteArray();

			}
			catch(IOException e)
			{
				throw new JNLPException("Unable to read a splash screen or window icon image",
						"Check your internet connection and try again", ERROR_CODE_REMOTE_IO_EXCEPTION, e);
			}
			finally
			{
				close(is);
			}
		}

		return data;
	}

	public void startProduct(String applicationFolder, String[] args, long popupAfter) throws JNLPException
	{
		File launcherFile = findEclipseLauncher(applicationFolder);
		String javaHome = System.getProperty("java.home");
		if(javaHome == null)
		{
			throw new JNLPException("System propery java.home is not set",
					"Set the system property which should point to java home directory and try again",
					ERROR_CODE_JAVA_HOME_NOT_SET_EXCEPTION);
		}

		File javaBin = new File(javaHome, "bin");
		File javaExe = new File(javaBin, isWindows()
				? "javaw.exe"
				: "java");

		if(!javaExe.exists())
		{
			throw new JNLPException("Unable to locate java runtime", "Check java installation and try again",
					ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
		}

		ArrayList<String> allArgs = new ArrayList<String>();
		allArgs.add(javaExe.toString());
		allArgs.add("-jar");
		allArgs.add(launcherFile.toString());

		String wsDir = getWorkspaceDir();
		if(wsDir != null)
		{
			allArgs.add("-data");
			allArgs.add(wsDir);
		}
		allArgs.add("-application");
		allArgs.add("org.eclipse.buckminster.jnlp.application");
		for(String arg : args)
			allArgs.add(arg);

		try
		{
			allArgs.addAll(getProxySettings());
		}
		catch(URISyntaxException e)
		{
			throw new JNLPException("Unable to detect proxy settings", "Report the problem",
					ERROR_CODE_JAVA_RUNTIME_EXCEPTION);
		}

		final String syncString = "sync info: application launched";
		allArgs.add("-syncString");
		allArgs.add(syncString);

		allArgs.add("-consoleLog");

		allArgs.add("-popupAfter");
		allArgs.add("" + popupAfter);

		allArgs.add("-ws");

		if(isWindows())
			allArgs.add("win32");
		else if(isAix())
			allArgs.add("motif");
		else if(isMaxOSx())
		{
			allArgs.add("carbon");
			allArgs.add("-XstartOnFirstThread");
		}
		else
			allArgs.add("gtk");

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
						System.err.println("Error reading from JNLP application standard output: " + e.getMessage());
					}
					finally
					{
						close(rd);
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
						System.err.println("Error reading from JNLP application standard error: " + e.getMessage());
					}
					finally
					{
						close(erd);
					}
				}
			}.start();
		}
		catch(IOException e)
		{
			throw new JNLPException("Can not run materializer wizard", "Check your system permissions and try again",
					ERROR_CODE_MATERIALIZER_EXECUTION_EXCEPTION, e);
		}
	}

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
	private List<String> getProxySettings() throws URISyntaxException
	{
		List<String> args = new ArrayList<String>();
		ProxySelector proxySelector = ProxySelector.getDefault();

		for(URI uri : new URI[] { new URI("http://dummy.host.com"), new URI("https://dummy.host.com"),
				new URI("ftp://dummy.host.com") })
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
					args.add("-" + protocol + ".proxyHost");
					args.add(iaddr.getHostName());
					args.add("-" + protocol + ".proxyPort");
					args.add("" + iaddr.getPort());
					args.add("-" + protocol + ".nonProxyHosts");
					args.add("localhost|127.0.0.1");
					break;
				}
			}
		}

		return args;
	}

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	public File findEclipseLauncher(String applicationFolder) throws JNLPException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find
		// it.
		//
		File siteRoot = new File(getInstallLocation(), applicationFolder);
		if(!siteRoot.isDirectory())
		{
			throw new JNLPException("Unable to locate the site root of " + getInstallLocation(),
					"Check disk space, system permissions and try again", ERROR_CODE_SITE_ROOT_EXCEPTION);
		}

		File pluginsDir = new File(siteRoot, "plugins");
		String[] names = pluginsDir.list();
		if(names == null)
		{
			throw new JNLPException(pluginsDir + " is not a directory", "Report the error and try later",
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
			launcher = new File(siteRoot, "startup.jar");
			if(!launcher.exists())
			{
				throw new JNLPException("Can not find file: " + pluginsDir
						+ "org.eclipse.equinox.launcher_<version>.jar",
						"Clear your java cache, browser cache and try again", ERROR_CODE_LAUNCHER_NOT_FOUND_EXCEPTION);
			}
		}
		else
			launcher = new File(pluginsDir, found);

		return launcher;
	}

	public static void close(Closeable closeable)
	{
		if(closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch(IOException e)
			{
			}
		}
	}

	private String getErrorURL()
	{
		return m_errorURL;
	}

	private Image getWindowIconImage()
	{
		return m_windowIconImage;
	}

}
