/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.cache.test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.buckminster.cache.Cache;
import org.eclipse.buckminster.cache.ICache;
import org.eclipse.buckminster.cache.unpack.NullOutputStream;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class TestCache extends TestCase
{
	private static final String zipFile = "http://www.eclipse.org/downloads/download.php?file=/eclipse/downloads/drops/R-3.3.2-200802211800/eclipse-examples-3.3.2-win32.zip&r=1";
	private static final String zipDigest = "http://download.eclipse.org/eclipse/downloads/drops/R-3.3.2-200802211800/checksum/eclipse-examples-3.3.2-win32.zip.md5";

	private final File m_cacheFolder;
	private final IProgressMonitor m_monitor = new PrintingMonitor();

	public TestCache(String method, File cacheFolder)
	{
		super(method);
		m_cacheFolder = cacheFolder;
	}

	public static Test suite() throws Exception
	{
		// Create a temporary space to be used for all tests
		//
		final File cacheFolder = File.createTempFile("cache-", ".test");
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
		ICache c = new Cache(m_cacheFolder);
		URL url = new URL("http://www.eclipse.org/buckminster/downloads.html");
		InputStream input = c.open(url, null, m_monitor);
		IOUtils.copy(input, NullOutputStream.INSTANCE, null);
		input.close();

		assertTrue("Not up to date when expected", c.isUpToDate(url, null, m_monitor));
	}

	public void testDigestCache() throws Exception
	{
		ICache c = new Cache(m_cacheFolder);

		URL url = new URL(zipFile);
		URL digestURL = new URL(zipDigest);
		InputStream input = c.open(url, digestURL, "MD5", m_monitor);
		IOUtils.copy(input, NullOutputStream.INSTANCE, null);
		input.close();

		assertTrue("Not up to date when expected", c.isUpToDate(url, digestURL, "MD5", m_monitor));
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
