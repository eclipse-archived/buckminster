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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * The P4 Client specification.
 * 
 * @author thhal
 */
public class ClientSpec extends DepotObject
{
	public enum LineEnd
	{
		/**
		 * Use mode native to the client (default).
		 */
		local,

		/**
		 * Macintosh-style: <code>CR</code> only.
		 */
		mac,

		/**
		 * Shared mode: Line endings are <code>LF</code> with any <code>CR/LF</code> pairs translated to
		 * <code>LF</code>-only style before storage or syncing with the depot.<br/> When you sync your client
		 * workspace, line endings will be <code>LF</code>. If you edit the file on a Windows machine, and your
		 * editor inserts <code>CR</code>s before each <code>LF</code>, the extra <code>CR</code>s will not
		 * appear in the archive file.<br/> The most common use of the <code>share</code> option is for users of
		 * Windows workstations who have UNIX home directories mounted as network drives; if they sync files from UNIX,
		 * but edit the files on the Windows machine, the <code>share</code> option eliminates any problems caused by
		 * Windows-based editors' insertion of extra carriage return characters at line endings.
		 */
		share,

		/**
		 * UNIX-style line endings: <code>LF</code> only.
		 */
		unix,

		/**
		 * Windows-style: <code>CR, LF</code>.
		 */
		win
	}

	private boolean m_dirty;

	/**
	 * Create a ClientSpec based on <code>info</code> obtained from <code>conn</code>.
	 * 
	 * @param conn
	 *            The P4 Connection
	 * @param info
	 *            Information stemming from a <code>p4 client</code> command.
	 * @throws BuckminsterException
	 */
	public ClientSpec(Connection conn, Map<String, String> info) throws BuckminsterException
	{
		super(conn, info);
		m_dirty = false;
	}

	/**
	 * Guarantee that the mapping is included in the view. Create a new mapping if needed.
	 * 
	 * @param depotPath
	 *            The path in the depot in UNC format
	 * @param localPath
	 *            The absolute location on disk for the local path.
	 * @return <code>true</code> if the location was added to the view, <code>false</code> if it was already
	 *         present.
	 */
	public boolean addLocation(IPath depotPath, IPath localPath) throws BuckminsterException
	{
		if(this.entriesContainsMapping(this.getView(), depotPath, localPath))
			return false;

		ViewEntry[] entries = this.getView();
		List<ViewEntry> newEntries = new ArrayList<ViewEntry>(entries.length + 1);
		for(ViewEntry entry : entries)
		{
			if(DepotURI.pathEquals(depotPath, entry.getDepotPath()))
				throw new BuckminsterException("Depot path " + depotPath + " is already mapped to "
						+ entry.getLocalPath() + " in client " + this.getClient());

			newEntries.add(entry);
		}

		IPath root = this.getRoot();
		IPath clientRoot = new Path("//" + this.getClient());
		IPath clientPath = null;
		if(root == null)
			clientPath = clientRoot.append(localPath.makeRelative());
		else
		{
			if(root.isPrefixOf(localPath))
				clientPath = clientRoot.append(localPath.removeFirstSegments(root.segmentCount()));
			else
			{
				for(IPath altPath : this.getAltRoots())
				{
					if(altPath.isPrefixOf(localPath))
					{
						clientPath = clientRoot.append(localPath.removeFirstSegments(altPath.segmentCount()));
						break;
					}
				}
			}
		}

		if(clientPath == null)
			throw new BuckminsterException("Local path " + localPath + " is not a root or altroot of client "
					+ this.getClient());

		newEntries.add(new ViewEntry(depotPath.append("..."), clientPath.append("...")));
		this.setView(newEntries.toArray(new ViewEntry[newEntries.size()]));
		return true;
	}

	/**
	 * Check if this spec has any pending changes and commit them if that is the case.
	 * 
	 * @throws BuckminsterException
	 */
	public synchronized void commitChanges() throws CoreException
	{
		if(m_dirty)
		{
			this.getConnection().setClientSpec(this.getInfo());
			m_dirty = false;
		}
	}

	/**
	 * Returns true if this view is mapping <code>depotPath</code> to <code>localPath</code>.
	 * 
	 * @param depotPath
	 *            The path in the depot in UNC format and without trailing &quot;...&quot;
	 * @param localPath
	 *            The absolute location on disk for the local path.
	 * @return
	 */
	public boolean containsMapping(IPath depotPath, IPath localPath)
	{
		return this.entriesContainsMapping(this.getView(), depotPath, localPath);
	}

