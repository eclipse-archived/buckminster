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
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.GitIndex;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.lib.Tree;
import org.eclipse.jgit.lib.TreeEntry;
import org.eclipse.jgit.lib.WorkDirCheckout;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.transport.URIish;

/**
 * An instance of RepositoryAccess manages one component in some branch or tag
 * in a repository.
 */
@SuppressWarnings("deprecation")
class RepositoryAccess {
	private static final Object NULL_STRING = "null";

	private static void appendObjectSummary(final StringBuilder sb, final String type, final PersonIdent author, final String message) {
		sb.append(type + " by "); //$NON-NLS-1$
		sb.append(author.getName());
		sb.append("\n"); //$NON-NLS-1$
		sb.append(author.getWhen());
		sb.append("\n\n"); //$NON-NLS-1$
		final int newLine = message.indexOf('\n');
		final int last = (newLine != -1 ? newLine : message.length());
		sb.append(message.substring(0, last));
	}

	private final boolean autoFetch;

	private final String component;

	private final File localRepo;

	private final String remoteName;

	private Repository repository;

	private RevWalk revWalk;

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
				if(remoteURIStr.startsWith("file:/") && remoteURIStr.length() > 6 && remoteURIStr.charAt(6) != '/') //$NON-NLS-1$
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

	void checkout(VersionMatch versionMatch, File destination, IProgressMonitor monitor) throws CoreException {
		try {
			Repository local = getRepository(monitor);
			synchronized (localRepo.getAbsolutePath().intern()) {
				GitIndex index = new GitIndex(local);
				WorkDirCheckout co = new WorkDirCheckout(local, local.getWorkTree(), index, getComponentTree(versionMatch, monitor));
				co.checkout();
				index.write();
			}
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	synchronized void close() {
		if (revWalk != null) {
			revWalk.dispose();
			revWalk = null;
		}
		if (repository != null) {
			repository.close();
			repository = null;
		}
	}

	RevCommit getCommit(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException {
		try {
			ObjectId objId = null;
			Repository repo = getRepository(monitor);
			String revstr = getGitTag(versionMatch);
			if (revstr != null) {
				objId = repo.resolve(revstr);
				if (objId == null)
					throw BuckminsterException.fromMessage("Unable to obtain ObjectID for tag %s", revstr); //$NON-NLS-1$
			} else {
				revstr = getGitBranch(versionMatch);
				objId = repo.resolve(revstr);
				if (objId == null) {
					String remoteBranch = getGitRemoteBranch(versionMatch);
					objId = repo.resolve(revstr);
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
			return revWalk.parseCommit(objId);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	String getComponent() {
		return component;
	}

	Tree getComponentTree(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException {
		try {
			RevCommit c = getCommit(versionMatch, monitor);
			if (component == null)
				return repository.mapTree(c.getTree());

			return (Tree) repository.mapTree(c.getTree()).findTreeMember(component);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	synchronized Repository getRepository(IProgressMonitor monitor) throws CoreException {
		if (repository != null)
			return repository;

		boolean infant = !localRepo.exists();
		try {
			repository = new FileRepository(localRepo);
			if (infant) {
				repository.create();
				fetch(null, monitor); // Initial clone
			}
			revWalk = new RevWalk(repository);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
		return repository;
	}

	void inspectObj(Object obj) throws IOException {
		StringBuilder sb = new StringBuilder();
		inspectObj(sb, obj);
		System.out.println(sb);
		System.out.println();
	}

	void inspectObj(StringBuilder sb, Object obj) throws IOException {
		if (obj instanceof RevCommit) {
			final RevCommit c = ((RevCommit) obj);
			appendObjectSummary(sb, "commit", c.getAuthorIdent(), c.getShortMessage()); //$NON-NLS-1$
			for (TreeEntry te : repository.mapTree(c.getTree()).members()) {
				sb.append("  "); //$NON-NLS-1$
				sb.append(te.getFullName());
				sb.append("\n"); //$NON-NLS-1$
			}
		} else if (obj instanceof RevTag) {
			final RevTag t = ((RevTag) obj);
			appendObjectSummary(sb, "tag", t.getTaggerIdent(), t.getShortMessage()); //$NON-NLS-1$
		} else if (obj instanceof Tree) {
			sb.append("tree"); //$NON-NLS-1$
			for (TreeEntry te : ((Tree) obj).members()) {
				sb.append("  "); //$NON-NLS-1$
				sb.append(te.getFullName());
				sb.append("\n"); //$NON-NLS-1$
			}
		} else if (obj instanceof Blob) {
			sb.append("blob"); //$NON-NLS-1$
		} else
			sb.append("locally unknown object"); //$NON-NLS-1$
	}

	void inspectRef(Ref ref) throws IOException {
		StringBuilder sb = new StringBuilder();
		inspectRef(sb, ref);
		System.out.println(sb);
		System.out.println();
	}

	void inspectRef(StringBuilder sb, Ref ref) throws IOException {
		sb.append(ref.getName());
		sb.append('\n');
		sb.append(ref.getObjectId().abbreviate(8).name());
		sb.append(" - "); //$NON-NLS-1$
		inspectObj(sb, ref.getObjectId());
	}

	boolean isAutoFetch() {
		return autoFetch;
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
			String gitBranch = getGitBranch(versionMatch);
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

	private String getBranchName(VersionMatch versionMatch) {
		if (versionMatch != null) {
			VersionSelector vs = versionMatch.getBranchOrTag();
			if (vs != null && vs.getType() == VersionSelector.BRANCH && !vs.isDefault())
				return vs.getName();
		}
		return null;
	}

	private String getGitBranch(VersionMatch versionMatch) {
		String branchName = getBranchName(versionMatch);
		if (branchName == null)
			return Constants.R_HEADS + Constants.MASTER;
		return Constants.R_HEADS + branchName;
	}

	private String getGitRemoteBranch(VersionMatch versionMatch) {
		String branchName = getBranchName(versionMatch);
		String remoteBase = Constants.R_REMOTES + remoteName + '/';
		if (branchName == null)
			return remoteBase + Constants.MASTER;
		return remoteBase + branchName;
	}

	private String getGitTag(VersionMatch versionMatch) {
		if (versionMatch == null)
			return null;

		VersionSelector vs = versionMatch.getBranchOrTag();
		if (vs == null || vs.getType() != VersionSelector.TAG)
			return null;

		return Constants.R_TAGS + vs.getName();
	}

	RevWalk getRevWalk() {
		return revWalk;
	}
}
