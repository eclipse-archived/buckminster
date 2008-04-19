/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.plugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * Abstract Base Class for a Plugin that supports convenient calls to logging 
 * @author Henrik Lindberg
 *
 */
public abstract class AbstractPlugin extends Plugin
{
	private AbstractPlugin m_plugin;
	
	public void logInfo(String message)
	{
		log(IStatus.INFO, IStatus.OK, message, null);
	}
	public void logError(Throwable e)
	{
		logError("Unexpected Exception", e);
	}
	public void logError(String message, Throwable e)
	{
		log(IStatus.ERROR, IStatus.OK, message, e);
	}
	public void log(int severity, int code, String message, Throwable e)
	{
		log(createStatus(severity, code, message, e));
	}
	public IStatus createStatus(int severity, int code, String message, Throwable e)
	{
		return new Status(severity, getPluginId(), code, message, e);
	}
	public void log(IStatus status)
	{
		getDefault().getLog().log(status);
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		m_plugin = this;
	}
	public AbstractPlugin getDefault()
	{
		return m_plugin;
	}
	@Override
	public void stop(BundleContext context) throws Exception
	{
		m_plugin = null;
		super.stop(context);
	}
	public abstract String getPluginId();
}
