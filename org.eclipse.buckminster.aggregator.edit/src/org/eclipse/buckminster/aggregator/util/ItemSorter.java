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

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2view.Feature;
import org.eclipse.buckminster.aggregator.p2view.IUPresentation;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.emf.ecore.EObject;

/**
 * Sorts items into several groups, new groups can be added
 * 
 * @author Karel Brezina
 */
public class ItemSorter
{
	public static enum ItemGroup
	{
		MDR, IU, FEATURE, MDR_STRUCTURED, IU_STRUCTURED, FEATURE_STRUCTURED, OTHER
	}

	private final Map<ItemGroup, List<?>> m_groups = new HashMap<ItemGroup, List<?>>();

	private int m_totalItemCount;

	public ItemSorter(Collection<?> items)
	{
		List<InstallableUnit> ius = new ArrayList<InstallableUnit>();
		List<InstallableUnit> features = new ArrayList<InstallableUnit>();
		List<MetadataRepository> mdrs = new ArrayList<MetadataRepository>();

		List<IUPresentation> iups = new ArrayList<IUPresentation>();
		List<Feature> structuredFeatures = new ArrayList<Feature>();
		List<MetadataRepositoryStructuredView> mdrsvs = new ArrayList<MetadataRepositoryStructuredView>();

		List<Object> others = new ArrayList<Object>();

		for(Object item : items)
		{
			m_totalItemCount++;

			if(item instanceof InstallableUnit)
			{
				InstallableUnit iu = (InstallableUnit)item;
				if(((EObject)iu).eContainer() instanceof MetadataRepository
						&& InstallableUnitUtils.getType(iu) != InstallableUnitType.OTHER)
				{
					ius.add(iu);

					if(InstallableUnitUtils.getType(iu) == InstallableUnitType.FEATURE)
						features.add(iu);
				}
				else
					others.add(item);
			}
			else if(item instanceof MetadataRepository)
				mdrs.add((MetadataRepository)item);
			else if(item instanceof IUPresentation)
			{
				IUPresentation iup = (IUPresentation)item;
				if(iup.getType() != InstallableUnitType.OTHER)
				{
					iups.add(iup);

					if(iup.getType() == InstallableUnitType.FEATURE)
						structuredFeatures.add((Feature)iup);
				}
				else
					others.add(item);
			}
			else if(item instanceof MetadataRepositoryStructuredView)
				mdrsvs.add((MetadataRepositoryStructuredView)item);
			else
				others.add(item);
		}

		m_groups.put(ItemGroup.MDR, mdrs);
		m_groups.put(ItemGroup.IU, ius);
		m_groups.put(ItemGroup.FEATURE, features);
		m_groups.put(ItemGroup.MDR_STRUCTURED, mdrsvs);
		m_groups.put(ItemGroup.IU_STRUCTURED, iups);
		m_groups.put(ItemGroup.FEATURE_STRUCTURED, structuredFeatures);
		m_groups.put(ItemGroup.OTHER, others);
	}

	public List<?> getGroupItems(ItemGroup group)
	{
		return m_groups.get(group);
	}

	public int getTotalItemCount()
	{
		return m_totalItemCount;
	}
}
