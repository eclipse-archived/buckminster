/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspecext.model.AlterArtifact;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterArtifactBuilder extends AlterAttributeBuilder {
	private final HashSet<IPath> removedPaths = new HashSet<IPath>();

	public AlterArtifactBuilder(AttributeBuilder baseBuilder) {
		super(baseBuilder);
	}

	public void addRemovedPath(IPath path) {
		removedPaths.add(path);
	}

	@Override
	public void clear() {
		super.clear();
		removedPaths.clear();
	}

	@Override
	public AlterAttribute<?> createAlterAttribute() {
		return new AlterArtifact((Artifact) createBase(), getRemovedHints(), getAlteredHints(), removedPaths);
	}

	public Set<IPath> getRemovedPaths() {
		return removedPaths;
	}
}
