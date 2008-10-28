/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.subclipse.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.tigris.subversion.clientadapter.Activator;
import org.tigris.subversion.subclipse.core.ISVNRepositoryLocation;
import org.tigris.subversion.subclipse.core.SVNClientManager;
import org.tigris.subversion.subclipse.core.SVNException;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.subclipse.core.repo.SVNRepositories;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.ISVNInfo;
import org.tigris.subversion.svnclientadapter.ISVNPromptUserPassword;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * <p>
 * The SVN repository will be able to use reader checks if a repository contains the three
 * recommended directories <code>trunk</code>, <code>tags</code>, and <code>branches</code>. A
 * missing <code>tags</code> directory is interpreted as no <code>tags</code>. A missing
 * <code>branches</code> directory is interpreted as no branches. In order to use <code>trunk</code>, <code>tags</code>, and <code>branches</code> repository identifier must contain the path
 * element <code>trunk</code>. Anything that follows the <code>trunk</code> element in the path will
 * be considered a <code>module</code> path. If no <code>trunk</code> element is present in the
 * path, the last element will be considered the <code>module</code>
 * </p>
 * <p>
 * The repository URL may also contain a query part that in turn may have four different flags:
 * <dl>
 * <dt>moduleBeforeTag</dt>
 * <dd>When resolving a tag, put the module name between the <code>tags</code> directory and the
 * actual tag</dd>
 * <dt>moduleAfterTag</dt>
 * <dd>When resolving a tag, append the module name after the actual tag</dd>
 * <dt>moduleBeforeBranch</dt>
 * <dd>When resolving a branch, put the module name between the <code>branches</code> directory and
 * the actual branch</dd>
 * <dt>moduleAfterBranch</dt>
 * <dd>When resolving a branch, append the module name after the actual branch</dd>
 * </dl>
 * </p>
 * A fragment in the repository URL will be treated as a sub-module. It will be appended at the end
 * of the resolved URL.
 * @author Thomas Hallgren
 */
public class SvnSession implements Closeable
{
	private static class RepositoryAccess
	{
		private final SVNUrl m_svnURL;

		private final String m_user;

		private final String m_password;

		public RepositoryAccess(String str) throws MalformedURLException
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
			m_svnURL = new SVNUrl(str);
			m_user = user;
			m_password = passwd;
		}

		public RepositoryAccess(SVNUrl svnURL, String user, String password)
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

		public SVNUrl getSvnURL()
		{
			return m_svnURL;
		}

		public String getUser()
		{
			return m_user;
		}

