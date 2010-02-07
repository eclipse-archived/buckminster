/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

/**
 * @author kolwing
 */
public abstract class AbstractBuckminsterBuilder extends IncrementalProjectBuilder implements IResourceChangeListener {
	public static final String ARG_REFRESH_RESOURCE = "refresh.resource"; //$NON-NLS-1$

	public static final String ARG_DERIVED_RESOURCE = "derived.resource"; //$NON-NLS-1$

	public static final String ARG_GIVEN_NAME_KEY = "given.name"; //$NON-NLS-1$

	public static final String ARG_DISABLED_KEY = "disabled"; //$NON-NLS-1$

	public static final String ARG_DELTA_RESOURCE_KEY = "delta.resource"; //$NON-NLS-1$

	public static final String ARG_AUTO_PRINTSTREAM_KEY = "auto.printstream"; //$NON-NLS-1$

	public static final String ARG_CLEAN_PRINTSTREAM_KEY = "clean.printstream"; //$NON-NLS-1$

	public static final String ARG_FULL_PRINTSTREAM_KEY = "full.printstream"; //$NON-NLS-1$

	public static final String ARG_INCREMENTAL_PRINTSTREAM_KEY = "incremental.printstream"; //$NON-NLS-1$

	public static String bestNameForBuilder(String givenName, IConfigurationElement ce) {
		StringBuilder sb = new StringBuilder();
		if (givenName != null)
			sb.append(givenName).append(" ("); //$NON-NLS-1$
		String s = ce.getDeclaringExtension().getLabel().trim();
		if (s.length() == 0)
			s = ce.getDeclaringExtension().getUniqueIdentifier();
		sb.append(s);
		if (givenName != null)
			sb.append(")"); //$NON-NLS-1$
		return sb.toString();
	}

	public static String getValue(Map<String, String> args, String key) {
		String v = args.get(key);
		if (v != null) {
			v = v.trim();
			if (v.length() == 0)
				v = null;
		}
		return v;
	}

	public static boolean isDeltaMatching(Map<String, String> args, IProject project, IResourceDelta delta, IResource[] notifyOnChangedResources)
			throws CoreException {
		// if there's no delta available, just go on
		//
		if (delta == null)
			return true;

		// if there is no delta resource configured, everything matches
		//
		String deltaResource = getValue(args, ARG_DELTA_RESOURCE_KEY);
		if (deltaResource == null)
			return true;

		// try to find the configured delta resource
		//
		if (delta.findMember(new Path(deltaResource)) != null)
			return true;

		// anything the builder wishes change notifications about are
		// implicitly delta resources
		// note: delta checks are for auto/incremental, notifications for any
		// change, so they are otherwise different usecases
		if (notifyOnChangedResources != null) {
			for (IResource r : notifyOnChangedResources)
				if (delta.findMember(r.getFullPath()) != null)
					return true;
		}

		return false;
	}

	public static boolean isDisabled(Map<String, String> args) {
		return Boolean.parseBoolean(getValue(args, ARG_DISABLED_KEY));
	}

	public static boolean isPrintingEnabledForKind(Map<String, String> args, int kind) {
		String key = null;
		switch (kind) {
			case AUTO_BUILD:
				key = ARG_AUTO_PRINTSTREAM_KEY;
				break;
			case CLEAN_BUILD:
				key = ARG_CLEAN_PRINTSTREAM_KEY;
				break;
			case FULL_BUILD:
				key = ARG_FULL_PRINTSTREAM_KEY;
				break;
			case INCREMENTAL_BUILD:
				key = ARG_INCREMENTAL_PRINTSTREAM_KEY;
				break;
		}

		if (key == null)
			return true;

		// an absent value indicates 'true'
		//
		String value = getValue(args, key);
		if (value == null)
			return true;

		return Boolean.parseBoolean(value);
	}

	public static String kindToString(int kind) {
		if (kind == AUTO_BUILD)
			return "AUTO"; //$NON-NLS-1$
		if (kind == CLEAN_BUILD)
			return "CLEAN"; //$NON-NLS-1$
		if (kind == FULL_BUILD)
			return "FULL"; //$NON-NLS-1$
		if (kind == INCREMENTAL_BUILD)
			return "INCREMENTAL"; //$NON-NLS-1$

		return "NONE"; //$NON-NLS-1$
	}

	public static void setDisabled(Map<String, String> args, boolean disabled) {
		if (disabled)
			args.put(ARG_DISABLED_KEY, Boolean.TRUE.toString());
		else
			args.remove(ARG_DISABLED_KEY);
	}

	public static void setPrintingEnabledForKind(Map<String, String> args, int kind, boolean enabled) {
		String key = null;
		switch (kind) {
			case AUTO_BUILD:
				key = ARG_AUTO_PRINTSTREAM_KEY;
				break;
			case CLEAN_BUILD:
				key = ARG_CLEAN_PRINTSTREAM_KEY;
				break;
			case FULL_BUILD:
				key = ARG_FULL_PRINTSTREAM_KEY;
				break;
			case INCREMENTAL_BUILD:
				key = ARG_INCREMENTAL_PRINTSTREAM_KEY;
				break;
		}

		if (key != null) {
			if (enabled)
				args.remove(key);
			else
				args.put(key, Boolean.FALSE.toString());
		}
	}

	private IConfigurationElement config;

