package org.eclipse.buckminster.pde.internal;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.metadata.RequiredCapability;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.expression.IMatchExpression;
import org.eclipse.equinox.p2.metadata.query.ExpressionQuery;
import org.eclipse.equinox.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;

@SuppressWarnings("restriction")
public class P2VersionFinder extends AbstractVersionFinder
{
	private static final String FEATURE_JAR = ".feature.jar"; //$NON-NLS-1$

	private static final String FEATURE_CLASSIFIER = "org.eclipse.update.feature"; //$NON-NLS-1$

	private static final String BUNDLE_CLASSIFIER = "osgi.bundle"; //$NON-NLS-1$

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
		String ctypeId = (ctype == null)
				? IComponentType.OSGI_BUNDLE
				: ctype.getId();
		String name = request.getName();
		VersionRange vr = null;
		boolean isFeature = ctypeId.equals(IComponentType.ECLIPSE_FEATURE);

		if(isFeature)
		{
			if(!name.endsWith(IPDEConstants.FEATURE_GROUP))
				name += IPDEConstants.FEATURE_GROUP;
		}
		else if(!ctypeId.equals(IComponentType.OSGI_BUNDLE))
			//
			// We only deal with features and bundles
			//
			return null;

		VersionRange designator = request.getVersionRange();
		if(designator != null)
		{
			if(!designator.getMinimum().isOSGiCompatible())
				//
				// We only deal with OSGi versions
				//
				return null;
			vr = new VersionRange(designator.toString());
		}

		IQueryResult<IInstallableUnit> c = m_mdr.query(new InstallableUnitQuery(name, vr), monitor);
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
		Collection<IArtifactKey> artifacts = best.getArtifacts();
		IArtifactKey wanted = null;
		for(IArtifactKey ak : artifacts)
		{
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
			String featureJarName = name.substring(0, name.length() - IPDEConstants.FEATURE_GROUP.length())
					+ FEATURE_JAR;
			IRequirement found = null;
			for(IRequirement rqc : best.getRequiredCapabilities())
			{
				IMatchExpression<IInstallableUnit> matches = rqc.getMatches();
				if(IInstallableUnit.NAMESPACE_IU_ID.equals(RequiredCapability.extractNamespace(matches))
						&& featureJarName.equals(RequiredCapability.extractName(matches)))
				{
					found = rqc;
					break;
				}
			}

			if(found == null)
				return null;

			c = m_mdr.query(new ExpressionQuery<IInstallableUnit>(IInstallableUnit.class, found.getMatches()), monitor);
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
			for(IArtifactKey ak : artifacts)
			{
				if(ak.getClassifier().equals(FEATURE_CLASSIFIER))
				{
					wanted = ak;
					break;
				}
			}
			if(wanted == null)
				return null;
		}

		return new VersionMatch(best.getVersion(), null, -1, null, wanted.toString());
	}
}
