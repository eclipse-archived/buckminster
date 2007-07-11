/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.site;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteContentProvider;
import org.eclipse.update.core.model.DefaultSiteParser;
import org.eclipse.update.internal.core.ExtendedSiteURLFactory;
import org.eclipse.update.internal.core.SiteFileContentProvider;
import org.xml.sax.SAXException;


/**
 * A IStreamConsumer responsible for reading and parsing a
 * <code>site.xml</code> type files.
 *
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SiteReader implements IStreamConsumer<Site>
{
	public static Site getSite(URL siteURL, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(URLUtils.openStream(siteURL, monitor));
			Site site = parseSite(input, siteURL);
			MonitorUtils.worked(monitor, 1);
			return site;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}

	public static Site getSite(File siteFile, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(siteFile));
			Site site = parseSite(input, siteFile.toURI().toURL());
			MonitorUtils.worked(monitor, 1);
			return site;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
			monitor.done();
		}
	}

	public Site consumeStream(IComponentReader fileReader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		try
		{
			monitor.subTask("Loading site definition");
			Site site = parseSite(stream, URLUtils.normalizeToURL(streamName));
			MonitorUtils.worked(monitor, 1);
			return site;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	private static Site parseSite(InputStream input, URL url) throws CoreException
	{
		try
		{
			SiteContentProvider contentProvider = new SiteFileContentProvider(url);
			ExtendedSiteURLFactory factory = new ExtendedSiteURLFactory();
			DefaultSiteParser parser = new DefaultSiteParser();
			parser.init(factory);
			Site site = (Site)parser.parse(input);
			IStatus status = parser.getStatus();
			if(status != null)
				throw new CoreException(status);
			site.setSiteContentProvider(contentProvider);
			contentProvider.setSite(site);
			site.resolve(url, url);
			return site;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}

