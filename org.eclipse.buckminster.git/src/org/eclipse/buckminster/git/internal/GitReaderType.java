package org.eclipse.buckminster.git.internal;

import java.io.File;
import java.net.URI;

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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.team.core.RepositoryProvider;

public class GitReaderType extends CatalogReaderType
{
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		return null;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new GitReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor)
			throws CoreException
	{
		// Register the project with the GitTeamProvider.
		//
		ConnectProviderOperation connectOp = new ConnectProviderOperation(project);
		connectOp.run(monitor);
	}

	public IPath getInstallLocation(Resolution resolution, MaterializationContext context) throws CoreException
	{
		String fmt = resolution.getRepository();
		int comma = fmt.lastIndexOf(',');
		File repo;
		if(comma >= 0)
		{
			fmt = fmt.substring(0, comma);
			repo = new File(fmt);
		} else {
			// The repository _is_ the component, so the install location
			// will be the repository parent.
			repo = new File(fmt).getParentFile();
		}
		return Path.fromOSString(repo.getAbsolutePath()).addTrailingSeparator();
	}

	public IPath getLeafArtifact(Resolution resolution, MaterializationContext context) throws CoreException
	{
		String fmt = resolution.getRepository();
		int comma = fmt.lastIndexOf(',');
		if(comma >= 0)
			fmt = fmt.substring(comma + 1);
		else {
			// The repository _is_ the component, so the leaf artifact is
			// the repository.
			fmt = new File(fmt).getName();
		}
		return Path.fromOSString(fmt).addTrailingSeparator();
	}
}
