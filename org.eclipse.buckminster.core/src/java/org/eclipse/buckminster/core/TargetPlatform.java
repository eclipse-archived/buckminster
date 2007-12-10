/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core;

import java.io.File;
import java.net.URL;

import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * @author Thomas Hallgren
 */
public class TargetPlatform extends AbstractExtension implements ITargetPlatform
{
	private static ITargetPlatform s_instance = null;

	public static final String TARGET_PLATFORM_PROVIDERS_POINT = CorePlugin.CORE_NAMESPACE + ".targetPlatformProviders";

	public static synchronized ITargetPlatform getInstance() throws CoreException
	{
		if(s_instance == null)
		{
			IExtensionRegistry exReg = Platform.getExtensionRegistry();
			IConfigurationElement[] elems = exReg.getConfigurationElementsFor(TARGET_PLATFORM_PROVIDERS_POINT);

			IConfigurationElement candidate = null;
			int maxPrio = -1;
			for(IConfigurationElement elem : elems)
			{
				String prioStr = elem.getAttribute("priority");
				if(prioStr == null)
					//
					// Bogus entry. The priority attribute is mandatory
					//
					continue;

				try
				{
					int prio = Integer.parseInt(prioStr);
					if(prio > maxPrio)
					{
						maxPrio = prio;
						candidate = elem;
					}
				}
				catch(NumberFormatException e)
				{
					continue;
				}
			}

			if(candidate == null)
				throw BuckminsterException.fromMessage("No targetPlatformProvider has been registered with the targetPlatformProviders extension point");
			s_instance = (ITargetPlatform)candidate.createExecutableExtension("class");
		}
		return s_instance;
	}

	public static final String TARGET_PREFIX = "target";

	public static final String TARGET_OS = TARGET_PREFIX + ".os";

	public static final String TARGET_WS = TARGET_PREFIX + ".ws";

	public static final String TARGET_ARCH = TARGET_PREFIX + ".arch";

	public static final String TARGET_NL = TARGET_PREFIX + ".nl";

	public static final String TARGET_LOCATION = TARGET_PREFIX + ".location";

	public String getArch()
	{
		return Platform.getOSArch();
	}

	public String getNL()
	{
		return Platform.getNL();
	}

	public String getOS()
	{
		return Platform.getOS();
	}

	public String getWS()
	{
		return Platform.getWS();
	}

	public File getLocation()
	{
		return getPlatformInstallLocation();
	}

	public static File getPlatformInstallLocation()
	{
		Location location = Platform.getInstallLocation();
		if(location == null)
			return null;

		URL eclipseHome = location.getURL();
		if(eclipseHome == null)
			return null;

		assert ("file".equals(eclipseHome.getProtocol()));
		return FileUtils.getFile(eclipseHome);
	}
}
