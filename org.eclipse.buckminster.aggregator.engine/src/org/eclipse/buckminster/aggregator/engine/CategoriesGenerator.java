package org.eclipse.buckminster.aggregator.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.ExclusionRule;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl;
import org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl;
import org.eclipse.buckminster.aggregator.util.TimeUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;

public class CategoriesGenerator extends BuilderPhase
{
	public CategoriesGenerator(Builder builder)
	{
		super(builder);
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		long start = TimeUtils.getNow();
		MonitorUtils.begin(monitor, 10);
		String info = "Starting generation of categories";
		MonitorUtils.subTask(monitor, info);
		log.info(info);
		try
		{
			ArrayList<InstallableUnit> results = new ArrayList<InstallableUnit>();
			Aggregator aggregator = getBuilder().getAggregator();
			for(CustomCategory category : aggregator.getCustomCategories())
				results.add(createCategoryIU(category));

			MonitorUtils.worked(monitor, 5);
			for(Contribution contrib : aggregator.getContributions(true))
				for(MappedRepository repo : contrib.getRepositories(true))
					results.addAll(getRepositoryCategories(repo));
			getBuilder().setCategoryIUs(results);
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
		log.info("Done. Took %s", TimeUtils.getFormattedDuration(start)); //$NON-NLS-1$
	}

	private InstallableUnit createCategoryIU(CustomCategory category)
	{
		P2Factory factory = P2Factory.eINSTANCE;
		InstallableUnitImpl cat = (InstallableUnitImpl)factory.createInstallableUnit();
		cat.setSingleton(true);
		String categoryId = category.getIdentifier();
		cat.setId(categoryId);
		cat.setVersion(Version.emptyVersion);

		Map<String, String> props = cat.getPropertyMap().map();
		props.put(IInstallableUnit.PROP_NAME, category.getLabel());
		props.put(IInstallableUnit.PROP_DESCRIPTION, category.getDescription());
		props.put(IInstallableUnit.PROP_TYPE_CATEGORY, "true"); //$NON-NLS-1$

		List<RequiredCapability> rcs = cat.getRequiredCapabilityList();
		for(Feature feature : category.getFeatures())
		{
			if(!feature.isBranchEnabled())
				continue;

			InstallableUnit iu = feature.getInstallableUnit();
			RequiredCapabilityImpl rc = (RequiredCapabilityImpl)factory.createRequiredCapability();
			rc.setFilter(iu.getFilter());
			rc.setName(iu.getId());
			rc.setNamespace(IInstallableUnit.NAMESPACE_IU_ID);
			rc.setRange(new VersionRange(iu.getVersion(), true, iu.getVersion(), true));
			rcs.add(rc);
		}

		// Add self capability
		ProvidedCapabilityImpl pc = (ProvidedCapabilityImpl)factory.createProvidedCapability();
		pc.setName(categoryId);
		pc.setNamespace(IInstallableUnit.NAMESPACE_IU_ID);
		pc.setVersion(Version.emptyVersion);
		cat.getProvidedCapabilityList().add(pc);

		return cat;
	}

	private List<InstallableUnit> getRepositoryCategories(MappedRepository repo)
	{
		Builder builder = getBuilder();
		if(builder.isMapVerbatim(repo))
			// Meta-data is included as reference so all categories will be mapped
			//
			return Collections.emptyList();

		ArrayList<InstallableUnit> categoryIUs = new ArrayList<InstallableUnit>();
		if(repo.isMapExclusive())
		{
			for(Category category : repo.getCategories())
			{
				if(category.isEnabled())
				{
					InstallableUnit iu = category.getInstallableUnit();
					if(builder.isTopLevelCategory(iu))
						categoryIUs.add(iu);
				}
			}
		}
		else
		{
			List<MapRule> mapRules = repo.getMapRules();
			allIUs: for(InstallableUnit iu : repo.getMetadataRepository().getInstallableUnits())
			{
				if(builder.isTopLevelCategory(iu))
				{
					for(MapRule mapRule : mapRules)
						if(mapRule instanceof ExclusionRule && mapRule.getInstallableUnit() == iu)
							continue allIUs;
					categoryIUs.add(iu);
				}
			}
		}

		// Add all categories from this repository.
		//
		String categoryPrefix = Trivial.trim(repo.getCategoryPrefix());
		if(categoryPrefix != null)
		{
			// All requirements for categories must be renamed.
			//
			StringBuilder prefixConcat = new StringBuilder();
			prefixConcat.append(categoryPrefix);
			prefixConcat.append(' ');
			int prefixLen = prefixConcat.length();
			int idx = categoryIUs.size();
			while(--idx >= 0)
			{
				InstallableUnit iu = categoryIUs.get(idx);
				InstallableUnit renamedIU = InstallableUnitImpl.importToModel(iu);
				prefixConcat.setLength(prefixLen);
				prefixConcat.append(iu.getProperty(IInstallableUnit.PROP_NAME));
				renamedIU.getPropertyMap().map().put(IInstallableUnit.PROP_NAME, prefixConcat.toString());
				categoryIUs.set(idx, renamedIU);
			}
		}
		return categoryIUs;
	}
}
