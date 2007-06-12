package org.eclipse.buckminster.jnlp.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.jnlp.bootstrap.IProductInstaller;
import org.eclipse.buckminster.jnlp.bootstrap.JNLPException;
import org.eclipse.buckminster.jnlp.bootstrap.Main;
import org.eclipse.buckminster.jnlp.bootstrap.ProgressFacade;

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

	private static final String PROP_UNPACK_COUNT = "unpackCount";
	private static final int DEFAULT_UNPACK_COUNT = 80;

	public void installProduct(Main main, ProgressFacade monitor) throws JNLPException
	{
		m_main = main;

		// We know that we have about 80 packed files to process. Since we're streaming
		// everything in one go, it's a bit hard to find the exact number.
		//
		int unpackCount = Integer.getInteger(PROP_UNPACK_COUNT, DEFAULT_UNPACK_COUNT).intValue();
		monitor.setTask("Unpacking", unpackCount + 20);
		installResource("product.zip", monitor);
		installResource("platform.zip", monitor);
		monitor.taskDone();
	}

	private void installResource(String resourceName, ProgressFacade monitor) throws JNLPException
	{
		monitor.taskIncrementalProgress(5);
		InputStream resourceZip = getClass().getResourceAsStream(resourceName);
		monitor.taskIncrementalProgress(5);
		if(resourceZip == null)
		{
			//
			// Nothing to install
			//
			throw new RuntimeException("Missing " + resourceName + " resource");
		}

		try
		{
			installFromStream(resourceZip, monitor);
		}
		finally
		{
			Main.close(resourceZip);
		}
	}

	private void installFromStream(InputStream productZip, ProgressFacade monitor) throws JNLPException
	{
		File installLocation = m_main.getInstallLocation();
		ZipInputStream zipInput = new ZipInputStream(productZip);
		ZipEntry zipEntry;
		try
		{
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
					{
						// Pack200 compressed file
						//
						file = new File(installLocation, name.substring(0, name.length() - PACK_SUFFIX_LEN));
						OutputStream output;
						try
						{
							output = new FileOutputStream(file);
						}
						catch(FileNotFoundException e)
						{
							throw new JNLPException("Can not create file: " + file.toString(), "Check disk space, system permissions and try again", e);
						}
						try
						{
							try
							{
								storeUnpacked(zipInput, output);
							}
							catch(IOException e)
							{
								throw new JNLPException("Can not unzip and save to file: " + file.toString(), "Check disk space, system permissions and try again", e);
							}
							monitor.taskIncrementalProgress(1);
						}
						finally
						{
							Main.close(output);
						}
					}
					else
					{
						// This is so quick that it doesn't generate a progress tick
						//
						file = new File(installLocation, name);
						try
						{
							storeVerbatim(file, zipInput);
						}
						catch(IOException e)
						{
							throw new JNLPException("Can not save to file: " + file.toString(), "Check disk space, system permissions and try again", e);
						}
					}

					if(file.getName().endsWith(".jar"))
					{
						try
						{
							if(recursiveUnpack(file))
								monitor.taskIncrementalProgress(1);
						}
						catch(IOException e)
						{
							throw new JNLPException("Can not unpack file: " + file.toString(), "Check disk space, system permissions and try again", e);
						}
					}
				}

				long tz = zipEntry.getTime();
				if(tz != -1L)
					file.setLastModified(tz);
			}
		}
		catch(IOException e)
		{
			throw new JNLPException("Can not read materialization wizard resource", "Check your internet connection and try again", e);
		}
	}

	private boolean recursiveUnpack(File file) throws IOException
	{
		// Make sure this jar file doesn't contain packed entries
		//
		JarOutputStream jarOutput = null;
		JarFile jarFile = new JarFile(file);
		File tempFile = null;
		try
		{
			boolean needRepack = false;
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements())
			{
				JarEntry entry = entries.nextElement();
				if(entry.getName().endsWith(PACK_SUFFIX))
				{
					needRepack = true;
					break;
				}
			}
			if(!needRepack)
				return false;

			// We need to repack this jar
			//
			byte[] copyBuf = new byte[8192];
			tempFile = File.createTempFile("unpack", ".jar", file.getParentFile());
			jarOutput = new JarOutputStream(new FileOutputStream(tempFile), jarFile.getManifest());
			entries = jarFile.entries();
			while(entries.hasMoreElements())
			{
				JarEntry entry = entries.nextElement();
				String entryName = entry.getName();
				if(entry.isDirectory())
				{
					if(!entryName.equalsIgnoreCase("meta-inf"))
						//
						// It's there already.
						//
						jarOutput.putNextEntry(entry);
					continue;
				}

				if(entryName.equalsIgnoreCase("meta-inf/manifest.mf"))
					//
					// Manifest is already written to output
					//
					continue;

				InputStream input = jarFile.getInputStream(entry);
				try
				{
					if(entryName.endsWith(PACK_SUFFIX))
					{
						String unpackedName = entryName.substring(0, entryName.length() - PACK_SUFFIX_LEN);
						JarEntry unpackedEntry = new JarEntry(unpackedName);
						unpackedEntry.setMethod(ZipEntry.DEFLATED);
						jarOutput.putNextEntry(unpackedEntry);
						storeUnpacked(input, jarOutput);
					}
					else
					{
						jarOutput.putNextEntry(entry);
						int count;
						while((count = input.read(copyBuf)) > 0)
							jarOutput.write(copyBuf, 0, count);
					}
				}
				finally
				{
					Main.close(input);
				}
			}
		}
		finally
		{
			Main.close(jarOutput);
			jarFile.close();
		}
		File mvTemp = new File(file.getAbsolutePath() + ".move");
		mvTemp.delete();
		renameFile(file, mvTemp);
		renameFile(tempFile, file);
		mvTemp.delete();
		return true;
	}

	private static void renameFile(File from, File to)
	{
		if(!from.renameTo(to))
			throw new RuntimeException(String.format("Unable to rename \"%s\" to \"%s\"", from, to));
	}

	private synchronized void storeUnpacked(InputStream packedInput, OutputStream result) throws IOException
	{

		if(m_unpacker == null)
			m_unpacker = Pack200.newUnpacker();

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
			
			JarOutputStream jarOut = new JarOutputStream(result);
			m_unpacker.unpack(gzipInput, jarOut);
			gzipInput = null; // Closed by unpack
			jarOut.finish();
		}
		finally
		{
			Main.close(gzipInput);
		}
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
