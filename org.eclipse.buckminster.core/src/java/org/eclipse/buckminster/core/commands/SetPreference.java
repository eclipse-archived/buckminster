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

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 */
public class SetPreference extends WorkspaceCommand {
	static private final OptionDescriptor TEST_DESCRIPTOR = new OptionDescriptor(null, "__test", OptionValueType.NONE); //$NON-NLS-1$

	public static void set(String preference, String value) throws Exception {
		SetPreference setpref = new SetPreference();
		setpref.handleUnparsed(new String[] { preference + '=' + value });
		setpref.run("setpref"); //$NON-NLS-1$
	}

	private boolean test = false;

	private String[] assignments;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(TEST_DESCRIPTOR);
		super.getOptionDescriptors(appendHere);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		test = option.is(TEST_DESCRIPTOR);
		super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		if (unparsed.length < 1)
			throw new UsageException(Messages.You_must_provide_at_least_one_preference_assignment);
		assignments = unparsed;
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		PreferenceMappingManager prefManager = PreferenceMappingManager.getInstance(test);
		for (int idx = 0; idx < assignments.length; ++idx) {
			String assignment = assignments[idx];
			int eqIdx = assignment.indexOf('=');
			if (eqIdx < 1 || eqIdx >= assignment.length() - 1)
				throw new UsageException(NLS.bind(Messages._0_is_not_an_assignment, assignment));
			String key = assignment.substring(0, eqIdx);
			String value = assignment.substring(eqIdx + 1);
			prefManager.getHandler(key).set(value);
			System.out.println(NLS.bind(Messages._0_set_to_the_value_1, key, value));
		}
		return 0;
	}
}
