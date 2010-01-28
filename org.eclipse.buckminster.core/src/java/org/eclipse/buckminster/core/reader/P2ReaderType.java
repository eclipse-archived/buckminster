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

import java.net.URI;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.expression.ExpressionUtil;
import org.eclipse.equinox.p2.metadata.expression.IExpression;
import org.eclipse.equinox.p2.metadata.query.ExpressionQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;

public class P2ReaderType extends CatalogReaderType
{
	private static final IExpression iuQuery = ExpressionUtil.parse("id == $0 && version == $1"); //$NON-NLS-1$

	public static IArtifactRepository getArtifactRepository(Provider provider,
			Map<String, ? extends Object> properties, IProgressMonitor monitor) throws CoreException
	{
		String url = provider.getURI(properties);
		URI uri = URLUtils.normalizeToURI(url, true);
		return getArtifactRepository(uri, monitor);
	}

	public static IArtifactRepository getArtifactRepository(ProviderMatch providerMatch, IProgressMonitor monitor)
			throws CoreException
	{
		return getArtifactRepository(providerMatch.getProvider(), providerMatch.getNodeQuery().getProperties(), monitor);
	}

	public static IArtifactRepository getArtifactRepository(URI repoLocation, IProgressMonitor monitor)
			throws CoreException
	{
		IArtifactRepositoryManager manager = (IArtifactRepositoryManager)CorePlugin.getDefault().getResolverAgent().getService(
				IArtifactRepositoryManager.SERVICE_NAME);
		if(manager == null)
			throw new IllegalStateException("No artifact repository manager found"); //$NON-NLS-1$

		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
		finally
		{
			if(monitor != null)
				monitor.done();
		}
	}

	public static IInstallableUnit getIU(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		IMetadataRepository mdr = getMetadataRepository(providerMatch, monitor);
		VersionMatch vm = providerMatch.getVersionMatch();
		IQueryResult<IInstallableUnit> result = mdr.query(new ExpressionQuery<IInstallableUnit>(IInstallableUnit.class,
				iuQuery, vm.getArtifactInfo(), vm.getVersion()), monitor);
		return result.isEmpty()
				? null
				: result.iterator().next();
	}

	public static IMetadataRepository getMetadataRepository(Provider provider,
			Map<String, ? extends Object> properties, IProgressMonitor monitor) throws CoreException
	{
		String url = provider.getURI(properties);
		URI uri = URLUtils.normalizeToURI(url, true);
		return getMetadataRepository(uri, monitor);
	}

	public static IMetadataRepository getMetadataRepository(ProviderMatch providerMatch, IProgressMonitor monitor)
			throws CoreException
	{
		return getMetadataRepository(providerMatch.getProvider(), providerMatch.getNodeQuery().getProperties(), monitor);
	}

	public static IMetadataRepository getMetadataRepository(URI repoLocation, IProgressMonitor monitor)
			throws CoreException
	{
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager)CorePlugin.getDefault().getResolverAgent().getService(
				IMetadataRepositoryManager.SERVICE_NAME);
		if(manager == null)
			throw new IllegalStateException("No artifact repository manager found"); //$NON-NLS-1$

		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
		finally
		{
			if(monitor != null)
				monitor.done();
		}
	}

	public P2ReaderType()
	{
	}

	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRecommendedMaterializer()
	{
		return IMaterializer.P2;
	}

	public BOMNode getResolution(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		SubMonitor subMon = SubMonitor.convert(monitor, 20);
		IInstallableUnit iu = getIU(providerMatch, subMon.newChild(10));
		IMetadataRepository mdr = getMetadataRepository(providerMatch, subMon.newChild(10));
		return new ResolvedNode(providerMatch.getNodeQuery(), new Resolution(providerMatch.createResolution(
				new CSpecBuilder(mdr, iu), false)));
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		IMetadataRepository mdr = getMetadataRepository(provider, nodeQuery.getProperties(), monitor);
		return new P2VersionFinder(provider, ctype, nodeQuery, mdr);
	}
}
