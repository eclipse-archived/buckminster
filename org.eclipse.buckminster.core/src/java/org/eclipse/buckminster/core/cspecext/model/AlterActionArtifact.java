/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.IArtifact;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterActionArtifact extends AlterArtifact
{
	public AlterActionArtifact(ActionArtifact base, Set<String> removedHints, Map<String, String> alteredHints,
			Set<IPath> removedPaths)
	{
		super(base, removedHints, alteredHints, removedPaths);
	}

	@Override
	public void alterAttribute(TopLevelAttributeBuilder original) throws CoreException
	{
		IArtifact base = getBase();
		ActionArtifactBuilder aaBld = (ActionArtifactBuilder)original;
		alterPaths(aaBld);
		alterDocumentation(aaBld);
		aaBld.setType(CSpecExtension.overrideCheckNull(aaBld.getType(), base.getType()));
		aaBld.setBase(CSpecExtension.overrideCheckNull(aaBld.getBase(), base.getBase()));
	}
}
