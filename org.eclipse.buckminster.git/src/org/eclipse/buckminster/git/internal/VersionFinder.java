package org.eclipse.buckminster.git.internal;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractSCCSVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.lib.Commit;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.Tag;
import org.eclipse.jgit.lib.Tree;
import org.eclipse.jgit.lib.TreeEntry;

public class VersionFinder extends AbstractSCCSVersionFinder
{
	private RepositoryKeeper repositoryKeeper;

	public VersionFinder(Provider provider, IComponentType ctype, NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
		repositoryKeeper = new RepositoryKeeper(getProvider().getURI(getQuery().getProperties()), null, getProvider().getProviderProperties());
	}

	@Override
	public synchronized void close()
	{
		if(repositoryKeeper != null)
		{
			repositoryKeeper.close();
			repositoryKeeper = null;
		}
	}

	@Override
	protected boolean checkComponentExistence(VersionMatch versionMatch, IProgressMonitor monitor) throws CoreException
	{
		return false;
	}

	@Override
	protected List<RevisionEntry> getBranchesOrTags(boolean branches, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			ArrayList<RevisionEntry> branchesOrTags = new ArrayList<RevisionEntry>();
			Repository repo = repositoryKeeper.getRepository(monitor);
			String component = repositoryKeeper.getComponent();
			System.out.println(repo.getBranch());
			for(Ref ref : repo.getAllRefs().values())
			{

				String name = ref.getName();
				int lastSlash = name.lastIndexOf('/');
				if(lastSlash < 0)
					continue;

				Object obj = repo.mapObject(ref.getObjectId(), name);
				if(branches)
				{
					if(!(obj instanceof Commit))
						continue;

					// Last part of name is the branch
					String branch = name.substring(lastSlash + 1);
					if(Constants.MASTER.equals(branch))
						continue;

					Commit c = (Commit)obj;
					if(!(component == null || c.getTree().existsTree(component)))
						continue;

					inspectRef(ref);

					// TODO: RevisionEntry should hold abbreviated object id instead of long revision
					branchesOrTags.add(new RevisionEntry(branch, c.getAuthor().getWhen(), 0L));
				}
				else
				{
					if(!(obj instanceof Tag))
						continue;

					Tag tag = (Tag)obj;
					if(component != null)
					{
						// Check that the component exists in the associated Commit
						do
						{
							obj = repo.mapObject(((Tag)obj).getObjId(), null);
						} while(obj instanceof Tag);

						if(!(obj instanceof Commit && ((Commit)obj).getTree().existsTree(component)))
							continue;
					}
					branchesOrTags.add(new RevisionEntry(tag.getTag(), tag.getAuthor().getWhen(), 0L));
				}
			}
			return branchesOrTags;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

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

	private void inspectRef(Ref ref) throws CoreException, IOException
	{
		Repository repo = repositoryKeeper.getRepository(null);
		Object obj = repo.mapObject(ref.getObjectId(), ref.getName());
		final StringBuilder sb = new StringBuilder();
		sb.append(ref.getName());
		sb.append('\n');
		sb.append(ref.getObjectId().abbreviate(repo).name());
		sb.append(" - ");
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
		}
		else if(obj instanceof Blob)
		{
			sb.append("blob");
		}
		else
			sb.append("locally unknown object");
		System.out.println(sb);
		System.out.println();

	}

	@Override
	protected RevisionEntry getTrunk(IProgressMonitor monitor) throws CoreException
	{
		try
		{
			Repository repo = repositoryKeeper.getRepository(monitor);
			String component = repositoryKeeper.getComponent();
			System.out.println(repo.getBranch());
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
				if(!(component == null || c.getTree().existsTree(component)))
					continue;

				inspectRef(ref);

				// TODO: RevisionEntry should hold abbreviated object id instead of long revision
				return new RevisionEntry(component, c.getAuthor().getWhen(), 0L);
			}
			return null;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	String getGitBranch(String branchName)
	{
		if(branchName == null)
			return Constants.R_HEADS + Constants.MASTER;
		return Constants.R_HEADS + branchName;
	}
}
