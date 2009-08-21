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
 * @author Karel Brezina
 * 
 */
public class ItemUtils
{
	public static final String GROUP_MDR = "MDR";

	public static final String GROUP_IU = "IU";

	public static final String GROUP_OTHER = "OTHER";

	public static Map<String, List<?>> groupItems(Collection<?> items)
	{
		List<InstallableUnit> selectedIUs = new ArrayList<InstallableUnit>();
		List<MetadataRepository> selectedMDRs = new ArrayList<MetadataRepository>();
		List<Object> selectedOthers = new ArrayList<Object>();

		for(Object item : items)
		{
			if(item instanceof InstallableUnit)
			{
				InstallableUnit iu = (InstallableUnit)item;
				if(((EObject)iu).eContainer() instanceof MetadataRepository
						&& iu.getType() != InstallableUnitType.OTHER)
					selectedIUs.add((InstallableUnit)item);
				else
					selectedOthers.add(item);
			}
			else if(item instanceof MetadataRepository)
				selectedMDRs.add((MetadataRepository)item);
			else
				selectedOthers.add(item);
		}

		Map<String, List<?>> selectionGroups = new HashMap<String, List<?>>();
		selectionGroups.put(GROUP_MDR, selectedMDRs);
		selectionGroups.put(GROUP_IU, selectedIUs);
		selectionGroups.put(GROUP_OTHER, selectedOthers);

		return selectionGroups;
	}

}
