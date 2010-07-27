/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.internal.CacheImpl;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.NullOutputStream;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class TestCache extends TestCase
{
	private static final String zipFile = "http://download.eclipse.org/eclipse/downloads/drops/R-3.6-201006080911/org.eclipse.releng.tools-3.6.zip";
	private static final String zipDigest = "http://download.eclipse.org/eclipse/downloads/drops/R-3.6-201006080911/checksum/org.eclipse.releng.tools-3.6.zip.md5";

	private final File m_cacheFolder;
	private final IProgressMonitor m_monitor = new PrintingMonitor();

	public TestCache(String method, File cacheFolder)
	{
		super(method);
		m_cacheFolder = cacheFolder;
	}

	public static Test suite()
	{
		// Create a temporary space to be used for all tests
		//
		final File cacheFolder;
		try {
			cacheFolder = File.createTempFile("cache-", ".test");
		} catch (IOException e) {
			fail(e.getMessage());
			return null;
		}
		cacheFolder.delete();

		TestSuite suite = new TestSuite()
		{
			@Override
			public void run(TestResult result)
			{
				super.run(result);
				delete(cacheFolder);
			}
		};
		suite.addTest(new TestCache("testPlainCache", cacheFolder));
		suite.addTest(new TestCache("testDigestCache", cacheFolder));
		return suite;
	}

	public void testPlainCache() throws Exception
	{
		ICache c = new CacheImpl(m_cacheFolder);
		URL url = new URL("http://www.eclipse.org/buckminster/downloads.html");
		InputStream input = c.open(url, null, null, null, m_monitor);
		IOUtils.copy(input, NullOutputStream.INSTANCE, null);
		input.close();

		assertTrue("Not up to date when expected", c.isUpToDate(url, null, null, m_monitor));
	}

	public void testDigestCache() throws Exception
	{
		ICache c = new CacheImpl(m_cacheFolder);

		URL url = new URL(zipFile);
		URL digestURL = new URL(zipDigest);
		InputStream input = c.open(url, digestURL, null, "MD5", null, m_monitor);
		IOUtils.copy(input, NullOutputStream.INSTANCE, null);
		input.close();

		assertTrue("Not up to date when expected", c.isUpToDate(url, digestURL, null, "MD5", m_monitor));
	}

	static void delete(File file)
	{
		File[] files = file.listFiles();
		if(files != null)
			for(File child : files)
				delete(child);
		file.delete();
	}
}
