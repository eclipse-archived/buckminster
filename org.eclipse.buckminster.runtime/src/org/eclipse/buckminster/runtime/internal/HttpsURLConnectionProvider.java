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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.ICertificateTrustInquiry;
import org.eclipse.buckminster.runtime.IURLConnectionProvider;
import org.eclipse.buckminster.runtime.URLUtils;

/**
 * @author kolwing
 */
public class HttpsURLConnectionProvider implements IURLConnectionProvider
{
	public static final String INTERNAL_HTTPS_URL_CONNECTION_PROVIDER_UI_POINT = Buckminster.PLUGIN_ID
		+ ".internalHttpsURLConnectionProviderUI";

	private static final CertificateFactory X509_CF;

	private static final CertPathValidator PKIX_CPV;

	private static final File CACERTS_FILE;

	private static final Set<CertPath> s_knownCertPaths = Collections.synchronizedSet(new HashSet<CertPath>());

	static
	{
		try
		{
			X509_CF = CertificateFactory.getInstance("X.509");

			PKIX_CPV = CertPathValidator.getInstance("PKIX");

			StringBuffer sb = new StringBuffer(System.getProperty("java.home"));
			sb.append(File.separatorChar).append("lib");
			sb.append(File.separatorChar).append("security");
			sb.append(File.separatorChar).append("cacerts");
			CACERTS_FILE = new File(sb.toString());
		}
		catch(Throwable t)
		{
			throw new ExceptionInInitializerError(t);
		}
	}

	public URLConnection openConnection(final URL url) throws IOException
	{
		try
		{
			// get an initialized keystore instance
			//
			final KeyStore ks = this.getKeyStore();

			// Create a custom trust manager
			// TODO: allow a user to slot in other variants (e.g. a 'trust
			// anything')
			//
			TrustManager[] promptUserTrustManager = new TrustManager[] { new X509TrustManager()
			{
				public java.security.cert.X509Certificate[] getAcceptedIssuers()
				{
					throw new RuntimeException("Unexpected call to X509TrustManager.getAcceptedIssuers()");
				}

				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws CertificateException
				{
					throw new RuntimeException("Unexpected call to X509TrustManager.checkClientTrusted()");
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws CertificateException
				{
					// error checking per the docs...
					//
					if(certs == null || certs.length == 0 || authType == null || authType.length() == 0)
						throw new IllegalArgumentException();

					CertPath certPath = X509_CF.generateCertPath(Arrays.asList(certs));
					try
					{
						// create parameters
						// set it to not check any cert revocation list
						//
						PKIXParameters pkixParams = new PKIXParameters(ks);
						pkixParams.setRevocationEnabled(false);

						// validate the certs we have been called with
						//
						PKIX_CPV.validate(certPath, pkixParams);
					}
					catch(CertPathValidatorException cpve)
					{
						if(!HttpsURLConnectionProvider.this.promptUser(url, certPath))
							throw new CertificateException("Not accepted by user");
					}
					catch(KeyStoreException e)
					{
						throw new CertificateException(e.getMessage());
					}
					catch(InvalidAlgorithmParameterException e)
					{
						throw new CertificateException(e.getMessage());
					}
				}
			} };

			// Install the trust manager in the connection and it will activate
			// when getInputstream is called
			// 
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, promptUserTrustManager, null);
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());

			return conn;
		}
		catch(Throwable t)
		{
			IOException ioe = null;
			if(t instanceof IOException)
				ioe = (IOException)t;
			else
			{
				ioe = new IOException(t.getMessage());
				ioe.initCause(t);
			}
			throw ioe;
		}
	}

	private KeyStore getKeyStore() throws Exception
	{
		// get (an uninitialized) keystore instance of the default type
		//
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

		// initialize it; with a stream if possible, without it otherwise
		//
		InputStream ksStream = null;
		try
		{
			File ksFile = this.getKeyStoreFile();
			if(ksFile != null)
				ksStream = new FileInputStream(ksFile);
			ks.load(ksStream, null);
		}
		finally
		{
			if(ksStream != null)
				ksStream.close();
		}

		return ks;
	}

	private File getKeyStoreFile()
	{
		// use the override if it has been configured...
		// (blindly return it - if it blows, the user should face the
		// consequences)
		//
		String ksNameFromProp = System.getProperty("javax.net.ssl.trustStore");
		if(ksNameFromProp != null)
			return new File(ksNameFromProp);

		// ...else try the supposed default (it might not exist though, in the
		// JDK for example)
		//
		return CACERTS_FILE.isFile() ? CACERTS_FILE : null;
	}

	private boolean promptUser(URL url, CertPath certPath) throws CertificateException
	{
		if(s_knownCertPaths.contains(certPath))
			return true;

		ICertificateTrustInquiry ui = URLUtils.getTrustInquiry();
		if(ui == null)
			throw new CertificateException("Missing implementation to inquire if certificate can be trusted");

		boolean accepted = ui.canTrust(url, certPath);
		if(accepted)
		{
			if(s_knownCertPaths.size() > 1000)
			{
				// avoid memleak...
				//
				Buckminster.getLogger().warning("Excessive count of known cert paths - clearing");
				s_knownCertPaths.clear();
			}
			s_knownCertPaths.add(certPath);
		}
		return accepted;
	}
}
