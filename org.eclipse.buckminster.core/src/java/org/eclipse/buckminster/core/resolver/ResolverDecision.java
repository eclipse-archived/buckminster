/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.model.common.ComponentRequest;

/**
 * @author Thomas Hallgren
 * 
 */
public class ResolverDecision {
	private final ComponentRequest request;

	private final ResolverDecisionType type;

	private final Object[] args;

	public ResolverDecision(ComponentRequest request, ResolverDecisionType type, Object[] args) {
		this.request = request;
		this.type = type;
		this.args = args;
	}

	public ComponentRequest getRequest() {
		return request;
	}

	public ResolverDecisionType getType() {
		return type;
	}

	@Override
	public String toString() {
		return type.getMessage(args);
	}
}
