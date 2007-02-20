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
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.client.Command;

@SuppressWarnings("restriction")
public class VersionFinder extends CVSSession implements IVersionFinder
{
	public static final Command.LocalOption HEADERS_ONLY = new CVSReaderType.MyLocalOption("-h");

	private RepositoryMetaData m_metaData;

	public VersionFinder(Provider provider, NodeQuery query) throws CoreException
	{
		super(provider.getURI(query.getProperties()));
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		return new VersionMatch(
				VersionFactory.defaultVersion(),
				VersionSelectorFactory.timestamp(this.getLastModificationDate(monitor).getTime()));
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		if(query.matches(VersionFactory.defaultVersion()))
			//
			// The query allows us to use the latest on the main branch. So that's what
			// we will be doing.
			//
			return this.getDefaultVersion(monitor);

		IVersionSelector exact = query.getExactMatch();
		RepositoryMetaData metaData = this.getMetaData(exact, monitor);
		Date time = metaData.getLastModification();
		long lastModTime = time == null ? System.currentTimeMillis() : time.getTime();

		List<VersionMatch> selectors = new ArrayList<VersionMatch>();
		switch(query.getType())
		{
		case LATEST:
			for(String branchName : metaData.getBranchNames())
			{
				IVersionSelector vs = VersionSelectorFactory.latest(branchName);
				if(query.matches(vs))
				{
					selectors.add(new VersionMatch(
							query.createVersion(vs),
							VersionSelectorFactory.timestamp(branchName, lastModTime)));
				}
			}
			break;
		case TAG:
			for(String tagName : metaData.getTagNames())
			{
				IVersionSelector vs = VersionSelectorFactory.tag(query.getBranch(), tagName);
				if(query.matches(vs))
				{
					// We trust that tags don't move so we don't use a timestamp here.
					//
					selectors.add(new VersionMatch(query.createVersion(vs), vs));
				}
			}
			break;
		case TIMESTAMP:
			IVersionSelector vs = query.getExactMatch();
			if(vs != null)
				selectors.add(new VersionMatch(query.createVersion(vs), vs));
			break;
		default:
			return null;
		}

		VersionMatch best = null;
		for(VersionMatch selector : selectors)
		{
			if(best == null || best.getVersion().compareTo(selector.getVersion()) < 0)
				best = selector;
		}
		return best;
	}

	private Date getLastModificationDate(IProgressMonitor monitor) throws CoreException
	{
		return this.getMetaData(null, monitor).getLastModification();
	}

	private RepositoryMetaData getMetaData(IVersionSelector exact, IProgressMonitor monitor) throws CoreException
	{
		CVSTag fixed = null;
		if(exact != null)
		{
			switch(exact.getType())
			{
			case TAG:
				fixed = new CVSTag(exact.toString(), CVSTag.VERSION);
				break;
			case LATEST:
				if(!exact.isDefaultBranch())
					fixed = new CVSTag(exact.getBranchName(), CVSTag.BRANCH);
			}
		}
		if(m_metaData == null)
			m_metaData = RepositoryMetaData.getMetaData(this, fixed, monitor);
		return m_metaData;
	}
}
