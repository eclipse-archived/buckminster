/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

/**
 * The up to date policy tells Buckminster how to go about determining the timestamp of a product when deciding if it is
 * up to date in respect to its prerequisites.
 * 
 * @author Thomas Hallgren
 */
public enum UpToDatePolicy
{
	/**
	 * Never trust a product defined as a folder since the expected number of files is unknown
	 */
	DEFAULT,

	/**
	 * Let the associated actor decide
	 */
	ACTOR,

	/**
	 * Trust timestamp only when the product contains the number of files denoted in attribute 'count'
	 */
	COUNT,

	/**
	 * Trust timestamp of a product that matches the action prerequisites in number and optional pattern. The 'count'
	 * attribute may be used to denote file additions
	 */
	MAPPER,

	/**
	 * Trust timestamp of any product that contains at least one file
	 */
	NOT_EMPTY
}
