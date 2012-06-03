package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.IndexDiff;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepository;
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
	private static final Logger logger = Buckminster.getLogger();

	private static final Object NULL_STRING = "null"; //$NON-NLS-1$

	private static String getGitTag(VersionMatch versionMatch) {
		String tagName = getTagName(versionMatch);
		if (tagName == null)
			return null;
		return Constants.R_TAGS + tagName;
	}

	private static String getTagName(VersionMatch versionMatch) {
		if (versionMatch == null)
			return null;

		VersionSelector vs = versionMatch.getBranchOrTag();
		if (vs != null && vs.getType() == VersionSelector.TAG && !vs.isDefault()) {
			String tagName = vs.getName();
			if (tagName.startsWith("refs/")) //$NON-NLS-1$
				tagName = tagName.substring(5);
			if (tagName.startsWith("tags/")) //$NON-NLS-1$
				tagName = tagName.substring(5);
			return tagName;
		}
		return null;
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

	synchronized void close() {
		if (repository != null) {
			repository.close();
			repository = null;
		}
	}

	RevCommit getBranchOrTagId(Repository repo, VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException {
		try {
			RevCommit objId = null;
			String revstr = getGitTag(versionMatch);
			if (revstr != null) {
				// Set the working copy to reflect a branch based on a tag
				//
				objId = parseCommit(repo.getRef(revstr));
				if (objId == null)
					throw BuckminsterException.fromMessage("Unable to obtain ObjectID for tag %s", revstr); //$NON-NLS-1$

				// We don't want to simply check out the tag since that would
				// create a detached HEAD. Instead
				// we create a branch that originates from the tag. Unless that
				// hasn't been done already
				// of course.
				//
				Git git = new Git(repo);
				CheckoutCommand co = git.checkout();

				String tagBranch = "tag-branch_" + getTagName(versionMatch); //$NON-NLS-1$
				objId = parseCommit(repo.getRef(tagBranch));
				if (objId == null) {
					logger.info("Checking out %s to new branch %s", revstr, tagBranch); //$NON-NLS-1$
					co.setCreateBranch(true);
					co.setName(tagBranch);
					co.setStartPoint(revstr);
					return parseCommit(co.call());
				}
				RevCommit currentId = parseCommit(repo.getRef(Constants.HEAD));
				if (!currentId.equals(objId)) {
					logger.info("Checking out existing branch %s to get tag %s", tagBranch, revstr); //$NON-NLS-1$
					co.setCreateBranch(false);
					co.setName(tagBranch);
					RevCommit branchCommit = parseCommit(co.call());
					if (!objId.equals(branchCommit)) {
						// So what do we do now? If we reset this branch to the
						// tag, we will
						// loose commits.
						logger.warning("Branch %s has moved since it was created from %s", tagBranch, revstr); //$NON-NLS-1$
					}
				}
				return objId;
			}

			revstr = getGitBranch(versionMatch);
			if (revstr == null)
				revstr = Constants.HEAD;

			objId = parseCommit(repo.getRef(revstr));
			if (objId != null)
				return objId;

			String remoteBranch = getGitRemoteBranch(versionMatch, repo.getBranch());
			objId = parseCommit(repo.getRef(remoteBranch));
			if (objId == null)
				throw BuckminsterException.fromMessage("Unable to obtain ObjectID for branch %s", remoteBranch); //$NON-NLS-1$

			// We must create a new local branch that tracks the origin:
			//
			// git checkout --track -b <branch> origin/<branch>
			//
			String localBranch = getBranchName(versionMatch);
			if (localBranch == null)
				localBranch = Constants.MASTER;

			logger.info("Creating branch %s to track remote branch %s", localBranch, remoteBranch); //$NON-NLS-1$
			Git git = new Git(repo);
			CheckoutCommand co = git.checkout();
			co.setUpstreamMode(SetupUpstreamMode.TRACK);
			co.setCreateBranch(true);
			co.setName(localBranch);
			co.setStartPoint(remoteBranch);
			objId = parseCommit(co.call());

			// Set up pull configuration
			StoredConfig config = repo.getConfig();
			config.setString("branch", localBranch, "remote", remoteName); //$NON-NLS-1$//$NON-NLS-2$
			config.setString("branch", localBranch, "merge", Constants.R_HEADS + localBranch); //$NON-NLS-1$ //$NON-NLS-2$
			config.save();
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

	Repository getRepository() throws CoreException {
		File canonicalLocalRepo;
		try {
			canonicalLocalRepo = localRepo.getCanonicalFile();
		} catch (IOException e) {
			canonicalLocalRepo = localRepo;
		}
		String repoPath = canonicalLocalRepo.getAbsolutePath().intern();

		synchronized (repoPath) {
			if (repository != null)
				return repository;

			try {
				boolean infant = !canonicalLocalRepo.exists();

				if (infant) {
					File localDir = canonicalLocalRepo.getParentFile();
					logger.info("Cloning remote repository %s into %s", repoURI.toString(), localDir.getAbsolutePath()); //$NON-NLS-1$
					CloneCommand cc = Git.cloneRepository();
					cc.setBare(false);
					cc.setDirectory(localDir);
					cc.setNoCheckout(false);
					cc.setURI(repoURI.toPrivateString());
					cc.call();
				}
				repository = new FileRepository(canonicalLocalRepo);

				// Add repository if it's not already addded
				RepositoryUtil repoUtil = org.eclipse.egit.core.Activator.getDefault().getRepositoryUtil();
				if (repoUtil.addConfiguredRepository(canonicalLocalRepo))
					logger.info("Added Git repository at %s to the set of known repositories", repoPath); //$NON-NLS-1$
				return repository;
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw BuckminsterException.wrap(e);
			}
		}
	}

	Repository getRepository(VersionMatch vm, IProgressMonitor monitor) throws CoreException {
		String refStr = getGitTag(vm);
		if (refStr == null)
			refStr = getGitBranch(vm);
		try {
			Repository repo = getRepository();
			synchronized (getRepositoryPath()) {
				RevCommit wantedId = getBranchOrTagId(repo, vm, monitor);
				RevCommit currentId = parseCommit(repo.getRef(Constants.HEAD));
				if (refStr == null || wantedId.equals(currentId))
					return repo;

				IndexDiff diff = new IndexDiff(repo, refStr, new FileTreeIterator(repo));
				if (!diff.diff())
					return repo;

				if (component != null) {
					// We don't signal a conflict unless we find one beneath our
					// own component
					if (!(scanFiles(diff.getMissing()) || scanFiles(diff.getModified()) || scanFiles(diff.getUntracked())))
						return repo;
				}
			}

			throw BuckminsterException.fromMessage(NLS.bind(Messages.git_reader_0_cannot_switch_to_1_without_causing_conflict_beneath_2,
					new Object[] { localRepo.getAbsolutePath(), refStr, component }));
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	String getRepositoryPath() {
		File canonicalLocalRepo;
		try {
			canonicalLocalRepo = localRepo.getCanonicalFile();
		} catch (IOException e) {
			canonicalLocalRepo = localRepo;
		}
		return canonicalLocalRepo.getAbsolutePath().intern();
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
				path = component + (path != null ? '/' + path : ""); //$NON-NLS-1$
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
		ObjectId id = getBranchOrTagId(getRepository(versionMatch, monitor), versionMatch, monitor);
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

	private String getBranchName(VersionMatch versionMatch) {
		if (versionMatch != null) {
			VersionSelector vs = versionMatch.getBranchOrTag();
			if (vs != null && vs.getType() == VersionSelector.BRANCH && !vs.isDefault()) {
				String branchName = vs.getName();
				if (branchName.startsWith("refs/")) //$NON-NLS-1$
					branchName = branchName.substring(5);
				if (branchName.startsWith("remotes/")) //$NON-NLS-1$
					branchName = branchName.substring(8);
				else if (branchName.startsWith("heads/")) //$NON-NLS-1$
					branchName = branchName.substring(6);
				int rlen = remoteName.length();
				if (branchName.length() > rlen && branchName.startsWith(remoteName) && branchName.charAt(rlen) == '/')
					branchName = branchName.substring(rlen + 1);
				return branchName;
			}
		}
		return null;
	}

	private String getGitBranch(VersionMatch versionMatch) {
		String branchName = getBranchName(versionMatch);
		return branchName == null ? null : Constants.R_HEADS + branchName;
	}

	private String getGitRemoteBranch(VersionMatch versionMatch, String currentBranch) {
		String branchName = getBranchName(versionMatch);
		String remoteBase = remoteName + '/';
		if (branchName == null)
			branchName = currentBranch;
		return remoteBase + branchName;
	}

	private RevCommit parseCommit(Ref branch) throws MissingObjectException, IncorrectObjectTypeException, IOException {
		if (branch == null)
			return null;
		RevWalk rw = new RevWalk(repository);
		try {
			return rw.parseCommit(branch.getObjectId());
		} finally {
			rw.release();
		}
	}
}
