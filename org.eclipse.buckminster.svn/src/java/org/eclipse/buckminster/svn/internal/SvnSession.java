/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.svn.internal;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.tigris.subversion.subclipse.core.ISVNRepositoryLocation;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.subclipse.core.client.NotificationListener;
import org.tigris.subversion.subclipse.core.repo.SVNRepositories;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.ISVNPromptUserPassword;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * The SVN repository reader assumes that any repository contains the three
 * recommended directories <code>trunk</code>, <code>tags</code>, and
 * <code>branches</code>. A missing <code>tags</code> directory is
 * interpreted as no <code>tags</code>. A missing <code>branches</code>
 * directory is interpreted as no branches. The URL used as the repository
 * identifier must contain the path element trunk. Anything that follows the
 * <code>trunk</code> element in the path will be considered a
 * <code>module</code> path. The repository URL may also contain a query part
 * that in turn may have four different flags:
 * <dl>
 * <dt>moduleBeforeTag</dt>
 * <dd>When resolving a tag, put the module name between the <code>tags</code>
 * directory and the actual tag</dd>
 * <dt>moduleAfterTag</dt>
 * <dd>When resolving a tag, append the module name after the actual tag</dd>
 * <dt>moduleBeforeBranch</dt>
 * <dd>When resolving a branch, put the module name between the
 * <code>branches</code> directory and the actual branch</dd>
 * <dt>moduleAfterBranch</dt>
 * <dd>When resolving a branch, append the module name after the actual branch</dd>
 * </dl>
 * A fragment in the repository URL will be treated as a sub-module. It will be
 * appended at the end of the resolved URL.
 * 
 * @author Thomas Hallgren
 */
public class SvnSession
{
	private ISVNClientAdapter m_clientAdapter;

	private final IPath m_module;

	private final IPath m_subModule;

	private final String m_urlLeadIn;

	private final boolean m_moduleBeforeTag;

	private final boolean m_moduleAfterTag;

	private final boolean m_moduleBeforeBranch;

	private final boolean m_moduleAfterBranch;

	private final String m_branch;

	private final String m_tag;

	private final String m_username;

	private final String m_password;

	private class UnattendedPromptUserPassword implements ISVNPromptUserPassword
	{
		private int m_promptUserLimit = 3;
		private int m_promptPasswordLimit = 3;

		public String askQuestion(String realm, String question, boolean showAnswer, boolean maySave)
		{
			// We do not support questions
			//
			return null;
		}

		public int askTrustSSLServer(String info, boolean allowPermanently)
		{
			return ISVNPromptUserPassword.AcceptTemporary;		
		}

		public boolean askYesNo(String realm, String question, boolean yesIsDefault)
		{
			return yesIsDefault;
		}

		public String getPassword()
		{
			return m_password;
		}

		public int getSSHPort()
		{
			// We do not support SSH
			//
			return -1;
		}

		public String getSSHPrivateKeyPassphrase()
		{
			// We do not support SSH
			//
			return null;
		}

		public String getSSHPrivateKeyPath()
		{
			// We do not support SSH
			//
			return null;
		}

		public String getSSLClientCertPassword()
		{
			// We do not support SSL
			//
			return null;
		}

		public String getSSLClientCertPath()
		{
			// We do not support SSL
			//
			return null;
		}

		public String getUsername()
		{
			return m_username;
		}

		public boolean prompt(String realm, String username, boolean maySave)
		{
			// We support the password prompt only if we actually know the password 
			// and only a limited number of times 
 			//
			return m_password != null && --m_promptPasswordLimit >= 0;
		}

		public boolean promptSSH(String realm, String username, int sshPort, boolean maySave)
		{
			// We do not support SSH prompt
			//
			return false;
		}

		public boolean promptSSL(String realm, boolean maySave)
		{
			// We do not support SSL prompt
			//
			return false;
		}

		public boolean promptUser(String realm, String username, boolean maySave)
		{
			// We do support the user prompt but only a limited number of times 
 			//
			return --m_promptUserLimit >= 0;
		}

		public boolean userAllowedSave()
		{
			// No need to save anything 
			//
			return false;
		}
	}

	/**
	 * Different versions of subclipse have different signatures for this method. We
	 * want to cover them all.
	 * @return
	 */
	private static Method s_getKnownRepositories;
	private static Object[] s_getKnownRepositoriesArgs;

