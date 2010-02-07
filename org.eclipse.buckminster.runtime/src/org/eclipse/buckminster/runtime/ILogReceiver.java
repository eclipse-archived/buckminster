/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.buckminster.runtime;

import java.io.OutputStream;

import org.eclipse.core.runtime.CoreException;

/**
 * @author ken1
 */
public interface ILogReceiver {
	OutputStream start(String title, String type, boolean activateOnWrite, boolean errorStream) throws CoreException;
}
