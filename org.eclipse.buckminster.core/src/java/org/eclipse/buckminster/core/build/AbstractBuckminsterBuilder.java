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
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceVisitor;
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
public abstract class AbstractBuckminsterBuilder extends IncrementalProjectBuilder implements IResourceChangeListener
{
	public static final String ARG_REFRESH_RESOURCE = "refresh.resource";

	public static final String ARG_DERIVED_RESOURCE = "derived.resource";

	public static final String ARG_GIVEN_NAME_KEY = "given.name";

	public static final String ARG_DISABLED_KEY = "disabled";

	public static final String ARG_DELTA_RESOURCE_KEY = "delta.resource";

	public static final String ARG_AUTO_PRINTSTREAM_KEY = "auto.printstream";

	public static final String ARG_CLEAN_PRINTSTREAM_KEY = "clean.printstream";

	public static final String ARG_FULL_PRINTSTREAM_KEY = "full.printstream";

	public static final String ARG_INCREMENTAL_PRINTSTREAM_KEY = "incremental.printstream";

	public static String bestNameForBuilder(String givenName, IConfigurationElement ce)
	{
		StringBuilder sb = new StringBuilder();
		if(givenName != null)
			sb.append(givenName).append(" (");
		String s = ce.getDeclaringExtension().getLabel().trim();
		if(s.length() == 0)
			s = ce.getDeclaringExtension().getUniqueIdentifier();
		sb.append(s);
		if(givenName != null)
			sb.append(")");
		return sb.toString();
	}

	public static String getValue(Map<String, String> args, String key)
	{
		String v = args.get(key);
		if(v != null)
		{
			v = v.trim();
			if(v.length() == 0)
				v = null;
		}
		return v;
	}

	public static boolean isDeltaMatching(Map<String, String> args, IProject project, IResourceDelta delta,
			IResource[] notifyOnChangedResources) throws CoreException
	{
		// if there's no delta available, just go on
		//
		if(delta == null)
			return true;

		// if there is no delta resource configured, everything matches
		//
		String deltaResource = getValue(args, ARG_DELTA_RESOURCE_KEY);
		if(deltaResource == null)
			return true;

		// try to find the configured delta resource
		//
		if(delta.findMember(new Path(deltaResource)) != null)
			return true;

		// anything the builder wishes change notifications about are
		// implicitly delta resources
		// note: delta checks are for auto/incremental, notifications for any
		// change, so they are otherwise different usecases
		if(notifyOnChangedResources != null)
		{
			for(IResource r : notifyOnChangedResources)
				if(delta.findMember(r.getFullPath()) != null)
					return true;
		}

		return false;
	}

	public static boolean isDisabled(Map<String, String> args)
	{
		return Boolean.parseBoolean(getValue(args, ARG_DISABLED_KEY));
	}

