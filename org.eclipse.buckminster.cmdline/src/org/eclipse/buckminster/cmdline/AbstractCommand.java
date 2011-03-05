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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;

import org.eclipse.buckminster.cmdline.parser.ParseResult;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.osgi.util.NLS;

abstract public class AbstractCommand {
	static private final OptionDescriptor helpDescriptor = new OptionDescriptor('?', "help", OptionValueType.NONE); //$NON-NLS-1$

	private String calledUsingName;

	private CommandInfo cmdInfo;

	private boolean addHelpFlags;

	private boolean helpRequested = false;

	private Map<String, String> properties;

	public void addProperties(Map<String, String> props) {
		if (properties == null)
			properties = new HashMap<String, String>(props);
		else
			properties.putAll(props);
	}

	public void addProperty(String key, String value) {
		if (properties == null)
			properties = new HashMap<String, String>();
		properties.put(key, value);
	}

	public ProgressProvider getProgressProvider() {
		return new ProgressProvider() {
			@Override
			public IProgressMonitor createMonitor(Job job) {
				return getDefaultMonitor();
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
		return run(getProgressProvider().getDefaultMonitor());
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
		appendHere.add(Headless.DEFINE_DESCRIPTOR);
		appendHere.add(Headless.PROPERTIES_DESCRIPTOR);
	}

	protected void handleOption(Option option) throws Exception {
		if (option.is(Headless.DEFINE_DESCRIPTOR)) {
			String v = option.getValue();
			Matcher m = Headless.DEFINE_PATTERN.matcher(v);
			if (!m.matches())
				throw new IllegalArgumentException(NLS.bind(Messages.Not_a_key_value_string_0, v));
			String key = m.group(1);
			String value = m.group(2) == null ? "" //$NON-NLS-1$
					: m.group(2);
			addProperty(key, value);
		}
		if (option.is(Headless.PROPERTIES_DESCRIPTOR)) {
			String v = option.getValue();
			InputStream input = null;
			try {
				URL propsURL = URLUtils.normalizeToURL(v);
				input = new BufferedInputStream(propsURL.openStream());
				Properties props = new Properties();
				props.load(input);
				Enumeration<?> names = props.propertyNames();
				while (names.hasMoreElements()) {
					String name = (String) names.nextElement();
					addProperty(name, props.getProperty(name));
				}
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(NLS.bind(Messages.Invalid_URL_or_Path_0, v));
			} finally {
				IOUtils.close(input);
			}
		}
	}

	protected void handleUnparsed(String[] unparsed) throws Exception {
		// noop
	}

	protected void help() throws Exception {
		help(getHelpStream());
	}

	protected void help(InputStream helpStream) throws Exception {
		if (helpStream == null)
			System.err.println(NLS.bind(Messages.AbstractCommand_Help_missing_for_0, getFullName()));
		else {
			try {
				System.out.print(NLS.bind(Messages.AbstractCommand_Help_text_for_0, getFullName()));
				IOUtils.copy(helpStream, System.out, null);
			} finally {
				IOUtils.close(helpStream);
			}
			System.out.flush();
		}
	}

	protected boolean isHelpRequested() {
		return helpRequested;
	}

	protected abstract int run(IProgressMonitor monitor) throws Exception;

	final int basicRun(String calledAs, CommandInfo commandInfo, String[] commandArgs) throws Exception {
		calledUsingName = calledAs;
		cmdInfo = commandInfo;

		ArrayList<OptionDescriptor> optionDescriptors = new ArrayList<OptionDescriptor>();
		getOptionDescriptors(optionDescriptors);
		if (addHelpFlags)
			optionDescriptors.add(helpDescriptor);

		Properties sysProps = null;
		try {
			parseOptions(commandArgs, optionDescriptors);
			if (isHelpRequested()) {
				help();
				return Headless.EXIT_OK;
			}
			if (properties != null && !properties.isEmpty()) {
				sysProps = System.getProperties();
				Properties cmdProps = new Properties(sysProps);
				for (Entry<String, String> entry : properties.entrySet())
					cmdProps.setProperty(entry.getKey(), entry.getValue());
				System.setProperties(cmdProps);
			}
			return run(getProgressProvider().getDefaultMonitor());
		} catch (UsageException e) {
			System.err.println(e.getMessage());
			if (e.isEmitHelp())
				help();
			return Headless.EXIT_FAIL;
		} finally {
			if (sysProps != null)
				System.setProperties(sysProps);
		}
	}

	private void parseOptions(String[] args, List<OptionDescriptor> optionDescriptors) throws Exception {
		ParseResult pr = ParseResult.parse(args, optionDescriptors);
		Option[] options = pr.getOptions();
		helpRequested = false;
		beginOptionProcessing();
		int top = options.length;
		for (int idx = 0; idx < top; ++idx) {
			Option option = options[idx];
			if (option.is(helpDescriptor))
				helpRequested = true;
			else
				handleOption(option);
		}
		endOptionProcessing();
		handleUnparsed(pr.getUnparsed());
	}
}
