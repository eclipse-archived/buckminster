/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.galileo.builder;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.buckminster.galileo.builder.BuildModel.Contribution;
import org.eclipse.buckminster.galileo.builder.BuildModel.Feature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;

@SuppressWarnings("restriction")
public class GalileoFeatureAction extends AbstractPublisherAction
{
	private final BuildModel m_buildModel;

	private final IMetadataRepository m_mdr;

	public GalileoFeatureAction(BuildModel buildModel, IMetadataRepository mdr)
	{
		m_buildModel = buildModel;
		m_mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		try
		{
			Feature galileoFeature = null;
			for(Contribution contrib : m_buildModel.getContributions())
				for(Feature feature : contrib.getFeatures())
					if("org.eclipse.galileo".equals(feature.getId())) //$NON-NLS-1$
					{
						galileoFeature = feature;
						break;
					}

			if(galileoFeature == null)
				// Not found, so do nothing
				return Status.OK_STATUS;

			InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
			String id = galileoFeature.getId();
			String featureGroupId = id + Activator.FEATURE_GROUP_SUFFIX;
			Version featureVersion = galileoFeature.getVersion();
			iu.setId(featureGroupId);
			iu.setVersion(featureVersion);
			iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
			iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(featureGroupId, featureVersion)));

			ArrayList<IRequiredCapability> required = new ArrayList<IRequiredCapability>();
			for(Contribution contrib : m_buildModel.getContributions())
				for(Feature feature : contrib.getFeatures())
				{
					String requiredId = feature.getId();
					if(requiredId.equals(galileoFeature.getId()))
						continue;

					requiredId += Activator.FEATURE_GROUP_SUFFIX;
					Version v = feature.getVersion();
					VersionRange range = null;
					if(!Version.emptyVersion.equals(v))
						range = new VersionRange(v, true, v, true);
					required.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, requiredId,
							range, null, false, false));
				}
			iu.addRequiredCapabilities(required);
			m_mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
			return Status.OK_STATUS;
		}
		catch(CoreException e)
		{
			return e.getStatus();
		}
	}
}
