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
import java.util.List;

import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;

@SuppressWarnings("restriction")
public class AllContributedCapabilitiesFeatureAction extends AbstractPublisherAction {
	private final Builder builder;

	private final IMetadataRepository mdr;

	private final IMetadataRepository globalMdr;

	public AllContributedCapabilitiesFeatureAction(Builder builder, IMetadataRepository globalMdr, IMetadataRepository mdr) {
		this.builder = builder;
		this.globalMdr = globalMdr;
		this.mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		Logger log = Buckminster.getLogger();
		Feature globalCapabilitiesFeature;
		try {
			globalCapabilitiesFeature = builder.getGlobalCapabilitiesFeature();
			if (globalCapabilitiesFeature == null)
				return Status.OK_STATUS;
		} catch (CoreException e) {
			return e.getStatus();
		}

		IInstallableUnit existingCapIU = Builder.getIU(globalMdr, globalCapabilitiesFeature.getId(), globalCapabilitiesFeature.getVersion());
		if (existingCapIU != null)
			log.info("Found existing capabilities feature: %s/%s, extending...", existingCapIU.getId(), existingCapIU.getVersion());
		else
			log.info("Creating new capabilities feature: %s/%s...", globalCapabilitiesFeature.getId(), globalCapabilitiesFeature.getVersion());

		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
		iu.setId(globalCapabilitiesFeature.getId() + Builder.FEATURE_GROUP_SUFFIX);
		iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(iu.getId(), iu.getVersion())));

		ArrayList<IRequiredCapability> required = new ArrayList<IRequiredCapability>();
		boolean didExtend = false;
		List<Contribution> contribs = builder.getBuild().getContributions();
		for (Contribution contrib : contribs) {
			for (Feature feature : contrib.getFeatures()) {
				if (feature == globalCapabilitiesFeature)
					continue;

				if (!Builder.isCapabilitiesFeature(contrib, feature))
					continue;

				String featureId = feature.getId() + Builder.FEATURE_GROUP_SUFFIX;
				IInstallableUnit featureIU = Builder.getIU(globalMdr, featureId, feature.getVersion());
				for (IRequiredCapability cap : featureIU.getRequiredCapabilities())
					if (IInstallableUnit.NAMESPACE_IU_ID.equals(cap.getNamespace()) || Builder.NAMESPACE_OSGI_BUNDLE.equals(cap.getNamespace())) {
						didExtend = true;
						log.info("Adding capabilities bundle: %s/%s", cap.getName(), cap.getRange());
						required.add(cap);
					}
			}
		}

		if (!didExtend) {
			if (existingCapIU == null) {
				// Remove the empty feature from the model
				outer: for (Contribution contrib : contribs) {
					List<Feature> features = contrib.getFeatures();
					for (Feature feature : features)
						if (feature == globalCapabilitiesFeature) {
							features.remove(feature);
							builder.setCapabilitiesContribution(null);
							break outer;
						}
				}
			}
			log.info("Done. Found no features that contains capabilities");
			return Status.OK_STATUS;
		}

		Version version = Version.parseVersion(globalCapabilitiesFeature.getVersion());
		if (existingCapIU == null)
			iu.setVersion(version);
		else {
			for (IRequiredCapability cap : existingCapIU.getRequiredCapabilities())
				required.add(cap);
			if (version.getQualifier() == null)
				version = Version.parseVersion(version.toString() + ".extended");
			else
				version = Version.parseVersion(version.toString() + "-extended");
			iu.setVersion(version);
			globalCapabilitiesFeature.setVersion(version.toString());
		}

		iu.addRequiredCapabilities(required);
		mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
		if (existingCapIU == null)
			log.info("Done.");
		else
			log.info("Done. Version of extended feature is %s", iu.getVersion());
		return Status.OK_STATUS;
	}
}
