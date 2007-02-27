/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline.prefs;

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

	protected void handleOption(Option option) throws Exception
	{
		if(option.is(TEST_DESCRIPTOR))
			m_test = true;
	}

	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new SimpleErrorExitException("Too many arguments");
		if(len == 1)
			m_pattern = unparsed[0];
	}

	protected int run(IProgressMonitor monitor) throws Exception
	{
		BasicPreferenceHandler[] handlers = PreferenceMappingManager.getInstance(m_test).getAllHandlers(
			m_pattern);
		if(handlers.length == 0)
		{
			System.out.print("No preferences found");
			if(m_pattern != null)
				System.out.print(" (is the pattern '" + m_pattern + "' correct?)");
			System.out.println();
		}
		else
		{
			System.out.println("Found " + handlers.length + " preference(s):");
			for(int idx = 0; idx < handlers.length; ++idx)
			{
				BasicPreferenceHandler bph = handlers[idx];
				System.out.println(bph.getName());
				System.out.print("  Description: ");
				System.out.println(bph.getDescription());
				System.out.print("  Key        : ");
				System.out.println(bph.getKey());
				String v = bph.get(null);
				if(v == null)
					System.out.println("  (no value set)");
				else
				{
					System.out.print("  Value      : ");
					System.out.println(v);
				}
			}
		}

		return 0;
	}
}
