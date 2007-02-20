/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;

/**
 * @author Thomas Hallgren
 */
public class AlterDependency
{
	private final ComponentRequest m_base;

	public AlterDependency(ComponentRequest base)
	{
		m_base = base;
	}

	public String getName()
	{
		return m_base.getName();
	}

	public void alterDependency(DependencyBuilder dep)
	{
		dep.setCategory(CSpecExtension.overrideCheckNull(m_base.getCategory(), dep.getCategory()));
		dep.setVersionDesignator(CSpecExtension.overrideCheckNull(m_base.getVersionDesignator(), dep.getVersionDesignator()));
	}
}
