/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven.indexer;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class IndexNotFoundException extends Exception
{
	private static final long serialVersionUID = -802899846175692819L;

	public IndexNotFoundException()
	{
	}

	/**
	 * @param message
	 */
	public IndexNotFoundException(String message)
	{
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IndexNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public IndexNotFoundException(Throwable cause)
	{
		super(cause);
	}

}
