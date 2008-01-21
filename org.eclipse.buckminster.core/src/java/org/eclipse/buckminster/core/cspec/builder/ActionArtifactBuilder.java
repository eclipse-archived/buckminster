/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.model.NamedElement;

/**
 * @author Thomas Hallgren
 */
public class ActionArtifactBuilder extends ArtifactBuilder
{
	private String m_actionName;

	@Override
	public void clear()
	{
		super.clear();
		m_actionName = null;
	}

	ActionArtifactBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public Artifact createAttribute()
	{
		return new ActionArtifact(this);
	}

	public String getActionName()
	{
		return m_actionName;
	}

	@Override
	public void initFrom(NamedElement namedElement)
	{
		ActionArtifact actionArtifact = (ActionArtifact)namedElement;
		super.initFrom(actionArtifact);
		m_actionName = actionArtifact.getActionName();
	}

	public void setActionName(String actionName)
	{
		m_actionName = actionName;
	}
}
