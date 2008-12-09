package org.eclipse.buckminster.subversion;

import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class GenericReaderType<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> extends CatalogReaderType
{

	final public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		// Left null intentionally
		return null;
	}

	private ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> getSession(String repositoryURI, VersionSelector branchOrTag)
			throws CoreException
	{
		return getSession(repositoryURI, branchOrTag, -1, null, new RMContext(null));
	}

	abstract protected ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> getSession(String repositoryURI, VersionSelector branchOrTag,
			long revision, Date timestamp, RMContext context) throws CoreException;

	@Override
	final public Date getLastModification(String repositoryLocation, VersionSelector versionSelector,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		final ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> session = getSession(repositoryLocation, versionSelector);
		try
		{
			return session.getLastTimestamp();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	final public long getLastRevision(String repositoryLocation, VersionSelector versionSelector,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 1);
		final ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> session = getSession(repositoryLocation, versionSelector);
		try
		{
			return session.getLastChangeNumber();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	final public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor)
			throws CoreException
	{
		VersionMatch vm = cr.getVersionMatch();
		ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> session = getSession(cr.getRepository(), vm.getBranchOrTag(), vm
				.getRevision(), vm.getTimestamp(), context);
		session.createCommonRoots(context);
		session = getSession(cr.getRepository(), vm.getBranchOrTag(), vm
				.getRevision(), vm.getTimestamp(), context);
		try
		{
			updateRepositoryMap(project, session);
		}
		catch(Exception ex)
		{
			throw BuckminsterException.wrap(ex);
		}
		finally
		{
			session.close();
		}
		MonitorUtils.complete(monitor);
	}

	abstract protected void updateRepositoryMap(IProject project, ISubversionSession<SVN_ENTRY_TYPE,SVN_REVISION_TYPE> session)
			throws Exception;
}
