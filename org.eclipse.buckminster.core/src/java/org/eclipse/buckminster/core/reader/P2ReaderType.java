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
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.P2Materializer;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.touchpoint.eclipse.PublisherUtil;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRequest;
import org.eclipse.equinox.p2.repository.artifact.IFileArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;

@SuppressWarnings("restriction")
public class P2ReaderType extends CatalogReaderType {
	public static final String SIMPLE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID + ".simpleRepository"; //$NON-NLS-1$

	public static IArtifactRepository getArtifactRepository(Provider provider, Map<String, ? extends Object> properties, IProgressMonitor monitor)
			throws CoreException {
		return getArtifactRepository(getURI(provider, properties), monitor);
	}

	public static IArtifactRepository getArtifactRepository(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		return getArtifactRepository(providerMatch.getProvider(), providerMatch.getNodeQuery().getProperties(), monitor);
	}

	public static IArtifactRepository getArtifactRepository(URI repoLocation, IProgressMonitor monitor) throws CoreException {
		IArtifactRepositoryManager manager = getArtifactRepositoryManager();
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try {
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		} catch (ProvisionException e) {
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}

	public static IArtifactRepositoryManager getArtifactRepositoryManager() throws CoreException {
		IArtifactRepositoryManager manager = (IArtifactRepositoryManager) CorePlugin.getDefault().getResolverAgent()
				.getService(IArtifactRepositoryManager.SERVICE_NAME);
		if (manager == null)
			throw new IllegalStateException("No artifact repository manager found"); //$NON-NLS-1$
		return manager;
	}

	public static IInstallableUnit getIU(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		IMetadataRepository mdr = getMetadataRepository(providerMatch, monitor);
		VersionMatch vm = providerMatch.getVersionMatch();
		IQueryResult<IInstallableUnit> result = mdr.query(QueryUtil.createIUQuery(vm.getArtifactInfo(), vm.getVersion()), monitor);
		return result.isEmpty() ? null : result.iterator().next();
	}

	public static IMetadataRepository getMetadataRepository(Provider provider, Map<String, ? extends Object> properties, IProgressMonitor monitor)
			throws CoreException {
		return getMetadataRepository(getURI(provider, properties), monitor);
	}

	public static IMetadataRepository getMetadataRepository(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		return getMetadataRepository(providerMatch.getProvider(), providerMatch.getNodeQuery().getProperties(), monitor);
	}

	public static IMetadataRepository getMetadataRepository(URI repoLocation, IProgressMonitor monitor) throws CoreException {
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager) CorePlugin.getDefault().getResolverAgent()
				.getService(IMetadataRepositoryManager.SERVICE_NAME);
		if (manager == null)
			throw new IllegalStateException("No artifact repository manager found"); //$NON-NLS-1$

		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try {
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		} catch (ProvisionException e) {
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}

	public static IFileArtifactRepository getTempAR(SubMonitor subMon) throws CoreException {
		File tempRepositoryFolder = CorePlugin.getDefault().getStateLocation().append("tempAR").toFile(); //$NON-NLS-1$
		IArtifactRepositoryManager manager = getArtifactRepositoryManager();
		URI tempRepositoryURI = tempRepositoryFolder.toURI();
		IFileArtifactRepository tempAr;
		try {
			tempAr = (IFileArtifactRepository) manager.loadRepository(tempRepositoryURI, subMon);
		} catch (ProvisionException e) {
			tempAr = (IFileArtifactRepository) manager.createRepository(tempRepositoryURI,
					"temporary artifacts" + " artifacts", SIMPLE_ARTIFACTS_TYPE, Collections.<String, String> emptyMap()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return tempAr;
	}

	public static URI getURI(Provider provider, Map<String, ? extends Object> properties) throws CoreException {
		return P2Materializer.cleanURIFromImportType(URLUtils.normalizeToURI(provider.getURI(properties), true));
	}

	public P2ReaderType() {
	}

	@Override
	public String convertFetchFactoryLocator(Map<String, String> fetchFactoryLocator, String componentName) throws CoreException {
		// This property is guaranteed to be set
		return fetchFactoryLocator.get("repository"); //$NON-NLS-1$
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRecommendedMaterializer() {
		return IMaterializer.P2;
	}

	public BOMNode getResolution(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		SubMonitor subMon = SubMonitor.convert(monitor, 20);
		try {
			IInstallableUnit iu = getIU(providerMatch, subMon.newChild(22));
			if (iu == null)
				throw new MissingComponentException(providerMatch.getNodeQuery().getComponentRequest().toString());

			MonitorUtils.testCancelStatus(subMon);
			if (Boolean.valueOf(iu.getProperty(IInstallableUnit.PROP_PARTIAL_IU)).booleanValue())
				iu = resolvePartialIU(iu, providerMatch, subMon.newChild(10));
			else
				subMon.worked(10);

			IMetadataRepository mdr = getMetadataRepository(providerMatch, subMon.newChild(2));
			return new ResolvedNode(providerMatch.getNodeQuery(), new Resolution(providerMatch.createResolution(new CSpecBuilder(providerMatch
					.getNodeQuery().getProperties(), mdr, iu), false)));
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		IMetadataRepository mdr = getMetadataRepository(provider, nodeQuery.getProperties(), monitor);
		return new P2VersionFinder(provider, ctype, nodeQuery, mdr);
	}

	IInstallableUnit resolvePartialIU(IInstallableUnit iu, ProviderMatch providerMatch, SubMonitor subMon) throws CoreException {
		Logger logger = CorePlugin.getLogger();
		String info = "Converting partial IU for " + iu.getId() + "..."; //$NON-NLS-1$//$NON-NLS-2$
		subMon.beginTask(info, 1110);
		logger.debug(info);
		IArtifactRepository sourceAr = getArtifactRepository(providerMatch, subMon.newChild(100));
		MonitorUtils.testCancelStatus(subMon);

		Collection<IArtifactKey> artifacts = iu.getArtifacts();
		IArtifactKey key = artifacts.iterator().next();
		IFileArtifactRepository tempAr = getTempAR(subMon.newChild(10));
		MonitorUtils.testCancelStatus(subMon);

		IArtifactRequest request = getArtifactRepositoryManager().createMirrorRequest(key, tempAr, null, null);
		request.perform(sourceAr, subMon.newChild(1000));
		IStatus result = request.getResult();
		switch (result.getSeverity()) {
			case IStatus.INFO:
				logger.info(result.getMessage());
			case IStatus.OK:
				// Unfortunately, this doesn't necessarily mean that everything
				// is OK. Zero sized files are
				// silently ignored. See bug 290986
				// We can't have that here.
				if (!tempAr.contains(key))
					throw BuckminsterException.fromMessage("Zero bytes copied while mirroring partial IU artifact %s", key); //$NON-NLS-1$
				break;
			case IStatus.CANCEL:
				throw new OperationCanceledException();
			default:
				if (result.getCode() == org.eclipse.equinox.p2.core.ProvisionException.ARTIFACT_EXISTS) {
					logger.debug("Artifact %s is already present", key); //$NON-NLS-1$
					break;
				}
				throw BuckminsterException.wrap(result);
		}

		File bundleFile = tempAr.getArtifactFile(key);
		if (bundleFile == null)
			throw BuckminsterException.fromMessage("Unable to resolve partial IU. Artifact file for %s could not be found", key); //$NON-NLS-1$

		IInstallableUnit preparedIU = PublisherUtil.createBundleIU(key, bundleFile);
		if (preparedIU == null)
			throw BuckminsterException.fromMessage("Unable to resolve partial IU. Artifact file for %s did not contain a bundle manifest", key); //$NON-NLS-1$
		return preparedIU;
	}
}