	private Object data;

	private String propertyName;

	private PrintStream errStream = null;

	private PrintStream outStream = null;

	private IResource[] notifyOnChangedResources;

	// regardless of if delta is matching or not, always do a build
	// the very first time the builder is called (unless disabled, of course)
	// this catches cases where otherwise the builder hasn't had the opportunity
	// to set resource notifications, for example
	private boolean initialBuildDone = false;

	public void resourceChanged(IResourceChangeEvent event) {
		if (notifyOnChangedResources != null && event.getType() == IResourceChangeEvent.POST_CHANGE) {
			// don't instantiate a list until we know it's needed
			List<IResource> changedResources = null;
			for (IResource r : notifyOnChangedResources) {
				IResourceDelta delta = event.getDelta().findMember(r.getFullPath());
				if (delta != null) {
					if (changedResources == null)
						changedResources = new ArrayList<IResource>();
					changedResources.add(r);
				}
			}

			if (changedResources != null)
				resourcesChangeNotification(changedResources.toArray(new IResource[0]));
		}
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		super.setInitializationData(config, propertyName, data);

		this.config = config;
		this.propertyName = propertyName;
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int,
	 * java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	final protected IProject[] build(int kind, Map rawArgs, IProgressMonitor monitor) throws CoreException {
		@SuppressWarnings("unchecked")
		Map<String, String> args = rawArgs;
		if (args == null)
			args = new HashMap<String, String>();

		boolean disabled = isDisabled(args);
		if (disabled)
			return null;

		boolean isDeltaMatching = ((kind != AUTO_BUILD && kind != INCREMENTAL_BUILD) ? true : isDeltaMatching(args, getProject(),
				getDelta(getProject()), notifyOnChangedResources));

		if (!isDeltaMatching && initialBuildDone)
			return null;

		IProject[] projects = null;
		initialBuildDone = true;

		boolean needsPrintStream = isPrintingEnabledForKind(args, kind);

		MonitorUtils.begin(monitor, 10);
		Logger logger = CorePlugin.getLogger();
		try {
			if (needsPrintStream) {
				outStream = Logger.getOutStream();
				errStream = Logger.getErrStream();
			} else {
				outStream = System.out;
				errStream = System.err;
			}

			if (logger.isDebugEnabled())
				logger.debug("[start AntBuilder(%s)] : %s - %s", kindToString(kind), getBestName(args), //$NON-NLS-1$
						getProject().getName());

			projects = doBuild(kind, args, MonitorUtils.subMonitor(monitor, 8));

			String refreshResource = getValue(args, ARG_REFRESH_RESOURCE);
			if (refreshResource != null) {
				IResource resource = getProject().findMember(new Path(refreshResource));
				if (resource != null)
					resource.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 1));
			}

			String derivedResource = getValue(args, ARG_DERIVED_RESOURCE);
			if (derivedResource != null) {
				IResource resource = getProject().findMember(new Path(derivedResource));
				if (resource != null) {
					if (refreshResource == null || !refreshResource.equals(derivedResource))
						resource.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 1));
					resource.setDerived(true, MonitorUtils.subMonitor(monitor, 1));
				}
			}
		} finally {
			MonitorUtils.done(monitor);
			if (logger.isDebugEnabled())
				logger.debug(String.format("[end AntBuilder(%s)]", kindToString(kind))); //$NON-NLS-1$
		}
		return projects;
	}

	@Override
	protected final void clean(IProgressMonitor monitor) throws CoreException {
		// make all go the same way
		//
		build(CLEAN_BUILD, getCommand().getArguments(), monitor);
	}

	protected IProject[] doAutoBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	protected IProject[] doBuild(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		if (kind == AUTO_BUILD)
			return doAutoBuild(args, monitor);
		if (kind == CLEAN_BUILD)
			return doCleanBuild(args, monitor);
		if (kind == FULL_BUILD)
			return doFullBuild(args, monitor);
		if (kind == INCREMENTAL_BUILD)
			return doIncrementalBuild(args, monitor);

		throw new CoreException(new Status(IStatus.ERROR, CorePlugin.CORE_NAMESPACE, 0, Messages.Unknown_kind, null));
	}

	protected IProject[] doCleanBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	protected IProject[] doFullBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	protected IProject[] doIncrementalBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	protected void doStartupOnInitialize() {
		// noop
	}

	protected String getBestName(Map<String, String> args) {
		return bestNameForBuilder(getGivenName(args), config);
	}

	protected IConfigurationElement getConfig() {
		return config;
	}

	protected Object getData() {
		return data;
	}

	protected PrintStream getErrStream() {
		return errStream;
	}

	protected String getGivenName(Map<String, String> args) {
		return getValue(args, ARG_GIVEN_NAME_KEY);
	}

	protected PrintStream getOutStream() {
		return outStream;
	}

	protected String getPropertyName() {
		return propertyName;
	}

	protected void notifyOnChangedResources(IResource[] resources) {
		notifyOnChangedResources = resources;
	}

	protected void resourcesChangeNotification(IResource[] changedResources) {
		// if someone has requested notification and then doesn't listen to it,
		// they deserve to be punished
		throw new IllegalStateException(Messages.Method_not_overridden);
	}

	@Override
	protected void startupOnInitialize() {
		super.startupOnInitialize();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		doStartupOnInitialize();
	}
}
