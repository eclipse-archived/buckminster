/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg.internal;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.ICVSFolder;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.client.Session;
import org.eclipse.team.internal.ccvs.core.resources.CVSWorkspaceRoot;
import org.eclipse.team.internal.ccvs.core.resources.RemoteFolderSandbox;


/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSSession
{
	private final ICVSRepositoryLocation m_location;
	private final String m_moduleName;
	private Session m_readerSession;

	public CVSSession(String repositoryURI) throws CoreException
	{
		StringTokenizer tokenizer = new StringTokenizer(repositoryURI, ",");
		try
		{
			String repo = tokenizer.nextToken();
			m_location = CVSReaderType.getLocationFromString(repo);
			String module = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
			if(module != null && module.startsWith("/"))
				module = module.substring(1);
			m_moduleName = module;
		}
		catch(NoSuchElementException e)
		{
			throw BuckminsterException.fromMessage("Repository URI not in <cvs root>,<module> format");
		}
	}

	public synchronized void close()
	{
		if(m_readerSession != null)
		{
			m_readerSession.close();
			m_readerSession = null;
		}
	}

	public String getRepository()
	{
		String location = m_location.getLocation(false);
		if(m_moduleName == null)
			return location;

		return location + '/' + m_moduleName;
	}

	public String getFilePrefix()
	{
		StringBuilder bld = new StringBuilder(m_location.getRootDirectory());
		bld.append('/');
		if(m_moduleName != null)
		{
			bld.append(m_moduleName);
			bld.append('/');
		}
		return bld.toString();
	}

	public final ICVSRepositoryLocation getLocation()
	{
		return m_location;
	}

	public final String getModuleName()
	{
		return m_moduleName;
	}

	synchronized Session getReaderSession(IProgressMonitor monitor)
	throws CoreException
	{
		if(m_readerSession == null)
		{
			ICVSFolder root = CVSWorkspaceRoot.getCVSFolderFor(ResourcesPlugin.getWorkspace().getRoot());
			m_readerSession = new Session(getLocation(), root, false /* output to console */);
			m_readerSession.open(monitor, false);
		}
		return m_readerSession;
	}

	RemoteFolderSandbox getSandbox(CVSTag tag)
	{
		return new RemoteFolderSandbox(null, this.getLocation(), this.getModuleName(), tag);
	}
}

