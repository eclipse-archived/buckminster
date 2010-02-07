package org.eclipse.buckminster.executor.actor;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.helpers.PropertyExpander;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.executor.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * This class declares a new Buckminster Actor that allow execution of commands
 * directly from the CSpec
 * 
 * @author Guillaume CHATELET
 * 
 */
public class ExecutorActor extends AbstractActor {
	private static final String EXECUTOR_ENV = "env"; //$NON-NLS-1$

	private static final String EXECUTOR_EXEC_ACTION = "exec"; //$NON-NLS-1$

	private static final String EXECUTOR_EXEC_DIR_ACTION = "execDir"; //$NON-NLS-1$

	private static final String EXECUTOR_SHELL_ACTION = "shell"; //$NON-NLS-1$

	private static final String EXECUTOR_NEW_ENVIRONMENT_ACTION = "newenvironment"; //$NON-NLS-1$

	private static final String EXECUTOR_FAIL_ON_ERROR = "failonerror"; //$NON-NLS-1$

	private static final String[] validProperties = { EXECUTOR_ENV, EXECUTOR_EXEC_ACTION, EXECUTOR_EXEC_DIR_ACTION, EXECUTOR_SHELL_ACTION,
			EXECUTOR_NEW_ENVIRONMENT_ACTION, EXECUTOR_FAIL_ON_ERROR };

	private static final String PLUGIN_ID = "org.eclipse.buckminster.executor"; //$NON-NLS-1$

	/**
	 * Splits the environment variables but protects quoted parts
	 * 
	 * @param env
	 * @return
	 */
	@SuppressWarnings("boxing")
	static String[] splitEnvironnementVariables(final String env) {
		final List<Integer> semicolonIndexes = indexesOf(env, ';');
		final List<Integer> quoteIndexes = indexesOf(env, '\"');
		if (quoteIndexes.size() % 2 != 0)
			throw new IllegalStateException(NLS.bind(Messages.odd_number_of_quoting_chars_in_0, env));
		// removing semicolon indexes between quote indexes
		final Iterator<Integer> quoteItr = quoteIndexes.iterator();
		while (quoteItr.hasNext()) {
			int min = quoteItr.next();
			int max = quoteItr.next();
			Iterator<Integer> semicolonItr = semicolonIndexes.iterator();
			while (semicolonItr.hasNext()) {
				int i = semicolonItr.next();
				if (i > min && i < max)
					semicolonItr.remove();
			}
		}
		// splitting the variables
		final List<String> result = new ArrayList<String>();
		int lastIndex = 0;
		for (int i : semicolonIndexes) {
			result.add(env.substring(lastIndex, i));
			lastIndex = i + 1;
		}
		result.add(env.substring(lastIndex));
		return result.toArray(new String[result.size()]);
	}

