/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.eclipse.buckminster.core.Messages;
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
import org.eclipse.equinox.internal.p2.updatesite.CategoryParser;
import org.eclipse.equinox.internal.p2.updatesite.SiteModel;
import org.xml.sax.SAXException;

/**
 * A IStreamConsumer responsible for reading and parsing a <code>site.xml</code>
 * type files.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings({ "restriction" })
public class SiteReader implements IStreamConsumer<SiteModel> {
	public static SiteModel getSite(File siteFile) throws CoreException, FileNotFoundException {
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(siteFile));
			return parseSite(input, siteFile.toURI());
		} finally {
			IOUtils.close(input);
		}
	}

	public static SiteModel getSite(URL siteURL, IConnectContext cctx) throws CoreException, FileNotFoundException {
		InputStream input = null;
		try {
			input = DownloadManager.read(siteURL, cctx);
			return parseSite(input, URI.create(siteURL.toExternalForm()));
		} finally {
			IOUtils.close(input);
		}
	}

	private static SiteModel parseSite(InputStream input, URI uri) throws CoreException, FileNotFoundException {
		try {
			CategoryParser siteParser = new CategoryParser(uri);
			SiteModel site = siteParser.parse(input);
			IStatus status = siteParser.getStatus();
			if (status != null)
				throw BuckminsterException.wrap(status);
			return site;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} catch (SAXException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public SiteModel consumeStream(IComponentReader fileReader, String streamName, InputStream stream, IProgressMonitor monitor)
			throws CoreException, IOException {
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		try {
			monitor.subTask(Messages.Loading_site_definition);
			SiteModel site = parseSite(stream, URLUtils.normalizeToURI(streamName, false));
			MonitorUtils.worked(monitor, 1);
			return site;
		} finally {
			monitor.done();
		}
	}
}
