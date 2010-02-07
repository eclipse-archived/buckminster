package org.eclipse.buckminster.subversion;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.buckminster.runtime.Trivial;

public final class RepositoryAccess {
	private final URI svnURL;

	private final String user;

	private final String password;

	public RepositoryAccess(String str) throws URISyntaxException {
		int idx = str.indexOf('^');
		String _user = null;
		String _password = null;
		if (idx >= 0) {
			_user = str.substring(idx + 1);
			str = str.substring(0, idx);
			idx = _user.indexOf('@');
			if (idx >= 0) {
				_password = _user.substring(idx + 1);
				_user = _user.substring(0, idx);
			}
		}
		this.svnURL = new URI(str);
		this.user = _user;
		this.password = _password;
	}

	public RepositoryAccess(URI svnURL, String user, String password) {
		this.svnURL = svnURL;
		this.user = user;
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RepositoryAccess))
			return false;
		RepositoryAccess that = (RepositoryAccess) o;
		return svnURL.equals(that.svnURL) && Trivial.equalsAllowNull(user, that.user) && Trivial.equalsAllowNull(password, that.password);
	}

	public String getPassword() {
		return password;
	}

	public URI getSvnURL() {
		return svnURL;
	}

	public String getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		int hash = svnURL.hashCode();
		if (user != null)
			hash = hash * 31 + user.hashCode();
		if (password != null)
			hash = hash * 31 + password.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		if (user == null)
			return svnURL.toString();

		StringBuilder bld = new StringBuilder();
		bld.append(svnURL.toString());
		bld.append('^');
		bld.append(user);
		if (password != null) {
			bld.append('@');
			bld.append(password);
		}
		return bld.toString();
	}
}
