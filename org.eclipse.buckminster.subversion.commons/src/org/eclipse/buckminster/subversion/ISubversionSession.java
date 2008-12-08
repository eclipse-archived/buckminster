package org.eclipse.buckminster.subversion;

import java.io.Closeable;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNRevision;

public interface ISubversionSession<SVN_ENTRY_TYPE> extends Closeable
{
	public void close();

	public long getLastChangeNumber() throws CoreException;

	public Date getLastTimestamp() throws CoreException;

	public SVNRevision getRevision();

	public SVNEntry getRootEntry(IProgressMonitor monitor) throws CoreException;

	public URI getSVNRootUrl(boolean branches) throws CoreException;

	public boolean hasTrunkStructure();

	public SVN_ENTRY_TYPE[] listFolder(URI url, IProgressMonitor monitor) throws CoreException;

	public URI getSVNUrl() throws CoreException;

	public URI getSVNUrl(String url) throws CoreException;

	public void createCommonRoots(RMContext context) throws CoreException;

	public ISvnEntryHelper<SVN_ENTRY_TYPE> getSvnEntryHelper();

}
