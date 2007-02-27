/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.util.Date;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This branch locator assumes that all branches are represented as folders
 * below the folder appointed by the component reader.
 * @author Thomas Hallgren
 */
class VersionFinder implements IVersionFinder
{
	private final DepotURI m_depotURI;
	private final Connection m_connection;

	VersionFinder(DepotURI depotURI)
	{
		m_depotURI = depotURI;
		m_connection = new Connection(m_depotURI);
	}

	public void close()
	{
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		IPath branchPath = m_depotURI.getDepotPath();
		String branch;
		if(m_depotURI.hasBranchDesignator())
		{
			branch = m_depotURI.getDefaultBranch();
			branchPath = branchPath.append(branch);
		}
		else
			branch = VersionSelectorFactory.DEFAULT_BRANCH;

		DepotFolder[] folders = m_connection.getFolders(branchPath);
		if(folders.length == 0)
			return null;

		return new VersionMatch(
			VersionFactory.defaultVersion(),
			VersionSelectorFactory.changeNumber(branch, m_connection.getLastChangeNumber(branchPath, null)));
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		if(query.matches(VersionFactory.defaultVersion()))
			//
			// The query allows us to use latest on default. So that's what
			// we will be doing.
			//
			return this.getDefaultVersion(monitor);

		// The DepotURI appoints the component root, i.e. it points to
		// the folder that in turn contains the branches.
		//
		IPath componentPath = m_depotURI.getDepotPath();
		DepotFolder[] folders;
		IVersionSelector exact;
		switch(query.getType())
		{
		case CHANGE_NUMBER:
		case TAG:
		case TIMESTAMP:
			String branch;
			if(m_depotURI.hasBranchDesignator())
			{
				branch = query.getBranch();
				componentPath = componentPath.append(branch);
			}
			else
				branch = VersionSelectorFactory.DEFAULT_BRANCH;

			folders = m_connection.getFolders(componentPath);
			if(folders.length == 0)
				return null;

			exact = query.getExactMatch();
			switch(query.getType())
			{
			case TAG:
				if(exact != null)
				{
					Label label = m_connection.getLabel(exact.getQualifier());
					if(label != null)
					{
						long changeNumber = m_connection.getLastChangeNumber(componentPath, label.getLabel());
						if(changeNumber >= 0)
						{
							return new VersionMatch(
								query.createVersion(exact),
								VersionSelectorFactory.changeNumber(branch, changeNumber));
						}
					}
					break;
				}

				VersionMatch best = null;
				for(Label label : m_connection.getLabels(componentPath.append("...")))
				{
					String labelName = label.getLabel();
					IVersionSelector selector = VersionSelectorFactory.tag(branch, labelName);
					if(query.matches(selector))
					{
						IVersion version = query.createVersion(selector);
						if(best == null || best.getVersion().compareTo(version) < 0)
						{
							long changeNumber = m_connection.getLastChangeNumber(componentPath, labelName);
							if(changeNumber < 0)
								continue;

							best =  new VersionMatch(version, VersionSelectorFactory.changeNumber(branch, changeNumber));
						}
					}
				}
				return best;

			case TIMESTAMP:
				if(exact != null)
				{
					long changeNumber = m_connection.getLastChangeNumber(
							componentPath, m_connection.formatDate(new Date(exact.getNumericQualifier())));

					if(changeNumber >= 0)
					{
						return new VersionMatch(
							query.createVersion(exact),
							VersionSelectorFactory.changeNumber(branch, changeNumber));
					}
					break;
				}
				throw new BuckminsterException("An exlicit version designator is required in order to resolve a timestamp");

			default:
				if(exact != null)
					return new VersionMatch(query.createVersion(exact), exact);
				throw new BuckminsterException("An exlicit version designator is required in order to resolve a change number");
			}
			break;

		case LATEST:
			if(!m_depotURI.hasBranchDesignator())
				return null;

			exact = query.getExactMatch();
			if(exact != null)
			{
				String branchName = exact.getBranchName();
				componentPath = componentPath.append(branchName);
				folders = m_connection.getFolders(componentPath);
				if(folders.length == 0)
					break;

				long changeNumber = m_connection.getLastChangeNumber(componentPath, null);
				if(changeNumber < 0)
					break;

				return new VersionMatch(query.createVersion(exact), VersionSelectorFactory.changeNumber(branchName, changeNumber));
			}

			VersionMatch best = null;
			folders = m_connection.getFolders(componentPath.append("*"));
			for(DepotFolder folder : folders)
			{
				IPath branchPath = folder.getDepotPath();
				String branchName = branchPath.lastSegment();
				IVersionSelector selector = VersionSelectorFactory.latest(branchName);
				if(query.matches(selector))
				{
					long changeNumber = m_connection.getLastChangeNumber(branchPath, null);
					if(changeNumber < 0)
						continue;

					IVersion version = query.createVersion(selector);
					if(best == null || best.getVersion().compareTo(version) < 0)
					{
						best = new VersionMatch(version, VersionSelectorFactory.changeNumber(branchName, changeNumber));
					}
				}
			}
			return best;
		default:
		}
		return null;
	}
}
