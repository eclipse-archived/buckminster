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

import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.subversion.GenericVersionFinder;
import org.eclipse.buckminster.subversion.ISubversionSession;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNRevision;

public class SubversiveVersionFinder extends GenericVersionFinder<SVNEntry, SVNRevision>
{

	public SubversiveVersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
	}

	@Override
	protected ISubversionSession<SVNEntry, SVNRevision> getSession(String repositoryURI, VersionSelector branchOrTag,
			long revision, Date timestamp, RMContext context) throws CoreException
	{
		return new SubversiveSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}
}
