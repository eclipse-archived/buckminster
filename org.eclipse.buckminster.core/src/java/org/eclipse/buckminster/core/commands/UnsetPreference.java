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

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 * 
 */
public class UnsetPreference extends WorkspaceCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE);

	private boolean m_test = false;

	private String m_name;

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { TEST_DESCRIPTOR };
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if (option.is(TEST_DESCRIPTOR))
			m_test = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if (len > 1)
			throw new SimpleErrorExitException("Too many arguments");
		if (len == 1)
			m_name = unparsed[0];
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		if (m_name == null)
			throw new UsageException("You must provide a preference name");

		BasicPreferenceHandler bph = PreferenceMappingManager.getInstance(m_test).getHandler(m_name);
		bph.unset();
		System.out.println("Unset the value for " + bph.getName());
		return 0;
	}
}
