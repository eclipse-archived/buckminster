/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.aggregator.ExclusionRule;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.ValidConfigurationsRule;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DragAndDropFeedback;

/**
 * @author Karel Brezina
 * 
 */
public class AddIUsToMappedRepositoryCommand extends AbstractCommand implements DragAndDropFeedback
{
	private MappedRepository m_mappedRepo;

	private List<InstallableUnit> m_selectedIUs;

	private int m_operation;

	private List<MappedUnit> m_addedMappedUnits = new ArrayList<MappedUnit>();

	private List<MapRule> m_addedMapRules = new ArrayList<MapRule>();

	public AddIUsToMappedRepositoryCommand(MappedRepository mappedRepo, List<InstallableUnit> selectedIUs)
	{
		this(mappedRepo, selectedIUs, AggregatorEditPlugin.ADD_IU);
	}

	public AddIUsToMappedRepositoryCommand(MappedRepository mappedRepo, List<InstallableUnit> selectedIUs, int operation)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Map_to_command_prefix") + " "
				+ AggregatorEditPlugin.INSTANCE.getString("_UI_MappedRepository_type") + " " + mappedRepo.getLocation());

		m_mappedRepo = mappedRepo;
		m_selectedIUs = selectedIUs;
		m_operation = operation;
	}

	public void execute()
	{
		m_addedMappedUnits.clear();
		m_addedMapRules.clear();

		if((m_operation & AggregatorEditPlugin.ADD_IU) > 0)
			for(InstallableUnit iu : m_selectedIUs)
			{
				MappedUnit newMU = ItemUtils.addIU(m_mappedRepo, iu);
				if(newMU != null)
					m_addedMappedUnits.add(newMU);
			}
		else if((m_operation & (AggregatorEditPlugin.ADD_EXCLUSION_RULE | AggregatorEditPlugin.ADD_VALID_CONFIGURATIONS_RULE)) > 0)
			for(InstallableUnit iu : m_selectedIUs)
			{
				MapRule newMR = ItemUtils.addMapRule(m_mappedRepo, iu,
						(m_operation & AggregatorEditPlugin.ADD_EXCLUSION_RULE) > 0
								? ExclusionRule.class
								: ValidConfigurationsRule.class);
				if(newMR != null)
					m_addedMapRules.add(newMR);
			}
	}

	public int getFeedback()
	{
		return FEEDBACK_SELECT;
	}

	public int getOperation()
	{
		return DROP_LINK;
	}

	public void redo()
	{
		execute();
	}

	@Override
	public void undo()
	{
		for(MappedUnit unit : m_addedMappedUnits)
		{
			MappedRepository repo = (MappedRepository)((EObject)unit).eContainer();
			repo.removeUnit(unit);
		}

		for(MapRule rule : m_addedMapRules)
		{
			MappedRepository repo = (MappedRepository)((EObject)rule).eContainer();
			repo.getMapRules().remove(rule);
		}
	}

	// validated prior command creation
	public boolean validate(Object owner, float location, int operations, int operation, Collection<?> collection)
	{
		return true;
	}

	@Override
	protected boolean prepare()
	{
		boolean result = m_mappedRepo != null && m_mappedRepo.isBranchEnabled() && m_selectedIUs != null
				&& m_selectedIUs.size() > 0 && ItemUtils.haveSameLocation(m_mappedRepo, m_selectedIUs);

		if(result)
			for(InstallableUnit iu : m_selectedIUs)
				if(ItemUtils.findMappedUnit(m_mappedRepo, iu) != null
						|| ItemUtils.findMapRule(m_mappedRepo, iu) != null)
					return false;

		return result;
	}
}
