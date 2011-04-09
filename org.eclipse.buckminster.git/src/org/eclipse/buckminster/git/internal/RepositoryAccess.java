package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.EclipseGitProgressTransformer;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.IndexDiff;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.osgi.util.NLS;

/**
 * An instance of RepositoryAccess manages one component in some branch or tag
 * in a repository.
 */
class RepositoryAccess {
	private static final Object NULL_STRING = "null"; //$NON-NLS-1$

	private static String getBranchName(VersionMatch versionMatch) {
		if (versionMatch != null) {
			VersionSelector vs = versionMatch.getBranchOrTag();
			if (vs != null && vs.getType() == VersionSelector.BRANCH && !vs.isDefault())
				return vs.getName();
		}
		return null;
	}

	private static String getGitBranch(VersionMatch versionMatch, boolean nullIfMaster) {
		String branchName = getBranchName(versionMatch);
		if (branchName == null) {
			if (nullIfMaster)
				return null;
			branchName = Constants.MASTER;
		}
		return Constants.R_HEADS + branchName;
	}

	private static String getGitTag(VersionMatch versionMatch) {
		if (versionMatch == null)
			return null;

		VersionSelector vs = versionMatch.getBranchOrTag();
		if (vs == null || vs.getType() != VersionSelector.TAG)
			return null;

		return Constants.R_TAGS + vs.getName();
	}

	private final boolean autoFetch;

	private final String component;

	private final File localRepo;

	private final String remoteName;

	private Repository repository;

	private final URIish repoURI;

	RepositoryAccess(String fmt, Map<String, String> properties) throws CoreException {
		int comma = fmt.lastIndexOf(',');
		if (comma >= 0) {
			component = fmt.substring(comma + 1);
			fmt = fmt.substring(0, comma);
		} else
			component = null; // The repo _is_ the component

		localRepo = new File(fmt, ".git"); //$NON-NLS-1$
		if (!localRepo.isAbsolute())
			throw BuckminsterException.fromMessage("Git repository path \"%s\" is not absolute", fmt); //$NON-NLS-1$

		String remoteURIStr = properties.get(IPropertyKeys.PROP_REMOTE_URI);
		if (remoteURIStr != null)
			try {
				if (remoteURIStr.startsWith("file:/") && remoteURIStr.length() > 6 && remoteURIStr.charAt(6) != '/') //$NON-NLS-1$
					remoteURIStr = "file:///" + remoteURIStr.substring(6); //$NON-NLS-1$
				URIish uri = new URIish(remoteURIStr);
				String username = uri.getUser();
				if (username != null) {
					if (NULL_STRING.equals(username))
						uri = uri.setUser(null);

					String password = uri.getPass();
					if (password != null && NULL_STRING.equals(password))
						uri = uri.setPass(null);
				}
				repoURI = uri;
			} catch (URISyntaxException e) {
				throw BuckminsterException.wrap(e);
			}
		else
			repoURI = null;

		fmt = localRepo.getAbsolutePath();
		if (!localRepo.exists()) {
			if (repoURI == null)
				throw BuckminsterException.fromMessage("Git repository path \"%s\" does not exist and value is provided for the \"%s\" property", //$NON-NLS-1$
						fmt, IPropertyKeys.PROP_REMOTE_URI);
		}
		autoFetch = Boolean.parseBoolean(properties.get(IPropertyKeys.PROP_AUTO_FETCH));

		String tmp = properties.get(IPropertyKeys.PROP_REMOTE_NAME);
		if (tmp == null)
			tmp = Constants.DEFAULT_REMOTE_NAME;
		remoteName = tmp;
	}

