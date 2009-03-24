/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IActionArtifact;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Artifact;

/**
 * @author Thomas Hallgren
 */
public class ActionArtifactBuilder extends ArtifactBuilder implements IActionArtifact
{
	private String m_actionName;

	private String m_alias;

	ActionArtifactBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_actionName = null;
		m_alias = null;
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

	public String getAlias()
	{
		return m_alias;
	}

	@Override
	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder)
	{
		return specBuilder == getCSpecBuilder()
				? this
				: new ActionArtifactBuilder(specBuilder);
	}

	@Override
	public void initFrom(IAttribute attribute)
	{
		IActionArtifact actionArtifact = (IActionArtifact)attribute;
		super.initFrom(actionArtifact);
		m_actionName = actionArtifact.getActionName();
		m_alias = actionArtifact.getAlias();
	}

	public void setActionName(String actionName)
	{
		m_actionName = actionName;
	}

	public void setAlias(String alias)
	{
		m_alias = alias;
	}
}
