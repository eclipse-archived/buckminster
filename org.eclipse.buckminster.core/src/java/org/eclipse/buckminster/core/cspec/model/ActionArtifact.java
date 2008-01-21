/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class ActionArtifact extends Artifact
{
	private final String m_actionName;

	public ActionArtifact(ActionArtifactBuilder builder)
	{
		super(builder);
		m_actionName = builder.getActionName();
	}

	public final Action getAction() throws MissingAttributeException
	{
		return (Action)this.getCSpec().getRequiredAttribute(m_actionName);
	}

	public String getActionName()
	{
		return m_actionName;
	}

	@Override
	public boolean isEnabled(IModelCache ctx) throws CoreException
	{
		return getAction().isEnabled(ctx);
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx)
	{
		return true;
	}

	@Override
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		return cspecBuilder.createActionArtifactBuilder();
	}

	@Override
	protected IPath getExpandedBase(Map<String, String> local) throws CoreException
	{
		return getAction().getExpandedBase(getBase(), local);
	}
}
