/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.test.util.progress.test;

import java.io.File;
import java.io.IOException;

import org.eclipse.buckminster.util.progress.StdInOutProgressMonitor;

public class ExternalScriptTest extends BaseTestCase
{

	private static final String OSGI_OS = "osgi.os";

	public void testExternalScript() throws IOException
	{
		CountingProgressMonitor testMonitor = new CountingProgressMonitor();
		StdInOutProgressMonitor client = new StdInOutProgressMonitor(testMonitor);
		client.setPrefix("___");
		String os = System.getProperty(OSGI_OS);
		File shellScript;
		String cmdString;
		if(os.startsWith("win") || os.startsWith("Win"))
		{
			shellScript = getTestData("getting hello.bat", "testData/hello.bat");
			cmdString = "cmd.exe";
		}
		else{
			shellScript = getTestData("getting hello.sh", "testData/hello.sh");
			cmdString = "/bin/sh";
		}
		
		String[] cmd = new String[] { cmdString, shellScript.getCanonicalPath() };
		client.setCmd(cmd);
		client.execute();
		
		assertTrue("Script should use 10 work units", testMonitor.totWork == 10);
		assertTrue("Script should have reported 10 units", testMonitor.workedAmount == 10);
		assertTrue("Script should have called done", testMonitor.doneCounter == 1);
	}
}
