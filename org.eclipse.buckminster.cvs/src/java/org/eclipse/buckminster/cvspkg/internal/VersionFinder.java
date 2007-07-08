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
import org.eclipse.team.internal.ccvs.core.client.Command;

@SuppressWarnings("restriction")
public class VersionFinder extends AbstractSCCSVersionFinder
{
	public static final Command.LocalOption HEADERS_ONLY = new CVSReaderType.MyLocalOption("-h");

	private RepositoryMetaData m_metaData;
	private final CVSSession m_session;

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
		m_session = new CVSSession(provider.getURI(query.getProperties()));
	}

	@Override
	public void close()
	{
		m_session.close();
	}

	private RepositoryMetaData getMetaData(IProgressMonitor monitor) throws CoreException
	{
		if(m_metaData == null)
			m_metaData = RepositoryMetaData.getMetaData(m_session, null, monitor);
		else
			MonitorUtils.complete(monitor);
		return m_metaData;
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException
	{
		RepositoryMetaData metaData = getMetaData(monitor);
		Date lastModTime = metaData.getLastModification();
		if(lastModTime == null)
			lastModTime = new Date();

		// Limit the match results to a specific timestamp if that is supplied
		//
		Date timestamp = getQuery().getTimestamp();
		if(timestamp != null && timestamp.compareTo(lastModTime) < 0)
			lastModTime = timestamp;

		String[] names = branches ? metaData.getBranchNames() : metaData.getTagNames(); 
		if(names.length == 0)
			return Collections.emptyList();

		ArrayList<RevisionEntry> entries = new ArrayList<RevisionEntry>(names.length);
		for(String name : names)
			entries.add(new RevisionEntry(name, lastModTime, -1));
		return entries;
	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException
	{
		RepositoryMetaData metaData = getMetaData(monitor);
		Date lastModTime = metaData.getLastModification();
		if(lastModTime == null)
			lastModTime = new Date();
		return new RevisionEntry(null, lastModTime, -1);
	}

	@Override
	protected boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException
	{
		// The component exists or we would not have been able to obtain its meta-data
		//
		MonitorUtils.complete(monitor);
		return true;
	}
}
