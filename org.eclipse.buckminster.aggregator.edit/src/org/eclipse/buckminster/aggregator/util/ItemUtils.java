/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.List;

import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Karel Brezina
 * 
 */
public class ItemUtils
{

	/**
	 * Searches for a MappedRepository with the same location
	 * 
	 * @param mappedRepos
	 * @param mappedRepo
	 * @return
	 */
	public static MappedRepository findMappedRepository(List<MappedRepository> mappedRepos, MappedRepository mappedRepo)
	{
		for(MappedRepository repo : mappedRepos)
			if(mappedRepo == repo || mappedRepo.getLocation() != null && repo.getLocation() != null
					&& mappedRepo.getLocation().equalsIgnoreCase(repo.getLocation()))
				return repo;

		return null;
	}

	/**
	 * Searches for a MappedUnit with the same ID
	 * 
	 * @param mappedUnits
	 * @param mappedUnit
	 * @return
	 */
	public static MappedUnit findMappedUnit(EList<MappedUnit> mappedUnits, MappedUnit mappedUnit)
	{
		for(MappedUnit unit : mappedUnits)
			if(mappedUnit == unit
					|| mappedUnit.getInstallableUnit() != null
					&& unit.getInstallableUnit() != null
					&& (mappedUnit.getInstallableUnit() == unit.getInstallableUnit() || mappedUnit.getInstallableUnit().getId() != null
							&& unit.getInstallableUnit().getId() != null
							&& mappedUnit.getInstallableUnit().getId().equals(unit.getInstallableUnit().getId())))
				return unit;

		return null;
	}

	/**
	 * Tests if selectedUIs come from a MDR with the same location as mappedRepo
	 * 
	 * @param mappedRepo
	 * @param selectedIUs
	 * @return
	 */
	public static boolean haveSameLocation(MappedRepository mappedRepo, List<InstallableUnit> selectedIUs)
	{
		String location = mappedRepo.getLocation();

		if(location == null)
			return false;

		for(InstallableUnit iu : selectedIUs)
		{
			if(!(((EObject)iu).eContainer() instanceof MetadataRepository))
				return false;
			MetadataRepository mdr = (MetadataRepository)((EObject)iu).eContainer();
			if(mdr.getLocation() == null)
				return false;
			if(!(location.equalsIgnoreCase(mdr.getLocation().toString())))
				return false;
		}

		return true;
	}
}
