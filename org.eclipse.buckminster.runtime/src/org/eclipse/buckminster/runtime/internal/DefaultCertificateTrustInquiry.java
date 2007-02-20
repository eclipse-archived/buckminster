/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.runtime.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.ICertificateTrustInquiry;

/**
 * @author kolwing
 * 
 */
public class DefaultCertificateTrustInquiry implements ICertificateTrustInquiry
{
	public boolean canTrust(URL url, CertPath certPath) throws CertificateException
	{
		try
		{
			PrintStream out = System.out;
			out.println("Certificate validation problem!");
			out.print("URL: ");
			out.println(url);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Boolean accept = null;
			do
			{
				out.println("(enter 'yes' or 'no'; 'cert'/'path' shows details (primary or full path)");
				out.print("Do you wish to trust this certificate (no)? ");
				out.flush();
				String inp = br.readLine().trim();
				if (inp.length() == 0 || inp.equalsIgnoreCase("no"))
					accept = Boolean.FALSE;
				else if (inp.equalsIgnoreCase("yes"))
					accept = Boolean.TRUE;
				else if (inp.equalsIgnoreCase("cert"))
					out.println(certPath.getCertificates().get(0));
				else if (inp.equalsIgnoreCase("path"))
					out.println(certPath);
			} while(accept == null);

			return accept.booleanValue();
		}
		catch(IOException ioe)
		{
			Buckminster.getLogger().warning("Input exception", ioe);
			return false;
		}
	}}
