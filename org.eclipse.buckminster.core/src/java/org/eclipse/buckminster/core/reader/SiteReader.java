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
import java.net.URL;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteContentProvider;
import org.eclipse.update.core.model.DefaultSiteParser;
import org.eclipse.update.internal.core.ExtendedSiteURLFactory;
import org.eclipse.update.internal.core.SiteFileContentProvider;


/**
 * A IStreamConsumer responsible for reading and parsing a
 * <code>site.xml</code> file.
 *
 * @author thhal
 */
@SuppressWarnings("restriction")
public class SiteReader implements IStreamConsumer<Site>
{
	public static Site getSite(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		SiteReader pdr = new SiteReader();
		try
		{
			Site site;
			if(reader instanceof ICatalogReader)
				site = ((ICatalogReader)reader).readFile(IProjectDescription.DESCRIPTION_FILE_NAME, pdr, monitor);
			else
				site = ((IFileReader)reader).readFile(pdr, monitor);
			return site;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public Site consumeStream(IComponentReader fileReader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		try
		{
			monitor.beginTask(null, 1);
			monitor.subTask("Loading site definition");
			URL url = URLUtils.normalizeToURL(streamName);
			SiteContentProvider contentProvider = new SiteFileContentProvider(url);
			ExtendedSiteURLFactory factory = new ExtendedSiteURLFactory();
			DefaultSiteParser parser = new DefaultSiteParser();
			parser.init(factory);
			Site site = (Site)parser.parse(stream);
			IStatus status = parser.getStatus();
			if(status != null)
				throw new CoreException(status);
			site.setSiteContentProvider(contentProvider);
			contentProvider.setSite(site);
			site.resolve(url, url);
			MonitorUtils.worked(monitor, 1);
			return site;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}
}

