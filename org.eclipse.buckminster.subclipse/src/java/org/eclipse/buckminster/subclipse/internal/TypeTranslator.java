package org.eclipse.buckminster.subclipse.internal;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNLock;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * Translates Subclipse types to more standard eclipse SVN types Used to unify the logic between the two SVN clients
 * (Subclipse and Subversive)
 * 
 * @author Guillaume Chatelet
 * 
 */
public class TypeTranslator
{
	public static SVNEntry from(ISVNDirEntry entry)
	{
		if(entry == null)
			return null;
		final String path = entry.getPath();
		final long revision = entry.getLastChangedRevision().getNumber();
		final long date = entry.getLastChangedDate().getTime();
		final String author = entry.getLastCommitAuthor();
		final boolean hasProps = entry.getHasProps();
		final int nodeKind = entry.getNodeKind().toInt();
		final long size = entry.getSize();
		final SVNLock lock = null;
		return new SVNEntry(path, revision, date, author, hasProps, nodeKind, size, lock);
	}

	public static org.eclipse.team.svn.core.connector.SVNRevision from(
			org.tigris.subversion.svnclientadapter.SVNRevision rev)
	{
		return SVNRevision.fromString(rev.toString());
	}

	public static org.tigris.subversion.svnclientadapter.SVNRevision from(SVNRevision revision) throws ParseException
	{
		return org.tigris.subversion.svnclientadapter.SVNRevision.getRevision(revision.toString());
	}

	public static URI from(SVNUrl url) throws CoreException
	{
		try
		{
			return new URI(url.toString());
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static SVNUrl from(URI url) throws CoreException
	{
		try
		{
			return new SVNUrl(url.toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

}
