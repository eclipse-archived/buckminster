/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 * 
 */
public class TransformerMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -3941245237432861824L;

	private final String[] m_arguments;

	/**
	 * @param defaultMessageFormat
	 */
	public TransformerMismatchException(BidirectionalTransformer invalid)
	{
		super("The substitution {0} -> {1} is not reversed by {2} -> {3}");
		m_arguments = new String[] {
			invalid.getFromPattern().toString(),
			invalid.getFromReplacement(),
			invalid.getToPattern().toString(),
			invalid.getToReplacement()
			};
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_arguments;
	}
}
