/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class Build extends WorkspaceCommand {
	static private final OptionDescriptor cleanDescriptor = new OptionDescriptor('c', "clean", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor thoroughDescriptor = new OptionDescriptor('t', "thorough", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor logfileDescriptor = new OptionDescriptor('l', "logfile", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor continueOnErrorDescriptor = new OptionDescriptor('C', "continueonerror", OptionValueType.NONE); //$NON-NLS-1$

	private static final int MAX_INCREMENTAL_RETRY_COUNT = 3;

	private static final int SUCCEEDED = 0;

	private static final int FAILED = 1;

	public static IMarker[] build(IProgressMonitor monitor, boolean clean) throws Exception {
		return build(monitor, clean, false);
	}

	public static IMarker[] build(IProgressMonitor monitor, boolean clean, boolean thorough) throws Exception {
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot wsRoot = ws.getRoot();
		IProject[] projs = wsRoot.getProjects();

		try {
			monitor.beginTask(null, projs.length * (clean ? 9 : 7));

			// Ensure that the workspace is in sync
			//
			wsRoot.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, projs.length));

			if (clean || thorough)
				//
				// Clean first if requested
				//
				ws.build(IncrementalProjectBuilder.CLEAN_BUILD, MonitorUtils.subMonitor(monitor, projs.length * 2));

			if (thorough)
				TargetPlatform.getInstance().refresh();

			ws.build(IncrementalProjectBuilder.FULL_BUILD, MonitorUtils.subMonitor(monitor, projs.length * 5));

			// Some errors are caused by circular dependencies in the build
			// hierarchy. They might be
			// fixed by additional incremental builds so we make
			// MAX_INCREMENTAL_RETRY_COUNT attempts
			// before we report errors.
			//
			IMarker[] markers;
			int top;
			for (int retries = 0;; ++retries) {
				// Get all problem markers. Sort them by timestamp
				//
				markers = wsRoot.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
				top = markers.length;
				if (top == 0 || retries >= MAX_INCREMENTAL_RETRY_COUNT)
					break;

				boolean retryNeeded = false;
				for (int idx = 0; idx < top; ++idx) {
					if (markers[idx].getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) != IMarker.SEVERITY_ERROR)
						continue;

					retryNeeded = true;
					break;
				}

				if (!retryNeeded)
					//
					// No errors spotted, we're happy.
					//
					break;

				// Build incrementally and then obtain a new set of markers
				//
				int buildType = (thorough && retries == 0) ? IncrementalProjectBuilder.FULL_BUILD : IncrementalProjectBuilder.INCREMENTAL_BUILD;
				ws.build(buildType, MonitorUtils.subMonitor(monitor, projs.length));
			}
			Arrays.sort(markers, new Comparator<IMarker>() {
				@Override
				public int compare(IMarker a, IMarker b) {
					try {
						long diff = a.getCreationTime() - b.getCreationTime();
						return diff > 0 ? 1 : (diff < 0 ? -1 : 0);
					} catch (CoreException e) {
						return 0;
					}
				}
			});

			try {
				// Refresh workspace info since the build might have affected
				// cspec generation. When executing with a script, the next
				// perform will run with the same generated meta-data.
				WorkspaceInfo.forceRefreshOnAll(MonitorUtils.subMonitor(monitor, projs.length));
			} catch (Exception e) {
				// ignore
			}

			return markers;
		} finally {
			monitor.done();
		}
	}

	public static String formatMarkerMessage(String type, IMarker problem) {
		StringBuilder bld = new StringBuilder();
		bld.append(type);
		bld.append(": file "); //$NON-NLS-1$
		bld.append(problem.getResource().getLocation().toOSString());
		int line = problem.getAttribute(IMarker.LINE_NUMBER, -1);
		if (line > 0) {
			bld.append(", line "); //$NON-NLS-1$
			bld.append(line);
		}
		bld.append(": "); //$NON-NLS-1$
		bld.append(problem.getAttribute(IMarker.MESSAGE, "")); //$NON-NLS-1$
		return bld.toString();
	}

	// set if a clean build is requested
	//
	private boolean clean = false;

	private boolean thorough = false;

	private File logFile = null;

	private boolean continueOnError = false;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		appendHere.add(cleanDescriptor);
		appendHere.add(thoroughDescriptor);
		appendHere.add(continueOnErrorDescriptor);
		appendHere.add(logfileDescriptor);
		super.getOptionDescriptors(appendHere);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(cleanDescriptor))
			clean = true;
		else if (option.is(thoroughDescriptor))
			thorough = true;
		else if (option.is(continueOnErrorDescriptor))
			continueOnError = true;
		else if (option.is(logfileDescriptor))
			logFile = new File(option.getValue());
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		if (unparsed.length > 0)
			throw new UsageException(Messages.Too_many_arguments);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		long start = System.currentTimeMillis();
		IMarker[] problems = build(monitor, clean, thorough);
		long seconds = (System.currentTimeMillis() - start) / 1000;

		PrintStream log = null;
		if (logFile != null)
			log = new PrintStream(logFile);

		try {
			int errors = 0;
			int warnings = 0;
			int infos = 0;
			for (IMarker problem : problems) {
				PrintStream console = null;
				String message = null;
				switch (problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO)) {
					case IMarker.SEVERITY_ERROR:
						++errors;
						console = System.err;
						message = formatMarkerMessage("Error", problem); //$NON-NLS-1$
						break;
					case IMarker.SEVERITY_WARNING:
						++warnings;
						console = System.err;
						message = formatMarkerMessage("Warning", problem); //$NON-NLS-1$
						break;
					case IMarker.SEVERITY_INFO:
						++infos;
						console = System.out;
						message = formatMarkerMessage("Info", problem); //$NON-NLS-1$
				}

				if (message != null) {
					if (console != null)
						console.println(message);
					if (log != null)
						log.println(message);
				}
			}

			System.out.println("Errors: " + errors); //$NON-NLS-1$
			System.out.println("Warnings: " + warnings); //$NON-NLS-1$
			System.out.println("Infos: " + infos); //$NON-NLS-1$

			int exitValue = errors == 0 ? SUCCEEDED : FAILED;
			System.out.println("Build " + (exitValue == FAILED ? "failed" : "succeeded") + " after " + seconds + " seconds."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			if (exitValue == FAILED && continueOnError)
				System.out.println("Build continues anyway..."); //$NON-NLS-1$ 
			return continueOnError ? SUCCEEDED : exitValue;
		} finally {
			if (log != null)
				log.close();
		}
	}

}
