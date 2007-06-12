/*******************************************************************************
 * Copyright (c) 2004, 2005
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

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.svnclientadapter.ISVNInfo;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;

/**
 * @author thhal
 */
public class SvnReaderType extends AbstractReaderType
{
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new SvnRemoteFileReader(this, providerMatch);
	}

	@Override
	public Date getLastModification(String repositoryLocation, IVersionSelector versionSelector,
			IProgressMonitor monitor) throws CoreException
	{
		String branch = null;
		String tag = null;
		if(versionSelector != null)
		{
			branch = versionSelector.getBranchName();
			if(versionSelector.getType() == VersionSelectorType.TAG)
				tag = versionSelector.getQualifier();
		}
		SvnSession session = new SvnSession(repositoryLocation, branch, tag);
		try
		{
			return session.getLastTimestamp();
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public long getLastRevision(String repositoryLocation, IVersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException
	{
		String branch = null;
		String tag = null;
		if(versionSelector != null)
		{
			branch = versionSelector.getBranchName();
			if(versionSelector.getType() == VersionSelectorType.TAG)
				tag = versionSelector.getQualifier();
		}
		SvnSession session = new SvnSession(repositoryLocation, branch, tag);
		try
		{
			return session.getLastChangeNumber();
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
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
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider.getURI(nodeQuery.getProperties()));
	}

	public static SVNRevision getSVNRevision(IVersionSelector vs)
	{
		switch(vs.getType())
		{
		case CHANGE_NUMBER:
			return new SVNRevision.Number(Long.parseLong(vs.getQualifier()));
		case TIMESTAMP:
			return new SVNRevision.DateSpec(new Date(vs.getNumericQualifier()));
		default:
			return SVNRevision.HEAD;
		}
	}
}
