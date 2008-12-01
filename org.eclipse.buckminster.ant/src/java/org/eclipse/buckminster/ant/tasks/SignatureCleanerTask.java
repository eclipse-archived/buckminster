/**
 * 
 */
package org.eclipse.buckminster.ant.tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.buckminster.ant.Messages;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.osgi.util.NLS;

/**
 * @author thhal
 * 
 */
public class SignatureCleanerTask
{
	private final List<File> m_jarFiles;

	private final byte[] m_buffer = new byte[0x8000];

	public SignatureCleanerTask(List<File> jarFiles)
	{
		m_jarFiles = jarFiles;
	}

	public void clean() throws IOException
	{
		for(File jarFile : m_jarFiles)
		{
			int len;
			File folder = jarFile.getParentFile();
			File tmpFile1 = File.createTempFile("jarclean", ".tmp", folder); //$NON-NLS-1$ //$NON-NLS-2$
			boolean cleaned = false;
			try
			{
				ZipInputStream zipInput = null;
				ZipOutputStream zipOutput = null;
				try
				{
					zipInput = new ZipInputStream(new BufferedInputStream(new FileInputStream(jarFile), 0x8000));
					zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFile1), 0x8000));

					ZipEntry entry;
					while((entry = zipInput.getNextEntry()) != null)
					{
						String name = entry.getName();
						if(name.startsWith("META-INF/") && name.indexOf('/', 9) < 0 //$NON-NLS-1$
								&& (name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".SF"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						{
							// Skip this entry
							cleaned = true;
							continue;
						}
						// Copy entry to output
						zipOutput.putNextEntry(entry);
						while((len = zipInput.read(m_buffer)) > 0)
							zipOutput.write(m_buffer, 0, len);
					}
				}
				finally
				{
					IOUtils.close(zipInput);
					IOUtils.close(zipOutput);
				}

				if(cleaned)
				{
					// Rename the old file
					//
					File tmpFile2 = new File(tmpFile1.getAbsolutePath() + ".delete"); //$NON-NLS-1$
					if(!jarFile.renameTo(tmpFile2))
						throw new IOException(NLS.bind(Messages.SignatureCleanerTask_Unable_to_rename_jar_0_to_tmp_1,
								jarFile, tmpFile2));

					if(!tmpFile1.renameTo(jarFile))
					{
						// Make an attempt to undo the previous rename.
						//
						tmpFile2.renameTo(jarFile);
						throw new IOException(NLS.bind(Messages.SignatureCleanerTask_Unable_to_rename_tmp_1_to_jar_1,
								tmpFile1, jarFile));
					}
					tmpFile1 = tmpFile2; // Delete this one instead in the finally clause
				}
			}
			finally
			{
				tmpFile1.delete();
			}
		}
	}
}
