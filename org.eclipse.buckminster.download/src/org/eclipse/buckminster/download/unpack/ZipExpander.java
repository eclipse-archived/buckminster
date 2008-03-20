/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.unpack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.download.IExpander;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.NullOutputStream;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class ZipExpander implements IExpander
{
	public void expand(InputStream inputs, File destinationFolder, IProgressMonitor monitor) throws CoreException
	{
		ZipEntry entry;
		ZipInputStream input = null;

		int ticksLeft = 600;
		MonitorUtils.begin(monitor, ticksLeft);
		if(destinationFolder != null)
		{
			if(!(destinationFolder.isDirectory() || destinationFolder.mkdirs()))
				throw BuckminsterException.fromMessage("Unable to unzip into directory %s", destinationFolder);

			MonitorUtils.worked(monitor, 10);
			ticksLeft -= 10;
		}

		try
		{
			input = new ZipInputStream(inputs);
			while((entry = input.getNextEntry()) != null)
			{
				String name = entry.getName();
				if(entry.isDirectory())
				{
					if(destinationFolder == null)
						continue;

					File subDir = new File(destinationFolder, name);
					if(!(subDir.isDirectory() || subDir.mkdirs()))
						throw BuckminsterException.fromMessage("Unable to unzip into directory %s", destinationFolder);

					if(ticksLeft >= 10)
					{
						MonitorUtils.worked(monitor, 10);
						ticksLeft -= 10;
					}
					continue;
				}

				OutputStream output = null;
				try
				{
					if(destinationFolder == null)
						output = NullOutputStream.INSTANCE;
					else
						output = new FileOutputStream(new File(destinationFolder, name));
					
					IProgressMonitor subMon = null;
					if(ticksLeft >= 20)
					{
						subMon = MonitorUtils.subMonitor(monitor, 10);
						ticksLeft -= 10;
					}
					IOUtils.copy(input, output, subMon);
				}
				finally
				{
					IOUtils.close(output);
				}
			}
			if(ticksLeft > 0)
				MonitorUtils.worked(monitor, ticksLeft);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}
}