	public static boolean isPrintingEnabledForKind(Map<String, String> args, int kind)
	{
		String key = null;
		switch(kind)
		{
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

		if(key == null)
			return true;

		// an absent value indicates 'true'
		//
		String value = getValue(args, key);
		if(value == null)
			return true;

		return Boolean.parseBoolean(value);
	}

	public static String kindToString(int kind)
	{
		if(kind == AUTO_BUILD)
			return "AUTO";
		if(kind == CLEAN_BUILD)
			return "CLEAN";
		if(kind == FULL_BUILD)
			return "FULL";
		if(kind == INCREMENTAL_BUILD)
			return "INCREMENTAL";

		return "NONE";
	}

	public static void setDisabled(Map<String, String> args, boolean disabled)
	{
		if(disabled)
			args.put(ARG_DISABLED_KEY, Boolean.TRUE.toString());
		else
			args.remove(ARG_DISABLED_KEY);
	}

	public static void setPrintingEnabledForKind(Map<String, String> args, int kind, boolean enabled)
	{
		String key = null;
		switch(kind)
		{
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

		if(key != null)
		{
			if(enabled)
				args.remove(key);
			else
				args.put(key, Boolean.FALSE.toString());
		}
	}

	private IConfigurationElement m_config;

	private Object m_data;

	private String m_propertyName;

	private PrintStream m_errStream = null;

	private PrintStream m_outStream = null;

	private IResource[] m_notifyOnChangedResources;

	// regardless of if delta is matching or not, always do a build
	// the very first time the builder is called (unless disabled, of course)
	// this catches cases where otherwise the builder hasn't had the opportunity
	// to set resource notifications, for example
	private boolean m_initialBuildDone = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IncrementalProjectBuilder#build(int, java.util.Map,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	final protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException
	{
		if(args == null)
			args = new HashMap();

		boolean disabled = isDisabled(args);
		boolean isDeltaMatching = ((kind != AUTO_BUILD && kind != INCREMENTAL_BUILD)
				? true
				: isDeltaMatching(args, this.getProject(), this.getDelta(this.getProject()), m_notifyOnChangedResources));

		IProject[] projects = null;
		if(!disabled && (isDeltaMatching || !m_initialBuildDone))
		{
			m_initialBuildDone = true;

			boolean needsPrintStream = isPrintingEnabledForKind(args, kind);

			Logger logger = CorePlugin.getLogger();
			try
			{
				if(needsPrintStream)
				{
					m_outStream = Logger.getOutStream();
					m_errStream = Logger.getErrStream();
				}
				else
				{
					m_outStream = System.out;
					m_errStream = System.err;
				}

				if(logger.isDebugEnabled())
					logger.debug("[start AntBuilder(%s)] : %s - %s", kindToString(kind), getBestName(args),
							getProject().getName());

				projects = doBuild(kind, args, monitor);

				String refreshResource = getValue(args, ARG_REFRESH_RESOURCE);
				if(refreshResource != null)
				{
					IResource resource = this.getProject().findMember(new Path(refreshResource));
					if(resource != null)
						resource.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor,
								IProgressMonitor.UNKNOWN));
				}

				String derivedResource = getValue(args, ARG_DERIVED_RESOURCE);
				if(derivedResource != null)
				{
					IResource resource = this.getProject().findMember(new Path(derivedResource));
					if(resource != null)
					{
						if(refreshResource == null || !refreshResource.equals(derivedResource))
							resource.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor,
									IProgressMonitor.UNKNOWN));
						resource.accept(new IResourceVisitor()
						{
							public boolean visit(IResource rs) throws CoreException
							{
								rs.setDerived(true);
								return true;
							}
						});
					}
				}
			}
			finally
			{
				if(logger.isDebugEnabled())
					logger.debug(String.format("[end AntBuilder(%s)]", kindToString(kind)));
			}
		}
		return projects;
	}

	@Override
	protected final void clean(IProgressMonitor monitor) throws CoreException
	{
		// make all go the same way
		//
		this.build(CLEAN_BUILD, this.getCommand().getArguments(), monitor);
	}

	protected IProject[] doAutoBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	protected IProject[] doBuild(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		if(kind == AUTO_BUILD)
			return this.doAutoBuild(args, monitor);
		if(kind == CLEAN_BUILD)
			return this.doCleanBuild(args, monitor);
		if(kind == FULL_BUILD)
			return this.doFullBuild(args, monitor);
		if(kind == INCREMENTAL_BUILD)
			return this.doIncrementalBuild(args, monitor);

		throw new CoreException(new Status(IStatus.ERROR, CorePlugin.CORE_NAMESPACE, 0, "Unknown kind", null));
	}

	protected IProject[] doCleanBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	protected IProject[] doFullBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	protected IProject[] doIncrementalBuild(Map<String, String> args, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	protected void doStartupOnInitialize()
	{
		// noop
	}

	protected String getBestName(Map<String, String> args)
	{
		return bestNameForBuilder(this.getGivenName(args), m_config);
	}

	protected IConfigurationElement getConfig()
	{
		return m_config;
	}

	protected Object getData()
	{
		return m_data;
	}

	protected PrintStream getErrStream()
	{
		return m_errStream;
	}

	protected String getGivenName(Map<String, String> args)
	{
		return getValue(args, ARG_GIVEN_NAME_KEY);
	}

	protected PrintStream getOutStream()
	{
		return m_outStream;
	}

	protected String getPropertyName()
	{
		return m_propertyName;
	}

	protected void notifyOnChangedResources(IResource[] resources)
	{
		m_notifyOnChangedResources = resources;
	}

	public void resourceChanged(IResourceChangeEvent event)
	{
		if(m_notifyOnChangedResources != null && event.getType() == IResourceChangeEvent.POST_CHANGE)
		{
			// don't instantiate a list until we know it's needed
			List<IResource> changedResources = null;
			for(IResource r : m_notifyOnChangedResources)
			{
				IResourceDelta delta = event.getDelta().findMember(r.getFullPath());
				if(delta != null)
				{
					if(changedResources == null)
						changedResources = new ArrayList<IResource>();
					changedResources.add(r);
				}
			}

			if(changedResources != null)
				this.resourcesChangeNotification(changedResources.toArray(new IResource[0]));
		}
	}

	protected void resourcesChangeNotification(IResource[] changedResources)
	{
		// if someone has requested notification and then doesn't listen to it,
		// they deserve to be punished
		throw new IllegalStateException("Method not overridden");
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		super.setInitializationData(config, propertyName, data);

		m_config = config;
		m_propertyName = propertyName;
		m_data = data;
	}

	@Override
	protected void startupOnInitialize()
	{
		super.startupOnInitialize();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		this.doStartupOnInitialize();
	}
}
