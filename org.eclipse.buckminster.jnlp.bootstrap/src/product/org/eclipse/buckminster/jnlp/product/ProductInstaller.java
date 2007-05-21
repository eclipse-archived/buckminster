package org.eclipse.buckminster.jnlp.product;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.jnlp.bootstrap.IProductInstaller;

public class ProductInstaller implements IProductInstaller
{
	private static final String PACK_SUFFIX = ".pack.gz";

	private static final int PACK_SUFFIX_LEN = PACK_SUFFIX.length();

	private static final char s_fileSep;

	private static final Pattern s_launcherPattern = Pattern.compile("^org\\.eclipse\\.equinox\\.launcher_(.+)\\.jar$");

	private static final String USER_HOME = "@user.home";

	static
	{
		String fileSep = System.getProperty("file.separator");
		s_fileSep = (fileSep == null || fileSep.length() < 1)
				? '/'
				: fileSep.charAt(0);
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

	public void installProduct() throws IOException
	{
		InputStream productZip = getClass().getResourceAsStream("product.zip");
		if(productZip == null)
		{
			//
			// Nothing to install
			//
			throw new RuntimeException("Missing product.zip resource");
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
		allArgs.add("-data");
		allArgs.add(getWorkspaceDir());
		allArgs.add("-application");
		allArgs.add("org.eclipse.buckminster.jnlp.application");
		for(String arg : args)
			allArgs.add(arg);

		Runtime runtime = Runtime.getRuntime();
		runtime.exec(allArgs.toArray(new String[allArgs.size()]));
	}

	public static boolean isWindows()
	{
		String os = System.getProperty("os.name");
		return os != null && os.length() >= 7 && "windows".equalsIgnoreCase(os.substring(0, 7));
	}

	synchronized File getInstallLocation() throws IOException
	{
		if(m_installLocation == null)
		{
			String userHome = System.getProperty("user.home");
			if(userHome != null)
			{
				m_installLocation = new File(userHome, isWindows()
						? "Application Data\\buckminster"
						: ".buckminster");
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

	private synchronized File storeUnpacked(String packedName, InputStream packedInput) throws IOException
	{
		String unpackedName = packedName.substring(0, packedName.length() - PACK_SUFFIX_LEN);
		File unpackedFile = new File(getInstallLocation(), unpackedName);

		if(m_unpacker == null)
			m_unpacker = Pack200.newUnpacker();

		JarOutputStream jarOut = null;
		GZIPInputStream gzipInput = null;
		try
		{
			// Wrap the incoming stream to prevent it from being closed
			// when the GZIPInputStream is closed.
			//
			gzipInput = new GZIPInputStream(new FilterInputStream(packedInput)
			{
				@Override
				public void close()
				{
				}
			});
			jarOut = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(unpackedFile)));
			m_unpacker.unpack(gzipInput, jarOut);
			gzipInput = null; // Closed by unpack
		}
		finally
		{
			close(jarOut);
			close(gzipInput);
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
}
