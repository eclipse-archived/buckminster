/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.jnlp.product;

import static org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants;
import org.eclipse.buckminster.jnlp.bootstrap.CorruptedFileException;
import org.eclipse.buckminster.jnlp.bootstrap.IProductInstaller;
import org.eclipse.buckminster.jnlp.bootstrap.JNLPException;
import org.eclipse.buckminster.jnlp.bootstrap.Main;
import org.eclipse.buckminster.jnlp.bootstrap.OperationCanceledException;
import org.eclipse.buckminster.jnlp.bootstrap.ProgressFacade;

public class ProductInstaller implements IProductInstaller
{
	private static final String INSTALL_FOLDER = "installer";
	
	private static final String INSTALL_DONE_FOLDER = "installation.completed";
	
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

	public void installProduct(Main main, ProgressFacade monitor) throws JNLPException, OperationCanceledException, CorruptedFileException
	{
		m_main = main;

		// We know that we have about 80 packed files to process. Since we're streaming
		// everything in one go, it's a bit hard to find the exact number.
		//
		int unpackCount = Integer.getInteger(PROP_UNPACK_COUNT, DEFAULT_UNPACK_COUNT).intValue();
		monitor.setTask("Unpacking", unpackCount + 30);

		for(String folder : getInstallFolders())
		{
			deleteRecursive(new File(m_main.getInstallLocation(), folder));
		}
		monitor.taskIncrementalProgress(10);

		monitor.checkCanceled();
		
		installResource("product.zip", monitor);
		installResource("platform.zip", monitor);
		installResource("extensions.zip", monitor, false);
		
		File appFolder = new File(m_main.getInstallLocation(), getApplicationFolder());
		try
		{
			new File(appFolder, INSTALL_DONE_FOLDER).createNewFile();
		}
		catch(IOException e)
		{
			throw new JNLPException("Can not create a new file",
					"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION, e);
		}
		
		monitor.taskDone();
	}

	private static void deleteRecursive(File file) throws JNLPException
	{
		if(!file.exists())
			return;

		try
		{
			File[] list = file.listFiles();
			int count = (list == null)
					? 0
					: list.length;
			if(count > 0)
			{
				while(--count >= 0)
					deleteRecursive(list[count]);
			}

			if(!file.delete() && file.exists())
				throw new JNLPException("Unable to delete " + file.getAbsolutePath(), "Check file permissions",
						BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION);
		}
		catch(SecurityException e)
		{
			throw new JNLPException("Unable to delete " + file.getAbsolutePath() + ": " + e.getMessage(),
					"Check file permissions", BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION, e);
		}
	}

	private void installResource(String resourceName, ProgressFacade monitor) throws JNLPException, OperationCanceledException, CorruptedFileException
	{
		installResource(resourceName, monitor, true);
	}

	private void installResource(String resourceName, ProgressFacade monitor, boolean required) throws JNLPException, OperationCanceledException, CorruptedFileException
	{
		monitor.taskIncrementalProgress(5);
		InputStream resourceZip = getClass().getResourceAsStream(resourceName);
		InputStream resourceZipMD5 = getClass().getResourceAsStream(resourceName + ".MD5");
		monitor.taskIncrementalProgress(5);
		if(resourceZip == null)
		{
			//
			// Nothing to install
			//
			if(required)
				throw new RuntimeException("Missing " + resourceName + " resource");
			
			return;
		}

		try
		{
			installFromStream(resourceZip, resourceZipMD5, monitor);
		}
		finally
		{
			Main.close(resourceZip);
			Main.close(resourceZipMD5);
		}
	}

	private void installFromStream(InputStream productZip, InputStream productZipMD5, ProgressFacade monitor) throws JNLPException, OperationCanceledException, CorruptedFileException
	{
		try
		{
			byte[] productBytes = readStream(productZip);
			String computedMD5 = encrypt(productBytes, "MD5").trim();
			String originalMD5 = new String(readStream(productZipMD5)).trim();
			
			if(!computedMD5.equals(originalMD5))
				throw new CorruptedFileException();

			File installLocation = m_main.getInstallLocation();
			ZipInputStream zipInput = new ZipInputStream(new ByteArrayInputStream(productBytes));
			ZipEntry zipEntry;
			
			while((zipEntry = zipInput.getNextEntry()) != null)
			{
				monitor.checkCanceled();				
				
				boolean folderOK = false;
				for(String folder : getInstallFolders())
				{
					if(zipEntry.getName().startsWith(folder))
					{
						folderOK = true;
						break;
					}
				}
				
				if(!folderOK)
				{
					throw new JNLPException("Materializer error", "Materializer is probably corrupted. Report the error.", ERROR_CODE_MATERIALIZER_INSTALL_EXCEPTION);
				}
				
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
							throw new JNLPException("Can not create file: " + file.toString(),
									"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION,
									e);
						}
						try
						{
							try
							{
								storeUnpacked(zipInput, output);
							}
							catch(IOException e)
							{
								throw new JNLPException("Can not unzip and save to file: " + file.toString(),
										"Check disk space, system permissions and try again",
										ERROR_CODE_FILE_IO_EXCEPTION, e);
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
							throw new JNLPException("Can not save to file: " + file.toString(),
									"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION,
									e);
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
							throw new JNLPException("Can not unpack file: " + file.toString(),
									"Check disk space, system permissions and try again", ERROR_CODE_FILE_IO_EXCEPTION,
									e);
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
			throw new JNLPException("Can not read materialization wizard resource",
					"Check your internet connection and try again", ERROR_CODE_REMOTE_IO_EXCEPTION, e);
		}
	}

	private byte[] readStream(InputStream input) throws IOException
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] copyBuf = new byte[8192];
		int count;
		while((count = input.read(copyBuf)) > 0)
			output.write(copyBuf, 0, count);

		return output.toByteArray();
	}
	
	private static String encrypt(byte[] bytes, String algorithmName)
	{
		String md5val = "";
		MessageDigest algorithm = null;

		try
		{
			algorithm = MessageDigest.getInstance(algorithmName);
		}
		catch(NoSuchAlgorithmException nsae)
		{
			throw new IllegalArgumentException("Unknown encrypt algorithm: " + algorithmName);
		}

		algorithm.reset();
		algorithm.update(bytes);
		byte messageDigest[] = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for(int i = 0; i < messageDigest.length; i++)
		{
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length() == 1)
			{
				hexString.append('0');
			}
			hexString.append(hex);
		}
		md5val = hexString.toString();
		return md5val;
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

	public String getApplicationFolder()
	{
		return INSTALL_FOLDER;
	}

	public String[] getInstallFolders()
	{
		return new String[]{INSTALL_FOLDER};
	}

	public boolean isInstalled(File installLocation) throws JNLPException 
	{
		File appFolder = new File(installLocation, getApplicationFolder());
		return new File(appFolder, INSTALL_DONE_FOLDER).exists();
	}
}
