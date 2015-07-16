/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 *
 * Contributors:
 *  Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=428301
 */

package org.eclipse.buckminster.git.internal;

import static org.eclipse.buckminster.core.helpers.MapUtils.getString;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.ITeamReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.egit.core.project.GitProjectData;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilterGroup;
import org.eclipse.jgit.treewalk.filter.TreeFilter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.core.RepositoryProvider;

public class GitReaderType extends CatalogReaderType implements ITeamReaderType {
	@Override
	public String convertFetchFactoryLocator(Map<String, Object> fetchFactoryLocator, String componentName) throws CoreException {
		String repo = getString(fetchFactoryLocator, "repo"); //$NON-NLS-1$
		if (repo == null)
			throw new IllegalArgumentException(NLS.bind(Messages.git_reader_type_is_missing_required_property_0, "repo")); //$NON-NLS-1$
		String localClone = "${workspace.root}"; //$NON-NLS-1$
		try {
			URIish tmpURI = new URIish(repo);
			IPath tmpPath = Path.fromPortableString(tmpURI.getPath());
			localClone += '/' + tmpPath.lastSegment();
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		}
		String path = getString(fetchFactoryLocator, "path"); //$NON-NLS-1$
		if (path != null)
			localClone += ',' + path;
		return localClone;
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		return null;
	}

