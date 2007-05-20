/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class is supposed to be called as a JNLP application. It pops up
 * a splash and the in will access a resource. The idea is that the resource
 * should be declared for lazy downloading and thus not triggered until
 * someone tries to access it. Since that access happens after the splash
 * has popped up, everything should be done in the right order.
 *
 * @author Thomas Hallgren 
 */
public class Main
{
	private static final String PACK_SUFFIX = ".pack.gz";

	private static final int PACK_SUFFIX_LEN = PACK_SUFFIX.length();

	private static final char s_fileSep;

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private static final String USER_HOME = "@user.home";

	static
	{
		String fileSep = System.getProperty("file.separator");
		s_fileSep = (fileSep == null || fileSep.length() < 1) ? '/' : fileSep.charAt(0);
	}

	public static void close(InputStream input)
	{
		try
		{
			input.close();
		}
		catch(IOException e)
		{}
	}

	public static void close(OutputStream output)
	{
		try
		{
			output.close();
		}
		catch(IOException e)
		{}
	}

	public static void main(String[] args)
	{
		try
		{
			Main main = new Main();
			main.run(args);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}

	private static String getMainClassName(File launcherFile) throws IOException
	{
		JarFile jarFile = null;
		try
		{
			jarFile = new JarFile(launcherFile);
			Manifest manifest = jarFile.getManifest();
			return (manifest == null) ? null : manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);
		}
		finally
		{
			if(jarFile != null)
				jarFile.close();
		}
	}

	private static String osAdjustName(String name)
	{
		if(s_fileSep != '/')
			name = name.replace('/', s_fileSep);
		return name;
	}

	private File m_installLocation;

	private File m_siteRoot;

	private Unpacker m_unpacker;

	public File findEclipseLauncher() throws IOException
	{
		// Eclipse 3.3 no longer have a startup.jar in the root. Instead, they have a
		// org.eclipse.equinox.launcher_xxxx.jar file under plugins. Let's find
		// it.
		//
		if(m_siteRoot == null)
			throw new IOException("Unable to locate the site root of " + getInstallLocation());

		File pluginsDir = new File(m_siteRoot, "plugins");
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
			launcher = new File(m_siteRoot, "startup.jar");
			if(!launcher.exists())
				throw new FileNotFoundException(pluginsDir + "org.eclipse.equinox.launcher_<version>.jar");
		}
		else
			launcher = new File(pluginsDir, found);
		
		return launcher;
	}

	synchronized File getInstallLocation() throws IOException
	{
		if(m_installLocation == null)
		{
			String userHome = System.getProperty("user.home");
			if(userHome != null)
			{
				String os = System.getProperty("os.name");
				boolean isWindows = (os != null && os.length() >= 7 && "windows".equalsIgnoreCase(os.substring(0, 7)));
				m_installLocation = new File(userHome, isWindows ? "Application Data\\buckminster" : ".buckminster");
			}
			else
				m_installLocation = File.createTempFile("bucky", ".site");
			m_installLocation.mkdirs();
		}
		return m_installLocation;
	}

	String getWorkspaceDir() throws IOException
	{
		String workspaceDir = System.getProperty("osgi.instance.area", null);
		if(workspaceDir != null)
		{
			if(workspaceDir.startsWith(USER_HOME))
				workspaceDir = System.getProperty("user.home") + workspaceDir.substring(USER_HOME.length());
		}
		return workspaceDir;
	}

	void installProduct() throws IOException
	{
		InputStream productZip = getClass().getResourceAsStream("product.zip");
		if(productZip == null)
		{
			// Nothing to install
			//
			System.err.println("No product found");
			return;
		}

		File installLocation = getInstallLocation();
		try
		{
			ZipInputStream zipInput = new ZipInputStream(productZip);
			ZipEntry zipEntry;
			while((zipEntry = zipInput.getNextEntry()) != null)
			{
				String name = osAdjustName(zipEntry.getName());
				File file;
				if(zipEntry.isDirectory())
				{
					file = new File(installLocation, name);
					file.mkdirs();

					// Ensure that this directory exists
					//
					if(m_siteRoot == null)
						//
						// First directory found is always the site root
						//
						m_siteRoot = file; 
				}
				else
				{
					if(name.endsWith(PACK_SUFFIX))
						//
						// Pack200 compressed file
						//
						file = storeUnpacked(name, zipInput);
					else
					{
						file = new File(installLocation, name);
						storeVerbatim(file, zipInput);
					}
				}
				long tz = zipEntry.getTime();
				if(tz != -1L)
					file.setLastModified(tz);
			}
		}
		finally
		{
			close(productZip);
		}			
	}

	void run(String[] args)
	{
		showSplash();
		try
		{
			installProduct();
			startProduct(args);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			tearDownSplash();
		}
	}

	void startProduct(String[] args) throws Exception
	{
		File launcherFile = findEclipseLauncher();
		String mainClassName = getMainClassName(launcherFile);
		if(mainClassName == null)
		{
			System.err.println("No Main-Class declared in jar manifest");
			return;
		}

		ArrayList<String> allArgs = new ArrayList<String>();
		allArgs.add("-data");
		allArgs.add(getWorkspaceDir());

		for(String arg : args)
			allArgs.add(arg);

		URLClassLoader loader = new URLClassLoader(new URL[] { launcherFile.toURI().toURL() });
		Class<?> mainClass = loader.loadClass(mainClassName);
		Method main = mainClass.getMethod("main", new Class[] { args.getClass() });
		int mods = main.getModifiers();
		if(main.getReturnType() == void.class && Modifier.isStatic(mods) && Modifier.isPublic(mods))
			main.invoke(null, new Object[] { allArgs.toArray(new String[allArgs.size()]) });
		else
			System.err.println("No public static void main(String[]) method found in main class");
	}

	private void showSplash()
	{

	}

	private synchronized File storeUnpacked(String packedName, InputStream packedInput) throws IOException
	{
		String unpackedName = packedName.substring(0, packedName.length() - PACK_SUFFIX_LEN);
		File unpackedFile = new File(getInstallLocation(), unpackedName);

		if(m_unpacker == null)
			m_unpacker = Pack200.newUnpacker();

		JarOutputStream jarOut = null;
		try
		{
			jarOut = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(unpackedFile)));
			m_unpacker.unpack(packedInput, jarOut);
		}
		finally
		{
			close(jarOut);
		}
		return unpackedFile;
	}

	private synchronized void storeVerbatim(File file, InputStream packedInput) throws IOException
	{
		OutputStream out = null;
		try
		{
			int count;
			byte[] buffer = new byte[0x1000];
			out = new FileOutputStream(file);
			while((count = packedInput.read(buffer)) > 0)
				out.write(buffer, 0, count);
		}
		finally
		{
			close(out);
		}
	}

	private void tearDownSplash()
	{

	}
}
