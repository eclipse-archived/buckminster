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
public class ActionArtifactBuilder extends ArtifactBuilder implements IActionArtifact {
	private String actionName;

	private String alias;

	ActionArtifactBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
	}

	@Override
	public void clear() {
		super.clear();
		actionName = null;
		alias = null;
	}

	@Override
	public Artifact createAttribute() {
		return new ActionArtifact(this);
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
	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder) {
		return specBuilder == getCSpecBuilder() ? this : new ActionArtifactBuilder(specBuilder);
	}

	@Override
	public void initFrom(IAttribute attribute) {
		IActionArtifact actionArtifact = (IActionArtifact) attribute;
		super.initFrom(actionArtifact);
		actionName = actionArtifact.getActionName();
		alias = actionArtifact.getAlias();
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
