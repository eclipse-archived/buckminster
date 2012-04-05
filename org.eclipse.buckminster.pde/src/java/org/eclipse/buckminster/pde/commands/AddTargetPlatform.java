/*****************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.prefs.TargetPlatformPathHandler;
import org.eclipse.core.runtime.IProgressMonitor;

public class AddTargetPlatform extends WorkspaceCommand {
	private String targetPlatformPath;

	private String targetPlatformName;

	private boolean addAsActive;

	static private final OptionDescriptor OPTION_ACTIVE = new OptionDescriptor('A', "active", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor OPTION_NAME = new OptionDescriptor('N', "name", OptionValueType.REQUIRED); //$NON-NLS-1$

	public String getTargetPlatformName() {
		return targetPlatformName;
	}

	public String getTargetPlatformPath() {
		return targetPlatformPath;
	}

	public boolean isAddAsActive() {
		return addAsActive;
	}

	public void setAddAsActive(boolean importAsActive) {
		this.addAsActive = importAsActive;
	}

	public void setTargetPlatformName(String targetPlatformName) {
		this.targetPlatformName = targetPlatformName;
	}

	public void setTargetPlatformPath(String targetPlatformPath) {
		this.targetPlatformPath = targetPlatformPath;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(OPTION_ACTIVE);
		appendHere.add(OPTION_NAME);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(OPTION_ACTIVE))
			setAddAsActive(true);
		else if (option.is(OPTION_NAME))
			setTargetPlatformName(option.getValue());
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		if (unparsed.length > 1)
			throw new SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_many_arguments);
		if (unparsed.length < 1)
			throw new SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_few_arguments);

		setTargetPlatformPath(unparsed[0]);

		if (getTargetPlatformName() == null)
			throw new SimpleErrorExitException(Messages.target_platform_must_be_named);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		TargetPlatformPathHandler.setTargetPlatform(getTargetPlatformPath(), getTargetPlatformName(), isAddAsActive());
		return 0;
	}
}
