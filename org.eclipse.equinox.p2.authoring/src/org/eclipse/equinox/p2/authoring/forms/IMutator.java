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
 * @author Henrik Lindberg
 * 
 */
public interface IMutator
{
	/**
	 * Should update a model object with the value in input, or throw an Exception describing why the value can not be
	 * set.
	 * 
	 * @param input
	 *            - the new input value to the model "property"
	 */
	void setValue(String input) throws Exception;

	/**
	 * Should return the model object value as a String (on a form that can be set again using {@link #setValue(String)}
	 * ).
	 * 
	 * @return the value of the model object property as a String.
	 */
	String getValue();

	/**
	 * Should update a model object with the value in input, or throw an Exception describing why the value can not be
	 * set.
	 * 
	 * @param input
	 *            - the new input value to the model "property"
	 * @throws Exception
	 */
	void setValue(boolean input) throws Exception;

	/**
	 * Should return the model object value as a boolean (on a form that can be set again using
	 * {@link #setValue(boolean)}).
	 * 
	 * @return the value of the model object property as a String.
	 */
	boolean getBooleanValue();
}
