/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.subversive.internal;

import java.net.MalformedURLException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.polarion.team.svn.core.client.ClientWrapperException;
import org.polarion.team.svn.core.client.DirEntry;
import org.polarion.team.svn.core.client.Revision;
import org.polarion.team.svn.core.operation.SVNNullProgressMonitor;
import org.tmatesoft.svn.core.SVNURL;

public class SubversiveVersionFinder extends SubversiveSession implements IVersionFinder {
	public SubversiveVersionFinder(String repositoryURI) throws CoreException {
		super(repositoryURI, null, null);
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException {
		DirEntry entry = this.getTrunkModuleRoot();
		if (entry != null) {
			return new VersionMatch(VersionFactory.defaultVersion(), VersionSelectorFactory.changeNumber(entry.lastChangedRevision));
		}
		return null;
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException {
		if (query.matches(VersionFactory.defaultVersion()))
			//
			// The query allows us to use latest on default. So that's what
			// we will be doing.
			//
			return this.getDefaultVersion(monitor);

		VersionMatch best = null;
		IVersionSelector exact = query.getExactMatch();
		switch (query.getType()) {
		case TAG:
			DirEntry[] allTags = this.internalGetEntries(false, monitor);
			if (exact != null) {
				for (DirEntry entry : allTags) {
					if (entry.path.equals(exact.getQualifier())) {
						best = new VersionMatch(query.createVersion(exact), VersionSelectorFactory.changeNumber(entry.lastChangedRevision));
						break;
					}
				}
			} else {
				for (DirEntry entry : allTags) {
					IVersionSelector selector = VersionSelectorFactory.tag(entry.path);
					if (query.matches(selector)) {
						IVersion version = query.createVersion(selector);
						if (best == null || best.getVersion().compareTo(version) < 0)
							best = new VersionMatch(version, VersionSelectorFactory.changeNumber(entry.lastChangedRevision));
					}
				}
			}
			break;

		case LATEST:
			//
			// trunk/latest is handled as default version already
			//
			DirEntry[] allBranches = this.internalGetEntries(true, monitor);
			if (exact != null) {
				String branchName = exact.getBranchName();
				for (DirEntry entry : allBranches) {
					if (entry.path.equals(branchName)) {
						best = new VersionMatch(query.createVersion(exact), VersionSelectorFactory.changeNumber(branchName, entry.lastChangedRevision));
						break;
					}
				}
			} else {
				for (DirEntry entry : allBranches) {
					String branch = entry.path;
					IVersionSelector branchSelector = VersionSelectorFactory.latest(branch);
					if (query.matches(branchSelector)) {
						IVersion version = query.createVersion(branchSelector);
						if (best == null || best.getVersion().compareTo(version) < 0)
							best = new VersionMatch(version, VersionSelectorFactory.changeNumber(branch, entry.lastChangedRevision));
					}
				}
			}
			break;

		case CHANGE_NUMBER:
		case TIMESTAMP:
			if (exact == null)
				throw new BuckminsterException("An exlicit version designator is required in order to resolve a timestamp or change number");

			long qual = exact.getNumericQualifier();
			if (exact.isDefaultBranch()) {
				DirEntry entry = this.getTrunkModuleRoot();
				if (entry != null) {
					best = new VersionMatch(VersionFactory.defaultVersion(), query.getType() == VersionSelectorType.TIMESTAMP ? VersionSelectorFactory.timestamp(qual) : VersionSelectorFactory.changeNumber(qual));
				}
			} else {
				String branchName = exact.getBranchName();
				for (DirEntry entry : this.internalGetEntries(true, monitor)) {
					if (entry.path.equals(branchName)) {
						best = new VersionMatch(query.createVersion(exact), query.getType() == VersionSelectorType.TIMESTAMP ? VersionSelectorFactory.timestamp(qual) : VersionSelectorFactory.changeNumber(qual));
						break;
					}
				}
			}
			break;
		default:
		}
		return best;
	}

	private DirEntry[] internalGetEntries(boolean branches, IProgressMonitor monitor) throws CoreException {
		try {
			SVNURL url = this.getSVNRootUrl(branches);
			DirEntry[] entries = this.getSVNClientWrapper().list(url.toString(), Revision.HEAD, Revision.HEAD, false, false, new SVNNullProgressMonitor());
			if (entries != null)
				return entries;
		} catch (ClientWrapperException e) {
			// Assume that this is due to missing repository
		} catch (MalformedURLException e) {
			throw new BuckminsterException(e.getMessage(), e);
		}
		return new DirEntry[0];
	}

	private DirEntry getTrunkModuleRoot() throws CoreException {
		try {
			SVNURL url = this.getSVNUrl(null);

			int lastIndexOfSlash = url.toString().lastIndexOf("/");
			String parentURL = url.toString().substring(0, lastIndexOfSlash);
			DirEntry[] entries2 = this.getSVNClientWrapper().list(parentURL, Revision.HEAD, Revision.HEAD, false, false, new SVNNullProgressMonitor());
			String entryToFind = url.toString().substring(lastIndexOfSlash+1,url.toString().length());
			DirEntry result = null;
			for (DirEntry entry: entries2){
				if (entry.path.equals(entryToFind)){
					result = entry;
				}
			}			
			return result;
		} catch (ClientWrapperException e) {
			// Assume that this is due to missing repository
		} catch (MalformedURLException e) {
			throw new BuckminsterException(e.getMessage(),e);
		}
		return null;
	}

	public void close() {
		// TODO Auto-generated method stub

	}

}
