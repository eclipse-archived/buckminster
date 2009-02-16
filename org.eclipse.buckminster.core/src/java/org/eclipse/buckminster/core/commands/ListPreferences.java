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
import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 */
public class ListPreferences extends AbstractCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", //$NON-NLS-1$
			OptionValueType.NONE);

	private boolean m_test = false;

	private String m_pattern = null;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(TEST_DESCRIPTOR);
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
			throw new SimpleErrorExitException(Messages.Too_many_arguments);
		if(len == 1)
			m_pattern = unparsed[0];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		PrintStream out = System.out;
		List<BasicPreferenceHandler> handlers = PreferenceMappingManager.getInstance(m_test).getAllHandlers(m_pattern);
		int top = handlers.size();
		if(top == 0)
		{
			out.print(Messages.No_preferences_found);
			if(m_pattern != null)
				out.format(NLS.bind(Messages.Is_the_pattern_0_correct, m_pattern));
			out.println();
			return 0;
		}

		out.print(Messages.Found);
		out.print(top);
		out.println(Messages.Preferences);
		for(int idx = 0; idx < top; ++idx)
		{
			BasicPreferenceHandler bph = handlers.get(idx);
			out.println(bph.getName());
			if(bph.getDescription() != null)
			{
				out.print(Messages.Description);
				out.println(bph.getDescription());
			}
			if(bph.getKey() != null)
			{
				out.print(Messages.Key);
				out.println(bph.getKey());
			}
			String v = bph.get(null);
			if(v == null)
				out.println(Messages.No_value_set);
			else
			{
				out.print(Messages.Value);
				out.println(v);
			}
		}
		return 0;
	}
}
