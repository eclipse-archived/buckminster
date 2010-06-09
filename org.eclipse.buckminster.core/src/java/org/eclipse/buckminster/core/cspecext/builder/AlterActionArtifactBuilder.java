/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspecext.model.AlterActionArtifact;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;

/**
 * @author Thomas Hallgren
 */
public class AlterActionArtifactBuilder extends AlterArtifactBuilder {
	public AlterActionArtifactBuilder(ActionArtifactBuilder baseBuilder) {
		super(baseBuilder);
	}

	@Override
	public AlterAttribute<Artifact> createAlterAttribute() {
		return new AlterActionArtifact((ActionArtifact) createBase(), getRemovedHints(), getAlteredHints(), getRemovedPaths());
	}
}
