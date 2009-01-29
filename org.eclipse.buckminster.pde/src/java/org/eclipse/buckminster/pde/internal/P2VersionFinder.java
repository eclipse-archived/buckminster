package org.eclipse.buckminster.pde.internal;

import java.net.URI;
import java.util.Iterator;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.core.helpers.ServiceHelper;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;

@SuppressWarnings("restriction")
public class P2VersionFinder extends AbstractVersionFinder
{
	private static final String FEATURE_GROUP = ".feature.group"; //$NON-NLS-1$

	private static final String FEATURE_JAR = ".feature.jar"; //$NON-NLS-1$

	private static final String FEATURE_CLASSIFIER = "org.eclipse.update.feature"; //$NON-NLS-1$

	private static final String BUNDLE_CLASSIFIER = "osgi.bundle"; //$NON-NLS-1$

	public static IMetadataRepository getMetadataRepository(URI repoLocation, IProgressMonitor monitor)
			throws CoreException
	{
		IMetadataRepositoryManager manager = (IMetadataRepositoryManager)ServiceHelper.getService(PDEPlugin
				.getContext(), IMetadataRepositoryManager.class.getName());
		if(manager == null)
			throw new IllegalStateException("No metadata repository manager found"); //$NON-NLS-1$

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

	private final IMetadataRepository m_mdr;

	public P2VersionFinder(Provider provider, IComponentType componentType, NodeQuery query, IMetadataRepository mdr)
	{
		super(provider, componentType, query);
		m_mdr = mdr;
	}

	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = getQuery();
		ComponentRequest request = query.getComponentRequest();
		IComponentType ctype = request.getComponentType();
		String name = request.getName();
		VersionRange vr = null;
		boolean isFeature = ctype.getId().equals(IComponentType.ECLIPSE_FEATURE);

		if(isFeature)
		{
			if(!name.endsWith(FEATURE_GROUP))
				name += FEATURE_GROUP;
		}
		else if(!ctype.getId().equals(IComponentType.OSGI_BUNDLE))
			//
			// We only deal with features and bundles
			//
			return null;

		IVersionDesignator designator = request.getVersionDesignator();
		if(designator != null)
		{
			if(!designator.getVersion().getType().getId().equals(IVersionType.OSGI))
				//
				// We only deal with OSGi versions
				//
				return null;
			vr = new VersionRange(designator.toString());
		}

		Collector c = m_mdr.query(new InstallableUnitQuery(name, vr), new Collector(), monitor);
		if(c.isEmpty())
			return null;

		// Collector only contains matching versions. Use the highest one.
		//
		Iterator<?> itor = c.iterator();
		IInstallableUnit best = null;
		while(itor.hasNext())
		{
			IInstallableUnit iu = (IInstallableUnit)itor.next();
			if(best == null || best.getVersion().compareTo(iu.getVersion()) < 0)
				best = iu;
		}

		// Find the wanted artifact.
		//
		IArtifactKey[] artifacts = best.getArtifacts();
		IArtifactKey wanted = null;
		int idx = artifacts.length;
		while(--idx >= 0)
		{
			IArtifactKey ak = artifacts[idx];
			if(isFeature)
			{
				if(ak.getClassifier().equals(FEATURE_CLASSIFIER))
				{
					wanted = ak;
					break;
				}
			}
			else
			{
				if(ak.getClassifier().equals(BUNDLE_CLASSIFIER))
				{
					wanted = ak;
					break;
				}
			}
		}

		if(wanted == null)
		{
			if(!isFeature)
				return null;

			// Check if the <feature name>.feature.jar requirement is present
			//
			String featureJarName = name.substring(0, name.length() - FEATURE_GROUP.length()) + FEATURE_JAR;
			IRequiredCapability found = null;
			for(IRequiredCapability rqc : best.getRequiredCapabilities())
				if(IInstallableUnit.NAMESPACE_IU_ID.equals(rqc.getNamespace()) && featureJarName.equals(rqc.getName()))
				{
					found = rqc;
					break;
				}

			if(found == null)
				return null;

			c = new Collector();
			m_mdr.query(new InstallableUnitQuery(found.getName(), found.getRange()), c, monitor);
			if(c.isEmpty())
				return null;

			itor = c.iterator();
			IInstallableUnit bestJar = null;
			while(itor.hasNext())
			{
				IInstallableUnit iu = (IInstallableUnit)itor.next();
				if(bestJar == null || bestJar.getVersion().compareTo(iu.getVersion()) < 0)
					bestJar = iu;
			}

			// Find the wanted artifact.
			//
			artifacts = bestJar.getArtifacts();
			idx = artifacts.length;
			while(--idx >= 0)
			{
				IArtifactKey ak = artifacts[idx];
				if(ak.getClassifier().equals(FEATURE_CLASSIFIER))
				{
					wanted = ak;
					break;
				}
			}
			if(wanted == null)
				return null;
		}

		return new VersionMatch(VersionFactory.OSGiType.fromString(best.getVersion().toString()), null, -1, null,
				wanted.toString());
	}
}
