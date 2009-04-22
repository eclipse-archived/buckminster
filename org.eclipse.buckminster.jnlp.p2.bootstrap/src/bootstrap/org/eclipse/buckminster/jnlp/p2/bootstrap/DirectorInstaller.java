/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.jnlp.p2.bootstrap;

import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_MATERIALIZER_INSTALL_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_PROPERTY_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Unpacker;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DirectorInstaller
{
	private static final String DIRECTOR_FOLDER_NAME = "director"; //$NON-NLS-1$

	private static final String DIRECTOR_BUILD_PROPERTIES_FILE_NAME = "director.build.properties";

	private static final String PROP_BUILD_TIMESTAMP = "build.timestamp";

	private static final String PACK_PROPERTIES_FILE = "pack.properties"; //$NON-NLS-1$

	private static final String PACK_SUFFIX = ".pack.gz"; //$NON-NLS-1$

	private static final int PACK_SUFFIX_LEN = PACK_SUFFIX.length();

	private static final char s_fileSep;

	static
	{
		String fileSep = System.getProperty("file.separator"); //$NON-NLS-1$
		s_fileSep = (fileSep == null || fileSep.length() < 1)
				? '/'
				: fileSep.charAt(0);
	}

	public static long copyFile(InputStream input, OutputStream output) throws IOException
	{
		byte[] buf = new byte[0x2000];
		long total = 0;
		int count;
		while((count = input.read(buf)) > 0)
		{
			output.write(buf, 0, count);
			total += count;
		}
		return total;
	}

	private static String encrypt(byte[] bytes, String algorithmName)
	{
		String md5val = ""; //$NON-NLS-1$
		MessageDigest algorithm = null;

		try
		{
			algorithm = MessageDigest.getInstance(algorithmName);
		}
		catch(NoSuchAlgorithmException nsae)
		{
			throw new IllegalArgumentException(Messages.getString("unknown_encrypt_algorithm_colon") + algorithmName); //$NON-NLS-1$
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

	private static String osAdjustName(String name)
	{
		if(s_fileSep != '/')
			name = name.replace('/', s_fileSep);
		return name;
	}

	private static void renameFile(File from, File to)
	{
		if(!from.renameTo(to))
			throw new RuntimeException(Messages.getString("unable_to_rename_0_to_1", from, to)); //$NON-NLS-1$
	}

	private File m_installLocation;

	private File m_directorFolder;

	private Unpacker m_unpacker;

	public DirectorInstaller(File installLocation)
	{
		m_installLocation = installLocation;
		m_directorFolder = new File(m_installLocation, DIRECTOR_FOLDER_NAME);
	}

	public File getDirectorFolder()
	{
		return m_directorFolder;
	}

	public String getDirectorFolderName()
	{
		return DIRECTOR_FOLDER_NAME;
	}

	public String[] getInstallFolders()
	{
		return new String[] { DIRECTOR_FOLDER_NAME };
	}

	public void installDirector(String directorArchiveURL, String directorBuildPropertiesURL, ProgressFacade monitor)
			throws JNLPException, OperationCanceledException, CorruptedFileException
	{
		monitor.setTask(Messages.getString("Installing director application"), 100); //$NON-NLS-1$

		try
		{
			removeDirector();
			monitor.taskIncrementalProgress(10);

			installResource(directorArchiveURL, monitor); //$NON-NLS-1$
			monitor.checkCanceled();

			// copy director.build.properties file
			try
			{
				File directorBuildPropertiesFile = new File(m_directorFolder, DIRECTOR_BUILD_PROPERTIES_FILE_NAME);
				directorBuildPropertiesFile.createNewFile();

				InputStream is = new URL(directorBuildPropertiesURL).openStream();
				OutputStream os = new FileOutputStream(directorBuildPropertiesFile);
				try
				{
					copyFile(is, os);
				}
				finally
				{
					Utils.close(os);
					Utils.close(is);
				}
			}
			catch(IOException e)
			{
				throw new JNLPException(
						Messages.getString("can_not_create_a_new_file"), //$NON-NLS-1$
						Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
			}
		}
		finally
		{
			monitor.taskDone();
		}
	}

	public boolean isLatestDirectorInstalled(String remoteDirectorBuildPropertiesURL) throws JNLPException
	{
		Properties remoteBuildProp = loadProperties(remoteDirectorBuildPropertiesURL);
		File directorBuildPropertiesFile = new File(m_directorFolder, DIRECTOR_BUILD_PROPERTIES_FILE_NAME);

		if(m_directorFolder.exists())
		{
			if(directorBuildPropertiesFile.exists())
			{
				Properties localBuildProp = loadProperties(directorBuildPropertiesFile.toURI().toString());

				String remoteTimestamp = (String)remoteBuildProp.get(PROP_BUILD_TIMESTAMP);
				String localTimestamp = (String)localBuildProp.get(PROP_BUILD_TIMESTAMP);

				if(remoteTimestamp != null && localTimestamp != null && remoteTimestamp.compareTo(localTimestamp) <= 0)
				{
					// local director is up-to-date
					return true;
				}
			}
		}

		return false;
	}

	public void removeDirector() throws JNLPException
	{
		Utils.deleteRecursive(m_directorFolder);
	}

	private String[] getSkipFiles()
	{
		return new String[] { PACK_PROPERTIES_FILE };
	}

	private void installFromStream(InputStream productZip, InputStream productZipMD5, ProgressFacade monitor)
			throws JNLPException, OperationCanceledException, CorruptedFileException, IOException
	{
		byte[] productBytes = readStream(productZip);
		String computedMD5 = encrypt(productBytes, "MD5").trim(); //$NON-NLS-1$
		String originalMD5 = new String(readStream(productZipMD5)).trim();

		if(!computedMD5.equals(originalMD5))
			throw new CorruptedFileException();

		ZipInputStream zipInput = new ZipInputStream(new ByteArrayInputStream(productBytes));
		ZipEntry zipEntry;

		zipEntryCycle: while((zipEntry = zipInput.getNextEntry()) != null)
		{
			monitor.checkCanceled();

			for(String skipFile : getSkipFiles())
				if(zipEntry.getName().equals(skipFile))
					continue zipEntryCycle;

			if(!zipEntry.getName().startsWith(getDirectorFolderName()))
			{
				throw new JNLPException(
						Messages.getString("materializer_error"), Messages.getString("director_application_is_probably_corrupted_report_the_error"), ERROR_CODE_MATERIALIZER_INSTALL_EXCEPTION); //$NON-NLS-1$ //$NON-NLS-2$
			}

			String name = osAdjustName(zipEntry.getName());
			File file;
			if(zipEntry.isDirectory())
			{
				file = new File(m_installLocation, name);
				file.mkdirs();
			}
			else
			{
				if(name.endsWith(PACK_SUFFIX))
				{
					// Pack200 compressed file
					//
					file = new File(m_installLocation, name.substring(0, name.length() - PACK_SUFFIX_LEN));
					OutputStream output;
					try
					{
						output = new FileOutputStream(file);
					}
					catch(FileNotFoundException e)
					{
						throw new JNLPException(
								Messages.getString("can_not_create_file_colon") + file.toString(), //$NON-NLS-1$
								Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, //$NON-NLS-1$
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
							throw new JNLPException(
									Messages.getString("can_not_unzip_and_save_to_file_colon") + file.toString(), //$NON-NLS-1$
									Messages.getString("check_disk_space_system_permissions_and_try_again"), //$NON-NLS-1$
									ERROR_CODE_FILE_IO_EXCEPTION, e);
						}
						monitor.taskIncrementalProgress(1);
					}
					finally
					{
						Utils.close(output);
					}
				}
				else
				{
					// This is so quick that it doesn't generate a progress tick
					//
					file = new File(m_installLocation, name);
					try
					{
						storeVerbatim(file, zipInput);
					}
					catch(IOException e)
					{
						throw new JNLPException(
								Messages.getString("can_not_save_to_file_colon") + file.toString(), //$NON-NLS-1$
								Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, //$NON-NLS-1$
								e);
					}
				}

				if(file.getName().endsWith(".jar")) //$NON-NLS-1$
				{
					try
					{
						if(recursiveUnpack(file))
							monitor.taskIncrementalProgress(1);
					}
					catch(IOException e)
					{
						throw new JNLPException(
								Messages.getString("can_not_unpack_file_colon") + file.toString(), //$NON-NLS-1$
								Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, //$NON-NLS-1$
								e);
					}
				}
			}

			long tz = zipEntry.getTime();
			if(tz != -1L)
				file.setLastModified(tz);
		}
	}

	private void installResource(String resourceURL, ProgressFacade monitor) throws JNLPException,
			OperationCanceledException, CorruptedFileException
	{
		try
		{
			monitor.taskIncrementalProgress(5);
			InputStream resourceZip = new URL(resourceURL).openStream();
			InputStream resourceZipMD5 = new URL(resourceURL + ".MD5").openStream();; //$NON-NLS-1$
			monitor.taskIncrementalProgress(5);
			if(resourceZip == null)
			{
				//
				// Nothing to install
				//
				throw new RuntimeException(Messages.getString("missing_0_resource", resourceURL)); //$NON-NLS-1$
			}

			try
			{
				installFromStream(resourceZip, resourceZipMD5, monitor);
			}
			finally
			{
				Utils.close(resourceZip);
				Utils.close(resourceZipMD5);
			}
		}
		catch(IOException e)
		{
			throw new JNLPException(
					Messages.getString("can_not_read_materialization_wizard_resource"), //$NON-NLS-1$
					Messages.getString("check_your_internet_connection_and_try_again"), ERROR_CODE_REMOTE_IO_EXCEPTION, e); //$NON-NLS-1$
		}
	}

	private Properties loadProperties(String urlString) throws JNLPException
	{
		Properties prop = new Properties();

		URL propertiesURL = null;
		InputStream is = null;

		try
		{
			propertiesURL = new URL(urlString.trim());
			is = propertiesURL.openStream();
			prop.load(is);
		}
		catch(MalformedURLException e)
		{
			throw new JNLPException(
					Messages.getString("can_not_read_URL_to_properties"), Messages.getString("report_the_error_and_try_later"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
		}
		catch(IOException e)
		{
			throw new JNLPException(
					Messages.getString("can_not_read_properties"), Messages.getString("report_the_error_and_try_later"), //$NON-NLS-1$ //$NON-NLS-2$
					ERROR_CODE_PROPERTY_IO_EXCEPTION, e);
		}
		finally
		{
			Utils.close(is);
		}

		return prop;
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
			tempFile = File.createTempFile("unpack", ".jar", file.getParentFile()); //$NON-NLS-1$ //$NON-NLS-2$
			jarOutput = new JarOutputStream(new FileOutputStream(tempFile), jarFile.getManifest());
			entries = jarFile.entries();
			while(entries.hasMoreElements())
			{
				JarEntry entry = entries.nextElement();
				String entryName = entry.getName();
				if(entry.isDirectory())
				{
					if(!entryName.equalsIgnoreCase("meta-inf")) //$NON-NLS-1$
						//
						// It's there already.
						//
						jarOutput.putNextEntry(entry);
					continue;
				}

				if(entryName.equalsIgnoreCase("meta-inf/manifest.mf")) //$NON-NLS-1$
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
					Utils.close(input);
				}
			}
		}
		finally
		{
			Utils.close(jarOutput);
			jarFile.close();
		}
		File mvTemp = new File(file.getAbsolutePath() + ".move"); //$NON-NLS-1$
		mvTemp.delete();
		renameFile(file, mvTemp);
		renameFile(tempFile, file);
		mvTemp.delete();
		return true;
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
			Utils.close(gzipInput);
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
			Utils.close(out);
		}
	}

}
