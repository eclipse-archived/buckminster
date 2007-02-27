/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * A IStreamConsumer responsible for reading and parsing a
 * <code>.project</code> file.
 *
 * @author thhal
 */
public class ProjectDescReader implements IStreamConsumer<IProjectDescription>
{
	public static IProjectDescription getProjectDescription(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		ProjectDescReader pdr = new ProjectDescReader();
		try
		{
			IProjectDescription projDesc;
			if(reader instanceof ICatalogReader)
				projDesc = ((ICatalogReader)reader).readFile(IProjectDescription.DESCRIPTION_FILE_NAME, pdr, monitor);
			else
				projDesc = ((IFileReader)reader).readFile(pdr, monitor);
			return projDesc;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public IProjectDescription consumeStream(IComponentReader fileReader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		try
		{
			monitor.beginTask(null, 1);
			monitor.subTask("Loading project description");
			IProjectDescription pd = ResourcesPlugin.getWorkspace().loadProjectDescription(stream);
			MonitorUtils.worked(monitor, 1);
			return pd;
		}
		finally
		{
			monitor.done();
		}
	}
}

