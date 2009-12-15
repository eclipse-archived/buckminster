package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.EclipseGitProgressTransformer;
import org.eclipse.jgit.lib.Commit;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.GitIndex;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryConfig;
import org.eclipse.jgit.lib.Tree;
import org.eclipse.jgit.lib.WorkDirCheckout;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.URIish;

@SuppressWarnings("deprecation")
class RepositoryKeeper
{
	private String getGitBranch()
	{
		String branchName = null;
		if(versionMatch != null) {
			VersionSelector vs = versionMatch.getBranchOrTag();
			if(vs != null && vs.getType() == VersionSelector.BRANCH && !vs.isDefault())
				branchName = vs.getName();
		}
		if(branchName == null)
			return Constants.R_HEADS + Constants.MASTER;
		return Constants.R_HEADS + branchName;
	}

	private final boolean autoFetch;

	private final String component;

	private final File localRepo;

	private final String remoteName;

	private final URIish repoURI;

	private final VersionMatch versionMatch;

	private Repository repository;

	RepositoryKeeper(String fmt, VersionMatch match, Map<String, String> properties) throws CoreException
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
		versionMatch = match;
	}

	void checkout(File destination, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			String gitBranch = getGitBranch();
			Repository local = getRepository(monitor);
			Ref head = repository.getRef(gitBranch);
			if(head == null || head.getObjectId() == null)
			{
				// Branch is probably not cloned yet. So let's try and get it.
				//
				clone(monitor);
				head = repository.getRef(gitBranch);
				if(head == null)
					throw BuckminsterException.fromMessage("Unable to obtain reference to branch: %s", getGitBranch());
			}

			// TODO: Assert that destination and component match
			GitIndex index = new GitIndex(local);
			WorkDirCheckout co = new WorkDirCheckout(local, local.getWorkDir(), index, getComponentTree(monitor));
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

	String getComponent()
	{
		return component;
	}

	synchronized Repository getRepository(IProgressMonitor monitor) throws CoreException
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
				clone(monitor);
			}
			else
			{
				if(autoFetch)
					fetch(monitor);
			}
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return repository;
	}

	Tree getComponentTree(IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Repository repo = getRepository(monitor);
			for(Ref ref : repo.getAllRefs().values())
			{

				String name = ref.getName();
				int lastSlash = name.lastIndexOf('/');
				if(lastSlash < 0)
					continue;

				Object obj = repo.mapObject(ref.getObjectId(), name);
				if(!(obj instanceof Commit))
					continue;

				// Last part of name is the branch
				String branch = name.substring(lastSlash + 1);
				if(!Constants.MASTER.equals(branch))
					continue;

				Commit c = (Commit)obj;
				if(component == null)
					return c.getTree();

				return (Tree)c.getTree().findTreeMember(component);
			}
			return null;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}


	private void clone(IProgressMonitor monitor) throws CoreException, IOException
	{
		monitor.setTaskName("Initializing local repository");
		Transport tn = null;
		try
		{
			// Set the current branch
			//
			String gitBranch = getGitBranch();
			repository.writeSymref(Constants.HEAD, gitBranch);

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
			Ref head = result.getAdvertisedRef(gitBranch);
			if(head == null || head.getObjectId() == null)
				// This is bad. The desired branch was not found
				throw BuckminsterException.fromMessage("Unable to find branch %s in remote repository %s", gitBranch, repoURI);

			Commit mapCommit = repository.mapCommit(head.getObjectId());
			RefUpdate u = repository.updateRef(Constants.HEAD);
			u.setNewObjectId(mapCommit.getCommitId());
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

	private void fetch(IProgressMonitor monitor) throws CoreException, IOException
	{
		Transport tn = null;
		try
		{
			RemoteConfig remoteConfig = null;
			List<RemoteConfig> remoteConfigs;
			try
			{
				remoteConfigs = RemoteConfig.getAllRemoteConfigs(repository.getConfig());
			}
			catch(URISyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}
			for(RemoteConfig rc : remoteConfigs)
			{
				if(rc.getName().equals(remoteName))
				{
					remoteConfig = rc;
					break;
				}
			}
			if(remoteConfig == null)
				throw BuckminsterException.fromMessage("No remote configuration found for remote name %s", remoteName);
			tn = Transport.open(repository, remoteConfig);
			tn.fetch(new EclipseGitProgressTransformer(monitor), null);
		}
		finally
		{
			if(tn != null)
				tn.close();
			monitor.done();
		}
	}
}
