/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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
public class AlterArtifactBuilder extends AlterAttributeBuilder
{
	private final HashSet<IPath> m_removedPaths = new HashSet<IPath>();

	public AlterArtifactBuilder(AttributeBuilder baseBuilder)
	{
		super(baseBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_removedPaths.clear();
	}

	@Override
	public AlterAttribute<?> createAlterAttribute()
	{
		return new AlterArtifact((Artifact)createBase(), getRemovedHints(), getAlteredHints(), m_removedPaths);
	}

	public void addRemovedPath(IPath path)
	{
		m_removedPaths.add(path);
	}

	public Set<IPath> getRemovedPaths()
	{
		return m_removedPaths;
	}
}
