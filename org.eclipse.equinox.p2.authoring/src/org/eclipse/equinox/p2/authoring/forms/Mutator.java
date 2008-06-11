/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.forms;

/**
 * Handy mutator implementation with null/default implementation.
 * 
 * @author Henrik Lindberg
 * 
 */
public class Mutator implements IMutator
{

	/**
	 * Returns false
	 * 
	 * @see org.eclipse.equinox.p2.authoring.forms.IMutator#getBooleanValue()
	 */
	public boolean getBooleanValue()
	{
		return false;
	}

	/**
	 * Returns ""
	 * 
	 * @see org.eclipse.equinox.p2.authoring.forms.IMutator#getValue()
	 */
	public String getValue()
	{
		return ""; //$NON-NLS-1$
	}

	/**
	 * Does nothing
	 * 
	 * @see org.eclipse.equinox.p2.authoring.forms.IMutator#setValue(java.lang.String)
	 */
	public void setValue(String input) throws Exception
	{

	}

	/**
	 * Does nothing
	 * 
	 * @throws Exception
	 * @see org.eclipse.equinox.p2.authoring.forms.IMutator#setValue(boolean)
	 */
	public void setValue(boolean input) throws Exception
	{

	}

}
