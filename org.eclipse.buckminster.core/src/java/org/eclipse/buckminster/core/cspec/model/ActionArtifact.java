/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class ActionArtifact extends Artifact
{
	private final String m_actionName;

	public ActionArtifact(String actionName, String name, boolean publ, Map<String,String> installerHints, Documentation documentation, String type, IPath base, Set<IPath> paths)
	{
		super(name, publ, installerHints, documentation, type, base, paths);
		m_actionName = actionName;
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
	public boolean isEnabled(IModelCache ctx)
	{
		try
		{
			return this.getAction().isEnabled(ctx);
		}
		catch(MissingAttributeException e)
		{
			return false;
		}
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx)
	{
		return true;
	}
}
