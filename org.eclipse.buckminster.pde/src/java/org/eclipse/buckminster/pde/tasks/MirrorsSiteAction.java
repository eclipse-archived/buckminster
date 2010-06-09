/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.repository.IRepository;

/**
 * Action that generates referenced repositories for a p2 MDR
 */
@SuppressWarnings("restriction")
public class MirrorsSiteAction extends AbstractPublisherAction {
	private final String mirrors;

	public MirrorsSiteAction(String mirrors) {
		this.mirrors = mirrors;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		if (mirrors != null) {
			publisherInfo.getMetadataRepository().setProperty(IRepository.PROP_MIRRORS_URL, mirrors);
			// there does not really need to be an artifact repo but if there
			// is, setup its mirrors.
			if (publisherInfo.getArtifactRepository() != null)
				publisherInfo.getArtifactRepository().setProperty(IRepository.PROP_MIRRORS_URL, mirrors);
		}
		return Status.OK_STATUS;
	}
}
