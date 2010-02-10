package org.eclipse.buckminster.subversion;

import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.ReferenceInfo;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

public abstract class GenericReaderType<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> extends CatalogReaderType {

	@Override
	public ReferenceInfo extractReferenceInfo(String reference) throws CoreException {
		String[] parts = TextUtils.split(reference, ",");
		if (parts.length < 3)
			throw BuckminsterException.fromMessage(NLS.bind("The svn readerType cannot parse PSF project reference {0}", reference));

		String repositoryLocation = parts[1];
		String module = null;
		VersionSelector selector = null;
		boolean useScheme = false;
		int trunkPos = repositoryLocation.indexOf("/trunk");
		if (trunkPos > 0) {
			// Do we have something after /trunk ?
			//
			int trunkEnd = trunkPos + 6;
			if (repositoryLocation.length() > trunkEnd) {
				// We do, then treat that as the module.
				//
				module = repositoryLocation.substring(trunkEnd);
				repositoryLocation = repositoryLocation.substring(0, trunkPos);
				useScheme = true;
			}
		} else {
			int branchPos = repositoryLocation.indexOf("/branches/");
			if (branchPos > 0) {
				// Treat what ever follows directly after /branches/ as a branch
				// name
				//
				int branchNameStart = branchPos + 10;
				int branchNameEnd = repositoryLocation.indexOf('/', branchNameStart);
				if (branchNameEnd == -1)
					branchNameEnd = repositoryLocation.length();
				else
					module = repositoryLocation.substring(branchNameEnd);
				selector = VersionSelector.branch(repositoryLocation.substring(branchNameStart, branchNameEnd));
				repositoryLocation = repositoryLocation.substring(0, branchPos);
				useScheme = true;
			} else {
				int tagPos = repositoryLocation.indexOf("/tags/");
				if (tagPos > 0) {
					// Treat what ever follows directly after /tags/ as a tag
					// name
					//
					int tagNameStart = tagPos + 6;
					int tagNameEnd = repositoryLocation.indexOf('/', tagNameStart);
					if (tagNameEnd == -1)
						tagNameEnd = repositoryLocation.length();
					else
						module = repositoryLocation.substring(tagNameEnd);
					selector = VersionSelector.tag(repositoryLocation.substring(tagNameStart, tagNameEnd));
					repositoryLocation = repositoryLocation.substring(0, tagPos);
					useScheme = true;
				}
			}
		}

		if (useScheme) {
			StringBuilder thaUri = new StringBuilder();
			thaUri.append(repositoryLocation);
			thaUri.append("/trunk");
			if (module != null) {
				thaUri.append(module);
				thaUri.append("?moduleAfterBranch&moduleAfterTag");
			} else
				thaUri.append("?moduleBeforeBranch&moduleBeforeTag");
			repositoryLocation = thaUri.toString();
		}
		return new ReferenceInfo(repositoryLocation, selector, parts[2]);
	}

	@Override
	final public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		// Left null intentionally
		return null;
	}

	@Override
	final public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 1);
		final ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> session = getSession(repositoryLocation, versionSelector);
		try {
			return session.getLastTimestamp();
		} finally {
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	final public long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 1);
		final ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> session = getSession(repositoryLocation, versionSelector);
		try {
			return session.getLastChangeNumber();
		} finally {
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	final public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
		VersionMatch vm = cr.getVersionMatch();
		ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> session = getSession(cr.getRepository(), vm.getBranchOrTag(), vm.getNumericRevision(),
				vm.getTimestamp(), context);
		session.createCommonRoots(context);
		session = getSession(cr.getRepository(), vm.getBranchOrTag(), vm.getNumericRevision(), vm.getTimestamp(), context);
		try {
			updateRepositoryMap(project, session);
		} catch (Exception ex) {
			throw BuckminsterException.wrap(ex);
		} finally {
			session.close();
		}
		MonitorUtils.complete(monitor);
	}

	abstract protected ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> getSession(String repositoryURI, VersionSelector branchOrTag,
			long revision, Date timestamp, RMContext context) throws CoreException;

	abstract protected void updateRepositoryMap(IProject project, ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> session) throws Exception;

	private ISubversionSession<SVN_ENTRY_TYPE, SVN_REVISION_TYPE> getSession(String repositoryURI, VersionSelector branchOrTag) throws CoreException {
		return getSession(repositoryURI, branchOrTag, -1, null, new RMContext(null));
	}
}
