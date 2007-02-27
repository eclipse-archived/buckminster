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

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author thhal
 */
public class DepotFolder extends DepotObject
{
	DepotFolder(Connection conn, Map<String, String> info)
	{
		super(conn, info);
	}

	public IPath getDepotPath()
	{
		return new Path(this.get("dir"));
	}

	public DepotFile[] getFiles(boolean includeDeleted) throws CoreException
	{
		List<DepotFile> files = this.getConnection().getFiles(new String[] { this.getListPath() }, includeDeleted);
		return files.toArray(new DepotFile[files.size()]);
	}

	public DepotFolder[] getFolders(boolean includeDeleted) throws CoreException
	{
		String listPath = this.getListPath();
		String[] args = includeDeleted
				? new String[] { "-D", listPath }
				: new String[] { listPath };
		Connection conn = this.getConnection();
		List<Map<String, String>> data = conn.exec("dirs", args);

		int top = data.size();
		DepotFolder[] folders = new DepotFolder[top];
		for(int idx = 0; idx < top; idx++)
			folders[idx] = new DepotFolder(conn, data.get(idx));

		return folders;
	}

	public IPath getClientPath() throws CoreException
	{
		String path = this.getConnection().where(this.getDepotPath().append("..."))[2];
		return new Path(path.substring(0, path.length() - 4));
	}

	@Override
	public String toString()
	{
		return this.getDepotPath().toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!(o instanceof DepotFolder))
			return false;
		DepotFolder that = (DepotFolder)o;

		if(!this.getDepotPath().equals(that.getDepotPath()))
			return false;

		if(!this.getConnection().equals(that.getConnection()))
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int hc = 17;

		hc = 37 * hc + this.getDepotPath().hashCode();
		hc = 37 * hc + this.getConnection().hashCode();

		return hc;
	}

	private String getListPath()
	{
		return this.getDepotPath().append("*").toString();
	}
}
