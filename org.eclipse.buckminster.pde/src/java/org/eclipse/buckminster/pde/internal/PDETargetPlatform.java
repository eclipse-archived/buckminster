/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.File;

import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.pde.core.plugin.TargetPlatform;

/**
 * @author Thomas Hallgren
 */
public class PDETargetPlatform extends AbstractExtension implements ITargetPlatform
{
	public String getArch()
	{
		return TargetPlatform.getOSArch();
	}

	public String getNL()
	{
		return TargetPlatform.getNL();
	}

	public String getOS()
	{
		return TargetPlatform.getOS();
	}

	public String getWS()
	{
		return TargetPlatform.getWS();
	}

	public File getLocation()
	{
		String location = TargetPlatform.getLocation();
		return (location == null || location.length() == 0) ? null : new File(location);
	}
}
