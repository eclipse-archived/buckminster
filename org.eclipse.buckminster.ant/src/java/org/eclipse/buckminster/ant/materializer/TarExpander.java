package org.eclipse.buckminster.ant.materializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.IExpander;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public class TarExpander extends AbstractExtension implements IExpander
{
	public void expand(InputStream inputs, IPath finalLocation, IProgressMonitor monitor) throws CoreException
	{
		TarEntry entry;
		TarInputStream input = null;

		MonitorUtils.begin(monitor, 600);
		File dest = null;
		if(finalLocation != null)
		{
			dest = finalLocation.toFile();
			FileUtils.prepareDestination(dest, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 100));
		}

		try
		{
			int ticksLeft = 500;
			input = new TarInputStream(inputs);

			while((entry = input.getNextEntry()) != null)
			{
				String name = entry.getName();
				IProgressMonitor subMonitor;
				if(ticksLeft > 0)
				{
					subMonitor = MonitorUtils.subMonitor(monitor, 10);
					ticksLeft -= 10;
				}
				else
					subMonitor = null;

				if(entry.isDirectory())
				{
					if(dest != null)
						FileUtils.createDirectory(new File(dest, name), subMonitor);
					continue;
				}
				FileUtils.copyFile(input, dest, name, subMonitor);
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
