package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.history.IFileHistory;
import org.eclipse.team.core.history.IFileHistoryProvider;
import org.eclipse.team.core.history.IFileRevision;

public class GitReaderType extends CatalogReaderType {
	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		return null;
	}

	@Override
	public IPath getInstallLocation(Resolution resolution, MaterializationContext context) throws CoreException {
		String fmt = resolution.getRepository();
		int comma = fmt.lastIndexOf(',');
		File repo;
		if (comma >= 0) {
			fmt = fmt.substring(0, comma);
			repo = new File(fmt);
		} else {
			// The repository _is_ the component, so the install location
			// will be the repository parent.
			repo = new File(fmt).getParentFile();
		}
		return Path.fromOSString(repo.getAbsolutePath()).addTrailingSeparator();
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath workingCopyPath = Path.fromOSString(workingCopy.getAbsolutePath());
		IResource resource = wsRoot.getContainerForLocation(workingCopyPath);
		if (resource == null) {
			resource = wsRoot.getFileForLocation(workingCopyPath);
			if (resource == null)
				return null;
		}
		RepositoryProvider provider = RepositoryProvider.getProvider(resource.getProject());
		if (provider == null)
			return null;

		IFileHistory history = provider.getFileHistoryProvider().getFileHistoryFor(resource, IFileHistoryProvider.SINGLE_REVISION, monitor);
		IFileRevision[] revisions = history.getFileRevisions();
		return revisions.length == 0 ? null : new Date(revisions[0].getTimestamp());
	}

	@Override
	public IPath getLeafArtifact(Resolution resolution, MaterializationContext context) throws CoreException {
		String fmt = resolution.getRepository();
		int comma = fmt.lastIndexOf(',');
		if (comma >= 0)
			fmt = fmt.substring(comma + 1);
		else {
			// The repository _is_ the component, so the leaf artifact is
			// the repository.
			fmt = new File(fmt).getName();
		}
		return Path.fromOSString(fmt).addTrailingSeparator();
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new GitReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}

	/**
	 * Closes any cached RepositoryAccess instances.
	 */
	@Override
	public void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException {

	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
		// Register the project with the GitTeamProvider.
		//
		String fmt = cr.getRepository();
		int comma = fmt.lastIndexOf(',');
		ConnectProviderOperation connectOp;
		File repoDir;
		if (comma >= 0) {
			fmt = fmt.substring(0, comma);
			repoDir = Path.fromPortableString(fmt).append(".git").toFile(); //$NON-NLS-1$
			connectOp = new ConnectProviderOperation(project, repoDir);
		} else {
			repoDir = new File(fmt);
			connectOp = new ConnectProviderOperation(project);
		}
		
		// Add repository if it's not already addded
		RepositoryUtil repoUtil = org.eclipse.egit.core.Activator.getDefault().getRepositoryUtil();
		repoUtil.addConfiguredRepository(repoDir);

		connectOp.execute(monitor);
	}
}
