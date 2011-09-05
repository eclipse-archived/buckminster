package org.eclipse.buckminster.git.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractSCCSVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

public class VersionFinder extends AbstractSCCSVersionFinder {
	private RepositoryAccess repoAccess;

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException {
		super(provider, ctype, query);
		@SuppressWarnings("unchecked")
		Map<String, String> props = (Map<String, String>) provider.getProperties(query.getProperties());
		repoAccess = new RepositoryAccess(getProvider().getURI(props), props);
	}

	@Override
	public synchronized void close() {
		if (repoAccess != null) {
			repoAccess.close();
			repoAccess = null;
		}
	}

	@Override
	protected boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException {
		synchronized (repoAccess.getRepositoryPath()) {
			TreeWalk walk = repoAccess.getTreeWalk(versionMatch, null, monitor);
			try {
				return walk.next();
			} catch (Exception e) {
				throw BuckminsterException.wrap(e);
			} finally {
				walk.release();
			}
		}
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException {
		try {
			Repository repo = repoAccess.getRepository();
			synchronized (repoAccess.getRepositoryPath()) {
				RevWalk revWalk = new RevWalk(repo);
				try {
					ArrayList<RevisionEntry> branchesOrTags = new ArrayList<RevisionEntry>();
					String component = repoAccess.getComponent();
					for (Ref ref : repo.getAllRefs().values()) {

						String name = ref.getName();
						int lastSlash = name.lastIndexOf('/');
						if (lastSlash < 0)
							continue;

						RevObject obj = revWalk.parseAny(ref.getObjectId());
						if (branches) {
							if (!(obj instanceof RevCommit))
								continue;

							// Last part of name is the branch
							String branch = name.substring(lastSlash + 1);
							if (Constants.MASTER.equals(branch))
								continue;

							RevCommit c = (RevCommit) obj;
							if (!(component == null || TreeWalk.forPath(repo, component, c.getTree()) != null))
								continue;

							// repoAccess.inspectRef(ref);

							// TODO: RevisionEntry should hold abbreviated
							// object id
							// instead of long revision
							branchesOrTags.add(new RevisionEntry(branch, c.getAuthorIdent().getWhen(), 0L));
						} else {
							if (!(obj instanceof RevTag))
								continue;

							RevTag tag = (RevTag) obj;
							if (component != null) {
								// Check that the component exists in the
								// associated
								// Commit
								do {
									obj = ((RevTag) obj).getObject();
								} while (obj instanceof RevTag);

								if (!(obj instanceof RevCommit && TreeWalk.forPath(repo, component, ((RevCommit) obj).getTree()) != null))
									continue;
							}
							branchesOrTags.add(new RevisionEntry(tag.getTagName(), tag.getTaggerIdent().getWhen(), 0L));
						}
					}
					return branchesOrTags;
				} finally {
					revWalk.release();
				}
			}
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException {
		// In git, this means get the current HEAD
		Repository repo = repoAccess.getRepository(null, monitor);
		RevWalk revWalk = new RevWalk(repo);
		try {
			String component = repoAccess.getComponent();
			Ref head = repo.getRef(Constants.HEAD);
			if (head == null)
				return null;
			RevCommit c = revWalk.parseCommit(head.getObjectId());
			if (component != null && TreeWalk.forPath(repo, component, c.getTree()) == null)
				return null;

			// TODO: RevisionEntry should hold abbreviated object id instead
			// of long revision
			return new RevisionEntry(component, c.getAuthorIdent().getWhen(), 0L);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			revWalk.release();
		}
	}
}
