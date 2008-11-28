package org.eclipse.buckminster.fetcher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.util.Base64;
import org.eclipse.osgi.util.NLS;

/**
 * Base class for {@link IResourceFetcher}. Provides authentication and fetch entry point for children classes. Also
 * creates destination directory if needed.
 * 
 * @author Guillaume CHATELET
 */
public abstract class AbstractResourceFetcher implements IResourceFetcher
{
	private final URL m_url;

	private final String m_destinationDir;

	private String m_login;

	private String m_password;

	public AbstractResourceFetcher(URL url, String dir)
	{
		m_url = url;
		m_destinationDir = dir;
	}

	public void fetch(IProgressMonitor monitor) throws IOException, CoreException
	{
		URLConnection conn = null;
		InputStream in = null;
		try
		{
			conn = m_url.openConnection();
			if(m_login != null)
			{
				final String userPassword = m_login + ':' + m_password;
				final String encoding = Base64.encode(userPassword.getBytes());
				conn.setRequestProperty("Authorization", "Basic " + encoding); //$NON-NLS-1$ //$NON-NLS-2$
			}
			in = conn.getInputStream();
			monitor.subTask(NLS.bind(Messages.preparing_destination_folder_0, m_destinationDir));
			prepareDirectories(m_destinationDir);
			monitor.subTask(NLS.bind(Messages.fetching_0, getUrl().toString()));
			consume(new BufferedInputStream(in), monitor);
		}
		finally
		{
			try
			{
				if(in != null)
				{
					in.close();
				}
			}
			catch(IOException ioe)
			{
			}
		}
	}

	public void setBasicAuthCredential(String login, String password)
	{
		if(login == null || password == null)
			throw new NullPointerException(Messages.login_and_password_must_be_not_null);
		m_login = login;
		m_password = password;
	}

	protected abstract void consume(InputStream stream, IProgressMonitor monitor) throws IOException, CoreException;

	final protected String getDestinationDir()
	{
		return m_destinationDir;
	}

	protected URL getUrl()
	{
		return m_url;
	}

	final private void prepareDirectories(String dir)
	{
		new File(dir).mkdirs();
	}
}
