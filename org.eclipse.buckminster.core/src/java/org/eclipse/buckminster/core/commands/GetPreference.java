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

import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 * 
 */
public class GetPreference extends AbstractCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor DEFAULT_DESCRIPTOR = new OptionDescriptor('d', "default", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	static private final OptionDescriptor ONLYVALUE_DESCRIPTOR = new OptionDescriptor(null, "onlyvalue", //$NON-NLS-1$
			OptionValueType.NONE);

	private boolean m_test = false;

	private boolean m_onlyValue = false;

	private String m_name;

	private String m_default;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(TEST_DESCRIPTOR);
		appendHere.add(DEFAULT_DESCRIPTOR);
		appendHere.add(ONLYVALUE_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(TEST_DESCRIPTOR))
			m_test = true;
		else if(option.is(DEFAULT_DESCRIPTOR))
			m_default = option.getValue();
		else if(option.is(ONLYVALUE_DESCRIPTOR))
			m_onlyValue = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new SimpleErrorExitException(Messages.Too_many_arguments);
		if(len == 1)
			m_name = unparsed[0];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_name == null)
			throw new SimpleErrorExitException(Messages.You_must_provide_a_preference_name);

		BasicPreferenceHandler bph = PreferenceMappingManager.getInstance(m_test).getHandler(m_name);
		String v = bph.get(m_default);
		if(!m_onlyValue)
			System.out.print(bph.getName() + "="); //$NON-NLS-1$
		if(v != null)
			System.out.println(v);
		return 0;
	}
}
