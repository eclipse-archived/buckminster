/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.galileo.builder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.Bundle;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
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

/**
 * This action creates the feature that contains all capabilities bundles. The
 * bundles can be contributed 'as is', or in a feature. Bundles with an id that
 * ends with then name <i>.capabilities</i> and features with an id ending with
 * either <i>.capabilities</i> or <i>.capabilities.feature</i> are considered.
 */
@SuppressWarnings("restriction")
public class AllContributedCapabilitiesAction extends AbstractPublisherAction {

	@SuppressWarnings("unchecked")
	private static Map<String, String> getIUProperties(IInstallableUnit iu) {
		return iu.getProperties();
	}

	private final Builder builder;

	private final IMetadataRepository mdr;

	private final IMetadataRepository globalMdr;

	public AllContributedCapabilitiesAction(Builder builder, IMetadataRepository globalMdr, IMetadataRepository mdr) {
		this.builder = builder;
		this.globalMdr = globalMdr;
		this.mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		Logger log = Buckminster.getLogger();
		Feature brandingFeature = builder.getBrandingFeature();
		if (brandingFeature == null || !builder.isBrandingBuild())
			return Status.OK_STATUS;

		Build build = builder.getBuild();
		IInstallableUnit existingCapIU = Builder.getIU(globalMdr, brandingFeature.getId() + Builder.FEATURE_GROUP_SUFFIX, brandingFeature
				.getVersion());
		if (existingCapIU != null)
			log.info("Found existing capabilities feature: %s/%s, extending...", existingCapIU.getId(), existingCapIU.getVersion());
		else
			log.info("Creating new capabilities feature: %s/%s...", brandingFeature.getId(), brandingFeature.getVersion());

		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
		iu.setId(brandingFeature.getId() + Builder.FEATURE_GROUP_SUFFIX);

		ArrayList<IRequiredCapability> required = new ArrayList<IRequiredCapability>();
		boolean didExtend = false;
		List<Contribution> contribs = build.getContributions();
		for (Contribution contrib : contribs) {
			for (Feature feature : contrib.getFeatures()) {
				if (feature == brandingFeature || !Builder.isCapabilitiesFeature(feature))
					continue;

				String featureId = feature.getId() + Builder.FEATURE_GROUP_SUFFIX;
				IInstallableUnit featureIU = Builder.getIU(globalMdr, featureId, feature.getVersion());
				for (IRequiredCapability cap : featureIU.getRequiredCapabilities())
					if (IInstallableUnit.NAMESPACE_IU_ID.equals(cap.getNamespace()) && !cap.getName().endsWith(".feature.jar")) {
						didExtend = true;
						log.info("Adding capabilities bundle: %s/%s", cap.getName(), cap.getRange());
						required.add(cap);
					}
			}
			for (Bundle bundle : contrib.getBundles()) {
				if (!Builder.isCapabilitiesBundle(bundle))
					continue;
				IInstallableUnit bundleIU = Builder.getIU(globalMdr, bundle.getId(), bundle.getVersion());
				Version v = bundleIU.getVersion();
				VersionRange vr = new VersionRange(v, true, v, true);
				IRequiredCapability cap = MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, bundleIU.getId(), vr, bundleIU
						.getFilter(), true, false);
				didExtend = true;
				log.info("Adding capabilities bundle: %s/%s", cap.getName(), cap.getRange());
				required.add(cap);
			}
		}

		if (!didExtend) {
			if (existingCapIU == null) {
				// Remove the empty feature from the model
				outer: for (Contribution contrib : contribs) {
					List<Feature> features = contrib.getFeatures();
					for (Feature feature : features)
						if (feature == brandingFeature) {
							features.remove(feature);
							builder.setBrandingContribution(null);
							break outer;
						}
				}
			}
			log.info("Done. Found no contributed capabilities");
			return Status.OK_STATUS;
		}

		Version version = Version.parseVersion(brandingFeature.getVersion());
		String timestamp = build.getDate() + '-' + build.getTime();
		if (existingCapIU == null) {
			iu.setProperty(IInstallableUnit.PROP_NAME, build.getLabel() + " Capability Definitions");
			if ("qualifier".equals(version.getQualifier())) {
				version = Version.createOSGi(version.getMajor(), version.getMinor(), version.getMicro(), timestamp);
			}
		} else {
			for (IRequiredCapability cap : existingCapIU.getRequiredCapabilities())
				required.add(cap);

			for (Map.Entry<String, String> entry : getIUProperties(existingCapIU).entrySet())
				iu.setProperty(entry.getKey(), entry.getValue());
			iu.setLicense(existingCapIU.getLicense());
			iu.setCopyright(existingCapIU.getCopyright());
			String qualifier = version.getQualifier();
			if (qualifier == null || "qualifier".equals(qualifier))
				qualifier = timestamp;
			else {
				try {
					// An existing timestamp is replaced
					// by a new timestamp.
					if (qualifier.startsWith("v2")) {
						Builder.TIMESTAMP_FORMAT.parse(qualifier.substring(1));
						qualifier = 'v' + timestamp;
					} else {
						Builder.TIMESTAMP_FORMAT.parse(qualifier);
						qualifier = timestamp;
					}
				} catch (ParseException e) {
					qualifier += '-' + timestamp;
				}
			}
			version = Version.createOSGi(version.getMajor(), version.getMinor(), version.getMicro(), qualifier);
		}
		brandingFeature.setVersion(version.toString());
		iu.setVersion(version);
		iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(iu.getId(), iu.getVersion())));
		iu.addRequiredCapabilities(required);

		mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
		if (existingCapIU == null)
			log.info("Done creating capabilites feature. Version is %s", version);
		else
			log.info("Done extending capabilities feature. Version is %s", version);
		return Status.OK_STATUS;
	}
}
