/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

import java.util.ArrayList;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class CommandInfo
{
	public static final int NORMAL = 0;

	public static final int DEPRECATED = 1;

	public static final int HIDDEN = 2;

	public static final int DISABLED = 3;

	static private final String COMMAND_EXTPOINT = "org.eclipse.buckminster.cmdline.commands";

	static private final String ALIAS_ELEMENTS = "alias";

	static private final String CLASS_ATTRIBUTE = "class";

	static private final String NAME_ATTRIBUTE = "name";

	static private final String DEPRECATED_BY_ATTRIBUTE = "deprecatedBy";

	static private final char PERIOD_CHARACTER = '.';

	static private final String STATUS_ATTRIBUTE = "status";

	static private final String ADD_HELP_FLAGS_ATTRIBUTE = "addhelpflags";

	static private CommandInfo[] s_commandInfos;

	public static CommandInfo getCommand(String commandName) throws UsageException
	{
		ArrayList<CommandInfo> matches = new ArrayList<CommandInfo>();
		CommandInfo[] implementors = CommandInfo.getImplementors();
		int top = implementors.length;
		StringBuffer sb = new StringBuffer();
		for(int idx = 0; idx < top; ++idx)
		{
			CommandInfo ci = implementors[idx];
			if(ci.getStatus() == CommandInfo.DISABLED)
				continue;

			String[] allNames = ci.getAllNames();
			for(int nidx = 0; nidx < allNames.length; ++nidx)
			{
				String[] parts = allNames[nidx].split("\\.");
				int len = parts.length;
				for(int i = 0; i < len; i++)
				{
					sb.setLength(0);
					for(int j = len - i - 1; j < len; j++)
					{
						if(sb.length() != 0)
							sb.append('.');
						sb.append(parts[j]);
					}
					if(sb.toString().equals(commandName))
						matches.add(ci);
				}
			}
		}

		int foundMatches = matches.size();
		if(foundMatches == 0)
			throw new UsageException("Command '" + commandName + "' not found.");

		if(foundMatches > 1)
		{
			sb.setLength(0);
			sb.append("Multiple matches for '");
			sb.append(commandName);
			sb.append("':");
			for(int idx = 0; idx < foundMatches; ++idx)
			{
				CommandInfo ci = matches.get(idx);
				sb.append(ci.getFullName());
				sb.append(" (implemented by class: ");
				sb.append(ci.getImplementingClass());
				sb.append(')');
				if(idx < foundMatches - 1)
					sb.append(", ");
			}
			throw new UsageException(sb.toString());
		}

		CommandInfo ci = matches.get(0);
		if(ci.getStatus() == DEPRECATED)
		{
			sb.setLength(0);
			sb.append("Command ");
			sb.append(ci.getName());
			sb.append(" is deprecated");

			String by = ci.getDeprecatedBy();
			if(by != null)
			{
				sb.append(", use ");
				sb.append(by);
				sb.append(" instead");
			}
			Buckminster.getLogger().warning(sb.toString());
		}
		return ci;
	}

	static public synchronized CommandInfo[] getImplementors()
	{
		if(s_commandInfos == null)
		{
			IExtensionRegistry er = Platform.getExtensionRegistry();
			IConfigurationElement[] elems = er.getConfigurationElementsFor(COMMAND_EXTPOINT);

			s_commandInfos = new CommandInfo[elems.length];
			for(int i = 0; i < elems.length; i++)
				s_commandInfos[i] = new CommandInfo(elems[i]);
		}

		return s_commandInfos;
	}

	private static int parseCommandStatus(String statusString)
	{
		int status;
		if("normal".equalsIgnoreCase(statusString))
			status = NORMAL;
		else if("deprecated".equalsIgnoreCase(statusString))
			status = DEPRECATED;
		else if("hidden".equalsIgnoreCase(statusString))
			status = HIDDEN;
		else if("disabled".equalsIgnoreCase(statusString))
			status = DISABLED;
		else
			throw new IllegalArgumentException(statusString + " is not a valid command status");
		return status;
	}

	private final String[] m_aliases;

	private final IConfigurationElement m_cfgElement;

	private final String m_deprecatedBy;

	private final String m_name;

	private final String[] m_allNames;

	private final String m_namespace;

	private final int m_status;

	private CommandInfo(IConfigurationElement cfgElement)
	{
		m_cfgElement = cfgElement;
		m_status = parseCommandStatus(cfgElement.getAttribute(STATUS_ATTRIBUTE));

		IExtension ext = m_cfgElement.getDeclaringExtension();
		StringBuffer ns = new StringBuffer(ext.getNamespaceIdentifier());

		String name = cfgElement.getAttribute(NAME_ATTRIBUTE);
		int period = name.lastIndexOf(PERIOD_CHARACTER);
		if(period != -1)
		{
			ns.append(PERIOD_CHARACTER).append(name.substring(0, period));
			name = name.substring(period + 1);
		}
		m_namespace = ns.toString();
		m_name = name;

		IConfigurationElement[] aliasElements = cfgElement.getChildren(ALIAS_ELEMENTS);
		m_aliases = new String[aliasElements.length];
		for(int i = 0; i < m_aliases.length; i++)
		{
			String alias = aliasElements[i].getAttribute(NAME_ATTRIBUTE);
			if(alias.indexOf(PERIOD_CHARACTER) != -1)
				throw new IllegalCommandAliasException(alias);
			m_aliases[i] = alias;
		}

		m_allNames = new String[1 + m_aliases.length];
		m_allNames[0] = m_namespace + PERIOD_CHARACTER + m_name;
		for(int i = 0; i < m_aliases.length; i++)
			m_allNames[i + 1] = m_namespace + PERIOD_CHARACTER + m_aliases[i];

		m_deprecatedBy = cfgElement.getAttribute(DEPRECATED_BY_ATTRIBUTE);
	}

	public AbstractCommand createInstance() throws CoreException
	{
		AbstractCommand cmd = (AbstractCommand)m_cfgElement.createExecutableExtension(CLASS_ATTRIBUTE);
		cmd.init(Boolean.valueOf(m_cfgElement.getAttribute(ADD_HELP_FLAGS_ATTRIBUTE)).booleanValue());
		return cmd;
	}

	public String[] getAliases()
	{
		return m_aliases;
	}

	public String[] getAllNames()
	{
		return m_allNames;
	}

	public IConfigurationElement getCfgElement()
	{
		return m_cfgElement;
	}

	public String getDeprecatedBy()
	{
		return m_deprecatedBy;
	}

	public String getFullName()
	{
		return m_allNames[0];
	}

	public String getImplementingClass()
	{
		return m_cfgElement.getAttribute(CLASS_ATTRIBUTE);
	}

	public String getName()
	{
		return m_name;
	}

	public String getNamespace()
	{
		return m_namespace;
	}

	public int getStatus()
	{
		return m_status;
	}
}
