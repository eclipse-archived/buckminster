/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp.p2;

public class MissingPropertyException extends Exception
{
	private static final long serialVersionUID = 41992797592163523L;

	public MissingPropertyException(String propertyKey)
	{
		super("Missing required property '" + propertyKey + '\'');
	}
}
