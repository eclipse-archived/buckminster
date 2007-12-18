/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.io.PrintStream;
import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 */
public class ListPreferences extends AbstractCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test",
		OptionValueType.NONE);

	private boolean m_test = false;

	private String m_pattern = null;

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { TEST_DESCRIPTOR };
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(TEST_DESCRIPTOR))
			m_test = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new SimpleErrorExitException("Too many arguments");
		if(len == 1)
			m_pattern = unparsed[0];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		PrintStream out = System.out;
		List<BasicPreferenceHandler> handlers = PreferenceMappingManager.getInstance(m_test).getAllHandlers(
			m_pattern);
		int top = handlers.size();
		if(top == 0)
		{
			out.print("No preferences found");
			if(m_pattern != null)
				out.format(" (is the pattern '%s' correct?)", m_pattern);
			out.println();
			return 0;
		}

		out.print("Found ");
		out.print(top);
		out.println(" preference(s):");
		for(int idx = 0; idx < top; ++idx)
		{
			BasicPreferenceHandler bph = handlers.get(idx);
			out.println(bph.getName());
			if(bph.getDescription() != null)
			{
				out.print("  Description: ");
				out.println(bph.getDescription());
			}
			if(bph.getKey() != null)
			{
				out.print("  Key        : ");
				out.println(bph.getKey());
			}
			String v = bph.get(null);
			if(v == null)
				out.println("  (no value set)");
			else
			{
				out.print("  Value      : ");
				out.println(v);
			}
		}
		return 0;
	}
}
