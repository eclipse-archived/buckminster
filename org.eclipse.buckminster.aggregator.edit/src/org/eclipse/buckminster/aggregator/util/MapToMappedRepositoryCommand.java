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

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.command.DragAndDropFeedback;

/**
 * @author Karel Brezina
 * 
 */
public class MapToMappedRepositoryCommand extends AbstractCommand implements DragAndDropFeedback
{
	private MappedRepository m_mappedRepo;

	private List<InstallableUnit> m_selectedIUs;

	private List<MappedUnit> m_addedMappedUnits = new ArrayList<MappedUnit>();

	public MapToMappedRepositoryCommand(MappedRepository mappedRepo, List<InstallableUnit> selectedIUs)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Map_to_command_prefix") + " "
				+ AggregatorEditPlugin.INSTANCE.getString("_UI_MappedRepository_type") + " " + mappedRepo.getLocation());

		m_mappedRepo = mappedRepo;
		m_selectedIUs = selectedIUs;
	}

	public void execute()
	{
		m_addedMappedUnits.clear();

		for(InstallableUnit iu : m_selectedIUs)
		{
			MappedUnit newMU = AggregatorFactory.eINSTANCE.createMappedUnit(iu);
			MappedUnit mu = ItemUtils.findMappedUnit(m_mappedRepo.getUnits(false), newMU);
			if(mu == null)
			{
				m_mappedRepo.addUnit(newMU);
				m_addedMappedUnits.add(newMU);
			}
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
			MappedRepository repo = (MappedRepository)unit.eContainer();
			repo.removeUnit(unit);
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
		return m_mappedRepo != null && m_mappedRepo.isBranchEnabled() && m_selectedIUs != null
				&& m_selectedIUs.size() > 0 && ItemUtils.haveSameLocation(m_mappedRepo, m_selectedIUs);
	}
}
