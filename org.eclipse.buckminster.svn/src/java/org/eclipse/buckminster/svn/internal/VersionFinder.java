/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.svn.internal;

import java.net.MalformedURLException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

public class VersionFinder extends SvnSession implements IVersionFinder
{
	public VersionFinder(String repositoryURI) throws CoreException
	{
		super(repositoryURI, null, null);
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		ISVNDirEntry entry = this.getTrunkModuleRoot();
		if(entry != null)
		{
			return new VersionMatch(
				VersionFactory.defaultVersion(),
				VersionSelectorFactory.changeNumber(entry.getLastChangedRevision().getNumber()));
		}
		return null;
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		if(query.matches(VersionFactory.defaultVersion()))
			//
			// The query allows us to use latest on default. So that's what
			// we will be doing.
			//
			return this.getDefaultVersion(monitor);

		VersionMatch best = null;
		IVersionSelector exact = query.getExactMatch();
		switch(query.getType())
		{
		case TAG:
			ISVNDirEntry[] allTags = this.internalGetEntries(false, monitor);
			if(exact != null)
			{
				for(ISVNDirEntry entry : allTags)
				{
					if(entry.getPath().equals(exact.getQualifier()))
					{
						best = new VersionMatch(
							query.createVersion(exact),
							VersionSelectorFactory.changeNumber(entry.getLastChangedRevision().getNumber()));
						break;
					}
				}
			}
			else
			{
				for(ISVNDirEntry entry : allTags)
				{
					IVersionSelector selector = VersionSelectorFactory.tag(entry.getPath());
					if(query.matches(selector))
					{
						IVersion version = query.createVersion(selector);
						if(best == null || best.getVersion().compareTo(version) < 0)
							best = new VersionMatch(
									version,
									VersionSelectorFactory.changeNumber(entry.getLastChangedRevision().getNumber()));
					}
				}
			}
			break;

		case LATEST:
			//
			// trunk/latest is handled as default version already
			//
			ISVNDirEntry[] allBranches = this.internalGetEntries(true, monitor);
			if(exact != null)
			{
				String branchName = exact.getBranchName();
				for(ISVNDirEntry entry : allBranches)
				{
					if(entry.getPath().equals(branchName))
					{
						best = new VersionMatch(
								query.createVersion(exact),
								VersionSelectorFactory.changeNumber(branchName, entry.getLastChangedRevision().getNumber()));
						break;
					}
				}
			}
			else
			{
				for(ISVNDirEntry entry : allBranches)
				{
					String branch = entry.getPath();
					IVersionSelector branchSelector = VersionSelectorFactory.latest(branch);
					if(query.matches(branchSelector))
					{
						IVersion version = query.createVersion(branchSelector);
						if(best == null || best.getVersion().compareTo(version) < 0)
							best = new VersionMatch(
								version,
								VersionSelectorFactory.changeNumber(branch, entry.getLastChangedRevision().getNumber()));
					}
				}
			}
			break;

		case CHANGE_NUMBER:
		case TIMESTAMP:
			if(exact == null)
				throw new BuckminsterException("An exlicit version designator is required in order to resolve a timestamp or change number");

			long qual = exact.getNumericQualifier();
			if(exact.isDefaultBranch())
			{
				ISVNDirEntry entry = this.getTrunkModuleRoot();
				if(entry != null)
				{
					best = new VersionMatch(
						VersionFactory.defaultVersion(),
						query.getType() == VersionSelectorType.TIMESTAMP
							? VersionSelectorFactory.timestamp(qual)
							: VersionSelectorFactory.changeNumber(qual));
				}
			}
			else
			{
				String branchName = exact.getBranchName();
				for(ISVNDirEntry entry : this.internalGetEntries(true, monitor))
				{
					if(entry.getPath().equals(branchName))
					{
						best = new VersionMatch(
								query.createVersion(exact),
								query.getType() == VersionSelectorType.TIMESTAMP
									? VersionSelectorFactory.timestamp(qual)
									: VersionSelectorFactory.changeNumber(qual));
						break;
					}
				}
			}
			break;
		default:
		}
		return best;		
	}

	private ISVNDirEntry[] internalGetEntries(boolean branches, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			SVNUrl url = this.getSVNRootUrl(branches);
			ISVNDirEntry[] entries = this.getClientAdapter().getList(url, SVNRevision.HEAD, false);
			if(entries != null)
				return entries;
		}
		catch(SVNClientException e)
		{
			// Assume that this is due to missing repository
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return new ISVNDirEntry[0];
	}

	private ISVNDirEntry getTrunkModuleRoot() throws CoreException
	{
		try
		{
			SVNUrl url = this.getSVNUrl(null);
			return this.getClientAdapter().getDirEntry(url, SVNRevision.HEAD);
		}
		catch(SVNClientException e)
		{
			// Assume that this is due to missing repository
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return null;
	}
}
