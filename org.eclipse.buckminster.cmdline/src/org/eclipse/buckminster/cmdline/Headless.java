/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.cmdline;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.buckminster.cmdline.parser.CommandLineParser;
import org.eclipse.buckminster.cmdline.parser.InvalidOptionValueException;
import org.eclipse.buckminster.cmdline.parser.ParseResult;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
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
			m_args = args == null ? Trivial.EMPTY_STRING_ARRAY : args;
		}

		public String[] getArgs()
		{
			return m_args;
		}

		public String getName()
		{
			return m_name;
		}

		public String toString()
		{
			int nargs = m_args.length;
			if(nargs == 0)
				return m_name;
			
			StringBuffer bld = new StringBuffer();
			bld.append(m_name);
			for(int idx = 0; idx < nargs; ++idx)
			{
				bld.append(" '");
				bld.append(m_args[idx]);
				bld.append('\'');
			}
			return bld.toString();
		}
	}

	/** The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.buckminster.cmdline";

	static final public int EXIT_FORCED = 2;
	static final public int EXIT_FAIL = 1;
	static final public int EXIT_OK = 0;

	// be a little less user-friendly by displaying nasty stack traces on
	// exception
	//
	private static final OptionDescriptor DISPLAY_STACKTRACE = new OptionDescriptor(null, "displaystacktrace", NONE);

	// help options, treated equally
	//
	private static final OptionDescriptor HELP = new OptionDescriptor('?', "help", OptionValueType.NONE);

	// logging possibilities
	//
	private static final OptionDescriptor LOG_LEVEL = new OptionDescriptor('L', "loglevel", REQUIRED);

	// logging possibilities
	//
	private static final OptionDescriptor FILE = new OptionDescriptor('S', "scriptfile", REQUIRED);

	private final ArrayList m_invocations = new ArrayList();

	private boolean m_displayStackTrace = false;

	private boolean m_help = false;

	private boolean m_usingScript = false;

	private int m_logLevel = Logger.INFO;

	public Object start(IApplicationContext context) throws Exception
	{
		return run(context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
	}

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
			System.err.println("Command canceled");
		}
		catch(InterruptedException e)
		{
			System.err.println("Command was interrupted");
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
			deeplyPrint(e, System.err, new Stack());
		}
		return new Integer(exitValue);
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
			System.out.println("No command provided. Try one of:");
			System.out.println("  buckminster --help");
			System.out.println("  buckminster listcommands");
			System.out.println("  buckminster <command> --help");
			return EXIT_FAIL;
		}

		Logger logger = Buckminster.getLogger();
		for(int idx = 0; idx < top; ++idx)
		{
			Invocation invocation = (Invocation)m_invocations.get(idx);
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

	protected void parse(String[] args) throws Exception
	{
		ArrayList optionArr = new ArrayList();
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
				if("info".equalsIgnoreCase(arg))
					logLevel = Logger.INFO;
				else if("warning".equalsIgnoreCase(arg))
					logLevel = Logger.WARNING;
				else if("error".equalsIgnoreCase(arg))
					logLevel = Logger.ERROR;
				else if("debug".equalsIgnoreCase(arg))
					logLevel = Logger.DEBUG;
				else
					throw new InvalidOptionValueException(option.getName(), option.getValue());
				m_logLevel = logLevel;
			}
			else
				throw new InternalError("Unexpected option");
		}

		String[] unparsed = pr.getUnparsed();
		if(unparsed.length > 0)
		{
			if(scriptFile != null)
				throw new UsageException("The --scriptfile option cannot be combined with a command", true);

			String[] commandArgs = new String[unparsed.length - 1];
			System.arraycopy(unparsed, 1, commandArgs, 0, commandArgs.length);
			m_invocations.add(new Invocation(unparsed[0], commandArgs));
		}
		else if(scriptFile != null)
		{
			InputStream lines = null;
			try
			{
				if(scriptFile.equals("-"))
					lines = System.in;
				else
				{
					URL url = URLUtils.normalizeToURL(scriptFile);
					lines = URLUtils.openStream(url, new NullProgressMonitor());
				}

				LineNumberReader reader = new LineNumberReader(new InputStreamReader(lines));
				String line;
				while((line = reader.readLine()) != null)
				{
					CommandLineParser tokenParser = new CommandLineParser(line);
					if(tokenParser.hasNext())
					{
						String command = (String)tokenParser.next();
						ArrayList tokens = new ArrayList();
						while(tokenParser.hasNext())
							tokens.add(tokenParser.next());
						m_invocations.add(new Invocation(command, (String[])tokens.toArray(new String[tokens.size()])));
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

	protected void help(PrintStream ps) throws Exception
	{
		PrintStream out = System.out;
		InputStream is = getClass().getResourceAsStream("Headless.help");
		if(is == null)
			out.println("Help is not available");
		else
		{
			out.println("Help text for buckminster:");
			try
			{
				IOUtils.copy(is, out);
				out.flush();
			}
			finally
			{
				IOUtils.close(is);
			}
		}
	}

	private void deeplyPrint(IStatus status, PrintStream strm, Stack level)
	{
		strm.print(toLevelString(level));
		String msg = status.getMessage();
		strm.println(msg);
		Throwable cause = status.getException();
		if(cause != null)
		{
			strm.print("Caused by: ");
			if(!m_displayStackTrace || (msg.equals(cause.getMessage()) || msg.equals(cause.toString())))
				deeplyPrint(cause, strm, level);
		}

		if(status.isMultiStatus())
		{

			IStatus[] children = status.getChildren();
			for(int i = 0; i < children.length; i++)
			{
				level.push(new Integer(i + 1));
				deeplyPrint(children[i], strm, level);
				level.pop();
			}
		}
	}

	private void deeplyPrint(CoreException ce, PrintStream strm, Stack level)
	{
		strm.print(toLevelString(level));
		if(m_displayStackTrace)
			ce.printStackTrace(strm);
		deeplyPrint(ce.getStatus(), strm, level);
	}

	private void deeplyPrint(Throwable t, PrintStream strm, Stack level)
	{
		if(t instanceof CoreException)
			deeplyPrint((CoreException)t, strm, level);
		else
		{
			strm.print(toLevelString(level));
			if(m_displayStackTrace)
				t.printStackTrace(strm);
			else
			{
				strm.println(t.toString());
				Throwable cause = t.getCause();
				if(cause != null)
				{
					strm.print("Caused by: ");
					deeplyPrint(cause, strm, level);
				}
			}
		}
	}

	private String toLevelString(Stack level)
	{
		if(level.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer("[0");
		int top = level.size();
		for(int idx = 0; idx < top; ++idx)
		{
			sb.append('.');
			sb.append(level.get(idx));
		}
		sb.append("]");
		return sb.toString();
	}

	public void stop()
	{
		// TODO Auto-generated method stub
		
	}
}
