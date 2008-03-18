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
import java.io.OutputStream;
import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.buckminster.cache.download.FileReader;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 *
 */
public class TestFileTransfer extends TestCase
{
	public void testFileTransfer() throws Exception
	{
		FileReader x = new FileReader();
		File temp = File.createTempFile("filetransfer", ".test");
		OutputStream out = new FileOutputStream(temp);
		temp.deleteOnExit();

		x.readURL(new URL("http://www.eclipse.org/buckminster/downloads.html"), out, new IProgressMonitor()
		{
			private boolean m_cancelled;
		
			public void beginTask(String name, int totalWork)
			{
				System.out.format("-- beginTask(%s, %d)%n", name, Integer.valueOf(totalWork));
			}

			public void done()
			{
				System.out.println("-- done()");
			}

			public void internalWorked(double work)
			{
			}

			public boolean isCanceled()
			{
				return m_cancelled;
			}

			public void setCanceled(boolean value)
			{
				m_cancelled = value;
			}

			public void setTaskName(String name)
			{
			}

			public void subTask(String name)
			{
				System.out.format("-- subTask(%s)%n", name);
			}

			public void worked(int work)
			{
				System.out.format("-- worked(%d)%n", Integer.valueOf(work));
			}
		});
		out.close();
		assertTrue("File is empty", temp.length() > 0);
	}
}
