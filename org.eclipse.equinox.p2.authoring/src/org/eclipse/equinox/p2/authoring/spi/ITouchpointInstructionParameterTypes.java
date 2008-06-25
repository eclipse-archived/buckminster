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

package org.eclipse.equinox.p2.authoring.spi;

/**
 * Describes the parameter type of a p2 Touchpoint Type's instruction.
 * @author Henrik Lindberg
 *
 */
public interface ITouchpointInstructionParameterTypes
{
	/** Parameter can be any string */
	public static final String TYPE_STRING = "string.type";
	
	/** Parameter must be translateable into a boolean */
	public static final String TYPE_BOOLEAN = "boolean.type";
	
	/** Parameter must be translateable into an integer value */
	public static final String TYPE_INT = "int.type";
	
	/** Parameter must be translateable into an integer value >= 0 */
	public static final String TYPE_MIN0_INT = "min0int.type";
	
	/** Parameter is a reference to an artifact listed in the IU */
	public static final String TYPE_ARTIFACTREF = "artifactref.type";
	
	/** Parameter is a reference to an artifact listed in the IU, or the special @artifact value */
	public static final String TYPE_IMPLIED_ARTIFACTREF = "implied.artifactref.type";
	
	/** Parameter is URI string */
	public static final String TYPE_URI = "uri.type";
	
	/** Parameter is a path string */
	public static final String TYPE_PATH = "path.type";

	/** Parameter is a file name (no path) */
	public static final String TYPE_FILENAME = "file.type";
	
	/** Parameter is a path string or the special string "@artifact" */
	public static final String TYPE_IMPLIED_PATH = "implied.path.type";

	/** Parameter is an OSGi version */
	public static final String TYPE_VERSION = "version.type";

	/** Parameter is an OSGi version or the special string "default" */
	public static final String TYPE_DEFAULT_VERSION = "default.version.type";
	
	/** Parameter is a period separated structured name */
	public static final String TYPE_NAME = "name.type";
		
}
