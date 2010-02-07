/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.parser.ParseResult;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.osgi.util.NLS;

abstract public class AbstractCommand {
	static private final OptionDescriptor helpDescriptor = new OptionDescriptor('?', "help", OptionValueType.NONE); //$NON-NLS-1$

	private String calledUsingName;

	private CommandInfo cmdInfo;

	private boolean addHelpFlags;

	public ProgressProvider getProgressProvider() {
		return new ProgressProvider() {
			@Override
			public IProgressMonitor createMonitor(Job job) {
				return this.getDefaultMonitor();
			}
		};
	}

	public void init(boolean helpFlags) {
		addHelpFlags = helpFlags;
	}

	/**
	 * Internal run command. Assumes that all options has been set.
	 * 
	 * @param cmdName
	 *            The name of the command.
	 * @return the exit code.
	 */
	public int run(String cmdName) throws Exception {
		calledUsingName = cmdName;
		cmdInfo = CommandInfo.getCommand(cmdName);
		return this.run(this.getProgressProvider().getDefaultMonitor());
	}

	protected void beginOptionProcessing() throws Exception {
		// noop
	}

	protected void endOptionProcessing() throws Exception {
		// noop
	}

	protected String getCalledUsingName() throws Exception {
		return calledUsingName;
	}

	protected CommandInfo getCommandInfo() throws Exception {
		return cmdInfo;
	}

	protected String getFullName() throws Exception {
		return cmdInfo.getFullName();
	}

	protected InputStream getHelpStream() throws Exception {
		Class<? extends AbstractCommand> myClass = getClass();
		String helpResource = "/" + myClass.getName().replace('.', '/') + ".help"; //$NON-NLS-1$ //$NON-NLS-2$
		return myClass.getResourceAsStream(helpResource);
	}

	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
	}

	protected void handleOption(Option option) throws Exception {
		// noop
	}

	protected void handleUnparsed(String[] unparsed) throws Exception {
		// noop
	}

	protected void help() throws Exception {
		this.help(this.getHelpStream());
	}

	protected void help(InputStream helpStream) throws Exception {
		if (helpStream == null)
			System.err.println(NLS.bind(Messages.AbstractCommand_Help_missing_for_0, this.getFullName()));
		else {
			try {
				System.out.print(NLS.bind(Messages.AbstractCommand_Help_text_for_0, this.getFullName()));
				IOUtils.copy(helpStream, System.out, null);
			} finally {
				IOUtils.close(helpStream);
			}
			System.out.flush();
		}
	}

	protected abstract int run(IProgressMonitor monitor) throws Exception;

	final int basicRun(String calledAs, CommandInfo commandInfo, String[] commandArgs) throws Exception {
		calledUsingName = calledAs;
		cmdInfo = commandInfo;

		ArrayList<OptionDescriptor> optionDescriptors = new ArrayList<OptionDescriptor>();
		this.getOptionDescriptors(optionDescriptors);
		if (addHelpFlags)
			optionDescriptors.add(helpDescriptor);

		try {
			boolean helpRequested = this.parseOptions(commandArgs, optionDescriptors);
			if (helpRequested) {
				this.help();
				return Headless.EXIT_OK;
			}
			return this.run(this.getProgressProvider().getDefaultMonitor());
		} catch (UsageException e) {
			System.err.println(e.getMessage());
			if (e.isEmitHelp())
				this.help();
			return Headless.EXIT_FAIL;
		}
	}

	private boolean parseOptions(String[] args, List<OptionDescriptor> optionDescriptors) throws Exception {
		ParseResult pr = ParseResult.parse(args, optionDescriptors);
		Option[] options = pr.getOptions();
		boolean helpRequested = false;
		this.beginOptionProcessing();
		int top = options.length;
		for (int idx = 0; idx < top; ++idx) {
			Option option = options[idx];
			if (option.is(helpDescriptor))
				helpRequested = true;
			else
				this.handleOption(option);
		}
		this.endOptionProcessing();
		this.handleUnparsed(pr.getUnparsed());
		return helpRequested;
	}
}
