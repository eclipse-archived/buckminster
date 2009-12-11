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
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
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

	private Map<InstallableUnit, MappedRepository> m_mapIUMappedRepo = new HashMap<InstallableUnit, MappedRepository>();

	private Map<MappedRepository, List<MappedUnit>> m_unitsAddedToMappedRepo = new HashMap<MappedRepository, List<MappedUnit>>();

	public AddIUsToParentRepositoryCommand(Aggregator aggregator, List<InstallableUnit> selectedIUs)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Add_to_parent_Mapped_Repository"));

		m_aggregator = aggregator;
		m_selectedIUs = selectedIUs;
	}

	public void execute()
	{
		m_unitsAddedToMappedRepo.clear();

		for(InstallableUnit iu : m_selectedIUs)
		{
			MappedRepository repo = m_mapIUMappedRepo.get(iu);

			if(!repo.isEnabled())
				continue;

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

	}

	public void redo()
	{
		for(MappedRepository mappedRepo : m_unitsAddedToMappedRepo.keySet())
			for(MappedUnit unit : m_unitsAddedToMappedRepo.get(mappedRepo))
				mappedRepo.removeUnit(unit);
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

			m_mapIUMappedRepo.put(iu, mappedRepo);
			someEnabled = someEnabled || mappedRepo.isBranchEnabled();
		}

		return m_mapIUMappedRepo.size() > 0 && someEnabled;
	}

}
