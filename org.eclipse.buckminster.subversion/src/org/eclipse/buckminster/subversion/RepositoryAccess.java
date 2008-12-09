package org.eclipse.buckminster.subversion;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.buckminster.runtime.Trivial;

public final class RepositoryAccess
{
	private final URI m_svnURL;

	private final String m_user;

	private final String m_password;

	public RepositoryAccess(String str) throws URISyntaxException
	{
		int idx = str.indexOf('^');
		String user = null;
		String passwd = null;
		if(idx >= 0)
		{
			user = str.substring(idx + 1);
			str = str.substring(0, idx);
			idx = user.indexOf('@');
			if(idx >= 0)
			{
				passwd = user.substring(idx + 1);
				user = user.substring(0, idx);
			}
		}
		m_svnURL = new URI(str);
		m_user = user;
		m_password = passwd;
	}

	public RepositoryAccess(URI svnURL, String user, String password)
	{
		m_svnURL = svnURL;
		m_user = user;
		m_password = password;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(!(o instanceof RepositoryAccess))
			return false;
		RepositoryAccess that = (RepositoryAccess)o;
		return m_svnURL.equals(that.m_svnURL) && Trivial.equalsAllowNull(m_user, that.m_user)
				&& Trivial.equalsAllowNull(m_password, that.m_password);
	}

	public String getPassword()
	{
		return m_password;
	}

	public URI getSvnURL()
	{
		return m_svnURL;
	}

	public String getUser()
	{
		return m_user;
	}

	@Override
	public int hashCode()
	{
		int hash = m_svnURL.hashCode();
		if(m_user != null)
			hash = hash * 31 + m_user.hashCode();
		if(m_password != null)
			hash = hash * 31 + m_password.hashCode();
		return hash;
	}

	@Override
	public String toString()
	{
		if(m_user == null)
			return m_svnURL.toString();

		StringBuilder bld = new StringBuilder();
		bld.append(m_svnURL.toString());
		bld.append('^');
		bld.append(m_user);
		if(m_password != null)
		{
			bld.append('@');
			bld.append(m_password);
		}
		return bld.toString();
	}
}
