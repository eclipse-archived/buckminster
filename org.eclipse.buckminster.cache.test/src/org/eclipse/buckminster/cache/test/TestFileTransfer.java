/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.cache.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.buckminster.cache.download.FileReader;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;

/**
 * @author Thomas Hallgren
 * 
 */
public class TestFileTransfer extends TestCase
{
	private String testURL = "http://www.eclipse.org/buckminster/downloads.html";

	TestFileTransfer(String methodName)
	{
		super(methodName);
	}

	public static Test suite() throws Exception
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new TestFileTransfer("testFileInfo"));
		suite.addTest(new TestFileTransfer("testReadInto"));
		suite.addTest(new TestFileTransfer("testRead"));
		return suite;
	}

	public void testFileInfo() throws Exception
	{
		FileReader x = new FileReader();
		checkFileInfo(x.readInfo(new URL(testURL)));
	}

	public void testReadInto() throws Exception
	{
		File temp = File.createTempFile("filetransfer", ".test");
		try
		{
			OutputStream out = new FileOutputStream(temp);
			temp.deleteOnExit();

			FileReader x = new FileReader();
			x.readInto(new URL(testURL), out, new PrintingMonitor());
			out.close();
			assertTrue("File is empty", temp.length() > 0);
			checkFileInfo(x.getLastFileInfo());
		}
		finally
		{
			temp.delete();
		}
	}

	public void testRead() throws Exception
	{
		File temp = File.createTempFile("filetransfer", ".test");
		try
		{
			OutputStream out = new FileOutputStream(temp);
			temp.deleteOnExit();

			FileReader x = new FileReader();
			InputStream in = x.read(new URL(testURL));
			IOUtils.copy(in, out, null);
			out.close();
			assertTrue("File is empty", temp.length() > 0);
			checkFileInfo(x.getLastFileInfo());
		}
		finally
		{
			temp.delete();
		}
	}

	private static void checkFileInfo(IFileInfo fileInfo)
	{
		assertTrue("Missing file name", fileInfo.getName() != null);
		assertTrue("Missing last modified", fileInfo.getLastModified() != 0L);
		assertTrue("Missing size", fileInfo.getSize() > 0L);
	}
}
