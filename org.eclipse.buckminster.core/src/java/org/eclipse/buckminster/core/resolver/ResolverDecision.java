/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;


/**
 * @author Thomas Hallgren
 *
 */
public class ResolverDecision
{
	private final ComponentRequest m_request;
	private final ResolverDecisionType m_type;
	private final Object[] m_args;

	public ResolverDecision(ComponentRequest request, ResolverDecisionType type, Object[] args)
	{
		m_request = request;
		m_type = type;
		m_args = args;
	}

	public ComponentRequest getRequest()
	{
		return m_request;
	}

	@Override
	public String toString()
	{
		return m_type.getMessage(m_args);
	}
}
