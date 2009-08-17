/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.Configuration;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
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
	static class Requirement
	{
		MappedRepository repository;

		IRequiredCapability capability;
	}

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

		Map<String, Requirement> required = new HashMap<String, Requirement>();

		boolean errorsFound = false;
		List<Contribution> contribs = builder.getAggregator().getContributions();
		SubMonitor subMon = SubMonitor.convert(monitor, 2 + contribs.size());
		try
		{
			Set<String> explicit = new HashSet<String>();
			for(Contribution contrib : builder.getAggregator().getContributions(true))
			{
				ArrayList<String> errors = new ArrayList<String>();
				for(MappedRepository repository : contrib.getRepositories(true))
				{
					List<InstallableUnit> allIUs = repository.getMetadataRepository().getInstallableUnits();
					if(repository.isMapEverything())
					{
						// Verify that all products and features can be installed.
						//
						for(InstallableUnit riu : allIUs)
						{
							// We assume that all groups that are not categories are either products or
							// features.
							//
							if("true".equalsIgnoreCase(riu.getProperty(IInstallableUnit.PROP_TYPE_GROUP))
									&& !"true".equalsIgnoreCase(riu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
								addRequirementFor(repository, riu, null, required, errors, explicit, false);
						}
					}
					else
					{
						for(MappedUnit mu : repository.getUnits(true))
						{
							if(mu instanceof Category)
							{
								addCategoryContent(mu.getInstallableUnit(), repository, allIUs, required, errors,
										explicit);
								continue;
							}
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
							addRequirementFor(repository, mu.getInstallableUnit(), filter, required, errors, explicit,
									true);
						}
					}
				}
				if(errors.size() > 0)
				{
					errorsFound = true;
					builder.sendEmail(contrib, errors);
				}
				MonitorUtils.worked(subMon, 1);
			}
			if(errorsFound)
				return new Status(IStatus.ERROR, Engine.PLUGIN_ID, "Features without repositories");

			IRequiredCapability[] rcArr = new IRequiredCapability[required.size()];
			int idx = 0;
			for(Requirement rc : required.values())
				rcArr[idx++] = rc.capability;
			iu.setRequiredCapabilities(rcArr);
			mdr.addInstallableUnits(new IInstallableUnit[] { MetadataFactory.createInstallableUnit(iu) });
			return Status.OK_STATUS;
		}
		finally
		{
			MonitorUtils.done(subMon);
		}
	}

	private void addCategoryContent(InstallableUnit category, MappedRepository repository,
			List<InstallableUnit> allIUs, Map<String, Requirement> required, List<String> errors, Set<String> explicit)
	{
		// We don't map categories verbatim here. They are added elsewhere. We do
		// map their contents though.
		requirements: for(IRequiredCapability rc : category.getRequiredCapabilities())
		{
			for(InstallableUnit riu : allIUs)
			{
				if(riu.satisfies(rc))
				{
					if("true".equalsIgnoreCase(riu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
					{
						// Nested category
						addCategoryContent(riu, repository, allIUs, required, errors, explicit);
						continue requirements;
					}

					String filterStr = rc.getFilter();
					Filter filter = null;
					if(filterStr != null)
					{
						try
						{
							filter = FilterFactory.newInstance(filterStr);
						}
						catch(InvalidSyntaxException e)
						{
							throw new RuntimeException(e);
						}
					}
					addRequirementFor(repository, riu, filter, required, errors, explicit, false);
					continue requirements;
				}
			}

			// Categorized IU is not found
			//
			String error = format("Category %s includes a requirement for %s that cannot be fulfilled",
					category.getId(), rc);
			errors.add(error);
			Buckminster.getLogger().error(error);
		}
	}

	private void addRequirementFor(MappedRepository mr, InstallableUnit iu, Filter filter,
			Map<String, Requirement> requirements, List<String> errors, Set<String> explicit, boolean isExplicit)
	{
		Logger log = Buckminster.getLogger();
		String id = iu.getId();
		Version v = iu.getVersion();
		if(builder.excludeFromVerification(iu))
		{
			log.debug("%s/%s excluded from verification", id, v);
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
		IRequiredCapability rc = MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID, id, range,
				iuFilterStr, false, false);

		Requirement rq = new Requirement();
		rq.repository = mr;
		rq.capability = rc;
		Requirement old = requirements.put(id, rq);
		if(old == null || old.capability.equals(rc))
		{
			if(isExplicit)
				explicit.add(id);
			return;
		}

		Version oldVersion = old.capability.getRange().getMinimum();
		if(explicit.contains(id))
		{
			if(!isExplicit)
			{
				log.debug("%s/%s excluded since version %s is explicitly mapped", id, v, oldVersion);
				builder.addMappingExclusion(mr, new VersionedName(id, v));
				// Reinstate the old one
				requirements.put(id, old);
			}
			else
			{
				String error;
				if(v.equals(oldVersion))
					error = format("%s/%s is explicitly mapped more then once but with differnet configurations", id, v);
				else
					error = format("%s is explicitly mapped using both version %s and %s", id, v, oldVersion);
				errors.add(error);
				log.error(error);
			}
			return;
		}

		if(isExplicit)
		{
			log.debug("%s/%s excluded since version %s is explicitly mapped", id, oldVersion, v);
			builder.addMappingExclusion(old.repository, new VersionedName(id, oldVersion));
			explicit.add(id);
			return;
		}

		int cmp = v.compareTo(oldVersion);
		if(cmp < 0)
		{
			// The previous version was higher
			//
			builder.addMappingExclusion(mr, new VersionedName(id, v));
			log.debug("%s/%s excluded since a higher version (%s) was found", id, v, oldVersion);

			// Reinstate the old one
			requirements.put(id, old);
		}
		else
		{
			builder.addMappingExclusion(old.repository, new VersionedName(id, oldVersion));
			log.debug("%s/%s excluded since a higher version (%s) was found", id, oldVersion, v);
		}
	}
}
