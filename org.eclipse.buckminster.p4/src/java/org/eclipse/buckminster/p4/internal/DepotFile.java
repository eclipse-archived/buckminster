/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.io.File;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/**
 * @author thhal
 */
public class DepotFile extends DepotObject
{
	public DepotFile(Connection conn, Map<String, String> info)
	{
		super(conn, info);
	}

	public String getHeadAction()
	{
		return this.get("headAction"); //$NON-NLS-1$
	}

	public IPath getClientPath()
	{
		return new Path(this.get("clientFile")); //$NON-NLS-1$
	}

	public IPath getDepotPath()
	{
		return new Path(this.get("depotFile")); //$NON-NLS-1$
	}

	public void copyTo(File tempFile) throws CoreException
	{
		this.getConnection().exec("print", new String[] { "-o", tempFile.getAbsolutePath(), this.getDepotPath().toString() }); //$NON-NLS-1$ //$NON-NLS-2$
	}
}

