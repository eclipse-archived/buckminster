package org.eclipse.buckminster.executor.actor;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.helpers.PropertyExpander;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * This class declares a new Buckminster Actor that allow execution of commands directly from the CSpec
 * 
 * @author Guillaume CHATELET
 * 
 */
public class ExecutorActor extends AbstractActor
{
	private static final String EXECUTOR_ENV = "env";

	private static final String EXECUTOR_EXEC_ACTION = "exec";

	private static final String EXECUTOR_EXEC_DIR_ACTION = "execDir";

	private static final String EXECUTOR_SHELL_ACTION = "shell";

	private static final String[] validProperties = { EXECUTOR_ENV, EXECUTOR_EXEC_ACTION, EXECUTOR_EXEC_DIR_ACTION,
			EXECUTOR_SHELL_ACTION };

	private static final String PLUGIN_ID = "org.eclipse.buckminster.executor";

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		monitor.beginTask(null, 1);
		monitor.subTask(ctx.getAction().getQualifiedName());
		try
		{
			checkProperties();
			final PropertyExpander expander = new PropertyExpander(ctx);
			final PrintStream errorStream = ctx.getErrorStream();
			final PrintStream outputStream = ctx.getOutputStream();
			final File executionDir = getExecutionDir(ctx);
			final String command = expander.expand(prepareCommandLine());
			final String[] env = prepareEnvironmentVariables(expander);
			final Process proc = Runtime.getRuntime().exec(command, env, executionDir);
			// any error message?
			final StreamGobblerRedirector errorGobbler = new StreamGobblerRedirector(proc.getErrorStream(), errorStream);
			// any output?
			final StreamGobblerRedirector outputGobbler = new StreamGobblerRedirector(proc.getInputStream(),
					outputStream);
			// kick them off
			errorGobbler.start();
			outputGobbler.start();
			final int returnCode = proc.waitFor();
			outputStream.flush();
			if(returnCode != 0)
				CorePlugin.getLogger().error("Program " + command + " returned exit code " + returnCode);
		}
		catch(IOException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage());
		}
		catch(InterruptedException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage());
		}
		catch(CoreException e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(t, t.toString());
			throw e;
		}
		finally
		{
			monitor.done();
		}
		return Status.OK_STATUS;
	}

	private void checkProperties() throws CoreException
	{
		final HashSet<String> validSet = new HashSet<String>(Arrays.asList(validProperties));
		final Map<String, String> actorProperties = getActiveContext().getAction().getActorProperties();
		final Set<String> keySet = actorProperties.keySet();
		for(String property : keySet)
		{
			if(validSet.contains(property) == false)
			{
				final StringBuffer buffer = new StringBuffer("ActorProperty \"" + property
						+ "\" is not a valid one. Valid keys are :\n");
				for(String validProperty : validSet)
					buffer.append(validProperty).append(' ');
				throw new IllegalStateException(buffer.toString());
			}
		}
	}

	private File getExecutionDir(IActionContext ctx) throws CoreException
	{
		final String executionDir = TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_EXEC_DIR_ACTION));
		final String componentLocation = ctx.getComponentLocation().toOSString();
		if(executionDir == null)
			return new File(componentLocation);

		final File executionDirFile = new File(executionDir);
		if(executionDirFile.isAbsolute())
			return executionDirFile;

		return new File(componentLocation + executionDir);
	}

	private String prepareCommandLine() throws CoreException
	{
		// verifying command relevance
		final String execCommand = getExecCommand();
		final String shellCommand = getShellCommand();
		if(execCommand == null && shellCommand == null)
			throwError("You should specify at least one shell or one exec actorProperty");
		if(execCommand != null && shellCommand != null)
			throwError("You can specify \"" + EXECUTOR_EXEC_ACTION + "\" or \"" + EXECUTOR_SHELL_ACTION
					+ "\" actorProperty, but not both.");
		if(execCommand != null)
		{
			return execCommand;
		}

		final String shell = ShellCommand.getShellCommand();
		if(shell == null)
			throw new Error("Shell interpreter for OS " + ShellCommand.getOsName()
					+ " is not currently supported\nPlease submit a bug report");
		return shell + ' ' + shellCommand;
	}

	/**
	 * Helper to generate an error
	 * 
	 * @param message
	 * @throws CoreException
	 */
	private void throwError(final String message) throws CoreException
	{
		throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, message));
	}

	/**
	 * @return "exec" actorProperty or null if not set
	 */
	private String getExecCommand()
	{
		return TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_EXEC_ACTION));
	}

	/**
	 * @return "shell" actorProperty or null if not set
	 */
	private String getShellCommand()
	{
		return TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_SHELL_ACTION));
	}

	/**
	 * Prepare the environment variables. Retrieve them from the "env" actorProperties then tries to replace every
	 * variables by their value. If an environment variable refers to a Property or a NamedPath then the variable is
	 * replaced by the Property or NamedPath values
	 * 
	 * @return a list of initialized environment variables
	 * @throws CoreException
	 */
	private String[] prepareEnvironmentVariables(PropertyExpander expander) throws CoreException
	{
		final Set<String> envSet = new HashSet<String>();
		final String envProperty = TextUtils.notEmptyTrimmedString(this.getActorProperty(EXECUTOR_ENV));
		if(envProperty != null)
		{
			final String[] split = envProperty.split(";");
			for(String env : split)
				envSet.add(expander.expand(env));
		}
		if(envSet.isEmpty() == false)
		{
			final StringBuffer buffer = new StringBuffer("Setting environment variables :\n");
			for(String string : envSet)
				buffer.append(string).append('\n');
			CorePlugin.getLogger().debug(buffer.toString(), (Object)null);
		}
		return envSet.toArray(new String[envSet.size()]);
	}
}
