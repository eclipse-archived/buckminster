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

abstract public class AbstractCommand
{
	static private final OptionDescriptor s_helpDescriptor = new OptionDescriptor('?', "help", OptionValueType.NONE);

	private String m_calledUsingName;

	private CommandInfo m_cmdInfo;

	private boolean m_addHelpFlags;

	public ProgressProvider getProgressProvider()
	{
		return new ProgressProvider()
		{
			public IProgressMonitor createMonitor(Job job)
			{
				return this.getDefaultMonitor();
			}
		};		
	}

	public void init(boolean addHelpFlags)
	{
		m_addHelpFlags = addHelpFlags;
	}

	/**
	 * Internal run command. Assumes that all options has been set.
	 * @param cmdName The name of the command.
	 * @return the exit code.
	 */
	public int run(String cmdName) throws Exception
	{
		m_calledUsingName = cmdName;
		m_cmdInfo = CommandInfo.getCommand(cmdName);
		return this.run(this.getProgressProvider().getDefaultMonitor());
	}

	final int basicRun(String calledUsingName, CommandInfo cmdInfo, String[] commandArgs) throws Exception
	{
		m_calledUsingName = calledUsingName;
		m_cmdInfo = cmdInfo;

		ArrayList optionDescriptors = new ArrayList();
		this.getOptionDescriptors(optionDescriptors);
		if(m_addHelpFlags)
			optionDescriptors.add(s_helpDescriptor);

		try
		{
			boolean helpRequested = this.parseOptions(commandArgs, optionDescriptors);
			if(helpRequested)
			{
				this.help();
				return Headless.EXIT_OK;
			}
			return this.run(this.getProgressProvider().getDefaultMonitor());
		}
		catch(UsageException e)
		{
			System.err.println(e.getMessage());
			if(e.isEmitHelp())
				this.help();
			return Headless.EXIT_FAIL;
		}
	}

	protected void beginOptionProcessing() throws Exception
	{
		// noop
	}

	protected void endOptionProcessing() throws Exception
	{
		// noop
	}

	protected String getCalledUsingName() throws Exception
	{
		return m_calledUsingName;
	}

	protected CommandInfo getCommandInfo() throws Exception
	{
		return m_cmdInfo;
	}

	protected String getFullName() throws Exception
	{
		return m_cmdInfo.getFullName();
	}

	protected void getOptionDescriptors(List appendHere) throws Exception
	{
	}

	protected void handleOption(Option option) throws Exception
	{
		// noop
	}

	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		// noop
	}

	protected InputStream getHelpStream() throws Exception
	{
		Class myClass = this.getClass();
		String helpResource = "/" + myClass.getName().replace('.', '/') + ".help";
		return myClass.getResourceAsStream(helpResource);
	}

	protected void help() throws Exception
	{
		this.help(this.getHelpStream());
	}

	protected void help(InputStream helpStream) throws Exception
	{
		if(helpStream == null)
			System.err.println("Help missing for " + this.getFullName());
		else
		{
			try
			{
				System.out.print("Help text for ");
				System.out.print(this.getFullName());
				System.out.println(":");
				IOUtils.copy(helpStream, System.out);
			}
			finally
			{
				IOUtils.close(helpStream);
			}
			System.out.flush();
		}
	}

	protected abstract int run(IProgressMonitor monitor) throws Exception;

	private boolean parseOptions(String[] args, List optionDescriptors) throws Exception
	{
		ParseResult pr = ParseResult.parse(args, optionDescriptors);
		Option[] options = pr.getOptions();
		boolean helpRequested = false;
		this.beginOptionProcessing();
		int top = options.length;
		for(int idx = 0; idx < top; ++idx)
		{
			Option option = options[idx];
			if (option.is(s_helpDescriptor))
				helpRequested = true;
			else
				this.handleOption(option);
		}
		this.endOptionProcessing();
		this.handleUnparsed(pr.getUnparsed());
		return helpRequested;
	}
}
