/*******************************************************************************
 * Copyright (c) 2009, eXXcellent solutions gmbh
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * Contributors:
 *     Achim Demelt - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.osgi.util.NLS;

public class Launch extends WorkspaceCommand {
	private final class StreamListener implements IStreamListener {
		private PrintStream stream;

		public StreamListener(IStreamMonitor monitor, String outputFile, boolean stdout) {
			Launch.this.listeners.add(this);

			// open stream or use stdout/stderr
			if ("-".equals(outputFile)) //$NON-NLS-1$
				this.stream = stdout ? Logger.getOutStream() : Logger.getErrStream();
			else
				try {
					this.stream = new PrintStream(outputFile);
				} catch (FileNotFoundException e) {
					CorePlugin.getLogger().error(e, Messages.Launch_Cannot_open_stream, outputFile);
					this.stream = stdout ? Logger.getOutStream() : Logger.getErrStream();
				}

			// dump initial contents
			stream.print(monitor.getContents());
			// and now register to be notified of subsequent events
			monitor.addListener(this);
		}

		public void close() {
			if (stream != Logger.getOutStream() && stream != Logger.getErrStream())
				stream.close();
		}

		@Override
		public void streamAppended(String text, IStreamMonitor monitor) {
			stream.print(text);
		}
	}

	private static final OptionDescriptor LAUNCH_DESCRIPTOR = new OptionDescriptor('l', "launch", //$NON-NLS-1$
			OptionValueType.REQUIRED);

	private static final OptionDescriptor STDOUT_DESCRIPTOR = new OptionDescriptor(null, "stdout", //$NON-NLS-1$
			OptionValueType.OPTIONAL);

	private static final OptionDescriptor STDERR_DESCRIPTOR = new OptionDescriptor(null, "stderr", //$NON-NLS-1$
			OptionValueType.OPTIONAL);

	private String launchName;

	private String stdOutFile;

	private String stdErrFile;

	private IStreamMonitor[] stdOut;

	private IStreamMonitor[] stdErr;

	private List<StreamListener> listeners = new ArrayList<StreamListener>();

	private ILaunch launch;

	public ILaunch getLaunch() {
		return launch;
	}

	/**
	 * Returns a copy of the stderr streams of the launched processes.
	 * 
	 * @return A copy of the stderr streams of the launched processes.
	 */
	public IStreamMonitor[] getRawStdErr() {
		return stdErr.clone();
	}

	/**
	 * Returns a copy of the stdout streams of the launched processes.
	 * 
	 * @return A copy of the stdout streams of the launched processes.
	 */
	public IStreamMonitor[] getRawStdOut() {
		return stdOut.clone();
	}

	/**
	 * Returns the content of the standard error streams of all processes launch
	 * by the configuration. Note that this method may lead to
	 * memory shortage if the launch produces a large amount of output.
	 * 
	 * @return The contents of all standard error streams. An empty string if no
	 *         processes were launched or no content was produced.
	 */
	public String getStdErr() {
		StringBuffer content = new StringBuffer();
		for (IStreamMonitor err : stdErr)
			content.append(err.getContents());
		return content.toString();
	}

	/**
	 * Returns the content of the standard output streams of all processes
	 * launch by the configuration. Note that this method may lead to
	 * memory shortage if the launch produces a large amount of output.
	 * 
	 * @return The contents of all standard output streams. An empty string if
	 *         no processes were launched or no content was produced.
	 */
	public String getStdOut() {
		StringBuffer content = new StringBuffer();
		for (IStreamMonitor out : stdOut)
			content.append(out.getContents());
		return content.toString();
	}

	/**
	 * Returns the launch mode that is used for launching. Defaults to
	 * {@link ILaunchManager#RUN_MODE}. Subclasses may override this to launch
	 * in other modes.
	 * 
	 * @return The launch mode to use. Never null.
	 */
	protected String getLaunchMode() {
		return ILaunchManager.RUN_MODE;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(LAUNCH_DESCRIPTOR);
		appendHere.add(STDOUT_DESCRIPTOR);
		appendHere.add(STDERR_DESCRIPTOR);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		super.handleOption(option);

		if (option.is(LAUNCH_DESCRIPTOR))
			launchName = option.getValue();
		else if (option.is(STDOUT_DESCRIPTOR))
			stdOutFile = option.getValue() == null ? "-" //$NON-NLS-1$
					: option.getValue();
		else if (option.is(STDERR_DESCRIPTOR))
			stdErrFile = option.getValue() == null ? "-" //$NON-NLS-1$
					: option.getValue();
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		if (launchName == null)
			throw new UsageException(Messages.Launch_No_launch_config);

		IResource launchFile = ResourcesPlugin.getWorkspace().getRoot().findMember(launchName);
		if (launchFile == null || launchFile.getType() != IResource.FILE || !launchFile.exists())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Launch_Cannot_open_launch_config, launchName));

		ILaunchConfiguration launchConfiguration = DebugPlugin.getDefault().getLaunchManager().getLaunchConfiguration((IFile) launchFile);
		launch = launchConfiguration.launch(getLaunchMode(), monitor);

		// capture stdout/stderr streams
		IProcess[] processes = launch.getProcesses();
		stdOut = new IStreamMonitor[processes.length];
		stdErr = new IStreamMonitor[processes.length];
		for (int i = 0; i < processes.length; i++) {
			stdOut[i] = processes[i].getStreamsProxy().getOutputStreamMonitor();
			if (stdOutFile != null)
				new StreamListener(stdOut[i], stdOutFile, true);
			stdErr[i] = processes[i].getStreamsProxy().getErrorStreamMonitor();
			if (stdErrFile != null)
				new StreamListener(stdErr[i], stdErrFile, false);
		}

		try {
			// TODO: wait for a configurable, finite time and terminate process
			// if overdue
			while (!launch.isTerminated())
				Thread.sleep(500);

			// check for process exit status
			int result = 0;
			for (IProcess p : processes)
				if (p.getExitValue() != 0) {
					CorePlugin.getLogger().warning(Messages.Launch_Terminated_with_exit_status, p.getLabel(), Integer.valueOf(p.getExitValue()));
					result = p.getExitValue();
				}

			return result;
		} finally {
			for (StreamListener listener : listeners)
				listener.close();
		}
	}
}
