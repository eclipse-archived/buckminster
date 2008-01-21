/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core;

import java.io.File;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public interface ITargetPlatform
{
	/**
	 * Returns the target operating system
	 */
	String getOS();

	/**
	 * Returns the target windowing system.
	 */
	String getWS();

	/**
	 * Returns the target system architecture
	 */
	String getArch();

	/**
	 * Returns the target locale
	 */
	String getNL();

	/**
	 * Returns the target platform's main location
	 */
	File getLocation();

	/**
	 * Returns a list of all components (features, plugins, and fragments) that
	 * are known to the target platform.
	 */
	List<ComponentIdentifier> getComponents() throws CoreException;
}
