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

import org.eclipse.amalgam.releng.build.Bundle;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.amalgam.releng.build.Product;
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
 * This action creates the feature that contains all features and bundles that
 * are listed in the build contributions.
 * 
 * @see Builder#ALL_CONTRIBUTED_CONTENT_FEATURE
 */
@SuppressWarnings("restriction")
public class AllContributedContentAction extends AbstractPublisherAction {
	private final Builder builder;

	private final IMetadataRepository mdr;

	private final IMetadataRepository globalMdr;

	public AllContributedContentAction(Builder builder, IMetadataRepository globalMdr, IMetadataRepository mdr) {
		this.builder = builder;
		this.globalMdr = globalMdr;
		this.mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		Logger log = Buckminster.getLogger();
		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		iu.setId(Builder.ALL_CONTRIBUTED_CONTENT_FEATURE);
		iu.setVersion(Builder.ALL_CONTRIBUTED_CONTENT_VERSION);
		iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
		iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(iu.getId(), iu.getVersion())));

		Feature brandingFeature = builder.getBrandingFeature();

		boolean skipBrandingFeature = false;
		ArrayList<IRequiredCapability> required = new ArrayList<IRequiredCapability>();
		if (brandingFeature != null && builder.isBrandingBuild()) {
			// Did we extend this one? If we did, we have a new copy in the
			// non-global mdr that should be used instead of the global one
			IInstallableUnit gcapIU = Builder.getIU(mdr, brandingFeature.getId() + Builder.FEATURE_GROUP_SUFFIX, brandingFeature.getVersion());
			if (gcapIU != null) {
				Version v = gcapIU.getVersion();
				VersionRange range = null;
				if (!Version.emptyVersion.equals(v))
					range = new VersionRange(v, true, v, true);
				required.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, gcapIU.getId(), range, null, false, false));
				skipBrandingFeature = true;
			}
		}

		boolean errorsFound = false;
		for (Contribution contrib : builder.getBuild().getContributions()) {
			ArrayList<String> errors = new ArrayList<String>();
			for (Feature feature : contrib.getFeatures()) {
				String requiredId = feature.getId();
				if (builder.skipFeature(feature, errors))
					continue;

				if (brandingFeature != null && brandingFeature.getId().equals(feature.getId())) {
					if (skipBrandingFeature || feature.getRepo() == null)
						continue;
				}

				requiredId += Builder.FEATURE_GROUP_SUFFIX;
				IInstallableUnit featureIU = Builder.getIU(globalMdr, requiredId, feature.getVersion());
				if (builder.discardAsUnverified(featureIU)) {
					log.debug("Feature %s/%s excluded from verification", requiredId, feature.getVersion());
					continue;
				}

				Version v = featureIU.getVersion();
				VersionRange range = null;
				if (!Version.emptyVersion.equals(v))
					range = new VersionRange(v, true, v, true);
				String filter = featureIU.getFilter();
				required.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, requiredId, range, filter, false, false));
			}
			for (Bundle bundle : contrib.getBundles()) {
				IInstallableUnit bundleIU = Builder.getIU(globalMdr, bundle.getId(), bundle.getVersion());
				if (builder.discardAsUnverified(bundleIU)) {

					continue;
				}
				Version v = bundleIU.getVersion();
				VersionRange range = new VersionRange(v, true, v, true);
				String filter = bundleIU.getFilter();
				required.add(MetadataFactory.createRequiredCapability(Builder.NAMESPACE_OSGI_BUNDLE, bundleIU.getId(), range, filter, false, false));
			}
			for (Product product : contrib.getProducts()) {
				IInstallableUnit productIU = Builder.getIU(globalMdr, product.getId(), product.getVersion());
				Version v = productIU.getVersion();
				VersionRange range = new VersionRange(v, true, v, true);
				String filter = productIU.getFilter();
				required.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, productIU.getId(), range, filter, false,
						false));
			}
			if (errors.size() > 0) {
				errorsFound = true;
				builder.sendEmail(contrib, errors);
			}
		}
		if (errorsFound)
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Features without repositories");

		iu.addRequiredCapabilities(required);
		mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
		return Status.OK_STATUS;
	}
}
