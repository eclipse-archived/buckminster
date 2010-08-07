/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.cspec.IActionArtifact;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class ActionArtifact extends Artifact implements IActionArtifact {
	private final String actionName;

	private final String alias;

	public ActionArtifact(ActionArtifactBuilder builder) {
		super(builder);
		actionName = builder.getActionName();
		alias = builder.getAlias();
	}

	public final Action getAction() throws MissingAttributeException {
		return (Action) this.getCSpec().getRequiredAttribute(actionName);
	}

	@Override
	public String getActionName() {
		return actionName;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public boolean isEnabled(IModelCache ctx) throws CoreException {
		return getAction().isEnabled(ctx);
	}

	@Override
	public boolean isProducedByActions(IModelCache ctx) {
		return true;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		if (alias != null)
			Utils.addAttribute(attrs, Prerequisite.ATTR_ALIAS, alias);
	}

	@Override
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder) {
		return cspecBuilder.createActionArtifactBuilder();
	}

	@Override
	protected IPath getExpandedBase(Map<String, String> local) throws CoreException {
		return getAction().getExpandedBase(getBase(), local);
	}
}
