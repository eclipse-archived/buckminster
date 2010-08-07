/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ProviderScore;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.core.RepositoryProvider;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractReaderType extends AbstractExtension implements IReaderType {
	protected class DefaultVersionFinder extends AbstractVersionFinder {
		private final VersionMatch versionMatch;

		DefaultVersionFinder(Provider provider, IComponentType ctype, NodeQuery query) {
			super(provider, ctype, query);
			versionMatch = new VersionMatch(null, null, -1, null, null);
		}

		@Override
		public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException {
			MonitorUtils.complete(monitor);
			return versionMatch;
		}
	}

	public static IReaderType getTypeForRepositoryProvider(String providerId) throws CoreException {
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		for (IConfigurationElement elem : exReg.getConfigurationElementsFor(CorePlugin.READER_TYPE_POINT)) {
			if (providerId.equals(elem.getAttribute("teamRepositoryId"))) //$NON-NLS-1$
				return CorePlugin.getDefault().getReaderType(elem.getAttribute("id")); //$NON-NLS-1$
		}
		return null;
	}

	public static IReaderType getTypeForResource(IResource resource) throws CoreException {
		if (resource == null)
			return null;

		IProject project = resource.getProject();
		if (project == null)
			return null;

		RepositoryProvider provider = RepositoryProvider.getProvider(project);
		if (provider == null)
			return null;

		return getTypeForRepositoryProvider(provider.getID());
	}

	@Override
	public void addMaterializationNode(MaterializationSpecBuilder bld, Resolution res) throws CoreException {
	}

	@Override
	public String convertFetchFactoryLocator(Map<String, String> fetchFactoryLocator, String componentName) throws CoreException {
		throw new UnsupportedOperationException(NLS.bind(Messages.ReaderType_0_cannot_handle_fetchFactory_data, getId()));
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException {
		return null;
	}

	@Override
	public ReferenceInfo extractReferenceInfo(String reference) throws CoreException {
		throw new UnsupportedOperationException();
	}

	public VersionMatch getDefaultVersion() throws CoreException {
		return VersionMatch.DEFAULT;
	}

	@Override
	public IPath getFixedLocation(Resolution cr) throws CoreException {
		return null;
	}

	@Override
	public IPath getInstallLocation(Resolution resolution, MaterializationContext context) throws CoreException {
		return null;
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	@Override
	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException {
		return -1;
	}

	@Override
	public long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException {
		return -1;
	}

	@Override
	public IReaderType getLocalReaderType(boolean destIsFile) throws CoreException {
		return CorePlugin.getDefault().getReaderType(destIsFile ? URL : URL_CATALOG);
	}

	@Override
	public IComponentReader getReader(Provider provider, IComponentType ctype, NodeQuery query, VersionMatch versionMatch, IProgressMonitor monitor)
			throws CoreException {
		return getReader(new ProviderMatch(provider, ctype, versionMatch, ProviderScore.FAIR, query), monitor);
	}

	@Override
	public IComponentReader getReader(Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
		return getReader(cr.getProviderMatch(context), monitor);
	}

	@Override
	public String getRecommendedMaterializer() {
		return IMaterializer.WORKSPACE;
	}

	@Override
	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException {
		return null;
	}

	@Override
	public String getRemotePath(String repositoryLocation) throws CoreException {
		return null;
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery query, IProgressMonitor monitor) throws CoreException {
		return new DefaultVersionFinder(provider, ctype, query);
	}

	@Override
	public void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException {
	}

	@Override
	public void prepareMaterialization(List<Materialization> mtr, MaterializationContext context, IProgressMonitor monitor) throws CoreException {
	}

	@Override
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException {
	}
}
