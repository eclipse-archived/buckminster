/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.version;

import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractSCCSVersionFinder extends AbstractVersionFinder
{
	protected static class RevisionEntry
	{
		private final String m_entryName;
		private final long m_revision;
		private final Date m_timestamp;

		public RevisionEntry(String entryName, Date timestamp, long revision)
		{
			m_entryName = entryName;
			m_timestamp = timestamp;
			m_revision = revision;
		}

		public String getEntryName()
		{
			return m_entryName;
		}

		public long getRevision()
		{
			return m_revision;
		}

		public Date getTimestamp()
		{
			return m_timestamp;
		}
	}

	protected AbstractSCCSVersionFinder(Provider provider, IComponentType componentType, NodeQuery query)
	{
		super(provider, componentType, query);
	}

	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
	{
		try
		{
			NodeQuery query = getQuery();

			VersionSelector[] branchTagPath = query.getBranchTagPath();
			int idx = branchTagPath.length;

			boolean branches = false;
			boolean tags = false;
			boolean trunk = (idx == 0); // Use trunk if no branches or tags has been specified

			while(--idx >= 0)
			{
				VersionSelector branchOrTag = branchTagPath[idx];
				if(branchOrTag.getType() == VersionSelector.BRANCH)
					branches = true;
				else
					tags = true;
				if(branchOrTag.isDefault())
					trunk = true;
			}

			IVersionConverter versionConverter = getProvider().getVersionConverter();
			if(versionConverter != null)
			{
				// We are using a versionConverter. This rules out anything found
				// on the trunk.
				//
				trunk = false;

				// Exactly one of the tags or branches must be valid
				//
				if(versionConverter.getSelectorType() == VersionSelector.BRANCH)
				{
					if(tags == false)
						branches = true;
				}
				else
				{
					if(branches == false)
						tags = true;
				}

				// And perhaps this ruled out this finder all together?
				//
				if(!(branches || tags))
				{
					MonitorUtils.complete(monitor);
					return null;
				}
			}

			int ticks = 0;
			if(trunk)
				ticks += 10;
			if(branches)
				ticks += 10;
			if(tags)
				ticks += 10;

			monitor.beginTask(null, ticks);
			VersionMatch best = null;
			if(branches)
				best = getBestBranchOrTagMatch(true, MonitorUtils.subMonitor(monitor, 10));

			if(tags)
			{
				VersionMatch match = getBestBranchOrTagMatch(false, MonitorUtils.subMonitor(monitor, 10));
				if(best == null || query.compare(match, best) > 0)
					best = match;
			}

			if(trunk)
			{
				VersionMatch match = getBestTrunkMatch(MonitorUtils.subMonitor(monitor, 10));
				if(best == null || query.compare(match, best) > 0)
					best = match;
			}
			return best;
		}
		finally
		{
			monitor.done();
		}
	}

	protected VersionMatch getBestBranchOrTagMatch(boolean branches, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			List<RevisionEntry> entries = getBranchesOrTags(branches, MonitorUtils.subMonitor(monitor, 50));
			return getBestBranchOrTagMatch(branches, entries, MonitorUtils.subMonitor(monitor, 50));
		}
		finally
		{
			monitor.done();
		}
	}

	protected VersionMatch getBestBranchOrTagMatch(boolean branches, List<RevisionEntry> entries, IProgressMonitor monitor) throws CoreException
	{
		int top = entries.size();
		if(top == 0)
		{
			MonitorUtils.complete(monitor);
			return null;
		}

		IVersionConverter vConverter = getProvider().getVersionConverter();
		if(vConverter != null && vConverter.getSelectorType() != (branches ? VersionSelector.BRANCH : VersionSelector.TAG))
		{
			// Version converter not for the desired type so we will not find anything
			//
			MonitorUtils.complete(monitor);
			return null;
		}

		monitor.beginTask(null, top * 10);
		String space = getProvider().getSpace();
		NodeQuery query = getQuery();
		VersionSelector[] branchTagPath = query.getBranchTagPath();
		IVersionDesignator versionDesignator = query.getVersionDesignator();
		long revision = query.getRevision();
		Date timestamp = query.getTimestamp();
		VersionMatch best = null;
		for(RevisionEntry entry : entries)
		{
			// Rule out anything that is above a given revision
			//
			if(revision != -1 && entry.getRevision() > revision)
				continue;

			// Rule out anything that is later then a given time
			//
			if(timestamp != null)
			{
				Date entryTs = entry.getTimestamp();
				if(entryTs != null && entryTs.compareTo(timestamp) > 0)
					continue;
			}

			String name = entry.getEntryName();
			VersionSelector branchOrTag = branches ? VersionSelector.branch(name) : VersionSelector.tag(name);
			if(branchTagPath.length > 0 && VersionSelector.indexOf(branchTagPath, branchOrTag) < 0)
				//
				// This one will be discriminated anyway so there's no need to include it
				//
				continue;

			IVersion version;
			VersionMatch match = null;
			if(vConverter != null)
			{
				version = vConverter.createVersion(branchOrTag);
				if(version == null)
				{
					// Converter could not make sense of this tag. Skip it
					//
					MonitorUtils.worked(monitor, 10);
					continue;
				}

				// We need to assert that the component really exists on this branch
				// or with this tag.
				//
				match = new VersionMatch(version, branchOrTag, space, entry.getRevision(), entry.getTimestamp(), null);
				if(!checkComponentExistence(match, MonitorUtils.subMonitor(monitor, 10)))
					continue;
			}
			else
			{
				try
				{
					version = getVersionFromArtifacts(branchOrTag, MonitorUtils.subMonitor(monitor, 10));					
				}
				catch(CoreException e)
				{
					// Something is not right with this entry. Skip it.
					//
					continue;
				}
			}

			if(!(versionDesignator == null || versionDesignator.designates(version)))
				//
				// Discriminated by our designator
				//
				continue;

			if(match == null)
				match = new VersionMatch(version, branchOrTag, space, entry.getRevision(), entry.getTimestamp(), null);

			if(best == null || query.compare(match, best) > 0)
			{
				best = match;
				if(query.getResolutionPrio()[0] == AdvisorNode.PRIO_BRANCHTAG_PATH_INDEX)
					//
					// Branch/Tag path have the highest prio so there's no need to
					// check the next entry.
					//
					break;
			}
		}
		monitor.done();
		return best;
	}

	protected VersionMatch getBestTrunkMatch(IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			RevisionEntry entry = getTrunk(MonitorUtils.subMonitor(monitor, 50));
			if(entry == null)
				return null;

			IVersion version = getVersionFromArtifacts(null, MonitorUtils.subMonitor(monitor, 50));				
			return new VersionMatch(version, null, getProvider().getSpace(), entry.getRevision(), entry.getTimestamp(), null);
		}
		finally
		{
			monitor.done();
		}
	}

	protected abstract boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException;
	protected abstract List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException;
	protected abstract RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException;
}
