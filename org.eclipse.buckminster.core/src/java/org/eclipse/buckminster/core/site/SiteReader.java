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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteContentProvider;
import org.eclipse.update.core.model.DefaultSiteParser;
import org.eclipse.update.internal.core.ExtendedSiteURLFactory;
import org.eclipse.update.internal.core.SiteFileContentProvider;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A IStreamConsumer responsible for reading and parsing a <code>site.xml</code> type files.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings({ "restriction", "deprecation" })
public class SiteReader implements IStreamConsumer<SaxableSite>
{
	static class ExtendedDefaultSiteParser extends DefaultSiteParser
	{
		private boolean m_atTop = true;

		private String m_mirrorsURL;

		private String m_associateSitesURL;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
		{
			super.startElement(uri, localName, qName, attributes);
			if(m_atTop)
			{
				m_mirrorsURL = TextUtils.notEmptyTrimmedString(attributes.getValue(SaxableSite.ATTR_MIRRORS_URL));
				m_associateSitesURL = TextUtils.notEmptyTrimmedString(attributes.getValue(SaxableSite.ATTR_ASSOCIATE_SITES_URL));
				m_atTop = false;
			}
		}

		String getAssociateSitesURL()
		{
			return m_associateSitesURL;
		}

		String getMirrorsURL()
		{
			return m_mirrorsURL;
		}
	}

	public static SaxableSite getSite(File siteFile) throws CoreException, IOException
	{
		InputStream input = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(siteFile));
			SaxableSite site = parseSite(input, siteFile.toURI().toURL());
			return site;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public static SaxableSite getSite(URL siteURL, IConnectContext cctx) throws CoreException, IOException
	{
		InputStream input = null;
		try
		{
			input = DownloadManager.read(siteURL, cctx);
			SaxableSite site = parseSite(input, siteURL);
			return site;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	private static SaxableSite parseSite(InputStream input, URL url) throws CoreException, IOException
	{
		try
		{
			SiteContentProvider contentProvider = new SiteFileContentProvider(url);
			ExtendedSiteURLFactory factory = new ExtendedSiteURLFactory();
			ExtendedDefaultSiteParser parser = new ExtendedDefaultSiteParser();
			parser.init(factory);
			Site site = (Site)parser.parse(input);
			IStatus status = parser.getStatus();
			if(status != null)
				throw new CoreException(status);
			site.setSiteContentProvider(contentProvider);
			contentProvider.setSite(site);
			site.resolve(url, url);
			return new SaxableSite(site, parser.getMirrorsURL(), parser.getAssociateSitesURL());
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public SaxableSite consumeStream(IComponentReader fileReader, String streamName, InputStream stream,
			IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		try
		{
			monitor.subTask(Messages.Loading_site_definition);
			SaxableSite site = parseSite(stream, URLUtils.normalizeToURL(streamName));
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
}
