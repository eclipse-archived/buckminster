/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestConflictException extends LocalizedException
{
	private static final long serialVersionUID = -3756682213929960365L;
	private final String[] args;

	public ComponentRequestConflictException(ComponentRequest rq1, ComponentRequest rq2)
	{
		super("Component request {0} is inconflict with request {1}");
		args = new String[] { rq1.toString(), rq2.toString() };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return args;
	}
}
