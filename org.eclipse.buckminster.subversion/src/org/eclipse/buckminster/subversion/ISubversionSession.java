package org.eclipse.buckminster.subversion;

import java.io.Closeable;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public interface ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> extends Closeable {
	@Override
	public void close();

	public void createCommonRoots(RMContext context) throws CoreException;

	public long getLastChangeNumber() throws CoreException;

	public Date getLastTimestamp() throws CoreException;

	public SVN_REVISION_TYPE getRevision();

	public SVN_ENTRY_TYPE getRootEntry(IProgressMonitor monitor) throws CoreException;

	public ISvnEntryHelper<SVN_ENTRY_TYPE> getSvnEntryHelper();

	public URI getSVNRootUrl(boolean branches) throws CoreException;

	public URI getSVNUrl() throws CoreException;

	public URI getSVNUrl(String url) throws CoreException;

	public boolean hasTrunkStructure();

	public SVN_ENTRY_TYPE[] listFolder(URI url, IProgressMonitor monitor) throws CoreException;

}
