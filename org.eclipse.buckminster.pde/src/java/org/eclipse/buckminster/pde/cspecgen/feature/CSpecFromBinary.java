/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.cspecgen.feature;

import java.net.URI;

import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.core.ifeature.IFeature;

@SuppressWarnings("restriction")
public class CSpecFromBinary extends CSpecFromFeature {
	private final URI location;

	protected CSpecFromBinary(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature, URI uri) {
		super(cspecBuilder, reader, feature);
		location = uri;
	}

	@Override
	protected String getProductOutputFolder(String productId) {
		return null;
	}

	@Override
	void createFeatureJarAction(IProgressMonitor monitor) throws CoreException {
		IPath parentDir = new Path(".."); //$NON-NLS-1$
		ArtifactBuilder featureExport = getCSpec().addArtifact(ATTRIBUTE_FEATURE_JAR, true, null);
		featureExport.addPath(new Path(new Path(location.getPath()).lastSegment()));
		featureExport.setBase(parentDir);
		MonitorUtils.complete(monitor);
	}

	@Override
	void createFeatureSourceJarAction() throws CoreException {
		getCSpec().addGroup(ATTRIBUTE_SOURCE_FEATURE_JAR, true);
	}

	@Override
	void createSiteActions(IProgressMonitor monitor) throws CoreException {
	}
}
