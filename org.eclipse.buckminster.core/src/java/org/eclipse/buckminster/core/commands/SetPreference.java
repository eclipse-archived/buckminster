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
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author kolwing
 */
public class SetPreference extends WorkspaceCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE);

	private boolean m_test = false;

	private String[] m_assignments;

	protected OptionDescriptor[] getOptionDescriptors() throws Exception
	{
		return new OptionDescriptor[] { TEST_DESCRIPTOR };
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		m_test = option.is(TEST_DESCRIPTOR);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		if(unparsed.length < 1)
			throw new UsageException("You must provide at least one preference assignment, e.g. 'abc=123'");
		m_assignments = unparsed;
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		PreferenceMappingManager prefManager = PreferenceMappingManager.getInstance(m_test);
		for(int idx = 0; idx < m_assignments.length; ++idx)
		{
			String assignment = m_assignments[idx];
			int eqIdx = assignment.indexOf('=');
			if(eqIdx < 1 || eqIdx >= assignment.length() - 1)
				throw new UsageException(assignment + " is not an assignment");
			String key = assignment.substring(0, eqIdx);
			String value = assignment.substring(eqIdx + 1);
			prefManager.getHandler(key).set(value);
			System.out.println(key + " set to the value '" + value + "'");
		}
		return 0;
	}
}
