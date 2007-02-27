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

import java.net.URL;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;

/**
 * @author kolwing
 *
 */
public interface ICertificateTrustInquiry
{
	/**
	 * Ask if the given certificate can be trusted.
	 * @param url the URL that presents the certificate
	 * @param certPath The path to the certificate
	 * @return <code>true</code> if the certificate can be trusted
	 * @throws CertificateException
	 */
	public boolean canTrust(URL url, CertPath certPath) throws CertificateException;
}
