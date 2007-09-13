/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.subversive.internal;

import java.util.Date;

import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.AbstractReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.polarion.team.svn.core.client.Revision;

/**
 * @author thhal
 */
public class SubversiveReaderType extends AbstractReaderType {

	private SubversiveRemoteFileReader m_remoteFileReader = null;

	public IComponentReader getReader(ProviderMatch providerMatch,
			IProgressMonitor monitor) throws CoreException {
		m_remoteFileReader = new SubversiveRemoteFileReader(this, providerMatch);
		return m_remoteFileReader;

	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider,
			NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException {
		return new SubversiveVersionFinder(provider.getURI(nodeQuery
				.getProperties()));
	}

	@Override
	public void shareProject(IProject project, Resolution resolution,
			MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		m_remoteFileReader.map(project);
	}

	public static Revision getSVNRevision(IVersionSelector vs) {
		switch (vs.getType()) {
		case CHANGE_NUMBER:
			return new Revision.Number(Long.parseLong(vs.getQualifier()));
		case TIMESTAMP:
			return new Revision.DateSpec(new Date(vs.getNumericQualifier()));
		default:
			return Revision.HEAD;
		}
	}
}
