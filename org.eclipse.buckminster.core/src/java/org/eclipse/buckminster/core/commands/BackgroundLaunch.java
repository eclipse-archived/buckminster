/*******************************************************************************
 * Copyright (c) 2010 Markus Alexander Kuppe.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Markus Alexander Kuppe (ecf-dev_eclipse.org <at> lemmster <dot> de) - initial API and implementation
 ******************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;

public class BackgroundLaunch extends Launch {
	private static final OptionDescriptor IGNORE_MISSING = new OptionDescriptor('I', "ignoremissing", //$NON-NLS-1$
			OptionValueType.OPTIONAL);

	private boolean ignoreMissing = false;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(IGNORE_MISSING);
		super.getOptionDescriptors(appendHere);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		super.handleOption(option);
		if (option.is(IGNORE_MISSING))
			ignoreMissing = true;
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {

		if (ignoreMissing) {
			String launchName = getLaunchName();
			if (launchName == null)
				return 0;

			final IResource launchFile = ResourcesPlugin.getWorkspace().getRoot().findMember(launchName);
			if ((launchFile == null || launchFile.getType() != IResource.FILE || !launchFile.exists()))
				return 0;
		}
		final ILaunch launch = getLaunch(monitor);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					launch.terminate();
				} catch (DebugException e) {
					e.printStackTrace();
				}
			}
		});

		return 0;
	}
}
