/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import static org.eclipse.buckminster.runtime.Trivial.trim;

import java.io.PrintStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.osgi.util.NLS;

/**
 * An actor that triggers a launch configuration
 */
public class LaunchActor extends AbstractActor {
	static class StreamDispatcher implements IStreamListener {
		private final PrintStream stream;

		StreamDispatcher(PrintStream stream) {
			this.stream = stream;
		}

		@Override
		public void streamAppended(String text, IStreamMonitor monitor) {
			stream.print(text);
		}
	}

	public static final String ID = "launch"; //$NON-NLS-1$

	private static final String LAUNCHER_PATH = "path"; //$NON-NLS-1$

	private static final String LAUNCHER_MODE = "mode"; //$NON-NLS-1$

	/**
	 * Returns the component (project) relative path to the launch
	 * configuration. This is a required attribute.
	 *
	 * @return The component relative path to the launch configuration.
	 * @throws CoreException
	 *             if the attribute is missing
	 */
	private IPath getLaunchConfigurationPath() throws CoreException {
		String path = trim(getActorProperty(LAUNCHER_PATH));
		if (path == null)
			throw BuckminsterException.fromMessage(Messages.Launch_No_launch_config);
		return Path.fromPortableString(path);
	}

	/**
	 * Returns the launch mode that is used for launching. Defaults to
	 * {@link ILaunchManager#RUN_MODE}.
	 *
	 * @return The launch mode to use. Never null.
	 */
	protected String getLaunchMode() {
		String mode = trim(getActorProperty(LAUNCHER_MODE));
		return mode == null ? ILaunchManager.RUN_MODE : mode;
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		IProject project = ctx.getProject();
		IFile launchFile = null;
		IPath launchConfigPath = getLaunchConfigurationPath();
		if (project != null && project.isAccessible())
			launchFile = project.getFile(launchConfigPath);

		if (launchFile == null || !launchFile.exists())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Launch_Cannot_open_launch_config, launchConfigPath.toPortableString()));

		ILaunch launch = DebugPlugin.getDefault().getLaunchManager().getLaunchConfiguration(launchFile).launch(getLaunchMode(), monitor);
		IProcess[] processes = launch.getProcesses();
		StreamDispatcher out = new StreamDispatcher(ctx.getOutputStream());
		StreamDispatcher err = new StreamDispatcher(ctx.getErrorStream());
		for (IProcess p : processes) {
			IStreamsProxy streamsProxy = p.getStreamsProxy();
			if (streamsProxy != null) {
				IStreamMonitor outMon = streamsProxy.getOutputStreamMonitor();
				if (outMon != null)
					outMon.addListener(out);
				IStreamMonitor errMon = streamsProxy.getErrorStreamMonitor();
				if (errMon != null)
					errMon.addListener(err);
			}
		}
		try {
			while (!launch.isTerminated()) {
				if (monitor.isCanceled() && launch.canTerminate())
					launch.terminate();
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			throw new OperationCanceledException();
		}

		MultiStatus result = new MultiStatus(CorePlugin.getID(), 0, "launch problem", null); //$NON-NLS-1$
		for (IProcess p : processes)
			if (p.getExitValue() != 0)
				result.add(new Status(IStatus.ERROR, CorePlugin.getID(), NLS.bind(Messages.Launch_Terminated_with_exit_status, p.getLabel(),
						Integer.valueOf(p.getExitValue()))));
		project.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		return result;
	}
}