	/**
	 * The date and time that any part of the client workspace specification was last accessed by any Perforce command.
	 */
	public Date getAccess() throws CoreException
	{
		return this.getParsedDate("Access");
	}

	/**
	 * Returns up to two alternate client workspace roots.
	 * 
	 * @return The alternate roots. This array may have a lenght of zero.
	 */
	public IPath[] getAltRoots()
	{
		IPath[] altRoots;
		String ars = this.get("AltRoots");
		if(ars == null)
			return Trivial.EMPTY_PATH_ARRAY;

		String[] paths = splitMultiPaths(ars);
		int top = paths.length;
		altRoots = new IPath[top];
		for(int idx = 0; idx < top; ++idx)
			altRoots[idx] = new Path(paths[idx]);
		return altRoots;
	}

	/**
	 * The client workspace name, as specified in the <code>P4CLIENT</code> environment variable or its equivalents.
	 * 
	 * @return The name of the client workspace.
	 */
	public String getClient()
	{
		return this.get("Client");
	}

	/**
	 * A textual description of the client workspace. The default text is Created by owner.
	 * 
	 * @return The description or <code>null</code> if no description exists.
	 */
	public String getDescription()
	{
		return this.get("Description");
	}

	/**
	 * The name of the host machine on which this client workspace resides. If included, operations on this client
	 * workspace can be run only from this host.
	 * 
	 * @return The name of the <code>host</code> or null when no host is set.
	 */
	public String getHost()
	{
		return this.get("Host");
	}

	/**
	 * An option that control carriage-return/linefeed (CR/LF) conversion.
	 * 
	 * @return The setting of the <code>LineEnd</code> option.
	 */
	public LineEnd getLineEnd()
	{
		return LineEnd.valueOf(this.get("LineEnd"));
	}

	/**
	 * Convert the <code>clientPath</code> into possible local directories using the <code>AltRoots</code>.
	 * 
	 * @param clientPath
	 * @return An array, possibly empty but never <code>null</code>, of local directories.
	 */
	public IPath[] getLocalAltRoots(IPath clientPath)
	{
		IPath[] altRoots = this.getAltRoots();
		int top = altRoots.length;
		for(int idx = 0; idx < top; ++idx)
			altRoots[idx] = this.resolveClientPath(clientPath, altRoots[idx]);
		return altRoots;
	}

	/**
	 * Convert the <code>clientPath</code> into a local directory using the <code>Root</code>.
	 * 
	 * @param clientPath
	 * @return The local directory.
	 */
	public IPath getLocalRoot(IPath clientPath)
	{
		return this.resolveClientPath(clientPath, this.getRoot());
	}

	/**
	 * The Perforce user name of the user who owns the client workspace.
	 * 
	 * @return The owner of the client workspace.
	 */
	public String getOwner()
	{
		return this.get("Owner");
	}

	/**
	 * The directory (on the local host) relative to which all the files in the <code>view</code> are specified. The
	 * default is the current working directory.
	 * 
	 * @return The local root directory.
	 */
	public IPath getRoot()
	{
		String root = this.get("Root");
		return (root == null || root.equals("null"))
				? null
				: new Path(root);
	}

	/**
	 * The date the client workspace specification was last modified.
	 */
	public Date getUpdate() throws CoreException
	{
		return this.getParsedDate("Update");
	}

	/**
	 * Gets the mappings between files in the depot and files in the client workspace.
	 * 
	 * @return An array of mappings.
	 */
	public ViewEntry[] getView()
	{
		return this.getViewSpec();
	}

	/**
	 * Obtains the setting of the <code>allwrite</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setAllWrite(boolean flag)
	 */
	public boolean isAllWrite()
	{
		return this.isOption("allwrite");
	}

	/**
	 * Obtains the setting of the <code>clobber</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setClobber(boolean flag)
	 */
	public boolean isClobber()
	{
		return this.isOption("clobber");
	}

	/**
	 * Obtains the setting of the <code>compress</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setCompress(boolean flag)
	 */
	public boolean isCompress()
	{
		return this.isOption("compress");
	}

	/**
	 * Obtains the setting of the <code>locked</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setLocked(boolean flag)
	 */
	public boolean isLocked()
	{
		return this.isOption("locked");
	}

	/**
	 * Obtains the setting of the <code>modtime</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setModTime(boolean flag)
	 */
	public boolean isModTime()
	{
		return this.isOption("modtime");
	}

