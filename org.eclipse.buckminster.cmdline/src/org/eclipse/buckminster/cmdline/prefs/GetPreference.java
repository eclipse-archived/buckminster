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
import org.eclipse.buckminster.cmdline.prefs.BasicPreferenceHandler;
import org.eclipse.buckminster.cmdline.prefs.PreferenceMappingManager;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 * 
 */
public class GetPreference extends AbstractCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE);

	static private final OptionDescriptor DEFAULT_DESCRIPTOR = new OptionDescriptor('d', "default", OptionValueType.REQUIRED);

	static private final OptionDescriptor ONLYVALUE_DESCRIPTOR = new OptionDescriptor(null, "onlyvalue", OptionValueType.NONE);

	private boolean m_test = false;

	private boolean m_onlyValue = false;

	private String m_name;

	private String m_default;

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { TEST_DESCRIPTOR, DEFAULT_DESCRIPTOR, ONLYVALUE_DESCRIPTOR };
	}

	protected void handleOption(Option option) throws Exception
	{
		if (option.is(TEST_DESCRIPTOR))
			m_test = true;
		else if (option.is(DEFAULT_DESCRIPTOR))
			m_default = option.getValue();
		else if (option.is(ONLYVALUE_DESCRIPTOR))
			m_onlyValue = true;
	}

	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if (len > 1)
			throw new SimpleErrorExitException("Too many arguments");
		if (len == 1)
			m_name = unparsed[0];
	}

	protected int run(IProgressMonitor monitor) throws Exception
	{
		if (m_name == null)
			throw new SimpleErrorExitException("You must provide a preference name");

		BasicPreferenceHandler bph = PreferenceMappingManager.getInstance(m_test).getHandler(m_name);
		String v = bph.get(m_default);
		if (!m_onlyValue)
			System.out.print(bph.getName() + "=");
		if (v != null)
			System.out.println(v);
		return 0;
	}
}
