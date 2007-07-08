/*******************************************************************************
 * Copyright (c) 2004 - 2007
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.svn.internal;

import java.io.File;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.svnclientadapter.ISVNInfo;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;

/**
 * @author Thomas Hallgren
 */
public class SvnReaderType extends AbstractReaderType
{
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		return new SvnRemoteFileReader(this, providerMatch, monitor);
	}

	@Override
	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		SvnSession session = new SvnSession(repositoryLocation, versionSelector, -1L, null, new RMContext(null));
		try
		{
			return session.getLastTimestamp();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		try
		{
			ISVNInfo info = SVNProviderPlugin.getPlugin().getSVNClientManager().createSVNClient()
					.getInfoFromWorkingCopy(workingCopy);
			if(info != null)
				return info.getLastChangedDate();
			return null;
		}
		catch(SVNClientException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask(null, 1);
		SvnSession session = new SvnSession(repositoryLocation, versionSelector, -1L, null, new RMContext(null));
		try
		{
			return session.getLastChangeNumber();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		try
		{
			ISVNInfo info = SVNProviderPlugin.getPlugin().getSVNClientManager().createSVNClient()
					.getInfoFromWorkingCopy(workingCopy);
			if(info != null)
			{
				SVNRevision.Number lastRev = info.getLastChangedRevision();
				if(lastRev != null)
					return lastRev.getNumber();
			}
			return -1;
		}
		catch(SVNClientException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}
}
