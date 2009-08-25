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
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DragAndDropFeedback;

/**
 * @author Karel Brezina
 * 
 */
public class MapToContributionCommand extends AbstractCommand implements DragAndDropFeedback
{
	private Contribution m_contribution;

	private List<MetadataRepository> m_selectedMDRs;

	private List<InstallableUnit> m_selectedIUs;

	private List<MappedRepository> m_addedMappedRepos = new ArrayList<MappedRepository>();

	private List<MappedUnit> m_addedMappedUnits = new ArrayList<MappedUnit>();

	public MapToContributionCommand(Contribution contribution, List<MetadataRepository> selectedMDRs,
			List<InstallableUnit> selectedIUs)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Map_to_command_prefix")
				+ " "
				+ ((contribution.getLabel() == null || contribution.getLabel().length() == 0)
						? AggregatorEditPlugin.INSTANCE.getString("_UI_Contribution_type") + " ''"
						: AggregatorEditPlugin.INSTANCE.getString("_UI_Contribution_type") + " "
								+ contribution.getLabel()));

		m_contribution = contribution;
		m_selectedMDRs = selectedMDRs;
		m_selectedIUs = selectedIUs;
	}

	public void execute()
	{
		m_addedMappedRepos.clear();
		m_addedMappedUnits.clear();

		for(MetadataRepository mdr : m_selectedMDRs)
		{
			MappedRepository mappedRepo = AggregatorFactory.eINSTANCE.createMappedRepository(mdr);
			if(ItemUtils.findMappedRepository(m_contribution.getRepositories(), mappedRepo) == null)
			{
				m_contribution.getRepositories().add(mappedRepo);
				m_addedMappedRepos.add(mappedRepo);
			}
		}

		for(InstallableUnit iu : m_selectedIUs)
		{
			if(!(((EObject)iu).eContainer() instanceof MetadataRepository))
				return;

			MetadataRepository mdr = (MetadataRepository)((EObject)iu).eContainer();

			MappedRepository newMappedRepo = AggregatorFactory.eINSTANCE.createMappedRepository(mdr);
			MappedRepository mappedRepo = ItemUtils.findMappedRepository(m_contribution.getRepositories(),
					newMappedRepo);
			if(mappedRepo == null)
			{
				m_contribution.getRepositories().add(newMappedRepo);
				m_addedMappedRepos.add(newMappedRepo);
				mappedRepo = newMappedRepo;
			}

			MappedUnit newMU = AggregatorFactory.eINSTANCE.createMappedUnit(iu);
			MappedUnit mu = ItemUtils.findMappedUnit(mappedRepo.getUnits(false), newMU);
			if(mu == null)
			{
				mappedRepo.addUnit(newMU);
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

		m_contribution.getRepositories().removeAll(m_addedMappedRepos);
	}

	// validated prior command creation
	public boolean validate(Object owner, float location, int operations, int operation, Collection<?> collection)
	{
		return true;
	}

	@Override
	protected boolean prepare()
	{
		return m_contribution != null
				&& m_contribution.isEnabled()
				&& (m_selectedMDRs != null && m_selectedMDRs.size() > 0 || m_selectedIUs != null
						&& m_selectedIUs.size() > 0);
	}
}
