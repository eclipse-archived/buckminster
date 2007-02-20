/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline.internal;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.CommandInfo;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.parser.InvalidOptionValueException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;

public class ListCommands extends AbstractCommand
{
	private static final int LONG = 1;
	private static final int NORMAL = 2;
	private static final int SHORT = 3;

	static private final OptionDescriptor DISABLED_OPT = new OptionDescriptor(null, "__disabled", OptionValueType.NONE);

	static private final OptionDescriptor HIDDEN_OPT = new OptionDescriptor(null, "__hidden", OptionValueType.NONE);

	static private final OptionDescriptor STYLE_OPT = new OptionDescriptor(null, "style", OptionValueType.REQUIRED);

	private boolean m_showDisabled = false;

	private boolean m_showHidden = false;

	private int m_style = NORMAL;

	private static int parseStyle(String styleStr) throws InvalidOptionValueException
	{
		int style;
		if("long".equalsIgnoreCase(styleStr))
			style = LONG;
		else if("normal".equalsIgnoreCase(styleStr))
			style = NORMAL;
		else if("short".equalsIgnoreCase(styleStr))
			style = SHORT;
		else
			throw new InvalidOptionValueException(STYLE_OPT.getLongName(), styleStr);
		return style;
	}

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { HIDDEN_OPT, DISABLED_OPT, STYLE_OPT };
	}

	protected void handleOption(Option option) throws Exception
	{
		if (option.is(HIDDEN_OPT))
			m_showHidden = true;
		else if (option.is(DISABLED_OPT))
			m_showDisabled = true;
		else if (option.is(STYLE_OPT))
			m_style = parseStyle(option.getValue().toUpperCase());
	}

	protected int run(IProgressMonitor monitor) throws Exception
	{
		CommandInfo[] implementors = CommandInfo.getImplementors();
		switch (m_style)
		{
		case SHORT:
			this.showShort(implementors);
			break;
		case NORMAL:
			this.showNormal(implementors);
			break;
		default:
			this.showLong(implementors);
		}
		MonitorUtils.complete(monitor);
		return 0;
	}

	private boolean shouldShow(CommandInfo cmdInfo)
	{
		int s = cmdInfo.getStatus();
		if (s == CommandInfo.HIDDEN && !m_showHidden)
			return false;
		if (s == CommandInfo.DISABLED && !m_showDisabled)
			return false;
		return true;
	}

	private void showLong(CommandInfo[] implementors)
	{
		PrintStream out = System.out;
		out.println("Available commands by namespace:");
		SortedMap implementorsByNamespace = this.sortImplementorsByNamespace(implementors);
		Iterator allInfosItor = implementorsByNamespace.entrySet().iterator();
		while(allInfosItor.hasNext())
		{
			Map.Entry entry = (Map.Entry)allInfosItor.next();
			List implementorsForNamespace = (List)entry.getValue();
			Collections.sort(implementorsForNamespace, new Comparator()
			{
				public int compare(Object o1, Object o2)
				{
					return ((CommandInfo)o1).getName().compareTo(((CommandInfo)o2).getName());
				}
			});

			String namespace = (String)entry.getKey();
			int top = implementorsForNamespace.size();
			for(int idx = 0; idx < top; ++idx)
			{
				CommandInfo ci = (CommandInfo)implementorsForNamespace.get(idx);
				if (this.shouldShow(ci))
				{
					if (namespace != null)
					{
						out.print("  (");
						out.print(namespace);
						out.println(")");
						namespace = null;
					}
					out.print("    ");
					out.print(ci.getName());
					String[] aliases = ci.getAliases();
					if (aliases.length > 0)
					{
						out.print(" (");
						out.print(aliases.length);
						out.print(" alias");
						out.println(aliases.length > 1 ? "es)" : ")");
						Arrays.sort(aliases);
						for(int i = 0; i < aliases.length; ++i)
						{
							out.print("      ");
							out.println(aliases[i]);
						}
					}
					else
						out.println();
				}
			}
		}
	}

	private void showNormal(CommandInfo[] implementors)
	{
		PrintStream out = System.out;
		out.println("Available commands including aliases:");
		ArrayList names = new ArrayList();
		for(int idx = 0; idx < implementors.length; ++idx)
		{
			CommandInfo cmdInfo = implementors[idx];
			if (this.shouldShow(cmdInfo))
			{
				String[] allNames = cmdInfo.getAllNames();
				for(int i = 0; i < allNames.length; ++i)
					names.add(allNames[i]);
			}
		}
		Collections.sort(names);
		int top = names.size();
		for(int idx = 0; idx < top; ++idx)
		{
			out.print("  ");
			out.println(names.get(idx));
		}
	}

	private void showShort(CommandInfo[] implementors)
	{
		PrintStream out = System.out;
		ArrayList names = new ArrayList();
		for(int idx = 0; idx < implementors.length; ++idx)
		{
			CommandInfo cmdInfo = implementors[idx];
			if (this.shouldShow(cmdInfo))
				names.add(cmdInfo.getFullName());
		}
		Collections.sort(names);
		int top = names.size();
		for(int idx = 0; idx < top; ++idx)
			out.println(names.get(idx));
	}

	private SortedMap sortImplementorsByNamespace(CommandInfo[] implementors)
	{
		SortedMap implementorsByNamespace = new TreeMap();

		for(int idx = 0; idx < implementors.length; ++idx)
		{
			CommandInfo ci = implementors[idx];
			String namespace = ci.getNamespace();
			ArrayList ciList = (ArrayList)implementorsByNamespace.get(namespace);
			if (ciList == null)
				ciList = new ArrayList();
			ciList.add(ci);
			implementorsByNamespace.put(namespace, ciList);
		}
		return implementorsByNamespace;
	}
}

