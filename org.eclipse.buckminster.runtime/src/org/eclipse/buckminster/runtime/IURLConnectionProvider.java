/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author kolwing
 * 
 */
public interface IURLConnectionProvider
{

	public static final int MAX_CONNECTION_ATTEMPTS = 3;

	public static final int MILLISECS_BETWEEN_RETRIES = 3000;

	public static final String URL_CONNECTION_PROVIDERS_POINT = Buckminster.PLUGIN_ID + ".URLConnectionProviders"; //$NON-NLS-1$

	public URLConnection openConnection(URL url) throws IOException;
}
