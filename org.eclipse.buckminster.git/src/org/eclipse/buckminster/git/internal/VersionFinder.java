package org.eclipse.buckminster.git.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.AbstractSCCSVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.lib.Commit;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.Tag;

public class VersionFinder extends AbstractSCCSVersionFinder {
	private RepositoryAccess repoAccess;

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException {
		super(provider, ctype, query);
		repoAccess = new RepositoryAccess(getProvider().getURI(getQuery().getProperties()), getProvider().getProperties());
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
		return repoAccess.getComponentTree(versionMatch, monitor) != null;
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException {
		try {
			ArrayList<RevisionEntry> branchesOrTags = new ArrayList<RevisionEntry>();
			Repository repo = repoAccess.getRepository(monitor);
			String component = repoAccess.getComponent();
			System.out.println(repo.getBranch());
			for (Ref ref : repo.getAllRefs().values()) {

				String name = ref.getName();
				int lastSlash = name.lastIndexOf('/');
				if (lastSlash < 0)
					continue;

				Object obj = repo.mapObject(ref.getObjectId(), name);
				if (branches) {
					if (!(obj instanceof Commit))
						continue;

					// Last part of name is the branch
					String branch = name.substring(lastSlash + 1);
					if (Constants.MASTER.equals(branch))
						continue;

					Commit c = (Commit) obj;
					if (!(component == null || c.getTree().existsTree(component)))
						continue;

					// repoAccess.inspectRef(ref);

					// TODO: RevisionEntry should hold abbreviated object id
					// instead of long revision
					branchesOrTags.add(new RevisionEntry(branch, c.getAuthor().getWhen(), 0L));
				} else {
					if (!(obj instanceof Tag))
						continue;

					Tag tag = (Tag) obj;
					if (component != null) {
						// Check that the component exists in the associated
						// Commit
						do {
							obj = repo.mapObject(((Tag) obj).getObjId(), null);
						} while (obj instanceof Tag);

						if (!(obj instanceof Commit && ((Commit) obj).getTree().existsTree(component)))
							continue;
					}
					branchesOrTags.add(new RevisionEntry(tag.getTag(), tag.getAuthor().getWhen(), 0L));
				}
			}
			return branchesOrTags;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException {
		try {
			Repository repo = repoAccess.getRepository(monitor);
			String component = repoAccess.getComponent();
			System.out.println(repo.getBranch());
			for (Ref ref : repo.getAllRefs().values()) {

				String name = ref.getName();
				int lastSlash = name.lastIndexOf('/');
				if (lastSlash < 0)
					continue;

				Object obj = repo.mapObject(ref.getObjectId(), name);
				if (!(obj instanceof Commit))
					continue;

				// Last part of name is the branch
				String branch = name.substring(lastSlash + 1);
				if (!Constants.MASTER.equals(branch))
					continue;

				Commit c = (Commit) obj;
				if (!(component == null || c.getTree().existsTree(component)))
					continue;

				// repoAccess.inspectRef(ref);

				// TODO: RevisionEntry should hold abbreviated object id instead
				// of long revision
				return new RevisionEntry(component, c.getAuthor().getWhen(), 0L);
			}
			return null;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	String getGitBranch(String branchName) {
		if (branchName == null)
			return Constants.R_HEADS + Constants.MASTER;
		return Constants.R_HEADS + branchName;
	}
}
