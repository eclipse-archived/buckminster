package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.util.Map;

import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egit.core.EclipseGitProgressTransformer;
import org.eclipse.jgit.lib.Commit;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.GitIndex;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryConfig;
import org.eclipse.jgit.lib.Tag;
import org.eclipse.jgit.lib.Tree;
import org.eclipse.jgit.lib.TreeEntry;
import org.eclipse.jgit.lib.WorkDirCheckout;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.URIish;

/**
 * An instance of RepositoryAccess manages one component in some branch or tag in a repository.
 */
@SuppressWarnings("deprecation")
class RepositoryAccess
{
	private static void appendObjectSummary(final StringBuilder sb, final String type, final PersonIdent author,
			final String message)
	{
		sb.append(type + " by ");
		sb.append(author.getName());
		sb.append("\n");
		sb.append(author.getWhen());
		sb.append("\n\n");
		final int newLine = message.indexOf('\n');
		final int last = (newLine != -1
				? newLine
				: message.length());
		sb.append(message.substring(0, last));
	}

	private final boolean autoFetch;

	private final String component;

	private final File localRepo;

	private final String remoteName;

	private Repository repository;

	private final URIish repoURI;

	RepositoryAccess(String fmt, Map<String, String> properties) throws CoreException
	{
		int comma = fmt.lastIndexOf(',');
		if(comma >= 0)
		{
			component = fmt.substring(comma + 1);
			fmt = fmt.substring(0, comma);
		}
		else
			component = null; // The repo _is_ the component

		localRepo = new File(fmt, ".git");
		if(!localRepo.isAbsolute())
			throw BuckminsterException.fromMessage("Git repository path \"%s\" is not absolute", fmt);

		String remoteURIStr = properties.get(IPropertyKeys.PROP_REMOTE_URI);
		if(remoteURIStr != null)
			try
			{
				repoURI = new URIish(remoteURIStr);
			}
			catch(URISyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}
		else
			repoURI = null;

		fmt = localRepo.getAbsolutePath();
		if(!localRepo.exists())
		{
			if(repoURI == null)
				throw BuckminsterException.fromMessage(
						"Git repository path \"%s\" does not exist and value is provided for the \"%s\" property", fmt,
						IPropertyKeys.PROP_REMOTE_URI);
		}
		autoFetch = Boolean.parseBoolean(properties.get(IPropertyKeys.PROP_AUTO_FETCH));

		String tmp = properties.get(IPropertyKeys.PROP_REMOTE_NAME);
		if(tmp == null)
			tmp = Constants.DEFAULT_REMOTE_NAME;
		remoteName = tmp;
	}