	/**
	 * Obtains the setting of the <code>rmdir</code> option.
	 * 
	 * @return The current setting of the option.
	 * @see #setRmDir(boolean flag)
	 */
	public boolean isRmDir()
	{
		return this.isOption("rmdir");
	}

	/**
	 * If set, unopened files on the client are left writable.
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setAllWrite(boolean flag)
	{
		this.setOption("allwrite", "noallwrite", flag);
	}

	/**
	 * Up to two optional alternate client workspace roots. Perforce client programs use the first of the main and
	 * alternate roots to match the client program's current working directory. This enables users to use the same
	 * Perforce client specification on multiple platforms with different directory naming conventions. If you are using
	 * a Windows directory in any of your client roots, you must specify the Windows directory as your main client root
	 * and specify your other client root directories in the AltRoots: field. For example, an engineer building products
	 * on multiple platforms might specify a main client root of C:\Projects\Build for Windows builds, and an alternate
	 * root of /staff/userid/projects/build for any work on UNIX builds.
	 * 
	 * @param altRoots
	 *            Up to two alternative roots.
	 */
	public synchronized void setAltRoots(IPath[] altRoots)
	{
		if(altRoots == null || altRoots.length == 0)
		{
			m_dirty = (this.remove("AltRoots") != null);
			return;
		}
		if(altRoots.length > 2)
			throw new IllegalArgumentException("Max 2 paths allowed for AltRoots");

		StringBuilder bld = new StringBuilder();
		boolean first = true;
		for(IPath altRoot : altRoots)
		{
			if(first)
				first = false;
			else
				bld.append(' ');

			String path = altRoot.toString();
			if(path.indexOf(' ') >= 0)
			{
				bld.append('"');
				bld.append(path);
				bld.append('"');
			}
			else
				bld.append(path);
		}
		String newRoots = bld.toString();
		m_dirty = !newRoots.equals(this.put("AltRoots", newRoots));
	}

	/**
	 * If set, a <code>p4 sync</code> overwrites (&quot;clobbers&quot;) writable-but-unopened files in the client that
	 * have the same name as the newly-synced files
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setClobber(boolean flag)
	{
		this.setOption("clobber", "noclobber", flag);
	}

	/**
	 * If set, the data stream between the client and the server is compressed.
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setCompress(boolean flag)
	{
		this.setOption("compress", "nocompress", flag);
	}

	/**
	 * A textual description of the client workspace. The default text is Created by owner.
	 * 
	 * @param host
	 *            The description. Might be <code>null</code>.
	 */
	public synchronized void setDescription(String description)
	{
		if(description == null || description.length() == 0)
			m_dirty = (this.remove("Description") != null);
		else
			m_dirty = !description.equals(this.put("Description", description));
	}

	/**
	 * The hostname must be provided exactly as it appears in the output of p4 info when run from that host.<br/>This
	 * field is meant to prevent accidental misuse of client workspaces on the wrong machine. It doesn't provide
	 * security, since the actual value of the host name can be overridden with the -H flag to any p4 command, or with
	 * the P4HOST environment variable. For a similar mechanism that does provide security, use the IP address
	 * restriction feature of p4 protect.
	 * 
	 * @param host
	 *            The name of the host
	 */
	public synchronized void setHost(String host)
	{
		if(host == null || host.length() == 0)
			m_dirty = (this.remove("Host") != null);
		else
			m_dirty = !host.equals(this.put("Host", host));
	}

	/**
	 * Set the option that control carriage-return/linefeed (CR/LF) conversion.
	 * 
	 * @param lineEnd
	 */
	public synchronized void setLineEnd(LineEnd lineEnd)
	{
		if(lineEnd == null)
			lineEnd = LineEnd.local;
		m_dirty = !lineEnd.name().equals(this.put("LineEnd", lineEnd.name()));
	}

