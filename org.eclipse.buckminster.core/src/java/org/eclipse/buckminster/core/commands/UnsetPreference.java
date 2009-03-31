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

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 * 
 */
public class UnsetPreference extends WorkspaceCommand
{
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE); //$NON-NLS-1$

	private boolean m_test = false;

	private String[] m_assignments;

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
		if(unparsed.length < 1)
			throw new UsageException(Messages.You_must_provide_at_least_one_preference_to_unset);
		m_assignments = unparsed;
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception
	{
		PreferenceMappingManager prefManager = PreferenceMappingManager.getInstance(m_test);
		for(int idx = 0; idx < m_assignments.length; ++idx)
		{
			String assignment = m_assignments[idx];
			BasicPreferenceHandler bph = prefManager.getHandler(assignment);
			bph.unset();
			System.out.println(NLS.bind(Messages.Unset_the_value_for_0, bph.getName()));
		}
		return 0;
	}
}
