/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.buckminster.aggregator.Configuration;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
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
import org.osgi.framework.InvalidSyntaxException;

/**
 * This action creates the feature that contains all features and bundles that are listed in the build contributions.
 * 
 * @see Builder#ALL_CONTRIBUTED_CONTENT_FEATURE
 */
public class VerificationFeatureAction extends AbstractPublisherAction
{
	private final Builder builder;

	private final IMetadataRepository mdr;

	public VerificationFeatureAction(Builder builder, IMetadataRepository mdr)
	{
		this.builder = builder;
		this.mdr = mdr;
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		InstallableUnitDescription iu = new MetadataFactory.InstallableUnitDescription();
		iu.setId(Builder.ALL_CONTRIBUTED_CONTENT_FEATURE);
		iu.setVersion(Builder.ALL_CONTRIBUTED_CONTENT_VERSION);
		iu.setProperty(IInstallableUnit.PROP_TYPE_GROUP, Boolean.TRUE.toString());
		iu.addProvidedCapabilities(Collections.singletonList(createSelfCapability(iu.getId(), iu.getVersion())));

		HashSet<IRequiredCapability> required = new HashSet<IRequiredCapability>();

		boolean errorsFound = false;
		List<Contribution> contribs = builder.getAggregator().getContributions();
		MonitorUtils.begin(monitor, 2 + contribs.size());
		try
		{
			for(Contribution contrib : builder.getAggregator().getContributions())
			{
				ArrayList<String> errors = new ArrayList<String>();
				for(MappedRepository repository : contrib.getRepositories())
				{
					if(repository.isMapEverything())
					{
						// Verify that all products and features can be installed.
						//
						for(InstallableUnit riu : repository.getMetadataRepository().getInstallableUnits())
						{
							// We assume that all groups that are not categories are either products or
							// features.
							//
							if("true".equalsIgnoreCase(riu.getProperty(IInstallableUnit.PROP_TYPE_GROUP))
									&& !"true".equalsIgnoreCase(riu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
								addRequirementFor(riu, null, required);
						}
					}
					else
					{
						for(MappedUnit mu : repository.getUnits(true))
						{
							// TODO: Create filter from configurations
							//
							Filter filter = null;
							List<Configuration> configs = mu.getValidConfigurations();
							if(!configs.isEmpty())
							{
								StringBuilder filterBld = new StringBuilder();
								if(configs.size() > 1)
									filterBld.append("(|");

								for(Configuration config : configs)
								{
									filterBld.append("(&(osgi.os=");
									filterBld.append(config.getOperatingSystem().getLiteral());
									filterBld.append(")(osgi.ws=");
									filterBld.append(config.getWindowSystem().getLiteral());
									filterBld.append(")(osgi.arch=");
									filterBld.append(config.getArchitecture().getLiteral());
									filterBld.append("))");
								}
								if(configs.size() > 1)
									filterBld.append(')');
								try
								{
									filter = FilterFactory.newInstance(filterBld.toString());
								}
								catch(InvalidSyntaxException e)
								{
									throw new RuntimeException(e);
								}
							}
							addRequirementFor(mu.getInstallableUnit(), filter, required);
						}
					}
				}
				if(errors.size() > 0)
				{
					errorsFound = true;
					builder.sendEmail(contrib, errors);
				}
				MonitorUtils.worked(monitor, 1);
			}
			if(errorsFound)
				return new Status(IStatus.ERROR, Engine.PLUGIN_ID, "Features without repositories");

			iu.addRequiredCapabilities(required);
			mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
			return Status.OK_STATUS;
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}

	private void addRequirementFor(InstallableUnit iu, Filter filter, Collection<IRequiredCapability> requirements)
	{
		String id = iu.getId();
		Version v = iu.getVersion();
		if(builder.discardAsUnverified(iu))
		{
			Buckminster.getLogger().debug("%s/%s excluded from verification", id, v);
			return;
		}
		VersionRange range = null;
		if(!Version.emptyVersion.equals(v))
			range = new VersionRange(v, true, v, true);

		Filter iuFilter = filter;
		String iuFilterStr = Trivial.trim(iu.getFilter());
		if(iuFilterStr != null)
		{
			if(filter != null)
				try
				{
					iuFilter = FilterFactory.newInstance(iuFilterStr).addFilterWithAnd(filter);
				}
				catch(InvalidSyntaxException e)
				{
				}
		}
		if(iuFilter != null)
			iuFilterStr = iuFilter.toString();
		requirements.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, id, range,
				iuFilterStr, false, false));
	}
}