	public static ISVNRepositoryLocation[] getKnownRepositories() throws CoreException
	{
		SVNProviderPlugin svnPlugin = SVNProviderPlugin.getPlugin();
		SVNRepositories repos = svnPlugin.getRepositories();
		Class<? extends SVNRepositories> reposClass = repos.getClass();

		try
		{
			Method getter;
			Object[] args;
			synchronized(reposClass)
			{
				if(s_getKnownRepositories == null)
				{
					try
					{
						// Newer versions use the IProgressMonitor parameter
						//
						s_getKnownRepositories = reposClass.getMethod("getKnownRepositories", new Class[] { IProgressMonitor.class });
						s_getKnownRepositoriesArgs = new Object[] { new NullProgressMonitor() };
					}
					catch(NoSuchMethodException e)
					{
						// Older versions have no parameter.
						s_getKnownRepositories = reposClass.getMethod("getKnownRepositories", Trivial.EMPTY_CLASS_ARRAY);
						s_getKnownRepositoriesArgs = Trivial.EMPTY_OBJECT_ARRAY;
					}
				}
				getter = s_getKnownRepositories;
				args = s_getKnownRepositoriesArgs;
			}
			return (ISVNRepositoryLocation[])getter.invoke(repos, args);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	/**
	 * @param repositoryURI The string representation of the URI that appoints the
	 * trunk of repository module. No branch or tag information must be included.
	 * @param branch The desired branch or <code>null</code> if not applicable.
	 * @param tag The desired tag or <code>null</code> if not applicable.
	 * @throws CoreException
	 */
	public SvnSession(String repositoryURI, String branch, String tag)
			throws CoreException
	{
		try
		{
			URI uri = new URI(repositoryURI);

			// Find the repository root, i.e. the point just above 'trunk'.
			//
			IPath fullPath = new Path(uri.getPath());
			String[] pathSegements = fullPath.segments();
			int idx = pathSegements.length;
			while(--idx >= 0)
				if(pathSegements[idx].equalsIgnoreCase("trunk"))
					break;

			if(idx < 0)
				throw new MalformedURLException("The SVN URL must contain the path segment 'trunk'");

			int relPathLen = pathSegements.length - idx;

			StringBuilder bld = new StringBuilder();
			String scheme = uri.getScheme();
			if(scheme != null)
			{
				bld.append(scheme);
				bld.append(':');
			}
			
			String username = null;
			String password = null;
			String authority = uri.getAuthority();
			if(authority != null)
			{
				int atIdx = authority.indexOf('@');
				if(atIdx > 0)
				{
					String authentication = authority.substring(0, atIdx);
					authority = authority.substring(atIdx + 1);

					int upSplit = authentication.indexOf(':');
					if(upSplit > 0)
					{
						username = authentication.substring(0, upSplit);
						password = authentication.substring(upSplit + 1);
					}
				}
				bld.append("//");
				bld.append(authority);
			}
			m_username = username;
			m_password = password;

			bld.append(fullPath.removeLastSegments(relPathLen));
			String urlLeadIn = bld.toString();

			// Anything after 'trunk' is considered a module path. The module
			// path
			// will be used when finding branches and tags.
			//
			IPath modulePath = null;
			if(relPathLen > 1)
				modulePath = fullPath.removeFirstSegments(idx + 1);
			m_module = modulePath;

			String tmp = uri.getFragment();
			IPath subModule = null;
			if(tmp != null)
				subModule = new Path(tmp).makeRelative();
			m_subModule = subModule;

			boolean moduleBeforeTag = false;
			boolean moduleAfterTag = false;
			boolean moduleBeforeBranch = false;
			boolean moduleAfterBranch = false;
			for(String entry : TextUtils.decodeToQueryPairs(uri.getQuery()))
			{
				if(entry.equalsIgnoreCase("moduleBeforeTag"))
					moduleBeforeTag = true;
				else if(entry.equalsIgnoreCase("moduleAfterTag"))
					moduleAfterTag = true;
				else if(entry.equalsIgnoreCase("moduleBeforeBranch"))
					moduleBeforeBranch = true;
				else if(entry.equalsIgnoreCase("moduleAfterBranch"))
					moduleAfterBranch = true;
			}
			m_moduleBeforeTag = moduleBeforeTag;
			m_moduleAfterTag = moduleAfterTag;
			m_moduleBeforeBranch = moduleBeforeBranch;
			m_moduleAfterBranch = moduleAfterBranch;
			m_branch = branch;
			m_tag = tag;

			// Let's see if our SVNRootUrl matches any of the known
			// repositories.
			//
			int rank = 0;
			SVNUrl ourRoot = new SVNUrl(urlLeadIn);
			SVNProviderPlugin svnPlugin = SVNProviderPlugin.getPlugin();
			ISVNRepositoryLocation bestMatch = null;
			for(ISVNRepositoryLocation location : getKnownRepositories())
			{
				SVNUrl repoRoot = location.getRepositoryRoot();
				if(!Trivial.equalsAllowNull(repoRoot.getHost(), ourRoot.getHost()))
					continue;

				String repoProto = repoRoot.getProtocol().toLowerCase();
				String ourProto = ourRoot.getProtocol().toLowerCase();

				// We let the protocol svn match a repo that uses svn+ssh
				//
				boolean repoIsSSH = repoProto.equals("svn+ssh");
				if(rank > 200 && !repoIsSSH)
					continue;

				if(!(repoProto.equals(ourProto) || (ourProto.equals("svn") && repoIsSSH)))
					continue;

				String[] ourPath = ourRoot.getPathSegments();
				String[] repoPath = repoRoot.getPathSegments();
				
				idx = repoPath.length;
				final int top = ourPath.length;
				if(idx > top)
					//
					// repoPath is too qualified for our needs
					//
					continue;
				
				while(--idx >= 0)
					if(!ourPath[idx].equals(repoPath[idx]))
						break;

				if(idx >= 0)
					//
					// repoPath is not a prefix of ourPath
					//
					continue;

				urlLeadIn = repoRoot.toString();
				int diff = top - repoPath.length;
				if(diff > 0)
				{
					int myRank = (repoIsSSH ? 400 : 200) - diff;
					if(rank > myRank)
						continue;

					// Append the rest of our path
					//
					bld.setLength(0);
					bld.append(urlLeadIn);
					for(idx = repoPath.length; idx < top; ++idx)
					{
						bld.append('/');
						bld.append(ourPath[idx]);
					}
					urlLeadIn = bld.toString();
				}
				rank = (repoIsSSH ? 400 : 200) - diff;
				bestMatch = location;
				if(rank == 400)
					break;
			}
			m_urlLeadIn = urlLeadIn;

			if(bestMatch == null)
				m_clientAdapter = svnPlugin.createSVNClient();
			else
			{
				m_clientAdapter = bestMatch.getSVNClient();
				m_clientAdapter.removeNotifyListener(NotificationListener.getInstance());
			}

			// Add the UnattendedPromptUserPassword callback only in case
			// the authentication data (at least the username) is actually
			// specified in the URL
			//
			if(m_username != null)
				m_clientAdapter.addPasswordCallback(new UnattendedPromptUserPassword());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public void close()
	{
	}

	public long getLastChangeNumber() throws CoreException
	{
		try
		{
			SVNUrl svnURL = getSVNUrl(null);
			ISVNDirEntry root = m_clientAdapter.getDirEntry(svnURL, SVNRevision.HEAD);
			if(root == null)
				throw new FileNotFoundException(svnURL.toString());
			return root.getLastChangedRevision().getNumber();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public Date getLastTimestamp() throws CoreException
	{
		try
		{
			SVNUrl svnURL = getSVNUrl(null);
			ISVNDirEntry root = m_clientAdapter.getDirEntry(svnURL, SVNRevision.HEAD);
			if(root == null)
				throw new FileNotFoundException(svnURL.toString());
			return root.getLastChangedDate();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public String toString()
	{
		try
		{
			return getSVNUrl(null).toString();
		}
		catch(MalformedURLException e)
		{
			return super.toString();
		}
	}

	ISVNClientAdapter getClientAdapter()
	{
		return m_clientAdapter;
	}

	/**
	 * Returns the directory where it's expected to find a list of branches or
	 * tags.
	 * 
	 * @param branches
	 *            true if branches, false if tags.
	 * @return The SVNUrl appointing the branches or tags directory.
	 * @throws MalformedURLException
	 */
	SVNUrl getSVNRootUrl(boolean branches) throws MalformedURLException
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_urlLeadIn);

		if(branches)
		{
			bld.append("/branches");
			if(m_moduleBeforeBranch && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}
		else
		{
			bld.append("/tags");
			if(m_moduleBeforeTag && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}
		return new SVNUrl(bld.toString());
	}

	SVNUrl getSVNUrl(String fileName) throws MalformedURLException
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_urlLeadIn);

		if(m_branch != null)
		{
			bld.append("/branches");
			if(m_moduleBeforeBranch && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
			bld.append('/');
			bld.append(m_branch);
			if(m_moduleAfterBranch && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}
		else if(m_tag != null)
		{
			bld.append("/tags");
			if(m_moduleBeforeTag && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
			bld.append('/');
			bld.append(m_tag);
			if(m_moduleAfterTag && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}
		else
		{
			bld.append("/trunk");
			if(m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}

		if(m_subModule != null)
		{
			bld.append('/');
			bld.append(m_subModule);
		}
		if(fileName != null)
		{
			bld.append('/');
			bld.append(fileName);
		}
		return new SVNUrl(bld.toString());
	}
}