	void checkout(VersionMatch versionMatch, File destination, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Repository local = getRepository(monitor);
			GitIndex index = new GitIndex(local);
			WorkDirCheckout co = new WorkDirCheckout(local, local.getWorkDir(), index, getComponentTree(versionMatch, monitor));
			co.checkout();
			index.write();
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	synchronized void close()
	{
		if(repository != null)
		{
			repository.close();
			repository = null;
		}
	}

	Commit getCommit(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Object obj;
			Repository repo = getRepository(monitor);
			String gitTag = getGitTag(versionMatch);
			Ref ref;
			if(gitTag != null)
			{
				ref = repo.getRef(gitTag);
				if(ref == null)
					throw BuckminsterException.fromMessage("Unable to obtain Ref for tag %s", gitTag);
				obj = repo.mapObject(ref.getObjectId(), ref.getName());
				while(obj instanceof Tag)
					obj = repo.mapObject(((Tag)obj).getObjId(), null);
			}
			else
			{
				String gitBranch = getGitBranch(versionMatch);
				ref = repo.getRef(gitBranch);
				if(ref == null)
				{
					String remoteBranch = getGitRemoteBranch(versionMatch);
					ref = repo.getRef(remoteBranch);
					if(ref == null)
						throw BuckminsterException.fromMessage("Unable to obtain Ref for branch %s", gitBranch);

					// We need to clone the remote branch in order to get the Commit.
					fetch(versionMatch, monitor);
					ref = repository.getRef(gitBranch);
					if(ref == null)
						throw BuckminsterException.fromMessage("Unable to obtain cloned local Ref for branch %s",
								remoteBranch);
				}
				obj = repo.mapObject(ref.getObjectId(), ref.getName());
			}

			// inspectObj(obj);
			if(!(obj instanceof Commit))
				throw BuckminsterException.fromMessage("Unable to obtain Commit for ref %s", ref.getName());
			return (Commit)obj;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	String getComponent()
	{
		return component;
	}

	Tree getComponentTree(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Commit c = getCommit(versionMatch, monitor);
			if(component == null)
				return c.getTree();

			return (Tree)c.getTree().findTreeMember(component);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	Repository getRepository(IProgressMonitor monitor) throws CoreException
	{
		if(repository != null)
			return repository;

		boolean infant = !localRepo.exists();
		try
		{
			repository = new Repository(localRepo);
			if(infant)
			{
				repository.create();
				fetch(null, monitor); // Initial clone
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return repository;
	}

	void inspectRef(Ref ref) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		inspectRef(sb, ref);
		System.out.println(sb);
		System.out.println();
	}

	void inspectRef(StringBuilder sb, Ref ref) throws IOException
	{
		Object obj = repository.mapObject(ref.getObjectId(), ref.getName());
		sb.append(ref.getName());
		sb.append('\n');
		sb.append(ref.getObjectId().abbreviate(repository).name());
		sb.append(" - ");
		inspectObj(sb, obj);
	}

	void inspectObj(Object obj) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		inspectObj(sb, obj);
		System.out.println(sb);
		System.out.println();
	}

	void inspectObj(StringBuilder sb, Object obj) throws IOException
	{
		if(obj instanceof Commit)
		{
			final Commit c = ((Commit)obj);
			appendObjectSummary(sb, "commit", c.getAuthor(), c.getMessage());
			for(TreeEntry te : c.getTree().members())
			{
				sb.append("  ");
				sb.append(te.getFullName());
				sb.append("\n");
			}
		}
		else if(obj instanceof Tag)
		{
			final Tag t = ((Tag)obj);
			appendObjectSummary(sb, "tag", t.getAuthor(), t.getMessage());
		}
		else if(obj instanceof Tree)
		{
			sb.append("tree");
			for(TreeEntry te : ((Tree)obj).members())
			{
				sb.append("  ");
				sb.append(te.getFullName());
				sb.append("\n");
			}
		}
		else if(obj instanceof Blob)
		{
			sb.append("blob");
		}
		else
			sb.append("locally unknown object");
	}

	boolean isAutoFetch()
	{
		return autoFetch;
	}

	private void fetch(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException, IOException
	{
		if(repoURI == null)
			// Nothing to fetch. This is a no-op
			return;

		monitor.setTaskName("Initializing local repository");
		Transport tn = null;
		try
		{
			// Set the current branch
			//
			String gitBranch = getGitBranch(versionMatch);
			RefUpdate head = repository.updateRef(Constants.HEAD);
			head.disableRefLog();
			head.link(gitBranch);

			RepositoryConfig localConfig = repository.getConfig();
			RemoteConfig remoteConfig = new RemoteConfig(localConfig, remoteName);
			remoteConfig.addURI(repoURI);

			String dst = Constants.R_REMOTES + remoteConfig.getName();
			RefSpec wcrs = new RefSpec();
			wcrs = wcrs.setForceUpdate(true);
			wcrs = wcrs.setSourceDestination(Constants.R_HEADS + "*", dst + "/*");
			remoteConfig.addFetchRefSpec(wcrs);

			// we're setting up for a clone with a checkout
			localConfig.setBoolean("core", null, "bare", false);
			remoteConfig.update(localConfig);

			// branch is like 'Constants.R_HEADS + branchName', we need only
			// the 'branchName' part
			String branchName = gitBranch.substring(Constants.R_HEADS.length());

			// setup the default remote branch for branchName
			localConfig.setString(RepositoryConfig.BRANCH_SECTION, branchName, "remote", remoteName);
			localConfig.setString(RepositoryConfig.BRANCH_SECTION, branchName, "merge", gitBranch);
			localConfig.save();

			tn = Transport.open(repository, remoteConfig);
			FetchResult result = tn.fetch(new EclipseGitProgressTransformer(monitor), null);
			Ref advHead = result.getAdvertisedRef(gitBranch);
			if(advHead == null || advHead.getObjectId() == null)
				// This is bad. The desired branch was not found
				throw BuckminsterException.fromMessage("Unable to find branch %s in remote repository %s", gitBranch,
						repoURI);

			Commit c = repository.mapCommit(advHead.getObjectId());
			RefUpdate u = repository.updateRef(Constants.HEAD);
			u.setNewObjectId(c.getCommitId());
			u.forceUpdate();
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			if(tn != null)
				tn.close();
			monitor.done();
		}
	}

	private String getBranchName(VersionMatch versionMatch)
	{
		if(versionMatch != null)
		{
			VersionSelector vs = versionMatch.getBranchOrTag();
			if(vs != null && vs.getType() == VersionSelector.BRANCH && !vs.isDefault())
				return vs.getName();
		}
		return null;
	}

	private String getGitBranch(VersionMatch versionMatch)
	{
		String branchName = getBranchName(versionMatch);
		if(branchName == null)
			return Constants.R_HEADS + Constants.MASTER;
		return Constants.R_HEADS + branchName;
	}

	private String getGitRemoteBranch(VersionMatch versionMatch)
	{
		String branchName = getBranchName(versionMatch);
		String remoteBase = Constants.R_REMOTES + remoteName + '/';
		if(branchName == null)
			return remoteBase + Constants.MASTER;
		return remoteBase + branchName;
	}

	private String getGitTag(VersionMatch versionMatch)
	{
		if(versionMatch == null)
			return null;

		VersionSelector vs = versionMatch.getBranchOrTag();
		if(vs == null || vs.getType() != VersionSelector.TAG)
			return null;

		return Constants.R_TAGS + vs.getName();
	}
}
