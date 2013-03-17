/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.cmdline;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.cmdline.parser.CommandLineParser;
import org.eclipse.buckminster.cmdline.parser.InvalidOptionValueException;
import org.eclipse.buckminster.cmdline.parser.ParseResult;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.util.NLS;

/**
 * This class controls all aspects of the application's execution
 */
public class Headless implements IApplication, OptionValueType {
	public static class Invocation {
		private final String name;

		private final String[] args;

		public Invocation(String name, String[] args) {
			this.name = name;
			this.args = args == null ? Trivial.EMPTY_STRING_ARRAY : args;
		}

		public String[] getArgs() {
			return args;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			int nargs = args.length;
			if (nargs == 0)
				return name;

			StringBuffer bld = new StringBuffer();
			bld.append(name);
			for (int idx = 0; idx < nargs; ++idx) {
				bld.append(" '"); //$NON-NLS-1$
				bld.append(args[idx]);
				bld.append('\'');
			}
			return bld.toString();
		}
	}

	enum LogType {
		console, ant, eclipse
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

	static final OptionDescriptor DEFINE_DESCRIPTOR = new OptionDescriptor('D', "define", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	static final OptionDescriptor PROPERTIES_DESCRIPTOR = new OptionDescriptor('P', "properties", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	static final Pattern DEFINE_PATTERN = Pattern.compile("^([^=]+)(?:=(.+))?$"); //$NON-NLS-1$

	private final ArrayList<Invocation> invocations = new ArrayList<Invocation>();

	private Properties props;

	private boolean displayStackTrace = false;

	private boolean help = false;

	private boolean usingScript = false;

	private int logLevel = Logger.INFO;

	private int antLogLevel = -1;

	private static final Pattern commaSplit = Pattern.compile(","); //$NON-NLS-1$

	public void addProperty(String key, String value) {
		if (props == null)
			props = new Properties(System.getProperties());
		props.put(key, value);
	}

	public Object run(Object objArgs) throws Exception {
		Buckminster.setHeadless();
		int exitValue = EXIT_FAIL;
		try {
			exitValue = run((String[]) objArgs);
		} catch (OperationCanceledException e) {
			System.err.println(Messages.Headless_Command_canceled);
		} catch (InterruptedException e) {
			System.err.println(Messages.Headless_Command_was_interrupted);
		} catch (SimpleErrorExitException e) {
			System.err.println(e.getMessage());
			exitValue = e.getExitValue();
		} catch (UsageException e) {
			System.err.println(e.getMessage());
			if (e.isEmitHelp())
				help(System.out);
		} catch (Throwable e) {
			BuckminsterException.deeplyPrint(e, System.err, displayStackTrace);
		}
		return new Integer(exitValue);
	}

	@Override
	public Object start(IApplicationContext context) throws Exception {
		return run(context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
	}

	@Override
	public void stop() {
	}

	protected void help(PrintStream ps) throws Exception {
		PrintStream out = System.out;
		InputStream is = getClass().getResourceAsStream("Headless.help"); //$NON-NLS-1$
		if (is == null)
			out.println(Messages.Headless_Help_is_not_available);
		else {
			out.println(Messages.Headless_Help_text_for_buckminster);
			try {
				IOUtils.copy(is, out, null);
				out.flush();
			} finally {
				IOUtils.close(is);
			}
		}
	};

	protected void parse(String[] args) throws Exception {
		ArrayList<OptionDescriptor> optionArr = new ArrayList<OptionDescriptor>();
		optionArr.add(DISPLAY_STACKTRACE);
		optionArr.add(FILE);
		optionArr.add(HELP);
		optionArr.add(LOG_LEVEL);
		optionArr.add(DEFINE_DESCRIPTOR);
		optionArr.add(PROPERTIES_DESCRIPTOR);
		ParseResult pr = ParseResult.parse(args, optionArr);
		String scriptFile = null;

		Option[] options = pr.getOptions();
		int top = options.length;
		for (int idx = 0; idx < top; ++idx) {
			Option option = options[idx];
			if (option.is(HELP))
				help = true;
			else if (option.is(DISPLAY_STACKTRACE))
				displayStackTrace = true;
			else if (option.is(FILE))
				scriptFile = option.getValue();
			else if (option.is(LOG_LEVEL)) {
				int level;
				for (String levelDecl : commaSplit.split(option.getValue())) {
					LogType logType = LogType.console;
					int eqIdx = levelDecl.indexOf('=');
					if (eqIdx > 0) {
						String logTypeName = levelDecl.substring(0, eqIdx);
						if ("console".equalsIgnoreCase(logTypeName)) //$NON-NLS-1$
							logType = LogType.console;
						else if ("ant".equalsIgnoreCase(logTypeName)) //$NON-NLS-1$
							logType = LogType.ant;
						else
							throw new InvalidOptionValueException(option.getName(), option.getValue());
						levelDecl = levelDecl.substring(eqIdx + 1);
					}
					if ("info".equalsIgnoreCase(levelDecl)) //$NON-NLS-1$
						level = Logger.INFO;
					else if ("warning".equalsIgnoreCase(levelDecl)) //$NON-NLS-1$
						level = Logger.WARNING;
					else if ("error".equalsIgnoreCase(levelDecl)) //$NON-NLS-1$
						level = Logger.ERROR;
					else if ("debug".equalsIgnoreCase(levelDecl)) //$NON-NLS-1$
						level = Logger.DEBUG;
					else
						throw new InvalidOptionValueException(option.getName(), option.getValue());

					switch (logType) {
						case console:
							logLevel = level;
							break;
						case ant:
							antLogLevel = level;
					}
				}
			} else if (option.is(DEFINE_DESCRIPTOR)) {
				String v = option.getValue();
				Matcher m = DEFINE_PATTERN.matcher(v);
				if (!m.matches())
					throw new IllegalArgumentException(NLS.bind(Messages.Not_a_key_value_string_0, v));
				String key = m.group(1);
				String value = m.group(2) == null ? "" //$NON-NLS-1$
						: m.group(2);
				addProperty(key, value);
			} else if (option.is(PROPERTIES_DESCRIPTOR)) {
				String v = option.getValue();
				InputStream input = null;
				try {
					URL propsURL = URLUtils.normalizeToURL(v);
					input = new BufferedInputStream(propsURL.openStream());
					if (props == null)
						props = new Properties(System.getProperties());
					props.load(input);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(NLS.bind(Messages.Invalid_URL_or_Path_0, v));
				} finally {
					IOUtils.close(input);
				}
			} else
				throw new InternalError(Messages.Headless_Unexpected_option);
		}

		if (props != null)
			System.setProperties(props);

		String[] unparsed = pr.getUnparsed();
		if (unparsed.length > 0) {
			if (scriptFile != null)
				throw new UsageException(Messages.Headless_The_scriptfile_option_cannot_be_combined_with_a_command, true);

			String[] commandArgs = new String[unparsed.length - 1];
			System.arraycopy(unparsed, 1, commandArgs, 0, commandArgs.length);
			invocations.add(new Invocation(unparsed[0], commandArgs));
		} else if (scriptFile != null) {
			InputStream lines = null;
			try {
				if (scriptFile.equals("-")) //$NON-NLS-1$
					lines = System.in;
				else
					lines = new FileInputStream(scriptFile);

				LineNumberReader reader = new LineNumberReader(new InputStreamReader(lines));
				String line;
				while ((line = reader.readLine()) != null) {
					CommandLineParser tokenParser = new CommandLineParser(line);
					if (tokenParser.hasNext()) {
						String command = tokenParser.next();
						ArrayList<String> tokens = new ArrayList<String>();
						while (tokenParser.hasNext())
							tokens.add(tokenParser.next());
						invocations.add(new Invocation(command, tokens.toArray(new String[tokens.size()])));
						usingScript = true;
					}
				}
			} finally {
				if (lines != System.in)
					IOUtils.close(lines);
			}
		}
	}

	protected int run(String[] args) throws Exception {
		int currentAntLogLevel = BuckminsterPreferences.getLogLevelAntLogger();
		Properties sysProps = System.getProperties();
		try {
			parse(args);

			Logger.setConsoleLevelThreshold(logLevel);
			Logger.setEclipseLoggerLevelThreshold(logLevel);
			Logger.setEclipseLoggerToConsole(true);

			if (antLogLevel != -1)
				BuckminsterPreferences.setLogLevelAntLogger(antLogLevel);

			if (help) {
				help(System.out);
				return EXIT_OK;
			}

			final IJobManager jobMgr = Job.getJobManager();
			int top = invocations.size();
			if (top == 0) {
				System.out.println(Messages.Headless_No_command_provided_Try_one_of);
				System.out.println(Messages.Headless_buckminster__help);
				System.out.println(Messages.Headless_buckminster_listcommands);
				System.out.println(Messages.Headless_buckminster_command__help);
				return EXIT_FAIL;
			}

			Logger logger = Buckminster.getLogger();

			for (int idx = 0; idx < top; ++idx) {
				Invocation invocation = invocations.get(idx);
				String commandName = invocation.getName();
				CommandInfo ci = CommandInfo.getCommand(commandName);
				AbstractCommand cmd = ci.createInstance();
				jobMgr.setProgressProvider(cmd.getProgressProvider());

				if (logger.isDebugEnabled())
					logger.debug(invocation.toString());
				else if (usingScript)
					logger.info(invocation.toString());
				int exitValue = cmd.basicRun(commandName, ci, invocation.getArgs());
				if (exitValue != EXIT_OK)
					return exitValue;
			}
		} finally {
			if (props != null)
				System.setProperties(sysProps);
			if (antLogLevel != -1)
				BuckminsterPreferences.setLogLevelAntLogger(currentAntLogLevel);
		}
		return EXIT_OK;
	}

}
