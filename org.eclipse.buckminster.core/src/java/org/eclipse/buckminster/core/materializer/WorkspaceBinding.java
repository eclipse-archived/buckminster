/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
class WorkspaceBinding
{
	private final IPath m_workspaceRelativePath;
	private final Materialization m_materialization;

	WorkspaceBinding(IPath workspaceRelativePath, Materialization materialization)
	{
		m_workspaceRelativePath = workspaceRelativePath;
		m_materialization = materialization;
	}

	Materialization getMaterialization()
	{
		return m_materialization;
	}

	IPath getWorkspaceRelativePath()
	{
		return m_workspaceRelativePath;
	}
}

