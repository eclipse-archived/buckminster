/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.jnlp.ClipboardService;
import javax.jnlp.DownloadService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;

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

	private static final String PRODUCT = "product";

	private static final String USER_HOME = "@user.home";

	private File m_installLocation;
	
	public static final String PROP_SPLASH_IMAGE = "splashImage";

	public static void main(String[] args)
	{
		Main main = new Main();
		try
		{
			main.run(args);
			Runtime.getRuntime().exit(0);
		}
		catch(Throwable t)
		{			
			// PROPER ERROR HANDLING GOES HERE!
			//
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
			Runtime.getRuntime().exit(1);
		}
	}

	public synchronized File getInstallLocation() throws IOException
	{
		if(m_installLocation == null)
		{
			if(isWindows())
			{
				File appData = null;
				String appDataEnv = System.getenv("APPDATA");
				if(appDataEnv != null)
					appData = new File(appDataEnv);
				else
				{
					String userHome = System.getProperty("user.home");
					if(userHome != null)
						appData = new File(userHome, "Application Data");
				}
				if(appData != null)
					m_installLocation = new File(appData, "buckminster");
			}
			else
			{
				String userHome = System.getProperty("user.home");
				if(userHome != null)
					m_installLocation = new File(userHome, ".buckminster");
			}

			if(m_installLocation == null)
				m_installLocation = File.createTempFile("bucky", ".site");
			m_installLocation.mkdirs();
		}
		return m_installLocation;
	}

	public static boolean isWindows()
	{
		String os = System.getProperty("os.name");
		return os != null && os.length() >= 7 && "windows".equalsIgnoreCase(os.substring(0, 7));
	}

	public String getWorkspaceDir() throws IOException
	{
		String workspaceDir = System.getProperty("osgi.instance.area", null);
		if(workspaceDir != null)
		{
			if(workspaceDir.startsWith(USER_HOME))
				workspaceDir = System.getProperty("user.home") + workspaceDir.substring(USER_HOME.length());
		}
		return workspaceDir;
	}

	void installProduct() throws IOException, UnavailableServiceException
	{
	}

	private Properties parseArguments(String[] args) throws IOException
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
			throw new RuntimeException("Missing required argument -configURL <URL to config properties>");

		InputStream propStream = null;
		OutputStream localStream = null;
		try
		{
			URL propertiesURL = new URL(args[urlIdx].trim());
			if(!"file".equals(propertiesURL))
			{
				// Copy to local file. The installer that we bootstrap will need
				// this too and we don't want an extra http GET just to get it.
				//
				int count;
				byte[] bytes = new byte[8192];
				ByteArrayOutputStream bld = new ByteArrayOutputStream();
				propStream = propertiesURL.openStream();
				while((count = propStream.read(bytes, 0, bytes.length)) > 0)
					bld.write(bytes, 0, count);

				propStream.close();
				bytes = bld.toByteArray();
				propStream = new ByteArrayInputStream(bytes);

				// Create the local file
				//
				File localTemp = new File(getInstallLocation(), "temp");
				if(!(localTemp.exists() || localTemp.mkdirs()))
					throw new RuntimeException("Unable to create directory " + localTemp);

				File localProps = File.createTempFile("config", "properties", localTemp);
				localStream = new FileOutputStream(localProps);
				localStream.write(bytes);

				// Replace the configURL option value in the argument array with a pointer
				// to the local file. We convert to URI first since the toURL() on File
				// is broken (it doesn't convert spaces correctly).
				//
				args[urlIdx] = localProps.toURI().toURL().toExternalForm();
			}
			else
				propStream = new BufferedInputStream(propertiesURL.openStream());

			Properties props = new Properties();
			props.load(propStream);
			return props;
		}
		finally
		{
			close(propStream);
			close(localStream);
		}		
	}

	void run(String[] args) throws Exception
	{
		try
		{
			Properties props = parseArguments(args);
			String tmp = props.getProperty(PROP_SPLASH_IMAGE);
			byte[] splashData = null;
			if(tmp != null)
			{
				InputStream is = null;
				try
				{
					is = new URL(tmp).openStream();
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					byte[] buf = new byte[0x1000];
					int count;
					while((count = is.read(buf)) > 0)
						os.write(buf, 0, count);
					splashData = os.toByteArray();

				}
				finally
				{
					close(is);
				}
		        SplashWindow.splash(splashData);
			}

			File siteRoot = getSiteRoot();
			if(siteRoot == null)
			{
				/*// Uncomment to get two testloops of progress - do not use in production
				// test loop - uncomment to test splash progress without actually
				// running under Java Web Start - i.e. keep this comment in the code.
				DownloadServiceListener xdsl = SplashWindow.getDownloadServiceListener();
				for(int i = 0; i < 101; i++)
				{
					xdsl.progress(null,"", 0L, 0L, i);
					Thread.sleep(50);
				}
				for(int i = 0; i < 51; i++)
				{
					xdsl.progress(null,"", 0L, 0L, i);
					Thread.sleep(50);
				}
				// For debugging purposes - obtain data from the splash and put them in user's clipboard
				//
				SplashWindow.disposeSplash();
				System.err.print(SplashWindow.getDebugString());
				*/
								
				// Assume we don't have an installed product
				//
				DownloadService ds = (DownloadService)ServiceManager.lookup("javax.jnlp.DownloadService");
				// DownloadServiceListener dsl = ds.getDefaultProgressWindow();
				if(!ds.isPartCached(PRODUCT))
				{
			        // SplashWindow.disposeSplash();
					ds.loadPart(PRODUCT, SplashWindow.getDownloadServiceListener());
			        // SplashWindow.splash(splashData);
				}

				Class<?> installerClass = Class.forName(PRODUCT_INSTALLER_CLASS);
				IProductInstaller installer = (IProductInstaller)installerClass.newInstance();
				installer.installProduct(this);
			}
// NOTE: keep this to enable debugging - uncomment in splash window too. Stores the debug data
// in the clipboard.
//			ClipboardService clipservice = (ClipboardService)ServiceManager.lookup("javax.jnlp.ClipboardService");
//			StringSelection ss = new StringSelection(SplashWindow.getDebugString());
//			clipservice.setContents(ss);  
			startProduct(args);
			try
			{
				Thread.sleep(2000);
			}
			catch(InterruptedException e)
			{
			}
		}
		finally
		{
			// Give the app some time to start.
			//
	        SplashWindow.disposeSplash();
		}
	}

	public void startProduct(String[] args) throws Exception
	{
		File launcherFile = findEclipseLauncher();
		String javaHome = System.getProperty("java.home");
		if(javaHome == null)
			throw new RuntimeException("java.home property not set");

		File javaBin = new File(javaHome, "bin");
		File javaExe = new File(javaBin, isWindows() ? "javaw.exe" : "java");

		if(!javaExe.exists())
			throw new RuntimeException("Unable to locate java runtime");

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

		Runtime runtime = Runtime.getRuntime();
		runtime.exec(allArgs.toArray(new String[allArgs.size()]));
	}

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private File m_siteRoot;

	/**
	 * Returns the most recent folder that has a plugins and features
	 * subfolder or <code>null</code> if no such folder can be found.
	 * @return The most recent site root folder or <code>null</code>.
	 * @throws IOException
	 */
	public File getSiteRoot() throws IOException
	{
		if(m_siteRoot == null)
		{
			File bestCandidate = null;
			File[] candidates = getInstallLocation().listFiles();
			if(candidates != null)
			{
				long bestCandidateTime = 0;
				for(File candidate : candidates)
				{
					long candidateTime = candidate.lastModified();
					if((bestCandidate == null || bestCandidateTime < candidateTime)
					&& new File(candidate, "plugins").isDirectory()
					&& new File(candidate, "features").isDirectory())
					{
						bestCandidate = candidate;
						bestCandidateTime = candidateTime;
					}
				}
			}
			m_siteRoot = bestCandidate;
		}
		return m_siteRoot;
	}

	public File findEclipseLauncher() throws IOException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find
		// it.
		//
		File siteRoot = getSiteRoot();
		if(siteRoot == null)
			throw new RuntimeException("Unable to locate the site root of " + getInstallLocation());

		File pluginsDir = new File(siteRoot, "plugins");
		String[] names = pluginsDir.list();
		if(names == null)
			throw new IOException(pluginsDir + " is not a directory");

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
				throw new FileNotFoundException(pluginsDir + "org.eclipse.equinox.launcher_<version>.jar");
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
}
