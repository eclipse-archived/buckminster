package org.eclipse.buckminster.executor;

import org.eclipse.buckminster.executor.actor.ShellCommand;
import org.junit.Assert;
import org.junit.Test;

public class ShellCommandTest
{
	/**
	 * The full list of OS is the following "AIX", "Digital Unix", "FreeBSD", "HP UX", "Irix", "Linux", "Mac OS",
	 * "Mac OS X", "MPE/iX", "Netware 4.11", "OS/2", "Solaris", "Windows 2000", "Windows 95", "Windows 98",
	 * "Windows NT", "Windows XP"
	 * 
	 * But for the moment, only supporting those ones
	 */
	public static final String[] supportedOs = new String[] { "Linux", "Mac OS X", "Windows 2000", "Windows 95",
			"Windows 98", "Windows NT", "Windows XP" };

	@Test
	public void testImplementedShellCommand()
	{
		for(String osName : supportedOs)
		{
			System.setProperty("os.name", osName);
			Assert.assertNotNull(osName + " is not supported", ShellCommand.getShellCommand());
		}
	}
}
