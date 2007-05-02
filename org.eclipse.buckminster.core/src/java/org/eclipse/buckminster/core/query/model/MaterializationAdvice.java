/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.query.model;

import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class MaterializationAdvice
{
	private final String m_projectName;
	private final IPath m_destination;
	private final ConflictResolution m_notEmptyAction;

	public MaterializationAdvice(final String projectName, final IPath destination, final ConflictResolution notEmptyAction)
	{
		m_projectName = projectName;
		m_destination = destination;
		m_notEmptyAction = notEmptyAction;
	}

	public IPath getDestination()
	{
		return m_destination;
	}

	public ConflictResolution getNotEmptyAction()
	{
		return m_notEmptyAction;
	}

	public String getProjectName()
	{
		return m_projectName;
	}
}