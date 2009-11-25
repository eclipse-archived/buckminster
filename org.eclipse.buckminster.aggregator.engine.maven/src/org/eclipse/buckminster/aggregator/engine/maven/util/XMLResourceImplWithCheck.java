package org.eclipse.buckminster.aggregator.engine.maven.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.buckminster.aggregator.engine.maven.MavenManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class XMLResourceImplWithCheck extends XMLResourceImpl
{
	private static class URIHandlerWithCheck extends URIHandlerImpl
	{
		// If maven metadata exceeds this size, something is wrong...
		// The downloader will throw an error is such cases
		private static final long MAX_FILE_LENGTH = 10000000;

		public static void register(URIConverter owner)
		{
			new URIHandlerWithCheck(owner);
		}

		private URIConverter m_owner;

		private HttpClient m_httpClient;

		private URIHandlerWithCheck(URIConverter owner)
		{
			m_owner = owner;
			m_owner.getURIHandlers().add(0, this);
			m_httpClient = new HttpClient();
		}

		@Override
		public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
		{
			try
			{
				String uriString = uri.toString();
				String uriStringMD5 = uriString + ".md5";
				String uriStringSHA1 = uriString + ".sha1";

				String md5 = getDigest(uriStringMD5, MavenManager.MESSAGE_DIGESTERS[0].getDigestLength() << 1);
				String sha1 = getDigest(uriStringSHA1, MavenManager.MESSAGE_DIGESTERS[1].getDigestLength() << 1);

				byte[] content = download(uriString);
				String[] checkSums = MavenManager.createCheckSum(content, MavenManager.MESSAGE_DIGESTERS);
				if(!md5.equals(checkSums[0]))
					throw new Exception("Invalid MD5 for " + uriString + ": found " + md5 + ", expected "
							+ checkSums[0]);
				if(!sha1.equals(checkSums[1]))
					throw new Exception("Invalid SHA1 for " + uriString + ": found " + sha1 + ", expected "
							+ checkSums[1]);

				return new ByteArrayInputStream(content);
			}
			catch(IOException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				IOException ioe = new IOException(e.getMessage());
				ioe.initCause(e);
				throw ioe;
			}
		}

		@Override
		public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
		{
			for(org.eclipse.emf.ecore.resource.URIHandler handler : m_owner.getURIHandlers())
			{
				if(handler == this)
					continue;
				if(handler.canHandle(uri))
					return handler.createOutputStream(uri, options);
			}

			return super.createOutputStream(uri, options);
		}

		// Consider using ECF or even RepositoryTransport layer to support all the related features (such as
		// authorization)
		// BUT: ECF does not seem to honor KeepAlive support (why?) and RepositoryTransport seems to be very slow on
		// many short transfers
		private byte[] download(String uriStr) throws Exception
		{
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			InputStream is;
			java.net.URI uri = new java.net.URI(uriStr);
			String scheme = uri.getScheme();
			if("http".equals(scheme) || "https".equals(scheme))
			{
				HttpMethod method = new GetMethod(uriStr);
				m_httpClient.executeMethod(method);
				is = method.getResponseBodyAsStream();
			}
			else
				is = uri.toURL().openStream();
			int read;
			long total = 0;
			while((read = is.read(buffer)) != -1)
			{
				if((total += read) > MAX_FILE_LENGTH)
					throw new IOException("Remote file (" + uriStr + ") exceeds maximum expected size of "
							+ MAX_FILE_LENGTH + " bytes");
				os.write(buffer, 0, read);
			}
			return os.toByteArray();
		}

		private String getDigest(String uri, int len) throws Exception
		{
			String line = new String(download(uri));

			if(line.length() < len)
				throw new Exception("Invalid digest: " + line);

			return line.substring(0, len);
		}
	}

	private static URIConverter s_uriConverter = new ExtensibleURIConverterImpl();

	static
	{
		URIHandlerWithCheck.register(s_uriConverter);
	}

	public XMLResourceImplWithCheck(URI uri)
	{
		super(uri);
		setEncoding("UTF-8");
	}

	@Override
	public URIConverter getURIConverter()
	{
		return s_uriConverter;
	}
}
