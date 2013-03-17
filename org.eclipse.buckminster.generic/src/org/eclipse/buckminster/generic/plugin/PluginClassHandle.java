/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.plugin;

import org.eclipse.buckminster.generic.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * Handle to a class in a plugin.
 * 
 * @author Henrik Lindberg
 */
public class PluginClassHandle<T> {
	private final static String ATT_CLASS = "class"; //$NON-NLS-1$

	protected final IConfigurationElement configElement;

	private final Class<T> clazz;

	private T handle;

	private final Plugin plugin;

	protected PluginClassHandle(Plugin plugin, IConfigurationElement configElement, Class<T> clazz, String requiredElement) {
		this.plugin = plugin;
		this.configElement = configElement;
		this.clazz = clazz;

		if (!configElement.getName().equals(requiredElement))
			throw new IllegalArgumentException(NLS.bind(Messages.requiredElement_0_not_correct_expected_1, configElement.getName(), requiredElement));
	}

	public synchronized T getHandle() {
		if (handle != null)
			return handle;
		try {
			handle = clazz.cast(configElement.createExecutableExtension(ATT_CLASS));
		} catch (CoreException e) {
			logLoadError(configElement, e);
		}
		return handle;
	}

	protected void logLoadError(IConfigurationElement configElem, Exception e) {
		String name = configElem.getName();
		String msg = NLS.bind(Messages.failed_to_load_extension_point_element_0_in_1, (name == null ? "[" + Messages.missing_name_attribute + "]" //$NON-NLS-1$ //$NON-NLS-2$
		: name), configElem.getDeclaringExtension().getNamespaceIdentifier());
		ILog log = plugin.getLog();

		log.log(new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), msg));
	}
}
