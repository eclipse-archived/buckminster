/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

/**
 * @author Thomas Hallgren
 */
public class ImmutablePropertyException extends RuntimeException
{
	private static final long serialVersionUID = 4071760256419163007L;

	ImmutablePropertyException(String propKey)
	{
		super(propKey);
	}
}

