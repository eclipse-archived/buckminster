package org.eclipse.buckminster.executor.actor;

import java.util.HashMap;

/**
 * Provides shell interpreters for various Operating Systems
 * 
 * @author Guillaume CHATELET
 */
public final class ShellCommand
{
	private static final String OS_NAME = System.getProperty("os.name");

	private static final HashMap<String, String> s_shellCommands;

	static
	{
		s_shellCommands = new HashMap<String, String>();
		s_shellCommands.put("Windows 95", "command.com /C");
		s_shellCommands.put("Windows NT", "cmd.exe /C");
		s_shellCommands.put("Windows 2000", "cmd.exe /C");
		s_shellCommands.put("Windows 2003", "cmd.exe /C");
		s_shellCommands.put("Windows XP", "cmd.exe /C");
	}

	public static String getShellCommand()
	{
		return s_shellCommands.get(OS_NAME);
	}

	public static String getOsName()
	{
		return OS_NAME;
	}
}