	/**
	 * Grant or deny other users permission to edit the client specification (To make a locked client specification
	 * truly effective, you should also set a the client&apos;s owner&apos;s password with p4 passwd.)<br/> If locked,
	 * only the owner is able to use, edit, or delete the client spec. Perforce administrators can override the lock by
	 * using the -f (force) flag with p4 client.
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setLocked(boolean flag)
	{
		this.setOption("locked", "unlocked", flag);
	}

	/**
	 * For files without the +m (modtime) file type modifier
	 * <ul>
	 * <li>If modtime is set, the modification date (on the local filesystem) of a newly synced file is the datestamp
	 * on the file when the file was last modified.</li>
	 * <li>If nomodtime is set, the modification date is the date and time of sync.</li>
	 * </ul>
	 * For files with the +m (modtime) file type modifier, the modification date (on the local filesystem) of a newly
	 * synced file is the datestamp on the file when the file was submitted to the depot, regardless of the setting of
	 * modtime or nomodtime on the client.
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setModTime(boolean flag)
	{
		this.setOption("modtime", "nomodtime", flag);
	}

	/**
	 * Sets the Perforce user name of the user who owns the client workspace. The default is the user who created the
	 * client workspace.
	 */
	public synchronized void setOwner(String owner)
	{
		if(owner == null || owner.length() == 0)
			m_dirty = (this.remove("Owner") != null);
		else
			m_dirty = !owner.equals(this.put("Owner", owner));
	}

	/**
	 * If set, p4 sync deletes empty directories in a client if all files in the directory have been removed.
	 * 
	 * @param flag
	 *            <code>true</code> if this option should be set.
	 */
	public void setRmDir(boolean flag)
	{
		this.setOption("rmdir", "normdir", flag);
	}

	/**
	 * Sets the root directory on the local host. The path must be absolute.
	 * 
	 * @param root
	 *            The new root.
	 */
	public synchronized void setRoot(IPath root) throws BuckminsterException
	{
		if(root == null || !root.isAbsolute())
			throw new IllegalArgumentException("root cannot be null or relative");
		String osName = root.toOSString();
		m_dirty = !osName.equals(this.put("Root", osName));
	}

	/**
	 * Specifies the mappings between files in the depot and files in the client workspace.
	 * 
	 * @param view
	 *            An array of mappings.
	 */
	public synchronized void setView(ViewEntry[] view)
	{
		int top = view.length;
		int idx = 0;
		while(idx < top)
		{
			String newView = view[idx].toString();
			String oldView = this.put("View" + Integer.toString(idx), newView);
			if(!m_dirty && !newView.equals(oldView))
				m_dirty = true;
			++idx;
		}

		while(this.remove("View" + Integer.toString(idx)) != null)
		{
			m_dirty = true;
			++idx;
		}
	}

	private boolean entriesContainsMapping(ViewEntry[] entries, IPath depotPath, IPath localPath)
	{
		for(ViewEntry entry : entries)
		{
			if(DepotURI.pathEquals(depotPath, entry.getDepotPath()))
			{
				if(DepotURI.pathEquals(this.getLocalRoot(entry.getLocalPath()), localPath))
					return true;

				// Try AltRoots also.
				//
				for(IPath altPath : this.getLocalAltRoots(entry.getLocalPath()))
					if(DepotURI.pathEquals(altPath, localPath))
						return true;
			}
		}
		return false;
	}

	private String[] getSplitOptions()
	{
		return this.get("Options").split("\\s+");
	}

	private boolean isOption(String enabled)
	{
		for(String option : this.getSplitOptions())
			if(enabled.equals(option))
				return true;
		return false;
	}

	private IPath resolveClientPath(IPath clientPath, IPath root)
	{
		int numSegs = clientPath.segmentCount();
		if(!clientPath.isUNC() && numSegs >= 2)
			//
			// This is not a client path.
			//
			return null;

		if(!clientPath.segment(0).equals(this.getClient()))
			//
			// Client path does belong to this client.
			//
			return null;

		clientPath = clientPath.removeFirstSegments(1);
		if(root == null)
			clientPath.makeAbsolute();
		else
			clientPath = root.append(clientPath);
		return clientPath;
	}

	private synchronized void setOption(String enabled, String disabled, boolean flag)
	{
		boolean found = false;
		boolean changed = false;
		String[] options = this.getSplitOptions();
		int top = options.length;
		for(int idx = 0; idx < top; ++idx)
		{
			String option = options[idx];
			if(option.equals(enabled))
			{
				found = true;
				if(!flag)
				{
					changed = true;
					options[idx] = disabled;
				}
				break;
			}
			if(option.equals(disabled))
			{
				found = true;
				if(flag)
				{
					changed = true;
					options[idx] = enabled;
				}
				break;
			}
		}

		if(!found)
			throw new IllegalArgumentException("No such option: " + enabled);

		if(changed)
		{
			StringBuilder bld = new StringBuilder();
			bld.append(options[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				bld.append(' ');
				bld.append(options[idx]);
			}
			this.put("Options", bld.toString());
		}
		m_dirty = changed;
	}
}
