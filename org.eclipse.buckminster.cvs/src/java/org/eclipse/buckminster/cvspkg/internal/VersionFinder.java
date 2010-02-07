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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractSCCSVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.client.Command;

@SuppressWarnings("restriction")
public class VersionFinder extends AbstractSCCSVersionFinder {
	public static final Command.LocalOption HEADERS_ONLY = new CVSReaderType.MyLocalOption("-h"); //$NON-NLS-1$

	private RepositoryMetaData metaData;

	private final CVSSession session;

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException {
		super(provider, ctype, query);
		session = new CVSSession(provider.getURI(query.getProperties()));
	}

	@Override
	public void close() {
		session.close();
	}

	@Override
	protected boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException {
		// The component exists or we would not have been able to obtain its
		// meta-data
		//
		MonitorUtils.complete(monitor);
		return true;
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException {
		RepositoryMetaData repoMetaData = getMetaData(monitor, null);
		Date lastModTime = repoMetaData.getLastModification();
		if (lastModTime == null)
			lastModTime = new Date();

		// Limit the match results to a specific timestamp if that is supplied
		//
		Date timestamp = getQuery().getTimestamp();
		if (timestamp != null && timestamp.compareTo(lastModTime) < 0)
			lastModTime = timestamp;

		String[] names = branches ? repoMetaData.getBranchNames() : repoMetaData.getTagNames();
		if (names.length == 0)
			return Collections.emptyList();

		ArrayList<RevisionEntry> entries = new ArrayList<RevisionEntry>(names.length);
		for (String name : names)
			entries.add(new RevisionEntry(name, lastModTime, -1));
		return entries;
	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException {
		CVSTag fixedTag = null;
		Date timestamp = getQuery().getTimestamp();
		if (timestamp != null)
			fixedTag = new CVSTag(timestamp);

		if (timestamp == null) {
			RepositoryMetaData repoMetaData = getMetaData(monitor, fixedTag);
			timestamp = repoMetaData.getLastModification();
			if (timestamp == null)
				timestamp = new Date();
		} else
			MonitorUtils.complete(monitor);
		return new RevisionEntry(null, timestamp, -1);
	}

	private RepositoryMetaData getMetaData(IProgressMonitor monitor, CVSTag fixedTag) throws CoreException {
		if (metaData == null)
			metaData = RepositoryMetaData.getMetaData(session, fixedTag, monitor);
		else
			MonitorUtils.complete(monitor);
		return metaData;
	}
}
