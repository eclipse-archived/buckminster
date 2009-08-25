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
import org.eclipse.buckminster.aggregator.p2.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
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
		MDR, IU, OTHER
	}

	private final Map<ItemGroup, List<?>> m_groups = new HashMap<ItemGroup, List<?>>();

	private int m_totalItemCount;

	public ItemSorter(Collection<?> items)
	{
		List<InstallableUnit> ius = new ArrayList<InstallableUnit>();
		List<MetadataRepository> mdrs = new ArrayList<MetadataRepository>();
		List<Object> others = new ArrayList<Object>();

		for(Object item : items)
		{
			m_totalItemCount++;

			if(item instanceof InstallableUnit)
			{
				InstallableUnit iu = (InstallableUnit)item;
				if(((EObject)iu).eContainer() instanceof MetadataRepository
						&& iu.getType() != InstallableUnitType.OTHER)
					ius.add((InstallableUnit)item);
				else
					others.add(item);
			}
			else if(item instanceof MetadataRepository)
				mdrs.add((MetadataRepository)item);
			else
				others.add(item);
		}

		m_groups.put(ItemGroup.MDR, mdrs);
		m_groups.put(ItemGroup.IU, ius);
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
