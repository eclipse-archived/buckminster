package org.slf4j.impl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;

public class ExtensionPointLoader
{
	public static final String SLF4J_IMPL_POINT = "org.slf4j.impl";

	private static ILoggerFactory s_loggerFactory;

	private static IMarkerFactory s_markerFactory;

	private static MDCAdapter s_mdcAdapter;

	public static ILoggerFactory loadLoggerFactory()
	{
		if(s_loggerFactory == null)
			loadExtensions();
		return s_loggerFactory;
	}

	public static IMarkerFactory loadMarkerFactory()
	{
		if(s_markerFactory == null)
			loadExtensions();
		return s_markerFactory;
	}

	public static MDCAdapter loadMDCAdapter()
	{
		if(s_mdcAdapter == null)
			loadExtensions();
		return s_mdcAdapter;
	}

	private static void loadExtensions()
	{
		IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(SLF4J_IMPL_POINT);
		if(elems.length == 0)
			return;

		IConfigurationElement elem = elems[0];
		try
		{
			s_loggerFactory = (ILoggerFactory)elem.createExecutableExtension("loggerFactory");
		}
		catch(CoreException e)
		{
		}
		try
		{
			s_markerFactory = (IMarkerFactory)elem.createExecutableExtension("markerFactory");
		}
		catch(CoreException e)
		{
		}
		try
		{
			s_mdcAdapter = (MDCAdapter)elem.createExecutableExtension("mdcAdapter");
		}
		catch(CoreException e)
		{
		}
	}
}
