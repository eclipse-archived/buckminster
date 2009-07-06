/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
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
 * This action creates the feature that contains all features and bundles that are listed in the build contributions.
 * 
 * @see Builder#ALL_CONTRIBUTED_CONTENT_FEATURE
 */
public class AllContributedContentAction extends AbstractPublisherAction
{
	private final Builder builder;

	private final IMetadataRepository mdr;

	public AllContributedContentAction(Builder builder, IMetadataRepository mdr)
	{
		this.builder = builder;
		this.mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		Logger log = Buckminster.getLogger();
		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		iu.setId(Builder.ALL_CONTRIBUTED_CONTENT_FEATURE);
		iu.setVersion(Builder.ALL_CONTRIBUTED_CONTENT_VERSION);
		iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
		iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(iu.getId(), iu.getVersion())));

		ArrayList<IRequiredCapability> required = new ArrayList<IRequiredCapability>();

		boolean errorsFound = false;
		for(Contribution contrib : builder.getAggregator().getContributions())
		{
			ArrayList<String> errors = new ArrayList<String>();
			for(MappedRepository repository : contrib.getRepositories())
			{
				if(repository.isMapEverything())
					continue;

				for(MappedUnit mu : repository.getUnits(true))
				{
					InstallableUnit muIU = mu.getInstallableUnit();
					String id = muIU.getId();
					Version v = muIU.getVersion();
					if(builder.discardAsUnverified(muIU))
					{
						log.debug("%s/%s excluded from verification", id, v);
						continue;
					}
					VersionRange range = null;
					if(!Version.emptyVersion.equals(v))
						range = new VersionRange(v, true, v, true);
					required.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, id, range,
							null, false, false));
				}
			}
			if(errors.size() > 0)
			{
				errorsFound = true;
				builder.sendEmail(contrib, errors);
			}
		}
		if(errorsFound)
			return new Status(IStatus.ERROR, Engine.PLUGIN_ID, "Features without repositories");

		iu.addRequiredCapabilities(required);
		mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
		return Status.OK_STATUS;
	}
}