	void checkout(Repository repo, String refStr) throws CoreException {
		try {
			synchronized (localRepo.getAbsolutePath().intern()) {
				Git git = new Git(repo);
				CheckoutCommand co = git.checkout();
				co.setName(refStr);
				co.call();
			}
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	synchronized void close() {
		if (repository != null) {
			repository.close();
			repository = null;
		}
	}

	ObjectId getBranchOrTagId(VersionMatch versionMatch, boolean nullIfMaster, IProgressMonitor monitor) throws CoreException {
		try {
			ObjectId objId = null;
			Repository repo = getRepository(versionMatch, monitor);
			String revstr = getGitTag(versionMatch);
			if (revstr != null) {
				objId = repo.resolve(revstr);
				if (objId == null)
					throw BuckminsterException.fromMessage("Unable to obtain ObjectID for tag %s", revstr); //$NON-NLS-1$
			} else {
				revstr = getGitBranch(versionMatch, nullIfMaster);
				if (revstr == null)
					return null;

				objId = repo.resolve(revstr);
				if (objId == null) {
					String remoteBranch = getGitRemoteBranch(versionMatch);
					objId = repo.resolve(remoteBranch);
					if (objId == null)
						throw BuckminsterException.fromMessage("Unable to obtain ObjectID for branch %s", revstr); //$NON-NLS-1$

					// We need to clone the remote branch in order to get the
					// Commit.
					fetch(versionMatch, monitor);
					objId = repo.resolve(revstr);
					if (objId == null)
						throw BuckminsterException.fromMessage("Unable to obtain cloned local Ref for branch %s", remoteBranch); //$NON-NLS-1$
				}
			}
			return objId;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	String getComponent() {
		return component;
	}

	File getLocation(VersionMatch versionMatch) throws CoreException {
		File location = getRepository(versionMatch, new NullProgressMonitor()).getWorkTree();
		if (component != null)
			location = new File(location, component);
		return location;
	}

	Repository getRepository(VersionMatch vm, IProgressMonitor monitor) throws CoreException {
		synchronized (localRepo.getAbsolutePath().intern()) {
			String refStr = getGitTag(vm);
			if (refStr == null)
				refStr = getGitBranch(vm, false);

			try {
				if (repository == null) {
					boolean infant = !localRepo.exists();
					repository = new FileRepository(localRepo);

					if (infant) {
						repository.create();
						fetch(vm, monitor); // Initial clone
						checkout(repository, refStr);
						return repository;
					}
				}

				ObjectId wantedId = parseCommit(repository.getRef(refStr));
				ObjectId currentId = parseCommit(repository.getRef(Constants.HEAD));
				if (wantedId.equals(currentId))
					return repository;

				IndexDiff diff = new IndexDiff(repository, refStr, new FileTreeIterator(repository));
				if (!diff.diff())
					return repository;

				if (component != null) {
					// We don't signal a conflict unless we find one beneath our
					// own component
					if (!(scanFiles(diff.getMissing()) || scanFiles(diff.getModified()) || scanFiles(diff.getUntracked())))
						return repository;
				}

				throw BuckminsterException.fromMessage(NLS.bind(Messages.git_reader_0_cannot_switch_to_1_without_causing_conflict_beneath_2,
						new Object[] { localRepo.getAbsolutePath(), refStr, component }));
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			}
		}
	}

	TreeWalk getTreeWalk(Repository repo, ObjectId id, String path, IProgressMonitor monitor) throws CoreException {
		ObjectReader curs = repo.newObjectReader();
		try {
			CanonicalTreeParser p = new CanonicalTreeParser();
			RevWalk revWalk = new RevWalk(curs);
			try {
				p.reset(curs, revWalk.parseTree(id));
			} finally {
				revWalk.release();
			}

			TreeWalk treeWalk = new TreeWalk(curs);
			treeWalk.setRecursive(true);
			treeWalk.addTree(p);
			if (component == null) {
				if (path == null) {
					return treeWalk;
				}
			} else {
				path = component + '/' + path;
			}
			treeWalk.setFilter(PathFilter.create(path));
			return treeWalk;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			curs.release();
		}
	}

	TreeWalk getTreeWalk(VersionMatch versionMatch, String path, IProgressMonitor monitor) throws CoreException {
		ObjectId id = getBranchOrTagId(versionMatch, false, monitor);
		Repository repo = getRepository(versionMatch, monitor);
		return getTreeWalk(repo, id, path, monitor);
	}

	boolean isAutoFetch() {
		return autoFetch;
	}

	boolean scanFiles(Set<String> files) {
		if (files == null || files.isEmpty())
			return false;
		for (String file : files)
			if (file.startsWith(component))
				return true;
		return false;
	}

	private void fetch(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException, IOException {
		if (repoURI == null)
			// Nothing to fetch. This is a no-op
			return;

		monitor.setTaskName("Initializing local repository"); //$NON-NLS-1$
		Transport tn = null;
		try {
			// Set the current branch
			//
			String gitBranch = getGitBranch(versionMatch, false);
			RefUpdate head = repository.updateRef(Constants.HEAD);
			head.disableRefLog();
			head.link(gitBranch);

			StoredConfig localConfig = repository.getConfig();
			RemoteConfig remoteConfig = new RemoteConfig(localConfig, remoteName);
			remoteConfig.addURI(repoURI);

			String dst = Constants.R_REMOTES + remoteConfig.getName();
			RefSpec wcrs = new RefSpec();
			wcrs = wcrs.setForceUpdate(true);
			wcrs = wcrs.setSourceDestination(Constants.R_HEADS + "*", dst + "/*"); //$NON-NLS-1$//$NON-NLS-2$
			remoteConfig.addFetchRefSpec(wcrs);

			// we're setting up for a clone with a checkout
			localConfig.setBoolean("core", null, "bare", false); //$NON-NLS-1$//$NON-NLS-2$
			remoteConfig.update(localConfig);

			// branch is like 'Constants.R_HEADS + branchName', we need only
			// the 'branchName' part
			String branchName = gitBranch.substring(Constants.R_HEADS.length());

			// setup the default remote branch for branchName
			localConfig.setString("branch", branchName, "remote", remoteName); //$NON-NLS-1$ //$NON-NLS-2$
			localConfig.setString("branch", branchName, "merge", gitBranch); //$NON-NLS-1$ //$NON-NLS-2$
			localConfig.save();

			tn = Transport.open(repository, remoteConfig);
			FetchResult result = tn.fetch(new EclipseGitProgressTransformer(monitor), null);
			Ref advHead = result.getAdvertisedRef(gitBranch);
			if (advHead == null || advHead.getObjectId() == null)
				// This is bad. The desired branch was not found
				throw BuckminsterException.fromMessage("Unable to find branch %s in remote repository %s", gitBranch, repoURI); //$NON-NLS-1$

			if (!Constants.HEAD.equals(advHead.getName())) {
				RefUpdate u = repository.updateRef(Constants.HEAD);
				u.disableRefLog();
				u.link(advHead.getName());
			}

			RevCommit c = parseCommit(advHead);
			RefUpdate u = repository.updateRef(Constants.HEAD);
			u.setNewObjectId(c.getId());
			u.forceUpdate();
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			if (tn != null)
				tn.close();
			monitor.done();
		}
	}

	private String getGitRemoteBranch(VersionMatch versionMatch) {
		String branchName = getBranchName(versionMatch);
		String remoteBase = Constants.R_REMOTES + remoteName + '/';
		if (branchName == null)
			return remoteBase + Constants.MASTER;
		return remoteBase + branchName;
	}

	private RevCommit parseCommit(final Ref branch) throws MissingObjectException, IncorrectObjectTypeException, IOException {
		final RevWalk rw = new RevWalk(repository);
		final RevCommit commit;
		try {
			commit = rw.parseCommit(branch.getObjectId());
		} finally {
			rw.release();
		}
		return commit;
	}
}