	@Override
	public Map<String, String> getFetchFactoryProviderProps(Map<String, Object> fetchFactoryLocator, Provider delegee) {
		Map<String, String> props = new HashMap<String, String>();
		props.put(KeyConstants.IS_SOURCE, Boolean.TRUE.toString());
		props.put(KeyConstants.IS_MUTABLE, Boolean.TRUE.toString());

		String repo = getString(fetchFactoryLocator, "repo"); //$NON-NLS-1$
		if (repo == null)
			throw new IllegalArgumentException(NLS.bind(Messages.git_reader_type_is_missing_required_property_0, "repo")); //$NON-NLS-1$
		props.put(IPropertyKeys.PROP_REMOTE_URI, repo);
		return props;
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
		Logger logger = Buckminster.getLogger();
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		String workingCopyStr = workingCopy.getAbsolutePath();
		IPath workingCopyPath = Path.fromOSString(workingCopyStr);
		IResource resource = wsRoot.getContainerForLocation(workingCopyPath);
		if (resource == null)
			resource = wsRoot.getFileForLocation(workingCopyPath);

		if (resource == null) {
			// Try canonical path too before we give up
			try {
				workingCopyStr = workingCopy.getCanonicalPath();
				workingCopyPath = Path.fromOSString(workingCopyStr);
				resource = wsRoot.getContainerForLocation(workingCopyPath);
				if (resource == null)
					resource = wsRoot.getFileForLocation(workingCopyPath);
			} catch (IOException e) {
			}
		}

		if (resource == null) {
			logger.debug("getLastModification: Failed get resource for path %s", workingCopy.getAbsolutePath()); //$NON-NLS-1$
			return null;
		}

		RepositoryProvider provider = RepositoryProvider.getProvider(resource.getProject());
		if (provider == null) {
			logger.debug("getLastModification: Unable to get repository provider for project %s", resource.getProject().getName()); //$NON-NLS-1$
			return null;
		}

		RepositoryMapping rm = RepositoryMapping.getMapping(resource);
		if (rm == null) {
			logger.debug("getLastModification: Unable to get repository mapping for project %s", resource.getProject().getName()); //$NON-NLS-1$
			return null;
		}

		Repository repository = rm.getRepository();

		RevWalk walk = new RevWalk(repository);
		try {
			String gitPath = rm.getRepoRelativePath(resource);

			if (gitPath == null || gitPath.length() == 0) {
				walk.setTreeFilter(TreeFilter.ANY_DIFF);
			} else {
				walk.setTreeFilter(AndTreeFilter.create(PathFilterGroup.createFromStrings(Collections.singleton(gitPath)), TreeFilter.ANY_DIFF));
			}

			final AnyObjectId headId = repository.resolve(Constants.HEAD);
			if (headId == null) {
				logger.debug("getLastModification: Unable to find head revision in repository %s", repository.getDirectory().getAbsolutePath()); //$NON-NLS-1$
				return null;
			}

			walk.markStart(walk.parseCommit(headId));
			RevCommit rc;
			long lastTimestamp = 0;
			while ((rc = walk.next()) != null) {
				long secondsSinceEpoch = rc.getCommitTime();
				long commitTimestamp = secondsSinceEpoch * 1000L;
				if (commitTimestamp > lastTimestamp)
					lastTimestamp = commitTimestamp;
			}
			if (lastTimestamp == 0) {
				logger.debug("getLastModification: Unable to find any file revisions in project %s", resource.getProject().getName()); //$NON-NLS-1$
				return null;
			}
			return new Date(lastTimestamp);
		} catch (IOException ex) {
			throw BuckminsterException.wrap(ex);
		} finally {
			walk.close();
		}
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
	public String getSourceReference(IResource resource, IProgressMonitor monitor) throws CoreException {
		IProject project = resource.getProject();
		if (project == null)
			return null;

		RepositoryProvider provider = RepositoryProvider.getProvider(project);
		if (!(provider instanceof GitProvider))
			return null;

		GitProjectData projectData = ((GitProvider) provider).getData();
		if (projectData == null)
			return null;

		RepositoryMapping mapping = projectData.getRepositoryMapping(resource);
		if (mapping == null)
			return null;

		Repository repo = mapping.getRepository();
		if (repo == null)
			return null;

		StoredConfig config = repo.getConfig();
		String remoteConfig;
		Set<String> configNames = config.getSubsections("remote"); //$NON-NLS-1$
		if (configNames.size() == 1)
			remoteConfig = configNames.iterator().next();
		else if (configNames.contains(Constants.DEFAULT_REMOTE_NAME))
			remoteConfig = Constants.DEFAULT_REMOTE_NAME;
		else
			return null;

		String remoteLocation = config.getString("remote", remoteConfig, "url"); //$NON-NLS-1$//$NON-NLS-2$
		if (remoteLocation == null)
			return null;
		return "scm:git:" + remoteLocation; //$NON-NLS-1$
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
		File repoDir = null;
		if (cr.getReaderTypeId().equals("git")) { //$NON-NLS-1$
			String fmt = cr.getRepository();
			int comma = fmt.lastIndexOf(',');
			if (comma >= 0)
				fmt = fmt.substring(0, comma);
			repoDir = Path.fromPortableString(fmt).append(Constants.DOT_GIT).toFile();
		} else {
			IPath location = project.getLocation();
			while (location.segmentCount() > 0) {
				File dotGit = location.append(Constants.DOT_GIT).toFile();
				if (dotGit.exists()) {
					repoDir = dotGit;
					break;
				}
				location = location.removeLastSegments(1);
			}
			if (repoDir == null)
				return;
		}

		// Add repository if it's not already added
		try {
			repoDir = repoDir.getCanonicalFile();
		} catch (IOException e) {
		}
		String absPath = repoDir.getAbsolutePath().intern();

		synchronized (absPath) {
			Logger logger = Buckminster.getLogger();
			RepositoryUtil repoUtil = org.eclipse.egit.core.Activator.getDefault().getRepositoryUtil();
			if (repoUtil.addConfiguredRepository(repoDir))
				logger.info("Added Git repository at %s to the set of known repositories", absPath); //$NON-NLS-1$

			ConnectProviderOperation connectOp = new ConnectProviderOperation(project, repoDir);
			connectOp.execute(monitor);

			// Once connected, we should be able to get the provider for this
			// repository
			RepositoryProvider provider = RepositoryProvider.getProvider(project);
			if (provider == null)
				logger.warning("Failed to get team provider after connecting project %s to Git repository at %s", project.getName(), absPath); //$NON-NLS-1$
			else
				logger.info("Connected project %s to Git repository at %s", project.getName(), absPath); //$NON-NLS-1$
		}
	}

	@Override
	public IStatus tag(RepositoryProvider provider, IResource[] resources, String tag, boolean recurse, IProgressMonitor monitor)
			throws CoreException {
		// Not yet implemented.
		throw new UnsupportedOperationException();
	}
}
