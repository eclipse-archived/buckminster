package org.eclipse.buckminster.jnlp.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.jnlp.bootstrap.IProductInstaller;
import org.eclipse.buckminster.jnlp.bootstrap.Main;

public class ProductInstaller implements IProductInstaller
{
	private static final String PACK_SUFFIX = ".pack.gz";

	private static final int PACK_SUFFIX_LEN = PACK_SUFFIX.length();

	private static final char s_fileSep;

	static
	{
		String fileSep = System.getProperty("file.separator");
		s_fileSep = (fileSep == null || fileSep.length() < 1)
				? '/'
				: fileSep.charAt(0);
	}

	private static String osAdjustName(String name)
	{
		if(s_fileSep != '/')
			name = name.replace('/', s_fileSep);
		return name;
	}

	private Main m_main;

	private Unpacker m_unpacker;

	public void installProduct(Main main) throws IOException
	{
		m_main = main;
		installResource("product.zip");
		installResource("platform.zip");
	}

	private void installResource(String resourceName) throws IOException
	{
		InputStream resourceZip = getClass().getResourceAsStream(resourceName);
		if(resourceZip == null)
		{
			//
			// Nothing to install
			//
			throw new RuntimeException("Missing " + resourceName + " resource");
		}

		try
		{
			installFromStream(resourceZip);
		}
		finally
		{
			Main.close(resourceZip);
		}
	}

	private void installFromStream(InputStream productZip) throws IOException
	{
		File installLocation = m_main.getInstallLocation();
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

	private synchronized File storeUnpacked(String packedName, InputStream packedInput) throws IOException
	{
		String unpackedName = packedName.substring(0, packedName.length() - PACK_SUFFIX_LEN);
		File unpackedFile = new File(m_main.getInstallLocation(), unpackedName);

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
			Main.close(jarOut);
			Main.close(gzipInput);
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
			Main.close(out);
		}
	}
}
