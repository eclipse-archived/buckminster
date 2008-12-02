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

import java.util.Date;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;

/**
 * @author thhal
 */
public class Label extends DepotObject
{
	public Label(Connection conn, Map<String, String> info)
	{
		super(conn, info);
	}

	public Date getAccess() throws CoreException
	{
		return this.getParsedDate("Access"); //$NON-NLS-1$
	}

	public String getDescription()
	{
		return this.get("Description"); //$NON-NLS-1$
	}

	public String getLabel()
	{
		return this.get("Label"); //$NON-NLS-1$
	}

	public String getOwner()
	{
		return this.get("Owner"); //$NON-NLS-1$
	}

	public Date getUpdate() throws CoreException
	{
		return this.getParsedDate("Update"); //$NON-NLS-1$
	}

	public ViewEntry[] getView()
	{
		return this.getViewSpec();
	}

	public boolean isLocked()
	{
		return "locked".equals(this.get("Options")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public String toString()
	{
		return this.getLabel();
	}
}
