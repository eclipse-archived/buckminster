/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.cspecgen.feature;

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.core.ifeature.IFeature;

@SuppressWarnings("restriction")
public class CSpecFromBinary extends CSpecFromFeature {
	private final Path location;

	/**
	 * @param cspecBuilder
	 * @param reader
	 * @param feature
	 * @deprecated use
	 *             {@link #CSpecFromBinary(CSpecBuilder, ICatalogReader, IFeature, Path)}
	 */
	@Deprecated
	protected CSpecFromBinary(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature) {
		this(cspecBuilder, reader, feature, null);
	}

	protected CSpecFromBinary(CSpecBuilder cspecBuilder, ICatalogReader reader, IFeature feature, Path location) {
		super(cspecBuilder, reader, feature);
		this.location = location;
	}

	@Override
	protected String getProductOutputFolder(String productId) {
		return null;
	}

	@Override
	void createFeatureJarAction(IProgressMonitor monitor) throws CoreException {
		if (location != null && location.toFile().isFile()) {
			// Feature jar file is available, we just reuse it.
			//
			ArtifactBuilder featureExport = getCSpec().addArtifact(ATTRIBUTE_FEATURE_JAR, true, new Path("..")); //$NON-NLS-1$
			featureExport.addPath(new Path(location.lastSegment()));
		} else {
			// Only an unpacked feature directory seems to be available, we have
			// to re-pack it.
			//
			ActionBuilder featureExport = addAntAction(ATTRIBUTE_FEATURE_JAR, TASK_RECREATE_JAR, true);
			featureExport.addProductPath(new Path(buildArtifactName(feature.getId(), feature.getVersion(), true)));
			featureExport.getPrerequisitesBuilder().addSelfRequirement();
			featureExport.setPrerequisitesAlias(ALIAS_REQUIREMENTS);

			featureExport.setProductBase(OUTPUT_DIR);
			featureExport.setProductAlias(ALIAS_OUTPUT);
		}
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