		public String getPassword()
		{
			return m_password;
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

	private class UnattendedPromptUserPassword implements ISVNPromptUserPassword
	{
		private int m_promptPasswordLimit = 3;

		private int m_promptUserLimit = 3;

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

	private static ISVNDirEntry[] s_emptyFolder = new ISVNDirEntry[0];

	/**
	 * Different versions of subclipse have different signatures for this method. We want to cover
	 * them all.
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
						s_getKnownRepositories = reposClass.getMethod("getKnownRepositories",
							new Class[] { IProgressMonitor.class });
						s_getKnownRepositoriesArgs = new Object[] { new NullProgressMonitor() };
					}
					catch(NoSuchMethodException e)
					{
						// Older versions have no parameter.
						s_getKnownRepositories = reposClass.getMethod("getKnownRepositories",
							Trivial.EMPTY_CLASS_ARRAY);
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

	private final VersionSelector m_branchOrTag;

	private final ISVNClientAdapter m_clientAdapter;

	private final IPath m_module;

	private final boolean m_moduleAfterBranch;

	private final boolean m_moduleAfterTag;

	private final boolean m_moduleBeforeBranch;

	private final boolean m_moduleBeforeTag;

	private final boolean m_trunkStructure;

	private final String m_password;

	private final SVNRevision m_revision;

	private final IPath m_subModule;

	private final String m_urlLeadIn;

	private final String m_username;

	public static SVNRevision getSVNRevision(long revision, Date timestamp)
	{
		if(revision == -1)
		{
			if(timestamp == null)
				return SVNRevision.HEAD;

			return new SVNRevision.DateSpec(timestamp);
		}
		if(timestamp != null)
			throw new IllegalArgumentException("SvnSession cannot use both timestamp and revision number");
		return new SVNRevision.Number(revision);
	}

	private static final UUID CACHE_KEY_DIR_CACHE = UUID.randomUUID();

	private static final UUID CACHE_KEY_LIST_CACHE = UUID.randomUUID();

	private static final String UNKNOWN_ROOT_PREFIX = SvnSession.class.getPackage().getName() + ".root.";

	private static void addUnknownRoot(Map<String, String> properties, RepositoryAccess ra)
	{
		synchronized(properties)
		{
			int maxNum = -1;
			String raStr = ra.toString();
			for(Map.Entry<String, String> entries : properties.entrySet())
			{
				String key = entries.getKey();
				if(key.startsWith(UNKNOWN_ROOT_PREFIX))
				{
					int lastDot = key.lastIndexOf('.');
					if(lastDot < 0)
						continue;

					try
					{
						int keyNum = Integer.parseInt(key.substring(lastDot + 1));
						if(maxNum < keyNum)
							maxNum = keyNum;
					}
					catch(NumberFormatException e)
					{
						continue;
					}
					if(entries.getValue().equals(raStr))
						//
						// Entry is already present. Don't recreate
						//
						return;
				}
			}
			properties.put(UNKNOWN_ROOT_PREFIX + (maxNum + 1), raStr);
		}
	}

	private static void clearUnknownRoots(Map<String, String> properties)
	{
		synchronized(properties)
		{
			Iterator<String> keys = properties.keySet().iterator();
			while(keys.hasNext())
			{
				String key = keys.next();
				if(key.startsWith(UNKNOWN_ROOT_PREFIX))
					keys.remove();
			}
		}
	}

	private static List<RepositoryAccess> getUnknownRoots(Map<String, String> properties)
	{
		synchronized(properties)
		{
			List<RepositoryAccess> unknownRoots = null;
			for(Map.Entry<String, String> entries : properties.entrySet())
			{
				String key = entries.getKey();
				if(key.startsWith(UNKNOWN_ROOT_PREFIX))
				{
					RepositoryAccess ra;
					try
					{
						ra = new RepositoryAccess(entries.getValue());
					}
					catch(MalformedURLException e)
					{
						// Bogus entry
						continue;
					}
					if(unknownRoots == null)
						unknownRoots = new ArrayList<RepositoryAccess>();
					unknownRoots.add(ra);
				}
			}
			if(unknownRoots == null)
				unknownRoots = Collections.emptyList();
			return unknownRoots;
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, ISVNDirEntry[]> getListCache(Map<UUID, Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			Map<String, ISVNDirEntry[]> listCache = (Map<String, ISVNDirEntry[]>)ctxUserCache.get(CACHE_KEY_LIST_CACHE);
			if(listCache == null)
			{
				listCache = Collections.synchronizedMap(new HashMap<String, ISVNDirEntry[]>());
				ctxUserCache.put(CACHE_KEY_LIST_CACHE, listCache);
			}
			return listCache;
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, ISVNDirEntry> getDirCache(Map<UUID, Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			Map<String, ISVNDirEntry> dirCache = (Map<String, ISVNDirEntry>)ctxUserCache.get(CACHE_KEY_DIR_CACHE);
			if(dirCache == null)
			{
				dirCache = Collections.synchronizedMap(new HashMap<String, ISVNDirEntry>());
				ctxUserCache.put(CACHE_KEY_DIR_CACHE, dirCache);
			}
			return dirCache;
		}
	}

	private final Map<String, ISVNDirEntry> m_dirCache;

	private final Map<String, ISVNDirEntry[]> m_listCache;

	/**
	 * @param repositoryURI The string representation of the URI that appoints the trunk of
	 *            repository module. No branch or tag information must be included.
	 * @param branch The desired branch or <code>null</code> if not applicable.
	 * @param tag The desired tag or <code>null</code> if not applicable.
	 * @param revision The desired revision or <code>-1</code> of not applicable
	 * @param timestamp The desired timestamp or <code>null</code> if not applicable
	 * @param context The context used for the resolution/materialization operation
	 * @throws CoreException
	 */
	public SvnSession(String repositoryURI, VersionSelector branchOrTag, long revision, Date timestamp,
		RMContext context) throws CoreException
	{
		m_revision = getSVNRevision(revision, timestamp);
		m_branchOrTag = branchOrTag;

		Map<UUID, Object> userCache = context.getUserCache();
		m_dirCache = getDirCache(userCache);
		m_listCache = getListCache(userCache);

		try
		{
			URI uri = new URI(repositoryURI);

			// Find the repository root, i.e. the point just above 'trunk'.
			//
			IPath fullPath = new Path(uri.getPath());
			String[] pathSegments = fullPath.segments();
			int idx = pathSegments.length;
			while(--idx >= 0)
				if(pathSegments[idx].equals("trunk"))
					break;

			if(idx >= 0)
				m_trunkStructure = true;
			else
			{
				m_trunkStructure = false;
				idx = pathSegments.length - 1; // Last element is considered the module name
			}

			if(m_branchOrTag != null && !m_trunkStructure)
				//
				// No use continuing with this session since there's no hope finding
				// the desired branch or tag.
				//
				throw BuckminsterException.fromMessage("Branch or tag %s not found", m_branchOrTag);

			int relPathLen = pathSegments.length - idx;

			StringBuilder bld = new StringBuilder();
			String scheme = uri.getScheme();
			if(scheme != null)
			{
				bld.append(scheme);
				bld.append("://");
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
						if("null".equals(username))
							username = null;
						password = authentication.substring(upSplit + 1);
						if("null".equals(password))
							password = null;
					}
				}
				bld.append(authority);
			}
			m_username = username;
			m_password = password;

			if(fullPath.getDevice() != null)
				bld.append('/');

			bld.append(fullPath.removeLastSegments(relPathLen));
			String urlLeadIn = bld.toString();

			// Anything after 'trunk' is considered a module path. The module
			// path
			// will be used when finding branches and tags.
			//
			IPath modulePath = null;
			if(m_trunkStructure)
			{
				if(relPathLen > 1)
				{
					modulePath = fullPath.removeFirstSegments(idx + 1);
					modulePath = modulePath.setDevice(null);
				}
			}
			else
				modulePath = Path.fromPortableString(fullPath.lastSegment());
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
			if(m_trunkStructure)
			{
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
			}
			m_moduleBeforeTag = moduleBeforeTag;
			m_moduleAfterTag = moduleAfterTag;
			m_moduleBeforeBranch = moduleBeforeBranch;
			m_moduleAfterBranch = moduleAfterBranch;

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

				// We let the protocol svn or http match a repo that uses svn+ssh or https
				//
				boolean repoIsSSH = repoProto.equals("svn+ssh") || repoProto.equals("https");
				if(rank > 200 && !repoIsSSH)
					continue;

				if(!(repoProto.equals(ourProto) || (repoProto.equals("svn") && ourProto.equals("http"))
					|| (repoProto.equals("http") && ourProto.equals("svn")) || ((ourProto.equals("svn") || ourProto.equals("http")) && repoIsSSH)))
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
			synchronized(svnPlugin)
			{
				SVNClientManager clientManager = svnPlugin.getSVNClientManager();
				ISVNClientAdapter client = Activator.getDefault().getClientAdapter(
					clientManager.getSvnClientInterface());

				if(client == null)
				{
					client = Activator.getDefault().getAnyClientAdapter();
					if(client == null)
						throw new SVNException("Unable to load default SVN Client");
				}

				// Add the UnattendedPromptUserPassword callback only in case
				// the authentication data (at least the username) is actually
				// specified in the URL
				//
				ISVNPromptUserPassword pwCb = (m_username == null) ? svnPlugin.getSvnPromptUserPassword()
					: new UnattendedPromptUserPassword();

				if(pwCb != null)
					client.addPasswordCallback(svnPlugin.getSvnPromptUserPassword());

				m_clientAdapter = client;
				if(bestMatch == null)
					addUnknownRoot(context.getBindingProperties(), new RepositoryAccess(ourRoot, m_username,
						m_password));
			}
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
		m_clientAdapter.dispose();
	}

	public long getLastChangeNumber() throws CoreException
	{
		try
		{
			SVNUrl svnURL = getSVNUrl(null);
			ISVNDirEntry root = m_clientAdapter.getDirEntry(svnURL, m_revision);
			if(root == null)
				throw new FileNotFoundException(svnURL.toString());
			return root.getLastChangedRevision().getNumber();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public long getLastChangeNumber(File workingCopy) throws CoreException
	{
		try
		{
			return m_clientAdapter.getInfoFromWorkingCopy(workingCopy).getLastChangedRevision().getNumber();
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
			ISVNDirEntry root = m_clientAdapter.getDirEntry(svnURL, m_revision);
			if(root == null)
				throw new FileNotFoundException(svnURL.toString());
			return root.getLastChangedDate();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public SVNRevision getRevision()
	{
		return m_revision;
	}

	public boolean hasTrunkStructure()
	{
		return m_trunkStructure;
	}

	public SVNRevision.Number getRepositoryRevision(IProgressMonitor monitor) throws CoreException
	{
		SVNRevision.Number repoRev = null;

		if(m_revision instanceof SVNRevision.Number)
		{
			repoRev = (SVNRevision.Number)m_revision;
			MonitorUtils.complete(monitor);
		}
		else
		{
			monitor.beginTask(null, 1);
			try
			{
				for(int retries = 0;; ++retries)
				{
					try
					{
						SVNUrl svnURL = getSVNUrl(null);
						ISVNInfo info = m_clientAdapter.getInfo(svnURL);
						if(info == null)
							return null;
						repoRev = info.getRevision();
						break;
					}
					catch(SVNClientException e)
					{
						if(++retries < 3)
						{
							try
							{
								Thread.sleep(2000);
							}
							catch(InterruptedException e1)
							{
							}
							continue;
						}
						throw BuckminsterException.wrap(e);
					}
				}
			}
			finally
			{
				monitor.done();
			}
		}
		return repoRev;
	}

	@Override
	public String toString()
	{
		try
		{
			return getSVNUrl(null).toString();
		}
		catch(CoreException e)
		{
			return super.toString();
		}
	}

	ISVNClientAdapter getClientAdapter()
	{
		return m_clientAdapter;
	}

	/**
	 * Returns the directory where it's expected to find a list of branches or tags.
	 * @param branches true if branches, false if tags.
	 * @return The SVNUrl appointing the branches or tags directory.
	 * @throws MalformedURLException
	 */
	SVNUrl getSVNRootUrl(boolean branches) throws CoreException
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
		try
		{
			return new SVNUrl(bld.toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	SVNUrl getSVNUrl(String fileName) throws CoreException
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_urlLeadIn);

		if(m_branchOrTag == null)
		{
			if(m_trunkStructure)
				bld.append("/trunk");

			if(m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
		}
		else if(m_branchOrTag.getType() == VersionSelector.BRANCH)
		{
			bld.append("/branches");
			if(m_moduleBeforeBranch && m_module != null)
			{
				bld.append('/');
				bld.append(m_module);
			}
			bld.append('/');
			bld.append(m_branchOrTag.getName());
			if(m_moduleAfterBranch && m_module != null)
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
			bld.append('/');
			bld.append(m_branchOrTag.getName());
			if(m_moduleAfterTag && m_module != null)
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
		try
		{
			return new SVNUrl(bld.toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	ISVNDirEntry getRootEntry(IProgressMonitor monitor) throws CoreException
	{
		// Synchronizing on an interned string should make it impossible for two
		// sessions to request the same entry from the remote server
		//
		SVNUrl url = getSVNUrl(null);
		SVNUrl parent = url.getParent();
		if(parent != null)
		{
			// List the parent instead of fetching the folder explicitly. This
			// will save us a lot of calls since the list is cached.
			//
			String lastEntry = url.getLastPathSegment();
			ISVNDirEntry[] dirEntries;
			try
			{
				dirEntries = listFolder(url.getParent(), monitor);
			}
			catch(CoreException e)
			{
				dirEntries = s_emptyFolder;
			}
			for(ISVNDirEntry dirEntry : dirEntries)
				if(dirEntry.getPath().equals(lastEntry))
					return dirEntry;
			
			// Parent was not accessible. Perhaps we have no permissions.
		}

		SVNRevision revision = getRevision();
		String key = cacheKey(url, revision).intern();
		synchronized(key)
		{
			// Check the cache. We use containsKey since it might have
			// valid null entries
			//
			if(m_dirCache.containsKey(key))
				return m_dirCache.get(key);

			Logger logger = CorePlugin.getLogger();
			monitor.beginTask(null, 1);
			try
			{
				logger.debug("Obtaining remote folder %s[%s]", url, revision);
				ISVNDirEntry entry = getClientAdapter().getDirEntry(url, revision);
				m_dirCache.put(key, entry);
				return entry;
			}
			catch(SVNClientException e)
			{
				String msg = e.getMessage();
				if(msg != null)
				{
					msg = msg.toLowerCase();
					if(msg != null)
					{
						msg = msg.toLowerCase();
						if(msg.contains("non-existent") || msg.contains("not found"))
						{
							logger.debug("Remote folder does not exist %s[%s]", url, revision);
							m_dirCache.put(key, null);
							return null;
						}
					}
				}
				throw BuckminsterException.wrap(e);
			}
		}
	}

	ISVNDirEntry[] listFolder(SVNUrl url, IProgressMonitor monitor) throws CoreException
	{
		// Synchronizing on an interned string should make it impossible for two
		// sessions to request the same entry from the remote server
		//
		String key = cacheKey(url, m_revision).intern();
		synchronized(key)
		{
			ISVNDirEntry[] list = m_listCache.get(key);
			if(list != null)
				return list;

			Logger logger = CorePlugin.getLogger();
			monitor.beginTask(null, 1);
			try
			{
				logger.debug("Listing remote folder %s", key);
				list = m_clientAdapter.getList(url, m_revision, false);
				monitor.worked(1);
				if(list == null || list.length == 0)
				{
					logger.debug("Remote folder had no entries %s", key);
					list = s_emptyFolder;
				}
				m_listCache.put(key, list);
				return list;
			}
			catch(SVNClientException e)
			{
				String msg = e.getMessage();
				if(msg != null)
				{
					msg = msg.toLowerCase();
					if(msg.contains("non-existent") || msg.contains("not found"))
					{
						logger.debug("Remote folder does not exist %s", key);
						return s_emptyFolder;
					}
				}
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				monitor.done();
			}
		}
	}

	/**
	 * Create a string in the form &quot;url[revision]&quot;
	 * @param url The url to append
	 * @param revision The revision to append
	 * @return A string representation denoting an explicit revision of the URL
	 */
	static String cacheKey(SVNUrl url, SVNRevision revision)
	{
		StringBuilder bld = new StringBuilder();

		String protocol = url.getProtocol();
		int port = url.getPort();
		bld.append(protocol);
		bld.append("://");
		if(url.getHost() != null)
		{
			bld.append(url.getHost());
			if(port != -1)
			{
				bld.append(":");
				bld.append(port);
			}
		}

		String[] segments = url.getPathSegments();
		for(int i = 0; i < segments.length; i++)
		{
			bld.append('/');
			bld.append(segments[i]);
		}
		bld.append('#');
		bld.append(revision);
		return bld.toString();
	}

	static void createCommonRoots(RMContext context) throws CoreException
	{
		List<RepositoryAccess> unknownRoots = SvnSession.getUnknownRoots(context.getBindingProperties());
		if(unknownRoots.size() == 0)
			return;

		Collection<RepositoryAccess> sourceRoots = unknownRoots;
		if(unknownRoots.size() > 1)
		{
			// Get all common roots with a segment count of at least 1
			//
			for(;;)
			{
				Collection<RepositoryAccess> commonRoots = getCommonRootsStep(sourceRoots);
				if(commonRoots == sourceRoots)
					break;

				// Common roots were found. Iterate again to find commons
				// amongst the commons
				//
				sourceRoots = commonRoots;
			}
		}

		// Create the needed repositories so that Subclipse doesn't create every single
		// root for us.
		//
		SVNProviderPlugin svnPlugin = SVNProviderPlugin.getPlugin();
		SVNRepositories repos = svnPlugin.getRepositories();
		for(RepositoryAccess root : sourceRoots)
		{
			Properties configuration = new Properties();
			configuration.setProperty("url", root.getSvnURL().toString());
			String user = root.getUser();
			if(user != null)
				configuration.setProperty("user", user);
			String password = root.getPassword();
			if(password != null)
				configuration.setProperty("password", password);

			try
			{
				repos.addOrUpdateRepository(repos.createRepository(configuration));
			}
			catch(SVNException e)
			{
				// Repository already exists
			}
		}
		clearUnknownRoots(context.getBindingProperties());
	}

	private static Collection<RepositoryAccess> getCommonRootsStep(Collection<RepositoryAccess> source)
	throws CoreException
	{
		Collection<RepositoryAccess> commonRoots = null;
		for(RepositoryAccess repoAccess : source)
		{
			SVNUrl url = repoAccess.getSvnURL();
			for(RepositoryAccess repoAccessCmp : source)
			{
				if(repoAccess == repoAccessCmp)
					continue;

				SVNUrl cmp = repoAccessCmp.getSvnURL();
				if(!(Trivial.equalsAllowNull(url.getHost(),cmp.getHost()) && Trivial.equalsAllowNull(url.getProtocol(),cmp.getProtocol()) 
						&& url.getPort() == cmp.getPort()))
					continue;

				String[] urlSegs = url.getPathSegments();
				String[] cmpSegs = cmp.getPathSegments();
				int maxSegs = urlSegs.length;
				if(maxSegs > cmpSegs.length)
					maxSegs = cmpSegs.length;

				int idx;
				for(idx = 0; idx < maxSegs; ++idx)
					if(!urlSegs[idx].equals(cmpSegs[idx]))
						break;

				if(idx < 1)
					continue;

				String user = repoAccess.getUser();
				String cmpUser = repoAccessCmp.getUser();
				if(user == null)
					user = cmpUser;
				else
				{
					if(!(cmpUser == null || user.equals(cmpUser)))
						continue;
				}

				String password = repoAccess.getPassword();
				String cmpPassword = repoAccessCmp.getPassword();
				if(password == null)
					password = cmpPassword;
				else
				{
					if(!(cmpPassword == null || password.equals(cmpPassword)))
						continue;
				}

				StringBuilder bld = new StringBuilder();
				bld.append(url.getProtocol());
				bld.append("://");
				if(url.getHost() != null)
				{
					bld.append(url.getHost());
					if(url.getPort() != -1)
					{
						bld.append(":");
						bld.append(url.getPort());
					}
				}
				for(int pdx = 0; pdx < idx; ++pdx)
				{
					String seg = urlSegs[pdx];
					bld.append('/');
					if(idx > 0 && seg.equals("trunk") || seg.equals("tags") || seg.equals("branches"))
						//
						// Assume that common root is above this folder
						//
						break;

					bld.append(seg);
				}
				try
				{
					if(commonRoots == null)
						commonRoots = new HashSet<RepositoryAccess>();
					commonRoots.add(new RepositoryAccess(new SVNUrl(bld.toString()), user, password));
				}
				catch(MalformedURLException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
		}

		if(commonRoots == null)
			//
			// No common roots found
			//
			return source;

		// Add all SVNUrl's for which we don't have a common root
		//
		Set<RepositoryAccess> rogueRoots = null;
		for(RepositoryAccess repoAccess : source)
		{
			boolean found = false;
			SVNUrl url = repoAccess.getSvnURL();
			for(RepositoryAccess repoAccessCmp : commonRoots)
			{
				SVNUrl cmp = repoAccessCmp.getSvnURL();
				if(!(Trivial.equalsAllowNull(url.getHost(),cmp.getHost()) && Trivial.equalsAllowNull(url.getProtocol(),cmp.getProtocol()) 
						&& url.getPort() == cmp.getPort()))
					continue;

				String[] urlSegs = url.getPathSegments();
				String[] cmpSegs = cmp.getPathSegments();
				int maxSegs = cmpSegs.length;
				if(maxSegs > urlSegs.length)
					continue;

				int idx;
				for(idx = 0; idx < maxSegs; ++idx)
					if(!urlSegs[idx].equals(cmpSegs[idx]))
						break;

				if(idx < maxSegs)
					continue;

				String user = repoAccess.getUser();
				String cmpUser = repoAccessCmp.getUser();
				if(!(user == null || cmpUser == null || user.equals(cmpUser)))
					continue;

				String password = repoAccess.getPassword();
				String cmpPassword = repoAccessCmp.getPassword();
				if(!(password == null || cmpPassword == null || password.equals(cmpPassword)))
					continue;

				found = true;
				break;
			}
			if(found)
				continue;

			if(rogueRoots == null)
				rogueRoots = new HashSet<RepositoryAccess>();
			rogueRoots.add(repoAccess);
		}
		if(rogueRoots != null)
			commonRoots.addAll(rogueRoots);
		return commonRoots;
	}
}
