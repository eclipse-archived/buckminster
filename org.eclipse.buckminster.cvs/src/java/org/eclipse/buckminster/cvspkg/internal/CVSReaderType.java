/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.ReferenceInfo;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.cvspkg.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.internal.ccvs.core.CVSException;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSTag;
import org.eclipse.team.internal.ccvs.core.CVSTeamProvider;
import org.eclipse.team.internal.ccvs.core.ICVSRemoteResource;
import org.eclipse.team.internal.ccvs.core.ICVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.client.Command;
import org.eclipse.team.internal.ccvs.core.connection.CVSRepositoryLocation;
import org.eclipse.team.internal.ccvs.core.resources.CVSWorkspaceRoot;
import org.eclipse.team.internal.ccvs.core.syncinfo.ResourceSyncInfo;
import org.eclipse.team.internal.ccvs.core.util.KnownRepositories;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CVSReaderType extends CatalogReaderType {
	// The constructors of Command.LocalOption are not public
	//
	static class MyLocalOption extends Command.LocalOption {
		MyLocalOption(String opt) {
			super(opt);
		}
	}

	public static final String LOCAL_LINE_END = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final Command.LocalOption STDOUT = new MyLocalOption("-p"); //$NON-NLS-1$

	public static CVSRepositoryLocation getLocationFromString(String repo) throws CVSException {
		CVSRepositoryLocation wanted = CVSRepositoryLocation.fromString(repo);
		String wantedUser = wanted.getUsername();
		for (ICVSRepositoryLocation known : CVSProviderPlugin.getPlugin().getKnownRepositories()) {
			if (known.getHost().equals(wanted.getHost()) && known.getPort() == wanted.getPort()
					&& known.getRootDirectory().equals(wanted.getRootDirectory())) {
				String knownMethod = known.getMethod().getName();
				String wantedMethod = wanted.getMethod().getName();
				if (knownMethod.equals(wantedMethod) || ("extssh".equals(knownMethod) && "pserver".equals(wantedMethod))) //$NON-NLS-1$ //$NON-NLS-2$
				{
					if (wantedUser == null || "anonymous".equals(wantedUser) || wantedUser.equals(known.getUsername())) //$NON-NLS-1$
						return (CVSRepositoryLocation) known;
				}
			}
		}
		KnownRepositories.getInstance().addRepository(wanted, true);
		return wanted;
	}

	static CVSTag getCVSTag(VersionMatch match) throws CoreException {
		CVSTag tag;
		VersionSelector selector = match.getBranchOrTag();
		Date timestamp;
		if (selector == null && (timestamp = match.getTimestamp()) != null)
			tag = new CVSTag(timestamp);
		else
			tag = getCVSTag(selector);
		return tag;
	}

	static CVSTag getCVSTag(VersionSelector selector) throws CoreException {
		CVSTag tag;
		if (selector == null)
			tag = CVSTag.DEFAULT;
		else
			tag = new CVSTag(selector.getName(), selector.getType() == VersionSelector.TAG ? CVSTag.VERSION : CVSTag.BRANCH);
		return tag;
	}

	@Override
	public String convertFetchFactoryLocator(Map<String, String> fetchFactoryLocator, String componentName) throws CoreException {
		String cvsRoot = fetchFactoryLocator.get("cvsRoot"); //$NON-NLS-1$
		if (cvsRoot == null)
			throw BuckminsterException.fromMessage(Messages.illegal_fetch_factory_locator);

		StringBuilder locator = new StringBuilder(cvsRoot);
		String path = fetchFactoryLocator.get("path"); //$NON-NLS-1$
		locator.append(',');
		if (path != null)
			locator.append(path);
		else
			locator.append(componentName);
		return locator.toString();
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionMatch) throws CoreException {
		try {
			VersionSelector versionSelector = versionMatch.getBranchOrTag();
			CVSSession session = new CVSSession(repositoryLocator);
			ICVSRepositoryLocation location = session.getLocation();

			StringBuilder query = new StringBuilder();
			String method = location.getMethod().getName();
			if (!method.equals("pserver")) //$NON-NLS-1$
			{
				query.append("method="); //$NON-NLS-1$
				query.append(method);
			}
			if (versionSelector != null) {
				if (query.length() > 0)
					query.append('&');
				query.append("version"); //$NON-NLS-1$
				query.append(versionSelector.toString());
			}

			String user = location.getUsername();
			if ("anonymous".equals(user)) //$NON-NLS-1$
				user = null;

			IPath modulePath = new Path(session.getModuleName()).makeAbsolute();
			URI uri = new URI("cvs", user, location.getHost(), -1, location.getRootDirectory(), query.toString(), //$NON-NLS-1$
					modulePath.toPortableString());
			return uri.toURL();
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public ReferenceInfo extractReferenceInfo(String reference) throws CoreException {
		StringTokenizer tokenizer = new StringTokenizer(reference, ","); //$NON-NLS-1$
		String version = tokenizer.nextToken();
		// If this is a newer version, then ignore it
		if (!version.equals("1.0")) //$NON-NLS-1$
			throw BuckminsterException.fromMessage("The cvs reader only understands version PSF project references of version 1.0"); //$NON-NLS-1$

		String repositoryLocation = tokenizer.nextToken();
		String module = tokenizer.nextToken();
		String projectName = tokenizer.nextToken();
		VersionSelector selector = null;
		if (tokenizer.hasMoreElements()) {
			String branchInfo = tokenizer.nextToken();
			if (!(branchInfo.length() == 0 || CVSTag.DEFAULT.toString().equals(branchInfo)))
				selector = VersionSelector.branch(branchInfo);
		}
		return new ReferenceInfo(repositoryLocation + ',' + module, selector, projectName);
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		return null;
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 30);
		File[] folders = workingCopy.listFiles();
		MonitorUtils.worked(monitor, 10);
		if (folders == null) {
			// Folder was not a folder after all
			//
			monitor.done();
			return null;
		}

		Date youngest = null;
		if (folders.length > 0) {
			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 10);
			subMon.beginTask(null, folders.length * 10);
			for (File subFolder : folders) {
				Date ts = getLastModification(subFolder, MonitorUtils.subMonitor(subMon, 10));
				if (ts != null && (youngest == null || ts.compareTo(youngest) > 0))
					youngest = ts;
			}
			subMon.done();
		} else
			MonitorUtils.worked(monitor, 10);

		File entries = new File(new File(workingCopy, FileSystemCopier.CVS_DIRNAME), FileSystemCopier.ENTRIES);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(entries));
			String line;
			while ((line = input.readLine()) != null) {
				try {
					ResourceSyncInfo info = new ResourceSyncInfo(line, null);
					Date ts = info.getTimeStamp();
					if (ts != null && (youngest == null || ts.compareTo(youngest) > 0))
						youngest = ts;
				} catch (CVSException e) {
				}
			}
			MonitorUtils.worked(monitor, 10);
		} catch (FileNotFoundException e) {
			// No Entries file present in this folder
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
			monitor.done();
		}
		return youngest;
	}

	@Override
	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		CVSSession session = null;
		try {
			session = new CVSSession(repositoryLocation);
			RepositoryMetaData metaData = RepositoryMetaData.getMetaData(session, getCVSTag(versionSelector), monitor);
			return metaData.getLastModification();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new CVSReader(this, providerMatch);
	}

	@Override
	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath location = Path.fromOSString(workingCopy.toString());
		IResource resource = wsRoot.getContainerForLocation(location);
		if (resource == null)
			resource = wsRoot.getFileForLocation(location);

		if (resource == null)
			//
			// We only support workspace resources at this time
			//
			return null;

		ICVSRemoteResource cvsResource = CVSWorkspaceRoot.getRemoteResourceFor(resource);
		if (cvsResource == null)
			return null;

		return cvsResource.getRepository().getLocation(false) + ',' + cvsResource.getRepositoryRelativePath();
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
		// Register the project with the CVSTeamProvider.
		//
		String cvsTypeID = CVSProviderPlugin.getTypeId();
		RepositoryProvider.map(project, cvsTypeID);
		((CVSTeamProvider) RepositoryProvider.getProvider(project, cvsTypeID))
				.setWatchEditEnabled(CVSProviderPlugin.getPlugin().isWatchEditEnabled());
	}
}
