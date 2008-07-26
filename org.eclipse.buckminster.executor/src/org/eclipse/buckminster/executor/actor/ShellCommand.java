package org.eclipse.buckminster.executor.actor;

import java.util.HashMap;

/**
 * Provides shell interpreters for various Operating Systems
 * 
 * @author Guillaume CHATELET
 */
public final class ShellCommand
{
	private static final String WINDOWS_CMD = "cmd.exe /C";
	private static final String LINUX_CMD = "sh -c";
	private static final HashMap<String, String> s_shellCommands;

	static
	{
		s_shellCommands = new HashMap<String, String>();
		s_shellCommands.put("Windows 95", "command.com /C");
		s_shellCommands.put("Windows 98", "command.com /C");
		s_shellCommands.put("Windows NT", WINDOWS_CMD);
		s_shellCommands.put("Windows 2000", WINDOWS_CMD);
		s_shellCommands.put("Windows 2003", WINDOWS_CMD);
		s_shellCommands.put("Windows XP", WINDOWS_CMD);
		s_shellCommands.put("Linux", LINUX_CMD);
		s_shellCommands.put("Mac OS X", LINUX_CMD);
		s_shellCommands.put("Linux", LINUX_CMD);
	}

	public static String getShellCommand()
	{
		return s_shellCommands.get(getOsName());
	}

	public static String getOsName()
	{
		return System.getProperty("os.name");
	}
}