	/**
	 * returns the list of indexes where you can find the character c in string
	 * 
	 * @param string
	 * @param c
	 * @return
	 */
	@SuppressWarnings("boxing")
	private static List<Integer> indexesOf(String string, char c) {
		final List<Integer> list = new ArrayList<Integer>();
		int fromIndex = 0;
		int index;
		while ((index = string.indexOf(c, fromIndex)) != -1) {
			list.add(index);
			fromIndex = index + 1;
		}
		return list;
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		monitor.subTask(ctx.getAction().getQualifiedName());
		try {
			checkProperties();
			final String EXE = "[EXE] "; //$NON-NLS-1$
			final PropertyExpander expander = new PropertyExpander(ctx);
			final PrintStream errorStream = ctx.getErrorStream();
			final PrintStream outputStream = ctx.getOutputStream();
			final File executionDir = getExecutionDir(ctx);
			final String command = expander.expand(prepareCommandLine());
			final String[] env = prepareEnvironmentVariables(expander);
			CorePlugin.getLogger().info(EXE + NLS.bind(Messages.now_executing_0, command));
			CorePlugin.getLogger().info(EXE + NLS.bind(Messages.in_directory_0, executionDir));
			final Process proc = Runtime.getRuntime().exec(command, env, executionDir);
			// any error message ?
			final StreamGobblerRedirector errorGobbler = new StreamGobblerRedirector(proc.getErrorStream(), errorStream);
			// any output ?
			final StreamGobblerRedirector outputGobbler = new StreamGobblerRedirector(proc.getInputStream(), outputStream);
			// kick them off
			errorGobbler.start();
			outputGobbler.start();
			final int returnCode = proc.waitFor();
			outputStream.flush();
			if (returnCode != 0) {
				CorePlugin.getLogger().error(NLS.bind(Messages.program_0_exit_code_1, command, String.valueOf(returnCode)));
				if (getFailStatus())
					return Status.CANCEL_STATUS;
			}
		} catch (IOException e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage());
		} catch (InterruptedException e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage());
		} catch (CoreException e) {
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	private void checkProperties() throws CoreException {
		final HashSet<String> validSet = new HashSet<String>(Arrays.asList(validProperties));
		final Map<String, String> actorProperties = getActiveContext().getAction().getActorProperties();
		final Set<String> keySet = actorProperties.keySet();
		for (String property : keySet) {
			if (validSet.contains(property) == false) {
				final StringBuffer buffer = new StringBuffer();
				for (String validProperty : validSet)
					buffer.append(validProperty).append(' ');
				throw new IllegalStateException(NLS.bind(Messages.actorProperty_0_invalid_valid_are_1, property, buffer.toString()));
			}
		}
	}

	/**
	 * @return "exec" actorProperty or null if not set
	 */
	private String getExecCommand() {
		return TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_EXEC_ACTION));
	}

	private File getExecutionDir(IActionContext ctx) throws CoreException {
		final String executionDir = TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_EXEC_DIR_ACTION));
		final String componentLocation = ctx.getComponentLocation().toOSString();
		if (executionDir == null)
			return new File(componentLocation);

		final File executionDirFile = new File(executionDir);
		if (executionDirFile.isAbsolute())
			return executionDirFile;

		return new File(componentLocation + executionDir);
	}

	private boolean getFailStatus() {
		final String failOnErrorValue = this.getActorProperty(EXECUTOR_FAIL_ON_ERROR);
		if (failOnErrorValue == null)
			return true;
		return Boolean.parseBoolean(TextUtils.notEmptyTrimmedString(failOnErrorValue));
	}

	/**
	 * @return "shell" actorProperty or null if not set
	 */
	private String getShellCommand() {
		return TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_SHELL_ACTION));
	}

	private String prepareCommandLine() throws CoreException {
		// verifying command relevance
		final String execCommand = getExecCommand();
		final String shellCommand = getShellCommand();
		if (execCommand == null && shellCommand == null)
			throwError(NLS.bind(Messages.actorProperty_at_least_one_0_1, EXECUTOR_EXEC_ACTION, EXECUTOR_SHELL_ACTION));
		if (execCommand != null && shellCommand != null)
			throwError(NLS.bind(Messages.actorProperty_at_most_one_0_1, EXECUTOR_EXEC_ACTION, EXECUTOR_SHELL_ACTION));
		if (execCommand != null) {
			return execCommand;
		}

		final String shell = ShellCommand.getShellCommand();
		if (shell == null)
			throw new Error(NLS.bind(Messages.shell_interpreter_for_0_not_supported, ShellCommand.getOsName()));
		return shell + ' ' + shellCommand;
	}

	/**
	 * Prepare the environment variables. Retrieve them from the "env"
	 * actorProperties then tries to replace every variables by their value. If
	 * an environment variable refers to a Property or a NamedPath then the
	 * variable is replaced by the Property or NamedPath values
	 * 
	 * @return a list of initialized environment variables
	 * @throws CoreException
	 */
	private String[] prepareEnvironmentVariables(PropertyExpander expander) throws CoreException {
		final String ENV = "[ENV] "; //$NON-NLS-1$
		final Set<String> envSet = new HashSet<String>();
		final String envProperty = TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_ENV));

		final boolean useEnvironment = !Boolean.parseBoolean(this.getActorProperty(EXECUTOR_NEW_ENVIRONMENT_ACTION));
		CorePlugin.getLogger().info(ENV + NLS.bind(Messages.using_system_environment_0, String.valueOf(useEnvironment)));
		if (useEnvironment) {
			final Map<String, String> getenv = System.getenv();
			for (String key : getenv.keySet())
				envSet.add(key + '=' + getenv.get(key));
		}
		if (envProperty != null) {
			final String[] split = splitEnvironnementVariables(envProperty);
			for (String env : split)
				envSet.add(expander.expand(env));
		}

		Logger logger = CorePlugin.getLogger();
		if (logger.isDebugEnabled() && envSet.isEmpty() == false) {
			for (String string : envSet)
				logger.debug("%sSetting environment variable %s%n", ENV, string); //$NON-NLS-1$
		}
		return envSet.toArray(new String[envSet.size()]);
	}

	/**
	 * Helper to generate an error
	 * 
	 * @param message
	 * @throws CoreException
	 */
	private void throwError(final String message) throws CoreException {
		throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, message));
	}
}
