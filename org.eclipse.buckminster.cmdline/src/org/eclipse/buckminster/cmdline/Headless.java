/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.cmdline;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.ArrayList;

import org.eclipse.buckminster.cmdline.parser.CommandLineParser;
import org.eclipse.buckminster.cmdline.parser.InvalidOptionValueException;
import org.eclipse.buckminster.cmdline.parser.ParseResult;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Headless implements IApplication, OptionValueType
{
	public static class Invocation
	{
		private final String m_name;

		private final String[] m_args;

		public Invocation(String name, String[] args)
		{
			m_name = name;
			m_args = args == null
					? Trivial.EMPTY_STRING_ARRAY
					: args;
		}

		public String[] getArgs()
		{
			return m_args;
		}

		public String getName()
		{
			return m_name;
		}

		@Override
		public String toString()
		{
			int nargs = m_args.length;
			if(nargs == 0)
				return m_name;

			StringBuffer bld = new StringBuffer();
			bld.append(m_name);
			for(int idx = 0; idx < nargs; ++idx)
			{
				bld.append(" '"); //$NON-NLS-1$
				bld.append(m_args[idx]);
				bld.append('\'');
			}
			return bld.toString();
		}
	}

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.buckminster.cmdline"; //$NON-NLS-1$

	static final public int EXIT_FORCED = 2;

	static final public int EXIT_FAIL = 1;

	static final public int EXIT_OK = 0;

	// be a little less user-friendly by displaying nasty stack traces on
	// exception
	//
	private static final OptionDescriptor DISPLAY_STACKTRACE = new OptionDescriptor(null, "displaystacktrace", NONE); //$NON-NLS-1$

	// help options, treated equally
	//
	private static final OptionDescriptor HELP = new OptionDescriptor('?', "help", OptionValueType.NONE); //$NON-NLS-1$

	// logging possibilities
	//
	private static final OptionDescriptor LOG_LEVEL = new OptionDescriptor('L', "loglevel", REQUIRED); //$NON-NLS-1$

	// logging possibilities
	//
	private static final OptionDescriptor FILE = new OptionDescriptor('S', "scriptfile", REQUIRED); //$NON-NLS-1$

	private final ArrayList<Invocation> m_invocations = new ArrayList<Invocation>();

	private boolean m_displayStackTrace = false;

	private boolean m_help = false;

	private boolean m_usingScript = false;

	private int m_logLevel = Logger.INFO;

	public Object run(Object objArgs) throws Exception
	{
		Buckminster.setHeadless();
		int exitValue = EXIT_FAIL;
		try
		{
			exitValue = run((String[])objArgs);
		}
		catch(OperationCanceledException e)
		{
			System.err.println(Messages.Headless_Command_canceled);
		}
		catch(InterruptedException e)
		{
			System.err.println(Messages.Headless_Command_was_interrupted);
		}
		catch(SimpleErrorExitException e)
		{
			System.err.println(e.getMessage());
			exitValue = e.getExitValue();
		}
		catch(UsageException e)
		{
			System.err.println(e.getMessage());
			if(e.isEmitHelp())
				help(System.out);
		}
		catch(Throwable e)
		{
			BuckminsterException.deeplyPrint(e, System.err, m_displayStackTrace);
		}
		return new Integer(exitValue);
	}

	public Object start(IApplicationContext context) throws Exception
	{
		return run(context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
	}

	public void stop()
	{
	}

	protected void help(PrintStream ps) throws Exception
	{
		PrintStream out = System.out;
		InputStream is = getClass().getResourceAsStream("Headless.help"); //$NON-NLS-1$
		if(is == null)
			out.println(Messages.Headless_Help_is_not_available);
		else
		{
			out.println(Messages.Headless_Help_text_for_buckminster);
			try
			{
				IOUtils.copy(is, out, null);
				out.flush();
			}
			finally
			{
				IOUtils.close(is);
			}
		}
	}

	protected void parse(String[] args) throws Exception
	{
		ArrayList<OptionDescriptor> optionArr = new ArrayList<OptionDescriptor>();
		optionArr.add(DISPLAY_STACKTRACE);
		optionArr.add(FILE);
		optionArr.add(HELP);
		optionArr.add(LOG_LEVEL);
		ParseResult pr = ParseResult.parse(args, optionArr);
		String scriptFile = null;

		Option[] options = pr.getOptions();
		int top = options.length;
		for(int idx = 0; idx < top; ++idx)
		{
			Option option = options[idx];
			if(option.is(HELP))
				m_help = true;
			else if(option.is(DISPLAY_STACKTRACE))
				m_displayStackTrace = true;
			else if(option.is(FILE))
				scriptFile = option.getValue();
			else if(option.is(LOG_LEVEL))
			{
				int logLevel;
				String arg = option.getValue();
				if("info".equalsIgnoreCase(arg)) //$NON-NLS-1$
					logLevel = Logger.INFO;
				else if("warning".equalsIgnoreCase(arg)) //$NON-NLS-1$
					logLevel = Logger.WARNING;
				else if("error".equalsIgnoreCase(arg)) //$NON-NLS-1$
					logLevel = Logger.ERROR;
				else if("debug".equalsIgnoreCase(arg)) //$NON-NLS-1$
					logLevel = Logger.DEBUG;
				else
					throw new InvalidOptionValueException(option.getName(), option.getValue());
				m_logLevel = logLevel;
			}
			else
				throw new InternalError(Messages.Headless_Unexpected_option);
		}

		String[] unparsed = pr.getUnparsed();
		if(unparsed.length > 0)
		{
			if(scriptFile != null)
				throw new UsageException(Messages.Headless_The_scriptfile_option_cannot_be_combined_with_a_command,
						true);

			String[] commandArgs = new String[unparsed.length - 1];
			System.arraycopy(unparsed, 1, commandArgs, 0, commandArgs.length);
			m_invocations.add(new Invocation(unparsed[0], commandArgs));
		}
		else if(scriptFile != null)
		{
			InputStream lines = null;
			try
			{
				if(scriptFile.equals("-")) //$NON-NLS-1$
					lines = System.in;
				else
					lines = new FileInputStream(scriptFile);

				LineNumberReader reader = new LineNumberReader(new InputStreamReader(lines));
				String line;
				while((line = reader.readLine()) != null)
				{
					CommandLineParser tokenParser = new CommandLineParser(line);
					if(tokenParser.hasNext())
					{
						String command = tokenParser.next();
						ArrayList<String> tokens = new ArrayList<String>();
						while(tokenParser.hasNext())
							tokens.add(tokenParser.next());
						m_invocations.add(new Invocation(command, tokens.toArray(new String[tokens.size()])));
						m_usingScript = true;
					}
				}
			}
			finally
			{
				if(lines != System.in)
					IOUtils.close(lines);
			}
		}
	}

	protected int run(String[] args) throws Exception
	{
		parse(args);

		Logger.setConsoleLevelThreshold(m_logLevel);
		Logger.setEclipseLoggerLevelThreshold(m_logLevel);
		Logger.setEclipseLoggerToConsole(true);

		if(m_help)
		{
			help(System.out);
			return EXIT_OK;
		}

		final IJobManager jobMgr = Job.getJobManager();
		int top = m_invocations.size();
		if(top == 0)
		{
			System.out.println(Messages.Headless_No_command_provided_Try_one_of);
			System.out.println(Messages.Headless_buckminster__help);
			System.out.println(Messages.Headless_buckminster_listcommands);
			System.out.println(Messages.Headless_buckminster_command__help);
			return EXIT_FAIL;
		}

		Logger logger = Buckminster.getLogger();
		for(int idx = 0; idx < top; ++idx)
		{
			Invocation invocation = m_invocations.get(idx);
			String commandName = invocation.getName();
			CommandInfo ci = CommandInfo.getCommand(commandName);
			AbstractCommand cmd = ci.createInstance();
			jobMgr.setProgressProvider(cmd.getProgressProvider());

			if(logger.isDebugEnabled())
				logger.debug(invocation.toString());
			else if(m_usingScript)
				logger.info(invocation.toString());
			int exitValue = cmd.basicRun(commandName, ci, invocation.getArgs());
			if(exitValue != EXIT_OK)
				return exitValue;
		}
		return EXIT_OK;
	}
}
