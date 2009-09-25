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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DragAndDropFeedback;

/**
 * @author Karel Brezina
 * 
 */
public class AddToCustomCategoryCommand extends AbstractCommand implements DragAndDropFeedback
{
	private CustomCategory m_customCategory;

	private Map<InstallableUnit, MappedRepository> m_mapFeatureMappedRepo = new HashMap<InstallableUnit, MappedRepository>();

	private List<InstallableUnit> m_selectedFeatures;

	private List<Feature> m_featuresAddedToCustomCategory = new ArrayList<Feature>();

	private Map<MappedRepository, List<MappedUnit>> m_unitsAddedToMappedRepo = new HashMap<MappedRepository, List<MappedUnit>>();

	public AddToCustomCategoryCommand(CustomCategory category, List<InstallableUnit> selectedFeatures)
	{
		super(AggregatorEditPlugin.INSTANCE.getString("_UI_Add_to_command_prefix") + " "
				+ ((category.getLabel() == null || category.getLabel().length() == 0)
						? AggregatorEditPlugin.INSTANCE.getString("_UI_Category_type") + " ''"
						: AggregatorEditPlugin.INSTANCE.getString("_UI_Category_type") + " " + category.getLabel()));

		m_customCategory = category;
		m_selectedFeatures = selectedFeatures;
	}

	public void execute()
	{
		m_featuresAddedToCustomCategory.clear();
		m_unitsAddedToMappedRepo.clear();

		for(InstallableUnit feature : m_selectedFeatures)
		{
			MappedRepository repo = m_mapFeatureMappedRepo.get(feature);

			if(!repo.isEnabled())
				continue;

			MappedUnit unit = ItemUtils.findMappedUnit(repo, feature);

			if(unit == null)
			{
				unit = AggregatorFactory.eINSTANCE.createMappedUnit(feature);
				repo.addUnit(unit);

				List<MappedUnit> units = m_unitsAddedToMappedRepo.get(repo);
				if(units == null)
				{
					units = new ArrayList<MappedUnit>();
					m_unitsAddedToMappedRepo.put(repo, units);
				}
				units.add(unit);
			}
			else if(!unit.isEnabled())
				continue;

			if(unit instanceof Feature)
			{
				m_customCategory.getFeatures().add((Feature)unit);
				m_featuresAddedToCustomCategory.add((Feature)unit);
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
		m_customCategory.getFeatures().removeAll(m_featuresAddedToCustomCategory);

		for(MappedRepository mappedRepo : m_unitsAddedToMappedRepo.keySet())
			for(MappedUnit unit : m_unitsAddedToMappedRepo.get(mappedRepo))
				mappedRepo.removeUnit(unit);
	}

	// validated prior command creation
	public boolean validate(Object owner, float location, int operations, int operation, Collection<?> collection)
	{
		return true;
	}

	@Override
	protected boolean prepare()
	{
		Aggregator aggregator = (Aggregator)((EObject)m_customCategory).eContainer();

		for(InstallableUnit feature : m_selectedFeatures)
		{
			if(!(((EObject)feature).eContainer() instanceof MetadataRepository))
				return false;

			MetadataRepository mdr = (MetadataRepository)((EObject)feature).eContainer();

			MappedRepository mappedRepo = ItemUtils.findMappedRepository(aggregator, mdr);

			m_mapFeatureMappedRepo.put(feature, mappedRepo);
		}

		return m_customCategory != null && m_selectedFeatures != null && m_selectedFeatures.size() > 0 && isEnabled();
	}

	private boolean isEnabled()
	{
		for(InstallableUnit feature : m_mapFeatureMappedRepo.keySet())
		{
			MappedRepository repo = m_mapFeatureMappedRepo.get(feature);

			MappedUnit unit = ItemUtils.findMappedUnit(repo, feature);

			if(unit != null && !unit.isEnabled() || unit == null && repo != null && !repo.isEnabled())
				return false;
		}

		return true;
	}
}
