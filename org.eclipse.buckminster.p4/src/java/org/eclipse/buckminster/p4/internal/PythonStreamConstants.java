/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

/**
 * @author thhal
 */
public interface PythonStreamConstants
{
	static final String ENCODING = "UTF-8";	// TODO: I guess...
	static final char TYPE_NULL     = '0';
	static final char TYPE_NONE     = 'N';
	static final char TYPE_ELLIPSIS = '.';
	static final char TYPE_INT      = 'i';
	static final char TYPE_INT64    = 'I';
	static final char TYPE_FLOAT    = 'f';
	static final char TYPE_COMPLEX  = 'x';
	static final char TYPE_LONG     = 'l';
	static final char TYPE_STRING   = 's';
	static final char TYPE_TUPLE    = '(';
	static final char TYPE_LIST     = '[';
	static final char TYPE_DICT     = '{';
	static final char TYPE_CODE     = 'c';
	static final char TYPE_UNKNOWN  = '?';
}

