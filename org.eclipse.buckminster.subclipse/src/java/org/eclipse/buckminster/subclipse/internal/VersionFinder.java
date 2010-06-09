/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.subclipse.internal;

import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.subversion.GenericVersionFinder;
import org.eclipse.buckminster.subversion.ISubversionSession;
import org.eclipse.core.runtime.CoreException;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.SVNRevision;

public class VersionFinder extends GenericVersionFinder<ISVNDirEntry, SVNRevision> {

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException {
		super(provider, ctype, query);
	}

	@Override
	protected ISubversionSession<ISVNDirEntry, SVNRevision> getSession(String repositoryURI, VersionSelector branchOrTag, long revision,
			Date timestamp, RMContext context) throws CoreException {
		return new SvnSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}

}
