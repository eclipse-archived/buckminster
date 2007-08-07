/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import org.eclipse.buckminster.runtime.BuckminsterException;

/**
 * @author Karel Brezina
 *
 */
public class MaterializationUtils
{

	/**
	 * Checks HTTP response code and throws JNLPException if there is a problem
	 * 
	 * @param connection
	 * @throws JNLPException
	 * @throws IOException
	 */
	public static void checkConnection(URLConnection connection, String originalURL) throws JNLPException, IOException
	{
		if(connection instanceof HttpURLConnection)
		{
			HttpURLConnection httpConnection = (HttpURLConnection)connection;
			
			int responseCode = httpConnection.getResponseCode();
			
			if(responseCode != HttpURLConnection.HTTP_OK)
			{
				String errorCode;
				
				switch(responseCode)
				{
				case HttpURLConnection.HTTP_FORBIDDEN:
					
					errorCode = ERROR_CODE_403_EXCEPTION;
					break;

				case HttpURLConnection.HTTP_NOT_FOUND:
					
					errorCode = ERROR_CODE_404_EXCEPTION;
					break;

				case HttpURLConnection.	HTTP_INTERNAL_ERROR:
					
					errorCode = ERROR_CODE_500_EXCEPTION;
					break;

				default:
					errorCode = ERROR_CODE_REMOTE_IO_EXCEPTION;
					break;
				}
				
				throw new JNLPException("Cannot read materialization specification",
						errorCode,
						new BuckminsterException(originalURL + " - " + httpConnection.getResponseMessage()));

			}
		}
	}
}
