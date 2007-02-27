/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal;

import java.net.URL;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;

import org.eclipse.buckminster.runtime.ICertificateTrustInquiry;
import org.eclipse.swt.widgets.Display;

/**
 * @author kolwing
 * 
 */
public class GUICertificateTrustInquiry implements ICertificateTrustInquiry
{
	public boolean canTrust(URL url, CertPath certPath) throws CertificateException
	{
		// if we can run in the current Display, do so, otherwise we have to
		// create a new
		//
		boolean disposeDisplay = false;
		Display display = Display.getCurrent();
		if(display == null)
		{
			display = new Display();
			disposeDisplay = true;
		}

		try
		{
			return CertificateTrustInquiryUIDialog.promptUser(display.getActiveShell(),
					"Certificate validation problem!",
					"Do you wish to trust the certificate presented by " + url + "?", certPath);
		}
		finally
		{
			if(disposeDisplay)
				display.dispose();
		}
	}
}
