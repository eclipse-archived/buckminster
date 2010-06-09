/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.ExclusionRule;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.ValidConfigurationsRule;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Karel Brezina
 * 
 */
public class AddIUsToParentRepositoryCommand extends AbstractCommand
{
	private Aggregator m_aggregator;

	private List<InstallableUnit> m_selectedIUs;

	private int m_operation;

	private Map<InstallableUnit, MappedRepository> m_mapIUMappedRepo = new HashMap<InstallableUnit, MappedRepository>();

	private Map<MappedRepository, List<MappedUnit>> m_unitsAddedToMappedRepo = new HashMap<MappedRepository, List<MappedUnit>>();

	private Map<MappedRepository, List<MapRule>> m_rulesAddedToMappedRepo = new HashMap<MappedRepository, List<MapRule>>();

	public AddIUsToParentRepositoryCommand(Aggregator aggregator, List<InstallableUnit> selectedIUs, int operation)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Add_to_parent_Mapped_Repository"));

		m_aggregator = aggregator;
		m_selectedIUs = selectedIUs;
		m_operation = operation;
	}

	public void execute()
	{
		m_unitsAddedToMappedRepo.clear();
		m_rulesAddedToMappedRepo.clear();

		for(InstallableUnit iu : m_selectedIUs)
		{
			MappedRepository repo = m_mapIUMappedRepo.get(iu);

			if(!repo.isEnabled())
				continue;

			if((m_operation & AggregatorEditPlugin.ADD_IU) > 0)
			{
				MappedUnit unit = ItemUtils.findMappedUnit(repo, iu);

				if(unit == null)
				{
					unit = AggregatorFactory.eINSTANCE.createMappedUnit(iu);
					repo.addUnit(unit);

					List<MappedUnit> units = m_unitsAddedToMappedRepo.get(repo);
					if(units == null)
					{
						units = new ArrayList<MappedUnit>();
						m_unitsAddedToMappedRepo.put(repo, units);
					}
					units.add(unit);
				}
			}
			else if((m_operation & (AggregatorEditPlugin.ADD_EXCLUSION_RULE | AggregatorEditPlugin.ADD_VALID_CONFIGURATIONS_RULE)) > 0)
			{
				MapRule rule = ItemUtils.findMapRule(repo, iu);

				if(rule == null)
				{
					rule = AggregatorFactory.eINSTANCE.createMapRule(iu,
							(m_operation & AggregatorEditPlugin.ADD_EXCLUSION_RULE) > 0
									? ExclusionRule.class
									: ValidConfigurationsRule.class);
					repo.getMapRules().add(rule);

					List<MapRule> rules = m_rulesAddedToMappedRepo.get(repo);
					if(rules == null)
					{
						rules = new ArrayList<MapRule>();
						m_rulesAddedToMappedRepo.put(repo, rules);
					}
					rules.add(rule);
				}
			}
		}

	}

	public void redo()
	{
		for(MappedRepository mappedRepo : m_unitsAddedToMappedRepo.keySet())
		{
			for(MappedUnit unit : m_unitsAddedToMappedRepo.get(mappedRepo))
				mappedRepo.removeUnit(unit);

			for(MapRule rule : m_rulesAddedToMappedRepo.get(mappedRepo))
				mappedRepo.getMapRules().remove(rule);
		}
	}

	@Override
	protected boolean prepare()
	{
		boolean someEnabled = false;

		for(InstallableUnit iu : m_selectedIUs)
		{
			if(!(((EObject)iu).eContainer() instanceof MetadataRepository))
				return false;

			MetadataRepository mdr = (MetadataRepository)((EObject)iu).eContainer();
			MappedRepository mappedRepo = ItemUtils.findMappedRepository(m_aggregator, mdr);

			if(mappedRepo == null)
				continue;

			if(ItemUtils.findMappedUnit(mappedRepo, iu) != null || ItemUtils.findMapRule(mappedRepo, iu) != null)
				return false;

			m_mapIUMappedRepo.put(iu, mappedRepo);
			someEnabled = someEnabled || mappedRepo.isBranchEnabled();
		}

		return m_mapIUMappedRepo.size() > 0 && someEnabled;
	}

}
