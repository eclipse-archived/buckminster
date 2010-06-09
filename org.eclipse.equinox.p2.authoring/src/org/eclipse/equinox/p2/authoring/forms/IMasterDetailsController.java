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
public interface IMasterDetailsController
{
	/**
	 * User pressed "Add..."
	 */
	void add();

	/**
	 * User pressed "Remove"
	 */
	void remove();

	/*
	 * User pressed "Up"
	 */
	void up();

	/**
	 * User pressed "Down"
	 */
	void down();
}
