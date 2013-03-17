/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.cspec.IComponentRequest;

/**
 * @author Thomas Hallgren
 * 
 */
public class ResolverDecision {
	private final IComponentRequest request;

	private final ResolverDecisionType type;

	private final Object[] args;

	public ResolverDecision(IComponentRequest request, ResolverDecisionType type, Object[] args) {
		this.request = request;
		this.type = type;
		this.args = args;
	}

	public IComponentRequest getRequest() {
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
