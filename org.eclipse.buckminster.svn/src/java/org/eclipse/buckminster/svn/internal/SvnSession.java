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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.tigris.subversion.subclipse.core.ISVNRepositoryLocation;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.subclipse.core.client.NotificationListener;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
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
			String authority = uri.getRawAuthority();
			if(authority != null)
			{
				bld.append("//");
				bld.append(authority);
			}
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
			for(ISVNRepositoryLocation location : svnPlugin.getRepositories().getKnownRepositories())
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
