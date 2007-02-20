/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class AlterDependencyBuilder
{
	private final DependencyBuilder m_baseBuilder;

	public AlterDependencyBuilder(DependencyBuilder baseBuilder)
	{
		m_baseBuilder = baseBuilder;
	}

	public void clear()
	{
		m_baseBuilder.clear();
	}

	public String getName()
	{
		return m_baseBuilder.getName();
	}

	public AlterDependency createAlterDependency() throws CoreException
	{
		return new AlterDependency(m_baseBuilder.createDependency());
	}
}
