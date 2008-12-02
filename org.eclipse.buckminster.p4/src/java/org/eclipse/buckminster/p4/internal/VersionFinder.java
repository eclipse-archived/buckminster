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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractSCCSVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This branch locator assumes that all branches are represented as folders below the folder appointed by the component
 * reader.
 * 
 * @author Thomas Hallgren
 */
class VersionFinder extends AbstractSCCSVersionFinder
{
	private final DepotURI m_depotURI;

	private final Connection m_connection;

	VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
		Map<String, String> props = query.getProperties();
		m_depotURI = new DepotURI(DepotURI.createURI(provider.getURI(props)), null, props);
		m_connection = new Connection(m_depotURI);
	}

	@Override
	protected boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException
	{
		String[] branchNameBin = new String[1];
		FileSpec.Specifier specifier = P4RemoteReader.getSpecifier(versionMatch, m_connection, branchNameBin);
		Map<String, String> props = getQuery().getProperties();
		String uri = getProvider().getURI(props);
		DepotURI depotURI = new DepotURI(uri, branchNameBin[0], props);
		DepotFolder[] folders = m_connection.getFolders(depotURI.getDepotPath(), specifier);
		return folders.length > 0;
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException
	{
		return branches
				? getBranches(monitor)
				: getTags(monitor);
	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException
	{
		if(m_depotURI.hasBranchDesignator())
			return null;

		IPath componentPath = m_depotURI.getDepotPath();
		DepotFolder[] folders = m_connection.getFolders(componentPath, FileSpec.HEAD);
		if(folders.length == 0)
			return null;

		return new RevisionEntry(null, null, m_connection.getLastChangeNumber(componentPath, null));
	}

	private List<RevisionEntry> getBranches(IProgressMonitor monitor) throws CoreException
	{
		if(!m_depotURI.hasBranchDesignator())
			return null;

		IPath depotPath = m_depotURI.getDepotPath();
		DepotFolder[] folders = m_connection.getFolders(depotPath.append("*"), FileSpec.HEAD); //$NON-NLS-1$
		if(folders.length == 0)
			return Collections.emptyList();

		ArrayList<RevisionEntry> entries = new ArrayList<RevisionEntry>(folders.length);
		for(DepotFolder folder : folders)
		{
			IPath branchPath = folder.getDepotPath();
			String branchName = branchPath.lastSegment();
			entries.add(new RevisionEntry(branchName, null, m_connection.getLastChangeNumber(branchPath, null)));
		}
		return entries;
	}

	private List<RevisionEntry> getTags(IProgressMonitor monitor) throws CoreException
	{
		IPath depotPath = m_depotURI.getDepotPath();
		Label[] labels = m_connection.getLabels(depotPath.append("...")); //$NON-NLS-1$
		if(labels.length == 0)
			return Collections.emptyList();

		ArrayList<RevisionEntry> entries = new ArrayList<RevisionEntry>(labels.length);
		for(Label label : labels)
		{
			String labelName = label.getLabel();
			entries.add(new RevisionEntry(labelName, null, m_connection.getLastChangeNumber(depotPath, labelName)));
		}
		return entries;
	}
}
